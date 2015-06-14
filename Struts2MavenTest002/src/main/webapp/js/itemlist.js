/**
 *  画面ロード時
 */
$(document).ready(function(){
	try {
		// Datatables設定
		$("#itemListTable").dataTable({
			"processing": false,
			"serverSide": false,
			"ajax": {
				"url": "getItemJson.action",
				"type": "POST"
			},
			"columns": [
				{
					"data" : "itemcd",
					"orderDataType": "dom-text",
					"render": function ( data, type, full, meta ) {
						return '<input type="text" name="itemList[' + meta.row + '].itemCd" id="itemList[' + meta.row + '].itemCd" value="' + data + '" class="form-control input-sm input-itemcd"  maxlength="10" readonly="true" />';

					}
				},
				{
					"data" : "itemname",
					"orderDataType": "dom-text",
					"type" : 'string',
					"render": function ( data, type, full, meta ) {
						return '<input type="text" name="itemList[' + meta.row + '].itemName" id="itemList[' + meta.row + '].itemName" value="' + data + '" class="form-control input-sm input-itemname"  maxlength="25" />';
					}
				},
				{ "data" : "itemkbn",
					"orderDataType": "dom-text",
					"render": function ( data, type, full, meta ) {
						return '<input type="text" name="itemList[' + meta.row + '].itemKbn" id="itemList[' + meta.row + '].itemKbn" value="' + data + '" class="form-control input-sm input-itemkbn"  maxlength="2" />';
					}
				},
				{ "data" : "delflg",
					"orderDataType": "dom-select",
					"render": function ( data, type, full, meta ) {
						if (data == '0') {
							return '<select name="itemList[' + meta.row + '].delFlg" id="itemList[' + meta.row + '].delFlg" class="form-control input-sm input-delflg"><option value="0" selected>使用</option><option value="1">削除</option></select>';
						} else {
							return '<select name="itemList[' + meta.row + '].delFlg" id="itemList[' + meta.row + '].delFlg" class="form-control input-sm input-delflg"><option value="0">使用</option><option value="1" selected>削除</option></select>';
						}
					}
				},
				{ "data" : null,
					"orderDataType": "dom-checkbox",
					"render": function ( data, type, full, meta ) {
						return '<input type="checkbox" name="itemList[' + meta.row + '].targetRow" id="itemList[' + meta.row + '].targetRow" class="check" />';
					}
				}
			],
			'language': {
				'lengthMenu': '表示件数 _MENU_ 件',
				'zeroRecords': '表示データ読込中',
				'info': '表示中ページ _PAGE_ / _PAGES_ ',
				'infoEmpty': '読込中',
				'infoFiltered': '(フィルター中 全レコード _MAX_ 件)',
				"paginate": {
					"first": "最初",
					"last": "最後",
					"next": "次",
					"previous": "前"
				},
				"search": "フィルター:",
			}
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
			//document.itemListForm[0].remove();
			document.itemListForm.action = "item_confim.action";
			document.itemListForm.submit();
		} else {
			alert('チェックボックスにチェックを入れてください');
		}
	} catch(e){
		alert(e);
	}
}
//ファイルチェック
function isCsv(fileName) {
	var ret = false;
	try {
		// ファイル拡張子チェック
		var Extension = getExtension(fileName);
		if (Extension.toLowerCase() === "csv") {
			//displayMask();
			ret = true;
		} else {
			alert('CSVﾌｧｲﾙを選択してください');
		}
	} catch(e){
		alert(e);
	}
	return ret;
}
function getExtension(fileName) {
	var ret;
	try {
		if (!fileName) {
			return ret;
		}
		var fileTypes = fileName.split(".");
		var len = fileTypes.length;
		if (len === 0) {
			return ret;
		}
		ret = fileTypes[len - 1];
	} catch(e){
		alert(e);
	}
	return ret;
}