var action = "rwdjsq.do";
/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//��������ת
function dcmcLink(cellValue, rowObject) {
	var rwdjid = rowObject["rwdjid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rwdjid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(rwdjid) {
	var url = action + "?method=showView&rwdjid=" + rwdjid;
	showDialog("����Ǽ���Ϣ", 800, 500, url);
}
// ����
function add() {
	if(jQuery("#notcfbz").val() == "false"){
		showAlertDivLayer("����������Ǽ����룬��ȷ�ϣ�");
		return false;
	}
	var url = action + "?method=add";
	showDialog("����Ǽ�����", 800, 500, url);
}
/**
 * �޸�
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if ("0" != rows[0]['shzt']&&"3" !=rows[0]['shzt']) {
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var url = action + '?method=update&rwdjid=' + rows[0]["rwdjid"]+"&xh="+ rows[0]["xh"];
		var title = "����Ǽ��޸�";
		showDialog(title, 800, 500, url);
	}
}
/**
 * ����
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url,checkId) {
	if (!checkNotNull(checkId)) {
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������")
		return false;
	}
	var ywqrwxy = jQuery("[name='ywqrwxy']:checked").val();
	if(ywqrwxy == "" ||  ywqrwxy == null){
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������")
		return false;
	}
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		if(data["message"] == "����ɹ���" || data["message"] == "�ύ�ɹ���"){
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}else{
			showAlert(data["message"]);
			return false;
		}
			
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["shzt"] != "0" && row["shzt"] != "3"){
				flag = false;
				return false;
			}
		});
		if(!flag){
			showAlertDivLayer(jQuery("#lable_wjt_sc").val());
			return false;
		}
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("rwdjsq.do?method=delSqjl",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport('rwdjsq_export.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();// ���ø߼���ѯ����
	var url = "rwdjsq.do?method=exportData&dcclbh=rwdjsq_export.do";// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�ύ
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
		return false;
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
			showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=submitBusi", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//����
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
				jQuery.post(action+"?method=cancelSq", {
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
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['rwdjid'] + "&splc=" + rows[0]['splc']);
	}
}