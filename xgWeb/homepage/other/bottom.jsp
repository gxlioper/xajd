<script type="text/javascript" defer="defer">

	var sumWidth = 0;
	var splitPositionNo = 0;
	var liDropDownArr = new Array();
	
	jQuery("li[class='dropdown']").each(function(){
		sumWidth += jQuery(this).width();
			if(sumWidth > jQuery('#menuNav').width()-180){//确定分界li序号
						liDropDownArr.push(jQuery(this));//备份li对象
						jQuery(this).remove();//删除原来的li对象
			}
	});
		
	if(liDropDownArr.length>0){

		for(var p=0;p<liDropDownArr.length;p++){
			jQuery('#dropdown-menu').append(liDropDownArr[p]);
		}
	} else {
		jQuery("#moreMenuLi").css("display","none");
	}
	
	function fdyUserChange(){
		var url='/xgxt/fdyUserChange.do';
		document.forms[0].action = url;
		document.forms[0].submit();
	}
</script>