var flag = 0;
var addRowNum = 0;
//��ӡ����
function printReport(url){
	if(url == "qgzx_bb_gwsbb.do?gwdm="){
		if($("sqdw")){
			if(document.getElementById("sqdw").value == "" || document.getElementById("sqdw").value == null){
				alert("��ѡ�����뵥λ��");
				return false;
			}
		}
		url += document.getElementById("gwdm").value;
		url += "&yrdwdm=";
		url += document.getElementById("sqdw").value;
		url += "&xyrs=";
		url += document.getElementById("xyrs").value;
		url += "&gznr=";
		url += document.getElementById("gznr").value;
	}else{
		if($("xmdm")){
			if(document.getElementById("xmdm").value == "" || document.getElementById("xmdm").value == null){
				alert("��ѡ��Ҫ��ӡ�ĸ�λ��");
				return false;
			}
		}
		url += document.getElementById("xmdm").value;
		url += "&xh=";
		url += document.getElementById("xh").value;
	}
	window.open(url);
}

//��ȡ���˵�λ��Ϣ
function getYrdwInfo(){
	var yrdw = document.getElementById("sqdw").value;
	if(yrdw != null && yrdw != ""){
		getOtherData.getYrdwInfo(yrdw,TjYrdwInfo);
	}
}

//��������Ƿ����
function chkisDataExist(mustFill){
	var eles = mustFill.split("-");
	var isModi = "";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	if($("noCommend")){
		alert("�㻹û��ͨ��"+jQuery("#xbmc").val()+"�Ƽ�����ʱ���������λ��");
		return false;
	}
	if($("gwExists")){
		if(!confirm("���Ѿ��и�λ���ͨ�����������뽫�п���ʧȥ��ͨ���ĸ�λ��")){
			return false;
		}
	}
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
		
	if($("yhtc")){
		var yhtc = document.getElementById("yhtc").value;		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�س����ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("�������ɲ��ܴ���500���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("��ע���ܴ���500���ַ�");
	          		 return false;
	       		 }
			}
	}
	if(isModi == "modi"){	
		BatAlert.showTips('���ڲ��������Ե�...');
		dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
	}else{
		var xh = $("xh").value;
		var gwdmgwsbsj = $("xmdm").value;	
		var query = xh + "-" + gwdmgwsbsj;
		if($("xxdm").value == "10419"){
			//����ɽ��ѧ
			getOtherData.IsDataExist(query,jgsTjIsDataExist);
		}else{
			getOtherData.IsDataExist(query,TjIsDataExist);			
		}
	}
}

//�й����ʴ�ѧ�����������Ƿ����
function zgdzdx_sqgw_chkisDataExist(mustFill){
	
	var eles = mustFill.split("-");
	var isModi = "";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	if($("gwExists")){
		if(!confirm("���Ѿ��и�λ���ͨ�����������뽫�п���ʧȥ��ͨ���ĸ�λ��")){
			return false;
		}
	}
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	
	if(        ($("gwdmgwsbsj1").value==$("gwdmgwsbsj2").value && $("gwdmgwsbsj2").value !="")
			||($("gwdmgwsbsj2").value==$("gwdmgwsbsj3").value && $("gwdmgwsbsj2").value !="")
			||($("gwdmgwsbsj1").value==$("gwdmgwsbsj3").value && $("gwdmgwsbsj3").value !="")
		){
		alert("�������λ���ظ�����Ч��");
		return false;
	}
	if($("yhtc")){
		var yhtc = document.getElementById("yhtc").value;		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�س����ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("�������ɲ��ܴ���250���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("��ע���ܴ���200���ַ�");
	          		 return false;
	       		 }
			}
	}
	dwr.engine.setAsync(false);
	var jcFlag = true;
	for(i=1;i<4;i++){
		if($("gwdmgwsbsj"+i+"")){
			var gwdmgwsbsj = document.getElementById("gwdmgwsbsj"+i+"").value;
			if(gwdmgwsbsj!=""&&gwdmgwsbsj!=null){
				var xh = document.getElementById("xh").value;
				var query = xh + "-" + gwdmgwsbsj;
				getOtherData.zgdzdx_sqgw_IsDataExist(query,getResult);
				function getResult(data){
					var result = data;
					if(result==1){
						if($('doType') && $('doType').value == 'modi'){
							//�޸���Ϣ
							alert("�˸�λ�Ѿ����ͨ������ʱ�����޸�!")
						}else{
							alert("�������λ"+i+"�����ظ�����Ч!")
						}
						jcFlag = false;
					}
				}
			}
		}
	}	
	if(jcFlag){
		if(isModi == "modi"){
			dataDoSaveApply3('zgdzdx_hg_SaveOne.do','pkValue','gwsq');
		}else{		
			dataDoSaveApply3('zgdzdx_gwsq_SaveOne.do','pkValue','gwsq');
		}
	} else {
		return false;
	}
	dwr.engine.setAsync(true);
}

