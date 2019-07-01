function showTbody(obj,objTbody){
	if(obj.className=="up"){
		obj.className="down";
		obj.parentNode.parentNode.className="cur-tr";
		document.getElementById(objTbody).style.display="none";
		
	}else{
	
		var tbodys = jQuery('tbody');
		for(var i = 0 ; i < tbodys.length ; i++){
			var tbody = jQuery(tbodys[i]);
			var tName = tbody.attr('name');
			
			if (tName!='xsxx' && tName!='xyxx' && tName!='shxx'){
				tbody.attr('style','display:none');
			}
		}
		
		jQuery('a.up').attr('class','down');
		obj.className="up";
		obj.parentNode.parentNode.className="";
		document.getElementById(objTbody).style.display="";
	}
}

function setDb(obj,cz){
	jQuery('input[type=radio][name='+cz+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+cz+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
}

function setDq(obj,obj2,cz){
	
	var flg = jQuery(obj).val()=='1' || jQuery('input[name='+obj2+']:checked').val()=='1';
	
	jQuery('input[type=radio][name='+cz+']').eq(0).attr('disabled',flg);
	jQuery('input[type=radio][name='+cz+']').eq(1).attr('disabled',flg);
}

function setQm(obj,qka,qkb,qkc){
	jQuery('input[type=radio][name='+qka+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qka+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name='+qkb+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qkb+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name='+qkc+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qkc+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
}
//第五部分，父母都是残疾人
function setQm2(obj,qka,qkb,qkc,qkd,qke,qkf){
	jQuery('input[type=radio][name='+qka+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qka+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name='+qkb+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qkb+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name='+qkc+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qkc+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name='+qkd+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qkd+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name='+qke+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qke+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name='+qkf+']').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name='+qkf+']').eq(1).attr('disabled',jQuery(obj).val()=='1');
}
//第七部分（赡养老人情况），如赡养老人数为0，则后两个选项都不能选
function isZero(obj){
	
	jQuery('input[type=radio][name=lrsth]').eq(0).attr('disabled',jQuery(obj).val()=='0');
	jQuery('input[type=radio][name=lrsth]').eq(1).attr('disabled',jQuery(obj).val()=='0');
	
	jQuery('input[type=radio][name=lrstc]').eq(0).attr('disabled',jQuery(obj).val()=='0');
	jQuery('input[type=radio][name=lrstc]').eq(1).attr('disabled',jQuery(obj).val()=='0');
	
}

function setDqfb(arr1,arr2){
	
	var flg = false;
	
	for (var i = 0 ; i < arr1.length ; i++){
			flg = jQuery('input[type=radio][name='+arr1[i]+'][value=1]').attr('checked');
		
		if (flg){
			for (var j = 0 ; j < arr2.length ; j++){
				jQuery('input[type=radio][name='+arr2[j]+']').eq(0).attr('disabled',true);
				jQuery('input[type=radio][name='+arr2[j]+']').eq(1).attr('disabled',true);
			}
			break;
		}
	}
	
	if (!flg){
		for (var j = 0 ; j < arr2.length ; j++){
			jQuery('input[type=radio][name='+arr2[j]+']').eq(0).attr('disabled',false);
			jQuery('input[type=radio][name='+arr2[j]+']').eq(1).attr('disabled',false);
		}
	}
}


//第三部分(家庭信息-基本情况)，“双亲家庭”
function checkDqxx(obj){
	
	if (jQuery(obj).attr('checked')){
		var radios = jQuery('input[type=radio]',jQuery('#myTbody3'));
		var fmRadios = jQuery('input[type=radio]',jQuery('#myTbody4'));
		var fmstRadios = jQuery('input[type=radio]',jQuery('#myTbody5'));
		
		for (var i = 0 ; i < radios.length ; i++){
			var name = jQuery(radios[i]).attr('name');
			
			//if (name != 'fyfqt' && name != 'sqjt'){
			if (name != 'sqjt' ){
				jQuery(radios[i]).attr('disabled',jQuery(obj).val()=='1');
			}
			
			
		}
		//1为选中状态，0为disabled状态
		//alert(jQuery(obj).val());
		for (var i = 0 ; i < fmRadios.length ; i++){
			var name = jQuery(fmRadios[i]).attr('name');
			if (name == 'ywgzqk' ){
				if(jQuery(obj).val()=="1"){
					jQuery(fmRadios[i]).attr('disabled',true);
				}else{
					jQuery(fmRadios[i]).attr('disabled',false);	
				}
			}else{
				if(jQuery(obj).val()=="1"){
					jQuery(fmRadios[i]).attr('disabled',false);
				}else{
					jQuery(fmRadios[i]).attr('disabled',true);
				}
			}
		}
		
		
		for (var i = 0 ; i < fmstRadios.length ; i++){
			
			var name = jQuery(fmstRadios[i]).attr('name');
			var sqfmArr = ['fmstlh','fqstcpc','mqstpc','fqstjc','mqstjc','fmcj','fcj','mcj'];
			
			if (jQuery.inArray(name,sqfmArr) > -1){
				jQuery(fmstRadios[i]).attr('disabled',jQuery(obj).val()=='0');
			} else {
				jQuery(fmstRadios[i]).attr('disabled',jQuery(obj).val()=='1');
			}
		}
	}
}
//第三部分(家庭信息-基本情况)，除了“双亲家庭”
function checkDqfyxx(obj){
	
	var radios = jQuery('input[type=radio]',jQuery('#myTbody3'));
	var fmRadios = jQuery('input[type=radio]',jQuery('#myTbody4'));
	
	for (var i = 0 ; i < radios.length ; i++){
		if (jQuery(obj).attr('name') != jQuery(radios[i]).attr('name') ){
			jQuery(radios[i]).attr('disabled',jQuery(obj).val()=='1');

		}
	}
	
	//1为选中状态，0为disabled状态
	//alert(jQuery(obj).val());
	for (var i = 0 ; i < fmRadios.length ; i++){
		var name = jQuery(fmRadios[i]).attr('name');
		if (name == 'ywgzqk' ){
			if(jQuery(obj).val()=="1"){
				jQuery(fmRadios[i]).attr('disabled',false);
			}else{
				jQuery(fmRadios[i]).attr('disabled',true);	
			}
		}else{
			if(jQuery(obj).val()=="1"){
				jQuery(fmRadios[i]).attr('disabled',true);
			}else{
				jQuery(fmRadios[i]).attr('disabled',false);
			}
		}
	}
}
//第三部分(家庭信息-基本情况)，“抚养费其他”
function checkFyfqt(obj){
	
	if (jQuery(obj).attr('checked')){
		var radios = jQuery('input[type=radio]',jQuery('#myTbody3'));
		var fmRadios = jQuery('input[type=radio]',jQuery('#myTbody4'));
		
		for (var i = 0 ; i < radios.length ; i++){
			var name = jQuery(radios[i]).attr('name');
			
			//if (name != 'fyfqt' && name != 'sqjt'){
			if (name != 'fyfqt'){
				jQuery(radios[i]).attr('disabled',jQuery(obj).val()=='1');
			}
		}
		
		for (var i = 0 ; i < fmRadios.length ; i++){
			var name = jQuery(fmRadios[i]).attr('name');
			
			if (name != 'jtjjqt'&& name != 'fyfqt'){
				jQuery(fmRadios[i]).attr('disabled',jQuery(obj).val()=='1');
			}else{
				jQuery(fmRadios[i]).attr('disabled',jQuery(obj).val()=='0');
			}
		}
	
	}
}
function setFmgz(obj){
	var fmgz = jQuery('input[type=radio]',jQuery('#myTbody4'));
	
	for (var i = 0 ; i < fmgz.length ; i++){
		if (jQuery(obj).attr('name') != jQuery(fmgz[i]).attr('name') && jQuery(fmgz[i]).attr('name') != 'jtjjqt' && jQuery(fmgz[i]).attr('name') != 'ywgzqk'){
		//if (jQuery(obj).attr('name') != jQuery(fmgz[i]).attr('name') ){
			jQuery(fmgz[i]).attr('disabled',jQuery(obj).val()=='1');
		}
	}
}


function setFmst(obj){
	jQuery('input[type=radio][name=fqstcpc]').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=mqstpc]').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=fqstjc]').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=mqstjc]').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=fmcj]').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=fcj]').eq(0).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=mcj]').eq(0).attr('disabled',jQuery(obj).val()=='1');
	
	jQuery('input[type=radio][name=fqstcpc]').eq(1).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=mqstpc]').eq(1).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=fqstjc]').eq(1).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=mqstjc]').eq(1).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=fmcj]').eq(1).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=fcj]').eq(1).attr('disabled',jQuery(obj).val()=='1');
	jQuery('input[type=radio][name=mcj]').eq(1).attr('disabled',jQuery(obj).val()=='1');
	
	var disabled = jQuery('input[name=fmcj]:checked').val()=='1';
		//jQuery('input[name=fcj]').attr('disabled',disabled);
		//jQuery('input[name=mcj]').attr('disabled',disabled);
}