
jQuery(function(){
	gridSetting["params"]={"xn":jQuery("#xn").val(),"lxdm":jQuery("#lxdm").val(),"xzdm":jQuery("#xzdm").val(),"xmmc":jQuery("#xmmc").val()};
	jQuery("#dataTable").initGrid(gridSetting);
});

//点击返回按钮，返回到父页面并刷新
function reBack(){
	refreshForm("/xgxt/xg_pjpy_tjgl_pjxmhz.do?method=viewPjxmhz");
}

//定义DCCLBH
var DCCLBH='xg_pjpy_tjgl_pjxmhzRs.do';

//导出配置 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出的函数
	customExport(DCCLBH, pjxmhzRsExportData);
}

// 导出方法
function pjxmhzRsExportData() {
	var xn = jQuery("#xn").val();
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
	var xmmc = jQuery("#xmmc").val();
	var url = "xpj_pjxmhz.do?method=pjxmhzRsExportData&dcclbh=" + DCCLBH + "&xn=" + xn + "&lxdm=" + lxdm + "&xzdm="+ xzdm + "&xmmc=" + xmmc;//dcclbh,导出功能编号
	jQuery("form").eq(0).attr("action",url);
	jQuery("form").eq(0).submit();
}



function pjjgView(id,xh){
	var title = "评奖结果查询";
	showDialog(title,650,480,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='pjjgView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}