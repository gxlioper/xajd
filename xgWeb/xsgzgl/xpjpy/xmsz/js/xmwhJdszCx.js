jQuery(function() {
	onShow();
});

function onShow() {
	var url = "xpj_xmwh_jdsz.do?method=xmwhJdszSy";
	jQuery.post(url, {
		xmdm : jQuery("#xmdm").val()
	}, function(data) {
		var html = "";
		for ( var i = 0; i < data.length; i++) {
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
			sTr += "<td>无可选项目</td>";
			sTr += "</tr>";
			jQuery("#xmList").html(sTr);
			jQuery("#saveBtn").remove();
		}
		
		initSet();//勾选已选中的项目
	}, 'json');
	
	setSpzt();//根据审核状态进行设置
}

//根据审核状态进行设置
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
		jQuery("#saveBtn").remove();
	}
}


//勾选已选中的项目
function initSet(){	
	var url = "xpj_xmwh_jdsz.do?method=xmwhJdszCx&type=query";
	jQuery.post(url, {
		xmdm : jQuery("#xmdm").val()
	}, function(data) {
		for ( var i = 0; i < data.length; i++) {
			var o = data[i];
			jQuery("input:checkbox[name=xmdmView][value="+o.bjdxmdm+"]").attr("checked","checked");
		}
	}, 'json');
}

/*保存*/
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
	
	var url = "xpj_xmwh_jdsz.do?method=xmwhJdszXg&type=save";
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
