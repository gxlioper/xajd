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
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<body onload="check_user();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>			
			<html:form action="/exp_tbinfo" method="post">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置: 保险信息 - 按班级导出

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
										<html:select property="nj" style="width:180px"
											onchange="">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:180px"
											onchange="refreshForm('/xgxt/exp_tbinfo.do')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp; 专业：
										<html:select property="zydm" style="width:180px"
											onchange="refreshForm('/xgxt/exp_tbinfo.do');">
											<html:option value=""></html:option>
											<html:options collection="zyList" labelProperty="zymc"
												property="zydm" />
										</html:select>
										&nbsp;&nbsp;
									</td>
									<td width="10" align="center" valign="middle" rowspan="2">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/exp_tbinfo.do')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td>
										班级：
										<html:select property="bjdm" style="width:180px">
											<html:option value=""></html:option>
											<html:options collection="bjList" labelProperty="bjmc"
												property="bjdm" />
										</html:select>
										&nbsp;&nbsp;学号：
										<html:text property="xh" styleId="xh"/>
										&nbsp;&nbsp;姓名：
										<html:text property="xm" styleId="xm"/>
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
							<logic:notEmpty name="xybj">
								<div align="left" style="display: none">
									<font color="blue" size="4"><b><bean:write
												name="xybj" /> </b> </font>
								</div>
							</logic:notEmpty>
							<thead>

								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" length="5">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td style="display: none">
										确定签名
									</td>

								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)">
									<logic:iterate id="v" name="s" offset="0" length="5">
										<td align="left" style="cursor:hand">
											<bean:write name="v" />
										</td>
									</logic:iterate>

									<td style="display: none"></td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="dataExport1('/xgxt/expData.do?tableName=view_tbxx&realTable=xsbxb')"
							style="width:80px">
							导出数据
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="expTab('rsT','投保信息','')">
							打印列表
						</button>
					</div>
					<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
					</script>
			</html:form>
		</center>
	</body>
</html>
