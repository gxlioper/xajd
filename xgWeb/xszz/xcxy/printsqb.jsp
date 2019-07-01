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
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
<style media='print'>
	.noPrin{
	display:none;}
</style>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>		
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
	
	</script>
	
</head>

<body>
	<html:form action="xcxyXszz.do?method=printSqb" method="post">
		<div id="titName">
			<logic:notEqual value="国家助学金" name="rs" property="xmlc">
				<div align="center">
					<h3><bean:write name='rs'
							property="xmmc" />申请审批表（<bean:write name="LoginXn" />学年）</h3>
				</div>
				<div align="center">
					<b>学校：</b>
					<bean:write name='rs' property="xxmc" />
					&nbsp;&nbsp;
					<b><bean:message key="lable.xsgzyxpzxy" />：</b>
					<bean:write name='rs' property="xymc" />
					&nbsp;&nbsp;
					<b>专业：</b>
					<bean:write name='rs' property="zymc" />
					&nbsp;&nbsp;
					<b>班级：</b>
					<bean:write name='rs' property="bjmc" />
				</div>
				<br>
			</logic:notEqual>
			<logic:equal value="国家助学金" name="rs" property="xmlc">
				<div align="center">
					<h3><bean:write name='rs'
							property="xmmc" />申请表</h3>
				</div>
				<br>
			</logic:equal>
		</div>
		<table class="tbstyle" width="100%" id="theTable">
			<logic:notEqual value="国家助学金" name="rs" property="xmlc">
				<tr align="center" height="35">
					<td rowspan="4" width="4%">
						基
						<br>
						本
						<br>
						情
						<br>
						况
					</td>
					<td width="16%">
						姓名
					</td>
					<td width="16%">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="16%">
						性别
					</td>
					<td width="16%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="16%">
						出生年月
					</td>
					<td width="16%">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr height="35">
					<td align="center">
						学号
					</td>
					<td align="center">
						<bean:write name="rs" property="xh" />
					</td>
					<td align="center">
						民族
					</td>
					<td align="center">
						<bean:write name="rs" property="mzmc" />
					</td>
					<td align="center">
						入学时间
					</td>
					<td align="center">
						<bean:write name="rs" property="rxrq" />
					</td>
				</tr>
				<tr height="35">
					<td align="center">
						身份证号
					</td>
					<td align="center" colspan="5">
						<bean:write name="rs" property="sfzh" />
					</td>

				</tr>
				<tr height="35">
					<td align="center">
						政治面貌
					</td>
					<td align="center">
						<bean:write name="rs" property="zzmmmc" />
					</td>
					<td align="center" colspan="2">
						联系电话
					</td>
					<td align="center" colspan="2">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<logic:equal value="国家励志奖学金" name="rs" property="xmlc">
					<tr height="35" align="center">
						<td rowspan="3">
							家<br>庭<br>经<br>济<br>情<br>况
						</td>
						<td align="center">
							家庭户口
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="hkxz" />
						</td>
						<td align="center">
							家庭人口总数
						</td>
						<td>
							<bean:write name="rs" property="jtzrs" />
						</td>
					</tr>
					<tr height="35">
						<td align="center">
							家庭月总收入
						</td>
						<td align="center">
							<bean:write name="rs" property="jtysr" />
						</td>
						<td align="center">
							人均月收入
						</td>
						<td align="center">
							<bean:write name="rs" property="rjysr" />
						</td>
						<td align="center">
							收入来源
						</td>
						<td align="center">
							<bean:write name="rs" property="srly" />
						</td>
					</tr>
					<tr height="35">
						<td align="center">
							家庭地址
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="jtdz" />
						</td>
						<td align="center">
							邮政编码
						</td>
						<td align="center">
							<bean:write name="rs" property="yzbm" />
						</td>
					</tr>
				</logic:equal>
				<tr align="center" height="35">
					<td>
						学
						<br>
						习
						<br>
						情
						<br>
						况
					</td>
					<td colspan="6" align="left">
						<br>
						该学年必修课程数
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门，其中，优秀
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门，良好
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>门
						<br><br>
						成绩排名（<bean:write name="rs" property="jsxx" />按专业）：
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="pjcjpm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数），综合考评成绩
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zcj" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>分，<br><br>排名
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zcjpm" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>（名次/总人数）
						<br>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						获
						<br>
						奖
						<br>
						情
						<br>
						况
						<br>
						<br>
					</td>
					<td colspan="6" align="left">
						<br>
						主要奖项：
						<br><br>
						<div style="line-height: 28px;"><bean:write name="rs" property="chjl" /></div>
						<br>
						其中，院级奖励
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项； 校级奖励
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项； 省级以上奖励
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>项
						<br>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						申
						<br>
						请
						<br>
						理
						<br>
						由
						<br>
						︵
						<br>
						全
						<br>
						面
						<br>
						反
						<br>
						映
						<br>
						德
						<br>
						智
						<br>
						体
						<br>
						美
						<br>
						情
						<br>
						况
						<br>
						︶
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</td>
					<td colspan="6" align="left" style="vertical-align: top;">
						<br>
						<div style="line-height: 28px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sqly" /></div>
						<br>
						
						<div align="right">
							申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						<br>
						年
						<br>
						级
						<br>
						︵
						<br>
						专
						<br>
						业
						<br>
						︶
						<br>
						推
						<br>
						荐
						<br>
						意
						<br>
						见
						<br>
						<br>
						<br>
					</td>
					<td colspan="6">
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div align="right">
							推荐人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行政职务：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						<br>
						院
						<br>
						︵
						<br>
						系
						<br>
						︶
						<br>
						意
						<br>
						见
						<br>
						<br>
						<br>
					</td>
					<td colspan="6">
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div align="right">
							（公
							章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						<br>
						<br>
						学
						<br>
						校
						<br>
						意
						<br>
						见
						<br>
						<br>
					</td>
					<td colspan="6" align="left">
						<br>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经评审，并在
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;</u>范围内公示
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>天，无异议，现报请
						<br><br>
						同意该同学获得
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write
								name="LoginXn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>学年度国家奖学金。<br><br>
						<div align="right">
							（公
							章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
			</logic:notEqual>
			<logic:equal value="国家助学金" name="rs" property="xmlc">
				<tr align="center" height="35">
					<td rowspan="4" width="4%">
						本
						<br>
						人
						<br>
						情
						<br>
						况
					</td>
					<td width="12%">
						姓名
					</td>
					<td width="12%">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="12%">
						性别
					</td>
					<td width="12%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="12%">
						出生年月
					</td>
					<td width="12%">
						<bean:write name="rs" property="csrq" />
					</td>
					<td width="12%">
						民族
					</td>
					<td width="12%">
						<bean:write name="rs" property="mzmc" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						学号
					</td>
					<td colspan="4">
						<bean:write name="rs" property="xh" />
					</td>

					<td>
						入学时间
					</td>
					<td colspan="2">
						<bean:write name="rs" property="rxrq" />
					</td>
				</tr>
				<tr align="left" height="35">
					<td colspan="8">
						<bean:write name="rs" property="xxmc" />
						大学&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="xymc" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="zymc" />
						系&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						班
					</td>
				</tr>
				<tr height="35">
					<td align="left" colspan="8">
						曾获何种奖励
						<br>
						<div style="line-height: 28px;"><bean:write name="rs" property="chjl" /></div>
					</td>

				</tr>
				<tr align="center" height="35">
					<td rowspan="3">
						家
						<br>
						庭
						<br>
						经
						<br>
						济
						<br>
						情
						<br>
						况
					</td>
					<td>
						家庭户口
					</td>
					<td colspan="4">
						<bean:write name="rs" property="hkxz" />
					</td>
					<td>
						家庭人口总数
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtzrs" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td>
						家庭月总收入
					</td>
					<td>
						<bean:write name="rs" property="jtysr" />
					</td>
					<td colspan="2">
						人均月收入
					</td>
					<td>
						<bean:write name="rs" property="rjysr" />
					</td>
					<td>
						收入来源
					</td>
					<td colspan="2">
						<bean:write name="rs" property="srly" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td align="center">
						家庭地址
					</td>
					<td align="left" colspan="4">
						<bean:write name="rs" property="jtdz" />
					</td>
					<td align="center">
						邮政编码
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>

				<tr align="center" height="35">
					<td rowspan="7">
						家
						<br>
						庭
						<br>
						成
						<br>
						员
						<br>
						情
						<br>
						况
					</td>
					<td colspan="2">
						姓名
					</td>
					<td>
						年龄
					</td>
					<td colspan="2">
						与本人关系
					</td>
					<td colspan="3">
						工作或学习单位
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy1_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy1_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy1_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy2_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy2_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy2_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy3_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy3_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy3_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_xm" />
					</td>
					<td>
						<bean:write name="rs" property="jtcy4_nl" />
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jtcy4_gx" />
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtcy4_dw" />
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr align="center" height="35">
					<td colspan="2">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td colspan="2">
						&nbsp;
					</td>
					<td colspan="3">
						&nbsp;
					</td>
				</tr>
				<tr height="35">
					<td colspan="9">
						申请理由:
						<br>
						<div style="line-height: 28px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="sqly" /></div>
						<br>
						<br>
						
						<div align="right">
							申请人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr height="35">
					<td colspan="9">
						学校审核意见：
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="xxshyj" />
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div align="right">
							（公
							章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
						<div align="right">
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br>
					</td>
				</tr>
			</logic:equal>
		</table>
		<br>
		<div align=center class='noPrin'>
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
