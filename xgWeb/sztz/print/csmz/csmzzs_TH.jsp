<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>sztzzs.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
	<style media="print">
	.tbstyle{display:none}
	</style>
	<style>
.weizhi{
	position:absolute;
	left:1cm;
	top:1cm;
	}
.weizhi ul{
	list-style:none;
	margin:0px;
	padding:0px;	
	}
.weizhi li{
	list-style:none;
	margin:30px 0px;
	font-size:10px
	padding:0px;
	}
.weizhi h4{
	float:left;
	margin:0px 20px;
	font-size:16px;
	font-family:黑体;
	font-weight:normal;
	break-all:keep-all
	}	
.youbianxia{
	margin-top:5px;
	}
.yueri{
	margin-left:5px}
div{
   cursor:hand;
   position: absolute; 
   word-break:keep-all
}
.clin{
   font-family:cursive;
   font-size:12px;
   color: red;
}	
</style>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  </head>
  	<script language="javascript" src="js/function.js"></script>
  	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>

<script language="javascript">
var flag = 0;
var dragswitch=0
var nsx
function drag_dropns(name){
temp=eval(name)
temp.captureEvents(Event.MOUSEDOWN | Event.MOUSEUP)
temp.onmousedown=gons
temp.onmousemove=dragns
temp.onmouseup=stopns
}
function gons(e){
temp.captureEvents(Event.MOUSEMOVE)
nsx=e.x
nsy=e.y
}
function dragns(e){
if (dragswitch==1){
temp.moveBy(e.x-nsx,e.y-nsy)
return false
}
}
function stopns(){
temp.releaseEvents(Event.MOUSEMOVE)
}
var dragapproved=false

function drag_dropie(obj){
if (dragapproved==true){
obj.style.pixelLeft=tempx+event.clientX-iex
obj.style.pixelTop=tempy+event.clientY-iey
return false
}
}
function initializedragie(obj){
iex=event.clientX
iey=event.clientY
tempx=obj.style.pixelLeft
tempy=obj.style.pixelTop
dragapproved=true
}
if (document.all){
document.onmouseup=new Function("dragapproved=false");
}
function printField(){
  var obj = $("printField");
  obj.style.top = "50px";  
  if(flag==1){
     flag =0;
     $("flagBt").value="<==";
     $("flagBt").title="向左调整";
     obj.style.left = "350px";
  }else{
     flag =1;
     $("flagBt").value="==>";
     $("flagBt").title="向右调整";
     obj.style.left = "20px";
  }   
} 
</script>
  <body onload="printField()">
	<input type="hidden" name="fzrq" id="fzrq"
		value="<bean:write name="fzrq" />" />
	<input type="hidden" name="mb" id="mb" value="<bean:write name="mb" />" />
	<html:form action="/xcxy_sztzprintOne" method="post">
		<input type="hidden" name="topstr" id="topstr" value="" />
		<input type="hidden" name="leftstr" id="leftstr" value="" />
		<div id="printField" style="position: absolute;" >		
		<table width="100%"  width="100%" class="weizhi" >
			<tr>
				<td >
					<ul >
						<li>							
							<div id="xymc" onmousedown="initializedragie(this)"
								onMousemove="drag_dropie(this)" title="<bean:message key="lable.xsgzyxpzxy" />（系）">
								<bean:write name="rsMap" property="xymc" />
							</div>
							
							<div style="margin-left:250px" id="nj" onmousedown="initializedragie(this)"
								onMousemove="drag_dropie(this)" title="年级">
								<bean:write name="rsMap" property="nj" />
							</div>
						</li>
						<li>							
						<div style="margin-left:50px" id="xm" onmousedown="initializedragie(this)"
							onMousemove="drag_dropie(this)" title="姓名">
							<bean:write name="rsMap" property="xm" />
						</div>							
						</li>
						<li>			
						<div style="width: 300px;margin-top:14em;margin-left: 16.5em" id="fzN"  onmousedown="initializedragie(this)" 
							onMousemove="drag_dropie(this)"  title="年月日">
							<bean:write name="rsMap" property="fzN" />&nbsp;&nbsp;&nbsp;&nbsp;
							<bean:write name="rsMap" property="fzY" />&nbsp;&nbsp;&nbsp;&nbsp;
							<bean:write name="rsMap" property="fzR" />
						</div>
						</li>						
					</ul>
				</td>
			</tr>
		</table>
		</div>
		<TABLE width="99%" id="rsTable" class="tbstyle">
			<tr>
				<td align="left" class="title">
					证书第三页：校学生素质拓展认证中心认证				
				</td>				
			</tr>
			<tr>
			<td class="clin">
			注：1、在某行记录上，按下鼠标左键的同时，可以拖动该记录并改变其显示位置;
			2、单击右边"<=="或"==>"可进行左右页位置调换;			
			<button class='button2' onclick="printField()" id="flagBt"></button>
			
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			
			</td>
			</tr>
		</TABLE>
	</html:form>
</body>
</html:html>
