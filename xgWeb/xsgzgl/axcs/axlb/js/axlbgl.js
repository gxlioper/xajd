var gridSetting = {
	caption : "��������б�",
	pager : "pager",
	url : "axcsAxlbgl.do?method=axlbglList&type=query",
	colList : [ {
		label : '����',
		name : 'dm',
		index : 'dm',
		key : true,
		width : '25%'
	}, {
		label : '����',
		name : 'mc',
		index : 'mc',
		width : '25%'
	}, {
		label : '˵��',
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
	var title = "���Ӱ������";
	showDialog(title, 470, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'axcsAxlbgl.do?method=updateAxlb&dm=' + rows[0]["dm"];
		var title = "�޸İ������";
		showDialog(title, 470, 180, url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
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