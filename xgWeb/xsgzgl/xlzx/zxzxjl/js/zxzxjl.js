
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function save(type) {
	var ids = null;
	var url = "";
	if(type=='add'){
		ids = "xh-sfdszn-jtszd-jtjjzk-fqwhcd-mqwhcd-fmhyzk-jtjsbs-jtxhcd-sfzl-djrq-zxqw";
		url = "zxzx_zxzxjl.do?method=addZxJbxx&type=save";
	}else{
		ids = "sfdszn-jtszd-jtjjzk-fqwhcd-mqwhcd-fmhyzk-jtjsbs-jtxhcd-sfzl-djrq-zxqw";
		url = "zxzx_zxzxjl.do?method=updateZxJbxx&type=save";
	}
	if(!check(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var checkboxs = jQuery("input[name='yzxwts']:checked");
	if(checkboxs.length == 0){
		if(jQuery("#wtbc").val().length == 0){
			showAlert("请勾选咨询的问题或填写咨询问题补充");
			return false;
		}
	}
	
	ajaxSubFormWithFun("zxzxjlForm", url, function(data) {
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


function add() {
	var flg = true;
	var url = "zxzx_zxzxjl.do?method=addZxJbxx&type=add";
	var title = "新增咨询个人基本信息";
	showDialog(title, 800, 500, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else {
		var url = 'zxzx_zxzxjl.do?method=updateZxJbxx&type=update&xh=' + rows[0]["xh"];
		var title = "修改咨询个人基本信息";
		showDialog(title, 800, 500, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("删除操作会删除该学生下相关的所有谈话记录信息，您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("zxzx_zxzxjl.do?method=delZxJbxx", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

//咨询记录维护
function wh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要维护的学生的记录！");
	}else {
		var url = 'zxzx_zxzxjl.do?method=whZxzxjl&xh=' + rows[0]["xh"];
		var title = "维护咨询记录";
		showDialog(title, 800, 500, url);
	}
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ck(\""
			+ rowObject["xh"]+"\");'>" + cellValue
			+ "</a>";
}

//咨询记录维护
function ck(xh){	
		var url = 'zxzx_zxzxjl.do?method=ckZxzxjl&xh=' + xh;
		var title = "查看";
		showDialog(title, 800, 500, url);	
}

var DCCLBH = "xlzx_zxzx_zxzxjl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xlzxjlExportData);
}

//导出方法
function xlzxjlExportData() {
	setSearchTj();//设置高级查询条件
	var url = "zxzx_zxzxjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val().trim();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//打印登记表
function printExportDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "zxzx_zxzxjl.do?method=ExportxlzxDjb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "zxzx_zxzxjl.do?method=ExportxlzxDjbPl";
		url += "&value=" + ids;
		window.open(url);
	}
}

//打印咨询记录登记表
function printExportZxjlDjb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var len = ids.length;
	if (len == 1) {
		if(rows[0]["cs"] == 0){
			showAlertDivLayer("该学生无咨询记录！");
			return false;
		}
		var url = "zxzx_zxzxjl.do?method=ExportxlzxjlDjb";
		url += "&xh=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "zxzx_zxzxjl.do?method=ExportxlzxjlDjbPl";
		url += "&value=" + ids;
		window.open(url);
	}
}
