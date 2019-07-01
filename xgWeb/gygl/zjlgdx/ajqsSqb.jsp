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
			�㽭����ѧA�������걨��
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
					${rs.qsz }&nbsp;
				</td>
				<td align=center width="15%">
					���ҳ�Ա
				</td>
				<td align="left" colspan="4">
					<logic:present name="rs" property="qscy">
						<logic:iterate id="s" name="rs" property="qscy">
							<bean:write name="s"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:iterate>
					</logic:present>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align=center>
					��<br>��<br>��<br>��
				</td>
				<td align="center" colspan="2" valign="middle">
					�������������������:
				</td>
				<td align="left" colspan="4" valign="middle">
					<logic:present name="rs" property="qscj">
							<logic:iterate id="s" name="rs" property="qscj">
								&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;<bean:write name="s" property="zs"/>&nbsp;���������ɼ�:&nbsp;<bean:write name="s" property="fs"/><br>
							</logic:iterate>
					</logic:present>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" >
					<br>��<br>��<br>��<br>��<br><br>
				</td>
				<td colspan="6" align="left">
					<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sqly"/><br>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					A������<br>����Ҫ��
				</td>
				<td colspan="6" valign="bottom">
					<p align="left">
						<br>
						1��ÿ���Ծ������������롢��࣬ÿ���������ɼ�������85�֣�<br> <br>
						2����������������������ˮ����<br><br>
						3��ÿ��15�ա�30�գ��ڼ���˳�ӣ��������ۺϼ��ɼ�������90�֡�
						<br><br>
					</p>
				</td>
			</tr>

			<tr>
				<td align="center" valign="middle">
					��<br>��<br>��<br>ʩ
				</td>
				<td colspan="6" valign="bottom">

					<p align="left">
						<br>
						1�������ճ�������飬�ճ������ɼ�ȡ��<bean:message key="lable.xsgzyxpzxy" />��߷֣�<br> <br>
						2��������A�����ұ�־�� <br><br>
						3����ίѧ������֯��<bean:message key="lable.xsgzyxpzxy" />����Ԣ����칫�ҡ�¥�㳤�������ڽ��г�飬
						<br><br>&nbsp;&nbsp;&nbsp;��鲻�ϸ����������ȡ��A�����ҡ� <br><br>
						4��ѧԴ���ڹ�˾�޹��������ճ���������У������в�����A������Ҫ�����Ϊ��<br> <br>&nbsp;&nbsp;&nbsp;������ƾ��档<br> <br>
						5������һѧ��ΪA�����ң�����ȫУͨ�����<br>
						<br>
					</p>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					ѧ<br>Ժ<br>��<br>��<br>��<br>��
				</td>
				<td colspan="6" valign="bottom">
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
		<p align="left" style="font-size: 14px">&nbsp;&nbsp&nbsp;&nbspע�������һʽ���ݣ�<bean:message key="lable.xsgzyxpzxy" />���걨���Ҹ�һ�ݡ�</p>		
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
