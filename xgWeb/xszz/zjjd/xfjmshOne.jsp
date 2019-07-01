<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.Iterator"/>
<jsp:directive.page import="java.util.ArrayList"/>

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
		<meta name="Copyright" content="������� zfsoft" />
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
			if(("δ���" != xxsh ) && (userType != "admin")){
				alert("<bean:message key="lable.xsgzyxpzxy" />����ˣ��������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("ϵ���������ܴ���500���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���500���ַ�");
	          		 return false;
	       		 }
	       	}
			 refreshForm('/xgxt/zjjdzyjsxy_xszz.do?method=xfjmshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ѧ�Ѽ������ - �������
				</div>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
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
							ѧ�Ѽ���ȼ�
						</div>
					</td>
					<td width="30%">
						<bean:write name='rs' property="xfjmmc" />
					</td>
					<td width="16%">
						<div align="center">
							ѧ�Ѽ�����
						</div>
					</td>
					<td width="34%">
						<bean:write name='rs' property="xfjmje" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" readonly="true">
						<input type="hidden" id="nd" name="nd"
							value="<bean:write name='rs' property="nd" />" readonly="true">
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							רҵ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<td>
						<div align="center">
							�༶����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="csrq"/>
					</td>
					<td>
						<div align="center">
							��ѧ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="rxny"/>
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
							���
						</div>
					</td>
					<td>
						<bean:write name="rs" property="nd"/>
					</td>
					<td>
						<div align="center">
							�Ƿ�������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfkns"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
					<logic:equal name="userType" value="xy">
						<td>
							<div align="center">
								ϵ���
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
								ϵ���
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
								<bean:message key="lable.xsgzyxpzxy" />���
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
								ϵ������
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
								ϵ������
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
								<bean:message key="lable.xsgzyxpzxy" />������
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
							��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;¼
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								��ѧ���¼
							</div>
						</td>
						<td colspan="3">
							<%
							ArrayList xsjxjList = (ArrayList)request.getAttribute("xsJxjjlList");
							if(xsjxjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
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
								�ڹ���ѧ��¼
							</div>
						</td>
						<td colspan="3">
							<%
							ArrayList qgzxList = (ArrayList)request.getAttribute("xsQgzuCjjlList");
							if(qgzxList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
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
								ѧ��������¼
							</div>
						</td>
						<td colspan="3">
							��ʱ���Ѳ�����<br />
							<%
							ArrayList lsknbzList = (ArrayList)request.getAttribute("zjjdLsknbzList");
							if(lsknbzList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼<br />
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
						 	ѧ�ѻ��ɣ�<br />
							<%
							ArrayList xfhjList = (ArrayList)request.getAttribute("zjjdXfhjList");
							if(xfhjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼<br />
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
						 	ѧ�Ѽ��⣺<br />
							<%
							ArrayList xfjmList = (ArrayList)request.getAttribute("zjjdXfjmList");
							if(xfjmList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼<br />
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
						 	������ѧ��<br />
							<%
							ArrayList gjzxjList = (ArrayList)request.getAttribute("zjjdGjzxjList");
							if(gjzxjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼<br />
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
						 	������־��ѧ��<br />
							<%
							ArrayList gjlzjxjList = (ArrayList)request.getAttribute("zjjdGjLzjxjList");
							if(gjlzjxjList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼<br />
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
						 	������ѧ���<br />
							<%
							ArrayList gjzxdkList = (ArrayList)request.getAttribute("zjjdGjzxdkList");
							if(gjzxdkList.size() == 0){
							%>
							&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼<br />
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
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
