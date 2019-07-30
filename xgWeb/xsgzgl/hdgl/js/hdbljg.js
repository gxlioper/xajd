
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='hdbljgView(\""
			+ rowObject["jgid"]+"\");'>" + cellValue
			+ "</a>";
}

function hdbljgView(jgid) {
	showDialog("活动结果查看", 800, 550, "hdgl_hdbljg.do?method=viewHdbljg&jgid="+jgid);
}


function save(type) {
	var download = jQuery(".MultiFile-label").length;
	var hdxs = jQuery("#hdxs").val();
	var jzlx = jQuery("#jzlx").val();
	//var checkboxs = jQuery("[name='hdbqs']:checked").length;
	var ids = null;
	if(type=='save'){
		ids = "xh-hdmc-hdsj-hdxs-hdlx-zbf";
	}else{
		ids = "hdmc-hdsj-hdxs-hdlx-zbf";
	}
    if("讲座" == hdxs){
       ids += "-zjrxm-zjrdw-zjrzc-zjrzw-jzjb";
    }
	if(!checkNotNull(ids)){
		showAlert("请将带<font color='red'>*</font>的项目填写完整");
		return false;
	}
	if(download < 1){
		showAlert("请上传附件");
		return false;
	}
	var nlbqLen = jQuery("[name='nlbqs']:checked").length;
	if(nlbqLen > 3){
        showAlert("能力标签最多只能选三个，请确认！");
        return false;
	}
//	if(checkboxs < 1){
//		showAlert("请至少选择一个活动标签！");
//		return false;
//	}
	
	if("课程" == hdxs){
		if(jzlx == null || jzlx == ''){
			showAlert("请选择课程级别！");
			return false;
		} else {
			var zxkclx = jQuery("#zxkclx").val();
			if(zxkclx == ""){
                showAlert("请选择自选课程类型！");
                return false;
			}
		}
	}


	
	var url = "hdgl_hdbljg.do?method=saveJg&type=" + type;
	ajaxSubFormWithFun("hdbljgForm", url, function(data) {
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

function add(){
	var url = "hdgl_hdbljg.do?method=addHdbljg";
	var title = "新增活动结果";
	showDialog(title, 800, 550, url);
}

/**
 * 活动形式onchang事件
 * @return
 */
function changeHdxs(){
	var hdxs = jQuery("#hdxs").val();
	if("课程" == hdxs){
		jQuery("#jzlxTr").show();
        jQuery("tr[name='zjrxx_tr']").hide();
        JzInfoEmpty();
	}else if("讲座" == hdxs){

        jQuery("tr[name='zjrxx_tr']").show();
        jQuery("#lx_span").html("具体类型");
        jQuery("#con_span").html("讲座介绍");

        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");
	}else{
        jQuery("tr[name='zjrxx_tr']").hide();
        JzInfoEmpty();
        jQuery("#jzlxTr").hide();
        jQuery("#jzlx").val("");
        jQuery("#zxkclx").val("");

        var hdlx = jQuery("#hdlx").val();
        if ("活动"==hdxs && "4"==hdlx){
            jQuery("#zysc").show();
        }else {
            jQuery("#zysc").hide();
        }

	}
}

function changeHdlx() {
    var hdxs = jQuery("#hdxs").val();
    var hdlx = jQuery("#hdlx").val();
    if("活动"==hdxs && "4"==hdlx){
        jQuery("#zysc").show();
    }else {
        jQuery("#zysc").hide();
    }
}
function JzInfoEmpty(){
	var tr = jQuery("tr[name='zjrxx_tr']");
	tr.hide();
    tr.find("input").val("");
    tr.find("textarea").val("");
    tr.find("select").val("");
    jQuery("#lx_span").html("活动类型");
    jQuery("#con_span").html("活动内容及心得");
}
/**
 * 课程级别change
 */
function kcjbChange(){
	var v = jQuery("#jzlx").val();
	if("003" == v){
        jQuery("#zxkclxTh").removeAttr("style");
        jQuery("#zxkclxTd").removeAttr("style");
	} else {
        jQuery("#zxkclxTh").attr("style","display:none");
        jQuery("#zxkclxTd").attr("style","display:none");
	}
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(rows[0]['sjly']=='1'){
			showAlertDivLayer("审核流程过来的记录不能修改！");
	}else{
		var url = 'hdgl_hdbljg.do?method=updateHdbljg&jgid=' + rows[0]["jgid"];
		var title = "活动结果修改";
		showDialog(title, 800, 550, url);
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
		var url = "hdgl_hdbljg.do?method=delHdbljg";
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


var DCCLBH = "hdgl_hdbl_hdbljg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, hdjgExportData);
}

//导出方法
function hdjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "hdgl_hdbljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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

//导入
function dr() {
	// 调用通用的导入function，参数是导入功能模块代码。
	toImportDataNew("IMPORT_XAJT_HDJG");
	return false;

}
//个性化导入
function hdbljgImport(){
    var drmkdm = 'IMPORT_XAJT_HDJG';
    var url = "hdgl_hdbljg.do?method=hdbljgImport" + "&drmkdm=" + drmkdm;
    showDialog('导入',720,580,url,{close:function(){
        if (jQuery("#search_go")){
            jQuery("#search_go").click();
        }
    }});
    return false;
}