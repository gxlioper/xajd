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
  <p align=center style='text-align:center'><a name="OLE_LINK2"></a><a
name="OLE_LINK1"><b><span style='font-size:15.0pt;font-family:����;'>����ѧԺ������ѯԤԼ�����</span></b></a><span></span></p> 
  <div align=center> 
   <table width="100%" id="rsT" class="printstyle"> 
      <tr> 
        <td width=48 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>����</span></p></td> 
        <td width=122 colspan=4 class="Normal">&nbsp;${xs_rs.xm} </td> 
        <td width=40 colspan=2 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>�Ա�</span></p></td> 
        <td width=38 class="Normal">&nbsp;${xs_rs.xs_sex } </td> 
        <td width=48 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>ϵ��</span></p></td> 
        <td width=84 colspan=3 class="Normal">&nbsp;${xs_rs.xy} </td> 
        <td width=84 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>�꼶רҵ</span></p></td> 
        <td width=119 class="Normal" colspan="2">&nbsp; �꼶:${stu.nj }&nbsp;&nbsp;&nbsp;&nbsp;רҵ:${xs_rs.zy }</td> 
      </tr> 
      <tr> 
        <td width=100 colspan=2 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>ԤԼ��ʦ</span></p></td> 
        <td width=149 colspan=6 class="Normal">&nbsp; ${xs_rs.zxxxm }</td> 
        <td width=79 colspan=2 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>ԤԼʱ��</span></p></td> 
        <td width=258 colspan=5 class="Normal"> <p><span lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:12.0pt;font-family:����;'>&nbsp;${xs_rs.rq }</span></p></td> 
      </tr> 
      <tr> 
        <td width=171 colspan=6 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>����������ѯ����</span></p></td> 
        <td width=78 colspan=2 class="Normal">&nbsp; </td> 
        <td width=123 colspan=3 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>�Ƿ������Ů</span></p></td> 
        <td width=213 colspan=4 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>��</span><span
  lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:12.0pt;font-family:����;'>��</span></p></td> 
      </tr> 
      <tr> 
        <td width=48 rowspan=2 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>��ĸ�Ļ��̶�</span></p></td> 
        <td width=537 colspan=14 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>�������Ƽ�����</span><span
  lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp; </span><span style='font-size:
  12.0pt;font-family:����;  '>ר��</span><span lang=EN-US style='font-size:
  12.0pt;'>&nbsp;&nbsp; </span><span style='font-size:12.0pt;  font-family:����;'>��ר</span><span
  lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp; </span><span style='font-size:
  12.0pt;font-family:����;  '>������</span><span lang=EN-US style='font-size:
  12.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:12.0pt;font-family:����;'>��</span></p></td> 
      </tr> 
      <tr> 
        <td width=537 colspan=14 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>ĸ�����Ƽ�����</span><span
  lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp; </span><span style='font-size:
  12.0pt;font-family:����;  '>ר��</span><span lang=EN-US style='font-size:
  12.0pt;'>&nbsp;&nbsp; </span><span style='font-size:12.0pt;  font-family:����;'>��ר</span><span
  lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp; </span><span style='font-size:
  12.0pt;font-family:����;  '>������</span><span lang=EN-US style='font-size:
  12.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:12.0pt;font-family:����;'>��</span></p></td> 
      </tr> 
      <tr> 
        <td width=108 colspan=3 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>��ͥ�ṹ</span></p></td> 
        <td width=478 colspan=12 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>���׼�ͥ</span><span
  lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style='font-size:12.0pt;  font-family:����;'>������ͥ</span><span
  lang=EN-US style='font-size:12.0pt;'>&nbsp;&nbsp;&nbsp; </span></p></td> 
      </tr> 
      <tr> 
        <td width=136 colspan=4 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>Ǩ�ơ��ƾӾ���</span></p></td> 
        <td width=450 colspan=11 class="Normal">&nbsp; </td> 
      </tr> 
      <tr> 
        <td width=100 colspan=2 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>���ߣ������Ҫ��</span></p></td> 
        <td width=485 colspan=13 class="Normal">&nbsp; ${xs_rs.zxnr }</td> 
      </tr> 
      <tr> 
        <td width=100 colspan=2 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>���˵�ϣ����Ҫ������ѯ�Ķ�����Ը���ȣ�</span></p></td> 
        <td width=485 colspan=13 class="Normal">&nbsp;${xs_rs.qwmb } </td> 
      </tr> 
      <tr> 
        <td width=100 colspan=2 class="Normal"> <p><span style='font-size:12.0pt;  font-family:����;'>˵��</span></p></td> 
        <td width=485 colspan=13 class="Normal">&nbsp; </td> 
      </tr> 
      <tr height=0> 
        <td width=48 class="Normal"></td> 
        <td width=52 class="Normal"></td> 
        <td width=8 class="Normal"></td> 
        <td width=28 class="Normal"></td> 
        <td width=34 class="Normal"></td> 
        <td width=1 class="Normal"></td> 
        <td width=40 class="Normal"></td> 
        <td width=39 class="Normal"></td> 
        <td width=48 class="Normal"></td> 
        <td width=31 class="Normal"></td> 
        <td width=44 class="Normal"></td> 
        <td width=9 class="Normal"></td> 
        <td width=84 class="Normal"></td> 
        <td width=118 class="Normal"></td> 
        <td width=2 class="Normal"></td> 
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
