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
	<body onload="checkWinType();dataManLoadTest();">
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
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="url" name="url" value="/sjcz/sjb.jsp" />
				<fieldset>
					<legend>
						试卷维护
					</legend>
					<table width="99%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>试题类型:
							</td>
							<td align="left">
								<html:select name="rs" property="stlxdm" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="stlxdm"
										labelProperty="stlxmc" />
								</html:select>
							</td>
							<td align="right">
								试题难度级别：
							</td>
							<td align="left">
								<html:select name="rs" property="stndjbdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="stndjbList" property="stndjbdm"
										labelProperty="stndjbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>试题计分方式：
							</td>
							<td align="left">
								<html:select name="rs" property="stjffs" style="width:90px"
									styleId="stjffs"
									onchange="if(document.forms[0].stjffs.value==1) document.forms[0].stfz.disabled=false;else{ document.forms[0].stfz.value='';document.forms[0].stfz.disabled=true;}">
									<html:option value=""></html:option>
									<html:option value="1">按题</html:option>
									<html:option value="0">按选项</html:option>
								</html:select>
							</td>
							<td align="right">
								试题分值：
							</td>
							<td align="left">
								<html:text name='rs' property="stfz" style="width: 120px"
									styleId="stfz" onblur="numFormatChk(this)" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>试题答案：
							</td>
							<td align="left">
								<html:text name='rs' property="stda" style="width: 120px"
									styleId="stda" />
							</td>
							<td align="right">
								是否显示：
							</td>
							<td align="left">
								<html:select name="rs" property="stxsbj" style="width:60px"
									styleId="stxsbj">
									<html:options collection="ynList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								<font color="red">*</font>试题内容：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='stnr' style="width:95%"
									rows='6' />
							</td>
						</tr>
						<tr>
							<td align="right">
								试题答案解释：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='stdajs' style="width:95%"
									rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						修 改
					</button>
					<button class="button2"
						onclick="dataDoSave('stlxdm-stjffs-stda-stnr')" style="width:80px"
						id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			  <jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
