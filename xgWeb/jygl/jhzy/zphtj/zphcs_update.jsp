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
	<body >
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/jhzyzphcs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />
				<fieldset>
					<legend>
						调研计划
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />名称
							</td>
							<td align="left">
								<logic:equal  value="xy" name = "userType" scope="session">
								<input name = "xydm" type = "hidden" value = "<bean:write name = "userDep" scope = "session" />" />
								<html:select name="rs" property="xydm"  style="width:160px" styleId="xy" disabled="true">
									<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:equal>
								<logic:notEqual value="xy" name = "userType" scope="session">
									<html:select name="rs" property="xydm"  style="width:160px" styleId="xy">
									<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
								</logic:notEqual>
							</td>
							<td align="right">
								年度
							</td>
							<td align="left">
								<html:select name="rs"  property="nd" style="width:60px" styleId="nd">
										<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								举办大型招聘会次数
							</td>
							<td align="left">
								<html:text name='rs' property="dxzphcs" styleId="dxzphcs" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
							<td align="right" >
								大型招聘会单位数量
							</td>
							<td align="left">
								<html:text name='rs' property="dxzphdws" styleId="dxzphdws" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								大型招聘会岗位数量
							</td>
							<td align="left">
								<html:text name='rs' property="dxzphgws" styleId="dxzphgws" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
							<td align="right" >
								举办小型招聘会次数
							</td>
							<td align="left">
								<html:text name='rs' property="xxzphcs" styleId="xxzphcs" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								小型招聘会单位数量
							</td>
							<td align="left">
								<html:text name='rs' property="xxzphdws" styleId="xxzphdws" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
							<td align="right" >
								小型招聘会岗位数量
							</td>
							<td align="left">
								<html:text name='rs' property="xxzphgws" styleId="xxzphgws" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								举办用人单位宣讲会次数
							</td>
							<td align="left">
								<html:text name='rs' property="xjhcs" styleId="xjhcs" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
							<td align="right" >
								宣讲会单位数量
							</td>
							<td align="left">
								<html:text name='rs' property="xjhdws" styleId="xjhdws" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								宣讲会岗位数量
							</td>
							<td align="left">
								<html:text name='rs' property="xjhgws" styleId="xjhgws" maxlength="20"  onkeypress='return sztzNumInputValue(this,6,event)' onblur="chkInput(this,event)"/>
							</td>
							<td align="right" >
							</td>
							<td align="left">
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2"
						onclick="refreshForm('jhzyzphcs.do?method=zphcsSave');"
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
			<logic:present name="update">
				<logic:equal name="update" value="yes">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    alert("提交失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
