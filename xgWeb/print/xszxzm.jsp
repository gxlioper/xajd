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
    <title>学生在校证明打印页</title>
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
	width:17cm;
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
	margin:8px 20px}
.tag{
	text-align:left;
	font-size:22px;
	text-indent:2em;
	font-weight:normal;
	letter-spacing:4px;
	margin:8px 20px}
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
  <html:form action="/certificatePrintAll" method="post">
<div class="all">
<h1>证      明</h1>
<div class="con"><p>兹证明<U><bean:write  name="rs" property="xm"/></U>同学系我校全日制学生，该生于<U><bean:write  property="rxN" name="rxMap"/></U>年<U><bean:write property="rxY" name="rxMap"/></U>月入学，为<U><bean:write name="nj"/><bean:write property="zymc" name="rs"/></U>年级（专业）学生，（学制<U><bean:write property="xz" name="rxMap"/></U>年）</p></div>
<div class="con" style="margin-top:3em " align="left">特此证明！</div>
<div class="time" style="margin-top:3em "></div>
<div class="time" style="margin-top:3em "><bean:write name="xxmc"/><br/>学工处&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div class="time"><bean:write name="blMap" property="blN"/>年<bean:write name="blMap" property="blY"/>月<bean:write name="blMap" property="blR"/>日</div>
</div>
</html:form>
<script>
window.onload = function(){
	Print(true);
	var cu_R = window.opener.curr_row.rowIndex;
	if(cu_R >= window.opener.$("tabPri").rows.length){
		BatAlert.MyAlert("打印结束!","",function(){
			Close();
		});
		return false;
	}else{
		window.opener.rowOnClick(window.opener.$("tabPri").rows[cu_R]);
		var xh = window.opener.curr_row.cells[1].innerText.trim();
		window.location.href ="/xgxt/zzlgdx_rcsw.do?method=xszxzmPrint&xh="+xh;
	}
}
</script>
</body>
</html:html>
