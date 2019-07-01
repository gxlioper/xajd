var action="jtpjsz.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function jxmc(cellValue,rowObject){
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["jxid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(jxid) {
	var url = action+"?method=showView&jxid=" + jxid;
	showDialog("������Ϣ", 700, 400, url);
}
function pjdwmc(cellValue,rowObject){
	return fomartPjdw(cellValue);
}
function fomartPjdw(pjdw){
	var mc="";
	switch (pjdw) {
	case "xy":
		mc="��<font color='blue'>"+jQuery("#xbmc").val()+"</font>Ϊ��λ";
		break;
	case "bj":
		mc="��<font color='blue'>�༶</font>Ϊ��λ";
		break;
	case "qs":
		mc="��<font color='blue'>����</font>Ϊ��λ";
		break;	
	default:
		mc="����";
		break;
	}
	return mc;
}
/**
 * �Զ�����������λ����
 * 
 * @return
 */
function autoSetPjdwMc(){
	var jtpjdw=jQuery("#jtpjdw").val();
	jQuery("#jtpjdwmc").html(fomartPjdw(jtpjdw));
}
function sqkg(cellValue,rowObject){
	if(cellValue=="1"){
		return "����";
	}
	return "�ر�";
}
function jxpdzq(cellValue,rowObject){
	var pdxq=rowObject["pdxqmc"];
	if(!pdxq){
		pdxq="";
	}
	return rowObject["pdxn"] + pdxq;
}
// ����
function add() {
		var url =action+"?method=add";
		showDialog("����������������", 700, 440, url);
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = action+'?method=update&jxid=' + rows[0]["jxid"];
		var title = "�޸ļ�����������";
		showDialog(title, 700, 440, url);
	}
}
function copy(){
	var iscanCopy=jQuery("#iscanCopy").val();
	if(iscanCopy=="0"){
		showAlertDivLayer("�����������������ڣ����ܸ��ơ�",{},{"clkFun":function(){
		}});
		return false;
	}
	var sqzq=jQuery("#sqzq").val();
	var sqxn=sqzq.split(",")[0];
	var sqxq=sqzq.split(",")[1];
	var url =action+"?method=jtpjszcopy&sqxn="+sqxn+"&sqxq="+sqxq;
	showDialog("�����������ø���", 400, 200, url);
}
function savecopy(){
	var lyzq=jQuery("#lyzq").val();
	var lyxn=lyzq.split(",")[0];
	var lyxq=lyzq.split(",")[1];
	
	var sqzq=jQuery("#sqzq").val();
	var sqxn=sqzq.split(",")[0];
	var sqxq=sqzq.split(",")[1];
	jQuery.post(action + "?method=savecopy", {
		lyxn:lyxn,
		lyxq:lyxq,
		sqxn:sqxn,
		sqxq:sqxq
	}, function(data) {
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	}, 'json');
}
/**
 * �Զ�����������Աѡ��
 * 
 * @return
 */
function autoSetSelectSqr(){
	var sqrid=jQuery("#ksqxslx").val();
	jQuery("[type=checkbox][name=ksqxslx]").each(function(){
		var v=jQuery(this).val();
		if(sqrid.indexOf(v)!=-1){
			jQuery(this).attr("checked", true);
		}
	});
}

function autoSetKsqr(){
	var sqrId="";
	jQuery("[type=checkbox][name=ksqxslx]").each(function(){
		if(jQuery(this).is(":checked")){
			sqrId+=jQuery(this).val()+",";
		}
	});
	jQuery("#ksqxslx").val(sqrId);
}
function save(url,checkId){
	if(!checkNull(checkId)){
		return false;
	}
	// ���뿪�ؿ�����ʱ����Ҫ�ж��Ƿ�ѡ�����������
	var kqqk=jQuery("[name=sfkfsq]:checked").val();
	if(kqqk=="1"){
		if(!checkNull('splcid')){
			return false;
		}
	}else{
		// ����ر�״̬����������ÿ�
		jQuery("#splcid").val("");
	}
	
	autoSetKsqr();
	// lock();
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
			}else{
				showAlert(data["message"]);
				
			}},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
	// unlock();
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					//var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>������";
					//mes+="</br>";
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						query();
					}});
				}, 'json');
			}
		});

	}
}
function autoSetSqkg(){
	var kqqk=jQuery("[name=sfkfsq]:checked").val();
	if(kqqk=="1"){
		jQuery("#kqxx").show();
	}else{
		jQuery("#kqxx").hide();
	}
}
function query(){
	var map = {};
	var sqzq=jQuery("#sqzq").val();
	var sqxn=sqzq.split(",")[0];
	var sqxq=sqzq.split(",")[1];
	map["jxmc"] = jQuery("#jxmc").val();
	map["sqxn"] =sqxn;
	map["sqxq"] =sqxq;
	jQuery("#dataTable").reloadGrid(map);
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



/*
 *��������
 */
function setTjsz(cellValue,rowObject){
	var xmdm = rowObject.jxid;
	var sqkg = rowObject.sfkfsq;
	var xmmc = rowObject.jxmc;
	var jtpjdw = rowObject.jtpjdw;
	var value;
	if(jtpjdw != 'bj'&&jtpjdw != 'qs'){
		return "";
	}
	if(cellValue == '1'){
		value = "������";
	}else{
		value = "δ����";
	}
	value = setColor(value,cellValue);
	value = "<a  href='javascript:void(0);' onclick='return tjsz(\""+xmdm+"\",\""+sqkg+"\",\""+xmmc+"\");'>"+value+"</a>";
	return value;
}

/*��������*/
function tjsz(xmdm,sqkg,xmmc) {
	if(xmdm == null){//�����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["jxid"];
		sqkg = rows[0]["sfkfsq"];
		xmmc = rows[0]["pjjtmc"];
	}
	var url = 'xpj_xmwh_tjsz.do?method=xmwhTjszCx';
	url += "&xmdm=" + xmdm;
	url += "&xmmc=" + xmmc;
	url += "&flagpath=jtpj";
	var title = "��������";
	tjszDialog = showDialog(title, 750, 520, url,{close:function(){query();}});
}