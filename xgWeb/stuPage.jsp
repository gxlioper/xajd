<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<%@ include file="/homepage/other/head.jsp"%>

	<script type="text/javascript" src="js/prompt_browser.js"></script>
	<!-- ��ͨ������ʾ��Ϣ -->
	<script type="text/javascript" src="js/sysz/tsxx.js"></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="xtwh/wdyy/js/wdyy.js"></script>
	<script type="text/javascript" src="xtwh/wdyy/js/zf_dialog_1.0.js"></script>
    <script type="text/javascript" src="xtwh/wdyy/js/jquery-ui.min.js"></script>
		<%
			HomePageService homePageService = new HomePageService();
			homePageService.setStuznxlist(request);
		 %>
	<script type="text/javascript">

        if(!(browser.versions.webKit||browser.versions.gecko)){
            window.location.href = "message/prompt_browser.jsp";
        }

		jQuery(function(){
			//δ���ʼ�������0ʱ����ʾ�ײ���ʾ��
			if(parseInt('${wdyjs}') > 0){
				alertNotice("<center>����<font color='red'>"+'${wdyjs}'+"</font>����Ϣδ����</center>",5,"��ܰ��ʾ",
						{okVal:'�鿴',ok:function(){ 
					     window.open("znxgl_wdznx.do");
						},cancelVal:'ȡ��',cancel:true,id:"alertNotice"});
											
			}
		 
		})
		function reinitIframe(){
				var iframe = document.getElementById("bodyIfm");
				try{
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight);
					iframe.height =  height;
				}catch (ex){}
			}
			window.setInterval("reinitIframe()", 200);
			
					function setSize(w, h) {    
		var c_iframe = document.getElementById("c_iframe");    
		//c_iframe.src = "http://f.yiban.cn/apps.html#"+w+"|"+h;  
		}  
		setSize("985", "1310");
	</script>
	<body id="mainbody" class="student-worker-page">	
		
		<!-- ������ -->
		<input type="hidden" name="userName" id="userName" value="${userName}" />
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />

			<!-- ѧУLOGO -->
			<%@ include file="/homepage/info/logo.jsp"%>
     			<!-- MENU -->
			<%@ include file="/homepage/info/stuMenu.jsp"%>
			<input type="hidden" name="xstsType" id="xstsType" value="<%=xstsType%>" />
			<!-- MENU -->
			<!-- RIGHT-->
			<div name="framecon" class="mainframe">
				<iframe name="framecon" class="framecon" allowTransparency="true"
					src="stuMainBody.jsp" width="100%" frameborder="0" marginwidth="0"
					marginheight="0" style="min-height: 435px" id="bodyIfm"
					scrolling="no">
				</iframe>
			</div>
			<!-- RIGHT end-->
		    <!-- BOTTOM-->
			
			<%@ include file="/homepage/other/bottom.jsp"%>
			<!-- BOTTOM end-->
			
	</body>
</html>