//�й����ʴ�ѧ���ڼ�������Ƿ����
function zgdzdx_chkisDataExist(mustFill){
	var eles = mustFill.split("-");
	var isModi = "";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	if($("noCommend")){
		alert("�㻹û��ͨ��"+jQuery("#xbmc").val()+"�Ƽ�����ʱ���������λ��");
		return false;
	}
	if($("gwExists")){
		if(!confirm("���Ѿ��и�λ���ͨ�����������뽫�п���ʧȥ��ͨ���ĸ�λ��")){
			return false;
		}
	}
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	
	if($("hgdmhgsj1") != undefined){
		if($("hgdmhgsj1").selectedIndex <= 0){
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		}
	}
	if($("gwdmgwsbsj") != undefined && $("hgdmhgsj1") != undefined && $("hgdmhgsj2")!= undefined && $("hgdmhgsj3")!= undefined ){
		if(($("gwdmgwsbsj").value==$("hgdmhgsj1").value)||($("gwdmgwsbsj").value==$("hgdmhgsj2").value)
		||($("gwdmgwsbsj").value==$("hgdmhgsj3").value)){
			alert("��λ�ظ�����Ч��");
			return false;
		}
	}
	
	if($("hgdmhgsj1") != undefined && $("hgdmhgsj2")!= undefined && $("hgdmhgsj3")!= undefined ){
		if((($("hgdmhgsj2").selectedIndex > 0) && ($("hgdmhgsj1").value==$("hgdmhgsj2").value))||
		(($("hgdmhgsj2").selectedIndex > 0) && ($("hgdmhgsj2").value==$("hgdmhgsj3").value))){
			alert("��λ�ظ�����Ч��");
			return false;
		}
	}
	
	if($("hgdmhgsj1") != undefined && $("hgdmhgsj2")!= undefined && $("hgdmhgsj3")!= undefined ){
		if((($("hgdmhgsj3").selectedIndex > 0) && ($("hgdmhgsj1").value==$("hgdmhgsj3").value))||
		(($("hgdmhgsj3").selectedIndex > 0) && ($("hgdmhgsj2").value==$("hgdmhgsj3").value))){
			alert("��λ�ظ�����Ч��");
			return false;
		}
	}
	
	if($("yhtc")){
		var yhtc = document.getElementById("yhtc").value;		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�س����ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("�������ɲ��ܴ���250���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("��ע���ܴ���60���ַ�");
	          		 return false;
	       		 }
			}
	}
	
	if(isModi == "modi"){
		dataDoSaveApply3('zgdzdx_hg_SaveOne.do','pkValue','gwsq');
	}else{
		var xh = document.getElementById("xh").value;
		var gwdmgwsbsj = document.getElementById("gwdmgwsbsj").value;
		var query = xh + "-" + gwdmgwsbsj;
		getOtherData.zgdzdx_hg_IsDataExist(query,zgdzdxhg_TjIsDataExist);
	}
}

//�й����ʴ�ѧ�Ǹڼ�������Ƿ����
function zgdzdx_cg_chkisDataExist(mustFill){
	var eles = mustFill.split("-");
	var isModi="";
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	
	if($("gwdmgwsbsj")){
		var gwdmgwsbsj = document.getElementById("gwdmgwsbsj").value;
		if(gwdmgwsbsj == null){
			alert("�޸�λ��¼����������Ǹ�");
			return false;
		}
	}
	
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("�������ɲ��ܴ���250���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("bz")){
		var bz = document.getElementById("bz").value;
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          		 alert("��ע���ܴ���60���ַ�");
	          		 return false;
	       		 }
			}
	}
	
	var xh = document.getElementById("xh").value;
	var query = xh + "-" + gwdmgwsbsj;
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	if(isModi == "modi"){
		dataDoSaveApply3('zgdzdx_cg_SaveOne.do','gwdmgwsbsj','gwsq');
	}else{
		dataDoSaveApply3('zgdzdx_cg_Save.do','gwdmgwsbsj-sqly','gwsq');
	}
}

/**�й����ʴ�ѧ���˵�λ����ѧ����Ϣ�����������Ƿ����*/
function zgdzdx_yrdwghxssq_chkisDataExist(){
	if($("gwdmgwsbsj")){
		var gwdmgwsbsj = document.getElementById("gwdmgwsbsj").value;
		if(gwdmgwsbsj == null || gwdmgwsbsj==""){
			alert("��ѡ���λ��");
			return false;
		}
	}
	if($("sqly")){
		var sqly = document.getElementById("sqly").value;
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("�������ɲ��ܴ���250���ַ�");
	          		 return false;
	       		 }
			}
	}
	if($("gwyq")){
		var gwyq = document.getElementById("gwyq").value;
		if(gwyq != null){
	         	if(gwyq.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("��λҪ���ܴ���250���ַ�");
	          		 return false;
	       		 }
			}
	}
	if(table1.rows.length==0&&table2.rows.length==0){
		alert("û����дѧ������,�����ύ,�������ݺ�'+'��ť����ѧ�����ݣ�");
		return false;
	}
	dataDoSaveApply3('zgdzdx_yrdwghxssq_Save.do','gwdmgwsbsj-sqly','gwsq');
}

function getGwDetInfo(){
	var xn = document.getElementById("xn").value;
	var nd = document.getElementById("nd").value;
	var xq = document.getElementById("xq").value;
	var yrdwdm = document.getElementById("yrdwdm").value;
	getOtherData.getGwDetInfo(xn,nd,xq,yrdwdm,TjGwDetInfo);
}

//��ȡ��λ������
function getZrs(obj){
	document.getElementById("sqzrs").innerText = "0";
	for(i=0; i<document.getElementsByName("gwxx").length; i++){
		for(j=0; j<document.getElementsByName("gwxx").length; j++){
			if(document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value).value == ""){
				document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value).value = 0;
			}
		}
		if(identifydata(document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value))){
			document.getElementById("sqzrs").innerText = parseInt(document.getElementById("sqzrs").innerText) + parseInt(document.getElementById("xyrs"+document.getElementsByName("gwxx")[i].value).value);
		}else{
			break;
		}		
	}
}

//�ڹ���ѧ���ݱ���
function qgzxSave(url,mustFill){
	if(checkXnNd("xn", "nd")){
		var xxdm = document.getElementById('xxdm').value;
		var eles = mustFill.split("-");
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
		
		if(xxdm == "10491"){//�й����ʴ�ѧ
			url = "cjff.do?method=reWorkPut";	
		}
		document.forms[0].action = url;
		document.forms[0].submit();
	}
}
////////////////////Ajax�ύ����//////////////////////////
function TjYrdwInfo(data){
	if($("lxr")){document.getElementById("lxr").value = data[0]};
	if($("lxdh")){
		var lxdh=document.getElementById("lxdh").value;
		if(data[1]==null||data[1]==""){
			document.getElementById("lxdh").value = ""
		return false;}
		document.getElementById("lxdh").value = data[1]
		
	};
}

