<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.zfsoft.zfca.tp.cas.client.ZfssoConfig" %>
<jsp:directive.page import="xgxt.form.User" />
<jsp:directive.page import="xgxt.action.Base" />
<jsp:directive.page import="xgxt.DAO.DAO" />
<jsp:directive.page import="xgxt.comm.homepage.HomePageService"/>
<% String usezfca = ZfssoConfig.usezfca; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/xg_v4.ini"%>
		<jsp:directive.page import="xgxt.action.Base" />
		<jsp:directive.page import="xgxt.DAO.DAO" />
		<jsp:directive.page import="xgxt.action.CommanAction" />
		<jsp:directive.page import="xgxt.xtwh.xtwhOther.XtwhOtherService" />
		<style>
			.navbug {
				position: absolute;
				visibility: inherit;
				top: 0px;
				left: 0px; /* =====FGW2008-03-08加入===== */
				width: 128px;
				height: 400px;
				z-index: -999;
				filter: Alpha(Opacity=0)
			}
			
			.versions .main_versions_btn{
				display:block;
				width:150px;
				height:20px;
				line-height:20px;
				margin-left:5px;
				text-align:left;
				color:#1370D8;
				text-decoration:none;
				background:url(<%=stylePath%>/images/blue/ico_blue.gif) no-repeat 88px -997px;
			}
		</style>
		<script language="javascript" src="js/message.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language=javascript src="/xgxt/dwr/interface/getBaseData.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/getLxckDcls.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script language=javascript
			src="/xgxt/js/webServiceFunction/wsFunction.js"></script>
		<script language="javascript" src="js/main.js"></script>
		
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript">
		function logout(){
			confirmInfo("确定要退出吗？",function(tag){
				if(tag=="ok"){
					window.location='/xgxt/chkLogout.do';
				}else {
					return false;
				}
			});
		}
		
		jQuery(function(){
			var t;
			jQuery(".morediv").hover(
				function(){
					t = window.setTimeout("jQuery('#morelist').show()",500);				
				}
				,
				function(){
					jQuery('#morelist').hide();
					t = window.clearTimeout(t);
				}
			);
		});

	</script>
		
	</head>
	<body class="student-worker-page" onload="singleLogin();" id="mainbody">
		<html:form action="fdyUserChange" method="post">
			<input type="hidden" name="openType" id="openType"	
				value="${openType}" />
			<input type="hidden" name="userName" id="userName"
				value="${userName}" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep}" />
			<input type="hidden" name="fdyQx" id="fdyQx" value="${fdyQx}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType}" />
			<input type="hidden" name="jsName" id="jsName" value="${userOnLine}" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<input type="hidden" name="qhlx" id="qhlx" value="${qhlx}" />
			<input type="hidden" name="isFdyUser" id="isFdyUser"
				value="${isFdyUser}" />
			<input type="hidden" name="showShgcKnsTs" id="showShgcKnsTs"
				value="${showShgcKnsTs }" />
			<input type="hidden" name="userChange" id="userChange" value="${userChange}" />
			<input type="hidden" name="sfjryx" id="sfjryx" value="${sfjryx}" />
			<input type="hidden" name="type" id="type" value="${type }" />
			<%--杭州职业技术<bean:message key="lable.xsgzyxpzxy" />--%>
			<logic:equal value="128720" name="xxdm">
				<input type="hidden" name="fbsj" id="fbsj"
					value="<bean:write name="message" property="fbsj"/>" />
				<input type="hidden" name="fbnr" id="fbnr"
					value="<bean:write name="message" property="fbnr"/>" />
			</logic:equal>


			<div class="mainbody type_mainbody">
				<div class="topframe">
							
				<!-- 学校LOGO -->
				<%@ include file="/homepage/info/logo.jsp"%>
		           <!-- 学校LOGO end-->
				</div>
					<div class="navbar_index" id="navbar_index">
						<div class="container">
							<nav id="menuNav" class="navbar navbar-default navbar-static" role="navigation">
								<ul class="nav navbar-nav" id="nav_ul">
								<%
										String xstsType = null;
										String menuTop = request.getParameter("menuTop");
										String gnmkpath = request.getParameter("gnmkpath");
										String menuLeft = request.getParameter("menuLeft");
										
										User user = new User();
										String newQxwh = user.getUserRolesApply();
										String[] inputValue;
										try {
											String userName = (String) session.getAttribute("userName");
											String userChange = (String) session.getAttribute("userChange");
											String type = (String) session.getAttribute("type");
											String userType = (String) session.getAttribute("userOnLine");
											String xxdm = (String) session.getAttribute("xxdm");
											String sql = "";
											DAO dao = DAO.getInstance();
											if(newQxwh.equalsIgnoreCase("yes")){
											    //这个条件分支好像不用了，好多表都不存在	gavinshow/2018.9.19
												StringBuilder sqlBuilder = new StringBuilder();
												sqlBuilder.append("select gnmkdm,gnmkmc from gnmkdmb_dj a where a.sfqy='是' ");
												sqlBuilder.append("and gnmkdm like 'N__' ");
												sqlBuilder.append("and exists(select 1 from (select distinct a.yhm,b.gnmkdm from ");
												sqlBuilder.append("(select yhm,jsdm from xg_view_xtwh_yhjs where yhm=? and sfqy='是') a, ");
												sqlBuilder.append("xg_xtwh_jscdqxb b where a.jsdm=b.jsdm union select a.yhm,a.gnmkdm from ");
												sqlBuilder.append("xg_xtwh_yhcdqxb a where a.yhm=?) b where substr(b.gnmkdm,0,3) = a.gnmkdm) order by gnmkdm");
												sql=sqlBuilder.toString();
												inputValue = new String []{userName,userName};
											}else{
												if (userType.equalsIgnoreCase("teacher")) {
													sql = "select gnmkdm,gnmkmc from view_yhqx_dj where yhm=? and length(gnmkdm)=3 order by to_number(xssx)";
													CommanAction ca = new CommanAction();
													String[] returnRs = ca.getUsrSQL(sql, userName, xxdm,
														true, Boolean.parseBoolean(session.getAttribute("isFdy").toString()));
													ca = null;
													sql = returnRs[0];
													userName = returnRs[1];
												} else if (userType.equalsIgnoreCase("student")) {
													XtwhOtherService xtwhService = new XtwhOtherService();
													String[] returnMes = xtwhService.getTopMenuForStu(
													Base.xxdm, userName);
													sql = returnMes[0];
													xstsType = returnMes[1];
													userName = "6727";
												}
												inputValue = new String []{userName};
											}
											//================v4版本===================
											// 版本
											String edition = Base.getEdition();
											String mainPage = "default.jsp";

											if ("new".equalsIgnoreCase(edition)) {
										if ("student".equalsIgnoreCase(userType)) {
											mainPage = "stuPage.jsp";
										} else {
											mainPage = "teaPage.jsp";
										}
											}
										//================v4版本===================
										String[] outputValue = { "gnmkdm", "gnmkmc" };
										String menuListTop = dao.getStringToSplit(sql, inputValue,
										outputValue);
										String[] printOut = menuListTop.split("!!SplitSignOne!!");
										out.print(" <li class='dropdown' id=\"li-" + 0 + "\"><a href=\"" + mainPage
												+ "\"'>");
												out.print("首页");
												out.println("</a></li>");
												for (int i = 1; i < printOut.length; i++) {

											String[] gnmkcf = printOut[i].split("!!SplitSignTwo!!");

											//================v4版本===================
											String clooseClass = "";
											if ("new".equalsIgnoreCase(edition)) {
												if (menuTop.equalsIgnoreCase(gnmkcf[1])) {
													//System.out.println(menuTop+"@@"+gnmkcf[1]);
													clooseClass = "dropdown open";
												} else {
													clooseClass = "dropdown";
												}
											}
											//================v4版本===================
											out.print(" <li class='"+clooseClass+"' id=\"li-" + i + "\"'><a href=\"main1.jsp?act=left&menuTop=");
											out.print(gnmkcf[1]);
											out.print("\" title='");
											out.print(gnmkcf[2]);
											out.print("' id='");
											out.print(gnmkcf[1]);
											out.print("' onclick='setTsxx(this.id);try{menuUrl(\""
													+ gnmkcf[1] + "\")}catch(ex){}'>");
											out.print(gnmkcf[2]);
											out.println("</a>");

											out
													.println("<input type='hidden' name='hidGnmk' id='hd"
													+ i + "' value='" + gnmkcf[1] + "'>");

											out.println("</li>");

												}
										} catch (Exception e) {
											e.printStackTrace();
										}
								%>
								<!-- 更多菜单 -->
								<li class="dropdown dropdown-list" id="moreMenuLi">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<img class="menu-icon" src="images/menu_icon.png"></a>
									<ul class="dropdown-menu" id="dropdown-menu">
									</ul>
								</li>
								<!-- 更多菜单 end-->
							</ul>
							</nav>
							</div>
						</div>
					</div>
					<input type="hidden" name="urls" id="urls" value="/main1.jsp?act=left&menuTop=<%=request.getParameter("menuTop")%>" />
					<input type="hidden" name="xstsType" id="xstsType" value="<%=xstsType%>" />
					<input type="hidden" name="gnmkpath" id="gnmkpath" value="<%=gnmkpath%>" />
					<input type="hidden" name="chMenu" id="chMenu" value="<%=menuLeft%>" />
						
				  <div class="container">
					<div class="row">
						<div class="col-md-2 col-sm-2 padding_r0" style="margin-top:15px;z-index: 100;">
							<iframe name="midFrame" style="height:800px; width: 100%;"
								id="midFrame" marginwidth="0" marginheight="0" framespacing="0"
								frameborder="0" scrolling="no"
								src="initMenu.do?act=left&menuTop=<%=request.getParameter("menuTop")%>&chMenu=<%=menuLeft%>&gnmkpath=<%=gnmkpath%>"></iframe>
						</div>
					
						<div class="col-md-10 col-sm-10">
								<iframe id="ifm" name="framecon" class="framecon"
										allowTransparency="true"
										src="test.jsp" width="100%" style="min-height: 800px;border:none;margin-top: 15px;"></iframe>
						</div>
					</div>
				</div>
					
				</div>
			</div>


			<!-- 右下角消息提醒  begin-->
			<script type="text/javascript">
			window.onunload = function () {
				var xxdm = document.getElementById("xxdm").value;
				var userType = document.getElementById("userType").value;
			};
			var showShgcKnsTs = document.getElementById("showShgcKnsTs").value;
			if (showShgcKnsTs == "show") {
				window.onload = shgcKnsShow();
			}
	
			var xstsTs = document.getElementById('xstsType').value;
			if (xstsTs=="1"){
				window.onload = XstsShow();
			}
	
			function changeLogo(str) {
				var xxdm = document.getElementById("xxdm").value;
				if (xxdm == "11417") {
					if (str == "N16") {
						document.getElementById("xsgz").src = "images/system_gygl.gif";
					} else {
						document.getElementById("xsgz").src = "images/system_xsgz.gif";
					}
				}
			}
				
				//浙江传媒<bean:message key="lable.xsgzyxpzxy" />用户违纪处分提醒
			if (document.getElementById("xxdm").value == "11647" && (document.getElementById("userType").value == "xy" || document.getElementById("fdyQx").value == "true")) {
				zjcmwjcfMsg();
			}
				
				//中国地质大学复学提醒
			if (document.getElementById("xxdm").value == "10491" && document.getElementById("userType").value != "stu") {
				zgdzdxFxMsg();
			}
			if (document.getElementById("xxdm").value == "1049701" && document.getElementById("userType").value.trim() == "stu") {
				getXjydInfo.isWjcfJcsj($("userName").value, function (data) {
					if (null != data && "" != data) {
						var msg1 = new class_message("", 200, 120, "\u6d88\u606f\u63d0\u793a\uff1a", "\u60a8\u7684\u7559\u6821\u67e5\u770b\u5373\u5c06\u5230\u671f,\u8bf7\u7533\u8bf7\u89e3\u9664!", "\u67e5\u770b\u8be6\u7ec6", "true", "/xgxt/wjcfLxck.do");
						msg1.rect(null, null, null, screen.height - 50);
						msg1.speed = 60;
						msg1.step = 10;
						msg1.show();
					}
				});
			}
			</script>
			<!-- 右下角消息提醒  end-->

			
		</script>
		<!-- 输出菜单超过一行，将多出的菜单放在更多栏中 end -->
		
		<!-- 内容区iframe高度自适应  begin-->
		<script type="text/javascript">
		
			function framecon(){
			
				var iframe = document.getElementById("ifm");
				var midframe = document.getElementById("midFrame");
				try{
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var mHeight = midframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight,mHeight);
					iframe.height =  height;
				}catch (ex){
				
				}
			}
			function moreClick(){
				document.getElementById("ifm").height=0;
    			document.getElementById("ifm").height=document.getElementById("ifm").contentWindow.document.body.scrollHeight;
			}
			//window.setInterval("framecon()", 200);
		</script>
		<%@ include file="/homepage/other/bottom.jsp"%>
	
		
	</body>
	</html:form>
</html>
