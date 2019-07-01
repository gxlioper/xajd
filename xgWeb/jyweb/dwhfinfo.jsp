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

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />
		<base target="_self">
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/viewallzpinfo.do" method="post">
			<html:hidden name="rs" property="dwmc" />
			<div class="kuang" align="center">
				<fieldset>
					<legend>
						单位回复内容
					</legend>
					<table width="97%" border="1" cellpadding="0" cellspacing="0">

						<tr height="25">
							<td width="15%" align="right">
								单位名称：
							</td>
							<td width="35%">
								<bean:write name="rs" property="dwmc" />
							</td>
							<td width="15%" align="right">
								回复时间：
							</td>
							<td width="35%">
								<bean:write name="rs" property="hfsj" />
							</td>
						</tr>
						<tr height="25">
							<td colspan="4">
								<bean:write name="rs" property="xm" />
								同学，你好！
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<html:textarea name="rs" property="hf" rows="10"
									style="width:100%" readonly="true" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<logic:equal name="usertype" value="xs" scope="session">
									<a target="_blank"
										href="viewallzpinfo.do?method=alljyzpinfo&jytype=jyweb&act=query&dwmc=<bean:write name="rs" property="dwmc" />">去查看该单位发布的相关招聘信息</a>
								</logic:equal>
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</html:form>
	</body>
</html>
