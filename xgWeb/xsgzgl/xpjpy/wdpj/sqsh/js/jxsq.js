//��������


/**
 * �޸������
 * @returns {Boolean}
 */
function xgSqb(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		
		showDialog("�޸������",800,480,"xpj_sqsh.do?method=updateSqb&sqid="+rows[0]["sqid"]);
	}
}


/**
 * �鿴�����
 * @param id
 */
function ckSqb(id){
	showDialog("�鿴�����",800,495,"xpj_sqsh.do?method=viewSqb&sqid="+id);
}


/**
 * ��ѯ
 */
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}




/**
 * ��д�����
 */
function editSqb(){
	showDialog("��д�����",800,455,"xpj_sqsh.do?method=editSqb&xzdm="+jQuery("#xzdm").val());
}


/**
 * ɾ������
 */
function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["shzt"] != "0"){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xpj_sqsh.do?method=cancelXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


/**
 * ��������
 * @returns {Boolean}
 */
function saveJxsq(type){
	var xh = jQuery("#xh").val();
	var xxdm = jQuery("#xxdm").val();
	if (jQuery("#sqly").val() == "" || xh == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		
	
	if (jQuery("#sqly").val().length > 500){
		showAlert("���������������Ϊ500�����Ѿ���������ȷ�ϣ���");
		return false;
	}
	
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("��ѡ����Ҫ����Ľ���",{},{"clkFun":function(){
			showDialog("ѡ�����뽱��",450,350,"xpj_sqsh.do?method=selectPjxm&xh="+xh);
		}});
		return false;
	}
	
	var url = "xpj_sqsh.do?method=saveJxsq&type="+type;
	ajaxSubFormWithFun("sqshForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * �л������롢δ����
 * @param obj
 * @param tabId
 */
function selectSqxm(obj,tabId){
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	jQuery(".con_overlfow table").css("display","none");
	jQuery("#"+tabId).css("display","");
	
}

/**
 * ѡ��������Ŀ
 * @returns {Boolean}
 */
function selectXm(){
	
	var pjxm = jQuery("input[name=xmdm]:checked");
	
	if (pjxm.size() == 0){
		showAlert("��ѡ����Ҫ����Ľ��");
		return false;
	}
	var api = frameElement.api;
	var parentTbody = jQuery(api.get('parentDialog').document).find("#sqjx");
//	var parentTxsmDiv = jQuery(api.get('parentDialog').document).find("#txsmDiv")
	jQuery("tr",parentTbody).remove();
	
	
	var xxdmdiv = jQuery(api.get('parentDialog').document).find("#xxdm");
	var xxdm = xxdmdiv.val();
	var parent = jQuery(api.get('parentDialog').document).find("#fjxx");
	var djb = jQuery(api.get('parentDialog').document).find("#djb");
	if('11527' == xxdm){
		if(djb.length>0){
			djb.remove();
		}
	}
	//�й�����ѧԺ���Ի���У���Ƿ��������������
	if(jQuery("#xxdm").val() == "10355"){
	  var parentDoc = jQuery(api.get('parentDialog').document);
	  if(pjxm.size() == 1){
		  var url = "xpj_sqsh.do?method=checkHjsqCanAdd";
		  var checkResult = "";
			jQuery.ajax({
			type:'post',
			url:url,
			dataType:'json',
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			data:'xmdm='+pjxm[0].value,
			async: false,
			success:function(result){
				checkResult = result["message"];
			}
			
			});
			if(checkResult == "true"){
				jQuery(parentDoc).find("#hjbutton").show();
				jQuery(parentDoc).find("#hjtr").show();
			}else{
				jQuery(parentDoc).find("#hjbutton").hide();
				jQuery(parentDoc).find("#hjtr").hide();
			}
	  }else{
		  jQuery(parentDoc).find("#hjbutton").hide();
		  jQuery(parentDoc).find("#hjtr").hide();
	  }
		
		
		
	}	
	
	jQuery.each(pjxm,function(i,n){
		var tr = jQuery("<tr></tr>");
		var xmmcTd = jQuery("<td></td>");
		var xmjeTd = jQuery("<td></td>");
		var sqtsTd = jQuery("<td></td>");
		
		var xmdm = jQuery("<input type='hidden' name='xmdms' value='"+jQuery(n).val()+"'/>");
		var xmmc = jQuery(n).parent().next().text();
		var xmje = jQuery(n).parent().next().next().text();
		var kgbz= jQuery(n).parent().next().next().next().text();
		
		xmjeTd.append(xmje);
		xmmcTd.append(xmmc);
		xmmcTd.append(xmdm);
		
		sqtsTd.append(jQuery("<p></p>").append(kgbz));
		
//		parentTxsmDiv.html(kgbz);
		tr.append(xmmcTd).append(xmjeTd).append(sqtsTd);
		parentTbody.append(tr);
		if('11527' == xxdm){
			var djbb = jQuery(api.get('parentDialog').document).find("#djb");
			if(xmmc.trim() == '����ʡ2017�������ҵ��'){
				if(djbb.length>0){
					var td = jQuery(djbb).find("td");
					var html = '&nbsp;&nbsp;<a href="xsgzgl/xpjpy/wdpj/sqsh/sjdjb_11527.docx" target="_blank">ʡ���ǼǱ�����</a>';
					td.append(html);
				}else{					
					var html = '<tr id="djb"><th>�ǼǱ�����</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/sjdjb_11527.docx" target="_blank">ʡ���ǼǱ�����</a></td></tr>'
					parent.before(html);
				}
			}
		    if(xmmc.trim() == '���ϳ���ѧԺ2017�������ҵ��'){
		    	if(djbb.length>0){
		    		var td = jQuery(djbb).find("td");
		    		var html = '&nbsp;&nbsp;<a href="xsgzgl/xpjpy/wdpj/sqsh/xjdjb_11527.docx" target="_blank">У���ǼǱ�����</a>';
		    		td.append(html);
		    	}else{
		    		var html = '<tr id="djb"><th>�ǼǱ�����</th><td colspan="3"><a href="xsgzgl/xpjpy/wdpj/sqsh/xjdjb_11527.docx" target="_blank">У���ǼǱ�����</a></td></tr>'
					parent.before(html);
		    	}
		    }
		}
		if('11799' == xxdm){
			if("���ѧ��" == xmmc.trim()  ||
					 "���ѧ�������˶���" == xmmc.trim() || 
					 "���ѧ�����ջ��" == xmmc.trim() || 
					 "���ѧ�𣨿Ƽ����£�" == xmmc.trim() || 
					 "���ѧ��ѧϰ����1��" == xmmc.trim() || 
					 "���ѧ��ѧϰ����3��" == xmmc.trim() || 
					 "���ѧ��ѧϰ������" == xmmc.trim() ){
				//jQuery(api.get('parentDialog').document).find("#sqlyTr").hide();
				jQuery(api.get('parentDialog').document).find("#dxjxjTr").show();
				jQuery(api.get('parentDialog').document).find("#xmmc_11799").val(xmmc.trim());
			}else{
				//jQuery(api.get('parentDialog').document).find("#sqlyTr").show();
				jQuery(api.get('parentDialog').document).find("#dxjxjTr").hide();
				jQuery(api.get('parentDialog').document).find("#xmmc_11799").val("");
			}
		}
	});
	
	api.close();
}


/**
 * ���̸���
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
//		
//		var url = "xpj_sqsh.do?method=viewLcgz&sqid="+rows[0]["sqid"];
//		showDialog("���̸���",550,450,url,{max:false,min:false});
		
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
	
}


//����
function exportConfig(){
	var DCCLBH='pj_jxsq.do';
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH='pj_jxsq.do';
	
	setSearchTj();//���ø߼���ѯ����
	
	var url = "xpj_sqsh.do?method=sqExportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//�ύ
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	} else {
		for(var i=0;i<rows.length;i++){
			if (rows[i]["shzt"] != "0"&&rows[i]["shzt"] != "3"){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			if('3'!=rows[i]['shzt']&&"false"==rows[i]['isopen']){
				showAlertDivLayer("������Ŀʱ���ѹرգ��������Ա��ϵ��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
			var tips = submitLoading();
			try{ // ����������ӣ��ر����Ӵ��ڣ����ύʱ�ȴ����޷��ر�
				tips.show();
			}catch(e){
			}
			jQuery.post("xpj_sqsh.do?method=saveUpdateSqbPl",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

function cancleRst(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		var SFBJPY_Y = jQuery("#SFBJPY_Y").val();
		for(var i=0;i<ids.length;i++){
			if(SFBJPY_Y == 'true'){
				if(rows[i]['shzt']!='6'){
					showAlertDivLayer("ֻ�а༶�����еļ�¼���ܱ�������");
					return false;
				}
			}else{
				if(rows[i]['shzt']!='5'){
					showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
					return false;
				}
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
			
				jQuery.post("xpj_sqsh.do?method=cancle", {
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
 * �쳣���ݴ���
 * @return
 */
function exceptionDataResolve(){

    showConfirmDivLayer("��ȷ��Ҫ�����ύ�쳣���ݴ�����",{"okFun":function(){
        jQuery.post("xpj_sqsh.do?method=exceptionDataResolve",function(data){
            showAlertDivLayer(data["message"]);
        },'json');
    }});
}