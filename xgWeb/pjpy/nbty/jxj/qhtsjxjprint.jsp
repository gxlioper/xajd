<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">

		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />	
		<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>

	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<div class=Section1 style='layout-grid:15.6pt' align="center">
			<p align=center style='text-align:center;line-height:16.0pt;
'>
				<b><span
					style='font-size:18.0pt;font-family:
����;&quot;Times New Roman&quot;'>
						������һְҵ����ѧԺ<br/><u>&nbsp;&nbsp;${rs.xn }&nbsp;&nbsp;</u>ѧ���庮��ʹ��ѧ�������</span></b>
			</p>
			<table class="tbstyle" width="90%">
				<tr>
					<td width="8%" rowspan=4 align="center">
						��<br/>��<br/>��<br/>��<br/>
					</td>
					<td width="10%" align="center">
						����
					</td>
					<td width="15%" colspan=2 align="center">
						${rs.xm }
					</td>
					<td width="10%" align="center">
						�Ա�
					</td>
					<td width="10%" align="center">
						${rs.xb }
					</td>
					<td width="12%" colspan=2 align="center">
						��������
					</td>
					<td width="15%" align="center">
						${rs.csrq }
					</td>
					<td width="10%" colspan=2 align="center">
						����
					</td>
					<td width="10%" align="center">
						${rs.mzmc}
					</td>
				</tr>
				<tr>
					<td align="center">
						ѧ��
					</td>
					<td colspan=2 align="center">
						${rs.xh }
					</td>
					<td colspan=3 align="center">
						רҵ���꼶���༶
					</td>
					<td colspan=5 align="center">
						${rs.zymc }&nbsp;${rs.nj }&nbsp;${rs.bjmc }
					</td>
				</tr>
				<tr>
					<td align="center">
						�������
					</td>
					<td colspan=6 align="center">
						<p>
							&nbsp;&nbsp;${rs.jcqk}
						</p>
					</td>
					<td colspan=2 align="center">
						Ʒ�µȵ�
					</td>
					<td colspan=2 align="center">
						${rs.pddd }
					</td>
				</tr>
				<tr>
					<td colspan=2 align="center">
						��ѧ����<br/>��������д��<br/>������
					</td>
					<td colspan=5 align="center">
						${rs.hxnhzzz }
					</td>
					<td colspan=2 align="center">
						�Ƿ�μ���<br/>����ѧ����<br/>����ѧ����
					</td>
					<td colspan=2 align="center">
						${rs.help }
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							�������ɣ���Ӧд����ͥ����������������˼���ͥ��Ա����״����Ŀǰ��ѧϰ���Ʒ�б��֡���
						</p>
						<p>
							&nbsp;&nbsp;${rs.sqly }
						</p>
						<p>
							���˱�֤������������ʵ�����
						</p>
						<p align="right">
							������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							�༶���飺��Ӧд����ͥ����������̶ȡ�Ʒ�б��֡�ѧϰ������״����
						</p>
							
						<p>
							&nbsp;&nbsp;${rs.bjpy }
						</p>
						<p align="right">
							ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							Ժϵ�������ʾ�����
						</p>
						<p>
							&nbsp;&nbsp;${rs.xyshyj }
						</p>
						<p align="right">
							ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							ѧ���������
						</p>
						<p>
							&nbsp;&nbsp;${rs.xxshyj }
						</p>
						<p align="right">
							ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=12 valign=top>
						<p>
							Ժ�쵼��������
						</p>
						<p>
							&nbsp;&nbsp;${rs.xxshyj }
						</p>
						<p align="right">
							ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
		</div>
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
	</body>
</html>

