

jQuery(function(){
	var gridSetting = {
			caption:"绿色通道结果列表",
	pager:"pager",
	url:"rcsw_lstd_jggl.do?method=lstdjgManage&type=query",
	colList:[
	   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
		{label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
		{label:'学年',name:'xn', index: 'xn',width:'9%'},
		{label:'学期',name:'xqmc', index: 'xqmc',width:'3%'},
		{label:'姓名',name:'xm', index: 'xm',width:'8%'},
		{label:'年级',name:'nj', index: 'nj',width:'5%'},
		{label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'10%'},
		{label:'班级',name:'bjmc', index: 'bjdm',width:'10%'},
		{label:'申请时间',name:'sqsj', index: 'sqsj',width:'16%'},
		{label:'申请理由',name:'lxmc', index: 'lxmc',width:'15%'},
		{label:'流程数据',name:'sjly', index: 'sjly',hidden:true}
	],
	sortname: "sqsj",
 	sortorder: "desc"
}
	jQuery("#dataTable").initGrid(gridSetting);			
	
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_lstd_jggl.do?method=addLstdsqjg";
	var title = "增加结果信息";
	showDialog(title,680,410,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(sjly == '1'){
		showAlertDivLayer("流程数据不能修改！");
	}else {
		var url = 'rcsw_lstd_jggl.do?method=updateLstdjg&jgid='+ rows[0]["jgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "修改结果信息";
		showDialog(title, 680,410, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("流程数据不能删除！");
				return false;
			}
		}
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_lstd_jggl.do?method=delLstdjg", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="流程数据不能修改！";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function lstdjgView(jgid, xh) {
	showDialog("结果查询", 680,410, "rcsw_lstd_jggl.do?method=viewOneLstdjg&jgid=" + jgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='lstdjgView(\""
			+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_lstd_jg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, lstdjgExportData);
}

// 导出方法
function lstdjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_lstd_jggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

