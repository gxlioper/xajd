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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<body onload="check_user();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/insureHisQuery.do" method="post">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置:
						其它数据 - 保险信息 - 历年信息查询
					</div>
				</div>
				<logic:notEqual value="student" name="user">
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										年级：
										<html:select property="nj" style="width:90px"
											onchange="refreshForm('insureHisQuery.do')">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;学号：
										<html:text property="xh" />
										&nbsp;&nbsp;姓名：
										<html:text property="xm" />
									</td>	
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('insureHisQuery.do')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										班级：
										<html:select property="bjdm" style="width:120px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:180px"
											onchange="refreshForm('insureHisQuery.do')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="refreshForm('insureHisQuery.do')">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>				
									</td>									
								</tr>
								<tr>
								<td>
									查询信息：
										<html:select property="tabName" style="width:180px" styleId="tabName">
											<html:option value="view_xsbxxx">投保信息</html:option>
											<html:option value="lpxx">理陪信息</html:option>
											<html:option value="xfzrx">校园方责任险</html:option>
										</html:select>
								</td>
								</tr>
							</thead>
						</table>
					</fieldset>
				</logic:notEqual>
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsT" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:empty name="sh">
										<logic:iterate id="tit" name="topTr">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</logic:empty>
									<logic:notEmpty name="sh">
										<logic:iterate id="tit" name="topTr" offset="0" length="10">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</logic:notEmpty>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">								
									<tr onclick="rowOnClick(this)"
										ondblclick="">
										<logic:iterate id="v" name="s" offset="0" length="10">
											<td align="left" style="cursor:hand">
												<input type="hidden" value="<bean:write name="v"/>" />
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			</html:form>
		</center>
	</body>
</html>
