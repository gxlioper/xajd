/**
 * ��������б�ҳ��js
 */

var action="xszz_zzkff.do";

//����
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 */
function zzkffAdd(){
	var url =action+"?method=zzkffZj";
	var title = "�������������Ϣ";
	showDialog(title, 800, 500, url);
	jQuery("#dataTable").reloadGrid();
}

/**
 * ɾ��
 */
function zzkffDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=zzkffSc", {
					values : ids.toString()
				}, function(data) {
					//�ɹ�ɾ����������¼ alertInfo()
					//showAlertDivLayer(mes);
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}

/**
 * �޸�
 */
function zzkffUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var xh=rows[0]["xh"];
		var id=rows[0]["id"];
		var url =action+"?method=zzkffXg&xh="+xh+"&id="+id;
		var title = "�޸����������Ϣ";
		showDialog(title, 800, 500, url);
	}
}

/**
 * ����ѧ����ʾһ�����������Ϣ������
 */
function zzkffShow(id){
	var url = action+"?method=zzkffXq&id="+id;
	var title = "���������Ϣ";
	showDialog(title, 800, 500, url);
}

/**
 * �������������Ϣ�ı���
 */
function save(url,Ids){
	//��֤������
	if(!checkNotNull(Ids)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	//��֤�����ʽ��:���Ϊ��Ч����
	var je = jQuery("#je").val();
	if(isNaN(je)||je<0){
		jQuery("#je").focus();
		return showAlert("��ע�������Ϊ��Ч������");
	}
	
	lock();
	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
	unlock();
}

/**
 * ���뵼��
 */
var DCGLBH = "xszz_zzkff.do";//dcglbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCGLBH, xshdglExportData);
}

//��������
function xshdglExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_zzkff.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportDataNew("IMPORT_ZZKFF");
	return false;
}