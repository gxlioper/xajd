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
						����ʦ��ѧԺ������־��ѧ������������
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center" style="font-size:15px;">
						��${rs.xn }ѧ�꣩
				</td>
			</tr>
			<tr>
			<td align="center" style="font-size:15px;">
						<p align=center><bean:message key="lable.xb" />��${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;רҵ��${rs.zymc }&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�༶��${rs.bjmc }</p> 
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
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
							<td align="center" width="8%">${rs.xb }</td>
							<td align="center" width="14%">
								��������
							</td>
							<td align="center" width="14%">${rs.csrq }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								ѧ��
							</td>
							<td align="center">${rs.xh }</td>
							<td align="center">
								����
							</td>
							<td align="center">${rs.mzmc }</td>
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
								������ò
							</td>
							<td align="center">${rs.zzmmmc }</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								���������϶�����
							</td>
							<td align="center" colspan="3">${rs.knjb }</td>
							<td align="center">
								��ϵ�绰
							</td>
							<td align="center">${rs.lxdh }</td>
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
								��������
							</td>
							<td align="center">
								${rs.jtyb }
							</td>
						</tr>
						<tr  height="40px">
							<td align="center">
								��ͥ�˿�����
							</td>
							<td align="center">
								${rs.jtrs }
							</td>
							<td align="center">
								��ͥ��������
							</td>
							<td align="center">
								${rs.jtnzsr }
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
							<td align="center" colspan="5">
								${rs.jtdz }
							</td>
						
						</tr>
						<tr  height="40px">
							<td align="center">
								��
								<br />
								��
								<br />
								��
								<br />
								��
							</td>
							<td align="center" colspan="6" >
								���꼶רҵ��������<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>         ��ѧϰ�ɼ�������<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>         ���ۺϿ���������<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>       ��
							</td>
							
						</tr>
					<tr>
							<td align="center">
								��
								<br />
								��
								<br />
								��
								<br />
								��
								<br />
							</td>
							<td colspan="6">
								<p style="height:220px">
									��Ҫ���
									</br>
										${rs.sqly}
								</p>
							
								<div align="right" style="margin-bottom: px20">
									���У�Ժ������<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>      �� У������<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>      � ʡ�����Ͻ���<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>     �
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">
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
								<br />
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
								��
							</td>
							<td colspan="6">
								<p style="height:220px">
										${rs.sqly}
								</p>
							
								<div align="right" style="margin-bottom: px20">
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
							<td colspan="3">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xb" />�����
									<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
								</p>
								<div align="right">
									�����£�
									ǩ��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="4">
								<p style="height:200px">
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
