var isqy=false;
//��ʼ��
jQuery(document).ready(function(){ 
	if($("pkValue") && $("pkValue").value==""){
		getFFny();
	}
	jQuery("#ffny").bind("change",function(){
//		jQuery("#tsny").text(jQuery(this).val()+"��");
	});
	jQuery("#ffny").change();
	searchRs();

});


//��λ��������
function getGwdm(){
	jQuery.ajaxSetup({async:false});
	var parameter ={};
	parameter["xn"]=escape(jQuery("#xn").val());
	parameter["xq"]=escape(jQuery("#xq").val());
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
	jQuery.getJSON('qgzx_cjgl_ajax.do?method=getGwdm',parameter,function(data){
		jQuery('#gwdm').empty();
		jQuery('#gwdm').append("<option value=''>--------��ѡ��--------</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].gwdm + "\">" + data[i].gwmc + "</option>";
				jQuery('#gwdm').append(option);
			}
		}
	});
	jQuery.ajaxSetup({async:true});
}


//��÷�������
function getFFny(){
	jQuery.ajaxSetup({cache: false});
	jQuery.ajaxSetup({async:false});
	var parameter ={};
	parameter["xn"]=escape(jQuery("#xn").val());	
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
	jQuery.getJSON('qgzx_cjgl_ajax.do?method=getFFny',parameter,function(data){
		jQuery('#ffny').empty();
		if(data != null && data.length != 0){
			
			for(var i=0; i<data.length; i++){
				var option = "<option value='" + data[i].ffny + "'>" + data[i].ffny + "</option>";
				if(jQuery('#yf').val()==data[i].ffny){
					option = "<option value='" + data[i].ffny + "' selected='selected'>" + data[i].ffny + "</option>";
				
				}
				jQuery('#ffny').append(option);
			}
		}else{
			var option = "<option value=''>--��ǰ�������޷�������--</option>";
			jQuery('#ffny').append(option);
		}
	});
	jQuery.ajaxSetup({async:true});				
}


function setDivHeight(){
	if($("table_rs")){
		jQuery("#div_rs").height(jQuery("#table_rs").height()+40);	
	}
}

//��ѯǰ��֤
function onShow(){
	searchRs();
}
//��ѯ
function searchRs(){
    var xxdm = jQuery("#xxdm").val();
	var url = "qgzx_cjgl_ajax.do?method=getGwryList";
	url+="&xn="+jQuery("#xn").val();
	url+="&yrbm="+jQuery("#yrbm").val();
	url+="&gwdm="+jQuery("#gwdm").val();
	url+="&zgzt="+jQuery("#zgzt").val();
	url+="&ffny="+jQuery("#ffny").val();
	url+="&xq="+jQuery("#xq").val();
	url+="&sfyl="+jQuery("#sfyl").val();
	if(xxdm == "10351"){
        url+="&xm="+encodeURI(encodeURI(jQuery.trim(jQuery("#xsxm").val())));
	}
	var ie = 'ie';
	var otherValue = [ie];
    jQuery.post("qgzx_cjgl_ajax.do?method=getGwryList",
		{
			yrbm:jQuery("#yrbm").val(),
			doType:"queryYx",
			xn:jQuery("#xn").val(),
            xq:jQuery("#xq").val(),
            ffny:jQuery("#ffny").val()
		},
        function(result){
            var jsObject = JSON.parse(result);
            jQuery("#yxrsSpan").html(jsObject.yxrs);
            jQuery("#yxzrsSpan").html(jsObject.yxzrs);
            jQuery("#yxrs").val(jsObject.yxrs);
            jQuery("#yxzrs").val(jsObject.yxzrs);
        });
	searchRsByAjax(url,otherValue);
    setTimeout("setDivHeight()","2000");
    setTimeout("jlffxh()","1000");
    setTimeout("sfkff()","1000");
}
function sfkff(){
	var ffsj=jQuery("#ffny").val();
	ffsj+="-31";
	jQuery("#table_rs").find("tr").each(function(){
		var sgsj=jQuery(this).find("td").eq(5).html();
		if(compareDate(ffsj,sgsj,"-")==2){
			jQuery(this).find("input").attr("disabled","disabled");
			jQuery(this).find("input").val("");
		}
	});
}
function compareDate(aDate,bDate,splitStr){
	if(splitStr==null||""==splitStr){
		splitStr="-";
	}
	var aDates=aDate.split(splitStr);
	//��ʱ��a��bת��Ϊintֵ�������
	var a=aDates[0]+aDates[1]+aDates[2];
	//���ߵĲ�ֵ
	var c=parseInt(a)-parseInt(bDate);
	if(c==0){
		return "0";
	}else if(c>0){
		return "1";
	}else if(c<0){
		return "2";
	}
	return "3";
}
//�㽭��ý(�����Ƿ�����������λ�����������Լ��ĳ���׼)
function scCjbz(ind){
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    parameter["gwdm"]=escape(jQuery("#gwdm_"+ind).val());
    parameter["xh"]=escape(jQuery("#xh_"+ind).val());
    parameter["xn"]=escape(jQuery("#xn").val());
	var url = "qgzx_cjgl_ajax.do?method=scCjbz";
	jQuery.post(url,parameter,
		function(result){
		jQuery("#cjbz").val(result);
	});
	jQuery.ajaxSetup({async:true});
}
/**
 * �����
 * @return
 */
