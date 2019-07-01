var gridSetting = {
				caption:"�������ʴ���",
				pager:"pager",
				url:"rcsw_fyff_fftj.do?method=fftjQuery",
				colList:[
				   {label:'����;������',name:'fftjdm', index: 'fftjdm',formatter:dcmcLink,key:true,hidden:true},
				   {label:'����;��',name:'fftj', index: 'fftj',width:'50%'},
				   {label:'�����˺�',name:'ffzh', index: 'ffzh', width:'50%'}
				],
				sortname: "fftjdm",
			 	sortorder: "asc"
			};

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//��ѯ
function query(){
	var map = {};
	map["fftj"] = jQuery("#fftj").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "rcsw_fyff_fftj.do?method=addFftj";
	var title = "����";
	showDialog(title,350,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'rcsw_fyff_fftj.do?method=updateFftj&fftjdm='+rows[0]["fftjdm"];
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
				jQuery.post("rcsw_fyff_fftj.do?method=delFftj",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}

//go��Ŀ����
function goFfxm(){
	var url="rcsw_fyff_dmwh_ffxm.do";
	refreshForm(url);
}



//��������
function saveForm(){
	
	  var fftj=jQuery("#fftj").val();
	  if(fftj==""){
		  showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			return false;
	  }
	   var url = "rcsw_fyff_fftj.do?method=addFftj&type=save";
	    ajaxSubFormWithFun("FyfftjForm",url,function(data){
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
	var url = "rcsw_fyff_fftj.do?method=updateFftj&type=update";
	ajaxSubFormWithFun("FyfftjForm",url,function(data){
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