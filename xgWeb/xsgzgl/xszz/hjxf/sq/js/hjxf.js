function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
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
	var cfbz = jQuery("#cfbz").val();
	if( "1"  == cfbz){
		showAlertDivLayer("该学生在当前学年已有申请记录，请勿重复填写！");
		return false;
	}
	var url = "hjxf_sq.do?method=add";
	var title = "缓交学费申请增加";
	showDialog(title, 770, 552, url);
}

//增加修改申请保存
function saveHjxfSq(type){
	var ids = ""
	if(type == "updatesubmit" || type == "update"){
	   ids = "pkdj"+"-"+"dkqk"+"-"+"wnqfje"+"-"+"yjje"+"-"+"hjje"+"-"+"jqjzsj"+"-"+"sqyy";
	}else{
		ids ="xh"+"-"+ "pkdj"+"-"+"dkqk"+"-"+"wnqfje"+"-"+"yjje"+"-"+"hjje"+"-"+"jqjzsj"+"-"+"sqyy";
	}
	
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	if(jQuery("#jqjzsj").val() > jQuery("#checksj").val() && jQuery.trim(jQuery("#checksj").val()) != ""){
		showAlert("超出截止时间"+jQuery("#checksj").val()+"！");
        return false;
	}
	var url = "hjxf_sq.do?method=saveHjxfsq&type=" + type;
	ajaxSubFormWithFun("HjxfSqForm", url, function(data) {
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
function update(){
	var isopen = jQuery("#sqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = 'hjxf_sq.do?method=editHjxf&hjxfid=' + rows[0]["hjxfid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "缓交学费申请修改";
		showDialog(title, 770, 552, url);
	}
}

//删除申请
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_sc").val());
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("hjxf_sq.do?method=delHjxf",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
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
		showDialog("缓交学费审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['hjxfid'] + "&splc=" + rows[0]['splcid']);
	}
}

//提交申请
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
		showAlertDivLayer("请选择一条您要提交的记录！");
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
				jQuery.post("hjxf_sq.do?method=submitBusi", {
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
				jQuery.post("hjxf_sq.do?method=cancelHjxfsq", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

var DCCLBH = "xszz_hjxf_sq.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportData);
}

//导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var url = "hjxf_sq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='HjxfView(\""
			+ rowObject["hjxfid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function HjxfView(sqbh, xh) {
	showDialog("缓交学费申请查看", 700, 450, "hjxf_sq.do?method=HjxfView&hjxfid="
			+ sqbh + "&xh=" + xh);
}

//申请表
function printsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "hjxf_sq.do?method=getHjxfsq";
		url += "&hjxfid=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("请选择您要下载的记录！");
		return false;
	} else {
		var url = "hjxf_sq.do?method=gettHjxfTy";
		url += "&value=" + ids;
		window.open(url);
	}
}