function checkJe(){
	/**
	 * ����б����Ҳ�����λ��߳���׼�������ζ�Ų���������û��������߳���׼��ֱ�������ж�
	 */
	var parameter = {};
	if(jQuery("#table_rs>tbody").find("tr").find("input[name=pzje]").length == 0){
		 parameter = {isok:true,pksyz:true,ptyz:true};
		  return parameter;
	}else{
		var isok=true;
		var flag = jQuery("#xxdm").val() == "10351";
		var je = "";
		var pzje = "";
		var pksyz = true;
		var ptyz = true;
		jQuery("#table_rs>tbody").find("tr").each(function(){
			if(flag && jQuery(this).find("input[name=sfkns]").val() == '������'){
				 je = jQuery(this).find("input[name=je]").val();
				 pzje = jQuery("#pkscjzgsx").val();
				 if(parseInt(je)>parseInt(pzje)){
						jQuery(this).find("input[name=je]").attr("style","border:1px solid #FF99CC;");
						isqy=true;
						isok=false;
						pksyz=false;
				 }
			}else{
				 je=jQuery(this).find("input[name=je]").val();
				 pzje=jQuery(this).find("input[name=pzje]").val();
				 if(parseInt(je)>parseInt(pzje)){
					jQuery(this).find("input[name=je]").attr("style","border:1px solid #FF99CC;");
					isqy=true;
					ptyz = false;
					isok=false;
				 }
			}
			
		});
		 parameter = {isok:isok,pksyz:pksyz,ptyz:ptyz};
		return parameter;
	}
}
function autoSetColor(obj){
	if(isqy){
		var je=jQuery(obj).val();
		var pzje=jQuery(obj).parents("tr").find("lable[name=pzje]").text();
		if(parseInt(je)>parseInt(pzje)){
			jQuery(obj).attr("style","border:1px solid #FF99CC;");
		}else{
			jQuery(obj).attr("style","border:1px solid #4D93EA;");
		}
	}
}

//���������Ի�
function Jsje(ind,jfhb,zc,gwcjbz){
	var gs = (jQuery("#gs_"+ind).val()*100).toFixed();
	if(""==gs){
		gs=0;
	}
	//������   ��λ����׼ X ��ʱ
	var cjbz = (gwcjbz*100).toFixed();
	var zje = parseFloat(gs)*parseFloat(cjbz);
	zje = zje/10000;
	if(""==gs){
		zje = "";
	}
	jQuery("#je_"+ind).val(zje);
	//���� ���ѻ������   ���ѻ��� X ��ʱ
	var jfhbje = (jfhb*100).toFixed();
	var jfhbzje =parseFloat(gs)*parseFloat(jfhbje);
	
	jfhbzje = jfhbzje/10000;
	if(""==gs){
		jfhbzje = "";
	}
	jQuery("#jfhb_"+ind).val(jfhbzje);
	//���� �Գ���   �Գ� X ��ʱ
	var zcje = (zc*100).toFixed();
	var zczje =parseFloat(gs)*parseFloat(zcje);
	
	zczje = zczje/10000;
	if(""==gs){
		zczje = "";
	}
	jQuery("#zc_"+ind).val(zczje);
	
}

