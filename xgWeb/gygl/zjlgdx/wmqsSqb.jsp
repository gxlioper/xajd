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
	<title><bean:message key="lable.title" /></title>
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
		.tbstyle {
	border-collapse: collapse;
}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
	font-size: 14px;
}
	</style>
</head>

<body>

	<div align="center">
		<h3>
			�㽭����ѧ�������������
		</h3>
		<table border=1 cellspacing=0 cellpadding=0 align=center class="theTable">
			<tr height="30">
				<td width="10%" align=center>
					ѧ Ժ
				</td>
				<td width="30%" align="center" colspan="2">
					<bean:write name="rs" property="xymc" />
					&nbsp;
				</td>
				<td width="10%" align=center>
					¥�㡢���Һ�
				</td>
				<td width="20%" align="center">
					<bean:write name="rs" property="cs" />-<bean:write name="rs" property="qsh" />
					&nbsp;
				</td>
				<td width="10%" align=center>
					��ϵ�绰
				</td>
				<td width="20%" align="center">
					<bean:write name="rs" property="lxdh" />
					&nbsp;
				</td>
			</tr>
			<tr height="30">
				<td align=center>
					���ҳ�
				</td>
				<td align="center" width="15%">
					${rs.qsz }
				</td>
				<td align=center width="15%">
					���ҳ�Ա
				</td>
				<td align="left" colspan="2">
					<logic:present name="rs" property="qscy">
						<logic:iterate id="s" name="rs" property="qscy">
							<bean:write name="s"/>&nbsp;&nbsp;
						</logic:iterate>
					</logic:present>
					&nbsp;
				</td>
				<td align=center>
					�걨����
				</td>
				<td align="center" width="15%">
					<bean:write name="rs" property="sqsj" />
				</td>
			</tr>
			<tr>
				<td align=center>
					�걨����
				</td>
				<td align="left" colspan="6" valign="middle">
					&nbsp;
					<logic:present name="rs" property="qstj">
								&nbsp;${rs.qstj.xn}ѧ��&nbsp;&nbsp;${rs.qstj.xq}&nbsp;&nbsp;ѧ�ڵ�<bean:write name="rs" property="zs" />�ܱ���׼ΪA������<br>
					</logic:present>
					
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					��������<br>
					����Ҫ��

				</td>
				<td colspan="6" valign="bottom">
					<p align="left">
						<br>
						
						1����������ִ�45�����ϣ����¿��ˣ���<br>
						2���ճ������淶���˴�45�����ϣ����¿��ˣ���<br>
						   ������<br>
							��1������ط�����ʵ���ţ� ��2���ڷ�ѧϰ���̿����У�<br>
							��3��������������ò���ˣ� ��4�����ܶ����ڼ��Լ��<br>
							��5���������ࡢ��ֹ���壻 ��6����ʦ�������ϰ��ף�<br>
							��7���Ž�ͬѧ���Ȱ����壻 ��8������Ϊ�֡�����������<br>
							��9���������ά������ ��10��������У��������ᡣ
						<br><br>
					</p>
				</td>
			</tr>
			<tr>
				<td align="center" >
					<br><br>
					����<br>
					����<br>
					����<br>
					<br><br><br>
				</td>
				<td colspan="6" align="left" valign="top">
					<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sqly"/><br>
				</td>
			</tr>

			<tr>
				<td align="center" valign="middle">
					ѧ  Ժ<br>
					������

				</td>
				<td colspan="6" valign="bottom">			
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<p align="right">			
						����ˣ�ǩ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp; ��&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td align="center" >
					�������<br>
					�ɼ�
				</td>
				<td colspan="3">
					<br>
					<br>
					<br>
					<br>
					<p align="right">
						������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						��&nbsp;&nbsp; ��&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;
					</p>				
				</td>
				<td align="center" >
					�����淶<br>
					���˳ɼ�
				</td>
				<td colspan="2">
					<br>
					<br>
					<br>
					<p align="right">
						������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						��&nbsp;&nbsp; ��&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;
					</p>				
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					��Ԣ����<br>
					�����<br>���
				</td>
				<td colspan="6 valign="bottom">
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<p align="right">			
						����ˣ�ǩ������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp; ��&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;
					</p>
					<br>
				</td>
			</tr>
		</table>
		<p align="left" style="font-size: 14px">&nbsp;&nbsp&nbsp;&nbspע�������һʽ���ݣ���Ԣ����졢<bean:message key="lable.xsgzyxpzxy" />���걨���Ҹ�ִһ�ݡ�</p>		
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
