var lbmc = "";
var hdggmc = "";
var gridSetting = {
				caption:"",
				pager:"pager",
				url:"rcsw_txhd.do?method=lbdmList&type=query&lbmc="+lbmc,
				colList:[
				   {label:'������',name:'lbdm', index: 'lbdm',formatter:lbmcLink,key:true,hidden:true},
				   {label:'�������',name:'lbmc', index: 'lbmc',width:'30%'},
				   {label:'���˵��',name:'lbsm', index: 'lbsm',width:'40%'},
				   {label:'�Ƿ�ʹ��',name:'sfsy', index: 'sfsy',width:'30%'}
				],
				sortname: "lbdm",
			 	sortorder: "asc"
}

var gridSetting1 = {
		caption:"",
		pager:"pager",
		url:"rcsw_txhd.do?method=getHdgglist&type=query&hdggmc="+hdggmc,
		colList:[
		   {label:'�������',name:'hdggdm', index: 'hdggdm',width:'50%',key:true},
		   {label:'��������',name:'hdggmc', index: 'hdggmc',width:'50%'},
		   {label:'�Ƿ�ʹ��',name:'sfsy', index: 'sfsy',hidden:true}
		],
		sortname: "hdggdm",
	 	sortorder: "asc"
}

function lbmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}

//��ѯ
//function query(){
//	var map = {}; 
//	map["lbmc"] = jQuery("#lbmc").val();
//	jQuery("#dataTable").reloadGrid(map);
//}

//����
function add(){
	var tabType = jQuery("#tabType").val();
	if(tabType == 'lbwh'){
		var url = "rcsw_txhd.do?method=addLbdm";
		var title = "�������";
	}else{
		var url = "rcsw_txhd.do?method=addHdgg";
		var title = "���ӹ��";
	}
	showDialog(title,400,250,url);
}

//�޸�
function update(){
	var tabType = jQuery("#tabType").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		return false;
	}
	if(tabType == 'lbwh'){
		var url = 'rcsw_txhd.do?method=updateLbdm&lbdm='+rows[0]["lbdm"];
		var title = "�޸����";
	}else{
		var url = 'rcsw_txhd.do?method=updateHdgg&hdggdm='+rows[0]["hdggdm"]+'&hdggmc='+rows[0]["hdggmc"];
		var title = "�޸Ĺ��";
	}
	showDialog(title,400,250,url);
}

//ɾ��
function del(){
	var tabType = jQuery("#tabType").val();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	} 
	//console.log(tabType);
	if(tabType == 'lbwh'){
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("rcsw_txhd.do?method=delLbdm",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
	    }});
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		var flag = true
		var hdggmc = "";
		for(var i=0;i<rows.length;i++){
			if(rows[i]['sfsy']=='��'){
				var flag = false;
				hdggmc = rows[i]['hdggmc'];
				return false;
			}
		}
		if(!flag){
			showAlertDivLayer(rows[i]['hdggmc']+"����ʹ���У�������ɾ����");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("rcsw_txhd.do?method=delhdgg",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
	    }});
	}
}

//��������
function saveForm(){
	  var lbmc=jQuery("#lbmc").val();
	  if(lbmc==""){
		  showAlert("�뽫��<font color='#ff0000;'>*</font>����Ŀ��д������");
			return false;
	  }
	  if(jQuery("#lbsm").val().length > 200){
		  	showAlert("���˵�����ܳ���200�֣�");
			return false;
	  }
   var url = "rcsw_txhd.do?method=addLbdm&type=save";
    ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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
		showAlert("�뽫��<font color='#ff0000;'>*</font>����Ŀ��д������");
		return false;
	}
	if(jQuery("#lbsm").val().length > 200){
	  	showAlert("���˵�����ܳ���200�֣�");
		return false;
  }
	var url = "rcsw_txhd.do?method=updateLbdm&type=update"
	ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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

//�л���ǩҳѡ�
function selectTab(obj, tabType) {
	var map = {};
	jQuery("#tabType").val(tabType);
	if (tabType == "lbwh") {
		map["tabType"]="lbwh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#th_hdggmc").hide(); 
		jQuery("#td_hdggmc").hide(); 
		jQuery("#th_lbmc").show(); 
		jQuery("#td_lbmc").show(); 
		jQuery("#spanmc").text("���ά���б�");
	}else if(tabType == 'hdgg') {
		map["tabType"]="hdgg";
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
		jQuery("#th_lbmc").hide(); 
		jQuery("#td_lbmc").hide(); 
		jQuery("#th_hdggmc").show(); 
		jQuery("#td_hdggmc").show(); 
		jQuery("#spanmc").text("����ά���б�");
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//���ò�ѯurl·���Լ���ѯ����
function setSearch_url(){
	lbmc = encodeURI(encodeURI(jQuery("#lbmc").val()));
	if(jQuery("#tabType").val() == 'lbwh'){
		gridSetting.url = "rcsw_txhd.do?method=lbdmList&type=query&lbmc="+lbmc;
	}else if(jQuery("#tabType").val() == 'hdgg'){
		hdggmc = encodeURI(encodeURI(jQuery("#hdggmc").val()));
		gridSetting1.url = "rcsw_txhd.do?method=getHdgglist&type=query&hdggmc="+hdggmc;
	}
}

//��ѯ�����¼���
function keydown_search(keyEvent){
	  if(keyEvent.keyCode == 13){
		  setSearch_url();
		  searchRs();
	  }else{
		  return false;
	  }
}

//�����������
function saveHdggNew(){
  var hdggmc =jQuery("#hdggmc").val();
  if(hdggmc=="" || hdggmc == null){
	  showAlert("�������Ʋ���Ϊ�գ�");
		return false;
  }
  var url = "rcsw_txhd.do?method=saveAddNewHdgg";
   ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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

//����޸ı���
function saveHdggUpdate(){
  var hdggmc =jQuery("#hdggmc").val();
  if(hdggmc=="" || hdggmc == null){
	  showAlert("�������Ʋ���Ϊ�գ�");
		return false;
  }
  var url = "rcsw_txhd.do?method=saveUpdateNewHdgg";
  ajaxSubFormWithFun("TxhdDmwhForm",url,function(data){
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