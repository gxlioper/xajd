
//�鿴ѧ���춯��Ϣ
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='xjydshInfoCk(\""+row["xjydsqid"]+"\")'>"+cellValue+"</a>";
}

//�鿴ѧ���춯��Ϣ
function xjydshInfoCk(xjydsqid){
	showDialog("�鿴ѧ��ѧ���춯��Ϣ",800, 500,"xjydsh.do?method=xjydshCk&xjydsqid="+xjydsqid,null);
}


//
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

//ѧ���춯���
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		showAlert("�Ѵ���ļ�¼�����ٴ���ˣ�");
	}else if (rows.length != 1){
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
	}  else{
		showDialog("ѧ���춯���",810, 500,'xjydsh.do?method=xjydsh&xjydsqid='+rows[0]["xjydsqid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]);
	}
}


//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlert("��ѡ��һ����¼��");
	} else {
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['xjydsqid']+"&splc="+rows[0]['splc']);
	}
}



/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh(){
	var xxdm = jQuery("#xxdm").val();
	var shzt = jQuery("#shjg").val();
	if(jQuery("#shjg").val() == "0"){
		showAlertDivLayer("��ѡ�����״̬��");
		return false;
	}
	var checks = "shyj";
	
	var message;
	if(jQuery("#shjg").val() == "1"){
		message = "ͨ��";
		
		var isZhgw = jQuery("#isZhgw").val();
		if("true" == isZhgw){
			if("13871" == xxdm||"70002" == xxdm ||"5002" == xxdm ){
				checks = "xjydsj-shyj";
			}else{
				checks = "xjydwh-xjydsj-shyj";
			}
			if(!jQuery("#zzTzbj").is(":hidden")){
				checks += "-nj-xydm-zydm-bjdm"
			}
			if("10511" == xxdm) {
				if(!jQuery("#zzxzpd").is(":hidden")){
					checks += "-xz";
				}
				if(!jQuery("#zzxxpd").is(":hidden")){
					checks += "-lyqxxxdm";
				}
				checks += "-sfsfs-dqztdm-ydyydm";		
			}
		}
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	
	if(jQuery("#shjg").val() == "2"){
		message = "��ͨ��";
	}
	if(jQuery("#shjg").val() == "3"){
		message = "�˻�";
	}
	if(!check(checks)){
		showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
		return false;
	}
	//�ύ���
	showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
		var url = "xjydsh.do?method=xjydsh&type=save";
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}


function exportConfig() {
	customExport("ydshbase.do", exportData);
}
// ��������
function exportData() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	
	var url = "xjydsh.do?method=exportData&dcclbh='xjydsh.do'"+ "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
