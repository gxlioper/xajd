function query(obj,shlx){
	jQuery("#comp_title li").removeClass();
	jQuery(obj).parent().attr("class","ha");
	jQuery("#shlx").val(shlx);
	if(shlx=='ysh'){
		jQuery("#dataTable").initGrid(gridSetting2);
		jQuery("#btn_qxsh").show();
		jQuery("#btn_sh").hide();
	}else{
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#btn_qxsh").hide();
		jQuery("#btn_sh").show();
	}
	searchRs();
}


//���

function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ��˵ļ�¼��");
	}  else{
		showDialog("Υ�ʹ������",820,500,'wjcf_cfsh.do?method=cfsh&ywid='+rows[0]["ywid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"]);
	}
}

//������ˣ����������һ����˵�ʱ������д�����ĺź�ʱ�䣬������ʱȡ��
function go_sh_12930(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1){
		showDialog("Υ�ʹ������",760,500,'wjcf_cfsh.do?method=cfsh&ywid='+rows[0]["ywid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"]);
	}else if(rows.length == 0){
		alertInfo("��ѡ����Ҫ��˵ļ�¼��");
	}else{
		 var x=0; var y=0; var z=0;
		 for (var k in rows ) {
	        for (var i in rows) {
	            if (rows[k]["cflbdm"] != rows[i]["cflbdm"])
	            	x+=1;
	            if (rows[k]["gwid"] != rows[i]["gwid"])
	            	y+=1;
	            if (rows[k]["cffwqx"] != rows[i]["cffwqx"])
	            	z+=1;
	        }
		 }
		if(x!=0){
			alertInfo("��ѡ����ͬ�������ļ�¼��");
		}else if(y!=0){
			alertInfo("��ѡ����ͬ��˼���ļ�¼��");
		}else if(z!=0){
			alertInfo("��ѡ����Ȩ����ͬ�ļ�¼��");
		}else{
			if(rows[0]["gwid"]==rows[0]["cffwqx"]){
				showDialog('Υ�ʹ����������',700,300,'wjcf_cfsh.do?method=cfplsh&cffw=1&cflbdm='+rows[0]["cflbdm"] );
			}else{
				showDialog('Υ�ʹ����������',700,300,'wjcf_cfsh.do?method=cfplsh&cffw=0&cflbdm='+rows[0]["cflbdm"]);
			}
			
		}
		
	}
}

//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['ywid']+"&splc="+rows[0]['splcid']);
	}
}

/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh_bak(shzt,message){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	//�����һ�����ͨ��ʱ���ж��Ƿ���д�����ĺż�����ʱ��
	if((shzt=="1"||shzt==1)){
		if(jQuery("#isZhgw").val()=="true"){
			if(jQuery("#cfwh").val()==""){
				showAlertDivLayer("����д�����ĺţ�");
				return false;
			}
			if(jQuery("#cfsj").val()==""){
				showAlertDivLayer("����д����ʱ�䣡");
				return false;
			}
		}
	}
	if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splcid").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}

	//�ύ���
	showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
		var url = "wjcf_cfsh.do?method=cfsh&type=save";
		ajaxSubFormWithFun("cfshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
}

//��ѯ
function searchRs(){
	var map = getSuperSearch();
	map["shlx"] = jQuery.trim(jQuery("#shlx").val());
	jQuery("#dataTable").reloadGrid(map);
}
//������� �����մ�
function savePlsh(shzt,shyj,cffwqx,cfwh,cfsj,cflbdm,kzzd1,cfdqsj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcid = new Array();

	jQuery.each(rows,function(i,row){
		guid.push(row["ywid"]);
		gwid.push(row["gwid"]);
		xhs.push(row["xh"]);
		splcid.push(row["splcid"]);
	});

	jQuery.post(
		"wjcf_cfsh.do?method=cfplsh&type=save",
		{id:guid,
		 gwids:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 shzt:shzt,
		 cfwh:cfwh,
		 cfsj:cfsj,
		 ggqlb:kzzd1,
		 cfdqsj:cfdqsj,
		 gghlb:cflbdm,
		 cffw:cffwqx,
		 splcids:splcid
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	)
}

function getCfjdWord() {
	var ywid="";
	var url=null;
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length ==0) {
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
		return false;
	}else if(rows.length ==1){
	ywid = rows[0]["ywid"];
	var url = "wjcf_cfjg.do?method=doPrintCfjdWord&&ywid=" + ywid;
	}
	else{
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				ywid +=rows[i]["ywid"];
			}else{
				ywid +=rows[i]["ywid"]+",";
			}
		}
		url = "wjcf_cfsh.do?method=doPrintCfjdWordZip&&ywid=" + ywid;
	}
	window.open(url);

}
