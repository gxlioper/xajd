var DCCLBH = "xsxx_xygl.do";// dcclbh,�������ܱ��

// ��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

// ����
function showXyglAdd() {
	var url = "xsxx_xyglxx.do?method=xyglZj";
	showDialog("����У�ѹ�����Ϣ", 900, 500, url);
}

//ѧ�����Ӳ鿴��Ϣ
function xhLink(cellValue, rowObject) {
	var xh = rowObject.xh;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'   onclick='xyglView(\"" + xh
			+ "\");return false;' >" + xh + "</a>";
	return cellValue;
}

//�°�鿴������
function xyglView(xh) {
	showDialog("У�ѹ�����Ϣ��ѯ", 850, 500, "xsxx_xyglxx.do?method=xyglCk&xh=" + xh);
}

// �޸�
function showXyglEdit() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "xsxx_xyglxx.do?method=xyglXg";
		url += "&xh=" + ids;
		showDialog("�޸�У����Ϣ", 900, 550, url);
	} else {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}
}

// ɾ��
function deleteXygl() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len != 0) {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				var url = "xsxx_xyglxx.do?method=xyglSc";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"], {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});
	} else {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
}


// �Զ��嵼�� ����
function xyglExportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport(DCCLBH, xyglExportData, 1000, 500);
}

// ��������
function xyglExportData() {
	setSearchTj();// ���ø߼���ѯ����
	var url = "xsxx_xyglxx.do?dcclbh=" + DCCLBH + "&method=";
	
	url += "xyglExportData";
	
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
