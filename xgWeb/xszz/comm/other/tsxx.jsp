<%@ page language="java" contentType="text/html; charset=GBK"%>
<logic:present name="message">
	<script>
		if($("message") && $("message").value != ""){
		
			alert($("message").value);
			
			$("message").value = "";
			$("doType").value = "";

			if(opener){
				opener.document.getElementById("search_go").click();
	      		window.close();
			}					
			if(window.dialogArguments){
				if(window.dialogArguments.document.getElementById("search_go")){
					dialogArgumentsQueryChick();
				}
				window.close();
			}
		}
	</script>
</logic:present>
