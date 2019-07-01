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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpyjgsdxwh" method="post">

			<p align="center">
				<font size="4"><b>井冈山大学${rychmc }报批表</b>
				</font>
			</p>
			&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xymc" />院（系）&nbsp;&nbsp;&nbsp;<bean:write
				name="rs" property="nj" />&nbsp;级&nbsp;&nbsp;&nbsp;<bean:write
				name="rs" property="bjmc" />&nbsp;班&nbsp;&nbsp;&nbsp;学号:&nbsp;<bean:write
				name="rs" property="xh" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年  &nbsp;&nbsp; 月 &nbsp;&nbsp; 日
			<table align="center" width="98%" id="rsTable" class="tbstyle">
				<tr style="height:25px">
					<td width="11%">
						<div align="center">
							姓名
						</div>
					</td>
					<td width="13%">
						<div align="center">
							<bean:write name="rs" property="xm" />
						</div>
					</td>
					<td width="12%">
						<div align="center">
							性别
						</div>
					</td>
					<td width="7%">
						<div align="center">
							<bean:write name="rs" property="xb" />
						</div>
					</td>
					<td width="10%">
						<div align="center">
							年龄
						</div>
					</td>
					<td width="12%">
						<div align="center">
							<bean:write name="rs" property="nl" />
						</div>
					</td>
					<td width="17%">
						<div align="center">
							籍贯
						</div>
					</td>
					<td width="18%">
						<div align="center">
							<bean:write name="rs" property="jg" />
						</div>
					</td>
				</tr>
				<tr>
					<td >
						<div align="center">
							<p>
								受过何
							</p>
							<p>
								种奖励
							</p>
						</div>
					</td>
					<td colspan="2">
						<div align="center"><bean:write name="rs" property="jlqk"/> </div>
						
					</td>
					<td>
						<p align="center">
							是否党
						</p>
						<p align="center">
							团&nbsp;&nbsp;&nbsp;员
						</p>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="zzmm"/>
						</div>
					</td>
					<td>
						<p align="center">任&nbsp;&nbsp;何</p>
						<p align="center">&nbsp;职&nbsp;</p>
					</td>
					<td colspan="2">
						<div align="center"><bean:write name="rs" property="drzw"/></div>
						<div align="center"></div>
					</td>
				</tr>
				<tr>
					<td style="height:200px">
						<p align="center">
							&nbsp;
						</p>
						<p align="center">
							先进
						</p>
						<p align="center">
							事迹
						</p>
						<p align="center">
							&nbsp;
						</p>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zysj"/>
					</td>
				</tr>
				<tr>
					<td style="height:140px">
						<p align="center">
							班主任
						</p>
						<p align="center">
							意&nbsp;&nbsp;&nbsp;见
						</p>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fdyyj"/>
					</td>
				</tr>
				<tr>
					<td style="height:140px">
						<p align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</p>
						<p align="center">
							意见
						</p>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyyj"/>
					</td>
				</tr>
				<tr>
					<td style="height:140px">
						<p align="center">
							学校
						</p>
						<p align="center">
							意见
						</p>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xxyj"/>
					</td>
				</tr>
				<tr>
					<td style="height:140px">
						<p align="center">
							获奖学
						</p>
						<p align="center">
							金情况
						</p>
					</td>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="hjqk"/>
					</td>
				</tr>
			</table>
			<p align="right">
				(此表一式两份,可复印)&nbsp;&nbsp;&nbsp;&nbsp;
			</p>
		</html:form>
	</body>
</html>

