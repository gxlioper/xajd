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
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />

	<meta name="Copyright" content="正方软件 zfsoft" />

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<style media='print'>.noPrin{display:none}
	</style>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<body>
	<html:form action="/xbemyStuStatus.do" method="post">
		<p align="center">
			<strong>宁夏回族自治区普通高等学校学生转专业申请（备案）表 </strong>
		</p>
		<div>西北第二民族<bean:message key="lable.xsgzyxpzxy" />用表</div>	
		<div align="right">宁教普学籍字 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 号</div>
		<table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			<tr>
				<td width="43">
					<p align="center">
						姓名
					</p>
				</td>
				<td width="65" colspan="2">
					<p align="center">
						${rs.xm}
					</p>
				</td>
				<td width="48">
					<p align="center">
						性别
					</p>
				</td>
				<td width="48">
					<p align="center">
						${rs.xb}
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						民族
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						${rs.mzmc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						学 &nbsp;&nbsp; 校
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${xxmc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
					<p align="center">
						入学时间
					</p>
				</td>
				<td width="65" colspan="2">
					<p align="center">
						${rs.rxsj}
					</p>
				</td>
				<td width="48">
					<p align="center">
						学号
					</p>
				</td>
				<td width="168" colspan="5">
					<p align="center">
						${rs.xh}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						准考证号
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${rs.zkzh}
					</p>
				</td>
			</tr>
			<tr>
				<td width="108" colspan="3">
					<p align="center">
						录取专业
					</p>
				</td>
				<td width="216" colspan="6">
					<p align="center">
						${rs.zczymc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						转入专业
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${rs.zrzymc}
					</p>
				</td>
			</tr>
			<tr>
				<td width="108" colspan="3">
					<p align="center">
						转出年级
					</p>
				</td>
				<td width="216" colspan="6">
					<p align="center">
						${rs.zcnj}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						转入年级
					</p>
				</td>
				<td width="187" colspan="3">
					<p align="center">
						${rs.zrnj}
					</p>
				</td>
			</tr>
			<tr>
				<td width="108" colspan="3">
					<p align="center">
						学 &nbsp;&nbsp; &nbsp; 制
					</p>
				</td>
				<td width="48">
					<p align="center">
						${rs.zcxz}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						学历层次
					</p>
				</td>
				<td width="96" colspan="3">
					<p align="center">
						${rs.zcxlcc}
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						学 &nbsp;&nbsp;&nbsp; 制
					</p>
				</td>
				<td width="48">
					<p align="center">
						${rs.zrxz}
					</p>
				</td>
				<td width="72">
					<p align="center">
						学历层次
					</p>
				</td>
				<td width="67">
					<p align="center">
						${rs.zrxlcc}
					</p>
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						转专业申请（理由）
					</p>
				</td>
				<td width="499" colspan="12" valign="top">
					<p align="center" style="height:45px ">
						${rs.sqly}
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						申请人： ${rs.xm}
					</p>
					<p align="center">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp; ${rs.dqn}年 &nbsp;&nbsp; ${rs.dqy}月 &nbsp;&nbsp; ${rs.dqr}日
					</p>
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						转出专业院（系） 意见
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; 负责人签字：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日
				</td>
				<td width="84" colspan="2">
					<p align="center">
						转入专业 院（系）意见
					</p>
				</td>
				<td width="216" colspan="4" valign="top">
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; 负责人签字：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日					
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						招就处 &nbsp; 意见
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; 负责人签字：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日
				</td>
				<td width="84" colspan="2">
					<p align="center">
						财务处 &nbsp; 意见
					</p>
				</td>
				<td width="216" colspan="4" valign="top">
					<br/>
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; 负责人签字：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						教务处 &nbsp; 意见
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
						&nbsp;&nbsp;&nbsp; 负责人签字：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日					
				</td>
				<td width="84" colspan="2">
					<p align="center">
						学生处 &nbsp; 意见
					</p>
				</td>
				<td width="216" colspan="4" valign="top">
					<br/><br/><br/>
						&nbsp;&nbsp;&nbsp; 负责人签字：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日					
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
					<p align="center">
						高等学校审批意见
					</p>
				</td>
				<td width="199" colspan="6" valign="top">
					<br/>
					<br/>
					<br/>
						主管校长意见：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日
				</td>
				<td width="84" colspan="2">
						自治区教育厅备案意见
				</td>
				<td width="216" colspan="4" valign="top">
					<br/>
						经办人：
					<br/>
					<br/>
						处 &nbsp; 长：
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; （公章）
					<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年
						&nbsp;&nbsp; 月 &nbsp;&nbsp; 日					
				</td>
			</tr>
			<tr height="50px">
				<td width="84" colspan="2">
						备注
				</td>
				<td width="499" colspan="12" valign="top">
				<br/>
						&#149;&nbsp; 办理转专业手续时携带此表及招生录检表复印件，加盖学生学籍管理部门印章。
					<br/>
						&#149;&nbsp; 此表签批后，原件 存学生 处，教务处、财务处、招就处、学生所在<bean:message key="lable.xsgzyxpzxy" />及本人各持复印件一份
					<br/>
						&#149;&nbsp; 学历层次是指博士、硕士、本科、专科（高职）。
				</td>
			</tr>
		</table>
	</html:form>
</body>
<div class='noPrin' align="center">
	<input type='button' class='button2' value='页面设置'
		onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='打印预览'
		onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='直接打印'
		onclick="WebBrowser.ExecWB(6,6);return false;">
</div>
</html:html>

