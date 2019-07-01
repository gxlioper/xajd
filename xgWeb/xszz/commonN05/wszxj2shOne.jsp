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
			var xyshyj = document.getElementById('qtsm').value;
			
			if(("δ���" != xxsh ) && (userType == "xy")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
			if(("δ���" != xysh ) && (userType == "fdy")){
				alert("<bean:message key="lable.xsgzyxpzxy" />����ˣ��������޸���˽��!");
	          	return false;
			}
			if(fdyshyj != null){
	         	if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("����Ա���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("�������˵�����ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/n05_xszz.do?method=wszxj2shSave');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function chengJe(){
			var xmje = document.getElementById('xmje').value;
			
			document.getElementById('pzje').value = xmje;
		}
		</script>
		<html:form action="/n05_xszz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - <bean:write name="xmmc"/>��� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" />" />
			<input type="hidden" id="xmdm" name="xmdm"
				value="<bean:write name="xmdm" />">
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="7" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
							ѧ��
					</td>
					<td colspan="3">
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
					<td colspan="3">
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
							�꼶
						</div>
					</td>
					<td colspan="3">
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
					<td colspan="3">
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
							��ͥ��ַ
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="jtdz"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yzbm"/>
					</td>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ���ú�������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bxnhdhzzz"/>
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bkks"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ѧ��༶�ۺϲ�������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sxnbjzhcppm"/>
					</td>
					<td>
						<div align="center">
							��У�Ƿ��ܹ�����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zxfscf"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							��ͥ�˿�
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtrk"/>
					</td>
					<td>
						<div align="center">
							��ͥ�˾�������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrjysr"/>
					</td>
				</tr>
				<tr>
					<td rowspan="6" width="4%">
						<div align="center">
							��<br />ͥ<br />��<br />Ҫ<br />��<br />Ա
						</div>
					</td>
					<td width="12%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="14%">
						<div align="center">
							�뱾�˹�ϵ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							������(Ԫ)
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							ְҵ
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
					<td>
						<bean:write name="rs" property="jtcy1_sr"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_zy"/>
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
					<td>
						<bean:write name="rs" property="jtcy2_sr"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_zy"/>
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
					<td>
						<bean:write name="rs" property="jtcy3_sr"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_zy"/>
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
					<td>
						<bean:write name="rs" property="jtcy4_sr"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_zy"/>
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
					<td>
						<bean:write name="rs" property="jtcy5_sr"/>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy5_zy"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ԭ��
						</div>
					</td>
					<td colspan="5">
						<bean:write name="rs" property="sqyy"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqje"/>
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
				<tr>
					<logic:notEqual name="userType" value="xx">
						<td colspan="2">
							<div align="center">
								��׼���
							</div>
						</td>
						<td colspan="3">
							<bean:write name="rs" property="pzje"/>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</logic:notEqual>
					<logic:equal name="userType" value="xx">
						<td colspan="2">
							<div align="center">
								��׼���
							</div>
						</td>
						<td colspan="5">
							<html:select property="xmje" onchange="chengJe()">
								<html:option value=""></html:option>
								<html:options collection="wszxjJeList" property="xmje"
											labelProperty="xmje" />
							</html:select>
							&nbsp;&nbsp;
							<input type="text" id="pzje" name="pzje" readonly="readonly"
								style="width:80px;heigh:100%"
								value="<bean:write name="rs" property="pzje"/>">
						</td>
					</logic:equal>
					
				</tr>
				<logic:equal name="shjb" value="3">
					<logic:equal name="userType" value="fdy">
						<tr>
							<td colspan="2">
								<div align="center">
									����Ա���
								</div>
							</td>
							<td colspan="3">
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
							<td colspan="2">
								<div align="center">
									����Ա������
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="fdyshyj" rows='3' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
								<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
								<input type="hidden" id="qtsm" name="qtsm"
									value="<bean:write name="rs" property="qtsm"/>">
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
							<td colspan="3">
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
							<td colspan="5">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="3">
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
							<td colspan="2">
								<div align="center">
									����ѧ���Ƿ��м�ͥ��������֤��
								</div>
							</td>
							<td>
								<html:select name="rs" property="xy_sfyknzm">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<td>
								<div align="center">
									ƶ�������������Ƿ�������ѧ�����������
								</div>
							</td>
							<td>
								<html:select name="rs" property="xy_sfkns">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<td>
								<div align="center">
									������ѧ��༶�ۺϲ�������(%)
								</div>
							</td>
							<td>
								<input type="text" id="xy_sxnzhcppm" name="xy_sxnzhcppm" maxlength="3"
									style="width:100%;heigh:100%" 
									value="<bean:write name="rs" property="xy_sxnzhcppm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�༶��������ͨ����(%)
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="xy_bjmzpytgl" name="xy_bjmzpytgl" maxlength="3"
									style="width:100%;heigh:100%" 
									value="<bean:write name="rs" property="xy_bjmzpytgl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<div align="center">
									�Ƿ�ͬ���������
								</div>
							</td>
							<td>
								<html:select name="rs" property="xy_sftjsq">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����˵��
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="qtsm" rows='3' style="width:100%" onblur="yzdx(this,'qtsm')"/>
								<input type="hidden" id="qtsm" name="qtsm" value="">
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
							<td colspan="3">
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
							<td colspan="5">
								<bean:write name="rs" property="fdyshyj"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="3">
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
									����ѧ���Ƿ��м�ͥ��������֤��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sfyknzm"/>
							</td>
							<td>
								<div align="center">
									ƶ�������������Ƿ�������ѧ�����������
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sfkns"/>
							</td>
							<td>
								<div align="center">
									������ѧ��༶�ۺϲ�������(%)
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sxnzhcppm"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�༶��������ͨ����(%)
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xy_bjmzpytgl"/>
							</td>
							<td>
								<div align="center">
									�Ƿ�ͬ���������
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sftjsq"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����˵��
								</div>
							</td>
							<td colspan="5">
								<bean:write name="rs" property="qtsm"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td colspan="3">
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
							<td colspan="2">
								<div align="center">
									ѧУ������
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
								<input type="hidden" id="xxshyj" name="xxshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="qtsm" name="qtsm"
									value="<bean:write name="rs" property="qtsm"/>">
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
							<td colspan="3">
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
							<td colspan="2">
								<div align="center">
									����ѧ���Ƿ��м�ͥ��������֤��
								</div>
							</td>
							<td>
								<html:select name="rs" property="xy_sfyknzm">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<td>
								<div align="center">
									ƶ�������������Ƿ�������ѧ�����������
								</div>
							</td>
							<td>
								<html:select name="rs" property="xy_sfkns">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<td>
								<div align="center">
									������ѧ��༶�ۺϲ�������(%)
								</div>
							</td>
							<td>
								<input type="text" id="xy_sxnzhcppm" name="xy_sxnzhcppm" maxlength="3"
									style="width:100%;heigh:100%" 
									value="<bean:write name="rs" property="xy_sxnzhcppm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�༶��������ͨ����(%)
								</div>
							</td>
							<td colspan="3">
								<input type="text" id="xy_bjmzpytgl" name="xy_bjmzpytgl" maxlength="3"
									style="width:100%;heigh:100%" 
									value="<bean:write name="rs" property="xy_bjmzpytgl"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</td>
							<td>
								<div align="center">
									�Ƿ�ͬ���������
								</div>
							</td>
							<td>
								<html:select name="rs" property="xy_sftjsq">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����˵��
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="qtsm" rows='3' style="width:100%" onblur="yzdx(this,'qtsm')"/>
								<input type="hidden" id="qtsm" name="qtsm" value="">
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
							<td colspan="3">
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
									����ѧ���Ƿ��м�ͥ��������֤��
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sfyknzm"/>
							</td>
							<td>
								<div align="center">
									ƶ�������������Ƿ�������ѧ�����������
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sfkns"/>
							</td>
							<td>
								<div align="center">
									������ѧ��༶�ۺϲ�������(%)
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sxnzhcppm"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�༶��������ͨ����(%)
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="xy_bjmzpytgl"/>
							</td>
							<td>
								<div align="center">
									�Ƿ�ͬ���������
								</div>
							</td>
							<td>
								<bean:write name="rs" property="xy_sftjsq"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									����˵��
								</div>
							</td>
							<td colspan="5">
								<bean:write name="rs" property="qtsm"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td colspan="3">
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
							<td colspan="2">
								<div align="center">
									ѧУ������
								</div>
							</td>
							<td colspan="5">
								<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
								<input type="hidden" id="xxshyj" name="xxshyj" value="">
								<input type="hidden" id="fdyshyj" name="fdyshyj"
									value="<bean:write name="rs" property="fdyshyj"/>">
								<input type="hidden" id="qtsm" name="qtsm"
									value="<bean:write name="rs" property="qtsm"/>">
							</td>
						</tr>
					</logic:equal>
				</logic:notEqual>
				<!-- ���ݴ�ѧ -->
				<logic:equal name="xxdm" value="11078">
				<tr>
					<td colspan="7">
						<%@ include file="lnzzInfoList.jsp"%>
					</td>
				</tr>				
				</logic:equal>
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
