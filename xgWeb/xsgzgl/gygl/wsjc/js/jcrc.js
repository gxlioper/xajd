function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		showDialog('修改',700,480,'wsjcJcrc.do?method=edit&id='+rows[0]["id"]);
	}
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
			jQuery.post("wsjcJcrc.do?method=delete",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function add(){
	showDialog('增加',700,480,'wsjcJcrc.do?method=add');;
}

function jcrcSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要操作的记录！");
	} else {
		showConfirmDivLayer("该操作将会锁定卫生检查日程，不允许分数录入，是否确定继续操作？",{"okFun":function(){
			jQuery.post("wsjcJcrc.do?method=submit",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function jcrcCancel(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要操作的记录！");
	} else {
		showConfirmDivLayer("该操作将会取消卫生检查日程锁定，允许分数录入，是否确定继续操作？",{"okFun":function(){
			jQuery.post("wsjcJcrc.do?method=cancel",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}