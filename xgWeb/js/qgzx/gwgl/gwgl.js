var demoHtml = "";

function initData(){//��λ����ʾ��
	
	demoHtml = "�밴���¸�ʽ��������\n\n";
	demoHtml += "���磺\n";
	demoHtml += "�����ص㣺A¥101��\n";
	demoHtml += "�������ݣ��ճ��ĵ�����\n";
	demoHtml += "����ʱ�䣺����һ����������8:00~12:00)";
	if(null==jQuery("#gwms").val()||""==jQuery("#gwms").val()){
	jQuery("#gwms").val(demoHtml);
	jQuery("#gwms").css("color", "#999");
	}
	
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


jQuery(document).ready(function(){
	var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
	// �������� ���õ�н������
	var gwzgcjsx=jQuery("#gwzgcjsx").val();
	var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
	// ��λ���ó������
	var gwcjsx=jQuery("#gwcjsx").val();
	// ����˸�λδ����
	if(gwcjsx==""){
		jQuery("#gwcjsx").val(gwzgcjsx);
		jQuery("#gwcjsxh").text(gwzgcjsx);
	}
	if("no"==sfsdgwcjsx){
		jQuery("#gwcjsxTr").hide();
	}else{
		jQuery("#gwcjsxTr").show();
		if("no"==sfkgggwcjsx){
			jQuery("#gwcjsx").hide();
			jQuery("#gwcjsxh").show();
			jQuery("#sxcolor").hide();
		}else{
			jQuery("#gwcjsx").show();
			jQuery("#gwcjsxh").hide();
			jQuery("#sxcolor").show();
		}
	}
	
	var doType=jQuery("#doType").val();
	//��Ա���� ����ֻ�ܲ鿴
	if(doType=="ryxxZj"||doType=="ryxxTg"){
		jQuery("#gwcjsx").hide();
		jQuery("#gwcjsxh").show();
		jQuery("#sxcolor").hide();
	}
});
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
	 		alertInfo("���޽��ó���ϵͳ���õĸ�λ��߳������("+gwzgcjsx+")!",function(tag){
	 			if(tag=="ok"){
	 				return false;
	 			}
	 		});
	 		return false;
		}
	}
	return true;
}
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


// ��ѯ
function searchRs(){
	var url = "qgzx_gwgl_ajax.do?method=gwxxCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}


// �޸�gwxxXg �鿴 gwxxCk��Ա����ryxxZj��Ա�˸�ryxxTg
function showModi(type){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		var url="qgzx_gwgl.do?method=gwxxCz&pkValue="+pkValue;
		url+="&doType="+type;
		// showTopWin(url,800,600);
		showDialog('', 760, 488, url);
	}else{
		alertInfo("�빴ѡһ����Ҫ�����ļ�¼��");
		return false;
	}
}
//��λ��Ϣ����
function gwxxDr(){
	toImportData("IMPORT_QGZX_GWXX");
	return false;
}

//��λ��Ա��Ϣ����
function gwryDr(){
	toImportData("IMPORT_QGZX_GWRY");
	return false;
}

//��λ��Ա��Ϣ����
function gwryDr(){
	toImportData("IMPORT_QGZX_GWRY");
	return false;
}

