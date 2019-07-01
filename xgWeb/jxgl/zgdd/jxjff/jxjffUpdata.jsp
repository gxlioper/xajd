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
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/zgdd_jxgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/zgdd_jxgl.do?method=gfsjxjglUpdata" />
				<fieldset>
					<legend>
						国防生奖学金发放单个信息
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<logic:equal name="userType" value="gfb">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?gfs=yes',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
								</logic:equal>
							</td>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly = "true"/>
							</td>
							<td align="right">
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly = "true" />
							</td>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly = "true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly = "true"/>
							</td>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly = "true"/>
							</td>
							<td align="right">
								<font color="red">*</font>奖学金发放时间：
							</td>
							<td align="left">
								<html:text name='rs' property="jxjsfsj" styleId="jxjsfsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jxjsfsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								奖学金停发时间：
							</td>
							<td align="left">
							<html:text name='rs' property="jxjtfsj" styleId="jxjtfsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jxjtfsj','y-mm-dd');" />
							</td>
							<td align="right">
								奖学金恢复时间：
							</td>
							<td align="left">
								<html:text name='rs' property="jxjfhsj" styleId="jxjfhsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jxjfhsj','y-mm-dd');" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								奖学金停发原因:
							</td>
							<td colspan="3">
								<html:text name='rs' property="jxjtfyy" styleId="jxjtfyy" maxlength="50" style="width:100%"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<logic:equal name="userType" value="gfb">
					<button type="button" class="button2"
						onclick="SaveApply('xh-jxjsfsj','zgdd_jxgl.do?method=gfsjxjglSave');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
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
