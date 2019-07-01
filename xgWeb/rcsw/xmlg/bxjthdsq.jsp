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


	<title><bean:message key="lable.title" /></title>
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
		function SaveSq(){
		   var zzdw = document.getElementById('zzdw').value;
		   var hdnr = document.getElementById('hdnr').value;
		   var aqcs = document.getElementById('aqcs').value;
		   if(zzdw == ''){
		   		alert('组织单位不能为空！');
		   	   	return false;
		   }
		   if(hdnr.length > 1000){
		   		alert('活动内容不能大于1000字！');
		   	   	return false;
		   }
		   if(aqcs.length > 400){
		   		alert('安全措施不能大于400字！');
		   	   	return false;
		   }
		   
		   document.forms[0].action = "/xgxt/bxjthdgl.do?method=bxjthdglsq&doType=save";
		   document.forms[0].submit();
		}	
		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		
		function toPrint(){
			document.forms[0].action = "/xgxt/bxjthdgl.do?method=bxjthdPrint&lb=bx";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>
<body>
	<logic:equal value="no" name="sfksq">
		<br>
		<br>
		<br>
		<p align="center">
			<font color="red" size="2">该页面只允许学生访问！</font>
		</p>
	</logic:equal>
	<logic:notEqual value="no" name="sfksq">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：日常事务 - 系、班集体外出活动管理 - 活动申请
			</div>
		</div>
		<html:form action="/bxjthdgl" method="post">
			<table class="tbstyle" width="100%">
				<tr>
					<td align="right" width="15%">
						<font color="red">*</font>组织单位：
					</td>
					<td align="left" width="35%">
						<html:text property="zzdw"></html:text>
					</td>
					<td align="right" width="15%">
						负责人：
					</td>
					<td align="left" width="35%">
						<html:text property="fzr"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						负责人电话：
					</td>
					<td align="left">
						<html:text property="fzrdh"></html:text>
					</td>
					<td align="right">
						带队教师：
					</td>
					<td align="left">
						<html:text property="ddjs"></html:text>
					</td>

				</tr>
				<tr>
					<td align="right">
						带队教师电话：
					</td>
					<td align="left">
						<html:text property="ddjsdh"></html:text>
					</td>
					<td align="right">
						出行时间：
					</td>
					<td align="left">
						<html:text property="cxsj" styleId="cxsj" readonly="true"
							onclick="this.value='';return showCalendar('cxsj','y-mm-dd');"
							onblur="getRqVal('cxsj')"></html:text>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							活动内容
							<br>
							<font color="red"><限1000字内> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea  property="hdnr" rows="8" styleId="hdnr"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							安全措施
							<br>
							<font color="red"><限400字内> </font>
						</div>
					</td>
					<td colspan="3">
						<html:textarea property="aqcs" rows="8" styleId="aqcs"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="SaveSq()" id="buttonSave">
					提交申请
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrint()" id="buttonSave">
					打 印
				</button>
			</div>
		</html:form>
	
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('操作成功！');
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			    alert('操作失败！');
			  </script>
	</logic:equal>
	</logic:notEqual>
</body>
</html:html>
