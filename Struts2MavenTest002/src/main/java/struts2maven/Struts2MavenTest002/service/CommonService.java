package struts2maven.Struts2MavenTest002.service;

import java.util.List;
import java.util.Map;

import struts2maven.Struts2MavenTest002.model.UserList;

public interface CommonService extends BaseService {
	public Map<String, String> selectTableList();
}
