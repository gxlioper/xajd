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
			浙江理工大学特色寝室申报表
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
					<bean:write name="rs" property="sqsj" />&nbsp;
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
					特色寝室<br>
					要求


				</td>
				<td colspan="6" valign="bottom">
					<p align="left">
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;在文明寝室的基础上，特色寝室建设是当代大学生精神面貌和文化素养的综合反映，是校园文化建设的重要组成部分，是公寓文化的主旋律，有利于广大学生的发展与成才。参考题材为：<br>
						&nbsp;&nbsp;&nbsp;&nbsp;1、结合世界观的自我改造，对马列主义、毛泽东思想、邓小平理论和“三个代表”的重<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要思想进行系统学习和理论研究。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;2、结合专业特点，对本专业、交叉专业、新兴专业进行科学研究。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;3、结合文化学习，对中国文化、世界文化中的一些先进性内容进行专门学习和研究。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;4、结合大学生综合素质培养，持之以恒地开展某项文娱体育活动，并取得一定成效。<br>
						&nbsp;&nbsp;&nbsp;&nbsp;5、反映时代精神和提高创新能力的主题鲜明的其他项目。<br>

						<br><br>
					</p>
				</td>
			</tr>

			<tr>
				<td align="center">
					<br>开展特色<br>寝室建设<br>的具体工<br>作内容<br>

				</td>
				<td colspan="6" valign="top">			
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="jtgznr" />
					<br><br>
				</td>
			</tr>
			<tr>
				<td align="center">
					<br>
					现已开展<br>的工作情<br>况
					（可附页)
					<br>
				</td>
				<td colspan="6" valign="top">
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="ykzgzqk" />
					<br><br>				
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br>
					指导老师<br>的基本情<br>况及特色<br>设计论证<br>
					（可附页）
					<br>
				</td>
				<td colspan="6 valign="bottom">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br><br>
					学<br>
					院<br>
					推<br>
					荐<br>
					意<br>
					见<br>
					<br><br>

				</td>
				<td colspan="6 valign="bottom">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle">
					<br><br>
					委<br>
					学<br>
					工<br>
					部<br>
					审<br>
					核<br>
					意<br>
					见<br>
					<br><br>
				</td>
				<td colspan="6 valign="bottom">
					&nbsp;
				</td>
			</tr>
		</table>		
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
