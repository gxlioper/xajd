function addDkxx(){
	showDialog("����",800,520,"zxdkSyddk.do?method=addDkxx");
}

function editDkxx(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["sjly"] == "sqsh"){
			showAlertDivLayer("�������ݲ����޸ģ�");
			return false;
		}
		showDialog("�޸�",800,520,"zxdkSyddk.do?method=editDkxx&id="+rows[0]["id"]);
	}
}

function delDkxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}else {
		//���ɾ���ж�bug�޸ģ�by yxx[1206]
	    var flag = true;
		jQuery(rows).each(function(i,row){
			if(row['sjly'] == "sqsh"){
				flag = false;
				return;
			}
		});
		if(!flag){
			showAlertDivLayer("���������޷�ɾ����");
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("zxdkSyddk.do?method=delDkxx",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//����
function exportConfig(){
	var DCCLBH='zxdk_syddk.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_syddk.do';
	setSearchTj();//���ø߼���ѯ����
	
	var url = "zxdkSyddk.do?method=dcxx&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * �鿴�����
 * @param id
 */
function ckDkxx(id){
	showDialog("�鿴�����",800,520,"zxdkSyddk.do?method=ckDkxx&id="+id);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/*
 * ��֤�����ܶ�
 */
function checkZje(){
	var dkzs=jQuery("#dkje").val();
		if(parseInt(dkzs)>parseInt(jQuery("#dkzesx").val())){
			showAlertDivLayer("�������"+jQuery("#dkzesx").val()+"Ԫ!");
			jQuery("#dkje").focus();
				return false;
			}
	

}