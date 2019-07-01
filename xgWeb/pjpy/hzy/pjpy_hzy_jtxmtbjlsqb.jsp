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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script type="text/javascript" src="js/pjpy/pjpy_hzy.js"></script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  
  <body>
    <html:form action="/jtxmtbjlsq">
    <div align=center style="font-size: 18px;font-family: '����'">����ְҵ����<bean:message key="lable.xsgzyxpzxy" /><br>������Ŀ�ر��������</div>
         ���벿�ţ�<bean:write name="bmmc" />
    <html:hidden property="queryStr" styleId="queryStr"/>
	<table width="89%" class="tbstyle">
	  <tr>
	    <td height="36" colspan="2" scope="col" align=center><font color="red">*</font>�����</td>
	    <td width="26%" scope="col"><html:text property="hdmc" name="rs1" /></td>
	    <td colspan="2" scope="col" align=center>�ʱ��</td>
	    <td width="35%" scope="col"><html:text property="hdsj" name="rs1" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('hdsj','y-mm-dd');"  /></td>
	  </tr>
	  <tr>
	    <td colspan="2" scope="row" align=center>������</td>
	    <td><html:text property="hjmc" name="rs1" /></td>
	    <td colspan="2"><div align="center">���뽱�����</div></td>
	    <td><html:text property="sqje" name="rs1"/></td>
	  </tr>
	  <tr>
	    <th colspan="6" scope="row">���影�μ���Ա</th>
	  </tr>
	  <tr>
	    <td colspan="2" scope="row"><div align="center">�༶</div></td>
	    <td><div align="center">����</div></td>
	    <td colspan="2"><div align="center">�༶</div></td>
	    <td><div align="center">����</div></td>
	  </tr>
	  <logic:iterate id="stu" name="rs" length="10">
<%--	  <tr>--%>
<%--	    <td colspan="2" scope="row"><select id="01" name="bj" onchange="getStus(this)"></select></td>--%>
<%--	    <td><html:text property="xh" name="stu" style="width:150px" /><span style="width:18px;border:0px solid red;" name="span"><select id="02" name="stuName" style="margin-left:-150px;width:168px"></select></td>--%>
<%--	    <td colspan="2"><select id="03" name="bj" onchange="getStus(this)"></select></td>--%>
<%--	    <td><html:text property="xh" style="width:150px" /><span style="width:18px;border:0px solid red;" name="span"><select id="04" name="stuName" style="margin-left:-150px;width:168px"></select></td>--%>
<%--	  </tr>--%>
	  <tr>
	    <td colspan="2" scope="row"><input type="text" id="bjmc" name="bjmc" value="<bean:write name="stu" property="bjmc1" />"/></td>
	    <td><input type="text" id="xm" name="xm" value="<bean:write name="stu" property="xm1" />"/></td>
	    <td colspan="2"><input type="text" id="bjmc" name="bjmc" value="<bean:write name="stu" property="bjmc2" />"/></td>
	    <td><input type="text" id="xm" name="xm" value="<bean:write name="stu" property="xm2" />"/></td>
	  </tr>
	  </logic:iterate>
	  <tr>
	    <td width="7%" height="122" scope="row" align=center>
	    <p>ϵ</p>
	    <p>��Ժ��</p>
	    <p>��</p>
	    <p>��</p></td>
	    <td colspan="5">
		    <html:textarea property="xyyj" rows="7" cols="85" ></html:textarea>
		    <div style="float:right" id="date" name="date">
	    <br>ǩ������
	    <br>year�� month�� day��
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </div>
	    </td>
	  </tr>
<%--	  <logic:equal value="admin" name="userType" scope="session">--%>
	  <tr>
	    <td scope="row" align=center>
	    <p>ѧ</p>
	    <p>��</p>
	    <p>��</p>
	    <p>��</p>
	    <p>��</p></td>
	    <td colspan="3" scope="row"><html:textarea property="xscyj" rows="7" cols="50" disabled="true"></html:textarea>
	    <div style="float:right" id="date" name="date">
	    <br>ǩ������
	    <br>year�� month�� day��
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </div></td>
	    
	    <td width="11%" scope="row" align=center>
	      <p>ѧ</p>
	      <p>Ժ</p>
	      <p>��</p>
	    <p>��</p></td>
	    <td scope="row"><html:textarea property="xxyj" rows="7" cols="20" disabled="true"></html:textarea>
	    <div style="float:right"  id="date" name="date">
	    <br>ǩ������
	    <br>year�� month�� day��
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </div>
	    </td>
	  </tr>
<%--	  </logic:equal>--%>
	</table>
	<p>ע��1.���񽱲���	</p>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;2.�øֱ���д���ּ��������Ժ���£��쵼ǩ�ַ�Ϊ��Ч�� </p>
    </html:form>
    <div class="buttontool" align="center">
    <button class="button2" align="center" onclick="if(!checkAllInput('hdmc')) {return false;}submitFromAction('/xgxt/saveJtxmtbjlsq.do?method=saveJtxmtbjlsq')">
    	��&nbsp;&nbsp;&nbsp;&nbsp;��
    </button>
<%--    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--    <button class="button2" align="center" onclick="">--%>
<%--    	��&nbsp;&nbsp;&nbsp;&nbsp;ӡ--%>
<%--    </button>--%>
    </div>
  </body>
  <logic:present name="saveFlag">
  <logic:equal value="true" name="saveFlag">
  <script>
  	alert("����ɹ���");
  </script>
  </logic:equal>
  <logic:equal value="false" name="saveFlag">
  <script>
  	alert("����ʧ�ܣ�");
  </script>
  </logic:equal>
  </logic:present>
  <script type="text/javascript">
    initDate();
  </script>
</html:html>
