jQuery(document).ready(function(){ 
	var sxzd = jQuery("#sxzd").val();
	if(sxzd=="je"){
		jQuery("#font_sxsz").text("Ԫ(ѧ��ÿ�³�𲻵ó�����ֵ)");
	}else if(sxzd=="gs"){
		jQuery("#font_sxsz").text("Сʱ(ѧ��ÿ�¹�ʱ���ó�����ֵ)");
	}else{
		jQuery("#font_sxsz").text();
	}
	changeGwsq();
	changexsGwsq();
	jQuery("[name=xsgwsqsplc]").change(function() {// �������Ƽ��𣬼�ÿ��Ƽ��𣬸���������̽�����ʾ
		setKzlc(jQuery(this).val()); 
	});
	jQuery("[name=xsgwsqsplc]").change(function() {
		setYjKzlc(jQuery(this).val()); 
	});
	//��ȡ��ǰҪѡ�е�
	jQuery("[name=xsgwsqsplc]").change();
	jQuery("[name=xsyjgwsqsplc]").change();
	changeSqkg();
});
//���ü����������ݣ�������������������򲻿ɸ���
function checkSplc(){
	var splc=jQuery("#xsgwsqsplc").val();
	jQuery.ajax({
	   type: "POST",
	   url: "qgzx_xsgwsh.do?method=checkSplc",
	   data: {splc:splc,type:"1"},
	   dataType:"json",
	   success: function(data){
		   if(data["message"]=="true"){
				jQuery("#xsgwsqsplc").attr("disabled","disabled");
				jQuery("input[name=rskzjbView]").each(function(){
					jQuery(this).attr("disabled","disabled");
				});
		   }else{
				jQuery("#xsgwsqsplc").removeAttr("disabled");
				jQuery("input[name=rskzjbView]").each(function(){
					jQuery(this).removeAttr("disabled");
				});
		   }
	   }
	});
}

function checkSplcYj(){
	var splc=jQuery("#xsyjgwsqsplc").val();
	jQuery.ajax({
	   type: "POST",
	   url: "qgzx_xsgwsh.do?method=checkSplc",
	   data: {splc:splc,type:"5"},
	   dataType:"json",
	   success: function(data){
		   if(data["message"]=="true"){
				jQuery("#xsyjgwsqsplc").attr("disabled","disabled");
				jQuery("input[name=yjrskzjbView]").each(function(){
					jQuery(this).attr("disabled","disabled");
				});
		   }else{
				jQuery("#xsyjgwsqsplc").removeAttr("disabled");
				jQuery("input[name=yjrskzjbView]").each(function(){
					jQuery(this).removeAttr("disabled");
				});
		   }
	   }
	});
}
function setKzlc(value) {
	if (value == "") {
		jQuery("#rskzjbTd").html("");
		jQuery("#qxfwTd").html("");
		return;
	}
	var url = "qgzx_xsgwsh.do?method=loadRskz";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var sHtml = "";
		var radio1 = "";
		if (data != null) {
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}
			//�������ݷ�Χ
			setSjfw(data);
		}
		sHtml += radio1;
		jQuery("#rskzjbTd").html(sHtml);
		//��Ӽ�����ȡѡ�е�ֵ
		addRskzJt();
		var isH=false;
		var rskzjb=jQuery("#rskzjb").val();
			//���ñ���ĵ�ǰֵ
			jQuery("input[name=rskzjbView]").each(function(){
				var v=jQuery(this).val();
				if(v==rskzjb){
					jQuery(this).attr("checked","checked");
					isH=true;
					return false;
				}
			});
		if(!isH){
			//Ĭ�����һ��ѡ��
			var last=jQuery("input[name=rskzjbView]").last();
			last.attr("checked","checked");
			jQuery("#rskzjb").val(last.val());
		}
		checkSplc();
		//jQuery().atrr("checked","checked");
	}, 'json');
}
function setYjKzlc(value) {
	if (value == "") {
		jQuery("#yjqxfwTd").html("");
		return;
	}
	var url = "qgzx_xsgwsh.do?method=loadRskz";
	jQuery.post(url, {
		value : value
	}, function(data) {
		// var sHtml = "";
		// var radio1 = "";
		if (data != null) {
			/*for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='yjrskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}*/
			//�������ݷ�Χ
			setYjSjfw(data);
		}
		// sHtml += radio1;
		// jQuery("#yjrskzjbTd").html(sHtml);
		//��Ӽ�����ȡѡ�е�ֵ
		// addRskzJtYj();
		/*var isH=false;
		var rskzjb=jQuery("#yjrskzjb").val();
			//���ñ���ĵ�ǰֵ
			jQuery("input[name=yjrskzjbView]").each(function(){
				var v=jQuery(this).val();
				if(v==rskzjb){
					jQuery(this).attr("checked","checked");
					isH=true;
					return false;
				}
			});
		if(!isH){
			//Ĭ�����һ��ѡ��
			var last=jQuery("input[name=yjrskzjbView]").last();
			last.attr("checked","checked");
			jQuery("#yjrskzjb").val(last.val());
		}
		checkSplcYj();*/
		//jQuery().atrr("checked","checked");
	}, 'json');
}

