/**
 * �����¿�ܵ��빦�����
 * 
 * @param drmkdm ���빦�ܴ���
 * @return
 */
function toImportDataNew(drmkdm){
	var _SSO_DR_PATH = 'out_access.do?gnbh=import&toPage=/xgweb/dr/out_login.html';
	if(drmkdm == null || drmkdm == undefined || jQuery.trim(drmkdm) == ""){
		alert("����ģ�����δ����!");
		return false;
	}
	var url = _SSO_DR_PATH + "&drmkdm=" + drmkdm;
	showDialog('����',720,580,url,{close:function(){
		if (jQuery("#search_go")){
			jQuery("#search_go").click();
		}
	}});
	return false;
}


/**
 * ��ת������ҳ��
 * @param drmkdm
 * @return
 */
function toImportData(drmkdm){
	if(drmkdm == null || drmkdm == ""){
		alert("����ģ����벻��Ϊ�ա�");
		return false;
	}
	var url='import.do?method=toImportData'; 
	url+='&drmkdm='+drmkdm;
	showDialog('����',600,320,url,{close:function(){
		if (jQuery("#search_go")){
			jQuery("#search_go").click();
		}
	}});
	return false;
}

//����ģ��
function downloadTemplate(drwjgs){
	if(notNullImportTable()){
		var jqFrom=jQuery("#importForm");
		var url = "import.do?method=downloadTemplate";
		if(drwjgs != null && drwjgs != ""){
			url += "&drwjgs=" + drwjgs;
		}
		jqFrom.attr("action",url);
		jqFrom.submit();
	}
}

//���ش�������
function downloadErrorData(){
	var jqIsImport=jQuery("#isImport");
	var jqErrorDataNum=jQuery("#errorDataNum");
	if(jqIsImport.val() == "0"){
		alert("����δ�������ݻ��ѵ���ʧ�ܣ�");
		return false;
	}
	if(jqErrorDataNum.val() == "0"){
		alert("���ε���û�д�����Ϣ��");
		return false;
	}
	var jqFrom=jQuery("#importForm");
	var url="import.do?method=downloadErrorData";
	if(notNullImportTable()){
		jqFrom.attr("action",url);
		jqFrom.submit();
	}
	
}

//���õ�����
//����ģʽdrms:1Ϊupdate��0Ϊinsert
function setImportRsulte(data,drms){
	var reslutMsg="";
	var jqImportResult=jQuery("#importResult");
	var jqIsImport=jQuery("#isImport");
	var jqErrorDataNum=jQuery("#errorDataNum");
	if(data != null && data != ""){
		var drzs=data["drzs"];
		var cgs=data["cgs"];
		var cws=data["cws"];
		//�����Ƿ���ɹ�
		jqIsImport.val("1");
		//���ô�������
		jqErrorDataNum.val(cws);
		if(drms == "0"){
			reslutMsg="������������["+drzs+"]��������ɹ�["+cgs+"]��������ʧ��["+cws+"]����";
			//���ŵ��뿪�Ÿ���
			if("IMPORT_N100110" == jQuery("#drmkdm").val()){
				if(cws > 0){
					showConfirm("�Ƿ�ʧ���������ظ����ݸ�����ϵͳ��",{"okFun":function(){
						jQuery("#drms").val("1");
						jQuery("#drzs").val(drzs);
						jQuery("#cgs").val(cgs);
						jQuery("#cws").val(cws);
						importData();
						return;
					}});
				}
			}
		}else{
			var oldDrzs = jQuery("#drzs").val();
			var oldCgs = parseInt(jQuery("#cgs").val());
			var newCgs = cgs;
			var newCws = cws;
			if(oldCgs > 0){
				newCgs = oldCgs + parseInt(cgs);
			}
			reslutMsg="������������["+oldDrzs+"]��������ɹ�["+newCgs+"]��������ʧ��["+newCws+"]����";

		}
	}else{
		reslutMsg="����Ϊ�ջ򲻺Ϸ�������ʧ�ܣ���ο�ģ�����½��е��룡";
		//�����Ƿ���ɹ�
		jqIsImport.val("0");
		//���ô�������
		jqErrorDataNum.val("0");
	}
	jQuery("#drms").val("0");
	jqImportResult.html(reslutMsg);
}

//��֤������Ϊ��
function notNullImportTable(){
	var drmkdm=jQuery("#drmkdm").val();
	var drbm=jQuery("#drbm").val();
	if(drmkdm == null || drmkdm == ""){
		alert("��ǰҳ�����,�����½���!");
		return false;
	}
	if(drbm == null || drbm == ""){
		alert("��ѡ��ģ��!");
		return false;
	}
	return true;
}

//��֤�����ļ�����Ϊ��
function notNullImportFile(){
	var importFile=jQuery("#importFile").val();
	if(importFile == null || importFile == ""){
		alert("��ѡ�����ļ�!");
		return false;
	}
	var fileSuffix=importFile.substring(importFile.lastIndexOf(".")+1,importFile.length);
	fileSuffix = fileSuffix.toLowerCase();
	if(fileSuffix != "xls"  && fileSuffix != "dbf" ){
		alert("��ѡ��xls��dbf��ʽ���ļ����е���!")
		return false;
	}
	return true;
}

