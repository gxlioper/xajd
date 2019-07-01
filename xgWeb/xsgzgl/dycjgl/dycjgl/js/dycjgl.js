/**
 * 志愿服务申请相关js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 学号格式化
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwSqShow(\""
			+ rowObject["fwid"]+"\");'>" + cellValue
			+ "</a>";
}

/**
 * 服务地点过长截取
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}

/**
 * 查看
 */
function bjmdfswh() {
    var rows = jQuery("#dataTable").getSeletRow();
    document.location.href="dycjwh_dycjgl.do?method=bjmdfswh&bjdm="+rows[0]["bjdm"];
}

/**
 * 查看
 */
function viewBjmd() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一条您要修改的记录！");
    } else {
        var url = "dycjwh_dycjgl.do?method=viewBjmd&bjdm=" + rows[0]["bjdm"];
        showDialog("查看", 1043., 638, url);
    }
}





/**
 * 修改弹框页面
 */
function edit() {
	var sqkg = jQuery("#sqkg").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {

		var shzt = rows[0]["shzt"];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("当前申请已关闭，请与管理员联系！");
			return false;
		}
		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("只有未提交和已退回的记录才能修改！");
			return false;
		}

		var url = 'ztbhgl_ztbhsq.do?method=ztbhSqEdit&sqid=' + rows[0]["sqid"];
		var title = "主题班会修改";
		showDialog(title, 800, 550, url);
	}

}




//活动查看
function hdLink(cellValue, rowObject) {
    return "<a href='javascript:void(0);' class='name' onclick='View(\""
        + rowObject['sqid'] + "\");'>" + cellValue
        + "</a>";
}


function View(sqid) {
    showDialog("活动信息", 900, 450, "ztbhgl_ztbhsq.do?method=getHdInfo&sqid=" + sqid );
}

/**
 * 下载德育成绩导入模版
 */
function downloadExcl(){
    var bjdm = jQuery("#bjdm").val();
    var url = "dycjwh_dycjgl.do?method=downloadExcel&bjdm="+bjdm;
    jQuery("#DycjglForm").attr("target","_blank");
    jQuery("#DycjglForm").attr("action",url);
    jQuery("#DycjglForm").submit();

}



/**
 * 下载补考成绩导入模版
 */
function downloadExclBhg(){
    var bjdm = jQuery("#bjdm").val();
    var url = "dycjwh_dycjgl.do?method=downloadExclBhg&bjdm="+bjdm;
    jQuery("#DycjglForm").attr("target","_blank");
    jQuery("#DycjglForm").attr("action",url);
    jQuery("#DycjglForm").submit();

}


/**
 * 导入 德育成绩--上传文件
 * @returns {Boolean}
 */
function uploadDycj(){
    var file = jQuery("#importFile").val();
    if (file == "")
        return false;
    if (file.substring(file.length-4,file.length) != ".xls"){
        showAlert("导入文件只能为.xls格式,请确认！");
        return false;
    }
    url = "dycjwh_dycjgl.do?method=importDycj";
    jQuery("#DycjglForm").attr("target","");
    jQuery("#DycjglForm").attr("action",url).submit();
    if (parent.window){
        var api = frameElement.api,W = api.opener;
        W.jQuery("#dataTable").reloadGrid();
    }
}

/**
 * 导入 德育成绩不合格--上传文件
 * @returns {Boolean}
 */
function uploadBhg(){
    var file = jQuery("#importFile").val();
    if (file == "")
        return false;
    if (file.substring(file.length-4,file.length) != ".xls"){
        showAlert("导入文件只能为.xls格式,请确认！");
        return false;
    }
    url = "dycjwh_dycjgl.do?method=importDycjBhg";
    jQuery("#DycjglForm").attr("target","");
    jQuery("#DycjglForm").attr("action",url).submit();
    if (parent.window){
        var api = frameElement.api,W = api.opener;
        W.jQuery("#dataTable").reloadGrid();
    }
}





