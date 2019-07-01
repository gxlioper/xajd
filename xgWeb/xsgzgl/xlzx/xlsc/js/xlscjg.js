//高级查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加方法
function addXlscjg(){
	var url = "xlzx_xlscjg.do?method=xlscjgAdd";
	var title = "新增心理筛查结果";
	showDialog(title,700,385,url);
}

//增加保存方法
function addSave(){
	var xh = jQuery("#xh").val();
	var scrq = jQuery("#scrq").val();
	var scl = jQuery("#scl").val();
	var sds = jQuery("#sds").val();
	var sas = jQuery("#sas").val();
	var bkyy = jQuery("#bkyy").val();
	var bkjl = jQuery("#bkjl").val();
	var sfxyyt = jQuery("#sfxyyt").val();
	var sfyyt = jQuery("#sfyyt").val();
	if(xh==null||xh==""){
		showAlert("请选择一个学生！");
		return false;
	}
	if(scrq==null||scrq==""){
		showAlert("请填筛查日期！");
		return false;
	}
	if(scl==null||scl==""){
		showAlert("请填写SCL90结果！");
		return false;
	}
	if(sds==null||sds==""){
		showAlert("请填写SDS结果！");
		return false;
	}
	if(sas==null||sas==""){
		showAlert("请填写SAS结果！");
		return false;
	}
	if(bkyy==null||bkyy==""){
		showAlert("请填写贝克抑郁结果！");
		return false;
	}
	if(bkjl==null||bkjl==""){
		showAlert("请填写贝克焦虑结果！");
		return false;
	}
	if(sfxyyt==null||sfxyyt==""){
		showAlert("请选择学生是否需要参加约谈！");
		return false;
	}
	if(sfyyt==null||sfyyt==""){
		showAlert("请选择学生是否已参加约谈！");
		return false;
	}
	var url="xlzx_xlscjg.do?method=xlscjgAdd&type=save";
	ajaxSubFormWithFun("xlscjgForm", url, function(data){
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

//修改方法
function updateXlscjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	var title = "修改心理筛查结果";
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog(title,700,385,'xlzx_xlscjg.do?method=xlscjgUpdate&id='+rows[0]["id"]);
	}
}

//修改保存
function updateSave(){
	var xh = jQuery("#xh").val();
	var scrq = jQuery("#scrq").val();
	var scl = jQuery("#scl").val();
	var sds = jQuery("#sds").val();
	var sas = jQuery("#sas").val();
	var bkyy = jQuery("#bkyy").val();
	var bkjl = jQuery("#bkjl").val();
	var sfxyyt = jQuery("#sfxyyt").val();
	var sfyyt = jQuery("#sfyyt").val();
	if(xh==null||xh==""){
		showAlert("请选择一个学生！");
		return false;
	}
	if(scrq==null||scrq==""){
		showAlert("请填筛查日期！");
		return false;
	}
	if(scl==null||scl==""){
		showAlert("请填写SCL90结果！");
		return false;
	}
	if(sds==null||sds==""){
		showAlert("请填写SDS结果！");
		return false;
	}
	if(sas==null||sas==""){
		showAlert("请填写SAS结果！");
		return false;
	}
	if(bkyy==null||bkyy==""){
		showAlert("请填写贝克抑郁结果！");
		return false;
	}
	if(bkjl==null||bkjl==""){
		showAlert("请填写贝克焦虑结果！");
		return false;
	}
	if(sfxyyt==null||sfxyyt==""){
		showAlert("请选择学生是否需要参加约谈！");
		return false;
	}
	if(sfyyt==null||sfyyt==""){
		showAlert("请选择学生是否已参加约谈！");
		return false;
	}
	var url="xlzx_xlscjg.do?method=xlscjgUpdate&type=update";
	ajaxSubFormWithFun("xlscjgForm", url, function(data){
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

//删除方法
function deleteXlscjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("xlzx_xlscjg.do?method=xlscjgDelete",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

//点击学号链接
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewXlscjg(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

//查看方法
function viewXlscjg(id,cellValue){
	var title = "查看心理筛查结果";
	showDialog(title,700,365,'xlzx_xlscjg.do?method=xlscjgView&id='+id+"&xh="+cellValue,null);
}

//dcclbh,导出功能编号
var DCCLBH = "xg_xlzx_xlscjg.do";

//自定义导出
function exportConfig(){
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData(){
	setSearchTj();//设置高级查询条件
	var url = "xlzx_xlscjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_XLZX_XLSCJG");
	return false;
}