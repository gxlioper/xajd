<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;};
			.PageNext{
			page-break-after: always;
		}
		</style>
	<!-- end -->
	<html:form action="/stu_info_add" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						�Ĵ�ʡ������Ʊ��ѧ����������������רҵѧ����
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="90%" id="rsT" class="printstyle">
						<tr align="center" style="height: 35px">
							<td colspan=2>
								�� &nbsp;��
							</td>
							<td colspan=2>
								${rs.xm}
							</td>
							<td colspan=2>
								�Ա�
							</td>
							<td>
								${rs.xb}
							</td>
							<td>
								����
								<br />
								����
							</td>
							<td colspan=3>
								${rs.csrq}
							</td>
							<td rowspan=4>
								��������
								<span lang=EN-US>2</span>����Ƭ
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td colspan=2>
								Ժϵ
							</td>
							<td colspan=4>
								${rs.xymc}
							</td>
							<td colspan=3>
								�꼶/�༶
							</td>
							<td colspan=2>
								${rs.nj}/${rs.bjmc}
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td colspan=2>
								��ͥ��ַ
							</td>
							<td colspan=9 valign=middle>
								${rs.jtdz}
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td colspan=2>
								��ϵ�绰
							</td>
							<td colspan=9>
								${rs.lxdh}
							</td>
						</tr>
						<tr align="center" style="height: 35px">
							<td rowspan=4>
								��ͥ<br/>����<br/>���
							</td>
							<td colspan=2>
								��ͥ��Ա
							</td>
							<td colspan=2>
								�� ��
							</td>
							<td colspan=7>
								�� ͥ �� �� �� �� ˵ ��
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
						<tr align="center" style="height: 35px">
							<td colspan=2>
								&nbsp;${cy.mc }
							</td>
							<td colspan=2 >
								&nbsp;${cy.cyxm }
							</td>
							<td colspan=7>
								&nbsp;
							</td>
						</tr>
						</logic:iterate>
						<tr align="center" style="height: 35px">
							<td colspan=2 valign=middle>
								����ƶ��<br/>֤����λ
							</td>
							<td colspan=7 valign=middle>
								&nbsp;
							</td>
							<td valign=middle colspan="2">
								�� ϵ<br/>�� ��
							</td>
							<td valign=middle>
								&nbsp;
							</td>
						</tr>
						<tr align="center" style="height: 170px">
							<td>
								����<br/>��У<br/>����<br/>���
							</td>
							<td colspan=11 valign=middle align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
							</td>
						</tr>
						<tr style="height: 130px">
							<td align="center">
								Ժϵ<br/>����<br/>���
							</td>
							<td colspan=11 valign=middle>
								<p style="height:110px" align="left">
									<span style="height: 100px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }</span>
								</p>
								<span style="vertical-align: bottom;float: left;margin-left: 30px">ϵ�������쵼ǩ�֣�</span>
								<span style="vertical-align: bottom;float: left;margin-left: 120px">ϵ���������£�</span>
								<br/>
								<span  style="vertical-align: bottom;float: right;margin-right: 40px">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </span>
							</td>
						</tr>
						<tr style="height: 130px">
							<td align="center">
								ѧУ<br/>���<br/>���
							</td>
							<td colspan=11 valign=middle>
								<p style="height:110px" align="left">
									<span style="height: 100px">&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt2yj }</span>
								</p>
								<span style="vertical-align: bottom;float: left;margin-left: 30px">У�쵼ǩ�֣�</span>
								<span style="vertical-align: bottom;float: left;margin-left: 150px">ѧУ���£�</span>
								<br/>
								<span  style="vertical-align: bottom;float: right;margin-right: 40px">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </span>
							</td>
						</tr>
						<tr style="height: 130px">
							<td align="center">
								�Ĵ�ʡ<br/>������<br/>����<br/>���
							</td>
							<td colspan=11 valign="middle">
								<span style="height: 100px"></span>
								<span style="vertical-align: bottom;float: left;margin-left: 30px">�쵼ǩ�֣�</span>
								<span style="vertical-align: bottom;float: left;margin-left: 150px">ʡ�����ָ��£�</span>
								<br/>
								<span  style="vertical-align: bottom;float: right;margin-right: 40px">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� </span>
							</td>
						</tr>
						<tr height=0>
							<td width=10%></td>
							<td width=6%></td>
							<td width=8%></td>
							<td width=8%></td>
							<td width=4%></td>
							<td width=4%></td>
							<td width=8%></td>
							<td width=7%></td>
							<td width=1%></td>
							<td width=6%></td>
							<td width=6%></td>
							<td width=24%></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</html:form>
</body>
</html:html>
