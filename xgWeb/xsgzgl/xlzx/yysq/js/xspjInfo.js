function saveZxInfo(){
	if(jQuery("#xspj").val()==''){
		showAlert("��ѯ���۲���Ϊ�գ�");
		return false;
	}
	var parameter={yyid:$("yyid").value,xspj:jQuery("#xspj").val(),xspjzt:"2"};
		var url = "xlzx_zxyycl.do?method=updateXlzxInfo";
		jQuery.ajaxSetup({async:false});
			jQuery.post(url,parameter,function(data){
				if(data == true){
					showAlert("����ɹ���",{},{"clkFun":function(){
						frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
						iFClose();
				}});
				}else{
					showAlert("����ԤԼ��Ϣʧ�ܣ�",{},{"clkFun":function(){
					}});
				}
			},'json');
		jQuery.ajaxSetup({async:true});
}
