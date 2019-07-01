/*��ѯ����*/
function query(){
	var map = {};
	map["xmmc"] = jQuery.trim(jQuery("#xmmc").val());
	map["lxdm"]= jQuery("#lxdm").val();
	map["xzdm"]= jQuery("#xzdm").val();
	map["sqkg"]= jQuery("#sqkg").val();
	jQuery("#dataTable").reloadGrid(map);
}

/*ѧԺ��������*/
function setRsszXy(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var rsfpfs = rowObject.rsfpfs;
	var xmmc = rowObject.xmmc;
	var xmje = rowObject.xmje;
	var value;
	if(cellValue >= 1){
		cellValue = "1";  //����Ĵ���Ϊ���ã����ｫ�������»�ԭ
		value = "������("+rowObject.rssz+"��)";
	}else{
		value = "δ����";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+rsfpfs+"\",\""+xmmc+"\",\""+xmje+"\");' >"+value+"</a>";
	return value;
}

/*���������ʽӰ�죬��ɫ����д��Ԫ����*/
function setColor(value,status){
	var color;
	if(status == '1'){
		color = "#004400";
	}else{
		color = "red";
	}
	return value = "<font color='"+color+"'>" + value + "</font>";
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
	var url = 'xpjpy_xyrssz.do?method=xyrszCx';
	url += "&xmdm=" + xmdm;
	url += "&rsfpfs=" + rsfpfs;
	url += "&xmmc=" + xmmc;
	url += "&xmje=" + xmje;
	var title = "��������";
	showDialog(title, 750, 520, url,{close:function(){query();}});
}