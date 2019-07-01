function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
var DCCLBH = "rcsw_xshdgl_xshdgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xshdglExportData);
}

//导出方法
function xshdglExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xshdgl_xshdgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_XSHDGL");
	return false;
}


//增加
function add() {
	var url = "xshdgl_xshdgl.do?method=addXshd";
	var title = "活动增加";
	showDialog(title, 770, 550, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'xshdgl_xshdgl.do?method=editXshd&sqid=' + rows[0]["sqid"];
		var title = "活动修改";
		showDialog(title, 770, 550, url);
	}
}

//增加修改结果保存
function saveXshdgl(type){
	var ids = "hdmc"+"-"+"hdsj"+"-"+"hddd"+"-"+"zbdwdm"+"-"+"xbdwdm"+"-"+"cbdwdm"+"-"+"hdfzr"+"-"+"cyry";
	if(checkNotNull(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！");
		return false;
	}
	if(jQuery.trim(jQuery("#cyry").val()).length > 500){
		showAlert("参与人员不得超过500字！");
		return false;
	}
	if(jQuery.trim(jQuery("#hdjj").val()).length > 1000){
		showAlert("活动简介不得超过1000字！");
		return false;
	}
	var url = "xshdgl_xshdgl.do?method=saveXshd&type=" + type;
	ajaxSubFormWithFun("XshdglForm", url, function(data) {
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

//查看链接
function hdmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xshdglView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看ajaxurl跳转
function xshdglView(sqid, xh) {
	showDialog("活动查看", 770, 450, "xshdgl_xshdgl.do?method=ckXshd&sqid="
			+ sqid);
}
//删除结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xshdgl_xshdgl.do?method=delXshdgl",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function changeBmmc(){
	var autoSetting1 = {
		dataTable:"ZXBZ_XXBMDM",
		dataField:"bmmc",
		sqlTj: "",
		scrollHeight:165,
		width:220
	}
	var autoSetting2= {
			dataTable:"ZXBZ_XXBMDM",
			dataField:"bmmc",
			sqlTj: "",
			scrollHeight:165,
			width:220
		}
	var autoSetting3= {
			dataTable:"ZXBZ_XXBMDM",
			dataField:"bmmc",
			sqlTj: "",
			scrollHeight:165,
			width:220
		}
	// 模糊搜索下拉【项目名称】
	jQuery("#zbdwdm").setAutocomplete(autoSetting1);
	jQuery("#xbdwdm").setAutocomplete(autoSetting2);
	jQuery("#cbdwdm").setAutocomplete(autoSetting3);
	
}