
//����
function add() {
	showDialog('��д������', 800, 450, 'xsxwkhJbfgl.do?method=jbfAdd');
}
//����ѧ����Ϣ�鿴
function viewJbfgl(jbfid,cellValue){
	showDialog('ѧ����������Ϣ',800,420,'xsxwkhJbfgl.do?method=jbfView&jbfid='+jbfid+"&xh="+cellValue,null);
}
//����
function change1() {
	var i = jQuery("#bzrcpdj").val();
	if (i == "����") {
		jQuery("#bzrcpfz").val("45");
	} else if (i == "����") {
		jQuery("#bzrcpfz").val("43");
	} else if (i == "�ϸ�") {
		jQuery("#bzrcpfz").val("40");
	}
}
function change2() {
	var i = jQuery("#xscpdj").val();
	if (i == "����") {
		jQuery("#xscpfz").val("45");
	} else if (i == "����") {
		jQuery("#xscpfz").val("43");
	} else if (i == "�ϸ�") {
		jQuery("#xscpfz").val("40");
	}
}
function change3() {
	var i = jQuery("#bzrcpdj").val();
	if (i == "����") {
		jQuery("#bzrcpfz1").val("45");
	} else if (i == "����") {
		jQuery("#bzrcpfz1").val("43");
	} else if (i == "�ϸ�") {
		jQuery("#bzrcpfz1").val("40");
	}
}
function change4() {
	var i = jQuery("#xscpdj").val();
	if (i == "����") {
		jQuery("#xscpfz1").val("45");
	} else if (i == "����") {
		jQuery("#xscpfz1").val("43");
	} else if (i == "�ϸ�") {
		jQuery("#xscpfz1").val("40");
	}
}

//����--������
function save() {
	var xh = jQuery("#xh").val();
	var bzrcpdj = jQuery("#bzrcpdj").val();
	var xscpdj = jQuery("#xscpdj").val();

	if (xh == null || xh == "" || bzrcpdj == null || bzrcpdj == ""
			|| xscpdj == null || xscpdj == "") {
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "xsxwkhJbfgl.do?method=jbfAdd&type=save";
	ajaxSubFormWithFun("jbfglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}

	});
}


/*�h��*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xsxwkhJbfgl.do?method=JbfDelete", {
					values : ids.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
/*�޸Ĵ���*/
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		alertInfo("����ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸Ļ�������Ϣ', 800, 420,
				'xsxwkhJbfgl.do?method=jbfUpdate&jbfid=' + rows[0]["jbfid"]);
	}
}
/*�޸ı������*/
function saveUpdate() {
	var bzrcpdj = jQuery("#bzrcpdj").val();
	var xscpdj = jQuery("#xscpdj").val();
	if (bzrcpdj == null || bzrcpdj == "" || xscpdj == null || xscpdj == "") {
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "xsxwkhJbfgl.do?method=jbfUpdate&type=save";
	ajaxSubFormWithFun("jbfglForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);

		}
	});
}

/* ���� */
function importXX(){
	toImportDataNew("IMPORT_N158703_JBFGL");
	return false;
}

/* ������� */
function exportXX() {
	customExport('xsxwkh_jbfgl.do', exportData);
}
function exportData(){
	setSearchTj();// ���ø߼���ѯ����
	var url = "xsxwkhJbfgl.do?method=exportData&dcclbh=" + 'xsxwkh_jbfgl.do';// dcclbh,�������ܱ��,���ݱ��ֶ�
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
