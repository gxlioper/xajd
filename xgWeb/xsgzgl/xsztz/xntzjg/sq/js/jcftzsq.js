var DCCLBH = "pjpy_cpfwh.do";//dcclbh,�������ܱ��

function xmmcLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='XmsbjgView(\""
			+ rowObject["jgid"] + "\");'>" + cellValue + "</a>";
}

function XmsbjgView(jgid) {
	showDialog("��Ŀ�걨�鿴", 800, 500, "xmsbXmsbjg.do?method=viewXmsbjg&jgid="
			+ jgid);
}

function rsLink(cellValue, rowObject){
	if(rowObject["csms"] == "1" ){
		return "<a href='javascript:void(0);' class='name' onclick='rsView(\""
		+ rowObject["xmdm"] + "\");'>" + cellValue + "</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='TtsView(\""
		+ rowObject["xmdm"] + "\");'>" + cellValue + "</a>";
	}
	
}

function rsView(xmdm) {
	showDialog("ѧ���϶��鿴", 900, 500, "jcftz_sq.do?method=viewRs&xmdm="
			+ xmdm);
}

function TtsView(xmdm){
	showDialog("ѧ���϶��鿴", 900, 500,"jcftz_sq.do?method=TtrenDing&flag=view&xmdm="
			+ xmdm);
}

function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

function add(){
	var rows = jQuery("#dataTable").getSeletRow();	
	//��ȡ��Ŀ����
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�϶��ļ�¼��");
		return false;
	}
	var csms = rows[0]["csmsmc"];
	var sqkg = jQuery("#sqkg").val();
	if ("0" == sqkg && rows[0]["xfrdsqzt"] != "3") {
		showAlertDivLayer("��ǰ�ѹرգ��������Ա��ϵ��");
		return false;
	}
	if (rows[0]["xfrdsqzt"] != "0" && rows[0]["xfrdsqzt"] != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
	//���˺�������Ŀ������ж�
	if(csms == "����"){
		var url = "jcftz_sq.do?method=renDing&xmdm=" + rows[0]["xmdm"];
	}else if(csms == "����"){
		var url = "jcftz_sq.do?method=TtrenDing&xmdm=" + rows[0]["xmdm"];
	}else{
		return false;
	}
	
	document.forms[0].action=url;
	document.forms[0].submit();
}

function toNext(obj,event){
	var event = event || window.event;
	//��   
	if (event.keyCode==37){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index-1).select();
	}
	
	//��      
	if (event.keyCode==38){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
	}
	
	//��   ���� �س�
	if (event.keyCode==39 || event.keyCode==13){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index+1).select();
	}
	
	//�� 
	if (event.keyCode==40){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
	}
	
}

