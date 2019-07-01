function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "cxdd_pyjg.do?method=add";
	var title = "����������";
	showDialog(title, 660, 450, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]['sjly']=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var url = 'cxdd_pyjg.do?method=edit&xsid=' + rows[0]["xsid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "�������޸�";
		showDialog(title, 660, 450, url);
	}
}


//�����޸Ľ������
function saveCxjg(type){
	var ids = ""
	if( type == "update"){
	   ids = "xn"+"-"+"xq"+"-"+"pj"+"-"+"py";
	}else{
		ids ="xh"+"-"+ "xn"+"-"+"xq"+"-"+"pj"+"-"+"py";
	}
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(checkzs() == false){
		showAlert("���������80��-120��֮�䣡");
		return false;
	}
	var url = "cxdd_pyjg.do?method=saveCxjg&type=" + type;
	ajaxSubFormWithFun("CxddJgForm", url, function(data) {
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

function checkzs(){
	if(jQuery.trim(jQuery("#py").val()).length > 120 || jQuery.trim(jQuery("#py").val()).length < 80){
		return false;
	}else{
		return true;
	}
}

//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='CxjgView(\""
			+ rowObject["xsid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function CxjgView(xsid, xh) {
	showDialog("�������鿴", 660, 450, "cxdd_pyjg.do?method=view&xsid="
			+ xsid + "&xh=" + xh);
}

//ɾ����������
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}  else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("cxdd_pyjg.do?method=delCxjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "xsxx_cxdd_pyjg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, CxjgExportData);
}

//��������
function CxjgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "cxdd_pyjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_CXJG");
	return false;
}
