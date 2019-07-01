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
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	</script>
</head>

<body>
	<html:form action="gzdx_xszz.do?method=xsdkxxOne" method="post">
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					学号
				</td>
				<td width="34%">
					<bean:write name="rs" property="xh"/>
				</td>
				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						性别
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb"/>
				</td>
				<td>
					<div align="center">
						身份证号
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj"/>
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />名称
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						专业名称
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
				<td>
					<div align="center">
						班级名称
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						家庭住址
					</div>
				</td>
				<td>
					<bean:write name="rs" property="jtzz"/>
				</td>
				<td>
					<div align="center">
						邮政编码
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yzbm"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						联系电话1
					</div>
				</td>
				<td>
					<bean:write name="rs" property="lxdh1"/>
				</td>
				<td>
					<div align="center">
						联系电话2
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xdh2"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						联系电话3
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xdh3"/>
				</td>
				<td>
					<div align="center">
						单位电话
					</div>
				</td>
				<td>
					<bean:write name="rs" property="dwdh"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						工作单位
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="gzdw"/>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="bz"/>
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onClick="Close();" id="buttonPrint">
				关&nbsp;&nbsp;&nbsp;&nbsp;闭
			</button>
		</div>
	</html:form>
</body>
</html:html>
