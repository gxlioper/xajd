var DCCLBH = "pjpy_zyjgl_zyjwh.do";//dcclbh,导出功能编号
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "pjpy_zyjwhwh.do?method=addGrzyj";
	var title = "专业奖新增";
	showDialog(title,800,500,url);
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}
	else {
		var flag = false;
		jQuery.each(rows,function(i,n) {
			if(n['rddjmc'] != '未认定') {
				flag = true;
				return false;						
				}			
			})
		if(flag) {
			showAlertDivLayer("评定等级已被认定不能删除");
			return false;
		}	
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("pjpy_zyjwhwh.do?method=delGrzyj", {
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
	}else {
		var flag = false;
		jQuery.each(rows,function(i,n) {
			if(n['rddjmc'] != '未认定') {
				flag = true;
				return false;						
				}			
			})
		if(flag) {
			showAlertDivLayer("评定等级已被认定不能修改");
			return false;
		}
		var url = 'pjpy_zyjwhwh.do?method=updateGrzyj&id='+ rows[0]["id"]
		+ '&xh=' + rows[0]["xh"];
		var title = "修改专业奖信息";
		showDialog(title, 800, 500, url);
	}

}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xszbbjgExportData);
}

//导出方法
function xszbbjgExportData() {	
	setSearchTj();//设置高级查询条件
	var url = "pjpy_zyjwhwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();	
}

function rending() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要认定的记录！");
	}else {
		var url = 'pjpy_zyjwhwh.do?method=rendingGrzyj&id='+ rows[0]["id"]
		+ '&xh=' + rows[0]["xh"];
		var title = "等级认定";
		showDialog(title, 800, 600, url);
	}
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='grzyjView(\""
	+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
	+ "</a>";
}

function grzyjView(id,xh){
	showDialog('个人专业奖维护查看',800,600,'pjpy_zyjwhwh.do?method=grzyjView&id='+id + "&xh=" + xh);
}

