//初始化查询
var bj_gridSetting = {
		caption:"班级信息列表",
		pager:"pager",
		url:"xsxx_bjgl.do?method=bjList&type=query",
		colList:[
		   {label:'年级',name:'nj', index: 'nj',width:'10%'},//,formatter:xhLink
		   {label:'学院',name:'xymc', index: 'xymc',width:'8%'},
		   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
		   {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
		   {label:'操作',name:'bjmc', index: 'bjmc',width:'5%',formatter:xhLink},
		   {label:'主键',name:'bjdm', index: 'bjdm',key:true,hidden:true}
		],
		sortname: "bjdm",
	 	sortorder: "asc"
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='fdyrz_sq(\""+rowObject["bjdm"]+"\")'>申请</a>";
}

function goFdyrzsq(){
	refreshForm("szdw_fdyrz_sq.do?method=fdyrzsq");
}
function query(){
	var map = {};
	map["nj"] = jQuery("#nj").val();
	map["xydm"] = jQuery("#xy").val();
	map["zydm"] = jQuery("#zy").val();
	jQuery("#dataTablebj").reloadGrid(map);
}
//辅导员任职
function fdyrz_sq(bjdm){
	var xzbj_text = jQuery("#xzbj_text").val();
	var xzbj_text_len =xzbj_text.split(",").length;
	var zjz = jQuery("#zjz").val();
	if(zjz==""){
		alertInfo("请选择专兼职！");
	}else if(zjz=="兼职"&& xzbj_text_len>2){
		alertInfo("兼职辅导员最多申请两个班级！");
	}else{
		jQuery("#xzbj_text").val(jQuery("#xzbj_text").val()+","+bjdm);
		jQuery("#xzbj").load("szdw_fdyrz_sq.do?method=fdyrzsqbj&bjdm="+bjdm+"&type=sq");
	}
}