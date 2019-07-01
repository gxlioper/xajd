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
		<html:form action="/topic_pub" method="post">
			
			<logic:equal value="ok" name="inserted">
				<script type="text/javascript">
					alert('发布成功!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script type="text/javascript">
					alert('发布失败!');
				</script>
			</logic:equal>
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			
			<table width="100%" class="tbstyle">
				<tr>
					<th colspan="2" height="25" bgcolor=""><b>发表新主题</b></th>
				</tr>
				<tr>
					<td><font color="red">*</font>标题:</td>
					<td><input type="text" name="ztbt" id="ztbt" value="${rs.ztbt}" size="50"/></td>
				</tr>
				<tr>
					<td valign="top"><font color="red">*</font>正文:</td>
					<td>
						<textarea rows="10" cols="100" name="ztzw" id="ztzw" type="_moz">${rs.ztzw}</textarea>
					</td>
				</tr>
				<tr>
					<td valign="top"><font color="red">*</font>对象:</td>
					<td>
						<select id="dxzmc" name="dxzmc" >
							<logic:iterate id="dxz" name="dxzList">
							<option label="${dxz.dxzmc}" value="${dxz.dxzmc}">${dxz.dxzmc}</option>
							</logic:iterate>
						</select>
					</td>
				</tr>
			</table>
				<div class="buttontool" align="center">
					<input type="hidden" name="act" value="save"/>
					<button type="button" class="button2" onclick="if(document.getElementById('ztbt').value!=''&&document.getElementById('ztzw').value!=''){submit()}else{alert('请将带*的项填写完整')}" style="width:80px" id="buttonSave">
						保 存
					</button>
				</div>
			
			
		</html:form>
	</body>
</html>
