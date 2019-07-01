/**
	 * �߼���ѯ
	 * @return
	 */
	function searchRs() {
		var map = {};
		map["xn"] = jQuery("#xn").val();
		map["xq"] = jQuery("#xq").val();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * �ϱ�
	 * @return
	 */
	function sb(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ���ϱ��ܴΣ�");
			return false;
		} else{
			if(rows[0]["zbksrq"] > jQuery("#nowtime").val() || jQuery("#nowtime").val() > rows[0]["zbjsrq"]){
				showAlertDivLayer("�����ܱ��ճ��ϱ�ʱ���У�");
				return false;
			}
				var shzt = rows[0]['shzt'];
				if(shzt != '' && shzt != null){
					showAlertDivLayer("��ѡ��δ�ϱ����ܴΣ�");
					return false;
				}
				showDialog('�ϱ�',680,500,'xlzxnew_zbsb.do?method=addZbsb' + "&sbzbid=" + rows[0]['zbid']);
		}
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
			showDialog('�޸�',680,500,'xlzxnew_zbsb.do?method=editZbsb&sbsqid=' + rows[0]['sbsqid']);
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
					jQuery.post("xlzxnew_zbsb.do?method=cancel", {
						sbsqid : rows[0]['sbsqid'],
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
			showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbsqid']+"&splc="+rows[0]['splcid']);
		}
	}
	/**
	 * �ύ
	 * @return
	 */
	function submitBusi(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ��Ҫ�ύ�ļ�¼��");
			return false;
		} else {
			if(rows[0]['shzt'] == null || rows[0]['shzt']== ''){
				showAlertDivLayer("��ѡ�����ϱ������ݽ����ύ������");
				return false;
			}
			if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			
			showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
				"okFun" : function() {
					jQuery.post("xlzxnew_zbsb.do?method=submit", {
						sbsqid : rows[0]['sbsqid'],
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
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,500 , 'xlzxnew_zbsb.do?method=ckZbsb&sbsqid=" + rowObject['sbsqid'] + "')" + "\"";
		if(rowObject['sbsqid'] == '' || rowObject['sbsqid'] == null){
			onclickfn = "onclick=\"" + "showAlertDivLayer('��Ϣδ�ϱ���')" + "\"";
		}
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	function query(){
		var map = {};
		map["xn"] = jQuery("#xn").val();
		map["xq"] = jQuery("#xq").val();
		jQuery("#dataTable").reloadGrid(map);
	}