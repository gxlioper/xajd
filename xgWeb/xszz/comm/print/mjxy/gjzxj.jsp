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
					<h3>
						��ͨ���Ƹ�У���ߵ�ְҵѧУ������ѧ�������V1.2
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<tr height="40px">
							<td rowspan="4" align="center" width="7%">
								��
								<br />
								��
								<br />
								��
								<br />
								��
							</td>
							<td align="center" width="16%">
								����
							</td>
							<td align="center" width="16%">${rs.xm }</td>
							<td align="center" width="10%">
								�Ա�
							</td>
							<td align="center" width="12%">${rs.xb }</td>
							<td align="center" width="12%">
								��������
							</td>
							<td align="center" width="14%">${rs.csrq }</td>
							<td rowspan="4" align="center" width="13%">
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:145;height:175" />
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								����
							</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">
								����<br/>��ò
							</td>
							<td align="center">${rs.zzmmmc }</td>
							<td align="center">
								��ѧʱ��
							</td>
							<td align="center">${rs.rxrq }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								���֤����
							</td>
							<td align="center" colspan="3">${rs.sfzh }</td>
							<td align="center">
								��ϵ�绰
							</td>
							<td align="center">${rs.lxdh }</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="6">
								${xxmc }&nbsp;&nbsp;��ѧ&nbsp;&nbsp;&nbsp;&nbsp;
								${rs.xymc }&nbsp;&nbsp;<bean:message key="lable.xb" />&nbsp;&nbsp;&nbsp;&nbsp;
								${rs.zymc }&nbsp;&nbsp;ϵ&nbsp;&nbsp;&nbsp;&nbsp;
								${rs.bjmc }&nbsp;&nbsp;��
						</tr>
						<tr  height="40px">
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
							<td align="center">
								��ͥ����
							</td>
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
							<td align="center">
								��ͥ�˿�����
							</td>
							<td align="center">
								${rs.jtrs }
							</td>
						</tr>
						<tr  height="40px">
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
						<tr  height="40px">
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
						<tr style="height:30px">
							<td rowspan="${cyNum+1 }" align="center">
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
							<td colspan="3">
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
						<tr style="height:30px">
							<td align="center">
									${cy.cyxm }&nbsp;
							</td>
							<td align="center">
									${cy.cynl }&nbsp;
							</td>
							<td align="center"  colspan="2">
									${cy.mc }&nbsp;
							</td>
							<td align="center"  colspan="3">
									&nbsp;
							</td>
						</tr>
						</logic:iterate>
						<tr>
							<td colspan="8">
								<p style="height:180px">
									��������<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
								</p>
								<div>
									���п��ţ�${rs.yhkh }
								</div>
								<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:180px">
									Ժѧ����������������������<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
								</p>
								<div align="right">
									�����£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
