var gridSetting = {
				caption:"����ȼ�ά���б�",
				pager:"pager",
				url:"xpj_jxdj.do?method=jxdjList&type=query",
				colList:[
				   {label:'�ȼ�����',name:'jxdjdm', index: 'jxdjdm',key:true},
				   {label:'�ȼ�����',name:'jxdjmc', index: 'jxdjmc',width:'25%'},
				   {label:'�������',name:'jxlbmc', index: 'jxlbmc',width:'25%'},
				   {label:'������ʽ',name:'jsfsmc', index: 'jsfsmc',width:'25%'}
				],
				sortname: "jxdjdm",
			 	sortorder: "asc"
			}

			function dcmcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}
//��ѯ
function query(){
	var map = {};
	map["jxdjmc"] = jQuery("#jxdjmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	var url = "xpj_jxdj.do?method=addJxdj";
	var title = "���ӽ���ȼ�";
	showDialog(title,350,200,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xpj_jxdj.do?method=updateJxdj&jxdjdm='+rows[0]["jxdjdm"];
		var title = "�޸Ľ���ȼ�";
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
				jQuery.post("xpj_jxdj.do?method=delJxdj",{values:ids.toString()},function(data){
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

//��������
function goJxmc(){
	var url="pjpy_hjgl_jxmc.do";
	refreshForm(url);
}

//��������
function saveForm(){
	
	if(!checkNull("jxdjmc-jxlbdm-jsfs")){
		return false;
	}
   var url = "xpj_jxdj.do?method=addJxdj&type=save";
    ajaxSubFormWithFun("dmwhJxdjForm",url,function(data){
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
	
	if(!checkNull("jxdjmc-jxlbdm-jsfs")){
		return false;
	}
	var url = "xpj_jxdj.do?method=updateJxdj&type=update"
	ajaxSubFormWithFun("dmwhJxdjForm",url,function(data){
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