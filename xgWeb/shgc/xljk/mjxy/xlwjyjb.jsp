<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='
text-align:center'><span
style='font-size:14.0pt;font-family:����;'>����ѧԺѧ������Σ��Ԥ���ǼǱ�</span></p> 
  <p align=center style='
text-align:center'><span
lang=EN-US style='font-size:14.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span lang=EN-US style='font-size:12.0pt;'>&nbsp;</span><span
style='font-size:12.0pt;font-family:����_GB2312;
'>��ţ�</span></p> 
  <div align=center> 
    <table width="100%" id="rsT" class="printstyle"> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>�� ��</span></p></td> 
        <td width=108 class="Normal">&nbsp;${rs.xm } </td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>�� ��</span></p></td> 
        <td width=84 class="Normal">&nbsp; ${rs.xb }</td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>��������</span></p></td> 
        <td width=144 class="Normal">&nbsp; ${rs.csrq }</td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ϵ��</span></p></td> 
        <td width=108 class="Normal">&nbsp;${rs.xymc } </td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>�꼶רҵ</span></p></td> 
        <td width=84 class="Normal">&nbsp; ${rs.nj }  ${rs.zymc }</td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>��ϵ�绰</span></p></td> 
        <td width=144 class="Normal">&nbsp;${rs.lxdh } </td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>�� ͥ</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ס ַ</span></p></td> 
        <td width=288 colspan=4 class="Normal">&nbsp; ${rs.jtdz }</td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>��ͥ��</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ϵ�绰</span></p></td> 
        <td width=144 class="Normal">&nbsp; ${rs.lxdh1 }</td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal" align="center">��<br/>��<br/>��<br/>��</span></span></p></td> 
        <td width=528 colspan=7 class="Normal"> <p><span
  lang=EN-US style='font-size:12.0pt;font-family:
  ����_GB2312'>&nbsp;${rs.jbqk }</span><span
  style='font-size:12.0pt;font-family:����_GB2312'><br><br><br>��<span
  lang=EN-US>&nbsp;&nbsp; </span>��<span
  lang=EN-US>&nbsp; </span>��</span></p></td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>����Ա</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ǩ<span
  lang=EN-US>&nbsp; </span>��</span></p></td> 
        <td width=168 colspan=2 class="Normal">&nbsp; </td> 
        <td width=156 colspan=3 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312;'>ϵ��Ժ��</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>������ǩ��</span></p></td> 
        <td width=204 colspan=2 class="Normal">&nbsp; </td> 
      </tr> 
      <tr height=0> 
        <td width=79 class="Normal"></td> 
        <td width=108 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=36 class="Normal"></td> 
        <td width=84 class="Normal"></td> 
        <td width=36 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=144 class="Normal"></td> 
      </tr> 
    </table> 
  </div> 
</div> 
</body>
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
</html>

