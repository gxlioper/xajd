<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"读书管理列表",
			pager:"pager",
			url:"dekt_dsgljg.do?method=dsglJgList&type=query",
			colList:[
			   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%'},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'书院',name:'symc', index: 'symc',width:'15%'},
			   {label:'学院',name:'xymc', index: 'xymc',width:'15%'},
			   {label:'专业',name:'zymc', index: 'zymc',width:'15%'}	,
			   {label:'阅读本数',name:'ydbs', index: 'ydbs',width:'15%',formatter:xhLink}	
			]
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}


function viewDsxq(xh) {
	showDialog("查看阅读详情", 720, 520, "dekt_dsgljg.do?method=viewydxq&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewDsxq(\""
			+ rowObject["xh"] + "\");'>" + cellValue
			+ "</a>";
}



var DCCLBH = "xg_dekt_dsgljg.do";//dcclbh,导出功能编号
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var url = "dekt_dsgljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function Print(){
//var url = "hdgl_hdxx.do?method=xscjdPri";
//window.open(url);         
//location.href =url;
}


		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_dsgljg">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!--<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>删除</a>
						</li>
						</logic:equal>
						--><li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<li><a href="#" class="btn_dc" onclick="Print();return false;">成绩单</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>读书管理列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
