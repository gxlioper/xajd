<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<body >
	<html:form action="/zjcm_rych">
	<br/>
	 <div align="center" style="font-size:25px;font:'����' "><b>�㽭��ý<bean:message key="lable.xsgzyxpzxy" /><bean:write name="rs" property="xmmc" />����ߵǼǱ�</b></div><br/>
    <div align="center" style="font-size:12px; "> <bean:write name="rs" property="xn" />&nbsp; ѧ��&nbsp; <bean:write name="rs" property="xqmc" />ѧ�� <u><bean:write name="rs" property="xymc" />&nbsp;</u><bean:message key="lable.xsgzyxpzxy" /> <u>&nbsp;<bean:write name="rs" property="zymc" /></u>רҵ <u>&nbsp;<bean:write name="rs" property="bjmc" /></u>&nbsp;��
  <table class="printstyle" > 
    <tr> 
      <td width=60 colspan=2 height="30" > �� ��</td> 
      <td width=84 >&nbsp;<bean:write name="rs" property="xm" /> </td> 
      <td width=60 colspan=2 > �� ��</td> 
      <td width=96 colspan=2 >&nbsp;<bean:write name="rs" property="xb" /> </td> 
      <td width=84 colspan=3 > ��������</td> 
      <td width=84 colspan=3 >&nbsp;<bean:write name="rs" property="csrq" /> </td> 
      <td width=84 > ������ò</td> 
      <td width=84 colspan=2 >&nbsp;<bean:write name="rs" property="zzmmmc" /> </td> 
    </tr> 
    <tr> 
      <td width=60 colspan=2 height="30" > �� ��</td> 
      <td width=84 >&nbsp; <bean:write name="rs" property="mzmc" /></td> 
      <td width=60 colspan=2 > ְ ��</td> 
      <td width=96 colspan=2 >&nbsp; <bean:write name="rs" property="zw" /></td> 
      <td width=84 colspan=3 > ƽ������</td> 
      <td width=84 colspan=3 ><bean:write name="rs" property="pjcj" /></td> 
      <td width=84 > ������</td> 
      <td width=84 colspan=2 >&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=40 rowspan=8  height="30" style="text-align:center"> ��<br>ѧ<br>��<br>ѧ<br>ϰ<br>��<br>��</td> 
      <td width=150 colspan=3 height="30" > �� �� �� ��</td> 
      <td width=47 colspan=2 > �ɼ�</td> 
      <td width=159 colspan=5 > �� �� �� ��</td> 
      <td width=47 > �ɼ�</td> 
      <td width=149 colspan=3 > �� �� �� ��</td> 
      <td width=48 > �ɼ�</td> 
    </tr> 
    <tr> 
      <td width=150 colspan=3 height="30" >&nbsp;<bean:write name="rs" property="kcmc0" /> </td> 
      <td width=47 colspan=2 >&nbsp; <bean:write name="rs" property="cj0" /></td> 
      <td width=159 colspan=5 >&nbsp;<bean:write name="rs" property="kcmc1" /> </td> 
      <td width=47 >&nbsp; <bean:write name="rs" property="cj1" /> </td> 
      <td width=149 colspan=3 >&nbsp; <bean:write name="rs" property="kcmc2" /></td> 
      <td width=48 >&nbsp; <bean:write name="rs" property="cj2" /></td> 
    </tr> 
    <tr> 
      <td width=150 colspan=3 height="30" >&nbsp;<bean:write name="rs" property="kcmc3" /> </td> 
      <td width=47 colspan=2 >&nbsp; <bean:write name="rs" property="cj3" /></td> 
      <td width=159 colspan=5 >&nbsp;<bean:write name="rs" property="kcmc4" /> </td> 
      <td width=47 >&nbsp; <bean:write name="rs" property="cj4" /> </td> 
      <td width=149 colspan=3 >&nbsp; <bean:write name="rs" property="kcmc5" /></td> 
      <td width=48 >&nbsp; <bean:write name="rs" property="cj5" /></td> 
    </tr> 
      <tr> 
      <td width=150 colspan=3 height="30" >&nbsp;<bean:write name="rs" property="kcmc6" /> </td> 
      <td width=47 colspan=2 >&nbsp; <bean:write name="rs" property="cj6" /></td> 
      <td width=159 colspan=5 >&nbsp;<bean:write name="rs" property="kcmc7" /> </td> 
      <td width=47 >&nbsp; <bean:write name="rs" property="cj7" /> </td> 
      <td width=149 colspan=3 >&nbsp; <bean:write name="rs" property="kcmc8" /></td> 
      <td width=48 >&nbsp; <bean:write name="rs" property="cj8" /></td> 
    </tr> 
      <tr> 
      <td width=150 colspan=3 height="30" >&nbsp;<bean:write name="rs" property="kcmc9" /> </td> 
      <td width=47 colspan=2 >&nbsp; <bean:write name="rs" property="cj9" /></td> 
      <td width=159 colspan=5 >&nbsp;<bean:write name="rs" property="kcmc10" /> </td> 
      <td width=47 >&nbsp; <bean:write name="rs" property="cj10" /> </td> 
      <td width=149 colspan=3 >&nbsp; <bean:write name="rs" property="kcmc11" /></td> 
      <td width=48 >&nbsp; <bean:write name="rs" property="cj11" /></td> 
    </tr> 
    <tr> 
      <td width=150 colspan=3 height="30" >&nbsp;<bean:write name="rs" property="kcmc12" /> </td> 
      <td width=47 colspan=2 >&nbsp; <bean:write name="rs" property="cj12" /></td> 
      <td width=159 colspan=5 >&nbsp;<bean:write name="rs" property="kcmc13" /> </td> 
      <td width=47 >&nbsp; <bean:write name="rs" property="cj13" /> </td> 
      <td width=149 colspan=3 >&nbsp; <bean:write name="rs" property="kcmc14" /></td> 
      <td width=48 >&nbsp; <bean:write name="rs" property="cj14" /></td> 
    </tr> 
     <tr> 
      <td width=150 colspan=3 height="30" >&nbsp;<bean:write name="rs" property="kcmc15" /> </td> 
      <td width=47 colspan=2 >&nbsp; <bean:write name="rs" property="cj15" /></td> 
      <td width=159 colspan=5 >&nbsp;<bean:write name="rs" property="kcmc16" /> </td> 
      <td width=47 >&nbsp; <bean:write name="rs" property="cj16" /> </td> 
      <td width=149 colspan=3 >&nbsp; <bean:write name="rs" property="kcmc17" /></td> 
      <td width=48 >&nbsp; <bean:write name="rs" property="cj17" /></td> 
    </tr> 
      <tr> 
      <td width=150 colspan=3 height="30" >&nbsp;<bean:write name="rs" property="kcmc18" /> </td> 
      <td width=47 colspan=2 >&nbsp; <bean:write name="rs" property="cj18" /></td> 
      <td width=159 colspan=5 >&nbsp;<bean:write name="rs" property="kcmc19" /> </td> 
      <td width=47 >&nbsp; <bean:write name="rs" property="cj19" /> </td> 
      <td width=149 colspan=3 >&nbsp; <bean:write name="rs" property="kcmc20" /></td> 
      <td width=48 >&nbsp; <bean:write name="rs" property="cj20" /></td> 
    </tr> 
    <tr> 
      <td width=15 height = "300" > ��Ҫ�¼�</td> 
      <td width=600 colspan=15 >&nbsp; <bean:write name="rs" property="zysj" /></td> 
    </tr> 
    <tr> 
      <td width=40  height = "150" style="text-align:center" > ѧ<br>Ժ<br>��<br>��<br>ί<br>��<br>��<br>��<br>��</td> 
      <td width=276 colspan=7 > <bean:write name="rs" property="xyshyj" /><br>���� �£� ��&nbsp; ��&nbsp; ��</td> 
      <td width=40 style="text-align:center" > ѧ<br>У<br>��<br>��</td> 
      <td width=289 colspan=7 > <bean:write name="rs" property="xxshyj" /><br>���� �£� ��&nbsp; ��&nbsp; ��</td> 
    </tr> 
    <tr height=0> 
      <td width=36 ></td> 
      <td width=24 ></td> 
      <td width=84 ></td> 
      <td width=42 ></td> 
      <td width=18 ></td> 
      <td width=29 ></td> 
      <td width=67 ></td> 
      <td width=12 ></td> 
      <td width=35 ></td> 
      <td width=37 ></td> 
      <td width=8 ></td> 
      <td width=47 ></td> 
      <td width=29 ></td> 
      <td width=84 ></td> 
      <td width=36 ></td> 
      <td width=48 ></td> 
    </tr> 
  </table> 
  ע��1������һʽ�ķݣ�ѧ�����˵���һ�ݣ�<bean:message key="lable.xsgzyxpzxy" />һ�ݣ���ίһ�ݣ�Уѧ����һ�ݣ� 2���øֱ���д���ּ��������<bean:message key="lable.xsgzyxpzxy" />��ѧУ���£��쵼ǩ�ַ���Ч��</div> 
  </html:form>
  </body>
</html>
