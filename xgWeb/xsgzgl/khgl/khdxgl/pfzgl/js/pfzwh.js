jQuery(function(){
	jQuery("#btn_fh").bind("click",function(){
		document.location.href='khglPfzgl.do?method=getPfzglList';
		
	});
});
function searchRs() {
	var map = getSuperSearch();
	var fpzt = jQuery("#fpzt").val();
	if (null!=fpzt&&fpzt != "") {
		map["fpzt"] = fpzt;
	}else{
		map["fpzt"] = "kfp";
	}
	jQuery("#dataTable").reloadGrid(map);
}
//���˶�������鿴
function yhck(khdxid,khlx,sfnz,khdxmc) {
	showDialog("���˶���鿴", 750, 550, "khglKhdxgl.do?method=viewKhdxList&khdxid="
			+ khdxid + "&khlx=" + khlx+"&sfnz="+sfnz+"&khdxmc="+khdxmc);
}

function khdxrsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='yhck(\""+rowObject["khdxid"]+"\",\""+rowObject["khlx"]+"\",\""+rowObject["khdxsfnz"]+"\",\""+rowObject["khdxmc"]+"\");'>"+cellValue+"</a>";
}
function pfcyStuLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='pfcy(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
}
function pfcyTeaLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='pfcy(\""+rowObject["zgh"]+"\");'>"+cellValue+"</a>";
}
//���������
function pfcySz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxid=jQuery("#khdxid").val();
	var pfzid=jQuery("#pfzid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var zgh="";
	var xh="";
	var sfqx='0';//
	if (ids.length == 0||ids.length>1) {
		sfqx='1';//��ѡ���ѡ
		zgh="all";
		xh="all";
	}
	if('0'==sfqx){
		if("2"==khlx){//���˶�������Ϊ��ʦ
			zgh=rows[0]["zgh"];
		}else{
			xh=rows[0]["xh"];
		}
	}
	var url="khglPfzgl.do?method=showPfcy&pflx="+pflx+"&sfqx="+sfqx+"&khdxrs="+ids+"&khdxid="+khdxid+"&pfzid="+pfzid+"&zgh="+zgh+"&xh="+xh+"&khlx="+khlx;
	showDialog("���ֳ�Ա", 800, 600, url);
		 	
}

//�����鰴�༶����
function pfcybjSz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxid=jQuery("#khdxid").val();
	var pfzid=jQuery("#pfzid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var zgh="";
	var sfqx='0';//
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ���û���");
		return false;
	}
	zgh=rows[0]["zgh"];
	var xm =rows[0]["xm"];
	var bmmc =rows[0]["bmmc"];
	var url="khglPfzgl.do?method=showPfcy&pflx="+pflx+"&khdxrs="+ids+"&khdxid="+khdxid+"&pfzid="+pfzid+"&zgh="+zgh+"&khlx="+khlx+"&xm="+xm+"&bmmc="+bmmc;
	url=url+"&bjfp=y";
	showDialog("���ֳ�Ա�༶", 700, 450, url);
		 	
}


