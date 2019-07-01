/*****************************
onmouseover-onmouseout(grcc)
Version: Ver 1.0
Passed : XHtml 1.0, CSS 2.0, IE5.0+, FF1.0+, Opera8.5+
Update : 2010-08-23
*****************************/
function mouseover(str) {
if(document.getElementById(str)) {
document.getElementById(str).style.display="";
}
}
function mouseout(str) {
if(document.getElementById(str)) {
document.getElementById(str).style.display="none";
}
}

/*****************************
showhideDiv
Version: Ver 1.02
Passed : XHtml 1.0, CSS 2.0, IE5.0+, FF1.0+, Opera8.5+
Update : 2010-07-28
*****************************/
function getNext(o)
{
	while(o)
	{
		if(o.nextSibling.nodeType==1)
		{return o.nextSibling;}
		o=o.nextSibling;
	}
	return o;
}	
function showhidediv(o,id)
{
	var menuList=document.getElementById('gnmkid').value;
	var arrId = new Array();
	
	gnmk = menuList.split(splitSignOne);
	
    var sbtitle = getNext(o);
	if(sbtitle)
	{	
   		if(sbtitle.style.display=='block')
		{	
      	 	sbtitle.style.display = 'none';
       		o.className = "open";
   		}
		else
		{
   			sbtitle.style.display = 'block';
  			o.className = "close";
  			//�㿪һ����ģ��ر���չ����ģ��by qlj 2011.6.23
  			for(i = 2; i < gnmk.length; i++){
  				var ulid = "ul"+i;
  				var divid = "hh"+i;
  				if(ulid !=sbtitle.id && $(ulid)){
  					$(ulid).style.display = 'none';
  					$(divid).className = "open";
  				}
  			}
   		}
	
		if(sbtitle.style.display=='none')
		{
      	 	sbtitle.style.display = 'none';
       		o.className = "open";
   		}
		else
		{
   			sbtitle.style.display = 'block';
  			o.className = "close";
   		}
		
	}
}

/*****************************
JavaScript Code for CNLTreeMenu
Version: Ver 1.02
Passed : XHtml 1.0, CSS 2.0, IE5.0+, FF1.0+, Opera8.5+
Update : 2010-07-28
*****************************/
function Ob(o){
 var o=document.getElementById(o)?document.getElementById(o):o;
 return o;
}
function Hd(o) {
 Ob(o).style.display="none";
}
function Sw(o) {
 Ob(o).style.display="";
}
function ExCls(o,a,b,n){
 var o=Ob(o);
 for(i=0;i<n;i++) {o=o.parentNode;}
 o.className=o.className==a?b:a;
}
function CNLTreeMenu(id,TagName0) {
  this.id=id;
  this.TagName0=TagName0==""?"li":TagName0;
  this.AllNodes = Ob(this.id).getElementsByTagName(TagName0);
  this.InitCss = function (ClassName0,ClassName1,ClassName2,ImgUrl,parentId) {
  this.ClassName0=ClassName0;
  this.ClassName1=ClassName1;
  this.ClassName2=ClassName2;
  this.ImgUrl=ImgUrl || " ";
  this.ImgBlankA ="<img src=\""+this.ImgUrl+"\" class=\"s\" onclick=\"ExCls(this,'"+ClassName0+"','"+ClassName1+"',1);\" />";
  this.ImgBlankB ="<img src=\""+this.ImgUrl+"\" class=\"s\" />";
  
  	//=======edit by lr 2011.1.20 ==========
	  if(parentId != undefined && parentId != ""){
		 var num = 0;
		 this.AllNodes = [];
		 for(var m=0; m<Ob(parentId).children.length;m++){
			 if(Ob(parentId).children[m].tagName == "UL"){
				 this.AllNodes = Ob(parentId).children[m].children;
			 }
		 }
	  }
	  //=====end======================
  
  
  for (i=0;i<this.AllNodes.length;i++ ) {
   this.AllNodes[i].className==""?this.AllNodes[i].className=ClassName1:"";
   this.AllNodes[i].innerHTML=(this.AllNodes[i].className==ClassName2?this.ImgBlankB:this.ImgBlankA)+this.AllNodes[i].innerHTML;
   }
 }
 this.SetNodes = function (n) {
  var sClsName=n==0?this.ClassName0:this.ClassName1;
  for (i=0;i<this.AllNodes.length;i++ ) {
   this.AllNodes[i].className==this.ClassName2?"":this.AllNodes[i].className=sClsName;
  }
 }
}

