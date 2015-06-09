package struts2maven.Struts2MavenTest002.service;

import java.util.List;
import java.util.Map;

public interface LoginService extends BaseService {
	public List<Map<String,Object>> selectLoginInfo(String userId, String password);
}
