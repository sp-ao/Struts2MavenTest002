package struts2maven.Struts2MavenTest002.service;

import java.util.List;

public interface FileDownloadService extends BaseService {
	public List<Object> selectDownloadTable(String tableName, String colmun, String condition);
}
