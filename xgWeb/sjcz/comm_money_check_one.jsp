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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript" src="js/dwjlFunction.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã����⽻�� - ��� - ���⽻����ѧ����� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="xn||nd||xh||jlxmdm"/>" />
			<input type="hidden" name="xh" id="xh" value="<bean:write name="XH" />" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							�������⽻����ѧ�����
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name="XH" />
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="ND" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="XM" />
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="XN" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="XB" />
					</td>
					<td align="right">
						������Ŀ��
					</td>
					<td align="left">
						<bean:write name="DWJLXMMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<td align="right">
						������ʽ��
					</td>
					<td align="left">
						<bean:write name='DWJLFSMC' />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<td align="right">
						�������
					</td>
					<td align="left">
						<bean:write name="DWJLLBMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<td align="right">
						����ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="SQSJ" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="BJMC" />
					</td>
					<td align="right">
						�����
					</td>
					<td align="left">
						${SQJE }
					</td>
				</tr>
				<tr style="height:22px">
					<td></td>
					<td></td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left" colspan="">
						<html:select property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<%--�㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" />--%>
				<logic:equal value="13022" name="xxdm">
					<tr>
					<td colspan="4" align="center" bgcolor="#CCCCCC" style="color:blue;cursor:hand"
					onclick="document.all.child.style.display=(document.all.child.style.display =='none')?'':'none';getDwjljxjInfo();">������ʷ��Ϣ</td>
					</tr>
					<tr id="child" style="display='none'">
					<td colspan="4">
						<TABLE>
						<THEAD>
						<TR>
								<TD>���</TD>
								<TD>ѧ��</TD>
								<TD>ѧ��</TD>
								<TD>������Ŀ</TD>
								<TD>������ʽ</TD>
								<TD>�������</TD>
								<TD>������׼���</TD>
						</TR>
						</THEAD>
						<tbody id="dwjljxjInfo">
						</tbody>
						</TABLE>
					</td>
					</tr>
				</logic:equal>
			</table>
			<logic:equal value="yes" name="writeAble">
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/commMonChkOne.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			</logic:equal>
		</html:form>
	</body>
</html>
