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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<title>( �� )ѧ����ҽ�ѧ������������</title>
<style>
<!--
.Section1
	{page:Section1;}
-->

</style>
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
<html:form action="/zgdzdxXxwh" method="post">
<div align="center" class=Section1 style='layout-grid:15.6pt'> <h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ѧ������������<bean:write name = "rs" property="xn" />ѧ�꣩</h2> 
	<b>ѧУ��</b><bean:write name = "rs" property="xxmc" />&nbsp;<b><bean:message key="lable.xsgzyxpzxy" />��</b><bean:write name = "rs" property="xymc" />&nbsp;<b>רҵ��</b><bean:write name = "rs" property="zymc" />&nbsp;<b>�༶��</b><bean:write name = "rs" property="bjmc" />
  <table border=1 cellspacing=0 cellpadding=0 align=center
 width=655> 
    <tr> 
      <td width=67 rowspan=4 class="Normal"> <b>&nbsp; </b><b>��</b> <b>��</b> <b>��</b> <b>��</b></td> 
      <td width=84 class="Normal"> ����</td> 
      <td width=96 colspan=4 class="Normal"><bean:write name = "rs" property="xm" />&nbsp;</td> 
      <td width=84 colspan=4 class="Normal"> �Ա�</td> 
      <td width=84 colspan=4 class="Normal"><bean:write name = "rs" property="xb" /> &nbsp;</td> 
      <td width=84 colspan=5 class="Normal"> ��������</td> 
      <td width=52 colspan=3 class="Normal"><bean:write name = "rs" property="csrq" /> &nbsp;</td> 
      <td width=52 colspan=4 class="Normal"> ѧ��</td> 
      <td width=52 colspan=2 class="Normal"> &nbsp;</td> 
    </tr> 
    <tr> 
      <td width=84 class="Normal"> ѧ��</td> 
      <td width=96 colspan=4 class="Normal"><bean:write name = "rs" property="xh" />&nbsp; </td> 
      <td width=84 colspan=4 class="Normal"> ����</td> 
      <td width=84 colspan=4 class="Normal"><bean:write name = "rs" property="mzmc" />&nbsp;</td> 
      <td width=84 colspan=5 class="Normal"> ��ѧʱ��</td> 
      <td width=52 colspan=3 class="Normal"><bean:write name = "rs" property="rxny" />&nbsp;</td> 
      <td width=52 colspan=4 class="Normal"> ��ҵȥ��</td> 
      <td width=52 colspan=2 class="Normal">&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=84 class="Normal"> ���֤��</td> 
      <td width=28 class="Normal" colspan="27"><bean:write name = "rs" property="sfzh" />&nbsp;</td> 
    </tr> 
    <tr> 
      <td width=84 class="Normal"> ������ò</td> 
      <td width=96 colspan=4 class="Normal"><bean:write name = "rs" property="zzmmmc" />&nbsp;</td> 
      <td width=168 colspan=8 class="Normal"> ��ϵ�绰</td> 
      <td width=80 colspan=4 class="Normal"><bean:write name = "rs" property="lxdh" />&nbsp;&nbsp; </td> 
      <td width=80 colspan=6 class="Normal"> ƶ�����϶��ȼ�</td> 
      <td width=80 colspan=4 class="Normal">&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=67 class="Normal"> <b>ѧ</b> <b>ϰ</b> <b>��</b> <b>��</b> <b>��</b></td> 
      <td width=588 colspan=27 class="Normal"> ��ѧ����޿γ���<u><bean:write name = "rs" property="bxnbxkms" />&nbsp;&nbsp; </u>�ţ����У�����<u><bean:write name = "rs" property="bxnbxkyxms" />&nbsp;</u>�ţ�����<u><bean:write name = "rs" property="bxnbxlhxms" />&nbsp;</u>�� ѧ��ѧ�ּ��㣺<u><bean:write name = "rs" property="xnxfjd" />&nbsp; </u>����ѧ�ڣ�<u><bean:write name = "rs" property="sbxqxfjd" />&nbsp; </u>��ѧ�ڣ�<u><bean:write name = "rs" property="xbxqxfjd" />&nbsp; </u>��<br>
�ɼ�������רҵ����<u><bean:write name = "rs" property="cjpm" />&nbsp; </u>������/����������&nbsp;�ۺϲ����ɼ�<u><bean:write name = "rs" property="zhcpcj" />&nbsp; </u>�֣��༶����<u><bean:write name = "rs" property="zhcpcjpm" />&nbsp;</u>������/�������������޴����д���ޡ�������ˮƽ��ע�������������<u><bean:write name = "rs" property="wysp" />&nbsp;</u>�������ˮƽ��<u><bean:write name = "rs" property="jsjsp" />&nbsp; </u></td> 
    </tr> 
    <tr> 
      <td width=67 class="Normal"> <b>��</b> <b>����</b> <b>����</b> <b>��</b></td> 
      <td width=588 colspan=27 class="Normal"> ��Ҫ��� ���У�Ժ������<u><bean:write name = "rs" property="yxjl" />&nbsp;</u>�&nbsp;&nbsp; У������<u><bean:write name = "rs" property="xjjl" />&nbsp;</u>�&nbsp;&nbsp; ʡ�����Ͻ���<u><bean:write name = "rs" property="sjjl" />&nbsp;</u>�� �������ģ�</td> 
    </tr> 
    <tr> 
      <td width=67 class="Normal"> <b>��</b> <b>��</b> <b>��</b> <b>��</b> <b>��</b> <b>ȫ</b> <b>��</b> <b>��</b> <b>ӳ</b> <b>��</b> <b>��</b> <b>��</b> <b>��</b> <b>��</b> <b>��</b> ��</td> 
      <td width=588 colspan=27 class="Normal"> <bean:write name = "rs" property="sqly" /><br />�����ˣ�</td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>��</b> <b>ͥ</b> <b>��</b> <b>��</b></td> 
      <td width=588 class="Normal" colspan=27>&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>��һ</b> <b>ѧ��</b> <b>���</b> <b>����</b> <b>���</b></td> 
      <td width=588 class="Normal" colspan=27>&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>��&nbsp; ��</b> <b>��רҵ��</b> <b>��&nbsp; ��</b> <b>��&nbsp; ��</b></td> 
      <td width=588 class="Normal" colspan=27> &nbsp;�Ƽ��ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;����ְ�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; &nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>ѧ</b> <b>Ժ</b> <b>��</b> <b>��</b></td> 
      <td width=588 class="Normal" colspan=27> ����&nbsp; �£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp;&nbsp; &nbsp;��&nbsp;&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>ѧ</b> <b>У</b> <b>��</b> <b>��</b></td> 
      <td width=588 class="Normal" colspan=27> �����󣬲���_________________��Χ�ڹ�ʾ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>�죬�����飬�ֱ���ͬ���ͬѧ��ѧ���<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </u>��ѧ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����&nbsp; �£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</td> 
    </tr> 
    <tr> 
      <td width=72 class="Normal"> <b>�轱</b> <b>��λ</b> <b>���</b></td> 
      <td width=588 class="Normal" colspan=27> ����&nbsp; �£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��</td> 
    </tr> 
  </table> 
  </div> 
  <br/>
 <div class="noprint" align="center">
 <input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
</div>

</html:form>
</body>
</html>
