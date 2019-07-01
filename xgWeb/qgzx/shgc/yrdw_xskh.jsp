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
	<title><bean:message key="lable.title" />
	</title>
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
<script language="javascript">

	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable" class="tbstyle">
			<thead>
			<tr>
				<td scope="col" colspan="11">
					<div align="center">
						<h3 align="center">
							<strong> ${yrdwmc} <logic:notEmpty name="xn">
							${xn}学年
							</logic:notEmpty> <logic:notEmpty name="nd">
							${nd}年度
							</logic:notEmpty> <logic:notEmpty name="nj">
							${nj}级
							</logic:notEmpty> <logic:notEmpty name="xymc">
							${xymc}
							</logic:notEmpty> <logic:notEmpty name="gwmc">
							${gwmc}
							</logic:notEmpty> <logic:notEmpty name="gwxz">
							${gwxz}
							</logic:notEmpty>勤工助学时间表 </strong>
						</h3>
					</div>
				</td>
			</tr>
			</thead>
			<thead>
				<tr align="center" style="cursor:hand">					
					<logic:iterate id="tit" name="topTr" offset="0">
						<td id="<bean:write name="tit" property="en"/>" onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
				</tr>
			</thead>
			<logic:notEmpty name="rs">
			<logic:iterate name="rs" id="s">
				<tr>
					<logic:iterate id="v" name="s" offset="0">
						<td align="left">
							<bean:write name="v" />
						</td>
					</logic:iterate>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
			</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
