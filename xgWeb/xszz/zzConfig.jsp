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
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="js/commanFunction.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<body> 
<center> 
  <html:form action="/assisConf" method="post"> 
  <div class="title"> 
    <div class="title_img" id="title_m"> <bean:message key="tips.N050101" /> </div> 
  </div> 
  <fieldset> 
  <legend> 参数设置 </legend> 
  <table align="center"class="tbstyle" width="100%" id="rsTable">
  	<thead>
  	  <tr align="center">
  	    <td rowspan="2"><bean:message key="lable.xy" /></td>
  	    <td rowspan="2">困难生<br>人数限制</td>
  	    <td colspan="2"><bean:message key="lable.xpjjdksq" /></td>
  	    <td colspan="2"><bean:message key="lable.xfhj" /></td>
  	    <td colspan="2"><bean:message key="lable.knbzsq" /></td>
  	    <td colspan="2"><bean:message key="lable.wszxjsq" /></td>
  	    <td colspan="2"><bean:message key="lable.lyjszxjsq" /></td>  
  	    <td colspan="2"><bean:message key="lable.lstdsq" /></td>
  	    <td colspan="2"><bean:message key="lable.xsxfjmsq" /></td>	  
  	    <td colspan="2"><bean:message key="lable.gajjsq" /></td>    	      	    
  	  </tr>
  	  <tr align="center">
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td>
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td>
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td>
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td> 
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td>
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td>
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td>
  	    <td><bean:message key="lable.kssj" /></td>
  	    <td><bean:message key="lable.jssj" /></td>    	      	      	    
  	  </tr>
  	</thead>
  	<logic:present name="rs">
  	  <logic:iterate id="r" name="rs">
  	  <tr onclick="rowOnClick(this)" ondblclick="modiAConf()" align="center" style="cursor:hand">
  	    <td nowrap align="left"><bean:write name="r" property="bmmc"/><br>(共<bean:write name="r" property="xyrs"/>人)</td>
  	    <td nowrap align="left"><bean:write name="r" property="knsrsxz"/><html:hidden name="r" property="zydm"/></td>
  	    <td><bean:write name="r" property="kssja1"/><br><bean:write name="r" property="kssja2"/>:<bean:write name="r" property="kssja3"/>:<bean:write name="r" property="kssja4"/></td>
  	    <td><bean:write name="r" property="jssja1"/><br><bean:write name="r" property="jssja2"/>:<bean:write name="r" property="jssja3"/>:<bean:write name="r" property="jssja4"/></td>
  	    <td><bean:write name="r" property="kssjb1"/><br><bean:write name="r" property="kssjb2"/>:<bean:write name="r" property="kssjb3"/>:<bean:write name="r" property="kssjb4"/></td>
  	    <td><bean:write name="r" property="jssjb1"/><br><bean:write name="r" property="jssjb2"/>:<bean:write name="r" property="jssjb3"/>:<bean:write name="r" property="jssjb4"/></td>
  	    <td><bean:write name="r" property="kssjc1"/><br><bean:write name="r" property="kssjc2"/>:<bean:write name="r" property="kssjc3"/>:<bean:write name="r" property="kssjc4"/></td>
  	    <td><bean:write name="r" property="jssjc1"/><br><bean:write name="r" property="jssjc2"/>:<bean:write name="r" property="jssjc3"/>:<bean:write name="r" property="jssjc4"/></td>
  	    <td><bean:write name="r" property="kssjd1"/><br><bean:write name="r" property="kssjd2"/>:<bean:write name="r" property="kssjd3"/>:<bean:write name="r" property="kssjd4"/></td>
  	    <td><bean:write name="r" property="jssjd1"/><br><bean:write name="r" property="jssjd2"/>:<bean:write name="r" property="jssjd3"/>:<bean:write name="r" property="jssjd4"/></td>
  	    <td><bean:write name="r" property="kssje1"/><br><bean:write name="r" property="kssje2"/>:<bean:write name="r" property="kssje3"/>:<bean:write name="r" property="kssje4"/></td>
  	    <td><bean:write name="r" property="jssje1"/><br><bean:write name="r" property="jssje2"/>:<bean:write name="r" property="jssje3"/>:<bean:write name="r" property="jssje4"/></td>
  	    <td><bean:write name="r" property="kssjf1"/><br><bean:write name="r" property="kssjf2"/>:<bean:write name="r" property="kssjf3"/>:<bean:write name="r" property="kssjf4"/></td>
  	    <td><bean:write name="r" property="jssjf1"/><br><bean:write name="r" property="jssjf2"/>:<bean:write name="r" property="jssjf3"/>:<bean:write name="r" property="jssjf4"/></td>
  	    <td><bean:write name="r" property="kssjg1"/><br><bean:write name="r" property="kssjg2"/>:<bean:write name="r" property="kssjg3"/>:<bean:write name="r" property="kssjg4"/></td>
  	    <td><bean:write name="r" property="jssjg1"/><br><bean:write name="r" property="jssjg2"/>:<bean:write name="r" property="jssjg3"/>:<bean:write name="r" property="jssjg4"/></td>
  	    <td><bean:write name="r" property="kssjh1"/><br><bean:write name="r" property="kssjh2"/>:<bean:write name="r" property="kssjh3"/>:<bean:write name="r" property="kssjh4"/></td>
  	    <td><bean:write name="r" property="jssjh1"/><br><bean:write name="r" property="jssjh2"/>:<bean:write name="r" property="jssjh3"/>:<bean:write name="r" property="jssjh4"/></td>
  	  </tr>
  	  </logic:iterate>
  	</logic:present>
  	<logic:present name="rs1">
  	  <logic:iterate id="r" name="rs1">
  	    <thead>
	  	  <tr align="center">
	  	    <td nowrap align="left"><bean:write name="r" property="bmmc"/><br>(共<bean:write name="r" property="xyrs"/>人)</td>
	  	    <td nowrap align="left" id="totRsxz"><bean:write name="r" property="knsrsxz"/></td>
	  	    <td id="maxKsA"><bean:write name="r" property="kssja1"/><br><bean:write name="r" property="kssja2"/>:<bean:write name="r" property="kssja3"/>:<bean:write name="r" property="kssja4"/></td>
	  	    <td id="maxJsA"><bean:write name="r" property="jssja1"/><br><bean:write name="r" property="jssja2"/>:<bean:write name="r" property="jssja3"/>:<bean:write name="r" property="jssja4"/></td>
	  	    <td id="maxKsB"><bean:write name="r" property="kssjb1"/><br><bean:write name="r" property="kssjb2"/>:<bean:write name="r" property="kssjb3"/>:<bean:write name="r" property="kssjb4"/></td>
	  	    <td id="maxJsB"><bean:write name="r" property="jssjb1"/><br><bean:write name="r" property="jssjb2"/>:<bean:write name="r" property="jssjb3"/>:<bean:write name="r" property="jssjb4"/></td>
	  	    <td id="maxKsC"><bean:write name="r" property="kssjc1"/><br><bean:write name="r" property="kssjc2"/>:<bean:write name="r" property="kssjc3"/>:<bean:write name="r" property="kssjc4"/></td>
	  	    <td id="maxJsC"><bean:write name="r" property="jssjc1"/><br><bean:write name="r" property="jssjc2"/>:<bean:write name="r" property="jssjc3"/>:<bean:write name="r" property="jssjc4"/></td>
	  	    <td id="maxKsD"><bean:write name="r" property="kssjd1"/><br><bean:write name="r" property="kssjd2"/>:<bean:write name="r" property="kssjd3"/>:<bean:write name="r" property="kssjd4"/></td>
	  	    <td id="maxJsD"><bean:write name="r" property="jssjd1"/><br><bean:write name="r" property="jssjd2"/>:<bean:write name="r" property="jssjd3"/>:<bean:write name="r" property="jssjd4"/></td>
	  	    <td id="maxKsE"><bean:write name="r" property="kssje1"/><br><bean:write name="r" property="kssje2"/>:<bean:write name="r" property="kssje3"/>:<bean:write name="r" property="kssje4"/></td>
	  	    <td id="maxJsE"><bean:write name="r" property="jssje1"/><br><bean:write name="r" property="jssje2"/>:<bean:write name="r" property="jssje3"/>:<bean:write name="r" property="jssje4"/></td>
	  	    <td id="maxKsF"><bean:write name="r" property="kssjf1"/><br><bean:write name="r" property="kssjf2"/>:<bean:write name="r" property="kssjf3"/>:<bean:write name="r" property="kssjf4"/></td>
	  	    <td id="maxJsF"><bean:write name="r" property="jssjf1"/><br><bean:write name="r" property="jssjf2"/>:<bean:write name="r" property="jssjf3"/>:<bean:write name="r" property="jssjf4"/></td>
	  	    <td id="maxKsG"><bean:write name="r" property="kssjg1"/><br><bean:write name="r" property="kssjg2"/>:<bean:write name="r" property="kssjg3"/>:<bean:write name="r" property="kssjg4"/></td>
	  	    <td id="maxJsG"><bean:write name="r" property="jssjg1"/><br><bean:write name="r" property="jssjg2"/>:<bean:write name="r" property="jssjg3"/>:<bean:write name="r" property="jssjg4"/></td>
	  	    <td id="maxKsG"><bean:write name="r" property="kssjh1"/><br><bean:write name="r" property="kssjh2"/>:<bean:write name="r" property="kssjh3"/>:<bean:write name="r" property="kssjh4"/></td>
	  	    <td id="maxJsG"><bean:write name="r" property="jssjh1"/><br><bean:write name="r" property="jssjh2"/>:<bean:write name="r" property="jssjh3"/>:<bean:write name="r" property="jssjh4"/></td>
	  	  </tr>
	  	</thead>
  	  </logic:iterate>
  	</logic:present>
  </table>  
  <logic:equal value="xy" name="userType" scope="session">
    <script>
      showXyConfInfo();
    </script>
  </logic:equal>
  </fieldset> 
  <br /><br />
  <logic:equal value="yes" name="writeAble" >
  <div class="buttontool" id="btn" style="position: absolute;left:10px;top:100px;bottom:40px" width="100%">
	<button class="button2" onclick="modiAConf()">单个设置</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="button2" onclick="showTopWin('/xgxt/modiZzConf.do',500,370,'a');">批量设置</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<logic:notEqual value="xy" name="userType" scope="session">
	<button class="button2" onclick="viewXyConfig();">查看详情</button>
	&nbsp;&nbsp;&nbsp;&nbsp;
	</logic:notEqual>
	<button class="button2" onclick="setEleDisable('button');showTips('数据处理中```');refreshForm('assisRefreshNum.do');">刷新人数</button>
  </div>
  </logic:equal>
    
  </html:form> 
  
  <script type="text/javascript" src="js/bottomButton.js"></script> 
  <script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
  </script>
</center> 
</body>
</html>
