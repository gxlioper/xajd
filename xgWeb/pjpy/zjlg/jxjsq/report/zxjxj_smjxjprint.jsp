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
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
	<script language="javascript" src="js/function.js"></script>
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
<body>
	<div colspan="8"/>
			<center>桑 麻 基 金 会 奖 学 金<br/>
						评  审  表
					</center>
	</div>
		<html:form action="/zjlgPjpy" method="post">

			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" colspan="1">
						<font color="red">*</font>奖学金名称
					</td>
					<td colspan="2" style="width: 200px">
						<bean:write name="rs" property="jxjmc"/>
					</td>
					<td colspan="1" scope="row" style="width: 200px">
						<div align="center">
							奖学金类别
						</div>
					</td>
					<td colspan="4"  style="width: 200px">
						<bean:write name="rs" property="jxjlbmc"/>
					</td>
				</tr>
				<tr>
						<td align="center" colspan="1">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xh"/>
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="4" scope="col">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							银行卡号
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhkh"/>
					</td>
					<td colspan="1">
						<div align="center">
							银行类型
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="yhlx"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							学年
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xn"/>
					</td>
					<td colspan="1">
						<div align="center">
							学期
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="xq"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmm"/>
					</td>
					<td>
						<div align="center">
							担任职务
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="drzw"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							生源地
						</div>
					</td>
					<td colspan="2">
<%--					<bean:write name="rs" property="jgs"/>--%>
<%--						<html:text name="rs" property="jgs" readonly="true"></html:text>--%>
						<bean:write name="rs" property="jg"/>
					</td>
					<td>
						<div align="center">
							特长
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="tc"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							农村城镇
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="nccz"/>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
					
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							综合测评名次
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zhszcpcjpm"/>
					</td>
					<td>
						<div align="center">
						专业名次
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="zhuanypm"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							申 报 等 级
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sbdj"/>
					</td>
					<td>
						<div align="center">
						第几次获得桑麻奖学金
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="djchdjxj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>获<br>奖<br>情<br>况<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="jfqk"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							香港<br>桑麻<br>基金<br>会简<br>介
						</div>
					</td>
					<td colspan="8">
						香港桑麻基金会是著名实业家、爱国人士、香港特别行政区大紫荆勋章获得
						<br>者查济民先生所创建。查济民先生是我国纺织界耆宿，自三十年代初至今，
						<br>为发展纺织工业倾注了大量心力，成绩卓著。他以一位成功实业家的经验和
						<br>智慧，看到科技进步和培养人才是促进我国纺织工业开拓和发展的根本问
						<br>题，因此，他于一九九二年率先出资成立了桑麻基金会，并亲自担任基金会
						<br>主席，旨在缅怀我国纺织工业起源于桑麻，用以激励国内纺织界从业人员及
						<br>在培学子发扬爱国主义精神，重视科技，努力钻研，振兴中华纺织事业，基
						<br>金会先后在天津纺织工<bean:message key="lable.xsgzyxpzxy" />、东华大学（原中国纺织大学）、浙江理工大学
						<br>（原浙江丝绸工<bean:message key="lable.xsgzyxpzxy" />）和北京服装<bean:message key="lable.xsgzyxpzxy" />设立了桑麻奖学金，对奖励先进、发
						<br>掘人才提供了有利条件，促进了纺织教育事业的发展。
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							本<br>人<br>申<br>请
						</div>
					</td>
					<td colspan="8">
					（本人申请应包括：本人德智体诸方面的表现、担任社会工作及参加社会活动情况、申请奖学金的动机及努力目标，并简要说明家庭情况。）
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<bean:write name="rs" property="sqly"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>班<br>级<br>辅<br>导<br>员<br>意<br>见<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="fdyyj"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br><bean:message key="lable.xsgzyxpzxy" /><br>审核<br>意见<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>桑麻<br>奖学<br>评金<br>审评<br>意审<br>见委<br>员会<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="lshshyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>备<br>注<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea property="bz" rows='6' style="width:100%;display: none"  />
					<bean:write name="rs" property="bz"/>
					1.本表一式二份，表中填写内容要求电脑打印；<br>
					2.学校、院、系班级不可缩写。
					
					</td>
				</tr>
			</table>
	<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
		</html:form>
</body>
</html:html>
