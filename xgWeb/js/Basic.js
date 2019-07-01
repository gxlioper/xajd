function searchGo(url,parameter){

	var currentPage = "1";
	if($("currentPage")){
		currentPage = jQuery("#currentPage").val();
	}
	
	var editPageSize = "";
	if($("editPageSize")){
		editPageSize = jQuery("#editPageSize").val();	
	}
		
	var pagesize = "";
	if($("pagesize")){
		pagesize = jQuery("#pagesize").val();
	}
	parameter["currentPage"]=currentPage;
	parameter["editPageSize"]=editPageSize;
	parameter["pagesize"]=pagesize;

	jQuery("#divWaiting").css("display","");
	jQuery("#divDisable").css("display","");
	
	jQuery("#div_rs").load(url,parameter,function(){
		setTimeout("setPageInfo()",500);
		jQuery("#divWaiting").css("display","none");
		jQuery("#divDisable").css("display","none");
	});
}