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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<base target="_self">
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		
	<script type="text/javascript">
	function saveBl(){
		var dybl = $("dybl").value;
		var zybl = $("zybl").value;
		if((parseInt(dybl) + parseInt(zybl)) > 100){
			alert("比例相加不能超过100%，请确认！！！");
			return false;
		}
		refreshForm('/xgxt/zjlgPjpy.do?method=zhszcpSz&type=save&doType='+$('type').value);
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/chgPass" method="post">
			<div class="title">
				<div class="title_img">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
				<input type="hidden" name="type" id="type" value="<bean:write name="type" />"/>
				<table width="100%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="2">
								比例设置
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="50%">
							智育成绩所占比例：
						</td>
						<td align="left" width="50%">
							<html:text name="rs" property="zybl" style="width:30px"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
					<tr>
						<td align="right" width="">
							德育成绩所占比例：
						</td>
						<td align="left" width="">
							<html:text name="rs" property="dybl" style="width:30px" 
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>%
						</td>
					</tr>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="2">
						<button type="button" class="button2"
							onclick="saveBl();"
							style="width:80px">
							保 存
						</button>
						</td>
					</tr>
				</table>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				Close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
