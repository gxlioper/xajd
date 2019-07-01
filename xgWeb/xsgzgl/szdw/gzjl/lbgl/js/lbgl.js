var gridSetting = {
	caption : "������¼����б�",
	pager : "pager",
	url : "gzjllb.do?method=gzjllbList&type=query",
	colList : [ {
		label : '����������',
		name : 'lbdm',
		index : 'lbdm',
		key : true,
		width : '25%'
	}, {
		label : '�����������',
		name : 'lbmc',
		index : 'lbmc',
		width : '25%'
	}, {
		label : '�������˵��',
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
	var title = "���ӹ�����¼���";
	showDialog(title, 470, 250, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gzjllb.do?method=updateGzlb&lbdm=' + rows[0]["lbdm"];
		var title = "�޸Ĺ�����¼���";
		showDialog(title, 470, 250, url);
	}
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
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