function setSjfw(data){
	var check="";
	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		check+="<label><input type=\"checkbox\" name=\"qxfwkz\" value='";
		check+=o.spgwdm+"'/>";
		check+=o.spgwmc;
		check+="</label>";
		if (i != data.length - 1) {
			check += "<br/>";
		}
	}
	jQuery("#qxfwTd").html(check);
	var isH=false;
    jQuery("input[name=qxfwkz]").each(function(){
    	var V=jQuery(this).val();
    	if(isHaveV(V)){
    		jQuery(this).attr("checked","checked");
    		isH=true;
    	}
    });
/*	if(!isH){
		//Ĭ��ȫ��ѡ��
		jQuery("input[name=qxfwkz]").eq(0).attr("checked","checked");
	}*/
	//����Ȩ�޼���ֻ��ѡ��һ��
	jQuery("[name=qxfwkz]").bind("click",function(){
		var selectV=jQuery(this).val();
		jQuery("[name=qxfwkz]:checked").each(function(){
			var sv=jQuery(this).val();
			if(sv!=selectV){
				jQuery(this).removeAttr("checked");
			}
		});
	});
}
function setYjSjfw(data){

	var check="";
	for ( var i = 0; i < data.length; i++) {
		var o = data[i];
		check+="<label><input type=\"checkbox\" name=\"yjqxfwkz\" value='";
		check+=o.spgwdm+"'/>";
		check+=o.spgwmc;
		check+="</label>";
		if (i != data.length - 1) {
			check += "<br/>";
		}
	}
	jQuery("#yjqxfwTd").html(check);
	// var isH=false;
    jQuery("input[name=yjqxfwkz]").each(function(){
    	var V=jQuery(this).val();
    	if(isHaveVYj(V)){
    		jQuery(this).attr("checked","checked");
    		// isH=true;
    	}
    });
/*	if(!isH){
		//Ĭ��ȫ��ѡ��
		jQuery("input[name=qxfwkz]").eq(0).attr("checked","checked");
	}*/
	//����Ȩ�޼���ֻ��ѡ��һ��
	jQuery("[name=yjqxfwkz]").bind("click",function(){
		var selectV=jQuery(this).val();
		jQuery("[name=yjqxfwkz]:checked").each(function(){
			var sv=jQuery(this).val();
			if(sv!=selectV){
				jQuery(this).removeAttr("checked");
			}
		});
	});
}
function isHaveV(V){
	var qxfw=jQuery("#qxfw").val();
	var qxfws=qxfw.split(",");
	for(var i=0;i<qxfws.length;i++){
		if(V==qxfws[i]){
			return true;
		}
	}
	return false;
}
function isHaveVYj(V){
	var qxfw=jQuery("#yjqxfw").val();
	var qxfws=qxfw.split(",");
	for(var i=0;i<qxfws.length;i++){
		if(V==qxfws[i]){
			return true;
		}
	}
	return false;
}

function addRskzJt(){
	jQuery("[name=rskzjbView]").bind("click",function(){
		var v=jQuery(this).val();
		jQuery("#rskzjb").val(v);
	});
}

function addRskzJtYj(){
	jQuery("[name=yjrskzjbView]").bind("click",function(){
		var v=jQuery(this).val();
		jQuery("#yjrskzjb").val(v);
	});
}


function changKssj(value){
	if(value==""){
		$("kssj").focus();
		jQuery("span[id=cjffError]").text("��𷢷ſ���ʱ�䲻��Ϊ��!");
 		return false;
	}
	if(Number(value)>Number(jQuery("#jssj").val())){
		$("kssj").focus();
		jQuery("span[id=cjffError]").text("ʱ�����䲻��ȷ!");
		return false;
	}else{
		jQuery("span[id=cjffError]").text("");
	}
}

