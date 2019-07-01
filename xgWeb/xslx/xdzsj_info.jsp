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
	
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
	function expDate_lx(){
		document.forms[0].action = "/xgxt/leaveExpDate.do";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}	
	</script>
	<body >
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/xdzsj.do" method="post">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" name="tableName" value="view_xdzsjxxb"/>		
				<input type="hidden" name="realTable" value="xdzsjxxb"/>
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置: 学生离校 - 系党支部书记信息					
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
										用户名：
										<html:text property="dzsxm" />
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:180px"
											styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>										
									</td>
									<td width="10" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xdzsj.do')">
											查询
										</button>
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
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">									
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>		
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)">
									
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>	
				
				<logic:present name="writeAble">
				<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2"
								onclick="showTopWin('/xgxt/xdzsj.do?doType=view',400,300)"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2"
								onclick="showTopWin('/xgxt/xdzsj.do?doType=view&xm='+curr_row.cells[1].innerText,400,300)"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="refreshForm('/xgxt/xdzsj.do?doType=del&xm='+curr_row.cells[1].innerText)" style="width:80px">
								删 除
							</button>
				</div>
				</logic:equal>
				</logic:present>
				<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert("操作成功！");
					document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("操作失败！");
				</script>
				</logic:equal>
				</logic:present>
				<script type="text/javascript" src="js/bottomButton.js"></script>				
			</html:form>
		</center>
	</body>
</html>
