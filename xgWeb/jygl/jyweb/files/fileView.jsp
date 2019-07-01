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
				<!-- logo 和 菜单 -->
				<%--   <jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>--%>
			</div>
			<!--层-->
			<div class="mainframe">
				<!--普通新闻页-->
				<div class="newspage">
					<div class="newspagebg">
						<div class="title">
							<h3>
								${rs.wjm }
							</h3>
							<h4>
								<span>上传人：${rs.scr }</span>
								<span>上传时间：${rs.scsj }</span>
								<span>浏览次数：${rs.llcs }</span>
								<span>下载次数：${rs.xzcs }</span>
							</h4>
						</div>
						<div class="newcont">
							<p>
								${rs.wjsm }
							</p>

						</div>

						<div class="download" id="tzggb.fjtr">
							<font color="black">附件：</font>
							<span id="${rs.guid }"> <logic:present
									name="jyweb_userName" scope="session">
									<a style="color:blue"
										href="/xgxt/jyweb.do?method=download&pkValue=${rs.guid }&fileName=${rs.wjm }&filePath=${rs.wjlj }"><b>${rs.wjm
											}</b>
									</a>
								</logic:present> <logic:notPresent name="jyweb_userName" scope="session">
							您还未登录，请先<a style="color:red" href="/xgxt/jyweb.do?method=index"><b>登录</b>
									</a>
								</logic:notPresent> </span>
						</div>

						<div class="newsbar">
							<button onclick="javascript:window.window.close();" name="关闭"
								class="button">
								关 闭
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
