var tjszDialog ;
var DCCLBH='xpj_xmwh.do?method=xmwhCx';



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

/*
 *���뿪�� 
 */
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

/*
 *��˿��� 
 */
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

/*
 *��������
 */
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

/*
 *��������
 */
function setRssz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var sqxq = rowObject.sqxq;
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
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+sqxn+"\",\""+sqxq+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
	return value;
}

/*
 *���ѧԺ��������
 */
function setRsszXy(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var sqxq = rowObject.sqxq;
	var rsfpfs = rowObject.rsfpfs;
	var xmmc = rowObject.xmmc;
	var xmje = rowObject.xmje;
	var value;
	if(cellValue >= '1'){
		cellValue = "1";  //����Ĵ���Ϊ���ã����ｫ�������»�ԭ
		value = "������("+rowObject.rssz+"��)";
	}else{
		value = "δ����";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+sqxn+"\",\""+sqxq+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
	return value;
}


/*
 *�������
 */
function setJdsz(cellValue,rowObject){
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
	value = "<a  href='javascript:void(0);' onclick='return jdsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

/*
 *��˵�����������
 */
function setShsz(cellValue,rowObject){
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
	value = "<a  href='javascript:void(0);' onclick='return shsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

function query(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	map["sqkg"]= jQuery("#sqkg").val();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
        var xmxz = jQuery("#xmxz").val();
		var url = 'xpj_xmwh.do?method=xmwhZjXg&xmxz='+xmxz+'&xmdm=' + rows[0]["xmdm"];
		var title = "��Ŀ�޸�";
		showDialog(title, 680, 480, url);
	}
}

function add() {
    var xmxz = jQuery("#xmxz").val();
	var url = "xpj_xmwh.do?method=xmwhZjXg&xmxz="+xmxz;
	var title = "��Ŀ����";
	showDialog(title, 720, 460, url);
}

/*
 * ʱ�俪��
 */
function sjkg(xmdm) {
	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh.do?method=xmwhSjkg&xmdm=' + xmdm+'&xmxz='+xmxz;
	var title = "��Ŀʱ�����";
	showDialog(title, 600, 380, url);
}

/*��������*/
function tjsz(xmdm,sqkg,xmmc) {

	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqkg = rows[0]["sqkg"];
		xmmc = rows[0]["xmmc"];
        var xmxz = jQuery("#xmxz").val();
	}
	var url = 'xpj_xmwh_tjsz.do?method=xmwhTjszCx&xmxz='+xmxz;
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "��������";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}


/*��������*/
function rssz(xmdm,sqkg,sqxn,sqxq,rsfpfs,xmmc,xmje) {
	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
		sqxn = rows[0]["sqxn"];
		sqxq = rows[0]["sqxq"];
		sqkg = rows[0]["sqkg"];
		rsfpfs = rows[0]["rsfpfs"];
		xmmc = rows[0]["xmmc"];
		xmje=rows[0]["xmje"];
	}
    
	if (rsfpfs == null || rsfpfs == "all" || rsfpfs == "" || rsfpfs == "null") {
		showAlertDivLayer("������[�޸�]������[�������䷽ʽ]��");
		return false;
	}
	var czfs = jQuery("#czfs").val();
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh_rssz.do?method=xmwhRsszCx&czfs='+czfs;
	url += "&xmdm=" + xmdm;
    url += "&xmxz=" + xmxz;
	url += "&rsfpfs=" + rsfpfs;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	url += "&xmje=" + xmje;
	var title = "��������";
	showDialog(title, 750, 520, url,{close:function(){query();}});
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
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh_jdsz.do?method=xmwhJdszCx';
	url += "&xmdm=" + xmdm;
    url += "&xmxz=" + xmxz;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "���ɼ������";
	showDialog(title, 500, 280, url);
}


/*��˵�����������*/
function shsz(xmdm,sqkg,xmmc) {
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
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh_tzsz.do?method=xmwhShszCx';
	url += "&xmdm=" + xmdm;
    url += "&xmxz=" + xmxz;
	url += "&xmmc=" + encodeURI(encodeURI(xmmc));
	var title = "�����������";
	showDialog(title, 500,  235, url);
}

/*�����*/
function jxfz() {
    var xmxz = jQuery("#xmxz").val();
	var url = 'xpj_xmwh.do?method=xmwhJxfz&xmxz='+xmxz;
	var title = "�����";
	showDialog(title, 390, 230, url);
}

function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",{"okFun":function(){
			var url = "xpj_xmwh.do?method=xmwhSc";
			jQuery.post(url, {
				values : ids.toString()
			}, function(data) {
				if(data["success"] == false){
					showAlertDivLayer(data["message"]);
				}else{
					showAlertDivLayer(data["message"], {},{"clkFun": function(tag) {
						jQuery("#dataTable").reloadGrid();
					}});
				}
			}, 'json');
		}});
	}
}

//����
function exportConfig(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	var xmmc = jQuery.trim(jQuery("#xmmc").val());
	var lxdm = jQuery("#lxdm").val();
	var xzdm = jQuery("#xzdm").val();
    var xmxz = jQuery("#xmxz").val();
	
	var url = "xpj_xmwh.do?method=exportData&dcclbh=" + DCCLBH+"&xmmc="+xmmc+"&lxdm="+lxdm+"&xzdm="+xzdm+"&xmxz="+xmxz;//dcclbh,�������ܱ��
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

