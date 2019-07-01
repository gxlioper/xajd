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

	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
	<style media="print">
	.tbstyle{display:none}
	</style>
<style>
.weizhi{
	position:absolute;
	left:2cm;
	top:1.5cm;
	}
.weizhi ul{
	list-style:none;
	margin:0px;
	padding:0px;
	
	}
.weizhi li{
	list-style:none;
	margin:0px 0px;
	padding:0px; 
	}
.weizhi h4{	
	margin:10px 0px;
	font-size:12px;
	font-weight:normal;
	break-all:keep-all
	}	

.htit{ 
	font-size:16px;
	font-style: oblique;
	font-family:黑体;	
	padding: 0px;
	}
.toxm{  
   font-size:11px;
   margin:0px 0px;
}
.toxm2{  
   font-size:13px;
   margin:0px 0px;
}	
div{
   cursor:hand;
   position: absolute; 
   word-break:keep-all
}	
</style>
</head>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/String.js"></script>
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
		<input type="hidden" name="go" id="go" value="go" />
		<input type="hidden" name="fzrq" id="fzrq"
			value="<bean:write name="fzrq" />" />
		<input type="hidden" name="mb" id="mb"
			value="<bean:write name="mb" />" />
		<input type="hidden" name="xh" id="xh"
			value="<bean:write name="xh" />" />
		<button class="button2" style="height:40px" id="search_go" style="display:none"
			onclick="refreshForm('xcxy_sztzprintOne.do?mb='+document.forms[0].mb.value+'&fzrq='+document.forms[0].fzrq.value+'&xh='+document.forms[0].xh.value)">
		</button>
		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				未找到任何记录！
			</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<table width="500" class="weizhi" style="margin-top: 80px ">
				<tr style="cursor:hand;">
					<td align="center">
						<table width="500">
							<ul style="margin-top:17em;margin-left: 16em;margin-right: 15em;">
								<logic:iterate name="rs" id="s">
										<logic:notEmpty name="s" property="kmm">
											<li class="htit">
												<div >
													<bean:write name="s" property="kmm"/>
												</div>
											</li>
										</logic:notEmpty>
										<logic:notEmpty name="s" property="xmmc">
											<li>
												<div class="toxm2">
													<bean:write name="s" property="xmmc"/>
												</div>
											</li>
										</logic:notEmpty>
										<li>
											<div class="toxm">
												<bean:write name="s" property="xn"/>
												<bean:write name="s" property="xqmc"/>
												<bean:write name="s" property="jbmc"/>
											</div>
										</li>
										
<%--									<logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--										<li class="htit">--%>
<%--											<div >--%>
<%--												<bean:write name="v" />--%>
<%--											</div>--%>
<%--										</li>--%>
<%--									</logic:iterate>--%>
<%--									<li>--%>
<%--										<div class="toxm">--%>
<%--											<logic:iterate id="v" name="s" offset="3" length="1">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--										</div>--%>
<%--									</li>--%>
<%--									<li>--%>
<%--										<div class="toxm">--%>
<%--											<logic:iterate id="v" name="s" offset="1" length="2">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--											<logic:iterate id="v" name="s" offset="4" length="1">--%>
<%--												<bean:write name="v" />--%>
<%--											</logic:iterate>--%>
<%--										</div>--%>
<%--									</li>--%>
								</logic:iterate>
							</ul>
						</table>
					</td>
				</tr>
			</table>
			<div style="margin-top:680px;margin-left: 90px" id="zsbh"
				onmousedown="initializedragie(this)" onMousemove="drag_dropie(this)"
				title="证书编号">
				<bean:write name="bhMap" property="zsbh" />
			</div>
			<TABLE width="99%" id="rsTable" class="tbstyle">
				<TR>
					<TD height=3></TD>
				</TR>
				<TR>
					<TD>
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
					</TD>
				</TR>
				<TR>
					<TD height=3></TD>
				</TR>
			</TABLE>
		</logic:notEmpty>
	</html:form>
</body>
</html:html>
