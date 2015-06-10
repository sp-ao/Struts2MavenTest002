package struts2maven.Struts2MavenTest002.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import struts2maven.Struts2MavenTest002.model.UserList;
import struts2maven.Struts2MavenTest002.service.Common;
import struts2maven.Struts2MavenTest002.service.UserListService;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
/**
 * ユーザーリスト
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
// 遷移先定義
@Results({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "input", location = "userList", type = "redirect"),
	@Result(name = "confirm", location = "confirm", type = "chain"),
	@Result(name = "success", location = "userList.jsp"),
	@Result(name = "delete", location = "userList", type = "redirect"),
	@Result(name = "update", location = "userList", type = "redirect"),
	@Result(name = "insert", location = "userList", type = "redirect"),
	@Result(name = "none", location = "login.jsp")
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
* <code>ユーザーリスト画面</code>
*/
public class UserListAction extends BaseAction {

	// メッセージ
	private LinkedList<String> messagesList = new LinkedList<String>();
	// 戻り値
	private String ret = INPUT;

	// Bean
	private List<UserList> userList;
	public List<UserList> getUserList() {
		return userList;
	}
	public void setUserList(List<UserList> userList) {
		this.userList = userList;
	}

	// Bean
	private UserList userListBean;
	public UserList getUserListBean() {
		return userListBean;
	}
	@VisitorFieldValidator(message="")
	public void setUserListBean(UserList userListBean) {
		// メッセージクリア
		Common.clearBefMessage();
		this.userListBean = userListBean;
	}

	// Serviceの取得
    @Resource
    UserListService service;
	public UserListService getService() {
		return service;
	}
	public void setService(UserListService service) {
		this.service = service;
	}

