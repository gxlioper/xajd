function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add(){
	var url = "jxsq.do?method=add";
	var title = "奖项申请";
	showDialog(title, 770, 550, url);
}

//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
	if(rows[0]['shzt'] != '0' && rows[0]['shzt'] != '3'){
		showAlertDivLayer("只能修改未提交和已退回的记录！");
		return false;
	}
	var url = "jxsq.do?method=update&id="+rows[0]["id"];
	var title = "奖项申请修改";
	showDialog(title, 770, 550, url);
	}
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["shzt"] != "0" && row["shzt"] != "3"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer(jQuery("#lable_wjt_sc").val());
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("jxsq.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//提交
function submitBusi(){
	var isopen = jQuery("#isopen").val();
	if(isopen=='false'){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		return showAlertDivLayer("请选择一条您要提交的记录！");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("jxsq.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//撤销
function cancel() {
	var isopen = jQuery("isopen").val();
	if(isopen=='false'){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
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
				jQuery.post("jxsq.do?method=cancelSq", {
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

//流程跟踪
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
		showDialog("申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['id'] + "&splc=" + rows[0]['splc']);
	}
}
var DCCLBH = "pjpy_jxsq.do";
//自定义导出 功能
function exportConfig(){
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, jgExportData);
}

//导出方法
function jgExportData(){
	setSearchTj();//设置高级查询条件
	var url = "jxsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='sqView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function sqView(sqid, xh) {
	showDialog("查看", 770, 500, "jxsq.do?method=view&id="
			+ sqid + "&xh=" + xh);
}

