function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
	}
}

function showModi(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	

	if(len==1){	
		var sjly=jQuery("[name=div_pkValue]:checked").parent().parent().find("input[name=sjly]").val();
		
		if(sjly == "1"){
			alertInfo("�������ݲ����޸� ��");
			return false;
		}
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
		var url="wjcfsjXg.do";
		
		url+="?cfid="+pkValue;
		
		jQuery.ajaxSetup({async:false});
		var count = 0;	
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							
							if (json[0].sswh != "" && json[0].sswh != null) {
								count = 1;
								alertInfo("�ô��������߲����ٽ����޸ģ�");
								return false;
							}
							else if (json[0].zzwh != "" && json[0].zzwh != null) {
								count = 1;
								alertInfo("�ô�������ֹ�����ٽ����޸ģ�");
								return false;
							}
							else if (json[0].jcwh != "" && json[0].jcwh != null) {
								count = 1;
								alertInfo("�ô�����"+jQuery("#text").val()+"�����ٽ����޸ģ�");
								return false;
							}
							else if (json[0].jclc != "0" && json[0].jclc != null) {
								count = 1;
								alertInfo("�ô���"+jQuery("#text").val()+"����У������ٽ����޸ģ�");
								return false;
							}
							else if (json[0].sslc != "0" && json[0].sslc != null) {
								count = 1;
								alertInfo("�ô�����������У������ٽ����޸ģ�");
								return false;
							}
						}
					);
		jQuery.ajaxSetup({async:true});	
		if (count == 0) { 
		//showTopWin(url,780,660);}
		showDialog("", 800, 500, url);}
	}else{
		
		alertInfo("�빴ѡһ����Ҫ�޸ĵļ�¼��");
		
		return false;
	}
}

function showView(){

	var len=jQuery("[name=div_pkValue]:checked").length;
	
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var url="wjcfsjCk.do?cfid=";
		
		url+=pkValue;
		
		//showTopWin(url,780,660);
		showDialog("", 800, 500, url);
	}else{
		
		alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
		
		return false;
	}
}

function deleteJcrcgl(){
	
	var n=jQuery("[name=div_pkValue]:checked").length;
	
	var blog=true;
	if(n>0){
		var count = 0;
		jQuery("[name=div_pkValue]:checked").each(function(i){
			var sjly=jQuery(this).parent().parent().find("input[name=sjly]").val();
			
			if(sjly == "1"){
				count = count + 1;
			}
		
		});
		
		if(count >0){
			alertInfo("ѡ������ݼ�¼����������������ģ�����ɾ����");
			return false;
		}
	
		if(blog){
			confirmInfo("�ò�������ɾ��������Ϣ���Ƿ�ȷ������������",function(tag){
				
				if(tag=="ok"){
					
					var pkValue=new Array();
					
					var xh=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						
						pkValue[i]=jQuery(this).val();
					
					});
					
					var parameter={};

					parameter["pkValue"]=escape(pkValue.join("!!array!!"));
					
					var url= "wjcfsjSc.do";
					
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result,function(tag){
								
								if(tag=="ok"){
									searchRs();
								}
							
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
		}
	}else{
		
		alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
			
			if(tag=="ok"){
				return false;
			}
		
		});
	}
}

function cfsscl() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		jQuery.ajaxSetup({async:false});	
		var count = 0;
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							if (json[0].jcwh != "" && json[0].jcwh != null) {
								alertInfo("�ô�����"+jQuery("#text").val()+"�����ٽ������ߣ�");
								count = 1;
								return false;
							}
							if (json[0].zzwh != "" && json[0].zzwh != null) {
								alertInfo("�ô�������ֹ�����ٽ������ߣ�");
								count = 1;
								return false;
							}
							if (json[0].ssjg == '���Ĵ���') {
								document.getElementById('cfggw').style.display = "block";
							}
						}
					);
		
		jQuery.ajaxSetup({async:true});	
		if (count == 0) {
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfCfss&&cfid="+pkValue;
			showDialog("", 450, 280, url);
		}
	}else{
		alertInfo("�빴ѡһ����Ҫ�����ļ�¼��");
		return false;
	}
}
function ssjgsave() {
	var sswh = $("sswh").value;//�����ʺ�
	var sssj = $("sssj").value;//����ʱ��
	var ssjg = $("ssjg").value;//���߽��
	var cflbdm = $("cflbdm").value;//����������
	if("block"==document.getElementById('cfggw').style.display){
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg||null==cflbdm||""==cflbdm){
			alertError("�뽫��*����Ŀ��д������");
			return false;
		}
	}else{
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg){
			alertError("�뽫��*����Ŀ��д������");
			return false;
		}
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	refreshForm('saveWjcfssjg.do?cfid='+pkValue);
}
function cfjccl() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var url="wjcfsjCk.do?cfid=";
		
		url+=pkValue;
		
		jQuery.ajaxSetup({async:false});	
		var count=0;
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							if (json[0].zzwh != "" && json[0].zzwh != null) {
								alertInfo("�ô�������ֹ�����ٽ���"+jQuery("#text").val()+"��");
								count = 1;
								return false;
							}
						}
					);
		
		jQuery.ajaxSetup({async:true});	
		if(count == 0){
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfCfjc&cfid="+pkValue;
			showDialog("", 400, 240, url);
		}
	}else{
		
		alertInfo("�빴ѡһ����Ҫ�����ļ�¼��");
		
		return false;
	}
}

