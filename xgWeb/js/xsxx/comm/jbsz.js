/** ===============================�����==================================*/

//���õ�����������ֵ
function setPlszHiddenValue(lx,value){
	var id = "sz_"+lx+"_value";
	$(id).value = value;
}
		
//��ʾ���Ӳ�
function showZjDiv(){
	viewTempDiv("������ʾ��","zjDiv",350,200);
}

//��ʾ���в���Div
function showSzDiv(){
	var cur_bgc = "#ffdead";
	var num = $("xsqNr").getElementsByTagName('tr').length;
	var flag = true;
	
	for(var i=0;i<num;i++){
		var trObj = $("xsqNr").getElementsByTagName('tr')[i];
		//ѡ����
		if(trObj.style.backgroundColor == cur_bgc){
			var div_id = trObj.id.replace("tr","div");
			$("checked_tr").value = div_id;	
			flag = false;		
			break;
		}
	}
	
	if(flag){
		alert("��ѡ����Ҫ��������\n(ע����Ƭ�����в��ɲ���)");
		return false;
	}
	
	var xsq = div_id.split("_")[0];//��ʾ��
	var sch = div_id.split("_")[1];//������
	
	var szh = "szhs"+xsq.replace("xsq","");
	
	//����ɾ�������һ��
	if(sch!=$(szh).value){
		$("last_tr").value= "no";
	}else{
		$("last_tr").value= "yes";
	}
	
	viewTempDiv("���в���","czDiv",300,100);
	
	//�Ƿ������
	var last_tr = $("last_tr").value;
	
	if(last_tr == "yes"){
		$("czDiv_zjh").style.display = "";
		$("czDiv_sch").style.display = "";
	}else{
		$("czDiv_zjh").style.display = "none";
		$("czDiv_sch").style.display = "none";
	}
}
/** ===============================����� end==================================*/

/** ===============================���в���==================================*/

//�ϲ���ʾ��Tr
function hbXsqTr(){
	
	var div_id = $("checked_tr").value;
	var xsq = div_id.split("_")[0];//��ʾ��
	var xsqdm = xsq.replace("xsq","");//��ʾ������
	var hs = div_id.split("_")[1];//����

	var p_left_id = xsq+"_"+hs+"_left_p";
	var p_right_id = xsq+"_"+hs+"_right_p";
	var text_left_id = xsq+"_"+hs+"_left_text";
	var text_right_id = xsq+"_"+hs+"_right_text";
	
	//�˻��������ֶ�
	returnDfpZd();
	
	if($(div_id)){
		var divHtml = "<table width=\"100%\">";
			divHtml += "<tr>";
			divHtml += "<td width=\"20%\">";
			divHtml += "<p id=\""+xsq+"_"+hs+"_left_p\"></p>"
			divHtml += "<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\""+xsq+"_"+hs+"_left_text\" value=\"\"/>";
			divHtml += "<input type=\"hidden\" name=\"hbh_szxsq\" value=\""+xsqdm+"\"/>";
			divHtml += "<input type=\"hidden\" name=\"hbh\" value=\""+hs+"\"/>";
			divHtml += "</td>";
			divHtml += "<td colspan=\"3\">";
			divHtml += "<button onclick=\"clickXsqBtn(this)\" id=\""+xsq+"_"+hs+"_left\">Ӧ��</button>";
			divHtml += "</td>";
			divHtml += "</tr>";
			divHtml += "</table>";
					
		$(div_id).innerHTML = divHtml;
	}
	
	hiddenMessage(true,true);
}

