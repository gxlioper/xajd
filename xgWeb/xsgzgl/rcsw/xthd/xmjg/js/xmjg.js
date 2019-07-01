var action="rcsw_txhd_xmjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//��������ת
function dcmcLink(cellValue, rowObject) {
	var guid = rowObject["guid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + guid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(guid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&guid=" + guid;
	var title = "������Ϣ";

	showDialog(title, 800, 430, url);
}
//����
function add() {
		var url =action+"?method=add";
		var title = "�����";
		showDialog(title, 800, 490, url);
		jQuery("#dataTable").reloadGrid();
}

function save(url,checkId,sfjc){
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
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
			    		
			    	 }
				},
				contentType:"application/x-www-form-urlencoded;charset=utf-8"
			});	
}
function chkNumInputForThis(obj){
	//�����ǹ���js ����ͬҳ�� ��Щ���󲻴��ڵ�����
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
}
/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			//alert(id[i]);
			return false;
		}
	}
	return true;
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["sjly"]=='1'){
			showAlertDivLayer("������̹����ļ�¼�����޸ģ�");
			return false;
		}
		var xh=rows[0]["xh"];
		var url = action+'?method=update&xh='+xh+'&guid=' + rows[0]["guid"];
		var title = "�޸Ļ��Ϣ";
		showDialog(title, 800, 490, url);
		jQuery("#dataTable").reloadGrid();
	}
}
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("������̹����ļ�¼����ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['qjsqid']+"&splc="+rows[0]['splcid']);
	}
}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('rcsw_txhd_xmjgbase.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_txhd_xmjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ӡ�����
function printQjjgb(url){
	var qjjgid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjjgid +=rows[i]["qjjgid"];
			}else{
				qjjgid +=rows[i]["qjjgid"]+",";
			}
		}
		var url = url + "&qjjgid=" +qjjgid;
		window.open(url);
	}
}

function getHdsbWord() {
	var guid="";
	var url=null;
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length ==0) {
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
		return false;
	}else if(rows.length ==1){
		guid = rows[0]["guid"];
	var url = "rcsw_txhd_xmjg.do?method=doPrintHdsbWord&&guid=" + guid;
	}
	else{
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				guid +=rows[i]["guid"];
			}else{
				guid +=rows[i]["guid"]+",";
			}
		}
		url = "rcsw_txhd_xmjg.do?method=doPrintHdsbWordZip&&guid=" + guid;
	}
	window.open(url);

}

//����
function dr() {
	// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
	toImportDataNew("IMPORT_XSHDSBB");
	return false;

}




