var gridSetting = {
	caption : "资助发放列表",
	pager : "pager",
	url : "xszz_zzdyzzbtff.do?method=getBtffList&type=query",
	colList : [ 
	    	   {label : 'id',name : 'id',index : 'id',key : true,hidden:true},
	    	   {label : '学号',name : 'xh',index : 'xh',width : '15%' ,formatter : xhLink },
	    	   {label : '姓名',name : 'xm',index : 'xm',width : '8%'},
	    	   {label : '学年',name : 'pdxn',index : 'pdxn',width : '10%'},
	    	   {label : '学期',name : 'xqmc',index : 'xqmc',width : '10%'},
	    	   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '10%'},
	    	   {label : '学院',name : 'xymc',index : 'xymc',width : '20%'},
	    	   {label : '发放月份',name : 'ffyf',index : 'ffyf',width : '10%'},
	    	   {label : '发放金额<元>',name : 'ffje',index : 'ffje',width : '10%'}
	    	   ]
}

jQuery(function() {
	gridSetting["params"]=getSuperSearch();
	jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewZzmd(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function viewZzmd(id) {
	showDialog("资助发放查看", 800, 500, "xszz_zzdyzzbtff.do?method=viewBtff&id="
			+ id);
}

function btff() {
	var url = 'xszz_zzdyzzbtff.do?method=btff';
	var title = "补贴发放";
	showDialog(title, 450, 250, url);
}
//自定义导出 功能
var DCCLBH = "xszz_zzdy_zzbtff.do";
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

//导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "xszz_zzdyzzbtff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

