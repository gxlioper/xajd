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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="/xgxt/skin1/style/main1.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>	
	<body>		
		<html:form action="/qgzxZgdzdx" method="post">
		<p align="center"><strong>	���ݴ�ѧѧ���ڹ���ѧ��λ����� </strong></p>
		<p align="center"><strong>ѧ Ժ�� </strong>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<strong>�� �� ʱ ��</strong></p>
	<div align="center">
	  <table class="tbstyle">
	    <tr>
	      <td width="72"><p align="center"><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong></p></td>
	      <td width="72"><p><strong>&nbsp; </strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	      <td width="48"><p align="center"><strong>�Ա� </strong><strong></strong></p></td>
	      <td width="48"><p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="48"><p align="center"><strong>�༶ </strong><strong></strong></p></td>
	      <td width="84"><p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="96"><p align="center"><strong>ѧ�� </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>�� </strong><strong></strong><strong>ͥ </strong><strong></strong><strong>�� </strong><strong></strong><strong>ַ </strong><strong></strong></p></td>
	      <td width="96" colspan="2"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="48"><p align="center"><strong>���� </strong><strong></strong></p></td>
	      <td width="84"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="96"><p align="center"><strong>��ϵ�绰 </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>��ѧ�ڲ������Ŀ </strong><strong></strong></p></td>
	      <td width="144" colspan="3"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="180" colspan="2"><p align="center"><strong>�����ܹ����� </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>�Ƿ�Ƿ��ѧ�ѡ���� </strong></p></td>
	      <td width="144" colspan="3"><p align="center"><strong>&nbsp; </strong></p>
	          <p align="center"><strong>&nbsp; </strong></p></td>
	      <td width="180" colspan="2"><p align="center"><strong>�Ƿ��ý�ѧ�𡢽�� </strong><strong></strong></p></td>
	      <td width="156"><p align="center"><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong><strong>ͥ </strong><strong></strong></p>
	          <p><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong><strong>״ </strong><strong></strong><strong>�� </strong><strong></strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	      <td width="480" colspan="6" valign="top"><p><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>ѧ </strong><strong></strong><strong>ϰ </strong><strong></strong><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong></p>
	          <p align="center">����ѧ�ڸ��Ƴɼ��� </p></td>
	      <td width="480" colspan="6"><p align="center"><strong>&nbsp; </strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>�к��س� </strong><strong></strong></p></td>
	      <td width="480" colspan="6"><p align="center">1. �鷨 2. �滭 3. ���Դ��� 4. ��ҳ���� 5. д�� 6. ���� __________ </p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>�ɴ��º��ֹ��� </strong><strong></strong></p></td>
	      <td width="480" colspan="6" valign="top"><p><strong>&nbsp; </strong></p>
	          <p><strong>&nbsp; </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2" valign="top"><p><strong>&nbsp; </strong></p>
	          <p><strong>ѧ </strong><strong></strong><strong>Ժ </strong><strong></strong><strong>�� </strong><strong></strong><strong>�� </strong>�����ѧ��ѧϰ�����������ˣ� <strong></strong></p></td>
	      <td width="480" colspan="6" valign="bottom"><p align="right"><strong>&nbsp; </strong></p>
	          <p align="right"><strong>�쵼ǩ���� </strong></p>
	          <p align="right"><strong>��<bean:message key="lable.xsgzyxpzxy" />���£� </strong></p>
	          <p align="right"><strong>�� </strong><strong>�� </strong><strong>�� </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>ѧ </strong><strong></strong><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong><strong>�� </strong><strong></strong></p></td>
	      <td width="480" colspan="6" valign="bottom"><p align="right"><strong>&nbsp; </strong></p>
	          <p align="right"><strong>&nbsp; </strong></p>
	          <p align="right"><strong>�� </strong><strong>�� </strong><strong>�� </strong></p></td>
	    </tr>
	    <tr>
	      <td width="144" colspan="2"><p align="center"><strong>��ע </strong><strong></strong></p></td>
	      <td width="480" colspan="6" valign="top"><p><strong>&nbsp; </strong></p></td>
	    </tr>
	  </table>
	</div>
<p align="center"><strong>&nbsp; </strong></p>
<p align="left">
	<strong>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	����ÿ�ܿɹ���ʱ�� 
	</strong></p>
<div align="center">
  <table class="tbstyle">
    <tr>
      <td width="120"><p align="center">&nbsp; </p></td>
      <td width="96"><p align="center">����һ </p></td>
      <td width="98"><p align="center">���ڶ� </p></td>
      <td width="108"><p align="center">������ </p></td>
      <td width="106"><p align="center">������ </p></td>
      <td width="98"><p align="center">������ </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">��һ��� </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">�ڶ���� </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">������� </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
    <tr>
      <td width="120" valign="top"><p align="center">���Ĵ�� </p></td>
      <td width="96" valign="top"><p>&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
      <td width="108" valign="top"><p align="center">&nbsp; </p></td>
      <td width="106" valign="top"><p align="center">&nbsp; </p></td>
      <td width="98" valign="top"><p align="center">&nbsp; </p></td>
    </tr>
  </table>
</div>

<p align="center">ע�� ֻ������Ŀɹ���ʱ��δ��򡰡̡�������ʱ���������������ע��˵���� </p>		
<div class='noPrin' align="center">
	<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
</div>
</html:form>
</body>
</html>
