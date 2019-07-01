function choosezcxm(obj,aa){
	
	if(obj.checked){
		jQuery("td[name='fs"+aa+"']").show();
		jQuery("td[name='pm"+aa+"']").show();
	}else{
		jQuery("td[name='fs"+aa+"']").hide();
		jQuery("td[name='pm"+aa+"']").hide();
	}
	
	
}

function tocheck(sqid,shid,gwid){
	
	showDialog("Ω±œÓ…Û∫À",700,500,"xpj_sqsh.do?method=viewJxsh&sqid="+sqid+"&shid="+shid+"&gwid="+gwid);
}

function plshtg(){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var bjdms = jQuery("#bjdms").val();
	showDialog("≈˙¡ø…Û∫À",700,300,"xpj_sqsh.do?method=toPlshy&xn="+xn+"&xq="+xq+"&bjdms="+bjdms);
}