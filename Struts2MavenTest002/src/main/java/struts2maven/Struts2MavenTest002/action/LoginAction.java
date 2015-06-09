package struts2maven.Struts2MavenTest002.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * ログイン
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
//遷移先定義
@Results({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "none", location = "login.jsp"),
	@Result(name = "success", location = "login.jsp")
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
public class LoginAction extends BaseAction {
	@Action("login")
	@SkipValidation
	public String execute() throws Exception {
		// メッセージクリア
		clearBef();
		return SUCCESS;
	}
}
