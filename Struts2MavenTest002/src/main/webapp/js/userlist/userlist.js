/**
 *  画面ロード時
 */
$(document).ready(function(){
	try {
		// Datatables設定
		$('#userListTable').dataTable( {
	        "columns": [
	            null,
	            null,
	            null,
	            null,
	            null,
	            null
	         ]
		});
	} catch(e){
		alert(e);
	}
});

// 作成ボタン押下
function userListInsert(){
	try{
		document.userListForm.action = "confirm_insert_user.action";
		document.userListForm.submit();
	} catch(e){
		alert(e);
	}
}
// 更新ボタン押下
function userListUpdate(){
	try{
		document.userListForm.action = "confirm_update_user.action";
		document.userListForm.submit();
	} catch(e){
		alert(e);
	}
}
// 削除ボタン押下
function userListDelete(element){

	// クリックされた位置取得
	var row = $(element).closest("tr").index();
	var tdval = $("#userListTable tbody tr").eq(row).children();
	var arr = new Array(row);
	try{
		// 二重送信禁止
		submitDisable(element);

		$(tdval).each(function(i, elem) {
			arr[i] = $(elem).text();
		});
		// ﾕｰｻﾞｰID
		var userId = tdval[1].textContent;

		if (window.confirm("ﾕｰｻﾞｰID：" + arr[1] + "\nこのﾕｰｻﾞｰを削除してもよろしいですか？")) {
			var ele = document.createElement("input");
			// 渡した値にスペースとカンマが入ってしまう（課題）
		    ele.setAttribute("type", "hidden");
		    ele.setAttribute("name", "userListBean.editUserId");
		    ele.setAttribute("value", userId);
			document.userListForm.appendChild(ele);
			document.userListForm.action = "user_delete.action";
			document.userListForm.submit();
		};
	} catch(e){
		alert(e);
	}
}
//クリアボタン押下
function initField() {
	try{
		if (confirm("入力された内容を全てクリアしてもよろしいですか？")) {
			$('#inp_userid').val("");
			$('#inp_username').val("");
			$('#inp_password').val("");
			$('#inp_permission').val("0");
			$('#inp_delflg').val("0");
		}
	} catch(e){
		alert(e);
	}
}
/**
 *  テーブルクリック時
 */
function tableClick(obj) {

	var row = $(obj).closest("tr").index();
	var tdval = $("#userListTable tbody tr").eq(row).children();
	var arr = new Array(row);

	try{
		$(tdval).each(function(i, elem) {
			var setVal = $(elem).text();

			if (i===1) {
				$('#inp_userid').val(setVal);
			} else if (i===2) {
				$('#inp_username').val(setVal);
			} else if (i===3) {
				if (jQuery.trim(setVal) === '管理') {
					$('#inp_permission').val('1');
				} else if (jQuery.trim(setVal) === '一般') {
					$('#inp_permission').val('0');
				} else {
					$('#inp_permission').val('2');
				}
			} else if (i===4) {
				if (jQuery.trim(setVal)  === '使用不可') {
					$('#inp_delflg').val('1');
				} else {
					$('#inp_delflg').val('0');
				}
			}
		});
	} catch(e){
		alert(e);
	}
}

