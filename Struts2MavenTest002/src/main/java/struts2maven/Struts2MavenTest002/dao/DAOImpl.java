package struts2maven.Struts2MavenTest002.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * <pre>
 * DAO
 * データベースにアクセスする実装です。
 * </pre>
 *
 * @author A-pZ
 * @version 1.0.0
 */
public class DAOImpl extends SqlSessionDaoSupport implements DAO {

    /**
     * insertを実行します。
     *
     * @param queryString
     *            SQLID
     * @param object
     *            条件モデル
     */
    public void insert(String queryString, Object object) throws Exception {
        getSqlSession().insert(queryString, object);
    }

    /**
     * updateを実行します。
     *
     * @param queryString
     *            SQLID
     * @param object
     *            条件モデル
     * @return 更新件数
     */
    public int update(String queryString, Object object) throws Exception {
        int count = getSqlSession().update(queryString, object);

        return count;
    }

    /**
     * deleteを実行します。
     *
     * @param queryString
     *            SQLID
     * @param object
     *            条件モデル
     * @return 更新件数
     */
    public int delete(String queryString, Object object) {
        return getSqlSession().delete(queryString, object);
    }

    /**
     * カウント用SQL_ID変換
     *
     * @param sqlid
     *            SQL_ID
     * @return [SQLID]_count
     */
    private String getPagingId(String sqlid) {
        return sqlid + "_count";
    }

    /**
     * iBatisのRowHandlerを利用した場合の処理に使います。
     * RowHandlerはDAO内で使わずに、DAO呼び出しクラスで行制御を行って処理した方が、制御は楽に行うことができます。
     */
    /*
     * public void handledResult(String queryString , Object object , RowHandler
     * handler) throws Exception { SqlSessionClient sqlMap =
     * getSqlSessionTemplate().getSqlSessionClient();
     * sqlMap.queryWithRowHandler(queryString, object , handler); }
     */

    /**
     * selectを実行します。
     *
     * @param queryString
     *            SQLID
     * @param object
     *            条件モデル
     * @return 結果オブジェクトのリスト
     */
    public List<?> select(String queryString, Object object) {
        return getSqlSession().selectList(queryString, object);
    }

    /**
     * 最大件数を取得します。select count()用のメソッドです。
     * selectCountは自動的にページング用SQLIDを使います。ページング用SQLIDは、接尾語として_countがつくルールにしています。
     *
     * @param sqlid
     *            SQLID _countがついているSQLID（指定するときは_countをつけない！）
     * @param param
     *            パラメータ
     * @return 検索結果
     */
    public int selectCount(String sqlid, Object param) {
        Integer count = (Integer) selectObject(getPagingId(sqlid), param);
        return count.intValue();
    }

    /**
     * selectを実行します。
     *
     * @param queryString
     *            SQLID
     * @param object
     *            条件モデル
     * @return 結果オブジェクト(単一)
     */
    public Object selectObject(String queryString, Object object) {
        return getSqlSession().selectOne(queryString, object);
    }
}