function cfzzcl() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if(len==1){	
		
		var pkValue=jQuery("[name=div_pkValue]:checked").val();
		
		var url="wjcfsjCk.do?cfid=";
		
		url+=pkValue;
		
		jQuery.ajaxSetup({async:false});	
		var count = 0;
		jQuery.post("cfsssjCx.do",
						{pkValue:pkValue},
						function(result){
							var json=eval(result);
							if (json[0].jcwh != "" && json[0].jcwh != null) {
								alertInfo("�ô�����"+jQuery("#text").val()+"�����ٽ�����ֹ��");
								count = 1;
								return false;
							}
							if(!sfkzzFlag(json[0])){
								count = 1;
								return false;
							}
						},'json'
					);
		
		jQuery.ajaxSetup({async:true});	
		if (count == 0) {
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfCfzz&cfid="+pkValue;
			showDialog("", 400, 240, url);
		}
	}else{
		
		alertInfo("�빴ѡһ����Ҫ�����ļ�¼��");
		
		return false;
	}
}


function sfkzzFlag(cfInfo){
	var flag = true;
	jQuery.ajaxSetup({async:false});
	jQuery.post("wjcfCfshwh_cfsjwh.do?method=getKzzFlag",{cfsj:cfInfo.cfsj,zzsj:cfInfo.zzsj,cflbmc:cfInfo.cflbmc},function(data){ 
		if(data["message"]!=null && data["message"]!=""){
			alertInfo(data["message"]);
			flag = false;
		}
	},'json');
	jQuery.ajaxSetup({async:true});
	return flag;
}


function jcjgsave() {
	var jcwh = $("jcwh").value;//����ʺ�
	var jcsj = $("jcsj").value;//���ʱ��
	if(null==jcsj||""==jcsj||null==jcwh||""==jcwh){
		alertError("�뽫��*����Ŀ��д������");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	refreshForm('saveWjcfjcjg.do?cfid='+pkValue);
}
function discfgg() {
	var ssjg = jQuery("#ssjg").val();
	if (ssjg =='���Ĵ���') {
		document.getElementById('cfggw').style.display = "block";
	} else {
		document.getElementById('cfggw').style.display = "none";
		document.getElementById('cflbdm').value = "";
	}
}
/**
 * ����������
 * @return
 */
function getWord() {
	var len=jQuery("[name=div_pkValue]:checked").length;
	if (len != 1) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	
	var jcwh=jQuery("[name=div_pkValue]:checked").parent().parent().find("input[name=jcwh]").val();
	if(jcwh == null || jQuery.trim(jcwh) == ''){
		showAlertDivLayer("��ѡ��һ��"+jQuery("#text").val()+"���ּ�¼��");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	var url = "wjcfCfshwh_cfsjwh.do?method=doPrint&&cfid="+pkValue;
	window.open(url);
}
/**
 * Υ�ʹ���֪ͨ���ӡ
 * @return
 */
function getWjCfWord(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if (len != 1) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	var url ="wjcfCfshwh_cfsjwh.do?method=doPrintWjcfWord&&cfid="+pkValue;
	window.open(url);
	
}
/**
 * �㽭��ҵְҵ����ѧԺ���Ի���ѧ�����־���������
 * @return
 */
function getCfjdWord(){
	var len=jQuery("[name=div_pkValue]:checked").length;
	if (len != 1) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	var pkValue=jQuery("[name=div_pkValue]:checked").val();
	var url ="wjcfCfshwh_cfsjwh.do?method=doPrintCfjdWord&&cfid="+pkValue;
	window.open(url);
}

	
		
		
	