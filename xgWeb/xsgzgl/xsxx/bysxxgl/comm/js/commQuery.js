jQuery(function() {
	xsGkPic();	
});

//����ʦ���߿���Ƭ���Ի�
function xsGkPic(){
	 if("10511" != jQuery("#xxdm").val()) {
			jQuery("#stuGkImg").css("display", "none");
			}
		    else{
		    	jQuery("#stuGkImg").css("display", "");
			    }
	       
		    jQuery("#gkzpscbtn").css("display", "none");
}