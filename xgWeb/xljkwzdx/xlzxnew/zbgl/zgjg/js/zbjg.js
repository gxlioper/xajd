/**
	 * �߼���ѯ
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();;
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * �ϱ�
	 * @return
	 */
	function sb(){
		var rows = jQuery("#dataTable").getSeletRow();
		showDialog('�ϱ�',680,550,'xlzxnew_zbjg.do?method=addZbjg');
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
			if(rows[0]['sjly'] == "1"){
				showAlertDivLayer("������̹��������ݲ����޸ģ�");
				return false;
			}
			showDialog('�޸�',680,500,'xlzxnew_zbjg.do?method=editZbjg&sbjgid=' + rows[0]['sbjgid']);
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
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,500 , 'xlzxnew_zbjg.do?method=ckZbjg&sbjgid=" + rowObject['sbjgid'] + "')" + "\"";
		if(rowObject['sbjgid'] == '' || rowObject['sbjgid'] == null){
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
	
	//ɾ�����
	function del() {
		var ids = jQuery("#dataTable").getSeletIds();
		if (ids.length == 0){
			showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		} else {
			var rows = jQuery("#dataTable").getSeletRow();
			for(var i=0;i<ids.length;i++){
				if(rows[i]['sjly']=='1'){
					showAlertDivLayer("������̹����ļ�¼����ɾ����");
					return false;
				}
			}
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xlzxnew_zbjg.do?method=del",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	var DCCLBH = "xg_xlzxnew_zbjg.do";//dcclbh,�������ܱ��

	//�Զ��嵼�� ����
	function exportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport(DCCLBH, jgExportData);
	}

	//��������
	function jgExportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "xlzxnew_zbjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}