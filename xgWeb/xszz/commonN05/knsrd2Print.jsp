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
<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt' > 
<center>
  <table width=80%  class="tbstyle"  width=735> 
    <tr> 
      <td width=735 colspan=11 class="Normal"> <p align=center style='text-align:center;'><b><span
  style='font-size:22.0pt;font-family:����;'>������һְҵ����<bean:message key="lable.xsgzyxpzxy" /><br> 
          ѧ��ڶ�ѧ�ڼ�ͥ��������ѧ���϶����˻��ܱ�</span></b></p></td> 
    </tr> 
    <tr> 
      <td width=39 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>���</span></p></td> 
      <td width=39 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>����</span></p></td> 
      <td width=39 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>�Ա�</span></p></td> 
      <td width=148 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>רҵ���꼶���༶</span></p></td> 
      <td width=116 colspan=3 valign=bottom nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>�϶��ȼ�</span></p></td> 
      <td width=111 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>����С��ǩ��</span></p></td> 
      <td width=130 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>���鹤����ǩ��</span></p></td> 
      <td width=75 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'><bean:message key="lable.xsgzyxpzxy" />����</span></p></td> 
      <td width=39 rowspan=2 nowrap class="Normal"> <p align=center style='text-align:center;'><span
  style='font-size:12.0pt;font-family:����;'>��ע</span></p></td> 
    </tr> 
    <tr> 
      <td width=39 valign=bottom nowrap class="Normal"> <p align=left style='text-align:left;'><span
  style='font-size:12.0pt;font-family:����;'>����</span></p></td> 
      <td width=39 valign=bottom nowrap class="Normal"> <p align=left style='text-align:left;'><span
  style='font-size:12.0pt;font-family:����;'>����</span></p></td> 
      <td width=39 valign=bottom nowrap class="Normal"> <p align=left style='text-align:left;'><span
  style='font-size:12.0pt;font-family:����;'>һ��</span></p></td> 
    </tr> 
   <logic:iterate name="rs" id="s" indexId="index">
		<tr onclick="rowMoreClick(this,'',event);"
			ondblclick="modi('nbtyWmbj.do?method=nbtyShOneWmbj&doType=save')" style="cursor:hand">
				<td><bean:write name="index"/></td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<td>
								<logic:iterate id="v" name="s" offset="2" length="3">
										<bean:write name="v" />
								</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="5" length="1">
								  <logic:equal name="v" value="��������">
								  		<td>��</td>
								  		<td>&nbsp;</td>
								  		<td>&nbsp;</td>
								  </logic:equal>
								  <logic:equal name="v" value="����">
								  		<td>&nbsp;</td>
								  		<td>��</td>
								  		<td>&nbsp;</td>
								  </logic:equal>
								  <logic:equal name="v" value="һ������">
								  		<td>&nbsp;</td>
								  		<td>&nbsp;</td>
								  		<td>��</td>
								 </logic:equal>
								  <logic:equal name="v" value="δ���">
								  		<td>&nbsp;</td>
								  		<td>&nbsp;</td>
								  		<td>&nbsp;</td>
								 </logic:equal>
								 <logic:equal name="v" value="������">
								  		<td>&nbsp;</td>
								  		<td>&nbsp;</td>
								  		<td>&nbsp;</td>
								 </logic:equal>
								</logic:iterate>
								<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							</tr>
						</logic:iterate>
  </table> 
  </center>
  <div class="noprint" align="center">
			<input type='button' class='button2' value='ҳ������'
				onclick="WebBrowser.ExecWB(8,1);return false;">
			<input type='button' class='button2' value='��ӡԤ��'
				onclick="WebBrowser.ExecWB(7,1);return false;">
			<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
				onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
</div> 
</body>
</html>
