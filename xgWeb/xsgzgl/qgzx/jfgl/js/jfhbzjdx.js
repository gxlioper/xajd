//�߼���ѯ
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

//ȫѡ
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}

//��������ҳ��
function add(){
	var url = "qgzx_jfgl.do?method=jfhbxxAdd";
	var title = "���ѻ�������";
	showDialog(title,800,500,url);
}

//���ѹ�������
var count = 0;
//���Ӿ�����
function addTr(){
	tr = jQuery('#hidden_jfxx').html();
	tr = tr.replace(/hbsj!!@@!!id/i,'hbsj'+(count));
	tr = tr.replace(/hbsj!!@@!!onclick/i,'hbsj'+(count++));
	jQuery('#tbody_jfxx').append(tr);
	var obj=jQuery('#tbody_jfxx').find("select[name=bm]").last();
	//changeTrBmdm(obj);
	return false;
}
//ɾ��������
function delTr(){
	var checkbox = jQuery('#tbody_jfxx').find('input[type=checkbox]:checked[name!=th]');
	
	if (checkbox.length > 0){
		for (var i = 0 ; i < checkbox.length ; i++){
			jQuery(checkbox[i]).parents("tr").eq(0).remove();
		}
	} else {
		alertInfo('��ѡ����Ҫɾ������!');
 		return false;
	}
}

//��������
function saveJfhb(){
	if($("nd") && $("nd").value==""){
 		alertInfo("��Ȳ���Ϊ��!");
 		return false;
	}
	if(jQuery("#tbody_jfxx tr").length<1){
		alertInfo("������Ŀ����Ϊ��,����������һ��!");
 		return false;
	}
	var bms = jQuery("#tbody_jfxx tr td:nth-child(2)");
	var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(3)");
	var hbjes = jQuery("#tbody_jfxx tr td:nth-child(4)");
	var bzs = jQuery("#tbody_jfxx tr td:nth-child(5)");
	var bm = "";
	var hbsj ="";
	var hbje ="";
	var bz ="";
	for(var i = 0; i < bms.length;i++){
		var bmdm=jQuery(bms[i]).find("select[name=bm]").val();
		if("-1"==bmdm||""==bmdm){
			alertInfo("���˵�λ����Ϊ��!");
     		return false;
		}
		if(""==jQuery(hbsjs[i]).find("input[name=hbsj]").val()){
			alertInfo("����ʱ�䲻��Ϊ��!");
     		return false;
		}
		if(""==jQuery(hbjes[i]).find("input[name=hbje]").val()){
			alertInfo("��������Ϊ��!");
     		return false;
		}
		bm+=jQuery(bms[i]).find("select[name=bm]").val()+"!!@@!!";
		hbsj+=jQuery(hbsjs[i]).find("input[name=hbsj]").val()+"!!@@!!";
		hbje+=jQuery(hbjes[i]).find("input[name=hbje]").val()+"!!@@!!";
		bz+=jQuery(bzs[i]).find("input[name=bz]").val()+"!!@@!!";
	}
	jQuery("#bms").val(bm);
	jQuery("#hbsjs").val(hbsj);
	jQuery("#hbjes").val(hbje);
	jQuery("#bzs").val(bz+"end");
	
	var arr=[];
	jQuery("#tbody_jfxx").find("tr").each(function(){
		var bm0=jQuery(this).find("select[name=bm]").val();
		var hbsj0=jQuery(this).find("input[name=hbsj]").val();
		var pk=bm0+":"+hbsj0;
		arr.push(pk);
	});
	if(checkSame(arr)){
		alertInfo("ͬһ���˵�λ���ܴ���������ͬ����ʱ��ľ�����Ŀ,��ȷ�ϣ�");
 		return false;
	}
	var message = checkBcInfo();
	if("true"!=message){
		alertInfo(message);
		return false;
	}
	confirmInfo("�Ƿ�Ҫ���������ݣ�",saveJfxxInfo);	
}

//�ж�����arr�Ƿ�����ظ�ֵ
function checkSame(arr){
	var json={};
	for (var i = 0; i < arr.length; i++) {
		if (!json[arr[i]]) {
			json[arr[i]] = 1;
		}else{
			return true;
		}
	}
	return false;
}

