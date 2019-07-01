<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<logic:iterate name="rsList" id="rs">
		<center>
			<p>
				<span style="font-size:18.0pt;font-family:宋体"><B>北京联合大学${rs.xmmc}登记表</B>
				</span>
			</p>
		</center>
		<table class="printtab" width="90%">
			<tr style="height: 45px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>姓名</span>
					</p>
				</td>
				<td width=11% align=center>
					&nbsp; ${rs.xm }
				</td>
				<td width=11% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>性</span> &nbsp;
						<span style='font-family:宋体;"Times New Roman"'>别</span>
					</p>
				</td>
				<td width=11% align=center>
					&nbsp;${rs.xb }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center;'>
						<span style='font-family:宋体;"Times New Roman"'>专业</span>
					</p>
				</td>
				<td width=28% colspan=2 align=center>
					&nbsp;${rs.zymc }
				</td>
			</tr>
			<tr style="height: 45px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>民族</span>
					</p>
				</td>
				<td align=center>
					&nbsp;${rs.mzmc }
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>政治面貌</span>
					</p>
				</td>
				<td align=center>
					&nbsp; ${rs.zzmmmc }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>学号</span>
					</p>
				</td>
				<td width=28% colspan=2 align=center>
					&nbsp;${rs.xh }
				</td>
			</tr>
			<tr style="height: 45px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>评奖学年</span>
					</p>
				</td>
				<td align=center>
					&nbsp;${rs.pjxn }
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>联系方式</span>
					</p>
				</td>
				<td align=center style="word-break:break-all;">
					${rs.sjhm }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>班级</span>
					</p>
				</td>
				<td width=28% align=center colspan=2>
					&nbsp;${rs.bjmc }
				</td>
			</tr>
			<tr style="height: 45px">
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>所在<bean:message key="lable.xb" /></span>
					</p>
				</td>
				<td  align=center colspan=3>
					&nbsp;${rs.xymc }
				</td>
				<td width=14% align=center>
					<p>
						<span style='font-family:宋体;
  &quot;Times New Roman&quot;'>推荐等级</span>
					</p>
				</td>
				<td width=28% align=center colspan=2>
					&nbsp;${rs.tjdj}
				</td>
			</tr>
			<tr style="height: 45px">
				<td  align=center rowspan=2>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>综合测评信息</span>
					</p>
				</td>
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>德育成绩</span>
					</p>
				</td>
				<td align=center>
					&nbsp;${rs.zd2 }
				</td>
				<td align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>学业成绩</span>
					</p>
				</td>
				<td width=14% align=center>
					&nbsp;${rs.zd3}
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>文体成绩</span>
					</p>
				</td>
				<td width=14% align=center>
					&nbsp;${rs.zd4 }
				</td>
			</tr>
			<tr style="height: 45px">
				<td  align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>综合测评<br/>总分</span>
					</p>
				</td>
				<td align=center colspan=3>
					&nbsp;${rs.zd1 }
				</td>
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>综合测评班级排名</span>
					</p>
				</td>
				<td width=14% align=center>
					&nbsp;${rs.zcfbjpm }
				</td>
			</tr>
			<tr style="height: 450px">
				<td width=14% align=center>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>申</span>
					</p>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>请</span>
					</p>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>理</span>
					</p>
					<p align=center style='text-align:center'>
						<span style='font-family:宋体;"Times New Roman"'>由</span>
					</p>
				</td>
				<td  colspan=6 valign=top>
					<p>
						<span style='font-family:宋体;
  &quot;Times New Roman&quot;'>个人主要事迹：<br />&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly} </span>
					</p>
				</td>
			</tr>
			<tr style="height:150px">
				<td colspan=4>
					<p align="left" style="vertical-align: top;height: 80px">
						<span><bean:message key="lable.xb" />意见<br /> &nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj} </span>
					</p>

					<p align="right" style="vertical-align: bottom;">
						<span style='font-family:宋体'>盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</p>
				</td>

				<td colspan=3>
					<p align="left" style="vertical-align: top;height: 80px">
						<span>学校意见<br /> &nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj } </span>
					</p>
					<p align="right" style="vertical-align: bottom;">
						<span style='font-family:宋体'>盖&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</p>
				</td>
			</tr>
		</table>
		<p>
			<span style='font-family:宋体;&quot;Times New Roman&quot;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：此表一式两份，可复制、打印。</span>
		</p>
			<logic:equal name="rs" property="isEnd" value="no">
				<div style='page-break-before:always;'>&nbsp;</div>
			</logic:equal>

		</logic:iterate>
	</body>
</html>
