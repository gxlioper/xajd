var action="qgzx_wdgwsq.do";
function saveForm(){
	if(yzForm()){
		//jQuery("#but_save").hide();
		//jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val()},function(data){
		//	var message = data["message"];
		//	if(message=="true"){
				var url = "qgzx_wdgwsq.do?method=wdgwSq&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
						
						 if (parent.window){
		    					refershParent();
		    				}
		    			}});
				});
			//}else{
			//	jQuery("#but_save").show();
			//	showAlert(message);
			//}
		//},'json');
		
	}
}
function updateForm(type){
	
	var xssqkg = jQuery("#xssqkg").val();
	var shzt = jQuery("#shzt1").val();
	
//	if("3"!=shzt&&"submit"==type&&"true"!= xssqkg){
//		showAlert("��ǰ��λ����δ���ţ���ȷ�ϣ�");
//		return false;
//	}
	
	
	if(type == 'submit'){if(yzForm()){
		jQuery("#but_save").hide();
		jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val(),sqbh:jQuery("#sqbh").val()},function(data){
			var message = data["message"];
			if(message=="true"){
				var url = "qgzx_wdgwsq.do?method=update&type="+type;
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
				});
			}else{
				jQuery("#but_save").show();
				showAlert(message);
			}
		},'json');
		}
	}else{if(yzForm()){
		var url = "qgzx_wdgwsq.do?method=update&type="+type;
		ajaxSubFormWithFun("demoForm",url,function(data){
			 showAlert(data["message"],{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
		});
	}
	}
	
	
}
function tj(url){
	if(yzForm()){
		//jQuery("#but_save").hide();
		jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val()},function(data){
			var message = data["message"];
			if(message=="true"){
				var url = "qgzx_wdgwsq.do?method=wdgwSq&type=submit";
				ajaxSubFormWithFun("demoForm",url,function(data){
			 		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
			 		 	/* var msg = data["message"]+"<br>�뼰ʱ��ϵ��ʦ���ԣ�һСʱ���λ�����Զ�ʧЧ��";*/
			    		 showAlert(data["message"],{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
			    	 }
			 		 else{
			    		 showAlert(data["message"]);
			    	 }
				});
			}else{
				jQuery("#but_save").show();
				showAlert(message);
			}
		},'json');
		
	}
}
//��֤���ύ��Ϣ
function yzForm(){
	var gwdm = jQuery("#gwdm").val();
	var sqly = jQuery("#sqly").val();
	var xh = jQuery("#xh").val();
	var result =false;
	if(xh==null||""==xh){
		showAlert("��ѡ��һ��ѧ��");
		return false;
	}else if(gwdm==null||""==gwdm){
		showAlert("��ѡ�������λ");
		return false;
	}else if(sqly==null||""==sqly){
		showAlert("��������������");
		return false;
	}else if(sqly.length>1500){
		showAlert("�������ɲ��ܳ���1500��");
		return false;
	} else if(!jQuery("#xyscheck").is(":checked")){
        showAlert("���Ķ�Э�飡");
        return false;
	}else{
		result =true;
	}

	return result;
}
//��ʼ����ѯ
var gridSetting = {
		caption:"�ҵĸ�λ�����б�",
		pager:"pager",
		url:"qgzx_wdgwsq.do?method=wdgwsqCx&type=query",
		colList:[
           {label:'',name:'sqbh', index: 'sqbh',width:'3%',key:true,hidden:true},
           {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
		   {label:'����',name:'xm', index: 'xm',width:'8%'},
		   {label:'���',name:'pyccmc', index: 'pyccmc',width:'12%'},
            {label:'�����༶',name:'bjmc', index: 'bjmc',width:'12%'},
            {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'12%'},
		   {label:'��λ����',name:'gwmc', index: 'gwmc',width:'15%'},
            {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
/*		   {label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
		   {label:'��λ���',name:'bmlb', index: 'bmlb',width:'7%'},
		   {label:'��λ��������',name:'sqrs', index: 'sqrs',width:'10%'},*/
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'����״̬',name:'shztmc', index: 'shztmc',width:'12%'},
           {label:'��λ����',name:'gwdm', index: 'gwdm',width:'5%',hidden:true},
		   {label:'����״̬',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true}

		],
		sortname: "sqsj",
	 	sortorder: "desc"
	}

function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["sqbh"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}
function add(){
	/*jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			var url="qgzx_wdgwsq.do?method=wdgwSq";
			refreshForm("qgzx_wdgwsq.do?method=wdgwSq");
			showDialog("��λ����",800,500,url,null);
		}else{
			alertInfo(message);
		}
	},'json');*/
	var isopen = jQuery("#xssqkg").val();
	/*if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}*/
	if(jQuery("#xxdm").val()=='10704' && jQuery("#isTg")){//�����Ƽ���ѧ���Ի�
		if(jQuery("#isTg").val() == '1'){
			showAlertDivLayer("�˸ڲ���һ�꣬���������¸�λ��");
			return false;
		}
	}
	var url="qgzx_wdgwsq.do?method=wdgwSq";
	showDialog("��λ����",800,500,url,null);
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function btn_close(){
	refreshForm("qgzx_wdgwsq.do?method=wdgwsqCx");
}
//���ز�ѯ���
function query(){
	var map ={};
	var array = jQuery("#myForm").serializeArray();
		jQuery(array).each(function(index) {
			map[jQuery(this).attr("name")] = jQuery(this).val();
	 });
	jQuery("#dataTable").reloadGrid(map);
}
//ѡ�������λ
function wdgwxzCx(){
	var xh = jQuery("#xh").val();
	if(xh==null || xh==""){
		showAlert("��ѡ��һ��ѧ��");
	}else{
		var flg = true;
		jQuery.ajaxSetup({async:false});
		if(jQuery("#xxdm").val() == '10704'){//�����Ƽ���ѧ���Ի��жϸ�ѧ���˸�ʱ��
			var url = 'qgzx_wdgwsq.do?method=checkSfTg';			
			jQuery.getJSON(url,{xh:xh},function(data){
				if(data["message"] == '1' || data["message"] == 1){					
					flg = false;
				}
			})			
		}
		jQuery.ajaxSetup({async:true});
		if(!flg){
			showAlertDivLayer("�˸ڲ���һ�꣬���������¸�λ��");
			return false;
		}
		showDialog("ѡ���λ",800,500,"qgzx_wdgwsq.do?method=wdgwxzCx&xh="+xh,{
			close:function(){
			}
		});
	}
	
}
//�������鿴
function lcgz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		var shzt=rows[0]["shzt"];
		if(shzt==0||shzt=="0"){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		//showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+ids.toString(),null);
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
	}
}
//������Ϣ�鿴
function seeInfo(sqbh,xh){
	if(sqbh==""){
		alertInfo("����ѡ��һ��Ҫ�鿴�ĸ�λ���룡");
	} else {
		showDialog("ѧ����λ����",765,500,"qgzx_xsgwsh.do?method=xsgwSh&type=ck&xh="+xh+"&sqbh="+sqbh,{
			close:function(){
			}
		});
	}
}
function submitBusi(){
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:rows[0]["xh"],gwdm:rows[0]["gwdm"],sqbh:rows[0]["sqbh"]},function(data){
					var message = data["message"];
					if(message=="true"){
						jQuery.post(action+"?method=subBusi", {
							values : ids.toString(),
							lcid : rows[0]['splc'],
							xh : rows[0]['xh'],
							gwdm : rows[0]['gwdm']
						}, function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}else{
						showAlertDivLayer(message);
					}
				},'json');
			}
		});
	}
	
}
//�޸�
function update() {
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	var xssqkg = jQuery("#xssqkg").val();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt=rows[0]["shzt"];
		if(shzt!='0'&&shzt!='3'){
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		var xh=rows[0]["xh"];
		var gwdm=rows[0]["gwdm"];
		var url = action+'?method=update&sqbh=' + rows[0]["sqbh"]+"&xh="+xh+"&gwdm="+gwdm+"&xssqkg="+xssqkg;
		var title = "�޸ĸ�λ����";
		showDialog(title, 800, 500, url);
	}
}
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				
			}
		});
	}
}
//ȡ������
function qx_sh(){
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
			
				jQuery.post(action+"?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
				
			}
		});
	}
}


function qggwsqExportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport("qgzx_wdgwsq.do?method=wdgwsqCx", qgsqExportData);
}
	

	
// ��������
function qgsqExportData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "qgzx_wdgwsq.do?method=qgsqExportData&dcclbh=" + "qgzx_wdgwsq.do?method=wdgwsqCx";//dcclbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}