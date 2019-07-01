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
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ϵ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/auditing_zzsf_gjzxdk_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
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
						<td align="center" width="16%">
							ѧ��
						</td>
						<td align="left" width="34%">
							<bean:write name="rs" property="xh" />
						</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ϵ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny"/>
					</td>
					<td>
						<div align="center">
							��ҵ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="byny"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<td>
						<div align="center">
							���ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zgxl"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jg"/>
					</td>
					<td>
						<div align="center">
							�������ڵ�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="hjszd"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����״��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jkzk"/>
					</td>
					<td>
						<div align="center">
							����״��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="hyzk"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtdh"/>
					</td>
					<td>
						<div align="center">
							�ƶ��绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yddh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							E-Mail��ַ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="e_mail"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ��ַ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��1_����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_xm"/>
					</td>
					<td>
						<div align="center">
							��֤��1_�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_xb"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��1_֤�����ͼ�����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr1_zjlxjhm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��1_�����˹�ϵ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_yjkrgx"/>
					</td>
					<td>
						<div align="center">
							��֤��1_��ϵ�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr1_lxdh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��1_ͨѶ��ַ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr1_txdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��2_����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_xm"/>
					</td>
					<td>
						<div align="center">
							��֤��2_�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_xb"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��2_֤�����ͼ�����
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr2_zjlxjhm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��2_�����˹�ϵ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_yjkrgx"/>
					</td>
					<td>
						<div align="center">
							��֤��2_��ϵ�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jzr2_lxdh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��֤��2_ͨѶ��ַ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jzr2_txdz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����ܽ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jkzje"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dkqx"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							���ʽ
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hkfs"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="dkzl"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ�ѵ���(����)
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xfdj"/>
							Ԫ
					</td>
					<td>
						<div align="center">
							ס�޷ѵ���(����)
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zsfdj"/>
							Ԫ
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����ѵ���
						</div>
					</td>
					<td>
						<bean:write name="rs" property="shfdj"/>
							Ԫ
					</td>
					<td colspan="2">
						<div align="left">
							��&nbsp;&nbsp;&nbsp;&nbsp;
							<bean:write name="rs" property="shffs"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�տ����ʻ����ͼ��ʺ�
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="skrzhlxjzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj"/>
					</td>
					<td>
						<div align="center">
							��˽��
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<logic:equal name="userType" value="xx">
				<tr>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<input type="text" id="dcr" maxlength="50" name="dcr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dcr"/>">
					</td>
					<td>
						<div align="center">
							�����
						</div>
					</td>
					<td>
						<input type="text" id="scr" maxlength="50" name="scr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="scr"/>">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ǩ����
						</div>
					</td>
					<td>
						<input type="text" id="qpr" maxlength="50" name="qpr"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qpr"/>">
					</td>
					<td>
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				</logic:equal>
				<tr>
					<td>
						<div align="center">
							ϵ������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧУ������
						</div>
					</td>
					<td colspan="3">
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
