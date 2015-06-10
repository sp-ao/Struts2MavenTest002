package struts2maven.Struts2MavenTest002.action;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import struts2maven.Struts2MavenTest002.model.FileUpload;
import struts2maven.Struts2MavenTest002.model.ItemList;
import struts2maven.Struts2MavenTest002.service.Common;
import struts2maven.Struts2MavenTest002.service.ItemListService;

/**
 * 商品リスト
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
// 遷移先定義
@Results({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "input", location = "itemList", type = "redirect"),
	@Result(name = "none", location = "login", type = "redirect"),
	@Result(name = "confirm", location = "confirm", type = "chain"),
	@Result(name = "success", location = "itemList.jsp"),
	@Result(name = "insert", location = "itemList", type = "redirect"),
	@Result(name = "update", location = "itemList", type = "redirect")
})
// 例外処理定義
@ExceptionMappings({
	@ExceptionMapping(exception="java.lang.Exception" , result="exception")
})
// Spring3設定。
// Controllerとして動作
@Controller

// Spring3設定。
// Actionクラスのスコープ設定
@Scope("prototype")
/**
* <code>ユーザー一覧画面</code>
*/
public class ItemListAction extends BaseAction {

	// メッセージ
	private LinkedList<String> messagesList = new LinkedList<String>();
	// 結果格納
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	// 戻り値
	private String ret = INPUT;

	// Bean
	private List<ItemList> itemList;
	public List<ItemList> getItemList() {
		return itemList;
	}
	@VisitorFieldValidator(message="")
	public void setItemList(List<ItemList> itemList) {
		this.itemList = itemList;
	}
	// Bean
	private FileUpload fileUploadBean;
	public FileUpload getFileUploadBean() {
		return fileUploadBean;
	}
	public void setFileUploadBean(FileUpload fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}
	// Bean
	private ItemList itemListBean;
	public ItemList getItemListBean() {
		return itemListBean;
	}
	public void setItemListBean(ItemList itemListBean) {
		// メッセージクリア
		Common.clearBefMessage();
		this.itemListBean = itemListBean;
	}

	// Service
    @Resource
    ItemListService service;
	public ItemListService getService() {
		return service;
	}
	public void setService(ItemListService service) {
		this.service = service;
	}

	// メイン画面
	@Action("itemList")
	@SkipValidation
	public String excute() throws Exception {
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
		// 商品リストデータ取得サービス
		itemList = service.selectItemList();
		// 取得出来なかった場合
		if (itemList.size() < 1) {
			// メッセージ設定
			messagesList.add(getText("itemList.select.failure"));
			setActionErrors(messagesList);
		}
		// セッションに商品リストを設定
		session.put("ItemList", itemList);
		// 戻り値
		return SUCCESS;
	}

	// 商品リスト作成
	@Action("insert_item")
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String ItemListUpload() throws Exception {
		// メッセージ初期化
		clearBef();
		// エラーカウント
		Integer errCount = 0;
		// 処理行数
		String insertCount = "";

		// ファイルが存在する場合
		if (fileUploadBean != null) {
			if (fileUploadBean.getDocument() != null &&
				fileUploadBean.getDocument().exists()) {

				// CSVデータ取得
				ICsvBeanReader inFile = new CsvBeanReader(
					new FileReader(
						fileUploadBean.getDocument().getAbsoluteFile()),
						CsvPreference.EXCEL_PREFERENCE
				);

				// CSVデータチェック
				resultMap = service.chkItemList(inFile, itemListBean);
				errCount = (Integer)resultMap.get("ERRCNT");
				insertCount = resultMap.get("INSERTCNT").toString();

				// メッセージ件数判定
				if (errCount > 0) {
					// メッセージ設定
					messagesList = (LinkedList<String>) resultMap.get("ERRMSG");
					setActionErrors(messagesList);
					return ret;
				} else {
					// 商品リスト作成サービス
					ret = service.insertItemList(resultMap);
					// 戻り値判定
					if (ret.equals("insert")) {
						// メッセージ設定
						addActionMessage(getText("itemList.insert.success", new String[]{insertCount}));
						return ret;
					}
				}
			}
		}
		// メッセージ設定
		messagesList.add(getText("itemList.insert.failure"));
		setActionErrors(messagesList);
		return ret;
	}
	// 商品リスト更新
	@Action("update_item")
	@SuppressWarnings("unchecked")
	public String UpdateItem() throws Exception {
		// メッセージ初期化
		clearBef();
		// 画面値判定
		if (session != null) {
			if (session.get("OUTPUT_LIST") != null) {
				// 商品リスト更新
				ret = service.updateItemList(
						(List<Object>)session.get("INPUT_LIST"));
				if (ret.equals("update")) {
					// メッセージ設定
					addActionMessage(getText("itemList.update.success"));
					return ret;
				}
			}
		}
		// メッセージ設定
		messagesList.add(getText("itemList.update.failure"));
		setActionErrors(messagesList);
		// 戻り値
		return ret;
	}
	// 商品リスト更新確認
	@Action("item_confim")
	public String ItemConfrm() throws Exception {
		// メッセージ初期化
		clearBef();
		// 更新行数
		Integer count = 0;
		// 表示データ取得
		List<Object> outItemArr = new ArrayList<Object>();
		// 更新データ取得
		List<Object> inpItemArr = new ArrayList<Object>();
		// 処理名
		outItemArr.add("商品ﾘｽﾄ：更新");
		// ヘッダ名
		outItemArr.add(ItemList.HEADER_CONFIRM);
		// 出力用文字列
		String outDelFlg = "";
		// 更新データがNULLでない
		if (itemList != null){
			// null項目削除
			itemList.removeAll(Collections.singleton(null));
			// 更新データ
			for (int i = 0; i<itemList.size(); i++) {
				if (itemList.get(i).getTargetRow().equals("true")) {
					List<String> inpList = new ArrayList<String>();
					List<String> outList = new ArrayList<String>();

					// 表示用データ設定
					// 商品コード
					outList.add(itemList.get(i).getItemCd());
					// 商品名
					outList.add(itemList.get(i).getItemName());
					// 商品区分
					outList.add(itemList.get(i).getItemKbn());
					// 状態
					if (itemList.get(i).getDelFlg().equals("0")) {
						outDelFlg = getText("delflg.shiyou");
					} else {
						outDelFlg = getText("delflg.delete");
					}
					outList.add(outDelFlg);
					outItemArr.add(outList);
					// 更新用データ設定
					inpList.add(itemList.get(i).getItemCd());
					inpList.add(itemList.get(i).getItemName());
					inpList.add(itemList.get(i).getItemKbn());
					inpList.add(itemList.get(i).getDelFlg());
					inpItemArr.add(inpList);
					count++;
				}
			}

			// 表示データ設定
			session.put("OUTPUT_LIST", outItemArr);
			// 更新データ設定
			session.put("INPUT_LIST", inpItemArr);
			// 更新アクション名設定
			session.put("SUBMIT_ACTION", "update_item");
			// 更新行数
			session.put("ROW_COUNT", count.toString());
			// 戻り値
			ret = "confirm";
		} else {
			// メッセージ設定
			messagesList.add(getText("itemList.update.failure"));
			setActionErrors(messagesList);
		}
		return ret;
	}
}
