<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/lstd/lstdjg/js/lstdjgManage.js"></script>
		<script type="text/javascript">
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_lstd_jggl">
		
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<logic:notEqual name="userType" value="stu">
					<logic:equal name="writeAble" value="yes">
						<!-- 按钮 -->
						<div class="buttonbox">
							<ul>
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="add();return false;" 
									>增加</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
									>修改</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
									>删除</a>
								</li>
								<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
							</ul>
						</div>
					</logic:equal>
				</logic:notEqual>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>绿色通道结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
