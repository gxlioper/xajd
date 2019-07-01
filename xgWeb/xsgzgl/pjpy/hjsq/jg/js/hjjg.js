function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "jxjg.do?method=add";
	var title = "奖项结果";
	showDialog(title, 770, 550, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
	if(rows[0]['sjly'] == '1' ){
		showAlertDivLayer("审核流程过来的数据不能修改！");
		return false;
	}
	var url = "jxjg.do?method=update&id="+rows[0]["id"];
	var title = "奖项结果修改";
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
			if(row["sjly"] == "1"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer("审核流程过来的数据不能被删除！");
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jxjg.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


var DCCLBH = "pjpy_jxjg.do";
//自定义导出 功能
function exportConfig(){
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jgExportData);
}

//导出方法
function jgExportData(){
	setSearchTj();//设置高级查询条件
	var url = "jxjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function sqView(sqid, xh) {
	showDialog("查看", 770, 500, "jxjg.do?method=view&id="
			+ sqid + "&xh=" + xh);
}

