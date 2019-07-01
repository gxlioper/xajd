<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<% String usezfca = ZfssoConfig.usezfca; %>
<jsp:directive.page import="xgxt.action.Base" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/checkPassword.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/jsbn.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/prng4.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/rng.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/rsa.js"></script>
		<script type='text/javascript' src="js/jquery/rsa/base64.js"></script>
		<% if(null !=usezfca && "1".equals(usezfca)){ %>
		<script language="javascript">
			window.location.href = "<%=ZfssoConfig.casurl%>/login?service=<%=java.net.URLEncoder.encode("http://"+ZfssoConfig.ywxtservername+"/xgxt/teaPage.jsp", "utf-8")%>";
		</script>
		<%}%>
		<script type="text/javascript">
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
					return true;
				}
			}
	
			//pwStrength函数   用于验证
			//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示提示
			function passStrength(password) {
				var jqYhmmdj=jQuery("#yhmmdj");
				if (password == null || password == '') {
					hideErrMsg();
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
			
			function dl() {
				var password = jQuery("#password").val();
				if (jQuery("#userName") == "" || password == "") {
					showAlert("用户名和密码不能为空！");
					return false;
				} 
				
				if (jQuery("#yzm_t").val() == "" || jQuery("#yzm_t").val().length != 4) {
					showAlert("验证码不合法！");
					jQuery("#yzm_t").focus();
					return false;
				} 
				
				var rsaKey = new RSAKey();
				rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
				var enPassword = hex2b64(rsaKey.encrypt(password));
				jQuery("#password").val(enPassword);
				jQuery("#loginForm").submit();
			}
		</script>
	</head>
	<body class="login_bg"
		onload="document.getElementById('userName').focus();">
		<html:form action="/chkLogin.do"  styleId="loginForm">
			<!-- 防止浏览器自动填充密码 -->
			<input type="text" style="display: none;"/>
			<input type="password" style="display: none;"/>
			<!-- 防止浏览器自动填充密码 end -->
		
			<input type="hidden" name=yhmmdj id="yhmmdj" />
			<div class="login_main">
				<div class="login_logo">
					<h2>
						<img src="<%=stylePath%>logo/logo_school.png" />
					</h2>
					<h3>
						<img src="<%=stylePath%>logo/logo_xg.png" />
					</h3>
				</div>
				<div class="login_left">
					<img class="login_pic" src="<%=stylePath%>logo/login_pic.png" />
				</div>
				<div class="login_right">
					<div class="login">
						<div class="user">
							<label for="">
								用户名：
							</label>
							<input name="userName" id="userName" type="text" autocomplete="off"
								  onblur="passStrength(document.getElementById('password').value);" 
								  class="text_nor" maxlength="20" style="width:120px" />
						</div>
						<div class="passw">
							<label for="">
								密&nbsp;&nbsp;&nbsp;码：
							</label>
							<input name="password" id="password" type="password" autocomplete="off" value=""
								   onkeyup="passStrength(this.value);" class="text_nor" maxlength="20" style="width:120px"/>
						</div>
						<div class="yzm">
							<label>
								验证码：
							</label>
							<input name="yzm" type="text" id="yzm_t" class="text_nor" 
								style="width:45px;" maxlength="4" id="yzm"/>
							<img src="yzm.jsp?rand=<%=System.currentTimeMillis() %>" border="0" align="absmiddle" width="70px"
								height="19px"/>
						</div>
						<div class="btn">
							<button class="btn_dl"  id="btn_dl" type="button" onclick="dl();"></button>
							<a href="mmzhgl_mmzh.do?method=checkYh&type=view"  target="_blank" >忘记密码?</a>
							<button class="btn_cz" type="reset"></button>
						</div>
						<div class="prompt_dl" id="login_errorMsg" style="display: none;">
							
						</div>
						<div class="login_notice">
							<p>
								为了您的帐号安全，请及时修改初始密码。
							</p>
						</div>
					</div>
				</div>
				<!-- 陕西师范首页 S -->
			    <div class="index_demo3" style="width: 891px;">
			      <h3 class="title"><span>友情链接</span></h3>
			      <ul class="piclink">
			        <li><a href="images/xszzgl.jpg" title="学生资助管理" target="_blank"><img src="images/xszzgls.jpg" /></a></li>
			        <li><a href="images/xgdb.jpg" title="学工导报" target="_blank"><img src="images/xgdbs.jpg" /></a></li>
			        <li><a href="images/xljkjy.jpg" title="心理健康教育" target="_blank"><img src="images/xljkjys.jpg" /></a></li>
			      </ul>
			      <ul class="urllink">
			        <li><a href="http://www.xsc.snnu.edu.cn/" target="_blank">学工网</a></li>
			        <li><a href="http://zzzx.snnu.edu.cn/" target="_blank">资助网</a></li>
			        <li><a href="http://www.xsc.snnu.edu.cn/xljk/index.asp" target="_blank">心理健康</a></li>
			      </ul>
			    </div>
				<div class="login_copyright">
					<span>&copy;1999-2017 陕西师范大学学生处 版权所有  地址：西安市西长安街620号  电话：029-85310323</span>
					<br/>
					<span>技术支持：<a href="http://www.zfsoft.com"
						target="_blank" >正方软件股份有限公司</a>&nbsp;&nbsp;联系电话：0571-89902828 </span>
						<a class="ver" href="javascript:void(0);">
							<bean:message key="Version"/>
						</a>
				</div>
				<!-- 陕西师范首页 E -->
			</div>
			<div class="errors" style="display: none">
				<logic:notEmpty name="commanForm" property="errMsg" scope="request">
					<script defer="defer"> 
						showAlert("<bean:write name="commanForm" property="errMsg" scope="request" />");
		        	</script>
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