//ȡ����ʾ��Tr
function qxXsqTr(){
	
	var div_id = $("checked_tr").value;
	var xsq = div_id.split("_")[0];//��ʾ��
	var xsqdm = xsq.replace("xsq","");//��ʾ������
	var hs = div_id.split("_")[1];//����
	
	//�˻��������ֶ�
	returnDfpZd();
	
	if($(div_id)){
		var divHtml = "<table width=\"100%\">";
			divHtml += "<tr>";
			divHtml += "<td width=\"20%\">";
			divHtml += "<p id=\""+xsq+"_"+hs+"_left_p\"></p>"
			divHtml += "<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\""+xsq+"_"+hs+"_left_text\" value=\"\"/>";
			divHtml += "</td>";
			divHtml += "<td width=\"30%\">";
			divHtml += "<button onclick=\"clickXsqBtn(this)\" id=\""+xsq+"_"+hs+"_left\">Ӧ��</button>";
			divHtml += "</td>";
			divHtml += "<td width=\"20%\">";
			divHtml += "<p id=\""+xsq+"_"+hs+"_right_p\"></p>";
			divHtml += "<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\""+xsq+"_"+hs+"_right_text\" value=\"\"/>";
			divHtml += "</td>";
			divHtml += "<td width=\"30%\">";
			divHtml += "<button onclick=\"clickXsqBtn(this)\" id=\""+xsq+"_"+hs+"_right\">Ӧ��</button>";
			divHtml += "</td>";
			divHtml += "</tr>";
			divHtml += "</table>";
					
		$(div_id).innerHTML = divHtml;
	}
	
	hiddenMessage(true,true);
}

//ɾ����ʾ��Tr
function delXsqTr(){
	
	var div_id = $("checked_tr").value;
	
	//�˻��������ֶ�
	returnDfpZd();
	
	var xsq = div_id.split("_")[0];//��ʾ��
	var sch = div_id.split("_")[1];//ɾ����
	
	var szh = "szhs"+xsq.replace("xsq","");
	var syhs = parseInt($(szh).value) - 1;//ʣ������
	
	//����ɾ�������һ��
	if(sch!=$(szh).value){
		alert("ֻ�ܹ�ɾ����ʾ�������һ�У���ȷ�ϣ�");
		return false;
	}
	
	//��ʣһ�е�ʱ�򲻿�ɾ��
	if(syhs !=0){
		$(szh).value = syhs;
	}else{
		alert("�Ѿ��Ǹ���ʾ�������һ�У�����ɾ����\nע�������ɾ��������ʾ���Ļ����빴\n    ѡ����ʾ������ִ��ɾ��������");
		return false;
	}
	
	if($(div_id)){
		$(div_id).innerHTML = "";
	}

	
	hiddenMessage(true,true);
}

//������ʾ��Trv
function addXsqTr(){

	var tr_id = $("checked_tr").value;
	var xsq = tr_id.split("_")[0];//��ʾ��
	var czh = tr_id.split("_")[1];//������
	var xsqdm = xsq.replace("xsq","");//��ʾ������
	var szh = "szhs"+xsqdm;
	var newhs = parseInt($(szh).value) + 1;//������
	
	var xsq_div_id = xsq+"_div";
	var xsqHtml = $(xsq_div_id).innerHTML;
	
	$(xsq_div_id).innerHTML = "";
	
	xsqHtml+="<table class=\"formlist\" border=\"0\" align=\"center\" style=\"width: 90%\">";
	xsqHtml+="<tbody><tr onclick=\"rowOnClick(this);\" id=\""+xsq+"_"+newhs+"_tr\"><td>";
	xsqHtml+="<div id=\""+xsq+"_"+newhs+"_div\">";
	xsqHtml+="<table width=\"100%\">";
	xsqHtml+="<tr>";
	
	xsqHtml+="<td width=\"20%\">";
	xsqHtml+="<p id=\"xsq"+xsqdm+"_"+newhs+"_left_p\"></p>";
	xsqHtml+="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+newhs+"_left_text\"/>";
	xsqHtml+="</td>";
	
	xsqHtml+="<td width=\"30%\">";
	xsqHtml+="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+newhs+"_left\">Ӧ��</button>";
	xsqHtml+="</td>";
	
	xsqHtml+="<td width=\"20%\">";
	xsqHtml+="<p id=\"xsq"+xsqdm+"_"+newhs+"_right_p\"></p>";
	xsqHtml+="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+newhs+"_right_text\"/>";
	xsqHtml+="</td>";
	
	xsqHtml+="<td width=\"30%\">";
	xsqHtml+="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+newhs+"_right\">Ӧ��</button>";
	xsqHtml+="</td>";
	xsqHtml+="</tr>";
	xsqHtml+="</table>";
	xsqHtml+="</div>";
	xsqHtml+="</table>";
	
	$(xsq_div_id).innerHTML = xsqHtml;
	
	hiddenMessage(true,true);
	
	//��ձ�����ɫ
	var num = $(xsq_div_id).getElementsByTagName("tr").length;
	for(var i=0;i<num;i++){
		var obj = $(xsq_div_id).getElementsByTagName("tr")[i];
		obj.style.backgroundColor = "";
	}
	
	$("szhs"+xsqdm).value = newhs;//����
}