//�����Ƽ���ѧ10704 
function checkGs(ind,gdgcjbz){
	var gs =jQuery("#gs_"+ind).val();
	if(gs<20){
//		showAlertDivLayer("�̶��ڵ���20����ʱ���ܷ��ų��");
		jQuery("#je_"+ind).val(0);
	}else{
		jQuery("#je_"+ind).val(gdgcjbz);	
	}
}

//������
function getResult(ind){
	var gs = (jQuery("#gs_"+ind).val()*100).toFixed();
	
	if(""==gs){
		gs=0;
	}
	var cjbz = (jQuery("#cjbz").val()*100).toFixed();
	/**
	 * ���ݴ�ѧ���Ի�
	 */
	if(jQuery("#xxdm").val() == "10351" && jQuery("#gs_"+ind).parent().parent().find("input[name='sfkns']").val() == "������"){
		cjbz = (jQuery("#pkscjbz").val()*100).toFixed();
	}

	var result = parseFloat(gs)*parseFloat(cjbz);
	
	result = result/10000;
	
	
	
	if(""==gs){
		result = "";
	}
	/**if("0"==result){
		result = "";
	}**/
	//���ܴ��ڳ�����ޣ������Զ�����Ϊ���
	///var pzje=jQuery("#gs_"+ind).parents("tr").find("lable[name=pzje]").text();
	//if(result>=pzje){
	//	result=pzje;
	//}
	jQuery("#je_"+ind).val(result);
}	
//��¼�Ѿ����� ѧ�� ��λ
function jlffxh(){
	var xh="";
	var gw="";
	var gwid=jQuery("input[name=gwdm]");
	jQuery("input[name=xh]").each(function(i,e){
		var je=jQuery(this).parents("tr").find("td").eq(6).find("input[name=je]").val();
		if(je!=""){
			xh+=jQuery(this).val();
			xh+="!!@@!!";
			gw+=jQuery(gwid[i]).val();
			gw+="!!@@!!";
		}
	});
	jQuery("#yffxh").val(xh);
	jQuery("#yffgw").val(gw);
}
function checkTjzt(){
	var flag = false;
	var tjzt =jQuery("#ffzt").val();
	if("1"==tjzt){
		flag=true;
	}
	return flag;
}
// ����
function saveCjxxff(){
	jlffxh();
	var xxdm = jQuery("#xxdm").val();
	var ffny = jQuery("#ffny").val();
	if("102770"==xxdm){
		if(checkTjzt()){
			showAlertDivLayer("����"+ffny+"��𷢷����ύ�������ظ��ύ��");
			return false;
		};
	}
	//���������ѧ���Բ�ѡ��ѧ��
	if("10052" != xxdm && $("xn") && $("xn").value==""){
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
	
	if($("ffny") && $("ffny").value==""){
 		alertInfo("�������²���Ϊ��!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	
	if("12309"==xxdm){
		var cjsx=null;
		var xh = jQuery("input[name=xh]");
		var gwdm = jQuery("input[name=gwdm]");
		var gs = jQuery("input[name=gs]");
		var je = jQuery("input[name=je]");
		var bz = jQuery("input[name=bz]");
		var khdj = jQuery("select[name=khdj]");
		
		var gwcjbz =jQuery("input[name=gwcjbz]");
		var jfhb =jQuery("input[name=jfhb]");
		var zc =jQuery("input[name=zc]");
		var gwcjsx =jQuery("input[name=gwcjsx]");
		
		var xhs = "";
		var gwdms = "";
		var gss = "";
		var jes = "";
		var bzs = "";
		var khdjs="";
		var jfhbs ="";
		var zcs="";
		
		if(xh.length==0){
			alertInfo("�ø�λ�·�����Ա���ţ����豣����ύ!");
	 		return false;
		}
		//��������
		for(var i = 0;i <xh.length;i++){
			if(jQuery(je[i]).val()!=""){//�������ֶβ�Ϊ�գ�ȥ�ж�  ��λ����׼X��ʱ �󲻴��� ��λ�±�׼
				 if(/*sxzd=="je"&&*/parseFloat(jQuery(je[i]).val())>parseFloat(jQuery(gwcjsx[i]).val())){
					alertInfo("ѧ��<font color='red'>["+jQuery(xh[i]).val()+"]</font>���Ž��ܴ��ڸø�λÿ�³������("+jQuery(gwcjsx[i]).val()+"Ԫ)!");
		     		return false;
				}
				if(jQuery(gs[i]).val()!=null&&jQuery(gs[i]).val()!=""){
					gss+=jQuery(gs[i]).val()+"!!@@!!";
				}
				if(jQuery(je[i]).val()!=null&&jQuery(je[i]).val()!=""){
					jes+=jQuery(je[i]).val()+"!!@@!!";
				}
				if(jQuery(jfhb[i]).val()!=""){
					jfhbs+=jQuery(jfhb[i]).val()+"!!@@!!";
				}
				if(jQuery(zc[i]).val()!=null&&jQuery(zc[i]).val()!=""){
					zcs+=jQuery(zc[i]).val()+"!!@@!!";
				}
				xhs+=jQuery(xh[i]).val()+"!!@@!!";
				gwdms+=jQuery(gwdm[i]).val()+"!!@@!!";
				
				bzs+=jQuery(bz[i]).val()+"!!@@!!";
				khdjs+=jQuery(khdj[i]).val()+"!!@@!!";
			
				
			}
		}
		var gs = gss.split("!!@@!!"); 
		var jfhb = jfhbs.split("!!@@!!");
		var zc = zcs.split("!!@@!!");
		var je = jes.split("!!@@!!");
		if(gs.length!=jfhb.length||gs.length!=zc.length||gs.length!=je.length){
			alertInfo("��ʱ�ͽ�����ͬʱ���Ż���ͬʱ������!");
			return false;
		}
		if(""==jes){
			alertInfo("�ø�λ�·��޷��Ž����豣��!");
	 		return false;
		}
		//����Ϊ�գ�������
		gss+="end";
		bzs+="end";
	    jQuery("#xhs").val(xhs);
	    jQuery("#gwdms").val(gwdms);
	    jQuery("#gss").val(gss);
	    jQuery("#jes").val(jes);
	    jQuery("#bzs").val(bzs);
	    jQuery("#khdjs").val(khdjs);
		jQuery("#cjsx").val(cjsx);
		jQuery("#jfhbs").val(jfhbs);
		jQuery("#zcs").val(zcs);
		var message = checkTjInfo();
		if(""!=message){		
			showAlert(message,{},{"clkFun":function(){
					if (parent.window){
						//refershParent();
						return false;
					}
				}});
			return false;
		}
		saveCjffForWCSY();
		
		
	}else{
	
	var cjsx=null;
	var xh = jQuery("input[name=xh]");
	var gwdm = jQuery("input[name=gwdm]");
	var gwxz = jQuery("input[name=gwxz]");
	var gwxn = jQuery("input[name=gwxn]");
	var gwxq = jQuery("input[name=gwxq]");
	var gs = jQuery("input[name=gs]");
	var je = jQuery("input[name=je]");
	var bz = "";
	if(xxdm == "10351"){
        bz = jQuery("select[name=bz]");
        var len = 0;
        for(var i=0;i<bz.length;i++){
        	if(jQuery(bz[i]).val() == '����')
        		len ++;
		}
       //var len = jQuery("select[name=bz][value=]").length;
        var yxzrs = jQuery("#yxzrs").val();
        if(len > yxzrs){
            alertInfo("����������������!");
            return false;
        }
	}else{
        bz = jQuery("input[name=bz]");
	}
	var khdj = jQuery("select[name=khdj]");
	var zhdlgs = jQuery("input[name=zhdlgs]");
	var jcdlgs = jQuery("input[name=jcdlgs]");
	var xhs = "";
	var gwdms = "";
	var gwxzs = "";//��λ����

	var gwxns = "";
	var gwxqs = "";

	var gss = "";
	var jes = "";
	var bzs = "";
	var zhdlgss = "";
	var jcdlgss = "";
	var khdjs="";
	if(xh.length==0){
		alertInfo("�ø�λ�·�����Ա���ţ����豣����ύ!",function(tag){
 			if(tag=="ok"){
 				return false;
 			}
 		});
 		return false;
	}
	//�������check��982
	var jsonYzRs = checkJe();
	if(!jsonYzRs["isok"]){
		if(!jsonYzRs["ptyz"]){
			alertInfo("���ܳ���<font color='blue'>��λ�������</font>,��ȷ��");
		}
		if(!jsonYzRs["pksyz"]){
			alertInfo("���ܳ���<font color='blue'>ƶ������λ�������</font>,��ȷ��");
		}
 		return false;
	}else{
		isqy=false;
	}
	//��ª�Ĳ��� 982������Ĵ���ʵ�ڲ��Ҷ�����������ɣ��ڼ��Լ�����£���
	var sxzd = jQuery("#sxzd").val();
	for(var i = 0;i <xh.length;i++){
		cjsx=jQuery("#sxsz").val();
		var dyje=0;
		var xhgss=0;
		if(xxdm == "11647"){
			if(""==jQuery(gs[i]).val() || ""==jQuery(je[i]).val()){
				alertInfo("��ʱ���ܿ�!");
				return false;
			}
		} else {
			if(!jQuery(je[i]).val()){
				continue;
			}
		}
		for(var j = 0;j <xh.length;j++){
			if(""!=jQuery(je[j]).val()){
				if(""==jQuery(gs[j]).val()){
					alertInfo("��ʱ���ܿ�!");
					return false;
				}

				if(xxdm == "10351" && ""==jQuery(bz[j]).val()){
					alertInfo("���˽�����ܿ�!");
					return false;
				}
				
				if(jQuery(xh[i]).val()==jQuery(xh[j]).val()){
/*					if(sxzd=="je"){
						dyje=parseFloat(dyje)+parseFloat(jQuery(je[j]).val());
					}else if(sxzd=="gs"){
						dyje=dyje+parseFloat(jQuery(gs[i]).val())*parseFloat(jQuery(je[j]).val());
						xhgss=parseFloat(xhgss)+parseFloat(jQuery(gs[i]).val());
					}*/
					dyje=parseFloat(dyje)+parseFloat(jQuery(je[j]).val());
					if(sxzd=="gs"){
						xhgss=parseFloat(xhgss)+parseFloat(jQuery(gs[j]).val());
					}
				}
			}
		}
		if(sxzd=="gs"&&parseFloat(xhgss)>parseFloat(jQuery("#sxsz").val())){
			alertInfo("<font color='red'>["+jQuery(xh[i]).val()+"]</font>���Ź�ʱ���ܴ���ÿ��ѧ����ʱ����("+jQuery("#sxsz").val()+"Сʱ)!",function(tag){
     			if(tag=="ok"){
     				return false;
     			}
     		});
     		return false;
		}
		//���Ƕ��ǲ��� ������õ��ǹ�ʱ����ǰ���������Ϊ ���޳��Թ�ʱ
		if(sxzd=="gs"){
			cjsx=parseFloat(jQuery("#cjbz").val())*parseFloat(cjsx);
		}
		if(sxzd=="gs" && jQuery("#xxdm").val() == "10351" && jQuery(xh[i]).parent().parent().find("input[name='sfkns']").val() =='������'){
			cjsx=parseFloat(jQuery("#pkscjbz").val())*parseFloat(jQuery("#sxsz").val());
		}
			if(parseFloat(dyje)>parseFloat(cjsx) && jQuery("#xxdm").val() != "11647"){
				if(jQuery("#xxdm").val() != "10704"&&gwxz[i]!="�̶���"){
					var title = "<font color='red'>["+jQuery(xh[i]).val()+"]</font>���Ž��ܴ���ѧ��ÿ�³������("+cjsx+"Ԫ)!"
					if(jQuery("#xxdm").val() == "10351" && jQuery(xh[i]).parent().parent().find("input[name='sfkns']").val() =='������'){
						title = "<font color='red'>["+jQuery(xh[i]).val()+"]</font>���Ž��ܴ���ƶ����ÿ�³������("+cjsx+"Ԫ)!"
					}
					alertInfo(title);
					return false;
				}
			}
	}
	//��������
	for(var i = 0;i <xh.length;i++){
		if(""!=jQuery(je[i]).val() /*&& "0"!=jQuery(je[i]).val()*/){
			if(jQuery("#xxdm").val() != '10351'){
				var sxzd = jQuery("#sxzd").val();
				if(sxzd=="gs"&&parseFloat(jQuery(gs[i]).val())>parseFloat(jQuery("#sxsz").val())){
					alertInfo("<font color='red'>["+jQuery(xh[i]).val()+"]</font>���Ź�ʱ���ܴ���ѧ��ÿ�¹�ʱ����("+jQuery("#sxsz").val()+"Сʱ)!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
				}else if(sxzd=="je"&&parseFloat(jQuery(je[i]).val())>parseFloat(jQuery("#sxsz").val())){
					if(jQuery("#xxdm").val() != "10704"&&gwxz[i]!="�̶���"){
						alertInfo("<font color='red'>["+jQuery(xh[i]).val()+"]</font>���Ž��ܴ���ѧ��ÿ�³������("+jQuery("#sxsz").val()+"Ԫ)!");
			     		return false;
					}
				}
			}
			xhs+=jQuery(xh[i]).val()+"!!@@!!";
			gwdms+=jQuery(gwdm[i]).val()+"!!@@!!";
			gwxzs+=jQuery(gwxz[i]).val()+"!!@@!!";

            gwxns+=jQuery(gwxn[i]).val()+"!!@@!!";
            gwxqs+=jQuery(gwxq[i]).val()+"!!@@!!";

			gss+=jQuery(gs[i]).val()+"!!@@!!";
			jes+=jQuery(je[i]).val()+"!!@@!!";
			bzs+=jQuery(bz[i]).val()+"!!@@!!";
			khdjs+=jQuery(khdj[i]).val()+"!!@@!!";
			jcdlgss+=jQuery(jcdlgs[i]).val()+"!!@@!!";
			zhdlgss+=jQuery(zhdlgs[i]).val()+"!!@@!!";
			
			
		}
	}

	if(""==jes){
		alertInfo("�ø�λ�·��޷��Ž����豣��!");
 		return false;
	}
	//����Ϊ�գ�������
	gss+="end";
	bzs+="end";
	jcdlgss+="end";
	zhdlgss+="end";
    jQuery("#xhs").val(xhs);
    jQuery("#gwdms").val(gwdms);
    jQuery("#gwxzs").val(gwxzs);
	jQuery("#gwxns").val(gwxns);
	jQuery("#gwxqs").val(gwxqs);
    jQuery("#gss").val(gss);
    jQuery("#jes").val(jes);
    jQuery("#bzs").val(bzs);
    jQuery("#jcdlgss").val(jcdlgss);
    jQuery("#zhdlgss").val(zhdlgss);
    jQuery("#khdjs").val(khdjs);
    if(sxzd=="gs"){
		cjsx=parseFloat(jQuery("#cjbz").val())*parseFloat(jQuery("#sxsz").val());
	}
	jQuery("#cjsx").val(cjsx);
	var message = checkTjInfo();
	if(""!=message){		
		showAlert(message,{},{"clkFun":function(){
				if (parent.window){
					//refershParent();
					return false;
				}
			}});
		return false;
	}
	saveCjff();
	}
}
//�����ύ(��������������)
function saveCjffForWCSY(){
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["xq"]=escape(jQuery("#xq").val());
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
    parameter["ffny"]=escape(jQuery("#ffny").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["gwdm"]=escape(jQuery("#gwdms").val());
    parameter["gs"]=escape(jQuery("#gss").val());
    parameter["je"]=escape(jQuery("#jes").val());
    parameter["bz"]=escape(jQuery("#bzs").val());
    parameter["khdj"]=escape(jQuery("#khdjs").val());
    parameter["cjffr"]=escape(jQuery("#cjffr").val());
    parameter["jfhb"]=escape(jQuery("#jfhbs").val());
	parameter["zc"]=escape(jQuery("#zcs").val());
	
    //�Ѿ����Ź���ѧ������¼�޸�ǰ��ѧ��
    parameter["yffxh"]=escape(jQuery("#yffxh").val());
    parameter["yffgw"]=escape(jQuery("#yffgw").val());
    parameter["cjsx"] = escape(jQuery("#cjsx").val());
    parameter["sxzd"] = escape(jQuery("#sxzd").val());
    parameter["sxsz"] = escape(jQuery("#sxsz").val());
	var url = "qgzx_cjgl_ajax.do?method=saveCjffxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";			
			showAlert(result,{},{"clkFun":function(){
				if("����ɹ�"!=result){
					return false;
				}
				else if(parent.window){
 					refershParent();
 				}
 			}});
			}
	);
	jQuery.ajaxSetup({async:true});
}
//�����ύ
function saveCjff(){
	jQuery.ajaxSetup({async:false});
	// �õ�JSON����
    var parameter ={};	
    parameter["xn"]=escape(jQuery("#xn").val());
    parameter["xq"]=escape(jQuery("#xq").val());
    parameter["yrbm"]=escape(jQuery("#yrbm").val());
    parameter["ffny"]=escape(jQuery("#ffny").val());
    parameter["xh"]=escape(jQuery("#xhs").val());
    parameter["gwdm"]=escape(jQuery("#gwdms").val());
    parameter["gwxz"] = escape(jQuery("#gwxzs").val());

    parameter["gwxn"]=escape(jQuery("#gwxns").val());
    parameter["gwxq"] = escape(jQuery("#gwxqs").val());

    parameter["gs"]=escape(jQuery("#gss").val());
    parameter["je"]=escape(jQuery("#jes").val());
    parameter["bz"]=escape(jQuery("#bzs").val());
    parameter["khdj"]=escape(jQuery("#khdjs").val());
    parameter["cjffr"]=escape(jQuery("#cjffr").val());
    parameter["jcdlgs"]=escape(jQuery("#jcdlgss").val());
    parameter["zhdlgs"]=escape(jQuery("#zhdlgss").val());
    
    //�Ѿ����Ź���ѧ������¼�޸�ǰ��ѧ��
    parameter["yffxh"]=escape(jQuery("#yffxh").val());
    parameter["yffgw"]=escape(jQuery("#yffgw").val());
    parameter["cjsx"] = escape(jQuery("#cjsx").val());
    parameter["sxzd"] = escape(jQuery("#sxzd").val());
    parameter["sxsz"] = escape(jQuery("#sxsz").val());

	var url = "qgzx_cjgl_ajax.do?method=saveCjffxx";
    $("divWaiting").style.display="";
	$("divDisable").style.display="";
	jQuery.post(url,parameter,
		function(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";			
			showAlert(result,{},{"clkFun":function(){
				if("����ɹ�"!=result){
					return false;
				}
				else if(parent.window){
 					refershParent();
 				}
 			}});
			}
	);
	jQuery.ajaxSetup({async:true});
}

//��֤�ύ��Ϣ
function checkTjInfo(){
	var xxdm = jQuery("#xxdm").val();
	var data = "";
	var parameter={};
	var url="qgzx_cjgl_ajax.do?method=checkTjInfo";	
	parameter["xn"]=escape(jQuery("#xn").val());
	parameter["gwdm"]=escape(jQuery("#gwdm").val());
	parameter["yrbm"]=escape(jQuery("#yrbm").val());
	parameter["ffny"]=escape(jQuery("#ffny").val());
	parameter["je"]=escape(jQuery("#jes").val());
	if("12309"==xxdm){
		parameter["jfhb"]=escape(jQuery("#jfhbs").val());
		parameter["zc"]=escape(jQuery("#zcs").val());
	}
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

//�㽭�����Ի������ܹ�ʱ

function getZgs(ind){
	
	var jcdlgs = jQuery("#jcdlgs_"+ind).val();  //������ʱ	
	var jcdl = jQuery("#jcdl_"+ind).val();		//��������
	var zhdlgs = jQuery("#zhdlgs_"+ind).val();	//�ۺϹ�ʱ
	var zhdl = jQuery("#zhdl_"+ind).val();		//�ۺϵ���
	var kndjdl = jQuery("#kndjdl_"+ind).val();	//���ѵȼ�����
	
	jcdlgs = jcdlgs == ""?0:jcdlgs;
	jcdl = jcdl == ""?0:jcdl;
	zhdlgs = zhdlgs == ""?0:zhdlgs;
	zhdl = zhdl == ""?0:zhdl;
	kndjdl = kndjdl == ""?0:kndjdl;
	
	//(������ʱx��������+�ۺϹ�ʱx�ۺϵ���)x���ѵȼ�����
	var result = ((parseFloat(jcdlgs)*parseFloat(jcdl)+parseFloat(zhdlgs)*parseFloat(zhdl))*parseFloat(kndjdl)).toFixed(2);
	
	jQuery("#gs_"+ind).val(result);
	getResult(ind);
}


