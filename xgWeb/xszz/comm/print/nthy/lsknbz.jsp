<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<html:html>
<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
<body>
	<table width="95%" border="0" id="theTable" align="center">
		<tr>
			<td align="center">
				<h2>
					${xxmc }ѧ�����Ѳ��������
				</h2>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table width="100%" class="tbstyle">
					<tr>
						<td width="7%"></td>
						<td width="8%"></td>
						<td width="6%"></td>
						<td width="8"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="9%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="5%"></td>
						<td width="11%"></td>
						<td width="5%"></td>
						<td width="11%"></td>
					</tr>
				  <tr height="40px">
				    <td colspan="2" align="center">�� ��</td>
				    <td colspan="2" align="center">${rs.xm }</td>
				    <td colspan="2" align="center">�Ա� </td>
				    <td align="center">${rs.xb }</td>
				    <td colspan="3" align="center">ѧ Ժ </td>
				    <td colspan="2" align="center">${rs.xymc }</td>
				    <td align="center">רҵ�꼶 </td>
				    <td align="center" colspan="2">${rs.zymc } &nbsp;&nbsp;${rs.nj }</td>
				  </tr>
				  <tr height="40px"> 
				    <td align="center" colspan="2"><p align="center">ѧ �� </p></td>
				    <td align="center" colspan="2"><p align="center">${rs.xh } </p></td>
				    <td align="center" colspan="2"><p align="center">ѧ�� </p></td>
				    <td align="center" ><p>${rs.xz } </p></td>
				    <td align="center" colspan="3"><p align="center">��ҵʱ�� </p></td>
				    <td align="center" colspan="2"><p>${rs.bysj } </p></td>
				    <td align="center" ><p align="center">��ϵ�绰 </p></td>
				    <td align="center" colspan="2"><p>${rs.lxdh } </p></td>
				  </tr>
				  <tr height="40px">
				    <td colspan="3"><p align="center">��ͥ��ϸ��ַ </p></td>
				    <td colspan="12"><p align="center">${rs.jtdz } </p></td>
				  </tr>
				  <tr height="40px">
				    <td colspan="3"><p align="center">˼��Ʒ����ʵ���� </p></td>
				    <td colspan="2"><p align="center">${rs.sxpdbx } </p></td>
				    <td colspan="7"><p align="center">ѧϰ������� </p></td>
				    <td colspan="3"><p align="center">${rs.xxztbx } </p></td>
				  </tr>
				  <tr height="40px">
				    <td colspan="5"><p align="center">�ܵ�����Υ�ʹ��� </p></td>
				    <td colspan="10"> ${rs.wjcfqk }</td>
				  </tr>
				  <tr height="40px">
				    <td align="center" colspan="3">��ѧ��<br/>�з����</td>
				    <td align="center" colspan="2">${rs.bxnsfdk } </td>
				    <td align="center" colspan="4">��ѧ���з�<br/>ѧ�Ѽ���</td>
				    <td align="center" colspan="2">${rs.bxnsfjm }</td>
				    <td align="center" colspan="3">��ѧ���з�<br/>�μ��ڹ���ѧ</td>
				    <td align="center">${rs.bxnsfqg }</td>
				  </tr>
				  <tr height="40px">
				    <td align="center" colspan="3">��ѧ���Ƿ��<br/>�ý�ѧ�� </td>
				    <td align="center" colspan="2">${rs.bxnsfjxj } </td>
				    <td align="center" colspan="4">��ѧ���Ƿ���<br/>������Ѳ���</td>
				    <td align="center" colspan="2">${rs.bxnsfbz } </td>
				    <td align="center" colspan="3">��ѧ���Ƿ���<br/>���������</td>
				    <td align="center">${rs.bxnsfszz }</td>
				  </tr>
				  <tr>
				    <td align="center">
				    	<br/><br/>��<br/><br/>��<br/><br/>��<br/><br/>��<br/><br/>
				    </td>
				    <td width="598" colspan="14">
				    	<p style="height:180px">
				        &nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
				       	</p>
				    </td>
				  </tr>
				  <tr>
				    <td width="283" colspan="8"><p>�༶��� </p>
				        <p>&nbsp; </p>
				        <p>&nbsp; </p>
				        <p>&nbsp; </p>
				        <p>&nbsp; </p>
				        <p>���鲹��______________Ԫ </p>
				        <p align="center">������ǩ�� </p>
				        <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 �� </p></td>
				    <td width="358" colspan="7"><p><bean:message key="lable.xb" />��� </p>
				        <p>&nbsp; </p>
				        <p align="left">&nbsp; </p>
				        <p align="left">ͬ�ⲹ��______________Ԫ </p>
				        <p align="left">&nbsp; </p>
				        <p align="right">
				        	����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        </p>
				        <p align="right">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        				 �� </p></td>
				  </tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				ע�����ṩ���֤������ϸ����
			</td>
		</tr>
	</table>
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
