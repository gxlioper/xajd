<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
 <html:form action="/prise_conf_rs" method="post" >
<center>
<h3> <bean:write name="map" property="xxmc"/>授奖学生登记表</h3>
			<table width="90%" class="tbstyle">
				<tr>
					<td width="93" align="center">
						授奖项目
					</td>
					<td colspan="3" align="center">
						<bean:write name="map" property="jxjmc"/>
					</td>
					<td colspan="2" align="center">
						所在班级
					</td>
					<td colspan="2" align="center">
						<bean:write name="map" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						姓名
					</td>
					<td width="90" align="center">
						<bean:write name="map" property="xm"/>
					</td>
					<td width="70" align="center">
						性别
					</td>
					<td width="90" align="center">
						<bean:write name="map" property="xb"/>
					</td>
					<td width="33" align="center">
						民族
					</td>
					<td width="100" align="center">
						<bean:write name="map" property="mzmc"/>
					</td>
					<td width="84" align="center">
						出生年月
					</td>
					<td width="71" align="center">
						<bean:write name="map" property="csny"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						入党、团时间
					</td>
					<td align="center">
						${map.rtrq }
					</td>
					<td align="center">
						团内职务
					</td>
					<td align="center">
						&nbsp;
					</td>
					<td colspan="2" align="center">
						行政职务
					</td>
					<td colspan="2" align="center">
						${map.drzw }
					</td>
				</tr>
				<tr>
					<td align="center">
						<p>
							主
						</p>
						<p>
							要
						</p>
						<p>
							事
						</p>
						<p>
							迹
						</p>
					</td>
					<td colspan="7" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xxjl }
						<div align="right">
						班主任签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td rowspan="7" align="center">
						<p>
							学
						</p>
						<p>
							业
						</p>
						<p></p>
						成
						<p>
							绩
						</p>
					</td>
					<td align="center">
						科&nbsp;&nbsp;目
					</td>
					<td align="center">
						成&nbsp;&nbsp;绩
					</td>
					<td align="center">
						科&nbsp;&nbsp;目
					</td>
					<td colspan="2" align="center">
						成&nbsp;&nbsp;绩
					</td>
					<td align="center">
						科&nbsp;&nbsp;目
					</td>
					<td align="center">
						成&nbsp;&nbsp;绩
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc1 }
					</td>
					<td height="30" align="center">
						${rs.cj1 }
					</td>
					<td align="center">
						${rs.kcmc2 }
					</td >
					<td colspan="2" align="center">
						${rs.cj2 }
					</td>
					<td align="center">
						${rs.kcmc3 }
					</td >
					<td align="center">
						${rs.cj3 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc4 }
					</td>
					<td height="30" align="center">
						${rs.cj4 }
					</td>
					<td align="center">
						${rs.kcmc5 }
					</td>
					<td colspan="2" align="center">
						${rs.cj5 }
					</td>
					<td align="center">
						${rs.kcmc6 }
					</td>
					<td align="center">
						${rs.cj6 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc7 }
					</td>
					<td height="30" align="center">
						${rs.cj7 }
					</td>
					<td align="center">
						${rs.kcmc8 }
					</td>
					<td colspan="2" align="center">
						${rs.cj8 }
					</td>
					<td align="center">
						${rs.kcmc9 }
					</td>
					<td align="center">
						${rs.cj9 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc10 }
					</td>
					<td height="30" align="center">
						${rs.cj10 }
					</td>
					<td align="center">
						${rs.kcmc11 }
					</td>
					<td colspan="2" align="center">
						${rs.cj11 }
					</td>
					<td align="center">
						${rs.kcmc12 }
					</td>
					<td align="center">
						${rs.cj12 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc13 }
					</td>
					<td height="30" align="center">
						${rs.cj13 }
					</td>
					<td align="center">
						${rs.kcmc14 }
					</td>
					<td colspan="2" align="center">
						${rs.cj14 }
					</td>
					<td align="center">
						${rs.kcmc15 }
					</td>
					<td align="center">
						${rs.cj15 }
					</td>
				</tr>
				<tr>
					<td align="center">
						${rs.kcmc16 }
					</td>
					<td height="30" align="center">
						${rs.cj16 }
					</td>
					<td align="center">
						${rs.kcmc17 }
					</td>
					<td colspan="2" align="center">
						${rs.cj17 }
					</td>
					<td align="center">
						${rs.kcmc18 }
					</td>
					<td align="center">
						${rs.cj18 }
					</td>
				</tr>
				<tr>
					<td align="center">
						<p>
							系
						</p>
						<p>
							(部)
						</p>
						<p>
							意
						</p>
						<p>
							见
						</p>
					</td>
					<td colspan="7">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xyshyj }
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							年&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<p>
							学
						</p>
						<p>
							校
						</p>
						<p>
							意
						</p>
						<p>
							见
						</p>
					</td>
					<td colspan="7">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xxshyj }
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;
							月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr align="center">
					<td>
						<p>
							备
						</p>
						<p>
							注
						</p>
					</td>
					<td colspan="7" height="100px">
						&nbsp;
					</td>
				</tr>
			</table>
		</center>
</html:form>
</body>
</html:html>