function TjIsDataExist(data){
	if(data != null){
		if(data == "1"){
			if(confirm("���Ѿ�����˸�λ\n�˲�����������������޸ģ�ȷ��Ҫ�޸���")){
				BatAlert.showTips('���ڲ��������Ե�...');
				dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
			}else{
				return false;
			}
		}else if( data== "2"){
			alert("���Ѿ�����˸�λ\n���Ѿ�ͨ����ˣ����Ѳ���������˸�λ��");
			return false;
		}else if(data == '3'){
			alert('���Ѿ��������λ\n���Ѿ�ͨ����ˣ����Ѳ����������λ��');
			return false;
		}else{
			BatAlert.showTips('���ڲ��������Ե�...');
			dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
		}
	}else{
		alert("��ȡ����ʧ�ܣ�");
		return false;
	}
}
/**���ʴ�ѧ����������Ϣ����*/
function zgdzdxhg_TjIsDataExist(data){
	if(data != null){
		if(data == "0"){
			alert("��Ŀǰû���ڸڣ��������뻻�ڣ�");
			return false;
		}else if(data == '1'){
			dataDoSaveApply3('zgdzdx_hg_Save.do','xmdm','gwsq');
		}
	}else{
		alert("��ȡ����ʧ�ܣ�");
		return false;
	}
}

//���ھ���ɽ��ѧ
function jgsTjIsDataExist(data){
	var xh = document.getElementById("xh").value;
	var pkValue = document.getElementById("xmdm").value;
	var varDjsj  = ""
	if($("djsj")){
		if($("djsj").checked == true){
			//����ѧ�����㡰����������������
			//document.getElementById("djsj").value = "1";
			varDjsj = "1";
		}else{
			//document.getElementById("djsj").value = "0";
			varDjsj = "0";
		}
	}
	if(data != null){
		if(data == "1"){
			if(confirm("���Ѿ�����˸�λ\n�˲�����������������޸ģ�ȷ��Ҫ�޸���")){
				dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
			}else{
				return false;
			}
		}else if( data== "2"){
			alert("���Ѿ�����˸�λ\n���Ѿ�ͨ����ˣ��㲻��������˸�λ��");
			return false;
		}else{
			cqkjFunc.isStudentCondOK(xh,pkValue,varDjsj,function(data){
				if(data == "0"){
					//��������,ֱ���ύ
					document.getElementById("fuhexx").value = "1";
					dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
				}else{
					if(confirm("ͬѧ�����ϵı�׼�У� \n" + data + "\n���п��ܲ���ͨ�����Ƿ��������!")){
						//��ʹ������,Ҳ����Ϣ�ύ	
						document.getElementById("fuhexx").value = "0";
						dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq');
					}else{
						return;
					}
				}
			});
		}
	}else{
		alert("��ȡ����ʧ�ܣ�");
		return false;
	}
}

function TjGwDetInfo(data){
	if(data!=null){
		document.getElementById("rTable").rows[3].cells[1].innerText = data[0];
		document.getElementById("rTable").rows[3].cells[3].innerText = data[1];
		document.getElementById("rTable").rows[4].cells[1].innerText = data[2];
		document.getElementById("rTable").rows[4].cells[3].innerText = data[4];
		document.getElementById("rTable").rows[5].cells[1].innerText = data[5];
		document.getElementById("rTable").rows[5].cells[3].innerText = data[6];
		document.getElementById("gwxzdm").value = data[3];
	}else{
		alert("�ø�λδ�������ѣ�");
		return false;
	}
}


function commitApply(url,nStr){
	var str = nStr.split("-");
	var value = "";
	var isModi = "";
	if($("xmdm")){
		var xmdm = document.getElementById("xmdm").value;
	}
	for (var i = 0 ;i<str.length; i++){
		value = document.getElementById(str[i]).value;
		if(value == ""){
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		}
	}	
	if($("isModi")){
		isModi = document.getElementById("isModi").value;
	}
	if(isModi == "modi"){
		refreshFrom(url+"&xmdm="+xmdm);
	}else{
		var xh = document.getElementById("xh").value;
		var gwdmgwsbsj = document.getElementById("xmdm").value;
		var query = xh + "-" + gwdmgwsbsj;
		getOtherData.IsDataExist(query,function(data){
		if(data != null){
			if(data == "1"){
				if(confirm("���Ѿ�����˸�λ\n�˲�����������������޸ģ�ȷ��Ҫ�޸���")){
					refreshForm(url+"&xmdm="+xmdm);
				}else{
					return false;
				}
			}else if( data== "2"){
				alert("���Ѿ�����˸�λ\n���Ѿ�ͨ����ˣ��㲻��������˸�λ��");
				return false;
			}else{			
			    refreshForm(url+"&xmdm="+xmdm);
			}
	}else{
		alert("��ȡ����ʧ�ܣ�");
		return false;
	}
		});
	}
}

function getExcel(){
				document.forms[0].target = "_blank";
				refreshForm('xsqgzx.do?method=expRoster');
				document.forms[0].target = "_self";
			}
			
			function modiBzInfo(){
			   if(curr_row==null){
			   	alert('��ѡ����Ҫ�޸ĵļ�¼��');
			   	return false;
			   }
			   var pkValue = curr_row.cells[0].getElementsByTagName('input')[0].value;
			   showTopWin('xsqgzx.do?method=modiXsgwxxRemark&pkValue='+pkValue,400,300);
			}

function dataDel(url){
		var RowsStr="!!";	
		var realTable = document.getElementById('realTable').value;	
		var mes = "ȷ��Ҫ������ѡ��¼��";
		for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	if(document.getElementsByName("pkV")[i].checked){
	    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	}
		}
		//document.forms[0].delPk.value = RowsStr;
		
		if (RowsStr=="!!"){
			alert("��ѡ��Ҫ���������ļ�¼��");
			return false;
		}
		
		if(realTable != null && "gwxxb" == realTable){
			mes = "ɾ����λ��ɾ����λ�µ�����ѧ����Ϣ��ȷ��ɾ����";
		}
		
		if (!confirm(mes)){
			return false;
		}
		
		url += "&pkString=";
		url += RowsStr;
		url += "&page=";
		url += "page";//��ת����λ��ѯҳ��
		refreshForm(url);
}


function print(url, para,paraName){
	var parameter = para.split('-');
	var paraNames = paraName.split('-');
	for(var i=0; i<parameter.length; i++){
		url += "&" + parameter[i] + "=";
		url += document.getElementById(parameter[i]).value;
	}
	for(var i=0 ; i<paraNames.length; i++){
		url += "&" + paraNames[i] + "=";
		url += document.getElementById(paraNames[i]).options[document.getElementById(paraNames[i]).selectedIndex].text;
	}
	window.open(url);
}

