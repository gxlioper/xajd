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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">

</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<html:form action="/hdzj_report_save" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置： 评奖评优 - 参数设置 - 奖学金单个设置
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" name="pk" property="pk" value="<bean:write name="rs" property="jxjdm"/>" />
				<input type="hidden" name="jxjNd" property="jxjNd" value="<bean:write name="rs" property="nd"/>" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>奖学金单个设置表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
							<td align="right" colspan="2">
								<font color="red">*</font>奖学金:
							</td>
							<td align="left">
							<html:select name="rs" property="jxjdm" style="width:150px" styleId="jxjdm" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
							</html:select>
							</td>
						<td align="right" >
							<font color="red">*</font>年度
						</td>
						<td align="left" colspan="4">	
						<html:select name = "rs" property="nd" style="width:90px" disabled="true"
									styleId="nd">
									<html:options collection="njList" property="nd"
										labelProperty="nd" />
						</html:select>
						</td>
					</tr>
					<tr style="height:22px">
							<td align="right" colspan="2">
								<font color="red">*</font>奖学金类别:
							</td>
							<td align="left">
							<html:select name="rs" property="lbdm" style="width:150px" styleId="jxjdm">
									<html:option value=""></html:option>
									<html:options collection="jxjlbList" property="lbdm"
										labelProperty="lbmc" />
							</html:select>
							</td>
						<td align="right" >
							<font color="red">*</font>是否只需要贫困生参评
						</td>
						<td align="left" colspan="4">	
						<html:select name = "rs" property="sfxdpks" style="width:90px"
									styleId="nd">
									<html:options collection="sfpksList" property="en"
										labelProperty="cn" />
						</html:select>
						</td>
					</tr>
					<tr style="height:22px"> 
						<td align="right">
							<font color="red">*</font>评奖开始时间
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="pjksrq" styleId="pjksrq"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('pjksrq','y-mm-dd');" />
						</td>
						<td align="right">
							<font color="red">*</font>评奖结束时间
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="pjjsrq" styleId="pjjsrq"
								onblur="dateFormatChg(pjjsrq)" style="cursor:hand;"
								onclick="return showCalendar('pjjsrq','y-mm-dd');" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" >
							<font color="red">*</font>奖学金金额：
						</td>
						<td align="left" colspan="8">
							<html:text name="rs" property="jxjje" style="width: 120px"/> 元
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							奖学金详细说明：
						</td>
						<td colspan="7">
							<html:textarea name='rs' property='jxjxxsm' style="width:99%"
								rows='12' />
						</td>
					</tr>
					
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="pjpyTableDoSave('/xgxt/pjpy_jxjsz_save.do','jxjdm-nd-lbdm-sfxdpks-pjksrq-pjjsrq-jxjje')">
						 修 改
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("提交成功！");
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