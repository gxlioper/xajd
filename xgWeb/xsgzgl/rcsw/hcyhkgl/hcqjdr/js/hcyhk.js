
/*�h��*/
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var xhs="";
	for(var i=0;i<rows.length;i++){
		xhs+=rows[i]["xh"]+",";
	}	
	xhs=xhs.substring(0,xhs.length-1);
	if (ids.length == 0) {
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("hcyhk_hcyhqjdr.do?method=HcqjDelete", {
					values : ids.toString(),
					xhs : xhs.toString()
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
	var url = "hcyhk_hcyhqjdr.do?method=importhcyhk";
	var title = "����";
	showDialog(title, 550, 350, url);
}
//����
function downloadxzmb(){
	window.open("hcyhk_hcyhqjdr.do?method=downloadFile");
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
	var url = "hcyhk_hcyhqjdr.do?method=SaveDrForm";
	if(jQuery("#drmb").val() == "" ||��jQuery("#drmb").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("hcqjForm", url, function(data) {
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
   				    		  jQuery("#errora").attr("data_file","hcyhk_hcyhqjdr.do?method=downloadFileError&filename="+data["gid"]);
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