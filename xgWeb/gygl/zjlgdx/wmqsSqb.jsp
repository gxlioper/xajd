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
			浙江理工大学文明寝室申请表
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
					${rs.qsz }
				</td>
				<td align=center width="15%">
					寝室成员
				</td>
				<td align="left" colspan="2">
					<logic:present name="rs" property="qscy">
						<logic:iterate id="s" name="rs" property="qscy">
							<bean:write name="s"/>&nbsp;&nbsp;
						</logic:iterate>
					</logic:present>
					&nbsp;
				</td>
				<td align=center>
					申报日期
				</td>
				<td align="center" width="15%">
					<bean:write name="rs" property="sqsj" />
				</td>
			</tr>
			<tr>
				<td align=center>
					申报条件
				</td>
				<td align="left" colspan="6" valign="middle">
					&nbsp;
					<logic:present name="rs" property="qstj">
								&nbsp;${rs.qstj.xn}学年&nbsp;&nbsp;${rs.qstj.xq}&nbsp;&nbsp;学期第<bean:write name="rs" property="zs" />周被批准为A级寝室<br>
					</logic:present>
					
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					文明寝室<br>
					基本要求

				</td>
				<td colspan="6" valign="bottom">
					<p align="left">
						<br>
						
						1、卫生复查分达45分以上（按月考核）；<br>
						2、日常文明规范考核达45分以上（按月考核）。<br>
						   做到：<br>
							（1）遵纪守法、诚实守信； （2）勤奋学习、刻苦钻研；<br>
							（3）语言文明、礼貌待人； （4）艰苦奋斗、勤俭节约；<br>
							（5）服饰整洁、举止得体； （6）尊敬师长、敬老爱幼；<br>
							（7）团结同学、热爱集体； （8）助人为乐、弘扬正气；<br>
							（9）爱护公物、维护秩序； （10）爱国爱校、奉献社会。
						<br><br>
					</p>
				</td>
			</tr>
			<tr>
				<td align="center" >
					<br><br>
					申请<br>
					理由<br>
					陈述<br>
					<br><br><br>
				</td>
				<td colspan="6" align="left" valign="top">
					<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sqly"/><br>
				</td>
			</tr>

			<tr>
				<td align="center" valign="middle">
					学  院<br>
					审核意见

				</td>
				<td colspan="6" valign="bottom">			
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
				</td>
			</tr>
			<tr>
				<td align="center" >
					卫生检查<br>
					成绩
				</td>
				<td colspan="3">
					<br>
					<br>
					<br>
					<br>
					<p align="right">
						考核人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						年&nbsp;&nbsp; 月&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;
					</p>				
				</td>
				<td align="center" >
					文明规范<br>
					考核成绩
				</td>
				<td colspan="2">
					<br>
					<br>
					<br>
					<p align="right">
						考核人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						年&nbsp;&nbsp; 月&nbsp;&nbsp; 日 &nbsp;&nbsp;&nbsp;
					</p>				
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					公寓建设<br>
					办审核<br>意见
				</td>
				<td colspan="6 valign="bottom">
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
		<p align="left" style="font-size: 14px">&nbsp;&nbsp&nbsp;&nbsp注：本表格一式三份：公寓建设办、<bean:message key="lable.xsgzyxpzxy" />、申报寝室各执一份。</p>		
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
