//ǰ��������¼ҳ��
function goTzjl(){
	var url = "pj_tzjl.do";
	refreshForm(url);
}

//ǰ��������Ա����ҳ��
function goCpry(){
	var url = "pj_cpmd.do";
	refreshForm(url);
}

/**
 * ���Ӳ���ѧ��
 */

function cpxsZj(){
	
	showDialog("���������༶",640,288,"xpj_cpmd.do?method=tzcpxszt");
}

/**
 * ��������ѧ��
 */

function cpxsTz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������Ա��¼");
		return false;
	}
	
	var ids = jQuery("#dataTable").getSeletIds();
	var tjzt = rows[0]["tjzt"];
	var sfdh = rows[0]["sfdh"];
	var tzr = rows[0]["tzr"];
	var tzrxm = rows[0]["tzrxm"];
	var userName = document.getElementById("userName").value;
	
	if("���ύ"==tjzt){
		showAlertDivLayer("ѧ���۲���ѱ��ύȷ�ϣ������ٽ��е���");
		return false;
	}
	if(tzr!=userName&&"��"==sfdh&&null!=tzr){
		showAlertDivLayer("��ѧ���ѱ���"+tzrxm+"��������һ�Σ������ٽ��е���");
		return false;
	}
	
	
	showDialog("���������༶",640,288,"xpj_cpmd.do?method=tzcpxszt&xh=" + rows[0]["xh"]+"&ids="+ids);
}

/**
 * ȡ������ѧ��
 */
function cpxsDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ�������ʸ��ѧ����");
	} else {
		showConfirmDivLayer("��ȷ��ȡ��ѡ��ѧ���Ĳ����ʸ���",{"okFun":function(){
			jQuery.post("xpj_cpmd.do?method=delCpxs",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ѡ��༶ 
 */
function getBjbyPzzd(){
	var zdpzstr = jQuery('#zdpzstr').val();
	getBj(zdpzstr);
}


/**
 * ������������༶
 */

function saveCpxs(){

	var tzqbjdm = jQuery('#tzqbjdm').val();		//����ǰ�༶����
	var tzhbjdm = jQuery('#bjdm').val();		//������༶����
	var ids = jQuery('#ids').val();				//ѡ��id
	var bjmc = jQuery('#bjmc').val();			//������༶����
	var xh = jQuery('#wjxh').val();				//ѧ��

	if(xh == ""){
		showAlert("ѧ�Ų���Ϊ��");
		return false;
	}if(tzhbjdm == ""){
		showAlert("�����༶����Ϊ��");	
		return false;
	}
	
	showConfirm("��ȷ������ѧ����������"+bjmc+"������",{"okFun":function(){
		var url = "xpj_cpmd.do?method=updateCpxs&tzqbjdm="+tzqbjdm+"&ids="+ids+"&tzhbjdm="+tzhbjdm+"&xh="+xh;
		jQuery.post(url,{},function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}})
		},"json");
	}});
	
}

//����ѧ����Ϣ
function checkXh(xh){

	var xhInfo = jQuery('#xhInfo').val();
	if(""!=xhInfo){
		showAlert(xhInfo);
	}
	
	if (xh !="" ){
		document.location.href='xpj_cpmd.do?method=tzcpxszt&xh='+xh;
	}
}

//������¼����
function exportConfig(){
	var DCCLBH="pj_tzjl.do";
	customExport(DCCLBH, exportData);
}
function exportData(){
	var DCCLBH="pj_tzjl.do";
	setSearchTj();//���ø߼���ѯ����
	var url = "xpj_cpmd.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function tzCpbj(tzqCpbj,xh){
	var tzhCpbj = jQuery("#bjdm").val();
	
	jQuery.post("xpj_cpmd.do?method=updateCpxs",{xh:xh,tzqbjdm:tzqCpbj,tzhbjdm:tzhCpbj},function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	},"json");
}

//��ʾ�༶����Div
function showBjtzDiv(){
	
	var rows = jQuery("#dataTable").getSeletRow();

	for(var i=0;i<rows.length;i++){
		if(rows[i]['tjzt']=='1'){
			showAlertDivLayer("����������Ա�����Ե�����");
			return false;
		}
	}

	if(rows.length == 0){
		showAlertDivLayer("����<font color='blue'>��ѡ</font>��Ҫ�����༶��ѧ��");
		return false;
	}else{
		tipsWindown("ϵͳ��ʾ","id:div_bjtz","360","200","true","","true","id");
	}
}


//��Ա�༶����
function tzCpry(){
	
	var bjdm = jQuery("#bjdm").val();
	var ids = jQuery("#dataTable").getSeletIds();
	
	jQuery.post("xpj_cpmd.do?method=updateCpbj",{bjdm:bjdm,ids:ids.toString()},function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if(parent.window){
				refershParent();
			}
		}});
	},"json");
}

