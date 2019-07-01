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
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xysh = document.getElementById('xysh').value;
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var shmodel = document.getElementById('shmodel').value;
			
			if(shmodel != "" && shmodel=="depModel"){//���������
				if(document.getElementById('zt').value == '1'){
					alert("������ύ���������޸���˽��!");
					return false;
				}
			}else{
				if(("δ���" != xxsh ) && (userType == "xy")){
					alert("ѧУ����ˣ��������޸���˽��!");
		          	return false;
				}
				if(("δ���" != xysh ) && (userType == "fdy")){
					alert("<bean:message key="lable.xsgzyxpzxy" />����ˣ��������޸���˽��!");
		          	return false;
				}
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("����Ա���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/n05_xszz.do?method=lsbz2shSave');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/n05_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ��ʱ������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<input type="hidden" id="shmodel" name="shmodel" value="${shmodel}" />
			<input type="hidden" id="zt" name="zt" value="${rs.tjzt}" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" width="16%">
							ѧ��
					</td>
					<td width="34%">
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
						<input type="hidden" id="xq" name="xq"
							value="<bean:write name="rs" property="xq" />" />
						<bean:write name='rs' property="xh" />
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
							�꼶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							רҵ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xn"/>
					</td>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xqmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��ͥ��ַ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtzz"/>
					</td>
					<td>
						<div align="center">
							������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqje"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly"/>
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
					<logic:notEqual name="userType" value="xx">
						<td>
							<div align="center">
								��׼���
							</div>
						</td>
						<td>
							<logic:present name="shmodel">
							    <input type="text" id="pzje" name="pzje" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="pzje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'')" 
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</logic:present>
							<logic:notPresent name="shmodel">
							    <bean:write name="rs" property="pzje"/>
							</logic:notPresent>
							
						</td>
					</logic:notEqual>
					<logic:equal name="userType" value="xx">
						<td>
							<div align="center">
								��׼���
							</div>
						</td>
						<td>
							<input type="text" id="pzje" name="pzje" maxlength="5"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="pzje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'')" 
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</logic:equal>
				</tr>
				<logic:equal name="shjb" value="3">
					<logic:equal name="userType" value="fdy">
						<tr>
							<td>
								<div align="center">
									����Ա���
								</div>
							</td>
							<td>
								<html:select name="rs" property="fdysh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xysh" name="xysh"
									value="<bean:write name="rs" property="xysh"/>">
								<input type="hidden" id="xxsh" name="xxsh"
									value="<bean:write name="rs" property="xxsh"/>">
							</td>
							<td>
								<div align="center">
									���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdyshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="fdyshyj" rows='3' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
								<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
								<input type="hidden" id="xyshyj" name="xyshyj"
									value="<bean:write name="rs" property="xyshyj"/>">
								<input type="hidden" id="xxshyj" name="xxshyj"
									value="<bean:write name="rs" property="xxshyj"/>">
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xy">
						<tr>
							<td>
								<div align="center">
									����Ա���
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdysh"/>
							</td>
							<td>
								<div align="center">
									����Ա���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdyshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td>
								<html:select name="rs" property="xysh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xxsh" name="xxsh"
									value="<bean:write name="rs" property="xxsh"/>">
							</td>
							<td>
								<div align="center">
									���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
								<input type="hidden" id="xyshyj" name="xyshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xxshyj" name="xxshyj"
									value="<bean:write name="rs" property="xxshyj"/>">
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<tr>
							<td>
								<div align="center">
									����Ա���
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdysh"/>
							</td>
							<td>
								<div align="center">
									����Ա���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="fdyshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xysh"/>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xyshyj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td>
								<html:select name="rs" property="xxsh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xysh" name="xysh"
									value="<bean:write name="rs" property="xysh"/>">
							</td>
							<td>
								<div align="center">
									���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xxshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧУ������
								</div>
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
								<input type="hidden" id="xxshyj" name="xxshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xyshyj" name="xyshyj"
									value="<bean:write name="rs" property="xyshyj"/>">
							</td>
						</tr>
					</logic:equal>
				</logic:equal>
				<logic:notEqual name="shjb" value="3">
					<logic:equal name="userType" value="xy">
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td>
								<html:select name="rs" property="xysh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xxsh" name="xxsh"
									value="<bean:write name="rs" property="xxsh"/>">
							</td>
							<td>
								<div align="center">
									���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
								<input type="hidden" id="xyshyj" name="xyshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xxshyj" name="xxshyj"
									value="<bean:write name="rs" property="xxshyj"/>">
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="userType" value="xx">
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xysh"/>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xyshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xyshyj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td>
								<html:select name="rs" property="xxsh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
								<input type="hidden" id="xysh" name="xysh"
									value="<bean:write name="rs" property="xysh"/>">
							</td>
							<td>
								<div align="center">
									���ʱ��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xxshsj"/>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧУ������
								</div>
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
								<input type="hidden" id="xxshyj" name="xxshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="xyshyj" name="xyshyj"
									value="<bean:write name="rs" property="xyshyj"/>">
							</td>
						</tr>
					</logic:equal>
				</logic:notEqual>
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
