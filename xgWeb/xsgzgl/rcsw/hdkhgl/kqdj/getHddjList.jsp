<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hdkhgl/kqdj/js/kqdj.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "活动参加学生明细",
				pager : "pager",
				url : "hdkhgl_hddj.do?method=getHddjList&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '13%'
				}, {
					label : '学期',
					name : 'xq',
					index : 'xq',
					width : '10%'
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '5%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '17%'
				},{
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '13%'
				},{
					label : '活动时间',
					name : 'hdsj',
					index : 'hdsj',
					width : '23%'
				},{
					label : '是否参加',
					name : 'sfcj',
					index : 'sfcj',
					width : '5%'
				},{
					label : 'hdjgid',
					name : 'hdjgid',
					index : 'hdjgid',
					hidden : true
				}],
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
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="XsKqdjWh();return false;"  >考勤登记</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>活动参加学生明细&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
