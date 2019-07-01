<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
			<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
			<script>
				jQuery(document).ready(function(){
					jQuery("#text_lxdh").bind("blur",function(){
						var text = jQuery(this).val();
						if(text==""||null==text){
							return true;
						}
<%--							if(text.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){--%>
<%--								showAlert("您输入的电话不符合规则!",{},{"clkFun":function(){--%>
<%--									jQuery("#text_lxdh").val("");--%>
<%--									jQuery("#text_lxdh").focus();--%>
<%--								}});--%>
<%--							 return false;--%>
<%--						 }--%>
					});
				});
			</script>
	</head>
	<!-- 队伍维护Div begin -->
			<div id="div_dwwh">
				${html}
			</div>
</html>
