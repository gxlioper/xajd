<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ydgl/js/ydxx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "信息列表",
				pager : "pager",
				url : "ydXxgl.do?method=getYdlist&type=query",
				colList:[							
						    { label:'key',name:'ydxxid', index: 'ydxxid',hidden:true,key:true},
							{ label : '用电年月', name : 'ydyf', index : 'ydyf', width : '12%'},
							{ label : '楼栋', name : 'ldmc', index : 'ldmc', width : '10%'},
							{ label : '寝室', name : 'qsh', index : 'qsh', width : '10%',formatter:qsLink},
							{ label : '寝室人数', name : 'cws', index : 'cws', width : '10%'},
							{ label : '用电度数（度）', name : 'ds', index : 'ds', width : '8%'},
							{ label : '电费（元）', name : 'df', index : 'df', width : '8%'},
							{ label : '电费余额（元）', name : 'dfye', index : 'dfye', width : '8%'}
							],
							sortname: "ydyf,ldmc,qsh",
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
		<html:form action="/ydXxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg" >修改</a>
						</li>
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
				<span>学生用电信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
