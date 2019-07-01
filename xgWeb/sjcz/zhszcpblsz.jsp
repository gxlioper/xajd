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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/errMsg" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
				<input type="hidden" id="url" name="url" value="/sjcz/zhszcpblsz.jsp" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								综合素质测评比例设置
							</td>
						</tr>
					</thead>
					<tr>
						<td align="left">
							<bean:message key="lable.xsgzyxpzxy" />德育比例
							<html:text name="rs" property="XYDYKPF" styleId="XYDYKPF" style="width:30px"/>
							公寓德育比例
							<html:text name="rs" property="GYDYKPF" styleId="GYDYKPF" style="width:30px"/>
							智育比例
							<html:text name="rs" property="ZYCJ" styleId="ZYCJ" style="width:30px"/>
							体育比例
							<html:text name="rs" property="TYCJ" styleId="TYCJ" style="width:30px"/>
							学习创新比例
							<html:text name="rs" property="GZXXCX" styleId="GZXXCX" style="width:30px"/>
							是否走读生
							<html:select name="rs" property="SFZDS" styleId="SFZDS" >
								<html:option value="0">住宿生</html:option>
								<html:option value="1">走读生</html:option>
							</html:select>
					</tr>
					<tr>
						<td align="left">
							<bean:message key="lable.xsgzyxpzxy" />德育比例
							<html:text name="rs1" property="XYDYKPF1" styleId="XYDYKPF1" style="width:30px"/>
							公寓德育比例
							<html:text name="rs1" property="GYDYKPF1" styleId="GYDYKPF1" style="width:30px"/>
							智育比例
							<html:text name="rs1" property="ZYCJ1" styleId="ZYCJ1" style="width:30px"/>
							体育比例
							<html:text name="rs1" property="TYCJ1" styleId="TYCJ1" style="width:30px"/>
							学习创新比例
							<html:text name="rs1" property="GZXXCX1" styleId="GZXXCX1" style="width:30px"/>
							是否走读生
							<html:select name="rs1" property="SFZDS1" styleId="SFZDS1" >
								<html:option value="0">住宿生</html:option>
								<html:option value="1">走读生</html:option>
							</html:select>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="refreshForm('/xgxt/savablsz.do')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("<%=request.getAttribute("message").toString()%>");
				Close();
			</script>
		</logic:equal>
	</body>
</html>
