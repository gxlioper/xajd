

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

function  saveDr(){
	var url = "gyglnew_gyglry.do?method=dr";
	if(jQuery("#file").val() == "" ||��jQuery("#file").val() == null){
		showAlert("��ѡ�����ļ���");
		return false;
	}
	ajaxSubFormWithFun("form", url, function(data) {
		if(data["drNum"] && !data["message"]){
			var message = "�ɹ�����"+data["drNum"]+"����¼������"+data["errNum"]+"�������¼��"
			 showAlert(message,{},{"clkFun":function(){
				  	if(data["fileName"] != "" && data["fileName"] != null && data["fileName"] != "null"){
			    		  jQuery("#errortr").show();
			    		  jQuery("#errora").attr("data_file","gyglnew_gyglry.do?method=downloadError&filename="+data["fileName"]);
			    		  
			    	  }else{
			    		  if (parent.window){
								refershParent();
							}
			    	  }
			 }});
		}else{
			return showAlert(data["message"]);
		}
	});
	
}








//����
function downloadxzmb(){
	window.open("gyglnew_gyglry.do?method=downloadMb");
}

//������������
function downloaderror(){
	var url = jQuery("#errora").attr("data_file");
	window.open(url);
}