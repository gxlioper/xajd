var action = "rwdj.do";
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
		if(rows[0]["sjly"] == "1"){
			showAlertDivLayer("����������ݲ�����ɾ����");
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
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		if(data["message"] == "����ɹ���"){
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
	unlock();
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true;
		jQuery(rows).each(function(i,row){
			if(row["sjly"] == "1"){
				flag = false;
				return false;
			}
		});
		if(!flag){
			showAlertDivLayer("������������޷�ɾ����");
			return false;
		}

  var rows = jQuery("#dataTable").getSeletRow(); 
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action + "?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes = "�ɹ�ɾ����<font color='green'>&nbsp;"
							+ data["successDel"] + "&nbsp;</font>������";
					mes += "</br>";
					showAlertDivLayer(mes, {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});

	}
}
// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport('rwdj_export.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();// ���ø߼���ѯ����
	var url = "rwdj.do?method=exportData&dcclbh=rwdj_export.do";// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function drxx(){
	toImportDataNew("IMPORT_N151601_RWDJ");
	return false;
}