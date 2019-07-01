<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/gygl/gypynew/yxxs/js/yxxs.js"></script>
				<script type="text/javascript">
					jQuery(function(){
						var query=jQuery("#query").val();
					     var gridSetting = {
									caption:"优秀学生",
									pager:"pager",
									url:"gypy.do?method=yxxs&type=query",
									colList:[
									   {label:'id',name:'gypyid', index: 'gypyid',key:true,hidden:true},
									   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:dcmcLink},
									   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
									   {label:'性别',name:'xb', index: 'xb',width:'10%'},
									   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
									   {label:'楼栋',name:'ldmc', index: 'lddm',width:'15%'},
									   {label:'寝室号',name:'qsh', index: 'qsh',width:'15%'},
									   {label:'学年',name:'xn', index: 'xb',width:'10%'},
									   {label:'学期',name:'xqmc', index: 'xqdm',width:'10%'}
									],
									sortname: "xn",
								 	sortorder: "desc"
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
	<html:form action="/gypy.do?method=yxxs&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>优秀学生 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