function changJssj(value){
	if(value==""){
		$("jssj").focus();
		jQuery("span[id=cjffError]").text("��𷢷Ž���ʱ�䲻��Ϊ��!");
 		return false;
	}
	if(Number(value)<Number(jQuery("#kssj").val())){
		$("jssj").focus();
		jQuery("span[id=cjffError]").text("ʱ�����䲻��ȷ!");
 		return false;
	}else{
		jQuery("span[id=cjffError]").text("");
	}
}

//��֤��𷢷Ų���
function yz_cjffcs(){
	/*if($("sxsz") && $("sxsz").value==""){
 		alertInfo("�������޲���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("sxsz").focus();
 				return false;
 			}
 		});
 		return false;
	}*/
	
	/*if($("ksyf") && $("ksyf").value==""){
 		alertInfo("��𷢷��·��趨����Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("ksyf").focus();
 				return false;
 			}
 		});
 		return false;
	}*/
	
	if($("kssj") && $("kssj").value==""){
 		alertInfo("��𷢷ſ���ʱ�䲻��Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("jssj") && $("jssj").value==""){
 		alertInfo("��𷢷ſ���ʱ�䲻��Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("jssj").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("yrdwgwsqxn") && $("yrdwgwsqxn").value==""){
 		alertInfo("���˵�λ��λ����ѧ�겻��Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("yrdwgwsqxn").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("yrdwsplc") && $("yrdwsplc").value==""){
		alertInfo("���˵�λ�������̲���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("yrdwgwsqxn").focus();
 				return false;
 			}
 		});
 		return false;
	}

	if(jQuery("#sfsdgwcjsx").val() == "yes"){
		if(parseFloat($("cjbz").value)>parseFloat($("gwzgcjsx").value)){
			alertInfo("����׼���ܸ��ڸ�λ��߳������!",function(tag){
	 			if(tag=="ok"){
	 				$("cjbz").focus();
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}

	if(Number($("kssj").value) > Number($("jssj").value)){
 		alertInfo("��𷢷ſ�ʼʱ�䲻�ܴ��ڽ���ʱ��!",function(tag){
 			if(tag=="ok"){
 				$("jssj").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if(($("jsyf").value !="") && $("ksyf").value > $("jsyf").value){
 		alertInfo("��𷢷ſ��ſ�ʼ�·ݲ��ܴ��ڽ����·�!",function(tag){
 			if(tag=="ok"){
 				$("jsyf").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	return true;
}
//��֤���˵�λ����
function yz_yrdwcs(){

	if($("xsgws") && $("xsgws").value==""){
 		alertInfo("ѧ����λ������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("xsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("[name=sfkfgwsq]:checked").length==0){
		alertInfo("��λ���ز���Ϊ��!");
		return false;
	}
	//2014.7.4 ͳһȡ������ʱ�������֤
//	if(("on"==jQuery("[name=sfkfgwsq]:checked").val()) && $("gwsqkssj") && $("gwsqkssj").value=="" ){
// 		alertInfo("��λ���뿪ʼʱ�䲻��Ϊ��!",function(tag){
// 			if(tag=="ok"){
// 				$("gwsqkssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
//	
//	if(("on"==jQuery("[name=sfkfgwsq]:checked").val()) && $("gwsqjssj") && $("gwsqjssj").value=="" ){
// 		alertInfo("��λ�������ʱ�䲻��Ϊ��!",function(tag){
// 			if(tag=="ok"){
// 				$("gwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
//	
//	if(("on"==jQuery("[name=sfkfgwsq]:checked").val()) && $("gwsqkssj").value > $("gwsqjssj").value){
// 		alertInfo("��λ���뿪ʼʱ�䲻�ܴ��ڽ���ʱ��!",function(tag){
// 			if(tag=="ok"){
// 				$("gwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}

	if("off"==jQuery("[name=sfkfgwsq]:checked").val()){
		jQuery("#gwsqkssj").val("");
		jQuery("#gwsqjssj").val("");
	}
	return true;
	
}
//��֤��ѧ����λ����
function yz_xsgw(){
	if($("xsxsgws") && $("xsxsgws").value==""){
 		alertInfo("ѧ���������λ������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("xsxsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("[name=sfkfxsgwsq]:checked").length==0){
		alertInfo("��ѡ���Ƿ񿪷�ѧ����λ����!");
		return false;
	}
//	if(("on"==jQuery("[name=sfkfxsgwsq]:checked").val()) && $("xsgwsqkssj") && $("xsgwsqkssj").value=="" ){
// 		alertInfo("ѧ����λ���뿪ʼʱ�䲻��Ϊ��!",function(tag){
// 			if(tag=="ok"){
// 				$("xsgwsqkssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
//	
//	if(("on"==jQuery("[name=sfkfxsgwsq]:checked").val()) && $("xsgwsqjssj") && $("xsgwsqjssj").value=="" ){
// 		alertInfo("ѧ����λ�������ʱ�䲻��Ϊ��!",function(tag){
// 			if(tag=="ok"){
// 				$("xsgwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
	
//	if(("on"==jQuery("[name=sfkfxsgwsq]:checked").val()) && $("xsgwsqkssj").value > $("xsgwsqjssj").value){
// 		alertInfo("��λ���뿪ʼʱ�䲻�ܴ��ڽ���ʱ��!",function(tag){
// 			if(tag=="ok"){
// 				$("xsgwsqjssj").focus();
// 				return false;
// 			}
// 		});
// 		return false;
//	}
	
	if("off"==jQuery("[name=sfkfxsgwsq]:checked").val()){
		jQuery("#xsgwsqkssj").val("");
		jQuery("#xsgwsqjssj").val("");
	}
	
	if($("xsxsgws") && $("xsxsgws").value==""){
 		alertInfo("ѧ���������λ������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("xsxsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}
	
	if($("xsgws") && $("xsgws").value==""){
 		alertInfo("ѧ���ɻ�ø�λ������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				$("xsgws").focus();
 				return false;
 			}
 		});
 		return false;
	}

	if($("xsgwsqsplc") && $("xsgwsqsplc").value==""){
 		alertInfo("��ѡ��У��ѧ����λ����������!",function(tag){
 			if(tag=="ok"){
 				
 				return false;
 			}
 		});
 		return false;
	}
	if($("xsyjgwsqsplc") && $("xsyjgwsqsplc").value==""){
 		alertInfo("��ѡ��Ժ����ѧ����λ����������!",function(tag){
 			if(tag=="ok"){
 				
 				return false;
 			}
 		});
 		return false;
	}
	jQuery("[name=rs]")
	return true;
}
function checkGwSx(){
	var selectV=jQuery("#sfsdgwcjsx").val();
	if("no"==selectV){
		return true;
	}
	var sxzdV=jQuery("#sxzd").val();
	var sxszV=jQuery("#sxsz").val();
	var cjbzV=jQuery("#cjbz").val();
	var pksbzV=jQuery("#pkscjbz").val();
	var xscjsx=sxszV;
	var xscjsxPks=sxszV;
	if(sxzdV=="gs"){
		xscjsx=parseInt(sxszV)*parseInt(cjbzV);
		xscjsxPks=parseInt(sxszV)*parseInt(pksbzV);
	}
	var gwzgcjsxV=jQuery("#gwzgcjsx").val();
	if(parseInt(gwzgcjsxV)>parseInt(xscjsx)){
 		alertInfo("��λ����趨���޲��ó�������������(��ʱ&���)���ĳ����("+xscjsx+"Ԫ)!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#xxdm").val() == "10351"){
		var gwzgcjsxPks = jQuery("#pkscjzgsx").val();
		if(parseInt(gwzgcjsxPks)>parseInt(xscjsxPks)){
	 		alertInfo("ƶ������λ��߳���趨���޲��ó�������������(��ʱ&���)���ĳ����("+xscjsxPks+"Ԫ)!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}
	return true;
}
function checkGwcj(xxdm){
	if("12309"!=xxdm){
		var selectV=jQuery("#sfsdgwcjsx").val();
		if("no"==selectV){
			return true;
		}else{
			var gwzgcjsxV=jQuery("#gwzgcjsx").val();
			var sfkgggwcjsxV=jQuery('input[name="sfkgggwcjsx"]:checked').val();
			if(null==gwzgcjsxV||""==gwzgcjsxV){
				alertInfo("�����ø�λ��߳������!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
			if(null==sfkgggwcjsxV||""==sfkgggwcjsxV){
				alertInfo("���������˵�λ�����λʱ�ɷ���ĸ�λ�������!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
				return false;
			}
		}
	}
	return true;
}
function Save(){
    var ids = "cjsx"+"-"+"gzscsx";
    if(check(ids)==false){
        showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
        return false;
	}
	var gzscsx = jQuery("#gzscsx").val();
    if(gzscsx>40){
        showAlert("ÿ�¹���ʱ�����޲��ó���40Сʱ");
        return false;
	}
	if(!jQuery("input[name='sfkfgwsq']").is(":checked")){
        showAlert("��ѡ��У�ڸ�λ���뿪�أ�");
        return;
	}
	if(jQuery("input[name='sfkfgwsq']:checked").val() == "on"){
        if(!checkNotNull("yrdwsplc")){
            showAlert("��ѡ��У�ڸ�λ�����������̣�");
            return;
        }
	}
/*	if(!jQuery("input[name='sfkfxwgwsq']").is(":checked")){
        showAlert("��ѡ��У���λ���뿪�أ�");
        return;
	}
    if(jQuery("input[name='sfkfxwgwsq']:checked").val() == "on"){
        if(!checkNotNull("xwgwsplc")){
            showAlert("��ѡ��У���λ�����������̣�");
            return;
        }
    }*/
    if(!jQuery("input[name='sfkfxsgwsq']").is(":checked")){
        showAlert("��ѡ��ѧ����λ���뿪�أ�");
        return;
    }
    if(jQuery("input[name='sfkfxsgwsq']:checked").val() == "on"){
        if(!checkNotNull("xsgwsqsplc-xwxsgwsqsplc-gzscwhsplc-xslzsplc")){
            showAlert("��ѡ��ѧ����λ�������̣�");
            return;
        }
    }
    if(!jQuery("input[name='gzsqkg']").is(":checked")){
        showAlert("��ѡ�����걨���أ�");
        return;
    }
    if(jQuery("input[name='gzsqkg']:checked").val() == "on"){
        if(!checkNotNull("gzsqsplc")){
            showAlert("��ѡ�����걨�������̣�");
            return;
        }
    }
    var url = "qgzx_jcsz_ajax.do?method=save";
    ajaxSubFormWithFun("qgzxCsszForm",url,function(data){
        if (data["message"] == "����ɹ���") {
            showAlert(data["message"], {}, {
                "clkFun" : function() {
                    if (parent.window) {
                        refershParent();
                    }
                }
            });
        } else {
            showAlert(data["message"]);
        }
    });
}

//����������ֵ
function changSxsz(){
	var sxzd = jQuery("#sxzd").val();
	if(sxzd=="je"){
		jQuery("#font_sxsz").text("Ԫ(ѧ��ÿ�³�𲻵ó�����ֵ)");
		jQuery("#sxsz").val("");
		jQuery("#sxsz").focus();
	}else if(sxzd=="gs"){
		jQuery("#font_sxsz").text("Сʱ(ѧ��ÿ�¹�ʱ���ó�����ֵ)");
		jQuery("#sxsz").val("");
		jQuery("#sxsz").focus();
	}else{
		jQuery("#font_sxsz").text();
	}
}

//���¸�λ����
function changeGwsq(){
	var sfkfgwsq = jQuery("[name=sfkfgwsq]:checked").val();
	if("on"==sfkfgwsq){
		jQuery("#div_gwsqkfsj").show();
	}else if("off"==sfkfgwsq){
		jQuery("#div_gwsqkfsj").hide();
	}
}
//����ѧ����λ����
function changexsGwsq(){
	var sfkfgwsq = jQuery("[name=sfkfxsgwsq]:checked").val();
	if("on"==sfkfgwsq){
		jQuery("#div_xsgwsqkfsj").show();
	}else if("off"==sfkfgwsq){
		jQuery("#div_xsgwsqkfsj").hide();
	}
}

function changeSqkg(){
	var sqkg = jQuery("[name=sqkg]:checked").val();
	if("1"==sqkg){
		jQuery("[name=tr_cjff]").show();
	}else if("0"==sqkg){
		jQuery("[name=tr_cjff]").hide();
	}
}

//��֤�Ƿ�Ϊ��
function check(ids){
    var id=ids.split("-");
    for(var i=0;i<id.length;i++){
        var lddm=jQuery("#"+id[i]).val();
        if(lddm==null||""==lddm){
            return false;
        }
    }
    return true;
}

