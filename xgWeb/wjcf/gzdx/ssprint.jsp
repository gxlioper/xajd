<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" 
"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
<style media='print'>
.noPrin{
display:none;}
</style>
<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<body>
<script language='javascript' src='style/function.js'><script language='javascript' src='js/prototype-1.6.0.3.js'><script language='javascript'>var HKEY_Root,HKEY_Path,HKEY_Key;HKEY_Root='HKEY_CURRENT_USER';HKEY_Path='\Software\Microsoft\Internet Explorer\PageSetup\';var Wsh=new ActiveXObject('WScript.Shell');HKEY_Key='header';Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');HKEY_Key='footer';Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');</script>
<table class=formlist id=rsTable width="100%">
<thead><tr><th colSpan=5><span>ѧ��Υ�ʹ������������</span> </th></tr></thead>
<tr>
<td colSpan=5><FONT color=red>��ʾ1���������ϵ���ύ��ҳ���ݡ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<BR>2�����ӡ�����������ϣ����ɱ�����дǩ����ݽ�ѧУѧ������ίԱ�ᣨ��ϵ�绰������ص㿴��������ʾ���� </FONT></td></tr>
<tbody>
<tr>
<td align=right width="15%"><FONT color=red> </FONT>ѧ�� </td>
<td align=left width="25%"> ${rs.xh }</td>
<td align=right width="15%"><FONT color=red> </FONT> �����ļ��� </td>
<td align=left width="30%">${rs.cfwh }</td>
<td align=left width="15%" rowSpan=5><IMG src="/xgxt/pictures/${rs.xh }.jpg" border=0> </td></tr>
<tr>
<td align=right width="10%">���� </td>
<td align=left>${rs.xm }</td>
<td align=right width="15%">��� </td>
<td align=left>${rs.nd} </td></tr>
<tr>
<td align=right>�Ա� </td>
<td align=left>${rs.xb } </td>
<td align=right>ѧ�� </td>
<td align=left>${rs.xn } </td></tr>
<tr>
<td align=right>�꼶 </td>
<td align=left>${rs.nj } </td>
<td align=right>ѧ�� </td>
<td align=left>${rs.xq } </td></tr>
<tr>
<td align=right><bean:message key="lable.xsgzyxpzxy" /> </td>
<td align=left>${rs.xymc }</td>
<td align=right>����ʱ�� </td>
<td align=left>${rs.cfsj } </td></tr>
<tr>
<td align=right>רҵ </td>
<td align=left>${rs.zymc } </td>
<td align=right>������� </td>
<td align=left colSpan=2>${rs.cflbmc }</td></tr>
<tr>
<td align=right>�༶ </td>
<td align=left>${rs.bjmc } </td>
<td align=right>�������� </td>
<td align=left colSpan=2>${rs.cfyymc } </td></tr>
<tr>
<td align=right><FONT color=red> </FONT>��ϵ��ַ </td>
<td align=left>${rs.dz } </td>
<td align=right>����ʱ�� </td>
<td align=left colSpan=2>${rs.sssj } </td></tr>
<tr>
<td align=right>�������� </td>
<td align=left>${rs.yb} </td>
<td align=right><FONT color=red> </FONT>��ϵ�绰 </td>
<td align=left colSpan=2>${rs.lxdh } </td></tr>
<tr>
<td align=right>���߳�������<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; </td>
<td align=left colSpan=4>${rs.yq }</td></tr>
<tr>
<td align=right>���ϻ�&nbsp;&nbsp;&nbsp;<BR>֤������</td>
<td align=left colSpan=4> </td></tr>
</tbody>
<tr>
	<td colspan="5" align="right">
	<button type="button" onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
	<button type="button" onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
	<button type="button" onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button>
	</td>
</tr>
</table>
	
</body>
</html>
