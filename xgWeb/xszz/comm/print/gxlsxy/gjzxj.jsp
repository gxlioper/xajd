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
						������ѧԺ¹ɽѧԺ������ѧ�������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="90%" class="printstyle">
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
						<tr height="30px">
							<td align="center" rowspan="4">����<br/>���</td>
							<td align="center">����</td>
							<td align="center">${rs.xm }</td>
							<td align="center">�Ա�</td>
							<td align="center">${rs.xb }</td>
							<td align="center">��������</td>
							<td align="center">${rs.csrq }</td>
							<td align="center" rowspan="4">
								<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
									border="0" align="absmiddle" style="width:145;height:175" />
							</td>
						</tr>
						<tr height="30px">
							<td align="center">����</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">����<br/>��ò</td>
							<td align="center">${rs.zzmmmc }</td>
							<td align="center">��ѧ����</td>
							<td align="center">${rs.rxrq }</td>
						</tr>
						<tr height="30px">
							<td align="center">���֤����</td>
							<td align="center" colspan="3">${rs.sfzh }</td>
							<td align="center">��ϵ�绰</td>
							<td align="center">${rs.lxdh }</td>
						</tr>
						<tr height="30px">
							<td align="center" colspan="6">
								${rs.xymc }ϵ&nbsp;&nbsp;&nbsp;&nbsp;
								${rs.bjmc }��&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr height="30px">
							<td align="center" rowspan="3">
								��<br/>ͥ<br/>��<br/>��<br/>��<br/>��
							</td>
							<td align="center">��ͥ����</td>
							<td align="center" colspan="4">
									A������ B��ũ��
							</td>
							<td align="center">��ͥ�˿�����</td>
							<td align="center"></td>
						</tr>
						<tr height="30px">
							<td align="center">��ͥ��������</td>
							<td align="center"></td>
							<td align="center" colspan="2">�˾�������</td>
							<td align="center"></td>
							<td align="center">������Դ</td>
							<td align="center"></td>
						</tr>
						<tr height="30px">
							<td align="center">��ͥסַ</td>
							<td align="center" colspan="4"></td>
							<td align="center">��������</td>
							<td align="center"></td>
						</tr>
						
						<tr style="height:30px">
							<td align="center" rowspan="6">
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
									������ѧϰ����λ
							</td>
						</tr>
						<%
						for(int i=0;i<5;i++){
						%>
							<tr height="30px">
								<td align="center">
										&nbsp;&nbsp;
								</td>
								<td align="center">
										&nbsp;&nbsp;
								</td>
								<td align="center" colspan="2">
										&nbsp;&nbsp;
								</td>
								<td align="center" colspan="3">
										&nbsp;&nbsp;
								</td>
							</tr>
						<%} %>
						<tr>
							<td colspan="8">
								<p style="height:80px">
								�������ɣ�
								</p>
								��������ϸ���˲���һ�ݣ�
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
								<p style="height:70px">
								<bean:message key="lable.xb" />����������ע����ʾ�������${rs.shzt1yj }
								</p>
								<div align="right">
									ǩ����
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
								<p style="height:70px">
								ѧ�������������${rs.shzt2yj }
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
						<tr>
							<td colspan="8">
								<p style="height:70px">
								<bean:message key="lable.xb" />��������
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
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע��ѧ�������桢��ʵ��д�˱���������ϳ��ֻѱ����������������ȡ����ѡ�ʸ�
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
