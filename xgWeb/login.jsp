<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<% String usezfca = ZfssoConfig.usezfca; %>
<jsp:directive.page import="xgxt.action.Base" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
        <meta name=��renderer�� content=��webkit��>
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

			//��ʾ������Ϣ
			function showErrMsg(errorMsg){
				var mm = document.getElementById("login_errorMsg");
				mm.style.display="block";
				mm.innerHTML=errorMsg;
			}
	
			//���ش�����Ϣ
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
	
			//��֤�û����ƺ�����
			function checkYhmAndMm(){
				var jqYhm = jQuery("#userName");
				var jqMm = jQuery("#password"); 
				
				var yhm=jqYhm.val();
				var mm=jqMm.val();
				if(yhm != "" && yhm == mm){
					showErrMsg("�û��������벻����ͬ,���޸�!");
					return false;
				}else{
					hideErrMsg();
                    hideWaringMsg();
					return true;
				}
			}
	
			//pwStrength����   ������֤
			//���û��ſ����̻����������ʧȥ����ʱ,���ݲ�ͬ�ļ�����ʾ��ʾ
			function passStrength(password) {
				var jqYhmmdj=jQuery("#yhmmdj");
				if (password == null || password == '') {
					hideErrMsg();
                    hideWaringMsg();
				} else {
					var S_level = checkStrong(password);
					//��������ǿ��
					jqYhmmdj.val(S_level);
					switch (S_level) {
					case 0:
						showErrMsg("����̫��,�뼰ʱ�޸�!");
						strong = false;
						return false;
					case 1:
						showErrMsg("����ǿ��̫��,�뼰ʱ�޸�!");
						strong = false;
						return false;
					default:
						strong = true;
						hideErrMsg();
					}
				}
				
				//��֤�û����Ƿ���ͬ
				if(!checkYhmAndMm()){
					//��������ǿ�� ,�����趨������
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
			 var isCommitted = false;//���Ƿ��Ѿ��ύ��ʶ��Ĭ��Ϊfalse
			function dl() {
				var password = jQuery("#password").val();
				if (jQuery("#userName") == "" || password == "") {
					showErrMsg("�û��������벻��Ϊ�գ�");
					return false;
				} 
				
				if (jQuery("#yzm_t").val() == "" || jQuery("#yzm_t").val().length != 4) {
					showErrMsg("��֤�벻�Ϸ���");
					jQuery("#yzm_t").focus();
					return false;
				} 
				if(isCommitted==false){
                 isCommitted = true;//�ύ���󣬽����Ƿ��Ѿ��ύ��ʶ����Ϊtrue
	             }else{
	                return false;//����false��ô�������ύ
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
				<div class="col-sm-12 logo_1"><img src="images/logo_jw_d.png" style="margin-top:-3px">�������-
				<span class="header-sub-title">ѧ������������Ϣϵͳ</span>
				</div>
			</div>
			<div class="row sl_log_bor4">
			<div class="col-sm-8 hidden-xs sl_log_lf"> <img class="img-responsive" src="images/login_bg.png" width="657" height="463"> </div>
			<div class="col-sm-4 sl_log_rt">
			<html:form action="/chkLogin.do"   styleId="loginForm">
				<!-- ��ֹ������Զ�������� -->
			<input type="text" style="display: none;"/>
			<input type="text" name="password2" id="password2" style="display: none;"/>
			<input type="password" style="display: none;"/>
			<!-- ��ֹ������Զ�������� end -->		
			<input type="hidden" name=yhmmdj id="yhmmdj" />
					<h5>�û���¼</h5>
					<p class="bg-warning sl_warning" id="login_waringMsg" style="display: none;"></p>
					<p class="bg-danger sl_danger" id="login_errorMsg" style="display:none;"><span class="glyphicon glyphicon-minus-sign"></span>�������������˻�����ƥ�䣬���������롣����������˻�����</p>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><img src="images/user.png" width="16" height="16"></div>
							<input name="userName" id="userName" type="text" autocomplete="off"
							  onblur="passStrength(document.getElementById('password').value);" 
							  class="form-control" placeholder="�û���" maxlength="20" />
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><img src="images/pwd.png" width="16" height="16"></div>
							<input name="password" id="password" type="password" autocomplete="off" value=""
							   onkeyup="passStrength(this.value);" class="form-control" placeholder="����" maxlength="20" />
						</div>
					</div>
					<div class="form-group">
						<div class="validate">
						<input name="yzm" type="text" id="yzm_t" class="form-control" placeholder="��֤��" 
							 maxlength="4" id="yzm"/>
						</div>
						<div class="validate-img"> <img class="code-icon" src="yzm.jsp?rand=<%=System.currentTimeMillis()%>" width="92"  height="38"> </div>
					</div>
					<div class="form-group">
							<a href="mmzhgl_mmzh.do?method=checkYh&type=view"  class="checkbox pull-right" target="_blank" >��������?</a>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-primary btn-block login-btn" onclick="dl();">�� ¼</button>
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
			<p>&copy;1999-2017 <img src="images/zf_logo.png">��������ɷ����޹�˾ ��Ȩ����  <label><bean:message key="Version"/></label></p>
		</div>
	</body>
</html>
