var gridSetting = {
		caption:"�����嵥ģ���б�",
		pager:"pager",
		url:"daqdmb.do?method=daqdmbList&type=query",
		colList:[
		   {label:'�����嵥ģ����',name:'daqdmb_id', index: 'daqdmb_id',hidden:true},
		   {label:'�����嵥ģ������',name:'daqdmb_mc', index: 'daqdmb_mc',formatter:mbLink},
		   {label:'�󶨲�����',name:'ybdcls', index: 'ybdcls'},
		   {label:'����״̬����',name:'qyzt', index: 'qyzt',hidden:true},
		   {label:'����״̬',name:'qyztmc', index: 'qyztmc'},
		   {label:'��ѧ����',name:'ybdxss', index: 'ybdxss'}
		],
		sortname: "",
	 	sortorder: ""
	};
		
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
		function searchRs(){
			var map = getSuperSearch();
	
			jQuery("#dataTable").reloadGrid(map);
		}
		
		
		function mbLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='showDetail(\""+rowObject["daqdmb_id"]+"\");'>"+rowObject["daqdmb_mc"]+"</a>";
		}
		
		function showDetail(daqdmb_id){
			
			showDialog("�鿴�����嵥ģ��",580,475,"daqdmb.do?method=viewDaqdmb&daqdmb_id="+daqdmb_id);
		}
		
		function addDaqdmb(){
			showDialog("���ӵ����嵥ģ��",510,475,"daqdmb.do?method=addDaqdmb");			
		}
		
		function updateDaqdmb(){
			var daqdmbId= '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			 if(rowsValue.length != 1){
				 showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				return false;
			}else{
				if(rowsValue[0]["qyzt"]=="1"){
					showAlertDivLayer("�����ã������޸ģ�");
					return false;
				}
				daqdmbId = rowsValue[0]["daqdmb_id"];
			}
			showDialog("�޸ĵ����嵥ģ��",510,475,"daqdmb.do?method=updateDaqdmb&daqdmb_id="+daqdmbId);
		}
		
		function delDaqdmb(){
			var daqdmbIds = '';
			var rowsValue = jQuery("#dataTable").getSeletRow();
			if (rowsValue.length == 0){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				return false;
			}
			for(var i=0;i<rowsValue.length;i++){
				if(rowsValue[i]["qyzt"]=="1"){
					showAlertDivLayer("�����ã�����ɾ����");
					return false;
				}
				if(i==(rowsValue.length-1)){
					daqdmbIds += rowsValue[i]["daqdmb_id"];
				}else{
					daqdmbIds += rowsValue[i]["daqdmb_id"]+",";
				}
			}
	
			confirmInfo("��ȷ��Ҫɾ��<font color='red'>"+rowsValue.length +"</font>����¼��?",function(ty){
				if(ty=="ok"){
					jQuery.post("daqdmb.do?method=delDaqdmb",{daqdmbIds:daqdmbIds},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
					}
				});				
			}
		
			
	function  sfqy(type){
		var daqdmbIds = '';
	var rowsValue = jQuery("#dataTable").getSeletRow();
	if (rowsValue.length == 0){
		if(type=="1"){
			showAlertDivLayer("��ѡ����Ҫ���õļ�¼��");
		}else{
			showAlertDivLayer("��ѡ����Ҫͣ�õļ�¼��");
		}
		return false;
	}
	for(var i=0;i<rowsValue.length;i++){
		if(rowsValue[i]["ybdxss"]!=0 && type=="0"){
			showAlertDivLayer("��ѡ��ļ�¼�Ѱ�ѧ��������ͣ�ã�");
			return false;
		}
		if(i==(rowsValue.length-1)){
			daqdmbIds += rowsValue[i]["daqdmb_id"];
		}else{
			daqdmbIds += rowsValue[i]["daqdmb_id"]+",";
		}
	}
	var message = "";
	if(type=="1"){
		message = "��ȷ��Ҫ����ѡ��ļ�¼��";
	}else{
		message = "��ȷ��Ҫͣ��ѡ��ļ�¼��";
	}
	
	confirmInfo(message,function(ty){
		if(ty=="ok"){
	
			jQuery.post("daqdmb.do?method=updateQdmbQyzt",{daqdmbIds:daqdmbIds,qyzt:type},function(data){
				alertInfo(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}
	});
}
