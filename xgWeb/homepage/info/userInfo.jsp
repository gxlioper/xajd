<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<% String usezfca = ZfssoConfig.usezfca; %>
<div class="info">
	<div class="welcome">
		<!-- 用户控制面板 -->
		<div class="tool">
			<!-- 点击事件 onclick="showhid('downmenu');"-->
			<a class="tool_btn" 
<%--			href="#" --%>
			>${userNameReal }</a>
			<!-- 待业 <div class="downmenu" id="downmenu" style="display:none;">
				<a href="#" class="passw">修改密码</a><a href="#">个人资料</a>
			</div> --> 
		</div>
		<!-- 用户控制面板 end-->
		
		<!-- 问好js -->
		<span>	
			欢迎您！
		</span>
		<!-- 问好js end-->

		<!-- <a href="#" class="news">[新消息(0)]</a> 待业中-->
		<% if(null !=usezfca && "1".equals(usezfca)){ %>
		<a href="javascript:window.close();" title="退出"
			class="sign_out"></a>
		<%}else{%>
		<a href="#" title="注销" class="logout" 
			onclick="logout();"></a>
		<%}%>
	</div>
</div>