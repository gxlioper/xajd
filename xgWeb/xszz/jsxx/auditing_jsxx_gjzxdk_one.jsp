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
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}else if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("ѧУ���������ܴ���100���ַ�");
	          		 return false;
	       		 }
			 }
			 refreshForm('/xgxt/auditing_jsxx_gjzxdk_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������ѧ���� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���֤��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							���
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="nd" />
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ����ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xslxdh" />
					</td>
					<td>
						<div align="center">
							��ѧ�ɼ�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rxcj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sxqpm" />
					</td>
					<td>
						<div align="center">
							�γ�ƽ���ɼ�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="pjcj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtzz" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="9%">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							������λ��ְ��
						</div>
					</td>
					<td width="17%" align="center">
						<div align="center">
							������
						</div>
					</td>
				</tr>
				<tr>
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td colspan="4">
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
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td colspan="4">
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
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td colspan="4">
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
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td colspan="4">
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
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td colspan="4">
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
							�ճ�����
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="rcbx" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�������
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jcqk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ��������״��
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtjjknzk" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ȫѧ��ѧ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xnxf" />
					</td>
					<td>
						<div align="center">
							��ͥ�ṩ���ò�����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtfybzs" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ѧ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hjxf" />
					</td>
					<td>
						<div align="center">
							��ѧ���������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="bxqdks" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�ƻ����ʼʱ��(��)
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jhhkkssj" />
					</td>
					<td>
						<div align="center">
							�ƻ������ֹʱ��(��)
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jhhkjssj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ע
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
						</div>
					</td>
					<td colspan="3">
					</td>
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
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="left">
							ѧУ������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
