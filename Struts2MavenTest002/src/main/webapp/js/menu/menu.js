/**
 *  画面ロード時
 */
//}
$(document).ready(function () {
	try {
		// jsonパス
		var pathJson = document.getElementById('json-manu').value;
		//var pathJson = 'http://localhost:8080/Struts2MavenTest002/json/menu.json';
		$.get(pathJson, function(data){
			for(var i in data){
				$('#output').append('<dt><h4><strong>' + data[i].division + '</strong></h4></dt>');
				for(var j in data[i].kinou){
					$('#output').append('<dd>' + data[i].kinou[j].name + '（' + data[i].kinou[j].comment + '）</dd>');
				}
			}
		});
	} catch(e){
		alert(e);
	}
});