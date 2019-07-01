<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khbgl/js/khbgl.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
		
		</script>
		
	</head>
	<body>
	<html:form action="/khglKhbgl" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" class="btn_cx" id="search_go" onclick="searchRs();closeMore();return false;"/>
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="khnrwh();return false;" class="btn_sz" >考核内容维护</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="khnryl();return false;" class="btn_yl" >预览</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="khbfz();return false;" class="btn_fz" >复制</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qySz('1');return false;" class="btn_plqy" >启用</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qySz('2');return false;" class="btn_plty" >停用</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>考核表列表</font> </span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
