<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title>��ͥ��������ѧ���϶������</title>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
			<span style="font-size:22px;font-family:����">���ݷ�خְҵ����ѧԺ��ͥ��������ѧ���϶������</span>
			<br/><br/>
			<span style="font-size:12px;">
				ϵ��Ժ����<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;</u>
				�꼶���༶��<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.nj}-${rs.bjmc }&nbsp;&nbsp;&nbsp;&nbsp;</u>
				ѧ�ţ�<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;</u></span>
		</center>
		<table class="printtab">
			<tr>
				<td width="48" rowspan="2">
					<p align="center">
						<strong>ѧ�� </strong>
					</p>
					<p align="center">
						<strong>���� </strong>
					</p>
					<p align="center">
						<strong>���� </strong>
					</p>
					<p align="center">
						<strong>��� </strong>
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="108" colspan="4">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td width="60" colspan="3">
					<p align="center">
						<strong>�Ա� </strong>
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td width="96" colspan="4">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="96" colspan="5">
					<p align="center">
						${rs.mzmc }
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						<strong>��Դ�� </strong>
					</p>
				</td>
				<td width="108" colspan="3">
					<p align="center">
						${rs.syd }
					</p>
				</td>
			</tr>
			<tr>
				<td width="120" colspan="4">
					<p align="center">
						<strong>�¶����� </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<logic:present name="rs">
							<logic:equal name="rs" property="sfdq" value="��">
								<logic:notEqual name="rs" property="sfge" value="��">
									�� ��  �� ��
								</logic:notEqual>
							</logic:equal>
							<logic:equal name="rs" property="sfge" value="��">
								<logic:notEqual name="rs" property="sfdq" value="��">
									�� ��  �� ��
								</logic:notEqual>
							</logic:equal>
							<logic:equal name="rs" property="sfdq" value="��">
								<logic:equal name="rs" property="sfge" value="��">
									�� ��  �� ��
								</logic:equal>
							</logic:equal>
								<logic:notEqual name="rs" property="sfdq" value="��">
									<logic:notEqual name="rs" property="sfge" value="��">
										�� ��   �� ��
									</logic:notEqual>
								</logic:notEqual>
						</logic:present>
						<logic:notPresent name="rs">
							�� ��   �� ��
						</logic:notPresent>
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						<strong>�ͱ� </strong>
					</p>
				</td>
				<td width="96" colspan="4">
					<p align="center">
						<logic:present name="rs">
							<logic:equal value="��" name="rs" property="sfdb">
								�� ��   �� ��
							</logic:equal>
							<logic:equal value="��" name="rs" property="sfdb">
								�� ��   �� ��
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs">
							�� ��  �� ��
						</logic:notPresent>
					</p>
				</td>
				<td width="96" colspan="5">
					<p align="center">
						<strong>��ϵ�绰 </strong>
					</p>
				</td>
				<td width="180" colspan="5">
					<p align="center">
						${rs.lxdh }
					</p>
				</td>
			</tr>
			<tr>
				<td width="48" rowspan="5">
					<p align="center">
						<strong>��ͥ��Ա��� </strong>
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="80" colspan="3">
					<p align="center">
						<strong>��ѧ����ϵ</strong>
					</p>
				</td>
				<td width="84" colspan="2">
					<p align="center">
						<strong>ְҵ </strong>
					</p>
				</td>
				<td width="95" colspan="4">
					<p align="center">
						<strong>�����루Ԫ��</strong>
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="48" colspan="3">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="80" colspan="2">
					<p align="center">
						<strong>��ѧ����ϵ </strong>
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						<strong>ְҵ </strong>
					</p>
				</td>
				<td width="80">
					<p align="center">
						<strong>�����루Ԫ��</strong>
					</p>
				</td>
			</tr>
			
			<%
				List<HashMap<String,String>> cyList = (List<HashMap<String,String>>)request.getAttribute("cyList");
				
				int i = 0;
				while (i < cyList.size() && i<6){
			 %>
				<tr>
					<td width="84" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyxm") ? cyList.get(i).get("cyxm") : "" %>
						</p>
					</td>
					<td width="48" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynl") ? cyList.get(i).get("cynl") : "" %>
						</p>
					</td>
					<td width="60" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("mc") ? cyList.get(i).get("mc") : "" %>
						</p>
					</td>
					<td width="84" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyzy") ? cyList.get(i).get("cyzy") : "" %>
						</p>
					</td>
					<td width="60" colspan="4">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynsr") ? cyList.get(i).get("cynsr") : "" %>
						</p>
					</td>
					<%i++; %>
					<td width="72" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyxm") ? cyList.get(i).get("cyxm") : "" %>
						</p>
					</td>
					<td width="48" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynl") ? cyList.get(i).get("cynl") : "" %>
						</p>
					</td>
					<td width="60" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("mc") ? cyList.get(i).get("mc") : "" %>
						</p>
					</td>
					<td width="84" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyzy") ? cyList.get(i).get("cyzy") : "" %>
						</p>
					</td>
					<td width="60">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynsr") ? cyList.get(i).get("cynsr") : "" %>
						</p>
					</td>
				</tr>
			<%
				i++;
				}
				
				int n = 0;
								
				if (cyList.size()%2==1){
					n = 3 - (cyList.size()+1)/2;
				} else {
					n = 3 - cyList.size()/2;
				}
				
				for (int j = 0 ; j < n; j++){
			 %>			
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="84" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60" colspan="4">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="48" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60">
					<p align="center">
						&nbsp;
					</p>
				</td>
			</tr>
			<%
				}
			 %>
			<tr>
				<td width="661" colspan="25">
					<p>
						�������������������������ע��
					</p>
				</td>
			</tr>
			<tr>
				<td width="48">
					<p align="center">
						<strong>ѧ�����������϶����� </strong>
					</p>
				</td>
				<td width="661" colspan="25" valign="bottom">
					<p valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					</p>
					<br/>
					<p>
						ע��������ѧ��������д�������� ��ί���������д��
					</p>
				</td>
			</tr>
			<tr>
				<td width="48" rowspan="11">
					<p align="center">
						<strong>��һѧ��ѧϰ��� </strong>
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						<strong>ʱ�� </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>�񽱡���ѧ�� </strong>
					</p>
					<p align="center">
						<strong>�������������� </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>ʱ�� </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>�񽱡���ѧ�� </strong>
					</p>
					<p align="center">
						<strong>�������������� </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>ʱ�� </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>�񽱡���ѧ�� </strong>
					</p>
					<p align="center">
						<strong>�������������� </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="120" colspan="4">
					<p align="center">
						<strong>������� </strong><strong></strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>�ٵ���� </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>������� </strong>
					</p>
				</td>
				<td width="324" colspan="11">
					<p align="center">
						<strong>����Υ�ʹ������ </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="120" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="324" colspan="11">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="48" rowspan="4">
					<p align="center">
						<strong>���� </strong>
					</p>
					<p align="center">
						<strong>��� </strong>
					</p>
					<p align="center">
						<strong>Ŀ�� </strong>
					</p>
					<p align="center">
						<strong>��� </strong>
					</p>
				</td>
				<td width="126" colspan="6">
					<p align="center">
						<strong>�γ����� </strong>
					</p>
				</td>
				<td width="54" colspan="2">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>�γ����� </strong>
					</p>
				</td>
				<td width="81" colspan="3">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
				<td width="162" colspan="6">
					<p align="center">
						<strong>�γ����� </strong>
					</p>
				</td>
				<td width="81" colspan="2">
					<p align="center">
						<strong>���� </strong>
					</p>
				</td>
			</tr>
			
			
			<%
				List<HashMap<String,String>> bjgcjList = (List<HashMap<String,String>>)request.getAttribute("bjgcjList");
				
				int j = 0;
				while (j < bjgcjList.size()){
			 %>
			
			
			<tr>
				<td width="126" colspan="6">
					<p align="center">
						<%=j < bjgcjList.size()  && null != bjgcjList.get(j).get("kcmc") ? bjgcjList.get(j).get("kcmc") : "" %>
					</p>
				</td>
				<td width="54" colspan="2">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("cj") ? bjgcjList.get(j).get("cj") : "" %>
					</p>
				</td>
				<%j++; %>
				<td width="108" colspan="5">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("kcmc") ? bjgcjList.get(j).get("kcmc") : "" %>
					</p>
				</td>
				<td width="81" colspan="3">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("cj") ? bjgcjList.get(j).get("cj") : "" %>
					</p>
				</td>
				<%j++; %>
				<td width="162" colspan="6">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("kcmc") ? bjgcjList.get(j).get("kcmc") : "" %>
					</p>
				</td>
				<td width="81" colspan="2">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("cj") ? bjgcjList.get(j).get("cj") : "" %>
					</p>
				</td>
			</tr>
			
			<%
					j++;
				}
				
				for (int c = 0 ; c < 3-j/3 ; c++){
			 %>
			<tr>
				<td width="126" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="54" colspan="2">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="81" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="162" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="81" colspan="2">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<%
				}
			 %>
			<tr>
				<td width="48" rowspan="3">
					<p align="center">
						<strong>�������� </strong>
					</p>
				</td>
				<td width="48" rowspan="3">
					<p align="center">
						<strong>�Ƽ����� </strong><strong></strong>
					</p>
				</td>
				<td width="240" colspan="10">
					<p>
						
						<strong>
							<logic:equal value="��ͥ����һ������" property="knjb" name="rs">
								��
							</logic:equal>
							<logic:notEqual value="��ͥ����һ������" property="knjb" name="rs">
								�� 
							</logic:notEqual>
							A ����ͥ����һ������ 
						</strong>
					</p>
				</td>
				<td width="36" colspan="2" rowspan="3">
					<p align="center">
						<strong>�������� </strong><strong></strong>
					</p>
				</td>
				<td width="336" colspan="12" rowspan="3" valign="bottom">
					<p>
						<strong>��ί�������ǩ�֣� </strong>
					</p>
					<p align="right">
						<strong>
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							�� 
						</strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="240" colspan="10">
					<p>
						<strong>
							<logic:equal value="��ͥ������������" property="knjb" name="rs">
								��
							</logic:equal>
							<logic:notEqual value="��ͥ������������" property="knjb" name="rs">
								�� 
							</logic:notEqual>
							 B ����ͥ������������ 
						</strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="240" colspan="10">
					<p>
						<strong>
							<logic:equal value="��ͥ���ò�����" property="knjb" name="rs">
								��
							</logic:equal>
							<logic:notEqual value="��ͥ���ò�����" property="knjb" name="rs">
								�� 
							</logic:notEqual>
								C ����ͥ���ò����� 
						</strong>
					</p>
				</td>
			</tr>
			<tr height="100">
				<td width="48">
					<p align="center">
						<strong>��ע </strong>
					</p>
				</td>
				<td width="661" colspan="25"  valign="top">
						&nbsp;&nbsp;&nbsp;${rs.bz }
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>




		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				ֱ�Ӵ�ӡ
			</button>
		</div>
	</body>
</html>
