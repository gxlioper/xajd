function dtjsCommonSave(url,yzdz,yzcd,mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return;
		}
	}
	var splitDz = yzdz.split("-");
	var splitCd = yzcd.split("-");
	if(splitDz[0]!=""){
	for (i = 0; i < splitDz.length; i++) {
		var dzsjcd = $(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
		var dzsjmc = $(splitDz[i]).cells[0].innerHTML;
		if (dzsjcd.length>splitCd[i]) {
			alert(dzsjmc+"���ܳ���"+splitCd[i]+"���֣�");
			return;
		}
	}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

//�������ݲ���֤�����Ƿ�Ϊ��
function saveUpdate(url,pk){
	var key = new Array();
	if(pk!=""){
		key=pk.split("-");
	}
	
	if(key.length > 0){
		for(var i=0;i<key.length;i++){
			if($(key[i])){
				if($(key[i]).value == ""){
					alert("��\"*\"��Ŀ����Ϊ�գ���ȷ��");
					return false;
				}
			}
		}
	}
	BatAlert.showTips('���������У���ȴ�......');
	refreshForm(url)
}

//���´���
function showInfo(url,doType,w,h){
	if(doType == "update"){
		if(curr_row == null){
			alertInfo('��ѡ��Ҫ�޸ĵ����ݣ�');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alertInfo('��ѡ����˵����ݣ�');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alertInfo('��ѡ��Ҫ�鿴�����ݣ�');
			return false;
		}
	}
	
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

//����ҳ��
function openInfo(url){
	if(curr_row == null){
		alert('��ѡ��Ҫ���������ݣ�');
		return false;
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&pk="+pk;
	window.open(url);
}

//�����ύ
function sumitInfo(url,doType){
	//var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	var checkBoxArr = document.getElementsByName("checkVal");
	var len=0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked){
		len++;
		}
	}
	if(len > 0){
		//if(doType=="del"){
			url+="&doType="+doType;
		//	alert("url");
			if (confirm("ȷ��Ҫɾ������ѡ������?")) {
		//		alert("url");
				showTips('���������У���ȴ�......');
				refreshForm(url);
			}
		//}else{
			
		//}
	}else{
		alert("�빴ѡҪ���������");
		return false;
	}
}

function getSszb(){
	var xydm = $("xydm").value;
	var objId = "zbmc";
	var doType = "";
	
	if($("doType")){
		doType = $("doType").value;
	}
	
	if(xydm != "" && (doType == "add" || doType == "")){
		getSjxyDtjsDAO.getSelectList("sjxy_dzbb", "sszb", "sszb","","xydm",xydm,function(data){
			if(data !=null && data.length >0){
				if(data !=null && data.length >0){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					$(objId).options[0].value="";
				}
			}
		});
	}
}

function sendXx(){
	var tableName=$("tableName").value;
	var lx="";if($("lx")){lx=$("lx").value};
	var zd="";if($("zd")){zd=$("zd").value};
	var va="";if($("va")){va=$("va").value};
	var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
	url+="&tableName="+tableName;
	if(lx !=""){
		url+="&lx="+lx;
	}
	if(zd !=""){
		url+="&zd="+zd;
	}
	if(va !=""){
		url+="&va="+va;
	}
	showTopWin(url,800,600);
}

function djzh(){
	var checkBoxArr = document.getElementsByName("checkVal");
	var yzchk=true;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked){
		yzchk =false;
				break;
				}
	}
	if(yzchk==true){
		alert("�빴ѡ������ת����ѧ��");
		return;
	}
	
	viewTempDiv('�ȼ�ת��','tmpdiv1',280,160);
	
//	var d_width = document.body.clientWidth;
//	var d_height = document.body.clientHeight ;
//	var d_left = 0;
//	var d_top = 0;
//	var d_color = "#EEF4F9";
//	var d_width_top = 280;
//	var d_height_top = 120;
//	var d_left_top = (d_width - d_width_top) / 2;
//	var d_top_top = (d_height - d_height_top)/ 2;
//	var d_color_top = "#EEF4F9";
//	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
//	dd_html += "</div>";
//	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
//	dd_html += "<div class='tab'><table width='280' class='formlist'>";
//	dd_html += "<thead>";
//	dd_html += "<tr height='30'>";
//	dd_html += "<th colspan='2'>";
//	dd_html += "<span>��ѡ��</span>";
//	dd_html += "</th>";
//	dd_html += "</tr>";
//	dd_html += "</thead>";
//	dd_html += "<tbody>";
//	
//	dd_html += "<tr height='30'>";
//	dd_html += "<th align='right' width='30%'>";
//	dd_html += "ת���ȼ�:";
//	dd_html += "</th>";
//	dd_html += "<td align='left'>";
//	dd_html += "<select name='zhdj' id ='zhdj' style='width: 156px' onchange='chZhdj(this.value)'>" 
//	dd_html += $('zhdj').innerHTML;
//	dd_html += "</td>";
//	dd_html += "</tr>";
//	
//	dd_html += "<tr height='30'>";
//	dd_html += "<th align='right' width='30%'>";
//	dd_html += "ʱ��:";
//	dd_html += "</th>";
//	dd_html += "<td align='left'>";
//	dd_html += "<input type='text' name='zhsj' id='zhsj' style='width: 150px' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
//	dd_html += "</td>";
//	dd_html += "</tr>";
//	dd_html += "<tr bgcolor='EEF4F9'>";
//	dd_html += "<td colspan='2' align='right'>";
//	dd_html += "<button onclick='saveZhdj()';>ȷ��</button>";
//	dd_html += "<button onclick='closeAdd1()'>ȡ��</button>";
//	dd_html += "</td>";
//	dd_html += "</tr>";
//	dd_html += "<tbody>";
//	dd_html += "</table><div>";
//	dd_html += "</div>";
//	tmpdiv1.innerHTML = dd_html;
}
	