/*=====实现弹出层的相关js=====*/
///-------------------------------------------------------------------------
//jQuery弹出窗口 By Await [2009-11-22]
//--------------------------------------------------------------------------
/*参数：[可�1ￄ1�7�1�7�1�7�1�7�参数在调用时可写可不写,其他为必写]
----------------------------------------------------------------------------
    title:	窗口标题
  content:  内容(可�1ￄ1�7�1�7�1�7�1�7�内容为){ text | id | img | url | iframe }
    width:	内容宽度
   height:	内容高度
	 drag:  是否可以拖动(ture为是,false为否)
     time:	自动关闭等待的时间，为空是则不自动关闄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
   showbg:	[可�1ￄ1�7�1�7�1�7�1�7�参数]设置是否显示遮罩屄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7为不显示,1为显礄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
  cssName:  [可�1ￄ1�7�1�7�1�7�1�7�参数]附加class名称
 ------------------------------------------------------------------------*/
 //示例:黑色背景
 //------------------------------------------------------------------------
 //simpleWindown("例子","text:例子","500","400","true","3000","0","exa")
 //------------------------------------------------------------------------
var tempID = "";
var tempHTML = "";
var showWindown = true;
//var templateSrc = ""; 设置loading.gif路径
function tipsWindown(title,content,width,height,drag,time,showbg,cssName) {
	jQuery("#windown-box").remove(); //请除内容
	var width = width>= 950?this.width=950:this.width=width;	    //设置朄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7�窗口宽庄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	var height = height>= 527?this.height=527:this.height=height;  //设置朄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7�窗口高庄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	if(showWindown == true) {
		var simpleWindown_html = new String;
			
			simpleWindown_html = "<div id=\"windownbg\" class=\"windownbg\" style=\"height:"+jQuery(document).height()+"px;filter:alpha(opacity=0);opacity:0;z-index: 998\"><iframe style=\"width:100%;height:100%;filter:alpha(opacity=0);-moz-opacity:0\" frameborder=\"0\" ></iframe></div>";
			 //iframe试图解决IE6下浮动图片被select覆盖问题
			simpleWindown_html += "<div id=\"windown-box\" class=\"windown-box\" >";
			simpleWindown_html += "<div id=\"windown-title\" class=\"windown-title\"><h2></h2><span id=\"windown-close\" class=\"windown-close\">关闭</span></div>";
			simpleWindown_html += "<div id=\"windown-content-border\" class=\"windown-content-border\"><div id=\"windown-content\" class=\"windown-content\"></div></div>"; 
			simpleWindown_html += "</div>";
			if(jQuery("body>form:first").size()==1){
				jQuery("body>form:first").append(simpleWindown_html);
			}else{
				jQuery("body").append(simpleWindown_html);
			}
			show = false;
	}
	contentType = content.substring(0,content.indexOf(":"));
	content = content.substring(content.indexOf(":")+1,content.length);
	switch(contentType) {
		case "text":
			jQuery("#windown-content").html(content);
		break;
		case "id":
			tempID = content;
			tempHTML = jQuery("#"+content+"").html();
			
			jQuery("#windown-content").html(tempHTML);
			jQuery("#"+content+"").html('');
		break;
		case "img":
			jQuery("#windown-content").ajaxStart(function() {
				jQuery(this).html("<img src='../images/loading.gif' class='loading' />");
			});
			jQuery.ajax({
				error:function(){
					jQuery("#windown-content").html("<p class='windown-error'>??...</p>");
				},
				success:function(html){
					jQuery("#windown-content").html("<img src="+content+" alt='' />");
				}
			});
		break;
		case "url":
			var content_array=content.split("#");
			jQuery("#windown-content").ajaxStart(function(){
				jQuery(this).html("<p class='loading'/></p>");
			});
			jQuery.ajax({
				type:content_array[0],
				url:content_array[1],
				data:content_array[2],
				async: false,
				error:function(){
					jQuery("#windown-content").html("<p class='windown-error'>加载数据出错...</p>");
				},
				success:function(html){
					jQuery("#windown-content").html(html);
				}
			});
		break;
		case "iframe":
		jQuery("#windown-content").ajaxStart(function(){
			jQuery(this).html("<img src='../images/loading.gif' class='loading' />");
		});
		jQuery.ajax({
			error:function(){
				jQuery("#windown-content").html("<p class='windown-error'>加载数据出错...</p>");
			},
			success:function(html){
				jQuery("#windown-content").html("<iframe src=\""+content+"\" width=\"100%\" height=\""+parseInt(height)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
			}
		});
	}
	jQuery("#windown-title h2").html(title);
	if(showbg == "true") {jQuery("#windownbg").show();}else {jQuery("#windownbg").remove();};
	jQuery("#windownbg").animate({opacity:"0.5"},"normal");//设置透明庄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	jQuery("#windown-box").show();
	if( height >= 527 ) {
		jQuery("#windown-title").css({width:(parseInt(width)+22)+"px"});
		jQuery("#windown-content").css({width:(parseInt(width)+17)+"px",height:height+"px"});
	}else {
		jQuery("#windown-title").css({width:(parseInt(width)+10)+"px"});
		jQuery("#windown-content").css({width:width+"px",height:height+"px"});
	}
	var	cw = document.documentElement.clientWidth,ch = document.documentElement.clientHeight,est = document.documentElement.scrollTop; 
	var _version = jQuery.browser.version;
	if ( _version == 6.0 ) {
		jQuery("#windown-box").css({left:"50%",top:"220px",marginTop: -((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "998"});
	}else {
		jQuery("#windown-box").css({left:"50%",top:"220px",marginTop:-((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "998"});
	};
	var Drag_ID = document.getElementById("windown-box"),DragHead = document.getElementById("windown-title");
		
	var moveX = 0,moveY = 0,moveTop,moveLeft = 0,moveable = false;
		if ( _version == 6.0 ) {
			moveTop = est;
		}else {
			moveTop = 0;
		}
	var	sw = Drag_ID.scrollWidth,sh = Drag_ID.scrollHeight;
		DragHead.onmouseover = function(e) {
			if(drag == "true"){DragHead.style.cursor = "move";}else{DragHead.style.cursor = "default";}
		};
		DragHead.onmousedown = function(e) {
		if(drag == "true"){moveable = true;}else{moveable = false;}
		e = window.event?window.event:e;
		var ol = Drag_ID.offsetLeft, ot = Drag_ID.offsetTop-moveTop;
		moveX = e.clientX-ol;
		moveY = e.clientY-ot;
		document.onmousemove = function(e) {
				if (moveable) {
				e = window.event?window.event:e;
				var x = e.clientX - moveX;
				var y = e.clientY - moveY;
					if ( x > 0 &&( x + sw < cw) && y > 0 && (y + sh < ch) ) {
						Drag_ID.style.left = x + "px";
						Drag_ID.style.top = parseInt(y+moveTop) + "px";
						Drag_ID.style.margin = "auto";
						}
					}
				}
		document.onmouseup = function () {moveable = false;};
		Drag_ID.onselectstart = function(e){return false;}
	}
	jQuery("#windown-content").attr("class","windown-"+cssName);
	
	if( time == "" || typeof(time) == "undefined") {
		jQuery("#windown-close").click(function() {
			closeWindown();
		});
	}else { 
		setTimeout('closeWindown',time);
	}
	
	jQuery("#windownbg").focus();
	return false;
}

function tipsWindownX(title,content,width,height,drag,time,showbg,cssName) {
	jQuery("#windown-box").remove(); //请除内容
	var width = width>= 950?this.width=950:this.width=width;	    //设置朄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7�窗口宽庄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	var height = height>= 527?this.height=527:this.height=height;  //设置朄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7�窗口高庄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	if(showWindown == true) {
		var simpleWindown_html = new String;
			
			simpleWindown_html = "<div id=\"windownbg\" class=\"windownbg\" style=\"height:"+jQuery(document).height()+"px;filter:alpha(opacity=0);opacity:0;z-index: 998\"><iframe style=\"width:100%;height:100%;filter:alpha(opacity=0);-moz-opacity:0\" frameborder=\"0\" ></iframe></div>";
			 //iframe试图解决IE6下浮动图片被select覆盖问题
			simpleWindown_html += "<div id=\"windown-box\" class=\"windown-box\" >";
			simpleWindown_html += "<div id=\"windown-title\" class=\"windown-title\"><h2></h2><span id=\"windown-close\" class=\"windown-close\">关闭</span></div>";
			simpleWindown_html += "<div id=\"windown-content-border\" class=\"windown-content-border\"><div id=\"windown-content\" class=\"windown-content\"></div></div>"; 
			simpleWindown_html += "</div>";
			if(jQuery("body>form:first").size()==1){
				jQuery("body>form:first").append(simpleWindown_html);
			}else{
				jQuery("body").append(simpleWindown_html);
			}
			show = false;
	}
	contentType = content.substring(0,content.indexOf(":"));
	content = content.substring(content.indexOf(":")+1,content.length);
	switch(contentType) {
		case "text":
			jQuery("#windown-content").html(content);
		break;
		case "id":
			tempID = content;
			tempHTML = jQuery("#"+content+"").html();
			
			jQuery("#windown-content").html(tempHTML);
			jQuery("#"+content+"").html('');
		break;
		case "img":
			jQuery("#windown-content").ajaxStart(function() {
				jQuery(this).html("<img src='../images/loading.gif' class='loading' />");
			});
			jQuery.ajax({
				error:function(){
					jQuery("#windown-content").html("<p class='windown-error'>??...</p>");
				},
				success:function(html){
					jQuery("#windown-content").html("<img src="+content+" alt='' />");
				}
			});
		break;
		case "url":
			var content_array=content.split("#");
			jQuery("#windown-content").ajaxStart(function(){
				jQuery(this).html("<p class='loading'/></p>");
			});
			jQuery.ajax({
				type:content_array[0],
				url:content_array[1],
				data:content_array[2],
				async: false,
				error:function(){
					jQuery("#windown-content").html("<p class='windown-error'>加载数据出错...</p>");
				},
				success:function(html){
					jQuery("#windown-content").html(html);
				}
			});
		break;
		case "iframe":
		jQuery("#windown-content").ajaxStart(function(){
			jQuery(this).html("<img src='../images/loading.gif' class='loading' />");
		});
		jQuery.ajax({
			error:function(){
				jQuery("#windown-content").html("<p class='windown-error'>加载数据出错...</p>");
			},
			success:function(html){
				jQuery("#windown-content").html("<iframe src=\""+content+"\" width=\"100%\" height=\""+parseInt(height)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
			}
		});
	}
	jQuery("#windown-title h2").html(title);
	if(showbg == "true") {jQuery("#windownbg").show();}else {jQuery("#windownbg").remove();};
	jQuery("#windownbg").animate({opacity:"0.5"},"normal");//设置透明庄1�7�1�7�1�7�1�7�1�7�1�7�1�7�1�7
	jQuery("#windown-box").show();
	if( height >= 527 ) {
		jQuery("#windown-title").css({width:(parseInt(width)+22)+"px"});
		jQuery("#windown-content").css({width:(parseInt(width)+17)+"px",height:height+"px"});
	}else {
		jQuery("#windown-title").css({width:(parseInt(width)+10)+"px"});
		jQuery("#windown-content").css({width:width+"px",height:height+"px"});
	}
	var	cw = document.documentElement.clientWidth,ch = document.documentElement.clientHeight,est = document.documentElement.scrollTop; 
	var _version = jQuery.browser.version;
	if ( _version == 6.0 ) {
		jQuery("#windown-box").css({left:"50%",top:"220px",marginTop: -((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "998"});
	}else {
		jQuery("#windown-box").css({left:"50%",top:"220px",marginTop:-((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "998"});
	};
	var Drag_ID = document.getElementById("windown-box"),DragHead = document.getElementById("windown-title");
		
	var moveX = 0,moveY = 0,moveTop,moveLeft = 0,moveable = false;
		if ( _version == 6.0 ) {
			moveTop = est;
		}else {
			moveTop = 0;
		}
	var	sw = Drag_ID.scrollWidth,sh = Drag_ID.scrollHeight;
		DragHead.onmouseover = function(e) {
			if(drag == "true"){DragHead.style.cursor = "move";}else{DragHead.style.cursor = "default";}
		};
		DragHead.onmousedown = function(e) {
		if(drag == "true"){moveable = true;}else{moveable = false;}
		e = window.event?window.event:e;
		var ol = Drag_ID.offsetLeft, ot = Drag_ID.offsetTop-moveTop;
		moveX = e.clientX-ol;
		moveY = e.clientY-ot;
		document.onmousemove = function(e) {
				if (moveable) {
				e = window.event?window.event:e;
				var x = e.clientX - moveX;
				var y = e.clientY - moveY;
					if ( x > 0 &&( x + sw < cw) && y > 0 && (y + sh < ch) ) {
						Drag_ID.style.left = x + "px";
						Drag_ID.style.top = parseInt(y+moveTop) + "px";
						Drag_ID.style.margin = "auto";
						}
					}
				}
		document.onmouseup = function () {moveable = false;};
		Drag_ID.onselectstart = function(e){return false;}
	}
	jQuery("#windown-content").attr("class","windown-"+cssName);
	
	if( time == "" || typeof(time) == "undefined") {
		jQuery("#windown-close").click(function() {
			closeWindown();
		});
	}else { 
		setTimeout('closeWindown',time);
	}
	
	jQuery("#windownbg").focus();
	return false;
}


///////////////////////////////////////////////////////////////////////////////////////

