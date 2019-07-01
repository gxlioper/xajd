<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/dektxfsq/js/xfsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "申请查询",
				pager : "pager",
				url : "dekt_xfsq.do?method=getXfsqList",
				colList : [
					{label:'sqid',name:'sqid',index :'sqid',key:true,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'认定内容',name:'rdnr', index: 'rdnr',width:'44%'},
					{label:'获奖时间',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'12%'},
                    {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
					{label:'审批流程',name:'splc', index: 'splc',hidden:true}
				],
				sortname: "hjsj",
			 	sortorder: "desc"
			};
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
		<html:form action="/dekt_xmwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_shuc" onclick="submitBusi();return false;">提交</a></li>
						<li><a href="#" class="btn_dr" onclick="xfsqZdyImport();return false;">导入</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<li><a href="javascript:void(0);" onclick="shLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>申请查询</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
