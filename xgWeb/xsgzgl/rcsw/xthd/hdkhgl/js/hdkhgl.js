//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


//����
function importConfig(){
	toImportDataNew("IMPORT_RCSW_TXHD_HDKHGL");
	return false;
}

//�ɼ�����
function cjpd(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
		return false;
	}
	var xmdm = rows[0]["xmdm"];
	showDialog("��ɼ�����", 750, 500, "txhd_hdkhgl.do?method=khcjPd&hdxmbh="
			+ xmdm);
}

//���濼�˳ɼ�
function saveKhcj(){
	var len = jQuery("#tbody_cjpd tr").length;
	if(len == 1){
		showAlert("�û��ѧ���μӣ�������ִ�б��������");
		return false;
	}
	var flag = true;
	jQuery("#tbody_cjpd tr").each(function(b){
		if(b != 0){
			var tempcjpd=jQuery(this).find("select[name='sfhdxf']").val();
			if(tempcjpd == ''){
				flag = false;
				showAlert("ѧ�ֲ���Ϊ�գ�");
				return false;
			}
		}
	});
	if(flag == false){
		return false;
	}
	var url = "txhd_hdkhgl.do?method=savePfwh"
		ajaxSubFormWithFun("hdkhForm", url, function(data) {
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

function show(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='showView(\""
			+ rowObject["xmdm"] + "\");'>" + cellValue
			+ "</a>";
}


//�鿴ѧ��ajaxurl��ת
function showView(xmdm) {
	showDialog("���Ϣ�鿴", 770, 450, "txhd_hdkhgl.do?method=view&hdxmbh="
			+ xmdm );
}

var DCCLBH = "rcsw_txhd_hdkhgl.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, khjgExportData);
}

//��������
function khjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "txhd_hdkhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}