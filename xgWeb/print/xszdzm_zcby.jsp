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
    <title>�����ڶ�֤����ӡҳ</title>
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
function Preview(){
  try{
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return;
	}
  }catch(e){
  	alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
	return;
  }
  document.all.eprint.InitPrint();
  document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch
  document.all.eprint.marginTop=92;
  document.all.eprint.marginLeft=20;
  document.all.eprint.marginRight=20;
  document.all.eprint.marginBottom=10;
  document.all.eprint.header = "";
  document.all.eprint.footer = "";
  document.all.eprint.Preview();//��ӡԤ��
}

function Print() {
  try{
	if (document.all.eprint.defaultPrinterName.length==0){
		alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
		return false;
	} 
   document.all.eprint.InitPrint();
   document.all.eprint.SetMarginMeasure(1);//1mm��default, 2 inch
   document.all.eprint.marginTop=92;
   document.all.eprint.marginLeft=10;
   document.all.eprint.marginRight=10;
   document.all.eprint.marginBottom=10;
   document.all.eprint.header = "";
   document.all.eprint.footer = "";
   document.all.eprint.Print(true);//ֱ�Ӵ�ӡ
   }catch(e){
  	alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
	return false;
  }
  return true;
}

</script>
<body>
	<input type="hidden" name="mb" id="mb" value="<bean:write name="mb" />"/>
  <html:form action="/certificatePrintAll" method="post">
<div class="all">
<h1>�ڶ�֤��</h1>
<h2>��������ѧ��</h2>
<div class="con"><p>������Уѧ��<bean:write name="rs" property="xm"/>,�Ա�<bean:write property="xb" name="rs"/>,ѧ�ţ�<bean:write property="xh" name="rs"/>,<br/><bean:write property="rxN" name="rxMap"/>��<bean:write property="rxY" name="rxMap"/>�½�����У<bean:write property="xymc" name="rs"/><bean:write property="zymc" name="rs"/>רҵѧϰ,ĿǰΪ��У<bean:write name="nj"/>�꼶�ڶ�ѧ����</p></div>
<div class="con" style="margin-top:3em " align="left">�ش�֤��</div>
<div class="time" style="margin-top:3em "></div>
<div class="time" style="margin-top:3em "><bean:write name="xxmc"/><br/>ѧ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div class="time"><bean:write name="blMap" property="blN"/>��<bean:write name="blMap" property="blY"/>��<bean:write name="blMap" property="blR"/>��</div>
</div>
</html:form>
</body>
<script>
window.onload = function(){
	if(Print()){
		var cu_R = window.opener.curr_row.rowIndex;
		if(cu_R >= window.opener.$("tabPri").rows.length){
			BatAlert.MyAlert("��ӡ����!","",function(){
				Close();
			});
			return false;
		}else{
			window.opener.rowOnClick(window.opener.$("tabPri").rows[parseFloat(cu_R)]);
			var xh = window.opener.curr_row.cells[0].innerText.trim();
			var zmlx = window.opener.curr_row.cells[7].innerText.trim();
			window.location.href = "certificatePrintAll.do?xh=" + xh + "&mb=" + document.getElementById('mb').value + "&zmlx=" + zmlx;
		}
	}
}
</script>
</html:html>
