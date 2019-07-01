

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function bjmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='KqjgView(\""
			+ rowObject["jgid"] + "\",\"" + rowObject["bjdm"] + "\");'>" + cellValue
			+ "</a>";
}

//查看
function KqjgView(jgid, bjdm) {
	showDialog(jQuery("#gnmkmc").val()+"查看", 750, 550, "zwzxkqKqjg.do?method=viewKqjg&jgid="
			+ jgid + "&bjdm=" + bjdm);
}
/**
 *缺勤学生信息查看
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='qqxsView(\""
			+ rowObject["id"] + "\",\"" + rowObject["xh"] + "\");'>" + cellValue
			+ "</a>";
}

//查看
function qqxsView(id, xh) {
	showDialog(jQuery("#gnmkmc").val()+"查看", 600, 350, "zwzxkqKqjg.do?method=qqxsView&sqid="
			+ id + "&xh=" +xh);
}

// 保存
function saveKqjg(type) {
	var flg=true;
	var objArr= [];
	jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
		var obj = {};
		if (flg){
			var qqlx = jQuery(n).find("select[name=qqlxdm] option:selected").val();
			var xh= jQuery(n).find("td").eq(1).text();
			var kkjs = jQuery(n).find("input[name=kkjs]").val();
			var ylzd1 = jQuery(n).find("input[name=ylzd1]").val();
			obj.xh=xh;
			obj.kkjs=kkjs;
			obj.qqlxdm=qqlx;
			obj.ylzd1=ylzd1;
			objArr.push(obj);
			flg = (qqlx != "" );
		}
	});
	var validateFlag = true;
	validateFlag = checkKqxx(flg,null);
	if(validateFlag){
	var objStr = JSON.stringify(objArr);
	jQuery("#objStr").val(objStr);
	var url = "zwzxkqKqjg.do?method=saveKqjg&type="+type;
	ajaxSubFormWithFun("ZwzxKqjgForm", url, function(data) {
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
}
//增加
function add() {
	var url = "zwzxkqKqjg.do?method=addKqjg";
	var title = jQuery("#gnmkmc").val()+"增加";
	showDialog(title, 800, 500, url);
}
function checkXh(){
	if(jQuery("#xh").val()==""){
		showAlert("请先选择学生！");
		return false;
	}
}

//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'zwzxkqKqjg.do?method=editKqjg&jgid=' + rows[0]["jgid"];
		var title = jQuery("#gnmkmc").val()+"修改";
		showDialog(title, 800, 500, url);
	}
}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("zwzxkqKqjg.do?method=delKqjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "rcsw_zwzxkq_kqjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, zwzxkqKqjgExportData);
}

//导出方法
function zwzxkqKqjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zwzxkqKqjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//缺勤学生导出 功能
var qqxsDCCLBH = "rcsw_zwzxkq_xskqxx.do";
function qqxsExportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(qqxsDCCLBH, qqxsExportData);
}

//导出方法
function qqxsExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zwzxkqKqjg.do?method=qqxsExportData&dcclbh=" + qqxsDCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//缺勤学生导入
function qqxsDr(){
	toImportData("IMPORT_QGZX_QQXS");
	return false;
}

function kqxxTb() {
	var ids = jQuery("#dataTable").getSeletIds();
		showConfirmDivLayer("您确定要同步缺勤人数信息吗？",{"okFun":function(){
			jQuery.post("zwzxkqKqjg.do?method=qqxsxxTb",{},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
}