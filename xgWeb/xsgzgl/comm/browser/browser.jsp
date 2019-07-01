<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/xsgzgl/comm/browser/browser.ini"%>
		<script>
			jQuery(document).ready(function (){
				//添加所需要参数
				var api = frameElement.api, W = api.get('parentDialog');
				var content=jQuery("#content").val();
				//alert("id:"+content);
				var html;
				if(W&&W.jQuery){
					 html=W.jQuery("#"+content).html();
				}
				if(html==""||null==html){
					W= api.opener;
					html=W.jQuery("#"+content).html();
				}
				//alert(html);
				jQuery("#contentDiv").append(html);
			});
		</script>
	</head>
	<body>
		<html:form action="/browser" method="post" styleId="form">
			<input id="param" type="hidden" value="${rs.param}"/>
			<input id="content" type="hidden" value="${rs.content}"/>
			<div id="contentDiv">
			</div>
		</html:form>
		<div id="extendParam">
		</div>
	</body>
</html>