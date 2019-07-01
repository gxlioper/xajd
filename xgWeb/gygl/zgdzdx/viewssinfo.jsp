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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
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
							<logic:empty name="info">
								<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									您还没有入住！
								</p>
								</logic:empty>
							</logic:empty>
							<logic:notEmpty name="info">
								<script type="text/javascript">
									alert('<bean:write name="info"/>');
								</script>
							</logic:notEmpty>
							<logic:notEmpty name="rs">
								<fieldset>
									<legend>
										您的入住信息
									</legend>
									<table width="99%" class="tbstyle">
										<tr>
											<td align="right">
												学号：
											</td>
											<td align="left">
												<html:text name='rs' property="xh" style="width: 120px"
													 readonly="true"/>
											</td>
											<td align="right">
												姓名：
											</td>
											<td align="left">
												<html:text name='rs' property="xm" style="width: 120px"
													 readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="right">
												性别：
											</td>
											<td align="left">
												<html:text name='rs' property="xb" style="width: 120px"
													 readonly="true"/>
											</td>
											<td align="right">
												<bean:message key="lable.xsgzyxpzxy" />名称：
											</td>
											<td align="left">
												<html:text name='rs' property="xymc" style="width: 120px"
													 readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="right">
												专业名称：
											</td>
											<td align="left">
												<html:text name='rs' property="zymc" style="width: 120px"
													 readonly="true"/>
											</td>
											<td align="right">
												班级名称：
											</td>
											<td align="left">
												<html:text name='rs' property="bjmc" style="width: 120px"
													 readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="right">
												楼栋名称：
											</td>
											<td align="left">
												<html:text name='rs' property="ldmc" style="width: 120px"
													styleId="ldmc" readonly="true"/>
											</td>
											<td align="right">
												寝室号：
											</td>
											<td align="left">
												<html:text name='rs' property="qsh" style="width: 120px"
													styleId="qsh" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="right">
												可入住人数：
											</td>
											<td align="left">
												<html:text name='rs' property="krzrs" style="width: 120px"
													styleId="krzrs" readonly="true"/>
											</td>
											<td align="right">
												已入住人数：
											</td>
											<td align="left">
												<html:text name='rs' property="yrzrs" style="width: 120px"
													styleId="yrzrs" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="right">
												寝室电话：
											</td>
											<td align="left">
												<html:text name='rs' property="qsdh" style="width: 120px"
													styleId="qsdh" readonly="true"/>
											</td>
											<td align="right">
												收费标准：
											</td>
											<td align="left">
												<html:text name='rs' property="sfbz" style="width: 120px"
													styleId="sfbz" readonly="true"/>
											</td>
										</tr>
										
										<tr>
											<td align="right">
												分配类型：
											</td>
											<td align="left">
												<html:text name='rs' property="fpbz" style="width: 120px"
													styleId="fpbz" readonly="true"/>
											</td>
											<td align="right">
												是否夫妻房间：
											</td>
											<td align="left">
												<html:text name='rs' property="sffqfj" style="width: 120px"
													styleId="sffqfj" readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="right">
												备注：
											</td>
											<td colspan="3">
												<html:textarea name='rs' property='bz' style="width:95%"
													rows='4' readonly="true"/>
											</td>
										</tr>
										<tr>
											<td align="center" colspan="4">
												<font color="red">该寝室其他学生基本信息如下：</font>
											</td>
										</tr>
										<tr>
											<td align="center" colspan="4">
											<table width="99%" class="tbstyle">
												<thead>
													<tr align="center">
														<logic:iterate id="s" name="formtop">
															<td>${s}</td>
														</logic:iterate>
													</tr>
												</thead>
													<logic:iterate id="s1" name="rs" property="xsxx">
													<tr align="center">
															<logic:iterate id="s2" name="s1">
																<td>${s2}</td>
															</logic:iterate>
													</tr>
													</logic:iterate>
													<logic:empty name="rs" property="xsxx">
														<tr><td colspan="7" align="center">没有学生入住！</td></tr>
													</logic:empty>
											</table>
											</td>
										</tr>
									</table>
								</fieldset>
							</logic:notEmpty>
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
