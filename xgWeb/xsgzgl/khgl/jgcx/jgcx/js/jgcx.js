
/**
 * ����鿴
 * @return
 */
function viewJg(){
	var xxdm=jQuery("#xxdm").val();
	var url = "khgljgcx.do?method=xmjgList";
	if("10029"==xxdm||"12424"==xxdm){
		url= "khgljgcx.do?method=xmjgListOfSdty";
	}
	var rows = jQuery("#dataTable").getSeletRow();
	
	if(rows.length == 1){
		url+="&xmid="+rows[0]["xmid"]+"&khlx="+rows[0]["khlx"];
		document.location.href=url;
	}else{
		showAlertDivLayer("��ѡ��һ����Ŀ");
		return false;
	}
	
}


//����
function exportConfig(){
	var dclb = jQuery("#dclb").val();
	var DCCLBH='khgl_jgcx_ckJs.do';
	if('xs' == dclb){
		DCCLBH='khgl_jgcx_ckXs.do';
	}
	customExport(DCCLBH, exportData);
}

function exportData(){
	
	var dclb = jQuery("#dclb").val();
	var xmid = jQuery("#xmid").val();
	var khlx = jQuery("#khlx").val();
	
	var DCCLBH='khgl_jgcx_ckJs.do';
	
	if('xs' == dclb){
		DCCLBH='khgl_jgcx_ckXs.do';
	}
	setSearchTj();//���ø߼���ѯ����
	var url = "khgljgcx.do?method=exportData&dcclbh=" + DCCLBH+"&xmid="+xmid+"&khlx="+khlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function exportConfigOfSdty(){
	
	var dclb = jQuery("#dclb").val();
	var xmid = jQuery("#xmid").val();
	var khlx = jQuery("#khlx").val();
	setSearchTj();//���ø߼���ѯ����
	var url = "khgljgcx.do?method=exportConfigOfSdty&xmid="+xmid+"&khlx="+khlx+"&dclb="+dclb;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



/**
 * ���ͳ��ѧ��
 * @return
 */
function dftj(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var xmid = jQuery("#xmid").val();
	var khlx = jQuery("#khlx").val();
	var dclb = jQuery("#dclb").val();
	
	if(rows.length == 1){
		var url = "khgljgcx.do?method=dftjList&xmid="+xmid+"&khlx="+khlx+"&khdxr=";
		if('xs' == dclb){
			url+=rows[0]["xh"]+"&bmdm="+rows[0]["xydm"];
		}else{
			url+=rows[0]["zgh"]+"&bmdm="+rows[0]["bmdm"];
		}
		showDialog("���ͳ��",500,400,url,{max:false,min:false});
	}else{
		showAlertDivLayer("��ѡ��һ����");
		return false;
	}
}

/**
 * ѧ���԰����λ��ܴ�ӡ
 * �㽭��ҵ��ʦѧԺ���Ի�
 * @return
 */
function xsdbzrhzDy(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var xmid = jQuery("#xmid").val();
//	var khlx = jQuery("#khlx").val(); �����￼������������2ѽ
	var khdxrs = [];
	for(var i=0;i<rows.length;i++){
		khdxrs.push(rows[i]["zgh"]);
	}
	
	if(rows.length == 0){
		showAlertDivLayer("����ѡ���¼��");
		return false;
	}else{
		var url = "khgljgcx.do?method=xsdbzrhzDy&xmid="+xmid+"&khdxrs="+khdxrs;
		window.open(url);
	}
}
