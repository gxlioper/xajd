<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:directive.page import="java.util.Iterator"/>

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
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(("未审核" != xxsh ) && (userType != "admin")){
				alert("<bean:message key="lable.xsgzyxpzxy" />已审核，不能再修改审核结果!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("系审核意见不能大于500个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于500个字符");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/zjjdzyjsxy_xszz.do?method=gjlzjxjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/zjjdzyjsxy_xszz.do?method=gjlzjxjshSave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - 国家励志奖学金审核 - 单个审核
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td width="20%">
						<div align="center">
							学号
						</div>
					</td>
					<td width="30%">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" readonly="true">
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rs' property="nd" />" readonly="true">
					</td>
					<td width="16%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="34%">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							班级名称
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							出生日期
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
					<td>
						<div align="center">
							入学年月
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年度
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nd"/>
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding:0;">
						<%@ include file="yhkhOne.jsp"%>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							曾获何种奖励
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="chhzjl"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭户口
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jthk"/>
					</td>
					<td>
						<div align="center">
							家庭人口总数
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrkzs"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							人均月收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rjysr"/>
					</td>
					<td>
						<div align="center">
							家庭月总收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzsr"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							收入来源
						</div>
					</td>
					<td>
						<bean:write name="rs" property="srly"/>
					</td>
					<td>
						<div align="center">
							邮政编码
						</div>
					</td>
					<td>
						<bean:write name="rs" property="yzbm"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭住址
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtzz"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学习成绩
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xxcj"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请理由
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							申请时间
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
					<logic:equal name="userType" value="xy">
						<td>
							<div align="center">
								系审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<td>
							<div align="center">
								系审核
							</div>
						</td>
						<td>
							<bean:write name="rs" property="xysh" />
							<input type="hidden" id="xysh" name="xysh"
								value="<bean:write name='rs' property="xysh" />" />
						</td>
					</logic:equal>
				</tr>
				<logic:equal name="userType" value="admin">
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核
							</div>
						</td>
						<td>
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="xy">
					<tr>
						<td>
							<div align="center">
								系审核意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xyshyj" rows='5'
								style="width:100%" onblur="yzdx(this,'xyshyj')" />
							<input type="hidden" id="xyshyj" name="xyshyj" value="">
							<input type="hidden" id="xxshyj" name="xxshyj"
								value="<bean:write name='rs' property="xxshyj" />" />
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<td>
							<div align="center">
								系审核意见
							</div>
						</td>
						<td colspan="3">
							<bean:write name='rs' property="xyshyj" />
							<input type="hidden" id="xyshyj" name="xyshyj"
								value="<bean:write name='rs' property="xyshyj" />" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xsgzyxpzxy" />审核意见
							</div>
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="xxshyj" rows='5'
								style="width:100%" onblur="yzdx(this,'xxshyj')" />
							<input type="hidden" id="xxshyj" name="xxshyj" value="">
						</td>
					</tr>
				</logic:equal>
					<tr>
						<td colspan="4" align="center">
							个&nbsp;&nbsp;人&nbsp;&nbsp;记&nbsp;&nbsp;录
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								奖学金记录
							</div>
						</td>
						<td colspan="3">
							<%
							ArrayList xsjxjList = (ArrayList)request.getAttribute("xsJxjjlList");
							if(xsjxjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = xsjxjList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								勤工助学记录
							</div>
						</td>
						<td colspan="3">
							<%
							ArrayList qgzxList = (ArrayList)request.getAttribute("xsQgzuCjjlList");
							if(qgzxList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = qgzxList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								学生资助记录
							</div>
						</td>
						<td colspan="3">
							临时困难补助：<br />
							<%
							ArrayList lsknbzList = (ArrayList)request.getAttribute("zjjdLsknbzList");
							if(lsknbzList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录<br />
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = lsknbzList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						 	学费缓缴：<br />
							<%
							ArrayList xfhjList = (ArrayList)request.getAttribute("zjjdXfhjList");
							if(xfhjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录<br />
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = xfhjList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						 	学费减免：<br />
							<%
							ArrayList xfjmList = (ArrayList)request.getAttribute("zjjdXfjmList");
							if(xfjmList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录<br />
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = xfjmList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						 	国家助学金：<br />
							<%
							ArrayList gjzxjList = (ArrayList)request.getAttribute("zjjdGjzxjList");
							if(gjzxjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录<br />
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = gjzxjList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						 	国家励志奖学金：<br />
							<%
							ArrayList gjlzjxjList = (ArrayList)request.getAttribute("zjjdGjLzjxjList");
							if(gjlzjxjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录<br />
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = gjlzjxjList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						 	国家助学贷款：<br />
							<%
							ArrayList gjzxdkList = (ArrayList)request.getAttribute("zjjdGjzxdkList");
							if(gjzxdkList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;无记录<br />
							<%	
								} else {
							%>
							<br />
							<%
								for(Iterator it = gjzxdkList.iterator(); it.hasNext();){
							%>
						 		&nbsp;<%=it.next() %><br />
						 		<%} %>
							 <br />
						 	<%} %>
						</td>
					</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
	</body>
</html>
