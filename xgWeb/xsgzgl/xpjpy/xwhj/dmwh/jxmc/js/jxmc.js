var gridSetting = {
				caption:"��������ά���б�",
				pager:"pager",
				url:"xpj_jxmc.do?method=jxmcList&type=query",
				colList:[
				   {label:'���δ���',name:'jxmcdm', index: 'jxmcdm',key:true},
				   {label:'��������',name:'jxmcmc', index: 'jxmcmc'},
				   {label:'�������',name:'jxlbmc', index: 'jxlbmc'},
				   {label:'�����ȼ�',name:'jxdjmc', index: 'jxdjmc'},
				   {label:'������ʽ',name:'jsfsmc', index: 'jsfsmc'},
				   {label:'���',name:'je', index: 'je'}
				],
				sortname: "jxmcdm",
			 	sortorder: "asc"
			}

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//��ѯ
function query(){
	var map = {};
	map["jxmcmc"] = jQuery("#jxmcmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpj_jxmc.do?method=addJxmc";
	var title = "���ӽ�������";
	showDialog(title,350,250,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpj_jxmc.do?method=updateJxmc&jxmcdm='+rows[0]["jxmcdm"];
		var title = "�޸Ľ�������";
		showDialog(title,350,250,url);
	}
}

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xpj_jxmc.do?method=delJxmc",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//�������
function goJxlb(){
	var url="pjpy_hjgl_dmwh.do";
	refreshForm(url);
}

//����ȼ�
function goJxdj(){
	var url="pjpy_hjgl_jxdj.do";
	refreshForm(url);
}

//��������
function saveForm(){
	
	if(!checkNull("jxmcmc-jxlbdm-jsfs-jxdjdm")){
		return false;
	}
   var url = "xpj_jxmc.do?method=addJxmc&type=save";
    ajaxSubFormWithFun("dmwhJxmcForm",url,function(data){
  	 if(data["message"]=="����ɹ���"){
  		 showAlert(data["message"],{},{"clkFun":function(){
  				if (parent.window){
  					refershParent();
  				}
  			}});
  	 }else{
  		 showAlert(data["message"]);
  		 
  	 }
		});
}


//�޸ı���
function updSaveForm(){
	
	if(!checkNull("jxmcmc-jxlbdm-jsfs-jxdjdm")){
		return false;
	}
	var url = "xpj_jxmc.do?method=updateJxmc&type=update"
	ajaxSubFormWithFun("dmwhJxmcForm",url,function(data){
		if(data["message"]=="����ɹ���"){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		}else{
			showAlert(data["message"]);
			
		}
	});
}

//���У�鲻����0��ͷ
function ismoney(obj){
	if(obj.value.indexOf('0') == 0){
		showAlert("������0��ͷ��",{},{"clkFun":function(){
				jQuery(obj).val("");
				jQuery(obj).focus();
		}});
	}
}