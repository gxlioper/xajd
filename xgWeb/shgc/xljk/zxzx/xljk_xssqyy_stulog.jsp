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
<base target="_self" />
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
	</head>
	<script>
		function yanzheng()
		{
			var xh=document.all["xh"].value;
			var xsmm=document.all["xsmm"].value;
			if(xh=="")
			{
				alert("学号不能空!");
				document.all["xh"].focus();
				return false;
			}
			else if(xsmm.value==""){
				alert("密码不能为空!");
				document.all["xsmm"].focus();
				return false;
			}
			else 
			{
				var zxszy_xnid = document.all["zxszy_xnid"].value;
				document.all["yanzheng_flag"].value="yes";
				refreshForm('/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=student_check&zxszy_xnid='+zxszy_xnid);
			}
		}
		
		function enter()
		{
			//document.forms[0].action='/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Apply_Table';
			//document.forms[0].su
			//window.dialogArguments.location='/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Apply_Table';
			//window.close();
			var xh=document.all["xh"].value;
			var zxszy_xnid=document.all["zxszy_xnid"].value;
			window.dialogArguments.location='/xgxt/xljk_xssqyy_zysq.do?act=xljk_xssqyy_zysq&doType=Apply_Table&xh='+xh+'&zxszy_xnid='+zxszy_xnid;
			window.close();
		}
	</script>
	<body>
	<html:form action="/xljk_xssqyy_zysq" >
	        <div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：心理健康－学生申请预约－学生验证登陆
				</div>
			</div>
	<input type="hidden" id="zxszy_xnid" name="zxszy_xnid"
				value="<bean:write name="zxszy_xnid" scope="request"/>" />
	<input type="hidden" id="yanzheng_flag" name="yanzheng_flag" value="no" />
		<p align="center">
			<strong style="font-size:15px">学生申请认证：</strong>	
		<table class="tbstyle" style="width:100%;" align="center" id="tab" >
			<tr align="center">
				<td  height="20%" align="right">
					请输入学号：&nbsp;&nbsp;&nbsp;
				</td>
				<td  height="20%" align="left">
						<input type="text" name="xh" size="15" value="<bean:write name="xh" scope="request"/>">
				</td>
			</tr>
			<tr align="center">
				<td height="20%" align="right">
					请输入学生密码：
				</td>
				<td  height="20%" align="left">
						<input type="password" name="xsmm" size="15" value="<bean:write name="xsmm" scope="request"/>" >
				</td>
			</tr>	
			<tr>
				<br/>
				<br/>
			</tr>
			<tr>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<button class="button2" style="width:80px"  id="search_go" 
									onclick="yanzheng()">
						确 定
					</button>
				</td>
			</tr>		
		</table>
	</html:form>
	<logic:notEmpty name="message">
	
					<logic:equal name="message" value="log fail">
						<script>alert("用户名密码有误，请重新检查输入！")</script>
					</logic:equal>
					<logic:equal name="message" value="true">
						<script>
						alert("验证通过，请填写申请表！");
						enter();
						</script>
					</logic:equal>
					<logic:equal name="message" value="date error">
						<script>alert("批量日期有误!")</script>
					</logic:equal>
	</logic:notEmpty>
</body>
</html>
