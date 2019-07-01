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
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>

	<script language="javascript">
	function jyglByqxUpdate(){
	var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;
	 
	 
	 if(byqx==""){
	 alert("毕业去向必须填写！");
	 return false;
	 }
	 if(lxdz==""){
	 alert("联系地址必须填写！");
	 return false;
	 }
	 if(yzbm==""){
	 alert("邮政编码必须填写！");
	 return false;
	 }
	 if(!isNumber(yzbm)){
	 alert("邮政编码应为数字！")
	 return false;
	 }
	 if(yzbm!=""&&yzbm.length!=6){
	 alert("邮政编码长度不合要求！");
	 return false;
	 }
	 
	 
	 if(lxdh!=""&&!isNumber(lxdh)){
	 alert("联系电话应为数字！")
	 return false;
	 }
	 if(lxdh!=""&&lxdh.length<7){
	 alert("联系电话长度不合要求！");
	 return false;
	 }
	 if(yddh!=""&&!isNumber(yddh)){
	 alert("移动电话应为数字！")
	 return false;
	 }
	 if(yddh!=""&&yddh.length!=11){
	 alert("手机号码长度不合要求！");
	 return false;
	 } 
	 if(lxdh==""&&yddh==""){
	 alert("请至少填写一个联系电话！");
	 return false;
	 }
	   
		 		document.forms[0].action = "/xgxt/jyglByqxUpdate.do?doType=update&act=update";
		 		document.forms[0].submit();
    }
   
	
	function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
    
   
	</script>
	<body>
		<html:form action="/data_search" method="post">
			<fieldset>
				<legend>
					毕业去向信息
				</legend>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr>
							<td align="left" colspan="4">
								&nbsp;&nbsp;毕业年度:
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								&nbsp;&nbsp;提交时间：
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right" width="15%">
							学号：
						</td>
						<td align="left" width="35%">
							<html:text name="rs" property="xsxh" readonly="true"
								style="width=100%" />
						</td>
						<td align="right" width="15%">
							姓名：
						</td>
						<td align="left" width="35%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							生源地区：
						</td>
						<td align="left">
							<bean:write name="rs" property="sydq" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>毕业去向：
						</td>
						<td align="left">
							<html:select name="rs" property="byqx" styleId="byqx"
								style="width=100%">
								<html:options collection="byqxList" property="byqx"
									labelProperty="byqx" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>联系地址：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width=100%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>联系电话：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width=100%" />
						</td>
						<td align="right">
							<font color="red">*</font>邮政编码：
						</td>
						<td align="left">
							<html:text name="rs" property="yzbm" style="width=100%" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>移动电话：
						</td>
						<td align="left">
							<html:text name="rs" property="yddh" style="width=100%" />
						</td>
						<td align="right">
							<font color="red">*</font>电子邮箱：
						</td>
						<td align="left">
							<html:text name="rs" property="email" style="width=100%" />
						</td>
					</tr>

				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="jyglByqxUpdate()">
						提 交 修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
						关 闭 窗 口
					</button>
				</div>
			</fieldset>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
