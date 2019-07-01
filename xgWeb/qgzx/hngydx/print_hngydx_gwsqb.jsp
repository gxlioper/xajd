<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	
	<body>
	<html:form action="/xsqgzx.do" method="post">		
		<h3 align="center">${xxmc}�ڹ���ѧ�����</h3>		
		<p>��
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		���ʱ�䣺${rs.sj}
<%--		${rs.year}��${rs.month}��${rs.day}��   --%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		˳���______ </p>
		<table cellspacing="0" cellpadding="0" align="center" class="tbstyle" >
  <tr>
    <td width="72" colspan="2"><p align="center">�ա��� </p></td>
    <td width="173" colspan="6"><p align="center">${rs.xm} </p></td>
    <td width="140" colspan="7"><p align="center">������ò </p></td>
    <td width="65" colspan="4"><p>${rs.zzmmmc} </p></td>
    <td width="48" colspan="4"><p>�� �� </p></td>
    <td width="50" colspan="3"><p>${rs.xb} </p></td>
    <td width="106" colspan="2" rowspan="7"><p align="center">�� </p>
        <p align="center">�� </p>
        <p align="center">Ƭ </p>
        <p align="center">�� </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">ѧ���� </p></td>
    <td width="173" colspan="6"><p align="center">${rs.xh} </p></td>
    <td width="140" colspan="7"><p align="center">רҵ�༶ </p></td>
    <td width="163" colspan="11"><p>${rs.zymc}${rs.bjmc} </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">����� </p></td>
    <td width="173" colspan="6"><p align="center">${rs.ssbh}</p></td>
    <td width="140" colspan="7"><p align="center">��ϵ�绰 </p></td>
    <td width="163" colspan="11"><p align="center">${rs.lxdh} </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">������ </p></td>
    <td width="477" colspan="24"><p align="center">${rs.jg} </p></td>
  </tr>
  <tr>
    <td width="72" colspan="2"><p align="center">�������� </p></td>
    <td width="477" colspan="24"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="151" colspan="5" rowspan="2"><p>�Ƿ�Ը��μ�У���ڹ���ѧ��λ������Ŀ�ϴ򹴣� </p></td>
    <td width="288" colspan="13"><p align="center">�ҽ̣��꼶���γ̣� </p></td>
    <td width="32" colspan="3" rowspan="2"><p align="center">���� </p></td>
    <td width="26" rowspan="2"><p align="center">���� </p></td>
    <td width="26" colspan="3" rowspan="2"><p align="center">���� </p></td>
    <td width="26" rowspan="2"><p align="center">���� </p></td>
  </tr>
  <tr>
    <td width="288" colspan="13"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="103" colspan="3"><p>�к��س� </p></td>
    <td width="552" colspan="25"><p align="center">${rs.yhtc} </p></td>
  </tr>
  <tr>
    <td width="103" colspan="3"><p>��λ��¼ </p></td>
    <td width="552" colspan="25"><p align="center">${rs.gwjl} </p></td>
  </tr>
  <tr>
    <td width="103" colspan="3"><p align="center">��ͥ������� </p>
        <p align="center">������Ϊ����������д�� </p></td>
    <td width="41"><p align="center">������ </p></td>
    <td width="43" colspan="2"><p align="center">һ������ </p></td>
    <td width="39"><p align="center">���� </p></td>
    <td width="41" colspan="3"><p align="center">�������� </p></td>
    <td width="51" colspan="2"><p align="center">ѧϰ�ɼ���� </p></td>
    <td width="45" colspan="2"><p align="center">���� </p></td>
    <td width="45" colspan="2"><p align="center">���� </p></td>
    <td width="45" colspan="4"><p align="center">�ϸ� </p></td>
    <td width="45" colspan="3"><p align="center">������ </p></td>
    <td width="156" colspan="5" valign="top"><p>�������Ŀ��ע��</p></td>
  </tr>
  <tr>
    <td width="187" colspan="6"><p>��У�ڼ�����������д�����ơ�ʱ�䡢�� </p></td>
    <td width="468" colspan="22"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="187" colspan="6"><p>��У�ڼ��������������д����ʱ�䡢������ѧ�����ơ�ʱ�䡢��</p></td>
    <td width="468" colspan="22"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="55" rowspan="6"><p align="center">�����ڹ���ѧ��ʱ�� </p></td>
    <td width="48" colspan="2"><p>&nbsp; </p></td>
    <td width="84" colspan="3"><p align="center">��һ </p></td>
    <td width="74" colspan="3"><p align="center">�ܶ� </p></td>
    <td width="82" colspan="4"><p align="center">���� </p></td>
    <td width="77" colspan="4"><p align="center">���� </p></td>
    <td width="79" colspan="7"><p align="center">���� </p></td>
    <td width="79" colspan="3"><p align="center">���� </p></td>
    <td width="76"><p align="center">���� </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">1-2 �� </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">3-4 �� </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">5-6 �� </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">7-8 �� </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="48" colspan="2"><p align="center">�� �� </p></td>
    <td width="84" colspan="3"><p>&nbsp; </p></td>
    <td width="74" colspan="3"><p>&nbsp; </p></td>
    <td width="82" colspan="4"><p>&nbsp; </p></td>
    <td width="77" colspan="4"><p>&nbsp; </p></td>
    <td width="79" colspan="7"><p>&nbsp; </p></td>
    <td width="79" colspan="3"><p>&nbsp; </p></td>
    <td width="76"><p>&nbsp; </p></td>
  </tr>
  <tr height="40px">
    <td width="55">
       <p>����Ա</p>
       <p>���</p></td>
    <td width="240" colspan="10">
        <p>&nbsp; </p>
        <p>&nbsp; </p>
        <p align="right">ǩ������ &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </p>
        <p align="right">�ꡡ�¡��� &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </p></td>
    <td width="48" colspan="2"><p align="center">�������� </p>
        <p align="center">��� </p></td>
    <td width="312" colspan="15"><p>&nbsp; </p>
        <p>���Ƽ���_________________ ����_________________________________�ڹ���ѧ��λ���� . </p>
        <p align="right">ǩ������ &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>
        <p align="right">�ꡡ�¡��� &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p></td>
  </tr>
</table>
<p><strong>1 </strong><strong>���ڡ�����������ע��Ը����»��ó�������Щ��Ŀ�ڹ���ѧ��� </strong><strong></strong></p>
<p><strong>2 </strong><strong>���ڡ��к��س�������ע���Լ����س��⣬����ע������Ӣ��ͼ�����ȼ�֤�飬�������ε����ְ�� </strong><strong></strong></p>
<p><strong>3 </strong><strong>���ڡ���λ��¼����ע����ǰ���ɹ��ĸ�λ��������д�� </strong><strong></strong></p>
<p><strong>4 </strong><strong>��������ѧ�ڳɼ�����ͥƶ��֤�� </strong><strong></strong></p>
<p><strong>5 </strong><strong>����˳��ű��˲�����д�⣬����������ϸ����д�� </strong><strong></strong></p>
<p><strong>6 </strong><strong>����Ҫ�ر�˵�������ڱ��渽������˵���� </strong><strong></strong></p>
		</html:form>
	</body>
</html>
