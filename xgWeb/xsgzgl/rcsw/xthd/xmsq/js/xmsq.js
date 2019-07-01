
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


function saveForm(){
	var sqly = jQuery("#sqly").val();
	if(sqly==null||""==sqly){
		alertInfo("��������������");
		return false;
	}
	var url = "rcsw_txhd_xs_xmsq.do?method=xmsq&type=save";
	ajaxSubFormWithFun("demoForm",url,function(data){
	showAlert(data["message"],{},{"clkFun":function(){
						
	if (parent.window){
		    refershParent();
		    	}
		  }});
	});
			
}
var gridSetting1 = {
		caption:"��Ŀ����ѡ���б�",
		pager:"pager",
		url:"rcsw_txhd_xs_xmsq.do?method=xmsqAjaxXs&type=wsq",
		colList:[
		   {label:'key',name:'xmdm', index: 'xmdm',hidden:true,key:true},
		   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'20%'},
		   {label:'�ʱ��',name:'hdsj', index: 'hdsj',width:'30%'},
		   {label:'��ص�',name:'hddd', index: 'hddd',width:'20%'},
		   {label:'ʣ������',name:'syme', index: 'syme',width:'10%'},
		   {label:'����״̬',name:'shztmc', index: 'shztmc',width:'20%'},
		   {label:'����״̬id',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {name:'splc', index: 'splc',hidden:true},
		   {name:'sqid', index: 'sqid',hidden:true}
		   
		],
		// dblclick:function(rowObject){
			// ѡ���λ
		// xzGw(rowObject);
		// },
		sortname: "shzt",
	 	sortorder: "asc"
	};

var gridSetting2 = {
		caption:"��Ŀ�����б�",
		radioselect:true,
		pager:"pager",
		url:"rcsw_txhd_xs_xmsq.do?method=xmsqAjaxXs&type=ysq",
		colList:[
		   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
		   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'10%'},
		   {label:'�ʱ��',name:'hdsj', index: 'hdsj',width:'20%'},
		   {label:'��ص�',name:'hddd', index: 'hddd',width:'10%'},
		   {label:'��������',name:'sqrssx', index: 'sqrssx',width:'5%'},
		   {label:'����������',name:'ysqrs', index: 'ysqrs',width:'10%'},
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'25%'},
		   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'20%'},
		   {label:'���״̬',name:'shzt', index: 'shzt',width:'5%',hidden:true},
		   {name:'splc', index: 'splc',hidden:true}
		],
		sortname: "sqsj",
	 	sortorder: "desc"
	};




/**
 * �޸������
 * 
 * @returns {Boolean}
 */
function xgSqb(){
	 var xh = jQuery('#userName').val();
	 var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
		} else {
			var shzt=rows[0]["shzt"];
			if(shzt!='0'&&shzt!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			var gwdm=rows[0]["gwdm"];
			var url = 'rcsw_txhd_xs_xmsq.do?method=update&sqid=' + rows[0]["sqid"]+"&xh="+xh;
			var title = "�޸ĸ�λ����";
			showDialog(title, 800, 500, url);
		}
}

function updateForm(type){
	
	if(type == 'submit'){
		var sqly = jQuery("#sqly").val();
		if(sqly==null||""==sqly){
			alertInfo("��������������");
			return false;
		}
		jQuery("#but_save").hide();
				var url = "rcsw_txhd_xs_xmsq.do?method=update&type="+type;
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlert(data["message"],{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
				});
			}	
	else{
		var url = "rcsw_txhd_xs_xmsq.do?method=update&type="+type;
		ajaxSubFormWithFun("demoForm",url,function(data){
			 showAlert(data["message"],{},{"clkFun":function(){
    				if (parent.window){
    					refershParent();
    				}
    			}});
		});
	}
	
	
}

/**
 * ��д�����
 */