function savefs(obj,jgid,jcf){
	var str = jQuery(obj).val();
	//��ȡ����ģʽ
	var csms = jQuery("#csms").val();
	if(str != '' && str != null){
		if(parseFloat(jQuery(obj).val())<0){
			showAlertDivLayer("���������븺����",{},{"clkFun":function(){
				obj.focus();
			}});
			return false;
		}				
		if(parseFloat(jQuery(obj).val())>1){
			if (!/^[1-9]\d*([.][0-9])?$/.test(str)) {
				showAlertDivLayer("����������λΪ0�����֣�",{},{"clkFun":function(){
					obj.focus();
				}});
				return false;
			 }
		}
		if(parseFloat(jQuery(obj).val())<1){
			if (!/^[0]\d*([.][1-9])?$/.test(str)) {
				showAlertDivLayer("��������Ϊ0�����֣�",{},{"clkFun":function(){
					obj.focus();
				}});
				return false;
			 }
		}							
		var reg = /^[0-9]+(.[0-9]{1})?$/;
		var result = reg.test(str);
		if(!result){
			showAlertDivLayer("��ֵֻ��������һλС����",{},{"clkFun":function(){
				obj.focus();
			}});
			return false;
		}
		if(parseFloat(jQuery(obj).val()) > parseFloat(jcf)){
			showAlertDivLayer("����������ֲ��ܴ���ԭ�Ȼ����֣���ȷ�ϣ�",{},{"clkFun":function(){
				obj.focus();
			}});		
			return false;
		}
	}else{
		showAlertDivLayer("����������ֲ���Ϊ�գ������룡",{},{"clkFun":function(){
			obj.focus();
		}});
		    return false;
	}		
	var fz = obj.value;	
	jQuery.post("jcftz_sq.do?method=saveJcftzSq",{jgid:jgid,tzhjcf:fz,csms:csms},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
	
	jQuery.ajaxSetup({async:false});
	var jxdm = "";
	if(csms == "1"){
		jxdm = jQuery(obj).parent().parent().find("td").eq(10).find("select").eq(0).val();
	}else{
		jxdm = jQuery(obj).parent().parent().find("td").eq(12).find("select").eq(0).val();
	}
	
	if(jxdm== ''|| jxdm == null){
		if(csms == '1'){
		   jQuery(obj).parent().parent().find("td").eq(12).html(fz);
		}else{
		   jQuery(obj).parent().parent().find("td").eq(14).html(fz);
		}
	}else{
		jQuery.post("jcftz_sq.do?method=getFs",{jxdm:jxdm},function(data){
			if(csms == '1'){
				jQuery(obj).parent().parent().find("td").eq(12).html(Number(fz)+Number(data));	
			}else{
				jQuery(obj).parent().parent().find("td").eq(14).html(Number(fz)+Number(data));
			}
			
		},'json');
	}
	jQuery.ajaxSetup({async:true});
	
}

function saveJx(obj,jgid){
	var jxdm = obj.value;
	//��ȡ����ģʽ
	var csms = jQuery("#csms").val();
	if(jxdm == "" || jxdm == null){
		jQuery.post("jcftz_sq.do?method=saveJcftzSq&type=saveJxdm",{jgid:jgid,jxdm:jxdm,csms:csms},function(data){
			if (data){
				showAlert(data["message"]);
			}
		},"json");
	}else{
		jQuery.post("jcftz_sq.do?method=saveJcftzSq",{jgid:jgid,jxdm:jxdm,csms:csms},function(data){
			if (data){
				showAlert(data["message"]);
			}
		},"json");
	}
	jQuery.ajaxSetup({async:false});
	var fz = "";
	if(csms == "1"){
		 fz = jQuery(obj).parent().parent().find("td").eq(9).find("input").eq(0).val();
	}else{
		 fz = jQuery(obj).parent().parent().find("td").eq(11).find("input").eq(0).val();
	}
	if(jxdm== ''|| jxdm == null){
		if(csms == "1"){
			jQuery(obj).parent().parent().find("td").eq(12).html(fz);
		}else{
			jQuery(obj).parent().parent().find("td").eq(14).html(fz);
		}
	}else{
		jQuery.post("jcftz_sq.do?method=getFs",{jxdm:jxdm},function(data){
			if(csms == "1"){
				jQuery(obj).parent().parent().find("td").eq(12).html(Number(fz)+Number(data));	
			}else{
				jQuery(obj).parent().parent().find("td").eq(14).html(Number(fz)+Number(data));	
			}
		},'json');
	}	 
	jQuery.ajaxSetup({async:true});
	
}

function saveQq(obj,jgid){
	var sfqq = obj.value;
	var csms = jQuery("#csms").val();
	jQuery.post("jcftz_sq.do?method=saveJcftzSq",{jgid:jgid,sfqq:sfqq,csms:csms},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}

function tijiao(){
    var rows = jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	var csms = new Array();
	var xmdms = [];
	var sqkg = jQuery("#sqkg").val();
	var flag = false;
	if (rows.length < 1){
		showAlertDivLayer("������ѡ��һ����Ҫ�϶��ļ�¼��");
		return false;
	}
	jQuery.each(rows,function(i,n){
		if(n["xfrdsqzt"] != "0" && n["xfrdsqzt"] != "3"){
			showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
			flag = true;
			return false;
		}
		if('3' != n['xfrdsqzt'] && "0" == sqkg){
			showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
			flag = true;
			return false;
		}
		if(flag){
			showAlertDivLayer("���ύ�ļ�¼�����ٴ��ύ");
			flag = true;
			return false;
		}
		xmdms.push(n["xmdm"]);
		csms.push(n["csms"]);
	})
	if(flag){
		return false;
	}
	jQuery.ajaxSetup({async:false});
			showConfirmDivLayer("��ȷ���ύ��",{"okFun":function(){
				jQuery.post("jcftz_sq.do?method=submit",{xmdms:xmdms,ids:ids,csms:csms},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json'); 
			}});
	jQuery.ajaxSetup({async:true});
}

function Tj(){
	jQuery.ajaxSetup({async:false});
	var csms = new Array(); 
	csms.push(jQuery("#csms").val());
	showConfirmDivLayer("��ȷ���ύ��",{"okFun":function(){
			jQuery.post("jcftz_sq.do?method=submit",{ids:jQuery("#jgid").val(),xmdms:jQuery("#xmdm").val(),csms:csms},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
				fh();
			},'json');
		}});
	jQuery.ajaxSetup({async:true});
	
}

function fh(){
	refreshForm("/xgxt/sztz_jcftz_sq.do");
}

function yzfz(obj){
	var reg = /^[0-9]+(.[0-9]{1})?$/;
	var str = jQuery(obj).val();
	var result = reg.test(str);
	if(!result){
		showAlert("ֻ��������һλС��");
		return false;
	}	
}


function checkNumberinput(obj,num){
	if(parseInt(jQuery(obj).val()) > parseInt(num)){
		showAlert("��ֵ���Ϊ100����ȷ�ϣ�");
		return false;
	}
}

function selectAll(obj) {
	jQuery('input[type=checkbox]').attr('checked', jQuery(obj).attr('checked'));
}

//���汸ע
function savebz(obj,jgid,xm){
	var csms = jQuery("#csms").val();
	var str = jQuery(obj).val();
	//��ʱ��xm�趨ΪҪ�޸ĵ�������bz1Ϊ�޸ĵ�ֵ
	jQuery.post("jcftz_sq.do?method=saveJcftzSq",{jgid:jgid,csms:csms,xm:xm,bz1:str},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
}