function time(id){
	return showCalendar(id,'y-mm-dd');
}
	
function saveZhdj() {

	if($("zhsj")){
		if($("zhsj").value == ""){
			alert("��ȷ��ת��ʱ��!");
			return false;
		}
	}
	
	if($("select_zhdj")){
		if($("select_zhdj").value == ""){
			alert("��ȷ��ת���ȼ�!");
			return false;
		}
	}	
	
	var url = $("url").value;
	showTips('���������У���ȴ�......');
	refreshForm(url);
}

function chZhdj(zhdj){
	if($("select_zhdj")){
		$("select_zhdj").value = zhdj;
	}
}

function setDzbBjList(){

	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var userType = "";
	var isFdy="false";
	var isBzr="false";
	if($("userName")){userName = $("userName").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("isFdy")){isFdy=$("isFdy").value};
	if($("userType")&&$("userType")!=null){userType = $("userType").value};
	if("bzr"==userType){
		isFdy = "true";
	}
	if($("isBzr")){isBzr = $("isBzr").value};
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}	
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;	

	dwr.engine.setAsync(false);
	zjlgZbglDAO.setBjList(query,userName,isFdy,isBzr,function initTjList(data){
		if (data != null && typeof data == 'object') {
			var objId = "bj";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bjdm","bjmc");	
				$(objId).options[0].value = "";		
			}
		}
	});
	dwr.engine.setAsync(true);
		
}

function setDzbZyList(){

	var xydm = "";
	var query = "";
	var userName = "";
	var userType = "";	
	var nj    = "";
	var isFdy="";
	var isBzr="";
	if($("isFdy")){isFdy = $("isFdy").value};
	if($("isBzr")){
			isBzr = $("isBzr").value;
	}
	if($("xy")){xydm = $("xy").value};	
	if($("userName")){userName = $("userName").value};
	if($("userType")){userType = $("userType").value};
	if("bzr"==userType){
	   isFdy = "true";
	}
	if($("nj")&&$("nj")!=null){
	    nj = $("nj").value;
	}

	dwr.engine.setAsync(false);
	zjlgZbglDAO.setZyList(xydm,nj,userName,isFdy,isBzr,function initTjList(data){
		if (data != null && typeof data == 'object') {
			var objId = "zy";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"zydm","zymc");	
				$(objId).options[0].value = "";		
			}
		}
	});
	dwr.engine.setAsync(true);
}