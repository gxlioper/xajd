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
		<input type="hidden" id="pkString"  name="pkString" value="<bean:write name="pk"/>"/>
			<div class="title">			   
				<div class="title_img" id="title_m">
					当前位置：勤工助学 - 参数设置 - 考核上报时间设置
				</div>
			</div>
			<table align="center" class="tbstyle" width="100%">
			<thead>
			<tr>
				<td colspan="2">
					<center><b>考核上报时间设置</b></center>
				</td>
			</tr>
			</thead>
				<tr>
					<td align="right"> 开始时间</td>
					<td>
						<input type="hidden" name="kssj" id="kssj" value="" />
						<input type="text" readonly style="cursor:hand;width:80px"
							onclick="return showCalendar('kssj1','y-mm-dd');"
							value="<bean:write name="rs" property="kssj1"/>" name="kssj1" id="kssj1" />
							－
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value="<bean:write name="rs" property="kssj2"/>"
							name="kssj2" id="kssj2" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value="<bean:write name="rs" property="kssj3"/>"
							name="kssj3" id="kssj3" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value="<bean:write name="rs" property="kssj4"/>"
							name="kssj4" id="kssj4" />
					</td>					
				</tr>
				<tr>
					<td align="right"> 结束时间</td>
					<td>
						<input type="hidden" name="jssj" id="jssj" value="" />
						<input type="text" readonly style="cursor:hand;width:80px"
							onclick="return showCalendar('jssj1','y-mm-dd');"
							value="<bean:write name="rs" property="jssj1"/>" name="jssj1" id="jssj1" />
							－
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value="<bean:write name="rs" property="jssj2"/>"
							name="jssj2" id="jssj2" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value="<bean:write name="rs" property="jssj3"/>"
							name="jssj3" id="jssj3" />
							：
						<input type="text" onkeypress="return numInputValue(this,2,event)"
							style="width:20px" value="<bean:write name="rs" property="jssj4"/>"
							name="jssj4" id="jssj4" />
					</td>					
				</tr>				
			</table>	
			<div class="buttontool" align="center">				
					<button type="button" class="button2"
						onclick="saveTrainConf('kssj','jssj','','','qgzxShgc.do?method=saveKhsbsj')">
						保 存
					</button>
					<button type="button" class="button2"
						onclick="Close();return false;">
						关 闭
					</button>
			</div>
					
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
				<logic:empty name="mes">
					<script>
    					alert("操作成功！");
    					Close();
    					window.dialogArguments.document.getElementById('search_go').click();	
					</script>
				</logic:empty>
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}">
					<script>
    					alert(document.getElementById('mes').value);
    					Close();
    					window.dialogArguments.document.getElementById('search_go').click();	
					</script>
				</logic:notEmpty>					
				</logic:equal>
				<logic:equal name="result" value="false">
					<logic:empty name="mes">
					<script>
    					alert("操作失败！");
    					Close();
    					window.dialogArguments.document.getElementById('search_go').click();	
					</script>
				</logic:empty>
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}">
					<script>
    					alert(document.getElementById('mes').value);
    					Close();
    					window.dialogArguments.document.getElementById('search_go').click();	
					</script>
				</logic:notEmpty>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
