var ids = "xh-jykssj-jyjzsj";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='HjjyJgView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}
function HjjyJgView(jgid) {
	showDialog("户籍借用查看", 650, 450, "hjjyJyjg.do?method=HjjyJgView&jgid="+jgid);
}
function saveHjjyJg(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","备注","500")){
	var url = "hjjyJyjg.do?method=saveHjjyJg&type=" + type;
	ajaxSubFormWithFun("HjjyJgForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
	}
}

//修改
function editHjjyJg(type) {
	if(checkNull(ids)&&checkTextAreaLength("bz","备注","500")){
	var url = "hjjyJyjg.do?method=saveEditHjjyJg&type=" + type;
	ajaxSubFormWithFun("HjjyJgForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
	}
}

function add() {
	var url = "hjjyJyjg.do?method=addHjjyJg";
	var title = "户籍借用";
	showDialog(title, 650, 450, url);
}
function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'hjjyJyjg.do?method=editHjjyJg&jgid=' + rows[0]["jgid"];
		var title = "户籍借用修改";
		showDialog(title, 650, 450, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else{
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("hjjyJyjg.do?method=delHjjyJg", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}
//借用归还
function jygh(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
    var flag = true;
	if (ids.length != 1){
		showAlertDivLayer("请选择一条您要操作的记录！");
	}else if("1"==rows[0]["jyzt"]){
		showAlertDivLayer("请选择未归还的记录！");
	} else {
		showDialog("户籍归还",650,300,"hjjyJyjg.do?method=jygh&jgid="+ids.toString());
	}
}

var DCCLBH = "rcsw_hjjy_jyjg.do";//dcclbh,导出功能编号

function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_N158605_HJJYJG");
	return false;

}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jyjgExportData);
}

//导出方法
function jyjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "hjjyJyjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}