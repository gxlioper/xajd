function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "zjly_xsqj_jscx.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "xsqj_jscx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='qjjgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\",\""
			+ rowObject["sjly"] + "\",\""
			+ rowObject["actinstid"] + "\",\""
			+ rowObject["qsl"] + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function qjjgView(id, xh,sjly,actinstid,gjxxid) {

	if(sjly=='gj'){
		showDialog('学生公假信息',600,500,'xsgjgl.do?method=gjxxView&gjxxid='+gjxxid+"&xh="+xh,null);
	}else{
		showDialog("请假查看", 800, 500, "xsqj_jscx.do?method=Qjsqck&id="
				+ id + "&xh=" + xh+"&actinstid="+actinstid);
	}
}
//自定义导出 功能
function exportConfig_tjcx() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas_tjcx);
}

//导出方法
function ExportDatas_tjcx() {
	setSearchTj();//设置高级查询条件
	var url = "xsqj_jscx.do?method=exportData&flag=tjcx&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}