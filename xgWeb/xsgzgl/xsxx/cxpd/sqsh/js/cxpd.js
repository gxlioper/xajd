
function cxpdAdd(){
	showDialog("操行评定上报",680,450,"cxpdSqsh.do?method=pdsb");
}

function cksq(id){
	showDialog("操行评定查看",600,350,"cxpdSqsh.do?method=cksq&id="+id);
}

function xgsq(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("只能修改未提交或者已退回的记录！");
			return false;
		}
		
		showDialog("操行评定修改",680,450,"cxpdSqsh.do?method=xgsq&id="+rows[0]["id"]);
	}
}

//提交
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
	
	if (ids.length != 1){
		showAlertDivLayer("请选择您要提交的记录！！");
	} else {
		showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
			jQuery.post("cxpdSqsh.do?method=submit",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("请选择您要撤消的记录！");
	} else if (rows[0]["shzt"] != "5"){
		showAlertDivLayer("只有审核中的记录才能被撤销！");
		return false;
	} else {
		showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
			jQuery.post("cxpdSqsh.do?method=cancelSubmit",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消申请的记录！");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		showConfirmDivLayer("您确定要删除该申请吗？",{"okFun":function(){
			jQuery.post("cxpdSqsh.do?method=qxsq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 流程跟踪
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条记录！");
	} else if (rows[0]["shzt"] == "0"){
		showAlertDivLayer("该记录为未提交状态，请先提交！");
	} else {
		showDialog("流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
}

function searchRs(){
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
}


function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("请选择一条您要审核的记录！");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "cxpdSqsh.do?method=pdsh&id="+id;
		showDialog("操行评定审核",800,500,url);
	}
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

