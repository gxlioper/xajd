/**
 * ���ص���ģ��
 * @params drmkdm ����ģ�����
 */
function downloadTemplate(drmkdm){
	var jqFrom=jQuery("#importForm");
	var url = "hdgl_hdbljg.do?method=downloadTemplate";
	jqFrom.attr("action",url);
	jqFrom.submit();
}

/**
 * ��֤�Ƿ�ѡ�����ļ����ļ���ʽ
 */
function notNullImportFile(){
	var importFile=jQuery("#importFile").val();
	if(importFile == null || importFile == ""){
		showAlert("��ѡ�����ļ�!");
		return false;
	}
	var fileSuffix=importFile.substring(importFile.lastIndexOf(".")+1,importFile.length);
	fileSuffix = fileSuffix.toLowerCase();
	if(fileSuffix != "xls"){
		showAlert("�����ļ���ʽ���Ϸ�����ȷ��!")
		return false;
	}
	return true;
}

/**
 * ���浼�루����ʡ������һ���ĵ������ã�
 */
function selectImport(){
	if(!notNullImportFile()){
		return false;
	}
	var url = "hdgl_hdbljg.do?method=saveImport";
	ajaxSubFormWithFun("importForm",url,function(data){
		var file = jQuery("#importFile") 
		file.after(file.clone().val("")); 
		file.remove(); 
		
		if(data["result"]){
            jQuery("#cwsj").html("<font>�޴�������</font> ");
			showImportResult(data["totalCount"],data["totalCount"],0,"�ɹ�����");
		}else{
			if(data["error"]=="01"){
				showAlert(data["message"]);
			}else{
				var filename = data["errorTipsExcelName"];
				jQuery("#cwsj").html("<a href='javascript:void(0);' class='name' onclick=\"downloadError('"+filename+"')\">��������쳣����</a> ");
				showImportResult(data["totalCount"],data["totalCount"]-data["errorCount"],data["errorCount"],"�ɵ���");
			}
		}
	});
}

/**
 * ��ʾ��������ʾ��Ϣ
 */
function showImportResult(drzs,cgs,cws,tf){
	var reslutMsg="<font color='blue'>�ܼ�["+drzs+"]��</font>��<font color='green'>"+tf+"["
				   +cgs+"]��</font>��<font color='red'>����["+cws+"]��</font>��";
	jQuery("#importResult").html(reslutMsg);
	jQuery("#dr_result").show();
}

/**
 * ���ش�������
 * @params drmkdm ����ģ�����
 */
function downloadError(filename){
	var jqFrom=jQuery("#importForm");
	var url = "dekt_xfsq.do?method=downloadImportError&filename="+filename;
	jqFrom.attr("action",url);
	jqFrom.submit();
}







