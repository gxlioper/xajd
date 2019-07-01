//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "gypynew_gypyjg.do?method=addJg";
	var title = "增加星级寝室";
	showDialog(title, 770, 550, url);
}

//增加修改结果保存
function saveSq(){
	var ids = "lddm"+"-"+"qsh"+"-"+"sqxj"+"-"+"gxsj"+"-sqsj";
	if(!checkNotNull(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "gypynew_gypyjg.do?method=saveJg";
	ajaxSubFormWithFun("GypyJgForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["sqid"] + "\");'>" + cellValue
			+ "</a>";
}

function View(sqid) {
	showDialog("星级寝室结果查看", 770, 480, "gypynew_gypyjg.do?method=viewJg&jgid="
			+ sqid);
}

//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}  else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gypynew_gypyjg.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if ("0" != rows[0]['sjly']) {
			showAlertDivLayer("审核流程数据不能被修改！");
			return false;
		}
		if("1" == rows[0]["cxzt"]){
			showAlertDivLayer("撤星的数据不能被修改！");
			return false;
		}
		var url = 'gypynew_gypyjg.do?method=editJg&jgid=' + rows[0]["jgid"];
		var title = "星级寝室结果修改";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "gygl_gypynew_gypyjg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jgExportData);
}

//导出方法
function jgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gypynew_gypyjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//保存撤星
function saveCx(){
	var ids = "cxsj";
	if(!checkNotNull(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "gypynew_gypyjg.do?method=saveCx";
	ajaxSubFormWithFun("GypyJgForm", url, function(data) {
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

//撤星
function cancelcx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "gypynew_gypyjg.do?method=cancelXj&jgid="+rows[0]["jgid"];
	showDialog("撤星", 770, 480, url);
}

//查看学生链接
function qshLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["jgid"] + "\");'>" + cellValue
			+ "</a>";
}

function View(jgid) {
	showDialog("星级寝室结果查看", 770, 480, "gypynew_gypyjg.do?method=viewJg&jgid="
			+ jgid);
}