//���ֳ�Ա����
function pfcy(yhm){
	var pfzid=jQuery("#pfzid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var zgh="";
	var xh="";
	if("2"==khlx){//���˶�������Ϊ��ʦ
		zgh=yhm;
	}else{
		xh=yhm;
	}
	var url="khglPfzgl.do?method=viewPfcy&pflx="+pflx+"&pfzid="+pfzid+"&zgh="+zgh+"&xh="+xh+"&khlx="+khlx;
	
	showDialog("���ֳ�Ա�鿴", 800, 600, url);
		 	
}


//����
function khdxDr(dxlx) {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	if("1"==dxlx){
		toImportDataNew("IMPORT_N930101_KHDXGL_XS");
	}else{
		toImportDataNew("IMPORT_N930101_KHDXGL_JS");
	}
	return false;

}

//��������
function  savePfcy(type){
	var api = frameElement.api,W = api.opener;
	var map = W.getSuperSearch();
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxrs=jQuery("#khdxrs").val();
	var pfzid=jQuery("#pfzid").val();
	var khdxid=jQuery("#khdxid").val();
	var khlx=jQuery("#khlx").val();
	var pflx=jQuery("#pflx").val();
	var sfqx=jQuery("#sfqx").val();//���˶����Ƿ�Ĭ��ȫѡ
	var msg=null;
	var checkMsg ="";
	if(0==ids.length){
		showAlertDivLayer("��ѡ������Ҫ�������û���");
		return false;
	}
	 if("1"!=sfqx){
		 if(type=='del'){
			 for ( var i = 0; i < rows.length; i++) {
					if("1"==rows[i]["sfydf"]){
						if(i!=0){
							checkMsg+=",";
						}
						//���˶���Ϊѧ��
						if("1"==khlx){
							checkMsg+=rows[i]["xh"];
						}else{
							checkMsg+=rows[i]["zgh"];
						}
					}
					
				}
				if(""!=checkMsg){
					showAlertDivLayer("["+checkMsg+"]"+"�������ּ�¼��������ȡ�����֣�");
					return false;
				}
		   msg="�Ƿ�ȷ��ȡ��ѡ����Ա�������ʸ�?"; 
		 }else{
		   msg="�Ƿ�ȷ����ѡ����Ա��������ֳ�Ա��?";
		 }
		}
	 else{
		 msg="�Ƿ�ȡ��ԭ�����ֳ�Ա�������ʸ����ѡ����Ա?";
	 }
		map["values"] = ids.toString();
		map["pflx"] = pflx;
		map["khlx"] = khlx;
		map["khdxid"] = khdxid;
		map["khdxrs"] = khdxrs;
		map["pfzid"] = pfzid;
		map["sfqx"] = sfqx;
	showConfirmDivLayer(msg, {
		"okFun" : function() {
	jQuery.post("khglPfzgl.do?method=savePfcy&type="+type, map
		, function(data) {
	    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 jQuery("#dataTable").reloadGrid();
		    			 var api = frameElement.api,W = api.opener;
		    				jQuery(W.document).find('#search_go').click();
		    			 
	    			}});
		},"json");
	}});
	
}

//�������ֳ�Ա�༶
function  savePfcybj(type){
	var api = frameElement.api,W = api.opener;
	var map = W.getSuperSearch();
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxrs=jQuery("#khdxrs").val();
	var pfzid=jQuery("#pfzid").val();
	var khdxid=jQuery("#khdxid").val();
	var pflx=jQuery("#pflx").val();
	var msg=null;
	var checkMsg ="";
	if(type=='del'){
		msg="�Ƿ�ȷ��ȡ��ѡ�а༶�������ʸ�?"; 
	}else{
		msg="�Ƿ�ȷ����ѡ�а༶��������ֳ�Ա��?";
	}
	if(0==ids.length){
		showAlertDivLayer("��ѡ������Ҫ�����İ༶��");
		return false;
	}
	map["pfzid"] = pfzid;
	map["khdxrs"] = khdxrs;
	map["values"] = ids.toString();
	showConfirmDivLayer(msg, {
		"okFun" : function() {
	jQuery.post("khglPfzgl.do?method=savePfcy&type="+type+"&bjfp=y", map
		, function(data) {
	    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 //jQuery("#dataTable").reloadGrid();
		    			 //var api = frameElement.api,W = api.opener;
		    			 //jQuery(W.document).find('#search_go').click();
	    				if (parent.window){
	    					refershParent();
	    				}		    			 
	    			}});
		},"json");
	}});
	
}

//���������
function qkpfr(){
	
	var map = getSuperSearch();
	var ids = jQuery("#dataTable").getSeletIds();
	
	var pfzid=jQuery("#pfzid").val();
	var khdxid=jQuery("#khdxid").val();
	var pflx=jQuery("#pflx").val();
	var khlx=jQuery("#khlx").val();
	var sfqx="1";
		map["values"] = ids.toString();
		map["pflx"] = pflx;
		map["khlx"] = khlx;
		map["khdxid"] = khdxid;
		map["khdxrs"] = ids.toString();
		map["pfzid"] = pfzid;
		map["sfqx"] = sfqx;
	showConfirmDivLayer("�Ѵ�ֳ�Ա�޷���գ��Ƿ�ȷ�����δ��ֳ�Ա��", {
		"okFun" : function() {
	jQuery.post("khglPfzgl.do?method=savePfcy&type=del", map
		, function(data) {
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
	    			 jQuery("#dataTable").reloadGrid();
	    			}});
		},"json");
	}});
}

function pfcydr() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var khdxid=jQuery("#khdxid").val();
	var pfzid=jQuery("#pfzid").val();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ���û���");
		return false;
	}
	var zgh=rows[0]["zgh"];
	var xm =rows[0]["xm"];
	
	var url="khglPfzgl.do?method=pfcydr&khdxid="+khdxid+"&pfzid="+pfzid+"&zgh="+zgh+"&xm="+xm;
	showDialog("���ֳ�Ա����", 730, 550, url);
}
