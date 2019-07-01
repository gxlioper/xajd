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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');removeXnXq()">
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/calendar-zh.js"></script>
		<script language="javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">

			function yz(type){
				var bzlxdm = document.getElementById('bzlxdm').value;
				if((bzlxdm==null) || (bzlxdm=="")){
					alert("请先选者要查询的补助类型!");
					return false;
				}
				if(type=='1'){
					var bzrq1 = document.getElementById('bzrq1').value;
					var bzrq2 = document.getElementById('bzrq2').value;
					if((bzrq1=="") || (bzrq2=="")){
						alert("需要比较的的两个月份不能为空！");
						return false;
					}
					document.forms[0].action = "/xgxt/bzcx.do?doType=no1";
					document.forms[0].submit();
				}else if(type=='2'){
					var bzrq = document.getElementById('bzrq').value;
					if(bzrq==""){
						alert("要查询的日期不能为空！");
						return false;
					}
					document.forms[0].action = "/xgxt/bzcx.do?doType=no2";
					document.forms[0].submit();
				}else if(type=='3'){
					var bzrqt = document.getElementById('bzrqt').value;
					var xydmt = document.getElementById('xydmt').value;
					if(bzrqt==""){
						alert("要查询的日期不能为空！");
						return false;
					}
					document.forms[0].action = "/xgxt/bzcx.do?doType=no3";
					document.forms[0].submit();
				}else if(type=='4'){
					var bzrq4 = document.getElementById('bzrq4').value;
					if(bzrq4==""){
						alert("要查询的日期不能为空！");
						return false;
					}
					document.forms[0].action = "/xgxt/bzcx.do?doType=no4";
					document.forms[0].submit();
				}
			}	
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 学生补助 - 补助查询
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						总 查 询 条 件
					</legend>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="doType" name="doType"
						value="<bean:write name="doType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									补助类型：
									<html:select name="rs1" property="bzlxdm" style="width:180px"
										styleId="bz">
										<html:option value=""></html:option>
										<html:options collection="bzList" property="bzlxdm"
											labelProperty="bzlxmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									学籍状态:
									<html:select name="rs1" property="xjzt" style="width:80px" styleId="xjzt">
										<html:option value=""></html:option>
										<html:options collection="xjztList" property="xjzt"
											labelProperty="xjzt" />
									</html:select>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						按 月 份 比 较
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select name="rs1" property="xydm" style="width:180px"
										styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									补助日期：
									<input type="text" readonly style="cursor:hand;width:100px"
										onclick="return showCalendar('bzrq1','y-mm');"
										value="<bean:write name='rs1' property="bzrq1" />"
										name="bzrq1" id="bzrq1" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									比较补助日期：
									<input type="text" readonly style="cursor:hand;width:100px"
										onclick="return showCalendar('bzrq2','y-mm');"
										value="<bean:write name='rs1' property="bzrq2" />"
										name="bzrq2" id="bzrq2" />
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go" onclick="yz('1');">
										查询
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						学 生 未 发 补 助 查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select name="rs1" property="xydm1" style="width:180px"
										styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xy1List" property="xydm1"
											labelProperty="xymc1" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									补助日期：
									<input type="text" readonly style="cursor:hand;width:100px"
										onclick="return showCalendar('bzrq','y-mm');"
										value="<bean:write name='rs1' property="bzrq" />" name="bzrq"
										id="bzrq" />
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go" onclick="yz('2');">
										查询
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						学 院 统 计
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select name="rs1" property="xydml" style="width:180px"
										styleId="xy" onchange="selIndex1(this,'xydmt')">
										<html:option value=""></html:option>
										<html:options collection="xyList1" property="xydml"
											labelProperty="xymcl" />
									</html:select>
									<input type="text" id="xydmt" name="xydmt" readonly="readonly"
										style="width:210px"
										value="<bean:write name="rs1" property="xydmt"/>" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 补助日期：
									<input type="text" readonly style="cursor:hand;width:100px"
										onclick="return showCalendar('bzrqt','y-mm');"
										value="<bean:write name='rs1' property="bzrqt" />"
										name="bzrqt" id="bzrqt" />
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go" onclick="yz('3');">
										查询
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<fieldset>
					<legend>
						查 询 学 生 库 中 没 有 资 料 的 补 助 信 息
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<input type="text" style="cursor:hand;width:100px"
										value="<bean:write name='rs1' property="xymc4" />"
										name="xymc4" id="xymc4" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;补助日期：
									<input type="text" readonly style="cursor:hand;width:100px"
										onclick="return showCalendar('bzrq4','y-mm');"
										value="<bean:write name='rs1' property="bzrq4" />"
										name="bzrq4" id="bzrq4" />
								</td>
								<td width="50" rowspan="1" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go" onclick="yz('4');">
										查询
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
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
							<font color="blue">提示：单击表头可以排序！</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr style="cursor:hand">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button class="button2" onclick="dataExport()" style="width:80px">
							导出数据
						</button>
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

