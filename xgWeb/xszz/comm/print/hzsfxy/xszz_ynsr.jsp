<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	<html:form action="/typj" method="post">
		<br/><br/><br/><br/>
		<table width="80%" border="0" id="theTable" align="center">
			<tr height="60px">
				<td align="center">
					<b>
					<span style='font-size:16.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ӭ�����ˡ���ѧ�������<br/><br/>
					</span>
					</b>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr height="30px">
							<td align="center" width="8%">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>����</b>
								</span>
							</td>
							<td align="center" width="14%">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" width="5%">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>�Ա�</b>
								</span>
							</td>
							<td align="center" width="8%">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center" width="8%">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>��������</b>
								</span>
							</td>
							<td align="center" width="20%" colspan="2">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
							<td rowspan="3" align="center" width="13%">
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:115;height:125" />
							</td>
						</tr>
						<tr  height="25px">
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>&nbsp;��&nbsp;ͥ&nbsp;��&nbsp;ַ</b>
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
						</tr>
						<tr height="25px">
							<td align="left"  colspan="2">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>&nbsp;����<bean:message key="lable.xb" />���༶</b>
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xymc }&nbsp;${rs.bjmc }
								</span>
							</td>
						</tr>
						<tr height="25px">
							<td align="left" colspan="2">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<b>&nbsp;��ϵ��ʽ</b>
								</span>
							</td>
							<td align="left" colspan="6">
								<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sjhm }
								</span>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:240px">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									���˼�飺(������������Ͽɸ�ҳ) <br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									</span>
								</p>
								<div align="right">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����ˣ�ѧ����ǩ����
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:80px">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����������<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.bzrshyj }
									</span>
								</p>
								<div align="right">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������ǩ����
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<p style="height:100px">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<br/>
									<bean:message key="lable.xb" />�����
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
									</span>
								</p>
								<div align="center">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����£�
									<br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td colspan="3">
								<p style="height:100px">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<br/>
									��ӭ�����ˡ���ѧ�����ίԱ��칫�ң�
									<br/>
									</span>
								</p>
								<div align="center">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����£�
									<br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td colspan="2">
								<p style="height:100px">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<br/>
									ѧУ�����
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
									</span>
								</p>
								<div align="center">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����£�
									<br/><br/>
									</span>
								</div>
								<div align="right">
									<span style='font-size:14.0pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
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
