<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>�й�������չ����������ƻ������һ��</title>
</head>
<body> 
<div align="center"> 
 <p align=center ><b><span style='font-size:18.0pt;font-family:����;'>�й�������չ����������ƻ������һ��</span></b></p>
  <table class="tbstyle" width="85%"> 
    <tr align="center" height="30px"> 
      <td colspan=11>���� �� ��</td> 
    </tr> 
    <tr height="30px" align="center"> 
      <td align="center" width="15%">����</td> 
      <td width="15%">${rs.xm } </td> 
      <td colspan=2 width="10%" align="center">�Ա�</td> 
      <td colspan=2 width="15%" align="center">${rs.xb } </td> 
      <td colspan=2 width="10%" align="center">����</td> 
      <td colspan=2 width="15%" align="center">${rs.mzmc } </td> 
      <td rowspan=5 width="20%" align="center">��Ƭ</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>��������</td> 
      <td>${rs.csrq } </td> 
      <td colspan=2 align="center">����</td> 
      <td colspan=6 align="center">${rs.jg } </td> 
    </tr> 
    <tr height="30px"> 
      <td align="center">����ѧУ</td> 
      <td colspan=9 align="center"> 
      &nbsp;&nbsp;&nbsp;${xxsheng }&nbsp;&nbsp;&nbsp;ʡ&nbsp;&nbsp;&nbsp;${xxshi }&nbsp;&nbsp;&nbsp;�أ�����</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>���֤��</td> 
      <td colspan=4>${rs.sfzh } </td> 
      <td colspan=3> �꼶</td> 
      <td colspan=2>${rs.nj } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> ѧУ��ַ</td> 
      <td colspan=5>${rs.xxdz } </td> 
      <td colspan=2>�ʱ�</td> 
      <td colspan=2>${rs.xxyb }</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> ��ѧϰ�׶�</td> 
      <td colspan="10"> A<logic:equal value="��ѧ������ְ����ר��" name="rs" property="xxjd">��</logic:equal>&nbsp;&nbsp;&nbsp;��ѧ������ְ����ר��
      	B<logic:equal value="���У���ְ�ߡ���ר���м���" name="rs" property="xxjd">��</logic:equal>&nbsp;&nbsp;&nbsp;���У���ְ�ߡ���ר���м���
      	C<logic:equal value="����" name="rs" property="xxjd">��</logic:equal>&nbsp;&nbsp;&nbsp;����
      	D<logic:equal value="Сѧ" name="rs" property="xxjd">��</logic:equal>&nbsp;&nbsp;&nbsp;Сѧ&nbsp;&nbsp;&nbsp; </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> ����Ժϵרҵ����ѧ��</td> 
      <td colspan=10>${rs.xymc }${rs.zymc } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> ������ϵ�绰��������ϵ��ʽ</td> 
      <td colspan=10>${rs.lxdh } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td colspan=11> <b><span style='font-size:12.0pt;font-family:����;'>�� ͥ �� ��</span></b></td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>��ͥ���ڵ�</td> 
      <td colspan=10> 
               ${szsheng } &nbsp;&nbsp;ʡ/��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${szshi }&nbsp;&nbsp; ����/��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${szxian }&nbsp;&nbsp; ��/��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${rs.szzhen} &nbsp;&nbsp;�ֵ�/��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               ${rs.szcun } &nbsp;&nbsp;��</td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>��ͥ����</td> 
      <td colspan=6>A<logic:equal value="ũ��" name="rs" property="jthk">��</logic:equal>&nbsp;&nbsp;&nbsp;ũ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				 B<logic:equal value="����" name="rs" property="jthk">��</logic:equal>&nbsp;&nbsp;&nbsp;����</td> 
      <td colspan=2> <p align=center >�ʱ�</p></td> 
      <td colspan=2>${rs.jtyb } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td> ��ͥ������</td> 
      <td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jtnzsr }&nbsp;&nbsp;&nbsp;Ԫ</td> 
      <td colspan=4>�˾�������</td> 
      <td colspan=4>&nbsp;${rs.jtrjsr } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td>��ͥ���˿�</td> 
      <td colspan=2> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.jtrs }&nbsp;&nbsp;&nbsp;&nbsp;��</td> 
      <td colspan=4> <p align=center >������Դ</p></td> 
      <td colspan=4>${rs.srly } </td> 
    </tr> 
    <tr align="center" height="30px"> 
      <td rowspan=2>��ͥ��Ա</td> 
      <td colspan=10>
      	����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
       	������ϵ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  
       	����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

       	ְҵ�͵�λ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

       	��ϵ�绰
      
      </td> 
    </tr> 
    <tr height="120px" align="center"> 
      <td colspan=10 >
      	<logic:iterate name="cyList" id="cy">
					${cy.cyxm }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.mc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.cynl }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.cygzdw }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${cy.cydh }&nbsp;&nbsp;
				<br/>
			</logic:iterate>
      
      </td> 
    </tr> 
    <tr align="center" height="120px"> 
      <td width=108> <p align=center >��������</p></td> 
      <td width=529 colspan=10 valign="top">
      	<p align="left">
      		<br/>
      		&nbsp;&nbsp;&nbsp;${rs.sqsm }
      	</p>
      </td> 
    </tr> 
    <tr align="center" height="120px"> 
      <td width=108>��ͥƶ��<br/>ԭ��˵��</td> 
      <td width=529 colspan=10 align="left" valign="top">
		<p align="left" >
      		<br/>
      		&nbsp;&nbsp;&nbsp;${rs.pksm }
      	</p>
	  </td> 
    </tr> 
  </table> 
  <br/><br/><br/>
</div>

		<div align="center">
			<p align=center
				style='text-align: center; line-height: 150%; layout-grid-mode: char'>
				<span style='font-size: 18.0pt; line-height: 150%; font-family: ����'>�й�������չ����������ƻ����������</span>
			</p>
			<table class="tbstyle" width="85%">
				<tr height="30px">
					<td align="center">
						<b><span style='font-size: 12.0pt; font-family: ����;'>ʦ���Ƽ���ѧУ֤��</span></b>
					</td>
				</tr>
				<tr height="200px">
					<td align="left" valign="top">
						ʦ���Ƽ�������ѧУУ�����̵����Ρ������Ρ��ο���ʦ�ȸ����Ƽ�����
					</td>
				</tr>
				<tr height="30px">
					<td>
						�Ƽ���������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ݣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�绰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr height="200px">
					<td align="left" valign="top">
						����ѧУ�����
					</td>
				</tr>
				<tr height="30px">
					<td>
						��ϵ�绰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						ѧУ�쵼ǩ����&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr height="30px" align="center">
					<td>
						<b><span style='font-size: 12.0pt; font-family: ����;'>��ͥ�����������֤��</span></b>

					</td>
				</tr>
				<tr height="200px">
					<td align="left" valign="top">
						�����ֵ����´��������˼�ͥ��������״����˵����
					</td>
				</tr>
				<tr height="30px">
					<td>
						<p>
					��ϵ�绰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					��&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</td>
				</tr>
			</table>
			<p>
				<span style='font-family: ����;'>˵����������������Ա��λ�������ܷ���������������������ϲ����˻ء�</span>
			</p>
		</div>
		<br/><br/><br/>
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
</html>
