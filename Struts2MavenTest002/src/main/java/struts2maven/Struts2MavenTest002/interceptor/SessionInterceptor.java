package struts2maven.Struts2MavenTest002.interceptor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import struts2maven.Struts2MavenTest002.model.Login;
import struts2maven.Struts2MavenTest002.service.LoginService;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
* セッション情報インターセプター
*/
public class SessionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private ActionSupport action;

	// Serviceの取得
	@Resource
	LoginService service;
	public LoginService getService() {
		return service;
	}
	public void setService(LoginService service) {
		this.service = service;
	}

	public void init() {}
	public void destroy () {}
	public String intercept(ActionInvocation invocation) throws Exception {
		// リクエスト取得
		HttpServletRequest request = ServletActionContext.getRequest();
		// セッション取得
		HttpSession session = ServletActionContext.getRequest().getSession();
		// URLパラメタのuseridとpasswdを取得
		String userid	= request.getParameter("loginBean.userId");
		String passwd = request.getParameter("loginBean.password");

		try {
			// パラメタ判定(useridとpasswdパラメタ有り⇒ﾛｸﾞｲﾝ画面での認証)
			if (userid != null && passwd != null) {
				// 認証チェックを実施
				List<Map<String,Object>> result =
						service.selectLoginInfo(userid, passwd);
				// 取得件数>0 認証OK
				if (result.size() > 0){
					// 既存セッション取得
					session = ServletActionContext.getRequest().getSession(true);
					// 既存セッションを破棄
					session.invalidate();
					// 新規セッションを取得
					HttpSession newsession = ServletActionContext.getRequest().getSession(true);
					// 新規セッションに ログイン情報 を設定
					newsession.setAttribute("userid", result.get(0).get("userid"));
					newsession.setAttribute("permission", result.get(0).get("permission"));
					newsession.setAttribute("delflg", result.get(0).get("delflg"));

					// メニュー画面へ遷移
					return "login-success";
				// それ以外 認証OK
				} else {
					// メッセージ設定
					action = (ActionSupport) invocation.getAction();
					action.addActionError(action.getText("ﾕｰｻﾞｰID または ﾊﾟｽﾜｰﾄﾞ が違います"));
					// ログイン画面へ遷移
					return ActionSupport.NONE;
				}

			// // パラメタ判定(useridとpasswdパラメタ無し⇒通常画面での認証)
			} else {
				// userid が取得出来ない
				if (session.getAttribute("userid") == null ||
					session.getAttribute("userid").toString().length() == 0){

					// 通常画面へのアクセスなのに未ログインの場合には、
					// ログイン画面に遷移する。
					return ActionSupport.NONE;
				} else {
					// 後続のinterceptor実施
					return invocation.invoke();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionSupport.NONE;
	} /* end of	intercept() */
}
