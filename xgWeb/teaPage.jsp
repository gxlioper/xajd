<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.comm.homepage.HomePageService"/>
<jsp:directive.page import="java.util.*"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<%@ include file="/homepage/other/head.jsp"%>
	<script type="text/javascript" src="js/prompt_browser.js"></script>
	<script type="text/javascript" src="xtwh/wdyy/js/wdyy.js"></script>
	<script type="text/javascript" src="xtwh/wdyy/js/zf_dialog_1.0.js"></script>
	<script type="text/javascript" src="xtwh/wdyy/js/jquery-ui.min.js"></script>
	<script type='text/javascript'>
        if(!(browser.versions.webKit||browser.versions.gecko)){
            window.location.href = "message/prompt_browser.jsp";
        }

		jQuery(function(){
				//下载专区
				jQuery.post("<%=request.getContextPath()%>/xtwhSysz.do?method=loadFilesInfo",{},function(data){
					var maxLen = (data.length > 5) ? 5 : data.length;
			 		for (var i = 0 ; i < maxLen; i++){
				 		var html = "";
			 			if(data[i]["filepath"]){
			 				html += "<a class='list-group-item' href='<%=request.getContextPath()%>/czxxDtjsDyxx.do?method=downLoadFile&dir="+data[i]["filepath"]+"'>"+data[i]["filemc"]+"</a>";
			 			}
			 			jQuery("#fileList").append(html);
			 		}
			 	},"json");
				
			//代办事项
			var url = "xtwh_wdgz.do?method=queryWdgz";
		
			jQuery.post(url,{},function(data){
				for (var i = 0 ; i < data.length && i < 10; i++){
					if (!jQuery.isEmptyObject(data[i])){
						var gnmkpathV = data[i]["gnmkpath"];
						var liHtml = "<a class='list-group-item' href='";
							liHtml += gnmkpathV;
							liHtml +="'>【";
							liHtml +=data[i]["gznr"];
							liHtml +="】中，有<font color='red'> "
							liHtml += data[i]["dbs"];
							if(gnmkpathV == "szdw_thjl_zdgzxsk.do"){
								liHtml +=" </font>&nbsp;人需要您关注。";
							}else{
								liHtml +=" </font>&nbsp;人需要您审核。";
							}
							
							liHtml +="</a>";
						
						jQuery("#dbsxUI").append(liHtml);
					}
				}
			},"json");
			});
			function removeAllLiClass(){
				jQuery("#tagslist01").find("li").each(function(){
					jQuery(this).removeClass();
				});
			}
	</script>
	</head>
	<%
		HomePageService homePageService = new HomePageService();
		homePageService.setTeaList(request);
		homePageService.setGncdList(request);
	 %>
	<body id="mainbody"  class="student-worker-page">	
		<html:form action="/xtwhSysz">
		<!-- 隐藏域 -->
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="qhlx" id="qhlx" value="${qhlx}" />
		<input type="hidden" name="urls" id="urls" value="/teaPage.jsp" />
	
				
		<!-- 学校LOGO -->
		<%@ include file="/homepage/info/logo.jsp"%>
           <!-- 学校LOGO end-->
				
		<!-- MENU -->
		<%@ include file="/homepage/info/teaMenu.jsp"%>
		<input type="hidden" name="xstsType" id="xstsType" value="<%=xstsType%>" />
		<!-- MENU END-->
				
		<!-- MAIN -->
		<div class="mainframe" id="mainBody">
			<iframe name="framecon" class="framecon" allowTransparency="true" id="ifm"
					src="teaMainBody.jsp" width="100%" frameborder="0" marginwidth="0"
					marginheight="0" height="532px"
					scrolling="no">
			</iframe>
		</div>
		<!-- MAIN END -->


</html:form>
</body>
<!-- iframe 高度自适应 begin -->
	<script type="text/javascript" defer="defer">

		function reinitIframe(){
			var iframe = document.getElementById("ifm");
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
setSize("985", "1261");
	</script>
	<!-- iframe 高度自适应 end -->
<!-- BOTTOM-->
<%@ include file="/homepage/other/bottom.jsp"%>
</html>
