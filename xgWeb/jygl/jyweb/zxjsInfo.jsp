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
				   <jsp:include flush="true" page="/jygl/jyweb/title.jsp"></jsp:include>
			</div>
			<!--层-->
			<div class="mainframe">
				<!--普通新闻页-->
				<div class="newspage">
					<div class="newspagebg">
						<div style="min-height: 400px;">
							<p>
								<bean:write name="jsnr" filter="false"/>
							</p>

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
