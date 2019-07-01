/**
 * ־Ը���������js
 */

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ѧ�Ÿ�ʽ��
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='zyfwJgShow(\""
			+ rowObject["fwid"]+"\");'>" + cellValue
			+ "</a>";
}

/**
 * ����ص������ȡ
 */
function fwddSubString(cellValue, rowObject){
	var cellValueNotNull = cellValue ? cellValue : "";
	var cellValueNew = cellValueNotNull.length > 20 ? cellValue.substring(0,20)+"..." : cellValueNotNull;
	return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
}

/**
 * �鿴
 */
function zyfwJgShow(fwid) {
	var title = jQuery("#gnmkmc").val()+"�鿴";
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgShow&fwid="+fwid;
	showDialog(title, 800, 500,url);
}

/**
 * �����ı���
 */
var checkId = 'xh-xn-xq-fwkssj-fwjssj-fwddssx-fwdd-jzr-fwxss-fwnr';

function zyfwJgSaveForAdd() {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	if(jQuery("#fwddssx").val().endsWith("0000")){
		showAlert("�����ٽ�ʡ��������ѡ��������");
		return false;
	}
	if (jQuery("#fwnr").val().length>500) {
		showAlert("���������������500�֣�");
		return false;
	}
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgSaveForAdd";
	ajaxSubFormWithFun("zyfwJgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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

/**
 * �༭�ı���
 */
function zyfwJgSaveForEdit(type) {
	if(!checkNotNull(checkId)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	if(jQuery("#fwddssx").val().endsWith("0000")){
		showAlert("�����ٽ�ʡ��������ѡ��������");
		return false;
	}
	if (jQuery("#fwnr").val().length>500) {
		showAlert("���������������500�֣�");
		return false;
	}
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgSaveForEdit";
	ajaxSubFormWithFun("zyfwJgForm", url, function(data) {
		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
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

/**
 * ��д����ҳ��
 */
function add() {
	var url = "xsxx_zyfwgl_jg.do?method=zyfwJgAdd";
	var title = jQuery("#gnmkmc").val();
	showDialog(title, 800, 550, url);
}

/**
 * �޸ĵ���ҳ��
 */
function edit() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {

		var sjly = rows[0]["sjly"];
		if ("1" == sjly) {
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}

		var url = 'xsxx_zyfwgl_jg.do?method=zyfwJgEdit&fwid=' + rows[0]["fwid"];
		var title = jQuery("#gnmkmc").val()+"�޸�";
		showDialog(title, 800, 550, url);
	}

}

/**
 * ɾ��
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	for(var i=0;i<rows.length;i++){
		if (rows[i]["sjly"] == "1") {
			showAlertDivLayer("������̹����ļ�¼����ɾ����");
			return false;
		}
	}
	
	showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("xsxx_zyfwgl_jg.do?method=zyfwJgDel", {
				values : ids.toString()
			},
					function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
		}
	});
}

/**
 * ����
 */
var DCCLBH = "xsxx_zyfwgl_jg.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_zyfwgl_jg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ־Ը�������ĵ���
 */
function importConfig() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_ZYFWJG");
	return false;
}