function xmsq(){

	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("������ѡ��һ��������Ŀ��");
		return false;
	}

	if(rows[0]["sqid"] != '' && rows[0]["sqid"] != null){
		showAlertDivLayer("��ѡ��Ŀ�����룬��ȷ�ϣ�");
		return false;
	}
	
	if(rows[0]["syme"] =='0'){
		showAlertDivLayer("��ǰ���Ŀ��������������");
		return false;
	}
	

	var xmdm = rows[0]["xmdm"];
	var xh = jQuery('#userName').val();
	
	var url="rcsw_txhd_xs_xmsq.do?method=xmsqXs&xmdm=" + xmdm+"&xh="+xh;
	
	showDialog("��Ŀ����",800,500,url,null);
}


/**
 * ���̸���
 * 
 * @return
 */
function viewLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}	


// �ύ
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var xh = jQuery('#userName').val();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
						jQuery.post("rcsw_txhd_xs_xmsq.do?method=subBusi", {
							sqid : rows[0]['sqid'],
							splc : rows[0]['splc'],
							xmdm : rows[0]['xmdm'],
							xh :xh
						}, function(data) {
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
		}
		});
		}
}

// ����
function cxSqb(){
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
			
				jQuery.post("rcsw_txhd_xs_xmsq.do?method=cancle", {
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


function tj(url){
	var sqly = jQuery("#sqly").val();
	if(sqly==null||""==sqly){
		alertInfo("��������������");
		return false;
	}
	ajaxSubFormWithFun("demoForm",url,function(data){
		 if(data["message"]=="����ɹ���"||data["message"]=="�ύ�ɹ���"){
   		 showAlertDivLayer(data["message"],{},{"clkFun":function(){
   				if (parent.window){
   					refershParent();
   				}
   			}});
   	 }
		 else{
   		 showAlertDivLayer(data["message"]);
   	 }
	});
	}


/**
 * ɾ������
 */
function delSqb(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = [];
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i]['sqid'])
	}
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
		showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
		return false;
	}else {
		showConfirmDivLayer("��ȷ��Ҫȡ����������",{"okFun":function(){
			jQuery.post("rcsw_txhd_xs_xmsq.do?method=del",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}
/**
 * ���صǼǱ�
 */
function printXmdjb(url){
var sqid="";
var gwdm="";
var rows = jQuery("#dataTable").getSeletRow();
if (rows.length <1) {
 showAlertDivLayer("������ѡ��һ����¼��");
} else {
for(var i=0;i<rows.length;i++){
if(i==rows.length-1){
	sqid +=rows[i]["sqbh"];
	gwdm +=rows[i]["gwdm"];
}else{
	sqbh +=rows[i]["sqid"]+",";
	gwdm +=rows[i]["gwid"]+",";
}
}
var url = url + "&sqid=" +sqid+"&gwdm="+gwdm;
window.open(url);
}

}

/**
 * �л�tabҳ
 * 
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);
	if (shzt == "wsq"){
		jQuery('#li_ts :first').text(jQuery('#curXn').val());
		jQuery("#li_sq").css("display","");
		jQuery("#li_xg").css("display","");
		jQuery("#li_sc").css("display","");
		jQuery("#li_tj").css("display","");
		jQuery("#li_cx").css("display","none");
		jQuery("#li_lc").css("display","none");
		jQuery("#dataTable").initGrid(gridSetting1);
	} else {
		jQuery('#li_ts :first').text('');
		jQuery("#li_sq").css("display","none");
		jQuery("#li_xg").css("display","none");
		jQuery("#li_sc").css("display","none");
		jQuery("#li_tj").css("display","none");
		jQuery("#li_cx").css("display","");
		jQuery("#li_lc").css("display","");
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}



function auotSetCanCheck(){
	jQuery("tr[name=checkxm]").each(function(){
		var syme=jQuery(this).find("td[name=syme]").text();
		syme=jQuery.trim(syme);
		if(parseInt(syme,10)<1){
			jQuery(this).find("[name=checkbox]").attr("disabled",true);
		}
	});
}

