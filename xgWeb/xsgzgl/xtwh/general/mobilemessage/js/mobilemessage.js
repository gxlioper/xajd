var ids = "jsrxm-fsnr";
function textFormatter(cellValue,rowObject){
	
	if(null!=cellValue&&cellValue.length>20){
		cellValue=cellValue.substr(0,20)+"...";
	}
	return cellValue;
}

//��Ϣ����
function saveForm(){
	if(!checkNotNull(ids)){
		showAlert("�뽫��"+"<font color='red'>*</font>"+"��������д������");
		return false;
	}
	if(jQuery("#fsnr").length > 200){
		showAlert("���������������200�ַ�֮�ڣ�");
		return false;
	}
	var url = "xtwh_mobileMsg.do?method=saveSendMsg";
	ajaxSubFormWithFun("mobileMessageForm", url, function(data) {
		
		 if(data["message"]=="���ͳɹ�"){
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

function viewMsg(){
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length!=1){
		showAlert("��ѡ��һ����¼��");
		return false;
	}
	var url="xtwh_mobileMsg.do?method=mobileMsgView&id="+ids;
	showDialog('��������鿴',500,350,url);
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}