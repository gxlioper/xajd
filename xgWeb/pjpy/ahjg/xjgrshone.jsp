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
		<html:form action="/pjpyahjgwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - �����ƺ���� - ���������ƺ����
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue"/>" />
			<input type="hidden" name="failInfo" id="failInfo" value="${failInfo }"/>
			<input type="hidden" name="oldyesNo" id="oldyesNo" value="<bean:write name="ag" property="yesNo"/>"/>
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
					<td align="left"  id="selxh">
						<bean:write name="rs" property="xh"/>
						<input type="hidden" id="oldxh" name="oldxh" value="<bean:write name="rs" property="xh"/>"/>
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
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
						<input type="hidden" id="rychdm" name="rychdm" value="<bean:write name="rs" property="rychdm"/>"/>
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
						�³ɼ���
					</td>
					<td align="left">
						<bean:write name='rs' property="dcj"/>
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
						�ǳɼ���
					</td>
					<td align="left">
						<bean:write name="rs" property="zcj"/>
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
						��ɼ���
					</td>
					<td align="left">
						<bean:write name="rs" property="tcj"/>
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
					�γ�ƽ����:
					</td>
					<td align="left">
						<bean:write name="rs" property="pjf"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						���ڰ༶:
					</td>
					<td align="left">
						<bean:write name="rs" property="bjrychmc"/>
					</td>
					<td align="right">
						�༶����:
					</td>
					<td align="left">
					<bean:write name="rs" property="mc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��������:
					</td>
					<td align="left">
						<bean:write name="rs" property="wmss"/>
					</td>
					<td align="right">
						���������:
					</td>
					<td align="left">
						<bean:write name="rs" property="jsjej"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����ְ��:
					</td>
					<td align="left">
						<bean:write name="rs" property="drzw"/>
					</td>
					<td align="right">
						Ӣ���ļ�:
					</td>
					<td align="left">
						<bean:write name="rs" property="cet4"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						ʮ�Ѵ�ѧ���÷�:
					</td>
					<td align="left">
						<bean:write name="rs" property="df"/>
					</td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select name="ag" property="yesNo" styleId="yesNo" style="width:120px">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						ʮ�Ѵ�ѧ���÷�ȫУ������
					</td>
					<td align="left">
						<bean:write name="rs" property="pm"/>
					</td>
					<td align="right">&nbsp;</td>
					<td align="left">&nbsp;</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="if (document.getElementById('oldyesNo').value==document.getElementById('yesNo').value) {alert('��δ���κ��޸ģ�');return;} else refreshForm('xjgrshbyone.do');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="showTopWin('/xgxt/stu_info_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" style="width:90px"
					id="buttonQuery">
					�鿴ѧ����Ϣ
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ�ܣ�'+document.getElementById('failInfo').value);
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
