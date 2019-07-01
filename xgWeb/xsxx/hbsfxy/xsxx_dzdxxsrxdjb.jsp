<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/stuinfoFunction.js"></script>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
<body> 
<html:form action="/stu_info_add.do" method="post">
<hr style="width:100%"> 
<center>
    <br /> 
  <br /> 
  <table width="80%" align="center" border="0" class="tbstyle"> 
   <div class=Section1 style='layout-grid:15.6pt'>
  <p align=center style='text-align:center'><h2 align="center">�й����ʴ�ѧ������ѧ�ǼǱ�</h2></p>
  <p align="center">���:<bean:write name="rs" property="BJMC"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �������:</p>
  <div align="center">
    <table class="tbstyle" border=0 cellspacing=0 cellpadding=0 >
      <tr>
        <td width=57 rowspan=10 class="Normal"><p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>Ϣ</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=100 colspan=4 class="Normal"><bean:write name="rs" property="XM"/></td>
        <td width=57 class="Normal"><p align=center style='text-align:center'>�Ա�</p></td>
        <td width=59 colspan=2 class="Normal"><bean:write name="rs" property="XB"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>��������</p></td>
        <td width=72 colspan=3 class="Normal"><bean:write name="rs" property="CSRQ"/></td>
        <td width=84 rowspan=6 class="Normal">
			<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
							border="0" style="width:140;height:160"/>
		</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>������</p></td>
        <td width=100 colspan=4 class="Normal"><bean:write name="rs" property="CYM"/></td>
        <td width=57 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=59 colspan=2 class="Normal"><bean:write name="rs" property="MZ"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>�ڽ�����</p></td>
        <td width=72 colspan=3 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>���֤��</p></td>
        <td width=216 colspan=7 class="Normal"><bean:write name="rs" property="SFZH"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>�Ƿ��Ⱦ�</p></td>
        <td width=72 colspan=3 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>���</p></td>
        <td width=84 colspan=2 class="Normal"><bean:write name="rs" property="SG"/></td>
        <td width=84 colspan=4 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=48 class="Normal"><bean:write name="rs" property="TZ"/></td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>���˵绰</p></td>
        <td width=72 colspan=3 class="Normal"><bean:write name="rs" property="LXDH"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>������ò</p></td>
        <td width=84 colspan=2 class="Normal"><bean:write name="rs" property="ZZMM"/></td>
        <td width=84 colspan=4 class="Normal"><p align=center style='text-align:center'>�����س�</p></td>
        <td width=192 colspan=8 class="Normal"><bean:write name="rs" property="AH"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>����ʱ��</p></td>
        <td width=84 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 colspan=4 class="Normal"><p align=center style='text-align:center'>�뵳ʱ��</p></td>
        <td width=48 class="Normal">&nbsp;</td>
        <td width=72 colspan=4 class="Normal"><p align=center style='text-align:center'>ת��ʱ��</p></td>
        <td width=72 colspan=3 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="JG"/></td>
        <td width=84 rowspan=14 class="Normal"><p>��д˵��:<br>