//���ʲ���
function workPay(url){
	if(curr_row == null){
		alert('��ѡ��Ҫ���ŵĸ�λ��');
		return false;
	}
	
	var pk = curr_row.getElementsByTagName("input")[0].value;
	var gwmc = curr_row.cells[4].innerText;
	url += "&pk=";
	url += pk;
	url += "&gwmc=";
	url += gwmc;
	showTopWin(url,600,400);
}

//�㽭����ְҵ����ѧԺ��ӡѧ���ڹ���ѧ���˱�
function printKhb(url){
	var xh = document.getElementById('xh').value;
	var xm = document.getElementById('xm').value;
	var gwmc = document.getElementById('gwmc').value;
	var xymc = document.getElementById('xymc').value;
	var bjmc = document.getElementById('bjmc').value;
	var khdj = ""
	var bcbz = document.getElementById('spbcbz').value;
	var gzsj = document.getElementById('ysj').value;
	var gzje = document.getElementById('yje').value;
	var yrdwmc = document.getElementById('yrdwmc').value;
	if($('gzbx')){
		khdj = document.getElementById('gzbx').value;
	}
	
	url += "&xh=";
	url += xh;
	url += "&xm=";
	url += xm;
	url += "&xymc=";
	url += xymc;
	url += "&bjmc=";
	url += bjmc;
	url += "&gwmc=";
	url += gwmc;
	url += "&khdj=";
	url += khdj;
	url += "&bcbz=";
	url += bcbz;
	url += "&gzsj=";
	url += gzsj;
	url += "&gzje=";
	url += gzje;
	url += "&yrdwmc=";
	url += yrdwmc;
	
	window.open(url);
}

//���ʷ���
function fillMonth(url){
	if(curr_row == null){
		alert('����ѡ����Ӧ�ĸ�λ��¼!');
		return false;		
	}
	//var pk = document.forms[0].pk.value;
    var pkValue = curr_row.getElementsByTagName("input")[0].value;
	var gwxzmc = curr_row.cells[6].innerText;
	
	//url += "&pk=";
	//url += pk;
	url += "&pkValue=";
	url += pkValue;
	//url += "&gwxzmc=";
	//url += gwxzmc;
	
	cjff.checkFfsj(pkValue,function(data){
		if(data){
			alert('���ڲ��ڷ��ų���ʱ�䷶Χ֮�ڣ���ʱ���ܷ��ţ�');
			return false;
		}else{
			refreshForm(url);
		}
	});	
}

function printYgzbb(){
	var title = document.getElementById('title').innerText;	
	document.getElementById('printFlag').value='print';
	expAppTab('rsT',title+'<br/><br/><br/>','');
}

function saveYgzbb(){
	var flag = document.getElementById('printFlag').value;
	if(flag == null || flag == ""){
		alert('�����ȴ�ӡ�ſ��Ա���,����δ��ӡ!');
		return false;
	}else{
		document.getElementById('printFlag').value = '';
		refreshForm('cjff.do?method=saveYgzffInfo');
	}
}

//��ʼ��ҳ����ܽ��
function initZje(){
	document.getElementById('zje').value = document.getElementById('zje').innerText;
}


function changeCjje(obj){
	var spbcbz = document.getElementById('spbcbz').value;
	var id = obj.id;	
	var rownum = id.split('@@!!')[1];
	var yje = document.getElementById('cjje' + rownum).value;
	var zje = document.getElementById('zje').value;
	var zgcjje = document.getElementById('zgcjje').value;
	var zggzsj = document.getElementById('zggzsj').value;
	if(isNaN(obj.value)){
		alert('���������֣�');
	}
	if(yje =="" || yje == null){
		yje = "0";
	}
	var je =(parseFloat(spbcbz)*parseFloat(obj.value));
	var sj = parseFloat(obj.value);
	if(parseFloat(sj)>parseFloat(zggzsj)){
		alert("����ʱ�䳬�����Ƶ���߹���ʱ�䣬��������д��");
		document.getElementById("gzsj" + rownum).value="";
		return false;
	}
//	document.getElementById("zje").innerText = parseFloat(zje) - parseFloat(yje) + parseFloat(parseFloat(spbcbz)*parseFloat(obj.value));
//	document.getElementById("zje").value = parseFloat(zje) - parseFloat(yje) + parseFloat(parseFloat(spbcbz)*parseFloat(obj.value));
}

function initNext(){
	var count = document.getElementById('count').value;
	var startPage = document.getElementById('startPage').value;
	var pageSize = document.getElementById('pageSize').value;
	var currentPage = document.getElementById('currentPage').value;
	if(parseFloat(currentPage)*parseFloat(pageSize)<count){
		document.getElementById('nextOne').disabled=false;
	}
	if(parseFloat(currentPage)>1){
		document.getElementById('up').disabled=false;
	}
}

function goNext(){
	refreshForm("cjff.do?method=fillMonthNext");
}

function goUp(){
	refreshForm("cjff.do?method=fillMonthUp");
}

function checkRange(obj,IdNumber){
	var zgcjje = document.getElementById('zgcjje').value;
	var value = obj.value;
	var count = document.getElementById('count').value;
	var zcjje = 0;
	zgcjje = document.getElementById('zgcjje'+IdNumber).value;
	if(parseFloat(value)>parseFloat(zgcjje)){
		alert("���������Ƶ���߽���������д��");
		obj.value = "";
		return false;
	}
	for(var i=0; i<parseFloat(count);i++){
		var cjje = document.getElementById("cjje" + i).value;
		if(cjje == null || cjje == ''){
			cjje = 0;
		}
		zcjje += parseFloat(cjje);
	}
	document.getElementById("zje").innerText = zcjje;
}

