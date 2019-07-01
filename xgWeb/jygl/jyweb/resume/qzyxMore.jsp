<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<html:form action="/jyweb">
			<div class="index_mainbody">
				<div class="index_topframe">
					<!-- logo 和 菜单 -->
					<jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
				</div>
				<div class="index_mainframe">
					<div class="type_left">
						<div class="type_UserInfo">
							<h3>
								<span>用户信息</span>
							</h3>
							<div class="con">
								<logic:present name="jyweb_userName">
									<p>
										欢迎您：
										<font color="#025BBB">${jyweb_realName }</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">
											注 销
										</button>
									</div>
								</logic:present>
								<logic:notPresent name="jyweb_userName">
									<p>
										欢迎您：
										<font color="#025BBB">游客</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=index')">
											登录
										</button>
									</div>
								</logic:notPresent>
							</div>
						</div>
					</div>
					<div class="type_right">
						<div class="rightcon">
							<h3>
								<span>${title }</span>
							</h3>
							<div class="tabcon">
								<div class="type_search">
									<label>
										工作性质
										<html:select property="queryequals_gzxz">
											<html:option value=""></html:option>
											<html:options collection="gzxzList" property="en"
												labelProperty="cn" />
										</html:select>
									</label>
									<label>
										工作地点
										<html:text property="querylike_gzdd" maxlength="50"></html:text>
									</label>
									<label>
									</label>

									<div class="hdm">
										<ul>

										</ul>
										<p>
											<html:text property="querylike_mbgw" maxlength="50"
												value="请输入岗位名称"
												onfocus="if(value==defaultValue){value='';this.style.color='#000'}"
												onblur="if(!value){value=defaultValue;this.style.color='#999'}"
												style="color:#999999" />
											<button
												onclick="if(document.getElementById('querylike_mbgw').value==document.getElementById('querylike_mbgw').defaultValue){document.getElementById('querylike_mbgw').value=''} 
												allNotEmpThenGo('/xgxt/jyweb.do?method=qzyxMore')"
												id="search_go"></button>
										</p>
									</div>
								</div>

								<table width="800px" border="0" class="tab_03">
									<thead>
										<tr>
											<logic:iterate id="tit" name="topTr" offset="1"
												scope="request">
												<td id="${tit.en}" onclick="tableSort(this)">
													${tit.cn}
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<tbody>
										<logic:present name="rs">
											<logic:notEmpty name="rs">
												<logic:iterate name="rs" id="s">
													<tr>
														<td align="center" >
															<a href="#" class="underline"
																onclick="window.open('jywebUseCheckSession.do?method=resumeView&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>')">
																<logic:iterate id="v" name="s" offset="1" length="1">
																	${v }
																</logic:iterate> </a>
														</td>
														<logic:iterate id="v" name="s" offset="2">
															<td align="center">
																${v }
															</td>
														</logic:iterate>
													</tr>
												</logic:iterate>
												<%
													for (int i = 0; i < (Integer) request.getAttribute("maxNum")
														- ((List) request.getAttribute("rs")).size(); i++) {
												%>
												<tr>
													<logic:iterate id="t" name="topTr" offset="1" scope="request">
														<td>
															&nbsp;
														</td>
													</logic:iterate>
												</tr>
												<%
												}
												%>
											</logic:notEmpty>
										</logic:present>
										<logic:empty name="rs">
											<%
												for (int i = 0; i < (Integer) request
														.getAttribute("maxNum"); i++) {
											%>
											<tr>
												<logic:iterate id="t" name="topTr" offset="1" scope="request">
													<td>
														&nbsp;
													</td>
												</logic:iterate>
											</tr>
											<%
											}
											%>
									</logic:empty>
									</tbody>
								</table>
								<jsp:include flush="true"
									page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
								<script type="text/javascript">
											$('choose').className="hide";
								</script>
							</div>
						</div>
					</div>
				</div>
				<div class="index_botframe">
					<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
				</div>
			</div>
		</html:form>
	</body>
</html>
