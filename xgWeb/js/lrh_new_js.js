//��ctrlѡ�ж�����¼
 	var   Rows=new   Array(); //����ѡ�е��ж���   
 	var   ShiftStartRow=""; //Shift��ѡʱ�洢��ʼ�ж���   
    var   popUpWin=0;
  //ѡ��������   
  function   onfocusit(){   
  		iRow=window.event.srcElement;   
  		do{   
  			iRow=iRow.parentElement; 
  		}	while(iRow.tagName!='TR')   
    
  //Ctrl��ѡ   
  		if(event.ctrlKey){   
  			var   j=-1;   
  			for(i=0;i<Rows.length;i++){   
  			if(iRow==Rows[i]){
  			//	alert("a");
 			//	alert(Rows[i].getElementsByTagName("input")[0].value);   
  				j=i;   
  				break;   
  			}   
  		}   
  		if(j!=-1){   
  			for(i=j;i<Rows.length-1;i++){   
  			Rows[i]=Rows[i+1];
  		//	alert("b");
  		//	alert(Rows[i].getElementsByTagName("input")[0].value);
 			}   
  			Rows.length=Rows.length-1;   
 	 	}	
 	 	else{   
  			Rows[Rows.length]=iRow;   
  		}   
 	 		ShiftStartRow=iRow;   
  	} else{	
		if (Rows.length!=0){
			for (i=0; i<Rows.length; i++){	
				if (Rows[i].tagName.toLowerCase() == "tr") {
					Rows[i].style.backgroundColor = "#FFFFFF";
						// alert("aa");  
						// var kk=document.forms[0].xn_id.value;
						//document.
  	 					//alert(Rows[i].getElementsByTagName("input")[0].value);
	    		}
			}
		}
		Rows.length=1;
		Rows[0]=iRow;
//		ShiftStartRow=iRow;
	}
	 changeColor(iRow);
	}
	
	
function changeColor(E){   
  	for(var i=1;i<E.parentElement.rows.length;i++){   
  	 	E.parentElement.rows(i).style.backgroundColor="#FFFFFF"; 
 	}   
  	for(i=0;i<Rows.length;i++){   
  		Rows[i].style.backgroundColor="#EEEEEE";   
 	}   
 }
  
  
//�����Ի��������ɾ�Ĳ���
function viewMore_LRH(doType,zxsbh){
		var pkValue = "";
		var zxsbh=zxsbh;
		var url = "";
		var w = 550;
		var h = 450;
		//var w = 650;
		//var h = 500;
		if(doType=="View_Zxsxx"||doType=="View_Xssqzxs"){
			url="/xgxt/xljk_zxsxx_view.do?doType="+doType+"&zxsbh="+zxsbh;
			showTopWin(url,w,h);
		}else if (doType == "del") {
			if (curr_row == null) {
				alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
				return false;
			}else if (confirm("ȷ��Ҫɾ������������")) {
				pkValue =curr_row.cells[0].innerText;
				url="/xgxt/xljk_zxsxx_del.do?act=xljk_zxsxx&doType="+doType+"&zxsbh="+pkValue;
				refreshForm(url);
			}
		}else if (doType == "update") {
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			}else if (confirm("ȷ��Ҫ�޸ĸ���������")) {
				pkValue =curr_row.cells[0].innerText;
				url="/xgxt/xljk_zxsxx_update.do?act=xljk_zxsxx&zxxbh="+pkValue;
				showTopWin(url,w,h);
			}
		}
	}

//ˢ��url������ת
function refreshForm(url) {
	url = url.replace("/xgxt/","");
	document.forms[0].action = url;
	document.forms[0].submit();
}

