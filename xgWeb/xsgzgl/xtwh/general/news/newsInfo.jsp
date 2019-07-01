<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
<%--		<div class="tab_cur">--%>
<%--			<p class="location">--%>
<%--				<em>您的当前位置:</em><a>相关新闻</a>--%>
<%--			</p>--%>
<%--		</div>--%>

		<!--层-->
		<div class="mainframe">
			<!--普通新闻页-->
			<div class="newspage">
				<div class="newspagebg">
					<div class="title">
						<h3>
							<bean:write name="newstitle" />
						</h3>
						<h4>
							
							<span>
							<logic:equal value="10827" name="xxdm">
							发布日期：
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
							 发布时间：
							</logic:notEqual>
							<bean:write name="pubtime" />
							</span>
							<logic:notEqual value="12869" name="xxdm">
							<span>
							发布人：
							<logic:equal value="10827" name="xxdm">
								<bean:write name="xm" />
								</span><span>
								发布人部门：
								<bean:write name="bmmc" />
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								<bean:write name="puber" />
							</logic:notEqual>
							</span>
							</logic:notEqual>
							<logic:equal value="12869" name="xxdm">
							<span>
								发布部门：
								<bean:write name="bmmc" />
							</span>
							</logic:equal>
						</h4>
					</div>
					<div class="newcont">
						<bean:write name="newscont" filter="false" />

					</div>

					<div class="newsbar">
						<button type="button" onclick="javascript:window.window.close();" name="关闭"
							class="button">
							关 闭
						</button>
					</div>
				</div>

			</div>


		</div>

<%--		<div class="botframe">--%>
<%--			<div class="copy">--%>
<%--				<span>开发单位：杭州正方软件股份有限公司 版权所所有&copy;2010 联系电话：0570-89902888</span>--%>
<%--			</div>--%>
<%--		</div>--%>
</body>
</html>
