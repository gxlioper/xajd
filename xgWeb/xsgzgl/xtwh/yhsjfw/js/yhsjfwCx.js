var gridSetting = {
	caption:"查询结果",
	pager:"pager",
	url:"xtwh_yhsjfw.do?method=yhsjfwCx&type=query",
	colList:[
	   {label:'用户名',name:'yhm', index: 'yhm',width:'8%',key : true},
	   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
	   {label:'所属组',name:'zmc', index: 'zmc',width:'10%'},
	   {label:'所属部门',name:'bmmc', index: 'bmmc',width:'15%'},
	   {label:'辅导员',name:'fdynum', index: 'fdynum',width:'3%',formatter:setSffdyBzr},
	   {label:'班主任',name:'bzrnum', index: 'bzrnum',width:'3%',formatter:setSffdyBzr},
	   {label:'已授权范围',name:'sjfwmc', index: 'sjfwmc',noSort:true}
	],
	sortname: "yhm",
 	sortorder: "asc"
}


jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs(){
	var map = {};
	map["yhm"] = jQuery.trim(jQuery("#yhm").val());
	map["xm"] = jQuery.trim(jQuery("#xm").val());
	map["zdm"] = jQuery("#zdm").val();
	map["szbm"] = jQuery("#szbm").val();
	map["sffdy"] = jQuery("#sffdy").val();
	map["sfbzr"] = jQuery("#sfbzr").val();
	jQuery("#dataTable").reloadGrid(map);
}

/*
 * 设置是否辅导员、班主任
 */
function setSffdyBzr(cellValue,rowObject){
	var value;
	if(cellValue == null || cellValue == ""){
		value = "否";
	}else{
		value = "是";
	}
	return value;
}

function yhsjfwSq(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要操作的记录！");
	}else{
		var url = "xtwh_yhsjfw.do?method=yhsjfwSq";
		url += "&ids=" + ids.toString();
		var title = "用户数据范围授权";
		showDialog(title,815,550,url);
	}
}
