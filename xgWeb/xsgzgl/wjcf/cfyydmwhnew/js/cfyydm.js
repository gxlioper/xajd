function save(){
	var cfyymc=jQuery("#cfyymc");
	var kffs=jQuery("#kffs");
	if(cfyymc.val()=="" || kffs.val()==""){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var url="wjcf_cfyydmwh.do?method=cfyydmAdd&type=save";
    ajaxSubFormWithFun("cfyydmForm",url,function(data){
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


function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'wjcf_cfyydmwh.do?method=cfyydmUpdate&cfyydm='+rows[0]["cfyydm"];
		var title = "�޸Ĵ���ԭ��";
		showDialog(title,450,150,url);
	}
	
}

//�޸ı���
function saveUpdate(){
	var cfyymc=jQuery("#cfyymc");
	var kffs=jQuery("#kffs");
	if(cfyymc.val()=="" || kffs.val()==""){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	var url="wjcf_cfyydmwh.do?method=cfyydmUpdate&type=save";
    ajaxSubFormWithFun("cfyydmForm",url,function(data){
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
		jQuery.post("wjcf_cfyydmwh.do?method=cfyydmDelete",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}