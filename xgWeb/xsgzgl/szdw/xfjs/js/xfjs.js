/* ���� */
function importXf(){
	toImportDataNew("IMPORT_N471301_XFJS");
	return false;
}
var DCCLBH = "szdw_xfjswh.do";// dcclbh,�������ܱ��
function exportConfig() {
	customExport(DCCLBH, xfExportData);
}
//��������
function xfExportData() {
	setSearchTj();
	var url = "szdw_xfjsgl.do?method=exportData&dcclbh=" + DCCLBH;
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//����
function add() {
	var url = "szdw_xfjsgl.do?method=addXfjs";
	var title = "ѧ�翼������";
	showDialog(title, 700, 430, url);
}
function checkNumBer(obj){
	obj.value=obj.value.replace(/[^\d]/g,'');
}


function showBj(){
		showDialog('��ѡ��һ���༶',800,500,'szdw_xfjsgl.do?method=bjManage');
}

//���ӱ���
function addSave() {
	var xn = jQuery("#xn").val();
	if(xn == null || xn == ''){
		showAlert("��ѡ��ѧ��");
		return false;
	}
	var bj = jQuery("#bjdm").val();
	if(bj == null || bj == ''){
		showAlert("��ѡ��༶");
		return false;
	}
	var ydxsrs = jQuery("#ydxsrs").val();
	if(ydxsrs == null || ydxsrs == ''){
		showAlert("����дӦ��ѧ������");
		return false;
	}
	var sjcqrs = jQuery("#sjcqrs").val();
	if(sjcqrs == null || sjcqrs == ''){
		showAlert("����дʵ�ʳ�������");
		return false;
	}
	var jcsj = jQuery("#jcsj").val();
	if(jcsj == null || jcsj == ''){
		showAlert("��ѡ����ʱ��");
		return false;
	}
	var jcjc = jQuery("#jcjc").val();
	if(jcjc == null || jcjc == ''){
		showAlert("����д���ڴ�");
		return false;
	}
	
	var url = "szdw_xfjsgl.do?method=addXfjs&type=save";
	ajaxSubFormWithFun("xfjsForm", url, function(data) {
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


//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else {
		var url = 'szdw_xfjsgl.do?method=updateXf&id=' + rows[0]["id"];
		showDialog( "ѧ�翼���޸�", 700, 430, url);
	}

}

//�޸ı���
function updateSave() {
	var xn = jQuery("#xn").val();
	if(xn == null || xn == ''){
		showAlert("��ѡ��ѧ��");
		return false;
	}
	var bj = jQuery("#bjdm").val();
	if(bj == null || bj == ''){
		showAlert("��ѡ��༶");
		return false;
	}
	var ydxsrs = jQuery("#ydxsrs").val();
	if(ydxsrs == null || ydxsrs == ''){
		showAlert("����дӦ��ѧ������");
		return false;
	}
	var sjcqrs = jQuery("#sjcqrs").val();
	if(sjcqrs == null || sjcqrs == ''){
		showAlert("����дʵ�ʳ�������");
		return false;
	}
	var jcsj = jQuery("#jcsj").val();
	if(jcsj == null || jcsj == ''){
		showAlert("��ѡ����ʱ��");
		return false;
	}
	var jcjc = jQuery("#jcjc").val();
	if(jcjc == null || jcjc == ''){
		showAlert("����д���ڴ�");
		return false;
	}
	
	var url = "szdw_xfjsgl.do?method=updateXf&type=save";
	ajaxSubFormWithFun("xfjsForm", url, function(data) {
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

//�����鿴
function bjLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewXf(\""+rowObject["id"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function viewXf(id,cellValue){
	showDialog('ѧ�翼�ڲ鿴',700,430,'szdw_xfjsgl.do?method=viewXf&id='+id,null);
}


// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("szdw_xfjsgl.do?method=delXf", {
					values : ids.toString()
				},
						function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
			}
		});
	}
}
