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
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						��ͨ���Ƹ�У���ߵ�ְҵѧУ������ѧ�������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="15%"></td>
							<td width="15%"></td>
							<td width="9%"></td>
							<td width="9%"></td>
							<td width="15%"></td>
							<td width="15%"></td>
							<td width="15%"></td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="4">����<br/>���</td>
							<td align="center">����</td>
							<td align="center">${rs.xm }</td>
							<td align="center">�Ա�</td>
							<td align="center">${rs.xb }</td>
							<td align="center">��������</td>
							<td align="center">${rs.csrq }</td>
							<td align="center" rowspan="4">
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:145;height:175" />
							</td>
						</tr>
						<tr height="25px">
							<td align="center">����</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">����<br/>��ò</td>
							<td align="center">${rs.zzmmmc }</td>
							<td align="center">��ѧʱ��</td>
							<td align="center">${rs.rxrq }</td>
						</tr>
						<tr height="25px">
							<td align="center">���֤����</td>
							<td align="center" colspan="3">${rs.sfzh }</td>
							<td align="center">��ϵ�绰</td>
							<td align="center">${rs.lxdh }</td>
						</tr>
						<tr height="25px">
							<td align="center" colspan="6">
								${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xb" />&nbsp;&nbsp;&nbsp;&nbsp;
								${rs.zymc }&nbsp;&nbsp;&nbsp;&nbsp;ϵ&nbsp;&nbsp;&nbsp;&nbsp;
								${rs.bjmc }&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr height="25px">
							<td align="center" rowspan="3">
								��<br/>ͥ<br/>��<br/>��<br/>��<br/>��
							</td>
							<td align="center">��ͥ����</td>
							<td align="center" colspan="4">
								<logic:present name="rs" property="jthk">
									<logic:equal value="����" property="jthk" name="rs">
										����
									</logic:equal>
									<logic:equal value="ũ��" property="jthk" name="rs">
										ũ��
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									A������ B��ũ��
								</logic:notPresent>
							</td>
							<td align="center">��ͥ�˿�����</td>
							<td align="center">${rs.jtrs }</td>
						</tr>
						<tr height="25px">
							<td align="center">��ͥ��������</td>
							<td align="center">${rs.jtyzsr }</td>
							<td align="center" colspan="2">�˾�������</td>
							<td align="center">${rs.jtrjysr }</td>
							<td align="center">������Դ</td>
							<td align="center">${rs.srly }</td>
						</tr>
						<tr height="25px">
							<td align="center">��ͥסַ</td>
							<td align="center" colspan="4">${rs.jtdz }</td>
							<td align="center">��������</td>
							<td align="center">${rs.jtyb }</td>
						</tr>
						
						<tr style="height:25px">
							<td align="center" rowspan="${cyNum+1 }">
									��<br>
									ͥ<br>
									��<br>
									Ա<br>
									��<br>
									��
							</td>
							<td align="center">
									����
							</td>
							<td align="center">
									����
							</td>
							<td align="center" colspan="2">
									�뱾�˹�ϵ
							</td>
							<td align="center" colspan="3">
									������λ
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
							<tr height="20px">
								<td align="center">
										${cy.cyxm }&nbsp;&nbsp;
								</td>
								<td align="center">
										${cy.cynl }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="2">
										${cy.mc }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="3">
										${cy.cygzdw }&nbsp;&nbsp;
								</td>
							</tr>
						</logic:iterate>
						<tr>
							<td colspan="8">
								<p style="height:60px">
								�������ɣ�${rs.sqly }
								</p>
								<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:50px">
								�༶��������${rs.bjshyj }
								</p>
								<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:50px">
								<bean:message key="lable.xb" />����������ע����ʾ�������${rs.xyshyj }
								</p>
								<div align="right">
									Ժ�쵼ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:50px">
								ѧ������������
								</p>
								<div align="right">
									���£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:50px">
								ѧУ��������${rs.xxshyj }
								</p>
								<div align="right">
									�����£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
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