// ����
function copyGwxx(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	var blog=true;
	if(len>=1){	
		var array = jQuery("[name=div_pkValue]:checked");
		var num = 0;
		var str = "";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).parent().parent().find("td").eq(2).text();
			if(str.indexOf(pkValue)==-1){
				str += pkValue+"!!@@!!";
				num++;
			}
		}
		var parameter={};
		url = "qgzx_gwgl_ajax.do?method=getXn";
		jQuery.getJSON(url,parameter,function(data){
			jQuery('#xn').empty();
			jQuery('#xn').append("<option value=''>--��ѡ��--</option>");
			if(data != null && data.length != 0){
				for(var i=0; i<data.length; i++){
					if(str.indexOf(data[i].xn)==-1){
						var option = "<option value=\"" + data[i].xn + "\">" + data[i].xn + "</option>";
						jQuery('#xn').append(option);
					}
				}
			}
		});
		jQuery('#yxgw').html("��ǰ��ѡ��<font class='red'>"+num+"</font>��ѧ���е�<font class='red'>"+len+"</font>����λ");
		tipsWindown("��λ����","id:tempDiv","380","180","true","","true","id");
	}else{
		alertInfo("�빴ѡ��Ҫ���Ƶļ�¼��",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}


// ���Ʊ���
function gwxxDivSave(){
	if($("xn").value.trim()==""){
		alertInfo("��ѡ��Ҫ���Ƶ�ѧ�꣡",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	document.getElementById('tempDiv').style.display='none';
	var array = jQuery("[name=div_pkValue]:checked");
	var str = "";
	for (var i=0;i<array.length;i++) {
		var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
		str += pkValue+"!!@@!!";
	}
	var message = checkFzInfo(str);
	if(message!="true"){
		alertInfo(message,function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
	var parameter={}
	var url="qgzx_gwgl_ajax.do?method=gwxxFz&xn=";
	url+=$("xn").value.trim()
	parameter["pkValue"]=str;							
	jQuery.ajaxSetup({async:false});	
	jQuery.post(url,
		parameter,
		function(result){
			alertInfo(result,function(tag){
				if(tag=="ok"){
					closeWindown();
					searchRs();
				}
			});
		}
	);
	jQuery.ajaxSetup({async:true});
	
}


// ɾ��
function deleteGwxx(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len>=1){
		var array = jQuery("[name=div_pkValue]:checked");
		var str = "";
		for (var i=0;i<array.length;i++) {
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
				var url="qgzx_gwgl_ajax.do?method=gwxxSc";	
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


// ��֤������Ϣ
function checkFzInfo(str){
	var date = "true";
	var parameter={};
	var url="qgzx_gwgl_ajax.do?method=checkFzInfo&xn=";
	url+=$("xn").value.trim()
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


// ��֤ɾ����Ϣ
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

// ���ӱ����λ��Ϣ
function zjBcGwxx(){
	if($("xn") && $("xn").value==""){
 		alertInfo("ѧ�겻��Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
	if($("yrbm") && $("yrbm").value==""){
 		alertInfo("���˲��Ų���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
		}
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
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
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
	if(!checkSxje()){
		return false;
	}
	saveZjGwxxInfo();	
}


// ��֤������Ϣ
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
	parameter["gwmc"]=escape(jQuery("#gwmc").val());
	var url = "qgzx_gwgl_ajax.do?method=checkBcInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}


// ���ӱ����λ��Ϣ
function saveZjGwxxInfo(){
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=jQuery("#gwmc").val();
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["gwms"]=jQuery("#gwms").val();
	parameter["gwryyq"]=jQuery("#gwryyq").val();
	parameter["gwyqryxg"]=jQuery("#gwyqryxg").val();
	parameter["bz"]=jQuery("#bz").val();
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	var url = "qgzx_gwgl_ajax.do?method=save";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
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


// ��֤��������
function checkXqrs(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}


// ��֤�޸ĸ�λ��Ϣ
function bcXgGwxx(){
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
		if(parseFloat($("xqrs").value)<parseFloat($("zgrs").value)){
			alertInfo("������������С���ڸ�����!",function(tag){
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
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
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
	
	if(!checkSxje()){
		return false;
	}
	saveXgGwxxInfo();	
}


// �����޸ĸ�λ��Ϣ
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
	var url = "qgzx_gwgl_ajax.do?method=update";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
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


// ȫѡ
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}


// ������
function addTr(){
	// showTopWin('qgzx_gwgl.do?method=getStu&pkValue='+jQuery("#pkValue").val(),800,700);
	var xn=jQuery("#xn").val();
	var url='qgzx_gwgl.do?method=getStu&pkValue='+jQuery("#pkValue").val()+'&xn='+xn;
	showDialog('', 760, 520, url);
	return false;
}


// ɾ����
function delTr(){
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	// [982]�Ų�· ����ɾ���жϣ��������ݲ���ɾ��
	var isSqsj=false;
	var show="ѧ��<br>[<font color='red'>";
	if (checkbox.length > 0){
		var xhs = "";
		for (var i = 0 ; i < checkbox.length ; i++){
			var xh = jQuery(checkbox[i]).val()+"!!@@!!";
			var xxxx=xh.split("!!@@!!");
			if(xxxx[2]=="1"||xxxx[2]==1){
				show+=xxxx[0];
				show+=",</br>";
				isSqsj=true;
			}
			xhs+=xh;
		}
		// ȥ�����һ������ ",</br>"
		show=show.substring(0, show.length-",</br>".length);
		show+="</font>]<br>";
		if(isSqsj){
			show+="Ϊ�������ݣ�����ɾ����";
			alertInfo(show);
			return false;
		}
		var message = checkScRyxx(xhs);
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
				jQuery.ajaxSetup({async:false});
				// �õ�JSON����
			    var parameter ={};	
			    parameter["gwdm"]=escape(jQuery("#pkValue").val());
			    parameter["xh"]=xhs;
				var url = "qgzx_gwgl_ajax.do?method=bcScryxx";
			    $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						document.forms[0].action = window.location.href;
						document.forms[0].submit();
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	} else {
		alertInfo('��ѡ����Ҫɾ������!',function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
}


function checkScRyxx(xhs){
	var message = "true";
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=xhs;
	var url = "qgzx_gwgl_ajax.do?method=checkScRyxx";
	jQuery.post(url,parameter,
		function(result){
			message = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return message;
}

// ���ѧ����Ϣ
function getXsxx(){
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["pkValue"]=escape(jQuery("#xhs").val());
	var url = "qgzx_gwgl_ajax.do?method=getXsxx";
	jQuery.getJSON(url,parameter,
		function(data){
			if(data!=null && data.length !=0){
				var tr="";
				for(var i = 0; i < data.length; i++){
					tr+="<tr><td><input id='xh' name='xh' type='checkbox' value='"+data[i].xh+"'/></td><td>"+data[i].xh+"</td>";
					tr+="<td>"+data[i].xm+"</td><td>"+data[i].bjmc+"</td><td>"+data[i].sfkns+"</td>";
					tr+="<td><a href='#' onclick='showXsxx(this);return false;' value='"+data[i].xh+"'>"+data[i].qggws+"</a></td>";
					tr+="</tr>";					
				}
				jQuery('#tbody_zgryxx').prepend(tr);
			}
		}
	);
	jQuery.ajaxSetup({async:true});
	jQuery("#xhTal").val(jQuery("#xhTal").val()+jQuery("#xhs").val());
}


// ��֤������Ա��Ϣ
function bcZjRyxx(){
	var len = jQuery("#xhs").val().split("!!@@!!").length-1;
	if(len>parseInt(jQuery("#xqrs").val())){
		alertInfo('���ӵ���Ա�Ѵ��ڸ�λ������������',function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	saveRyxxInfo();
	
}


// ������Ա��Ϣ
function saveRyxxInfo(){
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["fknsrs"]=escape(jQuery("#fknsrs").val());
    parameter["xn"]=escape(jQuery("#xn").val());
	var url = "qgzx_gwgl_ajax.do?method=bcZjryxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if(result!="����ɹ�"){
				alertInfo(result,function(tag){
		 			if(tag=="ok"){
		 				return false;
		 			}
		 		});
		 		return false;
			}else{
				document.forms[0].action = window.location.href;
				document.forms[0].submit();
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}

// �˸���Ϣdiv
function tgRyxx(){
	var len=jQuery("input[type=checkbox][name!=th]:checked").length;
	var blog=true;
	if(len>=1){	
		var array = jQuery("input[type=checkbox][name!=th]:checked");
		var xhs = "";
		var sgsjs = "";
		var sqbhs="";
		for (var i=0;i<array.length;i++) {
			var pkValue = jQuery(array[i]).val().split("!!@@!!");
				xhs += pkValue[0]+"!!@@!!";
				sgsjs += pkValue[1]+"!!@@!!";
				sqbhs += pkValue[3]+"!!@@!!";
		}
		tipsWindown("��Ա�˸�","id:tempDiv","380","250","true","","true","id");
		jQuery('#xhs').val(xhs);
		jQuery('#sgsjs').val(sgsjs);
		jQuery('#sqbhs').val(sqbhs);
		jQuery('#tgry').html("��ǰ��ѡ��<font class='red'>"+len+"</font>���ڸ�ѧ�������˸�");
	}else{
		alertInfo("�빴ѡ��Ҫ�˸ڵ���Ա��",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
	}
}

// �����˸���Ϣ��֤
function bcTgryxx(){
	if($("tgyy") && $("tgyy").value==""){
		alertInfo("�˸�ԭ����Ϊ��!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
 		return false;
	}
	saveTgryxx();
}


function saveTgryxx(){
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=escape(jQuery('#xhs').val());
    parameter["sgsj"]=escape(jQuery('#sgsjs').val());
    parameter["tgyy"]=escape(jQuery("#tgyy").val());
    parameter["sqbhs"]=escape(jQuery("#sqbhs").val());
	var url = "qgzx_gwgl_ajax.do?method=bcTgryxx";
	jQuery.post(url,parameter,
		function(result){
			alertInfo(result,function(tag){
				if(tag="ok"){
					closeWindown();
					document.forms[0].action = window.location.href;
					document.forms[0].submit();
					return false;
				}
			});
			return false;
		}
	);
	jQuery.ajaxSetup({async:true});
}

// չʾѧ����λ��Ϣ
function showXsxx(obj){
	jQuery.ajaxSetup({cache:false});
	jQuery.ajaxSetup({async:false});
	var parameter = {};
	parameter["xh"]=escape(obj);
	var url="qgzx_gwgl_ajax.do?method=ryxxCk";
	jQuery.getJSON(url,parameter,
			function(data){
				jQuery('#tbody_ryxx').empty();
				jQuery('#tbody_zggwxx').empty();
				if(data!=null){
					var tr="<tr><th width='16%'>ѧ��</th><td width='34%'>"+data.xh+"</td><th width='16%'>����</th><td width='34%'>"+data.xm+"</td></tr>";
					tr+="<tr><th>�Ա�</th><td>"+data.xb+"</td><th>�꼶</th><td>"+data.nj+"</td></tr>";
					tr+="<tr><th>"+jQuery("#xbmc").val()+"</th><td>"+data.xymc+"</td><th>רҵ</th><td>"+data.zymc+"</td></tr>";
					tr+="<tr><th>�༶</th><td>"+data.bjmc+"</td><th>��ϵ�绰</th><td>"+((data.lxdh==null)?"":data.lxdh)+"</td></tr>";
					jQuery('#tbody_ryxx').append(tr);
					if(data.zggwHtml==null||""==data.zggwHtml){
						jQuery('#thead_zggwxx').hide();
						jQuery('#tbody_zggwxx').append("<tr><td>��ѧ�����ϸڼ�¼��</td></tr>");
						
					}else{
						jQuery('#thead_zggwxx').show();
						jQuery('#tbody_zggwxx').append(data.zggwHtml);
					}
				}
			}
		);
	jQuery.ajaxSetup({async:true});
	// showTopWin(url,600,400);
	// tipsWindown("ѧ���ڸ���Ϣ","id:xszgxxDiv","540","300","true","","true","id");
	tipsWindownNew("ѧ���ڸ���Ϣ","id:xszgxxDiv",540,320);
}

function refreshParentTg(){
	var api = frameElement.api,W = api.opener;
	
	if(W == undefined){
		if(W && W.document.getElementById('search_go')){
			W.document.getElementById('search_go').click();
		}
	}else{
		if(	W && W.document.getElementById('search_go')){
			W.document.getElementById('search_go').click();		
		}
	}
	Close();
}

//�°�鿴������
function zxsxxView(xh) {
	showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
			+ "&xs");
}