function add(){
	var rows=jQuery("#dataTable").getSeletRow();
	if(rows.length!=1){
		alertInfo("��ѡ��һ����Ҫ"+jQuery("#xbmc").val()+"�ļ�¼��");
	}else{
		if(rows[0]["shztmc"]!="δ�ύ����"){
			alertInfo("�����ظ�����"+jQuery("#xbmc").val()+"��");
			return false;
		}
		showDialog("����"+jQuery("#xbmc").val()+"����",760,500,'wjcf_cfjcsq.do?method=cfjcsqAdd&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"]);
	}
}
function save(url){

    if(!check("sqly-bjyj-qksm-filepath-filepath2")){
        return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
    }
	ajaxSubFormWithFun("cfjcsqForm",url,function(data){
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
			alertInfo("Υ�ͼ�¼��û����"+jQuery("#xbmc").val()+"�������޸ģ�");
			return false;
		}
		if(rows[0]["shzt"]!="0"&& rows[0]["shzt"]!="3"){
			alertInfo(""+jQuery("#xbmc").val()+"��������ˣ������޸ģ�");
			return false;
		}
		showDialog("����"+jQuery("#xbmc").val()+"�޸�",760,500,'wjcf_cfjcsq.do?method=cfjcsqUpdate&cfid='+rows[0]["cfid"]+'&xh='+rows[0]["xh"]+'&jcid='+rows[0]["jcid"]);
	}
}

function updateSave(url){
    if(!check("sqly-bjyj-qksm-filepath-filepath2")){
        return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
    }
	ajaxSubFormWithFun("cfjcsqForm",url,function(data){
	  	 if(data["message"]=="����ɹ���" ||data["message"]=="�ύ�ɹ���"  ){
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
	if(ids.length==0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("wjcf_cfjcsq.do?method=cfjcsqDelete",{values:ids.toString()},function(data){
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
		alertInfo("��δ����"+jQuery("#xbmc").val()+"�����ܸ������̣�");
		return false;
	}
	
	var shzt = rows[0]["shztmc"];

	if ("δ�ύ" == shzt){
				showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
				return false;
	}
	
	showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['jcid']+"&splc="+rows[0]['splcid']);
}

function showCfqxFlag(cflbdm){
	//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
	if(jQuery("#xxdm").val()=='13011') return false;
	
	if(cflbdm==""){return false;}
	var cfqx = "";
	jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
		jQuery("#showCfqx").html(data["message"]);
	},'json');
}

function check(ids){
    var id=ids.split("-");
    for(var i=0;i<id.length;i++){
        var lddm=jQuery("#"+id[i]).val();
        if(lddm==null||""==lddm){
            //alert(id[i]);
            return false;
        }
    }
    return true;
}