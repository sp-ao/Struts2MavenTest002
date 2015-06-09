/**
 *
 */
package struts2maven.Struts2MavenTest002.service;


import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AS
 * 共通処理
 */
@Service
public class CommonServiceImpl extends BaseServiceImpl
							  implements CommonService {

	private static Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);

	/**
	 * テーブル名一覧取得
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectTableList() {
		List<String> selectTableList = new ArrayList<String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		try {
			// テーブル情報をDBより取得
			selectTableList =
				(List<String>)dao.select("select_TableList", null);
			// 取得できた場合
			if (!selectTableList.isEmpty()) {
				//
				Iterator<String> iterator = selectTableList.iterator();
				//
				while (iterator.hasNext()) {
					// リスト一行取出し
					Object st = iterator.next();
					// Mapに格納
					selectMap.put(
						((HashMap<?, ?>) st).get("Name").toString(),
						((HashMap<?, ?>) st).get("Comment").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectMap;
	}

}
