/*����*/
jQuery(function() {
	onShow();
});

/*�޸�ģʽ���ѱ����������*/
var defaultShlc;

function onShow() {
	/*�������Ƽ������������̽�����ʾ*/
	jQuery("[name=shlc]").change(function() {
				setKzlc(jQuery(this).val());
			});
	defaultShlc = jQuery("[name=shlc]:checked").val();
	jQuery("[name=shlc]").change();
	/*�������䷽ʽ��ֵ*/
	jQuery("input:radio[name=rsfpfsView][value=" + jQuery("#rsfpfs").val()+ "]").attr("checked", "checked");
}

function setKzlc(value) {
	if (value == "") {
		jQuery("#rskzjbTd").html("");
		return;
	}
	var url = "xpj_xmwh.do?method=xmwhShfw";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var sHtml = "";
		var radio1 = "";
		if (data != null) {
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
			}
		}
		sHtml += radio1;
		jQuery("#rskzjbTd").html(sHtml);
		/*�������Ƽ���*/
		jQuery("input:radio[name=rskzjbView][value=" + jQuery("#rskzjb").val()+ "]").attr("checked", "checked");	
		if (defaultShlc == value) {
		}
		/*��������״̬*/
		setSpzt();
	}, 'json');
}


/**
 * �������״̬�����ò�����
 * @return
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery(".prompt").css("display","");
		jQuery("table input,select").not(jQuery("#xssx")).not(jQuery("#ywmc")).attr("disabled", "disabled");
	}
}



/*��Ŀ���ơ���Ŀ���͡���Ŀ���ʡ����뿪�� ģ����ѯ*/
function query(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	map["sqkg"]= jQuery("#sqkg").val();
	jQuery("#dataTable").reloadGrid(map);
}

/*������Ŀ����ҳ��*/
function add() {
	var url = "xpjpy_xmwh.do?method=xmwhAdd";
	var title = "��Ŀ����";
	showDialog(title, 650, 565, url);
}

