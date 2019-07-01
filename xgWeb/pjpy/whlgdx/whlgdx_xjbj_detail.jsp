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
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="pjpy/whlgdx/whlgdxjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<html:form action="/pjpy_whlgdx.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">
					 当前位置：评奖评优 - 信息维护 - 先进班级信息
					</span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="disableEle" name="disableEle" value="bjdm-xjbjlbdm" />
				 <div id="items" name="items" style="display:none; position: absolute;background-color: #AFEEEE; " ></div>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								先进班级信息维护
							</td>
						</tr>
					</thead>
					<tr>
						<td width="30%" align="right">
							<font color="red">*</font>学年：<font color="red">&nbsp;</font></td>
						<td width="17%" align="left">
							<html:select name='rs' property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<td width="28%" align="right"><font color="red">*</font>班级：
						</td>
						<td width="25%" align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
							<html:hidden name='rs' property="bjdm" styleId="bjdm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>学期：</td>
						<td align="left">
							<html:select name='rs' property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>先进班级类别：</td>
						<td align="left">
							<html:text property="xjbjlbmc" name="rs" readonly="true"></html:text>
							<html:hidden property="xjbjlbdm" name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3" align="left">
							<html:textarea name='rs' property="bz" styleId="bz" rows="3" style="width:98%"></html:textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
						<logic:equal value="yes" name="updated">
							<button class="button2"
							onclick="zhszcpsave('xn-xq-bjdm-xjbjlbdm','pjpy_whlgdx.do?method=xjbjSave');"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert("操作失败！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</logic:present>
</html>
