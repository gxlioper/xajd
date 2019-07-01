

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='khjgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

// 保存
function saveKhjg(type) {
	var xh = jQuery("#xh").val();
	if (jQuery("#yrdw").val() == ""||jQuery("#yrdw").val() == null||jQuery("#gwdm").val() == ""||jQuery("#gwdm").val() == null||
			jQuery("#gzdd").val() == "" || xh == ""||jQuery("#gzrq").val() == ""||jQuery("#gzkssj").val() == ""
		||jQuery("#gzjssj").val() == ""||jQuery("#gs").val() == ""||jQuery("#gznr").val() == "") {
		showAlert("请将必填项填写完整！");
		return false;
	}
	if("0" == jQuery("#gs").val()) {
		showAlert("工时不能为零！");
		return false;
	}
	if (jQuery("#gznr").val().length>1000) {
		showAlert("工作内容最多输入1000字！");
		return false;
	}
	var url = "mrgzkhKhjg.do?method=saveKhjg&type=" + type;
	ajaxSubFormWithFun("GzkhKhjgForm", url, function(data) {
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
//增加
function add() {
	var url = "mrgzkhKhjg.do?method=addKhjg";
	var title = "每日考核填写";
	showDialog(title, 750, 480, url);
}
function checkXh(){
	if(jQuery("#xh").val()==""){
		showAlert("请先选择学生！");
		return false;
	}
}
//岗位代码名称
function getGwxx(){
	jQuery.ajaxSetup({async:false});
	var parameter ={};
	parameter["xh"]=escape(jQuery("#xh").val());
    parameter["yrdw"]=escape(jQuery("#yrdw").val());
	jQuery.getJSON('mrgzkhKhsq.do?method=getGwxx&t='+new Date().getTime(),parameter,function(data){
		jQuery('#gwdm').empty();
		//jQuery('#gwdm').append("<option value=''>--------请选择--------</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].gwdm + "\">" + data[i].gwmc + "</option>";
				jQuery('#gwdm').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
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
		var url = 'mrgzkhKhjg.do?method=editKhjg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "每日考核填写";
		showDialog(title, 750, 480, url);
	}
}
//查看
function khjgView(id, xh) {
	showDialog("每日考核查看", 750, 550, "mrgzkhKhjg.do?method=khjgView&id="
			+ id + "&xh=" + xh);
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
			jQuery.post("mrgzkhKhjg.do?method=delKhjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "qgzx_mrgzkh_khjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, mrgzkhKhjgExportData);
}

//导出方法
function mrgzkhKhjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "mrgzkhKhjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
