
// 查看学生异动信息
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeXsydInfo(\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

//备注过长截取
function bzSubstring(cellValue,row){
	if(cellValue==null){
		cellValue="";
	}
	var strValue = cellValue;
	if(strValue.length > 10){
		strValue = strValue.substring(0, 10)+"...";
	}
	return "<span title='"+cellValue+"'>"+strValue+"</span>";;
}
//查看退宿床位信息
function tscwLink(cellValue,row){
	if( cellValue!=null && cellValue!=""){
		return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydqlddm"]+"\",\""+row["ydqqsh"]+"\",\""+row["ydqcwh"]+"\")'>"+cellValue+"</a>";
	}else{
		return "";
	}
}

//查看入住床位信息
function rzcwLink(cellValue,row){
	if( cellValue!=null && cellValue!=""){
		return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydhlddm"]+"\",\""+row["ydhqsh"]+"\",\""+row["ydhcwh"]+"\")'>"+cellValue+"</a>";
	}else{
		return "";
	}
}


//入住时间
function rzsjLink(cellValue,row){
	if( cellValue!=null && cellValue!=""){
		return cellValue.substr(0,10);
	}else{
		return "";
	}
}




//查看床位信息
function seeCwInfo(lddm,qsh,cwh){
	showDialog("查看床位信息",850,350,"ydjg.do?method=ckQsydInfo&ydqlddm="+lddm+"&ydqqsh="+qsh+"&ydqcwh="+cwh,null);
}


//查看学生异动信息
function seeXsydInfo(xh){
	showDialog("查看学生宿舍异动信息",650,480,"ydjg.do?method=ckXsydInfo&xh="+xh,null);
}
//查看单条宿舍异动信息
function ckYdjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要查看的记录！");
	}  else{
		showDialog("宿舍异动信息",760,390,'ydjg.do?method=ydjgck&ssydid='+rows[0]["ssydid"]);
	}
}

//申请
function addYdjg() {
		var url ="ydjg.do?method=add";
		var title = "宿舍异动增加";
		showDialog(title, 800, 490, url);
}

//查询
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function exportConfig() {
	customExport("ydjgbase.do", exportData,690,500);
}
// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "ydjg.do?method=exportData&dcclbh=ydjgbase.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//打印报表
function printTstzd() {
	var ssydid = jQuery("#dataTable").getSeletIds();
	if (ssydid.length <=0) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	for(var i=0; i<ssydid.length; i++){
		if("退宿"==rows[i]['ssydlxmc'] || "入住"==rows[i]['ssydlxmc']){
			showAlertDivLayer('"入住"/"退宿"不能打印"调宿通知单"');
			return false;
		}
	}
	
	var url = "ydjg.do?method=printTstzd&ssydid=" +ssydid.toString();
	
	window.open(url);
}

function printTsd() {
	var ssydid = jQuery("#dataTable").getSeletIds();
	if (ssydid.length <=0) {
		showAlertDivLayer("请选择一条记录！");
		return false;
	}
	
	var rows = jQuery("#dataTable").getSeletRow();
	
	for(var i=0; i<ssydid.length; i++){
		if("宿舍调整"==rows[i]['ssydlxmc'] || "入住"==rows[i]['ssydlxmc']){
			showAlertDivLayer('"宿舍调整"/"入住"不能打印"退宿单"');
			return false;
		}
	}
	
	var url = "ydjg.do?method=printTsd&ssydid=" +ssydid.toString();
	
	window.open(url);
}
