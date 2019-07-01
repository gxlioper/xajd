/*ÃÏÍþÍòËê¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ¹þ*/
/**
 * ¼ÓÔØ
 */
jQuery(function() {
	onShow();
});

/**
 * Õ¹ÏÖËùÓÐ¿ÉÒÔÉèÖÃ²»¿É¼æµÃµÄÏîÄ¿£¬ÒÑ¾­±»ÉêÇëµÄÏîÄ¿²»»á±»Õ¹Ê¾³öÀ´
 * @return
 */
function onShow(){
	var url = "xpjpy_xmwh_jdsz.do?method=xmwhJdszSy";
	jQuery.post(url,{xmdm:jQuery("#xmdm").val()},function(data){
		var html = "";
		
		for(var i = 0; i < data.length; i++) {
			var o = data[i];
			if(i%3 == 0){
				html += "<tr>";
			}
			html += "<td>";
			html += "<label>";
			html += "<input type='checkbox' value='"+o.xmdm+"' name='xmdmView'/>";
			html += o.xmmc;
			html += "</label>";
			html += "</td>";
			if((i+1)%3 == 0){
				html += "</tr>";
			}
		}
		
		var k = data.length ;
		if(k %3 > 0){
			while(k % 3 != 0){
				html += "<td>&nbsp;</td>";
				k++;
			}
			html += "</tr>";
		}
		if(data.length > 0){
			jQuery("#xmList").html(html);
		}else{
			var sTr = "<tr id='nodata' align='center'>";
			sTr += "<td>ÎÞ¿ÉÑ¡ÏîÄ¿</td>";
			sTr += "</tr>";
			jQuery("#xmList").html(sTr);
			jQuery("#saveBtn").remove();
		}
		/*¹´Ñ¡ÒÑÑ¡ÖÐµÄÏîÄ¿*/
		initSet();
	},'json');
	/*¸ù¾ÝÉóºË×´Ì¬½øÐÐÉèÖÃ*/
	setSpzt();
}


/**
 * ¹´Ñ¡ÒÑÑ¡ÖÐµÄÏîÄ¿
 * @return
 */
function initSet(){	
	var url = "xpjpy_xmwh_jdsz.do?method=xmwhJdszCx&type=query";
	jQuery.post(url, {xmdm:jQuery("#xmdm").val()}, function(data){
		for ( var i = 0; i < data.length; i++) {
			var o = data[i];
			jQuery("input:checkbox[name=xmdmView][value="+o.bjdxmdm+"]").attr("checked","checked");
		}
	}, 'json');
}

/**
 * ¸ù¾ÝÉóºË×´Ì¬½øÐÐÉèÖÃ
 * @return
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
		jQuery("#saveBtn").remove();
	}
}

/**
 * ±£´æ
 * @return
 */
function saveForm() {
	var xmdms = "";
	var flag = false;
	jQuery("input:checkbox[name=xmdmView]:checked").each(function(index){
		if(flag){
			xmdms += ",";
		}else{
			flag = true;
		}
		xmdms += jQuery(this).val();
	});
	
	var url = "xpjpy_xmwh_jdsz.do?method=xmwhJdszXg&type=save";
	url += "&xmdm=" + jQuery("#xmdm").val();
	url += "&xmdms=" + xmdms;
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"],{},{"clkFun": function(tag) {
			if (tag == "ok") {
				refershParent();
			}
		}});
	});	

}