1:���ᡢ��������д���ؼ��������ʡ�Ƹ����ŷ��ء�<br>
2:�������·幫Ԣ����ע�����·�A���������˳�������д�𳵻����ִ����յ�վ��<br>
3:��ͥ��ѧУ�ĵ�ַ��д�ܹ��յ��ż�����ϸ��ַ</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>������</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="SYD"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>��ͥסַ</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="JTDZ"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>��������</p></td>
        <td width=360 colspan=14 class="Normal"><bean:write name="rs" property="YZBM"/></td>
      </tr>
      <tr>
        <td width=57 rowspan=6 class="Normal"><p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>У</p>
          <p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>Ϣ</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>ѧ��</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="XH"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>�༶���</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="BJMC"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'><bean:message key="lable.xsgzyxpzxy" />����</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="XYMC"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>רҵ����</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="ZYMC"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>��ѧ����</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="RXRQ"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>�������</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="PYCC"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>�������</p></td>
        <td width=157 colspan=5 class="Normal"><bean:write name="rs" property="SSBH"/></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>����绰</p></td>
        <td width=132 colspan=6 class="Normal"><bean:write name="rs" property="QSDH"/></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>�˳�����</p></td>
        <td width=157 colspan=5 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>�˳�����</p></td>
        <td width=132 colspan=6 class="Normal"><p align=center style='text-align:center'>�人--</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>һ��ͨ��</p></td>
        <td width=157 colspan=5 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>�������п���</p></td>
        <td width=132 colspan=6 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=5 class="Normal"><p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>��</p>
          <p align=center style='text-align:center'>Ϣ</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>������</p></td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>��Դ��ѧ</p></td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal"><p align=center style='text-align:center'>ѧУ��ַ</p></td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 rowspan=2 class="Normal"><p align=center style='text-align:center'>�߿��ɼ�</p></td>
        <td width=43 class="Normal"><p align=center style='text-align:center'>�ܷ�</p></td>
        <td width=53 colspan=2 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=61 colspan=2 class="Normal"><p align=center style='text-align:center'>��ѧ</p></td>
        <td width=71 colspan=3 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal"><p align=center style='text-align:center'>�ۺ�</p></td>
      </tr>
      <tr>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=71 colspan=3 class="Normal">&nbsp;</td>
        <td width=48 colspan=2 class="Normal">&nbsp;</td>
        <td width=51 colspan=3 class="Normal">&nbsp;</td>
        <td width=33 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=5 class="Normal"><p align=center style='text-align:center'>����</p>
          <p align=center style='text-align:center'>ѧϰ</p>
          <p align=center style='text-align:center'>����</p></td>
        <td width=114 colspan=2 class="Normal"><p align=center style='text-align:center'>�ɺ�ʱ����ʱ</p></td>
        <td width=114 colspan=4 class="Normal"><p align=center style='text-align:center'>�ںε�λ</p></td>
        <td width=107 colspan=4 class="Normal"><p align=center style='text-align:center'>�κ�ְ��</p></td>
        <td width=96 colspan=5 class="Normal"><p align=center style='text-align:center'>�յ����ֽ����򴦷�</p></td>
        <td width=84 class="Normal"><p align=center style='text-align:center'>֤����</p></td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=114 colspan=2 class="Normal">&nbsp;</td>
        <td width=114 colspan=4 class="Normal">&nbsp;</td>
        <td width=107 colspan=4 class="Normal">&nbsp;</td>
        <td width=96 colspan=5 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=5 class="Normal"><p align=center style='text-align:center'>��ͥ</p>
          <p align=center style='text-align:center'>��Ҫ</p>
          <p align=center style='text-align:center'>��Ա</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>�ƺ�</p></td>
        <td width=43 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=53 colspan=2 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=61 colspan=2 class="Normal"><p align=center style='text-align:center'>������ò</p></td>
        <td width=143 colspan=7 class="Normal"><p align=center style='text-align:center'>������λ</p></td>
        <td width=60 colspan=2 class="Normal"><p align=center style='text-align:center'>ְ��</p></td>
        <td width=84 class="Normal"><p align=center style='text-align:center'>��ϵ�绰</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=57 rowspan=4 class="Normal"><p align=center style='text-align:center'>��Ҫ</p>
          <p align=center style='text-align:center'>���</p>
          <p align=center style='text-align:center'>��ϵ</p></td>
        <td width=70 class="Normal"><p align=center style='text-align:center'>�ƺ�</p></td>
        <td width=43 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=53 colspan=2 class="Normal"><p align=center style='text-align:center'>����</p></td>
        <td width=61 colspan=2 class="Normal"><p align=center style='text-align:center'>������ò</p></td>
        <td width=143 colspan=7 class="Normal"><p align=center style='text-align:center'>������λ</p></td>
        <td width=60 colspan=2 class="Normal"><p align=center style='text-align:center'>ְ��</p></td>
        <td width=84 class="Normal"><p align=center style='text-align:center'>��ϵ�绰</p></td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr>
        <td width=70 class="Normal">&nbsp;</td>
        <td width=43 class="Normal">&nbsp;</td>
        <td width=53 colspan=2 class="Normal">&nbsp;</td>
        <td width=61 colspan=2 class="Normal">&nbsp;</td>
        <td width=143 colspan=7 class="Normal">&nbsp;</td>
        <td width=60 colspan=2 class="Normal">&nbsp;</td>
        <td width=84 class="Normal">&nbsp;</td>
      </tr>
      <tr height=0>
        <td width=57 class="Normal"></td>
        <td width=70 class="Normal"></td>
        <td width=43 class="Normal"></td>
        <td width=41 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=4 class="Normal"></td>
        <td width=57 class="Normal"></td>
        <td width=11 class="Normal"></td>
        <td width=48 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=36 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=12 class="Normal"></td>
        <td width=27 class="Normal"></td>
        <td width=33 class="Normal"></td>
        <td width=84 class="Normal"></td>
      </tr>
    </table>
  </div>
</div>  
  </table>  
</center> 
<div class='noPrin' align="center">
	 <input type='button' value='ҳ������' onclick="try{WebBrowser.ExecWB(8,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">
	 <input type='button' value='��ӡԤ��' onclick="try{WebBrowser.ExecWB(7,1)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">
	 <input type='button' value='ֱ�Ӵ�ӡ' onclick="try{WebBrowser.ExecWB(6,6)}catch(e){alert('�����ð�ȫ��������ActiveX�ؼ��Ͳ����')}">
</div>
</html:form>

</body>
</html>
