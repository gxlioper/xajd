var gridSetting = {
				caption:"�������ʴ���",
				pager:"pager",
				url:"rcsw_fyff_ffxm.do?method=ffxmQuery",
				colList:[
				   {label:'������Ŀ����',name:'ffxmdm', index: 'ffxmdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'������Ŀ',name:'ffxmmc', index: 'ffxmmc',width:'30%'},
				   {label:'���ŷ�ʽ',name:'fffs', index: 'fffs', width:'30%'},
				   {label:'Ĭ�Ϸ��Ž��',name:'mrffje', index: 'mrffje', width:'30%'}
				],
				sortname: "ffxmdm",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//��ѯ
function query(){
	var map = {};
	map["ffxmmc"] = jQuery("#ffxmmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "rcsw_fyff_ffxm.do?method=addFfxm";
	var title = "����";
	showDialog(title,350,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_fyff_ffxm.do?method=updateFfxm&ffxmdm='+rows[0]["ffxmdm"];
		var title = "�޸�";
		showDialog(title,350,200,url);
	}
}


//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();

	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("rcsw_fyff_ffxm.do?method=delFfxm",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//go��Ŀ����
function goFftj(){
	var url="rcsw_fyff_dmwh_fftj.do";
	refreshForm(url);
}



//��������
function saveForm(){
	
	  var ffxmmc=jQuery("#ffxmmc").val();
	  if(ffxmmc==""){
		  showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			return false;
	  }
	   var url = "rcsw_fyff_ffxm.do?method=addFfxm&type=save";
	    ajaxSubFormWithFun("FyffxmForm",url,function(data){
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
	var ffxmmc=jQuery("#ffxmmc").val();
	if(ffxmmc==""){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	var url = "rcsw_fyff_ffxm.do?method=updateFfxm&type=update";
	ajaxSubFormWithFun("FyffxmForm",url,function(data){
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