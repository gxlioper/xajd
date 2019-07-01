<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/kyxmsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "科研项目列表",
				pager : "pager",
				url : "qgzx_kycxkyxmsq.do?method=getKyxmsqList&type=query",
				colList : [
							{ label : 'key', name : 'xmid', index : 'xmid',key:true,hidden : true },
							{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true },
							{ label : 'shzt', name : 'shzt', index : 'shzt',hidden : true },
							{ label : '项目编号', name : 'xmbh', index : 'xmbh', width : '8%' },
							{ label : '项目名称', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmmcLink},
							{ label : '项目属性', name : 'xmsxmc', index : 'xmsxmc', width : '10%' },
							{ label : '项目负责人', name : 'xm', index : 'xm', width : '10%' },
							{ label : '项目所属单位', name : 'ssdwmc', index : 'ssdwmc', width : '12%' },
							{ label : '项目开始时间', name : 'kssj', index : 'kssj', width : '10%' },
							{ label : '项目结束时间', name : 'jssj', index : 'jssj', width : '10%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' }
							]
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
		<html:form action="/qgzx_kycxkyxmsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_sz" onclick="xmwh();return false;"  >项目维护</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cywh();return false;" class="btn_sz" >成员维护</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc" >提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
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
				<span>科研项目列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
