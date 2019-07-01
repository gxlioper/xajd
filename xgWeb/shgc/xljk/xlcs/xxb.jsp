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
	<body
		onload="checkWinType();dataManLoadTest();document.forms[0].stlsh.value=window.dialogArguments.document.getElementById('stlsh').value;document.forms[0].stlsh.blur();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<div id="main" style="heigth:100px;">
				<div id="search_m" style="heigth:100px;">
					<div id="result">
						<div class="searchcontent">
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType"
									value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" id="pkValue" name="pkValue"
									value="<bean:write name="pkValue" scope="request"/>" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle"
									value="stlsh" />
								<input type="hidden" id="url" name="url" value="/sjcz/sjb.jsp" />
								<fieldset>
									<legend>
										选项维护
									</legend>
									<table width="99%" class="tbstyle">
										<tr>
											<td align="right">
												<font color="red">*</font>试题流水号：
											</td>
											<td align="left">
												<html:text name='rs' property="stlsh" style="width: 120px"
													styleId="stlsh" />
											</td>
											<td align="right">
												<font color="red">*</font>选项序号：
											</td>
											<td align="left">
												<html:text name='rs' property="xxxh" style="width: 120px"
													styleId="xxxh" />
											</td>
										</tr>
										<tr>
											<td align="right">
												选项分值：
											</td>
											<td align="left">
												<html:text name='rs' property="xxfz" style="width: 120px"
													styleId="xxfz" />
											</td>
											<td align="right">
												是否显示：
											</td>
											<td align="left">
												<html:select name="rs" property="xxxsbj" style="width:60px"
													styleId="xxxsbj">
													<html:options collection="ynList" property="en"
														labelProperty="cn" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td align="right">
												<font color="red">*</font>选项内容：
											</td>
											<td colspan="3">
												<html:textarea name='rs' property='xxnr' style="width:95%"
													rows='4' />
											</td>
										</tr>
									</table>
								</fieldset>
								<div class="buttontool">
									<button class="button2" onclick="dataCanModi(true)"
										style="width:80px" id="buttonModi">
										修 改
									</button>
									<button class="button2" onclick="dataDoSave('stlsh-xxxh-xxnr')"
										style="width:80px" id="buttonSave">
										保 存
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="button2" onclick="Close();return false;" style="width:80px"
										id="buttonClose">
										关 闭
									</button>
								</div>
							</logic:notEmpty>
						</div>
					</div>
				 <jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
			</div>				
		</html:form>
	</body>
</html>
