package struts2maven.Struts2MavenTest002.action;

import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
	@Result(name = "none", location = "login.jsp"),
	@Result(name = "success", location = "confirm.jsp")
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
* <code>確認画面</code>
*/
public class ConfirmAction extends BaseAction {

	// メイン画面
	@Action("confirm")
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

		return SUCCESS;
	}
}
