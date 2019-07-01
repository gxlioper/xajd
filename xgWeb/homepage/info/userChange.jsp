<%@ page language="java" contentType="text/html; charset=GBK"%>
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

	 	jQuery("span[name=yhjs]").css("display","none");

	 	if(isFdy=="true"){
	 		jQuery('#div_isFdy').css("display","");
	 		jsCou++;
		}

	 	if(isBzr=="true"){
	 		jQuery('#div_isBzr').css("display","");
	 		jsCou++;
		}

	 	if(isFdy=="true" && isBzr=="true"){
		 	jQuery("#div_isJd").css("display","");
		 	jsCou++;
		}

	 	if(sfjryx=="true" && userType=="xy"){
		 	jQuery("#div_isXy").css("display","");
		 	jsCou++;
		}

	 	if(sfjryx=="true" && userType=="sy"){
		 	jQuery("#div_isSy").css("display","");
		 	jsCou++;
		}
	 	
	 	if(sfjryx=="true" && (userType=="xx" || userType=="admin")){
		 	jQuery("#div_isXx").css("display","");
		 	jsCou++;
		}
		
	 	//衢州学院  用户权限由大到小  通用
		//if(jQuery("#xxdm").val() == '11488'){
			var qhlx = jQuery("#qhlx").val();
			if (sfjryx=="true" && (userType=="xx" || userType=="admin") && (qhlx == "" || qhlx == "isXx")){
				jQuery("#isXx").css("display","");
				jQuery("#div_isXx").css("display","none");
			}  else if (sfjryx=="true" && userType=="sy" && (qhlx == "" || qhlx == "isSy")){
				jQuery("#isSy").css("display","");
				jQuery("#div_isSy").css("display","none");
			}  else if (sfjryx=="true" && userType=="xy" && (qhlx == "" || qhlx == "isXy")){
				jQuery("#isXy").css("display","");
				jQuery("#div_isXy").css("display","none");
			}else if (fdyQx=="true" && bzrQx=="true"){
				jQuery("#isJd").css("display","");
				jQuery("#div_isJd").css("display","none");
			} else if(fdyQx=="true"){
				jQuery("#isFdy").css("display","");
				jQuery('#div_isFdy').css("display","none");
			} else if(bzrQx=="true"){
				jQuery("#isBzr").css("display","");
				jQuery('#div_isBzr').css("display","none");
			} 			
		//} else {
		//	if (fdyQx=="true" && bzrQx=="true"){
		//		jQuery("#isJd").css("display","");
		//		jQuery("#div_isJd").css("display","none");
		//	} else if(fdyQx=="true"){
		//		jQuery("#isFdy").css("display","");
		//		jQuery('#div_isFdy').css("display","none");
		//	} else if(bzrQx=="true"){
		//		jQuery("#isBzr").css("display","");
		//		jQuery('#div_isBzr').css("display","none");
		//	} else if (userType=="xy"){
		//		jQuery("#isXy").css("display","");
		//		jQuery("#div_isXy").css("display","none");
		//	} else {
		//		jQuery("#isXx").css("display","");
		//		jQuery("#div_isXx").css("display","none");
		//	}			
		//}

		if (jsCou <= 1){
			jQuery("#qxqh").css("display","none");
		}
	})
</script>
<!--版本选择-->
<div class="versions"  
	onmouseover="javascript:document.getElementById('versions').style.display='block'"
	onmouseout="javascript:document.getElementById('versions').style.display='none'">
	<a class="main_versions_btn" id="qxqh" href="#" >
		<span id="isFdy" style="display:none;" name="yhjs">
			辅导员
		</span>
		<span id="isBzr" style="display:none;" name="yhjs">
			班主任
		</span>
		<span id="isJd" style="display:none;" name="yhjs">
			辅导员兼班主任
		</span>
		<span id="isXy" style="display:none;" name="yhjs">
			<bean:message key="lable.xb" />
		</span>
		<span id="isSy" style="display:none;" name="yhjs">
			书院
		</span>
		<span id="isXx" style="display:none;" name="yhjs">
			学校
		</span>
	</a>
	<div class="versionslist" id="versions" style="display:none;width:100px;">
		<%--当前是辅导员用户--%>
		<div  id="div_isXx" style="display:none;">
			<a href="#" title="" class="passw"
				onclick="getUrls('isXx');">学校</a>
		</div>
		<div  id="div_isSy" style="display:none;">
			<a href="#" title="" class="passw"
				onclick="getUrls('isSy');">书院</a>
		</div>
		<div  id="div_isXy" style="display:none;">
			<a href="#" title="" class="passw"
				onclick="getUrls('isXy');"><bean:message key="lable.xb" /></a>
		</div>
		<div  id="div_isJd" style="display:none;">
			<a href="#" title="" class="passw"
				onclick="getUrls('isJd');">辅导员兼班主任</a>
		</div>
		<div  id="div_isFdy" style="display:none;">
			<a href="#" title="" class="passw"
				onclick="getUrls('isFdy');">辅导员</a>
		</div>
		<div  id="div_isBzr" style="display:none;">
			<a href="#" title="" class="passw"
				onclick="getUrls('isBzr');">班主任</a>
		</div>
	</div>
</div>
