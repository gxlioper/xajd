<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript">
function uploadZzFile(){
	if(window.opener == undefined){					 							
		var xh = window.dialogArguments.document.forms[0].xh.value;
		var sqsj = window.dialogArguments.document.forms[0].sqsj.value;
		var xmdm = window.dialogArguments.document.forms[0].xmdm.value;
		var realTable = window.dialogArguments.document.forms[0].realTable.value;
	}else{
		var xh = window.opener.document.forms[0].xh.value;
		var sqsj = window.opener.document.forms[0].sqsj.value;
		var xmdm = window.opener.document.forms[0].xmdm.value;
		var realTable = window.opener.document.forms[0].realTable.value;
	}
				
	var url = "/xgxt/commXszz.do?method=fileUpload&doType=save";
		url+="&xh="+xh;
		url+="&sqsj="+sqsj;
		url+="&xmdm="+xmdm;
		url+="&realTable="+realTable;
		
	refreshForm(url);
}
</script>
<html:form action='/uploadPicture.do' method='post' enctype='multipart/form-data'>
	<body onload="">
		<!-- 隐藏域 -->
		<%@ include file="/xszz/hiddenValue.jsp"%>
		<input type="text" id="zd" value="${zd }"/>
		<!-- 隐藏域 end-->
		<fieldset>
			<legend>
				附件上传
			</legend>
			<table class="tbstyle" border="0" align="center" style="width: 100%">		
				<tr style="height: 23px">	
					<td align="right" width="30%">
						选择文件：
					</td>						
					<td align="right">
						<input type="file" name="uploadFile" id="file" value="" />
					</td>				
				</tr>
				<tr>
					<td align="center" colspan="2">
						<button type="button"  class="button2" onclick="uploadZzFile()">
							上传
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"  class="button2" onclick="window.close();return false;">
							取消
						</button>
					</td>
				</tr>
			</table>
		</fieldset>
	</body>
	<logic:present name="message">
		<script>
			if($("message") && $("message").value != ""){
			
				alert($("message").value);
				
				$("message").value = "";
				$("doType").value = "";
				
				var xh = window.dialogArguments.document.forms[0].xh.value;
				var sqsj = window.dialogArguments.document.forms[0].sqsj.value;
				var xmdm = window.dialogArguments.document.forms[0].xmdm.value;
				var realTable = window.dialogArguments.document.forms[0].realTable.value;
				
				var url = window.dialogArguments.document.forms[0].url.value;	
					url+="&xh="+xh;
					url+="&sqsj="+sqsj;
					url+="&xmdm="+xmdm;
					url+="&realTable="+realTable;
					
				window.dialogArguments.document.forms[0].action = url;
				window.dialogArguments.document.forms[0].submit();
				window.close();

			}
		</script>
	</logic:present>
</html:form>

