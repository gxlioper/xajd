<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/comm/checkPassword.js"></script>
	<script type="text/javascript">
	  function onHint(){
	  	document.getElementById('hintLength').style.display='block';
	  }
	  function offHint(){
	  	document.getElementById('hintLength').style.display='none';
	  }

		
	  function chgPassWordCheckps() {
		    var newP1Obj=document.getElementById("newP1");
		    var newP2Obj=document.getElementById("newP2");
		  
			newP1 = newP1Obj.value;
			newP2 = newP2Obj.value;
			var hintCheck = document.getElementById("hintCheck");

			//验证密码
			if(!checkPsw(newP1)){
				return false;
			}

			//验证密码
			if(!checkMm(newP1Obj)){
				return false;
			}
			
			if(newP1!= newP2){
				alertInfo("两次密码输入不一致！");
		    	return false;
			}
			
			var url = "chgPass.do";
			refreshForm(url);
			/*if (document.forms[0].oldP.value == "") {
				showAlertDivLayer("请输入旧密码！");
				document.forms[0].oldP.value = "";
				document.forms[0].oldP.focus();
			} else {
				
				if (newP1.length < 6) {
					document.forms[0].newP1.value = "";
					document.forms[0].newP1.value = "";
					document.forms[0].newP1.focus();
				} else {
					if (newP1 != newP2) {
						//alert("两次输入的密码不一致！");
						hintCheck.style.display="block";
						document.forms[0].newP2.value = "";
					} else {
						var url = "chgPass.do";
						refreshForm(url);
					}
				}
			}*/
		}


	//验证密码
		function checkMm(obj){
			if(!checkMmStrength(obj)){
				//验证错误返回
				return false;
			}

			if(!checkYhmAndMm()){
				//验证错误返回
				return false;
			}

			if(!checkNmm()){
				//验证错误返回
				return false;
			}
			return true;
			
		}

		//验证密码
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
					alertInfo("密码长度不能为空并且不小于6位！!");
					isNotnulll =  false;
					break;
				case 1:
					alertInfo("密码强度太弱,请修改!");
					isNotnulll =  false;
					break;
				default:
				}
			}
			return isNotnulll;
		}
		
		//验证重复输入密码
		function checkNmm(){
			var obj = document.getElementById("newP2");
			var mm = jQuery('#newP1').val();
			var nmm = jQuery('#newP2').val();
			 if(mm!=nmm){
				 alertInfo('两次密码输入不一致！');
				return false;
			}else{
				return true;
			}
		}

		//验证用户名称和密码
		function checkYhmAndMm(){
			var jqYhm = jQuery("#yhm");
			var jqMm = jQuery("#newP1"); 
			
			var yhm=jqYhm.val();
			var mm=jqMm.val();
			if(yhm == mm){
				alertInfo("用户名和密码不能相同,请修改!");
				return false;
			}else{
				return true;
			}
		}
		
	  /*****************弱密码验证 start*****************/
		//密码强度验证
		var ratingMsgs = new Array(6);
		var ratingMsgColors = new Array(6);
		var barColors = new Array(6);
		ratingMsgs[0] = "弱";
		ratingMsgs[1] = "弱";
		ratingMsgs[2] = "中";
		ratingMsgs[3] = "强";
		ratingMsgs[4] = "强";
		ratingMsgs[5] = "未评级"; // 假如出现无法检测的状况
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

		//弱密码验证
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
				// 在长度检测后，检测密码组成复杂度
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
		/*****************弱密码验证 end*****************/
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
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护 - 密码维护 - 修改密码</a>
			</p>
		</div>
	
		<html:form action="/chgPass" method="post">
		<input type="hidden" name="yhm" id="yhm" value="${userName }"/>
		<input type="hidden" name="yhmmdj" id="yhmmdj"/>
		<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<font color="red">新密码长度为6~20位且不可为连续数字或相同字符</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>		
		<div class="tab">
		  <table width="100%"  border="0" class="formlist">
		    <thead>
		    	<tr>
		        	<th colspan="4"><span>密码修改</span></th>
		        </tr>
		    </thead>
		    <tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <button type="button" name="保存" onclick="chgPassWordCheckps()">保 存</button>
		            <button  name="重置" type="reset">重 置</button>
		          </div></td>
		      </tr>
		    </tfoot>
		    <tbody>
		      <tr>
		        <th width="20%"><span class="red">*</span>原密码</th>
		        <td width="80%">
		        	<input type="password" name="oldP" id="oldP" style="width: 145px;"/>
		        </td>
		      </tr>
		      <tr>
		        <th><span class="red">*</span>新密码</th>
		        <td>
		        	
		        	<div class="pos" style="z-index:1">
		       			<input type="password" name="newP1" id="newP1" maxlength="20"
		       				    onkeyup="CreateRatePasswdReq(this);"
	       					   style="float: left; width: 145px;"
		       			 />
		       			 <!-- ***********密码强度*********** -->
		         		 <table cellSpacing=0 cellPadding=0 border=0 class="resetCss" style="margin-left: 10px; float: left;">
							<tbody>
								<tr>
									<td style="padding:3px 0;">密码强度：</td>
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
						<!-- ***********密码强度*********** -->
		       			<%--<div id="msg_div" class="hide" style="z-index:2">
			                <p>密码长度为6~16位，至少包含数字、特殊符号、英文字母(大、小写)中的两类!</p>
			            </div>
		            --%>
		            </div>
		        	
		        </td>
		      </tr>
		      <tr>
		        <th><span class="red">*</span>重复新密码</th>
		        <td>
		        	<input type="password" name="newP2" id="newP2" maxlength="20" style="width: 145px;" /><div id="hintCheck" style="display:none;color: red;"></div>
		        </td>
		      </tr>
		    </tbody>
		  </table>
		  </div>
			<logic:notEmpty name="commanForm" property="changed" scope="request">
			<input type="hidden" value="<bean:write name="commanForm" property="changed" scope="request" />" id="msg"/>
			<script>
			showAlertDivLayer(document.getElementById("msg").value );				
			</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
