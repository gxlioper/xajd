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
		<title><bean:message key="lable.title" />
		</title>
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
	
	
	function savebyqx(){
	 var xsxh = document.getElementById("xsxh").value;
	 var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;
	 
	 
	 if(xsxh==""){
	 alert("学号必须填写！")
	 return false;
	 }
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
		 		document.forms[0].action = "/xgxt/savebyqx.do?doType=save";
		 		document.forms[0].submit();
	 }
	
	function returntobegin(){
	            document.forms[0].action = "/xgxt/savebyqx.do";
	            document.forms[0].submit();
	}
	
	
	
	
	function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
	</script>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：就业管理 - 就业协议方案 - 毕业去向录入
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>


				<html:hidden name="rs" property="nd" />
				<html:hidden name="rs" property="xslb" />

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:25px">
							<td colspan="4" align="center">
								<b>毕业去向录入</b>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								毕业年度:
								<html:text property="bynd" name="rs" readonly="true"
									style="width:35px" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>学号:
							</td>
							<td align="left" width="35%">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/byqxTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>学号：
							</td>
							<td align="left" width="35%">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
						<td align="right" width="15%">
							姓名：
						</td>
						<td align="left" width="35%">
							<html:text name="rs" property="name" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name="rs" property="xb" readonly="true"
								style="width:210px" />
						</td>
						<td align="right">
							生源地区：
						</td>
						<td align="left">
							<html:text name="rs" property="sydq" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>毕业去向：
						</td>
						<td align="left">
							<html:select name="rs" property="byqx" styleId="byqx"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="byqxList" property="byqx"
									labelProperty="byqx" />
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>联系地址：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"
								maxlength="40" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width:210px"
								maxlength="12" />
						</td>
						<td align="right">
							<font color="red">*</font>邮政编码：
						</td>
						<td align="left">
							<html:text name="rs" property="yzbm" style="width:210px"
								maxlength="6" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							移动电话：
						</td>
						<td align="left">
							<html:text name="rs" property="yddh" style="width:210px"
								maxlength="11" />
						</td>
						<td align="right">
							电子邮箱：
						</td>
						<td align="left">
							<html:text name="rs" property="email" style="width:210px"
								maxlength="20" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savebyqx()" style="width:80px">
						提 交
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" type="reset" onclick="returntobegin()"
						style="width:80px">
						重 置
					</button>
				</div>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
    alert("提交成功！");
    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    alert("提交失败！请检查是否重复提交！");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
