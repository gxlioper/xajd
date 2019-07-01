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
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：学生资助 - 信息维护 - 困难认定详细信息
		</div>
	</div>
		<html:form action="ycws_xszz.do?method=knsrdOne" method="post">
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pkVal" />">

			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" width="16%">
						学号
					</td>
					<td align="left" width="34%">
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
							民族
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
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
							学制
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<td>
						<div align="center">
							联系电话
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭类型
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtlx"/>
					</td>
					<td>
						<div align="center">
							是否低保
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfdb"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							家庭年收入
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtnsr"/>
					</td>
					<td>
						<div align="center">
							家庭人口
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtrk"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							是否有证明材料
						</div>
					</td>
					<td>
						<bean:write name="rs" property="sfyzmcl"/>
					</td>
					<td>
						<div align="center">
							困难认定
						</div>
					</td>
					<td>
						<bean:write name="rs" property="knrd"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>

		</html:form>
</body>
</html:html>
