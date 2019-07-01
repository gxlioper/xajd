function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加任职考核结果
function add() {
	var url = "szdw_xsgb_rzkhjg.do?method=add";
	var title = "增加学生干部考核结果";
	showDialog(title, 770, 535, url);
}

//增加修改任职考核结果保存
function saveKhjg(type){
	var ids = "zwmc"+"-"+"rzsj"+"-"+"xn"+"-"+"xq"+"-"+"grzp"+"-"+"zgdwyj";
	var idss = "grsz"+"-"+"qdyj"+"-"+"szdwyj"+"-"+"ddyj"+"-"+"xsgzcyj"+"-"+"bz";
	var names = "个人述职"+"-"+"区队意见"+"-"+"所在单位意见"+"-"+"大队意见"+"-"+"学生工作处意见"+"-"+"备注";
	var lens = "50"+"-"+"500"+"-"+"500"+"-"+"500"+"-"+"500"+"-"+"500";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs(idss,names,lens) == false){
		return false;
	}
	var url = "szdw_xsgb_rzkhjg.do?method=saveKhjg&type=" + type;
	ajaxSubFormWithFun("rzkhjgForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

//删除考核结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		/*
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}*/
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("szdw_xsgb_rzkhjg.do?method=delKhjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='KhjgView(\""
			+ rowObject["khjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转（直接点击学号链接）
function KhjgView(khjgid, xh) {
	showDialog("查看学生干部考核结果", 770, 535, "szdw_xsgb_rzkhjg.do?method=KhjgView&khjgid="
			+ khjgid + "&xh=" + xh);
}

//查看学生v2(勾选一条学生记录，点击查看)
function KhjgViewv2(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (parseInt(ids.length) != 1){
		showAlertDivLayer("请选择一条学生记录进行查看！");
		return false;
	}
	showDialog("查看学生干部考核结果", 770, 535, "szdw_xsgb_rzkhjg.do?method=KhjgView&khjgid="
			+ ids);
}

//修改学生干部考核结果
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		/*
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}*/
		var url = 'szdw_xsgb_rzkhjg.do?method=updateKhjg&khjgid=' + rows[0]["khjgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "修改学生干部考核结果";
		showDialog(title, 770, 535, url);
	}
}

var DCCLBH = "szdw_xsgb_rzkhjg.do";//dcclbh,导出功能编号
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xyzsjgExportData);
}

//导出方法
function xyzsjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "szdw_xsgb_rzkhjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//学生干部任职考核表导出
function printXyzsjgsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "szdw_xsgb_rzkhjg.do?method=getGbrzkhb";
		url += "&khjgid=" + ids+"&flag=jg";
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "szdw_xsgb_rzkhjg.do?method=getKhjgkhbTy";
		url += "&value=" + ids+"&flag=jg";
		window.open(url);
	}
}

//学生干部任职考核汇总表导出
function printKhhzb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "szdw_xsgb_rzkhjg.do?method=getKhhzb";
		url += "&khjgid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "szdw_xsgb_rzkhjg.do?method=getKhhzbTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_N470806_XSGBKH");
	return false;
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//备注等长字段数据
function checkzs(ids,names,lens){
	var id=ids.split("-");
	var name=names.split("-");
	var lenth =lens.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm.length>lenth[i]){
			showAlert(name[i]+"输入的长度不能超过"+lenth[i]+"，请确认！");
			jQuery("#"+id[i]).focus();
			return false;
		}
	}
}

function checkzsonKeyup(obj,lenth){
	if(obj.value.length>parseInt(lenth)){
		showAlert("输入的长度不能超过"+lenth+",请确认");
	}
}
