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
						${xxmc }ѧ����ʱ���Ѳ��������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
					  <tr>
					  	<td width="7%"></td>
					  	<td width="9%"></td>
					  	<td width="11%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="8%"></td>
					  </tr>
					  <tr height="40px">
					    <td><p align="center">�� �� </p></td>
					    <td><p align="center">${rs.xm } </p></td>
					    <td><p align="center">רҵ���༶ </p></td>
					    <td colspan="3"><p align="center">${rs.zymc }&nbsp;&nbsp;${rs.bjmc } </p></td>
					    <td colspan="2"><p align="center">����� </p></td>
					    <td colspan="3"><p align="center">${rs.ssbh } </p></td>
					    <td><p align="center">�绰 </p></td>
					    <td colspan="2"><p align="center">${rs.lxdh } </p></td>
					  </tr>
					  <tr height="40px">
					    <td width="60"rowspan="${cyNum+1 }">
					    	<p align="center">�� </p>
					        <p align="center">ͥ </p>
					        <p align="center">�� </p>
					        <p align="center">Ҫ </p>
					        <p align="center">�� </p>
					        <p align="center">Ա </p></td>
					    <td align="center">�� ��</td>
					    <td colspan="3" align="center">�뱾�˹�ϵ���绰 </td>
					    <td colspan="4" align="center">�� �� �� λ</td>
					    <td colspan="5" align="center">��������</td>
					  </tr>
					  <logic:iterate name="cyList" id="cy">
							<tr height="25px">
								<td align="center">
										${cy.cyxm }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="3">
										${cy.mc }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="4">
										${cy.cygzdw }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="5">
										${cy.cynsr }&nbsp;&nbsp;
								</td>
							</tr>
						</logic:iterate>
					  <tr height="40px">
					    <td colspan="2"><p align="center">��ͥ������ </p></td>
					    <td colspan="3"><p align="center">${rs.jtnzsr } </p></td>
					    <td colspan="4"><p align="center">��У�ڼ��¾������ </p></td>
					    <td colspan="5"><p align="center">${rs.yjshf } </p></td>
					  </tr>
					  <tr height="40px">
					    <td colspan="2"><p align="center">ѧ�ѱ�׼ </p></td>
					    <td colspan="3"><p align="center">${rs.xfbz } </p></td>
					    <td colspan="4"><p align="center">ĿǰǷ�Ѷ�� </p></td>
					    <td colspan="5"><p align="center">${rs.qfje } </p></td>
					  </tr>
					  <tr height="40px">
					    <td colspan="2"><p align="center">��ͥ��ϸ��ַ���ʱࣩ </p></td>
					    <td colspan="12"><p align="center">${rs.jtdz } </p></td>
					  </tr>
					  <logic:notEmpty name="cjList">
					  <tr>
					    <td rowspan="${cjNum+3 }"><p align="center">ѧ </p>
					        <p align="center">ϰ </p>
					        <p align="center">�� </p>
					        <p align="center">�� </p></td>
					    <td><p align="center">�� Ŀ </p></td>
					    <td colspan="2"><p align="center">�� �� </p></td>
					    <td colspan="3"><p align="center">�� Ŀ </p></td>
					    <td colspan="3"><p align="center">�� �� </p></td>
					    <td colspan="3"><p align="center">�� Ŀ </p></td>
					    <td><p align="center">�� �� </p></td>
					  </tr>
					<%
						List<HashMap<String, String>> cjList = (List<HashMap<String, String>>)request.getAttribute("cjList");
						
						int i=0;
						while (null!=cjList && i<cjList.size()){
					 %>
					 	<tr>
					 		<td align="center"><%if(i<cjList.size()) out.print(cjList.get(i).get("kcmc")); %></td>
					 		<td align="center" colspan="2"><%if(i<cjList.size()) out.print(cjList.get(i).get("cj")); %></td>
					 		<%
								i++;
							 %>
					 		<td align="center" colspan="3"><%if(i<cjList.size()) out.print(cjList.get(i).get("kcmc")); %></td>
					 		<td align="center" colspan="3"><%if(i<cjList.size()) out.print(cjList.get(i).get("cj")); %></td>
					 		<%
								i++;
							%>
					 		<td align="center" colspan="3"><%if(i<cjList.size()) out.print(cjList.get(i).get("kcmc")); %></td>
					 		<td align="center"><%if(i<cjList.size()) out.print(cjList.get(i).get("cj")); %></td>
					 	</tr>
					 <%
					 	i++;
					 	}
					  %>
					  <tr>
					 		<td align="center">&nbsp;</td>
					 		<td align="center" colspan="2"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center"></td>
					 	</tr>
					 	<tr>
					 		<td align="center">&nbsp;</td>
					 		<td align="center" colspan="2"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center"></td>
					 	</tr>
					  <tr>
					  </logic:notEmpty>
					    <td width="60"  align="center">
					    	�� <br/>
					        �� <br/>
					        �� <br/>
					        �� <br/>
					    </td>
					    <td width="640" colspan="13">
					    	<p style="height:80px">
					    		&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					    	</p>
					    </td>
					  </tr>
					  <tr>
					    <td width="60"><p align="center">��ͥ </p>
					        <p align="center">��� </p></td>
					    <td width="640" colspan="13"><p align="center">&nbsp; </p>
					        <p>�¶���
					        	<logic:equal value="��" name="rs" property="sfgc">
					        		��
					        	</logic:equal>
					        	
					        ���������׼�ͥ��
					        	<logic:equal value="��" name="rs" property="sfdq">
					        		��
					        	</logic:equal>
					        ����������ͱ�������
					        	<logic:equal value="��" name="rs" property="sfdb">
					        		��
					        	</logic:equal>
					       	 ������ͥ���������˵����
					       	 <logic:equal value="��" name="rs" property="lszn">
					        		����ʿ��Ů
					        	</logic:equal>
					       	 �� �������� </p>
					        <p align="center">&nbsp; </p></td>
					  </tr>
					  <tr>
					    <td colspan="14" align="center">
					    	<strong>���˱�֤��������������ʵ��Ч�� </strong>
					    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<br/>
					        ������ǩ����
					    </td>
					  </tr>
					  <tr>
					    <td width="60" align="center">
					        ѧ <br/>
					        Ժ <br/>
					        �� <br/>
					        ��<br/>
					    </td>
					    <td colspan="13" valign="top">
					    	<p style="height:80px">��������У���������Ҫ���֣� 
					    		<br/>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					    	</p>
					        <p align="center">���������������������������������������������ǡ��£������ꡡ���¡����� </p>
					  </tr>
					  <tr>
					    <td width="60" align="center">
					        ѧ <br/>
					        У <br/>
					        �� <br/>
					        �� <br/>
					     </td>
					    <td colspan="13">
					    	<p style="height:80px">
					    		&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					    	 </p>
					        <p align="center">���������������������������������������������ǡ��£������ꡡ���¡����� </p>
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
