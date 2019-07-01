<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);
		%>	
		<base target="_self"/>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
			type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
function expTab_se(the_table, tabTit, titSpan) {	
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='js/function.js'>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";		
	the_content += document.all(the_table).outerHTML;		
	//the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	//the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	//the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='ҳ������' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='��ӡԤ��' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"WebBrowser.ExecWB(6,6)\">";
	the_content += "<\/div>";
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}
	</script>
	</head>
	<body>		
	<center>
		<html:form action="/yxgl_init_time" method="post">
			<table id="expTb" width="99%">
			<tbody>
				<tr>
					<td width="100%" colspan="2">
						<div align="center" style="font-size: 35px;font-family:����;font-weight:bold;">
						���Ϲ�ҵ��ѧ</div><br>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div align="center" style="font-size: 20px;font-family:����;font-weight:bold;">
						2008��������ѧ��������</div><p>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<hr align="center" width="100%" size="2" color="#666666"/>
					<table id="table1" border="0" width="100%" bgcolor="#666666">
					<tr style="font-size: 18px;font-family:����;font-weight:bold;color:white">
						<td align="center">�������</td>
						<td align="center">��&nbsp;&nbsp;<input type="checkbox"/></td>
						<td align="center">��ʷ&nbsp;&nbsp;<input type="checkbox"/></td>
						<td align="center">������&nbsp;&nbsp;<input type="checkbox"/></td>
						<td align="center">ר����&nbsp;&nbsp;<input type="checkbox"/></td>
					</tr>
				</table>
				<hr align="center" width="100%" size="2"  color="#666666"/>	
				</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td style="width:75%">
						<p>������___<bean:write name="rs" property="xm"/>______ 
						   �Ա�__<bean:write name="rs" property="xb"/>__ 
						   ׼��֤�ţ�______ <bean:write name="rs" property="ksh"/>_______
						 
					</td>
					<td rowspan="4" style="width:25%">
						<img height="100" width="75"  border="1" align="right"
							src="<bean:write name="rs" property="picture"/>"/>
					</td>
				</tr>
				<tr><td>¼ȡ֪ͨ�飺_________________
						ѧ�ţ�_______<bean:write name="rs" property="xh"/>________</td></tr>
				<tr><td><bean:message key="lable.xsgzyxpzxy" />��_______<bean:write name="rs" property="xymc"/>________
						רҵ��_______<bean:write name="rs" property="zymc"/>________</td></tr>
				<tr><td>�༶��_______<bean:write name="rs" property="bjmc"/>________
						����ʱ�䣺_______��________��</td></tr>
			</tbody>
			
			<tbody>
				<tr>
					<td colspan="2">
			<table id="table3" width="100%">
					<tr><td>&nbsp;</td></tr>
				<tr>
				<td align="left" style="font-weight:bold;font-family:����;font-size: 17px;" colspan="5">
					һ����������ʱ�����һ��������
				</td>
				</tr>
				<tr>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:����;font-size: 13px;"><bean:message key="lable.xsgzyxpzxy" />�ʸ���飺</font>
							<div>
							 1.���鲢����׼��֤�����֤��<br>&nbsp;&nbsp;¼ȡ֪ͨ�顢���ӵ�������<br> &nbsp;&nbsp;Ƭ���뱾�������<br>
							 2.��д�����ǼǱ��˶�ѧ��<br>
							 3.��ȡ��ͨ��ũ�忨��ͨ���վ�<br>
							 4.��ȡס��֪ͨ��<p>
							 <div align="right">��ѧ  Ժ��</div>
							</div>
					</td>
					<td style="width:8%" align="center">
						<font style="height:4px;width:30px;font-size: 13px;">&rarr;</font>
					</td>
					<td style="width:25%;height:150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:����;font-size: 13px;">�շѣ�</font><br>
							 1.��ͨ��ũ�忨���Ѳ���<bean:message key="lable.xsgzyxpzxy" /><br>&nbsp;&nbsp;��ȡ�վݵ�ͬѧ�������<br>
							 2.���������ڵ�С��У԰����<br>&nbsp;&nbsp;����ȡ��ʱУ԰��ת��<p><br><br>
							 <div align="right">�����񴦣�</div>	
					</td>
					<td style="width:8%"  align="center">&rarr;</td>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:����;font-size: 13px;">ס�ޣ�</font><br>
							 	ƾס��֪ͨ������ס¥���Ǽ�<br>��ȡԿ����ס<br><br>
								____<bean:write name="rs" property="ldh"/>_____¥<br>
								____<bean:write name="rs" property="qsh"/>_____����<p><br>
							 <div align="right">���޽̹������ģ�</div>
					</td>
				</tr>
				</table>
				</td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
				 <td colspan="2">
				<table id="table3" width="100%">
				<tr>
						<td>
							&nbsp;
						</td>
						</tr>
				<tr>
				<td align="left" style="font-weight:bold;font-family:����;font-size: 17px;" colspan="5">
					������У�󣬾�ѵ�ڼ���<bean:message key="lable.xsgzyxpzxy" />֪ͨ�����������
				</td>
				</tr>
				<tr>
					<td width="25%" height="150px" style="border:1px #000000 solid">
						<div>
							<font style="font-weight:bold;font-family:����;font-size: 13px;">��죺</font>
							<div>
							 ʱ�䰲���ھ�ѵ�ڼ�<br>��첻�ϸ��ߣ���ѧУ������<br>���Ҵ���<br>
							<p><br>
							ҽʦ��<p>
							 <div align="right">��УҽԺ��</div>
							</div>
						</div>
					</td>
					<td style="width:8%"  align="center"><font style="height:4px;width:30px;font-size:10.5pt;">&rarr;</font></td>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:����;font-size: 13px;">������ϵ��</font>
							<p>
							 �����ˣ�<p><br><br><br><br>
							 <div align="right">����������</div>
					</td>
					<td style="width:8%"  align="center">&rarr;</td>
					<td width="25%" height="150px" style="border:1px #000000 solid">
							<font style="font-weight:bold;font-family:����;font-size: 13px;">ע�᣺</font><br>
							 	�˵���׼��֤��¼ȡ֪ͨ�鸽<br>��������<bean:message key="lable.xsgzyxpzxy" /><br>
							<p></p><br><br><br><br>
							 <div align="right">��ѧ Ժ��</div>
					</td>
				</tr>
				</table>
				 </td>
				</tr>
			</tbody>
			
			<tbody>
				<tr>
					<td colspan="2">
						<table  id="table4" width="100%" > 
						<tr>
						<td>
							&nbsp;
						</td>
						</tr>
					<tr>
					<td align="left" style="font-weight:bold;font-family:����;font-size: 17px;" colspan="5">
						����ע�����
					</td>
					</tr>
					<tr>
						<td align="left">
						<div style="width:100%">
							1.<bean:message key="lable.xsgzyxpzxy" />�ڷ�����ʱ��Ӧע����������������Ա�רҵ���༶��׼��֤�š�ѧ�ż�����ʱ�䡣<br>
							2.����ƾ�˵���׼��֤�ż���ѧ֪ͨ���ϸ��չ涨��˳�������ѧ�������йص�λ�ڰ���˵�ʱ��������
							Ӧǩ�����Ӹ�ר���¡�<br>
							3.�������"��ɫͨ��"��������ּ�ͥ���ڵ��������������ų��ߵ�����֤����ѧ���������֤������
							Ǩ��֤�����ڱ�����׼��֤ԭ������ѧУ���������ѧ����������ɫͨ�����ٵ����񴦰������������<br>
							4.����ƾ�����վ��԰༶Ϊ��λ���������顣<br>
							5.�����뽫�����������꣬���˵���׼��֤��֪ͨ��ڶ�������������<bean:message key="lable.xsgzyxpzxy" />��ƾ���񴦽ɷ��վ���<bean:message key="lable.xsgzyxpzxy" />ע���
							����ȡ��ѧ����<br>
							6.����������ѧУ��ͳһ��֯��ѧ�ʸ���飬�뷢����Υ����У�涨�ߣ���ȡ������У�ʸ�<br>
							7.��ȡУ԰����ַ����ɽ·У��ѧ8¥���棻��ԭ·У��1��¥113���䣻������У����¥�㳡��<br>
						</div>	
						</td>
					</tr>
				</table>	
					</td>
				</tr>
			</tbody>
		</table>		
		<div class="buttontool" align="center">	
			<button class="button2" onclick="expTab_se('expTb','','')">
						��ӡ����
			</button>
		</div>					
			</html:form>
		</center>
	</body>
</html>
