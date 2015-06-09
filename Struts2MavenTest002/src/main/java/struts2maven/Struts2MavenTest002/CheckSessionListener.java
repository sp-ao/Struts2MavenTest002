package struts2maven.Struts2MavenTest002;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Application Lifecycle Listener implementation class CheckSessionListener
 *
 */
public class CheckSessionListener implements HttpSessionListener {

	private ActionSupport action;
    /**
     * Default constructor.
     */
    public CheckSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  {
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  {
    	try {
    		// セッション取得
    		HttpSession session = se.getSession();
    		ServletContext context = session.getServletContext();
    		//セッションの終了直前に、最後にアクセスされた時刻を取得
    		long from = session.getLastAccessedTime();
    		//現在時刻を取得
    		long now = System.currentTimeMillis();
    		//セッションのタイムアウト値と比較
    		if (now - from < session.getMaxInactiveInterval()) {
    			context.log("invalidate()の呼び出しにより終了");
    		} else {
    			context.log("タイムアウトにより終了");
    		}
		} catch (Exception e) {
			System.out.println("Exception CheckSessionListener.sessionDestroyed" + e);
		} finally {
		}
    }
}
