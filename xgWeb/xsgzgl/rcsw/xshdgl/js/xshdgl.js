function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
var DCCLBH = "rcsw_xshdgl_xshdgl.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, xshdglExportData);
}

//��������
function xshdglExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xshdgl_xshdgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_XSHDGL");
	return false;
}


//����
function add() {
	var url = "xshdgl_xshdgl.do?method=addXshd";
	var title = "�����";
	showDialog(title, 770, 550, url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xshdgl_xshdgl.do?method=editXshd&sqid=' + rows[0]["sqid"];
		var title = "��޸�";
		showDialog(title, 770, 550, url);
	}
}

//�����޸Ľ������
function saveXshdgl(type){
	var ids = "hdmc"+"-"+"hdsj"+"-"+"hddd"+"-"+"zbdwdm"+"-"+"xbdwdm"+"-"+"cbdwdm"+"-"+"hdfzr"+"-"+"cyry";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(jQuery.trim(jQuery("#cyry").val()).length > 500){
		showAlert("������Ա���ó���500�֣�");
		return false;
	}
	if(jQuery.trim(jQuery("#hdjj").val()).length > 1000){
		showAlert("���鲻�ó���1000�֣�");
		return false;
	}
	var url = "xshdgl_xshdgl.do?method=saveXshd&type=" + type;
	ajaxSubFormWithFun("XshdglForm", url, function(data) {
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

//�鿴����
function hdmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xshdglView(\""
			+ rowObject["sqid"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ajaxurl��ת
function xshdglView(sqid, xh) {
	showDialog("��鿴", 770, 450, "xshdgl_xshdgl.do?method=ckXshd&sqid="
			+ sqid);
}
//ɾ�����
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xshdgl_xshdgl.do?method=delXshdgl",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function changeBmmc(){
	var autoSetting1 = {
		dataTable:"ZXBZ_XXBMDM",
		dataField:"bmmc",
		sqlTj: "",
		scrollHeight:165,
		width:220
	}
	var autoSetting2= {
			dataTable:"ZXBZ_XXBMDM",
			dataField:"bmmc",
			sqlTj: "",
			scrollHeight:165,
			width:220
		}
	var autoSetting3= {
			dataTable:"ZXBZ_XXBMDM",
			dataField:"bmmc",
			sqlTj: "",
			scrollHeight:165,
			width:220
		}
	// ģ��������������Ŀ���ơ�
	jQuery("#zbdwdm").setAutocomplete(autoSetting1);
	jQuery("#xbdwdm").setAutocomplete(autoSetting2);
	jQuery("#cbdwdm").setAutocomplete(autoSetting3);
	
}