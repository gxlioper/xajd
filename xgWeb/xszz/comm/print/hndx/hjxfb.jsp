<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>���ϴ�ѧѧ������ѧ�ӷ������</title>
		
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<div align="center">
			<p align=center style='text-align: center'>
				<b><span style='font-size: 18.0pt; font-family: ����;'>���ϴ�ѧѧ������ѧ�ӷ������</span>
				</b>
			</p>
			<p align="left" style="font-size:15px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<bean:message key="lable.xb" />��${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				ѧ�ţ�${rs.xh }
			</p>
			<table class="printstyle" width="85%">
				<tr>
					<td width="15%" colspan=2 align=center>
						<p>
							��&nbsp; ��
						</p>
					</td>
					<td width="20%" align=center>
						${rs.xm }
					</td>
					<td width="10%" align=center>
						<p>
							��&nbsp; ��
						</p>
					</td>
					<td width="10%" align=center>
						${rs.xb }
					</td>
					<td width="15%" align=center>
						<p>
							רҵ�༶
						</p>
					</td>
					<td width="30%" align=center>
						${rs.zymc }&nbsp;${rs.bjmc }
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<p>
							��&nbsp; ͥ
						</p>
						<p>
							���ڵ�
						</p>
					</td>
					<td colspan=5 align=left>
						<p>
							&nbsp;&nbsp;${szsheng }&nbsp;&nbsp;ʡ&nbsp;&nbsp;
							${szshi}&nbsp;&nbsp;�أ��У�&nbsp;&nbsp;
							${szxian }&nbsp;&nbsp; ���磩&nbsp;&nbsp;
							${rs.szzhen }&nbsp;&nbsp;��&nbsp;&nbsp;
							 �ʱࣺ ${rs.jtyb }
						</p>
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							��ͥ�������
						</p>
					</td>
					<td colspan=6 align=left height="60px">
						&nbsp;&nbsp;&nbsp;${rs.jtjjqk }
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							��Ƿѧ�ӷ����
						</p>
					</td>
					<td colspan=6 align=left height="60px">
						&nbsp;&nbsp;&nbsp;${rs.tqxzfqk }
					</td>
				</tr>
				<tr height="120px">
					<td rowspan=2>
						<p align=center style='text-align: center; line-height: 24.0pt;'>
							�ɷѼƻ�
						</p>
					</td>
					<td colspan=6 align=left height="60px">
						<p>
							&nbsp;&nbsp;&nbsp;�ƻ���&nbsp;&nbsp;&nbsp;${rs.jfjh }
						</p>
					</td>
				</tr>
				<tr>
					<td colspan=6 align=left height="60px">
						<p>
							&nbsp;&nbsp;&nbsp;����Ƿ�ѽ�ֹʱ�䣺&nbsp;&nbsp;&nbsp;${rs.jzsj }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="120px">
					<td colspan=7 height="60px">
						<p style='layout-grid-mode: char' align="center">
							<b>���˳�ŵ���粻��ʱ������Ƿѧ�ӷѣ���ѧУ�йع涨����</b>
						</p>
						<p align="right">
							����ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							<bean:message key="lable.xb" />���
						</p>
					</td>
					<td colspan=6 align=center height="60px">
						&nbsp;
					</td>
				</tr>
				<tr height="120px">
					<td>
						<p align=center>
							ѧУ���
						</p>
					</td>
					<td colspan=6 align=center height="60px">
						&nbsp;
					</td>
				</tr>
			</table>
			<p align="right">
				<span style='font-family: ����;' >Уѧ����������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</p>
		</div>

		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6, 6);">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
