<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<html:form action="jyweb">
			<input type="hidden" id=yhm value="${jyweb_userName }" />
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
								<p>
									欢迎您：
									<br />
									<font color="#025BBB">${jyweb_realName }</font>
								</p>
								<div class="btn" align="center">
									<button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">
										注 销
									</button>
								</div>
							</div>
						</div>
						<div class="typelist">
							<h3>
								<span>单位招聘信息维护</span>
							</h3>
							<ul>
								<li>
									<a href="jywebUseCheckSession.do?method=mmUpdate" target="framecon">修改密码</a>
								</li>
								<li>
									<a
										href="jywebUseCheckSession.do?method=companyUpdate&doType=update&pkValue=${jyweb_userName }"
										target="framecon">单位信息完善</a>
								</li>
								<li>
									<a href="workManage.do" target="framecon">发布招聘信息</a>
								</li>
								<li>
									<a href="jywebUseCheckSession.do?method=resumecx" target="framecon">查看学生简历投递</a>
								</li>
							</ul>
						</div>
					</div>

					<div class="type_right">
						<iframe name="framecon" allowTransparency="true"
							src="/xgxt/jyweb.do?method=companyView" width="100%"
							frameborder="0" marginwidth="0" marginheight="0"
							onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>500?fdh:500)"
							scrolling="no">

						</iframe>
					</div>


				</div>
				<div class="index_botframe">
					<jsp:include flush="true" page="/jygl/jyweb/foot.jsp"></jsp:include>
				</div>
			</div>
		</html:form>
	</body>
</html>
