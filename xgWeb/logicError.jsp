<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body>
	<html:form action="/errMsg" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ</a>
			</p>
		</div>
	<div id="url" style="display:none"></div>

	<logic:present name="message">
	<script type="text/javascript">
		window.onunload = function(){
			if(window.dialogArguments){
				window.dialogArguments.document.forms[0].action='<bean:write name="url" scope="request" />';//$('url').innerHTML;//xszz.do?method=fkxxList&go=go
				window.dialogArguments.document.forms[0].go.value = "go";
				window.dialogArguments.document.forms[0].submit();
			}
		}
		window.onload = function(){
			alert("<bean:write name="message" scope="request" />");
			//BatAlert.MyAlert("<bean:write name="message" scope="request" />","",function(){
				Close();
			//});
		}
	</script>
	</logic:present>

	</html:form>
</body>
</html>


