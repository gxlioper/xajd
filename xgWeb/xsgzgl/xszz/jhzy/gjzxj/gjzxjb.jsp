<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
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
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						��${rs.xn}ѧ�꣩��ͨ��У������ѧ������������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="left">
					<div style="font-size:15px;">
						<b>
						ѧУ����ְҵ����ѧԺ
						<bean:message key="lable.xb" />��${rs.xymc }
						רҵ��${rs.zymc }
						�༶��${rs.bjmc }
						</b>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					
					<table width="100%" class="printstyle">
						<tr>
							<td width="4.5%"></td>
							<td width="13.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
						</tr>
						<tr height="30px">
							<td align="center" rowspan="4">����<br/>���</td>
							<td align="center">����</td>
							<td align="center" colspan="4">${rs.xm }</td>
							<td align="center" colspan="2">�Ա�</td>
							<td align="center" colspan="4">${rs.xb }</td>
							<td align="center" colspan="3">��������</td>
							<td align="center" colspan="5">${rs.csrq }</td>
							
						</tr>
						<tr height="30px">
							<td align="center">ѧ��</td>
							<td align="center" colspan="4">${rs.xh }</td>
							<td align="center" colspan="2">����</td>
							<td align="center" colspan="4">${rs.mzmc }</td>
							<td align="center" colspan="3">��ѧʱ��</td>
							<td align="center" colspan="5">${rs.rxrq }</td>
						</tr>
						<tr height="30px">
							<td align="center">������ò</td>
							<td align="center" colspan="4">${rs.zzmmmc }</td>
							<td align="center" colspan="4">��ϵ�绰</td>
							<td align="center" colspan="10">${rs.sjhm }</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤��
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh1" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh2" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh3" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh4" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh5" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh6" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh7" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh8" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh9" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh10" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh11" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh12" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh13" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh14" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh15" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh16" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh17" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh18" />
								</div>
							</td>
						</tr>
						<tr height="30px">
							<td align="center" rowspan="4">
								��<br/>ͥ<br/>��<br/>��<br/>��<br/>��
							</td>
							<td align="center">��ͥ����</td>
							<td align="center" colspan="10">
								<logic:notEmpty name="rs" property="jthk">
									<logic:equal name="rs" property="jthk" value="����">
										<u>A������</U> B��ũ��
									</logic:equal>
									<logic:equal name="rs" property="jthk" value="ũ��">
										A������ <u>B��ũ��</U> 
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="rs" property="jthk">
										A������  B��ũ��
								</logic:empty>
							</td>
							<td align="center"  colspan="3">������Դ</td>
							<td align="center"  colspan="5">${rs.srly }</td>
						</tr>
						<tr height="30px">
							<td align="center">��ͥ��<br/>������</td>
							<td align="center" colspan="10">${rs.jtnzsr}</td>
							<td align="center" colspan="3">��ͥ��<br/>������</td>
							<td align="center" colspan="5">${rs.jtrks }</td>
						</tr>
						<tr height="30px">
							<td align="center">��ͥסַ</td>
							<td align="center" colspan="10">${rs.jtdz }</td>
							<td align="center" colspan="3">��������</td>
							<td align="center" colspan="5">${rs.jtyb }</td>
						</tr>
						<tr height="30px">
							<td align="center">�϶����</td>
							<td align="center" colspan="18">
								<logic:notEmpty name="rs" property="knstjdc">
									<logic:equal name="rs" property="knstjdc" value="�ر�����">
										 <u>A����ͥ�����ر�����</U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B����ͥ����һ������ 
									</logic:equal>
									<logic:equal name="rs" property="knstjdc" value="һ������">
										A����ͥ�����ر�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>B����ͥ����һ������ </U> 
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="rs" property="knstjdc">
										A����ͥ�����ر�����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B����ͥ����һ������
								</logic:empty>
							
							</td>
						</tr>
						
					<tr style="height:30px">
							<td align="center" rowspan="5">
									��<br>
									ͥ<br>
									��<br>
									Ա<br>
									��<br>
									��
							</td>
							<td align="center">
									����
							</td>
							<td align="center" colspan="2">
									����
							</td>
							<td align="center" colspan="4">
									�뱾�˹�ϵ
							</td>
							<td align="center" colspan="12">
									������ѧϰ����λ
							</td>
						</tr>
				
				<%
				
				ArrayList<HashMap<String,String>>cyList=(ArrayList<HashMap<String,String>>)request.getAttribute("cyList");
				int len=0;
				if(cyList!=null && cyList.size()>0){
					len=cyList.size();
				}
				
				for(int i=0;i<len;i++){
					HashMap<String,String>cyMap=cyList.get(i);
				%>
				<tr style="height:40px">
					 <td align=center>
						<div align="center">
							<%=cyMap.get("cyxm")==null ? "" : cyMap.get("cyxm")%>
						</div>
					</td>
					<td colspan="2" align=center>
						<div align="center">
							<%=cyMap.get("cynl")==null ? "" : cyMap.get("cynl")%>
						</div>
					</td>
					<td  colspan="4" align=center>
						<div align="center">
							<%=cyMap.get("cygx")==null ? "" : cyMap.get("cygx")%>
						</div>
					</td>
					<td colspan="12" align=center>
						<div align="center">
							<%=cyMap.get("cygzxxdw")==null ? "" : cyMap.get("cygzxxdw")%>
						</div>
					</td>
				</tr>
				<%
				}
				%> 
				 
				 
				<%
				
				for(int i=0;i<4-len;i++){
				%>
				<tr style="height:40px">
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan="2"  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan="4"   align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  colspan="12" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
				
				</tr>
				<%
				}
				%>
				
						
						<tr style="height:150px">
							<td align="center">
									����<br/>����<br/>��100<br/>�֣�<br/>
							</td>
							<td colspan="19">
								<p style="height:80px">
									${rs.sqly }
								</p>
								<div align="right" style="padding-right: 30px">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
						<tr style="height:120px">
							<td align="center"  >
									Ժ<br/>��ϵ��<br/>��<br/>��
							</td>
							<td colspan="19">
								<p style="height:70px">
								<bean:message key="lable.xb" />����������ע����ʾ�������${rs.xyshyj }
								</p>
								<div align="right" style="padding-right: 30px">
									ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
						<tr style="height:120px">
							<td align="center">
									ѧ<br/>У<br/>��<br/>��
							</td>
							<td colspan="19">
								<p style="height:70px">
								ѧ�������������${rs.xxshyj }
								</p>
								<div align="right" style="padding-right: 30px">
									�����£�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