//����һ��ѡ���¼
function lrh_createStList(yxstlbV,stV) {
	var flag = false;
	if (stV != null) {
		for (j = 0;j<stV.length;j++){
			for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
				if (document.forms[0].yxstlb.options[i].value == stV[j]) {
					flag = true;
				}
			}
			if(!flag){
				document.forms[0].yxstlb.options[document.forms[0].yxstlb.options.length] = new Option((document.forms[0].yxstlb.options.length + 1) + "----" + yxstlbV[j], stV[j]);
				flag = false;
			}else{
				flag = false;
			}
		}
		count.innerText = document.forms[0].yxstlb.options.length;
	}
}
//�����Ѿ����ڵ�ѡ���¼
function lrh_updateStList(flag) {
	if (flag == "clear") {
		document.forms[0].yxstlb.options.length = 0;
		count.innerText = document.forms[0].yxstlb.options.length;
	} else {
		var yxstlbListIndex = document.forms[0].yxstlb.selectedIndex;
		var tmp;
		if (yxstlbListIndex < 0) {
			alert("����ѡ����!");
			return false;
		}
		var m = 1;
		var size = document.forms[0].yxstlb.options.length;
		if (flag == "del") {
			for(var i = 0;i<size;i++){
				if(document.forms[0].yxstlb.options[i].selected==true){
					document.forms[0].yxstlb.options[i] = null;
					size--;
					i--;
				}else{
					var text = document.forms[0].yxstlb.options[i].text;
					document.forms[0].yxstlb.options[i].text = m + "----" + text.substring(text.indexOf('-')+5,text.length);
					m++;
				}
			}
//			document.forms[0].yxstlb.options[yxstlbListIndex] = null;
//			for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
//				document.forms[0].yxstlb.options[i].text = document.forms[0].yxstlb.options[i].value + "----" + (i + 1);
//		}
			count.innerText = document.forms[0].yxstlb.options.length;
		}
//		if (flag == "up") {
//			if (yxstlbListIndex == 0) {
//				return false;
//			}
//			tmp = document.forms[0].yxstlb.options[yxstlbListIndex - 1].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex - 1].value = document.forms[0].yxstlb.options[yxstlbListIndex].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex - 1].text = document.forms[0].yxstlb.options[yxstlbListIndex].value + "------------" + yxstlbListIndex;
//			document.forms[0].yxstlb.options[yxstlbListIndex].value = tmp;
//			document.forms[0].yxstlb.options[yxstlbListIndex].text = tmp + "------------" + (yxstlbListIndex + 1);
//			document.forms[0].yxstlb.selectedIndex = yxstlbListIndex - 1;
//		}
//		if (flag == "down") {
//			if (yxstlbListIndex >= document.forms[0].yxstlb.options.length - 1) {
//				return false;
//			}
//			tmp = document.forms[0].yxstlb.options[yxstlbListIndex + 1].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex + 1].value = document.forms[0].yxstlb.options[yxstlbListIndex].value;
//			document.forms[0].yxstlb.options[yxstlbListIndex + 1].text = document.forms[0].yxstlb.options[yxstlbListIndex].value + "------------" + (yxstlbListIndex + 2);
//			document.forms[0].yxstlb.options[yxstlbListIndex].value = tmp;
//			document.forms[0].yxstlb.options[yxstlbListIndex].text = tmp + "------------" + (yxstlbListIndex + 1);
//			document.forms[0].yxstlb.selectedIndex = yxstlbListIndex + 1;
//		}
	}
}

//�����ɵ�ѡ���¼���浽���ݿ�
function lrh_StSjDoSave() {
	var yxstStr = "";
	for (i = 0; i < document.forms[0].yxstlb.options.length; i++) {
		if (yxstStr != "") {
			yxstStr = yxstStr + "-";
		}
		//alert(document.forms[0].yxstlb.options[i].value);
		yxstStr = yxstStr + document.forms[0].yxstlb.options[i].value;
		//alert(yxstStr);
	}
	//var url='/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=sjst_st_add&yxstStr='+yxstStr;
	//document.forms[0].action = url;
	//document.forms[0].submit();
	/*do save*/
	var sjlsh =document.getElementById("sjlsh").value;
	underDealWith();
	sjstProcFun.sjstbBatchAddRecord(sjlsh,yxstStr,function(data){
		if(data){			
			alert("����ɹ���");
			window.dialogArguments.document.getElementById("search_go").click();
			Close();
		}else{
			alert("����ʧ�ܣ�");
			document.getElementById("tmpdiv").style.display = "none"; //no visable
		}
	});

}


//���������������ַ�
function chkInput(id){
	var key = event.keyCode;
	var len = document.getElementsByName(id).length;
	if((key>=48&&key<=57)||key==46||key==127||key==8||key==17||(key>=96&&key<=105)||key==110){
	}
	else{
		for(i=0;i<len;i++){
			var qmcj=document.getElementsByName(id)[i].value;
			var reg = /^\d+\.{0,1}\d{0,3}$/g;
			var role = qmcj.match(reg);
			if(role==null&&qmcj!=""){
				alert("������Ӣ��״̬�µ�����!")
            	document.getElementsByName(id)[i].value="";
            }
        }
	}
}

