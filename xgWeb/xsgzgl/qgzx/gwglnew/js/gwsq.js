jQuery(function() {
	jQuery("#jfhb,#zc").change(function(){
		checkJe(this);
  		var jfhb = jQuery("#jfhb").val();
  		var zc = jQuery("#zc").val();
  		if(jfhb==""&&zc==""){
  			jQuery("#gwcjbz").val("");
  		}else{
  		if(jfhb!=""&&zc==""){
  			jQuery("#gwcjbz").val(parseInt(jfhb));
		}else if(jfhb==""&&zc!=""){
			jQuery("#gwcjbz").val(parseInt(zc));
		}else if(jfhb!=""&&zc!=""){
			jQuery("#gwcjbz").val(parseInt(jfhb)+parseInt(zc));
		}}
	});
});

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
	var url = "qgzx_gwglnew_ajax.do?method=gwsqCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}

//����
function showAdd(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
 		return false;
	}
	//showTopWin('qgzx_gwglnew.do?method=gwsqZj',720,500);
	showDialog('��λ����', 760, 520, 'qgzx_gwglnew.do?method=gwsqZj');
}


//�޸�update	�鿴	view
function showModi(type,title){
	var kycz = jQuery("#kycz").val();
	if("update"==type&&"true"!=kycz){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
 		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==1){	
	    var shzt = rows[0]["shzt"];
		if("update"==type && ("3"!=shzt && "0"!=shzt)){
			showAlertDivLayer("�Ѿ���˹������ݲ����޸ģ�");
	 		return false;
		}
		var pkValue=rows[0]["gwdm"];
		var url="qgzx_gwglnew.do?method=gwsqXg&pkValue="+pkValue;
		url+="&doType="+type;
		//showTopWin(url,720,500);
		showDialog(title, 760, 520, url);
	}else{
		showAlertDivLayer(jQuery("#lable_one_xg").val());
		return false;
	}
}


