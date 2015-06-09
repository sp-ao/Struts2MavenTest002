package struts2maven.Struts2MavenTest002.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
/**
 * エラー
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
//遷移先定義
@Results({
	@Result(name = "success", location = "report.jsp")
})
public class ReportAction extends BaseAction {

	@Action ("report")
	public String report() throws Exception {
	  addActionMessage("ダウンロード例外です");
	  return "report";
	}
}
