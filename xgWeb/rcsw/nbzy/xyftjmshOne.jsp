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
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbzympgc" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					日常事务-名片工程-宁职院有约信息审核
				</div>
			</div>
			<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<fieldset>
					<legend>
						宁职院有约信息审核
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>访谈题目：
							</td>
							<td align="left" colspan="3">
								<html:text name = "rs" property="fttm" styleId="fttm" style="width:90%" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>访谈时间：
							</td>
							<td align="left">
								<html:text name = "rs" property="ftsj" styleId="ftsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									  readonly="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>访谈地点：
							</td>
							<td align="left">
								<html:text name = "rs"property="ftdd" styleId="ftdd" style="width:300" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>主持人：
							</td>
							<td align="left">
								<html:text name = "rs" property="zcr" styleId="zcr" readonly="true"/>
							</td>
							<td align="right">
								节目负责人:
							</td>
							<td align="left">
								<html:text name = "rs" property="jmfzr" styleId="jmfzr" style="width:300"/>
							</td>
						</tr>
						<tr>
							<td align="right" rowspan="4">
								受访嘉宾信息：
							</td>
							<td align="center">
								姓名
							</td>
							<td align="center">
								职务
							</td>
							<td align="center">
								电话
							</td>
						</tr>
						<tr>
							<td align="right">
								<html:text name = "rs" property="sfjbf" style="width:100%"/>
							</td>
							<td align="left">
								<html:text name = "rs" property="sfjbfzw" style="width:100%"/>
							</td>
							<td align="right">
								<html:text name = "rs" property="sfjbfdh" style="width:100%"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<html:text name = "rs" property="sfjbs" style="width:100%"/>
							</td>
							<td align="left">
								<html:text name = "rs" property="sfjbszw" style="width:100%" />
							</td>
							<td align="right">
								<html:text name = "rs" property="sfjbsdh" style="width:100%"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<html:text name = "rs" property="sfjbt" style="width:100%"/>
							</td>
							<td align="left">
								<html:text name = "rs" property="sfjbtzw" style="width:100%"/>
							</td>
							<td align="right">
								<html:text name = "rs" property="sfjbtdh" style="width:100%"/>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								<font color="red">*</font>访谈基本内容
							</td>
							<td colspan="3">
								<html:textarea  name = "rs" property='ftjbnr' style="width:99%" styleId="ftjbnr"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								主办部门意见
							</td>
							<td colspan="3">
								<html:textarea name = "rs" property='zbbmyj' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<logic:equal name = "userType" value="xy" scope="session">
						<tr align="left">
							<td align="right">
								学工部意见
							</td>
							<td colspan="3">
								<html:textarea name = "rs" property='xgbyj' style="width:99%"
									rows='5' readonly="true"/>
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name = "userType" value="xy" scope="session">
						<tr align="left">
							<td align="right">
								学工部意见
							</td>
							<td colspan="3">
								<html:textarea name = "rs" property='xgbyj' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</logic:notEqual>
						<tr align="left">
							<td align="right">
								备注
							</td>
							<td colspan="3">
								<html:textarea name = "rs" property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<logic:equal name = "userType" value="xy" scope="session">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核：
							</td>
							<td align="left">
								<html:select name='rs' property="xysh" style="width:90px" styleId="xysh" >
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<td align="right">
								学校审核:
							</td>
							<td align="left">
								<html:select name='rs' property="xxsh" style="width:90px" styleId="xxsh" disabled="true">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name = "userType" value="xy" scope="session">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核：
							</td>
							<td align="left">
								<html:select name='rs' property="xysh" style="width:90px" styleId="xysh" >
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<td align="right">
								学校审核:
							</td>
							<td align="left">
								<html:select name='rs' property="xxsh" style="width:90px" styleId="xxsh">
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
						</tr>
						</logic:notEqual>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('nbzympgc.do?method=saveXyftjmshOne','fttm-ftsj-ftdd-zcr-ftjbnr');"
						style="width:80px" id="buttonSave">
						修 改
					</button>
					<button type="button" class="button2"
						onclick="window.open('nbzympgc.do?method=XyftjmshReport&pk='+$('pk').value);"
						style="width:80px" id="buttonSave">
						打 应 报 表
					</button>
				</div>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败,请不要重复申请");
    </script>
				</logic:equal>
			</logic:present>
			
		</html:form>
	</body>
</html>
