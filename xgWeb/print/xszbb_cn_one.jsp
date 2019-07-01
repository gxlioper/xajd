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
    <title>学生证补办申请表打印页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="学生证补办申请表打印页">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
body{
	text-align:center;
	}
.all{
	margin:0 auto;
	width:20cm;
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
<h1>回  执</h1>
<div class="con"><p>今收到<u><bean:write name="xmMap" property="xm"/></u>同学补办学生证申请书，已缴费金额为<u>（伍元整、柒元整、拾贰元整）</u>，请于七日后携带此《回执》来领取新补学生证。</p></div>
<div class="time" style="margin-top:3em ">校学生管理中心</div>
<div class="time"><bean:write name="timeMap" property="nowYear"/>年<bean:write name="timeMap" property="nowMonth"/>月<bean:write name="timeMap" property="nowDay"/>日</div>
</div>
</html:form>
</body>
<script>

</script>
</html:html>
