<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
	<html:form action="/qgzxLogic">
	<div align="center"><h2>ѧ���ڹ���ѧ���˱�</h2></div>
	<p align="right"> ${year}�� ${month}��</p>
	<div align="center">	
	<table class="tbstyle">     
      <tr>
        <td  height="42">����</td>
        <td>${rs.xm}</td>
        <td width="74">ϵ��</td>
        <td>${rs.xymc}</td>
        <td width="99">�༶</td>
        <td>${rs.bjmc}</td>
        <td >ѧ��</td>
        <td>${rs.xh}</td>
      </tr>
      <tr>
        <td height="35">��λ����</td>
        <td colspan="3">${rs.gwmc}</td>
        <td width="151" colspan="2">���˵ȼ�</td>
        <td colspan="2">${rs.khdj}</td>
      </tr>
       <tr>
        <td height="35">�ù�����</td>
        <td colspan="3">${rs.yrdwmc}</td>
        <td width="151" colspan="2">ָ����ʦǩ��</td>
        <td colspan="2"></td>
      </tr>  
      <tr>
        <td height="35">���ʱ�׼(Ԫ/Сʱ)</td>
        <td colspan="2">${rs.bcbz}</td>
        <td width="151" >����ʱ��(Сʱ)</td>
        <td colspan="2">${rs.gzsj}</td>
        <td width="151" >���ʽ��(Ԫ)</td>
        <td >${rs.gzje}</td>
      </tr>      
      <tr>
        <td height="33" colspan="8">
          <p>�����쵼ǩ�֣�
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		  
		  <br/>
		  <br/>
		  <br/>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  ��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
        </td>
      </tr>
      <tr>
      	<td colspan="8">      	
      	ע��1�����˵ȼ������㡢���á��ϸ񡢲��ϸ����2�����ʱ�׼�뿼�˵ȼ��ҹ���<br/>
      	   3�����ʽ����ʱ�׼������ʱ��4�������������5��ǰ��ѧ�����ڹ���ѧ�������ڲ���
      	
      	</td>
      </tr>
    </table>
    </div>
	</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
	</body>
</html:html>
