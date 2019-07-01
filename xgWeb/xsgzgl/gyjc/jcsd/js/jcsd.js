/**
 * 查询
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * 分配人员
 * @param type
 * @return
 */
function fpjcr(type){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		return showAlertDivLayer("有且只能选择一条记录！");
	}
	document.location.href = "xg_gyjc_ryfp.do?xydm="+rows[0]['xydm']+"&jjlx="+type;
}

/**
 * 评分标准维护
 * @return
 */
function pfbzwh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		return showAlertDivLayer("有且只能选择一条记录！");
	}
	document.location.href = "xg_gyjc_pfbz.do?xydm="+rows[0]['xydm']+"&jjlx="+1;

}

/**
 * 楼栋详情formatter
 * @return
 */
function ldLink(cellValue, rowObject){
	return "共："+"<font style='color:#f59213'>"+cellValue+"</font>"+"栋"+"<font style='color:#f59213'>"+rowObject['chnum']+"</font>"+"层"+"<font style='color:#f59213'>"+rowObject['qshnum']+"</font>"+"个寝室";
}

/**
 * 
 * 评分标准formatter
 * @return
 */
function  pfLink(cellValue, rowObject){
	return "共"+"<font style='color:#f59213'>"+cellValue+"</font>"+"条";
}

