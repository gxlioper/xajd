<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/gjgl/js/gjgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "信息列表",
				pager : "pager",
				url : "xsgjgl.do?method=gjjgList&type=query",
				colList:[	
						    { label:'key',name:'gjxxid', index: 'gjxxid',hidden:true,key:true},						   
						    { label : '学号', name : 'xh', index : 'xh', width : '12%',formatter:xhLink},
							{ label : '姓名', name : 'xm', index : 'xm', width : '8%'},
							{ label : '寝室', name : 'qsh', index : 'qsh', width : '8%'},
							{ label : '主办方负责人', name : 'zbffzr', index : 'zbffzr', width : '8%'},
							{ label : '请假开始时间', name : 'qjkssj', index : 'qjkssj', width : '8%'},
							{ label : '请假结束时间', name : 'qjjssj', index : 'qjjssj', width : '8%'},
							{ label : '请假节次', name : 'qjjc', index : 'qjjc', width : '12%'},
							{ label : '是否归寝', name : 'sfgq', index : 'sfgq', width : '5%'},
							{ label : '不归寝时间', name : 'bgqsj', index : 'bgqsj', width : '8%'},
							{ label : '备注', name : 'bz', index : 'bz', width : '8%'}
							],
							sortname: "",
					 		sortorder: "desc"
					 		//radioselect:true
					 	}	
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xsgjgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<%--<li>
							<a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg" >修改</a>
						</li>--%>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="#" onclick="importXX();return false;" class="btn_dr">导入</a>
						</li>
						</logic:equal>
							<li>
							<a href="#" class="btn_dc" onclick="exportXX();return false;">导出</a>
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
				<span>学生公假信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>