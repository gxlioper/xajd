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
						����2&nbsp;&nbsp;&nbsp;&nbsp;�ߵ�ѧУ��ͥ��������ѧ���϶������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="left">
					<h3>
						ѧУ����������ְҵ����ѧԺ��������ѧְ��ѧԺ��
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="6%"></td>
							<td width="8%"></td>
							<td width="11%"></td>
							<td width="11%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td width="8%"></td>
							<td width="8%"></td>
							<td></td>
							<td width="9%"></td>
						</tr>
						<tr height="40px">
							<td rowspan="4" align="center"><strong>ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</strong></td>
							<td colspan="2" align="center">��&nbsp;&nbsp;��</td>
							<td align="center">${rs.xm }&nbsp;</td>
							<td colspan="2" align="center">�Ա�</td>
							<td align="center" colspan="2">${rs.xb }&nbsp;</td>
							<td align="center" colspan="2">ѧ��</td>
							<td align="center" colspan="2">${rs.xh }&nbsp;</td>
							<td align="center">����</td>
							<td align="center">${rs.mzmc }&nbsp;</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">���֤����</td>
							<td align="center" colspan="3">${rs.sfzh }&nbsp;</td>
							<td align="center" colspan="2">������ò</td>
							<td align="center" colspan="2">${rs.zzmmmc }&nbsp;</td>
							<td align="center" colspan="2">��ͥ�˾�������</td>
							<td align="center" colspan="2">${rs.jtrjsr }&nbsp;&nbsp;Ԫ</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">ѧ  Ժ</td>
							<td align="center" colspan="2">${rs.xymc }&nbsp;</td>
							<td align="center">ϵ</td>
							<td align="center" colspan="4"></td>
							<td align="center">רҵ</td>
							<td align="center" colspan="3">${rs.zymc }&nbsp;</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">��ͥ��ϵ�绰</td>
							<td align="center" colspan="3">${rs.jtdh }&nbsp;</td>
							<td align="center" colspan="3">��У��ϵ�绰</td>
							<td align="center" colspan="5">${rs.lxdh }&nbsp;</td>
						</tr>
						<tr>
							<td align="center"><strong>ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</strong></td>
							<td colspan="13">
								<p style="height:160px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.rdly }
								</p>
								<div align="right">
									ѧ��ǩ�֣�
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 ____________��_______��_____��
								</div>
								<div align="left">
									<strong>ע��������ϸ���˵����</strong>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center" rowspan="3"><strong>��<br/>��<br/>��<br/>��</strong> </td>
							<td align="center" rowspan="3">��<br/>��<br/>��<br/>��</td>
							<td colspan="2">A.��ͥ�������� ��</td>
							<td colspan="2" rowspan="3" align="center">��<br/>��<br/>��<br/>��</td>
							<td rowspan="3" colspan="8">
								<p style="height:120px">
								
								</p>
								����С���鳤�������Σ�
								<div align="right">
									ǩ�֣�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									_______��_____��____��&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">B.��ͥ������������  ��</td>
						</tr>
						<tr>
							<td colspan="2">C.��ͥ���ò�����   ��</td>
						</tr>
						<tr>
							<td align="center"><strong>��<br/>��<br/>��<br/>��</strong> </td>
							<td align="center"><bean:message key="lable.xb" /><br/>���</td>
							<td colspan="4">
								������С���Ƽ�����<bean:message key="lable.xb" />������<br/>�˺�<br/>
								��  ͬ������С�������<br/>
								��  ��ͬ������С�����������<br/>
								Ϊ_______________��
								<br/><br/><br/>
								�������鳤��ѧ�����������ˣ�<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								ǩ�֣�<br/><br/><br/>
								<div align="right">
								       	_______��___��__��&nbsp;<br/>
								       	���Ӹ�<bean:message key="lable.xb" />���£�&nbsp;&nbsp;&nbsp;&nbsp;
								       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td align="center">ѧУ<br/>ѧ��<br/>����<br/>����<br/>����<br/>���</td>
							<td colspan="7">
								��ѧ������<bean:message key="lable.xb" />���룬�����������ʵ��<br/>
								��  ͬ�⹤���������С�������<br/>
								��  ��ͬ�⹤���������С�����������Ϊ��<br/>
								_______________________________��<br/>
								������ǩ�֣�<br/><br/><br/><br/><br/><br/>
								       <div align="right">
								       	_________��______��____��&nbsp;<br/>
								       	���Ӹǲ��Ź��£�&nbsp;&nbsp;&nbsp;&nbsp;
								       	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
