//��һ�μ���
var isFirst=true;
//�����ѯҳ��

//ѧ������
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='oneFyffjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='oneFyffjgView(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}
//���÷��Ž���鿴
function oneFyffjgView(guid,xh){
	showDialog("���÷��Ž����ѯ",800,380,"rcsw_fyff_ffjg.do?method=oneFyffjgView&guid="+guid+"&xh="+xh);
}

//���ݷ�����Ŀ�ı���
function loadFfje(obj){
	var option=jQuery(obj).find("option:selected");
	var mrffje=jQuery(option).attr("mrffje");
	var fffs=jQuery(option).attr("fffs");
	
	jQuery("#sfje").val(mrffje);
	
	if("0"==fffs){
		jQuery("input[name=fffs][value=0]").attr("checked",'checked');
		jQuery("input[name=fffs][value=0]").click();
		jQuery("#bfyf").show();
		
	}else{
		jQuery("input[name=fffs][value=1]").attr("checked",'checked');
		jQuery("input[name=fffs][value=1]").click();
		jQuery("#bfyf").hide();
	}
	changeDate();
}


//����������ʾ��ʽ
function changeDate(obj){
	var selectV=jQuery(obj).val();
	if(selectV=="0"){//mm
		jQuery("#mm").show();
		if(isFirst){
			jQuery("#mm").val(jQuery("#ffsj").val());
			isFirst=false;
		}
		jQuery("#dd").hide();
	}else if(selectV=="1"){
		jQuery("#mm").hide();
		jQuery("#dd").show();
		if(isFirst){
			jQuery("#dd").val(jQuery("#ffsj").val());
			isFirst=false;
		}
	}
}
//�ж��Ƿ���Ҫ��д�����˺�
function changeFfzh(obj){
	var option=jQuery(obj).find("option:selected");
	var ffzh=jQuery(option).attr("ffzh");
	if("0" == ffzh){
		jQuery("#ffzh").show();
	}else {
		jQuery("#ffzh").hide();
	}
}


//����
function saveForm(method,type){
	
	var xh = jQuery("#xh").val();			//ѧ��
	var ffxmdm = jQuery("#ffxmdm").val();   //������Ŀ����
	var sfje = jQuery("#sfje").val();		//ʵ�����
	var fftjdm = jQuery("#fftjdm").val();	//����;������
	var ffzh = jQuery("#ffzh").val();		//�����˺�
	var mm = jQuery("#mm").val();			//�·�ʱ��
	var dd = jQuery("#dd").val();			//����ʱ��
	var fffs = jQuery('input[name="fffs"]:checked').val() // ���ŷ�ʽ
	var selectFfzh=jQuery("#fftjdm").find("option:selected").attr("ffzh");  //�����˺��Ƿ����
	
	if("" == xh){
//		showAlert("ѧ�Ų���Ϊ��");
//		return false;
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if("" == ffxmdm){
//		showAlert("������Ŀ����Ϊ��");
//		return false;
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}if("" == sfje){
//		showAlert("���Ž���Ϊ��");
//		return false;
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}if(("0"==fffs&&""==mm)||("1"==fffs&&""==dd)){
//		showAlert("����ʱ�������д");
//		return false;
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}if("" == fftjdm){
//		showAlert("����;��������д");
//		return false;
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}if("0" == selectFfzh && "" == ffzh){
//		showAlert("����;���˺ű�����д");
//		return false;
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		
	}
	
	//�˺�����Ǳ����շ����˺�
	if("0" != selectFfzh){
		jQuery('#ffzh').val("");
	}
	//����ʱ�丳ֵ
	if("0"==fffs){
		 jQuery('#ffsj').val(mm);
	}else if("1"==fffs){
		 jQuery('#ffsj').val(dd);
	}
	 var url = "rcsw_fyff_ffjg.do?method="+method+"&type="+type;
      ajaxSubFormWithFun("FyffjgForm",url,function(data){
    	  
    	  if (data["success"] == "false"){
    		  showAlert("��ѧ����"+jQuery("#ffxmdm>option:selected").text()+"��"+jQuery('#ffsj').val()+"�ѷ���" );
    	  } else {
    		  showAlert(data["message"],{},{"clkFun":function(){
        			if (parent.window){
     				 refershParent();
        			}
      		  }});
    	  }
    	  
      });
}


//��ʼ��������Ŀ
function selectFfxm(){
	var selectV=jQuery("#ffxmdmId").val();
	jQuery("#ffxmdm>option").each(function(){
		if(selectV==jQuery(this).val()){
			jQuery(this).attr("selected",true);
			jQuery("[name=fffs]:checked").click();
		}
	});
}

//��ʼ������;��
function selectFftj(){
	var selectV=jQuery("#fftjdmId").val();
	jQuery("#fftjdm>option").each(function(){
		if(selectV==jQuery(this).val()){
			jQuery(this).attr("selected",true);
			jQuery(this).change();
		}
	});
}


//����
function add(){
	var url = "rcsw_fyff_ffjg.do?method=addFyffjg";
	var title = "���ӷ��÷��Ž����Ϣ";
	showDialog(title,800,430,url);
}

//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_fyff_ffjg.do?method=updateFyffjg&guid='+rows[0]["guid"]+'&xh='+rows[0]["xh"];
		var title = "�޸ķ��÷��Ž��";
		showDialog(title,800,430,url);
	}
}


//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("rcsw_fyff_ffjg.do?method=deleteFyffjg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


//����
function exportConfig(){
	var DCCLBH='rcsw_fyff_fyffjg.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='rcsw_fyff_fyffjg.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_fyff_ffjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function importConfig(){
	toImportData("IMPORT_RCSW_FYFF");
	return false;
}

function changeMonthorDay(obj){
	if(obj.value == '0'){
		jQuery("#bfyf").show();
	}else if(obj.value == '1'){
		jQuery("#bfyf").hide();
	}
}


//�°浼��
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_RCSW_FYFF");
	return false;

}



