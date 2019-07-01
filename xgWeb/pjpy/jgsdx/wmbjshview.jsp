
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
  		<script language="javascript" src="js/pjpy/pjpy_hzy.js"></script>
  		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		 <script language="javascript" src="js/pjpyFunction.js"></script>
		<html:form action="/pjpyjgsdxwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="pjpy_ahjg_jxjsh" bundle="pjpyahjg"/>
				</div>
			</div>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							文明班级单个审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
				<td align="right" style="width: 20%">
						学年：
					</td>
					<td align="left" style="width: 30%">
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<td align="right">
						荣誉称号：
					</td>
					<td align="left">
						<html:text name="rs" property="rychmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:text name="rs" property="xymc" readonly="true"></html:text>
					</td>
					<td align="right">
						学生人数：
					</td>
					<td align="left">
						<html:text name="rs" property="xsrs" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<html:text name="rs" property="zymc" readonly="true"></html:text>
					</td>
					<td align="right">
						辅导员：
					</td>
					<td align="left">
						<html:text name="rs" property="bzr" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<html:text name="rs" property="bjmc" readonly="true"></html:text>
					</td>
					<td align="right">
						审核：
					</td>
					<td align="left">
						<html:select property="shxm" styleId="shxm" style="width:100px" styleClass="select">
							<html:options collection="shxmList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学期：
					</td>
					<td align="left">
						<html:text name="rs" property="xq"  readonly="true"></html:text>
					</td>
					<td align="right">
						学年内受记过&nbsp;&nbsp;&nbsp;<br/>以上处分人数：
					</td>
					<td align="left">
						<html:text property="bjcfrs" name="rs" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">主要事迹：</td>
					<td align="left" colspan="3">
						<textarea rows="5" cols="" style="width:98%" readonly="readonly"><bean:write name="rs" property="zysj"/></textarea>
					</td>
				</tr>
				<tr>
					<td align="right">审核意见：</td>
					<td align="left" colspan="3">
						<html:textarea property="shyj" rows="5"
						 style="width:98%" styleClass="inputtext"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('wmbjshbyone.do');"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>	
			</div>
		</html:form>
		<logic:notEmpty name="inserted">
			<logic:equal value="yes" name="inserted">
			<script>
				document.getElementById('shyj').value="";
				alert("操作成功");
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
			<logic:equal value="no" name="inserted">
			<script>
				alert("操作失败");
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		</logic:notEmpty>
	</body>
</html>
