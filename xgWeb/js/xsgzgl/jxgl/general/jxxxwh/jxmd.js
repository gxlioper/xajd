function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

//��ѯ
function searchRs(){
	var url = "jxgl_jxxxwh_ajax.do?method=jxmdCx&pkValue="+jQuery("#pkValue").val();
	var ie = "10.0";
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}



//��������
function jxmdsc(){
	getTJxrs();
	tipsWindown("��ѵ��������","id:tempDiv","450","350","true","","true","id");
}

//��þ�ѵ����
function getTJxrs(){
	jQuery.ajaxSetup({async:false});
	var array = jQuery("[name=cjnj]:checked");
	var str = "";
	var sfhx = jQuery("input[name=sfhx]:checked").val();
	var sfmx = jQuery("input[name=sfmx]:checked").val();
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).val();
		str += pkValue+"!!@@!!";
	}
	var parameter={};
	parameter["jxid"]=escape(jQuery("#pkValue").val());
	parameter["cjnj"]=escape(str);
	parameter["sfhx"]=escape(jQuery("input[name=sfhx]:checked").val());
	parameter["sfmx"]=escape(jQuery("input[name=sfmx]:checked").val());
	jQuery.ajaxSetup({cache:false});
	url = "jxgl_jxxxwh_ajax.do?method=getJxrs";
	jQuery.getJSON(url,parameter,function(data){
		if(data != null){
			jQuery('#njrs').text(data.njrs+"��");
			jQuery('#hxrs').text(data.hxrs+"��");
			jQuery('#mxrs').text(data.mxrs+"��");
			if("0"==data.njrs && "0"==data.hxrs && "0"==data.mxrs){
				jQuery("#bcBtn").hide();
			}else{
				jQuery("#bcBtn").show();
			}
			jQuery('#cjrs').text(data.cjrs+"��");
		}
	});
	jQuery.ajaxSetup({async:true});
}


//��þ�ѵ����
function getJxrs(){
	var array = jQuery("[name=cjnj]:checked");
	var str = "";
	var sfhx = jQuery("[name=sfhx]:checked").val();
	var sfmx = jQuery("[name=sfmx]:checked").val();
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).val();
		str += pkValue+"!!@@!!";
	}
	var parameter={};
	parameter["jxid"]=escape(jQuery("#pkValue").val());
	parameter["cjnj"]=escape(str);
	parameter["sfhx"]=escape(jQuery("[name=sfhx]:checked").val());
	parameter["sfmx"]=escape(jQuery("[name=sfmx]:checked").val());
	jQuery.ajaxSetup({cache:false});
	jQuery.ajaxSetup({async:false});
	url = "jxgl_jxxxwh_ajax.do?method=getJxrs";
	jQuery.getJSON(url,parameter,function(data){
		if(data != null){
			jQuery('#njrs').text(data.njrs+"��");
			jQuery('#hxrs').text(data.hxrs+"��");
			jQuery('#mxrs').text(data.mxrs+"��");
			if("0"==data.njrs && "0"==data.hxrs && "0"==data.mxrs){
				jQuery("#bcBtn").hide();
			}else{
				jQuery("#bcBtn").show();
			}
			jQuery('#cjrs').text(data.cjrs+"��");
		}
	});
	jQuery.ajaxSetup({async:true});
}


function jxmdDivSave(){
	var len = jQuery("[name=cjnj]:checked").length;
	if(len>=1){
		var array = jQuery("[name=cjnj]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).val();
			str += pkValue+"!!@@!!";
		}
		var parameter={};
		parameter["jxid"]=escape(jQuery("#pkValue").val());
		parameter["cjnj"]=escape(str);
		parameter["sfhx"]=escape(jQuery("[name=sfhx]:checked").val());
		parameter["sfmx"]=escape(jQuery("[name=sfmx]:checked").val());
		jQuery.ajaxSetup({async:false});
		url = "jxgl_jxxxwh_ajax.do?method=scJxmd";
		jQuery.post(url,
				parameter,
				function(result){
						alertInfo(result);
						closeWindown();
						searchRs();
				}
			);
		jQuery.ajaxSetup({async:true});
	}else{
		alertInfo("�����ٹ�ѡһ���꼶���б��棡");
		return false;
	}
}

