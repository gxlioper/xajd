<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<jsp:directive.page import="xgxt.action.Base"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="../../skin1/style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<style media='print'>
		.noPrin{
		display:none;}
	</style>
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>	
	<body>
		<p align="center"><h3 align="center">������һְҵ����<bean:message key="lable.xsgzyxpzxy" /> </h3></p>
		<p align="center"><h3 align="center"><%= Base.currXn%>ѧ��ѧ���ڹ���ѧ�ϸ������� </h3></p>
		<p align="center"><strong>&nbsp; </strong></p>
		<div align="center">
		  <table width="85%" cellpadding="0" cellspacing="0" class="tbstyle">  
			  <tr>
			    <td width="103" rowspan="5" class="xl31">��<br>��<br>��<br>��<br>��<br>�� </td>
			    <td colspan="2" class="xl22">�� �� </td>
			    <td colspan="2" class="xl22"></td>
			    <td colspan="2" class="xl22">�� �� </td>
			    <td colspan="3" class="xl28"></td>
			  </tr>
			  <tr>
			    <td colspan="2" class="xl22">��Դ�� </td>
			    <td colspan="2" class="xl22"></td>
			    <td colspan="2" class="xl22">��ϵ��ʽ </td>
			    <td colspan="3" class="xl28"></td>
			  </tr>
			  <tr>
			    <td width="128" class="xl23">�꼶��רҵ���༶ </td>
			    <td width="96" class="xl23"></td>
			    <td colspan="2" class="xl22"></td>
			    <td colspan="2" class="xl22">�������� </td>
			    <td colspan="3" class="xl28"></td>
			  </tr>
			  <tr>
			    <td colspan="2" class="xl26">���ܹ��������� <br>
			      (д��������ʱ��) </td>
			    <td colspan="7" class="xl22"></td>
			  </tr>
			  <tr>
			    <td colspan="9" class="xl36">�������ɣ� <br>
			        <br>
			        <br>
			        <br>
			     <p align="right"> ����ǩ����                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       ��          &nbsp;&nbsp;&nbsp;&nbsp;   ��              &nbsp;&nbsp;&nbsp;&nbsp;  ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</p></td>
			  </tr>
			  <tr>
			    <td rowspan="4" class="xl31">��<br>λ<br>��<br>�� </td>
			    <td height="14" class="xl23">�������� </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">ͼ��ݹ���Ա </td>
			    <td width="83" class="xl23"></td>
			    <td colspan="2" class="xl22">��ѧ¥����Ա </td>
			    <td colspan="2" class="xl28"></td>
			  </tr>
			  <tr>
			    <td class="xl22">����Ա </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">��������Ա </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">�����ݱ���Ա </td>
			    <td colspan="2" class="xl28"></td>
			  </tr>
			  <tr>
			    <td class="xl22">��дԱ </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl22">ʵ��¥����Ա </td>
			    <td class="xl23"></td>
			    <td colspan="2" class="xl28">ʵ���ұ���Ա </td>
			    <td colspan="2" class="xl28"></td>
			  </tr>
			  <tr>
			    <td class="xl22">ʳ�� </td>
			    <td class="xl23"></td>
			    <td width="117" class="xl23">�Ƿ�Ը��ȥ�������ĸ�λ </td>
			    <td class="xl23"></td>
			    <td class="xl25"></td>
			    <td colspan="4" class="xl25"></td>
			  </tr>
			  <tr>
			    <td rowspan="20" class="xl31">��<br>��<br>�� </td>
			    <td class="xl27"></td>
			    <td class="xl23">һ </td>
			    <td class="xl23">�� </td>
			    <td class="xl23">�� </td>
			    <td class="xl23">�� </td>
			    <td width="83" class="xl23">�� </td>
			    <td width="79" class="xl23">�� </td>
			    <td width="80" class="xl23">�� </td>
			    <td width="90" class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">1 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">2 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">3 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">4 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">5 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">6 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">7 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">8 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">9 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">10 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">11 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">12 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">13 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">14 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">15 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">16 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">17 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td class="xl22">18 </td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			    <td class="xl23"></td>
			  </tr>
			  <tr>
			    <td colspan="9" class="xl44">���пεĿո���򡰡̡� </td>
			  </tr>
			  <tr>
			    <td class="xl24" align="center">Ժ<br>ϵ<br>��<br>�� </td>
			    <td colspan="3">
					<br>
					<br>
			    <p align="right"> ǩ��: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;</p> </td>
			    <td class="xl26" width="112" align="center">
			    	 ѧ��<br>����<br>����<br>��� </td>
			    <td colspan="5">
			        <br>
			        <br>
			    <p align="right"> ǩ��: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;</p> </td>
			  </tr>
			</table>
		</div>
		<div align=center class='noPrin'>
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</body>
</html>