//��֤��ʷ��¼���Ƿ����ظ�����
function checkBcInfo(){
	var data = "true";
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["nd"]=escape(jQuery("#nd").val());
    parameter["bm"]=escape(jQuery("#bms").val());
    parameter["hbsj"]=escape(jQuery("#hbsjs").val());
	var url = "qgzx_jfgl.do?method=checkBcInfo";
	jQuery.post(url,parameter,
		function(result){
			data=result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}

function saveJfxxInfo(tag){
	if(tag=="ok"){
		jQuery.ajaxSetup({async:false});	
		// �õ�JSON����
	    var parameter ={};	
	    parameter["nd"]=escape(jQuery("#nd").val());
	    parameter["bm"]=escape(jQuery("#bms").val());
	    parameter["hbsj"]=escape(jQuery("#hbsjs").val());
	    parameter["hbje"]=escape(jQuery("#hbjes").val());
	    parameter["bz"]=escape(jQuery("#bzs").val());
	    var url="";
	    if(jQuery("#savetype").val()=="init"){
			url = "qgzx_jfgl_ajax.do?method=init";
		}else{
			url = "qgzx_jfgl_ajax.do?method=save";
		}
	    $("divWaiting").style.display="";
		$("divDisable").style.display="";
		jQuery.post(url,parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				if("����ɹ�"==result){
					//alertInfo(result);
					showAlert(result,{},{"clkFun":function(){
		 				if (parent.window){
		 					refershParent();
		 				}
		 			}});
				}else{
					alertInfo(result,function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
				}
			}
		);
		jQuery.ajaxSetup({async:true});
		}
	}

//�޸�
function showModi(doType){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("�빴ѡһ����¼���в�����");
	}else{
		var url='qgzx_jfgl.do?method=jfhbxxUpdate&pkValue='+ rows[0]["pkvalue"];
		url+="&doType="+doType;
		showDialog('���ѻ����޸�', 760, 525, url);
	}
}

//������Ϣ�޸�
//ɾ��
function delXgTr(){
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length > 0){
		confirmInfo("ȷ��Ҫɾ����ѡ�ľ�����Ŀ��",function(tag){
			if(tag=="ok"){	
				for (var i = 0 ; i < checkbox.length ; i++){
					jQuery(checkbox[i]).parents("tr").eq(0).remove();
				}
				return false;
			}
		});
		return false;
	} else {
		alertInfo('��ѡ����Ҫɾ������!');
		return false;
	}
}

//�޸ķ�������
function saveXgJfhb(){
	var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(2)");
	var hbjes = jQuery("#tbody_jfxx tr td:nth-child(3)");
	var bzs = jQuery("#tbody_jfxx tr td:nth-child(4)");
	var hbsj ="";
	var hbje ="";
	var bz ="";
	for(var i = 0; i < hbjes.length;i++){
		if(""==jQuery(hbjes[i]).find("input[name=hbje]").val()){
			alertInfo("��������Ϊ��!");
     		return false;
		}
		hbsj+=jQuery(hbsjs[i]).find("input[name=hbsj]").val()+"!!@@!!";
		hbje+=jQuery(hbjes[i]).find("input[name=hbje]").val()+"!!@@!!";
		bz+=jQuery(bzs[i]).find("input[name=bz]").val()+"!!@@!!";
	}
	
	jQuery("#hbsjs").val(hbsj);
	jQuery("#hbjes").val(hbje);
	jQuery("#bzs").val(bz+"end");
	var message = checkXgBcInfo();
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	confirmInfo("�Ƿ�Ҫ�����޸ĵ����ݣ�",saveXgJfxxInfo);	
}
//��֤�޸ı������Ϣ
function checkXgBcInfo(){
	var data = "true";
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["nd"]=escape(jQuery("#nd").val());
    parameter["bm"]=escape(jQuery("#bm").val());
    parameter["hbje"]=escape(jQuery("#hbjes").val());
	var url = "qgzx_jfgl.do?method=checkXgBcInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}
//�޸ı���
function saveXgJfxxInfo(tag){
	if(tag=="ok"){
		jQuery.ajaxSetup({async:false});	
		// �õ�JSON����
	    var parameter ={};	
	    parameter["nd"]=escape(jQuery("#nd").val());
	    parameter["bm"]=escape(jQuery("#bm").val());
	    parameter["hbsj"]=escape(jQuery("#hbsjs").val());
	    parameter["hbje"]=escape(jQuery("#hbjes").val());
	    parameter["bz"]=escape(jQuery("#bzs").val());
		var url = "qgzx_jfgl.do?method=updateBc";
	    $("divWaiting").style.display="";
		$("divDisable").style.display="";
		jQuery.post(url,parameter,
			function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				if("����ɹ�"==result){
					//alertInfo(result);
					 showAlert(result,{},{"clkFun":function(){
			 				if (parent.window){
			 					refershParent();
			 				}
			 			}});
				}else{
					alertInfo(result,function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
				}
			}
		);
		jQuery.ajaxSetup({async:true});
		}
	}

//�鿴
function view(doType){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("�빴ѡһ����¼���в�����");
	}else{
		var url='qgzx_jfgl.do?method=jfhbxxView&pkValue='+ rows[0]["pkvalue"];
		showDialog('���ѻ����鿴', 760, 525, url);
	}
}

//����
function importJfhb(){
	toImportDataNew("IMPORT_QGZX_JFHB_ZJDX");
	return false;
}

//dcclbh,�������ܱ��
var DCCLBH = "qgzx_jfcjgl_jfhb_zjdx.do";
//�Զ��嵼��
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}
// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qgzx_jfgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}