function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);

}

function bxbxsqZj(){
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
		return false;
	}
	showDialog('保险报销申请',750,450,'rcswBxglBxbxsq.do?method=bxbxsqZj');;
}

//附件格式验证
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}
//增加保存
function saveForm(type){
	var xh=jQuery("#xh").val();
	if(null==xh||""==xh){
		return showAlert("请将带*的项目填写完整");
	}
	if(jQuery("#xxdm").val()=="13871"&&jQuery("#filepath").val() == ""){
		showAlert("请将必填项填写完整。");
		return false;
	}
	var url = "rcswBxglBxbxsq.do?method=bxbxsqBc&type="+type;
	
	if (!zdybdCheck()) {
		return;
	}
	if(jQuery("#xxdm").val() == "13871"){
		if(parseFloat(jQuery("#bxje").val()) == 0){
			return showAlert("报销金额必须大于0！");
		}
	}
	if(!postfixCheck()){
		return showAlert("请上传支持的附件格式！");
	}
	ajaxSubFormWithFun("BxbxSqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["success"]!="false") {
				refershParent();
			}
		}});
	});
}
//删除
function bxbxsqDel(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		for (var i = 0; i < ids.length; i++) {
			if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
				showAlertDivLayer("只能删除未提交或者已退回的记录！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
			jQuery.post("rcswBxglBxbxsq.do?method=bxbxsqDel",{values:ids.toString()},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//修改
function bxbxsqXg(){
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg) {
		showAlertDivLayer("当前申请已关闭，请与管理员联系！");
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		} else {
		var url = 'rcswBxglBxbxsq.do?method=bxbxsqXg&sqid='+rows[0]["sqid"]+"&xh="+rows[0]["xh"];
		
		showDialog('保险报销申请修改', 750, 450, url);
	}
	}
	
}
//修改保存
function saveUpdateForm(type){
	var url = "rcswBxglBxbxsq.do?method=bxbxsqXgBc&type="+type;
	
	if (!zdybdCheck()) {
		return;
	}
	if(jQuery("#xxdm").val()=="13871"&&jQuery("#filepath").val() == ""){
		showAlert("请将必填项填写完整。");
		return false;
	}
	if(jQuery("#xxdm").val() == "13871"){
		if(parseFloat(jQuery("#bxje").val()) == 0){
			return showAlert("报销金额必须大于0！");
		}
	}
	if(!postfixCheck()){
		return showAlert("请上传支持的附件格式！");
	}
				
	ajaxSubFormWithFun("BxbxSqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["success"]!="false") {
				refershParent();
			}
		}});
	});
}

//提交
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var sqkg = jQuery("#sqkg").val();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	}
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcswBxglBxbxsq.do?method=submitBxbxsq", {
					values : ids.toString(),
					type:"submit",
					bxje:rows[0]['bxje'],
					csfysj:rows[0]['csfysj'],
					xh:rows[0]['xh'],
					lx:rows[0]['lx'],
					bxxz:rows[0]['bxxzmc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	

}
// 撤销
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要撤销的记录！");
	} else if (ids.length > 1) {
		showAlertDivLayer("请选择一条您要撤销的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("只有审核中的记录才能被撤销！");
				return false;
			}
		}

		showConfirmDivLayer("您确定要撤销选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcswBxglBxbxsq.do?method=cancelBxbxsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
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
	} 
	var shzt = rows[0]["shzt"];
	if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("保险报销申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
}


//学号链接查看
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='bxbxsqCk(\""+rowObject["sqid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

function bxbxsqCk(sqid,cellValue){
	
	showDialog("保险报销申请查看",750,450,'rcswBxglBxbxsq.do?method=bxbxsqCk&sqid='+sqid+"&xh="+cellValue,null);

}

function exportConfig(){
	//DCCLBH导出功能编号,执行导出函数 
	customExport("rcsw_bxgl_bxbxsq.do", bxbxExportData);
}

//导出
function bxbxExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcswBxglBxbxsq.do?method=bxbxExportData&dcclbh=" + "rcsw_bxgl_bxbxsq.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



//保险报销打印
function printBxbxZm() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length == 0) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	} else if (rows.length > 1) {
		var url="rcswBxglBxbxsq.do?method=getBxzmZip";
		var ids = jQuery("#dataTable").getSeletIds();
		url += "&value="+ids;
		window.open(url);
	} else {
		var sqid = rows[0]["sqid"];
		var url ="rcswBxglBxbxsq.do?method=getBxzm&sqid="+sqid;
		window.open(url);
	}
}

