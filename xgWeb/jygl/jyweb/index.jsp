<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/comman.js"></script>
		<script type="text/javascript">
			function setLayOut(){
				jyweb.getLayOut(function(data){
					for(var i=0 ;i<data.length;i++){
						///备用DIV换class
						$(data[i].mkbh).className = $(data[i].mkmc).className;
						///备用DIV换内容
						$(data[i].mkbh).innerHTML = $(data[i].mkmc).innerHTML;
						///删除原来的DIV
						$(data[i].mkmc).parentNode.removeChild($(data[i].mkmc));
					}
				});
			}
			jQuery(function(){
				jQuery('li',jQuery('#xnzpTitle')).eq(0).trigger('mouseover');
			})
		</script>
		<script type="text/javascript" src="js/jygl/jobCalendar.js"></script>
	</head>
	<body onload="loginClass('${userType }');">
		<html:form action="/jyweb" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${jyweb_userType }" />
			<input type="hidden" name="realName" id="realName"
				value="${jyweb_realName }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" id="contextPath"
				value="<%=request.getRealPath(".").substring(0, 1)%>" />


			<div class="index_mainbody">
				<div class="index_topframe">
					<!-- logo 和 菜单 -->
					<jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
				</div>
				<div class="index_mainframe">
					<div class="index_leftframe">
						<div id="1"></div>
						<div id="2"></div>
						<div id="3"></div>
						<logic:present name="jyweb_userName" scope="session">
							<div class="index_UserInfo" id="login">
								<h3>
									<span><bean:message key="jyweb.index.yhxx"
											bundle="JYWEB_RESOURCES" /> </span>
								</h3>
								<div class="con">
									<p style="width: 80px;">
										<img src="images/pic_face_01.gif" />
									</p>
									<p>
										欢迎您：
										<font color="#025BBB"><br />${jyweb_realName }</font>
										<br />
									</p>
									<div class="btn">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=manager')">
											个人中心
										</button>
										<button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">
											注 销
										</button>
									</div>
								</div>
							</div>
						</logic:present>
						<logic:notPresent name="jyweb_userName" scope="session">
							<div id="login" class="index_login">
								<div class="comp_01">
									<ul>
										<li id="stu">
											<a href="#" onclick="loginClass('stu')">学生登录</a>
										</li>
										<li id="xy">
											<a href="#" onclick="loginClass('xy')">教师登录</a>
										</li>
										<li id="dw">
											<a href="#" onclick="loginClass('dw')">企业登录</a>
										</li>

									</ul>
								</div>

								<div class="login">
									<div class="user">
										<label for="">
											用户名：
										</label>
										<html:text property="userName" styleClass="text_nor"
											styleId="userName" />
									</div>
									<div class="passw">
										<label for="">
											密&nbsp;&nbsp;&nbsp;码：
										</label>
										<html:password property="password" styleClass="text_nor"
											styleId="password"
											onkeypress="if(event.keyCode==13)checkLogin();" />
									</div>
									<div class="btn">
										<button class="btn_dl" id="btn_dl" onclick="checkLogin();">
											<span>登 录</span>
										</button>
										<button class="btn_zc" id="btn_zc"
											onclick="refreshForm('/xgxt/register.do');">
											<span>注 册</span>
										</button>
									</div>
									<a href="adminLogin.do" class="glydl">管理员登录</a>
								</div>
							</div>
						</logic:notPresent>
						<div id="search" class="index_news01" style="height: 200px">
							<span id="job"></span>
							<script type="text/javascript" defer="defer">
								new JobCalendar().show($('job'));	
							</script>

						</div>

						<div id="dwList" class="index_demo1">
							<h3>
								<span><bean:message key="jyweb.index.gwcx"
										bundle="JYWEB_RESOURCES" /> </span>
							</h3>
							<div class="con">
								<p class="type1">
									<label>
										关&nbsp;键&nbsp;&nbsp;字：
									</label>
									<html:text property="querylike_zpzw" style="width:100px"
										maxlength="50"></html:text>
								</p>
								<p class="type1">
									<label>
										工作地点：
									</label>
									<html:text property="querylike_gzdd" maxlength="25"
										style="width:100px"></html:text>
								</p>
								<p class="type1">
									<label>
										职位性质：
									</label>
									<html:select property="queryequals_gwxz">
										<html:option value=""></html:option>
										<html:options collection="gzxzList" property="en"
											labelProperty="cn" />
									</html:select>
								</p>

								<div class="btn">
									<button
										onclick="allNotEmpThenGo('/xgxt/jyweb.do?method=workMore')">
										查 询
									</button>
								</div>
							</div>

							<%--<h3>
								<span><bean:message key="jyweb.index.qyml"
										bundle="JYWEB_RESOURCES" /> </span>
								<a href="companyMore.do?queryequals_shzt=通过"></a>
							</h3>
							<ul>
								<logic:iterate id="d" name="dwList" offset="0" length="6">
									<li>
										<a href="/xgxt/jyweb.do?method=companyInfo&pkValue=${d.yhm }">${d.dwmc}</a>
									</li>
								</logic:iterate>
							</ul>
						--%>
						</div>
					</div>
					<div class="index_midframe">
						<div class="index_news02">
							<h3 class="index_title">
								<span><bean:message key="jyweb.index.xwzx"
										bundle="JYWEB_RESOURCES" /> </span><a href="jyweb_jyxw.do"></a>
							</h3>
							<h4>
								<jsp:include flush="true" page="swf.jsp"></jsp:include>
							</h4>
							<ul class="newslist_02 pic_news">
								<logic:iterate id="n" name="newsList" offset="0" length="6">
									<li>
										<a href="#"
											onclick="window.open('/xgxt/jyweb.do?method=newsInfo&pkValue=${n.rowid }')">
											${n.wjbt} </a><span class="time">${n.fbsj }</span>
									</li>
								</logic:iterate>
							</ul>
						</div>
						<div class="index_news03">
							<div class="comp">
								<ul id="xnzpTitle" style="width: 84%;">
									<li id="jyzs" onmouseover="setZpxx('jyzs','xnzpTitle');">
										<a href="javascript:setZpxx('jyzs','xnzpTitle');">就业站式</a>
									</li>
									<logic:iterate id="s" name="zplmList" offset="0" length="2">
										<li id="${s.dm }"
											onmouseover="setZpxx('${s.dm }','xnzpTitle');">
											<a href="javascript:setZpxx('${s.dm }','xnzpTitle');">${s.mc
												} </a>
										</li>
									</logic:iterate>
									<li id="xnzp" onmouseover="setZpxx('xnzp','xnzpTitle');">
										<a href="javascript:setZpxx('xnzp','xnzpTitle');">校内招聘</a>
									</li>
									<li id="qyzp" onmouseover="setZpxx('qyzp','xnzpTitle');">
										<a href="javascript:setZpxx('qyzp','xnzpTitle');">企业招聘</a>
									</li>

								</ul>

								<a href="jyweb_csyg.do" class="more" id="zpMore"></a>
							</div>
							<div id="news03" class="con">
							<div id="jyzsRs"> 
							
								  <iframe name="framecon" id="frame_jyyzs" allowTransparency="true" src="jygl/jyweb/jyzs.jsp" width="100%" frameborder="0" marginwidth="0" marginheight="0" onload="this.height=100"></iframe>
      <script type="text/javascript">
