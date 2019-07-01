<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<link id="csss1" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
<script type="text/javascript" src="js/function.js"></script>		
<script type="text/javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script language="javascript" src="js/calendar.js"></script>
<script language="javascript" src="js/calendar-zh.js"></script>
<script language="javascript" src="js/calendar-setup.js"></script>
  <body>
    <html:form action="/pjpy_apply_bg">
       <input type="hidden" name="redirect" id="redirect" value="true">
	   <input type="hidden" name="variable" id="variable" value="none">
	   <!--input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-bjmc" /-->
       <input type="hidden" name="url"  id="url"          value="/pjpy/pjpy_bgprise_apply.jsp">
       <input type="hidden" name="jxjlx" id="jxjlx"       value="baosteel">
       <input type="hidden" name="jxjdm" id="jxjdm"       value="<bean:write name="jxjdm"/>">
       <logic:present name="dboperation">
         <logic:equal value="true" name="dboperation">
         <script type="text/javascript">
         	alert("����ɹ���");
         </script>
         </logic:equal>
         <logic:equal value="false" name="dboperation">
         <script type="text/javascript">
         	alert("����ʧ�ܣ�");
         </script>
         </logic:equal>
       </logic:present>
       <div class="title">
       	 <div class="title_img" id="title_m">
       	 	��ǰ����λ�ã��������� �� ��ѧ������ �� ��д�����
       	 </div> 	
       </div>
       <table width="99%"  class="tbstyle" align="center">
          <thead>
            <tr><td colspan="10" align="center" style="font-size:14px">�� �� �� �� �� �� �� �� ѧ �� �� �� �� ��</td></tr>
          </thead>
          <tr> 
          <logic:equal value="teacher" name="userOnLine" scope="session">    
             <td  width="10%">ѧ��</td><td colspan="2"><html:text name="rs" property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
									<button onclick="showTopWin('stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button></td>
          </logic:equal>
          <logic:notEqual value="teacher" name="userOnLine" scope="session">
          	<td>ѧ��</td><td colspan="2"><html:text name="rs" property="xh" readonly="true"/>
									</td>
          </logic:notEqual>
		  <td colspan="7">&nbsp;</td>
          </tr>
		  <tr>
		    <td height="25">����</td>
		    <td colspan="2"><html:text name="rs" property="xm" style="width:98%" readonly="true"/></td>
		    <td width="14%"><div align="center">�Ա�</div></td>
		    <td width="12%"><html:text name="rs" property="xb" style="width:98%" readonly="true"/></td>
		    <td width="13%">��������</td>
		    <td colspan="2" ><html:text name="rs" property="csny" style="width:98%" readonly="true"/></td>
		    <td rowspan="4" colspan="2">&nbsp;</td>
		  </tr>
		  <tr>
		    <td  height="25">�μӵ���</td>
		    <td colspan="2"><html:text name="rs" property="zzmm" style="width:98%"/></td>
		    <td ><div align="center">����</div></td>
		    <td><html:text name="rs" property="mzmc" style="width:98%" /></td>
		    <td>��ѧ����</td>
		    <td colspan="2"><html:text name="rs" property="rxny" style="width:98%" readonly="true"/></td>
		  </tr>
		  <tr>
		    <td  height="25">Ժ��ϵ</td>
		    <td colspan="2" ><html:text name="rs" property="xymc" style="width:98%" readonly="true"/></td>
		    <td ><div align="center">רҵ</div></td>
		    <td colspan="4"><html:text name="rs" property="zymc" style="width:98%" readonly="true"/></td>
		  </tr>
		  <tr>
		    <td  height="25">E-mail</td>
		    <td colspan="2" ><html:text name="rs" property="email" style="width:98%"/></td>
		    <td ><div align="center">��ϵ�绰</div></td>
		    <td colspan="4"><html:text name="rs" property="lxdh" style="width:98%"/></td>
		  </tr>
		  <tr>
		    <td rowspan="6">�μ����ʵ���ͳе���Ṥ�����</td>
		    <td height="25" colspan="2">��ֹ����</td>
		    <td colspan="7"> ���ʵ������ /��Ṥ����ְ����</td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2"><html:text name="rs" property="qzrq1"  onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq1','y-mm-dd');"/></td>
		    <td colspan="7"><html:text name="rs" property="hdmc1" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2"><html:text name="rs" property="qzrq2"  onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq2','y-mm-dd');"/></td>
		    <td colspan="7"><html:text name="rs" property="hdmc2" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="qzrq3"  onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq3','y-mm-dd');"/></td>
		    <td colspan="7"><html:text name="rs" property="hdmc3" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="qzrq4" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq4','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="hdmc4" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="qzrq5" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('qzrq5','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="hdmc5" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td rowspan="6" >
		      <p align="center">�ڶ�</p>
		      <p align="center">ѧ��</p>
		      <p align="center">����</p>
		      <p align="center">��</p>
			  <p align="center">���</p>
			</td>
		    <td height="25" colspan="2">����</td>
		    <td colspan="7"> �������� /�佱��λ</td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq1" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq1','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc1" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq2" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq2','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc2" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq3" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq3','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc3" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq4" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq4','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc4" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="2" ><html:text name="rs" property="hjrq5" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('hjrq5','y-mm-dd');" /></td>
		    <td colspan="7"><html:text name="rs" property="jxmc5" style="width:88%"/></td>
		  </tr>
		  <tr>
		    <td height="25" colspan="3" >����ר��������ѧ��ѧϰ�ɼ�</td>
		    <td colspan="7">�꼶����<html:text name="rs" property="njrs" />�� ������ �꼶����<html:text name="rs" property="njpm" />�� </td>
		  </tr>
		  <tr>
		    <td rowspan="2" >�о����Ѿ���������ͳ�����</td>
		    <td width="10%" height="25"><div align="center">���ڿ���</div></td>
		    <td width="10%"><div align="center">���ʿ���</div></td>
		    <td width="14%" align="center">����ѧ������</td>
		    <td width="12%">����ѧ������</td>
		    <td width="13%">���б�SCI��¼</td>
		    <td width="8%">���б�EI��¼ </td>
		    <td width="9%">���ר��</td>
		    <td width="8%">�Ƽ�����</td>
		    <td>�� ��</td>
	      </tr>
		  <tr>
		    <td height="46"><html:text name="rs" property="gnkw"  style="width:45px"/>ƪ</td>
		    <td height="46"><html:text name="rs" property="gjkw" style="width:45px"/>ƪ</td>
		    <td height="46"><html:text name="rs" property="gnxshy"  style="width:55px"/>ƪ</td>
			<td><html:text name="rs" property="gjxshy"  style="width:30px"/>ƪ</td>
			<td><html:text name="rs" property="sci"  style="width:30px"/>ƪ</td>
			<td><html:text name="rs" property="ei"  style="width:30px"/>ƪ</td>
			<td><html:text name="rs" property="zl"  style="width:30px"/>��</td>
			<td><html:text name="rs" property="kjjl"  style="width:30px"/>��</td>
			<td><html:text name="rs" property="qt"  style="width:20px"/>ƪ���</td>
		  </tr>
		  <tr>
		    <td height="120" colspan="10" valign="top"><p>��������Ϊ��Ҫ���� (��ǰ����)��ѧ���ɹ� <strong>��ȡ����Ҫ��3ƪ��� </strong></p>
		    ��д˳��������Ŀ������������š����ڡ�ҳ�룻�Ƽ��ɹ�������ר�����Ƽ��������<br>
		    <html:textarea name="rs" property="xscg" rows="6" style="width:98%"></html:textarea></td>
		    
		  </tr>
		  <tr>
		    <td  height="50">��������Ҫ�¼�</td>
		    <td colspan="9"><html:textarea name="rs" property="zysj" rows='6' style="width:98%"/></td>
		  </tr>
		  
		  <logic:equal value="xy" name="userType" scope="session">
		  <tr>
		    <td  height="50"><bean:message key="lable.xsgzyxpzxy" />��ϵ����С�����</td>
		    <td colspan="9"><html:textarea name="rs" property="xyshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
		  <logic:equal value="admin" name="userType" scope="session">
		  <tr>
		    <td  height="50">ѧУ��λ�������</td>
		    <td colspan="9"><html:textarea name="rs" property="xxshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  <tr>
		    <td  height="50">���ֽ�������������ίԱ�����</td>
		    <td colspan="9"><html:textarea name="rs" property="baosteelshyj" rows='6' style="width:98%"/></td>
		  </tr>
		  </logic:equal>
		</table>
    </html:form>
    <div class="buttontool">
       <button class="button2" onclick="shgcPriseApplication()">�ύ����</button>
       <button class="button2" onclick="shgcPriseAppicationPrint()">�����ӡ</button>
    </div>
  </body>
</html:html>
