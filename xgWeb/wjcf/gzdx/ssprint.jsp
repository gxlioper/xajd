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
<thead><tr><th colSpan=5><span>学生违纪处分申诉申请表</span> </th></tr></thead>
<tr>
<td colSpan=5><FONT color=red>提示1、须在网上点击提交本页内容。&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<BR>2、须打印书面申述材料，并由本人手写签名后递交学校学生申诉委员会（联系电话及具体地点看决定书提示）。 </FONT></td></tr>
<tbody>
<tr>
<td align=right width="15%"><FONT color=red> </FONT>学号 </td>
<td align=left width="25%"> ${rs.xh }</td>
<td align=right width="15%"><FONT color=red> </FONT> 处分文件号 </td>
<td align=left width="30%">${rs.cfwh }</td>
<td align=left width="15%" rowSpan=5><IMG src="/xgxt/pictures/${rs.xh }.jpg" border=0> </td></tr>
<tr>
<td align=right width="10%">姓名 </td>
<td align=left>${rs.xm }</td>
<td align=right width="15%">年度 </td>
<td align=left>${rs.nd} </td></tr>
<tr>
<td align=right>性别 </td>
<td align=left>${rs.xb } </td>
<td align=right>学年 </td>
<td align=left>${rs.xn } </td></tr>
<tr>
<td align=right>年级 </td>
<td align=left>${rs.nj } </td>
<td align=right>学期 </td>
<td align=left>${rs.xq } </td></tr>
<tr>
<td align=right><bean:message key="lable.xsgzyxpzxy" /> </td>
<td align=left>${rs.xymc }</td>
<td align=right>处分时间 </td>
<td align=left>${rs.cfsj } </td></tr>
<tr>
<td align=right>专业 </td>
<td align=left>${rs.zymc } </td>
<td align=right>处分类别 </td>
<td align=left colSpan=2>${rs.cflbmc }</td></tr>
<tr>
<td align=right>班级 </td>
<td align=left>${rs.bjmc } </td>
<td align=right>处分事由 </td>
<td align=left colSpan=2>${rs.cfyymc } </td></tr>
<tr>
<td align=right><FONT color=red> </FONT>联系地址 </td>
<td align=left>${rs.dz } </td>
<td align=right>申诉时间 </td>
<td align=left colSpan=2>${rs.sssj } </td></tr>
<tr>
<td align=right>邮政编码 </td>
<td align=left>${rs.yb} </td>
<td align=right><FONT color=red> </FONT>联系电话 </td>
<td align=left colSpan=2>${rs.lxdh } </td></tr>
<tr>
<td align=right>申诉陈述理由<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; </td>
<td align=left colSpan=4>${rs.yq }</td></tr>
<tr>
<td align=right>材料或&nbsp;&nbsp;&nbsp;<BR>证明附件</td>
<td align=left colSpan=4> </td></tr>
</tbody>
<tr>
	<td colspan="5" align="right">
	<button type="button" onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
	<button type="button" onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
	<button type="button" onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button>
	</td>
</tr>
</table>
	
</body>
</html>
