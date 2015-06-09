/**
 * アコーディオン
 */
// 初回表示
$(document).ready(function(){
    $(".list-move").next(".list-contents").slideToggle(500, "swing");
    $(".list-move").next(".list-contents").siblings(".list-contents").slideUp(500);
    $(".list-move").toggleClass("open");
    $(this).siblings(".list-move").removeClass("open");
    // クッキーよりクラス名を取得
    var c = $.cookie("accordion");
    // 取得したクラス名を設定
    $("." + c).next(".list-contents").slideToggle(500);
});
// マウスオーバー
$(function(){
	$(".list-move").click(function(){
	    $(this).next(".list-contents").slideToggle(500, "swing");
	    $(this).next(".list-contents").siblings(".list-contents").slideUp(500);
	    $(this).toggleClass("open");
	    $(this).siblings(".list-move").removeClass("open");
	    // クリックした要素の一番目のクラスを取得
	    var c = $(this).get(0).className.split(" ")[0];
	    // クッキーに設定
	    $.cookie("accordion" , c);
	});
});