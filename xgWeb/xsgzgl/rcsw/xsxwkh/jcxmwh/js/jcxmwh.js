var ids="mc-fz";
jQuery(function(){
	jQuery("#dataTable").initGrid(gridSetting);
});
function query(){
	var map = {};
	map["mc"] = jQuery("#mc").val();
	jQuery("#dataTable").reloadGrid(map);
}
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xsxwkhJcxmwh.do?method=delJcxm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
	}
}
//��������
function saveForm(){
	if(checkNull(ids)&&checkTextAreaLength("bz","��ע","500")){
    var url = "xsxwkhJcxmwh.do?method=save";
     ajaxSubFormWithFun("xsxwJcxmwhForm",url,function(data){
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
 }
//���±���
function saveUpdateForm(){
	if(checkNull(ids)&&checkTextAreaLength("bz","��ע","500")){
    var url = "xsxwkhJcxmwh.do?method=update";
     ajaxSubFormWithFun("xsxwJcxmwhForm",url,function(data){
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
 }