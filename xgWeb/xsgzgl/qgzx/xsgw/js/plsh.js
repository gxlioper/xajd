function plsh(shzt){
	
	var shyj = jQuery("#shyj").val();
	if (shyj == ""){
		showAlert("����д��������");
		return false;
	}
	if (shyj.length>200){
		showAlert("���������ܳ���200��");
		return false;
	}
	
	var api = frameElement.api,W = api.opener;
	W.savePlsh(shzt,shyj);
	closeDialog();
	
//	var ids=jQuery("#ids").val();
//	var shgws=jQuery("#shgws").val();
//	var shyj=jQuery("#shyj").val();
//	var message=jQuery("#message").val();
//	jQuery.post("qgzx_xsgwsh.do?method=plsh",{ids:ids,shgws:shgws,shyj:shyj,shzt:shzt,message:message,type:'save'},function(data){
//		//testAlert(data);
//		var isHaveError=false;
//		var isBtg=false;
//		var cgts=data[data.length-1]["cgts"];
//		var message="<font color='green'>�ɹ����ͨ��["+cgts+"]������!</font></br>";
//		for(var i=0;i<data.length-1;i++){
//			message+=data[i]["message"];
//			message+="</br>";
//			if(data[i]["checktype"]=="btg"){
//				isBtg=true;
//			}
//			if(data[i]["checktype"]=="rowerror"){
//				isHaveError=true;
//			}
//		}
//		if(!isBtg){
//			if(isHaveError){//���������֤����
//				message+="���������������飡";
//			}else{
//				message="<font color='red'>"+message+"</font>";
//			}
//		}
//		alertMessage(message,function(){
//			var api = frameElement.api;var W=api.opener;
//			W.jQuery("#dataTable").reloadGrid();
//		});
//	},'json');
}