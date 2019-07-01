<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<jsp:directive.page import="xgxt.comm.homepage.HomePageService" />
<script type="text/javascript">
	function getUrls(qhlx){
		var url='/xgxt/userTypeChange.do';
		url+="?qhlx="+qhlx;
		document.forms[0].target = "_self";
		document.forms[0].action = url;
		document.forms[0].submit();
	}

	jQuery(function(){
		//用户权限切换
		var isBzr='${isBzr}';
	 	var isFdy='${isFdy}';
	 	var fdyQx="${fdyQx}";
	 	var bzrQx="${bzrQx}";
	 	var sfjryx='${sfjryx}';
	 	var userType='${userType}';
		var jsCou = 0;

	 	if(isFdy=="true"){
	 		jQuery('#li_isFdy').css("display","");
	 		jsCou++;
		}

	 	if(isBzr=="true"){
	 		jQuery('#li_isBzr').css("display","");
	 		jsCou++;
		}

	 	if(isFdy=="true" && isBzr=="true"){
		 	jQuery("#li_isJd").css("display","");
		 	jsCou++;
		}

	 	if(sfjryx=="true" && userType=="xy"){
		 	jQuery("#li_isXy").css("display","");
		 	jsCou++;
		}

	 	if(sfjryx=="true" && userType=="sy"){
		 	jQuery("#li_isSy").css("display","");
		 	jsCou++;
		}
	 	
	 	if(sfjryx=="true" && (userType=="xx" || userType=="admin")){
		 	jQuery("#li_isXx").css("display","");
		 	jsCou++;
		}
		
		var qhlx = jQuery("#qhlx").val();
		console.debug(qhlx);
		if (sfjryx=="true" && (userType=="xx" || userType=="admin") && (qhlx == "" || qhlx == "isXx")){
			jQuery(".yhjs").html(jQuery("#li_isXx a").html());
//			jQuery("#isXx").css("display","");
			jQuery("#li_isXx").css("display","none");
		} else if (sfjryx=="true" && userType=="sy" && (qhlx == "" || qhlx == "isSy")){
			jQuery(".yhjs").html(jQuery("#li_isSy a").html());
//			jQuery("#isSy").css("display","");
			jQuery("#li_isSy").css("display","none");
		}  else if (sfjryx=="true" && userType=="xy" && (qhlx == "" || qhlx == "isXy")){
			jQuery(".yhjs").html(jQuery("#li_isXy a").html());
//			jQuery("#isXy").css("display","");
			jQuery("#li_isXy").css("display","none");
		} else if (fdyQx=="true" && bzrQx=="true"){
			jQuery(".yhjs").html(jQuery("#li_isJd a").html());
//			jQuery("#isJd").css("display","");
			jQuery("#li_isJd").css("display","none");
		} else if(fdyQx=="true"){
			jQuery(".yhjs").html(jQuery("#li_isFdy a").html());
//			jQuery("#isFdy").css("display","");
			jQuery('#li_isFdy').css("display","none");
		} else if(bzrQx=="true"){
			jQuery(".yhjs").html(jQuery("#li_isBzr a").html());
//			jQuery("#isBzr").css("display","");
			jQuery('#li_isBzr').css("display","none");
		}
		

		if (jsCou <= 1){
			jQuery("#qxqh").css("display","none");
		}
	})
</script>
<%
		HomePageService homePageService_new = new HomePageService();
		String userType_new = (String) session.getAttribute("userOnLine");
		if ("student".equalsIgnoreCase(userType_new)) {
			homePageService_new.setStuznxlist(request);
		}else{
			homePageService_new.setTeaList(request);
		}
		 %>
	<nav class="navbar navbar-default ky_header ">
			<div class="container">
				<div class="navbar-header pull-left margin_r15">
					<a class="navbar-brand" href="#" style="padding:5px;"><img style="height:40px;width:150px" src="images/top_logo.png" class="pull-left">
						<h3 class="pull-left">学生工作管理信息系统</h3>
					</a>
				</div>
				<logic:notEqual value="" name="userName" scope="session">
				<ul class="nav navbar-nav pull-right navbar-right nav2">
					<li class="hidden-xs message-icon">
						<a href="znxgl_wdznx.do" title="站内信"><img src="images/message.png"><span class="badge message-number">${wdyjs}</span></a>
					</li>
				
					<li>
						<span class="yhjs" style="display: block;height: 54px;line-height: 58px;color: #fff;padding-left: 16px;margin-right: -10px;"></span>
					</li>
					<li class="navbar_notice">
						
						<a href="#" id="qxqh" class="dropdown-toggle" data-toggle="dropdown" title="身份切换"><img src="images/admin.png"></a>

						<ul class="dropdown-menu">
							<li id="li_isXx" style="display:none;">
								<a  href="#" title="" class="passw" onclick="getUrls('isXx');">学校</a>
							</li>
							<li id="li_isSy" style="display:none;" >
								<a  href="#" title="" class="passw" onclick="getUrls('isSy');">书院</a>
							</li>
							<li id="li_isXy" style="display:none;" >
								<a  href="#" title="" class="passw" onclick="getUrls('isXy');"><bean:message key="lable.xb" /></a>
							</li>
							<li id="li_isJd" style="display:none;">
								<a href="#" title="" class="passw" onclick="getUrls('isJd');">辅导员兼班主任</a>
							</li>
							<li id="li_isFdy" style="display:none;">
								<a  href="#" title="" class="passw" onclick="getUrls('isFdy');">辅导员</a>
							</li>
							<li id="li_isBzr" style="display:none;">
								<a  href="#" title="" class="passw" onclick="getUrls('isBzr');">班主任</a>
							</li>
							
							<li class="divider"></li>
						
						</ul>
					</li>
					<li>
						<a href="javascript:;" class="dropdown-toggle navbar_img" data-toggle="dropdown" title="个人中心">
						<logic:notEqual value="stu" name="userType">
						<img src="/xgxt/teaPic.jsp?zgh=${userName}">
						</logic:notEqual>
						<logic:equal value="stu" name="userType">
						<img src="/xgxt/stuPic.jsp?xh=${userName}">
						</logic:equal>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="javascript:;">${userNameReal }！</a>
							</li>
							<li>
							<a href="#" title="注销" onclick="logout();">注销</a>
							</li>
							<li class="divider"></li>
						</ul>
					</li>
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar_index">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
				</ul>
			</logic:notEqual>
			</div>
		</nav>
