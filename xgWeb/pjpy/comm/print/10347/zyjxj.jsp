<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=GBK">
		<title>湖州师范学院奖学金申请表</title>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext></object>
		<style media='print'>
			.noPrin {display: none;}
		</style>
	</head>
	<body>
		<center>
			<p>
				<span style="font-size: 26px; font-family: 宋体">湖州师范学院奖学金申请表</span>
			</p>
		</center>
			<table border="0" width="652px" class="printtab">
				<tr>
					<td width=80>
						<p><b><span>姓名</span></b></p>
					</td>
					<td width=91 colspan=2>
						${rs.xm }
					</td>
					<td width=49 colspan=2>
						<p><b><span>性别</span></b></p>
					</td>
					<td width=63>
						${rs.xb }
					</td>
					<td width=53>
						<p><b><span><bean:message key="lable.xb" /></span></b></p>
					</td>
					<td width=87 colspan=2>
						${rs.xymc }
					</td>
					<td width=67>
						<p><b><span>班级</span></b></p>
					</td>
					<td width=87 valign=top>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
        			<td width=80>
        				<p><b><span>政治面貌</span></b></p>
        			</td>
       				<td width=91 colspan=2>
       					${rs.zzmmmc }
       				</td>
        			<td width=49 colspan=2>
        				<p><b><span>职务</span></b></p>
        			</td>
        			<td width=157 colspan=2>
        				${rs.xszw }
        			</td>
        			<td width=119>
        				<p><b><span>申请奖学金类别</span></b></p>
        			</td>
        			<td colspan=3>
        				${rs.xmmc }
        			</td>
      			</tr>
				<tr>
					<td colspan=11 valign=top>
						<p>
							<b><span>申请理由：</span></b>
						</p>
						<br/>${rs.sqly }<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
						<p align="right">
							<b><span>签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
						</p>
						<br/>
						<p align="right">
							<b><span>日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
						</p>
					</td>
				</tr>
				<tr>
					<td width=182 colspan=4 valign=top>
						<p>
							<b><span>班委、团支部意见：</span>
							</b>
						</p>
					</td>
					<td width=221 colspan=4 valign=top>
						<p>
							<b><span>下属<bean:message key="lable.xb" />评比小组意见：</span></b>
						</p>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<p align="right">
							<b><span>（章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </b>
						</p>
						<br/>
						<p align="right">
							<b><span>日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
						</p>
					</td>
					<td width=175 colspan=3 valign=top>
						<p>
							<b><span>学校审核意见：</span></b>
						</p>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<p align="right">
							<b><span>（章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </b>
						</p>
						<br/>
						<p align="right">
							<b><span>日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></b>
						</p>
					</td>
				</tr>
				<tr>
					<td width=92 colspan=2>
						<p style='text-align: center'>
							<b><span>备注</span></b>
						</p>
					</td>
					<td width=485 colspan=9>
						&nbsp;
					</td>
				</tr>
			</table>
			<div style="width:652px; margin-top: 5px; margin:auto;" align="center">
				<p align="left">
					<b><span>注</span></b><b><span>：此表一式两份，一份<bean:message key="lable.xb" />留存，存入学生档案，一份由学校留存，用黑色钢笔填写或打印。</span></b>
				</p>
				<p align="right">
					<b><span>湖州师范学院学生处制表</span> </b>
				</p>
			</div>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>