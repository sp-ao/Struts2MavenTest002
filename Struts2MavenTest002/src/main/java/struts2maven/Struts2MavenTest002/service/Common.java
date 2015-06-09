package struts2maven.Struts2MavenTest002.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Bean;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.opensymphony.xwork2.ValidationAware;

import struts2maven.Struts2MavenTest002.action.LoginAction;
import sun.tools.tree.ThisExpression;

/**
 * 共通処理
 * @author sa
 *
 */
public class Common {
	// ファイル作成
	public static void makeFile(String fileName,
								String column,
								String colFlg,
								String separator,
								List<Object> dataList) throws Exception {
		try {
			// リストが空でない場合
			if (!dataList.isEmpty()) {
				//
				Iterator<Object> iterator = dataList.iterator();
				// バッファ開く
				BufferedWriter bw =
						new BufferedWriter(
							new OutputStreamWriter(
								new FileOutputStream(fileName)));
				// ヘッダ存在するなら
				if (!column.isEmpty()) {
					// ヘッダONOFFフラグ="1"
					if (colFlg == "1") {
						// ヘッダを出力
						bw.write(column);
						bw.newLine();
					}
					// ヘッダを配列に格納
					String[] arrayCol = column.split(",", -1);
					// リスト分ループ
					while (iterator.hasNext()) {
						// リスト一行取出し
						Object st = iterator.next();
						// ファイルへ出力
						outputFile(bw, arrayCol, st, separator);
						// 改行
						bw.newLine();
					}
					// バッファ閉じる
					bw.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ファイル削除
	public static void deleteFile(List<String> delFile) {
		try {
			for (String fileName:delFile) {
				if (fileName != null && fileName != "") {
					// ファイルオブジェクト作成
					File file = new File(fileName);
					if (file.exists()) {
						// ファイル削除
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ファイル出力
	public static BufferedWriter outputFile(BufferedWriter bw,
									  String[] arrayCol,
									  Object st,
									  String separator) {
		try {
			// カラムの数分出力
			for (int count = 0; count < arrayCol.length; count++){
				// カラムのデータをバッファに格納
				bw.write(((HashMap<?, ?>) st).get(arrayCol[count]).toString());
				// 最後のカラム以外
				if (count < arrayCol.length - 1) {
					// カンマをバッファに格納
					bw.write(separator);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bw;
	}

	public static final String FIELD_ERRORS_KEY	= "__MessageStoreInterceptor_FieldErrors_SessionKey";
	public static final String ACTION_ERRORS_KEY   = "__MessageStoreInterceptor_ActionErrors_SessionKey";
	public static final String ACTION_MESSAGES_KEY = "__MessageStoreInterceptor_ActionMessages_SessionKey";
	// メッセージ削除
	public static void clearBefMessage() {
		try {
			// セッション取得
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.removeAttribute(ACTION_ERRORS_KEY);
			session.removeAttribute(ACTION_MESSAGES_KEY);
			session.removeAttribute(FIELD_ERRORS_KEY);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}