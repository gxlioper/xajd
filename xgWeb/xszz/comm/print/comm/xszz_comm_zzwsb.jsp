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
						${xxmc }${rs.xmmc }ѧ�����������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="5%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
						</tr>
						<tr height="40px">
							<td align="center" rowspan="4">ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</td>
							<td align="center" colspan="2">��&nbsp;&nbsp;��</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center" colspan="2">��&nbsp;&nbsp;��</td>
							<td align="center">${rs.xb }</td>
							<td align="center">��������</td>
							<td align="center" colspan="2">${rs.csrq }</td>
							<td align="center">����</td>
							<td align="center">${rs.mzmc }</td>
							
							
						</tr>
						<tr  height="40px">
							<td align="center" colspan="2">���֤����</td>
							<td align="center" colspan="4">${rs.sfzh }</td>
							<td align="center">������ò</td>
							<td align="center" colspan="2">${rs.zzmmmc }</td>
							<td align="center" colspan="2">��ͥ�˾�������</td>
							<td align="center" >${rs.jtrjsr }&nbsp;Ԫ</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="2">Ժϵ</td>
							<td align="center" colspan="5">${rs.xymc }</td>
							<td align="center" colspan="2">רҵ</td>
							<td align="center" colspan="3">${rs.zymc }</td>
						</tr>
						<tr  height="40px">
							<td align="center" colspan="2">�༶</td>
							<td align="center" colspan="3">${rs.bjmc }</td>
							<td align="center" colspan="2">�꼶</td>
							<td align="center" >${rs.nj }</td>
							<td align="center" colspan="2">��ϵ�绰</td>
							<td align="center" colspan="2">${rs.lxdh }</td>
						</tr>
						<tr>
							<td align="center">��<br/><br/>��<br/><br/>��<br/><br/>��</td>
							<td colspan="12">
								<p style="height:220px">${rs.sqsm }</p>
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">ѧ<br/>Ժ<br/>��<br/>��<br/>��<br/>��</td>
							<td colspan="12">
								<p style="height:220px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
								</p>
								<div align="right">
									���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">ѧ<br/>��<br/>��<br/>��<br/>��<br/>��<br/>��</td>
							<td colspan="12">
								<p style="height:220px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
								</p>
								<div align="right">
									���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
