package struts2maven.Struts2MavenTest002.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import struts2maven.Struts2MavenTest002.service.Common;

import com.opensymphony.xwork2.ActionSupport;

//アクションが属するパッケージ名
@Namespace("/")
//独自パッケージ使用
@ParentPackage(value="default")
//インターセプター
@InterceptorRefs({
	@InterceptorRef("customStack")
})

public abstract class BaseAction extends ActionSupport
								 implements SessionAware, ServletRequestAware {
	// session
	public Map<String,Object> session;
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
	// request
	public HttpServletRequest req;
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public void clearBef() throws Exception {

		// メッセージ削除
		this.clearMessages();
		this.clearActionErrors();
		this.clearFieldErrors();

		// セッション判定
		if (session != null) {
			// アクション名格納
			session.put("action", this.getClass().getName().toString());
			// 一時ファイルパス判定
			if (session.get("DELFILE") != null) {
				// 一時ファイル削除
				Common.deleteFile((List<String>)session.get("DELFILE"));
				// セッション削除
				session.remove("DELFILE");
			}
		}
	}
}
