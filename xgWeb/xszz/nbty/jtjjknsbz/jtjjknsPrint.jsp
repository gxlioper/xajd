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
  <body bgcolor="#FFFFFF" border=1 class="Normal" lang=ZH-CN> 
  <center>
	<p style='line-height:16.0pt;'>
		<span style='font-size:14.0pt;font-family:����_GB2312;'><br></span>
	</p> 
  	<p align=center style='text-align:center;line-height:16.0pt;'>
  		<b>
  			<span style='font-size:18.0pt;font-family:����;&quot;Times New Roman&quot;'>������
  			������һְҵ����ѧԺ
  			</span>
  		</b>
  	</p> 
  	<p align=center style='text-align:center'>
  		<b>
  		<span style='font-size:18.0pt;font-family:����;&quot;Times New Roman&quot;'>
  			<logic:empty name="rs">
  				_________
  			</logic:empty>
  			<logic:notEmpty name="rs">
  				<U>${rs.xn}</U>
  			</logic:notEmpty>
  			</span>
  		</b>
  		<b>
  			<span style='font-size:18.0pt;font-family:����;&quot;Times New Roman&quot;'>ѧ���ͥ��������ѧ�����Ѳ���������</span>
  		</b>
  	</p> 
  	<div align="center" style=" font-size:23px;">
  	<table width=80%  class="tbstyle" height=680> 
    <tr> 
      <td height=16% width=5% rowspan=4 class="Normal" > 
      	<br>��<br>��<br>��<br>��
      </td> 
      <td width=12% valign=top class="Normal">
      		����
      </td> 
      <td width=14% colspan=2 valign=top class="Normal">
      		<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xm"/>
      		</logic:notEmpty>
      </td> 
      <td width=7% valign=top class="Normal">
      		�Ա�
      </td> 
      <td width=14% valign=top class="Normal">
     		<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xb"/>
      		</logic:notEmpty>
      </td> 
      <td width=18% colspan=2 valign=top class="Normal">
     	 ��������
      </td> 
      <td width=11% valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      	 <logic:notEmpty name="rs">
      			<bean:write name="rs" property="csrq"/>
      		</logic:notEmpty> 
      </td> 
      <td width=8% colspan=2 valign=top class="Normal">
      	����
      </td> 
      <td width=8% valign=top class="Normal">
         	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="mzmc"/>
      		</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=12% valign=top class="Normal">
        ѧ��
      </td> 
      <td width=14% colspan=2 valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      	<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xh"/>
      		</logic:notEmpty>
      </td> 
      <td width=26% colspan=3 valign=top class="Normal">
        רҵ���꼶���༶
      </td> 
      <td width=42% colspan=5 valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      	<logic:notEmpty name="rs">
      			<bean:write name="rs" property="zymc"/>&nbsp;<bean:write name="rs" property="nj"/>&nbsp;<bean:write name="rs" property="bjmc"/>
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=34% colspan=4 valign=top class="Normal">
      	���벹��������
      </td> 
      <td width=32% colspan=3 valign=top class="Normal">
      	<logic:empty name="rs">
      		��ѧ��<input type="checkbox" disabled  name="bzlx"/>
      		��ʱ���Ѳ���<input type="checkbox" disabled name="bzlx"/>
      	</logic:empty>
      	<logic:notEmpty name="rs">
      	   <logic:notEqual name="rs" property="bzlx" value="002">
      		��ѧ��<input type="checkbox" disabled checked name="bzlx"/>
      		��ʱ���Ѳ���<input type="checkbox" disabled name="bzlx"/>
      	   </logic:notEqual>
        	<logic:equal name="rs" property="bzlx" value="002">
      		��ѧ��<input type="checkbox" disabled name="bzlx"/>
      		��ʱ���Ѳ���<input type="checkbox" disabled checked name="bzlx"/>
      		</logic:equal>
      	</logic:notEmpty>
      </td> 
      <td width=16% colspan=2 class="Normal"> 
      	Ʒ�µȵ�
      </td> 
      <td width=12% colspan=2 valign=top class="Normal">
      	    <logic:empty name="rs">
      		&nbsp; 
      		</logic:empty>
      		<logic:notEmpty name="rs">
      			<bean:write name="rs" property="pddd"/>
      	</logic:notEmpty>
      </td> 
    </tr> 
    <tr> 
      <td width=18% colspan=2 valign=top class="Normal">
      	��ѧ������������д��������
      </td> 
      <td width=48% colspan=4 valign=top class="Normal">
      	<logic:empty name="rs">
      		&nbsp; 
      	</logic:empty>
      	<logic:notEmpty name="rs">
      			<bean:write name="rs" property="xnzz"/>
      	</logic:notEmpty>	
      </td> 
      <td width=16% colspan=2 class="Normal">
      	�Ƿ�μ��ڹ���ѧ��������ѧ����
      </td> 
      <td colspan=3>
     	 <logic:empty name="rs">
      		&nbsp; 
      	</logic:empty>
      	<logic:notEmpty name="rs">
      		<bean:write name="rs" property="cjqgdg"/>
      	</logic:notEmpty>	 
      </td> 
    </tr> 
    <tr> 
      <td width=100% height="18%" colspan=12 valign=top class="Normal">
        ��������:(Ӧд����ͥ����������������˼���ͥ��Ա����״����Ŀǰ��ѧϰ������״̬��)<br>
        <logic:notEmpty name="rs">
          <bean:write name="rs" property="sqly"/>
         </logic:notEmpty>
       <br> <br><br> <br><br> <br><br> <br> 
        ���˱�֤������������ʵ�����<br>
      
       <span style="float:right; " >������ǩ�����������������������������ꡡ���¡�����</span></td> 
    </tr> 
    <tr> 
      <td width=100% height=18% colspan=12 valign=top class="Normal">
      		�༶���飺��Ӧд����ͥ����������̶ȡ�Ʒ�б��֡�ѧϰ������״����<br> <br><br> <br><br> <br><br> <br> ������������������������������������
   			<span style="float:right; " >ǩ����������������������������
			��&nbsp;&nbsp;
 			��&nbsp;&nbsp;
 			��&nbsp;&nbsp;</span></td> 
    </tr> 
    <tr> 
      <td width=100% height="18%" colspan=12 valign=top class="Normal">
      		 Ժϵ�������ʾ�����<br> <br><br> <br><br> <br><br> <br> ����������������������������������
   			<span style="float:right; " >ǩ����������������������������
			��&nbsp;&nbsp;
 			��&nbsp;&nbsp;
 			��&nbsp;&nbsp;</span></td>
    </tr> 
    <tr> 
      <td width=100% height="9%" colspan=12 valign=top class="Normal">
      		ѧ����������������������<br>
            &nbsp;&nbsp;&nbsp;&nbsp;�����ʵ������������ܣ�������
            <logic:notEmpty name="rs">
            <logic:notEqual name="rs" property="bzmc" value="��ѧ��">
      		<input type=checkbox disabled  name="bzlx"/>
        	</logic:notEqual>
        	<logic:equal name="rs" property="bzmc" value="��ѧ��">
      		<input type=checkbox disabled checked name="bzlx"/>
        	</logic:equal>
        	</logic:notEmpty>
        	<logic:empty name="rs">
        		<input type=checkbox disabled name="bzlx"/>
        	</logic:empty>
            <bean:message key="lable.xsgzyxpzxy" />��ѧ��ÿ�������
            <logic:empty name="rs">________</logic:empty>
            <logic:notEmpty name="rs">
            	<logic:notEqual name="rs" property="bzmc" value="��ѧ��">
            	________
            	</logic:notEqual>
            	<logic:equal name="rs" property="bzmc" value="��ѧ��">
            	<U>${rs.bzje}</U>
            	</logic:equal>
            </logic:notEmpty>
            Ԫ��������
            <logic:notEmpty name="rs">
            <logic:notEqual name="rs" property="bzmc" value="��ʱ����">
      		<input type=checkbox disabled  name="bzlx"/>
        	</logic:notEqual>
        	<logic:equal name="rs" property="bzmc" value="��ʱ����">
      		<input type=checkbox disabled checked name="bzlx"/>
        	</logic:equal>
        	</logic:notEmpty>
        	<logic:empty name="rs">
        		<input type=checkbox disabled name="bzlx"/>
        	</logic:empty>
            <bean:message key="lable.xsgzyxpzxy" />��ʱ���Ѳ������������
            <logic:empty name="rs">
             	________
            </logic:empty>
            <logic:notEmpty name="rs">
            	<logic:notEqual name="rs" property="bzmc" value="��ʱ����">
            	________
            	</logic:notEqual>
            	<logic:equal name="rs" property="bzmc" value="��ʱ����">
            	<U>${rs.bzje}</U>
            	</logic:equal>
            </logic:notEmpty>
            Ԫ�����ŷ�ʽΪ��
             <logic:empty name="rs">________</logic:empty>
            <logic:notEmpty name="rs">
            	<logic:notEqual name="rs" property="bzlx" value="002">
            	________
            	</logic:notEqual>
            	<logic:empty name="rs" property="fffs">
            	    <logic:equal name="bzlx" value="002">
            		________
            		</logic:equal>
            	</logic:empty>
            	<logic:equal name="rs" property="bzlx" value="002">
            		<U>${rs.fffs}</U>
            	</logic:equal>
            </logic:notEmpty>
            <span style="float:right; " >ǩ����������������������������
            ��&nbsp;&nbsp;
            ��&nbsp;&nbsp;
            ��&nbsp;&nbsp;</span>
            </td> 
    </tr> 
    <tr> 
      <td width=100% height="7%" colspan=12 valign=top class="Normal">
      Ժ�쵼��������<br> <br><br> <br><br> <br><br> <br> 
       <span style="float:right; " >ǩ����������������������������
       ��&nbsp;&nbsp;
       ��&nbsp;&nbsp;
       ��
       </td> 
    </tr> 
  </table>  
  </div>
</center>
		<div class="noprint" align="center">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</body>
</html>
