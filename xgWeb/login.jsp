<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<% String usezfca = ZfssoConfig.usezfca; %>
<jsp:directive.page import="xgxt.action.Base" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
        <meta name=”renderer” content=”webkit”>
		<%@ include file="/syscommon/xg_v4.ini"%>
		<script type="text/javascript" src="js/prompt_browser.js"></script>
		<script type="text/javascript" src="js/comm/checkPassword.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/jsbn.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/prng4.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/rng.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/rsa.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/base64.js"></script>
		<link rel="stylesheet" type="text/css" href="comm/skin/zfstyle/ymPrompt.css" media="all"/>
		
		<% if(null !=usezfca && "1".equals(usezfca)){ %>
		<script language="javascript">
			window.location.href = "<%=ZfssoConfig.casurl%>/login?service=<%=java.net.URLEncoder.encode("http://"+ZfssoConfig.ywxtservername+"/xgxt/teaPage.jsp", "utf-8")%>";
		</script>
		<%}%>
		<script type="text/javascript">

            if(!(browser.versions.webKit||browser.versions.gecko)){
                window.location.href = "message/prompt_browser.jsp?pg=login";
            }

			//显示错误信息
			function showErrMsg(errorMsg){
				var mm = document.getElementById("login_errorMsg");
				mm.style.display="block";
				mm.innerHTML=errorMsg;
			}
	
			//隐藏错误信息
			function hideErrMsg(){
				var mm = document.getElementById("login_errorMsg");
				mm.style.display="none";
				mm.innerHTML="";
			}

			function hideWaringMsg(){
                var mm = document.getElementById("login_waringMsg");
                mm.style.display="none";
                mm.innerHTML="";
			}
	
			//验证用户名称和密码
			function checkYhmAndMm(){
				var jqYhm = jQuery("#userName");
				var jqMm = jQuery("#password"); 
				
				var yhm=jqYhm.val();
				var mm=jqMm.val();
				if(yhm != "" && yhm == mm){
					showErrMsg("用户名和密码不能相同,请修改!");
					return false;
				}else{
					hideErrMsg();
                    hideWaringMsg();
					return true;
				}
			}
	
			//pwStrength函数   用于验证
			//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示提示
			function passStrength(password) {
				var jqYhmmdj=jQuery("#yhmmdj");
				if (password == null || password == '') {
					hideErrMsg();
                    hideWaringMsg();
				} else {
					var S_level = checkStrong(password);
					//设置密码强度
					jqYhmmdj.val(S_level);
					switch (S_level) {
					case 0:
						showErrMsg("密码太短,请及时修改!");
						strong = false;
						return false;
					case 1:
						showErrMsg("密码强度太弱,请及时修改!");
						strong = false;
						return false;
					default:
						strong = true;
						hideErrMsg();
					}
				}
				
				//验证用户名是否相同
				if(!checkYhmAndMm()){
					//设置密码强度 ,定死设定弱密码
					jqYhmmdj.val("1");
				}
			}	
			
			var modulus,exponent;
			
			jQuery(function(){
				jQuery("#yzm_t").bind("keypress",function(e){
					if (e.keyCode == 13){
						dl();
					}
				});
				
				jQuery.ajaxSetup({cache:false});
				
				jQuery.getJSON("getPublicKey.do",function(data){
					modulus = data["modulus"];
					exponent = data["exponent"];
				});
				jQuery("#userName").focus();
			});
			 var isCommitted = false;//表单是否已经提交标识，默认为false
			function dl() {
				var password = jQuery("#password").val();
				if (jQuery("#userName") == "" || password == "") {
					showErrMsg("用户名和密码不能为空！");
					return false;
				} 
				
				if (jQuery("#yzm_t").val() == "" || jQuery("#yzm_t").val().length != 4) {
					showErrMsg("验证码不合法！");
					jQuery("#yzm_t").focus();
					return false;
				} 
				if(isCommitted==false){
                 isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
	             }else{
	                return false;//返回false那么表单将不提交
	             }
				var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
				var enPassword = hex2b64(rsaKey.encrypt(password));
				jQuery("#password").val(enPassword);
				var xxdm =jQuery("#xxdm").val();
				if("10335"==xxdm){
				jQuery("#password2").val(password);
				}
				jQuery("#loginForm").submit();
			}
		</script>
	</head>
	<body style="background:#fafafa;"
		onload="document.getElementById('userName').focus();">
			<div class="container container_1170">
				<div class="row sl_log_top">
				<div class="col-sm-12 logo_1"><img src="images/logo_jw_d.png" style="margin-top:-3px">正方软件-
				<span class="header-sub-title">学生工作管理信息系统</span>
				</div>
			</div>
			<div class="row sl_log_bor4">
			<div class="col-sm-8 hidden-xs sl_log_lf"> <img class="img-responsive" src="images/login_bg.png" width="657" height="463"> </div>
			<div class="col-sm-4 sl_log_rt">
			<html:form action="/chkLogin.do"   styleId="loginForm">
				<!-- 防止浏览器自动填充密码 -->
			<input type="text" style="display: none;"/>
			<input type="text" name="password2" id="password2" style="display: none;"/>
			<input type="password" style="display: none;"/>
			<!-- 防止浏览器自动填充密码 end -->		
			<input type="hidden" name=yhmmdj id="yhmmdj" />
					<h5>用户登录</h5>
					<p class="bg-warning sl_warning" id="login_waringMsg" style="display: none;"></p>
					<p class="bg-danger sl_danger" id="login_errorMsg" style="display:none;"><span class="glyphicon glyphicon-minus-sign"></span>您输入的密码和账户名不匹配，请重新输入。忘记密码或账户名？</p>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><img src="images/user.png" width="16" height="16"></div>
							<input name="userName" id="userName" type="text" autocomplete="off"
							  onblur="passStrength(document.getElementById('password').value);" 
							  class="form-control" placeholder="用户名" maxlength="20" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><img src="images/pwd.png" width="16" height="16"></div>
							<input name="password" id="password" type="password" autocomplete="off" value=""
							   onkeyup="passStrength(this.value);" class="form-control" placeholder="密码" maxlength="20" />
						</div>
					</div>
					<div class="form-group">
						<div class="validate">
						<input name="yzm" type="text" id="yzm_t" class="form-control" placeholder="验证码" 
							 maxlength="4" id="yzm"/>
						</div>
						<div class="validate-img"> <img class="code-icon" src="yzm.jsp?rand=<%=System.currentTimeMillis()%>" width="92"  height="38"> </div>
					</div>
					<div class="form-group">
							<a href="mmzhgl_mmzh.do?method=checkYh&type=view"  class="checkbox pull-right" target="_blank" >忘记密码?</a>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary btn-block login-btn" onclick="dl();">登 录</button>
					</div>
				<div class="errors" style="display: none">
				<logic:notEmpty name="commanForm" property="errMsg" scope="request">
					<script defer="defer"> 
						showErrMsg("<bean:write name="commanForm" property="errMsg" scope="request" />");
		        	</script>
				</logic:notEmpty>
				<logic:notEmpty name="commanForm" property="errMsg2" scope="request">
					<script defer="defer">
                        var mm = document.getElementById("login_waringMsg");
                        mm.style.display="block";
                        mm.innerHTML="<bean:write name="commanForm" property="errMsg2" scope="request" />";
					</script>
				</logic:notEmpty>
			</div>	
				</html:form>
			</div>
		</div>
		<div class="footer text-center login-footer">
			<p>&copy;1999-2017 <img src="images/zf_logo.png">正方软件股份有限公司 版权所有  <label><bean:message key="Version"/></label></p>
		</div>
	</body>
</html>
