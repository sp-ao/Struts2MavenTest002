/**
 *
 */
package struts2maven.Struts2MavenTest002.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.io.ICsvBeanReader;

import com.opensymphony.xwork2.ActionSupport;

import struts2maven.Struts2MavenTest002.model.ItemList;

/**
 * @author sa
 *
 */
@Service
public class ItemListServiceImpl extends BaseServiceImpl implements ItemListService {

	private String reg = "^[a-zA-Z0-9]+$";
	private String charSet = "UTF-8";

	/**
	 * 商品リスト取得
	 */
	@SuppressWarnings("unchecked")
	public List<ItemList> selectItemList() {
		List<ItemList> selectItemList = new ArrayList<ItemList>();
		try {
			selectItemList =
				(List<ItemList>)dao.select("select_itemlist", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectItemList;
	}
	/**
	 * 商品リストカウント取得
	 */
	public Integer selectItemCount(String itemCd) {
		Map<String, Object> selInfo = new HashMap<String, Object>();
		Integer dataCount = 0;
		try {
			selInfo.put("itemCd", itemCd);
			dataCount = dao.selectCount("select_itemlist", selInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataCount;
	}
	/**
	 * 商品リスト作成
	 */
	@Transactional
	public String insertItemList(Map<String, Object> itemMap) {

		@SuppressWarnings("unchecked")
		List<Object> itemArr = (List<Object>) itemMap.get("INSERTDATA");
		try {
			dao.insert("insert_itemlist", itemArr);
		} catch (Exception e) {
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		return "insert";
	}
	/**
	 * 商品リスト更新
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public String updateItemList(List<Object> object) {

		// 更新データ格納用
		List<HashMap<String, Object>> updInfo = new ArrayList<HashMap<String, Object>>();
		try {
			for (int i=0; i < object.size(); i++) {
				List<String> workList = new ArrayList<String>();
				workList = (List<String>) object.get(i);
				// 更新データ作成
				HashMap<String, Object> workMap = new HashMap<String, Object>();
				workMap.put("itemCd", workList.get(0).toString());
				workMap.put("itemName", workList.get(1).toString());
				workMap.put("itemKbn", workList.get(2).toString());
				workMap.put("delFlg", workList.get(3).toString());
				updInfo.add(workMap);
			}
			// 商品リスト更新
			dao.update("update_itemlist", updInfo);

		} catch (Exception e) {
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		return "update";
	}
	/**
	 * Csvファイルチェック（暫定）
	 */
	public Map<String, Object> chkItemList(ICsvBeanReader inFile, ItemList itemListBean) {

		// 結果格納用
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> resultArr = new ArrayList<Object>();
		// メッセージ用
		ActionSupport actionSupport = new ActionSupport();
		LinkedList<String> workMessagesList = new LinkedList<String>();
		// エラー数カウント
		Integer errCount = 0;
		// ループ数カウント
		Integer count = 0;
		// 行数カウント
		Integer rowCount = 1;
		// 行数文字列
		String rowStr = "";
		// ヘッダ名格納用
		List<Object> columnName = new ArrayList<Object>();
		// レコード存在チェック用
		Integer dataCount = 0;
		// key存在チェック用
		String workKeyStr = "";

		try {
			// リスト分ループ
			while ((itemListBean = inFile.read(ItemList.class, ItemList.HEADER)) != null) {
				// 行数文字列設定
				rowStr = rowCount.toString() + "行目：";
				// 行数カウント判定
				if (rowCount > 1) {

					// 商品ID
					// 必須
					if (itemListBean.getItemCd() == null) {
						// メッセージ設定
						workMessagesList.add(
							actionSupport.getText("itemlist.csv.require",
									new String[]{rowStr + columnName.get(0)})
						);
						errCount++;
					} else {
						// 桁数
						if (itemListBean.getItemCd().getBytes(charSet).length > 10) {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.length",
									new String[]{rowStr + columnName.get(0), "10"})
							);
							errCount++;
						}
						// 半角英数字
						if (!itemListBean.getItemCd().matches(reg)) {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.regex",
									new String[]{rowStr + columnName.get(0)})
							);
							errCount++;
						}
						// 同一キー存在チェック
						if ((workKeyStr.indexOf(itemListBean.getItemCd()) == -1)) {
							workKeyStr = workKeyStr + itemListBean.getItemCd() + " ";
						} else {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.keyexist",
									new String[]{rowStr + columnName.get(0)})
							);
							errCount++;
						}

						// DB存在チェック
						dataCount = selectItemCount(itemListBean.getItemCd());
						// 存在する
						if (dataCount > 0) {
							// メッセージ設定　
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.exist",
									new String[]{rowStr + columnName.get(0), itemListBean.getItemCd()})
							);
							errCount++;
						}
					}

					// null判定
					if (itemListBean.getItemName() == null) {
						// メッセージ設定
						workMessagesList.add(
							actionSupport.getText("itemlist.csv.require",
								new String[]{rowStr + columnName.get(1)})
						);
						errCount++;
					} else {
						// 桁数
						if (itemListBean.getItemName().getBytes(charSet).length > 45) {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.length",
									new String[]{rowStr + columnName.get(1), "45"})
							);
							errCount++;
						}
					}

					// 商品名
					// null判定
					if (itemListBean.getItemKbn() == null) {
						// メッセージ設定
						workMessagesList.add(
							actionSupport.getText("itemlist.csv.require",
								new String[]{rowStr + columnName.get(2)})
						);
						errCount++;
					} else {
						// 桁数
						if (itemListBean.getItemKbn().getBytes(charSet).length > 2) {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.length",
									new String[]{rowStr + columnName.get(2), "2"})
							);
							errCount++;
						}
						// 半角英数字
						if (!itemListBean.getItemKbn().matches(reg)) {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.regex",
									new String[]{rowStr + columnName.get(2)})
							);
							errCount++;
						}
					}

					// 削除ﾌﾗｸﾞ
					// null判定
					if (itemListBean.getDelFlg() == null) {
						// メッセージ設定
						workMessagesList.add(
							actionSupport.getText("itemlist.csv.require",
								new String[]{rowStr + columnName.get(3)})
						);
						errCount++;
					} else {
						// 桁数
						if (itemListBean.getDelFlg().getBytes(charSet).length > 1) {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.length",
									new String[]{rowStr + columnName.get(3), "1"})
							);
							errCount++;
						}
						// 半角英数字
						if (itemListBean.getDelFlg().length() > 1) {
							// メッセージ設定
							workMessagesList.add(
								actionSupport.getText("itemlist.csv.regex",
									new String[]{rowStr + columnName.get(3)})
							);
							errCount++;
						}
					}

					// エラー件数が0
					if (errCount < 1) {
						resultArr.add(count, itemListBean);
						// カウント
						count++;
					}

				} else {
					// ヘッダ取得
					if(itemListBean.getItemCd() != null) {
						columnName.add(0, itemListBean.getItemCd());
					} else {
						columnName.add(0, "");
					}
					if(itemListBean.getItemName() != null) {
						columnName.add(1, itemListBean.getItemName());
					} else {
						columnName.add(1, "");
					}
					if(itemListBean.getItemKbn() != null) {
						columnName.add(2, itemListBean.getItemKbn());
					} else {
						columnName.add(2, "");
					}
					if(itemListBean.getDelFlg() != null) {
						columnName.add(3, itemListBean.getDelFlg());
					} else {
						columnName.add(3, "");
					}
				}
				// 行数カウント
				rowCount++;
			}
			// 結果セット
			// エラー件数
			resultMap.put("ERRCNT", errCount);
			// エラーメッセージ
			resultMap.put("ERRMSG", workMessagesList);
			// 作成データ
			resultMap.put("INSERTDATA", resultArr);
			// 処理件数
			resultMap.put("INSERTCNT", count);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
