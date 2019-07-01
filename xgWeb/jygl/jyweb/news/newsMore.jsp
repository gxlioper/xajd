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
					<!-- logo �� �˵� -->
					<jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
				</div>
				<div class="index_mainframe">
					<div class="type_left">
						<div class="type_UserInfo">
							<h3>
								<span>�û���Ϣ</span>
							</h3>
							<div class="con">
								<logic:present name="jyweb_userName">
									<p>
										��ӭ����
										<font color="#025BBB">${jyweb_realName }</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">
											ע ��
										</button>
									</div>
								</logic:present>
								<logic:notPresent name="jyweb_userName">
									<p>
										��ӭ����
										<font color="#025BBB">�ο�</font>
									</p>
									<div class="btn" align="center">
										<button onclick="refreshForm('/xgxt/jyweb.do?method=index')">
											��¼
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
										����ʱ��
										<html:text property="querygreaterequal_fbsj"
											onclick="showCalendar(this.id,'y-mm-dd')" 
											styleId="querygreaterequal_fbsj"
											onblur='dateFormatChg(this)' />
										-
										<html:text onblur='dateFormatChg(this)'
											onclick="showCalendar(this.id,'y-mm-dd')" 
											styleId="querylessequal_fbsj"
											property="querylessequal_fbsj" />
									</label>

									<div class="hdm">
										<ul>
											<html:hidden property="queryequals_wjlx" value="${wjlx }" />
										</ul>
										<p>
											<html:text property="querylike_wjbt" maxlength="25"
												value="���������"
												onfocus="if(value==defaultValue){value='';this.style.color='#000'}"
												onblur="if(!value){value=defaultValue;this.style.color='#999'}"
												style="color:#999999" />
											<button
												onclick="if(document.getElementById('querylike_wjbt').value==document.getElementById('querylike_wjbt').defaultValue){document.getElementById('querylike_wjbt').value=''} 
                    	allNotEmpThenGo('/xgxt/jyweb.do?method=newsMore&doType=query')"
												id="search_go"></button>
										</p>
									</div>
								</div>

								
										<table width="800px" border="0" class="tab_03">
											<thead>
												<tr>
													<logic:iterate id="tit" name="topTr" offset="1"
														scope="request">
														<td onclick="tableSort(this)">
															${tit}
														</td>
													</logic:iterate>
												</tr>
											</thead>
											<tbody>
												<logic:present name="rs">
													<logic:notEmpty name="rs">
														<logic:iterate name="rs" id="s">
															<tr>
																<logic:iterate id="v" name="s" offset="1" length="1">
																	<td align="center">
																		<a class="underline"
																			href="#" onclick="window.open('/xgxt/jyweb.do?method=newsInfo&type=wjbt&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>')">${v
																			}</a>
																	</td>
																</logic:iterate>
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
										<jsp:include flush="true" page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
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
