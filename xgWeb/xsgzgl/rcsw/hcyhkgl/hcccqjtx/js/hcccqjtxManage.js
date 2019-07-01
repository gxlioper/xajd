

jQuery(function(){
	var gridSetting = null;
	var xxdm = jQuery("#xxdm").val();
	if("11318" == xxdm) {
		gridSetting = {
			caption:"火车乘车区间填写列表",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjtxgl.do?method=hcccqjtxManage&type=query",
			params:getSuperSearch(),
			colList:[
			   {label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'性别',name:'xb', index: 'xb',width:'5%'},
			   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'起始站',name:'ccqdz', index: 'ccqdz',width:'8%'},
			   {label:'终点站',name:'cczdz', index: 'cczdz',width:'8%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "txsj",
			sortorder: "desc"
		}
		
	}else {
	    gridSetting = {
			caption:"火车乘车区间填写列表",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjtxgl.do?method=hcccqjtxManage&type=query",
			params:getSuperSearch(),
			colList:[
			   {label:'key',name:'ccqjtxid', index: 'ccqjtxid',key:true ,hidden:true},
			   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'性别',name:'xb', index: 'xb',width:'5%'},
			   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'火车乘车区间',name:'hcccqjmc', index: 'hcccqjmc',width:'13%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "txsj",
		 	sortorder: "desc"
		}
	}
	jQuery("#dataTable").initGrid(gridSetting);
	
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_null_isopen").show();
		return false;
	}
	if ("false" == isopen){
		jQuery("#prompt_isopen").show();
		jQuery("#prompt_false_isopen").show();
		return false;
	}
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}

function add(){
	
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var url = "rcsw_hcyhk_hcccqjtxgl.do?method=addHcccqjtx";
	var title = "填写乘车区间";
	showDialog(title,790,450,url);
	
}



function update() {
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		
		var shzt = rows[0]["shzt"];
		if (shzt=='0'||shzt=='3'){
			var url = 'rcsw_hcyhk_hcccqjtxgl.do?method=updateHcccqjtx&ccqjtxid=' + rows[0]["ccqjtxid"] + '&xh=' + rows[0]["xh"];
			showDialog("修改填写乘车区间", 720, 450, url);
		}else{
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
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
				jQuery.post("rcsw_hcyhk_hcccqjtxgl.do?method=delHcccqjtx", {
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

function viewHcccqjtx(ccqjtxid,xh) {
	showDialog("火车优惠卡申请查询", 720, 520, "rcsw_hcyhk_hcccqjtxgl.do?method=viewHcccqjtx&ccqjtxid=" + ccqjtxid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHcccqjtx(\""
			+ rowObject["ccqjtxid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



var DCCLBH = "rcsw_hcyhk_hcccqjtx.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, rcxwxxwhExportData);
}

// 导出方法
function rcxwxxwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "rcsw_hcyhk_hcccqjtxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}