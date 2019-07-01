//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


//导入
function importConfig(){
	toImportDataNew("IMPORT_RCSW_TXHD_HDKHGL");
	return false;
}

//成绩评定
function cjpd(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要评定的记录！");
		return false;
	}
	var xmdm = rows[0]["xmdm"];
	showDialog("活动成绩评定", 750, 500, "txhd_hdkhgl.do?method=khcjPd&hdxmbh="
			+ xmdm);
}

//保存考核成绩
function saveKhcj(){
	var len = jQuery("#tbody_cjpd tr").length;
	if(len == 1){
		showAlert("该活动无学生参加，不允许执行保存操作！");
		return false;
	}
	var flag = true;
	jQuery("#tbody_cjpd tr").each(function(b){
		if(b != 0){
			var tempcjpd=jQuery(this).find("select[name='sfhdxf']").val();
			if(tempcjpd == ''){
				flag = false;
				showAlert("学分不可为空！");
				return false;
			}
		}
	});
	if(flag == false){
		return false;
	}
	var url = "txhd_hdkhgl.do?method=savePfwh"
		ajaxSubFormWithFun("hdkhForm", url, function(data) {
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

function show(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='showView(\""
			+ rowObject["xmdm"] + "\");'>" + cellValue
			+ "</a>";
}


//查看学生ajaxurl跳转
function showView(xmdm) {
	showDialog("活动信息查看", 770, 450, "txhd_hdkhgl.do?method=view&hdxmbh="
			+ xmdm );
}

var DCCLBH = "rcsw_txhd_hdkhgl.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, khjgExportData);
}

//导出方法
function khjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "txhd_hdkhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}