function rePut(){	
	var url = "qgzxLogic.do?method=showReTime";
	if(curr_row == null){
		alert('��ѡ��Ҫ���ŵĸ�λ��');
		return false;
	}
	
	var pkValue = curr_row.getElementsByTagName("input")[0].value;
	var pk = "gwdm||gwsbsj";
	var gwmc = curr_row.cells[3].innerText;
	
	url += "&pk=";
	url += pk;
	url += "&pkValue=";
	url += pkValue;
	url += "&gwmc=";
	url += gwmc;
	
	cjff.checkFfsj(pkValue,function(data){
		if(data){
			alert('���ڲ��ڷ��ų���ʱ�䷶Χ֮�ڣ���ʱ���ܷ��ţ�');
			return false;
		}else{
			refreshForm(url);
		}
	});	
	
}

function saveBfgz(){
	var flag = document.getElementById('printFlag').value;
	if(flag == null || flag == ""){
		alert('�����ȴ�ӡ�ſ��Ա���,����δ��ӡ!');
		return false;
	}else{
		document.getElementById('printFlag').value = '';
		refreshForm('cjff.do?method=saveGzbfInfo');
	}
}

function changeBfje(obj){
	var id = obj.id;	
	var rownum = id.split('@@!!')[1];
	var ysj = document.getElementById('yssj' + rownum).value;
	var sj = document.getElementById('gzsj@@!!'+rownum).value;
	var zggzsj = document.getElementById('zggzsj').value;
	
	if(isNaN(obj.value)){
		alert('���������֣�');
	}
	if(parseFloat(sj)>parseFloat(zggzsj)){
		alert('����ʱ�䳬����߹���ʱ�䣬��������д��');
		document.getElementById('gzsj'+rownum).value='';
		return false;
	}
}

function checkBfgzRange(obj){
	var zgcjje = document.getElementById('zgcjje').value;
	var value = obj.value;
	var id = obj.id;
	var number = id.split('@@')[1];
	var ysje = document.getElementById('yscj'+number).value;
	var zcjje = 0;
	var count = document.getElementById('count').value;
	if(ysje == null || ysje == ""){
		ysje = 0;
	}else{
		ysje = parseFloat(ysje);
	}
	
	var zffje = parseFloat(value)+ysje;
	
	if(zffje>parseFloat(zgcjje)){
		alert("���������Ƶ���߽���߻��ɷ��Ž��"+(parseFloat(zgcjje)-ysje)+"Ԫ��");
		obj.value = "";
		obj.focus();
		return false;
	}
	for(var i=0; i<parseFloat(count);i++){
		var cjje = document.getElementById("cjje" +i).value;
		if(cjje == null || cjje == ''){
			cjje = 0;
		}
		zcjje += parseFloat(cjje);
	}
	document.getElementById("zje").innerText = zcjje;
	return true;
}

function goBfgzNext(){
	refreshForm("cjff.do?method=rePayNext");
}

function goBfgzUp(){
	refreshForm("cjff.do?method=rePayUp");
}

function deleteCjff(){
	var url = "cjff.do?method=delCjffInfo";
	var pk = "xh||gwdm||sqsj||nd||yf||fflx";	
	var xxdm = document.getElementById('xxdm').value;	
	var RowsStr="!!";	
	for (i=0; i<document.getElementsByName("pkV").length; i++){
    	if(document.getElementsByName("pkV")[i].checked){
    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
    		if(xxdm=="10491"){//�й����ʴ�ѧ
    			url += "&fflx=";
    			url += document.getElementsByName("pkV")[i].parentNode.parentNode.cells[11].innerText;
    		}
    	}
	}				
	if (RowsStr=="!!"){
		alert("��ѡ��Ҫ���������ļ�¼��");
		return false;
	}	
	
	url += "&pkString=";
	url += pk;
	url += "&pkValue=";
	url += RowsStr;
	if(confirm('��ȷ��ɾ��ѡ�еļ�¼��')){
		refreshForm(url);
	}
}

function gwzjfp(){
	var RowsStr="!!";	
	var url = "qgzxZgdzdx.do?method=showGwfpPage";
	var mes = "ȷ��Ҫ������ѡ��¼��";
	for (i=0; i<document.getElementsByName("pkV").length; i++){
	    if(document.getElementsByName("pkV")[i].checked){
	    	RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    }
	}
				
	if (RowsStr=="!!"){
		alert("��ѡ��Ҫ���������ļ�¼��");
		return false;
	}
	
	for (i=0; i<document.getElementsByName("pkV").length; i++){
	    if(document.getElementsByName("pkV")[i].checked){
	    	 if(document.getElementsByName('sfyg')[i].value=='�и�'){
		    	alert('�Ѿ��и�λ��ѧ�������ٴη����λ����ȷ����ѡ��ļ�¼ȫ����û�и�λ��ѧ����');
		    	return false;		    
	    		}
	    }
	}		
	if (!confirm(mes)){
		return false;
	}
		
	url += "&pkString=";
	url += RowsStr;
	showTopWin(url,500,350);
}


function queryOne() {
	if((curr_row == null) || (curr_row == "")){
		return false;
	}
	var xh = curr_row.getElementsByTagName("input")[0].value;
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showOpenWindow(url, 800, 600);
}

function qgzxAudiBatch(yesNo){
	var pkString = "!!SplitOneSplit!!";
	var yesNo = yesNo;
	var url = "qgzxZgdzdx.do?method=saveQgzxshBatch";
			
	
	for (i=0; i<document.getElementsByName("pk").length; i++){ //�����ַ���
		if(document.getElementsByName("pk")[i].checked){
    		pkString += document.getElementsByName("pkV")[i].value +"!!SplitOneSplit!!";
    	}
	}
	
	if(pkString == "!!SplitOneSplit!!"){
		alert('��ѡ��Ҫ�����ļ�¼��');
		return false;
	}
	
	url += "&pkString=";
	url += pkString;
	url += "&yesNo=";
	url += yesNo;
	
	if (confirm("ȷ��Ҫ����������")) {
		refreshForm(url);				
	}
}

