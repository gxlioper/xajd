var action="xsgwsqnew_sq.do";
function saveForm(){
	if(yzForm()){
		//jQuery("#but_save").hide();
		//jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val()},function(data){
		//	var message = data["message"];
		//	if(message=="true"){
				var url = "xsgwsqnew_sq.do?method=addXsgwSq&type=save";
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
	}else{
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
function tj(url){
	if(yzForm()){		
		var url = "xsgwsqnew_sq.do?method=addXsgwSq&type=submit";
		ajaxSubFormWithFun("demoForm",url,function(data){
			if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
			    	}
			    }});
			}else{
			    showAlertDivLayer(data["message"]);
			}
		});	
	}
}
//��֤���ύ��Ϣ
function yzForm(){
	var gwdm = jQuery("#gwdm").val();
	var sqly = jQuery("#sqly").val();
	var xh = jQuery("#xh").val();
	var result =false;
	if(xh==null||""==xh){
		alertInfo("��ѡ��һ��ѧ��");
		return false;
	}else if(sqly==null||""==sqly){
		alertInfo("��������������");
		return false;
	}else if(sqly.length>500){
		alertInfo("�������ɲ��ܳ���500��");
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
		url:"xsgwsqnew_sq.do?method=getXsgwSqList&type=query",
		colList:[
           {label:'',name:'sqbh', index: 'sqbh',width:'3%',key:true,hidden:true},
           {label:'ѧ��',name:'xh', index: 'xh',width:'12%'},
		   {label:'����',name:'xm', index: 'xm',width:'8%'},
		   {label:'ѧ��',name:'zhxn', index: 'zhxn',width:'10%'},
		   {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
		   {label:'��λ����',name:'gwmc', index: 'gwmc',width:'15%',formatter:xhLink},
		   {label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
		   {label:'��λ���',name:'bmlb', index: 'bmlb',width:'7%'},
		   {label:'��λ����',name:'gwdm', index: 'gwdm',width:'5%',hidden:true},
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'����״̬',name:'shztmc', index: 'shztmc',width:'12%'},
		   {label:'����״̬',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true}
		  
		],
		sortname: "sqsj",
	 	sortorder: "desc"
	}

function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["sqbh"]+"\",\""+row["gwdm"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}
function add(){
	var isopen = jQuery("#xssqkg").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	if(jQuery("#xxdm").val() == '10704' && jQuery("#isTg")){
		if(jQuery("#isTg").val() == '1'){
			showAlertDivLayer("�˸ڲ���һ�꣬���������¸�λ��");
			return false;
		}
	}
	
	var url="xsgwsqnew_sq.do?method=addXsgwSq";
	showDialog("��λ����",800,500,url,null);
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
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
		if(shzt==99||shzt=="99"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		//showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+ids.toString(),null);
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
	}
}
//������Ϣ�鿴
function seeInfo(sqbh,gwdm,xh){
	if(sqbh == "" || sqbh == null || sqbh == "null") {
		showDialog("ѧ����λ����",765,500,"xsgwsqnew_sq.do?method=ckXsgwJgwh&xh="+xh+"&gwdm="+gwdm,{
			close:function(){
			}
		});
	} else {
		showDialog("ѧ����λ����",765,500,"xsgwsqnew_sq.do?method=ckXsgwSq&xh="+xh+"&sqbh="+sqbh,{
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
				jQuery.post(action+"?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['splc'],
					xh : rows[0]['xh']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');		
			}
		});
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
				showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
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


//ѡ�������λ
function wdgwxzCxF(){
	var xh = jQuery("#xh").val();
	if(xh==null || xh==""){
		showAlert("��ѡ��һ��ѧ��");
	}else{
		showDialog("ѡ���λ",800,500,"xsgwsqnew_sq.do?method=wdgwxzCx&xh="+xh,{
			close:function(){
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