//ɾ��
function deleteGwsq(){
	var kycz = jQuery("#kycz").val();
	if("true"!=kycz){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
 		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if(ids.length>=1){
		var rows = jQuery("#dataTable").getSeletRow();
		var str = "";
		for (var i=0;i<ids.length;i++) {
			var shzt = rows[i]["shzt"];
			if(rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3"){
				showAlertDivLayer("�Ѿ���˹������ݲ���ɾ����");
		 		return false;
			}
			var pkValue = rows[i]["gwdm"];
			str += pkValue+"!!@@!!";
		}
		var message = checkScInfo(str);
		if("true"!=message){
			showAlertDivLayer(message);
			return false;
		}
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
			"okFun" : function() {
				var parameter={}
				var url="qgzx_gwglnew_ajax.do?method=gwsqSc";	
				parameter["pkValue"]=str;							
				jQuery.ajaxSetup({async:false});	
				jQuery.post(url,
					parameter,
					function(result){
						showAlertDivLayer(result);
						jQuery("#dataTable").reloadGrid();
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		});
	}else{
		showAlertDivLayer(jQuery("#lable_some_sc").val());
	}
}


//��֤ɾ����Ϣ
function checkScInfo(str){
	var data = "true";
	var parameter={}
	var url="qgzx_gwglnew_ajax.do?method=checkScInfo";	
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
			showAlert("��λ������޲���Ϊ�գ�");
	 		return false;
		}
		if(parseInt(gwcjsx)>parseInt(gwzgcjsx)){
			showAlert("��λ������޲��ó���ϵͳ���õĸ�λ��߳������("+gwzgcjsx+")��");
	 		return false;
		}
	}
	return true;
}
//���칤�̸��Ի�������Ա
/*function showModi(type){//var ids = jQuery("#dataTable").getSeletIds();
	//var rows = jQuery("#dataTable").getSeletRow();
	//var len=jQuery("[name=yxssz]:checked").length;
	//var ids = jQuery("#dataTable").getSeletIds();
	//var len = ids.length;
	var rows = jQuery("#dataTable").getSeletRow();
	var len = rows.length;
	if(len==1){	
		var pkValue=rows[0].val();
		alert(pkValue);
		var url="qgzx_gwgl.do?method=gwxxCz&pkValue="+pkValue;
		url+="&doType="+type;
		// showTopWin(url,800,600);
		showDialog('', 760, 488, url);
	}else{
		alertInfo("�빴ѡһ����Ҫ�����ļ�¼��");
		return false;
	}
}*/
//�޸�gwxxXg �鿴 gwxxCk��Ա����ryxxZj��Ա�˸�ryxxTg
function showModiTg(type,title){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==1){	
		if (rows[0]["shzt"] == "1") {
			var sfyxgw=rows[0]["sfyxgw"];
			if((type=='ryxxZj' || type=='ryxxTg') && sfyxgw=='��'){
				showAlertDivLayer("��ѡ����Ч��λ��");
				return false;
			}
			var pkValue=rows[0]["gwdm"];
			var url="qgzx_gwglnew.do?method=gwxxCz&pkValue="+pkValue;
			url+="&doType="+type;
			showDialog(title, 760, 520, url);
		}else{
			showAlertDivLayer("��ѡ��һ�����ͨ���ļ�¼��");
		}
	}else{
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	}
}
//���ӱ����λ��Ϣ	
function zjBcGwsq(type){
	if($("gwmc") && $("gwmc").value==""){
		showAlert("��λ���Ʋ���Ϊ�գ�");
 		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
		showAlert("��λ���ʲ���Ϊ�գ�");
 		return false;
	}
	
	if($("xqrs") && $("xqrs").value==""){
		showAlert("������������Ϊ�գ�");
 		return false;
	}
	if($("knsrs") && $("knsrs").value==""){
		showAlert("������������Ϊ�գ�");
 		return false;
	}
	if(jQuery("#zxdwlb") && jQuery("#zxdwlb").val() == ""){
		showAlert("���˵�λ��� ����Ϊ�գ�");
 		return false;
	}
	if(jQuery("#zjly") && jQuery("#zjly").val() == ""){
		showAlert("�ʽ���Դ����Ϊ�գ�");
 		return false;
	}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		showAlert("�����������ܴ�������������");
 		return false;
	}
	var yxsszLen = jQuery("[name=yxssz]:checked").length;
	if(yxsszLen==0){
		showAlert("��ѡ����Чʱ���ã�");
		return false;
	}
	var sfsgwsqsxzLen = jQuery("[name=sfsgwsqsxz]:checked").length;
	if(sfsgwsqsxzLen==0){
		showAlert("��ѡ���Ƿ��ܸ�λ���������ƣ�");
		return false;
	}
	if($("gwkssj").value==""){
		showAlert("��λ��ʼʱ�䲻��Ϊ�գ�");
 		return false;
	}
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='xs' && $("gwjssj").value==""){
		showAlert("��λ����ʱ�䲻��Ϊ�գ�");
		return false;
	}
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
		showAlert("��λ��������Ϊ�գ�");
 		return false;
	}
	if($("gwryyq") && $("gwryyq").value==""){
		showAlert("��λ��ԱҪ����Ϊ�գ�");
 		return false;
	}
    if(jQuery("#ssbm") && jQuery("#ssbm").val()==""){
    	showAlert("�������Ų���Ϊ�գ�");
 		return false;
	}
    if(jQuery("#fzls") && jQuery("#fzls").val()==""){
    	showAlert("������ʦ����Ϊ�գ�");
 		return false;
    }
    if(jQuery("#stfzrxm") && jQuery("#stfzrxm").val()==""){
    	showAlert("������ʦ����Ϊ�գ�");
 		return false;
    }
    if(jQuery("#ssxq") && jQuery("#ssxq").val()==""){
    	showAlert("����У������Ϊ�գ�");
 		return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
    	showAlert("��ϵ�绰����Ϊ�գ�");
 		return false;
    }
    if(jQuery("#lsbgs") && jQuery("#lsbgs").val()==""){
    	showAlert("������ʦ�칫�Ҳ���Ϊ�գ�");
    	return false;
    }
    if(jQuery("#gzdd") && jQuery("#gzdd").val()==""){
    	showAlert("�����ص㲻��Ϊ�գ�");
    	return false;
    }
    if(jQuery("#gzsj") && jQuery("#gzsj").val()==""){
    	showAlert("����ʱ�䲻��Ϊ�գ�");
    	return false;
    }
    if(jQuery("#gznr") && jQuery("#gznr").val()==""){
    	showAlert("�������ݲ���Ϊ�գ�");
    	return false;
    }
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("��λ����˲���Ϊ�գ�");
    	return false;
    } 
    if(jQuery("#gwcjbz") && jQuery("#gwcjbz").val()==""){
    	showAlert("��λ����׼����Ϊ�գ�");
    	return false;
    }
    if(jQuery("#jfhb") && jQuery("#jfhb").val()==""){
    	showAlert("���ѻ�������Ϊ�գ�");
    	return false;
    } 
    //����ѧԺ���Ի��ֶ�
    if(jQuery("#gwlx") && jQuery("#gwlx").val()==""){
    	showAlert("��λ���Ͳ���Ϊ�գ�");
    	return false;
    } 
    
    if(jQuery("#zxfdy") && jQuery("#zxfdy").val()==""){
    	showAlert("��ѧ������Ա����Ϊ�գ�");
    	return false;
    }

    if(jQuery("#lxr") && jQuery("#lxr").val()==""){
        showAlert("��ϵ�˲���Ϊ�գ�");
        return false;
    }
    
    if(jQuery("#jfhb").length > 0&&jQuery("#gwcjbz").length > 0&&jQuery("#zc").length > 0){
    	var gwcjbz= parseInt(jQuery("#gwcjbz").val());
    	var jfhb = parseInt(jQuery("#jfhb").val());
    	var zc = parseInt(jQuery("#zc").val());  
    	if((zc+jfhb)!=gwcjbz){
    		showAlert("�����ϡ���λ����׼=���ѻ���+�Գ��");
    		return false;
    	}
    }
    
    if((parseInt(jQuery("#gwcjbz").val())-parseInt(jQuery("#gwcjsx").val()))>0){
    	showAlert("��λ����׼���ô��ڸ�λ������ޣ�");
		return false;
    }
    
    if(jQuery("#xxdm").val() == '10351'){
    	if(jQuery("input[name='sqxy']:checked").length < 1){
    		showAlert("��ѡ���λ���뿪��ѧԺ��");
    		return false;
    	}
    }
    
    
	if(!checkSxje())	{
		return false;
	}
	var message = checkBcInfo("add");
	if("true"!=message){
		showAlert(message);
		return false;
	}
	saveZjGwsqInfo(type);	
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
    parameter["xq"]=escape(jQuery("#xq").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=escape(jQuery("#gwmc").val().trim());
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		parameter["gwjssj"]="";
	}else{
		parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	}
	parameter["yxssz"]=yxssz;
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	var url = "qgzx_gwglnew_ajax.do?method=checkZjGwsqInfo";
	jQuery.post(url,parameter,
		function(result){
			data = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return data;
}


//���ӱ����λ��Ϣ
function saveZjGwsqInfo(type){
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};
	parameter["gwmc"]=encodeURI(encodeURI(jQuery("#gwmc").val()));
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["yxssz"]=yxssz;
	parameter["sfsgwsqsxz"]=jQuery("[name=sfsgwsqsxz]:checked").val();
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	parameter["gwms"]=encodeURI(encodeURI(jQuery("#gwms").val()));
	parameter["gwryyq"]=encodeURI(encodeURI(jQuery("#gwryyq").val()));
	parameter["gwyqryxg"]=encodeURI(encodeURI(jQuery("#gwyqryxg").val()));
	parameter["bz"]=encodeURI(encodeURI(jQuery("#bz").val()));
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	parameter["zjly"] = jQuery("#zjly").val();
	parameter["zxdwlb"] = jQuery("#zxdwlb").val();
	parameter["ssbm"]=jQuery("#ssbm").val();
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#fzls").val()));
	parameter["lxdh"]=jQuery("#lxdh").val();
	parameter["lsbgs"]=encodeURI(encodeURI(jQuery("#lsbgs").val()));
	parameter["gzdd"]=encodeURI(encodeURI(jQuery("#gzdd").val()));
	parameter["gzsj"]=encodeURI(encodeURI(jQuery("#gzsj").val()));
	parameter["xq"]=encodeURI(encodeURI(jQuery("#xq").val()));
	
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#stfzr").val()));
	parameter["ssxq"]=encodeURI(encodeURI(jQuery("#ssxq").val()));
	parameter["type"]=encodeURI(encodeURI(type));
	parameter["gznr"]=encodeURI(encodeURI(jQuery("#gznr").val()));
	parameter["xn"]=jQuery("#xn").val();
	

	//���ݴ�ѧ���Ի�
	if(jQuery("#xxdm").val() == '10351'){
		var checkboxs = jQuery("input[name='sqxy']:checked");
		var sqxy = [];
		jQuery.each(checkboxs,function(i,n){
			sqxy.push(jQuery(n).val());
		});
		parameter["sqxy"] = sqxy;
		parameter["doType"] = "zj";
        parameter["lxr"]=encodeURI(encodeURI(jQuery("#lxr").val()));
	}
	var url = "qgzx_gwglnew_ajax.do?method=bcZjGwsq&type="+type;
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
		 if(data["message"]=="����ɹ���"){
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   		 showAlert(data["message"]);
   		return false;
   		}
	});
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

