function searchRs() {
	var map = getSuperSearch();
	map["flag"] = jQuery("#flag").val();
	jQuery("#dataTable").reloadGrid(map);
}



/**
 * 增加
 * @return
 */
function addCcjg(){
	var url = "gyjc_ccjgcx.do?method=addCcjg";
	showDialog("增加抽查结果", 770, 550, url);
}

function showQsxxNotF5CallBack(rowData) {
	jQuery("#xydm").val(rowData["xydm"]);
	jQuery("#lddm").val(rowData["lddm"]);
	jQuery("#qsh").val(rowData["qsh"]);
	jQuery("#ldmc").html(rowData["ldmc"]);
	jQuery("#qshTd").html(rowData["qsh"]);
	jQuery("#ch").html(rowData["lddm"]);
	jQuery("#xymc").html(rowData["xymc"]);
	jQuery(".jcxmTr").remove();
}

//保存
function saveCcjg() {
	var qsh =jQuery("#qsh").val();
	var jcrq = jQuery("#jcrq").val();
	if(null==qsh || ''==qsh){
		showAlert("请先选择寝室！");
		return false;
	}
	if(null==jcrq || ''==jcrq){
		showAlert("请选择检查日期！");
		return false;
	}
	var objArr= [];
	jQuery.each(jQuery(".pfidSel"),function(i,n){
			var pfid = jQuery(n).find("option:selected").val();
			objArr.push(pfid);
	});
	/*
	if(objArr.length!=0&&jQuery(".MultiFile-label").length==0){
		showAlert("请添加附件！");
		return false;
	}*/
	jQuery("#pfid").val(objArr.toString());
	var url = "gyjc_ccjgcx.do?method=saveCcjg";
	ajaxSubFormWithFun("ccjgForm", url, function(data) {
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
//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'gyjc_ccjgcx.do?method=editCcjg&guid=' + rows[0]["guid"];
		showDialog("抽查结果修改", 770, 500, url);
	}
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("gyjc_ccjgcx.do?method=delCcjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//查看
function viewCcjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
	} else {
		var url = 'gyjc_ccjgcx.do?method=viewCcjg&guid=' + rows[0]["guid"];
		showDialog("抽查结果查看", 770, 500, url);
	}
}

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport('gyjc_ccjgcx.do',exportData);
}

// 导出方法
function exportData(dcclbh) {
	setSearchTj();//设置高级查询条件
	var url = "gyjc_ccjgcx.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
	url+="&rowConut="+jQuery("#rowConut").html();
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
