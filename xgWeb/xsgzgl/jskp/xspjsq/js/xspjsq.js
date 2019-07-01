/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 *���� 
 */
function xspjsqApply(){
	var url = "xspj_xspjsq.do?method=xspjsqApply";
	var title = "��������";
	showDialog(title, 680, 490, url);
}

/**
 * ��������ֶμ���
 */
var ids = "xmmc-bmmc-xmlbdm-cysj-xh-xn-dxqdm-fjid";

/**
 *���뱣��(����ݸ塢�ύ����)
 */
function xspjsqApplySave(saveFlag){

	var xmmc = jQuery("#xmmc").val();
	var bmmc = jQuery("#bmmc").val();
	var xmlbdm = jQuery("#xmlbdm").val();
	var cysj = jQuery("#cysj").val();
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var dxqdm = jQuery("#dxqdm").val();
	var fjwt = jQuery('.MultiFile-title');
	
	if("" == xmmc || null == xmmc){
		showAlert("����д��Ŀ���ƣ�");
		return false;
	}
	if("" == bmmc || null == bmmc){
		showAlert("��ѡ��ָ�����ţ�");
		return false;
	}
	if("" == xmlbdm || null == xmlbdm){
		showAlert("��ѡ����Ŀ���");
		return false;
	}
	if("" == cysj || null == cysj){
		showAlert("����д����ʱ�䣡");
		return false;
	}
	if("" == xh || null == xh){
		showAlert("����дѧ�ţ�");
		return false;
	}
	if("" == xn || null == xn){
		showAlert("��ѡ��ѧ�꣡");
		return false;
	}
	if("" == dxqdm || null == dxqdm){
		showAlert("��ѡ���ѧ�ڣ�");
		return false;
	}
	
	if(fjwt.length == 0){
		showAlert("���ϴ�������");
		return false;
	}
	
	var url = "xspj_xspjsq.do?method=xspjsqApplySave&saveFlag=" + saveFlag;
	ajaxSubFormWithFun("xspjsqForm", url, function(data){
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
 * ɾ��
 * @return
 */
function xspjsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["shzt"] != "0"){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xspj_xspjsq.do?method=xspjsqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �޸�
 * @return
 */
function xspjsqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		var title = "�޸���������";
		var url = "xspj_xspjsq.do?method=xspjsqUpdate&sqid="+rows[0]["sqid"];
		showDialog(title, 680, 490, url);
	}
}

/**
 * �޸ı���(����ݸ塢�ύ����)
 * ������ı���д��һ����������
 */
function xspjsqUpdateSave(saveFlag){
	
	/*��֤�����ֶ��Ƿ�δ��д*/
	if(!checkNotNull(ids)){
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "xspj_xspjsq.do?method=xspjsqApplySave&saveFlag=" + saveFlag;
	ajaxSubFormWithFun("xspjsqForm", url, function(data){
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
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xspjsqView(\""+rowObject["sqid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

/**
 * ������Ӳ鿴��ϸ��Ϣ
 * @param id
 * @param xh
 * @return
 */
function xspjsqView(sqid) {
	var title = "��������鿴";
	var url = "xspj_xspjsq.do?method=xspjsqView&sqid="+sqid;
	showDialog(title,690,535,url);
}

/**
 * �ύ��֧��������
 * @return
 */
function xspjsqSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(0 == ids.length){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼");
	}else{
		for(var i=0;i<rows.length;i++){
			if("0" != rows[i]["shzt"] && "3" != rows[i]["shzt"]){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			var tips = submitLoading();
			try{
				tips.show();
			}catch(e){
				
			}
			jQuery.post("xspj_xspjsq.do?method=xspjsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ����
 * @return
 */
function xspjsqRevoke() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	}else if (ids.length > 1){
		showAlertDivLayer("ֻ��ѡ��һ����¼���г�����");
	}else{
		if (rows[0]['shzt'] != '5') {
			showAlertDivLayer("ֻ�ܳ������״̬Ϊ������С��ļ�¼��");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xspj_xspjsq.do?method=xspjsqRevoke", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * ���̸���
 * @return
 */
function xspjsqTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	}else{
		if(shzt == "0"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splcid']);
	}
}

/*dcdlbh,�������ܱ��*/
var DCDLBH = "xspj_xspj_xspjsq.do";

/**
 * ����
 * @return
 */
function xspjsqExport() {
	/*DCCLBH�������ܱ��,ִ�е�������*/
	customExport(DCDLBH, xspjsqExportData);
}

/**
 * ����ִ��
 */
function xspjsqExportData() {
	/*���ø߼���ѯ����*/
	setSearchTj();
	var url = "xspj_xspjsq.do?method=xspjsqExport&dcclbh=" + DCDLBH;
	/*���ø߼���ѯ����*/
	url = addSuperSearchParams(url);
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����ҳ��
 * @return
 */
function xspjsqImport(){
	var url = "xspj_xspjsq.do?method=xspjsqImport";
	var title = "ѧ������������Ϣ����";
	showDialog(title, 500, 309, url);
}

/**
 * ����ģ������
 * @return
 */
function mbDownload(){
	var url = "xspj_xspjsq.do?method=downloadFile";
	window.open(url);
}

/**
 * ѡ���ļ������ļ����Ƹ�ֵ��input��
 */
function selectFiles(obj){
	var filefullpath = getFullPath(obj);
	checkFileType(obj);
}

/**
 * ��ȡinput file������
 * @param obj
 * @return
 */
function getFullPath(obj){ 
	if(obj){ 
	 if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
		 if(obj.files) 
		 { 
		   return obj.files.item(0).getAsDataURL(); 
		 } 
		 return obj.value; 
	 }
	 return obj.value; 
	}
}

/**
 * �ļ����Ϳ���
 * @param obj
 * @return
 */
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){
		/*����������ϴ�����,���input file��������д��������ie��chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("ֻ�����ϴ�xls���͵��ļ���");
		return false;
	}
}

/**
 * ���뱣��
 */
function importSave(){
	var url = "xspj_xspjsq.do?method=SaveDrForm";
	
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	
	ajaxSubFormWithFun("xspjsqForm", url, function(data) {
		if(data["message"]=="����ɹ���"){
		 jQuery("#errortr").hide();
		 jQuery("#errora").attr("href","");
		 showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
			  refershParent();
			}
		 }});
   	 }else{
		 showAlert(data["message"],{},{"clkFun":function(){
		      if(data["message"] == "����ʧ��,����ϸ�˶ԡ���������.xls����"){
		    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
		    		  jQuery("#errortr").show();
		    		  jQuery("#errora").attr("data_file","xspj_xspjsq.do?method=downloadFileError&filename="+data["gid"]);
		    	  }
		      }else{
		    	 jQuery("#errortr").hide();
		    	jQuery("#errora").attr("data_file","");
		      }
		      jQuery("#drmb").val("");
			}});
   		}
	});
}

/**
 * ������������
 * @return
 */
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}