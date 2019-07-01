


function changeTab(index){
	jQuery("#tabUl li.ha").removeClass("ha");
	jQuery("#tabUl li:eq("+index+")").addClass("ha");
	
	if (index == 0){
		jQuery("#dataTable").initGrid(yrzGrid);
		
		jQuery("#btn_tzrz").show();
		jQuery("#btn_dtz").show();
		jQuery("#btn_sc").hide();
		jQuery("#btn_qxtz").hide();
		jQuery("#btn_qrtz").hide();
	} else if (index == 1){
		jQuery("#dataTable").initGrid(dtzGrid);
		
		jQuery("#btn_tzrz").show();
		jQuery("#btn_dtz").hide();
		jQuery("#btn_sc").show();
		jQuery("#btn_qxtz").hide();
		jQuery("#btn_qrtz").hide();
	} else if (index == 2){
		jQuery("#btn_tzrz").hide();
		jQuery("#btn_dtz").hide();
		jQuery("#btn_sc").hide();
		jQuery("#btn_qxtz").show();
		jQuery("#btn_qrtz").show();
		
		jQuery("#dataTable").initGrid(qrtzGrid);
		
		setTimeout(function(){
			jQuery(":input[name=bktj]").parents("tr").css("background","red");
			jQuery(":input[name=bktj]").parents("tr").attr("title","Ŀ�괲λ��ռ�ã��봦��");
		},50);
	}
}

function szDtz(){
	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0){
		showAlertDivLayer("�빴ѡ��Ҫ�����ļ�¼��");
		return false;
	}
	
	jQuery.post("gy_plyd.do?method=szDtz",{ids:ids.toString()},function(data){
		if (data){
			searchRs();
		}
	},"json");
}

function qxDtz(){
	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0){
		showAlertDivLayer("�빴ѡ��Ҫ�����ļ�¼��");
		return false;
	}
	
	jQuery.post("gy_plyd.do?method=qxDtz",{ids:ids.toString()},function(data){
		if (data){
			searchRs();
		}
	},"json");
}

function tzrz(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	
	if (ids.length == 0){
		showAlertDivLayer("�빴ѡ��Ҫ�����ļ�¼��");
		return false;
	}
	
	var xb = rows[0]["xb"];
	var xbyz = true;
	
	for (var i = 0 ; i < rows.length ; i++){
		xbyz = rows[i]["xb"] == xb;
		
		if (!xbyz){
			break;
		}
	}
	
	if (xbyz){
		showDialog("������ס",750,500,"gy_plyd.do?method=tzrz&ids="+ids,{
			button: [
		         {
		             name: '����',
		             focus:true,
		             callback: function(){
		            	 this.content.saveXsrz();
		            	 searchRs();
		            	 return false;
		             }
		         },
		         {
		             name: 'ȡ��'
		         }
		     ]
		});
	} else {
		showAlertDivLayer("ѡ���ѧ���Ա����һ�£�");
	}
}

function qxtz(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("�빴ѡ��Ҫ�����ļ�¼��");
		return false;
	}
	jQuery.post("gy_plyd.do?method=qxtz",{ids:ids.toString()},function(data){
		if (data){
			searchRs();
		}
	},"json");
}

function qrtz(){
	jQuery.post("gy_plyd.do?method=qrtz",{},function(data){
		if (data){
			showAlertDivLayer(data["message"]);
			searchRs();
		}
	},"json");
}