//��֤
function checkJe(obj){
	for(var i = 0;i<obj.value.length;i++){
		obj.value = obj.value.replace(/^[0]/g,"");
	}
	obj.value = obj.value.replace(/[^\d]/g,"");
	if(obj.value==""){
		obj.value = "0";
	}
}


//��֤�޸ĸ�λ��Ϣ
function bcXgGwsq(type){
	if($("gwmc") && $("gwmc").value==""){
		showAlert("��λ���Ʋ���Ϊ�գ�");
 		return false;
	}
	if($("gwxzdm") && $("gwxzdm").value==""){
		showAlert("��λ���ʲ���Ϊ�գ�");
 		return false;
	}
	
	if($("xqrs") && $("xqrs").value==""){
		showAlert("������������Ϊ�գ�");
 		return false;
	}
	if($("knsrs") && $("knsrs").value==""){
		showAlert("������������Ϊ�գ�");
 		return false;
	}
	if(jQuery("#zxdwlb") && jQuery("#zxdwlb").val() == ""){
		showAlert("���˵�λ��� ����Ϊ�գ�");
 		return false;
	}
	if(jQuery("#zjly") && jQuery("#zjly").val() == ""){
		showAlert("�ʽ���Դ����Ϊ�գ�");
 		return false;
	}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		showAlert("�����������ܴ�������������");
 		return false;
	}
	var yxsszLen = jQuery("[name=yxssz]:checked").length;
	if(yxsszLen==0){
		showAlert("��ѡ����Чʱ���ã�");
		return false;
	}
	var sfsgwsqsxzLen = jQuery("[name=sfsgwsqsxz]:checked").length;
	if(sfsgwsqsxzLen==0){
		showAlert("��ѡ���Ƿ��ܸ�λ���������ƣ�");
		return false;
	}
	if($("gwkssj").value==""){
		showAlert("��λ��ʼʱ�䲻��Ϊ�գ�");
 		return false;
	}
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='xs' && $("gwjssj").value==""){
		showAlert("��λ����ʱ�䲻��Ϊ�գ�");
		return false;
	}
	if($("gwms") && ($("gwms").value==""||demoHtml==jQuery("#gwms").val())){
		showAlert("��λ��������Ϊ�գ�");
 		return false;
	}
	if($("gwryyq") && $("gwryyq").value==""){
		showAlert("��λ��ԱҪ����Ϊ�գ�");
 		return false;
	}
	
	if($("gwxh") && $("gwxh").value==""){
		showAlert("��λ��Ų���Ϊ�գ�");
 		return false;
	}
    if(jQuery("#ssbm") && jQuery("#ssbm").val()==""){
    	showAlert("�������Ų���Ϊ�գ�");
 		return false;
	}
    if(jQuery("#fzls") && jQuery("#fzls").val()==""){
    	showAlert("������ʦ����Ϊ�գ�");
 		return false;
    }
    if(jQuery("#stfzr") && jQuery("#stfzr").val()==""){
    	showAlert("������ʦ����Ϊ�գ�");
 		return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
    	showAlert("��ϵ�绰����Ϊ�գ�");
 		return false;
    }
    if(jQuery("#lsbgs") && jQuery("#lsbgs").val()==""){
    	showAlert("������ʦ�칫�Ҳ���Ϊ�գ�");
    	return false;
    }
    if(jQuery("#ssxq") && jQuery("#ssxq").val()==""){
    	showAlert("����У������Ϊ�գ�");
 		return false;
    }
    if(jQuery("#gzdd") && jQuery("#gzdd").val()==""){
    	showAlert("�����ص㲻��Ϊ�գ�");
    	return false;
    }
    if(jQuery("#gzsj") && jQuery("#gzsj").val()==""){
    	showAlert("����ʱ�䲻��Ϊ�գ�");
    	return false;
    }
    if(jQuery("#gznr") && jQuery("#gznr").val()==""){
    	showAlert("�������ݲ���Ϊ�գ�");
    	return false;
    }
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("��λ����˲���Ϊ�գ�");
    	return false;
    } 
    if(jQuery("#gwcjbz") && jQuery("#gwcjbz").val()==""){
    	showAlert("��λ����׼����Ϊ�գ�");
    	return false;
    }
    if(jQuery("#jfhb") && jQuery("#jfhb").val()==""){
    	showAlert("���ѻ�������Ϊ�գ�");
    	return false;
    }
    
  //����ѧԺ���Ի��ֶ�
    if(jQuery("#gwlx") && jQuery("#gwlx").val()==""){
    	showAlert("��λ���Ͳ���Ϊ�գ�");
    	return false;
    } 
    
    if(jQuery("#zxfdy") && jQuery("#zxfdy").val()==""){
    	showAlert("��ѧ������Ա����Ϊ�գ�");
    	return false;
    } 
    
    if(jQuery("#jfhb").length > 0&&jQuery("#gwcjbz").length > 0&&jQuery("#zc").length > 0){
    	var gwcjbz= parseInt(jQuery("#gwcjbz").val());
    	var jfhb = parseInt(jQuery("#jfhb").val());
    	var zc = parseInt(jQuery("#zc").val());  
    	if((zc+jfhb)!=gwcjbz){
    		showAlert("�����ϡ���λ����׼=���ѻ���+�Գ��");
    		return false;
    	}
    }
    if((parseInt(jQuery("#gwcjbz").val())-parseInt(jQuery("#gwcjsx").val()))>0){
    	showAlert("��λ����׼���ô��ڸ�λ������ޣ�");
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
	
	if(jQuery("#xxdm").val() == '10351'){
    	if(jQuery("input[name='sqxy']:checked").length < 1){
    		showAlert("��ѡ���λ���뿪��ѧԺ��");
    		return false;
    	}
    }
	
	var message = checkBcInfo("update");
	if("true"!=message){
		showAlert(message);
		return false;
	}
	saveXgGwxxInfo(type);	
}


