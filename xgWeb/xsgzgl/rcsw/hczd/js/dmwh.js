
function save(){
	var zdmc = jQuery("#zdmc").val();
	var shenfen = jQuery("#shenfen").val();
	if (jQuery.trim(zdmc) == "" || jQuery.trim(zdmc) == null || jQuery.trim(shenfen) == "" || jQuery.trim(shenfen) == null){
		showAlert("����д��*�ı����");
		return false;
	}
	var url="hczdgl_hczdwh.do?method=hczdAdd&type=save";
    ajaxSubFormWithFun("hczdForm",url,function(data){
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
		var url = 'hczdgl_hczdwh.do?method=hczdUpdate&zdmc='+encodeURI(encodeURI(rows[0]["zdmc"]));
		var title = "�޸Ļ�վ��";
		showDialog(title,450,310,url);
	}
	
}
//�޸ı���
function saveUpdate(){
	var zdmc = jQuery("#zdmc").val();
	var shenfen = jQuery("#shenfen").val();
	if (jQuery.trim(zdmc) == "" || jQuery.trim(zdmc) == null || jQuery.trim(shenfen) == "" || jQuery.trim(shenfen) == null){
		showAlert("����д��*�ı����");
		return false;
	}
	var url="hczdgl_hczdwh.do?method=hczdUpdate&type=save";
    ajaxSubFormWithFun("hczdForm",url,function(data){
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
		jQuery.post("hczdgl_hczdwh.do?method=hczdDel",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}



