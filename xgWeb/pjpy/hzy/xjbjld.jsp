<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/webPrint.js"></script>
	<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441" codebase="images/webprint.cab" viewasext> </object>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="js/bbld.js"></script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
  <logic:present name="pks">
<script>
window.onload = function(){
	Print(true);
	var kshs = '<bean:write name="pks"/>';
	if(kshs == ''){
		BatAlert.MyAlert("��ӡ����!","",function(){
			Close();
		});
		return false;
	}else{
		var tempArray = kshs.split(',');
		ksh=tempArray[0];
		tempArray.splice(0,1);
		window.location.href = 'hzzyxjbjld.do?pkValue='+ksh+'&pks='+tempArray.join(',');
	}
}
</script>
</logic:present>
    <html:form action="/pjpyhzzywh">
      <div align="center" style="font-size:18px;font:'����' ">${title }</div>
<br/>

<table width="100%" class="printstyle">
  <tr>
    <th height="30" width="20%" colspan="2" scope="col">�༶</th>
    <td height="30" width="30%" colspan="2" scope="col" align="center">&nbsp;${bjmc }</td>
    <th height="30" width="20%" colspan="2" scope="col">�೤</th>
    <td height="30" width="30%" colspan="2" scope="col" align="center">&nbsp;${bzxm }</td>
    
  </tr>
  <tr>
    <th height="30" colspan="2" scope="col">ѧ������</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;${xsrs }</td>
    <th height="30" colspan="2" scope="col">������</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;${bzr }</td>
  </tr>
  <tr>
    <th width="10%" height="121" rowspan="" scope="row">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
	<p>��</p>
    <p>Ҫ</p>
    <p>��</p>
    <p>��</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p></th>
    <th colspan="7"><p align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${zysj }</p></th>
  </tr>
  <tr>
    <th scope="row" width="10%" colspan="">
    <p>&nbsp;</p>
    <p>(ϵ)</p>
    <p>Ժ</p>
    <p>��</p>
    <p>��</p>
    <p>&nbsp;</p>
    </th>
    <th colspan="7" style="" >
   
    <p align="left" ><br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${xyyj }</p>
    
    <div align="right">ǩ��(����)��${xyqm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
    <br>
    <p align="right"> &nbsp;${xyshyear } &nbsp;��&nbsp; ${xyshmon } &nbsp;��&nbsp; ${xyshdate } &nbsp;�� </p></div></th>
    
   </tr>
   <tr> 
    <th scope="row" width="10%" colspan="">
    <p>&nbsp;</p>
    <p>ѧ</p>
    <p>Ժ</p>
    <p>��</p>
    <p>��</p>
   <p>&nbsp;</p>
    </th>
    <th colspan="7" scope="row" style="">
    
    <p align="left"><br/><br/><br/>
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${xxyj }</p>
    <div align="right">
    (����) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
   <p align="right"> ${xxshyear } &nbsp;��&nbsp; ${xxshmon } &nbsp;��&nbsp; ${xxshdate } &nbsp;��</p> </div></th>
  </tr>
</table>

    </html:form>
  </body>
</html:html>
