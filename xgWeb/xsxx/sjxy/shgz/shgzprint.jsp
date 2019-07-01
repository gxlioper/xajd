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
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>三江<bean:message key="lable.xsgzyxpzxy" />学生在校期间担任社会工作履历表</title>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">

		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>

		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt' align="center">
			<p align=center style='text-align:center'>
				<b><span
					style='font-size:16.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>三江<bean:message key="lable.xsgzyxpzxy" />学生在校期间担任社会工作履历表</span>
				</b>
			</p>
			<table class="tbstyle">
				<tr>
					<td width=69 valign="middle" align="center" class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>姓</span><span
								style='font-size:14.0pt'> </span><span
								style='font-size:14.0pt;font-family:
  宋体;&quot;Times New Roman&quot;'>名</span>
						</p>
					</td>
					<td width=89 colspan=2 valign="middle" align="center"
						class="Normal">
						${shgz.xm }
					</td>
					<td width=112 valign="middle" align="center" class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>院</span><span
								lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp; </span><span
								style='font-size:14.0pt;font-family:宋体;
  &quot;Times New Roman&quot;'>系</span>
						</p>
					</td>
					<td width=95 valign="middle" align="center" class="Normal">
						${shgz.xymc }
					</td>
					<td width=123 colspan=2 valign="middle" align="center"
						class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>学</span><span
								lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span
								style='font-size:14.0pt;font-family:宋体;
  &quot;Times New Roman&quot;'>号</span>
						</p>
					</td>
					<td width=128 valign="middle" align="center" class="Normal">
						${shgz.xh }
					</td>
				</tr>
				<tr>
					<td width=69 valign="middle" align="center" class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>性</span><span
								style='font-size:14.0pt'> </span><span
								style='font-size:14.0pt;font-family:
  宋体;&quot;Times New Roman&quot;'>别</span>
						</p>
					</td>
					<td width=89 colspan=2 valign="middle" align="center"
						class="Normal">
						${shgz.xb }
					</td>
					<td width=112 valign="middle" align="center" class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>联系方式</span>
						</p>
					</td>
					<td width=95 valign="middle" align="center" class="Normal">
						${shgz.sjhm }
					</td>
					<td width=123 colspan=2 valign="middle" align="center"
						class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>辅导员姓名</span>
						</p>
					</td>
					<td width=128 valign="middle" align="center" class="Normal">
						${shgz.fdyxm }
					</td>
				</tr>
				<tr>
					<td width=114 colspan=2 rowspan="${rzRow+1 }" valign="middle"
						align="center" class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>任职情况</span>
						</p>
					</td>
					<td width=156 colspan=2 valign=top class="Normal">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>起止时间</span>
						</p>
					</td>
					<td width=95 valign=top class="Normal">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>任职部门</span>
						</p>
					</td>
					<td width=90 valign=top class="Normal">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>职</span><span
								lang=EN-US>&nbsp; </span><span
								style='font-family:宋体;
  &quot;Times New Roman&quot;'>务</span>
						</p>
					</td>
					<td width=161 colspan=2 valign=top class="Normal">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>鉴定部门（签章）</span>
						</p>
					</td>
				</tr>
				<logic:iterate id="rzmap" name="rzqk">
					<tr>
						<td width=156 colspan=2 valign=top class="Normal">
							<p>
								<span lang=EN-US>&nbsp; </span>
								<span style='font-family:宋体;&quot;Times New Roman&quot;'>${rzmap.rzkssj}</span>
								<span lang=EN-US>—&nbsp; </span>
								<span style='font-family:宋体;"Times New Roman"'>${rzmap.rzjssj}</span>
							</p>
						</td>
						<td width=95 valign=top class="Normal">
							${rzmap.rzbm }
						</td>
						<td width=90 valign=top class="Normal">
							${rzmap.zw }
						</td>
						<td width=161 colspan=2 valign=top class="Normal">
							${rzmap.jdbm }
						</td>
					</tr>
				</logic:iterate>

				<logic:lessThan value="10" name="rzSize">

					<logic:iterate id="s" name="str" offset="0" length="${10-rzSize}">
						<tr>
							<td width=156 colspan=2 valign=top class="Normal">
								<p>
									<span lang=EN-US>&nbsp; </span><span
										style='font-family:宋体;
  &quot;Times New Roman&quot;'>年</span><span
										lang=EN-US>&nbsp; </span><span
										style='font-family:宋体;
  &quot;Times New Roman&quot;'>月</span><span
										lang=EN-US>—&nbsp; </span><span
										style='font-family:宋体;"Times New Roman"'>年</span><span
										lang=EN-US>&nbsp; </span><span
										style='font-family:宋体;
  &quot;Times New Roman&quot;'>月</span>
								</p>
							</td>
							<td width=95 valign=top class="Normal">
								&nbsp;
							</td>
							<td width=90 valign=top class="Normal">
								&nbsp;
							</td>
							<td width=161 colspan=2 valign=top class="Normal">
								&nbsp;
							</td>
						</tr>
					</logic:iterate>
				</logic:lessThan>
				<tr>
					<td width=114 colspan=2 rowspan='${hjRow+1 }' valign="middle"
						align="center" class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>获奖情况</span>
						</p>
					</td>
					<td width=156 colspan=2 valign=top class="Normal">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>获奖时间</span>
						</p>
					</td>
					<td width=185 colspan=2 valign=top class="Normal">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>奖励类别</span>
						</p>
					</td>
					<td width=161 colspan=2 valign=top class="Normal">
						<p align=center style='text-align:center'>
							<span style='font-family:宋体;"Times New Roman"'>授奖部门</span>
						</p>
					</td>
				</tr>
				<logic:iterate id="hjmap" name="hjqk">
					<tr>
						<td width=156 colspan=2 valign=top class="Normal">
							<p>
								<span lang=EN-US>&nbsp; </span><span
									style='font-family:宋体;
  &quot;Times New Roman&quot;'>${hjmap.hjkssj
									}</span><span lang=EN-US>—&nbsp; </span><span
									style='font-family:宋体;"Times New Roman"'>${hjmap.hjjssj
									}</span>
							</p>
						</td>
						<td width=185 colspan=2 valign=top class="Normal">
							${hjmap.jllb }
						</td>
						<td width=161 colspan=2 valign=top class="Normal">
							${hjmap.sjbm }
						</td>
					</tr>
				</logic:iterate>
				<logic:lessThan value="10" name="hjSize">
					<logic:iterate id="s" name="str" offset="0" length="${10-hjSize}">
						<tr>
							<td width=156 colspan=2 valign=top class="Normal">
								<p>
									<span lang=EN-US>&nbsp; </span><span
										style='font-family:宋体;
  &quot;Times New Roman&quot;'>年</span><span
										lang=EN-US>&nbsp; </span><span
										style='font-family:宋体;
  &quot;Times New Roman&quot;'>月</span><span
										lang=EN-US>—&nbsp; </span><span
										style='font-family:宋体;"Times New Roman"'>年</span><span
										lang=EN-US>&nbsp; </span><span
										style='font-family:宋体;
  &quot;Times New Roman&quot;'>月</span>
								</p>
							</td>
							<td width=185 colspan=2 valign=top class="Normal">
								&nbsp;
							</td>
							<td width=161 colspan=2 valign=top class="Normal">
								&nbsp;
							</td>
						</tr>
					</logic:iterate>
				</logic:lessThan>
				<tr>
					<td width=114 colspan=2 valign="middle" align="center"
						class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;
  &quot;Times New Roman&quot;'>分团委</span>
						</p>
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;
  &quot;Times New Roman&quot;'>意见</span>
						</p>
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>（盖章）</span>
						</p>
					</td>
					<td width=502 colspan=6 class="Normal">
						<p valign="top" align="left">
							&nbsp;&nbsp;${shgz.ftwshyj }
						</p>
						<br />
						<br />
						<br />
						<br />
						<p valign="bottom" align="right">
							<span style='font-family:宋体;"Times New Roman"'>年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
				<tr>
					<td width=114 colspan=2 valign="middle" align="center"
						class="Normal">
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;
  &quot;Times New Roman&quot;'>校团委</span>
						</p>
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;
  &quot;Times New Roman&quot;'>意见</span>
						</p>
						<p>
							<span
								style='font-size:14.0pt;font-family:宋体;"Times New Roman";"Times New Roman"'>（盖章）</span>
						</p>
					</td>
					<td width=502 colspan=6 class="Normal">
						<p valign="top" align="left">
							&nbsp;&nbsp;${shgz.xtwshyj }
						</p>
						<br />
						<br />
						<br />
						<br />
						<p valign="bottom" align="right">
							<span style='font-family:宋体;"Times New Roman"'>年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
				<tr height=0>
					<td width=69 class="Normal"></td>
					<td width=45 class="Normal"></td>
					<td width=44 class="Normal"></td>
					<td width=112 class="Normal"></td>
					<td width=95 class="Normal"></td>
					<td width=90 class="Normal"></td>
					<td width=33 class="Normal"></td>
					<td width=128 class="Normal"></td>
				</tr>
			</table>
			<p align="right">
				<span
					style='font-size:14.0pt;font-family:宋体;
&quot;Times New Roman&quot;'>共青团三江<bean:message key="lable.xsgzyxpzxy" />委员会制&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</p>
		</div>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
	</body>
</html>
