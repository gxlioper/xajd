<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/xsxx/js/fbglxsxx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"分班管理学生信息列表",
									pager:"pager",
									url:"fbglxsxx.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
									   {label:'考生号',name:'ksh', index: 'ksh',formatter:dcmcLink},
									   {label:'姓名',name:'xm', index: 'xm'},
									   {label:'性别',name:'xb', index: 'xb'},
									   {label:'年级',name:'nj', index: 'nj'},
									   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
									   {label:'专业',name:'zymc', index: 'zymc'},
									   {label:'投档成绩',name:'tdcj', index: 'tdcj'},
									   {label:'生源地',name:'syd', index: 'syd',hidden:true},
									   {label:'学制',name:'xz', index: 'xz'},
									   {label:'层次',name:'pyccmc', index: 'pyccmc'},
									   {label:'班级',name:'bjmc', index: 'bjmc'},
									   {label:'学号',name:'xh', index: 'xh'}
									]
									
								}
							jQuery("#dataTable").initGrid(gridSetting);
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/fbglxsxx?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;"
								class="btn_sc">删除</a>
						</li>
						<li><a href="#" onclick="drxx();return false;" class="btn_dr">导入</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">分班管理学生信息 <a id="title"
					href="javascript:;"
					style="float: right; margin-right: 30px; color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
