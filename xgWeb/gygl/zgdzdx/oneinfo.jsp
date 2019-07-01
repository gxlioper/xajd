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

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/zgdzdx_Gygl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<div id="main" style="heigth:100px;">
				<div id="search_m" style="heigth:100px;">
					<div id="result">
						<div class="searchcontent">
							<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									����û����ס��
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<fieldset>
									<legend>
										������ס��Ϣ
									</legend>
									<table width="99%" class="tbstyle">
										<tr>
											<td align="right">
												ѧ�ţ�
											</td>
											<td align="left">
												<html:text name='rs' property="xh" style="width: 120px"
													 readonly="true"/>
											</td>
											<td align="right">
												������
											</td>
											<td align="left">
												<html:text name='rs' property="xm" style="width: 120px"
													 readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="right">
												¥�����ƣ�
											</td>
											<td align="left">
												<html:text name='rs' property="ldmc" style="width: 120px"
													 readonly="true"/>
											</td>
											<td align="right">
												¥�㣺
											</td>
											<td align="left">
												<html:text name='rs' property="cs" style="width: 120px"
													readonly="true" />
											</td>
										</tr>
										<tr>
											<td align="right">
												���Һţ�
											</td>
											<td align="left">
												<html:text name='rs' property="qsh" style="width: 120px"
													readonly="true" />
											</td>
											
											<td align="right">
												���ҵ绰��													
											</td>
												<td align="left">
													<html:text name='rs' property="qsdh" style="width: 120px"
													readonly="true" />
												</td>										
										 </tr>
										 <tr>
											<td align="right">
<%--												�Ƿ���޷��䣺--%>
											</td>
											<td align="left">
<%--												<html:text name='rs' property="sffqfj" style="width: 120px"--%>
<%--													readonly="true" />--%>
											</td>
											
											<td align="right">
												�շѱ�׼��													
											</td>
												<td align="left">
													<html:text name='rs' property="sfbz" style="width: 120px"
													readonly="true" />
												</td>										
										 </tr>
										 <tr>
											<td align="right">
												��סʱ�䣺
											</td>
											<td align="left">
												<html:text name='rs' property="rzrq" style="width: 120px"
													readonly="true" />
											</td>
											
											<td align="right">
												�˷�ʱ�䣺													
											</td>
												<td align="left">
													<html:text name='rs' property="jzrq" style="width: 120px"
													readonly="true" />
												</td>										
										 </tr>
										
									</table>
								</fieldset>
								<div class="buttontool">								
									<button class="button2" onclick="Close();return false;" style="width:80px"
										id="buttonClose">
										�� ��
									</button>
								</div>
							</logic:notEmpty>
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
