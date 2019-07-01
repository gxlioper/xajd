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
	var url = "xmsqgl_xmjg.do?method=add";
	var title = "结果";
    showDialog(title, 770, 500, url);
	
}

//增加结果保存
function saveSqjg(type){
	var ids = "xh"+"-"+"xmmc";
	if(check(ids) == false){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xmsqgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsXmJgForm", url, function(data) {
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
	if(jQuery("#xmmc").val() == ''){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xmsqgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XsXmJgForm", url, function(data) {
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
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(jgid, xh) {
	showDialog("查看", 770, 450, "xmsqgl_xmjg.do?method=XmjgView&jgid="
			+ jgid + "&xh=" + xh);
}

//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("审核流程过来的记录不能删除！");
				return false;
			}
			if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
				showAlertDivLayer("项目学分已认定，不能删除！");
				return false;
			}
			
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("xmsqgl_xmjg.do?method=delSqjl",{values:ids.toString()},function(data){
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
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
			return false;
		}
		if((rows[0]['xfrdsqzt']!='0' && rows[0]['xfrdsqzt']!='3') || rows[0]['xfrdjgzt']!='0'){
			showAlertDivLayer("该项目学分已认定，不能修改！");
			return false;
		}
		var url = 'xmsqgl_xmjg.do?method=editSqjg&jgid=' + rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "学生项目申请修改";
		showDialog(title, 770, 500, url);
	}
}

var DCCLBH = "sztz_xmsqgl_xmjg.do";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xsxmsqJgExportData);
}

//导出方法
function xsxmsqJgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "xmsqgl_xmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//导入
function importConfig(){
	toImportDataNew("IMPORT_XSTZXM");
	return false;
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
	jQuery("tr[name=checkxm]").each(function(){
		var kcyrs = parseInt(jQuery.trim(jQuery(this).find("td:eq(3)").text()));
		var tgrs  = parseInt(jQuery.trim(jQuery(this).find("td:eq(4)").text()));
		if(kcyrs-tgrs == 0){
			jQuery(this).find("[name=checkbox]").attr("disabled",true);
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
	    var xmmc = test.jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xmmc']").val();
		test.jQuery("input [name='xmmc']").val(jQuery(selectBox).parent().parent().find("td:eq(1)").text());
		test.jQuery("#xmjbmc").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xmjbmc']").val());
		test.jQuery("#xn").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xn']").val());
		test.jQuery("#xq").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'xqmc']").val());
		test.jQuery("#sbbmmc").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'sbbmmc']").val());
		test.jQuery("#lxdh").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'lxdh']").val());
		test.jQuery("#sskmmc").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'sskmmc']").val());
		test.jQuery("#jcxf").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'jcxf']").val());
		test.jQuery("#kcyrs").text(jQuery(selectBox).parent().parent().find("td:eq(3)").text());
		test.jQuery("#sqrs").text(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'sqrs']").val());
		test.jQuery("#tgrs").text(jQuery(selectBox).parent().parent().find("td:eq(4) ").text());
		test.jQuery("#xmkssj").text(jQuery(selectBox).parent().parent().find("td:eq(2) ").text());
		test.jQuery("input[name='xmdm']").val(jQuery(selectBox).parent().parent().find("td:eq(0) input").val());
		//test.jQuery("#splc").val(jQuery(selectBox).parent().parent().find("td:eq(4) input[name = 'splc']").val());
		test.jQuery("#xmmc").val(xmmc);
	    iFClose();
}
