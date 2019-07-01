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
		<base target="_self">
	</head>
	
	<script language="javascript">
		function xskqxx_print()
		{
			openWin("/xgxt/StuCheckWorkInfoWh_print.do",800,650);
		}
		function stuPreWarn(color)
		{
			var xq=document.getElementById("xq").value;
			var xn=document.getElementById("xn").value;
			var url="/xgxt/wjcfStuPreWarn.do?act=go&color="+color+"&xq="+xq+"&xn="+xn;
			if (document.getElementById("xq").selectedIndex<=0 || document.getElementById("xn").selectedIndex<=0)
				alert("提示：该次操作是按学期和学年进行查询！请再次确认并已选择！");
			else
				refreshForm(url);
		}
	</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/kqxxwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					
					<logic:equal value="11049" name="xxdm">
						当前所在位置：日常管理 - 考勤管理 - 旷课预警
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						当前所在位置：日常事务 - 考勤管理 - 旷课预警
					</logic:notEqual>
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									学年：
									<html:select property="xn" style="width:120px" styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;&nbsp;学期：
									<html:select property="xq" style="width:120px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
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
							<font color="blue">提示：单击表头可以排序(
							<logic:present name="tips">
								<bean:write name="tips"/>
							</logic:present>)
							</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle" bgcolor="<bean:write name="cs"/>">
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
									<tr onclick="rowOnClick(this);" align="center" style="cursor:hand">
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
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="stuPreWarn('orange')" style="width:80px">
								橙色预警
							    </button>
							    &nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="stuPreWarn('red')" style="width:80px">
								红色预警
							    </button>
							    &nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="stuPreWarn('blank')" style="width:80px">
								黑名单
							    </button>
							    &nbsp;&nbsp;&nbsp;
							    <button type="button" class="button2"
								onclick="expTab('rsTable','<bean:write name="tops" scope="request"/>')"
								style="width:80px">
								打印/预览
							</button>
						</div>
					</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>

