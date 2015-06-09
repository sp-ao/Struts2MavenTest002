/**
 *
 */
package struts2maven.Struts2MavenTest002.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionSupport;

import struts2maven.Struts2MavenTest002.model.UserList;

/**
 * @author A-pZ
 *
 */
@Service
public class UserListServiceImpl extends BaseServiceImpl
								 implements UserListService {

	private static Logger log = LoggerFactory.getLogger(UserListServiceImpl.class);

	/**
	 * ユーザー取得
	 */
	@SuppressWarnings("unchecked")
	public List<UserList> selectUser(List<Object> object) {
		// 取得条件格納用
		Map<String, Object> selInfo = new HashMap<String, Object>();
		// 結果格納用
		List<UserList> selectUser = new ArrayList<UserList>();
		try {
			List<String> workList = new ArrayList<String>();
			workList = (List<String>) object.get(0);
			// 取得条件設定
			selInfo.put("userId", workList.get(0).toString());
			// ユーザーID取得
			selectUser = (List<UserList>)dao.select("select_user", selInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectUser;
	}
	/**
	 * ユーザー一覧取得
	 */
	@SuppressWarnings("unchecked")
	public List<UserList> selectUserList(UserList user) {
		// 結果格納用
		List<UserList> selectUserList = new ArrayList<UserList>();
		try {
			// 全ユーザーリスト取得
			selectUserList = (List<UserList>)dao.select("select_userlist", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectUserList;
	}
	/**
	 * ユーザー作成
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public String insertUserList(List<Object> object) {
		// 作成データ格納用
		HashMap<String, Object> insInfo = new HashMap<String, Object>();
		try {
			for (int i=0; i < object.size(); i++) {
				List<String> workList = new ArrayList<String>();
				workList = (List<String>) object.get(i);
				// 作成データ設定
				insInfo.put("userId", workList.get(0).toString());
				insInfo.put("userName", workList.get(1).toString());
				insInfo.put("password",
							SafePassword.getStretchedPassword(
								workList.get(2).toString(),
								workList.get(0).toString()));
				insInfo.put("permission", workList.get(3).toString());
				insInfo.put("delFlg", workList.get(4).toString());
			}
			// ユーザリスト作成
			dao.insert("insert_userlist", insInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		return "insert";
	}
	/**
	 * ユーザー更新
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public String updateUserList(List<Object> object) {
		// 更新データ格納用
		HashMap<String, Object> updInfo = new HashMap<String, Object>();
		try {
			for (int i=0; i < object.size(); i++) {
				List<String> workList = new ArrayList<String>();
				workList = (List<String>) object.get(i);
				// 作成データ設定
				updInfo.put("userId", workList.get(0).toString());
				updInfo.put("userName", workList.get(1).toString());
				updInfo.put("password",
							SafePassword.getStretchedPassword(
								workList.get(2).toString(),
								workList.get(0).toString()));
				updInfo.put("permission", workList.get(3).toString());
				updInfo.put("delFlg", workList.get(4).toString());
			}
			// ユーザリスト更新
			dao.update("update_userlist", updInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		return "update";
	}
	/**
	 * ユーザー削除
	 */
	@Transactional
	public String deleteUserList(UserList userListBean) {
		Map<String, Object> delInfo = new HashMap<String, Object>();

		try {
			// 削除データ設定
			String delUserId = userListBean.getEditUserId().replace(" ", "").replace(",", "");
			delInfo.put("userId", delUserId);
			// ユーザリスト削除
			dao.update("delete_userlist", delInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		return "delete";
	}
}
