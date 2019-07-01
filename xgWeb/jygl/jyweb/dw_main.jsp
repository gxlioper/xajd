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
									<br />
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
								<span>��λ��Ƹ��Ϣά��</span>
							</h3>
							<ul>
								<li>
									<a href="jywebUseCheckSession.do?method=mmUpdate" target="framecon">�޸�����</a>
								</li>
								<li>
									<a
										href="jywebUseCheckSession.do?method=companyUpdate&doType=update&pkValue=${jyweb_userName }"
										target="framecon">��λ��Ϣ����</a>
								</li>
								<li>
									<a href="workManage.do" target="framecon">������Ƹ��Ϣ</a>
								</li>
								<li>
									<a href="jywebUseCheckSession.do?method=resumecx" target="framecon">�鿴ѧ������Ͷ��</a>
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
