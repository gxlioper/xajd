<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/qgzx/glywh/glyZj.js"></script>
		<script>
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});
		</script>
	</head>
	<body>

		<!-- 标题 
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>-->
		<html:form action="/qgzx_glygl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="zjbcGly();return false;" class="btn_zj">添加</a>
						</li>
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box" >
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				
				<!-- 提示信息 -->
				<%@ include file="/comm/other/tsxx.jsp"%>
				
				<div id="div_rs"
					style="width:100%;height:400px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				
				<!--分页显示-->
				<div style="margin-top:-55px">
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxGlyglForm"></jsp:include>
				</div>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			
		</html:form>
	</body>
</html>