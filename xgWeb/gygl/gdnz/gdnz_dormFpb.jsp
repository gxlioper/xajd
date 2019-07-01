<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title>gdnz_dormFpb.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>
		.noPrin{display:none}
	</style>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
	<html:form action="/gdnz_dormFpb">
		<p align="center">
			<font style="font-weight:bold" size="2">�㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />Ů����������</font>
		</p>
		<table class="tbstyle" align="center" width="90%">
			<tr>
				<td colspan="2" height="40">
					<div align="center">
						��Ŀ
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xymc"/></div>
					</td>
				</logic:iterate>
				<td width="66">
					<div align="center">
						�ϼ�
					</div>
				</td>
				<td width="127">
					<div align="center">
						��ע
					</div>
				</td>
			</tr>
			<tr>
				<td width="77" rowspan="3">
					<div align="center">
						ѧ������
					</div>
				</td>
				<td width="200" height="36">
					<bean:write name="rs" property="newstu"/>��������
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="newsturs"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>��ѧ������<font color="red">����<bean:write name="rs" property="newstu"/>�죩</font>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="oldsturs"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					9�·�ȫϵ�����ϼ�
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="zsturs"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td rowspan="4">
					<div align="center">
						ѧ��ס�޷ֲ����
					</div>
				</td>
				<td height="36">
					ȫϵӦס6�˼�����
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xyyzrs6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					ȫϵ��ס10�˼�����
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xyyzrs10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>����ס6�˼�����
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="yzrs6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>����ס10�˼�����
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="yzrs10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td rowspan="4">
					<div align="center">
						Ӧ���䴲λ(����)��
					</div>
				</td>
				<td height="36">
					Ӧ����6�˼䴲λ(����)��
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ybccws6"/>(<bean:write name="fpxx" property="ybcss6"/>)��</div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					Ӧ����10�˼䴲λ(����)��
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ybccws10"/>(<bean:write name="fpxx" property="ybcss10"/>)��</div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>�����˼��մ�λ��
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="hkcws6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>��ʮ�˼��մ�λ��
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="hkcws10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<div align="center">
						���䴲λ(����)ʹ�÷ֲ�
					</div>
				</td>
				<td height="36">
					ӦԤ������6�˼䴲λ(����)��
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ylcws6"/>��</div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="36">
					<bean:write name="rs" property="oldstu"/>��ѧ���ɵ�����6�˼䴲λ(����)
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="ktzcw6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<p align="center">
			<font style="font-weight:bold" size="2">��ϵӦ�������ᰲ��</font>
		</p>
		<table class="tbstyle" align="center" width="90%">
			<tr>
				<td width="89" height="57">
					<div align="center">
						ϵ������
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="xymc"/></div>
					</td>
				</logic:iterate>
				<td width="110">
					<div align="center">
						��ע
					</div>
				</td>
			</tr>
			<tr>
				<td height="57">
					<div align="center">
						6�˼�
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="rs6"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="57">
					<div align="center">
						10�˼�
					</div>
				</td>
				<logic:iterate id="fpxx" name="fpList">
					<td width="66">
						<div align="center"><bean:write name="fpxx" property="rs10"/></div>
					</td>
				</logic:iterate>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
