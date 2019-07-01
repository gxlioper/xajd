<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body id="ccc">
	
		<div class="index_mainbody">
			<div class="index_topframe">
				<!-- logo 和 菜单 -->
				<jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
			</div>
			<div class="type_mainframe">

				<div class="typeleft floatleft" id="left">
					<div class="type_navigate">
						<h3>
							<span>导航栏</span>
						</h3>
						<ul style="display:block;">
							<logic:iterate id="m" name="menusList">
								<li>
									<a href="/xgxt/${m.mkly }" target="framecon"> <span>${m.mc}</span>
									</a>
								</li>
							</logic:iterate>
						</ul>
					</div>
				</div>
				<%--					<div class="btn_hide_on" id="leftBotton">--%>
				<%--						<button onclick="changeWin();return false;"></button>--%>
				<%--					</div>--%>
				<%--					<div class="btn_hide_off" style="display:none;" id="rightBotton">--%>
				<%--						<button onclick="changeWin();return false;"></button>--%>
				<%--					</div>--%>
				<div class="typeright floatright" id="right">
					<!--内容区主体-->
					<div class="typecon">
						<iframe name="framecon" allowTransparency="true"
							id="mainFrame"
							src="/xgxt/<logic:iterate id="m" name="menusList" offset="0" length="1">${m.mkly } </logic:iterate>"
							width="100%" frameborder="0" marginwidth="0" marginheight="0"
							onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>200?fdh:200)"
							scrolling="no">

						</iframe>
					</div>
				</div>
			</div>
			<div class="botframe">
				<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
			</div>
		</div>
	</body>
</html>
