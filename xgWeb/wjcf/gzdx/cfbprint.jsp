<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
<title>ѧ��Υ�ʹ��ֳʱ�������</title>
<style>
<!--
.Section1
	{page:Section1;}
-->
</style>
<style media='print'>
		.noPrin{display:none;}
	</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<body bgcolor="#FFFFFF" class="Normal" style='text-justify-trim:punctuation' lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center; font-weight: bold; font-size: 18px;'><span style='font-family:����'>ѧ��Υ�ʹ��ֳʱ�������</span></p> 
  <div align=center> 
    <table class="printstyle" align=center width="100%" > 
      <tr> 
        <th width=60 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>����</span></p></th> 
        <td width=140 class="Normal" align="center">&nbsp;${rs.xm } </td> 
        <th width=58 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>�Ա�</span></p></th> 
        <td width=109 class="Normal" align="center">&nbsp;${rs.xb } </td> 
        <th width=73 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>��������</span></p></th> 
        <td width=128 class="Normal" align="center">&nbsp; ${rs.csrq }</td> 
      </tr> 
      <tr> 
        <th width=60 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>����</span></p></th> 
        <td width=140 class="Normal" align="center">&nbsp; ${rs.jg }</td> 
        <th width=58 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>�༶</span></p></th> 
        <td width=109 class="Normal" align="center">&nbsp; ${rs.bjmc }</td> 
        <th width=73 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>ѧ��</span></p></th> 
        <td width=128 class="Normal" align="center">&nbsp; ${rs.xh }</td> 
      </tr> 
      <tr> 
        <th width=60 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>Υ����ʵ</span></p></th> 
        <td width=507 colspan=5 class="Normal"> <p>&nbsp;</p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p></td> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 valign=top class="Normal"> <p align="left"><span
  style='font-size:9.0pt;line-height:150%;font-family:����'><bean:message key="lable.xsgzyxpzxy" />�������ݼ����������</span></p> 
          <p align=center style='text-align:center;
  word-break:break-all'>&nbsp;${rs.xyclyj }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p align=right style='text-align:right;
  word-break:break-all'>&nbsp;</p>
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:����'><bean:message key="lable.xsgzyxpzxy" />�쵼ǩ����${rs.fsjname }<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p></th> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 class="Normal"> <p align=center style='text-align:center;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'><bean:message key="lable.xsgzyxpzxy" />����˼�빤����ѧ����<span lang=EN-US>&nbsp;&nbsp;${rs.year }&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;${rs.month }&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;${rs.date }&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp; </span>ȷ��<span lang=EN-US>&nbsp; </span></span></p></th> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 valign=top class="Normal"> <p align=left style='text-align:left;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>ѧ�������������</span></p>
          <p align=center style='text-align:center;
  '>&nbsp;${rs.xxclyj }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:����'>ѧ�����쵼ǩ����<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>ѧ�������£�<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p> 
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:����'>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>��<span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p></th> 
      </tr> 
      <tr> 
        <th width=567 colspan=6 valign=top class="Normal"> <p align=left style='text-align:left;
  '><span style='font-size:9.0pt;line-height:150%;font-family:
  ����'>ѧУ���������</span></p> 
          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:����'>����У�쵼ǩ����<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p>          <p align=right style='text-align:right;
  word-break:break-all'><span style='font-size:9.0pt;
  line-height:150%;font-family:����'>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>��<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>��<span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></p></th> 
      </tr> 
    </table> 
  </div> 
</div> 
<div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button> 
    </div>
</body>

</html>