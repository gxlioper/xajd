function add(){
	var rows=jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		alertInfo("��ѡ��һ����Ҫ���ߵļ�¼��");
	}else{
		if(rows[0]["shztmc"]!="δ�ύ����"){
			alertInfo("�����ظ��������ߣ�");
			return false;
		}
		var url = "";
		if(rows[0]['shztmc']=='�˻�' ){
			url = 'wjcf_cfsssq.do?method=cfsssqAdd&cfid='+rows[0]["cfid"]+'&ssid='+rows[0]["ssid"]+'&xh='+rows[0]["xh"]+'&returnflag=return';
		}else{
			url = 'wjcf_cfsssq.do?method=cfsssqAdd&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"];
		}
		showDialog("������������",760,500,url);
	}
}

function save(url){
	var sqly=jQuery("#sqly").val();
	if(sqly==""){
		showAlert("����д�������ɣ�");
		return false;
	}
	
	var returnflag = jQuery("#returnflag").val();
	ajaxSubFormWithFun("cfsssqForm",url,function(data){
	  	 if(data["message"]=="����ɹ���"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  				if (parent.window){
	  					refershParent();
	  				}
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
	});
}

function update(){
	var rows=jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if(rows[0]["shztmc"]=="δ�ύ����"){
			alertInfo("δ�ύ�������룡");
			return false;
		}
		if(rows[0]["shzt"]!="0"&&rows[0]["shzt"]!="3"){
			alertInfo("������������ˣ������޸ģ�");
			return false;
		}
		showDialog("���������޸�",760,500,'wjcf_cfsssq.do?method=cfsssqUpdate&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"]+'&ssid='+rows[0]["ssid"]);
	}
}

function updatesave(url){
	var sqly=jQuery("#sqly").val();
	if(sqly==""){
		showAlert("����д�������ɣ�");
		return false;
	}
	
	ajaxSubFormWithFun("cfsssqForm",url,function(data){
	  	 if(data["message"]=="����ɹ���"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  				if (parent.window){
	  					refershParent();
	  				}
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
		});
	
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();

	if(ids.length==0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	}else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
	}else if(rows[0]['shztmc']!='δ�ύ'){
		showAlertDivLayer("��ѡ��δ�ύ�ļ�¼��");
		return false;
	}else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("wjcf_cfsssq.do?method=cfsssqDelete",
					{values:ids.toString(),
					 splcid : rows[0]['splcid'],
					 shzt : rows[0]['shzt']},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
			}});
	}
	
}

//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids[0]==null){
		alertInfo("��δ�������ߣ����ܸ������̣�");
		return false;
	}
	
	var shzt = rows[0]["shztmc"];
	if ("δ�ύ" == shzt){
				showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
				return false;
			}
	showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['ssid']+"&splc="+rows[0]['splcid']);
}


function showCfqxFlag(cflbdm){
	//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
	if(jQuery("#xxdm").val()=='13011') return false;
	
	if(cflbdm==""){return false;}
	jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
		jQuery("#showCfqx").html(data["message"]);
	},'json');
}
