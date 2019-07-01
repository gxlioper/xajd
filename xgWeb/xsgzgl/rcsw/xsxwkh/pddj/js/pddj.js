
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='pddjView(\""
			+ rowObject["jbfid"]+"\",\"" + rowObject["xn"]+"\");'>" + cellValue
			+ "</a>";
}
function pddjView(jbfid,xn) {
	showDialog("�����ȼ��鿴", 650, 450, "xsxwkhDjpd.do?method=pddjView&jbfid="+jbfid+"&xn="+xn);
}
function getZpdj() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼");
		return false;
	}
	 if (rows.length == 1) {
		var url = "xsxwkhDjpd.do?method=pddjSingle&jbfid=" + rows[0]["jbfid"] + '&xn=' + rows[0]["xn"];
		showDialog("�����ȼ�", 700, 480, url);
	} else {
		showDialog("���������ȼ�", 500, 250, "xsxwkhDjpd.do?method=pddjPl&num="+rows.length);
	}
}


//�������
function savePddjPl(pddj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var xh = new Array();
	var xn = new Array();
	jQuery.each(rows, function(i, row) {
		xh.push(row["xh"]);
		xn.push(row["xn"]);
	});
	jQuery.post("xsxwkhDjpd.do?method=pddjPl&type=save", {
		pddj : pddj,
		xhs : xh,
		xns : xn
	}, function(data) {
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}
var DCCLBH="xsxwkh_djpd.do";
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, pddjExportData);
}

//��������
function pddjExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxwkhDjpd.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
