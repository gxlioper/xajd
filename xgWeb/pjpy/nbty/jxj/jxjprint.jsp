<%@ page language="java" pageEncoding="GBK"%>

<html>
	<head>
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
		<title><bean:message key="lable.xsgzyxpzxy" />����Ա���������䱸�������</title>
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt' align="center">
			<p align=center style='text-align:center;'>
				<b><span
					style='font-size:16.0pt;font-family:
����;&quot;Times New Roman&quot;'>������<span
						class=GramE>һ</span>ְҵ����ѧԺ</span>
				</b><b><span lang=EN-US style='font-size:16.0pt'><u>&nbsp;${map.nd }&nbsp;</u></span>
				</b><b><span
					style='font-size:16.0pt;font-family:����;"Times New Roman";"Times New Roman"'>ѧ���</span>
				</b>
			</p>
			<p style='text-align:center;middle;'>
				<span lang=EN-US
					style='font-size:22.0pt;font-family:����;&quot;Times New Roman&quot;'><b>��ѧ�������</b>
				</span>
			</p>
			<table class="tbstyle">
				<tr>
					<td height="29" colspan=2 valign="middle" align="center"
						class="Normal" width="12%">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>����</span>
					</td>
					<td valign="middle" align="center" class="Normal" width="18%">
						${map.xm }
					</td>
					<td colspan=2 valign="middle" align="center" class="Normal" width="12%">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>�Ա�</span>
					</td>
					<td colspan=2 valign="middle" align="center" class="Normal" width="18%">
						${map.xb }
					</td>
					<td valign="middle" align="center" class="Normal" width="15%">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>ѧ��</span>
					</td>
					<td valign="middle" align="center" class="Normal" width="25%">
						${map.xh }
					</td>
				</tr>
				<tr>
					<td height="29" colspan=2 valign="middle" align="center"
						class="Normal">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>Ժϵ</span>
					</td>
					<td width=110 valign="middle" align="center" class="Normal">
						${map.xymc }
					</td>
					<td colspan=2 valign="middle" align="center" class="Normal">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>רҵ</span>
					</td>
					<td colspan=2 valign="middle" align="center" class="Normal">
						${map.zymc }
					</td>
					<td width=100 valign="middle" align="center" class="Normal">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>�༶</span>
					</td>
					<td width=71 valign="middle" align="center" class="Normal">
						${map.bjmc }
					</td>
				</tr>
				<tr>
					<td height="28" colspan=2 valign="middle" align="center"
						class="Normal">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>�༶����</span>
					</td>
					<td width=110 valign="middle" align="center" class="Normal">
					
					</td>
					<td colspan=2 valign="middle" align="center" class="Normal">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>�༶����</span>
					</td>
					<td colspan=2 valign="middle" align="center" class="Normal">
						&nbsp;
					</td>
					<td width=100 valign="middle" align="center" class="Normal">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>��ѧ��ȴ�</span>
					</td>
					<td width=71 valign="middle" align="center" class="Normal">
						${map.jxjmc }
					</td>
				</tr>
				<tr>
					<td colspan=3 valign="middle" align="center" class="Normal">
						<span
							style='font-size:12.0pt;
  font-family:����;&quot;Times New Roman&quot;'>�������˵ȼ�</span>
					</td>
					<td colspan=2 valign="middle" align="center" class="Normal">
						${map.dykhdj }
					</td>
					<td colspan=3 valign="middle" align="center" class="Normal">
						<span
							style='font-size:12.0pt;font-family:����;
  &quot;Times New Roman&quot;'>��ѧ�����ʽ�����׼������ǻ��</span>
					</td>
					<td width=71 valign="middle" align="center" class="Normal">
						${map.xstzjkbz }
					</td>
				</tr>
				<tr>
					<td width=51 height="128" align="center" valign="middle"
						class="Normal"
						style='font-size:12.0pt;
  font-family:����;&quot;Times New Roman&quot;'>
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
					</td>
					<td colspan=8 valign=top class="Normal">
						<p align=left style='text-align:left;'>
							&nbsp;&nbsp;${map.sqly }
						</p>
						<p align=left style='text-align:left;'>
							<span lang=EN-US style='font-size:12.0pt'></span>
						</p>
						<p align=left valign="bottom" style='text-align:right;'>
							<span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>
								������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
				<tr>
					<td width=51 valign="middle" align="center" class="Normal"
						style='font-size:12.0pt;
  font-family:����;&quot;Times New Roman&quot;'>
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
					</td>
					<td colspan=8 valign=bottom class="Normal">
						<p align=left style='text-align:left;'>
							&nbsp;&nbsp;
						</p>
						<p align=left style='text-align:left;'>
							<span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
						</p>
						<p align=left style='text-align:left;'>
							&nbsp;&nbsp;
						</p>
						<p align=left style='text-align:left;'>
							&nbsp;
						</p>
						<p align=left style='text-align:right;'>
							<span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>
								������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
						<br />
					</td>
				</tr>
				<tr>
					<td width=51 height="146" align="center" valign="middle"
						class="Normal"
						style='font-size:12.0pt;
  font-family:����;&quot;Times New Roman&quot;'>
						Ժ
						<br />
						ϵ
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
					</td>
					<td colspan=3 valign=top class="Normal">
						<p align=left style='text-align:left;'>
							<span lang=EN-US style='font-size:12.0pt'></span>
						</p>
						<p align=left style='text-align:right;'>
							<span lang=EN-US style='font-size:12.0pt'> </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>

						<p align=left style='text-align:right;'>
							<span lang=EN-US style='font-size:12.0pt'> </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����:&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
						<p align=left style='text-align:right;'>
							<span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp; </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp; </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
					<td colspan=2 class="Normal" align="center" vlingn="middle"
						style='font-size:12.0pt;
  font-family:����;&quot;Times New Roman&quot;'>
						ѧ
						<br />
						Ժ
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
					</td>
					<td colspan=3 valign=top class="Normal">
						<p align=left style='text-align:left;'>
							<span lang=EN-US style='font-size:12.0pt'></span>
						</p>
						<p align=left style='text-align:right;'>
							<span lang=EN-US style='font-size:12.0pt'> </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>

						<p align=left style='text-align:right;'>
							<span lang=EN-US style='font-size:12.0pt'> </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����:&nbsp;&nbsp;&nbsp;&nbsp;</span>
						</p>
						<p align=left style='text-align:right;'>
							<span lang=EN-US style='font-size:12.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp; </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��</span><span
								lang=EN-US style='font-size:12.0pt'>&nbsp; </span><span
								style='font-size:12.0pt;font-family:����;"Times New Roman";"Times New Roman"'>��&nbsp;&nbsp;&nbsp;</span>
						</p>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		<br/>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
		<br/><br/>
	</body>
</html>
