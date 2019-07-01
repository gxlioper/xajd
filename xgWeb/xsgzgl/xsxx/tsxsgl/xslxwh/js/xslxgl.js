var gridSetting = {
	caption : "ѧ�������б�",
	pager : "pager",
	url : "tsxs_xslxwh.do?method=xslxwhManage&type=query",
	colList : [ {
		label : 'ѧ�����ʹ���',
		name : 'xslxdm',
		index : 'xslxdm',
		key : true,
		width : '25%'
	}, {
		label : 'ѧ����������',
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
	var title = "����ѧ������";
	showDialog(title, 470, 200, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'tsxs_xslxwh.do?method=updateXslx&xslxdm=' + rows[0]["xslxdm"];
		var title = "�޸�ѧ������";
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