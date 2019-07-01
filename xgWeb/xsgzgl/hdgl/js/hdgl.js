/**
 * 活动管理列表页面js
 */

/**
 * 搜索
 */
function searchRs(){
    var map = getSuperSearch();
    jQuery("#dataTable").reloadGrid(map);
}

/**
 * 活动管理详情页面显示
 */
function hdShow(hdid) {
    var url = "hdgl_hdgl.do?method=hdglView&hdid="+hdid;
    document.forms[0].action=url;
    document.forms[0].submit();
}

/**
 * 专家团成员设置
 */
function zjtcysz() {

    var ids = jQuery("#dataTable").getSeletIds();
    if (ids.length > 0) {
        var str = "";
        for(i=0;i<ids.length;i++){
            blog=true;
            str+="&ids="+ids[i];
        }
        var url = "hdgl_hdgl.do?method=zjtcysz"+str;
        var title = "专家团成员设置";
        showDialog(title, 800, 500, url);
    }else {
        showAlertDivLayer("请选择您要设置的记录！");
    }
}

/**
 * 阶段成员设置,用于划定各阶段审核人员权限
 * @return
 */
function jdcysz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length != 1){
		showAlertDivLayer("请选择一条您要操作的记录！");
	}else{
		var url = "hdgl_hdgl.do?method=jdcysz&hdid="+rows[0]["hdid"];
        var title = "阶段成员设置";
        showDialog(title, 800, 500, url);
	}
}

