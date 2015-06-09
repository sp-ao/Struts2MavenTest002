package struts2maven.Struts2MavenTest002.service;

import java.util.List;
import java.util.Map;

import org.supercsv.io.ICsvBeanReader;

import struts2maven.Struts2MavenTest002.model.ItemList;
import struts2maven.Struts2MavenTest002.model.UserList;

public interface ItemListService extends BaseService {
	public List<ItemList> selectItemList();
	public Integer selectItemCount(String itemCd);
	public String insertItemList(Map<String, Object> itemMap);
	public String updateItemList(List<Object> object);
	public Map<String, Object> chkItemList(ICsvBeanReader inFile, ItemList itemListBean);
}
