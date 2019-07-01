/**
 * ��ѯ
 * @return
 */
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ��������ֵ�ı��Զ����㹤ʱ��
 * @return
 */
function cjchange(){
	var bcje = jQuery("#bcje").val();
	if(jQuery.trim(bcje) == "" || jQuery.trim(bcje) == null){
		jQuery("#gss").val("0");
		return;
	}
	var cjbz = parseFloat(jQuery("#cjbz").val());
	var gss = (parseFloat(bcje)/cjbz).toFixed(1);
	jQuery("#gss").val(gss);
}

/**
 * ɾ��
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sftj']=='1'){
				showAlertDivLayer("�Ѿ��ύ�����ݲ���ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("cjff_zjdx.do?method=deljg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

var DCCLBH = "qgzx_jfcjgl_cjff_zjdx.do";//dcclbh,�������ܱ��

/**
 * �Զ��嵼��
 * @return
 */
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, jgExportData);
}

/**
 * ����
 * @return
 */
function jgExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "cjff_zjdx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ����
 * @param type
 * @return
 */
function saveForm(type){
	var ids = "xh"+"-"+"xm"+"-"+"ffndyf"+"-"+"yrdwdm"+"-"+"gwxzdm"+"-"+"gwlbdm"+"-"+"xqdm"+"-"+"bcje"+"-"+"gss"+"-"+"gznr";
	if(checkNotNull(ids) == false){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var bcje = jQuery("#bcje").val();
	var sxsz = jQuery("#sxsz").val();
	var sfyxcgcjsx = jQuery("#sfyxcgcjsx").val();
	
	if("��" == sfyxcgcjsx){
		if(parseInt(bcje) > parseInt(sxsz)){
			showAlert("���·��Ž��ܴ��ڳ�����ޣ�");
			return false;
		}
	}else{
		if(parseInt(bcje) > parseInt(sxsz) && (jQuery("#bz").val() == null || jQuery("#bz").val() == "")){
			showAlert("���·��Ž����ڳ�����ޣ�������д��ע��");
			return false;
		}
	}
	
	var url = "cjff_zjdx.do?method=saveForm&type=" + type;
	ajaxSubFormWithFun("CjffForm", url, function(data) {
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
 * �ύ
 * @return
 */
function tj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("cjff_zjdx.do?method=tj",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ����
 * @return
 */
function add(){
	var url = "cjff_zjdx.do?method=cjffAdd";
	var title = "��𷢷�";
	showDialog(title, 770, 550, url);
}

/**
 * �޸�
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
	    if(rows[0]["sftj"] == "1"){
	    	showAlertDivLayer("�Ѿ��ύ�����ݲ����޸ģ�");
			return false;
		}
		var url = 'cjff_zjdx.do?method=cjffEdit&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��𷢷��޸�";
		showDialog(title, 770, 550, url);
	}
}

/**
 * �鿴
 * @return
 */
function ck(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		var url = 'cjff_zjdx.do?method=cjffck&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��𷢷Ų鿴";
		showDialog(title, 770, 550, url);
	}
}

/**
 * ����
 * @return
 */
function dr(){
	var url = "cjff_zjdx.do?method=dr";
	var title = "����";
	showDialog(title, 770, 350, url);
}


//���ļ����Ƹ�ֵ��input��
function attachfilename(obj){
	var filefullpath = getFullPath(obj);
//	jQuery(obj).parent().find("input[name='wjmc']").val(filefullpath);
	checkFileType(obj);
}

//��ȡinput file������
function getFullPath(obj){ 
	if(obj) 
	{ 
		 if(window.navigator.userAgent.indexOf("Firefox")>=1) 
		 { 
			 if(obj.files) 
			 { 
				 return obj.files.item(0).getAsDataURL(); 
			 } 
			 return obj.value; 
		 } 
		 return obj.value; 
	 } 
} 

//�ļ����Ϳ���
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

function  saveRr(){
	var url = "cjff_zjdx.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("CjffForm", url, function(data) {
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
   				    		  jQuery("#errora").attr("data_file","cjff_zjdx.do?method=downloadFileError&filename="+data["gid"]);
   				    		  
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

//����
function downloadxzmb(){
	window.open("cjff_zjdx.do?method=downloadFile");
}

//������������
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}


/**
 * ȡ���ύ
 * @return
 */
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ���ύ�ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫȡ���ύѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("cjff_zjdx.do?method=CancelTjRecord",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}