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
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h2>
						<strong>
								重庆科技<bean:message key="lable.xsgzyxpzxy" />绿色通道
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td scope="col" width="14%">
								<div align="center">
									院&nbsp;&nbsp;系
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td scope="col" width="13%">
								<div align="center">
									班&nbsp;&nbsp;级
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td scope="col" width="13%">
								<div align="center">
									学&nbsp;&nbsp;号
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									姓&nbsp;&nbsp;名
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td>
								<div align="center">
									性&nbsp;&nbsp;别
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
								<div align="center">
									民&nbsp;&nbsp;族
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									缓&nbsp;&nbsp;交
									<br />
									<br />
									原&nbsp;&nbsp;因
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="hjyy" />
								<br />
								<p align="right">
									本人签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<bean:write name='rs' property="sqsj_year" />
									&nbsp;年&nbsp;
									<bean:write name='rs' property="sqsj_mon" />
									&nbsp;月&nbsp;
									<bean:write name='rs' property="sqsj_day" />
									&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									辅导员
									<br />
									<br />
									意&nbsp;&nbsp;见
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="fdyshyj" />
								<br />
								<p align="right">
									签字:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<logic:equal name="fdyspsj_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="fdyspsj_year" value="empty">
									<bean:write name='rs' property="fdyspsj_year" />
									</logic:notEqual>
									&nbsp;年&nbsp;
									<logic:equal name="fdyspsj_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="fdyspsj_mon" value="empty">
									<bean:write name='rs' property="fdyspsj_mon" />
									</logic:notEqual>
									&nbsp;月&nbsp;
									<logic:equal name="fdyspsj_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="fdyspsj_day" value="empty">
									<bean:write name='rs' property="fdyspsj_day" />
									</logic:notEqual>
									&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									院&nbsp;&nbsp;系
									<br />
									<br />
									意&nbsp;&nbsp;见
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<p align="right">
									盖章（签字）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<logic:equal name="xyspsj_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xyspsj_year" value="empty">
									<bean:write name='rs' property="xyspsj_year" />
									</logic:notEqual>
									&nbsp;年&nbsp;
									<logic:equal name="xyspsj_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xyspsj_mon" value="empty">
									<bean:write name='rs' property="xyspsj_mon" />
									</logic:notEqual>
									&nbsp;月&nbsp;
									<logic:equal name="xyspsj_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xyspsj_day" value="empty">
									<bean:write name='rs' property="xyspsj_day" />
									</logic:notEqual>
									&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									学生处
									<br />
									<br />
									意&nbsp;&nbsp;见
								</div>
							</td>
							<td colspan="5">
								<p>
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;经审核研究，同意缓交学费
									<u>&nbsp;
									<logic:equal name="tyhjxf" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="tyhjxf" value="empty">
									<bean:write name='rs' property="tyhjxf" />
									</logic:notEqual>
									&nbsp;</u>元(大写
									<u>&nbsp;
									<logic:equal name="tyhjxfdx" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="tyhjxfdx" value="empty">
									<bean:write name='rs' property="tyhjxfdx" />
									</logic:notEqual>
									&nbsp;</u>),
								
								<p>
									至
									<u>&nbsp;
									<logic:equal name="jzrq_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="jzrq_year" value="empty">
									<bean:write name='rs' property="jzrq_year" />
									</logic:notEqual>
									&nbsp;</u>年
									<u>&nbsp;
									<logic:equal name="jzrq_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="jzrq_mon" value="empty">
									<bean:write name='rs' property="jzrq_mon" />
									</logic:notEqual>
									&nbsp;</u>月
									<u>&nbsp;
									<logic:equal name="jzrq_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="jzrq_day" value="empty">
									<bean:write name='rs' property="jzrq_day" />
									</logic:notEqual>
									&nbsp;</u>日止。
								</p>
								<p align="right">
									盖章（签字）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<p align="right">
									<logic:equal name="xxspsj_year" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xxspsj_year" value="empty">
									<bean:write name='rs' property="xxspsj_year" />
									</logic:notEqual>
									&nbsp;年&nbsp;
									<logic:equal name="xxspsj_mon" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xxspsj_mon" value="empty">
									<bean:write name='rs' property="xxspsj_mon" />
									</logic:notEqual>
									&nbsp;月&nbsp;
									<logic:equal name="xxspsj_day" value="empty">
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="xxspsj_day" value="empty">
									<bean:write name='rs' property="xxspsj_day" />
									</logic:notEqual>
									&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									备&nbsp;&nbsp;注
								</div>
							</td>
							<td colspan="5">
								<br />
								<bean:write name='rs' property="bz" />
								<br />
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
	</div>
</body>
</html:html>
