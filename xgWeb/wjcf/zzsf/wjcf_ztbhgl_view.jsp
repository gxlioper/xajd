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
		function chkValue(doType)
		{	
			var zc=document.getElementById("zc").value;
			var jlr=document.getElementById("jlr").value;
			var rq=document.getElementById("rq").value;
			if (zc==""||jlr==""||rq==""){
				alert("红色*号信息为必填，请再次填写并确认！");
			}else{
				refreshForm('wjcf_ZtBhGl_oper.do?doType='+doType);
			}
		}
		function chkNum()
		{
			if ((event.keyCode >= 48 && event.keyCode <= 57))
			{
				event.returnValue = true;
			}
			else
				event.returnValue = false;
		}
		function getRqVal()
		{
			var rq=document.getElementById("rq").value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById("rq").value=rq;
			}
		}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/wjcf_ZtBhGl_oper" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"><logic:equal value="11049" name="xxdm">
						
					违纪处理 - 数据维护 - 主题班会
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
					
					违纪处分 - 数据维护 - 处分后表现
					</logic:notEqual></span>
				</div>
			</div>
				
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								<logic:equal value="11049" name="xxdm">
						
					主题班会记录
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
					
					处分后表现会议记录
					</logic:notEqual>
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							学号：
						</td>
						<td align="left">
							<input type="text" readonly="true" id="xh" name="xh" value="<bean:write name="rs" property="xh"/>">
						</td>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<input type="text" readonly="true" id="nj" name="nj" value="<bean:write name="rs" property="nj"/>">
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<input type="text" readonly="true" id="xm" name="xm" value="<bean:write name="rs" property="xm"/>">
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<input type="text" readonly="true" id="xymc" name="xymc" value="<bean:write name="rs" property="xymc"/>">
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<input type="text" readonly="true" id="zymc" name="zymc" value="<bean:write name="rs" property="zymc"/>">
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<input type="text" readonly="true" id="bjmc" name="bjmc" value="<bean:write name="rs" property="bjmc"/>"> 
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>主持人：
						</td>
						<td align="left">
							<input type="text" name="zc" id="zc" maxlength="15" value="<bean:write name="rs" property="zc"/>">
						</td>
						<td align="right">
							出席人数：
						</td>
						<td align="left">
							<input type="text" id="cxrs" name="cxrs" maxlength="4" value="<bean:write name="rs" property="cxrs"/>" onkeypress="chkNum()">
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>记录人：
						</td>
						<td align="left">
							<input type="text" id="jlr" name="jlr" maxlength="15" value="<bean:write name="rs" property="jlr"/>">
						</td>
						<td align="right">
							<font color="red">*</font>日期：
						</td>
						<td align="left">
							<input type="text" readonly="readonly" id="rq" name="rq" value="<bean:write name="rs" property="rq"/>">
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							出席人员签到：
						</td>
						<td colspan="3">
								<textarea rows="3" cols="" style="width: 99%" id="cxryqd" name="cxryqd"><bean:write name="rs" property="cxryqd"/></textarea>
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							会议记录：
						</td>
						<td colspan="3">
							<textarea rows="10" cols="" style="width: 99%" id="hyjl" name="hyjl"><bean:write name="rs" property="hyjl"/></textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
				<button type="button" class="button2"
						onclick="chkValue('modi')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
		</html:form>
				<logic:equal value="ok" name="done">
<script language="javascript">
alert("操作成功！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script language="javascript" >
  alert("操作失败！");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
	</body>
</html>
