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
						${xxmc }${rs.nj }<logic:notPresent name="rs">${nd }</logic:notPresent>�������<bean:message key="lable.xb" />��ҵ�����¼
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table class="tbstyle" width="100%">
						<tr>
							<td width="7%"></td>
							<td width="8%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
							<td width="17%"></td>
						</tr>
						<tr style="height:35px">
							<td colspan="2" align="center">����</td>
							<td align="center">${rs.xm }</td>
							<td align="center"><bean:message key="lable.xb" />����</td>
							<td align="center">${rs.xymc }</td>
							<td align="center">�������</td>
							<td align="center" >
								${rs.fclxmc }
							</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">�Ա�</td>
							<td align="center">${rs.xb }</td>
							<td align="center">רҵ����</td>
							<td align="center">${rs.zymc }</td>
							<td align="center">ѧ��</td>
							<td align="center">${rs.xl }</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">����</td>
							<td align="center">${rs.sydq }${rs.syds }${rs.sydx }</td>
							<td align="center">�༶</td>
							<td align="center">${rs.bjmc }</td>
							<td align="center">ѧ��</td>
							<td align="center">${rs.xh }</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">����</td>
							<td colspan="3" align="center">${rs.dzyx }</td>
							<td align="center">�ֻ�����</td>
							<td align="center">${rs.sjhm }</td>
						</tr>
						
						<tr style="height:35px">
							<td colspan="2" align="center">��ͥ��ַ</td>
							<td colspan="3" align="center">${rs.lxdz }</td>
							<td align="center">��������</td>
							<td align="center">${rs.yzbh }</td>
						</tr>
						
						<tr style="height:35px">
							<td rowspan="4" align="center">��<br/>��<br/>��<br/>��</td>
							<td align="center">1</td>
							<td colspan="5">
								${rs.shgz1 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">2</td>
							<td colspan="5">
								${rs.shgz2 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">3</td>
							<td colspan="5">
								${rs.shgz3 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">4</td>
							<td colspan="5">
								${rs.shgz4 }
							</td>
						</tr>
						
						<tr style="height:35px">
							<td rowspan="4" align="center">��<br/>��<br/>��<br/>��</td>
							<td align="center">1</td>
							<td colspan="5">
								${rs.hjqk1 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">2</td>
							<td colspan="5">
								${rs.hjqk2 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">3</td>
							<td colspan="5">
								${rs.hjqk3 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">4</td>
							<td colspan="5">
								${rs.hjqk4 }
							</td>
						</tr>
						
						<tr style="height:35px">
							<td rowspan="4" align="center">��<br/>��<br/>��<br/>��</td>
							<td align="center">1</td>
							<td colspan="5">
								${rs.fcsj1 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">2</td>
							<td colspan="5">
								${rs.fcsj2 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">3</td>
							<td colspan="5">
								${rs.fcsj3 }
							</td>
						</tr>
						<tr style="height:35px">
							<td align="center">4</td>
							<td colspan="5">
								${rs.fcsj4 }
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								��ɸ���
							</td>
							<td colspan="5">
								<p style="height:80px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.fcgy }
								</p>
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								��ע
							</td>
							<td colspan="5">
								<p style="height:80px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
								</p>
							</td>
						</tr>
					 </table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