//ɾ������ѵ����ѵ����ѵ
function jxmdModi(type){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		
		
		var flag = checkScJxmd();
		if(!flag){
			alertInfo("ѧ���������ھ�ѵ������ʹ�ã������ٴ�ɾ���������Ϣ!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		
		if("sc"==type){
			confirmInfo("�Ƿ�ȷ��ȡ����ѡѧ���ľ�ѵ�ʸ�",function(tag){
				if(tag=="ok"){
					var parameter={}
					var url="jxgl_jxxxwh_ajax.do?method=jxmdSc";	
					parameter["pkValue"]=str;							
					jQuery.ajaxSetup({async:false});	
					jQuery.post(url,
						parameter,
						function(result){
								//alertInfo(result,function(tag){
								//	if(tag=="ok"){
								//		searchRs();
								//	}
								//});
							alertInfo(result);
							searchRs();
						}
					);
					jQuery.ajaxSetup({async:true});
				}
			});
		}else if("hx"==type || "mx"==type || "cx"==type){
			var parameter={}
			var url="jxgl_jxxxwh_ajax.do?method=cxqkCz";	
			parameter["pkValue"]=str;
			parameter["cxqk"]=type;
			jQuery.ajaxSetup({async:false});	
			jQuery.post(url,
				parameter,
				function(result){
						alertInfo(result);
						searchRs();
				}
			);
			jQuery.ajaxSetup({async:true});
		}
	}else{
		alertInfo("�빴ѡ��Ҫ���������ݣ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}

//ɾ������ѵ����ѵ
function jxxx(cxz) {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len == 1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var flag = checkScJxmd();
		if(!flag){
			alertInfo("ѧ���������ھ�ѵ������ʹ�ã������ٴ�ɾ���������Ϣ!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}else{
		alertInfo("�빴ѡһ����Ҫ���������ݣ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var pk = jQuery("#pkValue").val();
	var xh = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(1).text();
	var url = "jxgl_jxxxwh.do?method=jxxx&xh="+xh+"&jxid="+pk+"&cxz="+cxz;
	if("hx" == cxz) {
		var title = "��ѵ��Ϣ";
	}else if("mx" == cxz) {
		var title = "��ѵ��Ϣ";
	}else if("sc" == cxz) {
		var title = "ȡ����ѵ��Ϣ";
	}
	showDialog(title, 800, 508, url);
}

// �鿴
function viewJxxx() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len != 1){
		alertInfo("�빴ѡһ����Ҫ�鿴�����ݣ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var cxqk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
	var pk = jQuery("#pkValue").val();
	var xh = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(1).text();
	var url = "jxgl_jxxxwh.do?method=viewJxxx&xh="+xh+"&jxid="+pk+"&cxqk="+cxqk;
	var title = "��ѵ��Ϣ";
	showDialog(title, 800, 508, url);
	
}



//��֤ɾ����Ϣ
function checkScJxmd(){
	var flag = false;
	jQuery.ajaxSetup({async:false});
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+"!!@@!!";
	}
	var parameter={}
	var url="jxgl_jxxxwh_ajax.do?method=checkScJxmd";	
	parameter["pkValue"]=str;	
	jQuery.post(url,parameter,
		function(result){
			if("true"==result){
				flag = true;
			}
		});
	jQuery.ajaxSetup({async:true});
	return flag;
}

//����
function exportConfig(){
	var DCCLBH='jxgl_jxxxwh.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='jxgl_jxxxwh.do';
	setSearchTj();//���ø߼���ѯ����
	var url = "jxgl_jxxxwh.do?method=exportData&dcclbh=" + DCCLBH+
	        "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


// ��ѵ
function printHx() {
	var len = jQuery("[name=div_pkValue]:checked").length;
	var cxqk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
	var pk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+",";
	}
	if (len == 1) {
		if("��ѵ" == cxqk) {
			var url = "jxgl_jxxxwh.do?method=printHx";
			url += "&pkValue=" + pk;
			window.open(url);
		}else {
			showAlertDivLayer("��ѡ��ѵ��¼���أ�");
			return false;
		}
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		for (var i=0;i<array.length;i++) {
			if("��ѵ" != jQuery(array[i]).parent().parent().find("td").eq(7).text()){
				showAlertDivLayer("��ѡ��ѵ��¼���أ�");
				return false;		
			}	
		}
		var url = "jxgl_jxxxwh.do?method=getHxbZip";
		url += "&value=" + str;
		window.open(url);
	}
}


// ��ѵ
function printMx() {
	var len = jQuery("[name=div_pkValue]:checked").length;
	var cxqk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(7).text();
	var pk = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+",";
	}
	if (len == 1) {
		if("��ѵ" == cxqk) {
			var url = "jxgl_jxxxwh.do?method=printMx";
			url += "&pkValue=" + pk;
			window.open(url);
		}else {
			showAlertDivLayer("��ѡ����ѵ��¼���أ�");
			return false;
		}
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		for (var i=0;i<array.length;i++) {
			if("��ѵ" != jQuery(array[i]).parent().parent().find("td").eq(7).text()){
				showAlertDivLayer("��ѡ����ѵ��¼���أ�");
				return false;		
			}	
		}
		var url = "jxgl_jxxxwh.do?method=getMxbZip";
		url += "&value=" + str;
		window.open(url);
	}
}


/**
 * ����ģ̬���ڣ��滻ԭ����showTopWin
 * 
 * @param width
 * @param height
 * @param url
 * @param t
 * @param ��������
 */
function showDialog(t,width,height,url,other){
	var title = t||"ѧ����������ϵͳ";
	var setting = {
		title:title,
		width:width,
		height:height,
		lock:true,
		fixed:true,
		focus:true,
		min:false,
		zIndex: 1976,
		content:'url:'+url
		
	};
	var params = jQuery.extend(setting, other || {});
	if(frameElement&& frameElement.api){
		var api = frameElement.api;
		var W = api.opener;
		params["id"] = "childDialog";
		params["parent"] = api;
		params["zIndex"] = 1977;
		return W.lhgdialog(params);
	}else{
		params["id"] = "parentDialog";
		params["parent"] = window;
		return lhgdialog(params);
	}
	
	
}
/**
 * �ر�ģ̬����
 */
function closeDialog(){
	var api = frameElement.api; 
	api.close();
}


/**
 * ���������
 */
function showAlertDivLayer() {
	var argumentsArr = Array.prototype.slice.call(arguments);
	if(argumentsArr[0] == null) return;
	
	var clickFun = null;
	
	if (argumentsArr.length == 3){
		clickFun = argumentsArr[2]["clkFun"];
	}
	ymPrompt.alert({
		title:"ϵͳ��ʾ",
		useSlide:true,
		maskAlphaColor:"#FFFFFF",
		maskAlpha:0.3,
		message:argumentsArr[0],
		width:340,
		winPos:[180,160],
		height:150,
		// showMask:false,
		handler:clickFun
	});
	// setTimeout(function(){ymPrompt.doHandler();},3000);
}

