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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="xyDisabled('xy')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function checkAndView(obj){
		var array = obj.getElementsByTagName('input');
		showTopWin('/xgxt/xcxyXszz.do?method=getDgView&temp='+array[0].value+'&xmdm='+array[1].value,750,650);
	}
	</script>
		<html:form action="xcxyXszz.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<logic:equal value="no" name="view">
				<script language="javascript">
					alert('该页面只允许学生访问！');
				</script>
			</logic:equal>
			<logic:notEqual value="no" name="view">
			<fieldset>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学号：
								<bean:write name="userName"/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								姓名：
								<bean:write name="userNameReal"/>
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
						<font color="blue">提示：双击一行可以查看详细信息,单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								ondblclick="checkAndView(this);">
								<logic:iterate id="v" name="s" offset="3" length="1">
								<td align="center">
									<logic:iterate id="v1" name="s" offset="0" length="2">
										<input type="hidden" value="<bean:write name="v1"/>">
									</logic:iterate>
									<bean:write name="v" />
								</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="4">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			</logic:notEqual>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
</html>
