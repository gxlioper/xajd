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
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jtjjknsPrint.jsp' starting page</title>
    	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<style media="print">
			.noprint{
				display:none
			}
			.print{
				display:block
			}
  </style>
<center>
<body  class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center'><b><span style='font-size:18.0pt;font-family:����;"Times New Roman";"Times New Roman"'>����<bean:message key="lable.xsgzyxpzxy" />ѧ���ڶ����û����</span></b></p> 
  <table class="tbstyle" width=85% height=700 > 
    <tr> 
      <td width=15% align="center" valign=middel class="Normal"> 
      	    �����
      </td> 
      <td width=240 align="center" colspan=2 valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hdmc}
      		</logic:notEmpty>
      </td> 
      <td width=90  align="center" colspan=2 valign=middel class="Normal">
     		 �ٰ첿��
      </td> 
      <td width=198  align="center" colspan=2 valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.jbbm} 
      		</logic:notEmpty>	
      </td> 
    </tr> 
    <tr> 
      <td width=105  align="center" valign=middel class="Normal">
      		��ٰ�ʱ��
      </td> 
      <td width=240  align="center" colspan=2 valign=middel class="Normal">
      		&nbsp; 
      		<logic:notEmpty name="rs">
      		${rs.jbsj }
      		</logic:notEmpty>
      		</td> 
      <td width=90  align="center" colspan=2 valign=middel class="Normal">
      		�ص�
      </td> 
      <td width=198  align="center" colspan=2 valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hddd }
      		</logic:notEmpty>
       </td> 
    </tr> 
    <tr> 
      <td width=105 align="center"  valign=middle class="Normal">
      		�������
	  </td> 
      <td width=168 align="center"  valign=middle class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		 ${rs.hdfzr }
      		</logic:notEmpty> 
      		 </td> 
      <td width=72  align="center" valign=middle class="Normal">
      		�����
      </td> 
      <td width=71  align="center" valign=middel class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		 ${rs.ssh }
      		</logic:notEmpty>		 
      </td> 
      <td width=73  align="center" colspan=2 valign=middel> 
          ��ϵ��ʽ
      </td> 
      <td width=144  align="center" valign=top class="Normal">
      	   &nbsp;
      	   <logic:notEmpty name="rs">
      	   ${rs.lxfs } 
      		</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=105  align="center" valign=middle class="Normal">
      	 ָ����ʦ
      </td> 
      <td width=168  align="center" valign=top class="Normal">
      	<logic:notEmpty name="rs">	
      		&nbsp; ${rs.zdls }
      	</logic:notEmpty>
      </td> 
      <td width=72  align="center" valign=middle class="Normal">�μӶ���<br>������</td> 
      <td width=288 align="center"  colspan=4 valign=top class="Normal">
      	<logic:notEmpty name="rs">
      		&nbsp; ${rs.cjdxrs }
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=105 align="center"  valign=middle class="Normal">����α�</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
     	 <logic:notEmpty name="rs">
      		&nbsp;${rs.yqjb } 
      		</logic:notEmpty>		
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105 height="15%" align="center" valign=middle class="Normal">���Ŀ�ĺ�<br>����</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hdmdyy } 
      		</logic:notEmpty>
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105  align="center" valign=middle class="Normal">������Է�<br>��</td> 
      <td width=528 align="center"  colspan=6 valign=top class="Normal">
      		&nbsp;
      	<logic:notEmpty name="rs">
      		${rs.hdkxfx }
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105  align="center" valign=middle class="Normal">�ʵʩʱ��<br>��</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
      		&nbsp;
      		<logic:notEmpty name="rs">
      		${rs.hdsssjb }
      		</logic:notEmpty>	
      	</td> 
    </tr> 
    <tr> 
      <td width=105  align="center" valign=middle class="Normal">���Ҫ����</td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal"> 
      	<logic:empty name="rs">
      		�ߣߣߣߣߣߣߣ�Ԫ
      	</logic:empty>
      	<logic:notEmpty name="rs">
      		<U>${rs.hdxyjf }</U>Ԫ
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr height="15%" > 
      <td width=105  align="center" valign=middle class="Normal"> <p><span style='  font-family:����'>�����Ԥ��<br>�嵥</span></p></td> 
      <td width=528  align="center" colspan=6 valign=top class="Normal">
      		&nbsp; 
      	<logic:notEmpty name="rs">
      		${rs.hdjfys }
      	</logic:notEmpty>	
      </td> 
    </tr> 
  </table> 
  <p><span style='font-family:����'>��ע<span
lang=EN-US>:</span>���еڶ����û�ľٰ�<span lang=EN-US>,</span>����ݽ�����߻���<span
lang=EN-US>,</span>�����߻����߲�����׼</span></p> 
</div> 
</body>
</center>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</html>