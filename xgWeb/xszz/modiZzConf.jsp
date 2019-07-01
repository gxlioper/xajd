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
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript" src="js/calendar.js"></script>
<script language="javascript" src="js/calendar-zh.js"></script>
<script language="javascript" src="js/calendar-setup.js"></script>
<base target="_self"/>
  <body onload="chkModiType();">
  <html:form action="/assisConf" method="post"> 
  <div class="title"> 
    <div class="title_img" id="title_m"> <bean:message key="tips.N050101" /> </div> 
  </div> 
  <fieldset> 
  <legend> <bean:message key="lable.cssz" /> </legend> 
  <table width="98%" class="tbstyle" align="center" id="rsTable"> 
    <thead> 
      <tr> 
        <td align="center" height="25" colspan="2"> <bean:message key="lable.cssz" /> </td> 
      </tr> 
    </thead>  
    <tr> 
      <td align="right" height="25"> 操作对象: <input type="hidden" name="userType" id="userType" value="<bean:write name="userType" scope="session" />" /></td> 
      <td align="left"> <input type="hidden" name="bmdm" id="bmdm" value="<bean:write name="zydm"/>" /><bean:write name="bmmc"/>（共<bean:write name="xyrs"/>人） <input type="hidden" name="xyrs" id="xyrs" value="<bean:write name="xyrs"/>" /></td> 
    </tr> 
    <tr> 
      <td align="right" height="25">  </td> 
      <td align="left"><bean:write name="knsrsxz"/></td> 
    </tr> 
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.xpjjdksq" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsja" id="kssqsja" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsja1','y-mm-dd');" value="<bean:write name="kssja1" />" name="kssqsja1" id="kssqsja1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssja2" />" name="kssqsja2" id="kssqsja2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssja3" />" name="kssqsja3" id="kssqsja3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssja4" />" name="kssqsja4" id="kssqsja4" /> </td> 
    </tr> 
    <tr>
      <td align="right" height="25"> <bean:message key="lable.xpjjdksq" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsja" id="jssqsja" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsja1','y-mm-dd');" value="<bean:write name="jssja1" />" name="jssqsja1" id="jssqsja1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssja2" />" name="jssqsja2" id="jssqsja2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssja3" />" name="jssqsja3" id="jssqsja3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssja4" />" name="jssqsja4" id="jssqsja4" /> </td> 
    </tr> 
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.xfhj" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsjb" id="kssqsjb" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsjb1','y-mm-dd');" value="<bean:write name="kssjb1" />" name="kssqsjb1" id="kssqsjb1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssjb2" />" name="kssqsjb2" id="kssqsjb2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjb3" />" name="kssqsjb3" id="kssqsjb3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjb4" />" name="kssqsjb4" id="kssqsjb4" /> </td> 
    </tr> 
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.xfhj" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsjb" id="jssqsjb" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsjb1','y-mm-dd');" value="<bean:write name="jssjb1" />" name="jssqsjb1" id="jssqsjb1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssjb2" />" name="jssqsjb2" id="jssqsjb2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjb3" />" name="jssqsjb3" id="jssqsjb3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjb4" />" name="jssqsjb4" id="jssqsjb4" /> </td> 
    </tr> 
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.knbzsq" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsjc" id="kssqsjc" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsjc1','y-mm-dd');" value="<bean:write name="kssjc1" />" name="kssqsjc1" id="kssqsjc1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssjc2" />" name="kssqsjc2" id="kssqsjc2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjc3" />" name="kssqsjc3" id="kssqsjc3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjc4" />" name="kssqsjc4" id="kssqsjc4" /> </td> 
    </tr> 
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.knbzsq" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsjc" id="jssqsjc" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsjc1','y-mm-dd');" value="<bean:write name="jssjc1" />" name="jssqsjc1" id="jssqsjc1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssjc2" />" name="jssqsjc2" id="jssqsjc2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjc3" />" name="jssqsjc3" id="jssqsjc3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjc4" />" name="jssqsjc4" id="jssqsjc4" /> </td> 
    </tr> 
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.wszxjsq" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsjd" id="kssqsjd" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsjd1','y-mm-dd');" value="<bean:write name="kssjd1" />" name="kssqsjd1" id="kssqsjd1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssjd2" />" name="kssqsjd2" id="kssqsjd2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjd3" />" name="kssqsjd3" id="kssqsjd3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjd4" />" name="kssqsjd4" id="kssqsjd4" /> </td> 
    </tr>     
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.wszxjsq" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsjd" id="jssqsjd" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsjd1','y-mm-dd');" value="<bean:write name="jssjd1" />" name="jssqsjd1" id="jssqsjd1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssjd2" />" name="jssqsjd2" id="jssqsjd2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjd3" />" name="jssqsjd3" id="jssqsjd3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjd4" />" name="jssqsjd4" id="jssqsjd4" /> </td> 
    </tr>   
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.lyjszxjsq" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsje" id="kssqsje" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsje1','y-mm-dd');" value="<bean:write name="kssje1" />" name="kssqsje1" id="kssqsje1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssje2" />" name="kssqsje2" id="kssqsje2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssje3" />" name="kssqsje3" id="kssqsje3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssje4" />" name="kssqsje4" id="kssqsje4" /> </td> 
    </tr>     
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.lyjszxjsq" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsje" id="jssqsje" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsje1','y-mm-dd');" value="<bean:write name="jssje1" />" name="jssqsje1" id="jssqsje1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssje2" />" name="jssqsje2" id="jssqsje2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssje3" />" name="jssqsje3" id="jssqsje3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssje4" />" name="jssqsje4" id="jssqsje4" /> </td> 
    </tr>    
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.lstdsq" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsjf" id="kssqsjf" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsjf1','y-mm-dd');" value="<bean:write name="kssjf1" />" name="kssqsjf1" id="kssqsjf1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssjf2" />" name="kssqsjf2" id="kssqsjf2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjf3" />" name="kssqsjf3" id="kssqsjf3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjf4" />" name="kssqsjf4" id="kssqsjf4" /> </td> 
    </tr>     
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.lstdsq" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsjf" id="jssqsjf" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsjf1','y-mm-dd');" value="<bean:write name="jssjf1" />" name="jssqsjf1" id="jssqsjf1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssjf2" />" name="jssqsjf2" id="jssqsjf2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjf3" />" name="jssqsjf3" id="jssqsjf3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjf4" />" name="jssqsjf4" id="jssqsjf4" /> </td> 
    </tr>     
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.xsxfjmsq" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsjg" id="kssqsjg" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsjg1','y-mm-dd');" value="<bean:write name="kssjg1" />" name="kssqsjg1" id="kssqsjg1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssjg2" />" name="kssqsjg2" id="kssqsjg2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjg3" />" name="kssqsjg3" id="kssqsjg3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjg4" />" name="kssqsjg4" id="kssqsjg4" /> </td> 
    </tr>     
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.xsxfjmsq" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsjg" id="jssqsjg" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsjg1','y-mm-dd');" value="<bean:write name="jssjg1" />" name="jssqsjg1" id="jssqsjg1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssjg2" />" name="jssqsjg2" id="jssqsjg2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjg3" />" name="jssqsjg3" id="jssqsjg3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjg4" />" name="jssqsjg4" id="jssqsjg4" /> </td> 
    </tr>  
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.gajjsq" /><bean:message key="lable.kssj" />: </td> 
      <td align="left"> <input type="hidden" name="kssqsjh" id="kssqsjh" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('kssqsjh1','y-mm-dd');" value="<bean:write name="kssjh1" />" name="kssqsjh1" id="kssqsjh1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="kssjh2" />" name="kssqsjh2" id="kssqsjh2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjh3" />" name="kssqsjh3" id="kssqsjh3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="kssjh4" />" name="kssqsjh4" id="kssqsjh4" /> </td> 
    </tr>     
    <tr> 
      <td align="right" height="25"> <bean:message key="lable.gajjsq" /><bean:message key="lable.jssj" />: </td> 
      <td align="left"> <input type="hidden" name="jssqsjh" id="jssqsjh" value="" /> 
        <input type="text" readonly style="cursor:hand;width:80px" onclick="return showCalendar('jssqsjh1','y-mm-dd');" value="<bean:write name="jssjh1" />" name="jssqsjh1" id="jssqsjh1" /> -
        <input type="text" onblur="numFormatChk(this,0,24)" style="width:30px" value="<bean:write name="jssjh2" />" name="jssqsjh2" id="jssqsjh2" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjh3" />" name="jssqsjh3" id="jssqsjh3" /> :
        <input type="text" onblur="numFormatChk(this,0,60)" style="width:30px" value="<bean:write name="jssjh4" />" name="jssqsjh4" id="jssqsjh4" /> </td> 
    </tr>
    <thead> 
      <tr> 
        <td height="25" colspan="2" align="center"> <button class="button2" onclick="saveZzConf()"> <bean:message key="lable.save" /> </button></td> 
      </tr> 
    </thead> 
  </table> 
  </fieldset> 
  <logic:present name="ok" scope="request">
    <logic:equal value="ok" name="ok" scope="request">
      <script>alert("保存成功！");window.dialogArguments.location = "/xgxt/zzConfig.do";Close();</script>
    </logic:equal>
    <logic:equal value="no" name="ok" scope="request">
      <script>alert("保存失败，请重试！");</script>
    </logic:equal>
  </logic:present>
  </html:form>
  </body>
</html>
