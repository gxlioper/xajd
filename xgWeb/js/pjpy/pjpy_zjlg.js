//�жϷ����Ƿ񳬹�100��
function chFs(obj){
	if(obj.value>100){
		alert("�������ܳ���100�֣���ȷ�ϣ���");
		obj.focus();
	}
}

//�������ŵ����ֳ�ʼֵ
function onShow(){
	var xydm = $("xyV").value;
	var zy = $("zyV").value;
	var bj = $("bjV").value;
	var nj = $("njV").value;
	var xn = $("xnV").value;
	var xh = $("xhV").value;
	var xm = $("xmV").value;
		
	for(var i=0;i<$("xy").options.length;i++){
		if($("xy").options[i].value == xydm){
			$("xy").options[i].selected = true;
		}
	}
	for(var i=0;i<$("zy").options.length;i++){
		if($("zy").options[i].value == zy){
			$("zy").options[i].selected = true;
		}
	}
	for(var i=0;i<$("bj").options.length;i++){
		if($("bj").options[i].value == bj){
			$("bj").options[i].selected = true;
		}
	}
	if($("userType").value == "xy" || $("userType").value == "teacher"){
		$("xy").disabled = true;
	}

	$("nj").value = nj;
	$("xn").value = xn 
	$("xh").value = xh 
	$("xm").value = xm;
	
}

