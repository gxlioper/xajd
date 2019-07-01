

jQuery(function(){
	var gridSetting = null;
	var xxdm = jQuery("#xxdm").val();
	if("11318" == xxdm) {
		gridSetting = {
			caption:"火车乘车区间填写结果列表",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjjggl.do?method=hcccqjjgManage&type=query",
			params:getSuperSearch(),
			colList:[
			   {label:'key',name:'ccqjjgid', index: 'ccqjjgid',key:true ,hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'6%'},
			   {label:'性别',name:'xb', index: 'xb',width:'3%'},
			   {label:'行政班级',name:'bjmc', index: 'bjdm',width:'8%'},
                {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'8%'},
			   {label:'学年',name:'xn', index: 'xn',width:'8%'},
			   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			   {label:'起始站',name:'ccqdz', index: 'ccqdz',width:'8%'},
			   {label:'终点站',name:'cczdz', index: 'cczdz',width:'8%'},
			   {label:'填写时间',name:'txsj', index: 'txsj',width:'6%'},
			   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
			],
			sortname: "txsj",
			sortorder: "desc"
		}
	}else if("13011" == xxdm){
		gridSetting = {
				caption:"火车乘车区间填写结果列表",
				pager:"pager",
				url:"rcsw_hcyhk_hcccqjjggl.do?method=hcccqjjgManage&type=query",
				params:getSuperSearch(),
				colList:[
				    {label:'key',name:'ccqjjgid', index: 'ccqjjgid',key:true ,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'6%'},
					{label:'性别',name:'xb', index: 'xb',width:'3%'},
                    {label:'行政班级',name:'bjmc', index: 'bjdm',width:'8%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'8%'},
					{label:'学年',name:'xn', index: 'xn',width:'8%'},
				    {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
				    {label:'火车乘车区间',name:'hcccqjmc', index: 'hcccqjmc',width:'13%'},
				    {label:'填写时间',name:'txsj', index: 'txsj',width:'6%'},
				    {label:'审核完成时间',name:'shwcsj', index: 'shwcsj',width:'6%'},
					{label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
				],
				sortname: "txsj",
			 	sortorder: "desc"
			}
	}
	else {
	    gridSetting = {
			caption:"火车乘车区间填写结果列表",
			pager:"pager",
			url:"rcsw_hcyhk_hcccqjjggl.do?method=hcccqjjgManage&type=query",
			params:getSuperSearch(),
			colList:[
			    {label:'key',name:'ccqjjgid', index: 'ccqjjgid',key:true ,hidden:true},
				{label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
				{label:'姓名',name:'xm', index: 'xm',width:'6%'},
				{label:'性别',name:'xb', index: 'xb',width:'3%'},
                {label:'行政班级',name:'bjmc', index: 'bjdm',width:'8%'},
                {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'8%'},
				{label:'学年',name:'xn', index: 'xn',width:'8%'},
			    {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
			    {label:'火车乘车区间',name:'hcccqjmc', index: 'hcccqjmc',width:'13%'},
			    {label:'填写时间',name:'txsj', index: 'txsj',width:'6%'},
				{label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
			],
			sortname: "txsj",
		 	sortorder: "desc"
		}
	} 
	jQuery("#dataTable").initGrid(gridSetting);			
});

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var url = "rcsw_hcyhk_hcccqjjggl.do?method=addHcccqjjg";
	var title = "增加火车乘车区间结果信息";
	showDialog(title,790,460,url);
}



function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	}else if(sjly == '1'){
		showAlertDivLayer("审核流数据，不能修改！");
	}else {
		var url = 'rcsw_hcyhk_hcccqjjggl.do?method=updateHcccqjjg&ccqjjgid='+ rows[0]["ccqjjgid"]
		+ '&xh=' + rows[0]["xh"];
		var title = "修改火车乘车区间结果信息";
		showDialog(title, 720, 460, url);
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
				jQuery.post("rcsw_hcyhk_hcccqjjggl.do?method=delHcccqjjg", {
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

function viewHcccqjjg(ccqjjgid, xh) {
	showDialog("火车乘车区间结果查询", 720, 440, "rcsw_hcyhk_hcccqjjggl.do?method=viewHcccqjjg&ccqjjgid=" + ccqjjgid
			+ "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewHcccqjjg(\""
			+ rowObject["ccqjjgid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

var DCCLBH = "rcsw_hcyhk_hcccqjjg.do";//dcclbh,导出功能编号

//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, xszbbjgExportData);
}

// 导出方法
function xszbbjgExportData() {
	
	setSearchTj();//设置高级查询条件
	var url = "rcsw_hcyhk_hcccqjjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
	
}