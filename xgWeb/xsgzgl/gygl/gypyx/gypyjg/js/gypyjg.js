//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "gypynew_gypyjg.do?method=addJg";
	var title = "�����Ǽ�����";
	showDialog(title, 770, 550, url);
}

//�����޸Ľ������
function saveSq(){
	var ids = "lddm"+"-"+"qsh"+"-"+"sqxj"+"-"+"gxsj"+"-sqsj";
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "gypynew_gypyjg.do?method=saveJg";
	ajaxSubFormWithFun("GypyJgForm", url, function(data) {
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

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["sqid"] + "\");'>" + cellValue
			+ "</a>";
}

function View(sqid) {
	showDialog("�Ǽ����ҽ���鿴", 770, 480, "gypynew_gypyjg.do?method=viewJg&jgid="
			+ sqid);
}

//ɾ��ס�޽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}  else {
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gypynew_gypyjg.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if ("0" != rows[0]['sjly']) {
			showAlertDivLayer("����������ݲ��ܱ��޸ģ�");
			return false;
		}
		if("1" == rows[0]["cxzt"]){
			showAlertDivLayer("���ǵ����ݲ��ܱ��޸ģ�");
			return false;
		}
		var url = 'gypynew_gypyjg.do?method=editJg&jgid=' + rows[0]["jgid"];
		var title = "�Ǽ����ҽ���޸�";
		showDialog(title, 770, 550, url);
	}
}

var DCCLBH = "gygl_gypynew_gypyjg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jgExportData);
}

//��������
function jgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gypynew_gypyjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//���泷��
function saveCx(){
	var ids = "cxsj";
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "gypynew_gypyjg.do?method=saveCx";
	ajaxSubFormWithFun("GypyJgForm", url, function(data) {
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

//����
function cancelcx(){
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "gypynew_gypyjg.do?method=cancelXj&jgid="+rows[0]["jgid"];
	showDialog("����", 770, 480, url);
}

//�鿴ѧ������
function qshLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["jgid"] + "\");'>" + cellValue
			+ "</a>";
}

function View(jgid) {
	showDialog("�Ǽ����ҽ���鿴", 770, 480, "gypynew_gypyjg.do?method=viewJg&jgid="
			+ jgid);
}


