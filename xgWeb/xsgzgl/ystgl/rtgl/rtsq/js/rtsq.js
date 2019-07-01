var ids = "ystxmmc"+"-"+"sqly"+"-"+"tc";
//��ѯ
function searchRs() {
	var map = getSuperSearch();
	var flag = jQuery("#flag").val();
	if (null!=flag&&flag != "") {
		map["flag"] = flag;
	}else{
		map["flag"] = "wsq";
	}
	jQuery("#dataTable").reloadGrid(map);
}

//��ǩҳѡ�
function selectTab(obj, flag) {
	jQuery("#flag").val(flag);
	if (flag == "wsq") {
		var map = getSuperSearch();
		map["flag"]="wsq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		var map = getSuperSearch();
		map["flag"]="ysq";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtsqView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}


//�鿴ѧ��ajaxurl��ת
function RtsqView(id, xh) {
	showDialog("���ų�Ա��ϸ��ѯ", 770, 450, "ystglRtsq.do?method=viewYstRtsq&rtid="
			+ id + "&xh=" + xh);
}

//ɾ��������Ϣ
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if(rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("ystglRtsq.do?method=delYstRtsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "ystgl_rtgl_rtsq.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, RtsqExportData);
}

//��������
function RtsqExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ystglRtsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_RTSQ");
	return false;
}


function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		if ('3' != rows[0]['shzt'] && "0" == rows[0]['sqkg']) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("ystglRtsq.do?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

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
				jQuery.post("ystglRtsq.do?method=cancelYstRtsq", {
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

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var sqkg = rows[0]['sqkg'];
		if ('3' != rows[0]['shzt'] && "0" == sqkg) {
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			return false;
		}
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}
		var url = 'ystglRtsq.do?method=editYstRtsq&rtid=' + rows[0]["rtid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "ѧ����������";
		showDialog(title, 770, 552, url);
	}
}

//����
function add() {
	var url = "ystglRtsq.do?method=add";
	var title = "ѧ����������";
    showDialog(title, 770, 552, url);
}

//��֤����
function checkzs(obj){
	if(jQuery(obj).val().length > 100){
		showAlert("�������Ϊ100�����Ѿ���������ȷ�ϣ�");
		return false;
	}
}


//���ӽ������
function saveYstRtsq(type){
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs(jQuery("#sqly")) == false || checkzs(jQuery("#tc")) == false ) {
		return false;
	}
	var url = "ystglRtsq.do?method=saveYstRtsq&type=" + type;
	ajaxSubFormWithFun("YstRtsqForm", url, function(data) {
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		});
}

/*
 * ���̸���
 */
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("ѧ�����������������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['rtid'] + "&splc=" + rows[0]['splc']);
	}
}

//toggle����չ��
function showYstmx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";
	jQuery(obj).attr("class",newClass);
	jQuery("#tbody_toggle").toggle();
}


