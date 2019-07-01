var gridSetting = {
	caption : "爱心类别列表",
	pager : "pager",
	url : "axcsAxlbgl.do?method=axlbglList&type=query",
	colList : [ {
		label : '代码',
		name : 'dm',
		index : 'dm',
		key : true,
		width : '25%'
	}, {
		label : '名称',
		name : 'mc',
		index : 'mc',
		width : '25%'
	}, {
		label : '说明',
		name : 'sm',
		index : 'sm',
		width : '50%'
	} ],
	sortname : "dm",
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
	map["mc"] = jQuery("#mc").val();
	jQuery("#dataTable").reloadGrid(map);
}

function add() {
	var url = "axcsAxlbgl.do?method=addAxlb";
	var title = "增加爱心类别";
	showDialog(title, 470, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var url = 'axcsAxlbgl.do?method=updateAxlb&dm=' + rows[0]["dm"];
		var title = "修改爱心类别";
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
				jQuery.post("axcsAxlbgl.do?method=delAxlb", {
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