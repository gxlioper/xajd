<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<html:form action="jyweb">
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
								<p>
									��ӭ����
									<font color="#025BBB">${jyweb_realName }</font>
								</p>
								<div class="btn" align="center">
									<button onclick="refreshForm('/xgxt/jyweb.do?method=logout')">
										ע ��
									</button>
								</div>
							</div>
						</div>
						<div class="typelist">
							<h3>
								<span>��������</span>
							</h3>
							<ul>
								<li>
									<a href="jywebUseCheckSession.do?method=resumeUpdate" target="framecon">���˼���</a>
								</li>
								<li>
									<a href="jywebUseCheckSession.do?method=revertcx" target="framecon">��λ�ظ�</a>
								</li>
								<li>
									<a
										href="/xgxt/jyweb.do?method=stuJobsMore&queryequals_xh=${jyweb_userName }&queryequals_joblb=�ղ�"
										target="framecon">���ղصĸ�λ</a>
								</li>
								<li>
									<a
										href="/xgxt/jyweb.do?method=stuJobsMore&queryequals_xh=${jyweb_userName }&queryequals_joblb=����"
										target="framecon">��������ĸ�λ</a>
								</li>
								<li>
									<a
										href="/xgxt/jyweb.do?method=stuJobsMore&queryequals_xh=${jyweb_userName }&queryequals_joblb=���"
										target="framecon">���������ĸ�λ</a>
								</li>
							</ul>
						</div>
					</div>

					<div class="type_right">
						<iframe name="framecon" allowTransparency="true"
							src="jywebUseCheckSession.do?method=resumeUpdate" width="100%"
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
