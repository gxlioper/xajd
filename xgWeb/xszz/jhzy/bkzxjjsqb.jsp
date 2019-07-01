<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/jhzyjsxy_xszz.do?method=bkzxjjsq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="/jhzyjsxy_xszz" method="post">
	<input type="hidden" id="msg" name="msg"
				value="${msg}">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> 金华职业技术<bean:message key="lable.xsgzyxpzxy" />帮困助学基金资助申请表 </strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr align="center">
				<td>
					（<bean:write name="rs" property="nd"/>年）
					<br />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="10%"><div align="center">姓名</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="xm"/></div></td>
						    <td width="10%"><div align="center">性别</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="xb"/></div></td>
						    <td width="10%"><div align="center">身份证号</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="sfzh"/></div></td>
						    <td width="10%"><div align="center">入学年月</div></td>
						    <td width="15%"><div align="center"><bean:write name="rs" property="rxrq"/></div></td>
						</tr>
						<tr>
							<td><div align="center"><bean:message key="lable.xsgzyxpzxy" /></div></td>
						    <td><div align="center"><bean:write name="rs" property="xymc"/></div></td>
						    <td><div align="center">班级</div></td>
						    <td><div align="center"><bean:write name="rs" property="bjmc"/></div></td>
						    <td><div align="center">学号</div></td>
						    <td><div align="center"><bean:write name="rs" property="xh"/></div></td>
						    <td><div align="center">学制</div></td>
						    <td><div align="center"><bean:write name="rs" property="xz"/></div></td>
						</tr>
  <tr>
    <td colspan="2"><div align="center">家庭地址及邮编</div></td>
    <td colspan="4"><bean:write name="rs" property="jtzzjyb"/></td>
    <td><div align="center">籍贯</div></td>
    <td><div align="center"><bean:write name="rs" property="jg"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">家庭年经济纯收入</div></td>
    <td colspan="4"><bean:write name="rs" property="jtnjjcsr"/></td>
    <td><div align="center">家庭人口数</div></td>
    <td><div align="center"><bean:write name="rs" property="jtrks"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">学习总体情况</div></td>
    <td colspan="4"><bean:write name="rs" property="xxztqk"/></td>
    <td><div align="center">德育</div></td>
    <td><div align="center"><bean:write name="rs" property="dy"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">曾获何种奖励</div></td>
    <td colspan="4"><bean:write name="rs" property="cshzjl"/></td>
    <td><div align="center">学费交纳情况</div></td>
    <td><div align="center"><bean:write name="rs" property="xfjnqk"/></div></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">曾受何种违纪处分</div></td>
    <td colspan="6"><bean:write name="rs" property="cshzwjcf"/></td>
  </tr>
  <tr>
    <td><div align="center">助学贷款<br />及金额</div></td>
    <td><bean:write name="rs" property="zxdkjje"/></td>
    <td><div align="center">获国家、校奖<br />学金金额</div></td>
    <td><bean:write name="rs" property="gjxjxjje"/></td>
    <td><div align="center">获国家励志<br />、助学金及<br />金额</div></td>
    <td><bean:write name="rs" property="gjlzzxjjje"/></td>
    <td><div align="center">本学年获困<br />难补助及金额</div></td>
    <td><bean:write name="rs" property="bxnhknbzjje"/></td>
  </tr>
  <tr>
    <td><div align="center">申<br /><br />请<br /><br />理<br /><br />由</div></td>
    <td colspan="7"><bean:write name="rs" property="sqly"/></td>
  </tr>
  <tr>
    <td colspan="2"><div align="center">助学基金资助等级及金额</div></td>
    <td colspan="6"><bean:write name="rs" property="zxjjxx"/></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;<bean:message key="lable.xsgzyxpzxy" />意见<br /><br /><br /><bean:write name="rs" property="xyshyj"/><br /><br /><br />
	<div align="center">盖&nbsp;章</div>
	<br />
	<div align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</div>
	</td>
    <td colspan="2">&nbsp;学生处意见<br /><br /><br /><bean:write name="rs" property="xxshyj"/><br /><br /><br />
	<div align="center">盖&nbsp;章</div>
	<br />
	<div align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</div></td>
    <td colspan="3">&nbsp;校分管领导(基金理事会)意见<br /><br /><br /><br /><br /><br />
	<div align="center">签&nbsp;字</div>
	<br />
	<div align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</div></td>
  </tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="返回" onclick="back();" />
	</div>
</body>
	<logic:present name="msg">
		<script>
			alert(''+document.getElementById('msg').value);
		</script>
	</logic:present>
</html:html>
