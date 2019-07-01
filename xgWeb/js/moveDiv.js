
var bIsCatchFlyBar = false;
var dragClickX = 0;
var dragClickY = 0;
var top = 300,left = 300;


//显示临时DIV
//title:div的标题
//divId:要显示的DIV的id
//width:宽度
//height:高度
//切记：如果你要弹出层的div里面有下拉框，请把这个下拉框加上id并以select_开头
function viewTempDiv(title,divId,width,height,isbackDiv){

  if (null == isbackDiv){
  	isbackDiv = true;
  }
  
  if($('##floatDiv##')==null){//显示操作区  
   var floatDiv = document.createElement('div');
   floatDiv.id = "##floatDiv##";
   floatDiv.style.position = "absolute";
   floatDiv.style.width = width + "px";
   floatDiv.style.height = height + "px";
   floatDiv.style.backgroundColor = "#FFFFFF";
   //floatDiv.style.cursor = "move";
   
   var d_width = document.body.clientWidth;
   var d_height = document.body.clientHeight;
   
   if (d_height > 500){
   	 d_height = 500;
   }
   
   var d_left_top = (d_width - width) / 2 +"px";
   var d_top_top =  (d_height - height) / 2 + "px";
   
   floatDiv.style.top = d_top_top;
   floatDiv.style.left = d_left_top;
   floatDiv.style.zIndex  = 1001;
   
   top = d_top_top;
   left = d_left_top;
   
   temp_divId=divId;
   temp_divHtml=document.getElementById(divId).innerHTML;
   
   var dd_html="";
   dd_html += "<div id=\"windown-title\" align='left'><h2>"+title+"</h2>";
   dd_html += "<span id=\"windown-close\" onclick=\"hiddenMessage(true,true)\">关闭</span></div>";
   dd_html += "<div id=\"open_win\" class=\"open_win01\" style=\"padding-top:5px\">";
   
   floatDiv.innerHTML = dd_html+document.getElementById(divId).innerHTML+"</div>";
   document.getElementById(divId).innerHTML = "";
//   document.body.removeAttribute($(divId))
   document.forms[0].appendChild(floatDiv);
   
//   floatDiv.attachEvent('onmousedown',function(){
//   		catchDiv();
//   });
//   floatDiv.attachEvent('onmouseup',function(){
//   		releaseDiv();
//   });
//	var moveX = 0,moveY = 0,moveTop,moveLeft = 0,moveable = true,drag=true;
//	var DragHead = document.getElementById("windown-title");
//	var	sw = floatDiv.scrollWidth,sh = floatDiv.scrollHeight;
//	var	cw = document.documentElement.clientWidth,
//		ch = document.documentElement.clientHeight,
//		est = document.documentElement.scrollTop; 
//	
//	DragHead.onmouseover = function(e) {
//			if(drag){DragHead.style.cursor = "move";}else{DragHead.style.cursor = "default";}
//	};
//	
//	DragHead.onmousedown = function(e) {
//		e = window.event?window.event:e;
//		var ol = floatDiv.offsetLeft, ot = floatDiv.offsetTop-moveTop;
//		moveX = e.clientX-ol;
//		moveY = e.clientY-ot;
//		document.onmousemove = function(e) {
//			if (moveable){
//				e = window.event?window.event:e;
//				
//				var x = e.clientX - moveX;
//				var y = e.clientY - moveY;
//				if ( x > 0 &&( x + sw < cw) && y > 0 && (y + sh < ch) ) {
//					floatDiv.style.left = x + "px";
//					floatDiv.style.top = parseInt(y+moveTop) + "px";
//					floatDiv.style.margin = "auto";
//				}
//			}
//		}
//		document.onmouseup = function () {moveable = false;};
//		floatDiv.onselectstart = function(e){return false;}
//	}
   
 }
  else{
   $('##floatDiv##').style.display = "block";
  }
  if($('##backDiv##')==null && isbackDiv ==true){//显示背景  
   var backDiv = document.createElement('div');
   backDiv.id = "##backDiv##";
   backDiv.style.backgroundColor = "Black";
   backDiv.style.filter = "alpha(opacity=60)";
   backDiv.style.MozOpacity = "0.70";
   backDiv.style.position = "absolute";
   backDiv.style.left = "0px";
   backDiv.style.top = "0px";
   backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth) +"px";
   //======2010.11.13 by lr=======
   var t_height = Math.max(document.body.scrollHeight, document.body.clientHeight);
   if(t_height <800){
  	t_height = 800; 
   }
   backDiv.style.height = t_height+"px";
 //======end 2010.11.13 by lr=======
   document.body.appendChild(backDiv);
   $('##backDiv##').style.xIndex = 1000;
  }
  else if(isbackDiv ==true){
   $('##backDiv##').style.display = "block";
  }  
  	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		var arr;
		var splitName;
		
		if ($(id)){
			arr = id.split('_');
		}
		
		if (null != arr && arr.length>0){
			splitName=arr[0];
		}
		
		if (splitName!="select"){
			obj.style.display = "none";
		}
		
	}
 }


function catchDiv(e) {
	
	bIsCatchFlyBar = true;
	var x = event.x + document.body.scrollLeft;
	var y = event.y + document.body.scrollTop;
	
	dragClickX = x - $('##floatDiv##').style.pixelLeft;
	dragClickY = y - $('##floatDiv##').style.pixelTop;

	$('##floatDiv##').setCapture();
	document.onmousemove = moveDiv;
}

function releaseDiv(e) {
	bIsCatchFlyBar = false;
	$('##floatDiv##').releaseCapture();
	document.onmousemove = null;
}


function moveDiv(e){   
	if(bIsCatchFlyBar){   
		$('##floatDiv##').style.left = event.x+document.body.scrollLeft-dragClickX;   
		$('##floatDiv##').style.top = event.y+document.body.scrollTop-dragClickY;   
	}   
  
}   

function loadDiv(){   
  if ($('##floatDiv##')){
	$('##floatDiv##').style.top= top;   
	$('##floatDiv##').style.left= left;   
  }
}


//window.onresize = loadDiv;
//window.onscroll = loadDiv;

