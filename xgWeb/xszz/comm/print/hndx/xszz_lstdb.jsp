<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>��������2009����ͥ��������ѧ������ɫͨ����������֪ͨ</title>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<body bgcolor="#FFFFFF" lang=ZH-CN>
		<div class=Section1 style='layout-grid: 15.6pt'>
			<p align=center>
				<b><span style='font-size: 18.0pt; font-family: ����_GB2312;'>���ϴ�ѧ������ѧ����ɫͨ���������
				</span> </b>
			</p>
			<div align=center>
				<table class="tbstyle" width="90%">
					<tr>
						<td colspan=15 align="center">
							<p>
								<b>���˻�����Ϣ</b>
							</p>
						</td>
					</tr>
					<tr align="center">
						<td width=9%>
							����
						</td>
						<td width=9%>
							${rs.xm }
						</td>
						<td width=10% colspan=2>
							�Ա�
						</td>
						<td width=10%>
							${rs.xb }
						</td>
						<td width=10%>
							����
						</td>
						<td width=10% colspan=4>
							${rs.mzmc }
						</td>
						<td width=22% colspan=3>
							��ѧ����ʱ��
						</td>
						<td width=20% colspan=2>
							${rs.rxrq }
						</td>
					</tr>
					<tr align="center">
						<td>
							<bean:message key="lable.xb" />
						</td>
						<td colspan=3>
							${rs.xymc }
						</td>
						<td>
							רҵ
						</td>
						<td colspan=5>
							${rs.zymc }
						</td>
						<td colspan=3>
							���޼�ͥ����ƶ��֤������
						</td>
						<td colspan=2>
							${rs.ywzm }
						</td>
					</tr>
					<tr align=center>
						<td>
							ѧ��
						</td>
						<td colspan=3>
							${rs.xz }&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td colspan=3>
							���֤����
						</td>
						<td colspan=8>
							${rs.sfzh }
						</td>
					</tr>
					<tr align="center">
						<td colspan=15>
							<p>
								<b>��ͥ��Ҫ��Ա���</b>
							</p>
						</td>
					</tr>

					<tr>
						<td>
							<p align=center>
								�� ν
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								����
							</p>
						</td>
						<td colspan=8>
							<p align=center>
								��ͥ��ַ������λ��ַ
							</p>
						</td>
						<td width="8%">
							<p align=center>
								�ʱ�
							</p>
						</td>
						<td colspan=2 width="14%">
							<p align=center>
								��ϵ�绰
							</p>
						</td>
						<td>
							<p align=center>
								������
							</p>
						</td>
					</tr>

					<logic:iterate name="cyList" id="cy">
						<tr>
							<td>
								<div align="center">
									${cy.mc }&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									${cy.cyxm }&nbsp;&nbsp;
								</div>
							</td>
							<td colspan=8>
								<div align="center">
									${cy.cygzdw }&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									${cy.cyyb }&nbsp;&nbsp;
								</div>
							</td>
							<td colspan=2>
								<div align="center">
									${cy.cydh }&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<p align=right>
									${cy.cyysr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ԫ
								</p>
							</td>
						</tr>
					</logic:iterate>

					<tr>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td colspan=8>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td>
							<p align=right>
								Ԫ
							</p>
						</td>
					</tr>

					<tr>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td colspan=8>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td colspan=2>
							&nbsp;
						</td>
						<td>
							<p align=right>
								Ԫ
							</p>
						</td>
					</tr>

					<tr>
						<td>
							<p align=center>
								<b>��ͥ <br /> ���</b>
							</p>
						</td>
						<td colspan=14>
							<p align=left>
								&nbsp;&nbsp;�¶���
								<logic:equal value="��" name="rs" property="sfge">��</logic:equal>
								�� ���׼�ͥ��
								<logic:equal value="��" name="rs" property="sfdq">��</logic:equal>
								�� ����ͱ�����
								<logic:equal value="��" name="rs" property="sfdb">��</logic:equal>
								�� ��ʿ��Ů��
								<logic:equal value="��" name="rs" property="lszn">��</logic:equal>
								�� �Ÿ���ͥ��Ů��
								<logic:equal value="��" name="rs" property="sfyfjt">��</logic:equal>
								�� �м�ѧ����
								<logic:equal value="��" name="rs" property="sfcj">��</logic:equal>
								�� ƶ��������������ѧ����
								<logic:equal value="��" name="rs" property="sfpkdqssmz">��</logic:equal>
								��
							</p>
						</td>
					</tr>
					<tr>
						<td colspan=15 valign=top>
							<p align=center>
								<b>��������</b>
							</p>
							<p style='line-height: 15.0pt;'>
								&nbsp;&nbsp;&nbsp;&nbsp;����
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>ԭ�򣬱��������ݻ��ɽ�
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>�ȷ��ã� ����
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>Ԫ�����˳�ŵ��
								<u> &nbsp;&nbsp;&nbsp;&nbsp;</u>��
								<u> &nbsp;&nbsp;&nbsp;&nbsp;</u>��
								<u>&nbsp; &nbsp;&nbsp;&nbsp;</u>��ǰ�������������ã���ѧУ�����԰�����ر�������������ס�ޡ�
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								<b>������������ʽ</b>
							</p>
						</td>
						<td colspan=14>
							<p>
								������ѧ��� ���ڹ���ѧ�� ��������ѧ�� ����ʱ���Ѳ����� ����������� ��
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p align=center>
								����
							</p>
							<p align=center>
								��ŵ
							</p>
						</td>
						<td colspan=14>
							<p>
								(1)��������������ʵ����������ٳɷ֡�
								<br />
								(2)�籾����д��������ʵ��������ѧ�ڼ佫���������κ����������е��ɴ˴�������Ӧ���Ρ�
							</p>

							<p align=center>
								ǩ��:
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</u>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>��
								<u>&nbsp;&nbsp;&nbsp; </u>��
								<u>&nbsp;&nbsp;&nbsp; </u>��
							</p>
						</td>
					</tr>
					<tr height="200px">
						<td>
							<p align=center>
								ѧ
								<br />
								Ժ
								<br />
								��
								<br />
								��
							</p>
						</td>
						<td colspan=7>
							<p>
								&nbsp;&nbsp;
							</p>
							<p align="right">
								ǩ�֣����£�:___________&nbsp;&nbsp;
								<br />
								_____��_____��_____��&nbsp;&nbsp;
							</p>
						</td>
						<td>
							<p align=center>
								ѧ
								<br />
								У
								<br />
								��
								<br />
								��
								<br />
								��
								<br />
								��
						</td>
						<td colspan=6>
							<p>
								&nbsp;&nbsp;
							</p>
							<p align="right">
								ǩ��:______________&nbsp;&nbsp;
								<br />
								_____��_____��_____��&nbsp;&nbsp;
							</p>
						</td>
					</tr>
				</table>
				<table border="0" width="90%">
					<tr height=0 >
						<td colspan="15" align="left">
								&nbsp;&nbsp;&nbsp;��ע��1�� Ŀǰ��������ѣ�
								<u>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ���������뼰ʱ��ѧУ��ϵ��<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								2����������������˱�����д��
						</td>
					</tr>
				</table>
			</div>

		</div>

		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
