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
				<td align="center" align="center">
					<h2>
						��������ְҵ����ѧԺ������ѧ���������
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center" align="center">
					<table  width="100%" class="tbstyle">
					  <tr>
					  	<td align="center" width="5%"></td>
					  	<td align="center" width="10%"></td>
					  	<td align="center" width="6%"></td>
					  	<td align="center" width="12%"></td>
					  	<td align="center" width="6%"></td>
					  	<td align="center" width="7%"></td>
					  	<td align="center" width="12%"></td>
					  	<td align="center" width="6%"></td>
					  	<td align="center" width="7%"></td>
					  	<td align="center" width="12%"></td>
					  	<td align="center" width="5%"></td>
					  	<td align="center" width="12%"></td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="3">�� �� </td>
					    <td align="center">${rs.xm }</td>
					    <td align="center">�Ա� </td>
					    <td align="center">${rs.xb } </td>
					    <td align="center">ѧ Ժ </td>
					    <td align="center" colspan="2">${rs.xymc } </td>
					    <td align="center">רҵ�꼶 </td>
					    <td align="center" colspan="2"> ${rs.zymc }&nbsp;&nbsp;&nbsp;${rs.nj }</td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="3">ѧ �� </td>
					    <td align="center">${rs.xh }</td>
					    <td align="center">ѧ�� </td>
					    <td align="center">${rs.xz } </td>
					    <td align="center">��ҵʱ�� </td>
					    <td align="center" colspan="2">${rs.bysj }</td>
					    <td align="center">���ҵ绰 </td>
					    <td align="center" colspan="2">${rs.qsdh } </td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="3">
					    	��ͥ��ϸ <br/>
					        ��ַ���ʱ�  
					    </td>
					    <td align="center" colspan="6">${rs.jtdz }&nbsp;&nbsp;${rs.jtyb }  </td>
					    <td align="center">
					    		��ͥ<br/>�绰  
					     </td>
					    <td align="center" colspan="2">  
					    	${rs.jtdh }
					    </td>
					  </tr>
					  <tr height="40px">
					    <td colspan="6"> 
					    	�Ͷ��ڼ�ѧϰ����������ۣ�<br/>
					         <logic:equal value="��" property="xxqkpj" name="rs">
					    		�š� &nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp; һ�� &nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp; 
					    	</logic:equal>
					    	<logic:equal value="��" property="xxqkpj" name="rs">
					    		��&nbsp;&nbsp;&nbsp;&nbsp;���� &nbsp;&nbsp; һ�� &nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
					    	</logic:equal>
					    	<logic:equal value="һ��" property="xxqkpj" name="rs">
					    		��&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp; һ�� ��&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
					    	</logic:equal>
					    	<logic:equal value="��" property="xxqkpj" name="rs">
					    		��&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp; һ�� &nbsp;&nbsp;&nbsp;&nbsp; �� ��&nbsp;&nbsp;
					    	</logic:equal>
					    	<logic:equal value="" property="xxqkpj" name="rs">
					    		��&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp; һ�� &nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;
					    	</logic:equal>
					        
					    </td>
					    <td align="center">�Ͷ��ڼ��ۼƲ�������޿�Ŀ�� </td>
					    <td align="center" colspan="2">${rs.bjgkm }</td>
					    <td align="center" colspan="2">����Υ�ʹ��� </td>
					    <td align="center">${rs.sfwj } </td>
					  </tr>
					  
					  <tr height="40px">
					    <td colspan="6">��ѧ��������˳ɼ���${rs.dycj } </td>
					    <td colspan="2">���޲������ü�¼ </td>
					    <td align="center" colspan="4">
					    	<logic:equal value="��" property="sfblxy" name="rs">
					    	�С�  �ޡ� 
					    	</logic:equal>
					    	<logic:equal value="��" property="sfblxy" name="rs">
					    	�С� �ޡ�  
					    	</logic:equal>
					    	<logic:equal value="" property="sfblxy" name="rs">
					    	�С� �ޡ� 
					    	</logic:equal>
					    </td>
					  </tr>
					  <tr height="40px">
					    <td align="center" colspan="2">��У�ڼ�Ԥ����������ܶ� </td>
					    <td align="center" colspan="3">${rs.zje } </td>
					    <td align="center" colspan="2">���������֤���� </td>
					    <td align="center" colspan="5">${rs.sfzh } </td>
					  </tr>
					  <tr>
					    <td align="center">
					    	��<br/><br/>��<br/><br/>��<br/><br/>��
					    </td>
					    <td colspan="11" >
					    	<p style="height:120px">
					    		&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqsm }
					    	</p>
					    </td>
					  </tr>
					  <tr>
					    <td colspan="5">
					    	<p style="height:140px">
					    		<bean:message key="lable.xb" />��������${rs.xyshyj }
					    	</p>
					    	<div align="center">
					    		����
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	</div>
					    </td>
					    <td colspan="3" >
					    	<p style="height:140px">
					    		ѧ���������${rs.xxshyj }
					    	</p>
					    	<div align="center">
					    		����
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	</div>
					    </td>
					    <td colspan="4" >
					    	<p style="height:140px">
					    		�������������
					    	</p>
					    	<div align="center">
					    		����
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    		 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	</div>
					    </td>
					  </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>������ϣ�</strong> 1)����ѧϰ�ɼ��ܱ�<bean:message key="lable.xb" />���£�������¼ȡ֪ͨ����ӡ���� 2)����<br/>
					������ѧԺƶ������ͥ����״�������������ֵ����ϸ���֤������ӡ��������3)�ҳ�ǩ<br/>
					��ͬ�������ڹ黹��Ϣ�ĸ�ҳ����ִ���� 4)��ͥ��֤����ϵ���Ĺ̶��绰��<br/>
					�ڻ��ѵ�ԭ����5)�ҳ����֤��ӡ�����������֤��ѧ��֤��ӡ�� (<strong>ÿλѧ��������</strong><br/>
					<strong>������ذ�����˳����A4ֽװ��</strong>��)
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