/** ===============================���в��� end==================================*/

/** ===============================�������ֶ�==================================*/
//�˻��������ֶ�
function returnDfpZd(){

	var div_id = $("checked_tr").value;
	var xsq = div_id.split("_")[0];//��ʾ��
	var xsqdm = xsq.replace("xsq","");//��ʾ������
	var hs = div_id.split("_")[1];//����

	var p_left_id = xsq+"_"+hs+"_left_p";
	var p_right_id = xsq+"_"+hs+"_right_p";
	var text_left_id = xsq+"_"+hs+"_left_text";
	var text_right_id = xsq+"_"+hs+"_right_text";

	var zd = new Array();
	var zdm = new Array();
	var n = 0;
	
	//���ֶ�
	if($(p_left_id) && $(text_left_id) && $(p_left_id).innerHTML !="" && $(text_left_id).value != "" ){
		zd[n] = $(text_left_id).value;
		zdm[n] = $(p_left_id).innerHTML;
		n++;
	}
	
	//���ֶ�
	if($(p_right_id) && $(text_right_id) && $(p_right_id).innerHTML !="" && $(text_right_id).value != "" ){
		zd[n] = $(text_right_id).value;
		zdm[n] = $(p_right_id).innerHTML;
		n++;
	}
	

	//���ֶη��ش�����
	var m = $("qyzd").options.length;
	for(var j=0;j<n;j++){
		var value=zd[j];
		var text=zdm[j];
		$("qyzd").options[m] = new Option(text,value);
		m++;
	}
}
/** ===============================�������ֶ� end==================================*/

/** ===============================��ʾ������==================================*/

//������ʾ��
function saveYmsz(){
	if (confirm("��Ҫ���������õ���ʾ������ȷ�ϣ�")) {
		//������Ҫ������ֶ���Ϣ
		creatZdInfo();
		//ִ�б������
		saveUpdate('/xgxt/xsxxJbsz.do?method=ymjbsz&doType=save','');
	}
}

