/**
 *
 */
package struts2maven.Struts2MavenTest002.service;

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

/**
 * @author A-pZ
 *
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl
							  implements LoginService {

    private static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    /**
     * ログイン情報
     */
	public List<Map<String,Object>> selectLoginInfo(String userId, String password) {
		Map<String, Object> lInfo = new HashMap<String, Object>();
		lInfo.put("userId", userId);
		lInfo.put("password", SafePassword.getStretchedPassword(password, userId));
		List<Map<String,Object>> result =
				(List<Map<String,Object>>)dao.select("select_logininfo", lInfo);
		return result;
	}
}
