<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
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
				<td align="left">
					<h3>
						509
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h2>
						����ѧԺѧ����ʱ���Ѳ��������V1.4
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="6%"></td>
							<td width="8%"></td>
							<td width="1%"></td>
							<td width="3%"></td>
							<td width="7%"></td>
							<td width="10%"></td>
							<td width="3%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="2%"></td>
							<td width="1%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">����</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center" colspan="2">�Ա�</td>
							<td align="center">${rs.xb }</td>
							<td align="center">����</td>
							<td align="center"colspan="2">${rs.mzmc }</td>
							<td align="center"colspan="2">ѧ��</td>
							<td align="center"colspan="3">${rs.xh }</td>
							<td align="center"colspan="2">����</td>
							<td align="center"colspan="3">${rs.ssbh }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">��λ</td>
							<td align="center" colspan="13">${rs.xymc }&nbsp;&nbsp;ϵ&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp;�꼶&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp;רҵ&nbsp;&nbsp;</td>
							<td align="center" colspan="2">�绰</td>
							<td align="center" colspan="3">${rs.lxdh }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="4">��ͥ��ַ����<br/>������</td>
							<td align="center" colspan="6">${rs.jtdz }&nbsp;&nbsp;${rs.jtyb }</td>
							<td align="center" colspan="2">��ͥ<br/>�绰</td>
							<td align="center" colspan="3">${rs.jtdh }</td>
							<td align="center" colspan="3">���벹<br/>�����</td>
							<td align="center" colspan="2"></td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="10">����Ժ��ϵ��ѧϰ�ɼ�����:&nbsp;&nbsp;&nbsp;&nbsp;${rs.cjpmbl }&nbsp;&nbsp;%</td>
							<td align="center" colspan="10">����Ժ��ϵ���ۺϲ�������:&nbsp;&nbsp;&nbsp;&nbsp;${rs.zjfpmBl }&nbsp;&nbsp;%</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3" rowspan="2">�Ƿ���<br/>����<br/>�����</td>
							<td align="center" colspan="2" rowspan="2">${rs.sfydk }<br/>${rs.ydkje }</td>
							<td align="center" colspan="3" rowspan="2">���ܺ����壨����ˣ������������</td>
							<td align="center" colspan="2" rowspan="2">${rs.jsnzz }<br/>${rs.jszzje }</td>
							<td align="center" colspan="2" rowspan="2">��ʱ���<br/>���Ѳ���<br/>�����</td>
							<td align="center" colspan="2" rowspan="2">${rs.hslgbz }<br/>${rs.bzje }</td>
							<td align="center" colspan="4">��ѧ���Ƿ�<br/>���ڹ�</td>
							<td align="center" colspan="2">${rs.sxqqgje }Ԫ/�¡�${rs.sxqqgys }&nbsp;��</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="4">��ѧ���Ƿ�<br/>���ڹ�</td>
							<td align="center" colspan="2">${rs.bxqqgje }Ԫ/�¡�${rs.bxqqgys }&nbsp;��</td>
						</tr>
						
						<%
							List<HashMap<String, String>> cyList = (List<HashMap<String, String>>)request.getAttribute("cyList");
						 %>
						
						<tr height="40px">
							<td align="center" rowspan="<%=cyList.size()/2+2 %>">��<br/>ͥ<br/>��<br/>��<br/>��<br/>��</td>
							<td align="center" colspan="3">��ͥ<br/>����</td>
							<td align="center" colspan="3">${rs.jthk }</td>
							<td align="center" colspan="4">��ͥ���˿�</td>
							<td align="center"></td>
							<td align="center" colspan="2">��ͥ������</td>
							<td align="center" colspan="4"></td>
							<td align="center">�˾�������</td>
							<td align="center">Ԫ</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3">����</td>
							<td align="center">��<br/>��</td>
							<td align="center" colspan="2">��ν</td>
							<td align="center" colspan="4">�ںδ�����<br/>��ѧϰ</td>
							<td align="center">ÿ������</td>
							<td align="center" colspan="2">����</td>
							<td align="center">����</td>
							<td align="center" colspan="3">��ν</td>
							<td align="center">�ںδ�������<br/>ѧϰ</td>
							<td align="center">ÿ��<br/>����</td>
						</tr>
						
						<%
							int i=0;
							while (i<cyList.size()){
						 %>
							<tr height="40px">
								<td align="center" colspan="3">
									<%=null==cyList.get(i).get("cyxm")?"":cyList.get(i).get("cyxm") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynl")?"":cyList.get(i).get("cynl") %>&nbsp;
								</td>
								<td align="center" colspan="2">
									<%=null==cyList.get(i).get("mc")?"":cyList.get(i).get("mc") %>
								</td>
								<td align="center" colspan="4">
									<%=null==cyList.get(i).get("cygzdw")?"":cyList.get(i).get("cygzdw") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynsr")?"":Math.rint(Integer.parseInt(cyList.get(i).get("cynsr"))/12) %>
								</td>
								<%
									i++;
								 %>
								<td align="center" colspan="2">
									<%=null==cyList.get(i).get("cyxm")?"":cyList.get(i).get("cyxm") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynl")?"":cyList.get(i).get("cynl") %>&nbsp;
								</td>
								<td align="center" colspan="3">
									<%=null==cyList.get(i).get("mc")?"":cyList.get(i).get("mc") %>
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cygzdw")?"":cyList.get(i).get("cygzdw") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynsr")?"":Math.rint(Integer.parseInt(cyList.get(i).get("cynsr"))/12) %>
								</td>
							</tr>
						
						<%
							i++;
							}
						 %>
						 <tr>
						 	<td align="center">��<br/>��<br/>��<br/>��</td>
						 	<td colspan="19">
						 		<p style="height:100px">
						 			&nbsp;&nbsp;&nbsp;&nbsp;
						 			${rs.sqly }
						 		</p>
						 		���˱�֤����������ʵ����
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		����ǩ����
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 	</td>
						 </tr>
						 <tr height="40px">
						 	<Td colspan="7" align="center">�༶�϶�����С�����</Td>
						 	<Td colspan="6" align="center">Ժϵ�������������</Td>
						 	<Td colspan="7" align="center">Уѧ���������������������</Td>
						 </tr>
						 <tr height="40px">
						 	<Td colspan="7">
						 		<p style="height:100px">
						 		
						 		</p>
						 		<div align="right">
						 			������ǩ����
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			��&nbsp;&nbsp;&nbsp;&nbsp;
						 		</div>
						 	</Td>
						 	<Td colspan="6">
						 		<p style="height:80px">
						 		
						 		</p>
						 		<div align="right">
						 			������ǩ����
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			���ǹ��£�
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			��&nbsp;&nbsp;&nbsp;&nbsp;
						 		</div>
						 	</Td>
						 	<Td colspan="7">
						 		<p style="height:80px">
						 		�������о�������ͬ�ⲹ������<br/>__________Ԫ����
						 		</p>
						 		<div align="right">
						 			������ǩ����
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			���ǹ��£�
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			��&nbsp;&nbsp;&nbsp;&nbsp;
						 		</div>
						 	</Td>
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
