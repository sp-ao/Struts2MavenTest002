/**
 *  画面ロード時
 */
$(document).ready(function(){
	try {
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
/**
 *  処理中マスク
 */
function displayMask() {
	try {
		// マスクの対象Class設定
		// メッセージとdelay （マスク表示までの遅延時間ミリ秒）を指定
		$('.main-contents').mask('処理中...', 1000);
	} catch(e){
		alert(e);
	}
}
/**
 *  二重送信・多重送信禁止
 */
function submitDisable(element){
//	try{
//		$('<input />').attr('type', 'hidden')
//			.attr('name', $(element).attr('name'))
//			.attr('value', $(element).val())
//			.appendTo('.multiBtnForm');
//		$(element).text('please wait...');
//		$(element).attr('disabled', true);
//	} catch(e) {
//	  alert(e );
//	}
}