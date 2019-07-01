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
	<style>
body{
	text-align:center;
	}
.all{
	margin:0 auto;
	width:18cm;
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
.nodisplay{
  display:none;
}	
</style>
<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>	
  </head>
  	<script language="javascript" src="js/function.js"></script>
  	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<object id="eprint" classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
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
	
	function displayContent(){	
	  document.getElementById("showDiv").innerHTML='<table>'+
			'<br/>'+
			'<br/>'+
			'<br/>'+
			'<tr>'+
			'<td>'+	
			'	输入授予的学士学位名称:<br><br></td>'+
			'<td>'+
			'<input type="text" id="xwmc"/><input type="button" value="确定" onclick="paseValue()">' +
			'<br></td>'+
			'</tr></table>';
	}
	
	function paseValue(){	
		    if(document.getElementById('showContent') && document.getElementById('xwmc')){
			     document.getElementById('showContent').innerHTML = document.getElementById('showContent').innerHTML.replace(/\{xw\}/g,document.getElementById('xwmc').value);
			} 
			document.getElementById('showDiv').innerHTML='';
			CloseFloatDiv();
			action();
		}
		
	function action(){
		var patt1=new RegExp("{xw}");
		if(!patt1.test(document.getElementById('showContent').innerHTML)){
			Print(true);
			var cu_R = window.opener.curr_row.rowIndex;
			if(cu_R >= window.opener.$("tabPri").rows.length){
				BatAlert.MyAlert("打印结束!","",function(){
					Close();
				});
				return false;
			}else{
				window.opener.rowOnClick(window.opener.$("tabPri").rows[cu_R]);
				var xh = window.opener.curr_row.cells[0].innerText.trim();
				var zmlx = window.opener.curr_row.cells[7].innerText.trim();
				window.location.href = "certificatePrintAll.do?xh=" + xh + "&mb=" + document.getElementById('mb').value + "&zmlx=" + zmlx;
			}
		}
	}
	</script>

<body onload="">
<div id="showDiv" class="nodisplay noprint"></div>
	<input type="hidden" name="mb" id="mb" value="<bean:write name="mb" />"/>
  <html:form action="/certificatePrintAll" method="post">
<div class="all print" id="showContent">
<h1>在读证明</h1>
<h2>（出国专用）</h2>
<div class="con"><p>兹有我校学生<bean:write name="rs" property="xm"/>,性别：<bean:write property="xb" name="rs"/>,
学号:<bean:write property="xh" name="rs"/>
,<bean:write property="rxn" name="rxMap"/>年<bean:write property="rxy" name="rxMap"/>月进入我校
<bean:write property="xymc" name="rs"/><bean:write property="zymc" name="rs"/>专业学习,
目前为我校<bean:write name="nj"/>年级在读学生，正常情况下，
该生将于<bean:write property="byn" name="byMap"/>年<bean:write property="byy" name="byMap"/>月毕业，
如达到学士学位授予标准，预计将授予{xw}学士学位。</p></div>
<div class="con" style="margin-top:3em " align="left">特此证明</div>
<div class="time" style="margin-top:3em "><bean:write name="xxmc"/><br/>学生处&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div class="time"><bean:write name="blMap" property="blN"/>年<bean:write name="blMap" property="blY"/>月<bean:write name="blMap" property="blR"/>日</div>
</div>
</html:form>
</body>
<script>
window.onload = function(){
    displayContent();
	showOperDiv('showDiv',true);
	var patt1=new RegExp("{xw}");

	if(!patt1.test(document.getElementById('showContent').innerHTML)){
		Print(true);
		var cu_R = window.opener.curr_row.rowIndex;
		if(cu_R >= window.opener.$("tabPri").rows.length){
			BatAlert.MyAlert("打印结束!","",function(){
				Close();
			});
			return false;
		}else{
			window.opener.rowOnClick(window.opener.$("tabPri").rows[cu_R]);
			var xh = window.opener.curr_row.cells[0].innerText.trim();
			var zmlx = window.opener.curr_row.cells[7].innerText.trim();
			window.location.href = "certificatePrintAll.do?xh=" + xh + "&mb=" + document.getElementById('mb').value + "&zmlx=" + zmlx;
		}
	}
}
</script>
</html:html>
