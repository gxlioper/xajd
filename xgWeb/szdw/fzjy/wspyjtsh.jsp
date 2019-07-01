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
		<html:form action="/szdwfzjy" method="post">
			
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			
			<logic:notEmpty name="rs">
			<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
			<input type="hidden" id="bjdm" name="bjdm"
				value="<bean:write name='rs' property='bjdm'/>" />
			
				<fieldset>
					<legend>
						五四评优先进集体
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>学年：
							</td>
							<td align="left">
								<html:select name = "rs" property="xn" style="width:120px" 
									styleId="xn">
								<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>年度：
							</td>
							<td align="left">
								<html:select name = "rs" property="nd" style="width:90px"
										styleId="nd">
								<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>申报奖项:
							</td>
							<td colspan="3">
								<html:text name = "rs" property="sbjx" style="width:99%"  styleId="sbjx"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								班级名称
							</td>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<bean:write name='rs' property="nj"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<bean:write  name='rs' property="zymc"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								春季成绩合格率：
							</td>
							<td align="left">
								<html:text name='rs' property="cjcjhgl" />
							</td>
							<td align="right">
								春季成绩优秀率:
							</td>
							<td align="left">
								<html:text name='rs' property="cjcjyxl" />
							</td>
						</tr>
						<tr>
							<td align="right">
								秋季成绩合格率：
							</td>
							<td align="left">
								<html:text name='rs' property="qjcjhgl " />
							</td>
							<td align="right">
								秋季成绩优秀率:
							</td>
							<td align="left">
								<html:text name='rs' property="cjcjyxl" />
							</td>
						</tr>
						<tr>
							<td align="right">
								英语4级通过率：
							</td>
							<td align="left">
								<html:text name='rs' property="yysjhgl" />
							</td>
							<td align="right">
								英语4级优秀率：
							</td>
							<td align="left">
								<html:text name='rs' property="yysjyxl" />
							</td>
						</tr>
						<tr>
							<td align="right">
								英语6级通过率：
							</td>
							<td align="left">
								<html:text name='rs' property="yyljhgl" />
							</td>
							<td align="right">
								英语6级优秀率：
							</td>
							<td align="left">
								<html:text name='rs' property="yyljyxl" />
							</td>
						</tr>
						<logic:equal  name="userType" value = "xy" scope = "session">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核：
							</td>
							<td align="left">
								<html:select name = "rs" property="xysh" style="width:120px" styleId="xysh" >
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<td align="right">								
							`	学校审核：
							</td>
							<td align="left">
								<html:select name = "rs"  property="xxsh" style="width:120px" styleId="xxsh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
								<input type = "hidden" name = "xxsh" value="<bean:write name = "rs"  property="xxsh"/>" />
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="userType" value = "xy" scope = "session">
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />审核：
							</td>
							<td align="left">
								<html:select  name = "rs"  property="xysh" style="width:120px" styleId="xysh" disabled = "true">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
								<input type = "hidden" name = "xysh" value="<bean:write name = "rs"  property="xysh"/>" />
							</td>
							<td align="right">
								学校审核：
							</td>
							<td align="left">
								<html:select  name = "rs"  property="xxsh" style="width:120px" styleId="xxsh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
						</tr>
						</logic:notEqual>
						<tr align="left">
							<td align="right">
								备注
							</td>
							<td colspan="3">
								<html:textarea name = "rs" property="bz" style="width:99%" rows="5" />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="szsxDataDoSave('szdwfzjy.do?method=wspyxjjtsh','bjdm-xn-nd');"
						style="width:80px" id="buttonSave">
						保存
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("修改成功！");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("修改失败！");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
