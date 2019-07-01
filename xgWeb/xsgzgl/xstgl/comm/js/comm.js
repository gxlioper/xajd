jQuery(function() {
	
	// =========== 根据菜单自动更新字段名称 begin =============
	var pWindow = getParentWindow();
	var gnmkmc = jQuery("#gnmkmc", pWindow.document).val();
	if(gnmkmc){
		jQuery("#gnmkmc_prompt_span").html(gnmkmc); // 子页面标题
	}
	// =========== 根据菜单自动更新字段名称 end =============
});
