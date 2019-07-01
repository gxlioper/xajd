<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>		
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="xsgzgl/xlzx/zxswh/js/zxsglManage.js"></script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_zxs">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" onclick="addZxs();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="#" onclick="updateZxs();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#" onclick="deleteZxs();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="#" onclick="setBatchZgStatus();return false;" class="btn_sz">设置在岗状态</a>
						</li>
					</logic:equal>
					<logic:equal name="writeAble" value="yes" >
						<li>
							<a href="#" onclick="exportZxsList();return false;" class="btn_dc">导出</a>
						</li>
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
				<span> 咨询师信息列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
