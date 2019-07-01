var dkzesx = jQuery("#dkzesx").val();

function showAuding() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
	} else if(rows.length == 1){
		var id = rows[0]["id"];
		var gwid = rows[0]["xtgwid"];
		var url = "zxdkXyddk.do?method=dksh&id="+id+"&gwid="+gwid;
		showDialog("������ѧ�������",800,500,url);
	}
}

/**
 * �޸������
 * @returns {Boolean}
 */
function xgSqb(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
			showAlertDivLayer("��������������У������޸ģ�");
			return false;
		}
		
		showDialog("�޸������",800,500,"zxdkXyddk.do?method=xgDksq&id="+rows[0]["id"]);
	}
}


/**
 * �鿴�����
 * @param id
 */
function ckSqb(id){
	showDialog("�鿴�����",800,590,"zxdkXyddk.do?method=ckDksq&id="+id);
}


/**
 * ��ѯ
 */
function searchRs(){
	var map = getSuperSearch();
	map["shzt"] = jQuery("#shzt").val();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * ��д�����
 */
function editSqb(){
	showDialog("��д�����",800,500,"zxdkXyddk.do?method=dksq");
}


/**
 * ȡ������
 */
function cancelSubmit(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else {
		jQuery.post("zxdkXyddk.do?method=cancelSubmit",{id:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
	}
}


/**
 * ��������
 * @returns {Boolean}
 */
function saveJxsq(type){
	var xh = jQuery("#xh").val();
	if (jQuery("#sqly").val() == "" || xh == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	
	if (jQuery("#sqjx tr").size() == 0){
		showAlert("��ѡ����Ҫ����Ľ���",{},{"clkFun":function(){
			showDialog("ѡ�����뽱��",450,350,"xpj_sqsh.do?method=selectPjxm&xh="+xh);
		}});
		return false;
	}
	
	var url = "xpj_sqsh.do?method=saveJxsq&type="+type;
	ajaxSubFormWithFun("sqshForm",url,function(data){
		showAlert(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


function selectTab(obj,zt){
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	jQuery("#shzt").val(zt);
	
	if (zt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
	}
	
	var map = getSuperSearch();
	map["shzt"] = zt;
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * ���̸���
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		showDialog("���̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
	}
	
}


//����
function exportConfig(){
	var DCCLBH='zxdk_xyddksq.do';
	customExport(DCCLBH, exportData);
}

function exportData(){
	var DCCLBH='zxdk_xyddksq.do';
	setSearchTj();//���ø߼���ѯ����
	
	var url = "zxdkXyddk.do?method=dcsq&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


//�ύ
function submitBusi(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	
	if (ids.length != 1){
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼����");
		return false;
	}
	if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼�Ѿ�������У������ظ��ύ��");
		return false;
	}
	jQuery.post("zxdkXyddk.do?method=submit",{id:ids.toString()},function(data){
		showAlertDivLayer(data["message"]);
		jQuery("#dataTable").reloadGrid();
	},'json');
}



/**
 * ȡ������
 */
function qxsq(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("��ѡ��ļ�¼����ˣ�����ɾ����");
		return false;
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("zxdkXyddk.do?method=delDksq",{id:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
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
			if("10335"==xxdm){
				if(null == xzf || ''== xzf){
					jQuery("#dkje").val(parseInt(dkzs));
				}
				else{
					jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
				}
			}else{
				if(null == dkqx || ''== dkqx){
					jQuery("#dkje").val(parseInt(dkzs));
				}
				else{
					jQuery("#dkje").val(parseInt(dkzs)*parseInt(dkqx));
				}
			}
			obj.focus();
		}});
	}else{
		jQuery("#mnje").val(dkzs);
		if("10335"==xxdm){
			if((null != xzf && ''!= xzf)&&(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val())){
				jQuery("#dkje").val(parseInt(dkzs)*parseInt(xzf));
			}
			else{
				jQuery("#dkje").val(parseInt(dkzs));
			}
		}else{
			if((null != dkqx && ''!= dkqx)&&(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val())){
				jQuery("#dkje").val(parseInt(dkzs)*parseInt(dkqx));
			}
			else{
				jQuery("#dkje").val(parseInt(dkzs));
			}
		}
		
	}
	
}

/*function checkZje(obj){
	checkMoney(obj);
	var dkzs=obj.value;
	if (null !=dkzs && ''!= dkzs) {
		if(parseInt(dkzs)>parseInt(jQuery("#dkzesx").val())){
			showAlertDivLayer("�ܶ��"+jQuery("#dkzesx").val()+"Ԫ!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
		}
}*/
function checkQx(obj){
	var dkqx=obj.value;
	if (null !=dkqx && ''!= dkqx) {
		if(parseInt(dkqx)>4){
			showAlertDivLayer("�������޳���4��!",{},{"clkFun":function(){
				obj.focus();
			}});
		}
		else{
			if(null!=jQuery("#mnje").val()&&''!=jQuery("#mnje").val()){
				jQuery("#dkje").val(parseInt(jQuery("#mnje").val())*parseInt(dkqx));
			}
		}
		}
	
}

/**
 * ���ؼ�ͥ���������Ϣ
 * @param obj
 * @return
 */
function showJtqk(obj){
	
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_jtqk").toggle();
}

/**
 * ��ѧ�����������༭��ͥ���
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	showDialog('��ͥ�������',780,500,'xszz_jtqkdc.do?method=dcxxModify&type=update&xh='+xh,{
		close:function(){
			reloadWindow();
		}
	});
}

/**
 * ��ѧ���������������
 * @return
 */
function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="zxdkXyddk.do?method=dksq&xh="+xh;
}

//����������Ϣ
function onShow(gndm) {
	var url = "zxdkXyddk.do?method=dkxx";
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

//�����Ի�ѡ��
function zddkqx(){
	var nj = jQuery("#nj").val();  //�꼶
	var xn = jQuery("#xn").val();  //ѧ��
	var xz = jQuery("#xz").val();  //ѧ��
	
	//��������=ʣ����ݣ��꼶-������ݣ�+ѧ��+13��     ������ܴ���20 ����20ȡ20
	var zddkqx = 0;
	if(null==xz||""==xz){
		xz=0;
	}
	if(null==nj||""==nj){
		nj=0;
	}
	if(xz!=0&&nj!=0){
		//parseInt(nj)-parseInt(xn.substring(5,10))+parseInt(xz)+13;
	zddkqx = parseInt(nj)-parseInt(jQuery("#qsxn").val())+parseInt(xz)+13;
	if(zddkqx>20){
		zddkqx=20;
	}
	jQuery("#dkqx").val(zddkqx);
	}
}

