<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" media="all"/>
		<link rel="stylesheet" type="text/css" href="css/base.css" media="all"/>
		<link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xtwh/mmzh/js/mmzh.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/check.js"></script>
	    <script type="text/javascript" src="js/comm/checkPassword.js"></script>
		<script type="text/javascript">
            jQuery(function(){
                setHeight();
                jQuery(window).resize(function(){
                    setHeight();
                });
            })
            function setHeight() {
                var ww = jQuery(window).height();
                jQuery('.topframe').css({
                    'min-height': ww + 'px'
                });
            }
			 function chgPassWordCheckps() {
				    var newmmObj=document.getElementById("newmm");
				    var newP2Obj=document.getElementById("newP2");
				  
					newmm = newmmObj.value;
					newP2 = newP2Obj.value;
					var hintCheck = document.getElementById("hintCheck");

					//��֤����
					if(!checkPsw(newmm)){
						return false;
					}

					//��֤����
					if(!checkMm(newmmObj)){
						return false;
					}
					
					if(newmm!= newP2){
						alertInfo("�����������벻һ�£�");
				    	return false;
					}

					tj();
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
					var newmm = jQuery('#newmm').val();
					if (newmm == null || newmm == '') {
						//hideDownError();
					} else {
						var S_level = checkStrong(newmm);
						jQuery("#yhmmdj").val(S_level);
						
						switch (S_level) {
						case 0:
							alertInfo("���볤�Ȳ���Ϊ�ղ��Ҳ�С��6λ��!");
							isNotnulll =  false;
							break;
						case 1:
							alertInfo("����ǿ��̫��,���޸�!");
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
					var mm = jQuery('#newmm').val();
					var nmm = jQuery('#newP2').val();
					 if(mm!=nmm){
						 alertInfo('�����������벻һ�£�');
						return false;
					}else{
						return true;
					}
				}

				//��֤�û����ƺ�����
				function checkYhmAndMm(){
					var jqYhm = jQuery("#yhm");
					var jqMm = jQuery("#newmm"); 
					
					var yhm=jqYhm.val();
					var mm=jqMm.val();
					if(yhm == mm){
						alertInfo("�û��������벻����ͬ,���޸�!");
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
			
		</script>
		<style type = "text/css">
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
			.tab .top-title {
				width: 490px;
				margin: 0 auto;
				height: 120px;
				overflow: hidden;
			}
			.tab .top-title li {
				width: 140px;
				float: left;
				min-height: 100px;
				position: relative;
			}
		</style>
	</head>
	<body class="student-worker-page">
		
		<!-- ������ end-->
		<div class="mainbody type_mainbody">	
			<div class="topframe">
				<!-- TOP -->
				<div class="head">	
				
					<!-- ѧУLOGO -->
					<%@ include file="/homepage/info/logo.jsp"%>
		            <!-- ѧУLOGO end-->
		            
				</div>
				<!-- TOP END--><!--
				
				<div class="menu">
					<div class="nav">
						<ul class="ul_find"> 
							<li>
								<a href="javascript:void(0);"
									id="li_page">�����һ�</a>
							</li>

						</ul>
					</div>
				</div>
				
				-->
				<div class="mainframe" style="width:100%; text-align: left;" id="mainBody" >
				<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<font color="red">
						�����볤��Ϊ6~20λ�Ҳ���Ϊ�������ֻ���ͬ�ַ���
					</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
				</div>
				<html:form action="/mmzhgl_mmzh" method="post" styleId="MmZhForm">
					<div class="tab">
					
						<ul class="top-title">
									<li class="infocus ico1">
										<a href="#"><span>�û�����֤</span></a>	
									</li>
							<li class="infocus">
								<a href="#"><span>�һط�ʽ</span></a>
							</li>
									<li class="infocus ico2">
										<a href="#"><span>��֤��Ϣ</span></a>
									</li>
									<li class="infocus ico3">
										<a href="#"><span>�޸�����</span></a>
									</li>
							</ul>
							<div class="tab-con">
								<div class="tab-list">
									<label>�û���</label>
									<div class="tab-text">
										<input  id = "yhm" name ="yhm" value="${yhm}" style="width: 145px;" readonly/>
									</div>
								</div>
								<div class="tab-list">
									<div class="pos" style="z-index:1">
										<label><span class="red">*</span>������</label>
										<div class="tab-text">
											<input type="password" name="newmm" id="newmm" maxlength="20" onkeyup="CreateRatePasswdReq(this);" style="width: 145px;" />
										</div>
										<!-- ***********����ǿ��*********** -->
						         		 <table cellSpacing=0 cellPadding=0 border=0 class="resetCss" style="margin-left: 160px;">
											<tbody>
												<tr>
													<td style="padding:3px 0;">����ǿ�ȣ�</td>
													<td style="color:#808080;font-weight:bold"><font id="passwdRating"></font></td>
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
		            				</div>
		            				
								</div>
								<div class="tab-list">
										
								</div>
								<div class="tab-list">
									<label><span class="red">*</span>�ظ�������</label>
									<div class="tab-text">
										<input name = "cfmm" type="password" id ="newP2" maxlength="20" style="width: 145px;"/>
									</div>
								</div>
								<div class="tab-list">
									<!--<label><span class="red">*</span>Ϊ������</label>-->
									<button type="button" class="" onclick="chgPassWordCheckps();return false;">�޸�</button>
								</div>
							</div>
					</div>
					
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