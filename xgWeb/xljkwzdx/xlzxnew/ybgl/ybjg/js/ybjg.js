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
		showDialog('�ϱ�',680,500,'xlzxnew_ybjg.do?method=addYbjg');
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
			showDialog('�޸�',680,500,'xlzxnew_ybjg.do?method=editYbjg&jgid=' + rows[0]['jgid']);
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
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,500 , 'xlzxnew_ybjg.do?method=ckYbjg&jgid=" + rowObject['jgid'] + "')" + "\"";
		if(rowObject['jgid'] == '' || rowObject['jgid'] == null){
			onclickfn = "onclick=\"" + "showAlertDivLayer('��Ϣδ�ϱ���')" + "\"";
		}
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	/**
	 * �����Ƽ�����Ի�
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */
	function yblink(cellValue,rowObject){
		var onclickfn = "";
		if(rowObject['sjly']=='0'){//���ֱ�����ӵ�
			onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,500 , 'xlzxnew_ybjg.do?method=ckYbjg&jgid=" + rowObject['ybid'] + "')" + "\"";
		}else if(rowObject['sjly']=='1'){//���������
			onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,500 , 'xlzxnew_ybsb.do?method=ckYbsb&sbid=" + rowObject['ybid'] + "')" + "\"";
		}else{//δ�ϱ�
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
				if(rows[i]['sjly']=='1'){
					showAlertDivLayer("������̹����ļ�¼����ɾ����");
					return false;
				}
			}
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xlzxnew_ybjg.do?method=del",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	var DCCLBH = "xg_xlzxnew_ybjg.do";//dcclbh,�������ܱ��
	var DCCLBH_10704 = "xg_xlzxnew_ybhz.do";
	//�Զ��嵼�� ����
	function exportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport(DCCLBH, jgExportData);
	}

	function exportHzConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport(DCCLBH_10704, hzExportData);
	}
	//��������
	function jgExportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "xlzxnew_ybjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
	function hzExportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "xlzxnew_ybjg.do?method=exportHzData&dcclbh=" + DCCLBH_10704;//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}