
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='khsqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
function khsqView(sqid, xh) {
	showDialog("每日工作考核申请查看", 750, 550, "mrgzkhKhsq.do?method=khsqView&sqid="
			+ sqid + "&xh=" + xh);
}
function saveKhsq(type) {	
	if("0" == jQuery("#gs").val()) {
		showAlert("工时不能为零！");
		return false;
	}
	// if(parseInt(jQuery("#gs").val())  > 8){
     //    showAlert("每天工作时长<=8小时！");
     //    return false;
	// }
	if(checkZdz()){
	var url = "mrgzkhKhsq.do?method=saveKhsq&type=" + type;
	ajaxSubFormWithFun("GzkhKhsqForm", url, function(data) {
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
/**
 * 参数验证
 * @return
 */
function checkZdz(){
	var xh = jQuery("#xh").val();
	var flag=true;
	if (jQuery("#yrdw").val() == ""||jQuery("#yrdw").val() == null||jQuery("#gwdm").val() == ""||jQuery("#gwdm").val() == null || xh == ""||jQuery("#gzrq").val() == ""||jQuery("#gzkssj").val() == ""
		||jQuery("#gzjssj").val() == ""||jQuery("#gs").val() == "") {
		showAlert("请将带<font color=\"red\">*</font>项填写完整！");
		flag= false;
		return flag;
	}
	/*if (jQuery("#gznr").val().length>1000) {
		showAlert("工作内容最多输入1000字！");
		flag=false;
		return flag;
	}*/
	var gzkssj = parseInt(jQuery("#gzkssj").val(),10);
	var gzjssj = parseInt(jQuery("#gzjssj").val(),10);
	var gzsjd = gzjssj-gzkssj;
	
	if(gzkssj>gzjssj){
		showAlert("工作开始时间不能大于工作结束时间！");
		flag=false;
		return flag;
	}
	return flag;
}
function add() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	
	var url = "mrgzkhKhsq.do?method=addKhsq";
	var title = "每日工作考核申请";
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
	jQuery.getJSON('mrgzkhKhsq.do?method=getGwxx',parameter,function(data){
		jQuery('#gwdm').empty();
		jQuery('#gwdm').append("<option value=''>--------请选择--------</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].gwdm + "\">" + data[i].gwmc + "</option>";
				jQuery('#gwdm').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
}


function update() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {

		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}

		var url = 'mrgzkhKhsq.do?method=editKhsq&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "每日工作考核申请修改";
		showDialog(title, 750, 480, url);
	}

}

// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_sc").val());
		return false;
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("mrgzkhKhsq.do?method=delKhsq", {
					values : ids.toString()
				},
						function(data) {
							var mes = "成功删除了<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>条数据";
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// 提交
function submitBusi() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer(jQuery("#lable_one_tj").val());
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "0" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("mrgzkhKhsq.do?method=saveEditKhsq&type=tj", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
// 撤销
function cancel() {
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("0" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
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
				jQuery.post("mrgzkhKhsq.do?method=cancelKhsq", {
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
/*
 * 流程跟踪
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("请选择一条流程跟踪记录！");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("每日工作考核审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}


var DCCLBH = "qgzx_mrgzkh_khsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, khsqExportData);
}

//导出方法
function khsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "mrgzkhKhsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}