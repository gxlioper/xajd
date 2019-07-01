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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<center>
			<html:form action="/chgPriseBat" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：军训管理 - 参数设置 - 时间设定
					</div>
				</div>
				<fieldset>
					<legend>
						军训服装申请时间设定
					</legend>
					<table width="80%" class="tbstyle" align="center">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									军训服装申请时间设定
								</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">
								学年：
							</td>
							<td height="25" align="left">
								<html:select property="xn" value="<%=(String)request.getAttribute("xn")%>">
									<html:options collection="xnndList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">
								年度：
							</td>
							<td height="25" align="left">
								<html:select property="nd" value="<%=(String)request.getAttribute("nd")%>">
									<html:options collection="xnndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">
								学期：
							</td>
							<td height="25" align="left">
								<html:select property="xq" value="<%=(String)request.getAttribute("xq")%>">
									<option value="">
									</option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">
								年级：
							</td>
							<td height="25" align="left">
								<html:select property="nj" style="width:90px" value="<%=(String)request.getAttribute("nj")%>">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								申请开始时间：
							</td>
							<td align="left">
								<input type="hidden" name="kssqsj" id="kssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('kssqsj1','y-mm-dd');"
									value="<bean:write name="kssqsj1" />" name="kssqsj1"
									id="kssqsj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssqsj2" />"
									name="kssqsj2" id="kssqsj2" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssqsj3" />"
									name="kssqsj3" id="kssqsj3" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssqsj4" />"
									name="kssqsj4" id="kssqsj4" />
							</td>
						</tr>
						<tr>
							<td align="right">
								申请截至时间：
							</td>
							<td align="left">
								<input type="hidden" name="jssqsj" id="jssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('jssqsj1','y-mm-dd');"
									value="<bean:write name="jssqsj1" />" name="jssqsj1"
									id="jssqsj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssqsj2" />"
									name="jssqsj2" id="jssqsj2" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssqsj3" />"
									name="jssqsj3" id="jssqsj3" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssqsj4" />"
									name="jssqsj4" id="jssqsj4" />
							</td>
						</tr>
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button type="button" class="button2"
										onclick="saveTrainConf('kssqsj','jssqsj','xn','nd','train_conf.do?act=save')">
										保存
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:notEmpty name="ok">
					<logic:equal name="ok" value="ok">
						<script>alert("保存成功!")</script>
					</logic:equal>
					<logic:equal name="ok" value="no">
						<script>alert("保存失败!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
