function add(){
	var url = 'wjcf_cfsbgl.do?method=cxCfsbAdd';
	showDialog("���Ӵ����ϱ�", 800,500, url);
	
}

function save(url){
	if(!check('xh-xn-xq-cflbdm-cfyydm-wjsj-filepath-filepath4')){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	/*if(jQuery("#xxdm").val()=="12872"){
		if (jQuery(".MultiFile-label").length == 0){
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");;
		}
	}*/
	if(jQuery("#xxdm").val()=="70002"){
		if(!check('cfyj-wjssjg')){
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		}
	}
	if(jQuery("#wjssjg").val().length > 1000){
		return showAlert("[Υ����ʵ����]�������Ϊ1000,���Ѿ���������ȷ�ϣ���");
	}
	if(jQuery("#bz").val().length > 1000){
		return showAlert("[��ע]�������Ϊ1000,���Ѿ���������ȷ�ϣ���");
	}
	
	
	var xh = jQuery("#xh").val();
	var cflbdm = jQuery("#cflbdm").val();
	var wjsj = jQuery("#wjsj").val();
	
	// ��֤�������ϱ��ͽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
	jQuery.post("wjcf_cfsbgl.do?method=checkExistCfsbjg", {
		xh:xh,
		cflbdm:cflbdm,
		wjsj:wjsj
	}, function(data) {
		if(data ==null || data){
			showAlert("��ѧ����"+wjsj+"��Υ�����ϱ������ڴ��ֽ���д��ڣ�");
			return false;
		}else{
			// �ύ����
		    ajaxSubFormWithFun("cfsbglForm",url,function(data){
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
	},"json");
	
}
//�޸�
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		if(rows[0]["shjg"]!="δ�ύ" && rows[0]["shjg"]!="�˻�"){
			showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼");
			return false;
		}
		var url = 'wjcf_cfsbgl.do?method=cxCfsbUpdate&cfid='+rows[0]["cfid"];
		var title = "�޸Ĵ����ϱ�";
		showDialog(title,800,500,url);
	}
	
}

//�����޸�
function saveUpdate(obj){
	if(!check('xh-xn-xq-cflbdm-cfyydm-wjsj-filepath-filepath4')){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(jQuery("#xxdm").val()=="70002"){
		if(!check('cfyj-wjssjg')){
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		}
	}
	/*if(jQuery("#xxdm").val()=="12872"){
		if (jQuery(".MultiFile-label").length == 0){		
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");;
		}
	}*/
	if(jQuery("#wjssjg").val().length > 1000){
		return showAlert("[Υ����ʵ����]�������Ϊ1000,���Ѿ���������ȷ�ϣ���");
	}
	if(jQuery("#bz").val().length > 1000){
		return showAlert("[��ע]�������Ϊ1000,���Ѿ���������ȷ�ϣ���");
	}
	var url= "";
	var xh = jQuery("#xh").val();
	var cflbdm = jQuery("#cflbdm").val();
	var wjsj = jQuery("#wjsj").val();
	var cfid = jQuery("#cfid").val();
	
	if(obj=="save"){
		url="wjcf_cfsbgl.do?method=cxCfsbUpdateSave&type=save";
	}else{
		url="wjcf_cfsbgl.do?method=cxCfsbUpdateSave&type=submit";
	}
	
	// ��֤�������ϱ��ͽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
	jQuery.post("wjcf_cfsbgl.do?method=checkExistCfsbjg", {
		xh:xh,
		cflbdm:cflbdm,
		wjsj:wjsj,
		cfid:cfid
	}, function(data) {
		if(data ==null || data){
			showAlert("��ѧ����"+wjsj+"��Υ�����ϱ������ڴ��ֽ���д��ڣ��޷��ύ��ȷ�ϣ�");
			return false;
		}else{
			// �ύ����
		    ajaxSubFormWithFun("cfsbglForm",url,function(data){
		  	 if(data["message"]=="����ɹ���"|| data["message"]=="�ύ�ɹ���"){
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
	},"json");
}

function cfsbView(cfid){
	var url = 'wjcf_cfsbgl.do?method=cfsbView&cfid='+cfid;
	var title = "�鿴�����ϱ�";
	showDialog(title,800,400,url);
}

function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length==0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	}else {
		for(var i=0;i<ids.length;i++){
			if (rows[i]["sbjg"] != "0"){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("wjcf_cfsbgl.do?method=cfsbDelete",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
			}});
	}
}

//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shjg"];
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		if ("δ�ύ" == shzt){
					showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
					return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['cfid']+"&splc="+rows[0]['splcid']);
	}
}

var DCCLBH = "wjcf_cfsbgl.do";//dcclbh,�������ܱ��
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ�ţ�ִ�е����ĺ���
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "wjcf_cfsbgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function detailInit(){
	var cflbdm = jQuery("#cflbdm").val();
	showCfqxFlag(cflbdm);
}

function updateInit(){
	var cflbdm = jQuery("#cflbdm").val();
	showCfqxFlag(cflbdm);
}

function showCfqxFlag(cflbdm){
	//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
	if(jQuery("#xxdm").val()=='13011') return false;
	
	if(cflbdm==""){return false;}
	jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
		jQuery("#showCfqx").html(data["message"]);
	},'json');
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
			//alert(id[i]);
			return false;
		}
	}
	return true;
}

function fjxz(){
	var url="wjcf_cfsh.do?method=fjxz";
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}