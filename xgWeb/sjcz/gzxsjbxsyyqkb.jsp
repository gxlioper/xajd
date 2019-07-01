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
	    function reademp(){
	    	var empisnot = document.getElementById("notemp").value;
	    	if(empisnot == "emp"){
	    		top.document.getElementById('zdgzqk').style.height="65px";
	    	}else{
	    		top.document.getElementById('zdgzqk').style.height="280px";
	    	}
	    }
	</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="reademp();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
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
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xymc-xzb" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="xh-xm-xb-xymc-xzb" />
			
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-xzb" />
				<input type="hidden" id="url" name="url"
					value="/sjcz/gzxsjbqkb.jsp" />
				<fieldset>
					<logic:notEmpty name="rs" property="xh" scope="request">
					<input id="notemp" type="hidden" name="notemp" value="notemp"/>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									基本情况
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly" styleId="xh"/>
							</td>
							<td align="right">
								初步识别：
							</td>
							<td align="left">
								<html:text name='rs' property="cbsb" styleId="xymc" />
							</td>
						</tr>
						<tr>
							<td align="right"> 
								关注级别： 
							</td>
							<td align="left">
								<html:text name='rs' property="gzjb" styleId="ssbh" />
							</td>
						</tr>
						<tr align="left">
							<td align="right"> 
							主要表现：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='zyzzjbx' style="width:99%"
									rows='10' />
							</td>
						</tr>
					</table>
					</logic:notEmpty>
					<logic:empty name="rs" property="xh" scope="request">
			               	不是重点关注学生
							<input id="notemp" type="hidden" name="notemp" value="emp"/>
					</logic:empty>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
