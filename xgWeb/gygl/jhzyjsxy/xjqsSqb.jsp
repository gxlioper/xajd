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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
</head>

<body>

	<div align="center">
		<h3>��ְҵ����<bean:message key="lable.xsgzyxpzxy" />�Ǽ������걨��<bean:write name="rs" property="xn"/>ѧ��<bean:write name="rs" property="month"/>�£�</h3>
		<table border=1 cellspacing=0 cellpadding=0 align=center class="tbstyle">
			<tr>
				<td width="15%"  align=center>
					¥ ��					
				</td>
				<td width="35%" align="center">
					<bean:write name="rs" property="ldmc"/>&nbsp;
				</td>
				<td width="15%" align=center>
					�� ��			
				</td>
				<td width="35%"  align="center">
					<bean:write name="rs" property="qsh"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td align=center>
					�� ��				
				</td>
				<td align="center">
					<bean:write name="rs" property="ssbj"/>&nbsp;
				</td>
				<td align=center>
					�� ��		
				</td>
				<td align="center">
					<bean:write name="rs" property="ssrs"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td align=center>
					�����������				
				</td>
				<td align="center">
					&nbsp;<bean:write name="wsJc" property="anum"/>&nbsp;
				</td>
				<td align=center>
					����������		
				</td>
				<td align="center">
					&nbsp;<bean:write name="wsJc" property="dbnum"/>&nbsp;
				</td>
			</tr>
			<tr>
				<td align=center>
					�걨�ȼ�				
				</td>
				<td colspan="3" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox name="rs" property="dj" value="���Ǽ�"></html:checkbox>���Ǽ�
					&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox name="rs" property="dj" value="���Ǽ�"></html:checkbox>���Ǽ�
					&nbsp;&nbsp;&nbsp;&nbsp;<html:checkbox name="rs" property="dj" value="���Ǽ�"></html:checkbox>���Ǽ�
				</td>
			</tr>
			<tr>
				<td align="center"  valign="middle" >
					<br>
					����
					<br>
					����
					<br>
					����
					<br>
					���
					<br>
					����
					<br>
				</td>
				<td colspan=5  valign="bottom">
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<bean:write  name="rs" property="qsjsqk"/>
					</p>
					<br>
					<br>
					<p align="right">
						���ҳ�ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp; ��&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		
			<tr>
				<td align="center" valign="middle">
					<br>
					¥������Ա
					<br>
					���
				</td>
				<td colspan=5   valign="bottom" >
					<br>
					<br>
					<br>
					<br>
					<br>
					<p align="right">		
						����Աǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp; ��&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br>
					<bean:message key="lable.xsgzyxpzxy" />ѧ����
					<br>
					���
					<br>
				</td>
				<td colspan=5   valign="bottom" >
					<br>
					<br>
					<br>
					<br>
					<br>
					<p align="right">
					
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp; ��&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br>
					Уѧ������
					<br>
					���������
					<br>
					���
					<br>
				</td>
				<td colspan=5   valign="bottom" >
					<br>
					<br>
					<br>
					<br>
					<br>
					<p align="right">
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp; ��&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>		
	</div>
					<br>
				<div class="buttontool noprint" align="center">
					<input type="button" class="button2" value="ҳ������"
						onclick="WebBrowser.ExecWB(8,1);">
					<input type="button" class="button2" value="��ӡԤ��"
						onclick="WebBrowser.ExecWB(7,1)">
					<input type="button" class="button2" value="ֱ�Ӵ�ӡ"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
	
</body>

</html:html>
