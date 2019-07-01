//���ѹ����ѯ
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}
//������ȸ��²���List
function changeNd(){
	jQuery("#tbody_jfxx").html("");
	jQuery("#ndtemp").val(jQuery("#nd").val());
	changeBm();
}

//����ѧ����²���list
function changeBm(){
	 var nd=jQuery("#ndtemp").val();
	 var xn=jQuery("#xn").val();
	 var xq=jQuery("#xq").val();
	 jQuery.ajax({
		   url: "qgzx_jfgl_ajax.do?method=getBM",
		   dataType: "json",
		   type:"post",
		   data:{xn:xn,nd:nd,xq:xq},
		   success: function(data){
			   if(data){
				   jQuery("#bm option").remove();
				   var optionsHtml="";
				   for(var i=0;i<data.length;i++){
					   var bmdm=data[i].bmdm;
					   var bmmc=data[i].bmmc;
					   optionsHtml+="<option value="+bmdm+">"+bmmc+"</options>"
				   }
				   if(optionsHtml==""){
					   optionsHtml="<option value=-1>�޶�Ӧ����</options>"
					   cleanGwxx();
				   }
				   jQuery("select[name=bm]").append(optionsHtml);
			   }
			   jQuery("select[name=bm]").each(function(){
				   bmdm=jQuery(this).first("option").val();
				   setGwxx(this,xn,bmdm);
			   });
		   }
	 });
}
/**
 * ���Ĳ���ѡ��
 * @return
 */
function changeTrBmdm(jsobj){
	 var obj=jQuery(jsobj);
	 cleanGwxx(obj);
	 var xn=jQuery("#xn").val();
	 var bmdm=obj.val();
	 setGwxx(obj, xn, bmdm);
}
/**
 * ��ո�λ��Ϣ
 * @param obj Ҫ��յ� �ж���(<select>)
 * @return
 */
function cleanGwxx(obj){
	if(null==obj){
		obj= jQuery("select[name=bm]");
	}
	obj.parents("tr").find("span[name=gws]").text("");
	obj.parents("tr").find("span[name=xqrs]").text("");
	obj.parents("tr").find("input[name=hbje]").val("");
}
/**
 * ���ø�λ��Ϣ
 * @param obj Ҫ���õ��ж���
 * @param nd  ���
 * @param bmdm ���ű���
 * @return
 */
function setGwxx(obj,xn,bmdm){
	 jQuery.ajax({
		   url: "qgzx_jfgl_ajax.do?method=getGwxx",
		   dataType: "json",
		   type:"post",
		   data:{xn:xn,yrdwdm:bmdm,xq:jQuery("#xq").val()},
		   success: function(data){
//			   var hbje="";
//			   if(data.xqrs&&data.gwcjsx){
//				   hbje=parseInt(data.xqrs)*parseInt(data.gwcjsx);
//			   }
			   var gws=data.gws;
			   if(gws=="0"||gws==0){
				   gws="";
			   }
			   jQuery(obj).parents("tr").find("span[name=gws]").text(gws);
			   jQuery(obj).parents("tr").find("span[name=xqrs]").text(data.xqrs);
			   jQuery(obj).parents("tr").find("input[name=hbje]").val(data.hbje);
		   }
	 });
}
function searchRs(){
	var url = "qgzx_jfgl_ajax.do?method=jfxxCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}
//�޸Ļ��߲鿴
function showModi(doType){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		var pkValue=jQuery("[name=div_pkValue]:checked").val();	
		var url="qgzx_jfgl.do?method=jfxxXg";		
		url+="&pkValue="+pkValue;
		url+="&doType="+doType;
		//showTopWin(url,800,600);
        var title = doType == "update"?"�����޸�":"�鿴��ϸ";
        showDialog(title, 760, 525, url);
	}else{
		alertInfo("�빴ѡһ����¼���в�����");
		return false;
	}
}

//���ѹ�������
var count = 0;


//����
function addTr(){
	tr = jQuery('#hidden_jfxx').html();
	tr = tr.replace(/hbsj!!@@!!id/i,'hbsj'+(count));
	tr = tr.replace(/hbsj!!@@!!onclick/i,'hbsj'+(count++));
	jQuery('#tbody_jfxx').append(tr);
	var obj=jQuery('#tbody_jfxx').find("select[name=bm]").last();
	changeTrBmdm(obj);
	return false;
}


