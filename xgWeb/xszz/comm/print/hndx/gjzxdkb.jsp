<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>�������ѧ��������������</title>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
</head>
<body> 
<div align="center"> 
  <div align="center" style="font-size:28px;font:'����' "><b>�������ѧ��������������</b></div>
			<table border="0" width="80%">
				<tr>
					<td>
						<p style="font-size: 15px;">
							ѧ&nbsp; Ժ��${rs.xymc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							ѧ �ţ�${rs.xh}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							���֤�ţ�${rs.sfzh }
						</p>
					</td>
				</tr>
			</table>

			<table class="printstyle" width="80%" > 
    <tr align="center"> 
      <td width="14%">��&nbsp;&nbsp;��</td> 
      <td width="12%">${rs.xm }</td> 
      <td colspan=1 width="14%">�� &nbsp;&nbsp;��</td> 
      <td colspan=3 width="20%">${rs.jg } </td> 
      <td width="13%">��&nbsp;&nbsp; ��</td> 
      <td width="10%">${rs.xb } </td> 
      <td width="10%">ѧ&nbsp;&nbsp;��</td> 
      <td width="7%">${rs.xz }</td> 
    </tr> 
    <tr align="center"> 
      <td>��&nbsp;&nbsp; ��</td> 
      <td>${rs.mzmc } </td> 
      <td colspan=1>ר&nbsp;&nbsp; ҵ</td> 
      <td colspan=3>${rs.zymc } </td> 
      <td> ��ѧ����</td> 
      <td colspan=3>${rs.rxrq } </td> 
    </tr> 
    <tr align="center"> 
      <td> QQ ��</td> 
      <td> ${rs.qqhm }</td> 
      <td colspan=1>E-MIAL</td> 
      <td colspan=3>${rs.lxdzxx } </td> 
      <td> ����״��</td> 
      <td colspan=3>
      	<logic:equal value="" name="rs" property="jkzk">
      	������ &nbsp;&nbsp;��һ��
      	</logic:equal>
      	
      	<logic:equal value="����" name="rs" property="jkzk">
      	������ &nbsp;&nbsp;��һ��
      	</logic:equal>
      	
      	<logic:equal value="һ��" name="rs" property="jkzk">
      	������ &nbsp;&nbsp;��һ��
      	</logic:equal>
      	
      </td> 
    </tr> 
    <tr align="center"> 
      <td> <p align=center>��ͥ��ַ</p></td> 
      <td colspan=5>${rs.jtdz }</td> 
      <td> <p align=center>��ͥ�绰</p></td> 
      <td colspan=3>${rs.jtdh }</td> 
    </tr> 
    <tr align="center"> 
      <td>��ͥ�ʱ�</td> 
      <td>${rs.jtyb }</td> 
      <td colspan="3">�Ƿ��������ѧ����</td> 
      <td>${rs.sfdk } </td> 
      <td>��������</td> 
      <td colspan=3>${rs.bkkm } </td> 
    </tr> 
    <tr height="80px"> 
      <td align="center">��������<br/>�����������</td> 
      <td colspan=9>
      <p>&nbsp;&nbsp;${rs.sqly }</p>
      	<p></p>
		<p align="center">
      	������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��
      	</p>
      </td>
    </tr> 
    <tr> 
      <td colspan=10> 
      	<p align="left">
      	        &nbsp;&nbsp;��1��&nbsp;&nbsp;&nbsp;��������������ʵ����С������ٳɷ֡�<br/>
      	        &nbsp;&nbsp;��2��&nbsp;&nbsp;&nbsp;�籾��������������ʵ���������е��ɴ˴�������Ӧ���Ρ�
        </p> 
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ǩ&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ������ǩ����ͬʱ�ֳ�ǩ����</p> 
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��</p></td> 
    </tr> 
    <tr align="center"> 
      <td> ѧ<br/>��<br/>֤<br/>��</td> 
      <td colspan=9>
      	<p>������У�ڼ�ѧ�ѱ�׼Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>Ԫ/ѧ�꣬ס�޷ѱ�׼Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>Ԫ/ѧ�꣬�ϼ�<u>&nbsp;&nbsp;&nbsp;&nbsp; </u>Ԫ/ѧ�ꡣ</p> 
        <p>�ش�֤��</p> 
        <p align=center><bean:message key="lable.xb" />���£�</p></td> 
    </tr> 
    <tr align="center"> 
      <td rowspan=3>��<br/>��<br/>��<br/>��</td> 
      <td colspan=2>��������ܶ�</td> 
      <td colspan=3>${rs.zje }&nbsp;&nbsp;Ԫ��Сд��</td> 
      <td colspan=1> <p align=center>�����������</p></td> 
      <td colspan=3> <p align=center>${rs.dknx }&nbsp;&nbsp; ��</p></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=9> <p align=center>�ÿ�ƻ�</p></td> 
    </tr> 
    <tr align="center"> 
      <td colspan=9> <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; ѧ��:&nbsp;&nbsp;&nbsp; �ÿ��____________Ԫ</p> 
        <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; ѧ��:&nbsp;&nbsp;&nbsp; �ÿ��____________Ԫ</p> 
        <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; ѧ��:&nbsp;&nbsp;&nbsp; �ÿ��____________Ԫ</p> 
        <p align=center>20&nbsp;&nbsp; -20&nbsp;&nbsp;&nbsp; ѧ��:&nbsp;&nbsp;&nbsp; �ÿ��____________Ԫ</p> 
        <p align=center>����ˣ�ǩ�֣���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</p></td> 
    </tr> 
    <tr height="120px"> 
      <td> <p align=center>��ע</p></td> 
      <td colspan=9 align="left"> 
      	<p>
      	&nbsp;&nbsp;${rs.bz }
      	</p>
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
</div>
</body>
</html>
