/**
 * @author ����Դ 
 * @����:ס�޽����js
 * @develop-date:2015-05-19
 * @lastupdate-date:2015-05-22
 */


function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add() {
	var url = "zjly_jcxxwh.do?method=add";
	var title = "��Ԣ�������ά��";
	showDialog(title, 770, 400, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'zjly_jcxxwh.do?method=editJg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��Ԣ��������޸�";
		showDialog(title, 770, 400, url);
	}
}

//�����޸Ľ������
function savejg(type){
	var ids = "xh"+"-"+"xn"+"-"+"xq"+"-"+"jcdm";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	var url = "zjly_jcxxwh.do?method=savejg&type=" + type;
	ajaxSubFormWithFun("GyjcWhForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='jgView(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function jgView(id, xh) {
	showDialog("��Ԣ��������鿴", 770, 400, "zjly_jcxxwh.do?method=jgView&id="
			+ id + "&xh=" + xh);
}
//ɾ��ס�޽��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("zjly_jcxxwh.do?method=deljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
var DCCLBH = "zjly_jcxxwh.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jgExportData);
}

//��������
function jgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zjly_jcxxwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_GYJCWH");
	return false;
}