//ɾ��
function delTr(){
	var checkbox = jQuery('#tbody_jfxx').find('input[type=checkbox]:checked[name!=th]');
	
	if (checkbox.length > 0){
		for (var i = 0 ; i < checkbox.length ; i++){
			jQuery(checkbox[i]).parents("tr").eq(0).remove();
		}
	} else {
		alertInfo('��ѡ����Ҫɾ������!',function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
}


//ȫѡ
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}


// ����
function bcZjjfxx(){
	if($("nd") && $("nd").value==""){
 		alertInfo("��Ȳ���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#tbody_jfxx tr").length<1){
		alertInfo("������Ŀ����Ϊ��,����������һ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	var bms = jQuery("#tbody_jfxx tr td:nth-child(2)");
	var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(5)");
	var hbjes = jQuery("#tbody_jfxx tr td:nth-child(6)");
	var bzs = jQuery("#tbody_jfxx tr td:nth-child(7)");
	var bm = "";
	var hbsj ="";
	var hbje ="";
	var bz ="";
	for(var i = 0; i < bms.length;i++){
		var bmdm=jQuery(bms[i]).find("select[name=bm]").val();
		if("-1"==bmdm||""==bmdm){
			alertInfo("���Ų���Ϊ��!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
     		return false;
		}
		if(""==jQuery(hbsjs[i]).find("input[name=hbsj]").val()){
			alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
     		return false;
		}
		if(""==jQuery(hbjes[i]).find("input[name=hbje]").val()){
			alertInfo("��������Ϊ��!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
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
		alertInfo("ͬһ���Ų��ܴ���������ͬ����ʱ��ľ�����Ŀ,��ȷ��",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	var message = checkBcInfo();
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	confirmInfo("�Ƿ�Ҫ���������ݣ�",saveJfxxInfo);	
}

function bcInit(){
	if($("nd") && $("nd").value==""){
 		alertInfo("��Ȳ���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(jQuery("#tbody_jfxx tr").length<1){
		alertInfo("������Ŀ����Ϊ��,����������һ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	var bms = jQuery("#tbody_jfxx tr td:nth-child(2)");
	var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(5)");
	var hbjes = jQuery("#tbody_jfxx tr td:nth-child(6)");
	var bzs = jQuery("#tbody_jfxx tr td:nth-child(7)");
	var bm = "";
	var hbsj ="";
	var hbje ="";
	var bz ="";
	for(var i = 0; i < bms.length;i++){
		var bmdm=jQuery(bms[i]).find("select[name=bm]").val();
		if("-1"==bmdm||""==bmdm){
			alertInfo("���Ų���Ϊ��!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
     		return false;
		}
		if(""==jQuery(hbsjs[i]).find("input[name=hbsj]").val()){
			alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
     		return false;
		}
		if(""==jQuery(hbjes[i]).find("input[name=hbje]").val()){
			alertInfo("��������Ϊ��!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
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
		arr.push(bm0);
	});
	if(checkSame(arr)){
		alertInfo("ͬһ���Ų��ܴ���������ͬ������Ŀ,��ȷ��",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	confirmInfo("�Ƿ�Ҫ�·ݳ�ʼ����",saveJfxxInfo);	
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


//��֤��ǰ��¼���Ƿ����ظ�
function checkSaveInfo(bms,hbsjs){
	var isok=false;
	var all="qgzx";
	jQuery("#tbody_jfxx").find("tr").each(function(){
		var bm=jQuery(this).find("select[name=bm]").val();
		var hbsj=jQuery(this).find("input[name=hbsj]").val();
		var pk=bm+":"+hbsj;
		if(pk==all){
			isok=true;
			return false;
		}
		all=pk;
	});
	return isok;
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
	var url = "qgzx_jfgl_ajax.do?method=checkBcInfo";
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
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["xq"]=escape(jQuery("#xq").val());
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
		alertInfo('��ѡ����Ҫɾ������!',function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
}


// ����
function bcXgjfxx(){
	var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(4)");
	var hbjes = jQuery("#tbody_jfxx tr td:nth-child(5)");
	var bzs = jQuery("#tbody_jfxx tr td:nth-child(6)");
	if(jQuery("#qgzq").val() == "xn"){
		var hbsjs = jQuery("#tbody_jfxx tr td:nth-child(3)");
		var hbjes = jQuery("#tbody_jfxx tr td:nth-child(4)");
		var bzs = jQuery("#tbody_jfxx tr td:nth-child(5)");
	}
	var hbsj ="";
	var hbje ="";
	var bz ="";
	for(var i = 0; i < hbjes.length;i++){
		if(""==jQuery(hbjes[i]).find("input[name=hbje]").val()){
			alertInfo("��������Ϊ��!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
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



//��֤�������Ϣ
function checkXgBcInfo(){
	var data = "true";
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["nd"]=escape(jQuery("#nd").val());
    parameter["bm"]=escape(jQuery("#bm").val());
    parameter["hbje"]=escape(jQuery("#hbjes").val());
	var url = "qgzx_jfgl_ajax.do?method=checkXgBcInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}



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
    var xns = new Array();
    var xqs = new Array();
    var trObjArr = jQuery("#tbody_jfxx").find("tr");
    for (var i=0;i<trObjArr.length;i++){
    	var forxn = jQuery(trObjArr[i]).find("[name='xn']").val();
    	var forxq = jQuery(trObjArr[i]).find("[name='xq']").val();
    	forxq = (!forxq)?"":forxq;
    	xns.push(forxn);
    	xqs.push(forxq);
    }
    parameter["xns"]=xns;
    parameter["xqs"]=xqs;
	var url = "qgzx_jfgl_ajax.do?method=update";
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