function submitList(){
	var i;
	document.forms[0].mappingItems.value = "";//���mappingItems�е�ֵ
	if (document.forms[0].mappingList.options.length <= 0) {
		alert("�����ֶβ���Ϊ��!");
		return false;
	}
	for (i = 0; i < document.forms[0].mappingList.options.length; i++) {
		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + "!!SplitOne!!";
		document.forms[0].mappingItems.value = document.forms[0].mappingItems.value + document.forms[0].mappingList.options[i].value;
	}
	document.forms[0].mappingItems.value = DWRUtil.byId("mappingItems").value.substr("!!SplitOne!!".length);//���е��ַ���ǰ���"!!SplitOne"
	
	var showConfirmText = "��ȷ��������ѡ���ֶ���";
	var showRunningText;
	
    if (confirm(showConfirmText)) {
    	var Items = document.getElementById("mappingItems").value;
    	var whereSql = document.getElementById("whereSql").value;
		window.open('qgzxZgdzdx.do?method=expData&mappingItems=' + Items + "&whereSql=" + whereSql);
	}
    
	return false;
}

function saveGwsq(url, notnull){
	var ele = notnull.split('-');
	var xh = document.getElementById('xh').value;
	//�����ֶ��ж�
	for(var i=0; i<ele.length; i++){
		if(document.getElementById(ele[i]).value==''){
			alert('�뽫��\*�ŵ���Ŀ��д������');
			return false;
		}	
	}
	//�ֶγ����ж�
	var yhtc = document.getElementById('yhtc').value;
	if(yhtc != null){
	     if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	         alert("�����س����ܴ���250���ַ�");
	         return false;
	      }
	}
	//�ύ
	url += "&xh=";
	url += xh;
	refreshForm(url);	
}

function printGwsq(){
	var url = "qgzxZjcm.do?method=printGwsqb";
	var xh = document.getElementById('xh').value;
	var xmdm = document.getElementById('xmdm').value;
	var lxdh = document.getElementById('lxdh').value;
	var sffcfp = document.getElementById('sffcfp1').checked;
	var jsjsp = document.getElementById('jsjsp').value;
	var yhtc = document.getElementById('yhtc').value;
	var mqqgzxqk = document.getElementById('mqqgzxqk').value;
	
	//�Ƿ���ӷ���	
	if(sffcfp){
		sffcfp = "1";
	}else{
		sffcfp = "0";
	}
	
	url += "&xh=";
	url += xh;
	url += "&xmdm=";
	url += xmdm;
	url += "&lxdh=";
	url += lxdh;
	url += "&sffcfp=";
	url += sffcfp;
	url += "&yhtc=";
	url += yhtc;
	url += "&mqqgzxqk=";
	url += mqqgzxqk;
	url += "&jsjsp=";
	url += jsjsp;
		
	window.open(url);
}

function clickAdd(){
	var number = document.getElementById('number').value;
	var xh = document.createElement("input");	    
	var xm = document.createElement("input");
	var gzsj = document.createElement("input");
	var cjje = document.createElement("input");
	var bz = document.createElement("input");
	var bjdm = document.createElement("input");
	var lxdh =	document.createElement("input");
	
	var tr_length = document.getElementById('tr_length').value;
	var count = document.getElementById('count').value;
	var yrdw = document.getElementById('yrdw').value;
	var gwmc = document.getElementById('gwmc').value;
	var start_length = 0;
	var show_length = 0;
	
	if(tr_length == null || tr_length==""){
		tr_length = 0;
	}
	
	if (count == 0){
		flag = 1;
	}

	if(parseInt(tr_length)==0){
		if(flag == 0){
			start_length = parseInt(count) + parseInt(tr_length);
			show_length = start_length + 1;	
		}else{
			start_length = parseInt(count) + parseInt(tr_length)+1;
			show_length = start_length + parseInt(count);
		}
	}else{
		if(flag == 0){
			start_length = parseInt(tr_length)+1;
			show_length = parseInt(start_length) + 1;	
		}else{
			start_length = parseInt(tr_length)+1;
			show_length = parseInt(start_length) + parseInt(count);	
		}
	}
		    
	xh.id="xh" + start_length;
	xh.name="xh" + start_length;
	xh.value = "";
	xh.style.width="80px";
	
	xm.id = "xm" + start_length;
	xm.name="xm" + start_length;
	xm.value = "";
	xm.style.width="80px";
	xm.disabled = true;
	
	bjdm.id = "bjdm" + start_length;
	bjdm.name="bjdm" + start_length;
	bjdm.value = "";
	bjdm.disabled=true;
	
	lxdh.id = "lxdh" + start_length;
	lxdh.name="lxdh" + start_length;
	lxdh.value = "";
	lxdh.disabled=true;
	
	gzsj.style.width="80px";
	gzsj.name="gzsj" + start_length;
	gzsj.value = "";
	gzsj.id = "gzsj" + start_length;
	
	cjje.style.width="40px";
	cjje.name="cjje" + start_length;
	cjje.value = "";
	cjje.id = "cjje"+start_length;
	
	bjdm.style.width="80px";
	lxdh.style.width="80px";
	bz.style.width="80px";
	bz.name="bz" + start_length;
	
	yrdw = document.getElementById('yrdwmc').value;
	gwmc = document.getElementById('gwdm').value;
	if(show_length <= 25){//���25��
		addRowNum = addRowNum + 1 ;
		document.getElementById('addRowNum').value = addRowNum;
		DWRUtil.addRows('jj',['dd'],[show_length,yrdw,gwmc,bjdm,xh,xm,gzsj,cjje,lxdh,'',bz]);
		$(xh.id).onchange=function(){
			var bjid= "bjdm" + this.id.replace("xh","");
			var xmid= "xm" + this.id.replace("xh","");
			var lxdhid= "lxdh" + this.id.replace("xh","");
			
			cjff.getXsInfo(this.value,function(data){
			if(data!=null){
				$(bjid).value=data[0];
				$(xmid).value=data[1];
				$(lxdhid).value=data[2];
				
			}
		});
		};
		$(gzsj.id).onchange=function(){
			changeBfjeLs(this);
		}
		$(cjje.id).onchange=function(){
			checkBfgzRangeLs(this);
			checkRange(this);
		}
		document.getElementById('tr_length').value = start_length;	
	}
	
	if(number == null || number==''){
		number=0 ;
	}
	document.getElementById('number').value = parseFloat(number) + 1;
}

