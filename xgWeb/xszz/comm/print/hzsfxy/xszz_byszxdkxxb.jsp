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
		<table width="90%" border="0" id="printstyle" align="center">
			<tr height="50px">
				<td align="center">
					<b>
					<span style='font-size:16.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ҵ����ѧ������Ϣ�ɼ���<br/><br/>
					</span>
					</b>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="printstyle">
						<tr>
							<td width="12%"></td>
							<td width="4"></td>
							<td width="2"></td>
							<td width="6"></td>
							<td width="3"></td>
							<td width="12"></td>
							<td width="3"></td>
							<td width=""></td>
							<td width="8"></td>
							<td width="6"></td>
							<td width="6"></td>
							<td width="3"></td>
							<td width="2"></td>
							<td width="10"></td>
							<td width="8"></td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�༶QQȺ��
								</span>
							</td>
							<td align="center" colspan="4">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����Email
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.dzyx }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xm }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								����Ժϵ
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.xymc }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								רҵ
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.zymc }
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ԺУ��ϵ��ʽ
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="left" rowspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�������֤����
								</span>
							</td>
							<td align="left" rowspan="2" colspan="5">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.sfzh }
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ͥסַ
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtdz }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="7">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								${rs.jtyb }
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��������
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="����" name="cy" property="mc">
											${cy.cyxm }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������λ
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="����" name="cy" property="mc">
											${cy.cygzdw }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="����" name="cy" property="mc">
											${cy.cydh }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								ĸ������
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="ĸ��" name="cy" property="mc">
											${cy.cyxm }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								������λ
								</span>
							</td>
							<td align="center" colspan="6">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="ĸ��" name="cy" property="mc">
											${cy.cygzdw }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								��ϵ�绰
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									<logic:iterate name="cyList" id="cy">
										<logic:equal value="ĸ��" name="cy" property="mc">
											${cy.cydh }
										</logic:equal>
									</logic:iterate>
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" rowspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								�����ܶ�
								</span>
							</td>
							<td align="center" rowspan="2" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
								&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									ѧ�Ѵ�����
								</span>
							</td>
							<td align="center" colspan="10">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									����Ѵ�����
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									ס�޷Ѵ�����
								</span>
							</td>
							<td align="center" colspan="5">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ͬ��
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��������
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��֧��������
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��׼����
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�ܽ��
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ͬ����Ա
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ͬ1
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ͬ2
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									���ʼ����
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									�����ֹ����
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ע
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ͬ1
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
						<tr height="40px">
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									��ͬ2
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="2">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center" colspan="3">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
							<td align="center">
								<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
									&nbsp;
								</span>
							</td>
						</tr>
					</table>
					<div align="left">
						<br/><br/>
						<span style='font-size:10.5pt;font-family:����_GB2312;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>
						��ע��1.�˱�<bean:message key="lable.xb" />�ϱ�����ʱ���������ʹ�ã����<bean:message key="lable.xb" />�����֯���ͬѧ������ʵ��д��<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  2����ԺУ��ϵ��ʽ��������<bean:message key="lable.xb" />��������Ա�칫�绰��<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  3.������ĸ����ϵ�绰����ֱ�ɼ���ͬ���루��һΪ��ͥ�绰��һΪ��ί���ί��绰����
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
