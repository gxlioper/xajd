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
				<td align="center">
					<h2>
						���ݴ�ѧ<logic:present name="rs">${rs.xn }</logic:present>
						<logic:notPresent name="rs">200&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;200&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
						��Ƚ�ѧ��������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="left">
					ѧ�ţ�${rs.xh } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<bean:message key="lable.xb" />��${rs.xymc }
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">����</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center">רҵ</td>
							<td align="center" colspan="2">${rs.zymc }</td>
							<td align="center">�꼶</td>
							<td align="center">${rs.nj }</td>
							<td align="center">�Ա�</td>
							<td align="center">${rs.xb }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">����</td>
							<td align="center"></td>
							<td align="center">����</td>
							<td align="center">${stuMap.mzmc }</td>
							<td align="center">������ò</td>
							<td align="center" colspan="2">
								${stuMap.zzmmmc }
							</td>
							<td align="center">�κ�ְ��</td>
							<td align="center" colspan="2">
								${stuMap.zw }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3">�Ƿ��ܹ�����</td>
							<td align="center" colspan="2">
								<logic:present name="rs">
									�ǩ����
								</logic:present>
								<logic:notPresent name="rs">
									�ǩ���
								</logic:notPresent>
							</td>
							<td align="center">��ѧ������</td>
							<td align="center" colspan="2">${rs.jxjmc }</td>
							<td align="center" >�Ƿ�Ƿѧ��</td>
							<td align="center" colspan="2">
								<logic:present name="rs">
									<logic:equal value="��" property="sfqf" name="rs">
										�ǡ̷�
									</logic:equal>
									<logic:equal value="��" property="sfqf" name="rs">
										�ǩ����
									</logic:equal>
									<logic:equal value="" property="sfqf" name="rs">
										�ǩ���
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs">
									�ǩ���
								</logic:notPresent>
							</td>
						</tr>
						
						<%
							List<HashMap<String,String>> clList1 = (List<HashMap<String,String>>)request.getAttribute("cjList1");
							List<HashMap<String,String>> clList2 = (List<HashMap<String,String>>)request.getAttribute("cjList2");
							
							int m = null !=clList1 && !clList1.isEmpty() ?  Double.valueOf(clList1.size()/3).intValue()+1 : 0;
							int n = null !=clList2 && !clList2.isEmpty() ?  Double.valueOf(clList2.size()/3).intValue()+1 : 0;
						 %>
						
						<tr height="40px">
							<td align="center" rowspan="<%=m+n+2 %>">ԭ<br/><br/>ʼ<br/><br/>��<br/><br/>��</td>
							<td align="center" rowspan="<%=m+1 %>">��<br/><br/>һ<br/><br/>ѧ<br/><br/>��<br/><br/>��<br/><br/>��</td>
							<td align="center" colspan="2">��Ŀ</td>
							<td align="center">�ɼ�</td>
							<td align="center" colspan="2">��Ŀ</td>
							<td align="center">�ɼ�</td>
							<td align="center" colspan="2">��Ŀ</td>
							<td align="center">�ɼ�</td>
						</tr>
						<%
							int i = 0;
							while (i<m*3){
						 %>
						
						<tr height="40px">
							<td align="center" colspan="2">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td align="center" colspan="2">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td  align="center"colspan="2">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
						</tr>
						<%
							}
						 %>
						<tr height="40px">
							<td align="center" rowspan="<%=n+1 %>">��<br/><br/>��<br/><br/>ѧ<br/><br/>��<br/><br/>��<br/><br/>��</td>
							<td align="center" colspan="2">��Ŀ</td>
							<td align="center">�ɼ�</td>
							<td align="center" colspan="2">��Ŀ</td>
							<td align="center">�ɼ�</td>
							<td align="center" colspan="2">��Ŀ</td>
							<td align="center">�ɼ�</td>
						</tr>
						
						<%
							i = 0;
							while (i<n*3){
						 %>
						
						<tr height="40px">
							<td colspan="2" align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td align="center" colspan="2">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td  align="center"colspan="2">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
						</tr>
						<%
							}
						 %>
						<tr height="40px">
							<td align="center" colspan="3">�༶��������</td>
							<td align="center">${stuMap.bjrs }</td>
							<td align="center" colspan="2">ƽ���ɼ�:</td>
							<td align="center">${stuMap.zycj }</td>
							<td align="center" colspan="2">�ܳɼ�������</td>
							<td align="center" colspan="2">${stuMap.zypm }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3">�ۺϲ�������:</td>
							<td align="center">${stuMap.zpm }</td>
							<td align="center" colspan="2">�ۺϲ�������:</td>
							<td align="center">${stuMap.fs }</td>
							<td align="center" colspan="2">������Ŀ����</td>
							<td align="center" colspan="2">${stuMap.bkkms }</td>
						</tr>
						<tr height="100px">
							<td align="center" colspan="2">������<br/>�����</td>
							<td colspan="9"></td>
						</tr>
					</table>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="8%" align="center">��<br/><br/>��<br/><br/>��<br/><br/>��</td>
							<td>
								<p style="height:160px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.fdyyj }
									<div style="margin-bottom: 0px" align="right">
										����ˣ�${rs.fdyshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��˽����${rs.fdysh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										�೤ǩ��:
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<logic:present property="xyshsj" name="rs">
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("fdyshsj").substring(0,4) %>
											&nbsp;&nbsp;��&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("fdyshsj").substring(5,7) %>
											&nbsp;&nbsp;��
											&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("fdyshsj").substring(8,10) %>
											&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:present>
										<logic:notPresent  property="xyshsj" name="rs">
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notPresent>
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">ѧ<br/><br/>Ժ<br/><br/>��<br/><br/>��</td>
							<td>
								<p style="height:140px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
									<div style="margin-bottom: 0px" align="right">
										����ˣ�${rs.xyshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��˽����${rs.xysh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										<logic:present property="xyshsj" name="rs">
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xyshsj").substring(0,4) %>
											&nbsp;&nbsp;��&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xyshsj").substring(5,7) %>
											&nbsp;&nbsp;��
											&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xyshsj").substring(8,10) %>
											&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:present>
										<logic:notPresent  property="xyshsj" name="rs">
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notPresent>
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">ѧ<br/>У<br/>��<br/>��<br/>��<br/>��<br/></td>
							<td>
								<p style="height:140px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
									<div style="margin-bottom: 0px" align="right">
										
										����ˣ�${rs.xxshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										��˽����${rs.xxsh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										<logic:present property="xxshsj" name="rs">
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xxshsj").substring(0,4) %>
											&nbsp;&nbsp;��&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xxshsj").substring(5,7) %>
											&nbsp;&nbsp;��
											&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xxshsj").substring(8,10) %>
											&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:present>
										<logic:notPresent  property="xxshsj" name="rs">
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notPresent>
										<br/>
										
										
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">��<br/><br/>ע</td>
							<td>
								<p style="height:140px">
									
								</p>
							</td>
						</tr>
					</table>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;�˱�������Ϻ���<bean:message key="lable.xb" />���桢װ����ÿһ��������ע�⣩������д��
					</div>
				</td>
			</tr>
		</table>
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
	</html:form>
</body>
</html:html>
