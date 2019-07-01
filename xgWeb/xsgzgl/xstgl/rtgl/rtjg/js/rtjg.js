//查询
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtjgView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\",\""
			+ rowObject["stid"] + "\");'>" + cellValue
			+ "</a>";
}

function xmmcLink(cellValue, rowObject) {
	if(jQuery("input[name='usertype']").val() != "stu"){
		return "<a href='javascript:void(0);' class='name' onclick='RtjgViewv2(\""
		+ rowObject["stid"] + "\");'>" + cellValue
		+ "</a>";
	}else{
		return cellValue;
	}
}


//查看学生ajaxurl跳转
function RtjgView(id, xh,stid) {
	showDialog("社团成员明细查询", 770, 440, "stglRtjg.do?method=Rtjgck&rtid="
			+ id + "&xh=" + xh+"&stid="+stid);
}

//查看学生v2(勾选一条学生记录，点击查看)
function RtjgViewv2(stid){
	showDialog("社团成员明细查询", 770, 440, "stglRtjg.do?method=Rtjgck"
			+"&stid="+stid);
}

//删除社团成员信息
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
			jQuery.post("stglRtjg.do?method=delCyxx",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "stgl_rtgl_rtjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, stcyxxExportData);
}

//导出方法
function stcyxxExportData() {
	setSearchTj();//设置高级查询条件
	var url = "stglRtjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_RTJG");
	return false;
}