function reinitIframe(){
var iframe = document.getElementById("frame_jyyzs");
try{
var bHeight = iframe.contentWindow.document.body.scrollHeight;
var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
var height = Math.max(bHeight, dHeight);
iframe.height =  height;
}catch (ex){}
}
window.setInterval("reinitIframe()", 200);
		</script></div>
								
								<!-- 可维护栏目的招聘信息 -->
								<ul id="zphList" class="newslist_02" style="display: none">
									<logic:iterate id="z" name="zphList" offset="0">
										<li value="${z.zplxdm }">
											<a href="#"
												onclick="window.open('/xgxt/jyweb.do?method=zphView&pkValue=${z.pkValue }')">${z.zphbt
												}</a><span class="time">${z.fbsj }</span>
										</li>
									</logic:iterate>
								</ul>
								<!-- 校内招聘-->
								<ul id="xnzpList" class="newslist_02" style="display: none">
									<logic:iterate id="xnzp" name="xnzpList" offset="0" length="7">
										<li>
											<a href="#"
												onclick="window.open('/xgxt/jyweb.do?method=ztzpUpdate&doType=view&pkValue=${xnzp.id }')">
												${xnzp.zpzt} </a><span class="time">${xnzp.fbsj }</span>
										</li>
									</logic:iterate>
								</ul>

								<!-- 企业招聘-->
								<ul id="qyzpList" class="newslist_02" style="display: none">
									<logic:iterate id="zpxx" name="zpxxList" offset="0" length="7">
										<li>
											<a href="#"
												onclick="window.open('/xgxt/jyweb.do?method=companyInfo&pkValue=${zpxx.yhm }')">
												${zpxx.gsmc} </a>
										</li>
									</logic:iterate>
								</ul>
							</div>
						</div>
						<div class="index_news03" id="div6">

							<div class="comp">
								<ul id="rctjTitle">
									<li class="current" id="rctj"
										onmouseover="setRctj('rctj', 'rctjTitle');">
										<a><bean:message key="jyweb.index.rctj"
												bundle="JYWEB_RESOURCES" />
										</a>
									</li>

									<logic:iterate id="s" name="zplmList" offset="2" length="3">
										<li id="${s.dm }"
											onmouseover="setRctj('${s.dm }','rctjTitle');">
											<a href="javascript:setRctj('${s.dm }','rctjTitle');">${s.mc
												} </a>
										</li>
									</logic:iterate>
								</ul>
								<a href="jyweb_qzyx.do" class="more" id="rcHerf"></a>
							</div>


							<div id="xtzpDiv" class="con" style="display: none">
								<ul id="xtzpList" class="newslist_02">
									<logic:iterate id="z" name="zphList" offset="0">
										<li value="${z.zplxdm }" style="display: none">
											<a href="#"
												onclick="window.open('/xgxt/jyweb.do?method=zphView&pkValue=${z.pkValue }')">${z.zphbt
												}</a><span class="time">${z.fbsj }</span>
										</li>
									</logic:iterate>
								</ul>
							</div>


							<table width="512px" border="0" cellpadding="0" cellspacing="0"
								id="rcTab" class="index_tab01">
								<thead>
									<tr>
										<td align="center">
											姓 名
										</td>
										<td align="center">
											性 别
										</td>
										<td align="center">
											学 历
										</td>
										<td align="center">
											专 业
										</td>
									</tr>
								</thead>
								<tbody>
									<logic:iterate id="v" name="rcList" length="5">
										<tr>
											<td align="center">
												${v.xm }
											</td>
											<td align="center">
												${v.xb }
											</td>
											<td align="center">
												${v.xl }
											</td>
											<td align="center">
												${v.zymc }
											</td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
					</div>
					<div class="index_rightframe">
						<div id="4"></div>
						<div id="5"></div>
						<div id="6"></div>


						<div id="inform" class="index_news04">
							<h3 class="index_title">
								<span><bean:message key="jyweb.index.tzgg"
										bundle="JYWEB_RESOURCES" /> </span><a href="jyweb_jygg.do"></a>
							</h3>
							<ul class="newslist_03">
								<marquee behavior="scroll" scrollAmount="1" width="210"
									height="160" direction="up" onmouseover=
	stop();
