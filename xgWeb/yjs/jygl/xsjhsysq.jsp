<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" media="all" href="style/calendar.css" title="win2k-cold-1" />
    <script type="text/javascript" src="style/calendar.js"></script>
    <script type="text/javascript" src="style/calendar-zh.js"></script>
    <script type="text/javascript" src="style/calendar-setup.js"></script>
	<script language="JavaScript" src="style/dmwh.js"></script>
	<script language="JavaScript" src="style/xjgl.js"></script>
	<script language="JavaScript" src="style/jhgl.js"></script>
	<script language="javascript">
		function checkForm(act){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("请输入学号！");
				return false;
			}
			var sqr = document.forms(0).sqr.value;
			if(sqr==""){
				alert("请输入申请人姓名！");
				return false;
			}
			var sqxx = document.forms(0).sqxx.value;
			if(sqxx==""){
				alert("请输入申请信息！");
				return false;
			}
			if(act=="add"){
				changTab('/yjsjwgl/xsjhsysq.do?act=add');
			}else{
				changTab('/yjsjwgl/xsjhsysq.do?act=modify');
			}
		}
	</script>
</head>
 <%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
	<html:form method="POST" action="xsjhsysq" enctype="multipart/form-data">		
		<div id="title">
			<div class="titiel_img"></div>
			当前所在位置：学工管理-->学生计划生育申请
		</div>
		<div>
			<fieldset>
			<legend>
					计划生育申请表
					<logic:notEqual value="true" name="canadd">
					申请时间:${map.sqsj}
					</logic:notEqual>
			</legend>
			<table width="99%">
				<tr>
					<td align="center" width="120px">
						<font color="red">*</font>学号
					</td>
					<logic:notEqual value="true" name="canadd">
						<td><input type="text" disabled="disabled" name="xh" value="${map.xh}"/></td>
					</logic:notEqual>
					<logic:equal value="true" name="canadd">
						<td><input type="text" name="xh" value="${map.xh}"/></td>
					</logic:equal>
					<td align="center" width="120px">
						<font color="red">*</font>申请人
					</td>
					<td><input type="text" name="sqr" value="${map.sqr}"/></td>
				</tr>
				<tr height="150px">
					<td align="center" width="120px">
						<font color="red">*</font>申请信息
					</td>
					<td colspan="3">
						<html:textarea property="sqxx" value="${map.sqxx}" style="width:550px;height:140px"></html:textarea> 
					</td>
				</tr>
			</table>
			</fieldset>
			<div id="button">
				<logic:equal value="true" name="canadd">
				<button class="button2" onclick="checkForm('add');">
					申  请
				</button>
				</logic:equal>
				<logic:notEqual value="true" name="canadd">
				<button class="button2" onclick="checkForm('modify');">
					修&nbsp&nbsp&nbsp改
				</button>
				<button class="button2" 
					onclick="changTab('/yjsjwgl/xsjhsysq.do?act=del')">
					取消申请
				</button>
				</logic:notEqual>
			</div>
			<logic:notEqual value="true" name="canadd">	
			<fieldset>
			<legend>
					审核情况
			</legend>
			<table>
				<tr>
					<td align="center" width="10%">
						导师审核状态
					</td>
					<td width="10%" align="center">${map.dsshzt}</td>
					<td align="center" width="10%">
						导师审核时间
					</td>
					<td width="10%" align="center">${map.dsshsj}</td>
				</tr>
				<tr>
					<td align="center">
						<bean:message key="lable.xb" />审核状态
					</td>
					<td align="center">${map.xyshzt}</td>
					<td align="center">
						<bean:message key="lable.xb" />审核时间
					</td>
					<td align="center">${map.xyshsj}</td>
				</tr>
				<tr>
					<td align="center">
						部门审核状态
					</td>
					<td align="center">${map.bmshzt}</td>
					<td align="center">
						部门审核时间
					</td>
					<td align="center">${map.bmshsj}</td>
				</tr>
				<tr>
					<td align="center">
						医务室审核状态
					</td>
					<td align="center">${map.ywsshzt}</td>
					<td align="center">
						医务室审核时间
					</td>
					<td align="center">${map.ywsshsj}</td>
				</tr>
			</table>
			</fieldset>
			</logic:notEqual>
		</div>
		<logic:equal name="result" value="view">
			<script>
		    	alert("<bean:write name='jyglForm' property='message'/>");
		  	</script>
		</logic:equal>
	</html:form>
</body>

</html:html>

