<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
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
		if (document.all.eprint.defaultPrinterName.length==0){
			alert("���Ȱ�װ��ӡ������ִ�д˹��ܣ�");
			return;
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
	}
	
	function paseValue(obj){
		document.getElementById('xwTmp').innerHTML=obj;
	}
	
	function showOperDiv(){			
	  	d_html = document.getElementById('tab_xwmc').innerHTML;
	  	showTempDivForJs('����ѧλ',d_html,400,100);
	}
	</script>
  </head>
  	
	
<body onload="showOperDiv();">
<div id="showDiv" style="display:none">
<table class="formlist" id="tab_xwmc" onkeyup="">
	<tbody>
		<tr>
		<th>		
			���������ѧʿѧλ����
		</th>
		<td>
			<input type="text" id="xwmc" onchange="paseValue(this.value)"/>
			<input type="button" value="ȷ��" onclick="document.getElementById('tempDiv').style.display='none'"/>
		</tr>
	</tbody>
</table>
</div>

<input type="hidden" name="mb" id="mb" value="<bean:write name="mb" />"/>
<html:form action="/certificatePrintAll" method="post">
	<div class="all" id="showContent" align="center">
	<span><font size="6">�ڶ�֤��</font></span><br/>
	<span><font size="5">������ר�ã�</font></span>
	<div class="con"><p>������Уѧ��<bean:write name="rs" property="xm"/>,�Ա�<bean:write property="xb" name="rs"/>,ѧ��:<bean:write property="xh" name="rs"/>
	,<br/><bean:write property="rxN" name="rxMap"/>��<bean:write property="rxY" name="rxMap"/>�½�����У<bean:write property="xymc" name="rs"/>
	<bean:write property="zymc" name="rs"/>רҵѧϰ,ĿǰΪ��У<bean:write name="nj"/>�꼶�ڶ�ѧ������������£�
	��������<bean:write property="byN" name="byMap"/>��<bean:write property="byY" name="byMap"/>�±�ҵ��
	��ﵽѧʿѧλ�����׼��Ԥ�ƽ�����<span id="xwTmp"></span>ѧʿѧλ��</p></div>
	<div class="con" style="margin-top:3em " align="left">�ش�֤��</div>
	<div class="time" style="margin-top:3em "><bean:write name="xxmc"/><br/>ѧ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	<div class="time"><bean:write name="blMap" property="blN"/>��<bean:write name="blMap" property="blY"/>��<bean:write name="blMap" property="blR"/>��</div>
	</div>
</html:form>
</body>
</html>
