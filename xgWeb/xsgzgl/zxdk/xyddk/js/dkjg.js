var gndm = "zxdk_query";

function addDkjg(){
	showDialog("����",800,500,"zxdkDkjg.do?method=addDkjg");
}

function editDkjg(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(jQuery("#xxdm").val() != "10704"){			
			if (rows[0]["sjly"] == "sqsh"){
				showAlertDivLayer("�������ݲ����޸ģ�");
				return false;
			}
		}
		showDialog("�޸�",800,500,"zxdkDkjg.do?method=editDkjg&id="+rows[0]["id"]);
	}
}

function delDkjg(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['sjly'] == 'sqsh') {
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("zxdkDkjg.do?method=delDkjg",{ids:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//����
function exportConfig(){
	var DCCLBH='zxdk_xyddkjg.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_xyddkjg.do';
	setSearchTj();//���ø߼���ѯ����
	
	var url = "zxdkDkjg.do?method=dcjg&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

/**
 * �鿴�����
 * @param id
 */
function ckDkjg(id){
	showDialog("�鿴�����",800,500,"zxdkDkjg.do?method=ckDkjg&id="+id);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//���������Ϣ
function onShow(gndm) {
	var url = "zxdkDkjg.do?method=dkxx";
	jQuery.ajax( {
		type : "post",
		async : false,
		url : url,
		data : {
			id : jQuery("#id").val()
		},
		dataType : "json",
		success : function(data) {
			zdybdInit(gndm, data);
		}
	});
}


//��֤�����ܶ�
function getzje(obj){
	checkMoney(obj);
	var xfdks = jQuery("#xfdks").val();//ѧ�Ѵ�����
	var zsfdks = jQuery("#zsfdks").val();//ס�޷Ѵ�����
	var shfdks = jQuery("#shfdks").val();//����Ѵ�����
	var dkqx = jQuery("#dkqx").val();
	var xzf = jQuery("#xzf").val();
	var xxdm = jQuery("#xxdm").val();
  var dqdks = obj.value;
  var dkzs=0;
  
 
	if (null !=xfdks && ''!= xfdks) {
		
	  dkzs = parseInt(xfdks)+parseInt(dkzs);
	}
	if (null != zsfdks && ''!= zsfdks) {
	  dkzs = parseInt(zsfdks)+parseInt(dkzs);		
	  }
	if (null !=shfdks && ''!= shfdks) {
	  dkzs = parseInt(shfdks)+parseInt(dkzs);
	  }
	
	if(dkzs>parseInt(jQuery("#dkzesx").val())){
		showAlertDivLayer("ÿ������ܶ��"+jQuery("#dkzesx").val()+"Ԫ!",{},{"clkFun":function(){
			jQuery("#mnje").val(dkzs);
			if(null == xzf || ''== xzf){
				jQuery("#dkje").val(parseInt(dkzs));
			}
			else{
				jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
			}
			
			obj.focus();
		}});
	}else{
		jQuery("#mnje").val(dkzs);
		if((null != xzf && ''!= xzf)&&(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val())){
			jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
		}
		else{
			jQuery("#dkje").val(parseInt(dkzs));
		}
	}
	
}