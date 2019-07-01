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
td{ font-size:15px;}
</style>
  <body>
	<html:form action="/rychqsb" method="post">		
			<p>
				<font size="+1">附表二：</font>
			</p>
			<div align="center">
				<font size="+1"><b>四川省普通高等学校本专科省优秀毕业生登记表</b>
				</font>
			</div>
			<table width="100%" class="tbstyle">
				<tr>
					<td >
						学校名称
					</td>
					<td colspan="3">
						${xxmc }
					</td>
					<td >
						专业名称
					</td>
					<td colspan="3">
					${map.zymc }
					</td>
				</tr>
				<tr>
					<td width="10%">
						姓名
					</td>
					<td width="10%">
					${map.xm }
					</td>
					<td width="10%">
						性别
					</td>
					<td width="20%">
						${map.xb }
						
					</td>
					<td width="10%">
						出生年月
					</td>
					<td width="10%">
					${map.csrq }
						
					</td>
					<td width="10%">
						民族
					</td>
					<td width="10%">
						${map.mzmc }
					</td>
				</tr>
				<tr>
					<td>
						生源地
					</td>
					<td colspan="2">
						${map.syd }
					</td>
					<td>
						政治面貌
					</td>
					<td>
						${map.zzmmmc }
					</td>
					<td>
						职务
					</td>
					<td colspan="2">
						${map.drzw }
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							主要事迹：
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.zysj }

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							盖章&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							${map.sn }年&nbsp;&nbsp;${map.sy }&nbsp; 月 &nbsp;&nbsp;${map.sr }&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							<bean:message key="lable.xsgzyxpzxy" />(系)意见：
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xyyj }

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							盖章&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							${map.xyn }年&nbsp;&nbsp;${map.xyy }&nbsp; 月 &nbsp;&nbsp;${map.xyr }&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							学校审批意见：
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${map.xxyj }

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							盖章&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							${map.xxn }年&nbsp;&nbsp;${map.xxy }&nbsp; 月 &nbsp;&nbsp;${map.xxr }&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<p>
							四川省教育厅审批意见：
						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;

						</p>
						<p>
							&nbsp;

						</p>
						<p>
						</p>
						<p align="right">
							盖章&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							年&nbsp;&nbsp;&nbsp; 月 &nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<ul>
				<font size="+1">注: 
				</font>1、此表一式二份，报省教育厅审批后返回学校，一份学习留存，另一份装入毕业生本人档案
			</ul>
			<ul>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、本表内容可打印或用钢笔填写，字迹要清楚
			</ul>
	</html:form>
</body>
</html:html>
