package struts2maven.Struts2MavenTest002.service;

import java.util.List;

import struts2maven.Struts2MavenTest002.model.UserList;

public interface UserListService extends BaseService {
	public List<UserList> selectUser(List<Object> object);
	public List<UserList> selectUserList(UserList user);
	public String insertUserList(List<Object> object);
	public String updateUserList(List<Object> object);
	public String deleteUserList(UserList user);
}
