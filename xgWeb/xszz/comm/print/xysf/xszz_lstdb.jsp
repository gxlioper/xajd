<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html:html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<title>����ʦ��ѧԺ������ѧ����ɫͨ���������</title>

		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<body>
		<div>
			<p align=center>
				<b><span style='font-size: 16.0pt; font-family: ����'>����ʦ��ѧԺ������ѧ����ɫͨ���������</span>
				</b>
			</p>
			<div align=center>
				<table class="tbstyle" border="0" align="center" style="width: 90%">
					<tr>
						<td rowspan=2 width="13%">
							<p align=center>
								����
								<br />
								���
								<br />
							</p>
						</td>
						<td width="8%">
							<p align=center>
								����
							</p>
						</td>
						<td width="16%" colspan=2>
							${rs.xm }
						</td>
						<td width="8%">
							<p align=center>
								�Ա�
							</p>
						</td>
						<td width="8%">
							${rs.xb }
						</td>
						<td width="14%" colspan=2>
							<p align=center>
								��������
							</p>
						</td>
						<td width="16%" colspan=2 class="Normal">
							${rs.csrq }
						</td>
						<td width="8%">
							<p align=center>
								����
							</p>
						</td>
						<td width="8%">
							${rs.mzmc}
						</td>
					</tr>
					<tr>
						<td colspan="7">
							<p align=left>
								<bean:message key="lable.xb" />��&nbsp;${rs.xymc }&nbsp;&nbsp;<br/>
								רҵ��&nbsp;${rs.zymc } &nbsp;&nbsp;<br/>
								�༶��&nbsp;${rs.bjmc }<br/>
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								Ӧ���ѽ��
							</p>
						</td>
						<td colspan=2>
							<p align=center>
							${rs.yjje }
							</p>
						</td>
					</tr>
					<tr>
						<td rowspan=${cyNum+1 }>
							<p align=center>
								��ͥ��Ա���
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								��&nbsp;��
							</p>
						</td>
						<td>
							<p align=center>
								����
							</p>
						</td>
						<td colspan=3>
							<p align=center>
								�뱾�˹�ϵ
							</p>
						</td>
						<td colspan=3>
							<p align=center>
								������ѧϰ��λ
							</p>
						</td>
						<td colspan=2>
							<p align=center>
								��ϵ�绰
							</p>
						</td>
					</tr>
					<logic:iterate name="cyList" id="cy">
				<tr>
					<td width="10%" colspan=2>
						<div align="center">
							${cy.cyxm }&nbsp;&nbsp;
						</div>
					</td>
					<td width="10%">
						<div align="center">
							${cy.cynl }&nbsp;&nbsp;
						</div>
					</td>
					<td width="10%" colspan=3>
						<div align="center">
							${cy.mc }&nbsp;&nbsp;
						</div>
					</td>
					<td width="10%" colspan=3>
						<div align="center">
							${cy.cygzdw }&nbsp;&nbsp;
						</div>
					</td>
					<td width="10%" colspan=2>
						<div align="center">
							${cy.cydh }&nbsp;&nbsp;
						</div>
					</td>
				</tr>
			</logic:iterate>
<%--					<tr>--%>
<%--						<td colspan=3>--%>
<%--							<p align=center>--%>
<%--								������Ԫ��--%>
<%--							</p>--%>
<%--						</td>--%>
<%--						<td colspan=5>--%>
<%--							${rs.qjfy }--%>
<%--						</td>--%>
<%--						<td colspan=2>--%>
<%--							<p align=center>--%>
<%--								�������ޣ��£�--%>
<%--							</p>--%>
<%--						</td>--%>
<%--						<td colspan=2>--%>
<%--							${rs.hjqx }--%>
<%--						</td>--%>
<%--					</tr>--%>
					<tr>
						<td>
							<p align=center>
								��
								<br />
								��
								<br />
								��
								<br />
								��
							</p>
						</td>
						<td colspan=11 valign=bottom>
							<p>
								${rs.sqly }
							</p>
							<p align=right>
								������ǩ����
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p align=center>
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
							</p>
						</td>
						<td colspan=11 valign=bottom>
							<p align=right>
								������ǩ����
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
							<p align=right>
								��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p align=center>
								����
								<br />
								<bean:message key="lable.xb" />
								<br />
								���
								<br />
							</p>
						</td>
						<td colspan=11 valign=bottom>
							<p>
								&nbsp;
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ͬ���ݽ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ԫ
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ͬ�⻺��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ԫ
								�����ޣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ǩ����
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����£�&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
							<p align="right">
								��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
							</p>
						</td>
					</tr>
					<tr>
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
							</p>
						</td>
						<td colspan=11>
							<p align=center>
								<br/>
								<span style='font-size:24.0pt;font-family:�����п�'>ͬ ��</span>
							</p>
							<p align=right>
								ѧ�������� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
							<p align=right>
								��&nbsp;&nbsp;�� &nbsp;&nbsp;�� &nbsp;
							</p>
						</td>
					</tr>
				</table>
				<br/><br/><br/>
				<table border="0">
				<tr>
				<td align="left">
				<span style='font-size: 12.0pt; font-family: ����_GB2312'>
							�˱�һʽ���ݣ����񴦡�ѧ��������һ�ݡ�����ʱ�踽�������뼰�й�����֤�����ϡ�</span>
				
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
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
	</body>
</html:html>
