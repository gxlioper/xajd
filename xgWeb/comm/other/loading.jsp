<%@ page language="java" contentType="text/html; charset=GBK"%>
<div id="div_tsxx" align="center">
	</br></br></br></br></br></br>
	<img src="<%=stylePath%>images/loading.gif"/>
	</br>
	<p id="p_tsxx"><B>���ݴ����У����Ժ󡣡���</B></p>
	
	<!-- ������ѡ���� -->
	<script language="javascript" defer="defer">
	
		function hiddenNr(){
			if($("div_toolbox")){
				$("div_toolbox").style.display="none";
			}
			if($("div_main_box")){
				$("div_main_box").style.display="none";
			}
			if($("div_tsxx")){
				$("div_tsxx").style.display="";
			}
		}
		
		function showNr(){
			if($("div_toolbox")){
				$("div_toolbox").style.display="";
			}
			if($("div_main_box")){
				$("div_main_box").style.display="";
			}
			$("div_tsxx").style.display="none";
		}
		setTimeout("showNr()",2000);
	</script>
</div>