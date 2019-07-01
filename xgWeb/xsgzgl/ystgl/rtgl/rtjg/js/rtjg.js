var ids = "ystxmmc"+"-"+"sqly"+"-"+"tc";
//查询
function searchRs() {
	var map = getSuperSearch();
	var flag = jQuery("#flag").val();
	if (null!=flag&&flag != "") {
		map["flag"] = flag;
	}else{
		map["flag"] = "wsq";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtjgView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



//查看学生ajaxurl跳转
function RtjgView(id, xh) {
	showDialog("入团结果查看", 770, 450, "ystglRtjg.do?method=viewYstRtjg&rtid="
			+ id + "&xh=" + xh);
}

//删除结果信息
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("ystglRtjg.do?method=delYstRtjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "ystgl_rtgl_rtjg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, RtjgExportData);
}

//导出方法
function RtjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "ystglRtjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_YSTGL_RTJG");
	return false;
}



//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if ("1" == rows[0]['sjly']) {
			showAlertDivLayer("审批流数据不允许修改！");
			return false;
		}
		var url = 'ystglRtjg.do?method=editYstRtjg&rtid=' + rows[0]["rtid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "学生入团结果";
		showDialog(title, 770, 552, url);
	}
}

//增加
function add() {
	var url = "ystglRtjg.do?method=add";
	var title = "学生入团结果";
    showDialog(title, 770, 552, url);
}

//验证字数
function checkzs(obj){
	if(jQuery(obj).val().length > 100){
		showAlert("最大字数为100，现已经超过，请确认！");
		return false;
	}
}


//增加结果保存
function saveYstRtjg(type){
	
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs(jQuery("#sqly")) == false || checkzs(jQuery("#tc")) == false ) {
		return false;
	}
	var url = "ystglRtjg.do?method=saveYstRtjg&type=" + type;
	ajaxSubFormWithFun("YstRtjgForm", url, function(data) {
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





