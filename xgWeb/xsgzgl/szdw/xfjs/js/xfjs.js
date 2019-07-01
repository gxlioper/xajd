/* 导入 */
function importXf(){
	toImportDataNew("IMPORT_N471301_XFJS");
	return false;
}
var DCCLBH = "szdw_xfjswh.do";// dcclbh,导出功能编号
function exportConfig() {
	customExport(DCCLBH, xfExportData);
}
//导出方法
function xfExportData() {
	setSearchTj();
	var url = "szdw_xfjsgl.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//增加
function add() {
	var url = "szdw_xfjsgl.do?method=addXfjs";
	var title = "学风考勤新增";
	showDialog(title, 700, 430, url);
}
function checkNumBer(obj){
	obj.value=obj.value.replace(/[^\d]/g,'');
}


function showBj(){
		showDialog('请选择一个班级',800,500,'szdw_xfjsgl.do?method=bjManage');
}

//增加保存
function addSave() {
	var xn = jQuery("#xn").val();
	if(xn == null || xn == ''){
		showAlert("请选择学年");
		return false;
	}
	var bj = jQuery("#bjdm").val();
	if(bj == null || bj == ''){
		showAlert("请选择班级");
		return false;
	}
	var ydxsrs = jQuery("#ydxsrs").val();
	if(ydxsrs == null || ydxsrs == ''){
		showAlert("请填写应到学生人数");
		return false;
	}
	var sjcqrs = jQuery("#sjcqrs").val();
	if(sjcqrs == null || sjcqrs == ''){
		showAlert("请填写实际出勤人数");
		return false;
	}
	var jcsj = jQuery("#jcsj").val();
	if(jcsj == null || jcsj == ''){
		showAlert("请选择检查时间");
		return false;
	}
	var jcjc = jQuery("#jcjc").val();
	if(jcjc == null || jcjc == ''){
		showAlert("请填写检查节次");
		return false;
	}
	
	var url = "szdw_xfjsgl.do?method=addXfjs&type=save";
	ajaxSubFormWithFun("xfjsForm", url, function(data) {
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
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'szdw_xfjsgl.do?method=updateXf&id=' + rows[0]["id"];
		showDialog( "学风考勤修改", 700, 430, url);
	}

}

//修改保存
function updateSave() {
	var xn = jQuery("#xn").val();
	if(xn == null || xn == ''){
		showAlert("请选择学年");
		return false;
	}
	var bj = jQuery("#bjdm").val();
	if(bj == null || bj == ''){
		showAlert("请选择班级");
		return false;
	}
	var ydxsrs = jQuery("#ydxsrs").val();
	if(ydxsrs == null || ydxsrs == ''){
		showAlert("请填写应到学生人数");
		return false;
	}
	var sjcqrs = jQuery("#sjcqrs").val();
	if(sjcqrs == null || sjcqrs == ''){
		showAlert("请填写实际出勤人数");
		return false;
	}
	var jcsj = jQuery("#jcsj").val();
	if(jcsj == null || jcsj == ''){
		showAlert("请选择检查时间");
		return false;
	}
	var jcjc = jQuery("#jcjc").val();
	if(jcjc == null || jcjc == ''){
		showAlert("请填写检查节次");
		return false;
	}
	
	var url = "szdw_xfjsgl.do?method=updateXf&type=save";
	ajaxSubFormWithFun("xfjsForm", url, function(data) {
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
function bjLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewXf(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function viewXf(id,cellValue){
	showDialog('学风考勤查看',700,430,'szdw_xfjsgl.do?method=viewXf&id='+id,null);
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
				jQuery.post("szdw_xfjsgl.do?method=delXf", {
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