//�����޸ĸ�λ��Ϣ
function saveXgGwxxInfo(type){
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["gwmc"]=encodeURI(encodeURI(jQuery("#gwmc").val()));
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["xqrs"]=escape(jQuery("#xqrs").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["yxssz"]=yxssz;
	parameter["sfsgwsqsxz"]=jQuery("[name=sfsgwsqsxz]:checked").val();
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	parameter["gwms"]=encodeURI(encodeURI(jQuery("#gwms").val()));
	parameter["gwryyq"]=encodeURI(encodeURI(jQuery("#gwryyq").val()));
	parameter["gwyqryxg"]=encodeURI(encodeURI(jQuery("#gwyqryxg").val()));
	parameter["bz"]=encodeURI(encodeURI(jQuery("#bz").val()));
	parameter["gwcjsx"]=escape(jQuery("#gwcjsx").val());
	parameter["gwxh"]=escape(jQuery("#gwxh").val()); 
	parameter["oldGwxh"]=escape(jQuery("#oldGwxh").val());
	parameter["zjly"] = jQuery("#zjly").val();
	parameter["zxdwlb"] = jQuery("#zxdwlb").val();
	parameter["ssbm"]=jQuery("#ssbm").val();
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#fzls").val()));
	parameter["lxdh"]=jQuery("#lxdh").val();
	parameter["lsbgs"]=encodeURI(encodeURI(jQuery("#lsbgs").val()));
	parameter["gzdd"]=encodeURI(encodeURI(jQuery("#gzdd").val()));
	parameter["gzsj"]=encodeURI(encodeURI(jQuery("#gzsj").val()));
	parameter["gznr"]=encodeURI(encodeURI(jQuery("#gznr").val()));
	parameter["xq"]=encodeURI(encodeURI(jQuery("#xq").val()));
	
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#stfzr").val()));
	parameter["ssxq"]=encodeURI(encodeURI(jQuery("#ssxq").val()));
	parameter["type"]=encodeURI(encodeURI(type));
	var url = "qgzx_gwglnew_ajax.do?method=bcXgGwsqInfo&type="+type;
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	
	//���ݴ�ѧ���Ի�
	if(jQuery("#xxdm").val() == '10351'){
		var checkboxs = jQuery("input[name='sqxy']:checked");
		var sqxy = [];
		jQuery.each(checkboxs,function(i,n){
			sqxy.push(jQuery(n).val());
		})
		parameter["sqxy"] = sqxy;
		parameter["doType"] = "xg";
		parameter["gwdm"] = jQuery("#pkValue").val();
	}
	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
		 if(data["message"]=="����ɹ���"){
   		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
   	 }else{
   		 showAlert(data["message"]);
   		return false;
   		}
	});
	jQuery.ajaxSetup({async:true});
}