function chkInput2(id){
	var key = event.keyCode;
	var len = document.getElementsByName(id).length;
	if((key>=48&&key<=57)||key==46||key==127||key==8||(key>=96&&key<=105)||key==110){
	}else{
		for(i=0;i<len;i++){
			var qmcj=document.getElementsByName(id)[i].value;
			var reg = /^\d+\.{0,1}\d{0,3}$/g;
			var role = qmcj.match(reg);
			if(role==null&&qmcj!=""){
				alert("������Ӣ��״̬�µ�����!")
            	document.getElementsByName(id)[i].value="";
            }
        }
	}
}
//
function check_time_type(id){
	var time=document.getElementById(id).value;
	var reg=/[0-23]{2}:[0-59]{2}/g;
	var role=time.Math(reg);
	if(role==null){
		alert(role);
	}else{
		alert(role);
	}
}

function check_File() {
	var tmp = document.getElementById("file").value;
	if (tmp.value == "" || tmp.substring(tmp.length - 4, tmp.length).toLowerCase() != ".xls") {
		alert("��ѡ����ļ����Ϸ���������ѡ��");
		document.getElementById("file").value = "";
		return false;
	} else {
		document.forms[0].action = "/xgxt/lrh_commen_util.do?act=lrh_commen_util&doType=date_inport&tableName=" + document.getElementById("tableName").value;
		document.forms[0].submit();
		return true;
	}
}


