function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


//删除社团综合维护信息
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("stglStzhwh.do?method=delStzhwhxx",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "stglStzhwh.do?method=getStcycjList";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, stzhwhExportData);
}

//导出方法
function stzhwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "stglStzhwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='StjgView(\""
			+ rowObject["stid"] + "\");'>" + cellValue + "</a>";
}

function StjgView(stid) {
	showDialog("社团详细查看", 750, 400, "stglStzhwh.do?method=StzhwhCk&stid="
			+ stid);
}

//社团成员状态维护
function stzhwhCyztwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	}
	var stid = rows[0]["stid"];
	showDialog("社团成员状态维护", 750, 500, "stglStzhwh.do?method=StCyZtwh&stid="
			+ stid);
}

//社团成员状态维护保存
function saveStCyZtwh(){
	var len = jQuery("#tbody_ztwh tr").length;
	if(len == 1){
		showAlert("该社团无成员信息，不允许执行保存操作！");
		return false;
	}
	var flag = true;
	jQuery("#tbody_ztwh tr").each(function(b){
		if(b != 0){
			var temptnzt=jQuery(this).find("select[name='tnzt']").val();
			if(temptnzt == ''){
				flag = false;
				showAlert("团内状态不可为空！");
				return false;
			}
		}
	});
	if(flag == false){
		return false;
	}
	var url = "stglStzhwh.do?method=saveStCyZtwh"
		ajaxSubFormWithFun("StzhwhForm", url, function(data) {
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

//社团成员成绩评定
function stzhwhCycjpd(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	}else if(rows[0]["bz"] != '1'){
		showAlertDivLayer("该社团不在有效活动时间内，无法评定成绩！");
		return false;
	}
	var stid = rows[0]["stid"];
	showDialog("社团成员成绩评定", 750, 500, "stglStzhwh.do?method=StCyCjpd&stid="
			+ stid);
}

//社团成员成绩评定保存
function saveStCyCjpd(){
	var len = jQuery("#tbody_ztwh tr").length;
	if(len == 1){
		showAlert("该社团无成员信息，不允许执行保存操作！");
		return false;
	}
	var flag = true;
	jQuery("#tbody_ztwh tr").each(function(b){
		if(b != 0){
			var tempcjpd=jQuery(this).find("select[name='cjpd']").val();
			if(tempcjpd == ''){
				flag = false;
				showAlert("成员成绩不可为空！");
				return false;
			}
		}
	});
	if(flag == false){
		return false;
	}
	var url = "stglStzhwh.do?method=saveStCyCjpd"
		ajaxSubFormWithFun("StzhwhForm", url, function(data) {
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

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='CycjView(\""
			+ rowObject["stid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//查看学生ajaxurl跳转
function CycjView(id, xh) {
	showDialog("社团成员明细查询", 770, 400, "stglStzhwh.do?method=Cycjck&stid="
			+ id + "&xh=" + xh);
}