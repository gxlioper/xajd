/**
 * ��ѯ
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "xwtzgl_xmjg.do?method=add";
	var title = "���";
    showDialog(title, 770, 500, url);
	
}

//���ӽ������
function saveSqjg(type){
	var ids = "xh-xmmc-xmjbdm-xn-xq-sskmdm-zxf";
	if(check(ids) == false){ 
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xwtzgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XwTzXmJgForm", url, function(data) {
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

function saveSqjgUpdate(type){
	if(jQuery("#xmmc").val() == ''){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	if(checkzs() == false){
		return false;
	}
	var url = "xwtzgl_xmjg.do?method=saveSqjg&type=" + type;
	ajaxSubFormWithFun("XwTzXmJgForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='SqjgView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

function SqjgView(sqid, xh) {
	showDialog("�鿴", 770, 450, "xwtzgl_xmjg.do?method=XmjgView&sqid="
			+ sqid + "&xh=" + xh);
}

//ɾ��������
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xwtzgl_xmjg.do?method=delSqjl",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}});
	
}


//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}
		var url = 'xwtzgl_xmjg.do?method=editSqjg&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "ѧ����Ŀ�����޸�";
		showDialog(title, 770, 500, url);
}

var DCCLBH = "sztz_xwtzgl_xmjg.do";

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xsxmsqJgExportData);
}

//��������
function xsxmsqJgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xwtzgl_xmjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_XWXSTZXM");
	return false;
}



function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("�������Ϊ500�����Ѿ���������ȷ�ϣ���");
		return false;
	}
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery.trim(jQuery("#"+id[i]).val());
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

