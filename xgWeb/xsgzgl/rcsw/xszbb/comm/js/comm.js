function displayHcDdAndSj(){
		var sfbbhcyhk = jQuery("#sfbbhcyhk").val();
		//�����Żݿ�����
		if (sfbbhcyhk == "��"){
			jQuery('.bbyhk_sjdd').css("display","");
		} else {
			//�ر�
			jQuery('.bbyhk_sjdd').css("display","none");
		}
	}

function changeHcyhk(){
	jQuery("input[name='sfbbhcyhk']").change(function(){
		 if("y"==jQuery(this).val()){
		 	jQuery(".bbyhk_sjdd").css("display","");
		 }else{
		 	jQuery(".bbyhk_sjdd").css("display","none");
		 	jQuery("#sj").val("");
		 	jQuery("#dd").val("");
		 }
		});
}