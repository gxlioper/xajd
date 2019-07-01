<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpynblgwh">
		<div align="center" style="font-size:20px;font:'黑体' ">
			<b>${xxmc } 届优秀毕业生评审表</b>
		</div>
		<br />
		<div>
			<table width="100%" class="printstyle">
				<tr>
					<th height="30" colspan="2" width="14%" scope="col">
						姓&nbsp;&nbsp;&nbsp;名
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xm" />
					</td>
					<th height="30" width="11%" scope="col">
						性&nbsp;&nbsp;&nbsp;别
					</th>
					<td height="30" colspan="2" scope="col" style="width:12%"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xb" />
					</td>
					<th height="30" width="12%" scope="col">
						出生年月
					</th>
					<td height="30" colspan="2" scope="col" width="12%" align="center">
						&nbsp;
						<bean:write name='rs' property="csrq" />
					</td>
					<th height="30" colspan="2" width="12%" scope="col">
						学&nbsp;&nbsp;&nbsp;号
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xh" />
					</td>
				</tr>
				<tr>
					<th height="30" colspan="2" width="14%" scope="col">
						所&nbsp;&nbsp;&nbsp;在
						<br />
						分&nbsp;&nbsp;&nbsp;院
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
					</td>
					<th height="30" width="11%" scope="col">
						专&nbsp;&nbsp;&nbsp;业
						<br />
						班&nbsp;&nbsp;&nbsp;级
					</th>
					<td height="30" colspan="5" scope="col" style="width:12%"
						align="center">
						&nbsp;
					</td>
					<th height="30" colspan="2" width="12%" scope="col">
						体育是
						<br />
						否达标
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th height="30" colspan="2" width="14%" scope="col">
						外&nbsp;&nbsp;&nbsp;语
						<br />
						等&nbsp;&nbsp;&nbsp;级
					</th>
					<td height="30" colspan="2" style="width:14%" scope="col"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xm" />
					</td>
					<th height="30" width="11%" scope="col">
						有无违
						<br />
						纪处分
					</th>
					<td height="30" colspan="2" scope="col" style="width:12%"
						align="center">
						&nbsp;
						<bean:write name='rs' property="xb" />
					</td>
					<th height="30" width="12%" scope="col">
						担任社会
						<br />
						工作情况
					</th>
					<td height="30" colspan="6" scope="col" width="12%" align="center">
						&nbsp;
						<bean:write name='rs' property="csrq" />
					</td>

				</tr>
				<tr>
					<th scope="row" style="width:6%" colspan="2">
						<p>
							&nbsp;
						</p>
						<p>
							获
						</p>
						<p>
							奖
						</p>
						<p>
							情
						</p>
						<p>
							况
						</p>
						<p>
							&nbsp;
						</p>
					</th>
					<th colspan="12" scope="row">

					</th>
				</tr>
				<tr>
					<th scope="row" style="width:6%" colspan="2">
						<p>
							&nbsp;
						</p>
						<p>
							个
						</p>
						<p>
							人
						</p>
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
						<p>
							&nbsp;
						</p>
					</th>
					<th colspan="12" scope="row">
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<p align="right">
							&nbsp;签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</th>
				</tr>
				<tr>
					<th align="" colspan="2">
						<p>所</p>
						<p>在</p>
						<p>分</p>
						<p>院</p>
						<p>意</p>
						<p>见</p>
					</th>
					<th colspan="2" width="20%">
						<br/><br/><br/><br/><br/>
						<p align="left">签名(盖章):</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;</p>
					</th>
					<th align="" colspan="2">
						<p>学</p>
						<p>生</p>
						<p>处</p>
						<p>意</p>
						<p>见</p>
					</th>
					<th colspan="2" width="20%">
						<br/><br/><br/><br/><br/>
						<p align="left">签名(盖章):</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;</p>
					</th>
					<th align="" colspan="2">
						<p>学</p>
						<p>院</p>
						<p>意</p>
						<p>见</p>
					</th>
					<th colspan="3" width="20%">
						<br/><br/><br/><br/><br/>
						<p align="left">签名(盖章):</p>
						<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;</p>
					</th>
				</tr>
			</table>
			<div>
				<b>注：</b>1.本表需用黑色钢笔或黑色水笔如实填写且内容不可涂改，要求字迹清晰端正。
				<br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				2.个人主要事迹不要采用附页的形式，此表格用A4纸打印或复印，申报时附上获奖证书复印件。

			</div>
			<div align="center" class='noPrin'>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
					页面设置
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
					打印预览
				</button>
				<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
					直接打印
				</button>
			</div>
		</div>
	</html:form>
</body>
</html:html>
