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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function SaveUpdate(){
		   if($('ydrs').value==''||$('sdrs').value==''){
		   		alert('带*的选项不能为空！');
		   		return false;
		   }  
		   var pk = $('pk').value;
		   document.forms[0].action = "/xgxt/cqtjgl.do?method=cqtjUpdate&doType=save&pk="+pk;
		   document.forms[0].submit();
		}		
		function toNumber(obj){
			obj.value = obj.value.replace(/[^\d]/g,'');
		}

	</script>
</head>
<body>

	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：日常事务 - 出勤统计维护 - 出勤统计修改
		</div>
	</div>
	<html:form action="/cqtjgl.do?method=cqtjAdd" method="post">
		<input type="hidden" name="pk" id="pk"
					value="${pk }" />
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right">
						学年：
				</td>
				<td align="left">
					<bean:write name="rs" property="xn"/>
				</td>
				<td align="right">
						学期：
				</td>
				<td align="left">
					<bean:write name="rs" property="xqmc"/>
				</td>

			</tr>
			<tr>
				<td align="right" width="15%">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left" width="35%">
					<bean:write name="rs" property="xymc"/>
						
				</td>
				<td align="right" width="15%">
					检查时间：
				</td>
				<td align="left" width="35%">
					<bean:write name="rs" property="jcsj"/>
				</td>
			</tr>
			<tr>
				<td align="right">
						<font color="red">*</font>应到人数：
				</td>
				<td align="left">
					<html:text name="rs" property="ydrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>
				<td align="right">
						<font color="red">*</font>实到人数：
				</td>
				<td align="left">
					<html:text name="rs" property="sdrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>

			</tr>
			<tr>
				<td align="right">
						请假人数：
				</td>
				<td align="left">
					<html:text name="rs" property="qjrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>
				<td align="right">
						旷课人数：
				</td>
				<td align="left">
					<html:text name="rs" property="kkrs" onkeyup="toNumber(this)" maxlength="8"></html:text>
				</td>
			</tr>
			
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="SaveUpdate()" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="Close()" id="buttonSave">
					关 闭
				</button>
		</div>

	</html:form>
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('操作成功！');
			 window.dialogArguments.document.getElementById('search_go').onclick();
			 window.close();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			 alert('操作失败！');
		</script>
	</logic:equal>
</body>
</html:html>
