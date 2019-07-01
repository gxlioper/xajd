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
<html:html locale="true">
<head>
	<base target="_self" />
	<title><bean:message key="lable.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/yxglFunction.js"></script>
	<script type="text/javascript">
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：
			<bean:message bundle="yxgl" key="yxgl_xyybd_sdjyqy" />
		</div>
	</div>
	<html:form action="yxgl_xyybd_sdjyqy.do" method="post">
		<input type="hidden" id="sxsfdm" name="sxsfdm" value="">
		<table class="tbstyle" align="center">
			<tr>
				<td align=right width="40%">
					选择设置省份:
				</td>
				<td align=left>
					<html:text property="sfmc" styleId="format"
						style="width:150px;height:21px;font-size:10pt;" readonly="true" />
					<span style="width:18px;border:0px solid red;"> <html:select
							property="sfdm" styleId="sfdmId" onchange="getSfValue()"
							style="margin-left:-150px;width:168px; background-color:#FFEEEE;">
							<html:option value=""></html:option>
							<html:options collection="sfList" property="sfdm"
								labelProperty="sfmc" />
						</html:select>
					</span>
				</td>
			</tr>
		</table>
		<logic:present name="rs">
			<p class="style7">
				所设省份如下:
				<br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:iterate id="s" name="rs">
	                    &nbsp;<bean:write name="s" /> ,
	      </logic:iterate>
		</logic:present>
		<div class="buttontool">
			<button class="button2" onclick="yxglNotSfmc();">
				设 定
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2"
				onclick="document.forms[0].action='yxgl_xyybd_sdjyqy.do?doType=cancel';document.forms[0].submit();">
				取消设定
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" onclick="window.close();return false;">
				关闭
			</button>
		</div>
	</html:form>
</body>
</html:html>