	// メイン画面
	@Action("userList")
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
		// ユーザーリスト取得サービス
		userList = service.selectUserList(userListBean);
		// 取得出来なかった場合
		if (userList.size() < 1) {
			// メッセージ設定
			messagesList.add(getText("userList.select.failure"));
			setActionErrors(messagesList);
		}
		// セッションにセット
		session.put("UserList", userList);
		// 戻り値
		return SUCCESS;
	}

	// ユーザーリスト作成
	@Action("user_insert")
	@SuppressWarnings("unchecked")
	public String UserInsert() throws Exception {
		// メッセージ初期化
		clearBef();
		// 画面値判定
		if (session != null) {
			if (session.get("INPUT_LIST") != null) {
				// ユーザー取得
				userList = service.selectUser(
						(List<Object>) session.get("INPUT_LIST"));
				// ユーザー存在しない
				if (userList.size() < 1) {
					// ユーザー作成サービス
					ret = service.insertUserList(
							(List<Object>) session.get("INPUT_LIST"));
					// 戻り値判定
					if (ret.equals("insert")) {
						// メッセージ設定
						addActionMessage(getText("userList.insert.success"));
						// 戻り値
						return ret;
					}
				} else {
					messagesList.add(getText("userList.insert.editUserId.exist"));
					setActionErrors(messagesList);
				}
			}
		}
		// メッセージ設定
		messagesList.add(getText("userList.insert.failure"));
		setActionErrors(messagesList);
		// 戻り値
		return ret;
	}

	// ユーザーリスト更新
	@SuppressWarnings("unchecked")
	@Action("user_update")
	public String UserUpdate() throws Exception {
		// メッセージ初期化
		clearBef();
		// 画面値判定
		if (session != null) {
			if (session.get("INPUT_LIST") != null) {
				// ユーザー取得
				userList = service.selectUser(
						(List<Object>) session.get("INPUT_LIST"));
				// ユーザー存在する
				if (userList.size() > 0) {
					// ユーザー更新サービス
					ret = service.updateUserList(
							(List<Object>) session.get("INPUT_LIST"));
					// 戻り値判定
					if (ret.equals("update")) {
						// メッセージ設定
						addActionMessage(getText("userList.update.success"));
						// 戻り値
						return ret;
					}
				} else {
					messagesList.add(getText("userList.update.editUserId.exist"));
					setActionErrors(messagesList);
					// 戻り値
					return ret;
				}
			}
		}
		// メッセージ設定
		messagesList.add(getText("userList.update.failure"));
		setActionErrors(messagesList);
		// 戻り値
		return ret;
	}

	// ユーザーリスト削除
	@Action("user_delete")
	@SkipValidation
	public String UserDelete() throws Exception {
		// メッセージ初期化
		clearBef();
		// 画面値判定
		if (userListBean != null) {
			// 削除対象取得
			List<String> delUser = new ArrayList<String>();
			List<Object> object = new ArrayList<Object>();
			// カンマと半角スペース削除（★Pending 暫定処理）
			delUser.add(userListBean.getEditUserId().replaceAll(",", "").replace(" ", ""));
			object.add(delUser);
			// ユーザー取得
			userList = service.selectUser(object);
			// ユーザー存在する
			if (userList.size() > 0) {
				// ユーザー削除サービス
				ret = service.deleteUserList(userListBean);
				if (ret.equals("delete")) {
					// メッセージ設定
					addActionMessage(getText("userList.delete.success"));
					// 戻り値
					return ret;
				}
			} else {
				messagesList.add(getText("userList.update.editUserId.exist"));
				setActionErrors(messagesList);
				// 戻り値
				return ret;
			}
		}
		// メッセージ設定
		messagesList.add(getText("userList.delete.failure"));
		setActionErrors(messagesList);
		// 戻り値
		return ret;
	}

	// ユーザーリスト確認
	@Actions ({
		@Action("confirm_insert_user"),
		@Action("confirm_update_user")
	})
	public String UserConfrm() throws Exception {
		// メッセージ初期化
		clearBef();
		// 更新行数
		Integer count = 0;
		// uri取得
		String reqestUri = this.req.getRequestURI().toString();
		// 表示データ格納
		List<Object> outUserArr = new ArrayList<Object>();
		// 更新データ格納
		List<Object> inpUserArr = new ArrayList<Object>();
		// データ格納
		List<String> inpList = new ArrayList<String>();
		List<String> outList = new ArrayList<String>();
		// 出力用文字列
		String outPermission = "";
		String outDelFlg = "";

		// データ有無判定
		if (userListBean != null) {
			// 作成
			if ((reqestUri.indexOf("insert")) > -1) {
				// 処理名
				outUserArr.add("ﾕｰｻﾞｰﾘｽﾄ：作成");
				// 作成アクション名設定
				session.put("SUBMIT_ACTION", "user_insert");
			// 更新
			} else if ((reqestUri.indexOf("update")) > -1) {
				// 処理名
				outUserArr.add("ﾕｰｻﾞｰﾘｽﾄ：更新");
				// 作成アクション名設定
				session.put("SUBMIT_ACTION", "user_update");
			}
			// ヘッダ名
			outUserArr.add(UserList.HEADER_CONFIRM);
			// 表示用データ設定
			// ユーザーID
			outList.add(userListBean.getEditUserId());
			// ユーザー名
			outList.add(userListBean.getEditUserName());
			// パスワード
			outList.add(userListBean.getEditPassword());
			// 権限
			if (userListBean.getEditPermission().equals("0")) {
				outPermission = getText("permission.ippan");
			} else if (userListBean.getEditPermission().equals("1")) {
				outPermission = getText("permission.kanri");
			} else {
				outPermission = getText("permission.guest");
			}
			outList.add(outPermission);
			// 状態
			if (userListBean.getEditDelFlg().equals("0")) {
				outDelFlg = getText("delflg.shiyou");
			} else {
				outDelFlg = getText("delflg.delete");
			}
			outList.add(outDelFlg);
			// 表示用データ格納
			outUserArr.add(outList);
			// 更新用データ設定
			inpList.add(userListBean.getEditUserId());
			inpList.add(userListBean.getEditUserName());
			inpList.add(userListBean.getEditPassword());
			inpList.add(userListBean.getEditPermission());
			inpList.add(userListBean.getEditDelFlg());
			// 更新用データ格納
			inpUserArr.add(inpList);
			count++;

			// 表示データ設定
			session.put("OUTPUT_LIST", outUserArr);
			// 更新データ設定
			session.put("INPUT_LIST", inpUserArr);
			// 更新行数
			session.put("ROW_COUNT", count.toString());
			// 戻り値
			ret = "confirm";
		} else {
			if ((reqestUri.indexOf("insert")) > -1) {
				// メッセージ設定
				messagesList.add(getText("userList.insert.failure"));
				setActionErrors(messagesList);
			} else if ((reqestUri.indexOf("update")) > -1) {
				// メッセージ設定
				messagesList.add(getText("userList.update.failure"));
				setActionErrors(messagesList);
			}
		}
		// 戻り値
		return ret;
	}
}
