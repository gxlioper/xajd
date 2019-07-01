function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "xzhcp_zcjg.do?method=add";
	var title = "综合测评登记";
	showDialog(title, 770, 550, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
	if(rows[0]['sjly'] != '0'){
		showAlertDivLayer("审核流程过来的数据不能修改！");
		return false;
	}
	var url = "xzhcp_zcjg.do?method=update&sqid="+rows[0]["sqid"];
	var title = "综合测评登记";
	showDialog(title, 770, 550, url);
	}
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["sjly"] != "0"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer("审核流记录不能被删除");
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xzhcp_zcjg.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}




var DCCLBH = "pjpy_xzhcp_zcjg.do";
//自定义导出 功能
function exportConfig(){
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jgExportData);
}

//导出方法
function jgExportData(){
	setSearchTj();//设置高级查询条件
	var url = "xzhcp_zcjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function sqView(sqid, xh) {
	showDialog("查看", 770, 500, "xzhcp_zcjg.do?method=view&sqid="
			+ sqid + "&xh=" + xh);
}

function printDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xzhcp_zcdj.do?method=getSqb";
		url += "&sqid=" + ids+"&flag=jg";
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xzhcp_zcdj.do?method=getSqbTy";
		url += "&value=" + ids+"&flag=jg";
		window.open(url);
	}
}

function printDjbAhny(){
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xzhcp_zcjg.do?method=getSqbAhny";
		url += "&sqid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "xzhcp_zcjg.do?method=getSqbTyAhny";
		url += "&value=" + ids;
		window.open(url);
	}
}
