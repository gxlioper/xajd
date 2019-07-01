function zpsq(){
	showDialog("德育自评申请",650,450,"xsxxDyxjDyzp.do?method=zpsq");
}

function xgsq(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("您选择的记录已经在审核中，不能修改！");
			return false;
		}
		
		showDialog("修改申请表",650,450,"xsxxDyxjDyzp.do?method=xgsq&id="+rows[0]["id"]);
	}
}

//提交
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("您选择的记录已经在审核中，无需重复提交！");
		return false;
	}
	
	if (ids.length != 1){
		showAlertDivLayer("请选择您要提交的记录！！");
	} else {
		jQuery.post("xsxxDyxjDyzp.do?method=submit",{id:ids.toString()},function(data){
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
		jQuery.post("xsxxDyxjDyzp.do?method=cancelSubmit",{id:ids.toString()},function(data){
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
			jQuery.post("xsxxDyxjDyzp.do?method=qxsq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function searchRs(){
	var map = getSuperSearch();
	map["shzt"]=jQuery("#shzt").val();
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

function showAuding() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	var flag=true;
	if (rows.length == 0) {
		showAlert("请选择一条您要审核的记录！");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var url = "xsxxDyxjDyzp.do?method=zpsh&id="+id+"&xtgwid="+rows[0]["xtgwid"];
		showDialog("德育自评审核",800,500,url);
	} else {
		var id = rows[0]["id"];
		for ( var i = 1; i < rows.length; i++) {
			if(rows[i]["sjdjmc"]!=rows[i-1]["sjdjmc"]){
				flag=false;
				break;
				
			}
		}
//		if(!flag){
//			showAlertDivLayer("请选择相同的评定等级！");
//			return false;
//		}
		showDialog("德育自评批量审核", 500, 250, "xsxxDyxjDyzp.do?method=dyzpPlSh&id="+id);
	}
}

function checklenbyzpnr(){
	var zpnrStr = jQuery("#zpnr").val();
	if(zpnrStr == null ||jQuery.trim(zpnrStr) == ""){
		showAlert("自评内容不能为空！");
		return false;
	}else if(zpnrStr.length <400 || zpnrStr.length >1000 ){
		showAlert("该输入项字数为400-1000，请确认！");
		return false;
	}else{
		return true;
	}
}

/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['id'] + "&splc=" + rows[0]['splcid']);
	}
}

