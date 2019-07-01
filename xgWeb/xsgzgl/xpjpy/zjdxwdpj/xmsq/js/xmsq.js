/**
 * ��ѯ
 * @return
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * ����ҳ��
 */
function xmsqAdd(){
	
	/*��ȡ�������õĿ���״̬*/
	var isopen = jQuery("#isopen").val();
	
	/*��������Ϊ��ʱ*/
	if(isopen == null || isopen == ""){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		showAlertDivLayer();
		return false;
	}
	
	/*����������Чʱ���ڣ���ť�����Σ���ʾ������ǰδ�������룬����ϵ����Ա����*/
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}

	var url = "xpjpy_xmsq.do?method=xmsqAdd";
	var title = "��Ŀ��������";
	showDialog(title,800,525,url);
}

/**
 * ���������޸�
 * @return
 */
function xmsqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		var title = "��Ŀ�����޸�";
		var url = "xpjpy_xmsq.do?method=xmsqUpdate&id="+rows[0]["id"];
		showDialog(title,800,510,url);
	}
}

/**
 * �л������롢δ����
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery(".con_overlfow table").css("display","none");
	jQuery("#"+tabId).css("display","");
	
}


/**
 * ѡ��������Ŀ
 * @return
 */
function selectXm(){
	var pjxm = jQuery("input[name=xmdm]:checked");
	if (pjxm.size() == 0){
		showAlert("��ѡ����Ҫ����Ľ��");
		return false;
	}
	var api = frameElement.api;
	var parentTbody = jQuery(api.get('parentDialog').document).find("#sqjx");
	jQuery("tr",parentTbody).remove();
	var xxdmdiv = jQuery(api.get('parentDialog').document).find("#xxdm");
	var xxdm = xxdmdiv.val();
	var parent = jQuery(api.get('parentDialog').document).find("#fjxxid");
	var djb = jQuery(api.get('parentDialog').document).find("#djb");

    jQuery.ajaxSetup({async: false});
	jQuery.each(pjxm,function(i,n){
		var tr = jQuery("<tr></tr>");
		var xmmcTd = jQuery("<td></td>");
		var xmjeTd = jQuery("<td></td>");
		var sqtsTd = jQuery("<td></td>");
        var xmdybTd = jQuery("<td></td>");
        var xmdybDiv = jQuery("<div id=xmdybDiv"+i+"></div>");
		
		var xmdm = jQuery("<input type='hidden' name='xmdms' value='"+jQuery(n).val()+"'/>");
		var xmmc = jQuery(n).parent().next().text();
		var xmje = jQuery(n).parent().next().next().text();
		var kgbz= jQuery(n).parent().next().next().next().text();
		var xmdyb = jQuery(n).parent().next().next().next().next().text();
		
		xmjeTd.append(xmje);
		xmmcTd.append(xmmc);
		xmmcTd.append(xmdm);
		xmdybTd.append(xmdybDiv);

		sqtsTd.append(jQuery("<p></p>").append(kgbz));
		tr.append(xmmcTd).append(xmjeTd).append(xmdybTd).append(sqtsTd);
		parentTbody.append(tr);

		//���ø���
		// initFj("#xmdybDiv"+i,xmdyb,"xmdyb");
       jQuery(xmdybDiv).multiUploader_q({
            gid : xmdyb,
            uid : "xmdyb"+i,
		    isParent:1
        });
	});
    jQuery.ajaxSetup({async: true});
	api.close();
}


/**
 * ���뱣��
 * @param type
 * @return
 */
function saveXmsq(type){
	var xh = jQuery("#xh").val();
	
	/*����ֻ��ѡ��һ������*/
	if (jQuery("#sqjx tr").size() != 1){
		showAlertDivLayer("��ֻ��ѡ��һ������ύ��");
		return false;
	}
	/*����ѡ������Ľ���*/
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("��ѡ����Ҫ����Ľ���",{},{"clkFun":function(){
			showDialog("ѡ�����뽱��",500,400,"xpjpy_xmsq.do?method=selectPjxm&xh="+xh);
		}});
		return false;
	}
	var ids = "xh-wysp-sqly";
	/*������*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "xpjpy_xmsq.do?method=saveXmsq&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * ɾ��
 * @return
 */
function xmsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	}else{
		/*ѭ��ѡ�м�¼�Ƿ������״̬��Ϊ0������*/
		for(var i=0; i <ids.length; i++){
			if(rows[i]["shzt"] != "0"){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xpjpy_xmsq.do?method=xmsqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * ��Ŀ�����޸ı���
 * @param type
 * @return
 */
function saveJxsqXg(type){
	
	var ids = "sqly-wysp";
	/*������*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "xpjpy_xmsq.do?method=saveJxsqXg&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='xmsqView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}


/**
 * ������Ӳ鿴��ϸ��Ϣ
 * @param id
 * @param xh
 * @return
 */
function xmsqView(id) {
	var title = "��Ŀ����鿴";
	var url = "xpjpy_xmsq.do?method=xmsqView&id="+id;
	showDialog(title,800,525,url);
}


/**
 * �ύ
 * @return
 */
function xmsqSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		for(var i=0;i<rows.length;i++){
			if ("0" != rows[i]["shzt"] && "3" != rows[i]["shzt"]){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			if('3' != rows[i]['shzt'] && "false" == rows[i]['isopen']){
				showAlertDivLayer("������Ŀʱ���ѹرգ��������Ա��ϵ��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			var tips = submitLoading();
			/*����������ӣ��ر����Ӵ��ڣ����ύʱ�ȴ����޷��ر�*/
			try{ 
				tips.show();
			}catch(e){
			}
			jQuery.post("xpjpy_xmsq.do?method=xmsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * ��Ŀ���볷��
 * @return
 */
function xmsqRevoke(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
				if(rows[i]["shzt"]!='5'){
					showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
					return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {"okFun" : function(){
				jQuery.post("xpjpy_xmsq.do?method=xmsqRevoke", {
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
 * ��Ŀ���̸���
 * @return
 */
function xmsqTrack(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(shzt == "0"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
	}
}


/**
 * ����
 * @return
 */
var DCCLBH = 'xpjpy_wdpj_pjsq.do';
function xmsqExport(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_xmsq.do?method=xmsqExportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();

}

/**
 * �ǼǱ��ӡ
 * @return
 */
function xmsqDownload() {
	/*ѡ��ļ�¼*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*��ѡ��¼*/
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	/*�жϴ�ӡ*/
	if(0 == len){
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}else if(1 == len){
		var url = "xpjpy_xmsq.do?method=getWordForid&id="+rows[0]["id"];
		window.open(url);
	}else{
		var url = "xpjpy_xmsq.do?method=getDjbZip&value="+ids;
		window.open(url);
	}
}

//����
function importPjjg(){
    toImportDataNew("IMPORT_N540301_XMSQ");
    return false;
}