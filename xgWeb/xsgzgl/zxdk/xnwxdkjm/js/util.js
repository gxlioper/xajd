/**
 * ������������
 * @return
 */
function checkzs(){
	if(jQuery("#sqly").val().length > 500){
		showAlertDivLayer("���������������Ϊ500�����Ѿ���������ȷ�ϣ�");
		return false;
	}
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery.trim(jQuery("#"+id[i]).val()) || jQuery.trim(jQuery("#"+id[i]).text());
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

//���У�鲻����0��ͷ
function ismoney(obj){
  if(obj.value.indexOf('0') == 0){
		 showAlert("������0��ͷ��",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
			}});
  }
}

//��Ϣ�����ȡ�����ܽ��
function selectXsYzFz(xh,flag){
	var rs = null;
	var data = {xh:xh,flag:flag}
	var url = "xnwxdkjm_sq.do?method=selectXsYzFz";
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:data,
	async: false,
	success:function(result){
			rs = result;
	 }
    });
	if(rs["sfbys"] == "��"){
		jQuery("#bccg").hide();
		jQuery("#tjsq").hide();
		jQuery("#zje").text(0);
//		jQuery("#jmbl").attr("disabled","disabled");
		showAlert("��ѧ�����Ǳ�ҵ�������ܽ�����Ϣ�������룡");
		return false;
	}else{
		if(rs["cfbz"] == "ysj"){
			jQuery("#bccg").hide();
			jQuery("#tjsq").hide();
			jQuery("#zje").text(0);
//			jQuery("#jmbl").attr("disabled","disabled");
			showAlert("��ѧ�����������¼�������ظ���д��");
			return false;
		}else{
			if(parseInt(rs["dkjl"]) == 0){
				jQuery("#bccg").hide();
				jQuery("#tjsq").hide();
				jQuery("#zje").text(0);
//				jQuery("#jmbl").attr("disabled","disabled");
				showAlert("��ѧ������Ϣ�����¼�����ܽ�����Ϣ�������룡");
				return false;
			}else{
				jQuery("#bccg").show();
				jQuery("#tjsq").show();
//				jQuery("#jmbl").removeAttr("disabled");
				jQuery("#zje").text(rs["zje"]);
			}
		}
	}
	
}

function changejmbl(jmbl){
	var data = {jmbl:jmbl};
	if(jQuery.trim(jmbl) != "" && jmbl != null ){
		var rs = null;
		var url = "xnwxdkjm_sq.do?method=changeJmbl";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
			if(result==null||result=="null" || rs == ""){
				rs = null;
			}else{
				rs = result['html'];;
			}
		 }
	    });
		jQuery("#jmyjdiv").html(rs);
		var jmbl = jQuery("#jmbl").val();
	    jmbl = parseInt(jmbl.replace("%",""))/100;
		var yjjmje = (parseInt(jQuery("#zje").text())*jmbl).toFixed(2);
		jQuery("#yjjmje").text(yjjmje);
		
	}else{
		jQuery("#yjjmje").text(0.00);
	}
}

function updateDataInit(){
	var jmbl = jQuery("#jmbl").val();
    jmbl = parseInt(jmbl.replace("%",""))/100;
	var yjjmje = (parseInt(jQuery("#zje").text())*jmbl).toFixed(2);
	jQuery("#yjjmje").text(yjjmje);
	var checkboxValue = jQuery("#checkboxvlaue").val();
	var checkValue = checkboxValue.split(",");
	for(var i=0;i<checkValue.length;i++){
		jQuery("[name='jmyj'][value='"+checkValue[i]+"']:checkbox").attr("checked","checked");
	}
}

function checkHaveValue(){
	var length = jQuery("input[type='checkbox']:checked").length;
	if(length > 0){
		return true;
	}else{
		return false;
	}
}