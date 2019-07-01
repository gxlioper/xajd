<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<html:html>
<head>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<style>
		.radic {
		position:relative;
		}
		.radic em {
		position:absolute;
		top:3px; 
		left:-4px;
		font-family:Arial;
		font-size:22px;
		}
		</style>
</head>
<body>

	<p align=center>
		<b><span
			style='font-size:19.0pt;font-family:宋体;letter-spacing:1.0pt'>“中天集团爱心公益基金”助学金申请表</span>
		</b>
	</p>
	<br />
	<br />
	<table class="printtab"
		style="font-family:仿宋_GB2312;font-size:16px;width:100%">
		<tr style="">
			<td align="center" valign="center">
				姓名
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.xm }
			</td>
			<td align="center" valign="center">
				性别
			</td>
			<td align="center" valign="center" >
				&nbsp;${rs.xb }
			</td>
			<td align="center" valign="center" >
				籍贯
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.jg }
			</td>
			<td align="center" valign="center">
				政治<br/>面貌
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.zzmmmc }
			</td>
		</tr>
		<tr style="">
			<td align="center" valign="center">
				<bean:message key="lable.xb" />
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.xymc}
			</td>
			<td align="center" valign="center">
				专业<br/>年级
			</td>
			<td align="center" valign="center">
				&nbsp;${rs.nj}&nbsp;${rs.zymc}
			</td>
			<td align="center" valign="center" >
				联系<br/>电话
			</td>
			<td align="center" valign="center" colspan=3>
				&nbsp;${rs.lxdh}
			</td>
		</tr>
		<tr style="">
			<td align="center" valign="center">
				德育<br/>成绩
			</td>
			<td align="center" valign="center">
				&nbsp;
			</td>
			<td align="center" valign="center">
				综合测<br/>评排名
			</td>
			<td align="center" valign="center">
				&nbsp;
			</td>
			<td align="center" valign="center" >
				高考<br/>成绩
			</td>
			<td align="center" valign="center" colspan=3>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center" valign="center" height="270px">
				家庭
				<br />
				个人
				<br />
				基本
				<br />
				情况与
				<br />
				主要
				<br />
				申请
				<br />
				理由
			</td>
			<td colspan=8>
				<p style="height: 200px">（含家庭成员、家庭年收入、家庭经济贫困情况等）</p>
				<p align="right">本人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<br/>
				<p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td align="center" valign="center">
				当年其
				<br />
				他主要
				<br />
				已受助
				<br />
				项目及
				<br />
				金额
			</td>
			<td colspan=8>
				<br/>
				无&nbsp;□
				<br />
				有&nbsp;□
				<br />
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;1、曾受助项目名称<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>已受助金额：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>元
				</p>
				<p>
					&nbsp;&nbsp;&nbsp;&nbsp;2、曾受助项目名称<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>已受助金额：<U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</U>元
				</p>
				<br/>
			</td>
		</tr>
		<tr height="100px">
			<td align="center" valign="center">
				学 院
				<br />
				意 见
			</td>
			<td colspan=8>
				<p style="height:80px"></p>
				<p align="right">（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p align="right"> 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</td>
		</tr>
		<tr height="100px">
			<td align="center" valign="center">
				学 校
				<br />
				意 见
			</td>
			<td colspan=8>
				<p style="height: 60px"></p>
				<p align="right">（盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p align="right"> 年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			</td>
		</tr>
		<tr height=0 >
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
			<td width="11%"></td>
		</tr>
	</table>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
