var gridSetting = {
				caption:"�����༶����",
				pager:"pager",
				url:"xpj_bjdldm.do?method=viewBjdldmList&type=query",
				colList:[
				   {label:'�༶�������',name:'lbdm', index: 'lbdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'�༶��������',name:'lbmc', index: 'lbmc',width:'50%'}
				],
				sortname: "lbmc",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//��ѯ
function query(){
	var map = {};
	map["lbmc"] = jQuery("#lbmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpj_bjdldm.do?method=addBjlbdm";
	var title = "���Ӱ༶����";
	showDialog(title,380,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpj_bjdldm.do?method=updateBjlbdm&lbdm='+rows[0]["lbdm"];
		var title = "�޸İ༶��������";
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
				jQuery.post("xpj_bjdldm.do?method=deleteBjlbdm",{values:ids.toString()},function(data){
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

//ǰ����Ŀ����
function goXmxz(){
	var url="pj_pjxzdm.do";
	refreshForm(url);
}

//��������
function saveForm(){
	  var lbmc=jQuery("#lbmc").val();
	  if(lbmc==""){
		  showAlert("�뽫��*����Ŀ��д������");
			return false;
	  }
   var url = "xpj_bjdldm.do?method=addBjlbdm&type=save";
    ajaxSubFormWithFun("bjdldmForm",url,function(data){
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
	var lbmc=jQuery("#lbmc").val();
	if(lbmc==""){
		showAlert("�뽫��*����Ŀ��д������");
		return false;
	}
	var url = "xpj_bjdldm.do?method=updateBjlbdm&type=update";
	ajaxSubFormWithFun("bjdldmForm",url,function(data){
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