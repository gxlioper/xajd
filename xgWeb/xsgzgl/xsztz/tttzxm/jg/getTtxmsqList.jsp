<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/jg/js/tttzxm.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/comm/js/comm.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "团体拓展项目结果列表",
				pager : "pager",
				url : "ttxm_jg.do?method=getTtxmsqList&type=query",
				colList : [ {
					label : 'key',
					name : 'ttjgid',
					index : 'ttjgid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}, {
					label : '团队名称',
					name : 'tdmc',
					index : 'tdmc',
					width : '10%',
					formatter:ttsqLink
				}, {
					label : '队长学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter:dzxhLink
					
				},{
					label : '队长姓名',
					name : 'dzxm',
					index : 'dzxm',
					width : '10%'
				}, {
					label : '成员数',
					name : 'tdnum',
					index : 'tdnum',
					width : '6%'
				}, {
					label : '队长所在院系',
					name : 'xymc',
					index : 'xydm',
					width : '5%'
				}, {
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '15%'
				},{
					label : '活动级别',
					name : 'xmjbmc',
					index : 'xmjbmc',
					width : '15%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},
				{
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},
				{
					name : 'xfrdsqzt',
					index : 'xfrdsqzt',
					hidden : true
				},
				{
					name : 'xfrdjgzt',
					index : 'xfrdjgzt',
					hidden : true
				}
				],
				sortname : "sqsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		<%@include file="/comm/hiddenValue.jsp"%></html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>团体拓展项目结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
