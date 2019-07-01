var DCCLBH = "pjpy_cpfwh.do";//dcclbh,导出功能编号

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCpfwh(\""
			+ rowObject["id"]+"\");'>" + cellValue
			+ "</a>";
}
function viewCpfwh(id) {
	showDialog("查看", 800, 500, "cpfwh_sq.do?method=viewCpfwh&id="+id);
}

function add() {
	var url = "cpfwh_sq.do?method=addCpfWh";
	var title = "增加";
	showDialog(title, 800, 460, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'cpfwh_sq.do?method=editCpfwh&id=' + rows[0]["id"];
		var title = "修改";
		showDialog(title, 800, 460, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("cpfwh_sq.do?method=delCpfwh", {
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

function saveSqjg(type){
	var ids = null;
	if(type=='add'){
		ids = "xh-fs";
	}else{
		ids = "fs";
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "cpfwh_sq.do?method=saveSqjg&type=" +type;
	ajaxSubFormWithFun("cpfwhForm", url, function(data) {
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

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, cpfwhExportData);
}

//导出方法
function cpfwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "cpfwh_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function downloadTemplate(){
	var url = "cpfwh_sq.do?method=downloadTemplate";
	//url = addSuperSearchParams(url,map);// 设置高级查询参数
	
	jQuery("#cpfwhForm").attr("target","_blank");
	jQuery("#cpfwhForm").attr("action",url);
	jQuery("#cpfwhForm").submit();

}

function dr(){
	toImportDataNew("IMPORT_CPFWH");
	return false;
}

function uploadCpfwh(){
	
	var file = jQuery("#importFile").val();
	var jsonStr = jQuery("#jsonStr").val();
	var map = JSON.parse(jsonStr);
	
	if (file == "")
		return false;
	
	if (file.substring(file.length-4,file.length) != ".xls"){
		showAlert("导入文件只能为.xls格式,请确认！");
		return false;
	}
	
	url = addSuperSearchParams("cpfwh_sq.do?method=importCpfwh",map);// 设置高级查询参数
	jQuery("#cpfwhForm").attr("target","");
	jQuery("#cpfwhForm").attr("action",url).submit();
}

function toImport(){
	//var id = jQuery("#id").val();
	//var jsonStr = jQuery("#jsonStr").val();
	//var map = JSON.parse(jsonStr);
	//map['num']=num; //下载的最大条数
	
	//避免导入数据超内存，控制下载条数
	//jQuery.ajaxSetup({async:false});
//	jQuery.post("xpj_zcfs.do?method=checkDownload&id="+id,map,function(data){
//		if (data == "true"){
//			showDialog("综测分导入",550,250,"xpj_zcfs.do?method=toImportZcfs&id="+id+"&jsonStr="+jsonStr,{close:function(){
//				if (jQuery("#search_go")){
//					jQuery("#search_go").click();
//				}
//			}});
//		} else {
//			showAlertDivLayer("导出数据过多，超出<font color='red'>"+num+"</font>条，请增加查询条件后进行导出！");						
//		}
//	});
//	jQuery.ajaxSetup({async:true});
	
	showDialog("综测分导入",550,250,"cpfwh_sq.do?method=toImportCpfwh",{close:function(){
		if (jQuery("#search_go")){
			jQuery("#search_go").click();
		}
	}});
}
