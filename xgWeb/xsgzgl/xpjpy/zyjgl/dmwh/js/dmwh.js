
function save(){
	var mc =jQuery("#rddjmc").val();
	if (jQuery.trim(mc) == ""){
		showAlert("�������������ȼ����ƣ�");
		return false;
	}
	var url="pjpy_zyjgldmwh.do?method=rddjAdd&type=save";
    ajaxSubFormWithFun("ZyjrddjwhForm",url,function(data){
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


//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'pjpy_zyjgldmwh.do?method=rddjUpdate&rddjdm='+rows[0]["rddjdm"];
		var title = "�޸��϶��ȼ�";
		showDialog(title,450,310,url);
	}
	
}
//�޸ı���
function saveUpdate(){
	var mc =jQuery("#rddjmc").val();
	if (jQuery.trim(mc) == ""){
		showAlert("�������������ȼ����ƣ�");
		return false;
	}
	var url="pjpy_zyjgldmwh.do?method=rddjUpdate&type=save";
    ajaxSubFormWithFun("ZyjrddjwhForm",url,function(data){
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

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("pjpy_zyjgldmwh.do?method=rddjDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}



