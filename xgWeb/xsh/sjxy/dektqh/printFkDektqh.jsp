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
  <p align=center style='text-align:center'><b><span style='font-size:22.0pt;font-family:����;"Times New Roman";"Times New Roman"'>�ڶ����û���������������</span></b></p> 
  <table class=tbstyle width=548> 
    <tr> 
      <td width=42 rowspan=4 valign=top class="Normal">
      	<b>
      		��<br>��<br>��<br>��
      	</b>
      </td> 
      <td width=62 valign=top class="Normal"> 	
      	������<br>�ϻ���<br>ϯ����<br>���
      </td> 
      <td width=444 colspan=4 align="right" valign=bottom class="Normal">
      		ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </td> 
    </tr> 
    <tr> 
      <td width=62 valign=top class="Normal"> 
      		ѧ����<br>��ϯ��<br>�����
      </td> 
      <td width=444 colspan=4 align="right" valign=bottom class="Normal">
      		ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr> 
    <tr> 
      <td width=506 colspan=5 valign=top class="Normal">
      	����ʦ�Ƿ���׼�ִ��
      	<logic:empty name="rs" property="xxsh" >
      		<input type=checkbox name="sh" disabled />��
      		<input type=checkbox name="sh" disabled  />��
      	</logic:empty>
      	<logic:equal name="rs" property="xxsh" value="ͨ��">
      		<input type=checkbox name="sh" disabled checked/>��
      		<input type=checkbox name="sh" disabled  />��
      	</logic:equal>
      	<logic:notEqual name="rs" property="xxsh" value="ͨ��">
      		<input type=checkbox name="sh" disabled/>��
      		<input type=checkbox name="sh" disabled  />��
      	</logic:notEqual>
      	<br>
      	<br>
      	<br>
      	<br>
 		<p align=right>ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
 	  </td> 
    </tr> 
    <tr> 
      <td width=74 colspan=2 valign=top class="Normal"> <p><span style='font-family:����'>��ί���<br>�������</span></p></td> 
      <td width=433 colspan=3 valign=top class="Normal"> <p align=right>ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
      </td> 
    </tr> 
    <tr> 
      <td width=42 rowspan=3 valign=top class="Normal"> 
      	<b>��<br>��<br>��<br>��</b>
      </td> 
      <td width=74 colspan=2 valign=top class="Normal">
      	ʵ�ʳ�<br>ϯ����
      </td> 
      <td width=144 valign=top class="Normal">&nbsp; </td> 
      <td width=84 valign=top class="Normal">�Ч��</td> 
      <td width=205 valign=top class="Normal">
      <logic:empty name="rs" property="hdxg" >
      		��<input type=checkbox name="sh" disabled checked/>
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  />
      	</logic:empty>
      <logic:equal name="rs" property="hdxg" value="��">
      		��<input type=checkbox name="sh" disabled checked/>
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  />
      	</logic:equal>
      	<logic:equal name="rs" property="hdxg" value="��">
      		��<input type=checkbox name="sh" disabled />
      		��<input type=checkbox name="sh" disabled checked />
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  />
      	</logic:equal>
      	<logic:equal name="rs" property="hdxg" value="��">
      		��<input type=checkbox name="sh" disabled />
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  checked/>
      		��<input type=checkbox name="sh" disabled  />
      	</logic:equal>
      	<logic:equal name="rs" property="hdxg" value="��">
      		��<input type=checkbox name="sh" disabled />
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  />
      		��<input type=checkbox name="sh" disabled  checked/>
      	</logic:equal>
      </td> 
    </tr> 
    <tr> 
      <td width=74 colspan=2 valign=top class="Normal">ѧ������<br>ϯ�º���<br>�ۡ����</td> 
      <td width=433 colspan=3 valign=bottom class="Normal">
      	<p align=right>ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
      </td> 
    </tr> 
    <tr> 
      <td width=74 colspan=2 valign=top class="Normal">
      	��ί��<br>������<br>�����ۡ�<br>���
      </td> 
      <td width=433 colspan=3 valign=top class="Normal"> <p><span style='font-family:����;
  &quot;Times New Roman&quot;'>ͼ</span><span
  lang=EN-US>&nbsp; </span><span style='font-family:����;"Times New Roman"'>Ƭ���У���</span><span lang=EN-US>&nbsp; </span><span
  style='font-family:����;
  &quot;Times New Roman&quot;'>�񣨣�</span></p> 
        <p><span style='font-family:����;
  &quot;Times New Roman&quot;'>ͨѶ�壺�У���</span><span
  lang=EN-US>&nbsp; </span><span style='font-family:����;"Times New Roman"'>�񣨣�</span></p> 
        <p><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
  lang=EN-US>&nbsp; </span><span style='font-family:����;"Times New Roman"'>����ͬ�⣨��</span><span lang=EN-US>&nbsp; </span><span
  style='font-family:����;
  &quot;Times New Roman&quot;'>��ͬ�⣨��</span></p> 
        <p align=right style='text-align:right'><span
  style='font-family:����;
  &quot;Times New Roman&quot;'>ǩ�����ߣߣߣߣ�</span></p> 
        <p align=right style='text-align:right'><span
  style='font-family:����;
  &quot;Times New Roman&quot;'>���ڣ�������£���</span></p></td> 
    </tr> 
  </table> 
</div> 
  <table width=548>
  <tr>
  <td>
 ��ע��
 </td></tr>
 <tr>
  <td>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1��δ����׼�Ļ����ִ�У�����׷��ֱ�ӵ����˵����Σ� </td></tr>
 <tr>
  <td>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2���������Ҫ��������������������ɱ�������ѣ������Ƿ�Ʊ��</td></tr>
 </table>
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
