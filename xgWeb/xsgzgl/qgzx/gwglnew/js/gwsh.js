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
jQuery(document).ready(function() {
	var sfsdgwcjsx = jQuery("#sfsdgwcjsx").val();
	// �������� ���õ�н������
		var gwzgcjsx = jQuery("#gwzgcjsx").val();
		var sfkgggwcjsx = jQuery("#sfkgggwcjsx").val();
		// ��λ���ó������
		var gwcjsx = jQuery("#gwcjsx").val();
		// ����˸�λδ����
		
		if (gwcjsx == "") {
			jQuery("#gwcjsx").val(gwzgcjsx);
			jQuery("#gwcjsxh").text(gwzgcjsx);
		}
		if ("no" == sfsdgwcjsx) {
			jQuery("#gwcjsxTr").hide();
		} else {
			jQuery("#gwcjsxTr").show();
			jQuery("#gwcjsx").hide();
			jQuery("#gwcjsxh").show();
			jQuery("#sxcolor").hide();
		}
		if("10704"==jQuery("#xxdm").val()&&jQuery("#gwxzmc").val()=="�̶���"){
			jQuery("#gwcjsxTr").hide();
			jQuery("#gdgcjbzTr").show();
		}
	});
function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}


//��ѯ
function searchRs(){
	var url = "qgzx_gwglnew_ajax.do?method=gwshCx";
	var ie = 'ie';
	var otherValue = [ie];
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","1000")
}


//���
function showModi(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if(ids.length==1){	
		var pkValue=rows[0]["gwdm"];
		var url="qgzx_gwglnew.do?method=gwshDgsh&pkValue="+pkValue+"&&shid=" + rows[0]["shid"]+ "&&gwid=" + rows[0]["gwid"];
		//showTopWin(url,800,600);
		showDialog('��λ���', 760, 470, url);
	}else if(ids.length>1){
		var str = "";
		for (var i=0;i<ids.length;i++) {
			var pkValue = rows[i]["gwdm"];
			str += pkValue+"!!@@!!";
		}
		//var message = checkScInfo(str);
		//if("true"!=message){
		//	alertInfo(message,function(tag){
		//		if(tag=="ok"){
		//			return false;
		//		}
		//	});
		//	return false;
		//}
		//jQuery("#pkValue").val(str);
		//tipsWindown("��λ���","id:tempDiv","580","180","true","","true","id");
		var url="qgzx_gwglnew.do?method=gwshPlsh&pkValue="+str;
		showDialog('��λ�������', 580, 280, url);
	}else{
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼��");
		return false;
	}
}


