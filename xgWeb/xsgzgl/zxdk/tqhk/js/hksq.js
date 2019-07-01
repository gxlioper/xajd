function addHksq(){
	showDialog("增加",800,520,"zxdkTqhk.do?method=hksq");
}

function editHksq(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("该申请正在审核中，不能修改！");
			return false;
		}
		showDialog("修改",800,520,"zxdkTqhk.do?method=xgsq&id="+rows[0]["id"]);
	}
}

//提交
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1){
		showAlertDivLayer("请选择您要提交的记录！！");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("您选择的记录已经在审核中，无需重复提交！");
			return false;
		}
		
		jQuery.post("zxdkTqhk.do?method=submit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}

function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else {
		jQuery.post("zxdkTqhk.do?method=cancelSubmit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}

function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("您选择的记录已审核，不能删除！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("zxdkTqhk.do?method=delTqhk",{id:ids.toString()},function(data){
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

function selectTab(obj,zt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery("#shzt").val(zt);
	
	if (zt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
	}
	
	var map = getSuperSearch();
	map["shzt"] = zt;
	jQuery("#dataTable").reloadGrid(map);
}


function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("请选择一条您要审核的记录！");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "zxdkTqhk.do?method=hksh&id="+id;
		showDialog("提前还款审核",800,500,url);
	}
}

function cancelAuding(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择您要撤消的记录！");
	} else {
		jQuery.post("zxdkTqhk.do?method=cancelAudit",{id:ids.toString(),gwid:rows[0]["xtgwid"]},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}

/**
 * 流程跟踪
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else {
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}