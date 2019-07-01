
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["sqid"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

function add(){
	jQuery.post("szdw_zwsq.do?method=yzZwsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("ѧ���ɲ�����ְ������",760,505,"szdw_zwsq.do?method=zwSq");
		}else{
			alertInfo(message);
		}
	},'json');
	
}

/**
 * ɾ������
 * @return
 */
function del(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
		return false;
	} else{
		for(i=0 ; i< rows.length; i++){
			if(rows[i]['shztdm'] != '0' && rows[i]['shztdm'] != '3'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
			jQuery.post("szdw_zwsq.do?method=deleteZwsqAction",{sqids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function view(sqid,xh){
	showDialog("ѧ���ɲ�����ְ��鿴",760,460,'szdw_zwsh.do?method=zwsh&type=ck&sqid='+sqid+"&xh="+xh+"&tt="+new Date().getTime());
}

function save_bak(method){
	if(yanzheng()){
		jQuery.post("szdw_zwsq.do?method=yzZwsq",{zwid:jQuery("#zwid").val(),xh:jQuery("#xh").val()},function(data){
			if(data.message!="true"){
				alertInfo(data.message);
			}else{
				//��֤�ɹ�����ܽ��б���
				var url = "szdw_zwsq.do?method="+method+"&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
							iFClose();
						}});
				});
			}
		},'json');
		
	}
}
function yanzheng(){
	var xh = jQuery("#xh").val();
	var zwid = jQuery("#zwid").val();
	var sqly = jQuery("#sqly").val();
	if(xh=="" || xh == undefined){
		alertInfo("��ѡ��һ��ѧ��");
	}else if(zwid=="" || zwid == undefined){
		alertInfo("��ѡ�������ְ��");
	}else if(sqly.length>=200){
		alertInfo("�������ɲ��ܳ���200��");
	}else{
		return true;
	}
	return false;
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function on_change(){
	jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView&lxdm="+jQuery("#lxdm").val());
}
function on_getZwwh_model(){
	jQuery("#tbody_zwxx").load("szdw_xsgb_zwwh.do?method=zwView&zwid="+jQuery("#zwid").val());
}
//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		if ("δ�ύ" == shzt){
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
//ȡ������
function qx_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length>=1){
		var sqids = "";
		//��ȡѡ��������Ų��� ,ƴ��
		var check = false;
		jQuery(rows).each(function(i){
			sqids = sqids+","+rows[i]["sqid"];
			var shzt = rows[i]['shzt'];
			if(shzt!='�����'){
				alertInfo("���������ѽ����������̲��ܳ���");
				return false;
			}else{
				check = true;
			}
		});
		if(check){
			confirmInfo("��ȷ��Ҫȡ��"+rows.length +"�������¼��?",function(ty){
				//alert(ty);
				if(ty=="ok"){
					jQuery.post("szdw_zwsq.do?method=qxsq",{sqids:sqids},function(data){
						if(data["success"]=="true"){
							alertInfo("ȡ������ɹ�");
						}else{
							alertInfo("ȡ������ʧ��");
						}
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
		}
		
		
	}else{
		alertInfo("��ѡ��һ����Ҫȡ���ļ�¼");
	}
}

//�޸�����
function update() {
	
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		var shzt = rows[0]["shzt"];
		if ("�����" == shzt){
			showAlertDivLayer("����Ϣ��¼��������У������޸ģ�");
			return false;
		}
		if ("ͨ��" == shzt){
			showAlertDivLayer("����Ϣ��¼����Ѿ�ͨ���������޸ģ�");
			return false;
		}
		if ("��ͨ��" == shzt){
			showAlertDivLayer("����Ϣ��¼����Ѿ���ͨ���������޸ģ�");
			return false;
		}
		var url = 'szdw_zwsq.do?method=zwsqXg&sqid='+rows[0]["sqid"]+'&xh='+rows[0]["xh"];
		var title = "�޸�ѧ���ɲ�����ְ��";
		showDialog(title, 720, 505, url);
		
	}
	
}

//��ӡ������
function printXsgbbab(url){
	var xh="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				xh +=rows[i]["xh"];
			}else{
				xh +=rows[i]["xh"]+",";
			}
		}
		var url = url + "&xh=" +xh;
		window.open(url);
	}
}