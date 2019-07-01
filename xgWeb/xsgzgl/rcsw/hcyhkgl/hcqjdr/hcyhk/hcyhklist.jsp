<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hcyhkgl/hcqjdr/js/hcyhk.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "火车优惠区间列表",
				pager : "pager",
				url : "hcyhk_hcyhqjdr.do?method=hcqjManage&type=query",
				colList:[	
						    { label:'key',name:'id', index: 'id',hidden:true,key:true},						   
						    { label : '学号', name : 'xh', index : 'xh', width : '8%'},
							{ label : '姓名', name : 'xm', index : 'xm', width : '5%'},
							{ label : '学院', name : 'xymc', index : 'xymc', width : '8%'},
							{ label : '专业', name : 'zymc', index : 'zymc', width : '8%'},
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '8%'},
							{ label : '火车优惠区间', name : 'hcyhk', index : 'hcyhk', width : '12%'}
						 ],
							sortname: "xymc,zymc,bjmc",
				 			sortorder: "desc"
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
		<html:form action="/hcyhk_hcyhqjdr">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="#" onclick="importXX();return false;" class="btn_dr">导入</a>
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
				<span>火车优惠区间列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>