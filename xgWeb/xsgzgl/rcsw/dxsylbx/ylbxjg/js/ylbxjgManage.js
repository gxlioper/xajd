var gridSetting = {
		caption:"医疗保险申请列表",
		pager:"pager",
		url:"rcsw_dxsylbx_ylbxjggl.do?method=ylbxjgManage&type=query",
		colList:[      
		   {label:'key',name:'yljgid', index: 'yljgid',key:true ,hidden:true},
		   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
		   {label:'学号',name:'xh', index: 'xh',width:'7%',formatter:xhLink},
		   {label:'姓名',name:'xm', index: 'xm',width:'5%'},
		   {label:'性别',name:'xb', index: 'xb',width:'3%'},
		   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
		   {label:'学年',name:'xn', index:'xn',width:'6%'},
		   {label:'学期',name:'xqmc', index:'xqmc',width:'5%'},
//		   {label:'参保状况',name:'cbzkmc', index: 'cbzkmc',width:'6%',formatter:cbzkLink},
		   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
		   {label:'参保状况代码',name:'cbzkdm', index: 'cbzkdm',hidden:true},
		   {label:'学期',name:'xq', index: 'xq',hidden:true},
		   {label:'补助代码',name:'czqebzdm', index: 'czqebzdm',hidden:true},
		   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
		],
		sortname: "cbsj",
	 	sortorder: "desc"
}
//{label:'参保时间',name:'cbsj', index: 'cbsj',width:'8%'},
jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);			
	
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_dxsylbx_ylbxjggl.do?method=addYlbxjg";
	var title = "增加大学生医疗保险结果信息";
	showDialog(title,790,520,url);
}



function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(sjly == '1'){
		showAlertDivLayer("审核流数据，不能修改！");
	}else {
		var url = 'rcsw_dxsylbx_ylbxjggl.do?method=updateYlbxjg&yljgid='+ rows[0]["yljgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "修改大学生医疗保险结果信息";
		showDialog(title, 790, 520, url);
	}

}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_dxsylbx_ylbxjggl.do?method=delYlbxjg", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="已走完审核流不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});		
	}
}

function ylbxjgView(yljgid, xh) {
	showDialog("大学生医疗保险结果查看", 720, 460, "rcsw_dxsylbx_ylbxjggl.do?method=viewYlbxjg&yljgid=" + yljgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ylbxjgView(\""
			+ rowObject["yljgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function viewCbzk(yljgid,xm) {
	showDialog("参保状况查看", 450, 220, "rcsw_dxsylbx_ylbxjggl.do?method=viewCbzk&yljgid=" + yljgid +"&xm="+xm);
}

function cbzkLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCbzk(\""
			+ rowObject["yljgid"] + "\",\""+ rowObject["xm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_dxsylbx_ylbxjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ylbxjgExportData);
}

// 导出方法
function ylbxjgExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_dxsylbx_ylbxjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//学生照片批量导出
function photoDownload() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_dxsylbx_ylbxjggl.do?method=photoDownload";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}