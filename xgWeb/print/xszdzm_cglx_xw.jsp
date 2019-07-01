<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
  	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
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
</style>
	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
	<script language="javascript">
	function Preview(){
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
	
	function paseValue(obj){
		document.getElementById('xwTmp').innerHTML=obj;
	}
	
	function showOperDiv(){			
	  	d_html = document.getElementById('tab_xwmc').innerHTML;
	  	showTempDivForJs('输入学位',d_html,400,100);
	}
	</script>
  </head>
  	
	
<body onload="showOperDiv();">
<div id="showDiv" style="display:none">
<table class="formlist" id="tab_xwmc" onkeyup="">
	<tbody>
		<tr>
		<th>		
			输入授予的学士学位名称
		</th>
		<td>
			<input type="text" id="xwmc" onchange="paseValue(this.value)"/>
			<input type="button" value="确定" onclick="document.getElementById('tempDiv').style.display='none'"/>
		</tr>
	</tbody>
</table>
</div>

<input type="hidden" name="mb" id="mb" value="<bean:write name="mb" />"/>
<html:form action="/certificatePrintAll" method="post">
	<div class="all" id="showContent" align="center">
	<span><font size="6">在读证明</font></span><br/>
	<span><font size="5">（出国专用）</font></span>
	<div class="con"><p>兹有我校学生<bean:write name="rs" property="xm"/>,性别：<bean:write property="xb" name="rs"/>,学号:<bean:write property="xh" name="rs"/>
	,<br/><bean:write property="rxN" name="rxMap"/>年<bean:write property="rxY" name="rxMap"/>月进入我校<bean:write property="xymc" name="rs"/>
	<bean:write property="zymc" name="rs"/>专业学习,目前为我校<bean:write name="nj"/>年级在读学生，正常情况下，
	该生将于<bean:write property="byN" name="byMap"/>年<bean:write property="byY" name="byMap"/>月毕业，
	如达到学士学位授予标准，预计将授予<span id="xwTmp"></span>学士学位。</p></div>
	<div class="con" style="margin-top:3em " align="left">特此证明</div>
	<div class="time" style="margin-top:3em "><bean:write name="xxmc"/><br/>学生处&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<div class="time"><bean:write name="blMap" property="blN"/>年<bean:write name="blMap" property="blY"/>月<bean:write name="blMap" property="blR"/>日</div>
	</div>
</html:form>
</body>
</html>
