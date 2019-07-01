<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
			<!--[if IE 6]> 
			<script src="js/ie6comm.js"></script> 
			<script> 
			DD_belatedPNG.fix('img'); 
			</script> 
			<![endif]-->
	</head>
	<body class="login_bg">
		<a id="txt" href="login.jsp" target="_top" style="display:none">s</a>
		<html:form action="/jyweb">
			<div class="login_main">
				<div class="login_logo">
					<h2>
						<img src="<%=stylePath %>logo/logo_school.png" />
					</h2>
					<h3>
						<img src="<%=stylePath %>logo/logo_xg.png" />
					</h3>
				</div>
				<div class="login_left">
					<img class="login_pic" src="<%=stylePath %>logo/login_pic.png" />
				</div>
				<div class="login_right">
					<div class="login">
						<div class="user">
							<label for="">
								�û�����
							</label>
							<input name="userName" id="userName" type="text" class="text_nor"
								maxlength="20" style="width:120px" />
							<input type="hidden" name="userType" value="admin" id="userType" />
						</div>
						<div class="passw">
							<label for="">
								��&nbsp;&nbsp;&nbsp;�룺
							</label>
							<input name="password" id="password" type="password"
								class="text_nor" maxlength="20" style="width:120px" />
						</div>
						<div class="yzm">
							<label>
								��֤�룺
							</label>
							<input name="yzm" type="text" class="text_nor"
								style="width:45px;" maxlength="4"
								onkeypress="if(event.keyCode==13)checkLogin();" />
							<img src="yzm.jsp" border="0" align="absmiddle" width="70px"
								height="19px" />
						</div>
						<div class="btn">
							<button class="btn_dl" onclick="checkLogin()"></button>
							<button class="btn_cz" type="reset"></button>
						</div>
						<div class="login_notice">
							<h3>
								������ʾ��
							</h3>
							<p>
								Ϊ�������ʺŰ�ȫ���뼰ʱ�޸����ĳ�ʼ���롣
							</p>
						</div>
					</div>
				</div>
				<div class="login_copyright">
					<span>&copy;1999-2010 <a href="http://www.zfsoft.com"
						target="_blank">������������ɷ����޹�˾</a> <span>��Ȩ����</span>&nbsp;&nbsp;��ϵ�绰��0571-89902828</span>
				</div>
			</div>
			<div class="errors" style="display: none">
				<logic:present name="message">
					<script>
						alert("${message}");
					</script>
				</logic:present>
			</div>
		</html:form>
	</body>
</html>



