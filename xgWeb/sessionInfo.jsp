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
		//url+="index.do";
	}
	
	//20170522 ��Ƕ�����´���ʱ��catch
	try{
		parent.document.location = url;
	}catch(e){
		window.document.location = url;
	}
	
}

function timeDown(){
	var time = eval(jQuery('#time').val());
	if(time>0){
		temp = time-1;
		jQuery('#time').val(temp) ;
		jQuery('#span_time').html(temp);
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
				
				<font size="5"><b>��¼ʱ�������<span id="span_time">10</span>���
				<a href="#" onclick="nextDo()" style="text-decoration:underline;">����</a>
				��¼ҳ�棬�����µ�¼��</b></font>
				<input type="hidden" id="time" value="10"/>
			</logic:notEqual>
			<logic:equal name="sessionForward" value="2">
				<div align="center">
				<font size="5"><b>��¼ʱ���������ѡ�񷵻صĵ�ַ��</b></font><br>
				<font size="5"><b>�粻��ѡ��</b></font>
				<input type="text" name="secs" id="secs" value="10" 
					style="text-align:left;ime-mode:disabled;width:20px;border:0;"/>
				<font size="5"><b>��󽫷���ѧ����¼ҳ�棬���µ�¼��</b></font>			
				<script language="javascript">
				var secs = $("secs").value;
				var wait = secs * 1000;
				for(i = 1; i <= secs; i++) {
					window.setTimeout("setSecond(" + i + ")", i * 1000);
				}
				
				function setSecond(second,value){
					printnr = (wait / 1000) - second;
					$("secs").value=printnr;
				}
				</script>
				</div>
				<div align="center">
				<a href="/xgxt"><font size="3" color="blue"><b>ѧ��</b></font></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/xgxt/index.do"><font size="3" color="blue"><b>��ҵ</b></font></a>
				</div>
			</logic:equal>
			</p>
		</html:form>
	</body>
</html>