package struts2maven.Struts2MavenTest002.action;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import struts2maven.Struts2MavenTest002.model.FileDownload;
import struts2maven.Struts2MavenTest002.service.CommonService;
import struts2maven.Struts2MavenTest002.service.FileDownloadService;
import struts2maven.Struts2MavenTest002.service.Common;

/**
 * ファイルアップロード
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
//インターセプター定義
@InterceptorRefs({
	@InterceptorRef("customStack")
})
//遷移先定義
@Results({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "download", location = "fileDownload", type = "stream",
			params = {
				"inputName",
				"inputStream",
				"contentType",
				"application/octet-stream; charset=UTF-8",
				"contentLength",
				"${contentLength}",
				"contentDisposition",
				"attachment; filename = ${fileName};filename*=utf-8''${encodedFileName}"
			}
	),
	@Result(name = "none", location = "login", type = "redirect"),
	@Result(name = "success", location = "fileDownload.jsp"),
	@Result(name = "input", location = "fileDownload.jsp")
})
//例外処理定義
@ExceptionMappings({
	@ExceptionMapping(exception="java.lang.Exception" , result="exception")
})
//Spring3設定
//Controllerとして動作
@Controller

//Spring3設定
//Actionクラスのスコープ設定
@Scope("prototype")
public class FileDownloadAction extends BaseAction {

	public static final Logger log = Logger.getRootLogger();
	private LinkedList<String> errorMessages = new LinkedList<String>();
	private Map<String, String> selectMap = new HashMap<String, String>();

	private FileDownload fileDownloadBean;
	public FileDownload getFileDownloadBean() {
		return fileDownloadBean;
	}
	public void setFileDownloadBean(FileDownload fileDownloadBean) {
		this.fileDownloadBean = fileDownloadBean;
	}

	private Long contentLength;
	private String fileName;
	private InputStream inputStream;

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}
	public Long getContentLength() {
		return contentLength;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	// FileDownloadServiceの取得
    @Resource
    FileDownloadService service;
	public FileDownloadService getService() {
		return service;
	}
	public void setService(FileDownloadService service) {
		this.service = service;
	}
	// CommonServiceの取得
    @Resource
    CommonService commonService;
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Action("fileDownload")
	public String execute() throws Exception {
		// セッション判定(テーブル名一覧)
		if (session != null) {
			if (session.get("selectMap") == null) {
				// テーブル名取得
				selectMap = commonService.selectTableList();
				// セッションに格納
				session.put("selectMap", selectMap);
			}
		}
		// メッセージクリア
		clearBef();
		return SUCCESS;
	}
	@Action("downloadStart")
	public String download() throws Exception {

		// ダウンロードするテーブル名取得
		String tableName = fileDownloadBean.getSelectTable();
		// ★Pending 取得する条件取得
		String condition = "";
		// ヘッダ取得
		String getHeadString = "table.colmn." + tableName;
		String column = getText(getHeadString);
		// ★Pending ヘッダONOFFフラグ
		String colFlg = "1";
		// ★Pending ファイルの拡張子取得
		String fileExtension = ".csv";
		// ★Pending セパレーター
		String separator = ",";
		// 一時ファイルパス
		String pathTemp = getText("filePath.tmp");
		// 一時ファイルフルパス
		StringBuilder bufPathFile = new StringBuilder();
		// 削除対象ファイルパス格納
		ArrayList<String> delFile = new ArrayList<String>();

		bufPathFile.append(pathTemp);
		bufPathFile.append(session.get("userid"));
		bufPathFile.append("_");
		bufPathFile.append(tableName);
		bufPathFile.append(fileExtension);
		//String pathTempFile = pathTemp + session.get("userid") + tableName + fileExtension;
		// テーブルデータ
		List<Object> dataList = new ArrayList<Object>();
		// テーブルデータ取得サービス
		dataList = service.selectDownloadTable(tableName, column, condition);
		if (dataList.size() > 0) {
			// ファイル作成
			Common.makeFile(bufPathFile.toString(), column, colFlg, separator, dataList);

			// ファイルオブジェクト作成
			File file = new File(bufPathFile.toString());
			setInputStream(new BufferedInputStream(new FileInputStream(file)));
			setFileName(URLEncoder.encode(file.getName(), "UTF-8"));
			setContentLength(file.length());

			// 削除対象ファイル格納
			if (session.get("DELFILE") != null) {
				delFile = (ArrayList<String>) session.get("DELFILE");
			}
			delFile.add(bufPathFile.toString());
			session.put("DELFILE", delFile);

			// クリア
			bufPathFile = null;
			// ★Pending メッセージ出ない
			//addActionMessage(getText("fileDownload.download.success"));
		} else {
			errorMessages.add(getText("fileDownload.download.dateList.exist"));
			setActionErrors(errorMessages);
			return INPUT;
		}
		// ダウンロード処理を実行
		return "download" ;
	}
}
