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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoadTest();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="url" name="url" value="/sjcz/sjb.jsp" />
				<input type="hidden" id="sjlsh" name="sjlsh"
					value="<bean:write name="rs" property="sjlsh"/>" />
				<fieldset>
					<legend>
						组卷维护
					</legend>
					<table width="99%" class="tbstyle">
						<tr>
							<td align="center" colspan="2">
								<font color="red">*</font>试卷名：
								<bean:write name="rs" property="sjm" />
							</td>
						</tr>
						<tr>
							<td align="center">
								已选试题列表：(试题流水号---序号)
							</td>
							<td align="center">
								已选试题数:
								<div id="count"></div>
							</td>
						</tr>
						<tr>
							<td align="center" width="70%">
								<html:select property="yxstlb" styleId="yxstlb" size="20"
									style="width: 90%">
									<logic:notEmpty name="stList">
										<html:options collection="stList" property="stlsh"
											labelProperty="stlshstxh" />
									</logic:notEmpty>
								</html:select>
							</td>
							<td align="center" valign="middle" width="30%">
								<br>
								<button type="button"
									onclick="showTopWin('/xgxt/stinfo_search.do?act=testQuestion',620,550)"
									class="button2" style="width: 50px;height: 20px" title="增加">
									+
								</button>
								<br>
								<br>
								<br>
								<button type="button" onclick="updateStList('del')" class="button2"
									style="width: 50px;height: 20px" title="删除">
									-
								</button>
								<br>
								<br>
								<br>
								<button type="button" onclick="updateStList('up')" class="button2"
									style="width: 50px;height: 20px" title="上移">
									∧
								</button>
								<br>
								<br>
								<br>
								<button type="button" onclick="updateStList('down')" class="button2"
									style="width: 50px;height: 20px" title="下移">
									∨
								</button>
								<br>
								<br>
								<br>
								<button type="button" onclick="updateStList('clear')" class="button2"
									style="width: 50px;height: 20px" title="清空">
									×
								</button>
								<br>
								<br>
								<br>
							<br />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						修 改
					</button>
					<button type="button" class="button2" onclick="StSjDoSave()" style="width:80px"
						id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
