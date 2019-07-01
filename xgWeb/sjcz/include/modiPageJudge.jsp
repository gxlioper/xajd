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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312" />
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
<logic:equal value="true" name="isSuccess">
	<script>
		alert('操作成功');
		if (window.dialogArguments) {
			dialogArgumentsQueryChick();
			close();
		}
	</script>
</logic:equal>
<logic:equal value="false" name="isSuccess">
	<script>
		alert('操作失败');
		if (window.dialogArguments) {
			dialogArgumentsQueryChick();
			close();
		}
	</script>
</logic:equal>