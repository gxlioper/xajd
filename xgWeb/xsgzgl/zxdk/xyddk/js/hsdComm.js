
/**
 * js�Զ����ر�����
 * ��ʦ��У԰�ش���(������ѧ����)����ͽ������Ҫ�⼸������,�����������,��˵�����һ��js
 * by [1206]yxy date:2016-11-09
 * @return
 */
function autoProduce(){
	var rs = getDqqx();
	/**
	 * �����������������ޣ�������ޣ���ǰѧ��
	 */
	var dkqs = parseInt(rs['dkqs']);
	var dkqx = parseInt(rs['dkqx']);
	var jesx = parseInt(rs['jesx']);
	var currxn = rs['currxn'];
	jQuery("#jesx").val(jesx);
	
	
	produceDkqsqx(dkqs,dkqx,currxn,jesx);
}

/**
 * ��ȡ��������
 * @return
 */
function getDqqx(){
	var rs = null;
	var xh = jQuery("#xh").val();
	var url = "zxdkXyddk.do?method=getHsdDkqx";
		jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'xh='+xh,
			async: false,
			success:function(result){
				if(result['rs'] == 'false'){
					showAlert(result['message'],{},{"clkFun":function(){
						jQuery("#bccg").hide();
						jQuery("#tjsq").hide();
					}});
					return false;
				}else{
					if(result['dkqs'] == '0' || (!result['dkqs']) || parseInt(result['dkqs']) <0){
						showAlert("��������Ϊ0�����ܽ������룡",{},{"clkFun":function(){
							jQuery("#bccg").hide();
							jQuery("#tjsq").hide();
						}});
					}
					rs = result;
				}
			}
			
		});
	   return rs;
}

/**
 * ���ɴ�����������
 * @return
 */
function produceDkqsqx(dkqs,dkqx,currxn,jesx){
	/**
	 * Ϊ�˰��¼��������selectԪ��
	 */
	jQuery("#dkqxtd").empty();
	var html = "<select name='dkqx' style='width:98%'  id='dkqx'>";
	for(var i=0;i<dkqs;i++){
		if(i+1 == dkqs){
			html +="<option value='"+(i+1)+"' selected='selected'>"+(i+1)+"</option>";
		}else{
			html +="<option value='"+(i+1)+"'>"+(i+1)+"</option>";
		}
		
	}
	html += "</select>";
	jQuery("#jhr1").val(dkqx);
	jQuery("#tip").text(jesx);
	/**
	 * ���¼�
	 */
	var htmlobj = jQuery(html);
	jQuery(htmlobj).change(function(){
		//����������0
		jQuery("#zsfdks").val("0");
		jQuery("#xfdks").val("0");
		jQuery("#shfdks").val("0");
		jQuery("#dkje").val("0");
		produceTableContent(parseInt(this.value),currxn,jesx);
		/**
		 * ����������Ĭ��ֵ���ɸ�
		 */
		jQuery("#jhr1").val(parseInt(this.value)*12);
	});
	jQuery("#dkqxtd").append(htmlobj);
	produceTableContent(dkqs,currxn,jesx);
}

/**
 * ���ɱ������
 * @return
 */
function produceTableContent(dkqs,currxn,jesx){
	jQuery("tr").remove(".showtr");
	/**
	 * �������Ҫ�󶨼��㷽��
	 */
	var html = "";
	var currnd1 = parseInt((currxn.split("-"))[0]);
	var currnd2 = parseInt((currxn.split("-"))[1]);
	for(var i = 0;i < dkqs;i++){
		/**
		 * ��ʱѧ��������ֻ������ֵ,��ѡ��Ĭ��ֵ
		 */
		var xn1 = (currnd1+i)+"-"+(currnd2+i);
		var xn2 = (currnd1+i+1)+"-"+(currnd2+i+1);
		html +="<tr class ='showtr'>";
		html +="<td>";
		html +="<select name='xn' style='width:98%'>";
		html +="<option value='"+xn1+"'>"+xn1+"</option>";
		html +="<option value='"+xn2+"'>"+xn2+"</option>";
		html +="</select>";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nzsfdk' class='dk'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nxfdk'  class='dk'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nshfdk' class='dk'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nzsfyje'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
	    html +="<td>";
	    html +="<input name='nxfyje'  style='width:98%' maxlength='5' onkeyup='checkInput(this)' />";
	    html +="</td>";
		html +="</tr>";
	}
	/**
	 * ����input
	 */
	var htmlobj = jQuery(html);
	jQuery(htmlobj).find(".dk").change(function(){
		checkNullInput(this);
		calculate(this);
	});
	jQuery("#tableshow").show();
	jQuery("#table").append(htmlobj);
}

/**
 * �����������Զ�����������
 * @return
 */
function  calculate(obj){
	/**
	 * ������������,��Ҫ�ж��Ƿ񳬹��������
	 */
	var jesx = parseInt(jQuery("#jesx").val());
	if(obj.value > jesx){
		showAlert("����������ޣ�",{},{"clkFun":function(){
			jQuery(obj).val("0");
			return false;
		}});
	}
	if(obj.name == 'nzsfdk' || obj.name == 'nxfdk' || obj.name == 'nshfdk' ){
		calEverykx(obj);
	}else{
		return false;
	}
	
}


/**
 * ����name����
 * @return
 */	
