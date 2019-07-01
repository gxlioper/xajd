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
		<script type='text/javascript' src='/xgxt/dwr/interface/getXljkSjm.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		
	</head>
	
	<script type="text/javascript">
		function yanzheng(){
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
			} else  {
				var sjlsh = document.all["sjlsh"].value;
				document.all["yanzheng_flag"].value="yes";
				refreshForm('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=stu_check&sjlsh='+sjlsh);
			}
		}
		
		function loginNow(){
			test();
		}
		
		function test() {
			var xh=document.all["xh"].value;
			var sjlsh=document.getElementById("sjlsh").value;
			if(sjlsh != null && sjlsh != ""){
			    getXljkSjm.get_sjmcBy_sjlsh(sjlsh,function(data){
			    	if(data == "个性自评试卷" || data == "心理健康自评试卷"){
						refreshForm('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=create_testPaper&sjlsh='+sjlsh+'&xh='+xh+'&stflag=1');	
					}else{
						refreshForm('/xgxt/xljk_xlcs_zxpc.do?act=zxpc&doType=create_testPaper&sjlsh='+sjlsh+'&xh='+xh);
					}	
			    });

			}	
		}
	</script>
	
	<body>
	<html:form action="/xljk_xlcs_zxpc" >
	<input type="hidden" id="sjlsh" name="sjlsh"
				value="<bean:write name="sjlsh" scope="request"/>" />
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
						<input type="password" name="xsmm" size="15" value="<bean:write name="xsmm" scope="request"/>" 
							onkeydown="if(event.keyCode==13)search_go.click();">
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
						alert("验证通过，开始测试！");
						loginNow();
						</script>
					</logic:equal>
					<logic:equal name="message" value="date error">
						<script>alert("批量日期有误!")</script>
					</logic:equal>
	</logic:notEmpty>
</body>

</html>
