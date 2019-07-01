function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "cxdd_pyjg.do?method=add";
	var title = "评语结果增加";
	showDialog(title, 660, 450, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'cxdd_pyjg.do?method=edit&xsid=' + rows[0]["xsid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "评语结果修改";
		showDialog(title, 660, 450, url);
	}
}


//增加修改结果保存
function saveCxjg(type){
	var ids = ""
	if( type == "update"){
	   ids = "xn"+"-"+"xq"+"-"+"pj"+"-"+"py";
	}else{
		ids ="xh"+"-"+ "xn"+"-"+"xq"+"-"+"pj"+"-"+"py";
	}
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(checkzs() == false){
		showAlert("评语控制在80字-120字之间！");
		return false;
	}
	var url = "cxdd_pyjg.do?method=saveCxjg&type=" + type;
	ajaxSubFormWithFun("CxddJgForm", url, function(data) {
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

function checkzs(){
	if(jQuery.trim(jQuery("#py").val()).length > 120 || jQuery.trim(jQuery("#py").val()).length < 80){
		return false;
	}else{
		return true;
	}
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='CxjgView(\""
			+ rowObject["xsid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function CxjgView(xsid, xh) {
	showDialog("评语结果查看", 660, 450, "cxdd_pyjg.do?method=view&xsid="
			+ xsid + "&xh=" + xh);
}

//删除贷款申请
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}  else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("cxdd_pyjg.do?method=delCxjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xsxx_cxdd_pyjg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, CxjgExportData);
}

//导出方法
function CxjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "cxdd_pyjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_CXJG");
	return false;
}
