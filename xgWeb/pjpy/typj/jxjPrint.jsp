<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<body>
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						${xxmc }��ѧ�������
					</h2>
					<br/>
					<logic:notPresent name="rs">
						��20&nbsp;&nbsp;&nbsp;&nbsp;---20&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣩
					</logic:notPresent>
					<logic:present name="rs">
						��${rs.xn } &nbsp;&nbsp;ѧ�꣩	
					</logic:present>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
							<td width="8%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="2">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center" colspan="2">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
							<td align="center" colspan="2">${rs.xb }</td>
							<td align="center" colspan="2">ѧ&nbsp;&nbsp;&nbsp;&nbsp;��</td>
							<td align="center" colspan="2">${rs.xh }</td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="2">ѧ&nbsp;&nbsp;&nbsp;&nbsp;Ժ</td>
							<td align="center" colspan="2">${rs.xymc }</td>
							<td align="center" colspan="2">ר&nbsp;&nbsp;&nbsp;&nbsp;ҵ</td>
							<td align="center" colspan="2">${rs.zymc }</td>
							<td align="center" colspan="2">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
							<td align="center" colspan="2">${rs.bjmc }</td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="2">�༶����</td>
							<td align="center" colspan="2">${rs.bjrs }</td>
							<td align="center" colspan="2">ѧ���ֳɼ�</td>
							<td align="center" colspan="2">${rs.xjfcj }</td>
							<td align="center" colspan="2">ѧ��������</td>
							<td align="center" colspan="2">${rs.xjfpm }</td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="3">ѧ��Ʒ�����۵ȼ�</td>
							<td align="center" colspan="5">${rs.pxdj }</td>
							<td align="center" colspan="2">��ѧ�����ʽ�����׼���ȼ�</td>
							<td align="center" colspan="2">${rs.jkdj }</td>
						</tr>
						<tr height="60px">
							<td align="center">����<br/>����</td>
							<td align="center" colspan="11">${rs.jxjmc }</td>
						</tr>
						<tr height="180px">
							<td align="center">����<br/>����</td>
							<td colspan="11">
								<p style="height:160px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
									<div align="right">
										������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
									
								</p>
							</td>
						</tr>
						<tr height="60px">
							<td align="center">����<br/>����</td>
							<td align="center" colspan="11"></td>
						</tr>
						<tr>
							<td align="center">�༶<br/>����<br/>���</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									<div align="right">
									
									����ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
							<td align="center">ѧ��<br/>����<br/>����<br/>����<br/>����<br/>���</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									<div align="right">
									
									��&nbsp;&nbsp;&nbsp;�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">ѧ<br/>Ժ<br/>��<br/>��<br/>��<br/>��</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
									<div align="right">
									
									��&nbsp;&nbsp;&nbsp;�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
							<td align="center">ѧ<br/>У<br/>��<br/>��<br/>��<br/>��</td>
							<td align="center" colspan="5">
								<p style="height:110px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
									<div align="right">
									
									��&nbsp;&nbsp;&nbsp;�£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>		  
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
								</p>
							</td>
						</tr>
					</table>
					<div align="left">
						ע���������� A4ֽ��ӡ����ɫīˮ�ֱʻ�ˮ����д
					</div>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
