/**
 *  画面ロード時
 */
$(document).ready(function(){
	try {
		// Datatables設定
		$('#itemListTable').dataTable( {
	        "columns": [
	            null,
	            { "orderDataType": "dom-text-numeric" },
	            { "orderDataType": "dom-text", type: 'string' },
	            { "orderDataType": "dom-text", type: 'string' },
	            { "orderDataType": "dom-select" },
	            { "orderDataType": "dom-checkbox" }
	         ]
		});
	} catch(e){
		alert(e);
	}
});

//更新ボタン押下
function itemListUpdate(){
	try {
		// チェックボックスチェック
		var chkVal = $('.check:checked').map(function() {
			  return $(this).val();
		});
		// 一つ以上チェックされていた場合
		if (chkVal.length > 0) {
			document.itemListForm.action = "item_confim.action";
			document.itemListForm.submit();
		} else {
			alert('チェックボックスにチェックを入れてください');
		}
	} catch(e){
		alert(e);
	}
}
