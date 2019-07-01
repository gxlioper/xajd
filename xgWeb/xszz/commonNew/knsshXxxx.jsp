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
			if((("ͨ��" == xxsh)) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
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
		
			refreshForm('/xgxt/new_common_xszz.do?method=knsshXxxx&actDo=save');
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ��������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="xxsh" value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td width="16%">
						<div align="center">
							���
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="nd" />
					</td>
					<td width="16%" scope="col">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td width="34%" scope="col">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xh" />
					</td>
					<td scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td scope="col">
						<bean:write name='rs' property="xm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<bean:write name='rs' property="sfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xymc" />
					</td>
					<td>
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xz" />
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
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ע
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="bz"/>
					</td>
				</tr>
				<logic:equal name="isXX" value="is">
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />���
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xysh" />
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
				</logic:equal>
				<logic:notEqual name="isXX" value="is">
				<tr>
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
					<td>
						<div align="center">
						</div>
					</td>
					<td>
					</td>
				</tr>
				</logic:notEqual>
				<tr>
					<td>
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xyshyj" rows='3' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td>
						<div align="left">
							ѧУ������
						</div>
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="xxshyj" rows='3' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
