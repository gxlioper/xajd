/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 保存
 * @return
 */
function bcgygly(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		showAlert("请先选择人员！");
		return false;
	}
	var zghs = new Array();
	for ( var i = 0; i < rows.length; i++) {
		zghs.push(rows[i]['zgh']);
	}
	jQuery.post("xlzx_zxsgly.do?method=saveAddGly",{zghs:zghs},function(data){
		 showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
	},'json');
}

/**
 * 删除
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xlzx_zxsgly.do?method=delgly",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * 增加
 * @return
 */
function add(){
	showDialog("添加咨询师管理员", 770, 552, "xlzx_zxsgly.do?method=addZxsgly");
}



