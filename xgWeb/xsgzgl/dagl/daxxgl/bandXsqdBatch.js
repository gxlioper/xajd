/**
 * ������ҳ
 * @return
 */
function czmy(){
	var url = "daxxgl.do?method=bandXsqdBatch";
	document.forms[0].action = url;
	document.forms[0].submit();
}

function  saveForm(){
	var values = jQuery("#values").val();
	var daqdmb_id = jQuery("#daqdmb_id").val();
	var selected = jQuery("#selected").val();
	var yxzxss = jQuery("#yxzxss").val();

	if(yxzxss=="0"){
		alertInfo("��ѡ������1��ѧ����");
		return false;
	}
	var info = new Array();
	var sx=1;
	var retunflg = false;

	//��ȡģ���������Ϣ
	jQuery("#xmInfo").find("tr").find("select[name=clid]").each(function(n){
		var daqdcl_id = jQuery(this).val();
		if(daqdcl_id == null || daqdcl_id ==""){
			alertInfo("��ѡ����ϣ�");
			jQuery(this).parents("tr:first").css("background-color","#b0cbe0").siblings().css("background-color","#FFFFFF");
			retunflg = true;
			return false;	
		}
	});
	// �д�����Ϣ��ֱ�ӷ���
	if(retunflg){
		return false;
	}
	
	//��ȡģ���ڲ�����Ϣ
	jQuery("#mbnTable tr").each(function(n){
		if(n>0){
			var clInfo = new Object();
			clInfo.daqdcl_id = jQuery(this).find("input[name=daqdcl_id]").val();
			clInfo.sx = sx;
			clInfo.fs = jQuery(this).find("input[name=fs]").val();
			clInfo.ys = jQuery(this).find("input[name=ys]").val();
			clInfo.gdrq = jQuery(this).find("input[name=gdrq]").val();
			clInfo.bz = jQuery(this).find("input[name=bz]").val();
			info[sx]=clInfo;
			sx ++;
		}
	});
	//��ȡģ���������Ϣ
	jQuery("#xmInfo").find("tr").each(function(n){
		
		// δ���浵���嵥��������ϵı���
		var daqdcl_id = jQuery(this).find("select[name=clid]").val();		
		if(daqdcl_id!=null && ""!=daqdcl_id){
			var clInfo = new Object();
			clInfo.daqdcl_id = daqdcl_id;
			clInfo.sx = sx;
			clInfo.fs = jQuery(this).find("input[name=_fs]").val();
			clInfo.ys = jQuery(this).find("input[name=_ys]").val();
			clInfo.gdrq = jQuery(this).find("input[name=_gdrq]").val();
			clInfo.bz = jQuery(this).find("input[name=_bz]").val();
			info[sx]=clInfo;
			sx ++;
		}
	});
	
	if(info==""){
		confirmInfo("��δѡ����ϣ�ȷ�����<font color='red'>"+yxzxss +"</font>����¼�Ĳ�����?",function(ty){
			if(ty=="ok"){
				var json=JSON.stringify(info);
				//��ȡģ���������Ϣ
				// �õ�JSON����
				var url = "daxxgl.do?method=bandXsqdBatch&type=save";
				jQuery("#form").ajaxSubmit({
					   type: "POST",
					   url: url,
					   dataType:"json",
					   data:{json:json,values:values,daqdmb_id:daqdmb_id,selected:selected},
					   success: function(data){
						   if(data["message"]=="����ɹ���"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
						  				if (parent.window){
						  					refershParent();
						  				}
					    			}});
					    	 }
					 		 else{
					    		 showAlert(data["message"]);
					    	 }
					   }
				});
				
			}else{
				return false;
			}
		});
	}else{
		var json=JSON.stringify(info);
		//��ȡģ���������Ϣ
		// �õ�JSON����
		var url = "daxxgl.do?method=bandXsqdBatch&type=save&selected=all";
				
		//�߼���ѯ
		url +="&searchTj=";
		url +=jQuery("#searchTj").val();
		url +="&searchTjz=";
		url +=jQuery("#searchTjz").val();
		url +="&mhcx_lx=";
		url +=jQuery("#mhcx_lx").val();
		url +="&searchLx=";
		url +=jQuery("#searchLx").val();

		//ģ����ѯ
		url +="&input_mhcx=";
		url +=jQuery("#input_mhcx").val();
		url +="&path=";
		url +=jQuery("#path").val();
		
		//alert(url);
		jQuery("#form").ajaxSubmit({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   data:{json:json,values:values,daqdmb_id:daqdmb_id,selected:selected},
			   success: function(data){
				   if(data["message"]=="����ɹ���"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
				  				if (parent.window){
				  					refershParent();
				  				}
			    			}});
			    	 }
			 		 else{
			    		 showAlert(data["message"]);
			    	 }
			   }
		});
	}
}

jQuery(function(){

	var dazrsj = jQuery("#dazrsj").val();
	if(dazrsj == ""){
		var mydate = new Date();
		jQuery("#dazrsj").val(jQuery("#sysdate").val());
	}
	
	// �Ƿ���ʾ�����嵥ģ��Ĳ����б�
	var daqdmb_id = jQuery("#daqdmb_id").val();
	if(daqdmb_id==""){
		jQuery("table[id=mbnTable]").parent().hide();
	}else{
		jQuery("table[id=mbnTable]").parent().show();
	}

	// �����嵥ģ���б�
	jQuery("#mbnTable tr").each(function(n){
		if(n>0){
			jQuery(this).find("input[name=gdrq]").val(jQuery("#dazrsj").val());
			jQuery(this).find("input[name=fs]").val("1");
		}
	});
	
	//setQxshow();
	sfzjMbwcl();
});


