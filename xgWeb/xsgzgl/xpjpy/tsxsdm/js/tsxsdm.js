 var gridSetting = {
				caption:"���������б�",
			pager:"pager",
			url:"xpj_tsxsdm.do?method=tsxsDmList&type=query",
			colList:[
			   {label:'���ʹ���',name:'lxdm', index: 'lxdm',formatter:lxdmLink,key:true,hidden:true},
			   {label:'��������',name:'lxmc', index: 'lxmc',width:'20%'},
			   {label:'��������',name:'lxsx', index: 'lxsx',width:'20%'},
			   {label:'����˵��',name:'lxsm', index: 'lxsm',width:'60%'}
			],
			sortname: "lxmc",
		 	sortorder: "asc"
		}

		function lxdmLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
		}


//�������Ʋ�ѯ
function query(){
	var map = {};
	map["lxmc"] = jQuery("#lxmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpj_tsxsdm.do?method=addTsxsDm";
	var title = "��������ѧ������";
	showDialog(title,400,250,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpj_tsxsdm.do?method=updateTsxsDm&lxdm='+rows[0]["lxdm"];
		var title = "�޸���������";
		showDialog(title,400,250,url);
	}
}

//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xpj_tsxsdm.do?method=delTsxsDm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}


//��������
function saveForm(){
	  var lxmc=jQuery("#lxmc").val();
	  if(lxmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }
     var url = "xpj_tsxsdm.do?method=addTsxsDm&type=save";
      ajaxSubFormWithFun("TsxsDmForm",url,function(data){
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
function delsaveForm(){
	 var lxmc=jQuery("#lxmc").val();
	  if(lxmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }
		var url = "xpj_tsxsdm.do?method=updateTsxsDm&type=update";
		ajaxSubFormWithFun("TsxsDmForm",url,function(data){
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