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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>

	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>

	<script type="text/javascript">
	  function jyxybldjxysh(){
         var xyshflag = $("xyshflag").value;
         var xxshflag = $("xxshflag").value;
	     var xysh = $("xysh").value;
	     var pkValue = $("pk").value;
	 
	 
	     if(xyshflag!="未审核"){
	        alert("审核已通过，你无需重复审核！");
	        return false;
	     }
	     if(xxshflag!="未审核"){
	        alert("学校用户已完成审核，无需再次审核！");
	        return false;
	     }
	     
	     
	       BatAlert.showTips('正在审核，请稍等待...');
	       document.forms[0].action = "jyxyBlsqViewMore.do?act=view&doType=xysh&doType2=xysh&xysh="+xysh+"&pkValue="+pkValue;
	       document.forms[0].submit();
	  
	  }
	
	
	</script>


	<body>
		<html:form action="/jyxyBlsqViewMore" method="post">
			<input type="hidden" name="pk" id="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<fieldset>
				<legend>
					协议书补领<bean:message key="lable.xsgzyxpzxy" />审核
				</legend>
				<table width="98%" id="rsT" class="tbstyle">
					<tr style="height:22px">
						<td align="right" width="15%">
							姓名:
						</td>
						<td align="left" width="25%">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right" width="10%">
							性别：
						</td>
						<td align="left" width="15%">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right" width="13%">
							学历：
						</td>
						<td align="left" width="25%">
							<bean:write name="rs" property="xl" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学号:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xh" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							毕业时间:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="bysj" />
						</td>
						<td align="right">
							专业：
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							原协议书编号:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="jyxybh" />
						</td>
						<td align="right">
							新协议书编号:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="newjyxybh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							申请时间
						</td>
						<td align="left" colspan="5">
							<bean:write name="rs" property="sqsj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核:
						</td>
						<td align="left" colspan="2">
							<input type="hidden" name="xyshflag"
								value="<bean:write name="rs" property="xysh" />">
							<html:select name="rs" property="xysh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="已通过√">已通过√</html:option>
								<html:option value="未通过X">未通过X</html:option>
							</html:select>
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核时间:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xyshsj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							学校审核:
						</td>
						<td align="left" colspan="2">
							<input type="hidden" name="xxshflag"
								value="<bean:write name="rs" property="xxsh" />">
							<bean:write name="rs" property="xxsh" />
						</td>
						<td align="right">
							学校审核时间:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xxshsj" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							申请报告：
						</td>
						<td align="left" colspan="5">
							<html:textarea name="rs" property="sqbg" rows="15"
								style="width:100%" readonly="true" />
						</td>
					</tr>
					<tr align="center">
						<td colspan="6">
							<button class="button2" onclick="jyxybldjxysh();">
								提交审核
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
								关闭
							</button>
						</td>
					</tr>
				</table>
			</fieldset>
		</html:form>
		<logic:notEmpty name="xysh">
			<logic:equal name="xysh" value="ok">
				<script>
                      alert("<bean:message key="lable.xsgzyxpzxy" />审核成功!");
                    </script>
			</logic:equal>
			<logic:equal name="xysh" value="no">
				<script>
                      alert("<bean:message key="lable.xsgzyxpzxy" />审核失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

