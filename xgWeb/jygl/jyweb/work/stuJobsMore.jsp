<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript">
			function setTdrq(count){
				var now = $('now').value;
				var	nowTime = now.substring(0,4)+"-"+now.substring(4,6)+"-"+now.substring(6,8);
				var tdrq = getDate(nowTime,Number(count)).replace('-','').replace('-','');
				$('tdrq').value=tdrq;
				$('search_go').click();
			}
		</script>
	</head>
	<body>


		<html:form action="/jyweb">
			<div class="type_right">
				<div class="rightcon">
					<h3>
						<span>我的${joblb }记录</span>
					</h3>
					<div class="tabcon">
						<div class="type_search">
							<label>
								单位名称
								<html:text property="querylike_dwmc" maxlength="25"></html:text>
								<input type="hidden" value="${joblb }" name="queryequals_joblb" />
								<input type="hidden" value="${jyweb_userName }"
									name="queryequals_xh" />

								<input type="hidden" name="querygreaterequal_tdrq" id="tdrq" />
								<input type="hidden" value="${nowTime }"
									name="querylessequal_tdrq" id="now" />
							</label>

							<div class="hdm">
								<ul>
									<li>
										<a href="#" onclick="setTdrq(-3)">近三天</a>
									</li>
									<li>
										<a href="#" onclick="setTdrq(-7)">近一周</a>
									</li>
									<li>
										<a href="#" onclick="setTdrq(-30)">近一月</a>
									</li>
								</ul>
								<p>
									<html:text property="querylike_gwmc" maxlength="50"
										value="请输入岗位名称"
										onfocus="if(value==defaultValue){value='';this.style.color='#000'}"
										onblur="if(!value){value=defaultValue;this.style.color='#999'}"
										style="color:#999999"></html:text>
									<button
										onclick="if(document.getElementById('querylike_gwmc').value==document.getElementById('querylike_gwmc').defaultValue){document.getElementById('querylike_gwmc').value=''} 
                    	allNotEmpThenGo('/xgxt/jyweb.do?method=stuJobsMore')"
										id="search_go"></button>
								</p>
							</div>
						</div>


						<table width="800px" border="0" class="tab_03">
							<thead>
								<tr>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
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
												<td align="center">
													<a href="#"
														onclick="window.open('/xgxt/jyweb.do?method=companyInfo&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>')">
														<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
													</a>
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
										for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
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
		</html:form>
	</body>
</html>
