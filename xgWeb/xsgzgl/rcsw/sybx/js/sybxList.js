
	
	/**
     * ��ҵ���յ�����Ϣ--�鿴
     * @param xh
     * @return
     */
    function viewSybx(guid,xh){
    	//showWindow("��ҵ������Ϣ",710,530,"rcsw_sybx.do?method=viewSybx&guid="+guid+"&xh="+xh);
		showDialog("��ҵ������Ϣ",720,440,"rcsw_sybx.do?method=viewSybx&guid="+guid+"&xh="+xh);
    }
	
	function xhLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='viewSybx(\""+rowObject["guid"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
	}
	
	
	/**
     * ��ҵ���յ�����Ϣ--����
     * @param xh
     * @return
     */
	function addSybx(){
		var url = "rcsw_sybx.do?method=addSybx";
		var title = "������ҵ������Ϣ";
		//showWindow(title,720,560,url);
		showDialog('', 730, 510, url);
	}
	
	function searchRs(){
		var map = getSuperSearch();

		jQuery("#dataTable").reloadGrid(map);
	}
	
	/**
     * ��ҵ���յ�����Ϣ--�޸�
     * @param xh
     * @return
     */
	function updateSybx(){
		var rows = jQuery("#dataTable").getSeletRow();

		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		} else {
			var url = 'rcsw_sybx.do?method=updateSybx&guid='+rows[0]["guid"]+'&xh='+rows[0]["xh"];
			var title = "�޸���ҵ������Ϣ";
			//showWindow(title,720,530,url);
			showDialog('', 730, 510, url);
		}
		
	}
	
	/**
     * ��ҵ������Ϣ--ɾ��
     * @param xh
     * @return
     */
	function delSybx(){
		var ids = jQuery("#dataTable").getSeletIds();

		if (ids.length == 0){
			showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		} else {
			showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("rcsw_sybx.do?method=deleteSybx",{values:ids.toString()},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
			}});
		}
	}
	
	/**
	 * ��ҵҽ�Ʊ���ά�� �Զ��嵼��
	 */
	function syylbxwhExportConfig() {
		customExport("rcsw_sybx_cx.do", syylbxwhExportData,1000,500);
	}
		
	
		
	// ��������
	function syylbxwhExportData() {
		setSearchTj();//���ø߼���ѯ����
		var url = "rcsw_sybx.do?method=syylbxwhExportData&dcclbh=" + "rcsw_sybx_cx.do";//dcclbh,�������ܱ��
		url = addSuperSearchParams(url);//���ø߼���ѯ����
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}
	