<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:present name="message">
	<script>
		if($("message") && $("message").value != ""){
		
			alert($("message").value);
			
			$("message").value = "";
			$("doType").value = "";
			if(window.dialogArguments){
				dialogArgumentsQueryChick();
				window.close();
			}
		}
	</script>
</logic:present>