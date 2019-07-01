function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewXsJqlxSq(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


function viewXsJqlxSq(sqid, xh) {
	showDialog("学生假期留校结果查询", 750, 420, "rcsw_jqlx.do?method=viewJqlxsq&sqid=" + sqid
			+ "&xh=" + xh);
}

function add(){
	var url = "rcsw_jqlx.do?method=addJqlxJg";
	var title = "新增假期留校结果";
	showDialog(title,750,530,url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var sjlx = rows[0]["sjlx"];
		/*if (sjlx=='1'){
			showAlertDivLayer("审批流数据不允许修改！");
			return false;
		}else{*/
		if(jQuery("#xxdm").val() == '10344'){
			if (sjlx=='1'){
				showAlertDivLayer("审批流数据不允许修改！");
				return false;
			}
		}
			var url = 'rcsw_jqlx.do?method=updateJqlxJg&sqid='+rows[0]["sqid"]+'&xh='+rows[0]["xh"];
			var title = "修改假期留校结果";
			if (sjlx=='1'){
				showDialog(title,750,543,url);
			}else{
				showDialog(title,750,530,url);
			}
		//}
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("rcsw_jqlx.do?method=delJqlxJg", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据。";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="的记录为审核流数据，不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//自定义导出 功能
function exportConfig() {
	var exportBh = "rcsw_jqlxjg.do";
	if("11458" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11458.do";
	}
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11488.do";
	}
	//DCCLBH导出功能编号,执行导出函数 
	customExport(exportBh, xszbbjgExportData);
}

// 导出方法
function xszbbjgExportData() {
	setSearchTj();//设置高级查询条件
	var exportBh = "rcsw_jqlxjg.do";
	if("11458" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11458.do";
	}
	if("11488" == jQuery("#xxdm").val()){
		exportBh = "rcsw_jqlxjg_11488.do";
	}
	var url = "rcsw_jqlx.do?method=exportData&dcclbh="+exportBh;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//打印申请表
function printjqlxsqb(url){
	var sqid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("请至少选择一条记录！");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				sqid +=rows[i]["sqid"];
			}else{
				sqid +=rows[i]["sqid"]+",";
			}
		}		
		var url = url + "&sqid=" +sqid;
		window.open(url);
	}
}