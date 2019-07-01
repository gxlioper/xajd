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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		function pltg()
		{
			var xn=document.all['xn'].value;
			var nd=document.all['nd'].value;
			var xq=document.all['xq'].value;
			var gnmk=document.all['gnmk'].value;
			if(""==xn||""==nd||""==gnmk)
			{
				alert("�뽫��*ѡ����д����");
				return false;
			}
			refreshForm("/xgxt/xx_plsh.do?doType=xx_plsh");
		}
	</script>

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<center>
			<html:form action="/xx_plsh" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�ѧ������ - ������� - ѧУ�������
					</div>
				</div>
				<fieldset>
					<legend>
						��������
					</legend>
					<table width="80%" align="center" class="tbstyle">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									ѧУ�������
								</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">							
									<font color="red">*</font>����б�
							</td>
							<td height="25" align="left">
								<html:select  property="gnmk">
									<html:option value="">---------��ѡ��--------</html:option>
									<html:options collection="list" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">							
									<font color="red">*</font>ѧ�꣺
							</td>
							<td height="25" align="left">
								<html:select property="xn">
									<html:options collection="xnndList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<logic:notEqual value="11417" name="xxdm">
						<tr>
							<td width="45%" height="25" align="right">
								<font color="red">*</font>��ȣ�
							</td>
							<td height="25" align="left">
								<html:select property="nd">
									<html:options collection="xnndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						</logic:notEqual>
						<tr>
							<td width="45%" height="25" align="right">
								ѧ�ڣ�
							</td>
							<td height="25" align="left">
								<html:select property="xq">
									<option value="">
									</option>
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button type="button" class="button2" onclick="pltg()">
										����ͨ��
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:notEmpty name="message">
					<logic:equal name="message" value="update_success">
						<script>alert("��˳ɹ�!")</script>
					</logic:equal>
					<logic:equal name="message" value="update_fail">
						<script>alert("���ʧ��!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
