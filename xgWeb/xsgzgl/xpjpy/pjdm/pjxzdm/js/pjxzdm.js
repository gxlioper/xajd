var gridSetting = {
				caption:"������Ŀ����",
				pager:"pager",
				url:"xpj_pjxzdm.do?method=viewPjxzdmList&type=query",
				colList:[
				   {label:'��Ŀ���ʴ���',name:'xmxzdm', index: 'xmxzdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'��Ŀ��������',name:'xmxzmc', index: 'xmxzmc',width:'50%'}
				],
				sortname: "xmxzdm",
			 	sortorder: "asc"
			}

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//��ѯ
function query(){
	var map = {};
	map["xmxzmc"] = jQuery("#xmxzmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpj_pjxzdm.do?method=addPjxzdm";
	var title = "������Ŀ����";
	showDialog(title,380,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpj_pjxzdm.do?method=updatePjxzdm&xmxzdm='+rows[0]["xmxzdm"];
		var title = "�޸���Ŀ��������";
		showDialog(title,380,200,url);
	}
}

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xpj_pjxzdm.do?method=delPjxzdm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//go��Ŀ����
function goXmlx(){
	var url="pj_dmwh.do";
	refreshForm(url);
}

//ǰ���༶����
function goBjdl(){
	var url="pj_bjdldm.do";
	refreshForm(url);
}

//��������
function saveForm(){
	  var xmlxmc=jQuery("#xmxzmc").val();
	  if(xmlxmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }
   var url = "xpj_pjxzdm.do?method=addPjxzdm&type=save";
    ajaxSubFormWithFun("pjxzdmForm",url,function(data){
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
	var xmxzmc=jQuery("#xmxzmc").val();
	if(xmxzmc==""){
		showAlert("�뽫��*����Ŀ��д������");
		return false;
	}
	var url = "xpj_pjxzdm.do?method=updatePjxzdm&type=update"
	ajaxSubFormWithFun("pjxzdmForm",url,function(data){
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