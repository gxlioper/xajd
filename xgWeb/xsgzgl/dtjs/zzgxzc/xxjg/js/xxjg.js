/**
 * ���Ž���-��֯��ϵת������Ϣ���ҳ��js
 */

var action="dtjs_xxjg.do";

//����
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����
 */
function xxjgAdd(){
	var url =action+"?method=xxjgAdd";
	var title = "����ת�������Ϣ";
	showDialog(title, 800, 500, url);
}

/**
 * ɾ��
 */
function xxjgDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("��ѡ�ļ�¼�а����������ݣ�����ɾ����������ѡ��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=xxjgDelete", {
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
function xxjgUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("��ѡ��ļ�¼Ϊ�������ݣ������޸ģ�");
			return false;
		}
		
		var xh=rows[0]["xh"];
		var jgid=rows[0]["jgid"];
		var url =action+"?method=xxjgUpdate&xh="+xh+"&jgid="+jgid;
		var title = "�޸�ת�������Ϣ";
		showDialog(title, 800, 500, url);
	}
}

/**
 * ����
 */
function xxjgShow(id){
	var url = action+"?method=xxjgShow&jgid="+id;
	var title = "ת�������Ϣ";
	showDialog(title, 800, 500, url);
}

/**
 * ���ӱ���
 */
function saveForAdd(Ids){
	var url = "dtjs_xxjg.do?method=xxjgAdd&type=save";
	//��֤������
	if(!checkNotNull(Ids)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	//��֤�����ʽ��:�Ƿ񿪾߻���֤��
	if(jQuery("[name='sfkjhyzm']:checked").length == 0){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	
	ajaxSubFormWithFun("form", url, function(data) {
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

/**
 * �޸ı���
 */
function saveForUpdate(Ids){
	var url = "dtjs_xxjg.do?method=xxjgUpdate&type=update";
	//��֤������
	if(!checkNotNull(Ids)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	//��֤�����ʽ��:�Ƿ񿪾߻���֤��
	if(jQuery("[name='sfkjhyzm']:checked").length == 0){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		return false;
	}
	
	//��֤������־�Ƿ񳬹�����
	if(jQuery("#gpyy").val().length>500){
		showAlert("����ԭ�򳬹�500�֣���ɾ����");
		return false;
	}
	
	ajaxSubFormWithFun("form", url, function(data) {
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

/**
 * ����
 */
var DCGLBH = "dtjs_xxjg.do";//dcglbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCGLBH, xshdglExportData);
}

//��������
function xshdglExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "dtjs_xxjg.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
