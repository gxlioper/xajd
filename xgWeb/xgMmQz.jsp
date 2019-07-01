<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/checkPassword.js"></script>
		<script type="text/javascript">
		
		jQuery(function() {
			viewMenu();
			//��ʼ������
			checkPassStrength();
			 // document.getElementById("wtda").value = '${wtda}';
			 // document.getElementById("wtid").value = '${wtid}';
			  if('${wtda}' != ''){
				  document.getElementById("flag").value = 'true';
			  }else{
				  document.getElementById("flag").value = 'false';
			  }
		});
		
		function logout(){
			confirmInfo("ȷ��Ҫ�˳���",function(tag){
				if(tag=="ok"){
					window.location='/xgxt/chkLogout.do';
				}else {
					return false;
				}
			});
		}

		function checkPsw(s) {
			if (s.length < 6) {
				showAlert('���벻��С��6λ');
				return false; // ����6λ    
			}
			if (/^(\d)\1+$/.test(s) ) {
				showAlert('���벻��Ϊ��ͬ�ַ�');
				return false;  // ȫһ��      
			}
			if (/^([a-z A-Z])\1+$/.test(s) ) {
				showAlert('���벻��Ϊ��ͬ�ַ�');
				return false;  // ȫһ��      
			}
			var str = s.replace(/\d/g, function($0, pos) {return parseInt($0)-pos;});     
			if (/^(\d)\1+$/.test(str)) {
				showAlert('���벻��Ϊ˳����ֵ');
				return false;  // ˳��           
			}
			str = s.replace(/\d/g, function($0, pos) {return parseInt($0)+pos;});     
			if (/^(\d)\1+$/.test(str)) {
				showAlert('���벻��Ϊ˳����ֵ');
				return false;   // ˳��     
			}
			return true;
		}

		function chgPassWordCheckps() {
		    var newP1Obj=document.getElementById("newP1");
		    var newP2Obj=document.getElementById("newP2");
			newP1 = newP1Obj.value;
			newP2 = newP2Obj.value;
			var hintCheck = document.getElementById("hintCheck");

			//��֤����
			if(!checkPsw(newP1)){
				return false;
			}

			//��֤����
			if(!checkMm(newP1Obj)){
				return false;
			}
			
			if(newP1!= newP2){
				showAlert("�����������벻һ�£�");
		    	return false;
			}

			if( jQuery("#wtda").val() == '') {
		    	showAlert("�ܱ��𰸲���Ϊ�գ�");
		    	return false;
	    	}
			
			var url = "xgMmQz.do";
			refreshForm(url);
		}
		
		//����ǰѡ�еĲ˵���ʽͻ�� 
		function viewMenu() {
			jQuery('ul.ul_find > li').removeClass();
			var classbz = jQuery('#classbz').val();
			if (classbz != null && classbz != "") {
				classbz = "li_"+classbz;
				jQuery('#'+classbz).parent().addClass('current');
			} else {
				jQuery('#li_page').parent().addClass('current');
			}
		}
		
		//��֤����ǿ��
		function checkPassStrength(){
			var yhmmdj="${user.yhmmdj}";
			//�û�����ȼ�С�ڵ���1  ,Ҳ����������
			if(parseInt(yhmmdj) <= 1){
				showAlert("����������ڼ�,Ϊ��ϵͳ��ȫ,�����޸�����!");
			}
		}
	
		//��֤����
		function checkMm(obj){
			if(!checkMmStrength(obj)){
				//��֤���󷵻�
				return false;
			}

			if(!checkYhmAndMm()){
				//��֤���󷵻�
				return false;
			}

			if(!checkNmm()){
				//��֤���󷵻�
				return false;
			}
			return true;
			
		}

		//��֤����
		function checkMmStrength(obj){
			var isNotnulll = true;
			var newP1 = jQuery('#newP1').val();
			if (newP1 == null || newP1 == '') {
				//hideDownError();
			} else {
				var S_level = checkStrong(newP1);
				jQuery("#yhmmdj").val(S_level);
				
				switch (S_level) {
				case 0:
					showAlert("���볤�Ȳ���Ϊ�ղ��Ҳ�С��6λ��!");
					isNotnulll =  false;
					break;
				case 1:
					showAlert("����ǿ��̫��,���޸�!");
					isNotnulll =  false;
					break;
				default:
				}
			}
			return isNotnulll;
		}
		
		//��֤�ظ���������
		function checkNmm(){
			var obj = document.getElementById("newP2");
			var mm = jQuery('#newP1').val();
			var nmm = jQuery('#newP2').val();
			 if(mm!=nmm){
				 showAlert('�����������벻һ�£�');
				return false;
			}else{
				return true;
			}
		}

		//��֤�û����ƺ�����
		function checkYhmAndMm(){
			var jqYhm = jQuery("#yhm");
			var jqMm = jQuery("#newP1"); 
			
			var yhm=jqYhm.val();
			var mm=jqMm.val();
			if(yhm == mm){
				showAlert("�û��������벻����ͬ,���޸�!");
				return false;
			}else{
				return true;
			}
		}
		
	  /*****************��������֤ start*****************/
		//����ǿ����֤
		var ratingMsgs = new Array(6);
		var ratingMsgColors = new Array(6);
		var barColors = new Array(6);
		ratingMsgs[0] = "��";
		ratingMsgs[1] = "��";
		ratingMsgs[2] = "��";
		ratingMsgs[3] = "ǿ";
		ratingMsgs[4] = "ǿ";
		ratingMsgs[5] = "δ����"; // ��������޷�����״��
		ratingMsgColors[0] = "#aa0033";
		ratingMsgColors[1] = "#aa0033";
		ratingMsgColors[2] = "#f5ac00";
		// ratingMsgColors[3] = "#6699cc";
		ratingMsgColors[3] = "#093";
		ratingMsgColors[4] = "#093";
		ratingMsgColors[5] = "#676767";
		barColors[0] = "#aa0033";
		barColors[1] = "#aa0033";
		barColors[2] = "#ffcc33";
		// barColors[3] = "#6699cc";
		barColors[3] = "#093";
		barColors[4] = "#093";
		barColors[5] = "#676767";
		var che=0;
		var min_passwd_len = 6; 

		//��������֤
		function CreateRatePasswdReq(pwd) {
			if (!isBrowserCompatible) {
				return;
			}

			// if(!document.getElementById) return false;
			// var pwd = document.getElementById("xkl");
			if (!pwd)
				return false;
			passwd = pwd.value;
			if (passwd.length < min_passwd_len) {
				if (passwd.length > 0) {
					DrawBar(0);
				} else {
					ResetBar();
				}
			} else {
				// �ڳ��ȼ��󣬼��������ɸ��Ӷ�
				rating = checkStrong(passwd);
				che = rating;
				DrawBar(rating);

			}
		}

		function getElement(name) {
			if (document.all) {
				return document.all(name);
			}
			return document.getElementById(name);
		}

		function DrawBar(rating) {
			var posbar = getElement('posBar');
			var negbar = getElement('negBar');
			var passwdRating = getElement('passwdRating');
			var barLength = getElement('passwdBar').width;
			if (rating >= 0 && rating <= 4) { // We successfully got a rating
				if (rating == 0) {
					posbar.style.width = barLength / 4 + "px";
					negbar.style.width = barLength / 4 * (3 - rating) + "px";
				} else {
					posbar.style.width = barLength / 4 * rating + "px";
					negbar.style.width = barLength / 4 * (4 - rating) + "px";
				}
			} else {
				posbar.style.width = "0px";
				negbar.style.width = barLength + "px";
				rating = 5; // Not rated Rating
			}
			posbar.style.background = barColors[rating];
			passwdRating.innerHTML = "<font color='" + ratingMsgColors[rating] + "'>"
					+ ratingMsgs[rating] + "</font>";
		}

		/* Checks Browser Compatibility */
		var agt = navigator.userAgent.toLowerCase();
		var is_op = (agt.indexOf("opera") != -1);
		var is_ie = (agt.indexOf("msie") != -1) && document.all && !is_op;
		var is_mac = (agt.indexOf("mac") != -1);
		var is_gk = (agt.indexOf("gecko") != -1);
		var is_sf = (agt.indexOf("safari") != -1);
		// Resets the password strength bar back to its initial state without any
		// message showing.
		function ResetBar() {
			var posbar = getElement('posBar');
			var negbar = getElement('negBar');
			var passwdRating = getElement('passwdRating');
			var barLength = getElement('passwdBar').width;
			posbar.style.width = "0px";
			negbar.style.width = barLength + "px";
			passwdRating.innerHTML = "";
		}

		function gff(str, pfx) {
			var i = str.indexOf(pfx);
			if (i != -1) {
				var v = parseFloat(str.substring(i + pfx.length));
				if (!isNaN(v)) {
					return v;
				}
			}
			return null;
		}	

		function Compatible() {
			if (is_ie && !is_op && !is_mac) {
				var v = gff(agt, "msie ");
				if (v != null) {
					return (v >= 6.0);
				}
			}
			if (is_gk && !is_sf) {
				var v = gff(agt, "rv:");
				if (v != null) {
					return (v >= 1.4);
				} else {
					v = gff(agt, "galeon/");
					if (v != null) {
						return (v >= 1.3);
					}
				}
			}
			if (is_sf) {
				var v = gff(agt, "applewebkit/");
				if (v != null) {
					return (v >= 124);
				}
			}
			return false;
		}	

		/* We also try to create an xmlhttp object to see if the browser supports it */
		var isBrowserCompatible = Compatible();
		/*****************��������֤ end*****************/
	</script>
	
	<style>
	.resetCss tr{
		border-collapse: collapse;
		border-spacing: 0;
		font:inherit;
		list-style: none;
	}
	
	.resetCss tr td{
		border: 0;
		padding: 0;
		vertical-align: 0px;
		
	}
	</style>
	</head>
	<body>
		<!-- ������ end-->
		<div class="mainbody type_mainbody">	
			<div class="topframe">
				<!-- TOP -->
				<div class="head">	
				
					<!-- ѧУLOGO -->
					<%@ include file="/homepage/info/logo.jsp"%>
		            <!-- ѧУLOGO end-->
		            
					<!-- �û���Ϣ -->
					<%@ include file="/homepage/info/userInfo.jsp"%>
		            <!-- �û���Ϣ end-->
		            
				</div>
				<!-- TOP END-->
				
				<div class="menu">
					<div class="nav">
						<ul class="ul_find"> 
							<li>
								<a href="javascript:void(0);"
									id="li_page">�޸�����</a>
							</li>

						</ul>
					</div>
				</div>
				
				<div class="mainframe" style="width:99%; text-align: left;" id="mainBody" >
				<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<font color="red">
						�����볤��Ϊ6~20λ�Ҳ���Ϊ�������ֻ���ͬ�ַ�
					</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
				</div>
				<html:form action="/chgPass"  method="post">
					<input type="hidden" name="yhm" id="yhm" value="${userName }"/>
					<input type="hidden" name="yhmmdj" id="yhmmdj" />
					<div class="tab">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span>�����޸�</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th align="right" style="width: 23%">
										<span class="red">*</span>ԭ����
									</th>
									<td>
										<input type="password" name="oldP" id="oldP" style="width:154px"/>
									</td>
								</tr>
								<tr>
									<th align="right">
										<span class="red">*</span>������
									</th>
									<td>
										<input type="password" maxlength="20" name="newP1" id="newP1" style="float: left;width:154px;"
										    onkeyup="CreateRatePasswdReq(this);"/>
										<!-- ***********����ǿ��*********** -->
										<table cellSpacing=0 cellPadding=0 border=0 class="resetCss"
											style="margin-left: 10px; float: left;"> 
											<tbody>
												<tr>
													<td style="padding: 3px 0;">
														����ǿ�ȣ�
													</td>
													<td style="color: #808080; font-weight: bold">
														<font id="passwdRating"></font>
													</td>
												</tr>
												<tr>
													<td colSpan=2>
														<table id=passwdBar cellSpacing=0 cellPadding=0 width=160
															bgColor=#ffffff border=0>
															<tbody>
																<tr>
																	<td id=posBar width=0% bgColor=#e0e0e0 height=4></td>
																	<td id=negBar width=100%; bgColor=#e0e0e0 height=4></td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
										<!-- ***********����ǿ��*********** -->
									</td>
								</tr>
								<tr>
									<th align="right">
										<span class="red">*</span>�ظ�������
									</th>
									<td>
										<input type="password" maxlength="20" name="newP2" id="newP2"
											style="width:154px"/>
									</td>
								</tr>
							    <tr>
		      		                <th><span class="red">*</span>�ܱ�����</th>
		      		                <td>
		      			                <html:select   property="wtid" styleId="wtid" style="width:154px;">
									    <html:options collection="mbList" property="wtid" labelProperty="mbwt" />
						                </html:select>
		      		                </td>
		      	                </tr>
		      	                <tr>
		      		                <th><span class="red">*</span>�ܱ���</th>
		      		                <td>
		      			              <input name = "wtda" id = "wtda" maxlength="50" style="width:154px;">
		      			              <input type = "hidden" name = "flag" id = "flag">
		      		                </td>
		      	                </tr>
		       
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="bz">
											"
											<span class="red">*</span>"Ϊ������
										</div>
										<div class="btn">
											<button type="button" class=""
												onclick="chgPassWordCheckps();return false;">
												�޸�
											</button>
										</div>
									</td>

								</tr>
							</tfoot>
						</table>
					</div>
					<logic:notEmpty name="commanForm" property="changed" scope="request">
					<input type="hidden" value="<bean:write name="commanForm" property="changed" scope="request" />" id="msg"/>
					<input type="hidden" value="${userOnLine}" id="userType"/>
					<script>
					jQuery(function(){
						var msg=document.getElementById("msg").value;
						//�������޸ĳɹ������ɹ���ת��teaPage.jsp,��ʧ�ܣ�����������ǰҳ�档
						showAlert(msg,{},{"clkFun":function(){
							var userType = document.getElementById("userType").value;
							if("student" == userType){
								window.location.href="stuPage.jsp";
							}else if("teacher" == userType){
								window.location.href="teaPage.jsp";
							}else{
								window.location.href="default.jsp";
							}
							
		    			}});
					});
					</script>
					</logic:notEmpty>
				</html:form>
			</div>
			
				<!-- MAIN END -->
				<!-- BOTTOM-->
				<%@ include file="/homepage/info/companyInfo.jsp"%>
				<!-- BOTTOM end-->
			</div>
		</div>
		
	</body>
</html>
