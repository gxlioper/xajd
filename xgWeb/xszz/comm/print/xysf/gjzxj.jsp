<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>����ʦ��ѧԺ������ѧ�������</title>
	<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div> 
  <p align=center style='font-size:22.0pt;font-family:����'><strong>����ʦ��ѧԺ������ѧ�������</strong></p> 
  <p align=center style='font-size:15.0pt;font-family:����;font-weight:normal;'><strong>��${rs.xn } ѧ�꣩</strong></p> 
  <table class="tbstyle" border="0" align="center" style="width: 90%"> 
    <tr> 
      <td width=7% rowspan=4> <p align=center>��<br/>��<br/>��<br/>��<br/></p></td> 
      <td width=15%> <p align=center>����</p></td> 
      <td width=15%>${rs.xm }</td> 
      <td width=8%> <p align=center>�Ա�</p></td> 
      <td width=15% colspan=2>${rs.xb } </td> 
      <td width=18%> <p align=center>��������</p></td> 
      <td width=22% colspan=2>${rs.csrq } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>����</p></td> 
      <td>${rs.mzmc } </td> 
      <td> <p align=center>����</p> 
        <p align=center>��ò</p></td> 
      <td colspan=2>${rs.zzmmmc }</td> 
      <td > <p align=center>��ѧʱ��</p></td> 
      <td colspan=2>${rs.rxrq }</td> 
    </tr> 
    <tr> 
      <td> <p align=center>���֤����</p></td> 
      <td colspan=4>${rs.sfzh }</td> 
      <td> <p align=center>��ϵ�绰</p></td> 
      <td colspan=2>${rs.lxdh } </td> 
    </tr> 
    <tr> 
      <td colspan=5> <p align=center>��ͥ���������϶�����</p></td> 
      <td colspan=3>${rs.knjb } </td> 
    </tr> 
    <tr> 
      <td rowspan=3> <p align=center>��<br/>ͥ<br/>��<br/>��<br/>��<br/>��<br/></p></td> 
      <td> <p align=center>��ͥ����</p></td> 
      <td colspan=5> 
      	<p align=center>
									A��<logic:equal value="����" property="jthk" name="rs">
										��
									</logic:equal>����
									&nbsp;&nbsp;
									B��<logic:equal value="ũ��" property="jthk" name="rs">
										��
									</logic:equal>
									ũ��
		</p></td> 
      <td width="6%"> <p align=center>��ͥ<br/>�˿�</p> 
      </td> 
      <td>${rs.jtrs } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>��ͥ��������</p></td> 
      <td>${rs.jtyzsr } </td> 
      <td colspan=3> <p align=center>�˾�������</p></td> 
      <td>${rs.jtrjysr }</td> 
      <td> <p align=center>����<br/>��Դ</p> 
      </td> 
      <td>${rs.srly } </td> 
    </tr> 
    <tr> 
      <td> <p align=center>��ͥסַ</p></td> 
      <td colspan=5>${rs.jtdz }</td> 
      <td> <p align=center>����<br/>����</p> 
      <td>${rs.jtyb } </td> 
    </tr> 
    <tr> 
      <td colspan=9> <p>�������ɣ�</p> 
      	<p>&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }</p>
        <p align=right >������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;</p></td> 
    </tr> 
    <tr> 
      <td colspan=5> <p><bean:message key="lable.xb" />��������</p> 
        <p><span style='font-size:18.0pt;font-family:�����п�'>ͬ�ⷢ��&nbsp;&nbsp;&nbsp;�ȹ�����ѧ��</span></p> 
        <p align=right>�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��</p></td> 
      <td colspan=4> <p>ѧУ������:</p> 
      	<p>&nbsp;&nbsp;&nbsp;</p>
        <p align=right>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��</p></td> 
    </tr> 
  </table> 
</div> 
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
