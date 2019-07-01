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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - �����ƺ���� - ���������ƺ����
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="pk"/>" />
				<input type="hidden" name="tg" id="tg" value="${tgres }"/>
				<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							���������ƺ����
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left" id="selxh">
						<bean:write name="rs" property="xh"/>
						<input type="hidden" name="xh" id="xh" value="<bean:write name="rs" property="xh"/>"/>
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
						<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd"/>"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
						<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn"/>"/>
					</td>
 				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						�����ƺţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="rychmc"/>
					</td>
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						˼��������ʷ�����
					</td>
					<td align="left">
						<bean:write name="rs" property="dcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						ѧϰƽ���ɼ���
					</td>
					<td align="left">
						<bean:write name="rs" property="xxpjcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
						ѧϰƽ���ɼ�������
					</td>
					<td align="left">
						<bean:write name="rs" property="xxpjcjpm"/>
					</td>
				</tr>

				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
						ѧϰƽ���ɼ�����������
					</td>
					<td align="left">
						<bean:write name="rs" property="xxpjcjpmbl"/>
					</td>
				</tr>				
				<tr style="height:22px">
					<td align="right">
						�������ʷ�����
					</td>
					<td align="left">
						<bean:write name="rs" property="stszzf"/>
					</td>
					<td align="right">
						��չ���ʷ�����
					</td>
					<td align="left">
						<bean:write name="rs" property="sztzzf"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�۲��ܷ֣�
					</td>
					<td align="left">
						<bean:write name="rs" property="stszzf"/>
					</td>
					<td align="right">
						�۲��ܷ�������
					</td>
					<td align="left">
						<bean:write name="rs" property="sztzzf"/>
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						�۲��ܷ�����������
					</td>
					<td align="left">
						<bean:write name="rs" property="zhszcpcjpmbl"/>
					</td>
					<td align="right">
						������ͷ�����
					</td>
					<td align="left">
						<bean:write name="rs" property="dkzdfs"/>
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						������������
					</td>
					<td align="left">
						<bean:write name="rs" property="wygjqk"/>
					</td>
					<td align="right">
						�������ɣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						�Ƿ������룺
					</td>
					<td align="left">
						<bean:write name="rs" property="sfdlsq"/>
					</td>
					<td align="right">
						�����������ɣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="dlsqly"/>
					</td>
				</tr>
				<tr style="height: 22px">
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select property="yesNo" styleId="yesNo" name="rs">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td></td>
					<td></td>
				</tr>
				<logic:equal value="xy" scope="request" name="userType">
				<tr>
					<td align="right">
						����Ա�����
					</td>
					<td align="left" colspan="3">
						<textarea name="fdyyj" style="width:100%" rows="3"><bean:write name="rs" property="fdyyj" /></textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��������
					</td>
					<td align="left" colspan="3">
						<textarea name="xyyj" style="width:100%" rows="3"><bean:write name="rs" property="xyshyj"/></textarea>
					</td>
				</tr>
				</logic:equal>
				
				<logic:equal value="xx" scope="request" name="userType">
				<tr>
					<td align="right">
						����Ա�����
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="fdyyj"/>
					</td>
				</tr>				
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��������
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						ѧУ��������
					</td>
					<td align="left" colspan="3">
						<textarea name="xxyj" style="width:100%" rows="3"><bean:write name="rs" property="xxshyj"/></textarea>
					</td>
				</tr>				
				</logic:equal>
			</table>				
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('pjpy_whlgdx.do?method=checkRychSave');"
					style="width:90px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:90px"
					id="buttonClose">
					�� ��
				</button>
				<logic:present name="showhzy">
				&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.open('/xgxt/dxjxjsp.do?method=dxjxjsp&pk=<bean:write name="xn||nd||xh||jxjdm"/>')" style="width:90px"
					id="buttonQuery">
					��ӡ����
				</button>
				</logic:present>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert(""+document.getElementById('tg').value);
			</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
