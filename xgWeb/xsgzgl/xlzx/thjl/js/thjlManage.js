
jQuery(function() {
	var gridSetting = {
			caption:"谈话记录列表",
			pager:"pager",
			url:"xlzx_thjl.do?method=thjlManage&doType=query",
			colList:[
			   {label:'id',name:'id', index: 'id',hidden:true,key:true},
			   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
			   {label:'姓名',name:'xsxm', index: 'xsxm'},
			   {label:'性别',name:'xsxb', index: 'xsxb'},
			   {label:'年级',name:'nj', index: 'nj'},
			   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc'},
			   {label:'专业',name:'zymc', index: 'zymc'},
			   {label:'班级',name:'bjmc', index: 'bjmc'},
			   {label:'谈话时间',name:'thsj', index: 'thsj',formatter:getNewDate},
			   {label:'谈话教师',name:'jsxm', index: 'jsxm'}
			],
			sortname: "",
		 	sortorder: ""
		};
	jQuery("#dataTable").initGrid(gridSetting);
});

function searchRs() {
	var map = getSuperSearch();

	jQuery("#dataTable").reloadGrid(map);
}

function getNewDate(cellValue) {
	var newDate = cellValue.substring(0, 10);
	return newDate;
}

function xhLink(cellValue, rowObject) {
	return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"showDetail('"
			+ rowObject["xh"] + "');\">" + cellValue + "</a>";
}

function showDetail(xh) {
	showDialog("谈话记录详情", 700, 500, "xlzx_thjl.do?method=thjlDetailByXh&xh="
			+ xh);
}

function viewThjl() {
	var id = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length != 1) {
		showAlertDivLayer("请选择一条您要查看的记录！");
		return false;
	} else {
		id = rowsValue[0]["id"];
	}
	showDialog("查看谈话记录", 700, 500,
			"xlzx_thjl.do?method=thjlDetail&doType=view&id=" + id);
}

function addThjl() {
	showDialog("新增谈话记录", 700, 510, "xlzx_thjl.do?method=thjlDetail&doType=add");
}

function updateThjl() {
	var id = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
		return false;
	} else {
		id = rowsValue[0]["id"];
	}
	showDialog("修改谈话记录", 700, 510,
			"xlzx_thjl.do?method=thjlDetail&doType=update&id=" + id);
}

function deleteThjl() {
	var dealThjl = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		for ( var i = 0; i < rowsValue.length; i++) {
			if (i == (rowsValue.length - 1)) {
				dealThjl += rowsValue[i]["id"];
			} else {
				dealThjl += rowsValue[i]["id"] + ",";
			}
		}
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.ajaxSetup( {
					async : false
				});
				jQuery.post("xlzx_thjl.do?method=deleteThjlInfo", {
					dealThjl : dealThjl
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				jQuery.ajaxSetup( {
					async : true
				});
			}
		});
	}
}

function exportThjl() {
	customExport("xlzx_thjl_thjl.do", exportThjlData, 700, 500);
}

// 导出方法
function exportThjlData() {
	setSearchTj();// 设置高级查询条件
	var url = "xlzx_thjl.do?method=exportThjlData&dcclbh="
			+ "xlzx_thjl_thjl.do";// dcclbh,导出功能编号
	url = addSuperSearchParams(url);// 设置高级查询参数
	jQuery("#form").eq(0).attr("action", url);
	jQuery("#form").eq(0).submit();
}

//新版导入
function dr() {
	toImportDataNew("IMPORT_XLZX_THJL");
	return false;
}