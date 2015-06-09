/**
 *
 */
package struts2maven.Struts2MavenTest002.service;

import struts2maven.Struts2MavenTest002.dao.DAO;

/**
 * @author sa
 */
public interface BaseService {
    /**
     * @return the dao
     */
    public DAO getDAO();

    /**
     * @param dao
     * the dao to set
     */
    public void setDAO(DAO dao);
}
