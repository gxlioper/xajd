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
		<html:form action="/grwjcfxxsearch.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：个人留校查看记录
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							学号：
							${userName }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							姓名：
							${userNameReal }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							历史违纪次数:
							${num }
						</td>
					</tr>
				</thead>
			</table>
			<div class="rightcontent">
				<logic:empty name="rs">
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							&nbsp;&nbsp;&nbsp;
							<font color="blue">违纪处分信息</font>
						</legend>
						<fieldset>
						<legend>
							&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息；</font>
<%--							<logic:equal value="11078" name="xxdm">--%>
<%--								<font color="red">对于已确认的处分信息不能再进行申诉操作!</font>									--%>
<%--							</logic:equal>--%>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<!-- 其它学校 -->
									<logic:notEqual value="11078" name="xxdm">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td onclick="tableSort(this)" nowrap>
											操作
										</td>
									</logic:notEqual>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr align="center" onclick="rowOnClick(this);" style="cursor:hand" ondblclick="showTopWin('grwjcfxxview.do?pkValue='+curr_row.getElementsByTagName('input')[0].value,600,450)">
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
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<button type="button" class="button2" onclick="refreshForm('wjcfLxck.do?doType=save&pkValue=${v }')">
														申请解除
													</button>
												</logic:iterate>
												
											</td>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				
				<br/>
				<logic:notEmpty name="rsData">
				<fieldset>
						<legend>
							&nbsp;&nbsp;&nbsp;
							<font color="blue">解除留校察看信息</font>
						</legend>
					<fieldset>
						<legend>
							&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息；</font>
						</legend>
						<table width="100%" id="rssTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">学年</td>
									<td align="center">年度</td>
									<td align="center">处分类别</td>
									<td align="center">处分原因</td>
									<td align="center">处分时间</td>
									<td align="center">处分文号</td>
									<td align="center">解除时间</td>
									<td align="center">解除文号</td>
									<td align="center">解除结果</td>
								</tr>
							</thead>
							<logic:iterate name="rsData" id="es">
									<tr align="center" onclick="rowOnClick(this);" style="cursor:hand" ondblclick="showTopWin('wjcf_zjcm_stulxckxx.do?pkValue='+curr_row.getElementsByTagName('input')[0].value,650,550)">
										<td>
											<logic:iterate id="v" name="es" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="es" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="es" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
					</fieldset>
				</logic:notEmpty>
				
				<script type="text/javascript">
					function chkPriseOne2(url, w, h) {
						if (w == null) {
							w = 700;
						}
						if (h == null) {
							h = 500;
						}	
						if (curr_row == null) {
							alert("请选择要操作的行！");
							return false;
						} else {		
							var val = curr_row.cells[0].getElementsByTagName("input")[0].value;
							url+=val;
							url+="&cfwh=";
							url+=curr_row.cells[8].innerText;
							url+="&cfsj=";
							url+=curr_row.cells[9].innerText;	
							showTopWin(url, w, h);
						}
					}
				</script>
			</div>
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("已申请解除,请不要重复申请!");
				</script>
			</logic:equal>
			</html:form>
	</body>
</html>

