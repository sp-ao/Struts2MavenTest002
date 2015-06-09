package struts2maven.Struts2MavenTest002.action;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import struts2maven.Struts2MavenTest002.model.FileUpload;
import struts2maven.Struts2MavenTest002.model.ItemList;
import struts2maven.Struts2MavenTest002.service.Common;
import struts2maven.Struts2MavenTest002.service.ItemListService;

/**
 * 商品リスト
 * @author sa
 *
 */
//アクションが属するパッケージ名
@Namespace("/")
// 遷移先定義
@Results({
	@Result(name = "exception", location = "report", type = "chain"),
	@Result(name = "input", location = "tableTest", type = "redirect"),
	@Result(name = "none", location = "login", type = "redirect"),
	@Result(name = "success", location = "tableTest.jsp")
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
* <code>ユーザー一覧画面</code>
*/
public class TableTestAction extends BaseAction {

	// メイン画面
	@Action("tableTest")
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