//��λ�������
function printgwsqb() {
	var ids = jQuery("#dataTable").getSeletIds();
	var len = ids.length;
	if (len == 1) {
		var url = "qgzx_gwglnew.do?method=getGwsqb";
		url += "&pkValue=" + ids;
		window.open(url);
	} else if (len == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	} else {
		var url = "qgzx_gwglnew.do?method=getGwsqbTy";
		url += "&value=" + ids;
		window.open(url);
	}
}

//�ύ
function submitBusi() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
		showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
		return false;
	}
	showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
		"okFun" : function() {
			jQuery.post("qgzx_gwglnew.do?method=submit", {
				values : ids.toString()
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
	});
	
}

//����
function cancel() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length > 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}

		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("qgzx_gwglnew.do?method=cancel", {
					values : ids.toString(),
					splcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}
//���̸���
function lcgz() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (1 != rows.length) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
		return false;
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" == shzt) {
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("�������̸���",530,310, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['gwdm'] + "&splc=" + rows[0]['splcid']);
	}
}


//�����Ƽ���ѧ���Ի� ��λ���ʱ䶯�¼�
function gwxzChange(obj){
	var option = obj.options[obj.selectedIndex];  
	var gwxz = option.innerText;
	var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();//�Ƿ��趨��λ�������	
	var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();//*���˵�λ�����λʱ�ɷ���ĸ�λ�������
	if(gwxz=="�̶���"){
		jQuery("#gdgcjbzTr").show();
		jQuery("#gwcjsxTr").hide();
	}else{
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
		jQuery("#gdgcjbzTr").hide();
		
	}
}