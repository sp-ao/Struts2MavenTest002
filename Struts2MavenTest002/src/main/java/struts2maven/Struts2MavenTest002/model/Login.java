package struts2maven.Struts2MavenTest002.model;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

public class Login {

	private String userId;
	private String password;
	private String permission;
	private String delFlg;

	public String getUserId() {
		return userId;
	}
	/**
	 * @param userid the userid to set
	 * ユーザID。必須入力、文字数4から10文字、半角英数のみ。
	 * shortCircuit実装。
	 */
	@RequiredStringValidator(
		message="",
		key="login.input.userId.require",
		shortCircuit=true)
	@StringLengthFieldValidator(
		message="",
		key="login.input.userId.length",
		minLength = "4" ,
		maxLength = "10",
		trim=true,
		shortCircuit=true)
	@RegexFieldValidator(
		message="",
		key="login.input.userId.regex",
		regexExpression="[0-9A-Za-z_]*")
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 * ﾊﾟｽﾜｰﾄﾞ。必須入力、文字数4から10文字、半角英数のみ。
	 * shortCircuit実装。
	 */
	@RequiredStringValidator(
		message="",
		key="login.input.password.require",
		shortCircuit=true)
	@StringLengthFieldValidator(
		message="",
		key="login.input.password.length",
		minLength = "4" ,
		maxLength = "10",
		trim=true,
		shortCircuit=true)
	@RegexFieldValidator(
		message="",
		key="login.input.password.regex",
		regexExpression="[0-9A-Za-z_]*")
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
}
