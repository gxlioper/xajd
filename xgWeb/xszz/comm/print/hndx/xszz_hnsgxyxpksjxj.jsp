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
						����ʡ��ͨ��У����ƶ������ѧ�������
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td rowspan="6" align="center" width="7%">
								<br />
								<br />
								����
								<br />
								���
								<br />
								<br />
							</td>
							<td align="center" width="16%">
								����
							</td>
							<td align="center" width="16%">${rs.xm }</td>
							<td align="center" width="10%">
								�Ա�
							</td>
							<td align="center" width="8%">${rs.xb }</td>
							<td align="center" width="14%">
								��������
							</td>
							<td align="center" width="14%">${rs.csrq }</td>
							<td align="center" width="15%" rowspan="4">
								<img
									src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
									border="0" align="absmiddle" style="width:140;height:160" />
							</td>
						</tr>
						<tr  height="40px">							
							<td align="center">
								����
							</td>
							<td align="center">${rs.mzmc }</td>
							<td align="center">
								������ò
							</td>
							<td align="center">${rs.zzmmmc }</td>
							<td align="center">
								��ѧʱ��
							</td>
							<td align="center">${rs.rxrq }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								���֤��
							</td>
							<td align="center" colspan="3">${rs.sfzh }</td>
							<td align="center">
								��ϵ�绰
							</td>
							<td align="center">${rs.lxdh }</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="6">
								���ϴ�ѧ
								<logic:empty name="rs" property="xymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<bean:message key="lable.xb" />
								</logic:empty>
								${rs.xymc}
								<logic:empty name="rs" property="zymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								ϵ 
								</logic:empty>
								${rs.zymc}
								<logic:empty name="rs" property="bjmc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								�� 
								</logic:empty>
								${rs.bjmc}
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								ѧ��
							</td>
							<td align="center" colspan="3">${rs.xh}</td>
							<td align="center">
								���п���
							</td>
							<td align="center" colspan="2">${rs.yhkh }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								������ֽ���
							</td>
							<td align="center" colspan="6">${rs.hjqk }</td>							
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
							<td align="center" colspan="3">
									A��<logic:equal value="����" property="jthk" name="rs">
										��
									</logic:equal>����
									&nbsp;&nbsp;
									B��<logic:equal value="ũ��" property="jthk" name="rs">
										��
									</logic:equal>
									ũ��
							</td>
							<td align="center">
								��ͥ�˿�����
							</td>
							<td align="center" colspan="2">
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
							<td align="center">
								�˾�������
							</td>
							<td align="center">
								${rs.jtrjysr}
							</td>
							<td align="center">
								������Դ
							</td>
							<td align="center" colspan="2"">
								${rs.srly }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								��ͥסַ
							</td>
							<td align="center" colspan="3">
								${rs.jtdz }
							</td>
							<td align="center">
								��������
							</td>
							<td align="center" colspan="2">
								${rs.jtyb }
							</td>
						</tr>
						<!--ѧ���ɼ�-->
						<tr>
							<td colspan="8">
								<p style="height:100px">
									&nbsp;&nbsp;&nbsp;&nbsp;ѧϰ�ɼ���
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:notEmpty name="cjList">
									<table width="100%" class="tbstyle">
										<tr>
											<td>
												ѧ��
										    </td>
											<td>
												ѧ��
										    </td>
											<td>
												�γ�����
										    </td>
											<td>
												�γ�����
										    </td>
											<td>
												�ɼ�
										    </td>
										</tr>
										<logic:iterate id="cj" name="cjList">
										<tr>
											<td>
												${cj.xn}
										    </td>
											<td>
												${cj.xq}
										    </td>
											<td>
												${cj.kcxz}
										    </td>
											<td>
												${cj.kcmc}
										    </td>
											<td>
												${cj.cj}
										    </td>
										</tr>
										</logic:iterate>										
									</table>
									</logic:notEmpty>
								</p>
								<div align="right">
									����Ƹ��£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:100px">
									&nbsp;&nbsp;&nbsp;&nbsp;�������ɣ�
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
								</p>
								<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<p style="height:100px">
									&nbsp;&nbsp;&nbsp;&nbsp;ѧУ�����
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
								</p>
								<div align="right">
									�����£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>	
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע���˱�һʽ���ݣ�һ�ݽ���������һ��ѧ�����������ס�</div>	
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
