package struts2maven.Struts2MavenTest002.model;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

public class UserList {

	public static final String[] HEADER
		= new String[]{"UserId", "UserName", "Password", "Permission", "DelFlg", "LastUpdate"};
	public static final String[] HEADER_CONFIRM
		= new String[]{"ﾕｰｻﾞｰID", "ﾕｰｻﾞｰ名", "ﾊﾟｽﾜｰﾄﾞ", "権限", "状態"};

	private String editUserId;
	private String editUserName;
	private String editPassword;
	private String editPermission;
	private String editDelFlg;

	public String getEditUserId() {
		return editUserId;
	}
	/**
	 * @param edituserid the edituserid to set
	 * ユーザID。必須入力、文字数4から10文字、半角英数のみ。
	 * shortCircuit実装。
	 */
	@RequiredStringValidator(
		message="",
		key="userList.input.editUserId.require",
		shortCircuit=true)
	@StringLengthFieldValidator(
		message="",
		key="userList.input.editUserId.length",
		minLength = "4" ,
		maxLength = "10",
		trim=true,
		shortCircuit=true)
	@RegexFieldValidator(
		message="",
		key="userList.input.editUserId.regex",
		regexExpression="[0-9A-Za-z_]*")
	public void setEditUserId(String editUserId) {
		this.editUserId = editUserId;
	}
	public String getEditUserName() {
		return editUserName;
	}
	/**
	 * @param editusername the editusername to set
	 * ユーザ名。必須入力、文字数1から20文字
	 * shortCircuit実装。
	 */
	@RequiredStringValidator(
		message="",
		key="userList.input.editUserName.require",
		shortCircuit=true)
	@StringLengthFieldValidator(
		message="",
		key="userList.input.editUserName.length",
		minLength = "1" ,
		maxLength = "20",
		trim=true,
		shortCircuit=true)
	public void setEditUserName(String editUserName) {
		this.editUserName = editUserName;
	}
	public String getEditPassword() {
		return editPassword;
	}
	/**
	 * @param editpassword the editpassword to set
	 * ﾊﾟｽﾜｰﾄﾞ。必須入力、文字数4から10文字、半角英数のみ。
	 * shortCircuit実装。
	 */
	@RequiredStringValidator(
		message="",
		key="userList.input.editPassword.require",
		shortCircuit=true)
	@StringLengthFieldValidator(
		message="",
		key="userList.input.editPassword.length",
		minLength = "4" ,
		maxLength = "10",
		trim=true,
		shortCircuit=true)
	@RegexFieldValidator(
		message="",
		key="userList.input.editPassword.regex",
		regexExpression="[0-9A-Za-z_]*")
	public void setEditPassword(String editPassword) {
		this.editPassword = editPassword;
	}
	public String getEditPermission() {
		return editPermission;
	}
	public void setEditPermission(String editPermission) {
		this.editPermission = editPermission;
	}
	public String getEditDelFlg() {
		return editDelFlg;
	}
	public void setEditDelFlg(String editDelFlg) {
		this.editDelFlg = editDelFlg;
	}

}
