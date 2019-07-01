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
			var xh = $("xh").value;
			var xn = $("xn").value;
			var type = $("type").value;
			var jg = $("jg").value;
			var url = "/xgxt/n05_xszz.do?method=xfjm1sq";
			url += "&xh="+xh;
			url += "&xn="+xn;
			url += "&type="+type;
			url+="&jg="+jg;
			document.forms[0].action = url;
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> <bean:write name='rs' property="xxmc" />经济困难学生减免学杂费申请表</strong>
						</h2>
						<html:hidden name='rs' property="xn"/>
						<input type="hidden" id="type" name="type" value="${type }">
						<input type="hidden" id="jg" name="jg" value="${jg }" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								<div align="center">
									姓&nbsp;&nbsp;名
								</div>
							</td>
							<td width="18%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									学&nbsp;&nbsp;号
								</div>
							</td>
							<td width="18%">
								<div align="center">
									<bean:write name='rs' property="xh" />
									<html:hidden name='rs' property="xh"/>
								</div>
							</td>
							<td width="15%">
								<div align="center">
									班&nbsp;&nbsp;级
								</div>
							</td>
							<td width="19%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									性&nbsp;&nbsp;别
								</div>
							</td>
							<td rowspan="2">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td rowspan="2">
								<div align="center">
									上学年综合
									<br />
									测评名次
								</div>
							</td>
							<td>
								名次：
								<bean:write name='rs' property="sxnzhcpmc" />
							</td>
							<td rowspan="2">
								<div align="center">
									补考门次
								</div>
							</td>
							<td rowspan="2">
								<div align="center">
									<bean:write name='rs' property="bkmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								参评人数：
								<bean:write name='rs' property="sxnzhcprs" />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									蓝/绿卡
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="llk" />
								</div>
							</td>
							<td>
								<div align="center">
									政治面貌
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									已免次数
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ymcs" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									联系方式
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxfs" />
								</div>
							</td>
							<td>
								<div align="center">
									有无勤工
									<br />
									助学岗位
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ywqgzx" />
								</div>
							</td>
							<td>
								<div align="center">
									家庭联系方式
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtlxfs" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									申
									<br />
									&nbsp;
									<br />
									请
									<br />
									&nbsp;
									<br />
									理
									<br />
									&nbsp;
									<br />
									由
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="sqly">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									申请人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<!-- 南京技师 -->
						<logic:equal name="xxdm" value="8001">
							<%@ include file="/xszz/njjs/xfxxPrint.jsp"%>
						</logic:equal>
						<!-- 南京技师 end-->
						<tr>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									意见
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="xyshyj">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(加盖公章)
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									党委学工
									<br />
									部意见
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(加盖公章)
								</div>
							</td>
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
</html:html>
