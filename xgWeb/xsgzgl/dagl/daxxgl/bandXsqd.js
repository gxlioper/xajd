/**
 * ������ҳ
 * @return
 */
function czmy(){
	var url = "daxxgl.do?method=bandXsqd&pk="+jQuery("#pk").val();
	document.forms[0].action = url;
	document.forms[0].submit();
}

function  saveForm(){
	var xh = jQuery("#xh").val();
	var dazrsj = jQuery("#dazrsj").val();
	var daqdmb_id = jQuery("#daqdmb_id").val();
	
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
		// �ѱ��浵���嵥��������ϵı���
		var daqdcl_id = jQuery(this).find("input[name=daqdcl_id]").val();	
		if(daqdcl_id!=null && ""!=daqdcl_id){

			var clInfo = new Object();
			clInfo.daqdcl_id = daqdcl_id;
			clInfo.sx = sx;
			clInfo.fs = jQuery(this).find("input[name=fs]").val();
			clInfo.ys = jQuery(this).find("input[name=ys]").val();
			clInfo.gdrq = jQuery(this).find("input[name=gdrq]").val();
			clInfo.bz = jQuery(this).find("input[name=bz]").val();
			info[sx]=clInfo;
			sx ++;
		}
		
		
		// δ���浵���嵥��������ϵı���
		daqdcl_id = jQuery(this).find("select[name=clid]").val();		
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
	
	var json=JSON.stringify(info);
	//��ȡģ���������Ϣ
	// �õ�JSON����
	var url = "daxxgl.do?method=bandXsqd&type=save";
	
	jQuery("#form").ajaxSubmit({
		   type: "POST",
		   url: url,
		   dataType:"json",
		   data:{json:json,xh:xh,dazrsj:dazrsj,daqdmb_id:daqdmb_id},
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


jQuery(function(){
	
	// �Ƿ���ʾ�����嵥ģ��Ĳ����б�
	var daqdmb_id = jQuery("#daqdmb_id").val();
	if(daqdmb_id==""){
		jQuery("table[id=mbnTable]").parent().hide();
	}else{
		jQuery("table[id=mbnTable]").parent().show();
	}
	var bdztFlg = false;
	// �����嵥ģ���б�
	jQuery("#mbnTable tr").each(function(n){
		if(n>0){
			if(jQuery(this).find("input[name=bdzt]").val()=="Y"){
				bdztFlg = true;
			}			
			if(!bdztFlg){
				jQuery(this).find("input[name=gdrq]").val(jQuery("#dazrsj").val());
				jQuery(this).find("input[name=fs]").val("1");
			}
		}
	});
	// ģ�����б������ݣ���
	var mbwclList = jQuery("#mbwclList").val();
	if(mbwclList!=null){
		if(mbwclList.length!=2){
			jQuery("input[type=radio][name=mbwclFlag][value=1]").attr("checked",true);
		}
	}
	sfzjMbwcl();
});

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
	var row = jQuery("<tr onclick='clickTr(this);return false;'></tr>");			
	var trNew=jQuery("tr[id=mbwSpan]").html();
	row.append(trNew);
	xmInfo.append(row);
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