function gwxxshBc(){
	if("10351"==jQuery("#xxdm").val()){
		if(!checksave()){
			return false;
		}
	}
	if("12867" ==jQuery("#xxdm").val()){
		
		if(jQuery("#xn").length>0 && jQuery("#xn").val() == "" ){
			showAlert("��ѡ��ѧ�꣡");
			return false;
		}
		if(jQuery("#xq").length>0 && jQuery("#xq").val() == "" ){
			showAlert("��ѡ��ѧ�ڣ�");
			return false;
		}
		if(jQuery("#nd").length>0 && jQuery("#nd").val() == "" ){
			showAlert("��ѡ����ȣ�");
			return false;
		}
		if(jQuery("#yxssz").length>0 && jQuery("#yxssz").val() == "" ){
			showAlert("��ѡ����Чʱ���ã�");
			return false;
		}
		if(jQuery("#sfsgwsqsxz").length>0 && jQuery("#sfsgwsqsxz").val() == "" ){
			showAlert("��ѡ���Ƿ��ܸ�λ���������ƣ�");
			return false;
		}
		if(jQuery("#gwkssj").length>0 && jQuery("#gwkssj").val() == "" ){
			showAlert("��ѡ���λ��ʼ���ڣ�");
			return false;
		}
		var yxssz = jQuery("[name=yxssz]:checked").val();
		if(yxssz=='xs' && $("gwjssj").value==""){
			showAlert("��λ����ʱ�䲻��Ϊ�գ�");
			return false;
		}
	}
	if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
		showAlert("����д��������");
		return false;
	}
	if (jQuery("#gwmc").val() == "" || jQuery("#gwmc").val() == ""){
		showAlert("����д��λ���ƣ�");
		return false;
	}
	if (jQuery("#xqrs").val() == "" || jQuery("#xqrs").val() == ""){
		showAlert("����д����������");
		return false;
	}
	if (jQuery("#gwlx").val() == "" || jQuery("#gwlx").val() == ""){
		showAlert("��ѡ���λ���ʣ�");
		return false;
	}
	if (jQuery("#knsrs").val() == "" || jQuery("#knsrs").val() == ""){
		showAlert("����д����������");
		return false;
	}
	if (jQuery("#gwshr").val() == "" || jQuery("#gwshr").val() == ""){
		showAlert("��ѡ���λ����ˣ�");
		return false;
	}
	if (jQuery("#gwryyq").val() == "" || jQuery("#gwryyq").val() == ""){
		showAlert("����д��λҪ��");
		return false;
	}
	if (jQuery("#gwms").val() == "" || jQuery("#gwms").val() == ""){
		showAlert("����д�������ݣ�");
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
	   
	var parameter={}
	var url="qgzx_gwglnew_ajax.do?method=gwxxshBc";	
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["lxr"]=encodeURI(encodeURI(jQuery("#lxr").val()));
	parameter["lxPhone"]=escape(jQuery("#lxPhone").val());
	parameter["sqsj"]=escape(jQuery("#sqsj").val());
	parameter["yrbm"]=escape(jQuery("#yrdwdm").val());
	parameter["xn"]=escape(jQuery("#xn").val());
	parameter["xq"]=escape(jQuery("#xq").val());
	parameter["nd"]=escape(jQuery("#nd").val());
	parameter["yxssz"]=escape(jQuery("#yxssz").val());
	parameter["sfsgwsqsxz"]=escape(jQuery("#sfsgwsqsxz").val());
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	parameter["qgzq"]=escape(jQuery("#qgzq").val());
	parameter["shyj"]=escape(jQuery("#shyj").val());
	parameter["gwcjbz"]=escape(jQuery("#gwcjbz").val());
	parameter["jfhb"]=escape(jQuery("#jfhb").val());
	parameter["zc"]=escape(jQuery("#zc").val());
	parameter["gwshr"]=escape(jQuery("#gwshr").val());
	parameter["gwshrxm"]=encodeURI(encodeURI(jQuery("#gwshrxm").val()));	
	parameter["gwyxs"]=encodeURI(encodeURI(jQuery("#gwyxs").val()));
	jQuery.ajaxSetup({async:false});	
	
	ajaxSubFormWithFun("gwshform",url,function(data){
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
	jQuery.ajaxSetup({async:true});
}

function checksave(){
	if($("gwxzdm") && $("gwxzdm").value==""){
		showAlert("��λ���ʲ���Ϊ�գ�");
 		return false;
	}
	if(parseInt($("xqrs").value)<parseInt($("knsrs").value)){
		showAlert("�����������ܴ�������������");
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
	
	var yxssz = jQuery("[name=yxssz]:checked").val();
	if(yxssz=='cq'){
		jQuery("#gwjssj").val("");
	}
	jQuery.ajaxSetup({async:false});	
	var parameter ={};
	parameter["pkValue"]=escape(jQuery("#pkValue").val());
	parameter["gwxzdm"]=escape(jQuery("#gwxzdm").val());
	parameter["knsrs"]=escape(jQuery("#knsrs").val());
	parameter["yxssz"]=yxssz;
	parameter["sfsgwsqsxz"]=jQuery("[name=sfsgwsqsxz]:checked").val();
	parameter["gwkssj"]=escape(jQuery("#gwkssj").val());
	parameter["gwjssj"]=escape(jQuery("#gwjssj").val());
	var url = "qgzx_gwglnew_ajax.do?method=bcXgGwshInfo";
	jQuery.post(url,parameter,
			function(result){
				if("����ɹ�"==result){
					/*showAlert(result+"��",{},{"clkFun":function(){
		 				if (parent.window){
		 					refershParent();
		 				}
		 			}});*/
				}else{
					showAlert(result+"��");
		     		return false;
				}
			}
		);
	return true;
}

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

function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function selectTab(obj, shzt) {
	jQuery("#shzt").val(shzt);
	if (shzt == "dsh") {
		jQuery("#li_sh").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

function splcInfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (1!=ids.length){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['gwdm']+"&splc="+rows[0]['splcid']);
	}
}

//��˳���
function cancelSh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		var splc = rows[0]["splcid"];
		var shid = rows[0]["shid"];
		var sqid = rows[0]["gwdm"];
		var shzt = rows[0]["shzt"];
		showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
			jQuery.post("qgzx_gwglnew.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
				// �ж��Ƿ����һ������(1:���һ�������ɹ���
				if("1" == data["cancelFlg"]){
					jQuery.post("qgzx_gwglnew.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
						showAlertDivLayer(result["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					},'json');
				}else{
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}
			
		},'json');
		}});
	}
}

//�������
function savePlsh(shzt, shyj) {
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var splcids =  new Array();
	jQuery.each(rows, function(i, row) {
		guid.push(row["gwdm"]);
		gwid.push(row["gwid"]);
		xhs.push(row["sqrzgh"]);
		splcids.push(row["splcid"]);
	});
	jQuery.post("qgzx_gwglnew_ajax.do?method=savePlsh", {
		shzt : shzt,
		splcids : splcids,
		id : guid,
		gwids : gwid,
		sqrs : xhs,
		shyj : shyj,
	}, function(data) {
		
		showAlertDivLayer(data["message"], {}, {
			"clkFun" : function() {
				jQuery("#dataTable").reloadGrid();
			}
		});
	}, 'json');
}