function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//dcclbh,�������ܱ��
var DCCLBH = "";
//�Զ��嵼�� ����
function exportConfig(bz) {
	//DCCLBH�������ܱ��,ִ�е������� 
	
	if(bz == '1'){
		DCCLBH = "xg_zjly_ylbx.do";
		jQuery("#cxblb").val("�²α�");
	}else{
		DCCLBH = "xg_zjly_ylbx_xb.do";
		jQuery("#cxblb").val("����");
	}
	customExport(DCCLBH, ExportDatas);
}

//��������
function ExportDatas() {
	setSearchTj();//���ø߼���ѯ����
	var url = "zjly_ylbx.do?method=exportData&dcclbh=" + DCCLBH+"&cxblb="+jQuery("#cxblb").val();//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//����
//function importConfig(){
//	toImportDataNew("IMPORT_YLBX");
//	return false;
//}

//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("zjly_ylbx.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
//�鿴ѧ������
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ylbxview(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//�鿴ѧ��ajaxurl��ת
function ylbxview(id, xh) {
	if(id.length<32){
		alertInfo("��ѧ��δ�α���");
		return false;
	}else{
		showDialog("ҽ�Ʊ��ղ鿴", 750, 350, "zjly_ylbx.do?method=ylbxck&id="
				+ id + "&xh=" + xh);
	}
}

//����ҳ����ת
function add(){
	showDialog('����',600,450,'zjly_ylbx.do?method=Ylbxadd');
}


//�༭ҳ����ת
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["id"]==null){
			alertInfo("��ѧ��δ�α���");
			return false;
		}else{
			showDialog('�޸�ѧ��ҽ����Ϣ',600,450,'zjly_ylbx.do?method=Ylbxedit&id=' + rows[0]["id"]+"&xh="+rows[0]["xh"]);
		}
	}
}

//����༭
function save(url,checkId) {
	if (!checkNull(checkId)) {
		return false;
	}
	//lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	//unlock();
}
/* ���� */
function importXX(){
	var url = "zjly_ylbx.do?method=importYlbx";
	var title = "����";
	showDialog(title, 550, 350, url);
}
//����
function downloadxzmb(){
	window.open("zjly_ylbx.do?method=downloadFile");
}

//������������
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}


//�ļ����Ϳ���
function attachfilename(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["xls"];
	if (jQuery.inArray(type, types) == -1){ //�����������з��� index,�����������������,�򷵻� -1;
		/*����������ϴ�����,���input file��������д��������ie��chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("ֻ�����ϴ�xls���͵��ļ�!");
		return false;
	}
}
function  saveRr(){
	var url = "zjly_ylbx.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("ylbxForm", url, function(data) {
		 if(data["cw"]=="none"){
			 jQuery("#errortr").hide();
		    jQuery("#errora").attr("href","");
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   			 showAlert(data["message"],{},{"clkFun":function(){
   				      if(data["cw"] == "yes"){
   				    	  if(data["gid"] != "" && data["gid"] != null && data["gid"] != "null"){
   				    		  jQuery("#errortr").show();
   				    		  jQuery("#errora").attr("data_file","zjly_ylbx.do?method=downloadFileError&filename="+data["gid"]);
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