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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript"> 
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<html:form action="/jhzy_gygl" method="post">
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name="userName" scope="session"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��Ǽ��������ҹ��� - �����ѯ
				</div>
			</div>

			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							ѧ�ţ�
							<bean:write name="userName" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							������
							<bean:write name="userNameReal" scope="session" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							�����������
							<bean:write name="numCout" scope="request" />
						</td>
					</tr>
				</thead>
			</table>
			<div id="result">
				<div class="searchcontent">
					<logic:equal value="0" name="numCout">
						<br />
						<br />
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:equal>
					<%--						<logic:notEqual value="0" name="numCout">--%>
					<%--							<div style="overflow-x:auto;overflow-y:auto;width:800px;">--%>
					<logic:notEmpty name="rsBjqsz">
						<fieldset>
							<legend>
								�Ǽ��������ң������¼����
								<bean:write name="rsBjqszNum" scope="request" />
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="">
                                         <td>
											ѧ��
										</td><td>
											ѧ��
										</td>
										<td>
											ѧ��
										</td>
										<td>
											����
										</td>
										<td>
											�༶
										</td>
										<td>
											����Ա���
										</td>
										<td>
											<bean:message key="lable.xsgzyxpzxy" />���
										</td>
										<td>
											ѧУ���
										</td>

									</tr>
								</thead>
								<logic:iterate name="rsBjqsz" id="s">
									<tr
										style="background-color:                                
                                        ">
										<logic:iterate id="v" name="s" offset="0">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
<%--					<logic:notEmpty name="rsEsjcz">--%>
<%--						<fieldset>--%>
<%--							<legend>--%>
<%--								��ʮ�Ѳ㳤�������¼����--%>
<%--								<bean:write name="rsEsjczNum" scope="request" />--%>
<%--							</legend>--%>
<%--							<table width="99%" id="rsTable" class="tbstyle">--%>
<%--								<thead>--%>
<%--									<tr align="center" style="">--%>
<%----%>
<%--										<td>--%>
<%--											ѧ��--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											����--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											�༶--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											����Ա���--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											<bean:message key="lable.xsgzyxpzxy" />���--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											ѧУ���--%>
<%--										</td>--%>
<%----%>
<%--									</tr>--%>
<%--								</thead>--%>
<%--								<logic:iterate name="rsEsjcz" id="s">--%>
<%--									<tr--%>
<%--										style="background-color:--%>
<%--                                        <logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--                                        <bean:write name="v"/>--%>
<%--                                        </logic:iterate>--%>
<%--                                        ">--%>
<%--										<logic:iterate id="v" name="s" offset="1">--%>
<%--											<td>--%>
<%--												<bean:write name="v" />--%>
<%--											</td>--%>
<%--										</logic:iterate>--%>
<%--									</tr>--%>
<%--								</logic:iterate>--%>
<%--							</table>--%>
<%--						</fieldset>--%>
<%--					</logic:notEmpty>					--%>
<%--					<logic:notEmpty name="rsWjlz">--%>
<%--						<fieldset>--%>
<%--							<legend>--%>
<%--								���¥���������¼����--%>
<%--								<bean:write name="rsWjlzNum" scope="request" />--%>
<%--							</legend>--%>
<%--							<table width="99%" id="rsTable" class="tbstyle">--%>
<%--								<thead>--%>
<%--									<tr align="center" style="">--%>
<%----%>
<%--										<td>--%>
<%--											ѧ��--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											����--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											�༶--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											����Ա���--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											<bean:message key="lable.xsgzyxpzxy" />���--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											ѧУ���--%>
<%--										</td>--%>
<%----%>
<%--									</tr>--%>
<%--								</thead>--%>
<%--								<logic:iterate name="rsWjlz" id="s">--%>
<%--									<tr--%>
<%--										style="background-color:--%>
<%--                                        <logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--                                        <bean:write name="v"/>--%>
<%--                                        </logic:iterate>--%>
<%--                                        ">--%>
<%--										<logic:iterate id="v" name="s" offset="1">--%>
<%--											<td>--%>
<%--												<bean:write name="v" />--%>
<%--											</td>--%>
<%--										</logic:iterate>--%>
<%--									</tr>--%>
<%--								</logic:iterate>--%>
<%--							</table>--%>
<%--						</fieldset>--%>
<%--					</logic:notEmpty>					--%>
				</div>
			</div>

		</html:form>
	</body>
</html>
