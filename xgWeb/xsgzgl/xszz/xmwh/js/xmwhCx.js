var tjszDialog ;
var gridSetting = {
	caption : "��Ŀ�б�",
	pager : "pager",
	url : "xszz_xmwh.do?method=xmwhCx&type=query",
	colList : [
	    {label : '��Ŀ����',name : 'xmdm',index : 'xmdm',key : true,hidden : true}, 
	    {label : 'ѧ��',name : 'sqxn',index : 'sqxn',hidden : true},
	    {label : 'ѧ��',name : 'sqxq',index : 'sqxq',hidden : true},
	    {label : '���뿪ʼʱ��',name : 'sqkssj',index : 'sqkssj',hidden : true},
	    {label : '�������ʱ��',name : 'sqjssj',index : 'sqjssj',hidden : true},
	    {label : '�������Ʒ�Χ',name : 'rskzfw',index : 'rskzfw',hidden : true},
	    {label : '����Ƿ�ѧ������',name : 'jesfxssq',index : 'jesfxssq',hidden : true},
		{label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '25%'}, 
		{label : '������',name : 'lbdm',index : 'lbdm',hidden : true},
		{label : '��Ŀ���',name : 'lbmc',index : 'lbmc',width : '15%'},
		{label : 'ѧ�����',name : 'xslb',index : 'xslb',hidden : true},
		{label : '���',name : 'je',	index : 'je',width : '10%',noSort:true ,formatter:jesfkt},
		{label : '��������',	name : 'sqkg',index : 'sqkg',width : '10%',	formatter:setSqkg}, 
		{label : '��������',name : 'tjsz',index : 'tjsz',width : '10%',formatter:setTjsz}, 
		{label : '��������',name : 'rssz',index : 'rssz',width : '10%',formatter:setRssz},
		{label : '���ɼ������',name : 'jdsz',index : 'jdsz',width : '10%',formatter:setJdsz},
		{label : '������������',name : 'shsz',index : 'shsz',width : '10%',formatter:setShsz}, 
		{label : '��������',name : 'jfsz',index : 'jfsz',width : '10%',formatter:setJfsz}
		],
	sortname : "xmmc",
	sortorder : "asc"
}

jQuery(function() {
	jQuery("#dataTable").initGrid(gridSetting);
});

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
		if((null==rowObject.sqkssj||currDate >= rowObject.sqkssj) && (null==rowObject.sqjssj ||currDate <= rowObject.sqjssj) ){
			value = "������";
			status = '1';
		}
	}
	value = setColor(value,status);
	value = "<a  href='javascript:void(0);' onclick='return jbsz(\""+xmdm+"\");' >"+value+"</a>";
	return value;
}
function jesfkt(cellValue,rowObject){
	var jesfxssq=rowObject.jesfxssq;
	var value='';
	if(jesfxssq=='1'){
		value=cellValue+"<font color='red'>(��)</font>";
	}else{
		value=cellValue;
	}
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
function setJfsz(cellValue,rowObject){
	var jfsz = rowObject.jfsz;
	var value;
	if(cellValue == '1'){
		value = "������";
	}else{
		value = "δ����";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return szjf(\""+rowObject["xmdm"]+"\");'>"+value+"</a>";
	return value;
}

function szjf(xmdm){
	var url = 'xszz_xmwh.do?method=xmwhJfsz&xmdm=' + xmdm;
	showDialog("��������", 680, 550, url);
}


/*
 *��������
 */
function setRssz(cellValue,rowObject){
	var xmdm = rowObject.xmdm;
	var sqkg = rowObject.sqkg;
	var sqxn = rowObject.sqxn;
	var sqxq = rowObject.sqxq;
	var rskzfw = rowObject.rskzfw;
	var xmmc = rowObject.xmmc;
	var xslb=rowObject.xslb;
	var value;
	if(cellValue == '1'){
		value = "������";
	}else{
		value = "δ����";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return rssz(\""+xmdm+"\",\""+sqkg+"\",\""+sqxn+"\",\""+sqxq+"\",\""+rskzfw+"\",\""+xmmc+"\",\""+xslb+"\");' >"+value+"</a>";
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
	map["sqzqdm"] = jQuery.trim(jQuery("#sqzqdm").val());
	map["lbdm"] = jQuery("#lbdm").val();
	jQuery("#dataTable").reloadGrid(map);
}

function update() {
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xszz_xmwh.do?method=xmwhXg&xmdm=' + rows[0]["xmdm"];
		var title = "��Ŀ�޸�";
		showDialog(title, 490, 400, url);
	}
}

function add() {
	var url = "xszz_xmwh.do?method=xmwhZj";
	var title = "��Ŀ����";
	showDialog(title, 490, 400, url);
}

/*
 * ��������
 */
function jbsz(xmdm) {
	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'xszz_xmwh.do?method=xmwhJbsz&xmdm=' + xmdm;
	var title = "��������";
	showDialog(title, 680, 600, url);
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
	}
	var url = 'xszz_xmwh_tjsz.do?method=xmwhTjszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	var title = "��������";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}


/*��������*/
function rssz(xmdm,sqkg,sqxn,sqxq,rskzfw,xmmc,xslb) {
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
		rskzfw = rows[0]["rskzfw"];
		xmmc = rows[0]["xmmc"];
		xslb = rows[0]["xslb"];
		
	}
	if (rskzfw == null  || rskzfw == "null"  || rskzfw == "all" || rskzfw == "") {
		showAlertDivLayer("������[��������]������[�������Ʒ�Χ]��");
		return false;
	}

	var url = 'xszz_xmwh_rssz.do?method=xmwhRsszCx';
	url += "&xmdm=" + xmdm;
	url += "&xn=" + sqxn;
	url += "&xq=" + sqxq;
	url += "&rskzfw=" + rskzfw;
	url += "&xmmc=" + xmmc;
	url += "&xslb=" + xslb;
	var title = "��������";
	showDialog(title, 750, 545, url,{close:function(){query();}});
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

	var url = 'xszz_xmwh_jdsz.do?method=xmwhJdszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	var title = "���ɼ������";
	showDialog(title, 600, 235, url);
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

	var url = 'xszz_xmwh_shsz.do?method=xmwhShszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	var title = "��˵�����������";
	showDialog(title, 600, 400, url);
}


function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",{"okFun":function(){
			var url = "xszz_xmwh.do?method=xmwhSc";
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

/*��Ŀ����*/
function jxfz() {
	var url = 'xszz_xmwh.do?method=xmwhfz';
	var title = "������Ŀ����";
	showDialog(title, 390, 230,url);
}
