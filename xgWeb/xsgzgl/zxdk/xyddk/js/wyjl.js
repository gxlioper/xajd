//增加
function addWyjl(){
	showDialog("增加违约记录",800,500,"zxdkWyjl.do?method=addWyjl");
}

//修改
function editWyjl(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {		
		showDialog("修改违约记录",800,500,"zxdkWyjl.do?method=editWyjl&xh="+rows[0]["xh"]);
	}
}

//删除
function delWyjl(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("zxdkWyjl.do?method=delWyjl",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//导出
function exportConfig(){
	var DCCLBH='zxdk_wyjl.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_wyjl.do';
	setSearchTj();//设置高级查询条件
	
	var url = "zxdkWyjl.do?method=dcwy&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function ckWyjl(xh){
	showDialog("查看违约记录",800,520,"zxdkWyjl.do?method=ckWyjl&xh="+xh);
}

function importYwxx(){
	toImportDataNew("ZXDK_XYDDK_WYJL");
	return false;
}