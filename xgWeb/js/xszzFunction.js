function modiOneConf(){
	if(chkCurrRow()){
		if(curr_row.getElementsByTagName("input") == null){
			alert("ϵͳ����,δȡ�����ݣ�\n��ˢ��ҳ������ԣ�");
		}else{
			var bmdm = curr_row.getElementsByTagName("input")[0].value;
			var src = "/xgxt/modiConf.do?doType=one&bmdm=" + bmdm;
			var w = 500;
			var h = 370;
			showTopWin(src,w,h,'a');
		}
	}
}
function modiAConf(){
	if(chkCurrRow()){
		if(curr_row.getElementsByTagName("input") === null){
			alert("ϵͳ����,δȡ�����ݣ�\n��ˢ��ҳ������ԣ�");
		}else{
			var bmdm = curr_row.getElementsByTagName("input")[0].value;
			var src = "/xgxt/modiZzConf.do?doType=one&bmdm=" + bmdm;
			var w = 500;
			var h = 370;
			showTopWin(src,w,h,'a');
		}
	}
}
function showXyConfInfo(){
	var oldRsxz = $("rsTable");
	var totRsxz = 0;
	var i = 0;
	for(i = 2;i < oldRsxz.rows.length - 1;i++){
		totRsxz += parseFloat(oldRsxz.rows[i].cells[1].innerText);
	}
	var maxRsxz = parseFloat(oldRsxz.rows[i].cells[1].innerText);
	var infoMsg = "<div align='left'><br>";
	infoMsg += "<font color='red'>�˱�����һ��ΪѧУ���õ��޶�����";
	if(!(window.dialogArguments) || (P$("userType") && P$("userType").value == "xy")){
		infoMsg += "�������޸Ĳ��ó����޶���Χ";
	}
	infoMsg += "��</font><br>";
	infoMsg += "<font color='blue'>��ǰ������Ϣ������������ָ��";
	infoMsg += "<font color='red'>" + maxRsxz + "</font>��";
	infoMsg += "�Ѿ�����";
	infoMsg += "<font color='red'>" + totRsxz + "</font>��";
	infoMsg += "����ָ��";
	infoMsg += "<font color='red'>" + (maxRsxz - totRsxz) + "</font>��</font></div>";
	document.write(infoMsg);
}
function saveConf(){
	/**�����������*/
	if($("userType").value == "xy"){
		var sType = $("bmdm").value;//�ж��Ƿ�Ϊ�������õ�����
		var xyrs = parseFloat($("xyrs").value);//��ȡѧԺ����
		var rsxz = parseFloat($("rsxz").value);//��ȡ��������
		var oldRsxz = P$("rsTable");//��ȡ��ҳ���
		var totRsxz = 0;
		var i = 0;
		for(i = 2;i < oldRsxz.rows.length - 1;i++){
			totRsxz += parseFloat(oldRsxz.rows[i].cells[1].innerText);
		}//���ܸ�ҳ����ӵ�λ���������ơ��ܺ�
		var maxRsxz = parseFloat(oldRsxz.rows[i].cells[1].innerText);//��ȡ��������
		var blRs = xyrs*rsxz/100;
		if(sType == "all" && blRs > maxRsxz){
			alert("�޸ĺ����������" + blRs + "�����������ƣ�" + maxRsxz + "�����������룡");
			$("rsxz").select();
			$("rsxz").focus();
			return false;
		}else if(sType != "all"){
			totRsxz -= parseFloat(window.dialogArguments.curr_row.cells[1].innerText);
			totRsxz += rsxz;
			if(totRsxz > maxRsxz){
				alert("�޸ĺ����������" + totRsxz + "�����������ƣ�" + maxRsxz + "�����������룡");
				$("rsxz").select();
				$("rsxz").focus();
				return false;
			}
		}
	}
	/**���ʱ������*/
	unionDateTime("kssqsja");
	unionDateTime("jssqsja");
	unionDateTime("kssqsjb");
	unionDateTime("jssqsjb");
	unionDateTime("kssqsjc");
	unionDateTime("jssqsjc");
	unionDateTime("kssqsjd");
	unionDateTime("jssqsjd");
	unionDateTime("kssqsje");
	unionDateTime("jssqsje");
	unionDateTime("kssqsjf");
	unionDateTime("jssqsjf");
	unionDateTime("kssqsjg");
	unionDateTime("jssqsjg");
	unionDateTime("kssqsjh");
	unionDateTime("jssqsjh");
	var newKsA = $("kssqsja").value;
	var newJsA = $("jssqsja").value;
	var newKsB = $("kssqsjb").value;
	var newJsB = $("jssqsjb").value;
	var newKsC = $("kssqsjc").value;
	var newJsC = $("jssqsjc").value;
	var newKsD = $("kssqsjd").value;
	var newJsD = $("jssqsjd").value;
	var newKsE = $("kssqsje").value;
	var newJsE = $("jssqsje").value;
	var newKsF = $("kssqsjf").value;
	var newJsF = $("jssqsjf").value;
	var newKsG = $("kssqsjg").value;
	var newJsG = $("jssqsjg").value;
	var newKsH = $("kssqsjh").value;
	var newJsH = $("jssqsjh").value;
	if($("userType").value == "xy"){
		var maxKsA = P$("maxKsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsA = P$("maxJsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsB = P$("maxKsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsB = P$("maxJsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsC = P$("maxKsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsC = P$("maxJsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsD = P$("maxKsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsD = P$("maxJsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsE = P$("maxKsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsE = P$("maxJsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsF = P$("maxKsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsF = P$("maxJsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsG = P$("maxKsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsG = P$("maxJsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsH = P$("maxKsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsH = P$("maxJsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		if(newKsA < maxKsA){
			alert("�������õġ����������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsB < maxKsB){
			alert("�������õġ�������ѧ�������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsC < maxKsC){
			alert("�������õġ����ҽ�ѧ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsD < maxKsD){
			alert("�������õġ�������ѧ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsE < maxKsE){
			alert("�������õġ�ʡ������ѧ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsF < maxKsF){
			alert("�������õġ�ʡ������ѧ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsG < maxKsG){
			alert("�������õġ���ѧ���������ѧ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsH < maxKsH){
			alert("�������õġ�У�ڴ�ѧ���������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsA > maxJsA){
			alert("�������õġ��������������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsB > maxJsB){
			alert("�������õġ�������ѧ�����������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsC > maxJsC){
			alert("�������õġ����ҽ�ѧ���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsD > maxJsD){
			alert("�������õġ�������ѧ���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsE > maxJsE){
			alert("�������õġ�ʡ������ѧ���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsF > maxJsF){
			alert("�������õġ�ʡ������ѧ���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsG > maxJsG){
			alert("�������õġ���ѧ���������ѧ���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsH > maxJsH){
			alert("�������õġ�У�ڴ�ѧ�������������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
	}
	if(newKsA > newJsA){
		alert("���������뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsB > newJsB){
		alert("������ѧ�������뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsC > newJsC){
		alert("���ҽ�ѧ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsD > newJsD){
		alert("������ѧ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsE > newJsE){
		alert("ʡ������ѧ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsF > newJsF){
		alert("ʡ������ѧ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsG > newJsG){
		alert("��ѧ���������ѧ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsH > newJsH){
		alert("У�ڴ�ѧ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	refreshForm("/xgxt/modiConf.do?act=save");
}
function saveZzConf(){
	/**�����������*/
	if($("userType").value == "xy"){
		var sType = $("bmdm").value;//�ж��Ƿ�Ϊ�������õ�����
		var xyrs = parseFloat($("xyrs").value);//��ȡѧԺ����
		var rsxz = parseFloat($("rsxz").value);//��ȡ��������
		var oldRsxz = P$("rsTable");//��ȡ��ҳ���
		var totRsxz = 0;
		var i = 0;
		for(i = 2;i < oldRsxz.rows.length - 1;i++){
			totRsxz += parseFloat(oldRsxz.rows[i].cells[1].innerText);
		}//���ܸ�ҳ����ӵ�λ���������ơ��ܺ�
		var maxRsxz = parseFloat(oldRsxz.rows[i].cells[1].innerText);//��ȡ��������
		var blRs = xyrs*rsxz/100;
		if(sType == "all" && blRs > maxRsxz){
			alert("�޸ĺ����������" + blRs + "�����������ƣ�" + maxRsxz + "�����������룡");
			$("rsxz").select();
			$("rsxz").focus();
			return false;
		}else if(sType != "all"){
			totRsxz -= parseFloat(window.dialogArguments.curr_row.cells[1].innerText);
			totRsxz += rsxz;
			if(totRsxz > maxRsxz){
				alert("�޸ĺ����������" + totRsxz + "�����������ƣ�" + maxRsxz + "�����������룡");
				$("rsxz").select();
				$("rsxz").focus();
				return false;
			}
		}
	}
	/**���ʱ������*/
	unionDateTime("kssqsja");
	unionDateTime("jssqsja");
	unionDateTime("kssqsjb");
	unionDateTime("jssqsjb");
	unionDateTime("kssqsjc");
	unionDateTime("jssqsjc");
	unionDateTime("kssqsjd");
	unionDateTime("jssqsjd");
	unionDateTime("kssqsje");
	unionDateTime("jssqsje");
	unionDateTime("kssqsjf");
	unionDateTime("jssqsjf");
	unionDateTime("kssqsjg");
	unionDateTime("jssqsjg");
	unionDateTime("kssqsjh");
	unionDateTime("jssqsjh");
	var newKsA = $("kssqsja").value;
	var newJsA = $("jssqsja").value;
	var newKsB = $("kssqsjb").value;
	var newJsB = $("jssqsjb").value;
	var newKsC = $("kssqsjc").value;
	var newJsC = $("jssqsjc").value;
	var newKsD = $("kssqsjd").value;
	var newJsD = $("jssqsjd").value;
	var newKsE = $("kssqsje").value;
	var newJsE = $("jssqsje").value;
	var newKsF = $("kssqsjf").value;
	var newJsF = $("jssqsjf").value;
	var newKsG = $("kssqsjg").value;
	var newJsG = $("jssqsjg").value;
	var newKsH = $("kssqsjh").value;
	var newJsH = $("jssqsjh").value;
	if($("userType").value == "xy"){
		var maxKsA = P$("maxKsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsA = P$("maxJsA").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsB = P$("maxKsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsB = P$("maxJsB").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsC = P$("maxKsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsC = P$("maxJsC").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsD = P$("maxKsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsD = P$("maxJsD").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsE = P$("maxKsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsE = P$("maxJsE").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsF = P$("maxKsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsF = P$("maxJsF").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsG = P$("maxKsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsG = P$("maxJsG").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxKsH = P$("maxKsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		var maxJsH = P$("maxJsH").innerHTML.replace(/(-|:|<br>)/gi,"");
		if(newKsA < maxKsA){
			alert("�������õġ���ƽ����������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsB < maxKsB){
			alert("�������õġ�ѧ�Ѳ������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsC < maxKsC){
			alert("�������õġ����Ѳ������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsD < maxKsD){
			alert("�������õġ�������ѧ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsE < maxKsE){
			alert("�������õġ���Ԫ������ѧ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsF < maxKsF){
			alert("�������õġ���ɫͨ�����뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsG < maxKsG){
			alert("�������õġ�ѧ��ѧ�Ѽ������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newKsH < maxKsH){
			alert("�������õġ��ذ��������뿪ʼʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsA > maxJsA){
			alert("�������õġ���ƽ��������������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsB > maxJsB){
			alert("�������õġ�ѧ�Ѳ����������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsC > maxJsC){
			alert("�������õġ����Ѳ����������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsD > maxJsD){
			alert("�������õġ����貹���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsE > maxJsE){
			alert("�������õġ���Ԫ������ѧ���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsF > maxJsF){
			alert("�������õġ���ɫͨ���������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsG > maxJsG){
			alert("�������õġ�ѧ��ѧ�Ѽ����������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
		if(newJsH > maxJsH){
			alert("�������õġ��ذ������������ʱ�䡱����ѧУ�涨��ʱ�䣬���������ã�");
			return false;
		}
	}
	if(newKsA > newJsA){
		alert("��ƽ����������뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsB > newJsB){
		alert("ѧ�Ѳ������뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsC > newJsC){
		alert("���Ѳ������뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsD > newJsD){
		alert("���貹�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsE > newJsE){
		alert("��Ԫ������ѧ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsF > newJsF){
		alert("��ɫͨ�����뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsG > newJsG){
		alert("ѧ��ѧ�Ѽ������뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	if(newKsH > newJsH){
		alert("�ذ��������뿪ʼʱ�����ڽ���ʱ�䣬���������ã�");
		return false;
	}
	refreshForm("/xgxt/modiZzConf.do?act=save");
}
function chkModiType(){
	var sType = $("bmdm").value;
	var sXyrs = $("xyrs").value;
	var tmpNum = $("rsTable").rows[2].cells[1].innerText;
	if(sType == "all"){
		$("rsTable").rows[2].cells[0].innerText = "�������������ƣ�������:";
		$("rsTable").rows[2].cells[1].innerHTML = "<input onblur=\"numFormatChk(this,0,100)\" type=\"text\" name=\"rsxz\" id=\"rsxz1\" value=\"" +tmpNum + "\" />%";
	}else{
		$("rsTable").rows[2].cells[0].innerText = "�������������ƣ���������:";
		$("rsTable").rows[2].cells[1].innerHTML = "<input onblur=\"numFormatChk(this,0," + sXyrs + ")\" type=\"text\" name=\"rsxz\" id=\"rsxz1\" value=\"" +tmpNum + "\" onkeyup=\"value=value.replace(/[^\\d]/g,'')\" onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))\" />��";
	}
}
function viewXyConf(){
	var src = "/xgxt/assisConf.do?xyToView=";
	if(chkCurrRow()){
		src += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(src,760,400,1);
	}
}
function viewXyConfig(){
	var src = "/xgxt/zzConfig.do?xyToView=";
	if(chkCurrRow()){
		src += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(src,760,400,1);
	}
}

function changePage(defaultId){//�л�ҳ��
	var title = defaultId.id.substr(0,defaultId.id.length-1);
	var titleName,anotherName;
	if (title == "gjjxj"){
		titleName = "gjjxj";
		document.getElementById("titName").value = "gjjxj";				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "gjzxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	} else if (title == "gjzxj") {
		titleName = "gjzxj";
		document.getElementById("titName").value = "gjzxj";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "gjjxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	}else if (title == "bzs"){
		titleName = "bzs";
		document.getElementById("titName").value = "bzs";				
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "yjs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	} else if (title == "yjs") {
	    document.forms[0].action = url;
	    document.forms[0].submit();
		titleName = "yjs";
		document.getElementById("titName").value = "yjs";		
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "bzs";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
	}else if (title == "szfjxj"){
		titleName = "szfjxj";
		document.getElementById("titName").value = "szfjxj";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "szfzxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	} else if (title == "szfzxj"){
		titleName = "szfzxj";
		document.getElementById("titName").value = "szfzxj";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "szfjxj";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	}else if (title == "zxdksq"){
		titleName = "zxdksq";
		document.getElementById("titName").value = "zxdksq";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "txsq";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	} else if (title == "txsq"){
		titleName = "txsq";
		document.getElementById("titName").value = "txsq";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "zxdksq";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		//document.forms[0].action = "";
		//document.forms[0].submit();
	} else if (title == "xscfb"){
		titleName = "xscfb";
		document.getElementById("titName").value = "xscfb";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "xsjlb";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		document.forms[0].action = "/xgxt/gydykp.do";
		document.forms[0].submit();
	} else {
		titleName = "xsjlb";
		document.getElementById("titName").value = "xsjlb";
		document.getElementById(titleName+"l").className = "xxk_on_l";
		document.getElementById(titleName+"m").className = "xxk_on_m";
		document.getElementById(titleName+"r").className = "xxk_on_r";
		anotherName = "xscfb";
		document.getElementById(anotherName+"l").className = "xxk_off_l";
		document.getElementById(anotherName+"m").className = "xxk_off_m";
		document.getElementById(anotherName+"r").className = "xxk_off_r";
		document.forms[0].action = "/xgxt/gydykp.do";
		document.forms[0].submit();
	}
}

function xssqgjjxjzxj(kind){
	var xh = document.getElementById("xh").value;
	if ((xh != "")){	
	if (kind == "txsq"){
		document.forms[0].action = "/xgxt/txsq.do?jxjlbType=txsq&doType=add&titName=" + kind;
		document.forms[0].submit();
		}
 	}else { 
 		alert("ѧ�Ų���Ϊ�գ�");
 		return false;
 	}
 }
 	
function toPrintOut(titName){//�����Ӧ�Ĵ�ӡҳ��
	if(titName == "shszfzxjxjsq" ){
		document.forms[0].action = "/xgxt/shszfzxjxjsqb.do";
		document.forms[0].submit();
	} else if(titName == "txsq" ){
		document.forms[0].action = "/xgxt/txsqb.do";
		document.forms[0].submit();
	} else if(titName == "hkxy" ){
		document.forms[0].action = "/xgxt/hkxysqb.do";
		document.forms[0].submit();
	} else if(titName == "zlqrs" ){
		document.forms[0].action = "/xgxt/zlqrs.do";
		document.forms[0].submit();
	}
}

function knbzChange(Obj){
	var url = "/xgxt/knbz.do?bzlb=";
	if(Obj.id == "lsknbz"){
		Obj.checked = true;
		url += "lsknbz";
	} else {
		Obj.checked = true;
		url += "zxbz";
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function knbzChange2(obj){
	var url = "/xgxt/knbz.do?bzlb=";
	if(obj.id == "a"){
		document.getElementById("lsknbz").checked = true;
		url += "lsknbz";
	} else {
		document.getElementById("zxbz").checked = true;
		url += "zxbz";
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

function loadPage(){//����/ʡ����ҳǩҳ�����
	var tab = document.getElementById("titName").value;
	document.getElementById(tab+"l").className = "xxk_on_l";
	document.getElementById(tab+"m").className = "xxk_on_m";
	document.getElementById(tab+"r").className = "xxk_on_r";
}
function knbzLoadPage(){//���Ѳ���ҳ�����
	var bzlb = document.getElementById("bzlb").value;
	document.getElementById(bzlb).checked = true;
	if(bzlb == "lsknbz"){
		//alert(document.forms[0].zxbzdm);
		document.forms[0].zxbzdm.disabled = true;
	}
}
function changePage1(){
	document.forms[0].action = "assis_result_t.do";
	document.forms[0].submit();
}
function printThePage(){
	var newwin = window.open(location.href,"_blank","");
}
function checkInputData(obj){
	obj.value = obj.value.replace(/[^\d]/g,'');
}