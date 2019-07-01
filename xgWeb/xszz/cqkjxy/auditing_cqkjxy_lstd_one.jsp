<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

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
		function yz1(){
			var tyhjxf = document.getElementById('tyhjxf').value;
			var zdqfxe = document.getElementById('zdqfxe').value;
			if(Math.round(tyhjxf) > Math.round(zdqfxe)){
				var t = "ͬ�⻺��ѧ��:" + tyhjxf + "Ԫ���ѳ�����׿�����Ƿ�Ѷ��:" + zdqfxe + "Ԫ!";
				alert(t);
	        	return false;
	       	}
			refreshForm('/xgxt/auditing_cqkjxy_lstd_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yz(){
			var xxsh = document.getElementById('xxsh').value
			var fdyshyj = document.getElementById('fdyshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			if(xxsh == "ͨ��"){
				alert("ѧУ�����ͨ�����������޸���˽��!");
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
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
			}
			refreshForm('/xgxt/auditing_cqkjxy_lstd_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ��ɫͨ����� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="zdqfxe" name="zdqfxe"
				value="<bean:write name="zdqfxe"/>">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh"/>">
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="2">
						ѧ��
					</td>
					<td align="left" colspan="4">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							רҵ
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����ԭ��
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="hjyy" />
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<div align="center">
							���������¼
						</div>
					</td>
				</tr>
				<tr>
					<td width="8%">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td width="8%" align="center">
						����
					</td>
					<td width="9%">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td width="9%" align="center">
						ѧУ����ʱ��
					</td>
					<td width="9%">
						<div align="center">
							ͬ�⻺��ѧ��
						</div>
					</td>
					<td width="9%" align="center">
						�ݻ�����ʱ��
					</td>
					<td colspan="2">
						����ԭ��
					</td>
				</tr>
				<logic:empty name="sqList">
					<tr>
						<td colspan="8" align="center">
							û�������¼!
						</td>
					</tr>
				</logic:empty>
				<logic:notEmpty name="sqList">
					<%
						ArrayList sqList = (ArrayList) request
								.getAttribute("sqList");

						for (Iterator it = sqList.iterator(); it.hasNext();) {
							String[] temp = (String[]) it.next();
					%>
					<tr>
						<td align="center">
							<%=temp[0]%>
						</td>
						<td align="center">
							<%=temp[1]%>
						</td>
						<td align="center">
							<%=temp[2]%>
						</td>
						<td align="center">
							<%=temp[4]%>
						</td>
						<td align="center">
							<%=temp[5]%>
						</td>
						<td align="center">
							<%=temp[6]%>
						</td>
						<td colspan="2">
							<%=temp[3]%>
						</td>
					</tr>
					<%
					}
					%>
				</logic:notEmpty>
				<logic:equal name="userType" value="fdy">
					<tr>
						<td colspan="2">
							<div align="center">
								Ƿ������
							</div>
						</td>
						<td colspan="4">
							<bean:write name="rs" property="qjf" />
						</td>
						<td colspan="2">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
					<tr>
						<td colspan="2">
							<div align="center">
								Ƿ������
							</div>
						</td>
						<td colspan="4">
							<bean:write name="rs" property="qjf" />
						</td>
						<td colspan="2">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								����Ա���
							</div>
						</td>
						<td colspan="4">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								����Ա����ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdyspsj" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xx">
					<tr>
						<td colspan="2">
							<div align="center">
								Ƿ������
							</div>
						</td>
						<td colspan="4">
							<bean:write name="rs" property="qjf" />
						</td>
						<td colspan="2">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								����Ա���
							</div>
						</td>
						<td colspan="4">
							<bean:write name="rs" property="fdysh" />
						</td>
						<td>
							<div align="center">
								����Ա����ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rs" property="fdyspsj" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td colspan="4">
							<bean:write name="rs" property="xysh" />
						</td>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />����ʱ��
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xyspsj" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ͬ�⻺��ѧ��
							</div>
						</td>
						<td colspan="4">
							<input name="tyhjxf" maxlength="8" id="tyhjxf" type="text"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="tyhjxf"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="center">
								��������
							</div>
						</td>
						<td>
							<html:select name="rs" property="hjqx">
								<html:option value="">------��ѡ��------</html:option>
								<html:options collection="hjqxList" property="hjqx"
									labelProperty="hjqx" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td colspan="2">
						<div align="center">
							��˽��
						</div>
					</td>
					<td colspan="4">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property='spsj' />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							����Ա������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
						<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<logic:equal name="userType" value="xx">
					<button class="button2" onclick="yz1()" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
				</logic:equal>
				<logic:notEqual name="userType" value="xx">
					<button class="button2" onclick="yz()" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
				</logic:notEqual>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
