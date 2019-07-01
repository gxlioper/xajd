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
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src="js/qgzx/jfgl/jfgl.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});
		//复制经费划拨费用
		function copy(){
			var url = "qgzx_jfgl.do?method=copyJfhb";
			var title = "复制经费划拨费用";
			showDialog(title, 500, 250, url);
		}
	</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/qgzx_jfgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showDialog('经费增加', 760, 525, 'qgzx_jfgl.do?method=jfxxZj');return false;" class="btn_zj">经费增加</a>
						</li>
						<logic:equal name="xxdm" value="10504">
						<logic:equal name="jfhbfs" value="1">
						<li>
							<a href="#"onclick="showDialog('月份初始化', 760, 525, 'qgzx_jfgl.do?method=jfxxInit');return false;" class="btn_zj">月份初始化</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="#" onclick="showModi('update');return false;" class="btn_xg">经费修改</a>
						</li>
						<!-- 浙江交通职业技术学院个性化 -->
						<logic:equal  name="xxdm" value="12036">
							<li>
								<a href="#" onclick="copy();return false;" class="btn_fz">复制</a>
							</li>
						</logic:equal>
						</logic:equal>
						<li><a href="#" class="btn_ck" onclick="showModi('view');return false;">查看明细</a></li>
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxJfglForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>