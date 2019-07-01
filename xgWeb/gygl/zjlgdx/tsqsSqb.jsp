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
			�㽭����ѧ��ɫ�����걨��
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
					<bean:write name="rs" property="sqsj" />&nbsp;
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
					��ɫ����<br>
					Ҫ��


				</td>
				<td colspan="6" valign="bottom">
					<p align="left">
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;���������ҵĻ����ϣ���ɫ���ҽ����ǵ�����ѧ��������ò���Ļ��������ۺϷ�ӳ����У԰�Ļ��������Ҫ��ɲ��֣��ǹ�Ԣ�Ļ��������ɣ������ڹ��ѧ���ķ�չ��ɲš��ο����Ϊ��<br>
						&nbsp;&nbsp;&nbsp;&nbsp;1���������۵����Ҹ��죬���������塢ë��˼�롢��Сƽ���ۺ͡�������������<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ҫ˼�����ϵͳѧϰ�������о���<br>
						&nbsp;&nbsp;&nbsp;&nbsp;2�����רҵ�ص㣬�Ա�רҵ������רҵ������רҵ���п�ѧ�о���<br>
						&nbsp;&nbsp;&nbsp;&nbsp;3������Ļ�ѧϰ�����й��Ļ��������Ļ��е�һЩ�Ƚ������ݽ���ר��ѧϰ���о���<br>
						&nbsp;&nbsp;&nbsp;&nbsp;4����ϴ�ѧ���ۺ�������������֮�Ժ�ؿ�չĳ���������������ȡ��һ����Ч��<br>
						&nbsp;&nbsp;&nbsp;&nbsp;5����ӳʱ���������ߴ�������������������������Ŀ��<br>

						<br><br>
					</p>
				</td>
			</tr>

			<tr>
				<td align="center">
					<br>��չ��ɫ<br>���ҽ���<br>�ľ��幤<br>������<br>

				</td>
				<td colspan="6" valign="top">			
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="jtgznr" />
					<br><br>
				</td>
			</tr>
			<tr>
				<td align="center">
					<br>
					���ѿ�չ<br>�Ĺ�����<br>��
					���ɸ�ҳ)
					<br>
				</td>
				<td colspan="6" valign="top">
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="ykzgzqk" />
					<br><br>				
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br>
					ָ����ʦ<br>�Ļ�����<br>������ɫ<br>�����֤<br>
					���ɸ�ҳ��
					<br>
				</td>
				<td colspan="6 valign="bottom">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br><br>
					ѧ<br>
					Ժ<br>
					��<br>
					��<br>
					��<br>
					��<br>
					<br><br>

				</td>
				<td colspan="6 valign="bottom">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br><br>
					ί<br>
					ѧ<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					��<br>
					<br><br>
				</td>
				<td colspan="6 valign="bottom">
					&nbsp;
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
