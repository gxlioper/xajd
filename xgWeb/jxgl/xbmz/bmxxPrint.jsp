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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title>���鱨���ǼǴ�ӡҳ
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
			<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
				<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body onload="close();document.getElementById('print').click()">
		<html:form action="/jxglrwbmxxsh" method="post">
		<input type="hidden" name="printName" value="���������" id="printName"/>
			<fieldset>
				<legend>
					���������
				</legend>
				<table width="100%" class="tbstyle" id="rwbm">
					<thead>
						<tr height="25">
							<td colspan="7" align="right">
								ѧ�ţ�
								<bean:write name="rs" property="xh" />
							</td>
						</tr>
					</thead>
					<tr height="30">
						<td width="10%" align="center">
							������
						</td>
						<td width="10%">
							<bean:write name="rs" property="xm" />
						</td>
						<td width="5%" align="right">
							�Ա�
						</td>
						<td width="10%">
							<bean:write name="rs" property="xb" />
						</td>
						<td width="5%" align="right">
							���壺
						</td>
						<td width="10%">
							<bean:write name="rs" property="mz" />
						</td>
						<td rowspan="4" width="5%" align="center" title="һ����">
							<img href="#" />
						</td>
					</tr>
					<tr height="30">
						<td align="center">
							���᣺
						</td>
						<td>
							<bean:write name="rs" property="jg" />
						</td>
						<td align="right">
							�������£�
						</td>
						<td>
							<bean:write name="rs" property="csny" />
						</td>
						<td align="right">
							�����ţ���
						</td>
						<td>
							<bean:write name="rs" property="zzmm" />

						</td>
					</tr>
					<tr height="30">
						<td align="center">
							��Уʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="rxsj" />
						</td>
						<td align="right">
							רҵ��ϵ����
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							��ͥ����
						</td>
						<td>
							<bean:write name="rs" property="jtcs" />
						</td>
					</tr>
					<tr height="30">
						<td align="center">
							�к��س���
						</td>
						<td>
							<bean:write name="rs" property="tc" />
						</td>
						<td align="right">
							ѧ��֤�ţ�
						</td>
						<td>
							<bean:write name="rs" property="xszh" />
						</td>
						<td align="right">
							���֤�ţ�
						</td>
						<td>
							<bean:write name="rs" property="sfzh" />
						</td>
					</tr>
					<tr height="30">
						<td align="center">
							��ͥסַ��
						</td>
						<td colspan="3">
							<bean:write name="rs" property="jtzz" />
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td>
							<bean:write name="rs" property="lxdh" />
						</td>
						<td align="center">
						</td>
					</tr>
					<tr height="80">
						<td align="center">
							�Ƿ��ܹ�
							<br>
							����ѵ��
						</td>
						<td align="center">
							<bean:write name="rs" property="sfjsxl" />
						</td>
						<td align="center">
							��ѵ����
							<br>
							��ʱ��
						</td>
						<td colspan="2">
							<bean:write name="rs" property="sxnr" />
						</td>
						<td align="right">
							���������
						</td>
						<td>
							<bean:write name="rs" property="jcqk" />
						</td>
					</tr>
					<tr height="80">
						<td align="center">
							��У���֣�
						</td>
						<td colspan="6">
							<bean:write name="rs" property="zxbx" />
						</td>
					</tr>
					<tr height="80">
						<td align="center">
							��ͥ��Ҫ
							<br>
							��Ա
						</td>
						<td colspan="6">
							<bean:write name="rs" property="jtzycy" />
						</td>
					</tr>
					<tr height="80">
						<td align="center">
							���˼���
						</td>
						<td colspan="6">
							<bean:write name="rs" property="grjl" />
						</td>
					</tr>
					<tr height="30">
						<td rowspan="2" align="center">
							���
							<br>
							�����ף�
						</td>
						<td rowspan="2">
							<bean:write name="rs" property="sg" />
						</td>
						<td rowspan="2" align="center">
							����
							<br>
							��ǧ�ˣ�
						</td>
						<td rowspan="2">
							<bean:write name="rs" property="tz" />
						</td>
						<td rowspan="2" align="center">
							����
						</td>
						<td align="right">
							���ۣ�
						</td>
						<td>
							<bean:write name="rs" property="slright" />
						</td>
					</tr>
					<tr height="30">
						<td align="right">
							���ۣ�
						</td>
						<td>
							<bean:write name="rs" property="slleft" />
						</td>
					</tr>
					<tr height="30">
						<td align="center">
							��ͥ������
							<br>
							��ʷ���
						</td>
						<td colspan="6">
							<bean:write name="rs" property="jtjgrbs" />
						</td>
					</tr>
					<tr>
						<td align="center">
							�Ǽ�ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="djsj" />
						</td>
						<td align="right">
							��˽����
						</td>
						<td>
							<bean:write name="rs" property="xxsh" />
						</td>
						<td align="center">
							����ˣ�
							<bean:write name="rs" property="xxshr" />
						</td>
						<td align="right">
							���ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="shsj" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��ͨ��ԭ��
						</td>
						<td colspan="6">
							<bean:write name="rs" property="btgyy" />
						</td>
					</tr>
					<tr>
						<td align="center">
							�Ǽ�ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="djsj" />
						</td>
						<td align="right">
							��˽����
						</td>
						<td>
							<bean:write name="rs"  property="zbbgssh" />
						</td>
						<td align="center">
							����ˣ�
							<bean:write name="rs" property="zbbgsshr" />
						</td>
						<td align="right">
							���ʱ�䣺
						</td>
						<td>
							<bean:write name="rs" property="zbbgsshsj" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��ͨ��ԭ��
						</td>
						<td colspan="6">
							<bean:write name="rs" 	 property="zbbgsshbtgyy" />
						</td>
					</tr>
					<input type="hidden" class="button2" name="button2" id="print"
						style="width:100px;display:none;" value="�� ӡ "
						onclick="expTab('rwbm',document.getElementById('printName').value,'')"/>

				</table>
			</fieldset>
		</html:form>
	</body>
</html>