onmouseout=
	start();
> <logic:iterate id="i"
									name="informList" offset="0" length="3">
									<li>
										<a href="#"
											onclick="window.open('/xgxt/jyweb.do?method=newsInfo&pkValue=${i.rowid }')">${i.wjbt
											}</a><span class="time">${i.fbsj }</span>
									</li>
								</logic:iterate> </marquee>
							</ul>
						</div>
						<div id="dowload" class="index_news01" style="height: 200px">
							<h3 class="index_title">
								<span><bean:message key="jyweb.index.xzzq"
										bundle="JYWEB_RESOURCES" /> </span><a href="jyweb_xzzq.do"></a>
							</h3>
							<ul class="newslist_01">
								<logic:iterate id="w" name="filesList" offset="0" length="6">
									<li>
										<a href="#"
											onclick="window.open('/xgxt/jyweb.do?method=fileView&pkValue=${w.guid }')">${w.wjm
											}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>
						<div id="xxinfo" class="index_demo2">
							<h3>
								<span><bean:message key="jyweb.index.lxwm"
										bundle="JYWEB_RESOURCES" /> </span>
							</h3>
							<div class="con">
								<h4>
									<bean:message key="jyweb.index.jybgs" bundle="JYWEB_RESOURCES" />
								</h4>
								<p>
									电话：${lxfxMap.lxdh }
									<br />
									传真：${lxfxMap.czhm }
									<br />
									email：${lxfxMap.dzyx}
									<br />
									地址：${lxfxMap.lxdz }
									<br />
									邮编：${lxfxMap.yzbh }
								</p>
							</div>
						</div>
					</div>
					<div class="index_demo3">
						<h3>
							<span><bean:message key="jyweb.index.yqlj"
									bundle="JYWEB_RESOURCES" /> </span>
						</h3>
						<ul class="piclink">
							<logic:iterate id="v" name="tpljList">
								<li>
									<a href="#" onclick="window.open('${v.ljwz }')"><img
											src="<%=request.getContextPath()%>/jygl/jyweb/download.jsp?filePath=${v.tpdz }"
											style="width: 123px; height: 37px;" /> </a>
								</li>
							</logic:iterate>
						</ul>
						<ul class="urllink">
							<logic:iterate id="s" name="wzljList">
								<li>
									<a href="#" onclick="window.open('${s.ljwz }')">${s.ljmc }</a>
								</li>
							</logic:iterate>
						</ul>
					</div>
				</div>
				<div class="index_botframe">
					<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
				</div>
			</div>

			<logic:present name="message">
				<script>
	alert("${message}");
</script>
			</logic:present>
		</html:form>
	</body>
</html>
