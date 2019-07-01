var gridSetting = {
	caption : "工作记录类别列表",
	pager : "pager",
	url : "gzjllb.do?method=gzjllbList&type=query",
	colList : [ {
		label : '工作类别代码',
		name : 'lbdm',
		index : 'lbdm',
		key : true,
		width : '25%'
	}, {
		label : '工作类别名称',
		name : 'lbmc',
		index : 'lbmc',
		width : '25%'
	}, {
		label : '工作类别说明',
		name : 'lbsm',
		index : 'lbsm',
		width : '50%'
	} ],
	sortname : "lbdm",
	sortorder : "asc"
}

function dcmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name'>" + cellValue + "</a>";
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

function query() {
	var map = {};
	map["lbmc"] = jQuery("#lbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "gzjllb.do?method=addGzlb";
	var title = "增加工作记录类别";
	showDialog(title, 470, 250, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'gzjllb.do?method=updateGzlb&lbdm=' + rows[0]["lbdm"];
		var title = "修改工作记录类别";
		showDialog(title, 470, 250, url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("gzjllb.do?method=delGzlb", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

function newChgCode(obj) {
	allNotEmpThenGo(obj.id);
}