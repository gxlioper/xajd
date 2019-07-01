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

		<%@include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
			<html:form action="/portallet.do?method=stuInfo" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm" scope="request"/>"/>
<%--				<div class="title">--%>
<%--					<div class="title_img" id="title_m">--%>
<%--						当前位置：学生信息 - 信息查询 - 个人信息--%>
<%--					</div>--%>
<%--				</div>--%>
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tabform">
						<thead>							
							<tr>								
								<td align="left">
									学号：
									<html:text property="xh" onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/portallet.do?method=stuInfo');" />
									</td>
									<td rowspan="2">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go" style="height:30px"					
										onclick="allNotEmpThenGo('/xgxt/portallet.do?method=stuInfo');">
										查 询
									</button>
									</td>
									</tr>
									<tr>
									<td>
									姓名：
									<html:text property="xm" onkeypress="if(event.keyCode == 13) allNotEmpThenGo('/xgxt/portallet.do?method=stuInfo');"/>									
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
						<table width="100%" id="rsTable" class="tabform">
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
							<tbody>
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="stuInfoWin(this)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" value="<bean:write name="v" />" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
								</tbody>
							</logic:iterate>
						</table>
						<TABLE width="99%" id="Table" class="tabform">
						<tbody>
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
								</tbody>
						</TABLE>
					</fieldset>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
									