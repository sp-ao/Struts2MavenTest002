package struts2maven.Struts2MavenTest002.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import struts2maven.Struts2MavenTest002.model.FileUpload;
import struts2maven.Struts2MavenTest002.model.Login;
import struts2maven.Struts2MavenTest002.service.Common;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

/**
 * ファイルアップロード
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
//遷移先定義
@Results({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "upload", location = "fileUpload", type="redirect"),
	@Result(name = "none", location = "login", type = "redirect"),
	@Result(name = "success", location = "fileUpload.jsp"),
	@Result(name = "input", location = "fileUpload.jsp")
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
public class FileUploadAction extends BaseAction {

	public static final Logger log = Logger.getRootLogger();

	private FileUpload fileUploadBean;
	public FileUpload getFileUploadBean() {
		return fileUploadBean;
	}
	public void setFileUploadBean(FileUpload fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}

	@Action("fileUpload")
	public String execute() throws Exception {
		// 他画面から遷移してきた場合
		if (session != null) {
			if (session.get("action") != null) {
				if (session.get("action") !=
					this.getClass().getName().toString()) {
					// メッセージクリア
					clearBef();
				}
			}
		}
		return SUCCESS;
	}
	@Action("fileUpload-Upload")
	public String upload() throws Exception {

		String msg = "";
		// メッセージ初期化
		clearBef();
		// ファイルが存在する場合
		if (fileUploadBean != null) {
			if (fileUploadBean.getDocument() != null &&
				fileUploadBean.getDocument().exists()) {
				// ファイル名取得
				msg = fileUploadBean.getDocumentFileName();
				// アップロード先のパス取得
				String filePath = getText("fileUpload.updFilePath");
				// ファイルオブジェクト作成
				File f = new File(filePath + fileUploadBean.getDocumentFileName());
				// ファイルをコピー
				FileUtils.copyFile(fileUploadBean.getDocument().getAbsoluteFile(), f);
				// メッセージ設定
				System.out.println(fileUploadBean.getDocumentContentType() + " " + fileUploadBean.getDocumentFileName());
				addActionMessage(msg + getText("fileUpload.upload.success"));
				return "upload";
			} else {
				// メッセージ設定
				System.out.println(fileUploadBean.getDocumentContentType() + " " + fileUploadBean.getDocumentFileName());
				addActionMessage(msg + getText("fileUpload.upload.failure"));
			}
		}
		return INPUT;
	}
}
