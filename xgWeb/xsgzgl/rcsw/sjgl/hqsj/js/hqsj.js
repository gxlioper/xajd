var DCCLBH = "rcswx_sjgl_hqsj.do";//dcclbh,导出功能编号
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "hqsj.do?method=hqsjAdd";
	var title = "后勤数据新增";
	showDialog(title,800,500,url);
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}
	else {	
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("hqsj.do?method=hqsjDel", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	}else{
		var url = 'hqsj.do?method=hqsjUpdate&id='+ rows[0]["id"]
		+ '&xh=' + rows[0]["xh"];
		var title = "修改考勤数据";
		showDialog(title, 800, 500, url);
	}
}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, hqsjExportData);
}

//导出方法
function hqsjExportData() {	
	setSearchTj();//设置高级查询条件
	var url = "hqsj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();	
}



function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='hqsjView(\""
	+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
	+ "</a>";
}

//查看
function hqsjView(id,xh){
	showDialog('后勤数据查看',800,400,'hqsj.do?method=hqsjView&id='+id + "&xh=" + xh);
}

function importHqsj(){
	toImportDataNew("IMPORT_HQSJ");
	return false;
}

