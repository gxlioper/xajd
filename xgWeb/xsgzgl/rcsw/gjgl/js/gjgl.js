//����ѧ����Ϣ�鿴

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='viewGjxx(\""+rowObject["gjxxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
function viewGjxx(gjxxid,cellValue){
	showDialog('ѧ��������Ϣ',800,420,'xsgjgl.do?method=gjxxView&gjxxid='+gjxxid+"&xh="+cellValue,null);
}
/*�h��*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xsgjgl.do?method=GjxxDelete", {
					values : ids.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/* ���� */
function importXX(){
	var url = "xsgjgl.do?method=importGjxxPrepare";
	var title = "����";
	showDialog(title, 550, 350, url);
}
//����
function downloadxzmb(){
	window.open("xsgjgl.do?method=downloadFile");
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
	var url = "xsgjgl.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("gjjgForm", url, function(data) {
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
   				    		  jQuery("#errora").attr("data_file","xsgjgl.do?method=downloadFileError&filename="+data["gid"]);
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

/* ������� */
function exportXX() {
	customExport('xsgjxxgl.do', exportData);
}
function exportData(){
	setSearchTj();// ���ø߼���ѯ����
	var url = "xsgjgl.do?method=exportData&dcclbh=" + 'ydxxgl.do';// dcclbh,�������ܱ��,���ݱ��ֶ�
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
