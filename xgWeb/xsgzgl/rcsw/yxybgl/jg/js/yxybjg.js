
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function yfLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='yxybjgView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}
function yxybjgView(jgid) {
	showDialog("院级月报查看", 800, 600, "yxybgl_jg.do?method=viewYxybjg&jgid="+jgid);
}


function saveYxybjg(type) {
	var flg=true;
	var ids = null;
	if(type=='save'||type=='submit'){
		ids = 'yf-xydm-bygzkzqk-xsgzrd-xssxdt-xstsjgzjy';
	}else{
		ids = 'bygzkzqk-xsgzrd-xssxdt-xstsjgzjy';		
	}
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	var url = "yxybgl_jg.do?method=saveYxybjg&type=" + type;
	ajaxSubFormWithFun("yxybjgForm", url, function(data) {
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
	var url = "yxybgl_jg.do?method=addYxybjg";
	var title = "月报填写";
	showDialog(title, 800, 600, url);
}

function update() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(rows[0]['sjly']=='1'){
		showAlertDivLayer("审核流程过来的记录不能修改！");
	}else {
		var url = 'yxybgl_jg.do?method=editYxybjg&jgid=' + rows[0]["jgid"];
		var title = "院级月报修改";
		showDialog(title, 800, 600, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("是否确定删除勾选的记录？", {
		"okFun" : function() {
		var url = "yxybgl_jg.do?method=delYxybjg";
		jQuery.post(url, {
			values : ids.toString()
		}, function(data) {
			if (data["success"] == false) {
				showAlertDivLayer(data["message"]);
			} else {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function(tag) {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}
		}, 'json');
	
}});
}
}


var DCCLBH = "rcsw_yxybgl_jg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, yxybjgExportData);
}

//导出方法
function yxybjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "yxybgl_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//合并导出
function hbdc() {
	setSearchTj();//设置高级查询条件
	var url = "yxybgl_jg.do?method=hbdc";
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}