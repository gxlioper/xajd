<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<% String usezfca = ZfssoConfig.usezfca; %>
<div class="info">
	<div class="welcome">
		<!-- �û�������� -->
		<div class="tool">
			<!-- ����¼� onclick="showhid('downmenu');"-->
			<a class="tool_btn" 
<%--			href="#" --%>
			>${userNameReal }</a>
			<!-- ��ҵ <div class="downmenu" id="downmenu" style="display:none;">
				<a href="#" class="passw">�޸�����</a><a href="#">��������</a>
			</div> --> 
		</div>
		<!-- �û�������� end-->
		
		<!-- �ʺ�js -->
		<span>	
			��ӭ����
		</span>
		<!-- �ʺ�js end-->

		<!-- <a href="#" class="news">[����Ϣ(0)]</a> ��ҵ��-->
		<% if(null !=usezfca && "1".equals(usezfca)){ %>
		<a href="javascript:window.close();" title="�˳�"
			class="sign_out"></a>
		<%}else{%>
		<a href="#" title="ע��" class="logout" 
			onclick="logout();"></a>
		<%}%>
	</div>
</div>