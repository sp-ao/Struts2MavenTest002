package struts2maven.Struts2MavenTest002.action;

import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import struts2maven.Struts2MavenTest002.model.Login;
import struts2maven.Struts2MavenTest002.service.Common;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

/**
 * メニュー
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
//遷移先定義
@Results({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "input", location = "login.jsp"),
	@Result(name = "none", location = "login.jsp"),
	@Result(name = "success", location = "menu.jsp")
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
public class MenuAction extends BaseAction {

	private Login loginBean;
	public Login getLoginBean() {
		return loginBean;
	}
	@VisitorFieldValidator(message="")
	public void setLoginBean(Login loginBean) {
		this.loginBean = loginBean;
	}
	// メイン画面
	@Action("menu")
	public String excute() throws Exception {
		// エラーメッセージ判定
		if (getActionErrors().size() > 0) {
			return INPUT;
		}
		// メッセージクリア
		clearBef();
		// jsonパス取得
		session.put("JSONMENU", getText("filePath.json") + getText("jsonFileName.menu"));

		return SUCCESS;
	}
}
