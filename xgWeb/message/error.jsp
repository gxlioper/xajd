<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<!--出错提示页-->
		<div class="page_error">
			<div class="con_error">
				<h3>
					页面出错了，别着急！可能由以下原因造成的：
				</h3>
				<span>给您带来不便，我们深表歉意。如有疑问请联系管理员!</span>
				
				<logic:notPresent name="errMsg">
					<logic:equal value="400" name="No" scope="request">
						<p>
							1.错误的请求
						</p>
					</logic:equal>
					<logic:equal value="404" name="No" scope="request">
						<p>
							1.页面未找到或被删除
						</p>
						<p>
							2.无法在所请求的端口上访问Web站点
						</p>
						<p>
							3.Web服务扩展锁定策略阻止本请求
						</p>
						<p>
							4.MIME映射策略阻止本请求
						</p>
					</logic:equal>
					<logic:equal value="500" name="No" scope="request">
						<p>
							1.内部服务器错误
						</p>
						<p>
							2.Web服务器太忙
						</p>
						<p>
							3.应用程序正忙于在Web服务器上重新启动
						</p>
						<p>
							4.UNC授权凭据不正确
						</p>
						<p>
							5.URL授权存储不能打开
						</p>
						<p>
							6.内部JSP错误
						</p>
					</logic:equal>	
				</logic:notPresent>
				<logic:present name="errMsg">
					<logic:notEmpty name="errMsg">
						<p>${errMsg }</p>
					</logic:notEmpty>
				</logic:present>
			</div>
		</div>
	</body>
</html>
