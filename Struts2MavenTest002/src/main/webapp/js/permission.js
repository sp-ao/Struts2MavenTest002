/**
 *  操作オブジェクト指定
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