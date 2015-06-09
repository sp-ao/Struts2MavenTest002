package struts2maven.Struts2MavenTest002.action;

import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
/**
 * ログアウト
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
//遷移先定義
@Results ({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "success", location = "login", type = "redirect"),
	@Result(name = "input", location = "login", type = "redirect")
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
/**
* <code>ログアウト</code>
*/
public class LogoutAction extends BaseAction {

	@Action("logout")
	public String execute() throws Exception {
		// メッセージクリア
		clearBef();
		// セッションクリア
		session.remove("userid");
		// ログイン画面へ遷移
		return INPUT;
	}
}
