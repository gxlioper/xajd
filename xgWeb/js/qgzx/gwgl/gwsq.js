
var demoHtml = "";

function initData(){//��λ����ʾ��
	demoHtml = "�밴���¸�ʽ��������\n\n";
	demoHtml += "���磺\n";
	demoHtml += "�����ص㣺A¥101��\n";
	demoHtml += "�������ݣ��ճ��ĵ�����\n";
	demoHtml += "����ʱ�䣺����һ����������8:00~12:00)";
	
	jQuery("#gwms").val(demoHtml);
	jQuery("#gwms").css("color", "#999");
	jQuery("#gwms").focus( function() {
		if (jQuery(this).val() == demoHtml) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}else{
			jQuery(this).css("color", "");
		}
	});

	jQuery("#gwms").blur( function() {
		if (jQuery(this).val() == "") {
			jQuery(this).val(demoHtml);
			jQuery(this).css("color", "#999");
		}
	});
}


function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


//��ѯ
function searchRs(){
	var url = "qgzx_gwgl_ajax.do?method=gwsqCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//����
function showAdd(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		alertInfo("δ���Ÿ�λ������ڸ�λ����ʱ�����,��ȷ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	//showTopWin('qgzx_gwgl.do?method=gwsqZj',720,500);
	showDialog('', 760, 590, 'qgzx_gwgl.do?method=gwsqZj');
}


//�޸�update	�鿴	view
function showModi(type){
	var kycz = jQuery("#kycz").val();
	if("update"==type&&"true"!=kycz){
		alertInfo("δ���Ÿ�λ������ڸ�λ����ʱ�����,��ȷ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
	    var shzt = jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(9).text();
		if("update"==type && ("ͨ��"==shzt || "��ͨ��"==shzt)){
			alertInfo("�Ѿ���˹������ݲ����޸�!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		var url="qgzx_gwgl.do?method=gwsqXg&pkValue="+pkValue;
		url+="&doType="+type;
		//showTopWin(url,720,500);
		showDialog('', 760, 590, url);
	}else{
		alertInfo("�빴ѡһ����Ҫ�����ļ�¼��");
		return false;
	}
}


//ɾ��
function deleteGwsq(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		alertInfo("δ���Ÿ�λ������ڸ�λ����ʱ�����,��ȷ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		var shztIndex = 9;
		for (var i=0;i<array.length;i++) {
			var shzt = jQuery(array[i]).parent().parent().find("td").eq(shztIndex).text();
			if("ͨ��"==shzt || "��ͨ��"==shzt){
				alertInfo("�Ѿ���˹������ݲ���ɾ��!",function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
			str += pkValue+"!!@@!!";
		}
		var message = checkScInfo(str);
		if("true"!=message){
			alertInfo(message,function(tag){
				if(tag=="ok"){
					return false;
				}
			});
			return false;
		}
		confirmInfo("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",function(tag){
			if(tag=="ok"){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var parameter={}
				var url="qgzx_gwgl_ajax.do?method=gwsqSc";	
				parameter["pkValue"]=str;							
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						if("ɾ���ɹ�"==result){
							alertInfo(result,function(tag){
								if(tag=="ok"){
									searchRs();
								}
							});
						}else{
							alertInfo(result);
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}


//��֤ɾ����Ϣ
function checkScInfo(str){
	var data = "true";
	var parameter={}
	var url="qgzx_gwgl_ajax.do?method=checkScInfo";	
	parameter["pkValue"]=str;							
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}

function checkSxje(){
	var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
	//�������� ���õ�н������
	var gwzgcjsx=jQuery("#gwzgcjsx").val();
	var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
	if("yes"==sfsdgwcjsx&&"yes"==sfkgggwcjsx){
		//��λ���ó������
		var gwcjsx=jQuery("#gwcjsx").val();
		if(gwcjsx==""){
	 		alertInfo("���޽���Ϊ��!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
		if(parseInt(gwcjsx)>parseInt(gwzgcjsx)){
	 		alertInfo("���޽��ó���ϵͳ���õĸ�λ��߳������("+gwzgcjsx+"Ԫ)!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}
	return true;
}

//���ӱ����λ��Ϣ	
function zjBcGwsq(){
	
	if($("gwmc") && $("gwmc").value==""){
 		alertInfo("��λ���Ʋ���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	var message = checkBcInfo("add");
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
 		alertInfo("��λ���ʲ���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("xqrs") && $("xqrs").value==""){
 		alertInfo("������������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("knsrs") && $("knsrs").value==""){
 		alertInfo("������������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		alertInfo("�����������ܴ�����������!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if($("gwms") && $("gwms").value==""){
 		alertInfo("��λ��������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwryyq") && $("gwryyq").value==""){
 		alertInfo("��λ��ԱҪ����Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(!checkSxje())	{
		return false;
	}
	saveZjGwsqInfo();	
}


//��֤������Ϣ
function checkBcInfo(type){
	var data ="true";
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    if(type=="update"){
    	parameter["pkValue"]=escape(jQuery("#pkValue").val());
    }
    parameter["xn"]=escape(jQuery("#xn").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=escape(jQuery("#gwmc").val().trim());
	var url = "qgzx_gwgl_ajax.do?method=checkZjGwsqInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}


//���ӱ����λ��Ϣ
function saveZjGwsqInfo(){
	
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};
	parameter["gwmc"]=jQuery("#gwmc").val();
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["gwms"]=jQuery("#gwms").val();
	parameter["gwryyq"]=jQuery("#gwryyq").val();
	parameter["gwyqryxg"]=jQuery("#gwyqryxg").val(); 
	parameter["bz"]=jQuery("#bz").val();
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	var url = "qgzx_gwgl_ajax.do?method=bcZjGwsq";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if("����ɹ�"==result){				
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


//��֤��������
function checkXqrs(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}


//��֤�޸ĸ�λ��Ϣ
function bcXgGwsq(){
	if($("gwmc") && $("gwmc").value==""){
 		alertInfo("��λ���Ʋ���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	var message = checkBcInfo("update");
	if("true"!=message){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
 		alertInfo("��λ���ʲ���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("xqrs") && $("xqrs").value==""){
 		alertInfo("������������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("knsrs") && $("knsrs").value==""){
 		alertInfo("������������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		alertInfo("�����������ܴ�����������!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if($("gwms") && $("gwms").value==""){
 		alertInfo("��λ��������Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwryyq") && $("gwryyq").value==""){
 		alertInfo("��λ��ԱҪ����Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("gwxh") && $("gwxh").value==""){
		alertInfo("��λ��Ų���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	if(!checkSxje())	{
		return false;
	}
	if(jQuery("#gwxh").val()!=null&&jQuery("#gwxh").val()!=""){
		if(!checkGwxh(jQuery("#gwxh").val())){
			 return false;
			}

}
	saveXgGwxxInfo();	
}


//�����޸ĸ�λ��Ϣ
function saveXgGwxxInfo(){
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["gwmc"]=jQuery("#gwmc").val();
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["gwms"]=jQuery("#gwms").val();
	parameter["gwryyq"]=jQuery("#gwryyq").val();
	parameter["gwyqryxg"]=jQuery("#gwyqryxg").val();
	parameter["bz"]=jQuery("#bz").val();
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	parameter["gwxh"]=escape(jQuery("#gwxh").val()); 
	parameter["oldGwxh"]=escape(jQuery("#oldGwxh").val());
	var url = "qgzx_gwgl_ajax.do?method=bcXgGwsqInfo";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			//alertInfo(result);
			if("����ɹ�"==result){
				// alertInfo(result);
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
