<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="left">
					<h3>
						����1
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h2>
						ȫ������ѧ��������ѧ����<br/>
						����ѧ���ɲ��Ƽ��ǼǱ�
					</h2>
					<br/><br/>
				</td>
			</tr>
			<tr>
				<td align="left">
					<h3>
						�Ƽ��У�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�Ƽ�ѧУ���ƣ�${xxmc }<br/><br/>
						�Ƽ������������ǰ��̣���<br/><br/>
						��  ������ѧ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��  ������ѧ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��  ������ѧ���ɲ�
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="8%"></td>
							<td width="5%"></td>
							<td width="15%"></td>
							<td width="10%"></td>
							<td width="15%"></td>
							<td width="10%"></td>
							<td width="15%"></td>
							<td width="22%"></td>
						</tr>
						<tr height="70px">
							<td align="center" colspan="2">����</td>
							<td align="center">${rs.xm }</td>
							<td align="center">�Ա�</td>
							<td align="center">${rs.xb }</td>
							<td align="center">����</td>
							<td align="center">${stuMap.mzmc }</td>
							<td align="center" rowspan="3" width="100px">һ<br/>��<br/>��<br/>Ƭ</td>
						</tr>
						<tr height="70px">
							<td align="center" colspan="2">������ò</td>
							<td align="center">${stuMap.zzmmmc }</td>
							<td align="center">��������</td>
							<td align="center" colspan="3">${stuMap.csrq }</td>
						</tr>
						<tr height="70px">
							<td align="center" colspan="3">����ѧУ��ϵ���꼶������ְ��</td>
							<td align="center" colspan="4">${xxmc}&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp;${stuMap.zw }</td>
						</tr>
						<tr height="120px">
							<td align="center" colspan="3">���Ƽ�Ϊȫ������ѧ��<br/>�ĺ�ѡ�˵����֤����</td>
							<td align="center" colspan="5">${stuMap.sfzh }</td>
						</tr>
						<tr height="120px">
							<td align="center" width="70px">��У�ڼ�<br/>��ʱ���<br/>���ֽ���</td>
							<td align="center" colspan="7"></td>
						</tr>
						<tr height="260px">
							<td align="center">��<br/><br/><br/>Ҫ<br/><br/><br/>��<br/><br/><br/>��</td>
							<td align="center" colspan="7"></td>
						</tr>
						
					</table>
					<br/>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="8%"></td>
							<td width="7%"></td>
							<td width="260px"></td>
							<td width="7%"></td>
							<td></td>
						</tr>
						<tr height="180px" >
							<td align="center" width="70px">��<br/><br/><br/>Ҫ<br/><br/><br/>��<br/><br/><br/>��</td>
							<td align="center" colspan="4"></td>
						</tr>
						<tr>
							<td align="center">ѧ<br/><br/>У<br/><br/>��<br/><br/>��</td>
							<td align="center" colspan="4">
								<p style="height:200px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
									<div style="margin-bottom: 0px" align="right">
										��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
						</tr>
						<tr height="200px" >
							<td align="center" >��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</td>
							<td align="center" colspan="2">
								<p style="height:180px">
									<div style="margin-bottom: 0px" align="right">
										��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
							<td align="center" width="100px">��<br/>��<br/>ί<br/>��<br/>��</td>
							<td align="center">
								<p style="height:180px">
									<div style="margin-bottom: 0px" align="right">
										��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								${rs.nd }��ȫ������ѧ��������ѧ��������ѧ���ɲ����Ƚ��༯�������쵼С�����
							</td>
							<td colspan="3">
								<p style="height:200px">
									<div style="margin-bottom: 0px" align="right">
										��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">��ע</td>
							<td align="left" colspan="4">
								<p style="height:40px">
									1.�˱�һʽ���ݣ�����һ����ѧ�����ڵ�ѧУ���棬��һ����Ϊ�Ƽ������ϱ���������������<br/>
									2.���Ƽ�Ϊȫ������ѧ���ĺ�ѡ�ˣ�����Ҫ��д�ñ����⣬��Ҫ�õ����˳ƣ�������������׫д1ƪ1500�ֵ��Ƚ��¼����ϣ����Ӱ棩���������󡢹�ʾ��
									
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
