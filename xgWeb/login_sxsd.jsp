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
					return true;
				}
			}
	
			//pwStrength����   ������֤
			//���û��ſ����̻����������ʧȥ����ʱ,���ݲ�ͬ�ļ�����ʾ��ʾ
			function passStrength(password) {
				var jqYhmmdj=jQuery("#yhmmdj");
				if (password == null || password == '') {
					hideErrMsg();
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
			
			function dl() {
				var password = jQuery("#password").val();
				if (jQuery("#userName") == "" || password == "") {
					showAlert("�û��������벻��Ϊ�գ�");
					return false;
				} 
				
				if (jQuery("#yzm_t").val() == "" || jQuery("#yzm_t").val().length != 4) {
					showAlert("��֤�벻�Ϸ���");
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
			<!-- ��ֹ������Զ�������� -->
			<input type="text" style="display: none;"/>
			<input type="password" style="display: none;"/>
			<!-- ��ֹ������Զ�������� end -->
		
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
								�û�����
							</label>
							<input name="userName" id="userName" type="text" autocomplete="off"
								  onblur="passStrength(document.getElementById('password').value);" 
								  class="text_nor" maxlength="20" style="width:120px" />
						</div>
						<div class="passw">
							<label for="">
								��&nbsp;&nbsp;&nbsp;�룺
							</label>
							<input name="password" id="password" type="password" autocomplete="off" value=""
								   onkeyup="passStrength(this.value);" class="text_nor" maxlength="20" style="width:120px"/>
						</div>
						<div class="yzm">
							<label>
								��֤�룺
							</label>
							<input name="yzm" type="text" id="yzm_t" class="text_nor" 
								style="width:45px;" maxlength="4" id="yzm"/>
							<img src="yzm.jsp?rand=<%=System.currentTimeMillis() %>" border="0" align="absmiddle" width="70px"
								height="19px"/>
						</div>
						<div class="btn">
							<button class="btn_dl"  id="btn_dl" type="button" onclick="dl();"></button>
							<a href="mmzhgl_mmzh.do?method=checkYh&type=view"  target="_blank" >��������?</a>
							<button class="btn_cz" type="reset"></button>
						</div>
						<div class="prompt_dl" id="login_errorMsg" style="display: none;">
							
						</div>
						<div class="login_notice">
							<p>
								Ϊ�������ʺŰ�ȫ���뼰ʱ�޸ĳ�ʼ���롣
							</p>
						</div>
					</div>
				</div>
				<!-- ����ʦ����ҳ S -->
			    <div class="index_demo3" style="width: 891px;">
			      <h3 class="title"><span>��������</span></h3>
			      <ul class="piclink">
			        <li><a href="images/xszzgl.jpg" title="ѧ����������" target="_blank"><img src="images/xszzgls.jpg" /></a></li>
			        <li><a href="images/xgdb.jpg" title="ѧ������" target="_blank"><img src="images/xgdbs.jpg" /></a></li>
			        <li><a href="images/xljkjy.jpg" title="����������" target="_blank"><img src="images/xljkjys.jpg" /></a></li>
			      </ul>
			      <ul class="urllink">
			        <li><a href="http://www.xsc.snnu.edu.cn/" target="_blank">ѧ����</a></li>
			        <li><a href="http://zzzx.snnu.edu.cn/" target="_blank">������</a></li>
			        <li><a href="http://www.xsc.snnu.edu.cn/xljk/index.asp" target="_blank">������</a></li>
			      </ul>
			    </div>
				<div class="login_copyright">
					<span>&copy;1999-2017 ����ʦ����ѧѧ���� ��Ȩ����  ��ַ����������������620��  �绰��029-85310323</span>
					<br/>
					<span>����֧�֣�<a href="http://www.zfsoft.com"
						target="_blank" >��������ɷ����޹�˾</a>&nbsp;&nbsp;��ϵ�绰��0571-89902828 </span>
						<a class="ver" href="javascript:void(0);">
							<bean:message key="Version"/>
						</a>
				</div>
				<!-- ����ʦ����ҳ E -->
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
