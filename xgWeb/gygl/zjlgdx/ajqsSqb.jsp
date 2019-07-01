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
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
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
		.tbstyle {
	border-collapse: collapse;
}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
	font-size: 14px;
}
	</style>
</head>

<body>

	<div align="center">
		<h3>
			浙江理工大学A级寝室申报表
		</h3>
		<table border=1 cellspacing=0 cellpadding=0 align=center class="theTable">
			<tr height="30">
				<td width="10%" align=center>
					学 院
				</td>
				<td width="30%" align="center" colspan="2">
					<bean:write name="rs" property="xymc" />
					&nbsp;
				</td>
				<td width="10%" align=center>
					楼层、寝室号
				</td>
				<td width="20%" align="center">
					<bean:write name="rs" property="cs" />-<bean:write name="rs" property="qsh" />
					&nbsp;
				</td>
				<td width="10%" align=center>
					联系电话
				</td>
				<td width="20%" align="center">
					<bean:write name="rs" property="lxdh" />
					&nbsp;
				</td>
			</tr>
			<tr height="30">
				<td align=center>
					寝室长
				</td>
				<td align="center" width="15%">
					${rs.qsz }&nbsp;
				</td>
				<td align=center width="15%">
					寝室成员
				</td>
				<td align="left" colspan="4">
					<logic:present name="rs" property="qscy">
						<logic:iterate id="s" name="rs" property="qscy">
							<bean:write name="s"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:iterate>
					</logic:present>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align=center>
					申<br>报<br>条<br>件
				</td>
				<td align="center" colspan="2" valign="middle">
					连续两周寝室卫生情况:
				</td>
				<td align="left" colspan="4" valign="middle">
					<logic:present name="rs" property="qscj">
							<logic:iterate id="s" name="rs" property="qscj">
								&nbsp;&nbsp;&nbsp;&nbsp;第&nbsp;<bean:write name="s" property="zs"/>&nbsp;周卫生检查成绩:&nbsp;<bean:write name="s" property="fs"/><br>
							</logic:iterate>
					</logic:present>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" >
					<br>申<br>报<br>理<br>由<br><br>
				</td>
				<td colspan="6" align="left">
					<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sqly"/><br>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					A级寝室<br>卫生要求
				</td>
				<td colspan="6" valign="bottom">
					<p align="left">
						<br>
						1、每天自觉保持寝室整齐、清洁，每次卫生抽查成绩不低于85分；<br> <br>
						2、保持室外走廊无垃圾、水迹；<br><br>
						3、每月15日、30日（节假日顺延）的卫生综合检查成绩不低于90分。
						<br><br>
					</p>
				</td>
			</tr>

			<tr>
				<td align="center" valign="middle">
					奖<br>惩<br>措<br>施
				</td>
				<td colspan="6" valign="bottom">

					<p align="left">
						<br>
						1、免于日常卫生检查，日常卫生成绩取各<bean:message key="lable.xsgzyxpzxy" />最高分；<br> <br>
						2、门上贴A级寝室标志； <br><br>
						3、党委学工部组织各<bean:message key="lable.xsgzyxpzxy" />、公寓建设办公室、楼层长，不定期进行抽查，
						<br><br>&nbsp;&nbsp;&nbsp;抽查不合格的寝室立即取消A级寝室。 <br><br>
						4、学源后勤公司宿管中心在日常宿舍管理中，发现有不符合A级寝室要求的行为，<br> <br>&nbsp;&nbsp;&nbsp;给予黄牌警告。<br> <br>
						5、连续一学期为A级寝室，给予全校通报表扬。<br>
						<br>
					</p>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					学<br>院<br>审<br>核<br>意<br>见
				</td>
				<td colspan="6" valign="bottom">
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<br>
					<p align="right">			
						审核人（签名）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						年&nbsp;&nbsp; 月&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;
					</p>
					<br>
				</td>
			</tr>
		</table>
		<p align="left" style="font-size: 14px">&nbsp;&nbsp&nbsp;&nbsp注：本表格一式两份：<bean:message key="lable.xsgzyxpzxy" />、申报寝室各一份。</p>		
	</div>
		<br>
				<div class="buttontool noprint" align="center">
					<input type="button" class="button2" value="页面设置"
						onclick="WebBrowser.ExecWB(8,1);">
					<input type="button" class="button2" value="打印预览"
						onclick="WebBrowser.ExecWB(7,1)">
					<input type="button" class="button2" value="直接打印"
						onclick="WebBrowser.ExecWB(6,6)">
				</div>
	
</body>
</html:html>
