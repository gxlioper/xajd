<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/tsxsgl/js/tsxsManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_tsxs">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes" >
						<li>
							<a href="#" onclick="addTsxs();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="#" onclick="updateTsxs();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#" onclick="deleteTsxs();return false;" class="btn_sc">删除</a>
						</li>
						<logic:notEqual name="xxdm" value="10704">
							<li>
								<a href="#" onclick="setTsxsGzzt();return false;" class="btn_sz">设置关注状态</a>
							</li>
						</logic:notEqual>
					</logic:equal>
					<logic:equal name="writeAble" value="yes" >
						<li>
							<a href="#" onclick="exportTsxsList();return false;" class="btn_dc">导出</a>
						</li>
						</logic:equal>
					<logic:equal value="10220" name="xxdm" >
						<li><a href="#" onclick="drxx();return false;" class="btn_dr">导入</a></li>
					</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 特殊学生信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
