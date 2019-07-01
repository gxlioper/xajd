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
		<title><bean:message key="lable.title" />
		</title>
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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
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
					��ǰ����λ�ã��ڹ���ѧ - ��� - ѧ��������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
			<input type="hidden" name="gwdm"
				value="<bean:write name="rs" property="gwdm"/>" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
							����ѧ���������
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="2" width="16%">
							ѧ��
						</td>
						<td align="left" colspan="3" width="34%">
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>
					<td align="center" width="16%">
						��λ����
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="gwdm" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="center" colspan="2">
						����
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="center">
						�Ա�
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						������ò
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zzmm" />
					</td>
					<td align="center">
						�����
					</td>
					<td colspan="2">
						<bean:write name="rs" property="ssbh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />����
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="center">
						רҵ����
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						�༶����
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="center">
						����ʱ��
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						����ְ��
					</td>
					<td colspan="3">
						<bean:write name="rs" property="btzw" />
					</td>
					<td align="center">
						�кμ����س�
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhtc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						����
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jg" />
					</td>
					<td align="center">
						ѧ���绰
					</td>

					<td colspan="2">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="center" colspan="2">
						���������޲�����γ�
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bkhywbjgkc" />
					</td>
					<td align="center">
						�Ƿ�Ը��ͳһ����
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfyytytp" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧϰ���
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="xxqk" />
					</td>
				</tr>
				<tr>
					<td rowspan="6" scope="row" width="4%">
						<div align="center">
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ҫ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="10%">
						<div align="center">
							��ν
						</div>
					</td>
					<td width="10%">
						<div align="center">
							����
						</div>
					</td>
					<td width="14%">
						<div align="center">
							����״��
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							����(ѧϰ)��λ��ְ��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							������(Ԫ)
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_cw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdwjzw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�¶�
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfgr" />
					</td>
					<td>
						<div align="center">
							��ʿ��Ů
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sflszn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�����뻧
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfwsrh" />
					</td>
					<td>
						<div align="center">
							�ز���
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfzbh" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�ͱ���
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfdbh" />
					</td>
					<td>
						<div align="center">
							��ĸ˫�¸�
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sffmsxg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ũ��
						</div>
					</td>
					<td colspan="3" align="center">
						<bean:write name="rs" property="sfcnh" />
					</td>
					<td>
						<div align="center">
							������(��ͥ���벻����֧����ѧ��������)
						</div>
					</td>
					<td colspan="2" align="center">
						<bean:write name="rs" property="sfdsr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�������Ѿ������
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtjjknqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ÿ��Ӧ���ɸ��ַ���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mnyjngzfy" />
					</td>
					<td>
						<div align="center">
							��ͥÿ���ṩ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtmntg" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�ϼ�ÿ����ȱ����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hjmnsqfy" />
					</td>
					<td>
						<div align="center">
							Ƿ��ѧ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="qjxfs" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ѵ������༰���
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="yhdkzljje" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							Ŀǰ�ڹ���ѧ���
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="mqqgzxqk" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						��������
					</td>
					<td colspan="6">
						<bean:write name="rs" property="xssq" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						��ע
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						�Ƿ�������
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="sfpks" />
					</td>
					<td align="center">
						���
					</td>
					<td align="left" colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="refreshForm('/xgxt/postStuChkOne.do?act=save');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ���");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="full" name="result">
			<script>
				alert("����������");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("����������������");
			</script>
		</logic:equal>
	</body>
</html>
