/**
 *
 */
package struts2maven.Struts2MavenTest002.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import struts2maven.Struts2MavenTest002.model.Login;
import struts2maven.Struts2MavenTest002.model.UserList;
import sun.tools.tree.ThisExpression;

/**
 * @author A-pZ
 *
 */
@Service
public class FileDownloadServiceImpl extends BaseServiceImpl
									 implements FileDownloadService {
    private static Logger log = LoggerFactory.getLogger(FileDownloadServiceImpl.class);
    /**
     * テーブルデータ取得
     */
	public List<Object> selectDownloadTable(String tableName, String colmun, String condition) {
		Map<String, Object> selInfo = new HashMap<String, Object>();
		List<Object> selectDownloadTable = new ArrayList<Object>();

		try {
			selInfo.put("tableName", tableName);
			selInfo.put("colmun", colmun);
			selInfo.put("condition", condition);
			selectDownloadTable =
				(List<Object>)dao.select("select_downloadTable", selInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectDownloadTable;
	}
}
