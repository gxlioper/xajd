var gridSetting = {
	caption : "学生类型列表",
	pager : "pager",
	url : "tsxs_xslxwh.do?method=xslxwhManage&type=query",
	colList : [ {
		label : '学生类型代码',
		name : 'xslxdm',
		index : 'xslxdm',
		key : true,
		width : '25%'
	}, {
		label : '学生类型名称',
		name : 'xslxmc',
		index : 'xslxmc',
		width : '25%'
	}],
	sortname : "xslxdm",
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
	map["xslxmc"] = jQuery("#xslxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "tsxs_xslxwh.do?method=addXslx";
	var title = "增加学生类型";
	showDialog(title, 470, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'tsxs_xslxwh.do?method=updateXslx&xslxdm=' + rows[0]["xslxdm"];
		var title = "修改学生类型";
		showDialog(title, 470, 180, url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("tsxs_xslxwh.do?method=delXslx", {
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