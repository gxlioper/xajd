var gridSetting = {
		caption:"����"+jQuery("#xbmc").val()+"����б�",
		pager:"pager",
		url:"wjcf_cfjcsh.do?method=cxCfjcshList&type=query",
		colList:[
				   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
				   {label:'����',name:'xm', index: 'xm'},
            		{label:'רҵ�༶',name:'zybjmc', index: 'zybjmc'},
            		{label:'��Ժ',name:'symc', index: 'symc'},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
				   {label:'�������',name:'cflbmc', index: 'cflbmc'},
				   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc'},
				   {label:'���״̬',name:'shzt', index: 'shzt'},
				   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
				   {label:'cfid',name:'cfid', index: 'cfid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true}
		],
		params:{shlx:"dsh"}
}

var gridSetting2 = {
		caption:"����"+jQuery("#xbmc").val()+"����б�",
		pager:"pager",
		url:"wjcf_cfjcsh.do?method=cxCfjcshList&type=query",
		colList:[
				   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
				   {label:'����',name:'xm', index: 'xm'},
            		{label:'רҵ�༶',name:'zybjmc', index: 'zybjmc'},
            		{label:'��Ժ',name:'symc', index: 'symc'},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
				   {label:'�������',name:'cflbmc', index: 'cflbmc'},
				   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc'},
				   {label:'���״̬',name:'shzt', index: 'shzt'},
            	   {label:'���ʱ��',name:'shsj', index: 'shsj'},
				   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
				   {label:'cfid',name:'cfid', index: 'cfid',hidden:true},
				   {label:'shid',name:'shid', index: 'shid',hidden:true}
		],
		params:{shlx:"ysh"}
}

//���
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ��˵ļ�¼��");
	}  else{
		showDialog("����"+jQuery("#xbmc").val()+"���",820,500,'wjcf_cfjcsh.do?method=jcsh&ywid='+rows[0]["ywid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"]+"&cfid="+rows[0]["cfid"]);
	}
}

//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['ywid']+"&splc="+rows[0]['splcid']);
	}
}


function query(obj,shlx){
	jQuery("#comp_title li").removeClass();
	jQuery(obj).parent().attr("class","ha");
	jQuery("#shlx").val(shlx);
	if(shlx=='ysh'){
		jQuery("#dataTable").initGrid(gridSetting2);
		jQuery("#btn_qxsh").show();
		jQuery("#btn_sh").hide();
	}else{
		jQuery("#dataTable").initGrid(gridSetting);
		jQuery("#btn_qxsh").hide();
		jQuery("#btn_sh").show();
	}
	searchRs();
}

//��ѯ
function searchRs(){
	var map = getSuperSearch();
	map["shlx"] = jQuery.trim(jQuery("#shlx").val());
	jQuery("#dataTable").reloadGrid(map);
}
/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh_bak(shzt,message){
	jQuery("#shzt").val(shzt);
	
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	//�����һ�����ͨ��ʱ���ж��Ƿ���д�����ĺż�����ʱ��
	if((shzt=="1"||shzt==1)){
		if(jQuery("#isZhgw").val()=="true"){
			if(jQuery("#jcwh").val()==""){
				showAlertDivLayer("����д"+jQuery("#xbmc").val()+"�ĺţ�");
				return false;
			}
			if(jQuery("#jcsj").val()==""){
				showAlertDivLayer("����д"+jQuery("#xbmc").val()+"ʱ�䣡");
				return false;
			}
		}
	}
	if(shzt=="3"||shzt==3){
		var shid = jQuery("#shid").val();
		var splc = jQuery("#splcid").val();
		var shyj = jQuery("#shyj").val();
		splc_th(shid,splc,shyj);
		return false;
	}
	//�ύ���
	showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
		var url = "wjcf_cfjcsh.do?method=jcsh&type=save";
		ajaxSubFormWithFun("cfjcshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
}

function showLsjl(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_hjjl").toggle();
}


