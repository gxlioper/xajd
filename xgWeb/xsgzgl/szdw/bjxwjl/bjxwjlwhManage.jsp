<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bjxwjl/js/wh.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "班级行为记录",
		pager : "pager",
		url : "szdw_bjxwjlwh.do?method=query",
		colList : [
				{ label : 'key', name : 'guid', index : 'guid',key : true, hidden : true },
				{ label : '学年', name : 'xn', index : 'xn', width : '10%'},
				{ label : '学期', name : 'xqmc', index : 'xqmc', width : '4%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xydm', width : '10%' },
				{ label : '班级', name : 'bjmc', index : 'bjdm', width : '14%' },
				{ label : '记录人', name : 'jlrmc', index : 'jlrmc', width : '8%' },
				{ label : '记录时间', name : 'jlsj', index : 'jlsj', width : '8%' },
				{ label : '内容', name : 'jlnrxs', index : 'jlnrxs', width : '40%' }],
		sortname : "jlsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
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
		<html:form action="/szdw_bjxwjlwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addSq();return false;"  title="点击该按钮，打开申请表填写页面。">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateSq();return false;" class="btn_xg" title="选中一条记录，点击该按钮可修改申请表。">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteSq();return false;" class="btn_sc" title="只能取消未审核的记录，已经在审核的不能取消。" >删除</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ckSq();return false;" class="btn_ck">查看</a>
						</li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>
						</logic:equal>				
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>班级行为记录&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
