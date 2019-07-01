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
    <title>出国在读证明打印页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
body{
	text-align:center;
	}
.all{
	margin:0 auto;
	width:16cm;
	margin-top:5cm
	}
h1,h2{
	text-align:center;
	width:100%;
	}
.con{
	margin:0 auto;
	width:100%;
	text-align:left;
	text-indent:2em;
	font-size:22px;
	line-height:1.5em;
	letter-spacing:4px;
	}
h3{
	text-align:left;
	margin-top:1.5em;
	font-size:22px;
	text-indent:2em;
	font-weight:normal;
	letter-spacing:4px;
	}
h4{
	text-align:left;
	margin-top:1.5em;
	font-size:22px;
	font-weight:normal;
	letter-spacing:4px;}
.time{
	text-align:right;
	font-size:22px;
	text-indent:2em;
	font-weight:normal;
	letter-spacing:4px;
	margin:8px}
</style>
  </head>
  	<script language="javascript" src="js/function.js"></script>
  	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
<script language="javascript">
function Preview() 
{
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  document.all.eprint.marginTop=92;
  document.all.eprint.marginLeft=20;
  document.all.eprint.marginRight=20;
  document.all.eprint.marginBottom=10;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  document.all.eprint.Preview();//打印预览
}

function Print() {
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("请先安装打印机，再执行此功能！");
		return;
	}
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm是default, 2 inch
  document.all.eprint.marginTop=92;
  document.all.eprint.marginLeft=10;
  document.all.eprint.marginRight=10;
  document.all.eprint.marginBottom=10;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  document.all.eprint.Print(true);//直接打印
}

</script>
<body>
	<input type="hidden" name="rxrq" id="rxrq" value="<bean:write name="rxrq" />"/>
	<input type="hidden" name="blrq" id="blrq" value="<bean:write name="blrq" />"/>
	<input type="hidden" name="nj" id="nj" value="<bean:write name="nj" />"/>
	<input type="hidden" name="jbrxm" id="jbrxm" value="<bean:write name="jbrxm" />"/>
	<input type="hidden" name="sfcg" id="sfcg" value="<bean:write name="sfcg" />"/>
  <html:form action="/certificatePrintAll" method="post">
<div class="all">
<h1>在读证明</h1>
<h2>（出国专用）</h2>
<div class="con">学生<bean:write name="rs" property="xm"/>，<bean:write name="rs" property="xb"/>，学号：<bean:write name="rs" property="xh"/>，<bean:write name="rxMap" property="rxN"/>年<bean:write name="rxMap" property="rxY"/>月进入我<bean:write name="xxmc"/><bean:write name="rs" property="xymc"/><bean:write name="rs" property="zymc"/>专业学习，目前为我校<bean:write name="nj"/>在读学生</div>
<h3>特此证明</h3>
<h4>经手人：<bean:write name="jbrxm"/></h4>

<div class="time" style="margin-top:3em "><bean:write name="xxmc"/>学生处</div>
<div class="time"><bean:write name="blMap" property="blN"/>年<bean:write name="blMap" property="blY"/>月<bean:write name="blMap" property="blR"/>日</div>
</div>
</html:form>
</body>
<script>
window.onload = function(){
	Print(true);
	var cu_R = window.opener.curr_row.rowIndex;
	if(cu_R >= window.opener.$("rsTable").rows.length){
		BatAlert.MyAlert("打印结束!","",function(){
			Close();
		});
		return false;
	}else{
		window.opener.rowOnClick(window.opener.$("rsTable").rows[cu_R]);
		var xh = window.opener.curr_row.cells[0].innerText.trim();
		window.location.href = "certificatePrintAll.do?xh="+xh+"&rxrq="+document.getElementById('rxrq').value+"&blrq="+document.getElementById('blrq').value+"&nj="+document.getElementById('nj').value+"&jbrxm="+document.getElementById("jbrxm").value+"&sfcg="+document.getElementById('sfcg').value;
	}
}
</script>
</html:html>
