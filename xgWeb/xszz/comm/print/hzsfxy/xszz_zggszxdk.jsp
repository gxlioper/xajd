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
		<br/><br/>
		<table width="95%" border="0" id="printstyle" align="center">
			<tr height="50px">
				<td align="center">
					<b>
					<span style='font-size:18.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						�й��������к����з�����ѧ��������������<br/><br/>
					</span>
					</b>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr>
							<td width="4%"></td>
							<td width="12%"></td>
							<td width="8%"></td>
							<td width="2%"></td>
							<td width="2%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="12%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="7%"></td>
							<td width="12%"></td>
							<td width="12%"></td>
						</tr>
						<!-- ��������� -->
						<!-- ��1�� -->
						<tr height="20px">
							<td align="center" rowspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��<br/>
								��<br/>
								��<br/>
								��<br/>
								��
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���֤����
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sfzh }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;&nbsp;&nbsp;&nbsp;��
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xb }
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.csrq }
								</span>
							</td>
						</tr>
						<!-- ��2�� -->
						<tr height="20px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�Ͷ�ѧУ
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xxmc }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧУ�绰
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;&nbsp;&nbsp;&nbsp;��
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- ��3�� -->
						<tr height="20px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								Ժϵ
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xymc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								רҵ
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zymc }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ&nbsp;&nbsp;&nbsp;&nbsp;��
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧ&nbsp;&nbsp;&nbsp;&nbsp;��
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xh }
								</span>
							</td>
						</tr>
						<!-- ��4�� -->
						<tr height="20px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ��ַ
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥ�绰
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdh }
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
								</span>
							</td>
						</tr>
						<!-- ��ͥ��Ա -->
						<tr style="height:20px">
							<td align="center" rowspan="${cyNum+1 }">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��<br>
									ͥ<br>
									��<br>
									Ա
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ν&nbsp;
								</span>
							</td>
							<td align="center" colspan="8">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;λ
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ϵ�绰
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									������
								</span>
							</td>
							</tr>
							<logic:iterate name="cyList" id="cy">
							<tr style="height:20px">
								<td align="center">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cyxm }&nbsp;
									</span>
								</td>
								<td align="center" colspan="2">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.mc }&nbsp;
									</span>
								</td>
								<td align="center" colspan="8">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cygzdw }&nbsp;
									</span>
								</td>
								<td align="center">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cydh }&nbsp;
									</span>
								</td>
								<td align="center">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										${cy.cyysr }&nbsp;
									</span>
								</td>
							</tr>
						</logic:iterate>
						<!-- ������ -->
						<!-- ��1�� -->
						<tr style="height:20px">
							<td align="center" rowspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��<br/>
								��<br/>
								��<br/>
								��
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������
								</span>
							</td>
							<td align="left" colspan="12">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�ܶ�(��д)��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>ѧ��<br/><br/>
								����ѧ�Ѵ��Сд����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ/ѧ�꣬����Ѵ��Сд����<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ/ѧ��
								</span>
							</td>
						</tr>
						<!-- ��2�� -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="12">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- ��3�� -->
						<tr style="height:20px">
							<td align="left" colspan="7">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����ڴ���˺�<br/><br/>
								����&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
								</span>
							</td>
							<td align="center" rowspan="2" colspan="6">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���˱�֤���ϸ�����д���ݾ���ʵ����<br/><br/>
								��������ˣ�������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
								</span>
							</td>
						</tr>
						<!-- ��4�� -->
						<tr style="height:20px">
							<td align="left">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�˺�
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- �Ӵ� -->
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<!-- ��֤����� -->
						<!-- ��1�� -->
						<tr style="height:20px">
							<td align="center" rowspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��<br/>
								֤<br/>
								��<br/>
								��<br/>
								��
								</span>
							</td>
							<td align="left" colspan="13">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����ɼ�֤��ǩ�����£����ô�ǩ��
								</span>
							</td>
						</tr>
						<!-- ��2�� -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;&nbsp;��
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;&nbsp;ν
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;λ&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����ǩ������
								</span>
							</td>
						</tr>
						<!-- ��3�� -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- ��4�� -->
						<tr style="height:20px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- �Ӵ� -->
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<!-- ��������� -->
						<!-- ��1�� -->
						<tr style="height:20px">
							<td align="center" rowspan="6">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								<br/>��<br/>
								��<br/>
								��<br/>
								��<br/>
								��<br/>
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								���������
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- ��2�� -->
						<tr style="height:20px">
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ѧУѧ������ϵ��
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- ��3�� -->
						<tr style="height:20px">
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����ѧУ�ɼ�
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="left" rowspan="4" colspan="5">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�������������У�Ͷ�ѧ������������������ʵ���ش�֤�� ��<br/><br/>
								<bean:message key="lable.xb" />��ǩ�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧУ��ǩ�£�<br/><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��<br/>
								</span>
							</td>
						</tr>
						<!-- ��4�� -->
						<tr style="height:20px">
							<td align="center" rowspan="2">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;��&nbsp;��<br/>Ʒ�±���
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;��
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- ��5�� -->
						<tr style="height:20px">
							<td align="center" colspan="3">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��&nbsp;��
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- ��6�� -->
						<tr style="height:20px">
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<!-- �Ӵ� -->
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<!-- ���������� -->
						<!-- ��1�� -->
						<tr style="height:50px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��<br/>
								��<br/>
								��<br/>
								��<br/>
								��<br/>
								��
								</span>
							</td>
							<td align="center" colspan="5">
								<div align="left">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										���������
									</span>
								</div>
								<div align="left">
									<p style="height:25px">
										
									</p>
								</div>
								<div align="right">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td align="center" colspan="5">
								<div align="left">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										��������
									</span>
								</div>
								<div align="left">
									<p style="height:25px">
										
									</p>
								</div>
								<div align="right">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
							<td align="center" colspan="3">
								<div align="left">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
										���������
									</span>
								</div>
								<div align="left">
									<p style="height:25px">
										
									</p>
								</div>
								<div align="right">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</span>
								</div>
								<br/>
								<div align="right">
									<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;
									</span>
								</div>
							</td>
						</tr>
						<!-- �Ӵ� -->
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<!-- ��׼��� -->
						<tr style="height:50px">
							<td align="center">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��<br/><br/>
								׼<br/><br/>
								��<br/><br/>
								��
								</span>
							</td>
							<td align="left" colspan="13">
								<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����ܶ�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ����������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�꣬���У�<br/><br/>
								ѧ�Ѵ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ/ѧ�ꡣÿѧ�귢��һ�Σ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�Σ�<br/><br/>
								����Ѵ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>Ԫ/ѧ�꣬ÿѧ�귢��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�Σ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>�Ρ�<br/>
								</span>
							</td>
						<!-- �Ӵ� -->
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<div align="left">
						<br/><br/>
						<span style='font-size:9pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						ע��<br/>
						��������������֮һ�ģ�������Ȩֹͣ���Ž�<br/>
						1�����ع��ҷ��ɡ����ɺ�ѧУ�����ƶȣ������·����¼��ѧУ���ؾ������ϴ��ּ�¼��<br/>
						2��ѧϰ�����棬Ʒ�²�Ͷ��ڼ��ۼƲ��ϸ�γ�1�ţ��������ϣ�<br/>
						3���в������ü�¼��
						</span>
					</div>
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
