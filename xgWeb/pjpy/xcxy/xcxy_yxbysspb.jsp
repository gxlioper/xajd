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
			<font size="+1">附表一：</font>
		</p>
		<div align="center">
			<font size="+1"><b>${xxmc }优秀大学毕业生推荐审批表</b>
			</font>
		</div>
		<table width="100%" class="tbstyle">
			<tr>
				<td align="center" height="30px">
					姓名
				</td>
				<td width="10%">
				${map.xm }
					
				</td>
				<td align="center">
					性别
				</td>
				<td width="10%">
					${map.xb }
				</td>
				<td align="center">
					出生年月
				</td>
				<td width="10%">
					${map.csrq }
				</td>
				<td align="center">
					民族
				</td>
				<td width="10%">
					${map.mzmc }
				</td>
				<td align="center">
					政治面貌
				</td>
				<td width="13%">
					${map.zzmmmc }
				</td>
			</tr>
			<tr>
				<td align="center">
					<p>
						系(部)
					</p>
					<p>
						专业
					</p>
				</td>
				<td colspan="6">
					${map.zymc }
				</td>
				<td align="center">
					<p>
						学历
					</p>
					<p>
						名称
					</p>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">
					<p>
						家庭
					</p>
					<p>
						住址
					</p>
				</td>
				<td colspan="9">
					${map.syd }
				</td>
			</tr>
			<tr>
				<td align="center">
					<br/><br/>
					<p>
						在校
					</p>
					<p>
						期间
					</p>
					<p>
						主要
					</p>
					<p>
						表现
					</p>
					<br/>
				</td>
				<td colspan="9">
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;${map.hjqk }

					</p>
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;

					</p>
					<br/>
					<p align="right">
						辅导员(班主任)签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; 月
						&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td align="center">
					<p>
						<bean:message key="lable.xb" />
					</p>
					<p>
						审批
					</p>
					<p>
						推荐
					</p>
					<p>
						意见
					</p>
					<br/>
				</td>
				<td colspan="4">
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;${map.xyyj }

					</p>
					<p>
						&nbsp;

					</p>
					<p>
					</p><br/>
					<p>
						(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;${map.xyn }&nbsp; 年&nbsp;&nbsp;${map.xyy }&nbsp; 月
						&nbsp;&nbsp;${map.xyr }&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td align="center">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />
					</p>
					<p>
						审批
					</p>
					<p>
						意见
					</p>
					<br/>
				</td>
				<td colspan="4">
					<p>
						&nbsp;

					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;${map.xxyj }

					</p>
					<p>
						&nbsp;

					</p>
					<p>
					</p>
					<br/>
					<p>
						(盖章)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;${map.xxn }&nbsp; 年&nbsp;&nbsp;${map.xxy }&nbsp; 月
						&nbsp;&nbsp;${map.xxr }&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<ul>
			<font size="+1">注: </font> 1、本表一式二份，(一份<bean:message key="lable.xsgzyxpzxy" />存档，一份装入学生档案)
		</ul>
		<ul>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、请严格按照此表格式、尺寸填写，学历名称填：博士、硕士、本科、专科
		</ul>
	</html:form>
</body>
</html:html>
