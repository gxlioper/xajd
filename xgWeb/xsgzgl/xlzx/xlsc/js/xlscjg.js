//�߼���ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//���ӷ���
function addXlscjg(){
	var url = "xlzx_xlscjg.do?method=xlscjgAdd";
	var title = "��������ɸ����";
	showDialog(title,700,385,url);
}

//���ӱ��淽��
function addSave(){
	var xh = jQuery("#xh").val();
	var scrq = jQuery("#scrq").val();
	var scl = jQuery("#scl").val();
	var sds = jQuery("#sds").val();
	var sas = jQuery("#sas").val();
	var bkyy = jQuery("#bkyy").val();
	var bkjl = jQuery("#bkjl").val();
	var sfxyyt = jQuery("#sfxyyt").val();
	var sfyyt = jQuery("#sfyyt").val();
	if(xh==null||xh==""){
		showAlert("��ѡ��һ��ѧ����");
		return false;
	}
	if(scrq==null||scrq==""){
		showAlert("����ɸ�����ڣ�");
		return false;
	}
	if(scl==null||scl==""){
		showAlert("����дSCL90�����");
		return false;
	}
	if(sds==null||sds==""){
		showAlert("����дSDS�����");
		return false;
	}
	if(sas==null||sas==""){
		showAlert("����дSAS�����");
		return false;
	}
	if(bkyy==null||bkyy==""){
		showAlert("����д�������������");
		return false;
	}
	if(bkjl==null||bkjl==""){
		showAlert("����д���˽��ǽ����");
		return false;
	}
	if(sfxyyt==null||sfxyyt==""){
		showAlert("��ѡ��ѧ���Ƿ���Ҫ�μ�Լ̸��");
		return false;
	}
	if(sfyyt==null||sfyyt==""){
		showAlert("��ѡ��ѧ���Ƿ��Ѳμ�Լ̸��");
		return false;
	}
	var url="xlzx_xlscjg.do?method=xlscjgAdd&type=save";
	ajaxSubFormWithFun("xlscjgForm", url, function(data){
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

//�޸ķ���
function updateXlscjg(){
	var rows = jQuery("#dataTable").getSeletRow();
	var title = "�޸�����ɸ����";
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog(title,700,385,'xlzx_xlscjg.do?method=xlscjgUpdate&id='+rows[0]["id"]);
	}
}

//�޸ı���
function updateSave(){
	var xh = jQuery("#xh").val();
	var scrq = jQuery("#scrq").val();
	var scl = jQuery("#scl").val();
	var sds = jQuery("#sds").val();
	var sas = jQuery("#sas").val();
	var bkyy = jQuery("#bkyy").val();
	var bkjl = jQuery("#bkjl").val();
	var sfxyyt = jQuery("#sfxyyt").val();
	var sfyyt = jQuery("#sfyyt").val();
	if(xh==null||xh==""){
		showAlert("��ѡ��һ��ѧ����");
		return false;
	}
	if(scrq==null||scrq==""){
		showAlert("����ɸ�����ڣ�");
		return false;
	}
	if(scl==null||scl==""){
		showAlert("����дSCL90�����");
		return false;
	}
	if(sds==null||sds==""){
		showAlert("����дSDS�����");
		return false;
	}
	if(sas==null||sas==""){
		showAlert("����дSAS�����");
		return false;
	}
	if(bkyy==null||bkyy==""){
		showAlert("����д�������������");
		return false;
	}
	if(bkjl==null||bkjl==""){
		showAlert("����д���˽��ǽ����");
		return false;
	}
	if(sfxyyt==null||sfxyyt==""){
		showAlert("��ѡ��ѧ���Ƿ���Ҫ�μ�Լ̸��");
		return false;
	}
	if(sfyyt==null||sfyyt==""){
		showAlert("��ѡ��ѧ���Ƿ��Ѳμ�Լ̸��");
		return false;
	}
	var url="xlzx_xlscjg.do?method=xlscjgUpdate&type=update";
	ajaxSubFormWithFun("xlscjgForm", url, function(data){
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

//ɾ������
function deleteXlscjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xlzx_xlscjg.do?method=xlscjgDelete",{values:ids.toString()},function(data){
			alertInfo(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	}
}

//���ѧ������
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewXlscjg(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}

//�鿴����
function viewXlscjg(id,cellValue){
	var title = "�鿴����ɸ����";
	showDialog(title,700,365,'xlzx_xlscjg.do?method=xlscjgView&id='+id+"&xh="+cellValue,null);
}

//dcclbh,�������ܱ��
var DCCLBH = "xg_xlzx_xlscjg.do";

//�Զ��嵼��
function exportConfig(){
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

// ��������
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xlzx_xlscjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_XLZX_XLSCJG");
	return false;
}