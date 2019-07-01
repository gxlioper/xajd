function pxztLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='pxztView(\""+row["pxid"]+"\")'>"+cellValue+"</a>";
}

function pxztView(pxid){
	showDialog("查看培训详情",600, 400,"xlzx_pxwh.do?method=pxwhView&pxid="+pxid);
}

function ybmxsLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='ybmxsView(\""+row["pxid"]+"\")'>"+cellValue+"</a>";
}

function ybmxsView(pxid){
	showDialog("已报名学生查看",800, 500,"xlzx_pxwh.do?method=ybmxsList&pxid="+pxid);
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

var DCCLBH = "xg_xlzx_pxwh.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ExportDatas);
}

//导出方法
function ExportDatas() {
	setSearchTj();//设置高级查询条件
	var url = "xlzx_pxwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//删除
function del() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	 }
	var flag = false;
	 for(var i = 0; i < rows.length; i++){
		 if(rows[i]["ybmrs"] != '0'){
			 flag = true;
		 }
	 }
	 if(flag){
		 showAlertDivLayer("已有学生报名，无法删除！");
		 return false;
	 }
	var ids = jQuery("#dataTable").getSeletIds();
	showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
		jQuery.post("xlzx_pxwh.do?method=pxwhDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}

//增加页面跳转
function add(){
	showDialog('增加培训项目',600,450,'xlzx_pxwh.do?method=pxwhAdd');
}


//编辑页面跳转
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改培训信息',600,450,'xlzx_pxwh.do?method=pxwhEdit&pxid=' + rows[0]["pxid"]);
	}
}

//保存
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	var ybmrs=jQuery("#ybmrs").html();
	if(ybmrs){ //编辑页面存在ybmrs，进入判断
		var sxrs=jQuery("#sxrs").val();
		if(parseInt(sxrs)<parseInt(ybmrs)){
			showAlertDivLayer("上限人数小于已报名人数！");
			return false;
		}
	}
	if(jQuery("#jssj").val()>jQuery("#pxsj").val()){
		showAlertDivLayer("报名结束时间不能晚于培训时间");
		return false;
	}
	//lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data["success"] == "false"){
				showAlert(data["message"]);
			} else {
				showAlert(data["message"], {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
			}
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	//unlock();
}
