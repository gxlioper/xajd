var gridSetting = {
				caption:"",
				pager:"pager",
				url:"jjgl_cssz.do?method=queryDataList&qryType=jjxk",
				radioselect:true,
				colList:[
				   {label:'ѧ�ƴ���',name:'jjxkdm', index: 'jjxkdm',width:'40%',key:true},
				   {label:'ѧ������',name:'jjxkmc', index: 'jjxkmc',width:'60%'},
				   {name:'jjxkdm', index: 'dm',hidden:true}
				   
				],
				sortname: "jjxkdm",
			 	sortorder: "asc"
			};


function dcmcLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
}


/**
 * ҳǩ�л�
 * @return
 */
function selectTab(obj,query_type){
	jQuery('#hiddenQryType').val(query_type);
	gridSetting['url'] =  "jjgl_cssz.do?method=queryDataList&qryType=" + query_type;
	if (query_type == "jjxk"){
		gridSetting['sortname'] = 'jjxkdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {label:'ѧ�ƴ���',name:'jjxkdm', index: 'jjxkdm',width:'40%',key:true},
			 {label:'ѧ������',name:'jjxkmc', index: 'jjxkmc',width:'60%'},
			 {name:'jjxkdm', index: 'dm',hidden:true}
		];  
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else if(query_type == "jjnj"){
		gridSetting['sortname'] = 'jjnjdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {label:'�꼶����',name:'jjnjdm', index: 'jjnjdm',width:'40%',key:true},
			 {label:'�꼶����',name:'jjnjmc', index: 'jjnjmc',width:'60%'},
			 {name:'jjnjdm', index: 'dm',hidden:true}
		]; 
		jQuery("#dataTable").initGrid(gridSetting);
	} else if(query_type == "sfbz"){
		gridSetting['sortname'] = 'jjxkdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {name:'id', index: 'id',key:true,hidden:true},
			 {label:'ѧ������',name:'jjxkmc', index: 'jjxkmc',width:'40%'},
			 {label:'�꼶����',name:'jjnjmc', index: 'jjnjmc',width:'40%'},
			 {label:'�շѱ�׼',name:'sfbz', index: 'sfbz',width:'20%'},
			 {name:'id', index: 'dm',hidden:true}
		]; 
		jQuery("#dataTable").initGrid(gridSetting);
	} else if(query_type == "fbzg"){
		gridSetting['sortname'] = 'fbzgdm';
		gridSetting['sortorder'] = 'asc';
		gridSetting['colList'] = [
			 {label:'����',name:'fbzgdm', index: 'fbzgdm',width:'10%',key:true},
			 {label:'����',name:'fbzgmc', index: 'fbzgmc',width:'20%'},
			 {label:'�����趨',name:'fy', index: 'fy',width:'10%'},
			 {label:'����',name:'fbzgms', index: 'fbzgms',width:'60%'},
			 {name:'fbzgdm', index: 'dm',hidden:true}
		]; 
		jQuery("#dataTable").initGrid(gridSetting);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

//��ѯ
function query(){
	var map = {};
	map["ffxmmc"] = jQuery("#ffxmmc").val();
	jQuery("#dataTable").reloadGrid(map);
}

//����
function add(){
	
	var url = "jjgl_cssz.do?method=add&qryType=" + jQuery('#hiddenQryType').val();
	var title = "����";
	showDialog(title,500,250,url);
}

//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var type = jQuery('#hiddenQryType').val();
		var url = "jjgl_cssz.do?method=update&qryType=" + jQuery('#hiddenQryType').val() + '&dm='+jQuery("#dataTable").getSeletIds()[0];
		var title = "�޸�";
		showDialog(title,500,250,url);
	}
}


//ɾ��
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var type = jQuery('#hiddenQryType').val();
	var url = '';
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("jjgl_" + type + ".do?method=delete",{delIds:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
		}});
		
	}
}