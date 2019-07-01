
jQuery(function(){
	var gridSetting = {
		caption : "��Ʒ�����б�",
		pager : "pager",
		url : "axcswpsqjs.do?method=wpsqJsList&type=query",
		colList : [ {
			label : 'key',
			name : 'sqid',
			index : 'sqid',
			key : true,
			hidden : true
		},{
			label : 'xmdm',
			name : 'xmdm',
			index : 'xmdm',
			hidden : true
		},{
			label : '��������',
			name : 'splc',
			index : 'splc',
			hidden : true
		}, {
			label : 'ѧ��',
			name : 'xh',
			index : 'xh',
			width : '12%',
			formatter : xhLink
		}, {
			label : 'ѧ��',
			name : 'xn',
			index : 'xn',
			width : '12%'
		}, {
			label : '����',
			name : 'xm',
			index : 'xm',
			width : '8%'
		}, {
			label : jQuery("#xbmc").val(),
			name : 'xymc',
			index : 'xymc',
			width : '20%'
		}, {
			label : '�༶',
			name : 'bjmc',
			index : 'bjdm',
			width : '20%'
		}, {
			label : '����ʱ��',
			name : 'sqsj',
			index : 'sqsj',
			width : '18%'
		}, {
			label : '��Ʒ����',
			name : 'xmmc',
			index : 'xmmc',
			width : '10%'
		}, {
			label : '��Ʒ���',
			name : 'xmlbmc',
			index : 'xmlbmc',
			width : '8%'
		}, {
			label : '���״̬',
			name : 'shztmc',
			index : 'shztmc',
			width : '6%'
		}, {
			label : '���״̬',
			name : 'shzt',
			index : 'shzt',
			hidden : true
		}, {
			label : '�������ɴ���',
			name : 'lxdm',
			index : 'lxdm',
			hidden : true
		}, {
			label : '���뿪��',
			name : 'isopen',
			index : 'isopen',
			hidden : true
		} ],
		sortname : "sqsj",
		sortorder : "desc"
	}
	var map = getSuperSearch();
	gridSetting["params"] = map;
	jQuery("#dataTable").initGrid(gridSetting);
})

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function selectXm() {
	var wpxm = jQuery("input[name=xmdm]:checked");
	var gotoPath = jQuery("#gotoPath").val();
	if (wpxm.size() != 1) {
		showAlert("��ѡ��һ����Ҫ�������Ʒ��");
		return false;
	}
	var xmdm = jQuery("input[name=xmdm]:checked").val();
	gotoPath = gotoPath + "&xmdm=" + xmdm + "&xh=" + jQuery("#xh").val();
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			api.reload(api.get('parentDialog'), gotoPath);
		} else {
			var W = api.opener;
			W.location = gotoPath;
		}
	} else if (parent.window) {
		parent.window.document.location = gotoPath;
	}
	iFClose();
}

function saveWpsq(type) {
	var xh = jQuery("#xh").val();
	if (jQuery("#sqly").val() == "" || xh == "") {
		showAlert("�뽫��������д������");
		return false;
	}
	if (jQuery("#xmdm").val() == "" || null == jQuery("#xmdm").val()) {
		showAlert("��ѡ��������Ʒ��");
		return false;
	}
	if (jQuery("#sqly").val().length>500) {
		showAlert("���������������500�֣�");
		return false;
	}
	var url = "axcswpsqjs.do?method=saveWpsq&type=" + type;
	ajaxSubFormWithFun("WpsqJsForm", url, function(data) {
		showAlert(data["message"], {}, {
			"clkFun" : function() {
				if (parent.window) {
					refershParent();
				}
			}
		});
	});
}
function add() {
	var url = "axcswpsqjs.do?method=wpsqZj";
	var title = "��Ʒ����";
	showDialog(title, 700, 480, url);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {

		var shzt = rows[0]["shzt"];

		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'axcswpsqjs.do?method=wpsqUpdate&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��Ʒ�����޸�";
		showDialog(title, 700, 480, url);
	}

}

// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=delWpsq", {
					values : ids.toString()
				},
						function(data) {
							var mes = "�ɹ�ɾ����<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>������";
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}

// �ύ
function submitBusi() {

	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		if ('3' != rows[0]['shzt'] && "false" == rows[0]['isopen']) {
			showAlertDivLayer("��ǰ��Ʒ�ѹر����룬�������Ա��ϵ��");
			return false;
		}

		var xmdm = rows[0]["xmdm"];
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=saveUpdateWpsq&type=tj", {
					values : ids.toString(),
					xmdm : xmdm
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
// ����
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=cancelWpsq", {
					values : ids.toString(),
					splcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {
		if ("0" == shzt) {
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("���ĳ����������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splc']);
	}
}

function wpsqView(sqid, xh) {
	showDialog("���ĳ�������鿴", 700, 580, "axcswpsqjs.do?method=wpsqView&sqid="
			+ sqid + "&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='wpsqView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}
var DCCLBH = "axcs_axcswpsq_tea.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, wpjgExportData);
}

//��������
function wpjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "axcswpsqjs.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}