<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/moveDiv.js"></script>
<script language="javascript">
function nextDo(){
	var url = "/xgxt/"
	var sessionForward = $("sessionForward").value;
	
	if(sessionForward == "1"){
		url+="index.do";
	}
	
	parent.document.location = url;
}

function timeDown(){
	var time = eval($('time').value);
	
	if(time>0){
		temp = time-1;
		$('time').value = temp;
		$('span_time').innerHTML = temp;
	}
}
</script>
	<body onload="window.setTimeout('nextDo()',10000);window.setInterval('timeDown()',1000);">
		<html:form action="/xszz_forward">
			<input type="hidden" name="sessionForward" id="sessionForward" value="${sessionForward }">
			<br />
			<br /> 
			<br /> 
			<br /> 
			<br /> 
			<p align="center">
			<img src="/xgxt/pictures/xszz/pic_prompt.gif"></img>
			</p>
			<p align="center">
			<logic:notEqual name="sessionForward" value="2">
				
				<font size="5" color="red"><b >���ʺ��������ط���½�����������ߣ��뾡���޸����룡</font><br/>
				<font size="5" ><span id="span_time">10</span>���
				<a href="#" onclick="nextDo()"><font color="blue">����</font></a>
				��½ҳ�棬�����µ�½��</b></font>
				<input type="hidden" id="time" value="10"/>
			</logic:notEqual>
			</p>
		</html:form>
	</body>
</html>