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
			refreshForm('/xgxt/n05_xszz.do?method=gjzxj3shSave');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function changeZxj(){
			var zxjdm = document.getElementById('zxjdm').value;
			
			if (zxjdm == null || zxjdm == "") {
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
			} else {
				var sT = document.getElementById('zxjdm')[document.getElementById('zxjdm').selectedIndex].text;
				var sList = sT.split("||");
				
				document.getElementById('zxjdj').value = sList[0];
				document.getElementById('zxjje').value = sList[1];
			}
		}
		function changesh(){
			var userType = document.getElementById('userType').value;
			var zxjdmT = document.getElementById('zxjdmT').value;
			var shjg = "";
			if ("xx" == userType) {
				shjg = document.getElementById('xxsh').value;
			}
			if ("xy" == userType) {
				shjg = document.getElementById('xysh').value;
			}
			if ("fdy" == userType) {
				shjg = document.getElementById('fdysh').value;
			}
			if (shjg == "ͨ��") {
				document.getElementById("zxjdm").disabled=false;
				if (zxjdmT != null && zxjdmT != ""){
					document.getElementById("zxjdm").value=zxjdmT;
				} else {
					document.getElementById("zxjdm").value="";
				}
				changeZxj();
			} else {
				document.getElementById("zxjdm").value = "";
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
				document.getElementById("zxjdm").disabled=true;
			}
		}
		</script>
		<html:form action="/n05_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ������ѧ����� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<input type="hidden" id="shjb" name="shjb" value="<bean:write name="shjb" />" />
			<input type="hidden" id="zxjdmT" name="zxjdmT" value="<bean:write name='rs' property="zxjdm" />" />
			<input type="hidden" id="shmodel" name="shmodel" value="${shmodel}" />
			<input type="hidden" id="zt" name="zt" value="${rs.tjzt}" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="6" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
							ѧ��
					</td>
					<td colspan="2">
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
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
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="csrq"/>
					</td>
					<td>
						<div align="center">
							��ѧʱ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�꼶
						</div>
					</td>
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							רҵ����
						</div>
					</td>
					<td colspan="2">
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
					<td colspan="2">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="lxdh"/>
					</td>
					<td>
						<div align="center">
							ѧ��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xsyhm"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xshm"/>
					</td>
					<td>
						<div align="center">
							ѧ���ʺ�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xszh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xn"/>
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
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jthk"/>
					</td>
					<td>
						<div align="center">
							��ͥ�˿�����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtzrks"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�˾�������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rjysr"/>
					</td>
					<td>
						<div align="center">
							��ͥ��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzsr"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������Դ
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="srly"/>
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
				<!-- �Ͼ���ʦ -->
				<logic:equal name="xxdm" value="8001">
					<tr>
						<td colspan="2">
							<div align="center">
								�Ƿ�ͱ�
							</div>
						</td>
						<td colspan="2">
							<bean:write name="rs" property="sfdb"/>
						</td>
						<td>
							
						</td>
						<td>
							
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥסַ
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="jtzz"/>
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��<br />ͥ<br />��<br />Ա<br />��<br />��
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							����
						</div>
					</td>
					<td width="17%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							������λ
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy1_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy1_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy2_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy2_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy3_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy3_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy4_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy4_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_dw"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:write name="rs" property="jtcy5_xm"/>
					</td>
					<td>
						&nbsp;<bean:write name="rs" property="jtcy5_nl"/>&nbsp;
					</td>
					<td>
						<bean:write name="rs" property="jtcy5_gx"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy5_dw"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ��
						</div>
					</td>
					<td colspan="2">
						<html:select name="rs" property="zxjdm" onchange="changeZxj();" styleId="zxjdm">
							<html:option value=""></html:option>
							<html:options collection="gjzxjList" property="dm"
										labelProperty="mc" />
						</html:select>
					</td>
					<td>
						<div align="center">
							��ѧ��ȼ�
						</div>
					</td>
					<td>
						<input type="text" id="zxjdj" name="zxjdj" readonly="readonly"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="zxjdj"/>">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td colspan="2">
						<input type="text" id="zxjje" name="zxjje" readonly="readonly"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="zxjje"/>">
					</td>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj"/>
					</td>
				</tr>
				<logic:equal name="shjb" value="3">
					<logic:equal name="userType" value="fdy">
						<tr>
							<td colspan="2">
								<div align="center">
									����Ա���
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="fdysh" onchange="changesh()" styleId="fdysh">
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
							<td colspan="2">
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="4">
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
							<td colspan="2">
								<div align="center">
									����Ա���
								</div>
							</td>
							<td colspan="2">
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
							<td colspan="2">
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xysh" onchange="changesh()" styleId="xysh">
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
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="4">
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
							<td colspan="2">
								<div align="center">
									����Ա���
								</div>
							</td>
							<td colspan="2">
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
							<td colspan="2">
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="2">
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
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="xyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xxsh" onchange="changesh()" styleId="xxsh">
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
							<td colspan="2">
								<div align="center">
									ѧУ������
								</div>
							</td>
							<td colspan="4">
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
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xysh" onchange="changesh()" styleId="xysh">
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
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="4">
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
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="2">
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
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
								</div>
							</td>
							<td colspan="4">
								<bean:write name="rs" property="xyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td colspan="2">
								<html:select name="rs" property="xxsh" onchange="changesh()" styleId="xxsh">
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
							<td colspan="2">
								<div align="center">
									ѧУ������
								</div>
							</td>
							<td colspan="4">
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
	<script language="javascript">
		var userType = document.getElementById('userType').value;
		var shjb = document.getElementById('shjb').value;
		var zxjdmT = document.getElementById('zxjdmT').value;
		var shjg = "";
		
		if ("xx" == userType) {
			shjg = document.getElementById('xxsh').value;
		}
		if ("xy" == userType) {
			shjg = document.getElementById('xysh').value;
		}
		if ("fdy" == userType) {
			shjg = document.getElementById('fdysh').value;
		}
		
		if (shjb == "3") {
			if (shjg == "ͨ��") {
				document.getElementById("zxjdm").disabled=false;
				if (zxjdmT != null && zxjdmT != ""){
					document.getElementById("zxjdm").value=zxjdmT;
				} else {
					document.getElementById("zxjdm").value="";
				}
				changeZxj();
			} else if ("fdy" == userType){
				document.getElementById("zxjdm").value = "";
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
				document.getElementById("zxjdm").disabled=true;
			} else {
				document.getElementById("zxjdm").disabled=true;
			}
		} else {
			if (shjg == "ͨ��") {
				document.getElementById("zxjdm").disabled=false;
				if (zxjdmT != null && zxjdmT != ""){
					document.getElementById("zxjdm").value=zxjdmT;
				} else {
					document.getElementById("zxjdm").value="";
				}
				changeZxj();
			} else if ("xy" == userType){
				document.getElementById("zxjdm").value = "";
				document.getElementById('zxjdj').value = "";
				document.getElementById('zxjje').value = "";
				document.getElementById("zxjdm").disabled=true;
			} else {
				document.getElementById("zxjdm").disabled=true;
			}
		}
	</script>
</html>
