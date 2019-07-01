/**
 * 查询
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//增加
function add() {
	var url = "xmsqgl_xmsq.do?method=add";
	var title = "申请";
    showDialog(title, 770, 500, url);
	
}

//增加结果保存
function saveSqjg(type){
	var ids = "xh-xmmc-sqly";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xmsqgl_xmsq.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsXmSqForm", url, function(data) {
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

function saveSqjgUpdate(type){
	if(jQuery("#xmmc").val() == ''||jQuery("#sqly").val() == ''||null==jQuery("#sqly").val()){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xmsqgl_xmsq.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsXmSqForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(sqid, xh) {
	showDialog("查看", 770, 450, "xmsqgl_xmsq.do?method=XmjgView&sqid="
			+ sqid + "&xh=" + xh);
}

//删除住宿结果
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("只能删除未提交或者已退回的记录！");
		return false;
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xmsqgl_xmsq.do?method=delSqjl",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//修改
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var sqkg = rows[0]['sqkg'];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}
		var url = 'xmsqgl_xmsq.do?method=editSqjg&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "学生项目申请修改";
		showDialog(title, 770, 500, url);
	}
}

var DCCLBH = "sztz_xmsqgl_xmsq.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xsxmsqJgExportData);
}

//导出方法
function xsxmsqJgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xmsqgl_xmsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//提交
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("请选择一条您要提交的记录！");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("请选择未提交或者已退回的记录！");
		return false;
	} else {
		if ('3' != rows[0]['shzt'] && "0" == rows[0]['sqkg']) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		showConfirmDivLayer("您确定要提交选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xmsqgl_xmsq.do?method=submitBusi", {
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
				jQuery.post("xmsqgl_xmsq.do?method=cancelXmsq", {
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
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer("该记录为未提交状态，请先提交！");
			return false;
		}
		showDialog("学生项目申请审批流程跟踪",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}


function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlert("最大字数为500，现已经超过，请确认！！");
		return false;
	}
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

function auotSetCanCheck(){
	jQuery("tr[name='checkxm']").each(function(){
		var kcyrs = parseInt(jQuery.trim(jQuery(this).find("td[name='kcyrs']").text()));
		var tgrs  = parseInt(jQuery.trim(jQuery(this).find("td[name='syme']").text()));
		if(kcyrs-tgrs == 0){
			jQuery(this).find("[name='checkbox']").attr("disabled",true);
		}
	});
}

/**
 * 选择项目页，切换已申请、未申请
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	if (tabId == "ysq"){
		jQuery("#titleTr td").last().css("display","none");
	} else {
		jQuery("#titleTr td").last().css("display","");
	}
}

/**
 * 确定选择项目
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("#wsq input:checkbox:checked");
	var test = api.get('parentDialog');
	if(selectBox.length != 1){
		showAlert("请选择一项活动，进行选择！");
		return false;
	}
	  	var xmmc = test.jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'xmmc']").val();
		test.jQuery("#xmjbmc").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'xmjbmc']").val());
		test.jQuery("#xn").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'xn']").val());
		test.jQuery("#xq").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'xqmc']").val());
		test.jQuery("#sbbmmc").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'sbbmmc']").val());
		test.jQuery("#lxdh").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'lxdh']").val());
		test.jQuery("#sskmmc").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'sskmmc']").val());
		test.jQuery("#jcxf").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'jcxf']").val());
		test.jQuery("#kcyrs").text(jQuery(selectBox).parent().parent().find("td[name='kcyrs']").text());
		test.jQuery("#sqrs").text(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'sqrs']").val());
		test.jQuery("#tgrs").text(jQuery(selectBox).parent().parent().find("td[name='syme'] ").text());
		test.jQuery("#xmkssj").text(jQuery(selectBox).parent().parent().find("td[name='xmkssj'] ").text());
		test.jQuery("input[name='xmdm']").val(jQuery(selectBox).parent().parent().find("td:eq(0) input").val());
		test.jQuery("#splc").val(jQuery(selectBox).parent().parent().find("td[name='syme'] input[name = 'splc']").val());
		test.jQuery("#xmmc").val(xmmc);
	    iFClose();
}


