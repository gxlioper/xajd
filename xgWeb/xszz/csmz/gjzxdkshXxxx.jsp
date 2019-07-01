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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var user = document.getElementById('user').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			if((("ƶ����" == xysh)||("������" == xysh)) && (user == "fdy")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if((("ƶ����" == xxsh)||("������" == xxsh)) && (user == "xy")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
		
			refreshForm('/xgxt/csmz_xszz.do?method=gjzxdkshXxxx&actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������ѧ������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="xysh" value="<bean:write name='rs' property='xysh' />" />
			<input type="hidden" name="user" value="<bean:write name="user" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="9" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td scope="row">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="nd" />
					</td>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="sqsj" />
					</td>
				</tr>
				<tr>
					<td align="center" width="16%">
						ѧ��
					</td>
					<td align="left" colspan="2">
						<bean:write name='rs' property="xh" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2" scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xb" />
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="sfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xymc" />
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="rxny" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="csrq" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="szss" />
					</td>
					<td>
						<div align="center">
							���
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="lb" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧ���绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="xsdh" />
					</td>
					<td>
						<div align="center">
							���������QQ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="dzyxhqqh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ������ϸ��ַ
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="jtfk" />
					</td>
					<td width="17%">
						<div align="center">
							�ʱ�
						</div>
					</td>
					<td width="17%">
						<bean:write name='rs' property="jtfkyb" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ����ϸ��ַ
						</div>
					</td>
					<td colspan="3">
						<bean:write name='rs' property="jtxxxzz" />
					</td>
					<td width="17%">
						<div align="center">
							�ʱ�
						</div>
					</td>
					<td width="17%">
						<bean:write name='rs' property="jtxxxzzyb" />
					</td>
				</tr>
				<tr>
					<td scope="row" rowspan="3">
						<div align="center">
							��ĸ��໤��
						</div>
					</td>
					<td width="17%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							��ν
						</div>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<div align="center">
							�̶��绰
						</div>
					</td>
					<td>
						<div align="center">
							�ֻ��绰
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="fmhjhr1_xm" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr1_cw" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr1_sfzh" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr1_gddh" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr1_sjhm" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name='rs' property="fmhjhr2_xm" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr2_cw" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr2_sfzh" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr2_gddh" />
					</td>
					<td>
						<bean:write name='rs' property="fmhjhr2_sjhm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="sqje" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name='rs' property="sqdknx" />
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div align="center">
							����ʵ�ʴ�����(��λ:Ԫ)
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧ��һ��
						</div>
					</td>
					<td>
						<div align="center">
							�ڶ���
						</div>
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name='rs' property="ywsjje1" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name='rs' property="ywsjje2" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name='rs' property="ywsjje3" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name='rs' property="ywsjje4" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name='rs' property="ywsjje5" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name='rs' property="ywsjje6" />
						</div>
					</td>
				</tr>
				<logic:equal name="user" value="xy">
					<tr>
						<td>
							<div align="center">
								����Ա���
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								����Ա�绰
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdydh" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="user" value="xx">
					<tr>
						<td>
							<div align="center">
								����Ա���
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								����Ա�绰
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="fdydh" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />�绰
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="xydh" />
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							��˽��
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="lslxdh" name="lslxdh"
							style="width:100%;heigh:100%" maxlength="15"
							value="<bean:write name='rs' property="lslxdh" />"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
