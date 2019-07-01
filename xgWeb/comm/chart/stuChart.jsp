<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="xgxt.form.User" />
<jsp:directive.page import="xgxt.comm.search.SearchService" />
<jsp:directive.page import="xgxt.comm.search.SearchForm" />
<jsp:directive.page import="xgxt.form.RequestForm" />
<jsp:directive.page import="xgxt.action.Base" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.HashMap" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src="js/comm/searchTj.js"></script>
		<script type='text/javascript' src="/xgxt/dwr/interface/searchUtil.js"></script>
		<script type="text/javascript">
		function searchRs(){
			refreshForm('/xgxt/chart.do?method=getStuChart');
		}
	</script>
	</head>

	<body>
		<html:form action="/chart.do?method=getStuChart">
	
			<input type="hidden" name="searchModel.path" value="${path }"/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->

			<div class="search_advanced">
				<div class="prop-item">
				<dl>
					<dt>
						统计类别：
					</dt>
					<dd>
						<ul>
							<li>
								<html:radio property="tjzd" value="xb">性别</html:radio>
							</li>

							<li>
								<html:radio property="tjzd" value="nj">年级</html:radio>
							</li>

							<li>
								<html:radio property="tjzd" value="xymc"><bean:message key="lable.xsgzyxpzxy" /></html:radio>
							</li>

							<li>
								<html:radio property="tjzd" value="zzmmmc">政治面貌</html:radio>
							</li>

							<li>
								<html:radio property="tjzd" value="xjztm">学籍</html:radio>
							</li>

							<li>
								<html:radio property="tjzd" value="xlmc">学历</html:radio>
							</li>

							<li>
								<html:radio property="tjzd" value="xwmc">学位</html:radio>
							</li>
							<li>
								<html:radio property="tjzd" value="mzmc">民族</html:radio>
							</li>
						</ul>
					</dd>
				</dl>
			</div>


			<div class="cartogram">
				<ul class="hdm">
					<li class="current">
						<html:radio property="tjlb" value="pie">饼图</html:radio>
					</li>
					<li>
						<html:radio property="tjlb" value="category">柱状图</html:radio>
					</li>
				</ul>
				<div class="con" algin="center">
					<%
						response.setContentType("text/html;charset=utf-8");  
					 %>
					<bean:write name="imageMap" filter="false" />
					<img src="${url }" usemap="#${fileName }" />
				</div>
			</div>
		</html:form>
	</body>
</html>
