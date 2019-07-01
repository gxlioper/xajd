jQuery(function() {
	xsGkPic();	
});

//华中师范高考照片个性化
function xsGkPic(){
	 if("10511" != jQuery("#xxdm").val()) {
			jQuery("#stuGkImg").css("display", "none");
			}
		    else{
		    	jQuery("#stuGkImg").css("display", "");
			    }
	       
		    jQuery("#gkzpscbtn").css("display", "none");
}