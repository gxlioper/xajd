	<%@ page language="java" contentType="text/html; charset=GBK"%>
	<head>
		<%@ include file="/syscommon/xg_v4.ini"%>
	
		<script type="text/javascript">
			jQuery(function(){
				initHomePage();
<%--				sessionForward();--%>
			 });
		 	
<%--		 	function sessionForward(){--%>
<%--				var value = $("userName").value;--%>
<%--				if(value == ""){--%>
<%--					refreshForm("/xgxt/sessionForward.jsp");--%>
<%--				}--%>
<%--			}--%>
<%--		--%>
			function logout(){
				confirmInfo("确定要退出吗？",function(tag){
					if(tag=="ok"){
						window.location='/xgxt/chkLogout.do';
					}else {
						return false;
					}
				});
			}
		
			
			function initHomePage(){
				//更多
				var t;
				jQuery(".morediv").hover(
					function(){
						t = window.setTimeout("jQuery('#morelist').show()",500);				
					}
					,
					function(){
						jQuery('#morelist').hide();
						t = window.clearTimeout(t);
					}
				);
				
			}
			
		</script>
		<style type="text/css">
			.versions .main_versions_btn{
			display:block;
			width:150px;
			height:20px;
			line-height:20px;
			margin-left:5px;
			text-align:left;
			color:#1370D8;
			text-decoration:none;
			background:url(<%=stylePath%>/images/blue/ico_blue.gif) no-repeat 88px -997px;
			}
		</style>
	</head>
	
	<logic:equal value="" name="userName" scope="session">
		<jsp:forward page="sessionForward.jsp"></jsp:forward>
	</logic:equal>
	