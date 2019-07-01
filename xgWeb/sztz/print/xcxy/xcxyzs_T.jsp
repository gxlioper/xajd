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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
.weizhi{
	position:absolute;
	left:1cm;
	top:0.2cm;
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
	font-size:20px;
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
</style>
  </head>
  	<script language="javascript" src="js/function.js"></script>
  	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/getPagePara.js"></script>
	<script language="javascript" src="/xgxt/dwr/engine.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
<%--<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>	--%>
<script language="javascript">

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
document.onmouseup=new Function("dragapproved=false")
}

<%--function Preview() --%>
<%--{--%>
<%--	if (document.all.eprint.defaultPrinterName.length==0){--%>
<%--		alert("请先安装打印机，再执行此功能！");--%>
<%--		return;--%>
<%--	}--%>
<%--  document.all.eprint.InitPrint();--%>
<%--  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch--%>
<%--  document.all.eprint.marginTop=92;--%>
<%--  document.all.eprint.marginLeft=20;--%>
<%--  document.all.eprint.marginRight=20;--%>
<%--  document.all.eprint.marginBottom=10;--%>
<%--  document.all.eprint.header = "";--%>
<%--  document.all.eprint.footer = "";--%>
<%--  document.all.eprint.Preview();//打印预览--%>
<%--}--%>
<%----%>
<%--function Print() {--%>
<%--	if (document.all.eprint.defaultPrinterName.length==0){--%>
<%--		alert("请先安装打印机，再执行此功能！");--%>
<%--		return;--%>
<%--	}--%>
<%--  document.all.eprint.InitPrint();--%>
<%--  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch--%>
<%--  document.all.eprint.header = "";--%>
<%--  document.all.eprint.footer = "";--%>
<%--  document.all.eprint.Print(true);//直接打印--%>
<%--}--%>
<%----%>
<%--    var top= new Array();--%>
<%--	var left = new Array();--%>
<%--    var x,y;--%>
<%--    var zd = ["xm","zymc","nj","fzN","fzY","fzR","zsbh"];--%>
<%--	--%>
<%--	var dragapproved=false	--%>
<%--	window.onload = function(){--%>
<%--		var topstr = new Array();--%>
<%--		var leftstr = new Array();--%>
<%--		if(document.getElementById("topstr").value != "" && document.getElementById("leftstr").value != ""){--%>
<%--			topstr = (document.getElementById("topstr").value).split("#");--%>
<%--			leftstr = (document.getElementById("leftstr").value).split("#");			--%>
<%--			for(var i=0;i<topstr.length;i++){--%>
<%--				var obj = document.getElementById(zd[i]);--%>
<%--				if(obj == null) break;--%>
<%--				obj.style.top = topstr[i];--%>
<%--				obj.style.left = leftstr[i];--%>
<%--			}--%>
<%--		}		--%>
<%--	Print(true);--%>
<%--	setTimeout("",1000);--%>
<%--	var cu_R = window.opener.curr_row.rowIndex;--%>
<%--	if(cu_R >= window.opener.$("tabPri").rows.length){--%>
<%--		BatAlert.MyAlert("打印结束!","",function(){--%>
<%--			Close();--%>
<%--		});--%>
<%--		return false;--%>
<%--	}else{--%>
<%--		window.opener.rowOnClick(window.opener.$("tabPri").rows[cu_R]);//单击下一行--%>
<%--		var xh = window.opener.curr_row.cells[1].innerText.trim();--%>
<%--		window.location.href = "xcxy_sztzprintOne.do?xh="+xh+"&fzrq="+document.getElementById('fzrq').value+"&mb="+document.getElementById('mb').value;		--%>
<%--	}--%>
<%--	}--%>
</script>
  <body>
	<input type="hidden" name="fzrq" id="fzrq"
		value="<bean:write name="fzrq" />" />
	<input type="hidden" name="mb" id="mb" value="<bean:write name="mb" />" />
	<html:form action="/xcxy_sztzprintOne" method="post">
		<input type="hidden" name="topstr" id="topstr" value="" />
		<input type="hidden" name="leftstr" id="leftstr" value="" />
		<table width="500" class="weizhi">
			<tr>
				<td align="center">
					<ul style="margin-top:15em;">
						<li style="padding-left: 21em">
							<h4>
								<div id="xymc" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="<bean:message key="lable.xsgzyxpzxy" />（系）">
									<bean:write name="rsMap" property="xymc" />
								</div>
							</h4>
						</li>
						<li>
							<h4 style="margin-left:80px">
								<div id="nj" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="年级">
									<bean:write name="rsMap" property="nj" />
								</div>
							</h4>
							<h4 style="margin-left:150px">
								<div id="xm" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="姓名">
									<bean:write name="rsMap" property="xm" />
								</div>
							</h4>
						<li>
						<li style="padding-top:9.5em;padding-left: 15em" title="年月日">
							<h4 style="margin-right:50px">
								<div id="fzN" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)">
									<bean:write name="fzMap" property="fzN" />
								</div>
							</h4>
							<h4 style="margin-left:30px;margin-right:30px">
								<div id="fzY" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)">
									<bean:write name="fzMap" property="fzY" />
								</div>
							</h4>
							
							<h4 >
								<div id="fzR" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)">
									<bean:write name="fzMap" property="fzR" />
								</div>
							</h4>
						</li>
						<li style="margin-top:7em;margin-left:3em">
							<h4>
								<div id="zsbh" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="证书编号">
									<bean:write name="bhMap" property="zsbh" />
								</div>
							</h4>
						</li>
					</ul>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
