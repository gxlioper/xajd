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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	
	<body>
		<html:form action="/qgzxLogic.do" method="post">
		<input type="hidden" id="pk"  name="pk" value="<bean:write name="pk"/>"/>
		<input type="hidden" id="xn"  name="xn" value="2007-2008"/>
		<input type="hidden" id="nd"  name="nd" value="2008"/>
			<div class="title">			   
				<div class="title_img" id="title_m">
					当前位置：勤工助学 - 参数设置 - 申请时间设置
				</div>
			</div>
			<table align="center" class="tbstyle" width="100%">
			<thead>
			<tr>
				<td colspan="2">
					<center><b>申请时间设置</b></center>
				</td>
			</tr>
			</thead>
			<logic:equal value="single" name="type">
				<tr>
					<td align="right"> 开始时间</td>
					<td>
						<input type="hidden" name="kssqsj" id="kssqsj" value="" />
						<input type="text" readonly style="cursor:hand;width:80px"
							onclick="return showCalendar('kssqsj1','y-mm-dd');"
							value="<bean:write name="kssj1" />" name="kssqsj1" id="kssqsj1" />
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
				</tr>
				<tr>
					<td align="right"> 结束时间</td>
					<td>
						<input type="hidden" name="jssqsj" id="jssqsj" value="" />
						<input type="text" readonly style="cursor:hand;width:80px"
							onclick="return showCalendar('jssqsj1','y-mm-dd');"
							value="<bean:write name="jssj1" />" name="jssqsj1" id="jssqsj1" />
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
				</tr>
				</logic:equal>
				<logic:equal value="batch" name="type">
					<tr>
					<td align="right"> 开始时间</td>
					<td>
						<input type="hidden" name="kssqsj" id="kssqsj" value="" />
						<input type="text" readonly style="cursor:hand;width:80px"
							onclick="return showCalendar('kssqsj1','y-mm-dd');"
							value="" name="kssqsj1" id="kssqsj1" />
							－
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value=""
							name="kssqsj2" id="kssqsj2" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value=""
							name="kssqsj3" id="kssqsj3" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value=""
							name="kssqsj4" id="kssqsj4" />
					</td>					
				</tr>
				<tr>
					<td align="right"> 结束时间</td>
					<td>
						<input type="hidden" name="jssqsj" id="jssqsj" value="" />
						<input type="text" readonly style="cursor:hand;width:80px"
							onclick="return showCalendar('jssqsj1','y-mm-dd');"
							value="" name="jssqsj1" id="jssqsj1" />
							－
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value=""
							name="jssqsj2" id="jssqsj2" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value=""
							name="jssqsj3" id="jssqsj3" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value=""
							name="jssqsj4" id="jssqsj4" />
					</td>					
				</tr>
				</logic:equal>
			</table>	
			<div class="buttontool" align="center">				
					<button type="button" class="button2"
						onclick="saveTrainConf('kssqsj','jssqsj','xn','nd','qgzxLogic.do?method=saveTimeConf')">
						保 存
					</button>
					<button type="button" class="button2"
						onclick="Close();return false;">
						关 闭
					</button>
			</div>
					
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
    					alert("操作成功！");
    					Close();
    					refreshForm('qgzxLogic.do?method=TimeConf');
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
    					alert("操作失败！");
    				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
