/* 导入 */
function dr(){
	toImportDataNew("IMPORT_N441201_HMDGL");
	return false;
}
var DCCLBH = "qgzx_hmdgl.do";// dcclbh,导出功能编号
function exportConfig() {
	customExport(DCCLBH, xfExportData);
}
//导出方法
function xfExportData() {
	setSearchTj();
	var url = "qgzxhmdgl.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//增加
function addHmd() {
	var url = "qgzxhmdgl.do?method=addHmd";
	var title = "黑名单增加";
	showDialog(title, 500, 300, url);
}

//增加保存
function saveHmd() {

	if(jQuery("#dwid").val() == ""){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	var url = "qgzxhmdgl.do?method=addHmd&type=save";
	ajaxSubFormWithFun("hmdglForm", url, function(data) {
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


//修改
function xgHmd() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'qgzxhmdgl.do?method=xgHmd&id=' + rows[0]["id"]+'&xh=' + rows[0]["xh"];
		showDialog( "黑名单修改", 700, 500, url);
	}

}

//修改保存
function saveXgHmd() {
	var checkId ="xh-gwmc-yrdw-sgsj-lgsj";
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	var url = "qgzxhmdgl.do?method=xgHmd&type=update";
	ajaxSubFormWithFun("hmdglForm", url, function(data) {
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

//单个查看
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckHmd(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function ckHmd(id,xh){
	showDialog('黑名单查看',700,430,'qgzxhmdgl.do?method=ckHmd&id='+id+ "&xh=" + xh,null);
}


function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var cs=jQuery("#"+id[i]).val();
		if(cs==null||""==cs){
			return false;
		}
	}
	return true;
}

// 删除
function delHmd() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("qgzxhmdgl.do?method=delHmd", {
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
