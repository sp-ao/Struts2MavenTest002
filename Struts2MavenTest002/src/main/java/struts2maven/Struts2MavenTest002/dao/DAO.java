package struts2maven.Struts2MavenTest002.dao;

import java.util.List;


/**
 * データベースにアクセスするインターフェース。
 * </pre>
 * @author A-pZ
 */
public interface DAO {

    /**
     * INSERTを実行する
     *
     * @param queryString SQLID
     * @param object パラメータークラス
     */
    public void insert(String queryString, Object object) throws Exception;

    /**
     * UPDATEを実行する
     *
     * @param queryString SQLID
     * @param object パラメータークラス
     * @return UPDATEの実行結果
     */
    public int update(String queryString, Object object) throws Exception ;

    /**
     * DELETEを実行する
     *
     * @param queryString SQLID
     * @param object パラメータークラス
     * @return DELETEの実行結果
     */
    public int delete(String queryString, Object object);

    /**
     * SELECTを実行する
     *
     * @param queryString SQLID
     * @param object パラメータークラス
     * @return 実行結果のクラス
     */
    public Object selectObject(String queryString, Object object);

    /**
     * SELECTを実行する
     *
     * @param queryString SQLID
     * @param object パラメータークラス
     * @return 実行結果のリスト
     */
    public List<?> select(String queryString, Object object);

    /**
     * 最大件数を取得する
     * @param sqlid
     * @param param
     *        パラメータ
     * @return 検索結果
     */
    public int selectCount(String sqlid, Object param);

    /**
     * iBatisのRowHandlerを使ったクエリを実行する。
     * @param queryString
     * @param object
     * @param handler
     * @throws Exception
     */
    //public void handledResult(String queryString , Object object , org.mybatis.spring. handler) throws Exception;

}
