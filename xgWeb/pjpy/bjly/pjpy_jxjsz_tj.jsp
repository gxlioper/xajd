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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<html:form action="/prise_condition" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 参数设置 - 条件设置
				</div>
			</div>
			<fieldset>
				<legend>
					条件设置
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								奖学金：
								<html:select property="xmdm" style="width:200px" styleId="jxjdm"
									onchange="refreshForm('/xgxt/prise_condition.do')">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="left">
								条件：
								<html:select property="zdm" style="width:120px" styleId="zdm">
									<html:option value=""></html:option>
									<html:options collection="zdList" property="zd"
										labelProperty="zdsm" />
								</html:select>
								--&gt;
								<select name="ysf" style="width:120px">
									<option value=""></option>
									<option value="&gt;=">
										大于或等于
									</option>
									<option value="&gt;">
										大于
									</option>
									<option value="=">
										等于
									</option>
									<option value="&lt;">
										小于
									</option>
									<option value="&lt;=">
										小于或等于
									</option>
								</select>
								--&gt;
								<input type="text" name="val" value="" style="width:120px" />
								<input type="hidden" name="go" value="a" />
								<button class="button2"
									onclick="if(allFilled()){document.forms[0].go.value='go';refreshForm('/xgxt/pjpy_bjly_tj.do');return true;}return false;">
									添加条件
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<br />
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						现有条件
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr>
								<td onclick="tableSort(this)">
									序号
								</td>
								<td onclick="tableSort(this)">
									条件字段
								</td>
								<td onclick="tableSort(this)">
									条件
								</td>
							</tr>
						</thead>
						<logic:iterate id="rs" name="rs" scope="request">
							<tr align="left" onclick="rowOnClick(this)" style="cursor:hand">
								<td>
									<bean:write name="rs" property="rownum" />
								</td>
								<td>
									<bean:write name="rs" property="tjzdm" />
								</td>
								<td>
									<bean:write name="rs" property="tj" />
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button class="button2" onclick="bllydelPriseCondi()">
					删除条件
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="delAllPriseCondi()">
					清空条件
				</button>
			</div>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
	</body>
</html>
