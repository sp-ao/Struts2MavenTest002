/**
 *  画面ロード時
 */
$(document).ready(function(){
	try {
		// フィルタ用カウンター
		var zeroCnt = 0;
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
					"type" : 'string',
					"render": function ( data, type, full, meta ) {
						return '<input type="text" name="itemList[' + meta.row + '].itemCd" id="itemList[' + meta.row + '].itemCd" maxlength="10" value="' + data + '" class="form-control input-sm input-itemcd" readonly="true" />';

					}
				},
				{
					"data" : "itemname",
					"orderDataType": "dom-text",
					"type" : 'string',
					"render": function ( data, type, full, meta ) {
						return '<input type="text" name="itemList[' + meta.row + '].itemName" id="itemList[' + meta.row + '].itemName" maxlength="25" value="' + data + '" class="form-control input-sm input-itemname" />';
					}
				},
				{ "data" : "itemkbn",
					"orderDataType": "dom-text",
					"type" : 'string',
					"render": function ( data, type, full, meta ) {
						return '<input type="text" name="itemList[' + meta.row + '].itemKbn" id="itemList[' + meta.row + '].itemKbn" maxlength="2" value="' + data + '" class="form-control input-sm input-itemkbn" />';
					}
				},
				{ "data" : "delflg",
					"orderDataType": "dom-select",
					"type" : 'string',
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
			'zeroRecords': '表示データなし',
			'info': '表示中ページ _PAGE_ / _PAGES_ ',
			'infoEmpty': '表示ページなし',
			'infoFiltered': '(フィルター中 全レコード _MAX_ 件)',
			"paginate": {
				"first": "最初",
				"last": "最後",
				"next": "次",
				"previous": "前"
			},
				"search": "フィルター:",
			},
			initComplete: function () {
				this.api().columns().every( function () {
					var reg = "";
					var column = this;
					var colNum = column[0];
					// 商品区分、状態
					if (colNum == 2 || colNum == 3) {
						var select = $('<select class="form-control input-sm" placeholder="フィルター"><option value=""></option></select>')
							.appendTo( $(column.footer()).empty())
							.on('change', function () {
								var val = $.fn.dataTable.util.escapeRegex(
									$(this).val()
								);
								// selectタグフィルタ
								if (colNum == 3) {
									// 「selected>使用or削除」となっている行取得
									reg = "selected." + this.value;
								} else {
									// 「value=値」となっている行取得
									reg = "value=.+" + this.value + "";
								}
								// フィルタ実行
								column
									.search(reg, true, false)
									.draw();
							});
					// それ以外
					} else {
						if (colNum != 4) {
							var title = $('#itemListTable thead th')
								.eq(column.index()).text();
							$('#itemListTable tfoot th')
								.eq(column.index())
								.html('<input type="text" class="form-control input-sm itemListTableSearch" placeholder="ﾌｨﾙﾀ '+title+'" />');
							var that = column;
							$('input', column.footer()).on( 'keyup change', function() {
								// 「value=値」となっている行取得
								reg = "value=.+" + this.value + "";
								// フィルタ実行
								that
									.search(reg, true, false)
									.draw();
							});
						}
					}

					// フィルタ内容作成
					column.data().unique().sort().each( function ( d, j ) {
						// 項目切替時カウント
						if (j == 0) {
							zeroCnt = zeroCnt+1;
						}
						// カウンタ判定
						if (zeroCnt == 4) {
							// select用selectタグ設定
							var delFlgText = $('.input-delflg option')[j].text;
							select.append( '<option value="'+delFlgText+'">'+delFlgText+'</option>' )
						} else if (zeroCnt == 3) {
							select.append( '<option value="'+d+'">'+d+'</option>' )
						}
					} );
				} );
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
			// 不要な要素削除
			var element = document.getElementsByName('itemListTable_length');
			for (i = element.length - 1; i >= 0; i--) {
			    element[i].parentNode.removeChild(element[i]);
			}
			// アクション設定
			document.itemListForm.action = "item_confim.action";
			// サブミット
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