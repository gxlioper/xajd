<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
	</script>
	</head>
	
	<body>
		<html:form action="/gwjsMore.do" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>最新公文</a>
				</p>
			</div>
			<div style="display: none">
				<input type="hidden" id="go" name="go" value="a" />
           		<button class="btn_cx" id="search_go" 
           		onclick="document.getElementById('go').value='go';refreshForm('/xgxt/gwjsMore.do');">
           		查 询
           		</button>
			</div>
				<logic:notPresent name="rs">
					<ul class="index_list_01">
						<li>&nbsp;&nbsp;&nbsp;暂无公文
						</li>
					</ul>
				</logic:notPresent>
				
				<logic:present name="rs">
					<ul class="index_list_01">
						<logic:iterate id="map" name="rs">
							<li>
								<a href="fileView.do?wjh=${map.wjh }&yd=yes"
										target="_blank" >&nbsp;${map.wjh }</a><span
									class="time">${map.wjffsj }</span>
							</li>											
						</logic:iterate>
					</ul>
				</logic:present>
				
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjscForm"></jsp:include>
			     <script type="text/javascript">
					$('choose').className="hide";
				 </script>
			   <!--分页显示-->
		</html:form>
	</body>
</html>
	