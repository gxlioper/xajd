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
	left:0cm;
	top:0.2cm;
	}
.weizhi ul{
	list-style:none;
	margin:0px;
	padding:0px;
	}
.weizhi li{
	list-style:none;
	margin:20px 0px;
	padding:0px;
	word-break:keep-al;	
	}
.weizhi h4{
	float:left;
	margin:0px 0px;
	font-size:16px;
	font-family:黑体;
	font-weight:normal;
	break-all:keep-all;
	word-break:keep-all
	}	
.youbianxia{
	margin-top:5px;
	}
.yueri{
	margin-left:5px}
div{
   cursor:hand;
   position:absolute;
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
<%--    var zd = ["xm","xm","mzmc","csNYR","lydq","zzmmmc","xymc","rxNYR","zsbh","fzNYR"];--%>
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
<%--	  }--%>
<%--	}--%>
</script>
  <body>
	<html:form action="/xcxy_sztzprintOne" method="post">
		<input type="hidden" name="fzrq" id="fzrq"
			value="<bean:write name="fzrq" />" />
		<input type="hidden" name="mb" id="mb"
			value="<bean:write name="mb" />" />
<%--		<input type="hidden" name="topstr" id="topstr" value="" />--%>
<%--		<input type="hidden" name="leftstr" id="leftstr" value="" />--%>
		<table width="500" class="weizhi">
			<tr>
				<td align="center">
					<ul style="margin-top:17em;margin-left: 17em;margin-right: 15em;">
						<li>
							<h4>
								<div id="xm" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="姓名">
									<bean:write name="rsMap" property="xm" />
								</div>
							</h4>
						</li>
						<li>
							<h4>
								<div id="xb" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="性别">
									<bean:write name="rsMap" property="xb" />
								</div>
							</h4>
						</li>
						<li>
							<h4>
								<div id="mzmc" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="民族">
									<bean:write name="rsMap" property="mzmc" />
								</div>
							</h4>
						</li>
						<li class="youbianxia">						
							<h4>
								<div id="csNYR" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="出生年月">
									<bean:write name="csMap" property="csNYR" />
								</div>
							</h4>
						</li>						
						<li>
							<h4>
								<div id="lydq" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="籍贯">
									<bean:write name="rsMap" property="lydq" />
								</div>
							</h4>
						</li>
						<li>
							<h4>
								<div id="zzmmmc" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="政治面貌">
									<bean:write name="rsMap" property="zzmmmc" />									
								</div>
							</h4>
						</li>
						<li>
							
							<h4 >
								<div id="zymc" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="院（系）、专业">
									<bean:write name="rsMap" property="xymc" />&nbsp;<bean:write name="rsMap" property="zymc" />
								</div>
							</h4>
						</li>
						<li>
							<h4>
								<div id="rxNYR" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="入校时间">
									<bean:write name="rxMap" property="rxNYR" />
								</div>
							</h4>
						</li>						
						<li>
							<h4>
								<div id="zsbh" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="证书编号">
									<bean:write name="bhMap" property="zsbh" />
								</div>
							</h4>
						</li>
						<li>
							<h4 style="word-break:keep-all">
								<div id="fzNYR" onmousedown="initializedragie(this)"
									onMousemove="drag_dropie(this)" title="颁证时间">
									<bean:write name="fzMap" property="fzNYR" />
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
