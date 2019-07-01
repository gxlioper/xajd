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
	<body>
	<script language="javascript">
	
	</script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/Wjcf_Xskqxx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�Υ�ʹ��� - ѧ������ - ������Ϣά��
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						<bean:message key="lable.xsgzyxpzxy" />�³�����ͳ��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									<bean:message key="lable.xsgzyxpzxy" />��&nbsp;&nbsp;&nbsp;&nbsp;
									<html:text property="xymc" styleId="xymc" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
									<td align="center">
									�����·ݣ�
									<html:text  styleId="rq" property="rq" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									�����ʣ�&nbsp;&nbsp;
									<html:text property="cql" styleId="cql" readonly="true"></html:text>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
			</div>
		</html:form>
	</body>
</html>
