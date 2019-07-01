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
function rysqAdd(){
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

	var url = "xpjpy_rysq.do?method=rysqAdd";
	var title = "��������";
	showDialog(title,800,525,url);
}

/**
 * ����
 */
function saveRysq(type){
	var xh = jQuery("#xh").val();
	/*����ֻ��ѡ��һ������*/
	if (jQuery("#sqjx tr").size() != 1){
		showAlertDivLayer("��ֻ��ѡ��һ������������ύ��");
		return false;
	}
	
	/*����ѡ������Ľ���*/
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("��ѡ����Ҫ����Ľ���",{},{"clkFun":function(){
			showDialog("ѡ�����뽱��",500,400,"xpjpy_rysq.do?method=selectRyxm&xh="+xh);
		}});
		return false;
	}
	
	var ids = "xh-sqly";
	/*������*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	var url = "xpjpy_rysq.do?method=saveRysq&type="+type;
	ajaxSubFormWithFun("rysqForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	});
}

/**
 * ��Ŀ�����޸�
 * @return
 */
function rysqUpdate(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	}else{
		if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		var title = "���������޸�";
		var url = "xpjpy_rysq.do?method=rysqUpdate&id="+rows[0]["id"];
		showDialog(title,800,510,url);
	}
}

/**
 * �޸ı���
 */
function saveRysqXg(type){
	
	var ids = "sqly";
	/*������*/
	if(!checkNotNull(ids)){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var url = "xpjpy_rysq.do?method=saveRysqXg&type="+type;
	ajaxSubFormWithFun("rysqForm",url,function(data){
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
function rysqDelete(){
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
			jQuery.post("xpjpy_rysq.do?method=rysqDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='rysqView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}


/**
 * ������Ӳ鿴��ϸ��Ϣ
 * @param id
 * @param xh
 * @return
 */
function rysqView(id) {
	var title = "��������鿴";
	var url = "xpjpy_rysq.do?method=rysqView&id="+id;
	showDialog(title,800,525,url);
}

/**
 * �ύ
 * @return
 */
function rysqSubmit(){
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
			/*�ύ�Ķ���̫���ˣ�������֮ǰ�ϵķ�����ûʲô̫������*/
			jQuery.post("xpjpy_xmsq.do?method=xmsqSubmit",{values:ids.toString()},function(data){
				tips.close();
				showAlertDivLayerSize(data["message"], 340, 200);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * �������볷��
 * @return
 */
function rysqRevoke(){
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
				jQuery.post("xpjpy_rysq.do?method=rysqRevoke", {
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
function rysqTrack(){
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
var DCCLBH = 'xpjpy_wdpj_rysq.do';
function rysqExport(){
	customExport(DCCLBH, exportData);
}
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xpjpy_rysq.do?method=rysqExportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();

}