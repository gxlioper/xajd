function initBTNTool(obj) {
	var diffY = document.body.scrollTop;
	var percent = 0.1 * (diffY - lastScrollY);
	if (percent > 0) {
		percent = Math.ceil(percent);
	} else {
		percent = Math.floor(percent);
	}
	$(obj).style.width = document.body.clientWidth - 2;
	if($("tipDiv")){
		$("tipDiv").style.pixelTop += percent;
	}
	if($("tipIFrame")){
		$("tipIFrame").style.pixelTop += percent;
	}	
	if($("tipsConv")){
		$("tipsConv").style.height = document.body.scrollHeight;
	}
	
	$(obj).style.pixelTop += percent;
	lastScrollY = lastScrollY + percent;
}
function btn_style_top_width_123(){
if($("btn") && !window.dialogArguments){
	/**
	if(window.dialogArguments){
		alert(window.screenTop);
		$("btn").style.pixelTop = document.body.offsetHeight;
		$("btn").style.width = "96%";
		$("btn").style.left = "2%";
		window.setInterval("initBTNTool('btn')",1);
	}else{
	*/
		$("btn").style.pixelTop = document.body.offsetHeight - $("btn").offsetHeight;
		$("btn").style.width = document.body.clientWidth - 2;
		$("btn").style.left = "1";
		window.setInterval("initBTNTool('btn')",1);
	
}
}
window.setTimeout("btn_style_top_width_123()", 150);
if($("writeAble")){
	if($("writeAble").value != "yes"){
	    if($("btn"))
		    jQuery("#btn").style.display = "none";
		if($("btn1"))
		    $("btn1").style.display = "none";
		if($("btn2"))
		    $("btn2").style.display = "none";
		if($("btn3"))
		    $("btn3").style.display = "none";
		if($("btn4"))
		    $("btn4").style.display = "none";
	}
}