function openWin(URLStr, width, height,type){
	if(width == null)width = 400;
	if(height == null)height = 300;
	var left = (BatAlert.W()-width)/2;
	var top = (BatAlert.H()-height)/2;
	if(popUpWin){
		if(!popUpWin.closed) popUpWin.close();
	}
	if(type == null){
		popUpWin = window.open(URLStr, "popUpWin", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=auto,resizable=no,copyhistory=yes,width=" + width + ",height=" + height + ",left=" + left + ", top=" + top);
	}else{
		popUpWin = window.open(URLStr, "popUpWin", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=" + width + ",height=" + height + ",left=" + left + ", top=" + top);
	}
}


/**
 * ���幫�÷���:����WebPrint���ɱ���
 * �ӱ��beginRow�п�ʼ���ж�col�е�ǰ�����е�ֵ�Ƿ���ȡ�������ȵĻ����ͷ�ҳ
 */
function expTabWebPrint(the_table, tabTit, titSpan, mar, beginRow, col) {
	if($(the_table).tagName.toLowerCase() == "table" && $(the_table).rows.length < 1){
		BatAlert.MyAlert("��ǰҳ��û�пɴ�ӡ�����ݣ�");
		return false;
	}
	if(mar){		
		try{
			var mars = mar.split("-");
			PageSetup(mars[0],mars[1],mars[2],mars[3],mars[4],mars[5]);
		}catch(e){
			
		}
	}
	var table_title = (titSpan == null || titSpan == "") ? tabTit : $(titSpan).innerText;
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n.noPrint{	page-break-after:always;}\n .fenPage{page-break-after:always;}\n";
	the_content += "</style>\n";
	the_content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\" />";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<script type=\"text/javascript\" src=\"/xgxt/js/webPrint.js\"></script>";
	the_content += "<script type=\"text/javascript\">";
	the_content += "window.onload = function () {";
	the_content += "	setPrintSize(10,5,10,15);";
	the_content += "	setPrintHFer('', \"&bҳ��:&p/&P\");";
	//the_content += "	alert(document.getElementById('"+the_table+"').outerHTML);";
	the_content += "	document.all.eprint.orientation = 2;";
	the_content += "};";
	the_content += "</script>";
	the_content += "<object id=eprint classid=\"clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441\" codebase=\"/xgxt/images/webprint.cab\" viewasext> </object>\n";
	the_content += "<center><div class='noPrin'><input type='button' class='button2' value='��ӡԤ��' onclick=\"Preview()\">";
	the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"Print()\"></div>";
	the_content += "<h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";
	var tempTable = $(the_table);
	if (beginRow != null) {	//�Ƿ���з����ӡ��ͨ���˲��������ж�
		for (var i = beginRow; i<tempTable.rows.length-1; i++) {
			if (tempTable.rows[i+1]!=null && tempTable.rows[i]!=null && tempTable.rows[i+1].cells[col-1]!=null && tempTable.rows[i].cells[col-1]!=null && tempTable.rows[i+1].cells[col-1].innerHTML!=tempTable.rows[i].cells[col-1].innerHTML){
				tempTable.rows[i+1].style.pageBreakBefore = "always";
			}
		}
	}
	the_content += tempTable.outerHTML;
	/*the_content = the_content.replace(/( *)(style=\")(.*)(\")/gi, "");
	the_content = the_content.replace(/(<td.*<)(input|button)(.*)(checkbox|text|button)(.*\/td>)/gi, "");
	the_content = the_content.replace(/( *)(on)([dbl]*)(click=\")(.*)(\")/gi, "");
	the_content = the_content.replace(/(<span)(.*)(<\/span>)/gi, "");
	the_content += "\n<br>";*/
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

function expTab(the_table, tabTit, titSpan) {
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;
    var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='js/function.js'>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";
	the_content += document.all(the_table).outerHTML;
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='ҳ������' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='��ӡԤ��' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"WebBrowser.ExecWB(6,6)\">";
	the_content += "<\/div>";
	//confirm(the_content);
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

/**����Ա����*/
function fdy_save(doType){
	var fdybh=document.all["fdybh"].value;
	if ( fdybh==""){
		alert ("����д����Ա��ţ�");
		document.all["fdybh"].focus();
		return false;
	}
	var xm=document.all["xm"].value;
	if ( xm==""){
		alert ("����д����Ա������");
		document.all["xm"].focus();
		return false;
	}
	
	var xxdm1=document.all["xxdm1"].value;
	if("10827" != xxdm1){
		var xb=document.all["xb"].value;
		if ( xb==""){
			alert ("��ѡ���Ա�");
			document.all["xb"].focus();
			return false;
		}
	}
	var xydm=document.all["xydm"].value;
	if ( xydm==""){
		alert ("ѡ��"+jQuery("#xbmc").val()+"!");
		document.all["xydm"].focus();
		return false;
	}
	var rzsj=document.all["rzsj"].value;
	if ( rzsj==""){
		alert ("��ѡ����ְʱ��!");
		document.all["rzsj"].focus();
		return false;
	}
	var xl=document.all["xl"].value;
		if ( xl==""){
		alert ("����дѧ��!");
		document.all["xl"].focus();
		return false;
	}
	var zy=document.all["zy"].value;
	if ( zy==""){
		alert ("����дרҵ!");
		document.all["zy"].focus();
		return false;
	}
	var sjhm=document.all["sjhm"].value;
	if ( sjhm==""){
		alert ("����д�ֻ�����!");
		document.all["sjhm"].focus();
		return false;
	}
	var lxdh=document.all["lxdh"].value;
	if ( lxdh==""){
		alert ("����д��ϵ�绰!");
		document.all["lxdh"].focus();
		return false;
	}
	var byyx=document.all["byyx"].value;
	if ( byyx==""){
		alert ("����д��ҵԺУ!");
		document.all["byyx"].focus();
		return false;
	}
	var gzjl=document.all["gzjl"].value;
	if ( gzjl==""){
		alert ("����д��������!");
		document.all["gzjl"].focus();
		return false;
	}
	var pxqk=document.all["pxqk"].value;
	if ( pxqk==""){
		alert ("����д��ѵ���!");
		document.all["pxqk"].focus();
		return false;
	}
	var lwcg=document.all["lwcg"].value;
	if ( lwcg==""){
		alert ("����д���ļ��ɹ�!");
		document.all["lwcg"].focus();
		return false;
	}
	if($("modi_flag")){
		document.all["modi_flag"].value="yes";
	}else if($("add_flag")){
			document.all["add_flag"].value="yes";
	}
	var gzjl=document.all["gzjl"].value;
	var pxqk=document.all["pxqk"].value;
	var lwcg=document.all["lwcg"].value;
	var bz=document.all["bz"].value;
	if(gzjl.length>300){
		alert("�����������ܳ���300�����֣���");
		return false;
	}
	if(pxqk.length>300){
		alert("��ѵ������ܳ���300�����֣���");
		return false;
	}
	if(lwcg.length>300){
		alert("���гɹ����ܳ���300�����֣���");
		return false;
	}
	if(bz.length>200){
		alert("��ע���ܳ���200�����֣���");
		return false;
	}
	underDealWith();
	refreshForm('/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=' + doType);
}

/**��֤�û���������*/
/**
function usercheck(){
	var userDep=document.all['userDep'].value;
	if(userDep!='010000'){
		document.getElementById('xy').disabled=true;
	}else{
		document.getElementById('xy').disabled=false;
	}
}
*/
function usercheck(){
	var userType=document.all['userType'].value;
	if("xx"==userType||"admin"==userType){
		document.getElementById('xy').disabled=false;
	}else{
		document.getElementById('xy').disabled=true;
	}
}

/**ѧԺ�����������*/
function xyxljkjyhd_save(doType){
	var zt=document.all["zt"].value;
	if ( zt==""){
		alert ("����д���⣡");
		document.all["zt"].focus();
		return false;
	}
	var xydm=document.all['xydm'].value;
	if(xydm==""){
		alert ("��ѡ��"+jQuery("#xbmc").val()+"��");
		document.all["xydm"].focus();
		return false;
	}
	var hdxs=document.all["hdxs"].value;
	if ( hdxs==""){
//		alert ("��ѡ�������ʽ!");
//		document.all["hdxs"].focus();
//		return false;
	}else{
		if(hdxs=="013"){
			var qthdxs=document.all["qthdxs"].value;
			if(qthdxs==""){
				alert ("����д�����������ʽ!");
				document.all["qthdxs"].focus();
				return false;
			}
		}
	}
	var dd=document.all["dd"].value;
	if ( dd==""){
		alert ("����д��ص㣡");
		document.all["dd"].focus();
		return false;
	}
			
	var rq=document.all["rq"].value;
	if ( rq==""){
		alert ("��ѡ�����ڣ�");
		document.all["rq"].focus();
		return false;
	}
			
	var zcr=document.all["zcr"].value;
	if ( zcr==""){
		alert ("����д�����ˣ�");
		document.all["zcr"].focus();
		return false;
	}
			
	var xs=document.all["xs"].value;
//	if ( xs==""){
//		alert ("����дѧ����");
//		document.all["xs"].focus();
//		return false;
//	}
			
	var cyxs=document.all["cyxs"].value;
	if ( cyxs==""){
		alert ("����д����ѧ����");
		document.all["cyxs"].focus();
		return false;
	}
			
	var rs=document.all["rs"].value;
	if ( rs==""){
		alert ("����д����ѧ��������");
		document.all["rs"].focus();
		return false;
	}
			
	var hdjl=document.all["hdjl"].value;
	if ( hdjl==""){
		alert ("����д�������¼��");
		document.all["hdjl"].focus();
		return false;
	}
			
	var hdxg=document.all["hdxg"].value;
	if ( hdxg==""){
		alert ("����д�����Ч����");
		document.all["hdxg"].focus();
		return false;
	}
	if($("modi_flag")){
		document.all["modi_flag"].value="yes";
	}else if($("add_flag")){
			document.all["add_flag"].value="yes";
	}		
	var hdjl=document.all["hdjl"].value;
	var hdxg=document.all["hdxg"].value;
	if (hdjl.length>350){
		alert ("�������¼���ܳ���600�����֣�");
		document.all["hdjl"].focus();
		return false;
	}
	if (hdxg.length>350){
		alert ("�����Ч�����ܳ���600�����֣�");
		document.all["hdxg"].focus();
		return false;
	}
	underDealWith();
	refreshForm('/xgxt/xljk_xyxljkjyhd.do?act=xyxljkjyhd&doType=' + doType);
}
	
function check_qthdxs(){
	var hdxs=document.all["hdxs"].value;
	if(hdxs=="013"){
		document.getElementById('qthdxs').readOnly=false;
	}else{
		document.getElementById('qthdxs').readOnly=true;
		document.all["qthdxs"].value="";
	}
}
	
function lrh_xyDisabled() {
	if (document.forms[0].userType.value == "xx") {
		document.getElementById('xydm').disabled = false;
	}
}	

/**�������۲�Ա��������*/
function gcy_save(doType){
	var xh=document.all["xh"].value;
	if ( xh==""){
		alert ("�����ѡ�񰴼�ѡ��ѧ����Ϣ��");
		document.all["xh"].focus();
		return false;
	}
	var csny=document.all["csny"].value;
//	if ( csny==""){
//		alert ("����д�۲�Ա��������");
//		document.all["csny"].focus();
//		return false;
//	}
	var sjhm=document.all["sjhm"].value;
	if ( sjhm==""){
		alert ("����д��Ա�ֻ�����");
		document.all["sjhm"].focus();
		return false;
	}
//	var qsdh=document.all["qsdh"].value;
//	if ( qsdh==""){
//		alert ("��д��Ա���ҵ绰");
//		document.all["qsdh"].focus();
//		return false;
//	}
//	var gcybh=document.all["gcybh"].value;
//	if ( gcybh==""){
//		alert ("����д�۲�Ա���");
//		document.all["gcybh"].focus();
//		return false;
//	}
	if($("modi_flag")){
		document.all["modi_flag"].value="yes";
	}else if($("add_flag")){
			document.all["add_flag"].value="yes";
	}		
	underDealWith();
	refreshForm('/xgxt/xljk_xsxlgcy.do?act=xsxlgcy&doType=' + doType);
}
	