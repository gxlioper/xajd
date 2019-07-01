
function initGridSetting(){
	var gridSetting = {};
	if("10704" == jQuery("#xxdm").val()){
		var gridSetting = {
				caption : "特殊学生列表",
				pager : "pager",
				url : "xlzx_tsxs.do?method=tsxsManage&doType=query",
				colList:[ 
				   {label : 'id',name : 'id',index : 'id',hidden : true}, 
				   {label : '学号',name : 'xh',index : 'xh',formatter : xhLink}, 
				   {label : '姓名',name : 'xm',index : 'xm'}, 
				   {label : '性别',name : 'xb',index : 'xb'}, 
				   {label : '年级',name : 'nj',index : 'nj'}, 
				   {label : '学院名称',name : 'xymc',index : 'xymc'}, 
				   {label : '班级',name : 'bjmc',index : 'bjmc'}, 
				   {label : '维护时间',name : 'xgsj',index : 'xgsj'}, 
				   {label : '预警程度',name : 'knlxdm',index : 'knlxdm',formatter : getKnlxmc}
				  ],
				sortname : "",
				sortorder : ""
			}
		
		
		
	}else if("11527" == jQuery("#xxdm").val()){
		var gridSetting = {
				caption : "特殊学生列表",
				pager : "pager",
				url : "xlzx_tsxs.do?method=tsxsManage&doType=query",
				colList:[ 
				   {label : 'id',name : 'id',index : 'id',hidden : true}, 
				   {label : '学号',name : 'xh',index : 'xh',formatter : xhLink}, 
				   {label : '姓名',name : 'xm',index : 'xm'}, 
				   {label : '性别',name : 'xb',index : 'xb'}, 
				   {label : '年级',name : 'nj',index : 'nj'}, 
				   {label : '学院名称',name : 'xymc',index : 'xymc'}, 
				   {label : '班级',name : 'bjmc',index : 'bjmc'}, 
				   {label : '维护时间',name : 'xgsj',index : 'xgsj'}, 
				   {label : '困难类型',name : 'knlxdm',index : 'knlxdm',formatter : getKnlxmc},
				   {label : '关注状态',name : 'gzztmc',index : 'gzztmc'},
				   {label : '周次',name : 'zc',index : 'zc'}
				  ],
				sortname : "",
				sortorder : ""
			}
	}else{
		var gridSetting = {
				caption : "特殊学生列表",
				pager : "pager",
				url : "xlzx_tsxs.do?method=tsxsManage&doType=query",
				colList:[ 
				   {label : 'id',name : 'id',index : 'id',hidden : true}, 
				   {label : '学号',name : 'xh',index : 'xh',formatter : xhLink}, 
				   {label : '姓名',name : 'xm',index : 'xm'}, 
				   {label : '性别',name : 'xb',index : 'xb'}, 
				   {label : '年级',name : 'nj',index : 'nj'}, 
				   {label : '学院名称',name : 'xymc',index : 'xymc'}, 
				   {label : '班级',name : 'bjmc',index : 'bjmc'}, 
				   {label : '维护时间',name : 'xgsj',index : 'xgsj'}, 
				   {label : '困难类型',name : 'knlxdm',index : 'knlxdm',formatter : getKnlxmc},
				   {label : '关注状态',name : 'gzztmc',index : 'gzztmc'}
				  ],
				sortname : "",
				sortorder : ""
		}
	}
  gridSetting["params"]=getSuperSearch();
  jQuery("#dataTable").initGrid(gridSetting);
	
}

jQuery(function(){
	initGridSetting();
});

function searchRs() {
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}
function xhLink(cellValue, rowObject) {
	var rs = "";
	if("11527" == jQuery("#xxdm").val()){
		rs = "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail_11527('"
			+ rowObject["xh"] + "','"+ rowObject["id"] + "','"+rowObject["zc"]+"');\">" + cellValue + "</a>"
	}else{
		rs = "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"
		+ rowObject["xh"] + "','"+ rowObject["id"]+"');\">" + cellValue + "</a>"
	}
	return rs;
}

