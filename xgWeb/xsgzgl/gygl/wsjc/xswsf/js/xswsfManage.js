jQuery(function(){
	var gridSetting = {
		caption : "学生卫生分",
		pager : "pager",
		url : "gyglnew_xswsf_12688.do?method=xswsfManage&type=query",
		colList : [
			{label:'guid',name:'guid',index :'guid',key:true,hidden:true},
			{label:'rcid',name:'rcid',index :'rcid',hidden:true},
			{label:'学号',name:'xh',index:'xh',width:'10%', formatter : xhLink},
			{label:'姓名',name:'xm',index:'xm',width:'10%'},
			{label:'年级',name:'nj',index:'nj',width:'7%'},
			{label:'学院',name:'xymc',index:'xymc',width:'15%'},
			{label:'专业',name:'zymc',index:'zymc',width:'15%'},
			{label:'检查日程',name:'jcrcxx',index:'jcrcxx',width:'20%'},
			{label:'分数',name:'fs',index:'fs',width:'7%'}
		],
	}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})
//学号链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewWmqsxs(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
}
function viewWmqsxs(id){
	showDialog("查看学生卫生分信息", 600, 450, "gyglnew_xswsf_12688.do?method=viewXswsf&guid="+id);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function edit(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
		return false;
	} else {
		showDialog('学生卫生分修改',600,450,'gyglnew_xswsf_12688.do?method=xswsfEdit&guid=' + rows[0]["guid"]);
	}
}
function save(){
	
	var fs = jQuery("#fs").val();
	if(fs == "" || fs==null){
		alertInfo("请填写分数！");
		return false;
	}
	var url = "gyglnew_xswsf_12688.do?method=xswsfSave";
	ajaxSubFormWithFun("xswsfForm", url, function(data) {
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
		var fs = jQuery("#fs").val();
		if(fs != ""){
			fs = fs/2;
			jQuery("#fs").val(fs.toFixed(2))
		}
}

function printWmqs(){
	showDialog('文明寝室名单生成',350,200,'gyglnew_xswsf_12688.do?method=printWmqs');
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
	var url = "gyglnew_xswsf_12688.do?method=saveXsmd";
	ajaxSubFormWithFun("xswsfForm", url, function(data) {
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



var DCCLBH = "gyglnew_xswsf_xswsfgl.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "gyglnew_xswsf_12688.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}