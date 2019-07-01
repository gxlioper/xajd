<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/zgjg/js/zbjg.js"></script>
		<script type="text/javascript">
		


	
	jQuery(function() {
		var gridSetting = {
				pager : "pager",
				url : "xlzxnew_zbjg.do?method=searchZbjgCx",
				colList : [
						{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
						{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
						{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : '周次', name : 'zbzc', index : 'zbzc', width : '10%' , formatter : link},
						{ label : '开始日期', name : 'zbksrq', index : 'zbksrq', width : '10%' },
						{ label : '结束日期', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
						{ label : '上报时间', name : 'sbsj', index : 'sbsj', width : '10%' },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true },
						{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true }
						],
				 };
		 var map = getSuperSearch();
		gridSetting["params"] = map;
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
		<html:form action="/xlzxnew_zbjg" >
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">上报</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>修改</a>
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

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
