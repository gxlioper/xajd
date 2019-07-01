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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">

	</script>
	<body onload="bjLimit('bj');onShow()">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>	
		<center>
			<html:form action="/zjlgPjpy" method="post">
				<input type="hidden" name="xyV" id="xyV" value="<bean:write name="xydm" scope="request"/>"/>
				<input type="hidden" name="zyV" id="zyV" value="<bean:write name="zyV" />"/>
				<input type="hidden" name="bjV" id="bjV" value="<bean:write name="bjV" />"/>
				<input type="hidden" name="njV" id="njV" value="<bean:write name="njV" />"/>
				<input type="hidden" name="xnV" id="xnV" value="<bean:write name="xnV" />"/>
				<input type="hidden" name="xhV" id="xhV" value="<bean:write name="xhV" />"/>
				<input type="hidden" name="xmV" id="xmV" value="<bean:write name="xmV" />"/>
				<input type="hidden" id="msg" name="msg" value="${msg}"/>
				<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
				<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
				<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="2">
								<input type="radio" name="cjlx" value="0" id="cjlx" 
									onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfPsf');" >
									平时分
								&nbsp; 
								<input type="radio" name="cjlx" value="1" id="cjlx" 
									onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfWsf');" >
								&nbsp; 
									卫生分
								<input type="radio" name="cjlx" value="2" id="cjlx" 
									onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfKqf');" >
									考勤分
								&nbsp; 	
								<input type="radio" name="cjlx" value="3" id="cjlx" 
									onclick="refreshForm('/xgxt/zjlgPjpy.do?method=dycpfZf');" checked>
									德育测评总分		
							</td>
						</tr>
						<tr>
						<td align="left">
							年级：
							<html:select property="nj" style="width:80px"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							学年：
							<html:select property="xn" style="width:100px">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;&nbsp;学号：
							<html:text property="xh" style="width:85px" maxlength="20"></html:text>
							&nbsp;&nbsp;&nbsp;姓名：
							<html:text property="xm" style="width:85px" maxlength="20"></html:text>								
						</td>
						<td width="10" rowspan="3" align="center" valign="middle">
							<input type="hidden" name="go" value="a" />
							<button type="button" class="button2" style="height:40px" id="search_go"
								onclick="allNotEmpThenGo('/xgxt/zjlgPjpy.do?method=dycpfZf')">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp;专业：
							<html:select property="zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;班级：
							<html:select property="bjdm" style="width:180px" styleId="bj">
								<html:option value=""></html:option>
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
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
							<font color="blue">
							提示：<bean:write name="xnts" />学年，德育分各项最高分分别为 
							平时分（<bean:write name="psfMax" />）
							卫生分（<bean:write name="wsfMax" />）
							考勤分（<bean:write name="kqfMax" />）
							红色为错误数据</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0" length="5">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td onclick="tableSort(this)" nowrap>
										平时分
									</td>
									<td onclick="tableSort(this)" nowrap>
										卫生分
									</td>
									<td onclick="tableSort(this)" nowrap>
										考勤分
									</td>
									<td onclick="tableSort(this)" nowrap>
										违纪扣分
									</td>
									<td onclick="tableSort(this)" nowrap>
										德育测评总分
									</td>
								</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<logic:equal value="yes" name="writeAble">
								<tr>	
									<td align="center">
										<bean:write name="s" property="xh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xm" />
									</td>
									<td align="center">
										<bean:write name="s" property="xb" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<FONT color="<bean:write name="s" property="psfcl" />"><bean:write name="s" property="psf" /></FONT>
									</td>
									<td align="center">
										<FONT color="<bean:write name="s" property="wsfcl" />"><bean:write name="s" property="wsf" /></FONT>
									</td>
									<td align="center">
										<FONT color="<bean:write name="s" property="kqfcl" />"><bean:write name="s" property="kqf" /></FONT>
									</td>
									<td align="center">
										<bean:write name="s" property="kf" />
									</td>
									<td align="center">
										<logic:lessThan  name="s" property="dycpf" value="1">
											<logic:equal name="s" property="dycpf" value="0">
											0
											</logic:equal>
											<logic:notEqual name="s" property="dycpf" value="0">
											0<bean:write name="s" property="dycpf" />
											</logic:notEqual>
										</logic:lessThan>
										<logic:greaterEqual name="s" property="dycpf" value="1">
											<bean:write name="s" property="dycpf" />
										</logic:greaterEqual>
									</td>
								</tr>
							</logic:equal>
						</logic:iterate>
					</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=zjlgPjpyForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
				</fieldset>						
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="userType" value="teacher">
							<button type="button" class="button2"
								onclick="showOpenWindow('/xgxt/zjlgPjpy.do?method=dycpfPsfSz&type=edit',400,300)"
								style="width:80px">
								参数设置
							</button>
						</logic:equal>
						<logic:notEqual name="userType" value="teacher">
						<logic:notEqual name="userType" value="xy">
							<button type="button" class="button2"
								onclick="showOpenWindow('/xgxt/zjlgPjpy.do?method=dycpfDycpfSz',400,300)"
								style="width:80px">
								参数设置
							</button>
						</logic:notEqual>
						</logic:notEqual>
					</div>
				</center>
				<div id="tmpdiv"></div>
				<logic:present name="msg">
				<script>
					alert(''+document.getElementById('msg').value);
				</script>
				</logic:present>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>