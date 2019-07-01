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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<body>
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<html:form action="/jhzy_rych" method="post">
				<div class=Section1 style='layout-grid:15.6pt;font-size: 20'>
					浙江省普通高等学校优秀毕业生登记表 &nbsp;
				</div>
				<div>
					学校：${xxmc}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院（系）：${rs.xymc}&nbsp;&nbsp;&nbsp;&nbsp;
					专业：${rs.zymc}&nbsp;&nbsp;&nbsp;&nbsp; 班级：${rs.bjmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年 &nbsp;&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;&nbsp;日
				</div>
				<table width="100%" class="tbstyle" align="center">
					<tr align="center">
						<td width="10%">
							姓 名
						</td>
						<td>
							${rs.xm }
						</td>
						<td width="8%">
							性 别
						</td>
						<td>
							${rs.xb }
						</td>
						<td colspan=2 width="10%">
							出生年月
						</td>
						<td>
							${rs.csrq }
						</td>
						<td width="8%">
							民 族
						</td>
						<td>
							${rs.mzmc }
						</td>
					</tr>
					<tr align="center">
						<td>
							生源地
						</td>
						<td colspan=3>
							${rs.syd }
						</td>
						<td colspan=2>
							政治面貌
						</td>
						<td>
							${rs.zzmmmc }
						</td>
						<td>
							职 务
						</td>
						<td>
							${rs.zw }
						</td>
					</tr>
					<tr align="center">
						<td>
							家庭地址
						</td>
						<td colspan=6>
							${rs.jtdz }
						</td>
						<td>
							联系电话
						</td>
						<td>
							${rs.sjhm }
						</td>
					</tr>
				</table>
				<table width="100%" class="tbstyle" >
					<tr >
						<td width="10%" align="center">
							本
							<br>
							人
							<br>
							简
							<br>
							历
						</td>
						<td>
							${brjl }
						</td>
					</tr>
					<tr >
						<td align="center">
							主
							<br>
							要
							<br>
							事
							<br>
							迹 
						</td>
						<td>
							${zysj }
						</td>
					</tr>
					<tr >
						<td align="center">
							在
							<br>
							校
							<br>
							期
							<br>
							间
							<br>
							获
							<br>
							奖
							<br>
							情
							<br>
							况
						</td>
						<td valign=top>
							${zxqjhjqk }
						</td>
					</tr>
				</table>
				<table width="100%" class="tbstyle" >
					<tr >
						<td width="10%" align="center">
							院
							<br>
							系
							<br>
							意
							<br>
							见
						</td>
						<td valign=top width="40%">
							${rsShyj.xyshyj }
						</td>
						<td valign=top  width="10%" align="center">
							学
							<br>
							校
							<br>
							意
							<br>
							见
						</td>
						<td valign=top width="40%">
							${rsShyj.xxshyj }
						</td>
					</tr>
					<tr >
						<td align="center">
							省
							<br>
							教
							<br>
							育
							<br>
							厅
							<br>
							意
							<br>
							见
						</td>
						<td valign=top>
							
						</td>
						<td valign=top align="center">
							毕
							<br>
							业
							<br>
							就
							<br>
							业
							<br>
							去
							<br>
							向
						</td>
						<td valign=top>
							&nbsp;
						</td>
					</tr>
					<tr >
						<td valign=top align="center">
							备<br><br>注
						</td>
						<td colspan=3 valign=top>
							&nbsp;
						</td>
					</tr>
				</table>
				<div align="right">
					浙江省教育厅制表 &nbsp;
				</div>
				<div align="left">
					注：1．此表一式两份：学生本人档案、学校各一份。
					<br>
					&nbsp; &nbsp; &nbsp; 2．本表内容可打印或用钢笔填写，字迹清楚，经院（系）、学校盖章，领导签字方有效。
				</div>
				<br>
				<br>
				<div class="buttontool noprint" align="center">
					<input  class="button2" value="页面设置"
						onclick="WebBrowser.ExecWB(8,1);">
					<input  class="button2" value="打印预览"
						onclick="WebBrowser.ExecWB(7,1)">
					<input  class="button2" value="直接打印"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>

</html>
