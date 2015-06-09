package struts2maven.Struts2MavenTest002.service;

import struts2maven.Struts2MavenTest002.dao.DAO;

/**
 *
 * @author A-pZ ( http://www.h3.dion.ne.jp/~alpha-pz/ )
 *
 */
public class BaseServiceImpl implements BaseService {

    public DAO getDAO() {
        return dao;
    }

    public void setDAO(DAO dao) {
        this.dao = dao;
    }

    protected DAO dao;
}
