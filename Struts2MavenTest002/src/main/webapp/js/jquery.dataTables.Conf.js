/**
 *  画面ロード時
 */
$(document).ready(function(){

	// input
	var inputTags = document.getElementById("main-contents").getElementsByTagName("input");
	// 権限取得
	var permissionVal = document.getElementById('permission').value;
	if (permissionVal != '1' && permissionVal != '0') {
		// ｹﾞｽﾄの操作対象設定
		for(var i = 0; i < inputTags.length; i++){
			inputTags[i].disabled = true;
		}
	} else if (permissionVal == '0') {
		// 一般の操作対象設定
	}
});

/**
 *  Datatable用
 */
/* 列のすべての入力ボックスの値を持つ配列を作成します。 */
$.fn.dataTable.ext.order['dom-text'] = function  ( settings, col )
{
    return this.api().column( col, {order:'index'} ).nodes().map( function ( td, i ) {
        return $('input', td).val();
    } );
}

/* 列のすべての入力ボックスの値を持つ配列を作成し、数値として解析 */
$.fn.dataTable.ext.order['dom-text-numeric'] = function  ( settings, col )
{
    return this.api().column( col, {order:'index'} ).nodes().map( function ( td, i ) {
        return $('input', td).val() * 1;
    } );
}

/* 列のすべての選択オプションの値を持つ配列を作成します。 */
$.fn.dataTable.ext.order['dom-select'] = function  ( settings, col )
{
    return this.api().column( col, {order:'index'} ).nodes().map( function ( td, i ) {
        return $('select', td).val();
    } );
}

/* 列のすべてのチェックボックスの値を持つ配列を作成します */
$.fn.dataTable.ext.order['dom-checkbox'] = function  ( settings, col )
{
    return this.api().column( col, {order:'index'} ).nodes().map( function ( td, i ) {
        return $('input', td).prop('checked') ? '1' : '0';
    } );
}