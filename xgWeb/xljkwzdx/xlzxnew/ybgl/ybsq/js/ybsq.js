/**
	 * �߼���ѯ
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * �ϱ�
	 * @return
	 */
	function sb(){
		showDialog('�ϱ�',680,500,'xlzxnew_ybsb.do?method=addYbsb');
	}
	
	
	/**
	 * �޸�
	 * @return
	 */
	function update(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		} else{
			var shzt = rows[0]['shzt'];
			if(shzt == '' || shzt == null){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			if(shzt != '0' && shzt != '3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			showDialog('�޸�',680,500,'xlzxnew_ybsb.do?method=editYbsb&sbid=' + rows[0]['sbid']);
		}
	}

	/**
	 * ����
	 * @return
	 */
	function cancle(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ��Ҫ�����ļ�¼��");
		} else {
			for(var i=0;i<rows.length;i++){
				if(rows[i]['shzt'] != '5'){
					showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
				"okFun" : function() {
					jQuery.post("xlzxnew_ybsb.do?method=cancel", {
						sbsqid : rows[0]['sbid'],
						splcid : rows[0]['splcid']
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
	function lcinfo(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����¼��");
		} else {
			if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="" || rows[0]["shzt"]==null){
				showAlertDivLayer("��ѡ�����ύ�ļ�¼��");
				return false;
			}
			showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbid']+"&splc="+rows[0]['splcid']);
		}
	}
	/**
	 * �ύ
	 * @return
	 */
	function submitBusi(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlert("��ѡ��һ��Ҫ�ύ�ļ�¼��");
			return false;
		} else {
			if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
				showAlert("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			
			showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
				"okFun" : function() {
					jQuery.post("xlzxnew_ybsb.do?method=submit", {
						sbsqid : rows[0]['sbid'],
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			});
		}
	}

	/**
	 * ����
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){

		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,500 , 'xlzxnew_ybsb.do?method=ckYbsb&sbid=" + rowObject['sbid'] + "')" + "\"";
		if(rowObject['sbid'] == '' || rowObject['sbid'] == null){
			onclickfn = "onclick=\"" + "showAlertDivLayer('��Ϣδ�ϱ���')" + "\"";
		}
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}
	
	//ɾ�����
	function del() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			for(var i=0;i<ids.length;i++){
				if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3'){
					showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
					return false;
				}
			}
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xlzxnew_ybsb.do?method=del",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	

