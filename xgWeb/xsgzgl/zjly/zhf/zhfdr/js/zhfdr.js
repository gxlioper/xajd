//��ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//���뵼��ҳ�淽��
function importJfxm(){
	var url = "zjly_zhfdr.do?method=importJfxmPrepare";
	var title = "����";
	showDialog(title, 770, 350, url);
}
//��������
function exportJfxm() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zjly_zhfdr.do?method=exportJfxm";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//�ϲ���������ϸ���
function exportXxsx() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zjly_zhfdr.do?method=exportXxsx";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
//�ϲ����� �����ܡ�
function exportHzsx() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zjly_zhfdr.do?method=exportHZsx";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
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
	var url = "zjly_zhfdr.do?method=SaveDrForm";
	/**/
	if(!checkNotNull("xmmkdm-jfxmdm")){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("ZhfDrForm", url, function(data) {
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
   				      if(data["message"] == "����ʧ��,����ϸ�˶ԡ������¼.xls����"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#errora").attr("data_file","zjly_zhfdr.do?method=downloadFileError&filename="+data["gid"]);
   				    		  
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



//ɾ�����
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
//		var rows = jQuery("#dataTable").getSeletRow();
//		for(var i=0;i<ids.length;i++){
//			if(rows[i]['shzt']=='1'){
//				showAlertDivLayer("�Ѿ��󶨵ļ�¼����ɾ����");
//				return false;
//			}
//		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("zjly_zhfdr.do?method=delRecord",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
//		if(rows[0]['shzt']=='1'){
//			showAlertDivLayer("�Ѿ��󶨵ļ�¼�����޸ģ�");
//			return false;
//		}
		var url = 'zjly_zhfdr.do?method=updateJg&id=' + rows[0]["id"]
				+ '&xh=' + rows[0]["xh"];
		var title = "�޸��ۺ����ʷ���Ϣ";
		showDialog(title, 770, 552, url);
	}
}

function savejg(type){
	if(!checkNotNull("xmmkdm-jfxmdm-sxsm-cysj")){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "zjly_zhfdr.do?method=updateJg&type=" + type;
	ajaxSubFormWithFun("ZhfDrForm", url, function(data) {
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
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function View(id, xh) {
	showDialog("�鿴�ۺ����ʷ���Ϣ", 770, 450,'zhf_sq.do?method=viewZhfsq&id='+id);
}

//����
function downloadxzmb(){
	window.open("zjly_zhfdr.do?method=downloadFile");
}

//������������
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}