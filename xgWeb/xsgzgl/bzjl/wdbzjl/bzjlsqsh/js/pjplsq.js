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
	
	showDialog("�������",700,500,"bzjl_sqsh.do?method=viewJxsh&sqid="+sqid+"&shid="+shid+"&gwid="+gwid);
}

function plshtg(){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var bjdms = jQuery("#bjdms").val();
	showDialog("�������",700,300,"bzjl_sqsh.do?method=toPlshy&xn="+xn+"&xq="+xq+"&bjdms="+bjdms);
}


/**
 * ɾ��
 */
function qxsq(id){
	
	showConfirmDivLayer("��ȷ��Ҫȡ����������",{"okFun":function(){
		jQuery.post("bzjl_sqsh.do?method=cancelXmsq",{values:id},function(data){
			jQuery('#search_go').click();
			showAlertDivLayer(data["message"]);
		},'json');
	}});
}

//�ύ
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['splcid'],
					ydlx : rows[0]['ssydlx'],
					xh : rows[0]['xh']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}

//����
function cancle(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splcid']
				}, function(data) {
					
					showAlertDivLayer(data["message"]);
					reload();
				}, 'json');
				
			}
		});
	}
	
}

//ѡ��������Ŀ
function choosepjxm(obj,aa){
	
	if(obj.checked){
		jQuery("td[name='pjxmtd"+aa+"']").show();
	}else{
		jQuery("td[name='pjxmtd"+aa+"']").hide();
	}
	
	
}