jQuery(function() {
	
	// =========== ���ݲ˵��Զ������ֶ����� begin =============
	var pWindow = getParentWindow();
	var gnmkmc = jQuery("#gnmkmc", pWindow.document).val();
	if(gnmkmc){
		jQuery("#gnmkmc_prompt_span").html(gnmkmc); // ��ҳ�����
	}
	// =========== ���ݲ˵��Զ������ֶ����� end =============
});
