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
						��������ְҵ����ѧԺѧ��ѧ�Ѽ��������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
					  <tr>
					  	<td width="6%"></td>
					  	<td width="10%"></td>
					  	<td width="1%"></td>
					  	<td width="7%"></td>
					  	<td width="5%"></td>
					  	<td width="5%"></td>
					  	<td width="11%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="10%"></td>
					  </tr>
					  <tr height="50px">
					    <td align="center" colspan="2">�� �� </td>
					    <td align="center" colspan="2">${rs.xm } </td>
					    <td align="center" colspan="2">�Ա� </td>
					    <td align="center">${rs.xb } </td>
					    <td align="center" colspan="2">ѧ Ժ </td>
					    <td align="center" colspan="2">${rs.xymc } </td>
					    <td align="center" colspan="2">רҵ�꼶 </td>
					    <td align="center">${rs.zymc }&nbsp;&nbsp;${rs.nj } </td>
					  </tr>
					  <tr height="50px">
					    <td align="center" colspan="2">ѧ �� </td>
					    <td align="center" colspan="2">${rs.xh } </td>
					    <td align="center" colspan="2">ѧ�� </td>
					    <td align="center">${rs.xz } </td>
					    <td align="center" colspan="2">��ҵʱ�� </td>
					    <td align="center" colspan="2">${rs.bysj } </td>
					    <td align="center" colspan="2">��ϵ�绰 </td>
					    <td align="center">${rs.lxdh } </td>
					  </tr>
					  <tr height="60px">
					    <td colspan="5">
					    	ѧϰ����������ۣ� <br/>
					    	<logic:equal value="��" property="xxqkpj" name="rs">
					    		�š� �� �� <br/>
					        	һ�� �� �� �� 
					    	</logic:equal>
					    	<logic:equal value="��" property="xxqkpj" name="rs">
					    		�š� �� �� <br/>
					        	һ�� �� �� �� 
					    	</logic:equal>
					    	<logic:equal value="һ��" property="xxqkpj" name="rs">
					    		�š� �� �� <br/>
					        	һ�� �� �� �� 
					    	</logic:equal>
					    	<logic:equal value="��" property="xxqkpj" name="rs">
					    		�š� �� �� <br/>
					        	һ�� �� �� �� 
					    	</logic:equal>
					    	<logic:equal value="" property="xxqkpj" name="rs">
					    		�š� �� �� <br/>
					        	һ�� �� �� �� 
					    	</logic:equal>
					        
					    </td>
					    <td colspan="4" align="center">
					    	�Ͷ��ڼ��ۼƲ�����<br/>���޿����� 
					    </td>
					    <td colspan="5">${rs.bjgkm } </td>
					  </tr>
					  <tr height="50px">
					    <td width="104" colspan="3" align="center">˼��Ʒ����ʵ���� </td>
					    <td width="95" colspan="2" align="center">${rs.sxpdbx } </td>
					    <td width="120" colspan="3" align="center">
					    	����<br/> 
					        ��� 
					     </td>
					    <td colspan="6" align="center">
					    	&nbsp;&nbsp;&nbsp;&nbsp;${rs.jcqk }
					    </td>
					  </tr>
					  <tr height="50px">
					    <td colspan="8" align="center">
					    	��У�ڼ��ܹ����־��ò�����������ʱ����<br/>
					    	������������ѧ����������ȣ� 
					    </td>
					    <td width="300" colspan="6" align="center">
					    	&nbsp;&nbsp;&nbsp;&nbsp;${rs.sghzbz }
					    </td>
					  </tr>
					  <tr height="50px">
					    <td align="center" colspan="2">
					    	��ѧ��ɷ���� 
					    </td>
					    <td width="120" colspan="4" align="center">
					    	<logic:equal value="��" property="sfjf" name="rs">
					    		ѧ���ѽɡ� <br/>
					        	ѧ��δ�ɡ� 
					    	</logic:equal>
					    	<logic:equal value="��" property="sfjf" name="rs">
					    		ѧ���ѽɡ� <br/>
					        	ѧ��δ�ɡ� 
					    	</logic:equal>
					    	<logic:equal value="" property="sfjf" name="rs">
					    		ѧ���ѽɡ� <br/>
					        	ѧ��δ�ɡ� 
					    	</logic:equal>
					    	
					    </td>
					    <td colspan="2"align="center">
					    	��ѧ���Ƿ��������
					    </td>
					    <td align="center" colspan="2">
					    	<logic:equal value="��" property="sfdk" name="rs">
					    		�� �� <br/>
					        	�� ��
					    	</logic:equal>
					    	<logic:equal value="��" property="sfdk" name="rs">
					    		�� �� <br/>
					        	�� ��
					    	</logic:equal>
					    	<logic:equal value="" property="sfdk" name="rs">
					    		�� �� <br/>
					        	�� ��
					    	</logic:equal>
					    	 
					    </td>
					    <td colspan="2" align="center">
					    	����ѧ�Ѽ������
					    </td>
					    <td colspan="2" align="center">
					    	<logic:equal value="ȫ��" property="sqlb" name="rs">
					    		ȫ�� �� <br/>
					        	������ ��
					    	</logic:equal>
					    	<logic:equal value="������" property="sqlb" name="rs">
					    		ȫ�� �� <br/>
					        	������ ��
					    	</logic:equal>
					    	<logic:equal value="" property="sqlb" name="rs">
					    		ȫ�� �� <br/>
					        	������ ��
					    	</logic:equal>
					    </td>
					  </tr>
					  <tr>
					    <td align="center">&nbsp; 
					     	��<br/><br/>��<br/><br/>��<br/><br/>��
					     </td>
					    <td colspan="13" valign="top">
					    	��ͥ�������Ѽ�Ҫ��� &nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					     </td>
					  </tr>
					  <tr>
					    <td colspan="7" valign="top">�༶��� 
					        <p style="height:120px">
					        	&nbsp;&nbsp;&nbsp;&nbsp;${rs.bjshyj }
					        </p>
					        <div align="right">
					        ������ǩ�� 
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        <br/>
					        �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					        </div>
					    </td>
					    <td colspan="7" valign="top"><bean:message key="lable.xb" />��� 
					    	 <p style="height:120px">
					    	 	&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					    	 </p>
					   		<div align="right">
					        ����
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        	 &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					        <br/>
					        �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
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
