<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<html:html>
	<head>
		<!-- ��ӡ�ؼ�begin -->
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
	<p style="height:50px">&nbsp;</p>
		<center>
			<br />
			<br />
			<p align=center>
				<b><span
					style='font-size:21px;font-family:����;font-weight:normal;'>
						��ͨ���Ƹ�У���ߵ�ְҵѧУ������־��ѧ������� </span>
				</b>
			</p>
			<br />
			<table class="printtab" style="font-family:����_GB2312;font-size:14px;width:17.67cm;">
				<tr class="nowrap">
					<td rowspan="5" align="center" width="9%" valign="center">
						����
						<br />
						���
					</td>
					<td align="center" width="19%" valign="center">
						����
					</td>
					<td align="center" width="14.5%" valign="center">
						${rs.xm }
					</td>
					<td align="center" width="8.5%" valign="center">
						�Ա�
					</td>
					<td align="center" width="9%" valign="center">
						${rs.kzzd1 }
					</td>
					<td align="center" width="12.5%" valign="center">
						��������
					</td>
					<td align="center" width="18.5%" valign="center">
						${rs.kzzd2 }
					</td>
					<td rowspan="4" align="center" width="110px" valign="center" style="padding:0px 0px 0px 0px">
						<img
							src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
							border="0" align="absmiddle" style="width:110px;height:140px" />
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						����
					</td>
					<td align="center">
						${rs.kzzd3 }
					</td>
					<td align="center" style="padding:0px 0px 0px 0px">
						����<br/>��ò
					</td>
					<td align="center">
						${rs.kzzd4 }
					</td>
					<td align="center">
						��ѧʱ��
					</td>
					<td align="center">
						${rs.kzzd5 }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						������ݺ���
					</td>
					<td align="center" colspan="3">
						${rs.kzzd6 }
					</td>
					<td align="center">
						��ϵ�绰
					</td>
					<td align="center">
						${rs.kzzd7 }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="left" colspan="4" style="position:relative;">
						&nbsp;
						<span style="position:absolute;right:180px;">${xxmc}</span>
						<span style="position:absolute;right:110px;">${rs.kzzd8}<bean:message key="lable.xb" /></span>
						<span style="position:absolute;right:60px;">${rs.kzzd9}ϵ</span>
						<span style="position:absolute;right:5px;">${rs.kzzd10}��</span>
					</td>
					<td align="center">
						ѧ��
					</td>
					<td align="center">
						${rs.xh }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						������ֽ���
					</td>
					<td colspan="6">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.hjqk }
					</td>
				</tr>
				<tr class="nowrap">
					<td rowspan="3" align="center">
						��
						<br />
						ͥ
						<br />
						��
						<br />
						��
						<br />
						��
						<br />
						��
					</td>
					<td align="center" class="nowrap">
						��ͥ����
					</td>
					<td align="center" colspan="4" style="padding:0px 0px 0px 0px">
						<logic:notEqual name="rs" property="jthk" value="">
							<logic:equal value="����" property="jthk" name="rs">
								<span class="radic">A<em>&radic;</em></span>������&nbsp;&nbsp;&nbsp;&nbsp;B��ũ��
							</logic:equal>
							<logic:equal value="ũ��" property="jthk" name="rs">
								A������&nbsp;&nbsp;&nbsp;&nbsp;<span class="radic">B<em>&radic;</em></span>��ũ��
							</logic:equal>
						</logic:notEqual>
						<logic:equal name="rs" property="jthk" value="">
								A������&nbsp;&nbsp;&nbsp;&nbsp;B��ũ��
						</logic:equal>
					</td>
					<td align="center">
						��ͥ�˿�����
					</td>
					<td align="center">
						${rs.jtrs }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						��ͥ��������
					</td>
					<td align="center">
						${rs.jtyzsr }
					</td>
					<td align="center" colspan="2">
						�˾�������
					</td>
					<td align="center">
						${rs.jtrjysr }
					</td>
					<td align="center">
						������Դ
					</td>
					<td align="center">
						${rs.srly }
					</td>
				</tr>
				<tr class="nowrap">
					<td align="center">
						��ͥסַ
					</td>
					<td align="center" colspan="4">
						${rs.jtdz }
					</td>
					<td align="center">
						��������
					</td>
					<td align="center">
						${rs.jtyb }
					</td>
				</tr>
				<tr>
					<td colspan="8" valign="top">
						<p style="height:95px">
							<br/>
							ѧϰ�ɼ�
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxcj }
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="8" style="position:relative;">
						<p style="height:110px;">
							<br/>
							��������
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
						</p>
						&nbsp;
						<div align="right" style="position:absolute;right:98px;">
							������ǩ����
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span style="width:150px;" >
								&nbsp;&nbsp;&nbsp;&nbsp;
								��&nbsp;&nbsp;��&nbsp;&nbsp;��
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8"  style="position:relative;">
						<p style="height:115px;">
							<br />
							ѧУ��������������������
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }
						</p>
						&nbsp;
						<div align="right" style="position:absolute;right:95px;">
							�����£�
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span style="width:150px;" align="right">
								<logic:notEqual name="rs" property="shsj1" value="">
									${rs.shsj1 }
								</logic:notEqual>
								<logic:equal name="rs" property="shsj1" value="">
									&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;��&nbsp;&nbsp;��
								</logic:equal>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="8" style="position:relative;">
						<p style="height:115px;">
							<br />
							ѧУ��������
							<br /><br />
							&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt2yj }
						</p>
						&nbsp;
						<div align="right" style="position:absolute;right:92px;">
							�����£� 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span style="width:150px;" align="right">
								<logic:notEqual name="rs" property="shsj2" value="">
									${rs.shsj2 }
								</logic:notEqual>
								<logic:equal name="rs" property="shsj2" value="">
									&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;��&nbsp;&nbsp;��
								</logic:equal>
							</span>
						</div>
					</td>

				</tr>
			</table>
	</center>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>

</body>
</html:html>
