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

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='RtjgView(\""
			+ rowObject["rtid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}



//�鿴ѧ��ajaxurl��ת
function RtjgView(id, xh) {
	showDialog("���Ž���鿴", 770, 450, "ystglRtjg.do?method=viewYstRtjg&rtid="
			+ id + "&xh=" + xh);
}

//ɾ�������Ϣ
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("ystglRtjg.do?method=delYstRtjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "ystgl_rtgl_rtjg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, RtjgExportData);
}

//��������
function RtjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "ystglRtjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_YSTGL_RTJG");
	return false;
}



//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if ("1" == rows[0]['sjly']) {
			showAlertDivLayer("���������ݲ������޸ģ�");
			return false;
		}
		var url = 'ystglRtjg.do?method=editYstRtjg&rtid=' + rows[0]["rtid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "ѧ�����Ž��";
		showDialog(title, 770, 552, url);
	}
}

//����
function add() {
	var url = "ystglRtjg.do?method=add";
	var title = "ѧ�����Ž��";
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
function saveYstRtjg(type){
	
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs(jQuery("#sqly")) == false || checkzs(jQuery("#tc")) == false ) {
		return false;
	}
	var url = "ystglRtjg.do?method=saveYstRtjg&type=" + type;
	ajaxSubFormWithFun("YstRtjgForm", url, function(data) {
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





