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
	demoHtml += "����ʱ�䣺����һ����������8:00~12:00��";
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
	
	if("10704"!=jQuery("#xxdm").val()){
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
	}else{
		var select = document.getElementsByName("gwxzdm");
		var gwxz = jQuery(select).find("option:selected").text();
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
		}
	}
	var doType=jQuery("#doType").val();
	//��Ա���� ����ֻ�ܲ鿴
	if(doType=="ryxxZj"||doType=="ryxxTg"){
		if("10704"==jQuery("#xxdm").val()&&jQuery("#gwxzmc").val()=="�̶���"){
			jQuery("#gwcjsxTr").hide();
			jQuery("#gdgcjbzTr").show();
		}
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
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


// �޸�gwxxXg �鿴 gwxxCk��Ա����ryxxZj��Ա�˸�ryxxTg
function showModi(type,title){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length==1){	
		var sfyxgw=rows[0]["sfyxgw"];
		if((type=='ryxxZj' || type=='ryxxTg') && sfyxgw=='��'){
			showAlertDivLayer("��ѡ����Ч��λ��");
			return false;
		}
		var pkValue=rows[0]["gwdm"];
		var url="qgzx_gwglnew.do?method=gwxxCz&pkValue="+pkValue;
		url+="&doType="+type;
		// showTopWin(url,800,600);
		showDialog(title, 760, 520, url);
	}else{
		if(type=='ryxxTg'){
			if(rows.length == 0){
				// ����
				jQuery("#gwdmPlHidden").val("");
				// ���ݲ�ѯ������������˸�
				var a_name_sf = jQuery("a[name=a_name_sf]");
				var sf_num = a_name_sf.length;
				if(sf_num == 1 && a_name_sf.eq(0).attr("id").replace("a_id_","") == '��'){
					var rowConut = jQuery("#rowConut").html();
					var url = "qgzx_gwglnew.do?method=gwxxTgWin&len="+rowConut;
					var title = "�����˸�";
					showDialog(title,380,200,url);
				}else{
					showAlertDivLayer("�������Ƿ���Ч��λ������ѡ���ǡ����������˸ڣ�");
					return false;
				}
			}else{
				// ���ݹ�ѡ��¼���������˸�
				var gwdms = new Array();
				for(var i=0;i<rows.length;i++){
					if(rows[i]['sfyxgw']=='��'){
						showAlertDivLayer("��ѡ����Ч��λ��");
						return false;
					}
					gwdms.push(rows[i]['gwdm']);
				}
				jQuery("#gwdmPlHidden").val(gwdms.join(','));
				var url = "qgzx_gwglnew.do?method=gwxxTgWin&len="+rows.length;
				var title = "�����˸�";
				showDialog(title,380,200,url);
			}
		}else{
			showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
		}
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

// ɾ��
function deleteGwxx(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length>=1){
		var str = "";
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly'] == "1"){
				showAlertDivLayer("������̹��������ݲ���ɾ����");
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
		showConfirmDivLayer(jQuery("#lable_confirm_sc").val(),{
			"okFun" : function() {
				var parameter={}
				var url="qgzx_gwglnew_ajax.do?method=gwxxSc";	
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

// ��֤ɾ����Ϣ
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

// ���ӱ����λ��Ϣ
function zjBcGwxx(){
	if($("xn") && $("xn").value==""){
		showAlert("ѧ�겻��Ϊ�գ�");
 		return false;
	}
	if($("yrbm") && $("yrbm").value==""){
		showAlert("���˲��Ų���Ϊ�գ�");
 		return false;
	}
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
    if(jQuery("#gwcjbz") && jQuery("#gwcjbz").val()==""){
    	showAlert("��λ����׼����Ϊ�գ�");
    	return false;
    }
    if(jQuery("#jfhb") && jQuery("#jfhb").val()==""){
    	showAlert("���ѻ�������Ϊ�գ�");
    	return false;
    } 
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("��λ����˲���Ϊ�գ�");
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
	if(!checkSxje()){
		return false;
	}
	if(jQuery("#xxdm").val() == '10351'){
    	if(jQuery("input[name='sqxy']:checked").length < 1){
    		showAlert("��ѡ���λ���뿪��ѧԺ��");
    		return false;
    	}
    }
	var message = checkBcInfo("add");
	if("true"!=message){
		showAlert(message);
		return false;
	}
    if(jQuery("#lxr") && jQuery("#lxr").val()==""){
        showAlert("��ϵ�˲���Ϊ�գ�");
        return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
        showAlert("��ϵ�绰����Ϊ�գ�");
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
    parameter["xq"]=escape(jQuery("#xq").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["gwmc"]=escape(jQuery("#gwmc").val());
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		parameter["gwjssj"]="";
	}else{
		parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	}
	parameter["yxssz"]=yxssz;
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	var url = "qgzx_gwglnew_ajax.do?method=checkBcInfo";
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
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	// �õ�JSON����
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
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
	parameter["gznr"]=encodeURI(encodeURI(jQuery("#gznr").val()));
	parameter["xq"]=encodeURI(encodeURI(jQuery("#xq").val()));
	
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#stfzr").val()));
	parameter["ssxq"]=encodeURI(encodeURI(jQuery("#ssxq").val()));
	var url = "qgzx_gwglnew_ajax.do?method=save";
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
		parameter["doType"] = "zj";
	}
	//disabled�ֶα��ύ����
	var yrbddis;
	var ssbmdis;
	if(jQuery("#yrbm")){
		yrbddis = jQuery("#yrbm").attr("disabled");
		jQuery("#yrbm").attr("disabled", false);
	}
	if(jQuery("#ssbm")){
		ssbmdis = jQuery("#ssbm").attr("disabled");
		jQuery("#ssbm").attr("disabled", false);
	}

	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
		//disabled�ֶα��ύ����
		if(jQuery("#yrbm")){
			jQuery("#yrbm").attr("disabled", yrbddis);
		}
		if(jQuery("#ssbm")){
			jQuery("#ssbm").attr("disabled", ssbmdis);
		}
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

function checkJe(obj){
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
	if(parseFloat($("xqrs").value)<parseFloat($("zgrs").value)){
		showAlert("������������С���ڸ�������");
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
    	showAlert("������ʦ����Ϊ�գ�");
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
    if(jQuery("#gwcjbz") && jQuery("#gwcjbz").val()==""){
    	showAlert("��λ����׼����Ϊ�գ�");
    	return false;
    }
    if(jQuery("#jfhb") && jQuery("#jfhb").val()==""){
    	showAlert("���ѻ�������Ϊ�գ�");
    	return false;
    } 
    if(jQuery("#gwshr") && jQuery("#gwshr").val()==""){
    	showAlert("��λ����˲���Ϊ�գ�");
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
	if(!checkSxje()){
		return false;
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
    if(jQuery("#lxr") && jQuery("#lxr").val()==""){
        showAlert("��ϵ�˲���Ϊ�գ�");
        return false;
    }
    if(jQuery("#lxdh") && jQuery("#lxdh").val()==""){
        showAlert("��ϵ�绰����Ϊ�գ�");
        return false;
    }
	saveXgGwxxInfo();	
}


// �����޸ĸ�λ��Ϣ
function saveXgGwxxInfo(){
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
	parameter["zjly"] = jQuery("#zjly").val();
	parameter["zxdwlb"] = jQuery("#zxdwlb").val();
	parameter["ssbm"]=jQuery("#ssbm").val();
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#fzls").val()));
	parameter["lxdh"]=jQuery("#lxdh").val();
	parameter["lsbgs"]=encodeURI(encodeURI(jQuery("#lsbgs").val()));
	parameter["gzdd"]=encodeURI(encodeURI(jQuery("#gzdd").val()));
	parameter["gzsj"]=encodeURI(encodeURI(jQuery("#gzsj").val()));
	parameter["gznr"]=encodeURI(encodeURI(jQuery("#gznr").val()));
	parameter["xq"]=encodeURI(encodeURI(jQuery("#xq").val()))
	
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));
	parameter["fzls"]=encodeURI(encodeURI(jQuery("#stfzr").val()));
	parameter["ssxq"]=encodeURI(encodeURI(jQuery("#ssxq").val()));
	var url = "qgzx_gwglnew_ajax.do?method=update";
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
	//disabled�ֶα��ύ����
	var yrbddis;
	var ssbmdis;
	if(jQuery("#yrbm")){
		yrbddis = jQuery("#yrbm").attr("disabled");
		jQuery("#yrbm").attr("disabled", false);
	}
	if(jQuery("#ssbm")){
		ssbmdis = jQuery("#ssbm").attr("disabled");
		jQuery("#ssbm").attr("disabled", false);
	}
	ajaxSubFormWithFun("form", url, function(data) {
		$("divWaiting").style.display="none";
		$("divDisable").style.display="none";
		//disabled�ֶα��ύ����
		if(jQuery("#yrbm")){
			jQuery("#yrbm").attr("disabled", yrbddis);
		}
		if(jQuery("#ssbm")){
			jQuery("#ssbm").attr("disabled", ssbmdis);
		}
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


// ȫѡ
function selectAll(obj){ 
	jQuery('input[type=checkbox]').attr('checked',jQuery(obj).attr('checked'));
}


// ������
function addTr(){
	// showTopWin('qgzx_gwglnew.do?method=getStu&pkValue='+jQuery("#pkValue").val(),800,700);
	var xn=jQuery("#xn").val();
	var url='qgzx_gwglnew.do?method=getStu&pkValue='+jQuery("#pkValue").val()+'&xn='+xn;
	showDialog('ѧ���б�', 800, 520, url);
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
			showAlert(show);
			return false;
		}
		var message = checkScRyxx(xhs);
		if("true"!=message){
			showAlert(message);
			return false;
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ���ѧ����", {
			"okFun" : function() {
				jQuery.ajaxSetup({async:false});
				// �õ�JSON����
			    var parameter ={};	
			    parameter["gwdm"]=escape(jQuery("#pkValue").val());
			    parameter["xh"]=xhs;
				var url = "qgzx_gwglnew_ajax.do?method=bcScryxx";
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
		showAlert("��ѡ����Ҫɾ����ѧ����");
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
	var url = "qgzx_gwglnew_ajax.do?method=checkScRyxx";
	jQuery.post(url,parameter,
		function(result){
			message = result;
		}
	);
	jQuery.ajaxSetup({async:true});
	return message;
}



// ��֤������Ա��Ϣ
function bcZjRyxx(){
	var len = jQuery("#xhs").val().split("!!@@!!").length-1;
	if(len>parseInt(jQuery("#xqrs").val())){
		showAlert('���ӵ�ѧ�����Ѵ��ڸ�λ������������');
 		return false;
	}
	saveRyxxInfo();
}


// ������Ա��Ϣ
function saveRyxxInfo(){
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    var date=new Date;
    var year=date.getFullYear(); 
    var month=date.getMonth()+1;
    month =(month<10 ? "0"+month:month); 
    var mydate = (year.toString()+month.toString());
    parameter["gwdm"]=escape(jQuery("#pkValue").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["fknsrs"]=escape(jQuery("#fknsrs").val());
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["sgsj"]=escape(mydate);
    parameter["sfsgwsqsxz"]=escape(jQuery("#sfsgwsqsxz").val());
	var url = "qgzx_gwglnew_ajax.do?method=bcZjryxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			if(result!="����ɹ�"){
				showAlert(result);
		 		return false;
			}else{
				document.forms[0].action = window.location.href;
				document.forms[0].submit();
			}
		}
	);
	jQuery.ajaxSetup({async:true});
}

function  saveRyzj(){
	var flag=true;
	var xsGwxx= [];
	jQuery.each(jQuery("#tbody_zgryxx tr"),function(i,n){
		var obj = {};
			var gwdm= jQuery("#pkValue").val();
			var sgsj = jQuery(n).find("td").find("input[name=sgsj]").val();
			if(null==sgsj||""==sgsj){
				flag=false;
				return false;
			}
			obj.gwdm=gwdm;
			obj.sgsj=sgsj;
			xsGwxx.push(obj);
	});
	if(!flag){
		showAlert("����д�ϸ��·ݣ�");
		return false;
	}
	var xsGwxxStr = JSON.stringify(xsGwxx);
	jQuery("#xsGwxxStr").val(xsGwxxStr);
	var url = "qgzx_gwglnew_ajax.do?method=saveRyzj";
	ajaxSubFormWithFun("qgzxGwglNewForm", url, function(data) {
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
		//tipsWindown("��Ա�˸�","id:tempDiv","380","250","true","","true","id");
		var url='qgzx_gwglnew.do?method=ryxxTgWin&len='+len;
		showDialog('��Ա�˸�', 380, 200, url);
		jQuery('#xhs').val(xhs);
		jQuery('#sgsjs').val(sgsjs);
		jQuery('#sqbhs').val(sqbhs);
	}else{
		showAlert("��ѡ����Ҫ�˸ڵ�ѧ����");
	}
}

// �����˸���Ϣ��֤
function bcTgryxx(){
	if($("tgyy") && $("tgyy").value==""){
		showAlert("�˸�ԭ����Ϊ�գ�");
 		return false;
	}
	saveTgryxx();
}


function saveTgryxx(){
	var api = frameElement.api,W = api.get('parentDialog');
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#pkValue",W.document).val());
    parameter["xh"]=escape(jQuery('#xhs',W.document).val());
    parameter["sgsj"]=escape(jQuery('#sgsjs',W.document).val());
    parameter["tgyy"]=escape(jQuery("#tgyy").val());
    parameter["sqbhs"]=escape(jQuery("#sqbhs",W.document).val());
	var url = "qgzx_gwglnew_ajax.do?method=bcTgryxx";
	jQuery.post(url,parameter,
		function(result){
			showAlert(result,{},{"clkFun":function(){
				api.close();
				W.document.forms[0].action = W.window.location.href;
				W.document.forms[0].submit();
				return false;
			}});
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
	var url="qgzx_gwglnew_ajax.do?method=ryxxCk";
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
						jQuery('#tbody_zggwxx').append("<tr><td>��ѧ�����ڸڼ�¼��</td></tr>");
						
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
	tipsWindownNew("ѧ���ڸ���Ϣ","id:xszgxxDiv",560,380);
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
function queryParentDocumentJgcxTj(){
	if(frameElement.api){
		var api = frameElement.api,W = api.opener;
		return W.document;
	}
	return parent.window.document;
}
//�°�鿴������
function zxsxxView(xh) {
	showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
}


//���˵�λ�ı������λ���뿪��ѧԺ
function changeYrdw(obj) {
	if(jQuery("#xxdm").val() == '10351'){//���ݴ�ѧ���Ի�
		var xydm = jQuery(obj).val();
		var checkboxs = jQuery("input[name='sqxy']");
		var num = 0;
		jQuery.each(checkboxs,function(i,n){
			if(jQuery(n).val() == xydm){
				jQuery(n).attr("checked",true);
				num++;
			}else{
				jQuery(n).attr("checked",false);
			}
		});
		if(num == 0){
			jQuery.each(checkboxs,function(i,n){
				jQuery(n).attr("checked",true);
			})
		}
	}else{
		return;
	}
}

//�����Ƽ���ѧ���Ի� ��λ���ʱ䶯�¼�
function gwxzChange111(obj){
	var option = obj.options[obj.selectedIndex];  
	var gwxz = option.innerText;
	if(gwxz=="�̶���"){
		jQuery("#gdgcjbzTr").show();
		jQuery("#gwcjsxTr").hide();
	}else{
		jQuery("#gwcjsx").hide();
		jQuery("#gdgcjbzTr").hide();
		jQuery("#gwcjsxTr").show();
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