//�������������µ���ʾ��
function setXsqBySz(){

	//��ʾ������
	var xsqmc = $("sz_xsqmc_value").value;
	//��ռ����
	var szhs = $("sz_szhs_value").value;
	//��Ƭ��ʾ
	var zpxs = $("sz_zpxs_value").value;
	//��Ƭλ��
	var zpwz = $("sz_zpwz_value").value;
	//��Ƭ��ռ��
	var zpszhs = $("sz_zpszhs_value").value;
	//��ʾ��
	var xsqNum = $("xsqNum").value;
	
	if(xsqmc == ""){
		alert("��ʾ�����Ʋ���Ϊ�գ���ȷ�ϣ�");
		return false;
	}

	if(szhs == ""){
		alert("��ռ��������Ϊ�գ���ȷ�ϣ�");
		return false;
	}
	
	if(zpxs == "��"){
	
		if(szhs < 6){
			alert("�����Ҫ��ʾ��Ƭ�Ļ�����ռ��������С��6�У���ȷ�ϣ�");
			return false;
		}
		
		if(zpszhs == ""){
			alert("��Ƭ��ռ��������Ϊ�գ���ȷ�ϣ�");
			return false;
		}

		if(zpszhs > szhs){
			alert("��Ƭ��ռ�в��ܴ�����ʾ����ռ�У���ȷ�ϣ�");
			return false;
		}
	}
	
	var xsqHtml = $("xsqNr").innerHTML;	
	var xsqdm = parseInt(xsqNum)+1;
		
	xsqHtml +="<table class=\"formlist\" border=\"0\" align=\"center\" style=\"width: 90%\" id=\"xsq"+xsqdm+"\">";
	xsqHtml +="<thead><tr><th colspan=\"4\"><span><a href=\"#\" onclick=\"hiddenXsq('"+xsqdm+"')\">"+xsqmc+"</span>";
	xsqHtml +="<input type=\"hidden\" name=\"xsqdm\" value=\""+xsqdm+"\"/>";//��ʾ������
	xsqHtml +="<input type=\"hidden\" name=\"xsqmc\" value=\""+xsqmc+"\"/>";//��ʾ������
	xsqHtml +="<input type=\"hidden\" name=\"szhs\" id=\"szhs"+xsqdm+"\" value=\""+szhs+"\"/>";//��ռ����
	xsqHtml +="<input type=\"hidden\" name=\"zpxs\" value=\""+zpxs+"\"/>";//��Ƭ��ʾ
	xsqHtml +="<input type=\"hidden\" name=\"zpwz\" value=\""+zpwz+"\"/>";//��Ƭλ��
	xsqHtml +="<input type=\"hidden\" name=\"zpszhs\" value=\""+zpszhs+"\"/>";//��Ƭ��ռ��
	xsqHtml +="<input type=\"hidden\" name=\"xssx\" value=\""+xsqdm+"\"/>";//��ʾ˳��
	xsqHtml +="<input type=\"checkbox\" id=\"xsq"+xsqdm+"_checkbox\" name=\"xsq_checkbox\" value=\""+xsqdm+"\"/>"
	xsqHtml +="ѡ����ʾ��";
										
	xsqHtml +="</th></tr></thead>";
	xsqHtml +="<tbody id=\"xsq"+xsqdm+"_tb\">";
	
	//��ʼ��
	var qsh = 1;
		
	//��Ҫ��ʾ��Ƭ
	if(zpxs == "��"){
		
		xsqHtml +="<tr id=\"xsq"+xsqdm+"_1_tr\">";
		xsqHtml +="<td colspan=\"4\">";
		xsqHtml +="<div id=\"xsq"+xsqdm+"_1_div\">";	
		xsqHtml +="<table width=\"100%\">";
			
		if(zpwz == "��"){
			
			//��
			xsqHtml +="<tr><td width=\"20%\">";
			xsqHtml +="<p id=\"xsq"+xsqdm+"_1_left_p\"></p>";
			xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_1_left_text\"/>";
			xsqHtml +="</td>";
			
			xsqHtml +="<td width=\"30%\">";
			xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_1_left\">Ӧ��</button>";
			xsqHtml +="</td>"
			//��
			xsqHtml +="<td width=\"20%\" rowspan=\"6\">ѧ</br>��</br>��</br>Ƭ</td>";
			xsqHtml +="<td width=\"30%\" rowspan=\"6\">";
			xsqHtml +="<img src=\"/xgxt/images/type_pic.gif\"/>";
			xsqHtml +="</td></tr>";
			
			for(var i=2;i<=zpszhs;i++){
				//��
				xsqHtml +="<tr><td width=\"20%\">";
				xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_left_p\"></p>";
				xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_left_text\" value=\"left\"/>";
				xsqHtml +="</td>";
				
				xsqHtml +="<td width=\"30%\">";
				xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_left\">Ӧ��</button>";
				xsqHtml +="</td>";
			}
			
		}else{
			//��
			xsqHtml +="<tr><td width=\"20%\" rowspan=\"6\">ѧ</br>��</br>��</br>Ƭ</td>";
			xsqHtml +="<td width=\"30%\" rowspan=\"6\">";
			xsqHtml +="<img src=\"/xgxt/images/type_pic.gif\"/>";
			xsqHtml +="</td>";
			
			//��
			xsqHtml +="<td width=\"20%\">";
			xsqHtml +="<p id=\"xsq"+xsqdm+"_1_right_p\"></p>";
			xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_1_right_text\"/>";
			xsqHtml +="</td>";
			
			xsqHtml +="<td width=\"30%\">";
			xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_1_right\">Ӧ��</button>";
			xsqHtml +="</td></tr>"
			
			for(var i=2;i<=zpszhs;i++){
				//��
				xsqHtml +="<tr><td width=\"20%\">";
				xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_right_p\"></p>";
				xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_right_text\" value=\"right\"/>";
				xsqHtml +="</td>";
				
				xsqHtml +="<td width=\"30%\">";
				xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_right\">Ӧ��</button>";
				xsqHtml +="</td>";
			}
		}
			
		xsqHtml +="</table>";			
		xsqHtml +="</div>";
		xsqHtml +="</td>";
		xsqHtml +="</tr>";
			
		qsh = parseInt(zpszhs)+1;
	}
	
	
	for(var i=qsh;i<=szhs;i++){
		xsqHtml +="<tr onclick=\"rowOnClick(this);\" id=\"xsq"+xsqdm+"_"+i+"_tr\">";
		xsqHtml +="<td colspan=\"4\">";
		xsqHtml +="<div id=\"xsq"+xsqdm+"_"+i+"_div\">";	
		xsqHtml +="<table width=\"100%\"><tr>";
		//��
		xsqHtml +="<td width=\"20%\">";
		xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_left_p\"></p>";
		xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_left_text\"/>";
		xsqHtml +="</td>";
		
		xsqHtml +="<td width=\"30%\">";
		xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_left\">Ӧ��</button>";
		xsqHtml +="</td>";
		
		//��
		xsqHtml +="<td width=\"20%\">";
		xsqHtml +="<p id=\"xsq"+xsqdm+"_"+i+"_right_p\"></p>";
		xsqHtml +="<input type=\"hidden\" name=\"yyzd"+xsqdm+"\" id=\"xsq"+xsqdm+"_"+i+"_right_text\"/>";
		xsqHtml +="</td>";
		
		xsqHtml +="<td width=\"30%\">";
		xsqHtml +="<button onclick=\"clickXsqBtn(this)\" id=\"xsq"+xsqdm+"_"+i+"_right\">Ӧ��</button>";
		xsqHtml +="</td>";
		
		xsqHtml +="</tr></table>";									
		xsqHtml +="</div>";
		xsqHtml +="</td>";
		xsqHtml +="</tr>";
	}
		
	xsqHtml +="</tbody>";
	xsqHtml +="</table>";
	xsqHtml +="</br>";
	
	$("xsqNr").innerHTML = "";
	$("xsqNr").innerHTML = xsqHtml;
		
	$("xsqNum").value = xsqdm;
	
	hiddenMessage(true,true);
}

