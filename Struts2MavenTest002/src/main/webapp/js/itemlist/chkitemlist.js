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
