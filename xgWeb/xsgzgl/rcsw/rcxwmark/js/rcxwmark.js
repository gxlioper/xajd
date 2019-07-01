/*查询*/
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/*选项卡切换*/
function selectTab(obj, shzt) {
	if (shzt == "wsz") {
		document.location.href = 'xg_rcsw_rcxwmark_wcl.do';
	} else {
		document.location.href = 'xg_rcsw_rcxwmark_ycl.do';
	}
}

//保存设置结果
function saveSzjg(type){
	//设置结果
	var jxdm = jQuery("[name='jxdm']:checked").val();
	if(jxdm == "" || jxdm == null){
		showAlertDivLayer("奖项设置不能为空！");
		return false;
	}
	var url = "rcsw_rcxwmark.do?method=saveSzjg&type=" + type;
	ajaxSubFormWithFun("RcxwmarkForm", url, function(data) {
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

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ckView(\""
			+ rowObject["rcxwjgid"] + "\",\""+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//查看学生ajaxurl跳转
function ckView(rcxwjgid,id,xh) {
	var title = "";
	var paras = "";
	var parasName = "";
	var cxlx = jQuery("#cxlx").val();
	if(cxlx == "yclcx"){
		title = "已设置查看";
	    paras = id;
		parasName = "id";
	}else{
		title = "未设置查看";
		paras = rcxwjgid;
		parasName = "rcxwjgid";
	}
	showDialog(title, 600, 450, "rcsw_rcxwmark.do?method=ck&"+parasName+"="+paras+"&type="+cxlx);
}
var DCCLBH = "";
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	var cxlx  = jQuery("#cxlx").val();
	if(cxlx == "yclcx"){
		DCCLBH = "xg_rcsw_rcxwmark_ycl.do";
	}else{
		DCCLBH = "xg_rcsw_rcxwmark_wcl.do";
	}
	customExport(DCCLBH, jgExportData);
}

//导出方法
function jgExportData() {
	var cxlx  = jQuery("#cxlx").val();
	setSearchTj();//设置高级查询条件
	var url = "rcsw_rcxwmark.do?method=exportData&dcclbh=" + DCCLBH+"&type="+cxlx;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//修改
function xg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'rcsw_rcxwmark.do?method=xg&id=' + rows[0]["id"];
		var title = "修改";
		showDialog(title, 600, 450, url);
	}
}

//设置
function sz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("请选择您要设置的记录！");
	} else {
		var url = 'rcsw_rcxwmark.do?method=Sz&rcxwjgids=' +ids.toString();
		var title = "设置";
		showDialog(title, 600, 450, url);
	}
}

//取消设置
function qxsz(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要取消设置的记录！");
	} else {
		showConfirmDivLayer("您确定要取消选择的记录吗？",{"okFun":function(){
			jQuery.post("rcsw_rcxwmark.do?method=qxsz",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

