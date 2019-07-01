<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/xswsjc/js/xswsjc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学生卫生分",
				pager : "pager",
				url : "gyglnew_xswsjc.do?method=getXswsjcList",
				colList : [
					{label:'jcrcid',name:'jcrcid',index :'jcrcid',key:true,hidden:true},
					{label:'园区',name:'yqmc',index:'yqmc',width:'10%'},
					{label:'楼栋',name:'ldmc',index:'ldmc',width:'13%'},
					{label:'寝室号',name:'qsh',index:'qsh',width:'7%'},
					{label:'床位号',name:'cwh',index:'cwh',width:'5%'},
					{label:'学号',name:'xh',index:'xh',width:'15%'},
					{label:'姓名',name:'xm',index:'xm',width:'10%'},
					{label:'学院',name:'xymc',index:'xymc',width:'15%'},
					{label:'辅导员',name:'fdyxm',index:'fdyxm',width:'10%'},
					{label:'检查日期',name:'jcrq',index:'jcrq',width:'10%'},
					{label:'等级',name:'fs',index:'fs',width:'5%'},
				],
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_pxwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
	<!-- 					 <li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li> -->
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">修改</a></li>
<!-- 						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >导入</a></li> -->
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
				<span>学生卫生分</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
