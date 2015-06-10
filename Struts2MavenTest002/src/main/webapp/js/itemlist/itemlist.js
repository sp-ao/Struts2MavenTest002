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

// アップロード
$(document).on('click', '.fileinput-upload-button', function () {
	  // クリックした時の処理
	$('.itemlist-main').mask('処理中...', 1000);
	return true;
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
			document.itemListForm[0].remove();
			document.itemListForm.action = "item_confim.action";
			document.itemListForm.submit();
		} else {
			alert('チェックボックスにチェックを入れてください');
		}
	} catch(e){
		alert(e);
	}
}
