<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="mainframe">
			<!--普通新闻页-->
			<div class="newspage">
				<div class="newspagebg">
					<div class="title">
						<h3>
							<bean:write name="rs" property="wjbt" />
						</h3>
						<h4>
							<span> 发布时间：<bean:write name="rs" property="fbsj" />
							</span>
							<span> <span> 发布人：<bean:write name="rs"
										property="fbr" />
							</span> <span>
						</h4>
					</div>
					<div class="newcont">
						<bean:write name="rs" property="wjnr" filter="false"/>
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
	</body>
</html>