//������ʾ��
function hiddenXsq(xsqid){
	var id = "xsq"+xsqid+"_tb";
	if($(id)){
		if($(id).style.display == "none"){
			$(id).style.display = "";
		}else{
			$(id).style.display = "none";
		}
	}
}

//ɾ����ʾ��
function delXsq(){

	var num = document.getElementsByName("xsq_checkbox").length;
	var flag = true;
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("xsq_checkbox")[i];
		if(obj.checked){
			flag = false;
		}
	}
	
	if(flag){
		alert("�빴ѡϣ��ɾ������ʾ����");
		return false;
	}
	
	if (confirm("��Ҫɾ������ѡ����ʾ������ȷ�ϣ�\n(ע��ɾ�������Ҫִ�б��������������ɾ��)")) {
		for(var i=(num-1);i>=0;i--){
			var obj = document.getElementsByName("xsq_checkbox")[i];
			var xsqdm = obj.value;
			if(obj.checked){
				var id = "xsq"+xsqdm;
				if($(id)){
				
					var zdCount = document.getElementsByName("yyzd"+xsqdm).length;
					var zdmCount = $(id).getElementsByTagName("p").length;
					
					var zd = new Array();
					var zdm = new Array();
					
					var n=0;

					for(var j=0;j<zdCount;j++){
						var zdObj = document.getElementsByName("yyzd"+xsqdm)[j];
						if(zdObj.value != ""){
							zd[n]=zdObj.value;
							n++;
						}
					}
					
					n = 0;
					
					for(var j=0;j<zdmCount;j++){
						var zdmObj = $(id).getElementsByTagName("p")[j];
						if(zdmObj.innerHTML != ""){
							zdm[n]=zdmObj.innerHTML;
							n++;
						}
					}
					
					var m = $("qyzd").options.length;
					for(var j=0;j<n;j++){
						var value=zd[j];
						var text=zdm[j];
						$("qyzd").options[m] = new Option(text,value);
						m++;
					}
					
					$(id+"_div").outerHTML = "";
				}
			}
		}
	}
}

//�����ʾ����ť
function clickXsqBtn(obj){
	//btnID
	var btn_Id = obj.id;
	var btn_nr = obj.innerHTML;
	//�ֶ��б�
	var optionSize = $("qyzd").options.length;

	if(btn_nr == "Ӧ��"){
		if(optionSize != "0"){
			obj.innerHTML = "ȡ��";
			yyBtn(btn_Id);
		}else{
			alert("�������ֶ��б��Ѿ�Ϊ�գ���ȷ�ϣ�");
			return false;
		}
	}else{
		obj.innerHTML = "Ӧ��";
		qxBtn(btn_Id);
	}
}

