//��ҵ����js
//author:�����
//version:1.0

//�����ҵ��ͳ��
function showYxbys(){
	$('temp').style.display='';
	
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 350;
	var d_height_top = 150;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 2;
	var d_color_top = "#EEF4F9";
					
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<br/>";
	dd_html += "<table width='300' class='tbstyle'>";
	dd_html += "<thead>";
	dd_html += "<tr height='30'>";
	dd_html += "<td colspan='2'>";
	dd_html += "----------------�����ҵ��ͳ��----------------";
	dd_html += "</td>";
	dd_html += "</tr>";
	dd_html += "</thead><tbody>";
	dd_html += "<tr><td align='right'><font color='red'>*</font>ͳ����Ŀ:</td><td>";
	dd_html +="<select id='xmlb' style='width:100px'><option value=''></option>";
	dd_html +="<option value='ʡ��'>ʡ��</option>";
	dd_html +="<option value='У��'>У��</option>";
	dd_html +="</select></td></tr>";
	dd_html += "<tr><td align='right'><font color='red'>*</font>"+jQuery("#xbmc").val()+"��</td><td><select name='xydm' id='xydm'></select></td></tr>";
	dd_html += "<tr><td align='center' colspan='2'><button class='button2'  onclick='printYxbys();'>ȷ&nbsp;&nbsp;��</button> ";
	dd_html +="&nbsp;&nbsp;&nbsp;&nbsp;<button class='button2' onclick='closeDiv();'>ȡ&nbsp;&nbsp;��</button></td></tr></tbody></table>";
	
	temp.innerHTML = dd_html;
	
	GetListData.getXyList(function(data){
		DWRUtil.removeAllOptions('xydm');	
		DWRUtil.addOptions('xydm',data,"dm","mc");
	});
	
}
//�����ҵ��ͳ��
function printYxbys(){
	var xydm = $('xydm').value;
	var xmlb = $('xmlb').value;
	
	if (''==xydm || ''==xmlb){
		alert('��*���Ϊ��!');
		return false;
	}
	var url = "/xgxt/jygl.do?method=printYxbys&xydm="+xydm+"&sqlb="+xmlb;
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}



var array = [];	
	
	//����Ƿ��й��м�¼,����ǹ��еķ������鹩����������
function isChecked(tagName) {
	array = new Array();

	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag = true;
			array[n] = checkBoxArr[i].value;
			n++;
		}
	}
	if (flag) {
		return true;
	} else {
		alert("\u6ca1\u6709\u9009\u62e9\u76f8\u5e94\u8bb0\u5f55\uff0c\u8bf7\u9009\u62e9\u4e4b\u540e\u518d\u8fdb\u884c\u64cd\u4f5c!");
		return false;
	}
}




//����ͳ����Ŀ�Ĵ�����ʾ��ͬ��������
function showSelect(text){
	
	if ($('nj') && "" == $('nj').options[0].value){
		$('nj').remove($('nj').options[0]);
	}
	
	if ($('nf') && "" == $('nf').options[0].value){
		$('nf').remove($('nf').options[0]);
	}
	
	//�������̼�����Ŀ��ע
	if ("" != text && "10388" == $('xxdm').value) {
		jyglDAO.getXmbz(text,function(data){
			if (null != data){
				$('xmbz').value = data;
			} else {
				$('xmbz').value = "";
			}
		});
	}
	
	
	$("cheNj").style.display = "none";
	
	//��Դ�طֲ����ͳ��
	if ("J1001" == text){
	
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = J1001Tj;
	} 
	//���ξ�ҵ��ͳ��
	else if ("J1002" == text){
	
		$("cheNj").style.display = "";
		$("njtr").style.display = "none";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = J1002Tj;
	} 
	//���༶��ҵͳ��
	else if ("J1003" == text){
	
		$("cheNj").style.display = "";
		$("njtr").style.display = "none";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = J1003Tj;
	} 
	//��רҵ��ҵͳ��
	else if ("J1004" == text){
		
		$("cheNj").style.display = "";
		$("njtr").style.display = "none";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "";
		
		$("tjbutton").onclick = J1004Tj;
	} 
	//��Ժ��ҵ�������ͳ��
	else if ("JFJGC1001" == text){
		
		$('xmlb').value = "xy";
		
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = printTj;
	} 
	//רҵ��ҵ�������ͳ��
	else if ("JFJGC1002" == text){
		
		$('xmlb').value = "zy";
		
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = printTj;
	} 
	//�༶��ҵ�������ͳ��
	else if ("JFJGC1003" == text){
		
		$('xmlb').value = "bj";
		
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = printTj;
	} 
	//��ҵ�������ͳ��
	else if ("JFJGC1004" == text){
		
		$('xmlb').value = "hz";
		
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = printTj;
	} 
	//���ƾ�ҵ��ͳ�Ʊ�
	else if ("JFJGC1005" == text){
		
		$('xmlb').value = "bks";
		
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = printTj;
	} 
	//ר�ƾ�ҵ��ͳ�Ʊ�
	else if ("JFJGC1006" == text){
		
		$('xmlb').value = "zks";
		
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = printTj;
	} 
	//����Ա�����ҵ�����ϸ��
	else if ("JFJGC1007" == text){
		$("njtr").style.display = "";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		
		$("tjbutton").onclick = JFJGC1007;
	}
	//��λ���ͳ�Ʊ�
	else if ("JFJGC1008" == text){
		$("njtr").style.display = "none";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = JFJGC1008;
	}
	//��ҵ����ͳ�Ʊ�
	else if ("JFJGC1009" == text){
		$("njtr").style.display = "none";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
		
		$("tjbutton").onclick = JFJGC1009;
	}
	
	else {
	
		$("njtr").style.display = "none";
		$("xytr").style.display = "none";
		$("zytr").style.display = "none";
		$("bjtr").style.display = "none";
		$("cheJyXy").style.display = "none";
	
		$("tjbutton").onclick = null;
	}
	
}
			
			
//��������-ͨ��
function printTj(){

	var xmlb = $('xmlb').value;
	var nf = $('nf').value;
	var xydm = $('xy').value;
	var zydm = $('zy').value;
	var bjdm = $('bj').value;
	var url = "/xgxt/jygl.do?method=printJyl&flg="+xmlb+"&nf="+nf;
	
	if ('xy'==xmlb){
		url+='&xydm='+xydm;
	} else if('zy'==xmlb || 'bks'==xmlb || 'zks'==xmlb){
		url+='&xydm='+xydm+"&zydm="+zydm;
	} else if('bj'==xmlb || 'hz'==xmlb){
		url+='&xydm='+xydm+"&zydm="+zydm+"&bjdm="+bjdm;
	} else {
		alert('��ѡ����Ҫͳ�Ƶ���Ŀ!');
		return false;
	}


	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}