/**
 * ������ѡȨ������
 * @return
 */
function setQxshow(){
	var yxtjhtml='';
	if(frameElement.api){
		yxtjhtml = jQuery(frameElement.api.opener.document).find('#yxtj_dl').html();
		var selectCount =  jQuery("#selectCount").val();
		if(selectCount!=null && selectCount!=""){
			jQuery("tr[id=xsfwSelect]").hide();
		}else{
			alert(yxtjhtml == null || yxtjhtml=="");
			
			if(yxtjhtml == null || yxtjhtml==""){
				yxtjhtml="<dd><h5>ȫ��</h5></dd>";
			}
			jQuery("tr[id=xsfwSelect]").show();
			jQuery("#yxtj_dl").html(yxtjhtml);
		}
	}
	//ȥ��ȡ������
	jQuery("#yxtj_dl").find("a").each(function(){
		jQuery(this).attr("onclick","");
		jQuery(this).find("span").remove();
	});
}

function sfzjMbwcl(){
	// ׷��ģ�������
	if(jQuery("input[type=radio][name=mbwclFlag][value=1]").prop("checked")){
		jQuery("tr[name=mbwclTr]").show();
		
	// ��׷��ģ�������
	}else if(jQuery("input[type=radio][name=mbwclFlag][value=0]").prop("checked")){
		jQuery("tr[name=mbwclTr]").hide();
	}
}

function selectCl(obj){
	// �������嵥ģ���ڲ����Ƿ��ظ��ж�
	jQuery("#mbnTable tr").each(function(n){
		if(n>0){
			if(obj.value == jQuery(this).find("input[name=daqdcl_id]").val()){
				alertInfo("���ϲ����ظ��趨��������ѡ��");
				jQuery(obj).val("");
				return false;
			}
		}
	});

	// ���ѱ��浵���嵥ģ��������Ƿ��ظ��ж�
	jQuery("#xmInfo").find("input[name=daqdcl_id]").each(function(n){
		if(obj.value == jQuery(this).val()){
			alertInfo("���ϲ����ظ��趨��������ѡ��");
			jQuery(obj).val("");
			return false;
		}
	});
	
	// �������嵥ģ�����б���ϣ������Ƿ��ظ��ж�
	jQuery(obj).parents("tr").nextAll().each(function(n){
			if(obj.value == jQuery(this).find("select[name=clid]").val()){
				alertInfo("���ϲ����ظ��趨��������ѡ��");
				jQuery(obj).val("");
				return false;
			}
	});

	// �������嵥ģ�����б���ϣ���ǰ���Ƿ��ظ��ж�
	jQuery(obj).parents("tr").prevAll().each(function(n){
			if(obj.value == jQuery(this).find("select[name=clid]").val()){
				alertInfo("���ϲ����ظ��趨��������ѡ��");
				jQuery(obj).val("");
				return false;
			}
	});
}

/** ***********************��ť����******************************* */
// ����һ��
function addMbwcl(){
	var xmInfo = jQuery('#xmInfo'); 
	var dazrsj = jQuery("#dazrsj").val();
	var row = jQuery("<tr onclick='clickTr(this);return false;'></tr>");			
	var trNew=jQuery("tr[id=mbwSpan]").html();
	row.append(trNew);
	xmInfo.append(row);
	//jQuery("#xmInfo>tr:last td input[name='_gdrq'").val(dazrsj);
}
// ɾ��ѡ�еļ�¼
function delMbwcl(){
	var length = jQuery('#xmInfo').find("input:checked").length;
	if (length !=0){
		confirmInfo("��ȷ��Ҫɾ��ѡ�е�<font color='red'>"+length +"</font>����¼��?",function(ty){
			if(ty=="ok"){
				jQuery(xmInfo).find("input:checked").each(function(){
					jQuery(this).parent().parent().remove();
				});
			}
		});
	}
}

// �ı���е�ѡ��״̬
function clickTr(whick){
	jQuery('#xmInfo').find("tr").attr("selectrow",false);
	jQuery(jQuery(whick)).attr("selectrow",true);			
	jQuery(jQuery(whick)).css("background-color","#b0cbe0").siblings().css("background-color","#FFFFFF");
	if(jQuery(jQuery(whick)).find(":checkbox").prop("checked")){
		jQuery(jQuery(whick)).find(":checkbox").removeAttr("checked");
	}else{
		jQuery(jQuery(whick)).find(":checkbox").attr("checked","checked");
	}
}
// ɾ�����м�¼
function delAllMbwcl(){
	confirmInfo("��ȷ��Ҫɾ�����еļ�¼��?",function(ty){
		if(ty=="ok"){
			jQuery('#xmInfo').find("tr").remove();
		}
	});
}

// ���ƶ�
function upMbwcl(){
	var length = jQuery('#xmInfo').find("tr[selectrow='true']").length;
	if(length ==1){
		var _cur = jQuery('#xmInfo').find("tr[selectrow='true']");
		var _pre = _cur.prev();
		_cur.insertBefore(_pre);
	}
}

// ���ƶ�
function downMbwcl(){
	var length = jQuery('#xmInfo').find("tr[selectrow='true']").length;
	if(length ==1){
		var _cur = jQuery('#xmInfo').find("tr[selectrow='true']");
		var _pre = _cur.next();
		_cur.insertAfter(_pre);
	}
}

/** *********************************************************** */



