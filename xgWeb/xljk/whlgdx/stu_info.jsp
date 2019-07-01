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
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
	</head>
	<base target="_self">
	<body onload="bzrLoad();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<script language="javascript" src="js/function.js"></script>
			<html:form action="/data_search" method="post">
			<input type="hidden" id="modiTag" name="modiTag" value="<bean:write name="modiTag"/>"/>
			<input type="hidden" id="isBzr" name="isBzr"
				value="<bean:write name="isBzr" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="session"/>" />	
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置：学生信息 - 信息查询 - 个人信息
					</div>
				</div>
				<fieldset>
					<input type="hidden" id="disabled" name="disabled"
						value="<bean:write name="disabled"/>" />
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" style="width:90px"
										onchange="refreshForm('/xgxt/stu_info.do')" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<logic:equal name="userType" value="xy" scope="session" >
										<html:select property="xydm" style="width:130px"
											onchange="refreshForm('/xgxt/stu_info.do')" disabled="true" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session" >
										<html:select property="xydm" style="width:130px"
											onchange="refreshForm('/xgxt/stu_info.do')" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:280px" styleId="zy"
										onchange="refreshForm('/xgxt/stu_info.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" id="go" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									班级：
									<html:select property="bjdm" style="width:120px" styleId="bj">
										<logic:notEqual value="yes" name="isBzr">
										<html:option value=""></html:option>
										</logic:notEqual>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;学号：
									<html:text property="xh" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');}"/>
									&nbsp;&nbsp;姓名：
									<html:text property="xm" onkeypress="if(event.keyCode==13){document.forms[0].go.value='go';refreshForm('/xgxt/stu_info.do');}"/>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
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
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendStuInfo()">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<TABLE width="99%" id="rsTable" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
						</TABLE>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
