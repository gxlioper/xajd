jQuery(function(){
	var gridSetting = {
		caption : "学生卫生分",
		pager : "pager",
		url : "gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdManage&type=query",
		colList : [
			{label:'id',name:'id',index :'id',key:true,hidden:true},
			{label:'学号',name:'xh',index:'xh',width:'10%', formatter : xhLink},
			{label:'姓名',name:'xm',index:'xm',width:'10%'},
			{label:'年级',name:'nj',index:'nj',width:'5%'},
			{label:'学院',name:'xymc',index:'xymc',width:'15%'},
			{label:'专业',name:'zymc',index:'zymc',width:'15%'},
			{label:'班级',name:'bjmc',index:'bjmc',width:'15%'},
			{label:'校级分数',name:'xjfs',index:'xjfs',width:'7%', formatter : xjfsLink},
			{label:'院级分数',name:'yjfs',index:'yjfs',width:'7%', formatter : yjfsLink},
			{label:'评定学年',name:'xn',index:'xn',width:'8%'},
			{label:'评定学期',name:'xqmc',index:'xqmc',width:'7%'}
		],
	}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})
//学号链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewWmqsxs(\""+rowObject["id"]+"\");'>"+cellValue+"</a>";
}
function yjfsLink(cellValue, rowObject) {
	var temp = cellValue.split(".");
	if(temp.length == 1){
		cellValue += ".00"
	}
	return cellValue;
}
function xjfsLink(cellValue, rowObject) {
	var temp = cellValue.split(".");
	if(temp.length == 1){
		cellValue += ".00"
	}
	return cellValue;
}
function viewWmqsxs(id){
	showDialog("查看文明寝室学生信息", 700, 450, "gyglnew_wmqsxsmd_12688.do?method=viewWmqsxsmd&id="+id);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function add(){
	showDialog('文明寝室学生增加',600,450,'gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdAdd');
}

function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('学生卫生分修改',600,450,'gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdEdit&id=' + rows[0]["id"]);
	}
}
function save(type){
	var xh = jQuery("#xh").val();
	var yjfs = jQuery("#yjfs").val();
	var xjfs = jQuery("#xjfs").val();
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	if("add" == type){
		if(xh == ""){
			alertInfo("请选择学号！");
			return false;
		}
		if(xn == "" || xn==null){
			alertInfo("请选择学年！");
			return false;
		}
		if(xq == "" || xq==null){
			alertInfo("请选择学期！");
			return false;
		}
	}
	if(yjfs == "" || yjfs==null){
		alertInfo("请填写院级分数！");
		return false;
	}
	if(xjfs == "" || xjfs==null){
		alertInfo("请填写校级分数！");
		return false;
	}
	
	var url = "gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdSave&type="+type;
	ajaxSubFormWithFun("wmqsxsmdForm", url, function(data) {
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
function changeFs(obj){
		var fs = jQuery(obj).val();
		if(fs != ""){
			fs = fs/2;
			jQuery(obj).val(fs.toFixed(2))
		}
}

function printWmqs(){
	showDialog('文明寝室名单生成',350,200,'gyglnew_wmqsxsmd_12688.do?method=printWmqs');
}
function saveXsmd(){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	if(xn == ""){
		alertInfo("请选择学年！");
		return false;
	}
	if(xq == ""){
		alertInfo("请选择学期！");
		return false;
	}
	var url = "gyglnew_wmqsxsmd_12688.do?method=saveXsmd";
	ajaxSubFormWithFun("wmqsxsmdForm", url, function(data) {
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



var DCCLBH = "gyglnew_wmqsxsmd_wmqsxsmdgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "gyglnew_wmqsxsmd_12688.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}