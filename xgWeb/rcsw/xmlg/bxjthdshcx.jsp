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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function chkView(){
   			var url = "/xgxt/xssfzbb.do?method=updatesfzbbxx&view=yes&pkVStr=";
   			var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pk;
    		showTopWin(url,"650","580");              		                  
 		}
		function queryData(){
			var xn=document.getElementById('xn').value;
			var xq=document.getElementById('xq').value;
			if(xn == ''||xq == ''){
				alert('学年,学期为必选！');
				return false;
			}
			refreshForm('/xgxt/xssfzbb.do?method=sfzbbshcx&doType=query');
		}
	</script>
	<body>
		<center>
			<html:form action="/xssfzbb" method="post">
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" name="realTable" id="realTable"
					value="<bean:write name="realTable"/>" />
				<input type="hidden" name="tableName" id="tableName"
					value="<bean:write name="tableName"/>" />
				<input type="hidden" name="isFdy" id="isFdy"
					value="<bean:write name="fdyQx" scope="session"/>" />
				<input type="hidden" name="userName" id="userName"
					value="<bean:write name="userName" scope="session"/>" />
				<input type="hidden" name="pkVStr" id="pkVStr" value="" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：日常事务 - 学生身份证管理 - 审核结果查询
					</div>
				</div>
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									学年：
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp; 学期：
									<html:select property="xq" styleId="xn" style="width:40px">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />：
									<logic:equal value="xy" name="userType">
										<html:select property="xydm" styleId="xy" disabled="true">
											<html:option value="">--请选择--</html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="xydm" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value="">--请选择--</html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" styleId="zy"
										onchange="initBjList();">
										<html:option value="">--请选择--</html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="queryData();">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left">
									班级：
									<html:select property="bjdm" styleId="bj" style="width:250px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;补办状态：
									<html:select property="bbzt" styleId="bbzt" style="width:120px"
										onchange="changeView(this);">
										<html:option value="">--请选择--</html:option>
										<html:option value="正在补办中">正在补办中</html:option>
										<html:option value="已补办好">已补办好</html:option>
									</html:select>
									&nbsp;&nbsp;审核状态：
									<html:select property="fdysh" styleId="fdysh"
										style="width:120px" onchange="changeView(this);">
										<html:option value="">--请选择--</html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>

								</td>
							</tr>
							<tr>
								<td align="left">
									学号：
									<html:text property="xh" style="width:120px"></html:text>
									&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:120px"></html:text>
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
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
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="chkView()">
									<td align="center">
										<input type="hidden" value="<bean:write name="s" property="pk"/>">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="xqmc" />
									</td>
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
										<bean:write name="s" property="nj" />
									</td>
									<td align="center">
										<bean:write name="s" property="xymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="zymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="bjmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="bbzt" />
									</td>
									<td align="center">
										<bean:write name="s" property="fdysh" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2" onclick="dataExport()" style="width:100px">
							导 出
						</button>
					</div>
				</center>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