//��Դ�طֲ����ͳ��
function J1001Tj(){
	if ($('nj').value == ''){ 
		if (confirm('��ѡ���꼶,����ѡĬ��Ϊ��ǰ���'))
			expData('/xgxt/jygl.do?method=sydtj');
	}else{
		expData('/xgxt/jygl.do?method=sydtj');
	}
}

//���ξ�ҵ��ͳ��
function J1002Tj(){
	var nj = jQuery('input[checked="true"]');

	if(nj.length > 0){
		//if (confirm('��ѡ���꼶,����ѡĬ��Ϊ��ǰ���'))
		expData('/xgxt/jygl.do?method=jyltj');
		
	}else{ 
		alert('��ѡ����Ҫͳ�Ƶ��꼶!');
		return false;
	}
}
//���༶��ҵͳ��
function J1003Tj(){
//	var nj = jQuery('input[checked="true"]');
//	if(nj.length > 0){
//		expData('/xgxt/jygl.do?method=bjjytj');
//	}else{ 
//		alert('��ѡ����Ҫͳ�Ƶ��꼶!');
//		return false;
//	}
	var nj= document.getElementsByName("selNj");
	var njnum=0;
	for(var i=0;i<nj.length;i++){
		if(nj[i].checked==true){
			njnum=njnum+1;
		}
	}
	if(njnum==0){
		alert("��ѡ����Ҫͳ�Ƶ��꼶!");
		return false;
	}else{
		expData('/xgxt/jygl.do?method=bjjytj');
		return false;
	}
}
//��רҵ��ҵͳ��
function J1004Tj(){
	//var nj = jQuery('input[checked="true"]');
	//var nj =$("selNj").value;
	var nj= document.getElementsByName("selNj");
	var jyxy= document.getElementsByName("selJyXy");
	var njnum=0;
	var xynum=0;
	for(var i=0;i<nj.length;i++){
		if(nj[i].checked==true){
			njnum=njnum+1;
		}
	}
	for(var i=0;i<jyxy.length;i++){
		if(jyxy[i].checked==true){
			xynum=xynum+1;
		}
	}
	if(njnum==0){
		alert("��ѡ����Ҫͳ�Ƶ��꼶!");
		return false;
	}else{
		if(xynum==0){
			alert("��ѡ����Ҫͳ�Ƶ�"+jQuery("#xbmc").val()+"!");
			return false;
		}else{
			expData('/xgxt/jygl.do?method=zyjytj');
			return false;
		}
	}
	if(xynum==0){
		alert("��ѡ����Ҫͳ�Ƶ�"+jQuery("#xbmc").val()+"!");
		return false;
	}else{
		if(njnum==0){
			alert("��ѡ����Ҫͳ�Ƶ��꼶!");
			return false;
		}else{
			expData('/xgxt/jygl.do?method=zyjytj');
			return false;
		}
	}
}
//����Ա�����ҵ�����ϸ
function JFJGC1007(){
	var url = "/xgxt/jygl.do?method=printJyl&flg=fdymx";
	expData(url);
}


//��λ���ͳ�Ʊ�
function JFJGC1008(){
	var url = "/xgxt/jygl.do?method=printJyl&flg=dwlb";
	expData(url);
}

//��ҵ����ͳ�Ʊ�
function JFJGC1009(){
	var url = "/xgxt/jygl.do?method=printJyl&flg=hyfl";
	expData(url);
}







