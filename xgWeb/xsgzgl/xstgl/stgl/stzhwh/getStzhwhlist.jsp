<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stzhwh/js/stzhwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
	jQuery(function(){
			var gridSetting = {
				caption : "社团结果列表",
				pager : "pager",
				url : "stglStzhwh.do?method=getStzhwhList&type=query",
				colList : [
							{ label : 'key', name : 'stid', index : 'stid',key : true, hidden : true },
							{ label : 'bz', name : 'bz', index : 'bz',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sqsj', name : 'sqsj', index : 'sqsj', hidden : true },
							{ label : 'sqkg', name : 'sqkg', index : 'sqkg', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
							{ label : 'sqkssj', name : 'sqkssj', index : 'sqkssj', hidden : true },
							{ label : 'sqjssj', name : 'sqjssj', index : 'sqjssj', hidden : true },
							{ label : 'xmlbdm', name : 'xmlbdm', index : 'xmlbdm', hidden : true },
							{ label : '学年', name : 'currxn', index : 'currxn', width : '15%' },
							{ label : '社团项目名称', name : 'stxmmc', index : 'stxmmc', width : '15%',formatter : xmmcLink  },
							{ label : '社团类别', name : 'stlbmc', index : 'stlbmc', width : '10%' },
							{ label : '项目类别', name : 'xmlbmc', index : 'xmlbmc', width : '12%' },
							{ label : '成员数量', name : 'cysl', index : 'cysl', width : '12%' },
							/*
							{ label : '社团开始时间', name : 'kssj', index : 'kssj', width : '5%',},
							{ label : '社团结束时间', name : 'jssj', index : 'jssj', width : '5%' },
							*/
							<logic:equal value="12872" name = "xxdm">
							{ label : '社团星级', name : 'stxj', index : 'stxj', width : '5%' },
							</logic:equal>
							{ label : '指导老师', name : 'zdlsxm', index : 'zdlsxm', width : '12%' }
							],
					sortname : "sqsj",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="usertype" value="${usertype}">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/stglRtjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="stzhwhCyztwh();return false;" class="btn_xg" >成员状态维护</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="stzhwhCycjpd();return false;" class="btn_xg" >成员成绩评定</a>
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
				<span>社团结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