/*������Ŀ���ӱ���*/
var ids = "xmmc-lxdm-xzdm-xssx-shlc";
function saveFormAdd(){
	
	if(!checkNotNull(ids)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	// �������ؼ�������ֵ���Ա��ύ��
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	if (rskzjb == null || rskzjb == "") {
		showAlert("��ѡ���������Ƽ���");
		return false;
	}
	
	/*�������䷽ʽ����ֵ���Ա��ύ��*/
	var rsfpfs = jQuery("input:radio[name=rsfpfsView]:checked").val();
	if (rsfpfs == null || rsfpfs == "") {
		showAlert("[�������䷽ʽ]������Ϊ�գ�");
		return false;
	}
	jQuery("#rsfpfs").val(rsfpfs);
	
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	/*�ж��Ƿ���������*/
	var sfrssz = jQuery("input:radio[name=sfrssz]:checked").val();
	if (sfrssz == null || sfrssz == "") {
		showAlert("��ѡ���Ƿ�����������");
		return false;
	}
	
	var url = "xpjpy_xmwh.do?method=saveFormAdd";
	
	jQuery("table input,select").attr("disabled",false);
	
	ajaxSubFormWithFun("zjdxXmwhForm", url, function(data) {
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

/*������Ŀ�޸�ҳ��*/
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpjpy_xmwh.do?method=xmwhUpdate&xmdm=' + rows[0]["xmdm"];
		var title = "��Ŀ�޸�";
		showDialog(title, 650, 565, url);
	}
}

/*������Ŀ�޸ı���*/
function saveFormUpdate(){
	if(!checkNotNull(ids)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	// �������Ƽ�������ֵ���Ա��ύ��
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	if (rskzjb == null || rskzjb == "") {
		showAlert("��ѡ���������Ƽ���");
		return false;
	}
	
	// �������䷽ʽ����ֵ���Ա��ύ��
	var rsfpfs = jQuery("input:radio[name=rsfpfsView]:checked").val();
	if (rsfpfs == null || rsfpfs == "") {
		showAlert("[�������䷽ʽ]������Ϊ�գ�");
		return false;
	}
	jQuery("#rsfpfs").val(rsfpfs);
	
	
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	/*�ж��Ƿ���������*/
	var sfrssz = jQuery("input:radio[name=sfrssz]:checked").val();
	if (sfrssz == null || sfrssz == "") {
		showAlert("��ѡ���Ƿ�����������");
		return false;
	}
	
	/*����URL*/
	var url = "xpjpy_xmwh.do?method=saveFormUpdate";
	/*ѡ��*/
	jQuery("table input,select").attr("disabled",false);
	ajaxSubFormWithFun("zjdxXmwhForm", url, function(data) {
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

/*������Ŀɾ������*/
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xpjpy_xmwh.do?method=xmwhDelete",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//���������ʽӰ�죬��ɫ����д��Ԫ����
function setColor(value,status){
	var color;
	if(status == '1'){
		color = "#004400";
	}else{
		color = "red";
	}
	return value = "<font color='"+color+"'>" + value + "</font>";
}

/*������뿪��*/
function setSqkg(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var value = "δ����";
	var status = '0';
	var color;
	if(cellValue == '1'){
		var currDate = jQuery("#currDate").val();
		if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
			
		}else{
			value = "�ѿ���";
			status = '1';
		}
	}
	value = setColor(value,status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+xmdm+"\");' >"+value+"</a>";
	return value;
}

/*�����˿���*/
function setShkg(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var value = "δ����";
	var status = '0';
	var color;
	if(cellValue == '1'){
		var currDate = jQuery("#currDate").val();
		if(rowObject.shkssj != null && currDate < rowObject.shkssj || rowObject.shjssj != null && currDate > rowObject.shjssj ){
		}else{
			value = "�ѿ���";
			status = '1';
		}
	}
	value = setColor(value,status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\""+xmdm+"\");' >"+value+"</a>";
	return value;
}

/*ʱ�俪��*/
function sjkg(xmdm) {
	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'xpjpy_xmwh.do?method=xmwhSjkg&xmdm=' + xmdm;
	var title = "��Ŀʱ�����";
	showDialog(title, 600, 355, url);
}

/*����Ƶ�����*/
function jxfz() {
	var url = 'xpjpy_xmwh.do?method=xmwhJxfz';
	var title = "�����";
	showDialog(title, 390, 230, url);
}

/*�����������*/
function setTjsz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var xmmc = rowObject.xmmc;
	var value;
	if(cellValue == '1'){
		value = "������";
	}else{
		value = "δ����";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return tjsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

/*�������*/
function jdsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqkg = rows[0]["sqkg"];
		xmmc = rows[0]["xmmc"];
	}

	var url = 'xpjpy_xmwh_jdsz.do?method=xmwhJdszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "���ɼ������";
	showDialog(title, 500, 280, url);
}

/*��ѡһ����¼����������ð�ť*/
var tjszDialog ;
function tjsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqkg = rows[0]["sqkg"];
		xmmc = rows[0]["xmmc"];
	}
	var url = 'xpjpy_xmwh_tjsz.do?method=xmwhTjszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "��������";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}

/*��������*/
function bbsz(type){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
	} else {
		var xmdm = rows[0]["xmdm"];
		document.location.href="xpjpy_xmwh_bbsz.do?method=bbylList&bblx="+type+"&xmdm="+rows[0]["xmdm"];
	}
}


/*
 *��������
 */
function setRssz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var rsfpfs = rowObject.rsfpfs;
	var xmmc = rowObject.xmmc;
	var xmje = rowObject.xmje;
	var value;
	if(cellValue >= 1){
		cellValue = 1; //����Ĵ���Ϊ���ã����ｫ�������»�ԭ
		value = "������";
	}else{
		value = "δ����";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
	return value;
}

/*��������*/
function rssz(xmdm,sqkg,rsfpfs,xmmc,xmje) {
	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		rsfpfs = rows[0]["rsfpfs"];
		xmmc = rows[0]["xmmc"];
		xmje=rows[0]["xmje"];
	}
    
	if (rsfpfs == null || rsfpfs == "all" || rsfpfs == "" || rsfpfs == "null") {
		showAlertDivLayer("������[�޸�]������[�������䷽ʽ]��");
		return false;
	}
	var url = 'xpjpy_xmwh_rssz.do?method=xmwhRsszCx';
	url += "&xmdm=" + xmdm;
	url += "&rsfpfs=" + rsfpfs;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	url += "&xmje=" + xmje;
	var title = "��������";
	showDialog(title, 750, 520, url,{close:function(){query();}});
}