<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
	<body>
		<html:form action="/XsgyglDispatch.do" method="post">
			<div align="center">
				<bean:write name="xxmc"/>��Ԣά����ǲ��
			</div>
			<div align="left">
				<u>&nbsp;&nbsp;<bean:write name="rs" property="xn"/>&nbsp;&nbsp;</u>ѧ��
				<u>&nbsp;&nbsp;<bean:write name="rs" property="xq"/>&nbsp;&nbsp;</u>ѧ��
			</div>
			<table width="100%" border="0" id="theTable"  class="printstyle">
				<tr>
					<td width="30%" align="right">
						���᣺
					</td>
					<td>
						<bean:write name="rs" property="ldmc"/><bean:write name="rs" property="qsh"/>
					</td>
					<td width="30%" align="right">
						�����ˣ�
					</td>
					<td>
						<bean:write name="rs" property="bxrxm"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						����ʱ�䣺
					</td>
					<td>
						<bean:write name="rs" property="bxsj"/>
					</td>
					<td align="right">
						�����˵绰��
					</td>
					<td>
						<bean:write name="rs" property="lxfs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						Ԥά��ʱ�䣺
					</td>
					<td>
						<bean:write name="rs" property="wxsj"/>
					</td>
					<td align="right">
						Ԥ��ǲά���ˣ�
					</td>
					<td>
						<bean:write name="rs" property="wxry"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						ʵ��ά��ʱ�䣺
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						ά�����ݣ�
					</td>
					<td colspan="3">
						<bean:write name="rs" property="wxnr"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						������ǩ����
					</td>
					<td>
						&nbsp;
					</td>
					<td align="right">
						ά����ǩ����
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="4" valign="bottom" align="right" height="50"> 
						���Ÿ���
					</td>
				</tr>
			</table>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>
	</body>
</html>
