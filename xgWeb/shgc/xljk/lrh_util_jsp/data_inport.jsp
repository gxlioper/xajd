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
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script language="javascript">

</script>
	<body
		onload="checkWinType();title_m.innerHTML = window.dialogArguments.document.all('title_m').innerHTML+' - 数据导入';">
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<html:form action='/lrh_commen_util.do' method='post'
			enctype='multipart/form-data'>
		<html:text property="tableName" style="display:none"/>
			<div class="title">
				<div class="title_img" id="title_m"></div>
			</div>
			<logic:notEmpty name="dataImported">
				<script>
					alert("操作成功！");
					window.close();
				</script>
			</logic:notEmpty>
			<div id="search_m">
				<div class="searchcontent">
					<br />
					<table width="99%" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									请选择要导入的文件
								</td>
							</tr>
						</thead>
						<tr>
							<td align="center">
								<input type="file" name="file" id="file" value="" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<button class="button2" onclick="check_File()">
									上传
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2" onclick="Close();return false;">
									关闭
								</button>
							</td>
						</tr>
					</table>
					<br />
				</div>
			</div>
		</html:form>
	</body>
</html>
