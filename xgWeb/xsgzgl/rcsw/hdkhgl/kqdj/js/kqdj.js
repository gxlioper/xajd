
/**
 * 查询
 * @return
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


//考勤登记维护
function XsKqdjWh(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "";
	if(ids.length == 0){
		showAlertDivLayer("请选择您需要进行考勤登记的记录！");
		return false;
	}else if(ids.length == 1){
		 url = 'hdkhgl_hddj.do?method=XsKqdjWh&hdjgid=' + rows[0]["hdjgid"]+ '&xh=' + rows[0]["xh"];
		 var title = "考勤登记";
		 showDialog(title, 500, 200, url);
	}else if(ids.length > 1){
	
		 url = 'hdkhgl_hddj.do?method=XsKqdjPlWh';
		 
		var title = "批量考勤登记";
		showDialog(title, 500, 200, url);
	}
}

//保存学生考勤登记信息
function saveKqdj(){
	var obj = jQuery("#sfcj");
	var obj1 = jQuery("#qqyy");
	if(jQuery(obj).val() == '否'){
		if(jQuery(obj1).val() == ''){
			showAlert("缺勤原因不可为空！");
			return false;
		}else if(checkzsonsubmit(obj1) == false){
			return false;
		}
	}else if(jQuery(obj).val() == '' ){
		showAlert("是否参加不可为空！");
		return false;
	}
	var url = "hdkhgl_hddj.do?method=saveKqdj";
	ajaxSubFormWithFun("HdkhglForm", url, function(data) {
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

var DCCLBH = "rcsw_hdkhgl_hddj.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xskqdjExportData);
}

//导出方法
function xskqdjExportData() {
	setSearchTj();//设置高级查询条件
	var url = "hdkhgl_hddj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//考勤登记状态联动
function kqdjOnchange(){
	if(jQuery("#sfcj").val() == '否'){
		jQuery("#qqblock").show();
	}else{
		jQuery("#qqblock").hide();
	}
}

//验证字数
function checkzsonsubmit(obj){
	if(jQuery(obj).val().length > 100){
		showAlert("最大字数为100，现已经超过，请确认！");
		return false;
	}
}

function xhLink(cellValue, rowObject) {
		 return "<a href='javascript:void(0);' class='name' onclick='KqdjView(\""
			+ rowObject["hdjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
	 
}

//查看学生ajaxurl跳转
function KqdjView(hdjgid,xh) {
	showDialog("考勤登记查看", 700, 370, "hdkhgl_hddj.do?method=kqdjview&hdjgid="
			+ hdjgid + "&xh=" + xh);
}

//保存批量考勤登记维护
function savePlKqdj(){
	var obj = jQuery("#sfcj");
	var obj1 = jQuery("#qqyy");
	if(jQuery(obj).val() == '否'){
		if(jQuery(obj1).val() == ''){
			showAlert("缺勤原因不可为空！");
			return false;
		}else if(checkzsonsubmit(obj1) == false){
			return false;
		}
	}else if(jQuery(obj).val() == '' ){
		showAlert("是否参加不可为空！");
		return false;
	}
	var api = frameElement.api,W = api.opener;
	var rows = W.jQuery("#dataTable").getSeletRow();
	var ids = new Array();
	var hdjgids = new Array();
	var xhs = new Array();
	var xmmcs = new Array();
	var xns = new Array();
	var xqs = new Array();
	var sfcj = jQuery(obj).val();
	var qqyy = jQuery(obj1).val();
	jQuery.each(rows, function(i, row) {
		if(row["id"] == null){
			ids.push("");
		}else{
			ids.push(row["id"]);
		}
		hdjgids.push(row["hdjgid"]);
		xhs.push(row["xh"]);
		xmmcs.push(row["xmmc"]);
		xns.push(row["xn"]);
		xqs.push(row["xq"]);
	});
	var data = {
		    xhs:xhs,
		    hdjgids:hdjgids,
		    ids:ids,
		    xmmcs:xmmcs,
		    sfcj:sfcj,
		    qqyy:qqyy,
		    xns:xns,
		    xqs:xqs
		};
	var url = "hdkhgl_hddj.do?method=saveXsKqdjPlWh";
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success: function(data) {
			 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			}
	
   });
}

