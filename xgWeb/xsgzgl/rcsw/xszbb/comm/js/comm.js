function displayHcDdAndSj(){
		var sfbbhcyhk = jQuery("#sfbbhcyhk").val();
		//补办优惠卡开启
		if (sfbbhcyhk == "是"){
			jQuery('.bbyhk_sjdd').css("display","");
		} else {
			//关闭
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