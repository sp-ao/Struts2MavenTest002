package struts2maven.Struts2MavenTest002.model;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class ItemList {

	public static final String[] HEADER
		= new String[]{"ItemCd", "ItemName", "ItemKbn", "DelFlg", "LastUpdate"};
	public static final String[] HEADER_CONFIRM
		= new String[]{"商品ｺｰﾄﾞ", "商品名", "商品区分", "状態"};

	private String itemCd;
	private String itemName;
	private String itemKbn;
	private String delFlg;
	private String lastUpdate;
	private String targetRow;

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	public String getItemCd() {
		return itemCd;
	}
	/**
	* @param edituserid the edituserid to set
	* 商品名。必須入力
	* shortCircuit実装。
	*/
	@RequiredStringValidator(
			message="",
			key="itemList.input.itemName.require",
			shortCircuit=true)
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemName() {
		return itemName;
	}
	/**
	* @param edituserid the edituserid to set
	* 商品区分。必須入力、半角英数のみ。
	* shortCircuit実装。
	*/
	@RequiredStringValidator(
			message="",
			key="itemList.input.itemKbn.require",
			shortCircuit=true)
	@RegexFieldValidator(
			message="",
			key="itemList.input.itemKbn.regex",
			regexExpression="[0-9A-Za-z_]*")
	public void setItemKbn(String itemKbn) {
		this.itemKbn = itemKbn;
	}
	public String getItemKbn() {
		return itemKbn;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public String getTargetRow() {
		return targetRow;
	}
	public void setTargetRow(String onoff) {
		targetRow = onoff;
	}
}
