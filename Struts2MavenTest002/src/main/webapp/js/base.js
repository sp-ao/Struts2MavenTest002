/**
 *  画面ロード時
 */
$(document).ready(function(){
	try {
		// フォーム存在判定
		if (!!document.forms[0]) {
			// フォーカス設定
			document.forms[0].elements[0].focus();
		}

		// エラーブロックに要素が存在する場合
		if ($('.alert-danger *')[0]) {
			// エラーブロック表示
			$('.alert-danger').css('display','block');
		// エラーブロックに要素が存在しない場合
		} else {
			// エラーブロック非表示
			$('.alert-danger').css('display','none');
		}
		// メッセージブロックに要素が存在する場合
		if ($('.alert-success *')[0]) {
			// メッセージブロック表示
			$('.alert-success').css('display','block');
		// メッセージブロックに要素が存在しない場合
		} else {
			// メッセージブロック非表示
			$('.alert-success').css('display','none');
		}
		// 二重クリック禁止
		DisableSubmit.init( { "id":["btn-login","btn-exec","btn-back"] }, 30000);
	} catch(e){
		alert(e);
	}
});

/**
 *  チェックボックス全選択
 */
function checkBoxAll(obj) {
	try {
		$('.' + obj.id).prop('checked', obj.checked);
	} catch(e){
		alert(e);
	}
}