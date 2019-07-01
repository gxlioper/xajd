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
		<base target="_self"/>
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
		function checkNull(){
			var fromDate = document.forms[0].fromDate.value;
			var toDate = document.forms[0].toDate.value;
			if(fromDate == ""){
				alert("请将开始时间填上!");
				document.getElementById("fromDate").focus();
				return;
			}
			if(toDate == ""){
				alert("请将结束时间填上!");
				document.getElementById("toDate").focus();
				return;
			}
			refreshForm('/xgxt/yxgl_init_time.do?doType=save');
		}
</script>

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		
		<center>
			<html:form action="/yxgl_init_time" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：迎新管理 - 参数设置 - 参数设定
					</div>
				</div>
				<fieldset>
					<legend>
						参数设置
					</legend>
					<table width="80%" align="center" class="tbstyle">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									迎新管理参数设定
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>迎新开始时间：
							</td>
							<td align="left">
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('fromDate','y-mm-dd');"
									value="<bean:write name="rs" property="fromDate"/>" 
									name="fromDate" id="fromDate" />
						<%-- 
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj2" />"
									name="kssqsj2" id="kssqsj2" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj3" />"
									name="kssqsj3" id="kssqsj3" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj4" />"
									name="kssqsj4" id="kssqsj4" />
							</td>
						--%>	
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>迎新截至时间：
							</td>
							<td align="left">
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('toDate','y-mm-dd');"
									value="<bean:write name="rs"  property="toDate"/>" 
									name="toDate" id="toDate" />
							<%-- 		
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj2" />"
									name="jssqsj2" id="jssqsj2" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj3" />"
									name="jssqsj3" id="jssqsj3" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj4" />"
									name="jssqsj4" id="jssqsj4" />
							</td>
							--%>
						</tr>							
						<thead>
							<tr>
								<td height="25" colspan="2" align="center">
									<button class="button2"
										onclick="checkNull()">
										保存
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				
				<logic:notEmpty name="message">
					<logic:equal name="message" value="ok">
						<script>alert("保存成功!")</script>
					</logic:equal>
					<logic:equal name="message" value="no">
						<script>alert("保存失败!")</script>
					</logic:equal>
				</logic:notEmpty>
				
			</html:form>
		</center>
	</body>
</html>