function calEverykx(obj){
	/**
	 * ���ü�����Ϊ0,�����������
	 */
	var name = obj.name;
	var value = parseInt(obj.value);
	var jsjg = 0;
	var xnjsjg = 0;
	var jesx = parseInt(jQuery("#jesx").val());
	/**
	 * �����ܴ���
	 */
	jQuery(".dk").not(obj).each(function(i,row){
		var rowvalue = this.value;
		if(rowvalue == '' || rowvalue == null){
			rowvalue = 0;
		}else{
			rowvalue = parseInt(rowvalue);
		}
		jsjg = jsjg + rowvalue;
	})
	
	jQuery(obj).parent().parent().find(".dk").not(obj).each(function(i,row){
		var rowvalue = this.value;
		if(rowvalue == '' || rowvalue == null){
			rowvalue = 0;
		}else{
			rowvalue = parseInt(rowvalue);
		}
		xnjsjg = xnjsjg + rowvalue;
	})
	/**
	 * ����������ۼƳ����������, �������Ϊ0
	 */
	xnjsjg = xnjsjg + value;
	if(xnjsjg >jesx){
		jQuery(obj).val("0");
	}else{
		jsjg = jsjg + value;
	}
	jQuery("#dkje").val(jsjg);
	
	/**
	 * �������������
	 */
	value = parseInt(obj.value);
	
	jsjg = 0;

	jQuery("[name='"+name+"']").not(obj).each(function(i,row){
		var rowvalue = this.value;
		if(rowvalue == '' || rowvalue == null){
			rowvalue = 0;
		}else{
			rowvalue = parseInt(rowvalue);
		}
		jsjg = jsjg + rowvalue;
		
	});
	/**
	 * ����������ۼƳ����������, �������Ϊ0
	 */
	jsjg = jsjg + value;
//	if(jsjg >jesx){
//		jsjg = jsjg-value;
//		jQuery(obj).val("0");
//	}
	
	/**
	 *�жϸ�ֵ
	 */
	if(name == 'nzsfdk'){
		/**
		 * ס�޷�
		 */
		jQuery("#zsfdks").val(jsjg);
	}else if(name == 'nxfdk'){
		/**
		 * ѧ��
		 */
		jQuery("#xfdks").val(jsjg);
	}else if(name == 'nshfdk'){
		/**
		 * �����
		 */
		jQuery("#shfdks").val(jsjg);
	}
	
	
}

/**
 * ���뱣��
 * @param type
 * @return
 */
function saveSqForHsd(type){
	if(!checkFormdata()){
		return false;
	}
	
	var url = "zxdkXyddk.do?method=saveDksqForHsd&type="+type;
	ajaxSubFormWithFun("xyddkForm",url,function(data){
		if(data["message"] == "����ɹ���"){
			showAlert(data["message"],{},{"clkFun":function(){
				refershParent();
			}});
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				return false;
			}});
		}
		
	});
}

/**
 * �޸ı���
 * @param type
 * @return
 */
function saveSqForHsdupdate(type){
	if(!checkFormdata()){
		return false;
	}
	
	var url = "zxdkXyddk.do?method=saveAndSubmitForHsd&type="+type;
	ajaxSubFormWithFun("xyddkForm",url,function(data){
		if(data["message"] == "����ɹ���"){
			showAlert(data["message"],{},{"clkFun":function(){
				refershParent();
			}});
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				return false;
			}});
		}
		
	});
}

/**
 * ����������
 * @param type
 * @return
 */
function saveJgForHsd(url){
	if(jQuery.trim(jQuery("#htbh").val()) == ""){
		showAlert("��ͬ��Ų���Ϊ�գ�");
	     return false;
	}
	if(!checkFormdata()){
		return false;
	}
	
	ajaxSubFormWithFun("xyddkForm",url,function(data){
		if(data["message"] == "����ɹ���"){
			showAlert(data["message"],{},{"clkFun":function(){
				refershParent();
			}});
		}else{
			showAlert(data["message"],{},{"clkFun":function(){
				return false;
			}});
		}
		
	});
}


/**
 * ����ǰ��֤����
 */
function checkFormdata(){
	var str = "xh"+"-"+"zsfdks"+"-"+"xfdks"+"-"+"shfdks"+"-"+"dkje"+"-"+"sqly"+"-"+"dkqx"+"-"+"jhr1";
	if(!checkNotNull(str)){
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������",{},{"clkFun":function(){
			
		}});
		return false;
	}
	var flag = true;
	jQuery(".showtr").find("input").each(function(i,row){
		var rowvalue = this.value;
		if(jQuery.trim(rowvalue) == null ||  jQuery.trim(rowvalue) == ""){
			flag = false;
			return flag;
		}
	})
	if(!flag){
		showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������",{},{"clkFun":function(){
			
		}});
		return false;
	}
	
//	if(parseInt(jQuery("#dkje").val()) > parseInt(jQuery("#jesx").val())){
//		showAlert("���ó����������"+jQuery("#jesx").val()+"Ԫ��",{},{"clkFun":function(){
//			
//		}});
//		return false;
//	}
	
	jQuery("[name='xn']").each(function(i,row){
		var rowvalue = this.value;
		var num = jQuery("option[value='"+rowvalue+"']:selected").length;
		if(num > 1){
			flag = false;
			return flag;
		}
	})
	if(!flag){
		showAlert("ѧ�겻���ظ���",{},{"clkFun":function(){
			
		}});
		return false;
	}
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("�������ɲ��ó���500�֣�",{},{"clkFun":function(){
			flag = false;
		}});
		if(!flag){
			return flag;
		}
	}
	//�Ƿ񵯳���ͥ���������дҳ��
	var openJtqk = jQuery("#openJtqk").val();
	if ("true" == openJtqk){
		var xh = jQuery("#xh").val();
		showAlertDivLayer("������д��ͥ��������",{},{"clkFun":function(){
			editJtqk();
		}});
		return false;
	}
	return flag;

}

