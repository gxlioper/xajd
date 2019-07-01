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
		<title>思政队伍评优</title>
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
	<body onload="check_user();initZyList();initBjList();">
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/jxgljz" method="post">
		<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理-军训编制-单个班级建制维护
				</div>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name='rs' property="pk" scope="request"/>" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
					<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
				<fieldset>
					<legend>
						<title>
						班级建制维护
						</title>
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />名称：
							</td>
							<td align="left">
							<html:select property="xydm" style="width:180px" styleId="xy" 
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<input type="hidden" name="xyV" value="<bean:write name='rs' property="xydm" scope="request"/>"/>
							</td>
							<td align="right">
								专业名称：
							</td>
							<td align="left">
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								<input type="hidden" name="zyV" value="<bean:write name = "rs" property="zydm" scope="request"/>"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								班级名称：
							</td>
							<td align="left">
								<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
								</html:select>
								<input type="hidden" name="bjV" value="<bean:write name = "rs" property="bjdm" scope="request"/>"/>
							</td>
							<td align="right">
								年度:
							</td>
							<td align="left">
								<html:select property="nd" style="width:90px"
										styleId="nd">
								<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								教官名称：
							</td>
							<td align="left">
								<html:text name = "rs" property="jgmc" />
							</td>
							<td align="right">
								指导员:
							</td>
							<td align="left">
								<html:text name = "rs" property="ljzdymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								营长：
							</td>
							<td align="left">
								<html:text name = "rs" property="yzmc" />
							</td>
							<td align="right">
								连长:
							</td>
							<td align="left">
								<html:text name = "rs" property="lzmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								所属营：
							</td>
							<td align="left">
								<html:text name = "rs" property="yjmc" />
							</td>
							<td align="right">
								所属连:
							</td>
							<td align="left">
								<html:text name = "rs" property="ljmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								所属连性别：
							</td>
							<td align="left">
								<html:select name = "rs" property="ljxb" style="width:80px" styleId="ljxb">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
								</html:select>
							</td>
							<td align="right">
								教导员:
							</td>
							<td align="left">
								<html:text name = "rs" property="yjjdy" />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('jxgljz.do?method=jxgljzwhOneSave','bj-jgmc-xb');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    				alert("提交成功！");
    				dialogArgumentsQueryChick();
					Close();
   				</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    				alert("提交失败！");
    				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
