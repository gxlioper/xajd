function checkNumBer(obj){
		obj.value=obj.value.replace(/[^\d]/g,'');
	}
//����
function add() {
	showDialog('����', 700, 450, 'ydXxgl.do?method=ydxxAdd');
}
//����������Ϣ�鿴
function qsLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewYdxx(\""+rowObject["ydxxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function viewYdxx(ydxxid,cellValue){
	showDialog('�����õ���Ϣ',700,450,'ydXxgl.do?method=ydxxView&ydxxid='+ydxxid+"&xh="+cellValue,null);
}

//����--������
function save() {
	var ydyf = jQuery("#ydyf").val();
	var lddm = jQuery("#lddm").val();
	var qsh = jQuery("#qsh").val();
	var ds = jQuery("#ds").val();
	var df = jQuery("#df").val();
	var dfye = jQuery("#dfye").val();
	var bz = jQuery("#bz").val();
	if (ydyf == "" || lddm == "" || qsh == ""
			|| ds == "" || df == "" || dfye == "") {
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	jQuery.post("ydXxgl.do?method=ydxxAdd&type=save", {
		ydyf : ydyf,
		lddm : lddm,
		qsh:qsh,
		ds:ds,
		df:df,
		dfye:dfye,
		bz:encodeURI(encodeURI(bz))
	}, function(data) {
		if(data['message'] != ''){
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
		}
	}, 'json');
}


/*�h��*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("ydXxgl.do?method=ydxxDelete", {
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
	if (rows.length != 1&&rows.length==0) {
		alertInfo("����ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else if(rows.length > 1){
		alertInfo("ÿ��ֻ���޸�һ����¼��");
	} else {
		showDialog('�޸�', 700, 450,
				'ydXxgl.do?method=ydxxUpdate&ydxxid=' + rows[0]["ydxxid"]);
	}
}
/*�޸ı������*/
function saveUpdate() {
	var ydxxid = jQuery("#ydxxid").val();
	var ydyf = jQuery("#ydyf").val();
	var lddm = jQuery("#lddm").val();
	var qsh = jQuery("#qsh").val();
	var ds = jQuery("#ds").val();
	var df = jQuery("#df").val();
	var dfye = jQuery("#dfye").val();
	var bz = jQuery("#bz").val();
	if (ydyf == "" || lddm == "" || qsh == ""
		|| ds == "" || df == "" || dfye == "") {
	showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	return false;
}
	var url = "ydXxgl.do?method=ydxxUpdatesv&type=save";
	jQuery.post(url, {
		ydxxid:ydxxid,
		ydyf : ydyf,
		lddm : lddm,
		qsh:qsh,
		ds:ds,
		df:df,
		dfye:dfye,
		bz:encodeURI(encodeURI(bz))
	}, function(data) {
		if(data['message'] == '����ɹ���'){
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
		}
	}, 'json');
}

/* ���� */
function importXX(){
	toImportDataNew("IMPORT_N382501_YDXX");
	return false;
}

/* ������� */
function exportXX() {
	customExport('ydxxgl.do', exportData);
}
function exportData(){
	setSearchTj();// ���ø߼���ѯ����
	var url = "ydXxgl.do?method=exportData&dcclbh=" + 'ydxxgl.do';// dcclbh,�������ܱ��,���ݱ��ֶ�
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
