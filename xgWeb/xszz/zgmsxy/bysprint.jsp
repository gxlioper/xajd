<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <!-- ��ӡ�ؼ�begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
  <body>
    <html:form action="/dxjxjsp">
     
<br/>
<div>

<table width="98%" border="0">

	<tr>
		<th align="left"><p>____________ ������ѧ��λ�� </p></th>
	</tr>
	<tr>
		<th height="22" align="left"> ������Уѧϰ�ڼ����й�������������֧������˹�����ѧ�����ҵ��У </th>
	</tr>
	<tr>
		<th align="left"> ǰ���д������ ______ Ԫ�����ձ���������ǩ������ѧ����ȷ���飬���� ____ </th>
	</tr>
	<tr>
		<th align="left"> �� ____ �¿�ʼ������Ϣ���� ____ �� ____ ��ȫ���������ҵĻ����˺� </th>
	</tr>
	<tr>
		<th align="left"> �� ���ش�֣�س�ŵ�����˱�֤�ϸ����д����ͬ�� </th>
	</tr>
	<tr>
		<th align="left"> ������ȷ�����Լ����������д��ҵ�����������Ϣ�ɼ���Ը����δ������� </th>
	</tr>
	<tr>
		<th align="left"> ֮ǰ��<bean:message key="lable.xsgzyxpzxy" />�����֤�ˣ���<bean:message key="lable.xsgzyxpzxy" />����Ա�� ____________ ������ϵ������֤��ʱ�� </th>
	</tr>
	<tr>
		<th align="left"> �Լ��䶯�����ϵ��ʽ����������ϵ�ˡ� </th>
	</tr>
	<tr>
		<th align="left"><p>����ŵ��һʽ ���� �������ҵ������ѧ��λ��ѧ��������һ�ݡ� </p></th>
	</tr>
	<tr>
		<th align="left"><p>��ѧ��λ��ϵ�˼��绰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��ŵ�ˣ���ǩ�֣� </p>
	    <p align="right">�� �� �� </p></th>
	</tr>
</table>
 <div align="center" style="font-size:18px;font:'����' "><b>������ѧ�����ҵ��������Ϣ�ɼ���</b></div>
<table width="98%" class="printstyle">
  <tr>
    <th width="15%"><div align="center">����</div></th>
    <th width="20%"><div align="center">${rs.xm }</div></th>
    <th width="15%"><div align="center">�Ա�</div></th>
    <th width="15%"><div align="center">${rs.xb }</div></th>
    <th width="15%"><div align="center">��ҵ����</div></th>
    <th width="20%"><div align="center">${rs.byny }</div></th>
  </tr>
  <tr>
    <th><div align="center">���֤��</div></th>
    <th colspan="2"><div align="center">${rs.sfzh }</div>      <div align="center"></div></th>
    <th><div align="center">ѧ��</div></th>
    <th colspan="2"><div align="center">${rs.xh }</div></th>
  </tr>
  <tr>
    <th><div align="center">��ͥ��ַ</div></th>
    <th colspan="3"><div align="center">${rs.jtzz }</div>      <div align="center"></div>      
      <div align="center"></div></th>
    <th><div align="center">��������</div></th>
    <th><div align="center">${rs.yzbm }</div></th>
  </tr>
  <tr>
    <th><div align="center">��������</div></th>
    <th colspan="2"><div align="center">${rs.fqxm }</div></th>
    <th><div align="center">������ϵ�绰</div>      <div align="center"></div></th>
    <th colspan="2"><div align="center">${rs.fqdh }</div></th>
  </tr>
  <tr>
    <th><div align="center">���׹�����λ</div></th>
    <th colspan="5"><div align="center">${rs.fqgzdw }</div></th>
  </tr>
  <tr>
    <th><div align="center">ĸ������</div></th>
    <th colspan="2"><div align="center">${rs.mqxm }</div></th>
    <th><div align="center">ĸ����ϵ�绰</div></th>
    <th colspan="2"><div align="center">${rs.mqdh }</div></th>
  </tr>
  <tr>
    <th><div align="center">ĸ�׹�����λ</div></th>
    <th colspan="5"><div align="center">${rs.mqgzdw }</div></th>
  </tr>
  <tr>
    <th><div align="center">�״α�ҵȥ��</div></th>
    <th colspan="5"><div align="center">${rs.brjyqxhdw }</div></th>
  </tr>
  <tr>
    <th><div align="center">��ǰ������λ����ַ</div></th>
    <th colspan="5"><div align="center">${rs.dqgzdwjdz }</div></th>
  </tr>
  <tr>
    <th><div align="center">��ǰ������λ�ʱ�</div></th>
    <th colspan="2"><div align="center">${rs.dqgzdwyb }</div>      <div align="center"></div></th>
    <th> <div align="center">��ǰ������λ�绰 </div></th>
    <th colspan="2"><div align="center">${rs.dqgzdwdh }</div>      <div align="center"></div></th>
  </tr>
  <tr>
    <th> <div align="center">Ŀǰ����Ч��ϵ��ʽ </div></th>
    <th colspan="5"><p align="center">��ͥ�绰��${rs.jtgddh } �ֻ���${rs.lxdh } </p>
      <div align="center">���估QQ �ţ�${rs.brdzyxjdzlxfs } </div></th>
  </tr>
  <tr>
    <th> <div align="center">��ϵ��ʽ������ </div></th>
    <th colspan="5"><div align="center">${rs.lxfsbgqk }</div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div>      <div align="center"></div></th>
  </tr>
</table>
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
<!-- ע���˱�һʽ���ݣ�ϵ��Ժ����Ժѧ������һ�� -->
    </div>
    </html:form>
  </body>
</html:html>