function changeBfjeLs(obj){
	var spbcbz = document.getElementById('spbcbz').value;
	var id = obj.id;	
	var rownum = id.replace("gzsj","");
	var sj = document.getElementById('gzsj' + rownum).value;
	var zggzsj = document.getElementById('zggzsj').value;
	if(isNaN(obj.value)){
		alert('���������֣�');
	}
	if(parseFloat(sj) > parseFloat(zggzsj)){
		alert('����ʱ�䳬����߹���ʱ�䣬��������д��');
		document.getElementById('gzsj' + rownum).value = '';
		return false;
	}
}

function checkBfgzRangeLs(obj){
	var zgcjje = document.getElementById('zgcjje').value;
	var start_length = document.getElementById('start_length').value;
	var value = obj.value;
	var id = obj.id;
	var number = id.replace("cjje","");
	var ysje = 0;	
	var zffje = parseFloat(value)+ysje;
	var count = document.getElementById('count').value;
	var number = document.getElementById('number').value;
	var zje = 0;
	
	if(zffje>parseFloat(zgcjje)){
		alert("���������Ƶ���߽���߻��ɷ��Ž��"+(parseFloat(zgcjje)-ysje)+"Ԫ��");
		obj.value = "";
		obj.focus();
		return false;
	}
	for(var i=0; i<parseFloat(count); i++){
		var je = 0;
		if(document.getElementById("cjje" + i)){
			je = document.getElementById("cjje" + i).value;
		}
		if(je == null || je == ''){
			je = 0;
		}
		zje += parseFloat(je);
	}
	if(number == null || number==''){
		number = 0;
	}
	if(start_length == null || start_length==''){
		start_length = 0;
	}
	for(var i=0; i<number; i++){
		var je = 0;
		if(document.getElementById("cjje" + (i + parseFloat(count)))){
			je = document.getElementById("cjje" + (i + parseFloat(count))).value;
		}
		if(je == null || je == ''){
			je = 0;
		}
		zje +=  parseFloat(je);
	}
	document.getElementById("zje").innerText = zje;
	
	return true;
}

function clickRemove(){
	var count = document.getElementById('tr_length').value;
	var number = document.getElementById('number').value;
	if(document.getElementById('jj').rows.length != 0){
		addRowNum = addRowNum - 1;
		document.getElementById('addRowNum').value = addRowNum;
		document.getElementById('jj').deleteRow(document.getElementById('jj').rows.length-1);
		document.getElementById('tr_length').value = parseInt(count)-1;		
	}
	
	if(number == null || number==''){
		number = 0;
	}
	document.getElementById('number').value = number - 1;
}

function goLsgzNext(){
	var page = document.getElementById('currentPage').value;
	document.getElementById('currentPage').value = parseInt(page) + 1;
	refreshForm("cjff.do?method=lsgzff");
}

function goLsgzUp(){
	var page = document.getElementById('currentPage').value;
	document.getElementById('currentPage').value = parseInt(page) - 1;
	refreshForm("cjff.do?method=lsgzff");
}

function saveLsgz(){
	var flag = document.getElementById('printFlag').value;
	if(flag == null || flag == ""){
		alert('�����ȴ�ӡ�ſ��Ա���,����δ��ӡ!');
		return false;
	}else{
		showTips('���������У���ȴ�......');
		document.getElementById('printFlag').value = '';
		refreshForm('cjff.do?method=saveGzlsInfo');
	}
}

//ѧ����ѯ��λ������Ϣ
function stuViewGwsqxx(url){
	var pkValue = curr_row.cells[0].getElementsByTagName('input')[0].value;
	
	url += "&pkValue=" + pkValue;
	showTopWin(url);
}

function loadGwByYrdw(yrdwdm,gwId,gwxzId){
	var gwxz = "";
	if(gwxzId != "" && document.getElementById(gwxzId)){
		gwxz = document.getElementById(gwxzId).value;
	}
	cqkjFunc.getGwmcByYrdw(yrdwdm,gwxz,function(data){
		DWRUtil.removeAllOptions(gwId);			
		DWRUtil.addOptions(gwId,data,"gwdm","gwdm");
	});
}

function showSelectGwDiv(url){ 
	var dd_html = "";
	dd_html += "<table width='250' class='tbstyle'>";
	dd_html += "	<tr>";
	dd_html += "		<td align='right'>��λ��</td>";
	dd_html += "		<td align='center'>"
	dd_html += "			<select id='selGw' name='selGw' onchange='initZdgwmc()'></select>";
	dd_html += "		</td>";
	dd_html += "	</tr>";
	dd_html += "	<tr>";
	dd_html += "		<td align='right'>ָ����λ��</td>";
	dd_html += "		<td>"
	dd_html += "			<textarea id='txtGw' name='txtGw' readonly='true' rows=3></textarea>";
	dd_html += "			<input type='hidden' id='hidGw' name='hidGw'/>"
	dd_html += "		</td>";
	dd_html += "	</tr>";
	dd_html += "	<tr>";
	dd_html += "		<td colspan='2'>";
	dd_html += "			<button class='button2' onclick='auditing(\""+url+"\")'>ȷ��</button>&nbsp;&nbsp;<button class='button2' onclick='closeAdd()'>ȡ��</button>";
	dd_html += "		</td>";
	dd_html += "	</tr>";
	dd_html += "</table>";
	//�����б�����
	var gwxzdm = $("gwxzdm").value;
	var xxdm = $("xxdm").value;
	var xydm = $("xydm").value;
	var userType = $("userType").value;
	//���ݴ�ѧ
	if("11078" == xxdm && "xy" == userType){
		qgzxZgdzdxFunc.getDWRGwGzdxList(gwxzdm,xydm,function(data){
			DWRUtil.removeAllOptions("selGw");			
			DWRUtil.addOptions("selGw",[{dm:'',mc:''}],"dm","mc");
			DWRUtil.addOptions("selGw",data,"gwdmgwsbsj","gwdm");		
		});
	}else{
		qgzxZgdzdxFunc.getDWRGwList(gwxzdm,function(data){
			DWRUtil.removeAllOptions("selGw");			
			DWRUtil.addOptions("selGw",[{dm:'',mc:''}],"dm","mc");
			DWRUtil.addOptions("selGw",data,"gwdmgwsbsj","gwdm");		
		});
	}
	showDiv(dd_html, 300, 120);
}

