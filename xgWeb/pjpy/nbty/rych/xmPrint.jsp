<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="../skin1/style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
		</style>
	<body>
	<center>
		<div align="center">
			<h3>宁波天一职业技术学院<logic:notEmpty name="rs"><bean:write name="rs" property="xn" /></logic:notEmpty>学年度</h3> 
		</div>
		<h2>
		<div align="center" style=" font-size:23px;">
			<logic:notEmpty name="rs" >
				<bean:write name="rs" property="rychmc" />登记表
			</logic:notEmpty>
			<logic:empty name="rs">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登记表
			</logic:empty>
		</div>
		</h2>
		<table class="tbstyle" height="750">
			<tr>
				<td width=5%>
					姓名
				</td>
				<td width=7%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xm" />
					</logic:notEmpty>
				</td>
				<td width=5%>
					性别
				</td>
				<td width=7%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xb" />
					</logic:notEmpty>
				</td>
				<td width=9%>
					出生年月
				</td>
				<td width=5%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="csrq" />
				</td>
				</logic:notEmpty>
				<td width=8%>
					民族
				</td>
				<td width=8%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="mzmc" />
					</logic:notEmpty>
				</td>
				<td width=8%>
					籍贯
				</td>
				<td width=8%>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="jg" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td >
					政治面貌
				</td>
				<td colspan=2>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zzmmmc" />
					</logic:notEmpty>
				</td>
				<td>
					现任职务
				</td>
				<td colspan="2">
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xrzw" />
					</logic:notEmpty>
				</td>
				<td colspan=2>
					 现任职时间
				</td>
				<td colspan=2>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="rxzsj" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td height=7%>
					专业及班级
				</td>
				<td colspan=3>
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zymc" /><br/>
					</logic:notEmpty>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="bjmc" />
					</logic:notEmpty>
				</td>
				<td>
					综合素质
				</td>
				<td>
					&nbsp;
				</td>
				<td colspan=2>
					学年平均成绩
				</td>
				<td colspan=2>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="xnpjcj" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr height=10%>
				<td colspan=2>
					获奖名称及时间
				</td>
				<td colspan=8>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="hjsjmc" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr height=25% width=5%>
				<td align=center>
					主
					<br>
					要
					<br>
					事
					<br>
					迹
				</td>
				<td colspan=9>
					&nbsp;
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zysj" />
					</logic:notEmpty>
				</td>
			</tr>
			<tr height=25% width=5%>
				<td align=center>
					班
					<br>
					主
					<br>
					任
					<br>
					意
					<br>
					见
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        日期:</span>
				</td>
				<td align=center width=5%>
					分
					<br>
					院
					<br>
					系
					<br>
					意
					<br>
					见
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        日期:</span>
				</td>
			</tr>
			<tr height=25%>
				<td align=center >
					学
					<br>
					生
					<br>
					处
					<br>
					意
					<br>
					见
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        日期:</span>
				</td>
				<td align=center  width=5%>
					学
					<br>
					院
					<br>
					意
					<br>
					见
				</td>
				<td colspan=4>
					&nbsp;
						<span style="float:right; " ><br><br><br>盖章:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                        日期:</span>
				</td>
			</tr>
		</table>
		</center>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='页面设置'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='打印预览'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='直接打印'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
	</body>
</html>
