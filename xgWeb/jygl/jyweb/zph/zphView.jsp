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
								${rs.zphbt }
							</h3>
							<h4>
								<span>�����ˣ�${rs.fbr }</span>
								<span>�������ڣ�${rs.fbsj }</span>
								<span>��Ƹ���ͣ�${rs.zplx }</span>
								<span>���������${rs.llcs }</span>
							</h4>
						</div>
						<div class="newcont">
							<p>
								${rs.zphnr }
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