function getKnlxmc(cellValue, rowObject) {
	var knlxmc = '';
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery.post("xlzx_tsxs.do?method=getKnlxmc", {
		knlxdm : cellValue
	}, function(data) {
		knlxmc = data;
	}, '');
	jQuery.ajaxSetup( {
		async : true
	});
	return knlxmc;
}
function showDetail(xh,id) {
	showDialog("特殊学生详情", 700, 310,
			"xlzx_tsxs.do?method=tsxsDetail&doType=view&xh=" + xh+"&id="+id);
}
function showDetail_11527(xh,id,zc) {
	showDialog("特殊学生详情", 700, 310,
			"xlzx_tsxs.do?method=tsxsDetail&doType=view&xh=" + xh+"&id="+id+"&zc="+zc);
}

function addTsxs() {
	showDialog("新增特殊学生", 700, 350, "xlzx_tsxs.do?method=tsxsDetail&doType=add");

}

function updateTsxs() {
	var xh = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length != 1) {
		showAlert("请选择一条您要修改的记录！");
		return false;
	} else {
		xh = rowsValue[0]["xh"];
		
	}
	 if("11527" == jQuery("#xxdm").val()){
		 zc = rowsValue[0]["zc"];
		 showDialog("修改特殊学生详情", 700, 340,
					"xlzx_tsxs.do?method=tsxsDetail&doType=update&xh=" + xh+"&zc="+zc);
	 }else{
		 showDialog("修改特殊学生详情", 700, 340,
					"xlzx_tsxs.do?method=tsxsDetail&doType=update&xh=" + xh); 
	 }
	
}

function deleteTsxs() {
	var dealTsxs = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlert("请选择您要删除的记录！");
	} else {
		// 验证是否有谈话记录
		if (delValidate(rowsValue) == "true") {
			showAlert("您选择的学生中存在谈话记录，不能删除！");
			return false;
		}
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				dealTsxs += rowsValue[i]["id"];
			} else {
				dealTsxs += rowsValue[i]["id"] + ",";
			}
		}
		showConfirm("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.ajaxSetup( {
					async : false
				});
				jQuery.post("xlzx_tsxs.do?method=deleteTsxsInfo", {
					dealTsxs : dealTsxs
				}, function(data) {
					showAlert(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				jQuery.ajaxSetup( {
					async : true
				});
			}
		});
	}
}

function delValidate(rowsValue) {
	var flag = false;
	for ( var i = 0; i < rowsValue.length; i++) {
		jQuery.ajaxSetup( {
			async : false
		});
		jQuery.post("xlzx_thjl.do?method=haveThjlFlagByXh", {
			xh : rowsValue[i]["xh"]
		}, function(data) {
			flag = data;
		}, '');
		jQuery.ajaxSetup( {
			async : true
		});
		if (flag == "true") {
			break;
		}
	}
	return flag;
}

function setTsxsGzzt() {
	var dealTsxs = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlert("请选择您要设置的记录！");
	} else {
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				dealTsxs += rowsValue[i]["id"];
			} else {
				dealTsxs += rowsValue[i]["id"] + ",";
			}
		}
		showDialog("特殊学生状态设置", 380, 200,
				"xlzx_tsxs.do?method=setTsxsGzzt&dealTsxs=" + dealTsxs);
	}
}

function exportTsxsList() {
	customExport("xlzx_tsxs_tsxs.do", exportTsxsData, 700, 500);
}

// 导出方法
function exportTsxsData() {
	setSearchTj();// 设置高级查询条件
	var url = "xlzx_tsxs.do?method=exportTsxsData&dcclbh="
			+ "xlzx_tsxs_tsxs.do";// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//导入
function drxx(){
	toImportData("IMPORT_N10220");
	return false;
}