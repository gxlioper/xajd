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
	<!-- ��ӡ�ؼ�begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
  </head>
  <body>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'����' "><bean:write name='rs' property="title"/></div>
<br/>
<div>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xymc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<bean:write name='rs' property="xn"/>&nbsp;ѧ��<bean:write name='rs' property="xq"/></div>
<table width="100%" class="printstyle">
  <tr>
    <th height="30" colspan="2" scope="col">�༶</th>
    <th height="30" colspan="2" width="25%" scope="col">&nbsp;<bean:write name='rs' property="bjmc"/></th>
    <th height="30" width="17%" scope="col">����</th>
    <th height="30" colspan="2" width="10%" scope="col">&nbsp;<bean:write name='rs' property="xm"/></th>
    <th height="30" width="15%" scope="col">ְ��</th>
    <th height="30" colspan="2" scope="col">&nbsp;<bean:write name='rs' property="drzw"/></th>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">�ɼ�����</th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="cjmc"/></td>
    <th><div align="center">�ۺ���������</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="zhpfmc"/></td>
    <th><div align="center">��ѧ��ȼ�</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="jxjmc"/></td>
  </tr>
  <tr>
    <th width="6%" height="100" rowspan="11" scope="row">
	<p>��</p>
    <p>ѧ</p>
    <p>��</p>
    <p>��</p>
    <p>ѧ</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
</th>
    <th height="30" colspan="3" scope="row">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</th>
    <th height="30" colspan="1" width="12%" scope="row">�� ��</th>
    <th height="30" colspan="4" width="35%" scope="row">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</th>
    <th height="30"  scope="row" width="12%">�� ��</th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc1"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj1"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc2"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj2"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc3"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj3"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc4"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj4"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc5"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj5"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc6"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj6"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc7"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj7"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc8"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj8"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc9"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj9"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc10"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj10"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc11"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj11"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc12"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj12"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc13"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj13"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc14"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj14"/></th>
  </tr>
  <tr>
   <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc15"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj15"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc16"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj16"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc17"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj17"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc18"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj18"/></th>
  </tr>
  <tr>
    <th height="30" colspan="3" scope="row">&nbsp;<bean:write name='rs' property="kcmc19"/></th>
    <th colspan="1" scope="row">&nbsp;<bean:write name='rs' property="kccj19"/></th>
    <th colspan="4" scope="row">&nbsp;<bean:write name='rs' property="kcmc20"/></th>
    <th scope="row">&nbsp;<bean:write name='rs' property="kccj20"/></th>
  </tr>
  <tr>
    <th scope="row"><p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row"><p style="height:100px " align="left"><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="jfqk"/></p><div align="right"><bean:write name="rs" property="xyshyear"/> �� <bean:write name="rs" property="xyshmon"/> �� <bean:write name="rs" property="xyshdate"/> �� </div></th>
    
  </tr>
  <tr>
    <th scope="row"><p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row">
    
    <p style="height:80px " align="left"><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="fdyyj"/></p><p align="right">ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="fdyqm"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right"><bean:write name="rs" property="xyshyear"/> �� <bean:write name="rs" property="xyshmon"/> �� <bean:write name="rs" property="xyshdate"/> ��</p></th>
  </tr>
  <tr>
    <th scope="row"><p>(ϵ)</p>
    <p>Ժ</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="4" scope="row">
    <p style="height:100px " align="left"><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xyshyj"/></p><p align="right">ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyqm"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p align="right"><bean:write name="rs" property="xyshyear"/> �� <bean:write name="rs" property="xyshmon"/> �� <bean:write name="rs" property="xyshdate"/> �� </p></th>
    <th scope="row" colspan=""><p>ѧ</p>
    <p>Ժ</p>
    <p>��</p>
    <p>��</p>
    </th>
    <th colspan="4" scope="row">
    
    <p style="height:100px " align="left"><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xxshyj"/></p><p align="right"><br/><br/><bean:write name="rs" property="xxshyear"/> �� <bean:write name="rs" property="xxshmon"/> �� <bean:write name="rs" property="xxshdate"/> �� </p>
    </th>
  </tr>
</table>
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
<!-- ע���˱�һʽ���ݣ�ϵ��Ժ����Ժѧ������һ�� -->
    </html:form>
  </body>
</html:html>
