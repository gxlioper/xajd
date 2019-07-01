<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
<script language="javascript">

</script>
<style>
html, body {
        height: 100%;
        margin: 0;
        padding: 0;
}

#returnBackUrll{
	width:50px !importent;
}
</style>
	<body onload="">
		<html:form action="/comm_bbdmpz">
			<div class="djb_list_nav">
				<h4></h4>
				<a href="${bbdmModel.thlj}" id="returnBackUrll">∑µªÿ</a>
			</div>
			<div align="center">
			<div class="page_prompt" >
			<div class="page_promptcon">
			  <h3>Œ¬‹∞Ã· æ£∫</h3>
			  <p>${yhInfo }</p>
			</div>
			<p>&nbsp;</p>
			</div>
			</div>
		</html:form>
	</body>
</html>