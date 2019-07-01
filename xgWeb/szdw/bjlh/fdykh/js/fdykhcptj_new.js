/**
 * 辅导员考核统计 新版js
 */
/**
 * 
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	map["tjlx"] = jQuery("#tjlx").val(); 
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * tab 切换
 * @param obj
 * @param tabId
 * @return
 */
function selecttjlb(obj,tabId){
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	jQuery(".con_overlfow table").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	jQuery(".con_overlfow_1 table").css("display","none");
	
	jQuery("#"+tabId+"_1").css("display","");
	
}

/**
 * 
 * @param obj
 * @param tabid
 */
function chageTB(obj,tabid){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	var grid = undefined;
	var map = getSuperSearch();
	if (tabid=="0"){
		jQuery("#tjlx").val('RS');
		grid = getRSGrid();
		map["tjlx"] = "RS"; 
		grid["params"] = map;
		jQuery("#dataTable").initGrid(grid);
	} else if(tabid == "1"){
		jQuery("#tjlx").val('BJ');
		grid = getBJGrid();
		map["tjlx"] = "BJ"; 
		grid["params"] = map;
		jQuery("#dataTable").initGrid(grid);
	}else{
		jQuery("#tjlx").val('ZZ');
		grid = getZZGrid();
		map["tjlx"] = "ZZ";  
		grid["params"] = map;
		jQuery("#dataTable").initGrid(grid);
	}
}

/**
 * 获取统计表格
 * @param type 表格类型 BJ.按班级统计表格/ZZ.按职责统计表格/RS.按人数统计表格
 * @return dataGrid
 */
function getRSGrid(){
	var gridSettingRS = {
			caption:"辅导员考核统计结果",
			pager:"pager",
			url:"bjlh_fdykh.do?method=fdykhKhcptjNew&type=query",
			colList:[
			   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'所在部门',name:'bmmc', index: 'bmmc',width:'10%'},
			   {label:'带班<br/>总数',name:'fdybjs', index: 'fdybjs',width:'2%'},
			   {label:'学生<br/>已评<br/>分人数/<br/>总人数',name:'xspfqk', index: 'xspfqk',width:'3%' , formatter:xhLink},
			   {label:'当前分',name:'xspjf', index: 'xspjf',width:'2%'},
			   {label:'有效分',name:'yxxspjf', index: 'yxxspjf',width:'2%'},
			   {label:'考核<br/>小组<br/>已评<br/>分人数/<br/>总人数',name:'cpxzpfqk', index: 'cpxzpfqk',width:'3%'},
			   {label:'平均<br/>分',name:'cpxzpjf', index: 'cpxzpjf',width:'6%'},
			   {label:'总分',name:'zf', index: 'zf',width:'6%'}
			],
			sortname: "xm",
			sortorder : "asc"
		}
	return gridSettingRS;
}
/**
 * 获取统计表格
 * @param type 表格类型 BJ.按班级统计表格/ZZ.按职责统计表格/RS.按人数统计表格
 * @return dataGrid
 */
function getBJGrid(){
	var gridSettingRS = {
			caption:"辅导员考核统计结果",
			pager:"pager",
			url:"bjlh_fdykh.do?method=fdykhKhcptjNew&type=query",
			colList:[
			   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'班级名称',name:'bjmc', index: 'bjmc',width:'13%'},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'所在部门',name:'bmmc', index: 'bmmc',width:'10%'},
			   {label:'班主任/<br/>辅导员',name:'zzlbmc', index: 'zzlbmc',width:'10%'},
			   {label:'学生<br/>已评<br/>分人数/<br/>总人数',name:'xspfqk', index: 'xspfqk',width:'3%' , formatter:xhLink},
			   {label:'当前分',name:'xspjf', index: 'xspjf',width:'2%'},
			   {label:'有效分',name:'yxxspjf', index: 'yxxspjf',width:'2%'},
			   {label:'考核<br/>小组<br/>已评<br/>分人数/<br/>总人数',name:'cpxzpfqk', index: 'cpxzpfqk',width:'3%'},
			   {label:'平均<br/>分',name:'cpxzpjf', index: 'cpxzpjf',width:'6%'},
			   {label:'总分',name:'zf', index: 'zf',width:'6%'}
			],
			
			sortname: "bjmc",
			sortorder : "asc"
		}
	return gridSettingRS;
}
/**
 * 获取统计表格
 * @param type 表格类型 BJ.按班级统计表格/ZZ.按职责统计表格/RS.按人数统计表格
 * @return dataGrid
 */
function getZZGrid(){
	var gridSettingRS = {
			caption:"辅导员考核统计结果",
			pager:"pager",
			url:"bjlh_fdykh.do?method=fdykhKhcptjNew&type=query",
			colList:[
			   {label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
			   {label:'学年',name:'xn', index: 'xn',width:'10%'},
			   {label:'职工号',name:'zgh', index: 'zgh',width:'10%' ,hidden:true},
			   {label:'姓名',name:'xm', index: 'xm',width:'9%'},
			   {label:'所在部门',name:'bmmc', index: 'bmmc',width:'10%'},
			   {label:'班主任/<br/>辅导员',name:'zzlbmc', index: 'zzlbmc',width:'10%'},
			   {label:'带班<br/>总数',name:'bjs', index: 'bjs',width:'2%'},
			   {label:'学生<br/>已评<br/>分人数/<br/>总人数',name:'xspfqk', index: 'xspfqk',width:'3%' , formatter:xhLink},
			   {label:'当前分',name:'xspjf', index: 'xspjf',width:'2%'},
			   {label:'有效分',name:'yxxspjf', index: 'yxxspjf',width:'2%'},
			   {label:'考核<br/>小组<br/>已评<br/>分人数/<br/>总人数',name:'cpxzpfqk', index: 'cpxzpfqk',width:'3%'},
			   {label:'平均<br/>分',name:'cpxzpjf', index: 'cpxzpjf',width:'6%'},
			   {label:'总分',name:'zf', index: 'zf',width:'6%'}
			],
			sortname: "zzlbmc",
			sortorder : "asc"
		}
	return gridSettingRS;
}

function xhLink(cellValue,rowObject){
	
	var rs = cellValue.split('/');
	
	//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	var onclickfn = "onclick=\"" + "showDialog('考核明细' , 600 , 400 , 'bjlh_fdykh.do?method=fdykhKhMxNew&pk=" + rowObject['pk'] + "')" + "\"";
	
	var href = "href = 'javascript:void(0);'";

	if(rs[0] === '0'){
		return rs[0] +"/" + rs[1];
	}else 
		return  "<a " + href + " class='name' " + onclickfn + " >" + rs[0] + "</a>" + "/" + rs[1];
}

var DCCLBH = "";

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	
	DCCLBH = "bjlh_fdykh_new_";
	
	var currentTab =  jQuery("#tjlx").val();
	
	DCCLBH = DCCLBH + currentTab + ".do";
	
	customExport(DCCLBH, exportData);
}

// 导出方法
function exportData() {
	setSearchTj();//设置高级查询条件
	var url = "bjlh_fdykh.do?method=exportDataNew&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}