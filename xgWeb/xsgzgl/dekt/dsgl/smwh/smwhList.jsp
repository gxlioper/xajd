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
            var gridSetting = {
                caption:"书单列表",
                pager:"pager",
                url:"dekt_smwhgl.do?method=smwhList&type=query",
                colList:[
                    {label:'key',name:'smid', index: 'smid',key:true ,hidden:true},
                    {label:'年级',name:'nj', index: 'nj',width:'8%'},
                    {label:'书名',name:'ssm', index: 'ssm',width:'10%',formatter:xhLink},
                    {label:'出版社',name:'cbs', index: 'cbs',width:'15%'},
                    {label:'作者',name:'author', index: 'author',width:'8%'},
                    {label:'类型',name:'lx', index: 'lx',width:'8%'},
                    {label:'是否推荐',name:'sftjmc', index: 'sftjmc',width:'8%'},
                    {label:'电子书链接',name:'ebook', index: 'ebook',width:'15%',formatter:ebookLink}
                ]
            };
jQuery(function(){

	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}
function view(smid) {
	showDialog("查看书本信息",700,300, "dekt_smwhgl.do?method=view&smid=" + smid);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='view(\""
			+ rowObject["smid"] + "\");'>" + cellValue
			+ "</a>";
}
function ebookLink(cellValue, rowObject) {
    if(cellValue == null) cellValue='';
return "<a href='javascript:void(0);'  onclick='ebook(\""
			+ rowObject["ebook"] + "\");'>" + cellValue + "</a>";
}
function ebook (url){
    if(url.indexOf("http://") == -1 || url.indexOf("https://") == -1 ){
        url = "https://"+url;
	}
	window.open(url);
}
function add(){
	var url = "dekt_smwhgl.do?method=add";
	var title = "增加书本信息";
	showDialog(title,700,500,url);
	
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
			var url = 'dekt_smwhgl.do?method=update&smid=' + rows[0]["smid"];
			showDialog("修改书本信息", 700,500, url);
	}
}


//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("dekt_smwhgl.do?method=del", {
					values : ids.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


//新版导入
function dr() {
	toImportDataNew("IMPORT_N820104_SMWH");
	return false;
}

var DCCLBH = "xg_dekt_smwhgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

// 导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var url = "dekt_smwhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
		
</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_smwhgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a>
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
				<span>读书信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