//
function pjpyCpzfpzj() {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var userType = $("userType").value;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 200;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='350' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>����������</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "����"+jQuery("#xbmc").val();
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	if(userType=='xy'){
		dd_html += "<select name='cpzxydm' id ='cpzxydm' style='width:150px' disabled='true'>" 
		dd_html += $('xy').innerHTML;
		dd_html += "</select>";
	}else{
		dd_html += "<select name='cpzxydm' id ='cpzxydm' style='width:150px'>" 
		dd_html += $('xy').innerHTML;
		dd_html += "</select>";
	}
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='left' colspan='2'>";
	dd_html += "ע��  �����������"+jQuery("#xbmc").val()+"���������������Ĵ�������";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "���������";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id ='zjcpzdm' name = 'zjcpzdm'/>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "����������";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'cpzmc' name = 'cpzmc'/>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>";
	dd_html += "<button onclick='cpzzj()'>ȷ��</button>";
	dd_html += "<button onclick='closeAdd1()'>ȡ��</button>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "<iframe  src='javascript:false' border=0px  style='position: absolute;visibility: inherit;top: 0px;left: 0px;width: 350px;height: 200px;z-index: -999;filter: Alpha(Opacity=0)'></iframe>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function cpzzj(){
	if($('cpzxydm').value==''){
		alert("��ѡ�����������"+jQuery("#xbmc").val());
		return;
	}
	if($('zjcpzdm').value==''){
		alert('����д���������');
		return;
	}
	if($('cpzmc').value==''){
		alert('����д����������');
		return;
	}
	refreshForm('zjlgPjpy.do?method=addCpz')
}

function addBjBatchColum(){
		//���������         
        var wfpBj =  document.getElementById("wfpBj");
        var cpz =  document.getElementById("cpz");
        var yfpbj= document.getElementById("yfpbj"); 
        var yfpLength = yfpbj.length;      
        var wfpBjNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
	    
        if(cpz.value==''){
		   alert('��ѡ�в�������ٽ��в�����');
		   return false;
	    }
	    //alert(wfpBj.options.length);
        for(var i=0;i<wfpBj.options.length;i++){
		    if(wfpBj.options[i].selected){
		    	bjdms[j]=wfpBj.options[i].value;
                bjmcs[j]=wfpBj.options[i].text;
                wfpBj.options[i]=null;
			  	wfpBjNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(wfpBj==0){
		   alert('�������\'δ����༶\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    //alert(j);
	    var yfpbjz = Array();
	    for(var i = 0;i<yfpbj.length;i++){
	    	yfpbjz[i] = yfpbj.options[i].value;
	    }
	    
	    for (i = 0; i < j; i++) {     	   
             yfpbj.options[yfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	        	
}

function addBjBatchColum(){
		//���������         
        var wfpBj =  document.getElementById("wfpBj");
        var cpz =  document.getElementById("cpz");
        var yfpbj= document.getElementById("yfpbj"); 
        var yfpLength = yfpbj.length;      
        var wfpBjNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
	    
        if(cpz.value==''){
		   alert('��ѡ�в�������ٽ��в�����');
		   return false;
	    }
	    //alert(wfpBj.options.length);
        for(var i=0;i<wfpBj.options.length;i++){	
		    if(wfpBj.options[i].selected){
		    	bjdms[j]=wfpBj.options[i].value;
                bjmcs[j]=wfpBj.options[i].text;
                wfpBj.options[i]=null;
			  	wfpBjNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(wfpBjNum==0){
		   alert('�������\'δ����༶\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    //alert(j);
	    for (i = 0; i < j; i++) {     	   
             yfpbj.options[yfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	        	
}

function delBjBatchColum(){
		//���������         
        var wfpBj =  document.getElementById("wfpBj");
        var cpz =  document.getElementById("cpz");
        var yfpbj= document.getElementById("yfpbj"); 
        var wfpLength = wfpBj.length;      
        var yfpBjNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();	
	    
        if(cpz.value==''){
		   alert('��ѡ�в�������ٽ��в�����');
		   return false;
	    }
	    //alert(wfpBj.options.length);
        for(var i=0;i<yfpbj.options.length;i++){	
		    if(yfpbj.options[i].selected){
		    	bjdms[j]=yfpbj.options[i].value;
                bjmcs[j]=yfpbj.options[i].text;
                yfpbj.options[i]=null;
			  	yfpBjNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(yfpBjNum==0){
		   alert('�������\'�ѷ���༶\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    //alert(j);
	    for (i = 0; i < j; i++) {     	   
             wfpBj.options[wfpLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }	        	
}

function saveYfbbj(){
	var bjText = '';
	var bjValue ='';
	for (i = 0; i < $('yfpbj').options.length; i++) {		
		 bjValue += $('yfpbj').options[i].value+'!!';
	}
	$('bjdms').value = bjValue;
	refreshForm('zjlgPjpy.do?method=saveCpzFp');
}

function chkPriseBatForZjlg() {
	var jxjMsg = document.getElementById("jxjdm").options[document.getElementById("jxjdm").selectedIndex].text;
	var xyMsg = document.getElementById("xy").options[document.getElementById("xy").selectedIndex].text;
	var cpzMsg = document.getElementById("cpz").options[document.getElementById("cpz").selectedIndex].text;
	var bjMsg = document.getElementById("bj").options[document.getElementById("bj").selectedIndex].text;
	var userType =$("userType").value;
	var dispFlag = $("dispFlag").value;
	if(userType=="xy"&&dispFlag=="xydm"){
		alert(jQuery("#xbmc").val()+"������������ѧУ���õ�����");
		return;
	}
	jxjMsg = (jxjMsg == "") ? "ȫ��" : jxjMsg;
	xyMsg = (xyMsg == "") ? "ȫ��" : xyMsg;
	cpzMsg = (cpzMsg == "") ? "ȫ��" : cpzMsg;
	bjMsg = (bjMsg == "") ? "ȫ��" : bjMsg;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 200;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<div class='tab'>"
	dd_html += "<table width='300' class='formlist'>";
	dd_html += "<thead>";
	dd_html += "<tr>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>��������</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "����"+jQuery("#xbmc").val();
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += xyMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "������";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += cpzMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "�༶";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += bjMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "��ѧ��";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += jxjMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr>";
	dd_html += "<th align='right'>";
	dd_html += "�趨����:";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'szbl' name = 'szbl' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>%";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>";
	dd_html += "<button onclick=plszSave('zjlgPjpy.do?method=plszSave')>ȷ��</button>";
	dd_html += "<button onclick='closeAdd1()'>�ر�</button>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function dgrstz() {
	var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight;
	var userType =$("userType").value;
	var dispFlag = $("dispFlag").value;
	if(userType=="xy"&&dispFlag=="xydm"){
		alert(jQuery("#xbmc").val()+"�����޸�ѧУ���õ�����");
		return;
	}
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span>��������</span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "��������Ϊ";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'jxjtzrs' name = 'jxjrs' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>��";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=if($('jxjtzrs').value==''){alert('������������Ϊ��')}else{refreshForm('zjlgPjpy.do?method=saveJxjRs&pk=";
	dd_html += 	pk;
	dd_html += 	"');}>ȷ��</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>ȡ��</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
	
}

function plszSave(url){
	if($('szbl').value > 100 || $('szbl').value == ''){
		alert('�����������������ϱ�׼��');
		return;
	}
	refreshForm(url);
}

function priseDataInitForZjlg() {
	if (confirm("��ʼ������������յ�ǰѧ����������ݣ����������ɡ�\nȷ��Ҫ��ʼ����?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>���ڳ�ʼ�����ݣ����Ժ�......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("/xgxt/zjlgPjpy.do?method=jxjcsh");
		return true;
	}
	return false;
}

function zjlgPriseConfJxj(url) {
	var jxjMsg = document.getElementById("jxjdm").value;
	var xyMsg = document.getElementById("xy").value;
	var bjMsg = document.getElementById("bj").value;
	if ((xyMsg + jxjMsg + bjMsg) == "") {
		if (confirm("��û��ѡ���κ�����,��ѯ���ķѽϳ�ʱ��.\nȷ��Ҫ������?")) {
			document.forms[0].go.value = "go";
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	} else {
		document.forms[0].go.value = "go";
		refreshForm(url);
		return true;
	}
}

function chgPriseXn() {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='formlist'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "<span>����ѧ��</span>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<th align='right'>";
	dd_html += "��������ѧ�����Ϊ";
	dd_html += "</th>";
	dd_html += "<td align='left'>";
	dd_html += "<select name='jxjxn' id ='jxjxn' style='width:150px'>" 
	dd_html += $('xn').innerHTML;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "<tfoot><tr><td colspan='2' align='right'>"
	dd_html += "<button onclick=refreshForm('zjlgPjpy.do?method=tzjxjxn');>ȷ��</button>";
	dd_html += "<button onclick='closeAdd1()'>ȡ��</button>";
	dd_html += "</td></tr></tfoot>"
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function chkXjbjBat() {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "-------------------�Ƚ��༶��������--------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "�趨����:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'szbl' name = 'szbl' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>%";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=plszSave('zjlgPjpy.do?method=xjbjPlszSave') >ȷ��</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>ȡ��</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function xjbjrstz() {
	var xxdm = $("xxdm").value;
	var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	var xymc =	curr_row.cells[1].innerText;
	var bjsl =	curr_row.cells[2].innerText;
	var szbl =	curr_row.cells[3].innerText;
	var jsme = bjsl*szbl;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "------------------------��������------------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += jQuery("#xbmc").val()+"����:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += xymc;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "�༶����:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += bjsl;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "���ñ���:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += szbl;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "��������:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += Math.floor(jsme,'2');
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	if("10657" == xxdm){//���ݴ�ѧ
		dd_html += "�༶��������Ϊ:";
	}else{
		dd_html += "��������Ϊ:";
	}
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'tzme' name = 'me' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>";
	if("10657" == xxdm){//���ݴ�ѧ
		dd_html += "��";
	}else{
		dd_html += "��:";
	}
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=if($('tzme').value==''){alert('������������Ϊ��')}else{refreshForm('zjlgPjpy.do?method=saveXjbjMe&pk=";
	dd_html += 	pk;
	dd_html += 	"');}>ȷ��</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>ȡ��</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
	
}

function rychDataInitForZjlg() {
	if (confirm("��ʼ������������յ�ǰѧ����������ݣ����������ɡ�\nȷ��Ҫ��ʼ����?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>���ڳ�ʼ�����ݣ����Ժ�......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("/xgxt/zjlgPjpy.do?method=rychcsh");
		return true;
	}
	return false;
}

function chkRychBatForZjlg() {
	var rychMsg = document.getElementById("rychdm").options[document.getElementById("rychdm").selectedIndex].text;
	var xyMsg = document.getElementById("xy").options[document.getElementById("xy").selectedIndex].text;
	var zyMsg = document.getElementById("zy").options[document.getElementById("zy").selectedIndex].text;
	var bjMsg = document.getElementById("bj").options[document.getElementById("bj").selectedIndex].text;
	rychMsg = (rychMsg == "") ? "ȫ��" : rychMsg;
	xyMsg = (xyMsg == "") ? "ȫ��" : xyMsg;
	zyMsg = (zyMsg == "") ? "ȫ��" : zyMsg;
	bjMsg = (bjMsg == "") ? "ȫ��" : bjMsg;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 200;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "------------------------��������------------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "����"+jQuery("#xbmc").val()+":";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += xyMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "רҵ:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += zyMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "�༶:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += bjMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "�����ƺ�:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += rychMsg;
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "�趨����:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'szbl' name = 'szbl' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>%";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=plszSave('zjlgPjpy.do?method=rychPlszSave')>ȷ��</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>ȡ��</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}

function rychrstz() {
	var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 300;
	var d_height_top = 120;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "------------------------��������------------------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	dd_html += "<tbody>";
	dd_html += "<tr height='30'>";
	dd_html += "<td align='right'>";
	dd_html += "��������Ϊ:";
	dd_html += "</td>";
	dd_html += "<td align='left'>";
	dd_html += "<input type='test' id = 'tzrychrs' name = 'rychrs' style='width:60px' onkeypress='return numInputValue(this,5,event)' maxlength='5'/>��";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tr bgcolor='EEF4F9'>";
	dd_html += "<td colspan='2' align='center'>";
	dd_html += "<button class='button2' onclick=if($('tzrychrs').value==''){alert('������������Ϊ��')}else{refreshForm('zjlgPjpy.do?method=saveRychRs&pk=";
	dd_html += 	pk;
	dd_html += 	"');}>ȷ��</button>";
	dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
	dd_html += "<button class='button2' onclick='closeAdd1()'>ȡ��</button>";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "<tbody>";
	dd_html += "</table>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
	
}

function getJxjList(jxjlb){
	getCpzfp.getJxjList(jxjlb,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "jxjdm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"jxjdm","jxjmc");		
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
	});
}

function zjlgChgDispConf(eID) {
	var obj = document.getElementById(eID);
	if (obj.value == "bjdm") {
		//dispZy.style.display = "";
		document.getElementById("cpz").disabled = false;
		//dispBj.style.display = "";
		document.getElementById("bj").disabled = false;
	} else {
		if (obj.value == "cpzdm") {
		//dispZy.style.display = "";
			document.getElementById("cpz").disabled = false;
		//dispBj.style.display = "none";
			document.getElementById("bj").disabled = true;
		} else {
			if (obj.value == "xydm") {
		//dispZy.style.display = "none";
				document.getElementById("cpz").disabled = true;
		//dispBj.style.display = "none";
				document.getElementById("bj").disabled = true;
			}
		}
	}
}
