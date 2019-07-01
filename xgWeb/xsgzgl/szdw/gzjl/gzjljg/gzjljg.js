
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function zghLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='gzjljgCk(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function setBz(cellValue,rowObject){
	if(null==cellValue){
		cellValue="";
	}
	return "<span title='"+rowObject["bz"]+"'>"+cellValue+"</span>";
	}
// 保存
function savegzjljg(type) {
	if(checkZdz()){
	var url = "gzjljg.do?method=savegzjljg&type=" + type;
	ajaxSubFormWithFun("GzjljgForm", url, function(data) {
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
}
//增加
function add() {
	var url = "gzjljg.do?method=gzjljgZj";
	var title = "工作记录填写";
	showDialog(title, 750, 550, url);
}
function checkzgh(){
	if(jQuery("#zgh").val()==""){
		showAlert("请先选择学生！");
		return false;
	}
}

//修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		var url = 'gzjljg.do?method=gzjljgXg&jgid=' + rows[0]["jgid"]
				+ '&zgh=' + rows[0]["zgh"];
		var title = "工作记录修改";
		showDialog(title, 750, 550, url);
	}
}
//查看
function gzjljgCk(id, zgh) {
	showDialog("工作记录查看", 750, 550, "gzjljg.do?method=gzjljgCk&jgid="
			+ id + "&zgh=" + zgh);
}
// 删除
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
			jQuery.post("gzjljg.do?method=delGzjljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "gzjl_gzjljg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, gzjljgExportData);
}

//导出方法
function gzjljgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "gzjljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//工作记录统计
function gzjltj(type) {
	kssj=jQuery("#gzsj_kssj").val();
	jssj=jQuery("#gzsj_jssj").val();
	if(null==kssj||""==kssj||null==jssj||""==jssj){
		showAlertDivLayer("请选择工作时间段！");
		return false;
	}
	var url = "gzjljg.do?method=gzjltj&type="+type;
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}
/**
 * 打印工作记录表
 */
function printGzjlb(url) {

	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("请选择一条记录！");
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	}
}


function gzjlb(type){
	kssj=jQuery("#gzsj_kssj").val();
	jssj=jQuery("#gzsj_jssj").val();
	if(null==kssj||""==kssj||null==jssj||""==jssj){
		showAlertDivLayer("请选择工作时间段！");
		return false;
	}
	var url = "gzjljg.do?method=gzjlb&type="+type+"&kssj="+kssj+"&jssj="+jssj;
	setSearchTj();
	url = addSuperSearchParams(url);
	document.forms[0].action = url;
	document.forms[0].submit();
}


