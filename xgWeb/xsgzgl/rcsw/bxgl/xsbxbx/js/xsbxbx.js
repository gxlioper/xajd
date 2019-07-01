function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	//autoTitleForGrid();
	//setTitle();
}

function addBx(){
	showDialog('增加',750,450,'rcswBxglXsbxbx.do?method=addBx');;
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
function saveForm(){
	var url = "rcswBxglXsbxbx.do?method=addBx&type=save";
				
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
				
	ajaxSubFormWithFun("demoForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["message"]!="门诊报销金额超出年度上限") {
				refershParent();
			}
		}});
	});
}
//删除
function delBx(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
			jQuery.post("rcswBxglXsbxbx.do?method=delBx",{values:ids.toString()},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//修改
function updateBx(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'rcswBxglXsbxbx.do?method=updateBx&bxid='+rows[0]["bxid"]+'&xh='+rows[0]["xh"];
		
		showDialog('修改', 750, 450, url);
	}
	
}
//修改保存
function saveFormU(){
	var url = "rcswBxglXsbxbx.do?method=updateBx&type=save";
				
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
				
	ajaxSubFormWithFun("demoForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window&&data["message"]!="门诊报销金额超出年度上限") {
				refershParent();
			}
		}});
	});
}

//学号链接查看
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckBx(\""+rowObject["bxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

function ckBx(bxid,cellValue){
	
	showDialog("查看",750,375,'rcswBxglXsbxbx.do?method=ckBx&bxid='+bxid+"&xh="+cellValue,null);

}

function exportConfig(){
	//DCCLBH导出功能编号,执行导出函数 
	customExport("rcsw_bxgl_xsbxbx.do", bxbxExportData);
}

//导出
function bxbxExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcswBxglXsbxbx.do?method=bxbxExportData&dcclbh=" + "rcsw_bxgl_xsbxbx.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importBx() {
	toImportData("IMPORT_N732502");
	return false;
}

//保险报销打印
function printBxbxZm() {
	var rows = jQuery("#dataTable").getSeletRow();	
	if (rows.length == 0) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	} else if (rows.length > 1) {
		var url="rcswBxglXsbxbx.do?method=getBxzmZip";
		var ids = jQuery("#dataTable").getSeletIds();
		url += "&value="+ids;
		window.open(url);
	} else {
		var bxid = rows[0]["bxid"];
		var url ="rcswBxglXsbxbx.do?method=getBxzm&bxid="+bxid;
		window.open(url);
	}
}