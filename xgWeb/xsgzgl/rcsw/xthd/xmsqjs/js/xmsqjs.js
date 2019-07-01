
function show(cellValue, rowObject){
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["xmdm"]+ "')\" class='name'>" + cellValue + "</a>";
}
function ckxx(xmdm){
	var url ="rcsw_txhd_xmszCx.do?method=showView&xmdm=" + xmdm;
	showDialog("���Ϣ", 800, 386, url);
}
function ckSqb(sqid){
	var url ="rcsw_txhd_xmsq.do?method=showView&sqid=" + sqid;
	showDialog("�������Ϣ", 800, 386, url);
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function auotSetCanCheck(){
	jQuery("tr[name=checkxm]").each(function(){
		var syme=jQuery(this).find("td[name=syme]").text();
		syme=jQuery.trim(syme);
		if(parseInt(syme,10)<1){
			jQuery(this).find("[name=checkbox]").attr("disabled",true);
		}
	});
}



/**
 * ��д�����
 */
function editSqb(){
	showDialog("��д�����",800,418,"rcsw_txhd_xmsq.do?method=txhdXmsq");
}


/**
 * ѡ��������Ŀ
 * @return
 */
function showXmxz(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog("ѡ����Ŀ",500,350,"rcsw_txhd_xmsq.do?method=getXmsqInfo&xh="+xh);
	} else {
		showAlertDivLayer("����ѡ��ѧ����");
	}
}

/**
 * ѡ����Ŀҳ���л������롢δ����
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	if (tabId == "ysq"){
		jQuery("#titleTr td").last().css("display","none");
	} else {
		jQuery("#titleTr td").last().css("display","");
	}
}


/**
 * ȷ��ѡ����Ŀ
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("#wsq input:checkbox:checked");
	
	var tbody = jQuery(api.get('parentDialog').document).find("#xmInfo");
		tbody.find("tr").remove();
		
	jQuery.each(selectBox,function(i,e){
			var trHtml = "<tr>";
			trHtml+="<td style='width: 20%'>";
			trHtml+="<input type='hidden' name='xmdmArray' value='"+jQuery(e).val()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(1).html();
			trHtml+="</td>";
			trHtml+="<td style='width: 20%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(2).html();
			trHtml+="</td>";
			trHtml+="<td style='width: 20%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(3).html();
			trHtml+="</td>";
			trHtml+="</tr>";
			tbody.append(trHtml);
	});
	iFClose();
}


/**
 * ��������
 * @param type
 * @return
 */
function saveXmsq(type){
	var xh = jQuery("#xh").val();
	var xmdmArray = jQuery("input[name=xmdmArray]");
	
	if (xh == ""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
	
	if (xmdmArray.length == 0){
		showAlert("����ѡ��ѧ��Ҫ����Ļ��Ŀ��",{},{"clkFun":function(){
			showDialog("ѡ����Ŀ",500,350,"rcsw_txhd_xmsq.do?method=getXmsqInfo&xh="+xh);
		}});
		return false;
	}
	
	if(!checkNull("sqly")){
		return false;
	}
	
	var url = "rcsw_txhd_xmsq.do?method=saveXmsq&type="+type;
	ajaxSubFormWithFun("TxhdXmsqJsForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * �޸����� 
 * @return
 */
function updateXmsq(){

	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		var shzt = rows[0]["shzt"];
		
		if ("0" != shzt&&"3" != shzt){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		showDialog("���Ŀ�޸�",800,428,"rcsw_txhd_xmsq.do?method=updateXmsq&sqid="+rows[0]["sqid"]);
	}
}


/**
 * �����޸�����
 * @return
 */
function saveSqxg(type){
	if (!checkNull("sqly")){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	var url = "rcsw_txhd_xmsq.do?method=saveSqxg&type="+type;
	ajaxSubFormWithFun("TxhdXmsqJsForm",url,function(data){
		 if (data["message"] == "����ɹ���" || data["message"] == "�ύ�ɹ���") {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlertDivLayer(data["message"]);
		}
	});
}


/**
 * ȡ������
 * 
 * @return
 */
function xmsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫȡ����������",{"okFun":function(){
			jQuery.post("rcsw_txhd_xmsq.do?method=delXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �ύ����
 * @return
 */
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		var shlcidnew = rows[0]['shlc'];
		
		if(rows[0]["shzt"]=="3"){
			shlcidnew = rows[0]['splc']
		}
		
		
		jQuery.post("rcsw_txhd_xmsq.do?method=sfksq",{xmdm:rows[0]['xmdm']},function(data){
			var message = data["message"];
			
			if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
		     showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_txhd_xmsq.do?method=subBusi", {
					values : ids.toString(),
					lcid : shlcidnew,
					xh : rows[0]['xh'],
					xmdm : rows[0]['xmdm']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
			}
			else{
				showAlertDivLayer(data["message"]);
			}
	},'json');
}
}




//����
function cancle(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("rcsw_txhd_xmsq.do?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}


/**
 * ���̸���
 * @return
 */
function xmsqLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}

var DCCLBH = "rcsw_txhd_xmsq_js.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ�ţ�ִ�е����ĺ���
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_txhd_xmsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

