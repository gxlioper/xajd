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
<%--			<input type="hidden" name="pkVal"--%>
<%--				value="<bean:write name="xn||nd||xh||rychdm"/>" />--%>
				<input type="hidden" id="tg" name="tg" value="${rs.yesNo }" />
				<input type="hidden" id="res" name="res" value="${mes }"/>
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
						<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq"/>"/>
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
						<input type="hidden" name="rychmc" id="rychmc" value="<bean:write name="rs" property="rychmc"/>"/>
						<input type="hidden" name="rychdm" id="rychdm" value="<bean:write name="rs" property="rychdm"/>"/>
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
						�����ɼ���
					</td>
					<td align="left">
						${rs.dcj }
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
						�����ɼ���
					</td>
					<td align="left">
						${rs.zcj }
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
						���ܼӷ֣�
					</td>
					<td align="left">
						${rs.jnjf }
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
						�ۺϲ����ɼ���
					</td>
					<td align="left">
						${rs.cpzf }
					</td>					
				</tr>
				<tr>
					<td align="right">
						��ˣ�
					</td>
					<td align="left" colspan="3">
						<html:select property="yesNo" styleId="yesNo" name="rs">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>

			</table>
			<div class="buttontool" align="center">
				
				<button type="button" class="button2"
					onclick="if (document.getElementById('yesNo').value==document.getElementById('tg').value) {alert('��δ���κ��޸ģ�');return;} else refreshForm('/xgxt/pjpy_zjsyzy.do?method=saveCheckCredit');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="showTopWin('/xgxt/stu_info_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" style="width:90px"
					id="buttonQuery">
					�鿴ѧ����Ϣ
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
				<logic:present name="result">
					<logic:equal value="true" name="result">
						<script>
							alert('�����ɹ�!');
							Close();
							window.dialogArguments.document.getElementById('search_go').click();
						</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<script>
							alert("" + document.getElementById("res").value);
							Close();
							window.dialogArguments.document.getElementById('search_go').click();
						</script>
					</logic:equal>					
				</logic:present>
			</div>
		</html:form>
	</body>
</html>