//Ӧ��
function yyBtn(btn_Id){
	//�����ֶ�
	var qyzd = $("qyzd");
	var selectNum = qyzd.selectedIndex;
	var selectEd = selectNum;
	var optionSize = qyzd.options.length;

	if(selectEd == optionSize - 1){
		selectEd = parseInt(selectNum) - 1;
	}
	
	var text = qyzd.options[selectNum].text;
	var value = qyzd.options[selectNum].value;

	qyzd.options[selectNum]=null;
	if(optionSize != 1 ){
		qyzd.options[selectEd].selected = "true";
	}
	
	//P_ID
	var p_Id = btn_Id+"_p";
	//Text_ID
	var text_Id = btn_Id+"_text";
	if($(p_Id)){
		$(p_Id).innerHTML = text;
	}
	
	if($(text_Id)){
		$(text_Id).value = value;
	}
}

//ȡ��
function qxBtn(btn_Id){
	
	//P_ID
	var p_Id = btn_Id+"_p";
	var text = $(p_Id).innerHTML;
	$(p_Id).innerHTML = "";

	//Text_ID
	var text_Id = btn_Id+"_text";
	var value = $(text_Id).value;
	$(text_Id).value = "";
	
	//�����ֶ�
	var qyzd = $("qyzd");
	qyzd.options[qyzd.options.length] = new Option(text,value);
	qyzd.options[qyzd.options.length-1].selected = "true";
	
}

/** ===============================��ʾ������ end==================================*/

/** ===============================��������==================================*/
//�����ѡ��
function clickRadioValue(num,mk,value){
	var id = "hid_"+mk+"_"+num;
	$(id).value = value;
}

//checked�Ƿ���ʾ��Ƭ
function checkedZpxs(zpxs){
	if(zpxs == "��"){
		$("zpwz_tr").style.display = "";
		$("zpszhs_tr").style.display = "";
	}else{
		$("zpwz_tr").style.display = "none";
		$("zpszhs_tr").style.display = "none";
	}
}

//���õ������ֵ
function setDivHiddenValue(lx,value){
	var id = "sz_"+lx+"_value";
	$(id).value = value;
}

//������Ҫ������ֶ���Ϣ
function creatZdInfo(){
	var xsq_num = document.getElementsByName("xsqdm").length;
	//��ʾ��
	for(var i=0;i<xsq_num;i++){
	
		var xsq_obj = document.getElementsByName("xsqdm")[i];
		var xsqdm = xsq_obj.value;
		var zdid = "yyzd"+xsqdm;
		
		var zd_num = document.getElementsByName(zdid).length;
		
		//�ֶ�
		for(var j=0;j<zd_num;j++){
			var zd_obj = document.getElementsByName(zdid)[j];
			if(zd_obj.value != ""){
				var id = zd_obj.id;
				var zd_szxsq = xsqdm;//�ֶ�������ʾ��
				var zd = zd_obj.value;//�ֶ�
				var szh = id.split("_")[1];//������
				var szl = id.split("_")[2];//������

				if(szl == "left"){
					szl = "1";
				}else{
					szl = "3";
				}
				//������Ҫ������ֶ���Ϣ
				var xsq_obj = document.createElement("input");
					xsq_obj.type = "text";
					xsq_obj.name = "zd_szxsq";
					xsq_obj.value = zd_szxsq;
				
				var zd_obj = document.createElement("input");
					zd_obj.type = "text";
					zd_obj.name = "zd";
					zd_obj.value = zd;
					
				var szh_obj = document.createElement("input");
					szh_obj.type = "text";
					szh_obj.name = "szh";
					szh_obj.value = szh;
				
				var szl_obj = document.createElement("input");
					szl_obj.type = "text";
					szl_obj.name = "szl";
					szl_obj.value = szl;
					
				document.forms[0].appendChild(xsq_obj);
				document.forms[0].appendChild(zd_obj);
				document.forms[0].appendChild(szh_obj);
				document.forms[0].appendChild(szl_obj);
			}
		}
	}
}

//���������ֶ�ֵ
function setQyzdValue(){

	var qyzd = $("qyzd");
	var optionSize = qyzd.options.length;
	
	if(optionSize > 0 ){
		qyzd.options[0].selected = "true";
	}
}
/** ===============================�������� end==================================*/