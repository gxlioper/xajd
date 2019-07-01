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
<html>
<head>
<base target="_self"/>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"　
			　type="text/css" media="all" />
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXljkSjm.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript">
			function enterNow(){
				var sjlsh = document.all["sjlsh"].value;
				var stxh = document.all["stxh"].value;
				getXljkSjm.get_sjmcBy_sjlsh(sjlsh,function(data){
			    	if(data == "心理健康自评试卷"){
			    		refreshForm('xljk_xlcs_zxpc.do?act=zxpc&doType=getstOneByOne_xljk&sjlsh='+sjlsh+'&stxh='+ stxh);
			    	}else{
			    		refreshForm('xljk_xlcs_zxpc.do?act=zxpc&doType=getstOneByOne&sjlsh='+sjlsh+'&stxh='+ stxh);
			    	}
			    });	
			}
		</script>
<title>在线普测</title>
</head>
<body>
	<html:form action="/xljk_xlcs_zxpc.do">
	<input type="hidden" name="sjlsh" value="<bean:write name="sjlsh" />" id="sjlsh"/>
	<input type="hidden" name="stxh" value="<bean:write name="stxh"  />" id="stxh"/>
	<table align="center">
		<tr>
		    <td align="center">
			    <strong style="font-size:15px;"><bean:write name="sjm" scope="request"/></strong>
		    </td>
		</tr>	
		<tr>
			<td align="center">
				<textarea rows="10" cols="60" readonly="readonly"><bean:write name="sjsm"/></textarea>
			</td>
		</tr>	
	</table>
	<div class="buttontool">
	    <button class="button2" style="width:130px;"  id="search_go"  onclick="enterNow()">
			我已经阅读该指导
		</button>
	</div>
</html:form>	
</body>
</html>