function initZdgwmc(){
	var value = val("selGw");
	var text = selText("selGw");
	var xmdm = val("hidGw");
	var zdgw = val("txtGw");
	if(value != null && value != ""){
		if(checkArrayEleRepeat(xmdm+"!!" + value,"!!")){
			alert('�ø�λ�Ѿ�ָ����');
			return false;
		}else{
			if(zdgw == null || zdgw == ""){
				setVal("txtGw", text);
			}else{
				setVal('txtGw',zdgw + "," + text);
			}
			
			setVal('hidGw',xmdm + "!!" + value);
		}
	}
}

//���ؼƳ��׼
function loadJcbz(value){
	var jcbz = "";
	cqkjFunc.loadJcbz(value,function(data){
		if(data == null || data == 'null' || data == ""){
			jcbz = "";
		}else{
			jcbz = data;
		}
		setVal('jybcbz',jcbz);
	});
}

//�������˵�λ�µĸ�λ
function loadYrdwGw(yrdwid,gwmcid,gwxzId){
	var yrdwdm = "";
	var gwxzdm = "";
	var userName = "";
	if(ele(yrdwid)){
		yrdwdm = val(yrdwid);
	}
	if(ele(gwxzId)){
		gwxzdm = val(gwxzId);
	}
	if(ele("userName")){
		userName = val("userName");
	}
	cqkjFunc.getYrdwGwList(userName,yrdwdm,gwxzdm,false,function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}

//���ر��˷����ĸ�λ
function loadBrfbGw(gwmcid,xnId,ndId,xqId){
	var gwfbr = "";
	if(ele("gwfbr")){
		gwfbr = ele("gwfbr").value;
	}
	cqkjFunc.getGwmcxxList({xn:val(xnId),nd:val(ndId),xq:val(xqId),userName:val('userName'),gwfbr:gwfbr,shjgFlag:true},function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}

function loadGwmcxx(gwmcid,xnId,ndId,xqId){
	var xxdm = '';
	var text = "";
	var gwxz = "";
	var yrdwdm = "";
	var xn = "";
	var nd = "";
	var xq= "";
	var shFlag = "";
	if ( document.getElementById('xxdm')) {
		xxdm=document.getElementById('xxdm').value
	}
	
	if(ele('gwxz')){
		gwxz = val('gwxz');
	}
	if(ele('gwxzdm')){
		gwxz = val('gwxzdm');
	}
	if(ele('yrdwdm')){
		yrdwdm = val('yrdwdm');
	}
	if(ele('yrdw')){
		yrdwdm = val('yrdw');
	}
	if(ele('xn')){
		xn = val('xn');
	}
	if(ele('nd')){
		nd = val('nd');
	}
	if(ele('xq')){
		xq = val('xq');
	}
	if(ele("shFlag")){
		shFlag = "false";
	}
	if ('11078'== xxdm){
		text = 'yes';
	} else {
		text = 'no';
	}
	
	cqkjFunc.getGwmcxxList({xn:xn,nd:nd,xq:xq,gwxzdm:gwxz,yrdwdm:yrdwdm,userName:val('userName'),shFlag:shFlag},text,function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}


function loadFgwfbrGwmcxx(gwmcid,xnId,ndId,xqId){
	var gwxz = "";
	var yrdwdm = "";
	var xn = "";
	var nd = "";
	var xq= "";
	var shFlag = "";
	if(ele('gwxz')){
		gwxz = val('gwxz');
	}
	if(ele('gwxzdm')){
		gwxz = val('gwxzdm');
	}
	if(ele('yrdwdm')){
		yrdwdm = val('yrdwdm');
	}
	if(ele('yrdw')){
		yrdwdm = val('yrdw');
	}
	if(ele('xn')){
		xn = val('xn');
	}
	if(ele('nd')){
		nd = val('nd');
	}
	if(ele('xq')){
		xq = val('xq');
	}
	if(ele("shFlag")){
		shFlag = "false";
	}
	cqkjFunc.getFgwfbrGwmcxxList({xn:xn,nd:nd,xq:xq,gwxzdm:gwxz,yrdwdm:yrdwdm,userName:val('userName'),gwfbr:val('userName'),shFlag:shFlag},"yes",function(data){
		DWRUtil.removeAllOptions(gwmcid);			
		DWRUtil.addOptions(gwmcid,[{gwmc:''}],"gwmc","gwmc");
		DWRUtil.addOptions(gwmcid,data,"gwmc","gwmc");		
	});
}

/**
 * �жϸ�λ����ʱ�������Ƿ����
 * */
function checkGwgzsjTime(){
	if(ele('gzkssj') && ele('gzjssj')){
		if((val('gzkssj') != '' && val('gzjssj') != '') 
				&& (val('gzkssj')>val('gzjssj'))){
			alert('������ʼ���ڲ������ڹ����������ڣ�');
			return false;
		}
	}
	return true;
}

/**
 * �滻�����ַ�
 * */
function replaceStr(obj){
	if(obj){
		var value = obj.value;
		ele(obj.id).value = value.replace('+','��').replace('#','��')
		                    .replace('%','��').replace('~','��').replace('!','��')
		                    .replace('$','��').replace('^','��').replace('^','��')
		                    .replace('&','��').replace('*','��');
		//ele(obj.id).value = value.replace('','');		
	}
}

/**
 * �жϸ�λ����ʱ�������Ƿ����
 * */
function checkFfsjTime(){
	if(ele('ffsjks') && ele('ffsjjs')){
		if((val('ffsjks') != '' && val('ffsjjs') != '') 
				&& (val('ffsjks').replace('-','')>val('ffsjjs').replace('-',''))){
			alert('���ſ�ʼʱ�䲻�����ڽ���ʱ�䣡');
			return false;
		}
	}
	return true;
}


function printGzhzb(){
	if(val('xn') == ''){
		alert('��ѡ��ѧ�꣡');
		return false;
	}
	if(val('xq') == ''){
		alert('��ѡ��ѧ�ڣ�');
		return false;
	}
	wjcfDataExport('qgzxcjff.do?method=printCjffhzb');
}

function printYrqkb(){
	wjcfDataExport('qgzxGwgl.do?method=printYrdwyrqkb');
}