<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body class="newspage_bg">
		<div class="mainbody type_mainbody">
			<div class="index_topframe">
				<!-- logo �� �˵� -->
				<%--   <jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>--%>
			</div>
			<!--��-->
			<div class="mainframe">
				<!--��ͨ����ҳ-->
				<div class="newspage">
					<div class="newspagebg">
						<div class="title">
							<h3>
								${rs.zpzt }
							</h3>
							<h4>
								<span>�����ˣ�${rs.fbr }</span>
								<span>�������ڣ�${rs.fbsj }</span>
								<span>��Ƹʱ�䣺${rs.zpsj }</span>
								<span>��Ƹ�ص㣺${rs.zpdd }</span>
								<span>���������${rs.llcs }</span>
							</h4>
						</div>
						<div class="newcont">
							<p>
								<strong>��Ƹ��Ϣ��</strong><br/>
								<table class="formlist">
									<tbody>
										<logic:iterate id="v" name="tempDwmcList">
											<tr>
												<td>
												<a href="/xgxt/jyweb.do?method=companyInfo&dwmc=${v }" >${v }</a>
												</td>
												<td>
													<logic:iterate id="s" name="ztzpGwList">
														<logic:equal value="${v }" name="s" property="gsmc">
															<a href="/xgxt/jyweb.do?method=workInfo&pkValue=${s.zpzw}${s.gsmc}${s.fbsj}">${s.zpzwmc }</a>&nbsp;&nbsp;
														</logic:equal>
													</logic:iterate>
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							<br/>
							<br/>
								<strong>��Ƹ���ݣ�</strong><br/>
								<bean:write name="rs" property="zpnr" filter="false"/>
							</p>

						</div>
						<div class="newsbar">
							<button onclick="javascript:window.window.close();" name="�ر�"
								class="button">
								�� ��
							</button>
						</div>
					</div>
				</div>
			</div>

			<div class="botframe">
				<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
			</div>

		</div>
	</body>
</html>
