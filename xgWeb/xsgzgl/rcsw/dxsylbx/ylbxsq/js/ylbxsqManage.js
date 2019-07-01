var gridSetting = {
	caption:"医疗保险申请列表",
	pager:"pager",
	url:"rcsw_dxsylbx_ylbxsqgl.do?method=ylbxsqManage&type=query",
	colList:[      
	   {label:'key',name:'ylsqid', index: 'ylsqid',key:true ,hidden:true},
	   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
	   {label:'学号',name:'xh', index: 'xh',width:'7%',formatter:xhLink},
	   {label:'姓名',name:'xm', index: 'xm',width:'5%'},
	   {label:'性别',name:'xb', index: 'xb',width:'3%'},
	   {label:'班级',name:'bjmc', index: 'bjdm',width:'10%'},
	   {label:'学年',name:'xn', index: 'xn',width:'6%'},
	   {label:'学期',name:'xqmc', index: 'xqmc',width:'5%'},
//	   {label:'参保状况',name:'cbzkmc', index: 'cbzkmc',width:'6%',formatter:cbzkLink},
	   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
	   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
	   {label:'参保状况代码',name:'cbzkdm', index: 'cbzkdm',hidden:true},
	   {label:'补助代码',name:'czqebzdm', index: 'czqebzdm',hidden:true}
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
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('基础设置未初始化，请联系管理员！');
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer("当前未开放申请，请联系管理员！");
		return false;
	}
	var url = "rcsw_dxsylbx_ylbxsqgl.do?method=addYlbxsq";
	var title = "增加医疗保险申请";
	showDialog(title,790,520,url);
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer('基础设置未初始化，请联系管理员！');
		return false;
	}
//	if ("false" == isopen){
//		showAlertDivLayer("当前未开放申请，请联系管理员！");
//		return false;
//	}
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var shzt = rows[0]["shztmc"];
		if ("审核中" == shzt){
			showAlertDivLayer("该信息记录正在审核中，不能修改！");
			return false;
		}
		if ("通过" == shzt){
			showAlertDivLayer("该信息记录审核已经通过，不能修改！");
			return false;
		}
		if ("不通过" == shzt){
			showAlertDivLayer("该信息记录审核已经不通过，不能修改！");
			return false;
		}
		var url = 'rcsw_dxsylbx_ylbxsqgl.do?method=updateYlbxsq&ylsqid=' + rows[0]["ylsqid"]
		+ '&xh=' + rows[0]["xh"] +'&cbzkdm=' + rows[0]["cbzkdm"]+'&czqebzdm=' + rows[0]["czqebzdm"];
		var title = "修改医疗保险申请";
		showDialog(title,790,520,url);
	}

}


//删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_dxsylbx_ylbxsqgl.do?method=delYlbxsq", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的申请已经提交不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


function viewYlbxsq(ylsqid, xh) {
	showDialog("医疗保险申请查看", 700, 480, "rcsw_dxsylbx_ylbxsqgl.do?method=viewYlbxsq&ylsqid=" + ylsqid+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewYlbxsq(\""
			+ rowObject["ylsqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



function viewCbzk(ylsqid,xm) {
	showDialog("参保状况查询", 450, 220, "rcsw_dxsylbx_ylbxsqgl.do?method=viewCbzk&ylsqid=" + ylsqid + "&xm="+xm);
}


function cbzkLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewCbzk(\""
			+ rowObject["ylsqid"] + "\",\""+ rowObject["xm"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_dxsylbx_ylbxsq.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, ylbxsqExportData);
}

// 导出方法
function ylbxsqExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_dxsylbx_ylbxsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}