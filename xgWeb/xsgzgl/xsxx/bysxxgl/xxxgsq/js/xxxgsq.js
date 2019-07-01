jQuery(function() {
	var gridSetting = {
		caption : "��ҵ����Ϣ�޸������б�",
		pager : "pager",
		url : "xsxx_bysxx_xxxgsq.do?method=XgSqCk&type=query",
		colList : [ {
			label : 'key',
			name : 'sqid',
			index : 'sqid',
			key : true,
			hidden : true
		}, {
			label : 'ѧ��',
			name : 'xh',
			index : 'xh',
			width : '12%',
			formatter : setXh
		}, {
			label : '����',
			name : 'xm',
			index : 'xm',
			width : '12%'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc',
			width : '18%'
		}, {
			label : 'רҵ',
			name : 'zymc',
			index : 'zymc',
			width : '18%'
		}, {
			label : '�༶',
			name : 'bjmc',
			index : 'bjmc',
			width : '15%'
		}, {
			label : '����ʱ��',
			name : 'xgsj',
			index : 'xgsj',
			width : '15%'
		}, {
			label : '���״̬',
			name : 'shjgmc',
			index : 'shjgmc',
			width : '10%'
		}, {
			label : '���״̬',
			name : 'shjg',
			index : 'shjg',
			hidden : true
		}, {
			label : '���id',
			name : 'splc',
			index : 'splc',
			hidden : true
		} ],
		params : {
		// shzt : "dsh"
		},// Ĭ�ϴ����
		sortname : "xgsj",
		sortorder : "desc"
	}
	jQuery("#dataTable").initGrid(gridSetting);
});

function xxxgsq() {
	var kfxg = jQuery("#kfxg").val();// �ɷ��޸�,y,n
	if ("y" != kfxg) {
		showAlertDivLayer("��ǰδ���ű�ҵ����Ϣ�޸����룬����ϵ����Ա��");
		return false;
	}
	var user = jQuery("#usertype").val();

	if ("stu" == user) {
		var xh = jQuery("#xh").val();

		var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSq";
		url += "&xh=" + xh;
		showDialog("��ҵ����Ϣ�޸�����", 850, 550, url);
	} else {
		showDialog('��ѡ��һ����ҵ��', 800, 500,
				'xsxx_bysxx_xxxgsq.do?method=showBysXx');
	}
}

function xxXgSq(type) {
	initParam();
	if (!zdybdCheck()) {
		return;
	}
	if (!getXgzdJson()) {
	}
	if (type == 'submit') {
		jQuery("#but_save").hide();
		var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSqTj";
		ajaxSubFormWithFun("demoform", url, function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	} else {
		var url = "xsxx_bysxx_xxxgsq.do?method=bysXxXgSqBc";
		ajaxSubFormWithFun("demoform", url, function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	}
}
// �����޸�
function xgsqXg() {
	var kfxg = jQuery("#kfxg").val();// �ɷ��޸�,y,n
	var rows = jQuery("#dataTable").getSeletRow();
	if ("y" != kfxg) {
		showAlertDivLayer("��ǰδ���ű�ҵ����Ϣ�޸����룬����ϵ����Ա��");
		return;
	}

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shjg = rows[0]["shjg"];
		if (shjg != '0' && shjg != '3') {
			showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
			return false;
		} else {

			var xh = rows[0]["xh"];
			var sqid = rows[0]["sqid"];
			var splc = rows[0]["splc"];
			var url = "xsxx_bysxx_xxxgsq.do?method=SqXg";
			url += "&xh=" + xh + "&sqid=" + sqid + "&splc=" + splc;
			showDialog("��ҵ����Ϣ�޸������޸�", 850, 550, url);
		}
	}
}
// ����ɾ��
function xgsqSc() {
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = [];
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i]['sqid'])
	}
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shjg"] != "0" && rows[0]["shjg"] != "3") {
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫȡ����������", {
			"okFun" : function() {
				jQuery.post("xsxx_bysxx_xxxgsq.do?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
// �����ύ
function xgsqTj() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shjg'] != '0' && rows[i]['shjg'] != '3') {
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xsxx_bysxx_xxxgsq.do?method=submitRecord", {
					sqid : rows[0]['sqid'],
					splc : rows[0]['splc'],
					xh : rows[0]['xh']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
// ����
function xgsqCx() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shjg'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {

				jQuery.post("xsxx_bysxx_xxxgsq.do?method=xgsqCx", {
					values : ids.toString(),
					lcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');

			}
		});
	}

}
// ���̸���
function shlcInfo() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if (rows[0]["shjg"] == "0") {
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("��ҵ����Ϣ�޸������������̸���", 530, 310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}

// ��������ת
function setXh(cellValue, rowObject) {
	var xh = rowObject.xh;
	var sqid = rowObject.sqid;
	if (xh == null) {
		xh = "";
	}
	cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='sqxxCk(\""
			+ xh + "\",\"" + sqid + "\" );return false;' >" + xh + "</a>";
	return cellValue;
}
// �鿴��Ϣ
function sqxxCk(xh, sqid) {
	var url = "xsxx_bysxx_xxxgsq.do?method=showXgXx&xh=" + xh + "&sqid=" + sqid;
	var title = "��ҵ����Ϣ�����޸Ľ���鿴";
	showDialog(title, 850, 550, url);
}

// ���صǼǱ�
function printByjdb(url) {
	var xh = "";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length < 1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else if (rows.length == 1) {

		url += "&xh=" + rows[0]["xh"];
		window.open(url);
	} else {
		for ( var i = 0; i < rows.length; i++) {
			if (i == rows.length - 1) {
				xh += rows[i]["xh"];
			} else {
				xh += rows[i]["xh"] + ",";
			}
		}
		var url = "xsxx_bysxx_xxgl.do?method=printByjdbZip" + "&xh=" + xh;
		window.open(url);
	}
}

/**
 * 
 * 
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
