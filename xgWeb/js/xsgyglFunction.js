//ѧ������ֲ�
function viewInfo(doType,url){
	var h = 800;
	var w = 600;
	if(url == "holiday_lsxx.do" || url == "qgzx_work_adjustInfo.do"){
		h = 700;
		w = 600;
	}
	
	var pkValue = "";
	if(doType == "del"){
		if(curr_row == null){
			alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
			return false;
		}else{
			if(confirm("ȷ��Ҫɾ������������")){
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += "?pkValue=";
				url += pkValue;
				url += "&doType=";
				url += doType;
				refreshForm(url);
				document.getElementById("search_go").click();
			}else{
				return false;
			}
		}
	}
	if(doType != "del"){
		url += "?doType=";
		url += doType;
		url += "&pkValue=";
		if(doType == "modi" ||(doType == "view" 
		                    || doType=="xshdjl")){
			if(curr_row == null){
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			}
			pkValue = curr_row.getElementsByTagName("input")[0].value;
			url += pkValue;			
		}
//		alert(url);
		showTopWin(url,h,w);
	}
}

//��������
function Savedata(mustFill,url){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	if($("sj_hour")){
		var hour = $("sj_hour").value;
		if(hour.match(/^\d+\.{0,1}\d{0,3}$/)==null || hour>24 || hour.length!=2){
			alert("�ʱ�������ʽ����ȷ��");
			document.getElementById('sj_hour').focus();
			return false;
		}
	}
	if($("sj_minute")){
		var minute = $("sj_minute").value;
		if(hour.match(/^\d+\.{0,1}\d{0,3}$/)==null || minute>60 || minute.length!=2){
			alert("�ʱ�������ʽ����ȷ��");
			document.getElementById('sj_minute').focus();
			return false;
		}
	}
	if($("sj_second")){
		var second = $("sj_second").value;
		if(second.match(/^\d+\.{0,1}\d{0,3}$/)==null || second>60 || second.length!=2){
			alert("�ʱ�������ʽ����ȷ��");
			document.getElementById('sj_second').focus();
			return false;
		}
	}
	if($("sjh")){
		var sjh = $("sjh").value;
		if(sjh!=""){
		    if(sjh.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			   alert("�ֻ������ʽ����ȷ!");
			   document.getElementById("sjh").focus();
			   return false;
		   }
		}
	}
	if($("qsdh")){
		var qsdh = $("qsdh").value;
		if(qsdh!=""){
		   if(qsdh.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			   alert("�绰�����ʽ����ȷ!");
			   document.getElementById("qsdh").focus();
			   return false;
		   }
		}
	}
	
	refreshForm(url);
}

function sflz(){
	var sfgh = document.getElementById("lzsj").value;
	if(sfgh == null || sfgh == ""){
		document.getElementById("sfgh").style.display = "";
	}
}

function cxInfo(tag){
	document.getElementById("kxqk").value = tag;
}

function checkLzf(){
	var lzsj = document.getElementById("lzsj").value;
	if(lzsj == null || lzsj == ""){
		document.getElementById("sfgh").style.display = "none";
	}else{
		document.getElementById("sfgh").style.display = "";
	}
}

function chkDormFpfs(flag){
	var fpfs = flag;
	var lddm = $("lddm").value;
	var cs = $("cs").value;
	var query = "";
	if(lddm == "" || lddm == null){
		lddm = "lddm";
	} else {
		lddm = "%" + lddm + "%";
	}
	if(cs == "" || cs == null){
		cs = "%";
	}else{
		cs = "%" + cs + "%";
	}
	query += fpfs;
	query += "!!-!!";
	query += lddm;
	query += "!!-!!";
	query += cs;
	document.getElementById("fpfs").value = fpfs;
	getOtherData.getDormInfo(query,TjDormInfo);
}

//ѡ��ѧԺ/רҵ/�༶����
function selBaseInfo(flag){
	if($(flag).value == "" || $(flag).value == null){
		$("search_suggest").style.display="none";
	}else{
		$("search_suggest").style.display="";
	}
	var xy = "";
	var zy = "";
	var bj = "";
	var nj = "";
	var query = "";
	if(flag == "xy"){
		if($("xy")){
			xy = $("xy").value;
			xy = (xy == " ") ? "%" : ("%"+xy+"%");
		}
		getOtherData.getXyInfo("search_suggest",xy,TjXyList);
	}else if(flag == "zy"){
		if($("xy")){
			xy = $("xy").value;
			xy = (xy == " ") ? "%" : ("%"+xy+"%");
		}
		if($("zy")){
			zy = $("zy").value;
			zy = (zy == " ") ? "%" : ("%"+zy+"%");
		}
		getOtherData.getZyInfo("search_suggest",xy,zy,TjXyList);
	}else{
		if($("nj")){
			nj = $("nj").value;
			nj = (nj == " ") ? "%" : ("%"+nj+"%");
		}
		if($("xy")){
			xy = $("xy").value;
			xy = (xy == " ") ? "%" : ("%"+xy+"%");
		}
		if($("zy")){
			zy = $("zy").value;
			zy = (zy == " ") ? "%" : ("%"+zy+"%");
		}
		if($("bj")){
			bj = $("bj").value;
			bj = (bj == " ") ? "%" : ("%"+bj+"%");
		}
		getOtherData.getBjInfo("search_suggest",nj,xy,zy,bj,TjXyList);
	}
}

//Mouse over function
function suggestOver(div_value) {
div_value.className = "suggest_link_over";
}
//Mouse out function
function suggestOut(div_value) {
	div_value.className = "suggest_link";
}

function setSearch(value) {
	$('xy').value = value;
	$('search_suggest').innerHTML = '';
	$("search_suggest").style.display="none";
}

///////////////////////Ajax �ύ����////////////////////////////////

//�ύ����Ϣ
function TjDormInfo(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("oracleList");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("oracleList");
			DWRUtil.addOptions("oracleList",data,"en","cn");		
		}
		
		if(document.getElementById("oracleList").options.length == 0){
			return false;
		}
		var syssList = document.getElementById("oracleList").options[0].value.split("/");
		var syssLe = syssList.length;
		var yfpssList;
		if(syssLe == 2){
			for(var i=0; i<document.getElementById("oracleList").options.length; i++){
				syssList = document.getElementById("oracleList").options[i].value.split("/");
				for(var j=0; j<document.getElementById("sql").options.length; j++){
					yfpssList = document.getElementById("sql").options[j].value.split("/");
					if(syssList[0] == yfpssList[1]){
						document.getElementById("oracleList").options[i] = null;
						i = i-1;
						break;
					}
				}
			}
		}else{
			for(var i=0; i<document.getElementById("oracleList").options.length; i++){
				syssList = document.getElementById("oracleList").options[i].value.split("/");
				for(var j=0; j<document.getElementById("sql").options.length; j++){
					yfpssList = document.getElementById("sql").options[j].value.split("/");
					if(yfpssList.length == 4){
						if(syssList[0] == yfpssList[1]){
							document.getElementById("oracleList").options[i] = null;
							i = i-1;
							break;
						}
					}else{
						if(syssList[0] == yfpssList[1] && syssList[2]==yfpssList[4]){
							document.getElementById("oracleList").options[i] = null;
							i = i-1;
							break;
						}
					}
				}
			}
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

function TjXyList(data){
	var objId =data[0];
	if(data[1] == null && typeof data[1] == 'object'){
		$(objId).innerHTML = '';
	    $("search_suggest").style.display="none";
		return false;
	}	
	$(objId).innerHTML = '';
	if( data[1].length==0){
    	$("search_suggest").style.display="none";	
    }else{
    	$("search_suggest").style.display="";	
    }
    var suggest = '<div style="overflow-y:auto;height:150px;">';
    for(i=0; i < data[1].length; i++) {
     suggest += '<div onmouseover="javascript:suggestOver(this);" ';
     suggest += 'onmouseout="javascript:suggestOut(this);" ';
     suggest += 'onclick="javascript:setSearch(this.innerHTML);" ';
     suggest += 'class="suggest_link">' +data[1][i].dm + '</div>';
    }
    suggest += '</div>';
    $(objId).innerHTML += suggest;	
}

//function LdDisabled(ele) {
//	   var userType = document.forms[0].userType.value;
//	   var xxdm = document.forms[0].xxdm.value;	
//	   var isGyFdy = document.forms[0].isGyFdy.value;
//	   if(isGyFdy=="yes"){
//	   if (userType!="admin") {
//		     var tmp = ele.split("-");
//		     for (i = 0; i < tmp.length; i++) {
//			   document.getElementById(tmp[i]).disabled = true;
//		   }
//		if($("V_lddm")!=null&&$("V_lddm").value==""){  
//		   getLcList();
//		  }else if($("V_lddm")!=null&&$("V_lddm").value!=""){
//		  	document.getElementById('qsh').disabled =true; 
//		  }
//	    }
//	  }  	    
//  } 

//��ȡ�������¥���б�
function initSsFpLdList(){
    //dataLoad(true);
	var xqdm = "";
	var xbxd = "";	
	if($("xq")){xqdm = $("xq").value};
	if($("xb")){xbxd = $("xb").value};			
		getOtherData.getSsFpLdList(xqdm,xbxd,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "ld";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");	
					$(objId).options[0].value = "";	
				}
			}
		});
	//dataLoad(false);	
}

//��ȡ�������������Ϣ�б�
function initSsFpSsXxList2(){
	var lddm = "";
	var cs = "";
	var xqdm = "";
	var xb ="";
	var fpfs = "";
	if($("ld")){lddm = $("ld").value};
	if($("cs")){cs = $("cs").value};
	if($("xq")){xqdm = $("xq").value};
	if($("xb")){xb = $("xb").value};
	///if($("fpfs")){fpfs = $("fpfs").value};
		getOtherData.getSsFpSsXxList2(xqdm,xb,lddm,cs,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					$(objId).options[0].value = "";
										
				}
			}
		});
}

//��ȡ���Ữ��������Ϣ�б�(����ְҵ����ѧԺ)
function initSsFpSsXxList(){
    //dataLoad(true);
    var objId = "oracleList";
    var show = objId+"show";   
    showDivWait(show,'10%','50%');	
	var lddm = "";
	var cs = "";
	var xqdm = "";
	var xb ="";
	var fpfs = "";
	if($("ld")){lddm = $("ld").value};
	if($("cs")){cs = $("cs").value};
	if($("xq")){xqdm = $("xq").value};
	if($("xb")){xb = $("xb").value};
	
	if(document.forms[0].xxdm.value == "12872"){//����ְҵ����ѧԺ
	   if($("fpfs").value=="acw"&&cs==""){//����λ����ʱ��Ϊ����ˢ�¹���û��Ҫ���ݴ������л���������ѡ����
	       alert("����ѡ���ţ�");
	       document.forms[0].fpfs[0].selected=true;
	       return false;
	   }	  
	   if($("fpfs")){fpfs = $("fpfs").value};
	   getOtherData.getSsFpSsXxList(xqdm,xb,lddm,cs,fpfs,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					if($(show)){	
		              $(show).style.display= "none";
	                }	 
				}
			}
		});		 
	}else{
	   getOtherData.getSsFpSsXxList(xqdm,xb,lddm,cs,"ass",function initTjList(data){
			if (data != null && typeof data == 'object') {
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					
					//--------2011.4.25 edit by ³��----begin--------------------
					//��ԭ����DWR���������б��ΪƴHTML�ķ�ʽ
					//ע���������ַ������ԣ�ƴHTML��ʽ�ٶ����
					 var html = new Array(); 
					 for (var i = 0 ; i < data.length; i++){
						 html[i] = ['<option value="', data[i].dm, '">', data[i].mc, '</option>'].join("");
					 }
					 var selectHtml = '<select name="oracleItem" style="width:100%;" size="27" id="oracleList" multiple="multiple">';
					 $('wfpDiv').innerHTML = selectHtml+html.join("")+"</seclet>";
					//--------2011.4.25 edit by ³��----end--------------------
					 
					if($(show)){	
		              $(show).style.display= "none";
	                }		
				}
			}
		});
	}
	//dataLoad(false);
}
//��ȡ���Ữ���ѷ���������Ϣ�б�
function initSsFpFpSxXxList(){
    var objId = "sql";
    var show = objId+"show";
    showDivWait(show,'70%','50%');	
	var lddm = "";
	var xq = "";
	var xydm = "";
	var bjdm = "";	
	var cs = "";
	var nj = "";
	if($("ld")){lddm = $("ld").value};
	if($("nj")){nj = $("nj").value};
	if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("cs")){cs = $("cs").value};
		getOtherData.getSsFpFpSsXxList(lddm,cs,nj,xydm,bjdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				//var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					//DWRUtil.removeAllOptions(objId);			
					//DWRUtil.addOptions(objId,data,"dm","mc");	
					//----------edit by ³�� 2011.4.26------begin-----
					 var html = new Array(); 
					 for (var i = 0 ; i < data.length; i++){
						 html[i] = ['<option value="', data[i].dm, '">', data[i].mc, '</option>'].join("");
					 }
					 var selectHtml = '<select property="sql" style="width:100%;" size="27" id="sql" multiple="multiple">';
					 $('yfpDiv').innerHTML = selectHtml+html.join("")+"</seclet>";
					 //-------------------end--------------------
					 
					 oldCondiSqlVConn();
					 if($(show)){	
			            $(show).style.display= "none";
		             }			
				}
			}
		});		
}

function initSsFpZsData(){
	var xydm = "";
	var bjdm = "";	
	var nj = "";
	var xqdm = "";
	if($("xq")){xqdm = $("xq").value};
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
    getOtherData.getSsFpZsData(xqdm,xydm,bjdm,nj,function(data){
       if(data!=null){
        allbody.innerText=data[0];
       	allboy.innerText=data[1];
       	allgirl.innerText=data[2];
       }
    });	
}
//��ȡ���Ữ��¥���б�
function initSsFpFpCsList(){
    
	var lddm = "";	
	if($("ld")){lddm = $("ld").value};;
		getOtherData.getSsFpCsList(lddm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "cs";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					$(objId).options[0].value = "";				
				}
			}		
		});
}
//��ȡ��λ����У���б�
function initCwFpXqListt(){
	var xydm = "";
	var nj  = "";
	var fpfs = "";
	var bjdm = "";
	if($("xy")){xydm = $("xy").value};
	if($("nj")){nj = $("nj").value};
	if($("bj")){bjdm = $("bj").value};
	if($("fpfs")){fpfs = $("fpfs").value};
		getOtherData.getCwFpXqList(xydm,fpfs,nj,bjdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "xq";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");											   
				    DWRUtil.removeAllOptions("ld");
				    DWRUtil.removeAllOptions("cs");
				    DWRUtil.removeAllOptions("oracleList");				    	
				}
			}
		});
}

//��ȡ��λ����¥���б�
function initCwFpLdList(){
	var xqdm = "";
	var xydm = "";
	var xb = "";
	var nj  = "";
	var cwfp = "noCheck";
	if($("cwfp")){		
    	var checkBox = document.getElementsByName("cwfp");
    	if(checkBox[0].checked){cwfp="checked"};	
	}
	if($("xq")){xqdm = $("xq").value};
	if($("xy")){xydm = $("xy").value};
	if($("xb")){xb = $("xb").value};
	if($("nj")){nj = $("nj").value};
		getOtherData.getCwFpLdList(xqdm,xydm,xb,cwfp,nj,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "ld";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					$(objId).options[0].value = "";
				}
			}
		});
}

//��ȡ��λ����¥���б�
function initCwFpFpCsList(){
	var lddm = "";	
	var nj  = "";
	var fpfs = "";
	var xydm = "";
	if($("ld")){lddm = $("ld").value};
	if($("nj")){nj = $("nj").value};
	if($("fpfs")){fpfs = $("fpfs").value};
	if($("xydm")){xydm = $("xydm").value};
		getOtherData.getcwFpCsList(lddm,nj,xydm,fpfs,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "cs";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
										
				}
			}		
		});
}


//��ȡ��λ�������ᴲλ��Ϣ�б�
function initCwFpSsCwXxList(releaseOptionValues,releaseOptionTexts){	
	
    var objId = "oracleList";
    var show = objId+"show";
    showDivWait(show,'6%','50%');	
	var xqdm = "";
	var xydm = "";
	var xb = "";
	var lddm = "";
	var cs = "";
	var nj  = "";
	var bjdm = "";
	var cwfp = "noCheck";
	if($("cwfp")){
		var checkBox = document.getElementsByName("cwfp");
    	if(checkBox[0].checked){cwfp="checked"};
	}	
	if($("xq")){xqdm = $("xq").value};
	if($("xy")){xydm = $("xy").value};
	if($("xb")){xb = $("xb").value};
	if($("ld")){lddm = $("ld").value};
	if($("cs")){cs = $("cs").value};
	if($("nj")){nj = $("nj").value};
	if($("bj")){bjdm = $("bj").value};
	
	//----------edit by ³�� 2011.4.26--------begin-----
	dwr.engine.setAsync(false);
	getGyglDAO.getCwFpSsCwXxList(xqdm,xydm,lddm,xb,cs,cwfp,nj,bjdm,releaseOptionValues,releaseOptionTexts,function(data){
		
		if (data != null && typeof data == 'object') {				
			//var objId = "oracleList";
			
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				//DWRUtil.removeAllOptions(objId);			
				//DWRUtil.addOptions(objId,data,"dm","mc");	
				 var html = new Array(); 
				 for (var i = 0 ; i < data.length; i++){
					 html[i] = ['<option value="', data[i].dm, '">', data[i].mc, '</option>'].join("");
				 }
				 var selectHtml = '<select property="oracleItem" style="width:100%;" size="27" multiple="multiple" id="oracleList">';
				 $('wfpDiv').innerHTML = selectHtml+html.join("")+"</seclet>";
				//---------------end--------------------------
			}
		}
		if($(show)){	
		  $(show).style.display= "none";
		}
	});
	dwr.engine.setAsync(true);
}
//��ȡ��λ����������
function initCwFpZsData(){
	var xydm = "";
	var bjdm = "";	
	var nj = "";
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
    getOtherData.getCwFpZsData(xydm,nj,bjdm,function(data){
       if(data!=null){
        allbody.innerText=data[0];
       	allboy.innerText=data[1];
       	allgirl.innerText=data[2];
       }
    });	
}

//��ȡ��λ�����ѷ������ᴲλ��Ϣ�б�
function initCwFpFpCwList(){
	var objId = "sql";
    var show = objId+"show";
    showDivWait(show,'70%','50%');	
	var xydm = "";
	var bjdm = "";	
	var nj = "";
	var xb = "";
	var cwfp = "noCheck";
	var lddm = "";
	if($("cwfp")){
		var checkBox = document.getElementsByName("cwfp");
    	if(checkBox[0].checked){cwfp="checked"};
	}
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
	if($("xb")){xb = $("xb").value};
	if($("ld")){lddm = $("ld").value};
	
	getOtherData.getCwFpFpCwList(xydm,nj,bjdm,xb,cwfp,lddm,function initTjList(data){
			if (data != null && typeof data == 'object') {				
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");							
					oldCondiSqlVConn();										
				}
			}
			if($(show)){	
			  $(show).style.display= "none";
			}
		});
}

function getGyJcList(str,nums){
     if($("xxdm").value!='12872' && $("xxdm").value!='11641'){
     return true;
     }else{
     var jctag = "";
     var num = "";
     var jcType = "";
     if(nums!=null){
         jcType = "jl";
         num = nums+1;  
     }else{
         jctag =str.name;
         num =jctag.replace('jc','');
         jcType =str.value;
     }   
     GetListData.GetGyJcList(jcType,function initTjList(data){
        if (data != null && typeof data == 'object') {
        var objId = "nr"+num;
        if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");							
				}
        }
     });
     }
}

//��ȡ��λ�ѷ���������
function initCwYfpZsData(){
	var xydm = "";
	var bjdm = "";	
	var nj = "";
    if($("xy")){xydm = $("xy").value};
    //if($("zy")){zydm = $("zy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
    getOtherData.getCwYfpZsData(xydm,nj,bjdm,function(data){
       if(data!=null){
        totalBed.innerText=data[0];
       	boyBed.innerText=data[1];
       	girlBed.innerText=data[2];
       }
    });	
}

function toSave() {
    var xhV = "";
    if($("xhV")){
	     xhV=$("xhV").value;
	}
	var lddm = document.getElementById("lddm").value;
	var qsh  = document.getElementById("qsh").value;
	var cwh  = document.getElementById("cwh").value;
	var xh   = document.getElementById("xh").value;
	var sfjh = "";
	
	//2010/7/1 edit by luojw
	var yzsscw = $("yzsscw").value;
	var isZzcw = false;
	if(yzsscw == lddm+qsh+cwh){
		isZzcw = true;
	}
	if($("sfjh")){
	     if($("sfjh").checked){
	        sfjh = "yes";
	     }
	}
	if(xh==xhV&&sfjh=="yes"){
	   alert("�����Ƿ������ܺ��Լ�������λ!");
	   return false;
	}else{
	  var pkValue = lddm+"-"+qsh+cwh;
	  dwr.engine.setAsync(false);
	  getSztzData.getDataEx("xszsxxb","ssbh||cwh",pkValue,function(str){ 
	  			if(isZzcw){
	  				 dataDoSave('xh-xn-xq-lddm-qsh-ydsj-lc-cwh');
	  			}else{
		         if(str!=""){
		           if(sfjh=="yes"){
		              if(confirm("���ȷ�����춯ǰ�����ᴲλ���Զ����н������Ƿ�Ҫ������")){
		                   dataDoSave('xh-xn-xq-lddm-qsh-ydsj-cwh');
		              }else{
		                   return false;
		              }
		           }else{
                      alert("��ѡ�춯�����ᴲλ��������ס,�ݲ��ܽ����춯��");
                      return false;		           
		           }	          
		         }else{	
		           	if(confirm("ȷ��Ҫ�������ᴲλ�춯��")){         
		               dataDoSave('xh-xn-xq-lddm-qsh-ydsj-lc-cwh');
		            }else{
		               return false;
		            }
		         }
		       }
      });	
      dwr.engine.setAsync(true);
    }	
}


//��ȡ��λ����ѧ���б�
function initCwFpXsList(){		
    var objId = "xh";
    var show = objId+"show";
    showDivWait(show,'30%','50%');
	var xydm = "";
	var bjdm = "";	
	var nj = "";
	var xb = "";
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
	if($("xb")){xb = $("xb").value};
	
	var ksh = "";
	if($("ksh")){
		ksh = $("ksh").value;
	}
	
	if(ksh == ""){
	getOtherData.getCwFpSsXsList(xydm,nj,bjdm,xb,function initTjList(data){
			if (data != null && typeof data == 'object') {				
				var objId = "xh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){					
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");												
				}
			}
          if($(show)){
				$(show).style.display= "none";
			}			
	});
	}
}

//��Ԣά�޸�������Ϣ�б�
function getGyWxNrFzBmList(str,nums){
        var wxnrdm = $("wxnr").value;
        getOtherData.GegGyWxNrFzBmList(wxnrdm,function initTjList(data){
        if (data != null && typeof data == 'object') {
				var objId = "fzbm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					//$(objId).options[0].value = "";
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                            }
						}
					  }
				  }
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
     });
 }
 
 /**
 	ȡ��֮ǰ���Ѿ�����Ĵ�λ���ݵ�sqlValue������
 */
 function oldCondiSqlVConn(){
	var sqlValue = new Array();
	document.forms[0].conditionSqlValue.value = "";
	
	for (i = 0; i < document.forms[0].sql.options.length; i++) {				
		sqlValue[sqlValue.length] = document.forms[0].sql.options[i].value;	
	}
	document.forms[0].oldCondiSqlValue.value = sqlValue;
}

function bedDistSave(){
  hiddenField();
  showTips();
  saveConditionSql();
  refreshForm('/xgxt/bed_distribute.do?doType=save')
}

function beforSubmit() {
   if (compartStatus) {
		if (confirm("��ǰ�ѷ�������б����˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
	         bedDistSave();
	    } else {
			refreshForm("/xgxt/bed_distribute.do");
		}
	}
}

function dataIinit(){
     $("xb").value=""; //�����Ա�ֵΪ��	
     var ld = document.forms[0].ld;
     var cs = document.forms[0].cs;
    	
     for (i=1;i<ld.length;i++) {	//���¥�����б�  
        ld.options[i]=null;
     }
     for (i=1;i<cs.length;i++) {	//��ղ���б�  
        cs.options[i]=null;
     }
            
}
function addBatchColum(){
      if(document.forms[0].nj.value==null||document.forms[0].nj.value==""){
        alert("����ѡ���꼶��");
        return false;
      } 
       if(document.forms[0].xydm.value==null||document.forms[0].xydm.value==""){
        alert("����ѡ��"+jQuery("#xbmc").val()+"��");
        return false;
      }
     var nj  = document.forms[0].nj.value;
     var xyV  = document.forms[0].xydm.value;
     var oraObject =  document.getElementById("oracleList");
     if(oraObject.options.length==0){
         alert("\'δ��������\'�б�Ϊ�գ�");
         return false;
     }         
     if(document.forms[0].xxdm.value == "12872"){//����ְҵ����ѧԺ
           if(document.forms[0].bjdm.value==null||document.forms[0].bjdm.value==""){
        	   alert("����ѡ��༶��");
        	   return false;
           }        
           var bjIndex = document.forms[0].bj.selectedIndex;
      	   var bjV = document.forms[0].bj.options[bjIndex].value;
	  	   var bjT = document.forms[0].bj.options[bjIndex].text;
	  	   var oraV = "";
           var oraT = "";   
           var count = 0; 
           var n = document.forms[0].sql.options.length;
           for(var i=0;i<oraObject.options.length;i++){
		      if(oraObject.options[i].selected){
			     count++;
		      }
	       }
	       if(count==0){
		     alert('�������\'δ��������\'�б���ѡ��һ���������¼��');
		     return false;
	       }
	       for(i=0;i<oraObject.options.length;i++){
              if(oraObject.options[i].selected){
                oraV=oraObject.options[i].value;
                oraT=oraObject.options[i].text;  //alert(nj+"/"+xyV+'/'+bjV+'/'+oraV);     
                document.forms[0].sql.options[n++] = new Option(bjT+'/'+oraT,nj+"/"+xyV+'/'+bjV+'/'+oraV);
                document.forms[0].oracleList.options[i]=null;
                i--;  
                compartStatus = true;                    
              }
           }    
      }else{
           var czfs = document.forms[0].fpfs.value;
           if(czfs=='ass'){//������
                var xyIndex = document.forms[0].xy.selectedIndex;
                var nj  = document.forms[0].nj.value;
                var xyV = document.forms[0].xy.options[xyIndex].value;
                var xyT = document.forms[0].xy.options[xyIndex].text; 
                var oraV = "";
                var oraT = "";   
                var count = 0; 
                var n = document.forms[0].sql.options.length;
                for(var i=0;i<oraObject.options.length;i++){
		            if(oraObject.options[i].selected){
			           count++;
		            }
	            }
                if(count==0){
		           alert('�������\'δ��������\'�б���ѡ��һ���������¼��');
		           return false;
	            }
                for(i=0;i<oraObject.options.length;i++){
                    if(oraObject.options[i].selected){
                    oraV=oraObject.options[i].value;
                    oraT=oraObject.options[i].text;        
                    document.forms[0].sql.options[n++] = new Option(nj+'/'+xyT+'/'+oraT,xyV+'/'+oraV+'/'+nj);
                    document.forms[0].oracleList.options[i]=null;
                     i--;                    
                    }
                 }  
                 compartStatus = true;
           }else{//����λ
                var count = 0; 
                var n = document.forms[0].sql.options.length;
                for(var i=0;i<oraObject.options.length;i++){
		            if(oraObject.options[i].selected){
			           count++;
                       if(count>=2)break;
		            }
	            }
                if(count==0){
		           alert('�������\'δ��������\'�б���ѡ��һ����¼��');
		           return false;
	            }
	            if(count>=2){
	               alert('\'����λ��\'���ַ�ʽ�£�ֻ��ѡ��һ����¼��');
		           return false;
	            }
	            
	            var oracleListIndexCw = document.forms[0].oracleList.selectedIndex;
    	        var acwValue = document.forms[0].oracleList.options[oracleListIndexCw].text;
    	        var qssycws = acwValue.substring(acwValue.lastIndexOf('/')+1);       
                showTopWin('/xgxt/acws_DormDis.do?qssycws='+qssycws+"&nj="+nj+"&addordel=add",400,250);
                return false;
                compartStatus = true;	            
           }
      }      
}

function delBatchColum(){    
    var oraObject =  document.getElementById("oracleList");
    var sqlObject =  document.getElementById("sql");
    var n = document.forms[0].oracleList.options.length;
    var m = document.forms[0].sql.options.length;
    var count = 0;
    for(var i=0;i<sqlObject.options.length;i++){
		if(sqlObject.options[i].selected){
			count++;
		}
	}
	if(m==0){
		alert('\'�ѻ������\'�б�Ϊ�գ�');
		return false;
	}	
	if(document.forms[0].xxdm.value == "12872"){//����ְҵ����ѧԺ
       for(i=0;i<sqlObject.options.length;i++){
           if(sqlObject.options[i].selected){
               sqlV = sqlObject.options[i].value;
               sqlT = sqlObject.options[i].text;
               sqlVArr = sqlV.split('/');
               if(sqlVArr.length==5){
                  sqlV = sqlVArr[sqlVArr.length-2]+'/'+sqlVArr[sqlVArr.length-1];
                  sqlT = sqlT.substr(sqlT.indexOf("/")+1,sqlT.length);
               }
               if(sqlVArr.length==6){
                  sqlV = sqlVArr[sqlVArr.length-3]+'/'+sqlVArr[sqlVArr.length-2]+'/'+sqlVArr[sqlVArr.length-1];
                  sqlT = sqlT.substr(sqlT.indexOf("/")+1,sqlT.length);
               }
                           
               document.forms[0].oracleList.options[n++] = new Option(sqlT,sqlV);
               document.forms[0].sql.options[i]=null;
              i--;
               compartStatus = true;
          }
       }    
   }else{
       var czfs = document.forms[0].fpfs.value;      
       if(czfs=='ass'){//������
          var count = 0;
          for(var i=0;i<sqlObject.options.length;i++){
		     if(sqlObject.options[i].selected){
			 count++;
		     }
	      }
	      if(count==0){
		    alert('�����ұ�\'�ѻ������\'�б���ѡ��һ���������¼��');
		    return false;
	      }
	      for(i=0;i<sqlObject.options.length;i++){
              if(sqlObject.options[i].selected){
                  
                 var sqlV = sqlObject.options[i].value;//sqlV���з�ʽ��ѧԺ����/������/���䴲λ��/�꼶
                 var sqlT = sqlObject.options[i].text;//sqlT���з�ʽ���꼶/ѧԺ����/¥������/¥��/���Һ�//�ܴ�λ��/���䴲λ��
                              
                 var sqlVArr = sqlV.split('/');
                 var sqlTArr = sqlT.split('/');
                 
                 for(j=0;j<oraObject.options.length;j++){
                     var oraV = oraObject.options[j].value;//oraV���з�ʽ:������/ʣ�ലλ��
                     if(oraV.substr(0,oraV.indexOf('/'))==sqlVArr[1]){
                        sqlVArr[2] =  parseInt(sqlVArr[2])+parseInt(oraV.substr(oraV.indexOf('/')+1,oraV.length));
                     }
                 }                       
                 document.forms[0].oracleList.options[n++] = new Option(sqlT.substr(sqlT.indexOf('/')+1,sqlT.length).substr(sqlT.substr(sqlT.indexOf('/')+1,sqlT.length).indexOf("/")+1,sqlT.substr(sqlT.indexOf('/')+1,sqlT.length).length),sqlVArr[1]+'/'+sqlVArr[2]);
                 document.forms[0].sql.options[i]=null;
                 i--;                
             }
          }
          compartStatus = true;    
       }else{//����λ
                var count = 0;                               
                for(var i=0;i<sqlObject.options.length;i++){
		            if(sqlObject.options[i].selected){
			           count++;
                       if(count>=2)break;
		            }
	            }
                if(count==0){
		           alert('�����ұ�\'�ѻ������\'�б���ѡ��һ����¼��');
		           return false;
	            }
	            if(count>=2){
	               alert('\'����λ��\'���ַ�ʽ�£�ֻ��ѡ��һ����¼��');
		           return false;
	            }	            
	            var sqlIndexCw = document.forms[0].sql.selectedIndex;
    	        var acwValue = document.forms[0].sql.options[sqlIndexCw].text;
    	        var nj = acwValue.substring(0,acwValue.indexOf('/'));
    	        var qssycws = acwValue.substring(acwValue.lastIndexOf('/')+1);       
                showTopWin('/xgxt/acws_DormDis.do?qssycws='+qssycws+"&nj="+nj+"&addordel=del",380,200);
                return false;
       }
   } 
}

function AcwsFpSend(){
    compartStatus = true; 
    var cws = document.getElementById('cws').value;  
    var addordel  = document.getElementById('addordel').value;
	window.dialogArguments.document.getElementById('addordel').value=addordel;
	window.dialogArguments.document.getElementById('cws').value=cws;
	close();
	window.dialogArguments.document.getElementById('acwsfp').click();
 }

function AcwsAddOrDel(){  
        var addordel =  document.getElementById('addordel').value;   
    	var cws = document.getElementById('cws').value;    	     
	 	if(addordel=="add"){//����λ������
	 	     var nj       = document.getElementById('nj').value;
		     var oracleListIndex = document.forms[0].oracleList.selectedIndex;
    	     var acwValue = document.forms[0].oracleList.options[oracleListIndex].text;
    	     var qssycws  = acwValue.substring(acwValue.lastIndexOf('/')+1);
    	     var xyIndex  = document.forms[0].xy.selectedIndex;      	    	
      	     var xyV      = document.forms[0].xy.options[xyIndex].value;     	
	 	     var xyT      = document.forms[0].xy.options[xyIndex].text;
	 	     var qssycws1    = parseInt(qssycws)-parseInt(cws);
      	     var addOracleV  = document.forms[0].oracleList.options[oracleListIndex].value;//addOracleV���з�ʽ�� ¥���������Һ�/ʣ�ലλ��    	
	  	     var addOracleT  = document.forms[0].oracleList.options[oracleListIndex].text;//addOracleV���з�ʽ��	������/ʣ�ലλ��  	
	  	     var addOracleT1 = addOracleT.substring(0,addOracleT.lastIndexOf('/'))+'/'+cws;	  	
	 	     var addOracleV1 = addOracleV.substring(0,addOracleV.indexOf('/')+1)+cws;	 		
	  	     var addOracleT2 = addOracleT.substring(0,addOracleT.lastIndexOf('/'))+'/'+qssycws1;	  	
	 	     var addOracleV2 = addOracleV.substring(0,addOracleV.indexOf('/')+1)+qssycws1;	 		 		
	         document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(nj+'/'+xyT + "/" + addOracleT1,xyV + "/" + addOracleV1+"/"+nj);    	
     	     if(qssycws1=='0'){
     	 	     document.forms[0].oracleList.options[oracleListIndex]=null;
     	     }else{
     	 	     document.forms[0].oracleList.options[oracleListIndex]=new Option(addOracleT2,addOracleV2);
     	     } 
     	}else  if(addordel=="del"){//����λ���ͷ�
     	     var sqlIndex = document.forms[0].sql.selectedIndex;
     	     var acwValue = document.forms[0].sql.options[sqlIndex].text;
     	     var yfpcws   = acwValue.substring(acwValue.lastIndexOf('/')+1);
     	     var yfpcws1  = parseInt(yfpcws)-parseInt(cws); 
     	     var sqlV     = document.forms[0].sql.options[sqlIndex].value;//sqlV���з�ʽ��ѧԺ����/������/���䴲λ��/�꼶     	
	  	     var sqlT     = document.forms[0].sql.options[sqlIndex].text;//sqlT���з�ʽ���꼶/ѧԺ����/¥������/¥��/���Һ�/�ܴ�λ��/���䴲λ��
	  	     var sqlT1 = sqlT.substring(0,sqlT.lastIndexOf('/'))+'/'+cws;	  	
	 	     var sqlV1 = sqlV.substring(0,sqlV.indexOf('/')+1)+cws;	  	
 	         var sqlT2 = sqlT.substring(0,sqlT.lastIndexOf('/'))+'/'+yfpcws1;	
 	         
 	         var sqlV2Arr = sqlV.split('/');  	
	 	     var sqlV2 = sqlV2Arr[0]+'/'+sqlV2Arr[1]+'/'+yfpcws1+'/'+sqlV2Arr[3];
	 	     
	 	     var oraVArr  = sqlV.split('/');
	 	     var oraTArr  = sqlT.split('/'); 
	 	     document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = 
	 	       new Option(oraTArr[2]+'/'+oraTArr[3]+'/'+oraTArr[4]+'/'+oraTArr[5]+'/'+cws,oraVArr[1]+'/'+cws);    	
	 	     if(yfpcws1=='0'){
     	 	     document.forms[0].sql.options[sqlIndex]=null;
     	     }else{
     	 	     document.forms[0].sql.options[sqlIndex]=new Option(sqlT2,sqlV2);
     	     } 
     	}
     	compartStatus = true;
     	close();
  }



function beforSSFPSubmit() {
   var clinText = "";
   var xyText  = document.forms[0].xydm.options[document.forms[0].xydm.selectedIndex].text;
   if(document.forms[0].xxdm.value == "12872"){//����ְҵ����ѧԺ
       var bjText = document.forms[0].bjdm.options[document.forms[0].bjdm.selectedIndex].text;
       clinText = bjText + "��";
   }else{
       clinText = xyText ;
   }
   if (compartStatus) {
		if (confirm(clinText+" �ѻ�������б����˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
	         dormDistSave();
	    } else {
			refreshForm("/xgxt/dorm_distribute.do");
		}
	}
}

function dormDistSave(){
  hiddenField();
  showTips();
  saveConditionSql();
  refreshForm('/xgxt/dorm_distribute.do?doType=save')
}

function addCwBatchColum(){//��λ����          
        var oraObject =  document.getElementById("oracleList");
        var xh= document.getElementById("xh");            
        var countCw = 0;
        var countXh = 0; 
        var n = document.forms[0].sql.options.length;
        
        for(var i=0;i<oraObject.options.length;i++){
		    if(oraObject.options[i].selected){
			  countCw++;
		    }
	    }
	    if(countCw==0){
		   alert('�������\'δ���䴲λ\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    for(var i=0;i<xh.options.length;i++){
		    if(xh.options[i].selected){
			  countXh++;
		    }
	    }
	    if(countXh==0){
		   alert('�������\'δ����ѧ��\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    if(countCw!=countXh){
	       alert('δ���䴲λ��δ����ѧ���б��У���ѡ���¼����һ�£�');
		   return false;
	    }else{
	    	//showMessage('showDiv',true,'#C7DEFC');    
	    	viewTempDiv('��סʱ������','showDiv',280,160);	    	    	        	
       }     
}
function addCwColum(){
        var zsrq = "";
        if($("zsrq"))zsrq=$("zsrq").value;
        if(zsrq==""){
           alert("��������סʱ�䣡");
           return false;
        }
        hiddenMessage(true,true);
        var oraObject =  document.getElementById("oracleList");
	    var xh= document.getElementById("xh"); 
	    var cwVarr =  Array();
	    var cwTarr =  Array();	
	    var xhVarr =  Array();
	    var xhTarr =  Array();
	    var j = 0; 
	    var k = 0; 
	    var n = document.forms[0].sql.options.length;
	    var countXh = 0; 
	    for(var i=0;i<xh.options.length;i++){
		    if(xh.options[i].selected){
			  countXh++;
		    }
	    }
	    	  	  
	    for(i=0;i<oraObject.options.length;i++){
            if(oraObject.options[i].selected){                                           
                 cwVarr[j]=document.forms[0].oracleList.options[i].value;
                 cwTarr[j]=document.forms[0].oracleList.options[i].text;
                 document.forms[0].oracleList.options[i]=null;
                 i--;
                 j++;                
            }
        }  
        for(i=0;i<xh.options.length;i++){
            if(xh.options[i].selected){                                           
                 xhVarr[k]=document.forms[0].xh.options[i].value;
                 xhTarr[k]=document.forms[0].xh.options[i].text;
                 document.forms[0].xh.options[i]=null;
                 i--;
                 k++;                
            }
        }
        for (i = 0; i < countXh; i++) {     	
        	 //alert(xhTarr[i].substring(xhTarr[i].lastIndexOf("/")+1));
        	 //alert(cwTarr[i].substring(cwTarr[i].lastIndexOf("/")+1));
        	 if(xhTarr[i].substring(xhTarr[i].lastIndexOf("/")+1)=="���"||(xhTarr[i].substring(xhTarr[i].lastIndexOf("/")+1)==cwTarr[i].substring(cwTarr[i].lastIndexOf("/")+1))){
        		 document.forms[0].sql.options[n++] = new Option(xhTarr[i]+'/'+cwTarr[i]+'/'+zsrq,xhVarr[i]+'/'+cwVarr[i]+'/'+zsrq);                                                       
        	 }else{
        		 alert("�Ա�ƥ�����");
        	 }
        }
         compartStatus = true;      
} 
function closeClear() {  
    $("kfbtnSave").disabled=true;
    $("kfbtnClose").disabled=true;	
}
function delCwBatchColum(){    
    var sqlObject =  document.getElementById("sql");
    var n = document.forms[0].oracleList.options.length;
    var m = document.forms[0].xh.options.length;
    var count = 0;
    var d = sqlObject.options.length;
    for(var i=0;i<d;i++){
		if(sqlObject.options[i].selected){
			count++;
		}
	}
	if(count==0){
		alert('�����ұ�\'�ѷ������\'�б�����ѡ��һ���������¼��');
		return false;
	}
	
	var releaseOptionValues = new Array();
	var releaseOptionTexts = new Array();
	
    for(var i=0;i<d;i++){
         if(null!= sqlObject.options[i] && sqlObject.options[i].selected){
              sqlV = sqlObject.options[i].value;
              sqlT = sqlObject.options[i].text;
              sqlTArr = sqlT.split('/');                                 
              document.forms[0].xh.options[m++] = new Option(sqlTArr[0]+'/'+sqlTArr[1]+'/'+sqlTArr[2],sqlV.substr(0,sqlV.indexOf('/')));
              document.forms[0].oracleList.options[n++] = new Option(sqlTArr[3]+'/'+sqlTArr[4]+'/'+sqlTArr[5],sqlV.substr(sqlV.indexOf("/")+1,sqlV.length));    
              releaseOptionValues[i] = sqlV.substr(sqlV.indexOf("/")+1,sqlV.length);
              releaseOptionTexts[i] = sqlTArr[3]+'/'+sqlTArr[4]+'/'+sqlTArr[5];
              document.forms[0].sql.options[i]=null;
              i--;              
         }
    }   
    compartStatus = true; 
    
    //���ͷŵĺ�δ����Ĵ�λ���ص�һ������
    //�����ˣ��������ٴ���ֿ���ס��--edit by ³��----2011.4.26-
   // initCwFpSsCwXxList(releaseOptionValues,releaseOptionTexts);
}

function ssfpTj(){
    var xydm = "";
	var bjdm = "";	
	var nj = "";
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};	
	getOtherData.getCwFpZsData(xydm,nj,bjdm,function(data){//δ����
       if(data!=null){
        allbody.innerText=data[0];
       	allboy.innerText=data[1];
       	allgirl.innerText=data[2];
       }
    });
    getOtherData.getSsFpYhfcws(nj,xydm,bjdm,function initArray2(data){
		$('totalBed').innerText = data[0];
		$('boyBed').innerText = data[1];
		$('girlBed').innerText = data[2];
		$('bgBed').innerText = data[3];
	});	
}

function cwfpTj(){
    var xydm = "";
	var bjdm = "";	
	var nj = "";
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
    getOtherData.getCwFpZsData(xydm,nj,bjdm,function(data){//δ����
       if(data!=null){
        allbody.innerText=data[0];
       	allboy.innerText=data[1];
       	allgirl.innerText=data[2];
       }
    });	
    getOtherData.getCwYfpZsData(xydm,nj,bjdm,function(data){//�ѷ���
       if(data!=null){
        totalBed.innerText=data[0];
       	boyBed.innerText=data[1];
       	girlBed.innerText=data[2];
       }
    });
}

function ssFp_Xq(){  
   $("xb").value="";
   initSsFpLdList();
   //initSsFpSsXxList();  
}

function CwFp_Nj(){
    //dataLoad(true);
    cwfpTj();
    initCwFpXqListt();//��λ����У����Ϣɸѡ
    initBjList();//�༶��Ϣɸѡ
    initCwFpXsList();//��λ����ѧ����Ϣɸѡ
    initCwFpFpCwList();//��λ�����ѷ��䴲λ��ѧ������Ϣɸѡ
    initCwFpSsCwXxList();//��λ�������ᴲλ��Ϣɸѡ
    //dataIinit();
    // dataLoad(false);
}
function CwFp_Xy(){ 
    //dataLoad(true);
    initBjList();//�༶��Ϣɸѡ
    initCwFpXqListt();//��λ����У����Ϣɸѡ
    cwfpTj();
    initCwFpXsList();//��λ����δ����ѧ����Ϣɸѡ
    initCwFpSsCwXxList();//��λ�������ᴲλ��Ϣɸѡ
    initCwFpFpCwList();//��λ�����ѷ��䴲λ��ѧ������Ϣɸѡ
    //dataLoad(false);  
}
function CwFp_Bj(){
    //dataLoad(true);
    cwfpTj();  
    initCwFpXsList();//��λ����δ����ѧ����Ϣɸѡ
    if($("xxdm").value=='12872'){//����ְҵ����ѧԺ 
      initCwFpXqListt();//��λ����У����Ϣɸѡ  
      initCwFpSsCwXxList();//��λ�������ᴲλ��Ϣɸѡ
    }
    initCwFpFpCwList();//��λ�����ѷ��䴲λ��ѧ������Ϣɸѡ
    //dataLoad(false);    
}
function CwFp_Xq(){
    //dataLoad(true);    
    $("xb").value=""; //�����Ա�ֵΪ��	
     var ld = document.forms[0].ld;
     var cs = document.forms[0].cs;
    	
     for (i=1;i<ld.length;i++) {	//���¥�����б�  
        ld.options[i]=null;
     }
     for (i=1;i<cs.length;i++) {	//��ղ���б�  
        cs.options[i]=null;
     }
    //ѡ��У����ֱ�Ӽ���δ���䴲λ��-----edit by ³��-------  
    //initCwFpSsCwXxList();//��λ�������ᴲλ��Ϣɸѡ
    dataIinit();
    //dataLoad(false);        
} 
function CwFp_Xb(){
    //dataLoad(true);    
    initCwFpLdList();//��λ����¥����Ϣɸѡ
    //initCwFpSsCwXxList();//��λ�������ᴲλ��Ϣɸѡ
    initCwFpXsList();//��λ����δ����ѧ����Ϣɸѡ
    initCwFpFpCwList();//��λ�����ѷ��䴲λ��ѧ������Ϣɸѡ
    //dataLoad(false);        
}
function CwFp_Ld(){
    //dataLoad(true);   
	//---------edit by ³��-2011.4.26---begin------
	//��¥��������δ���䴲λ����¥��2��������¥����������˴�λ��û���غã�����¥����ȥ���ش�λֱ�ӵ���JVM�Բ�����
    //initCwFpSsCwXxList();//��λ�������ᴲλ��Ϣɸѡ
	initCwFpFpCwList();//��λ�����ѷ��䴲λ��ѧ������Ϣɸѡ
	//-------------------end--------------
    initCwFpFpCsList(); //��λ���������Ϣɸѡ
    //dataLoad(false);       
}
function CwFp_Cs(){   
    initCwFpSsCwXxList();//��λ�������ᴲλ��Ϣɸѡ
}
function showDivWait(showlist,leftv,topv){  
	//IE7�е����⣬��ʱɾ����ʾ��ò��Ӱ�첻�� by luojw
    var d_html = "<table width='180' border='0' ><tr><td height='10' align='center'>";
	    d_html += "</td></tr></table>";	 
    var width = 0;
	var height = 0;
	var left = leftv;
	var top = topv;	
	var color = "#FFFFFF";
	var dd_html = "<div id="+showlist+" oncontextmenu='return false' onselectstart='return false'  style='width:200px;height:30px;position:absolute;z-index:100;filter:alpha(opacity=70); width:" + width + "px; height:" + height + "px; top:" + top + "; left:" + left + "; '>";	
	    dd_html += d_html;
	    dd_html += "</div>";
	//����ҳ���ϱ������IDΪ"tmpdivone","tmpdivtow","tmpdivthree"��div�㡣
	if(showlist=='oracleListshow'){
	  tmpdivone.innerHTML = dd_html;
	}else if(showlist=='xhshow'){
	  tmpdivtow.innerHTML = dd_html;
	}else if(showlist=='sqlshow'){
	  tmpdivthree.innerHTML = dd_html;
	}   
}

function showValues(){       
    var table_title = $('nf').value+"��"+$('yf').value+"�·���ס��Ԣ����Ա�����±���";
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='style/function.js'>";
	the_content += "<script language='javascript'>";
	the_content +="var HKEY_Root,HKEY_Path,HKEY_Key;" ;
	the_content +="HKEY_Root='HKEY_CURRENT_USER';";
	the_content +="HKEY_Path='\\Software\\Microsoft\\Internet Explorer\\PageSetup\\';";
	the_content +="var Wsh=new ActiveXObject('WScript.Shell');";
	the_content +="HKEY_Key='header';";
	the_content +="Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');";
	the_content +="HKEY_Key='footer';";
	the_content +="Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');";	              
	the_content +="</script>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><b>";
	the_content += table_title;
	the_content += "</b>";

	the_content +="<br><div align='right'>�㱨����ʱ��Σ�"+ $('gzksrq').value+"��"+ $('gzjsrq').value+"</div>";
    the_content +="<div align='center'> <table width='100%' class='tbstyle'> <tr><td width='15%'>";
	the_content +="����Ա����</td><td>"+ $('xm').value+"</td><td width='15%'>��ס¥��</td><td>"+$('rzqsh').value+"</td></tr>";   
    the_content +="<tr><td>����¥��</td><td colspan='3'>"+ $('fzld').value+"</td></tr></table>";
	the_content +="<table width=100%  class=tbstyle><tr><td rowspan=5 width=8%><br><br><p align=center>��</p><p align=center>Ҫ</p>";
	the_content +="<p align=center>��</p><p align=center>��</p><p align=center>��</p><p align=center>��</p><br><br></td><td height='80px'>";
	the_content += $('gznr_jyfk').value+"</td></tr><tr><td height='80px'>"+ $('jgznr_jyjl').value+"</td></tr><tr><td height='80px'>"+ $('gznr_jhynr').value;
	the_content +="</td></tr><tr><td height='80px'>"+ $('gznr_jxsdt').value+"</td></tr><tr><td height='80px'>"+ $('gznr_jqtnr').value+"</td></tr><tr><td rowspan=3>";
	the_content +="<br><br><p align=center>��Ҫ</p><p align=center>����</p><p align=center>����</p><p align=center>����</p><p align=center>";
	the_content +="����<p align=center>����</p><br><br></td><td height='100px'>"+ $('fkyj_xyfk').value+"</td></tr><tr><td height='80px'>"+ $('fkyj_xgbfk').value;
	the_content +="</td></tr><tr><td height='80px'>"+ $('fkyj_fwzxfk').value+"</td></tr><tr><td align=center>��ע</td><td height='80px'>"+ $('bz').value+"</td></tr></table>";
    
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='ҳ������' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='��ӡԤ��' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"WebBrowser.ExecWB(6,6)\"></div>";
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);

	newwin.document.close();
	newwin = null;
}

function jswmsqb(){
    var ksn="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    var ksy="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    var ksr="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    var jsn="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    var jsy="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    var jsr="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; 
    var kssj="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    var jssj="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; 
    if($("hdksrq")&&$("hdksrq").value!=""){
        var ksrq = $("hdksrq").value;
        ksn = ksrq.substring(0,4);
        ksy = ksrq.substring(5,6);
        ksr = ksrq.substring(7,8);    
    }
    if($("hdjsrq")&&$("hdjsrq").value!=""){
        var ksrq = $("hdjsrq").value;
        jsn = ksrq.substring(0,4);
        jsy = ksrq.substring(5,6);
        jsr = ksrq.substring(7,8);    
        
    }
    
    if($("hdkssj").value!=""){
       kssj = $("hdkssj").value;
    }
    if($("hdjssj").value!=""){
       jssj = $("hdjssj").value;
    }
    
    var table_title = "�й����ʴ�ѧ���人�����ᡢ�������ֻ�ǼǱ�";
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='style/function.js'>";
	the_content += "<script language='javascript'>";
	the_content +="var HKEY_Root,HKEY_Path,HKEY_Key;" ;
	the_content +="HKEY_Root='HKEY_CURRENT_USER';";
	the_content +="HKEY_Path='\\Software\\Microsoft\\Internet Explorer\\PageSetup\\';";
	the_content +="var Wsh=new ActiveXObject('WScript.Shell');";
	the_content +="HKEY_Key='header';";
	the_content +="Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');";
	the_content +="HKEY_Key='footer';";
	the_content +="Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,'');";	              
	the_content +="</script>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><b>";
	the_content += table_title;
	the_content += "</b>";

	the_content +="<br><table class='tbstyle' width='100%'  align='center'> <tr><td   width='10%' >�����</td><td  >"+$('hdmc').value+"</td><td width='12%' align='center'> ";
    the_content +="����������</td><td width='10%'>"+$('fzrxm').value+"</td><td width='12%' align='center'> ����������</td><td width='10%'>"+$('jsrxm').value+"</td></tr><tr><td > ���쵥λ</td><td  >"+$('zbdw').value+"</td><td  align='center'> ��������ϵ��ʽ</td>";
	the_content +="<td>"+$('fzrlxfs').value+"</td><td align='center'> ��������ϵ��ʽ</td><td>"+$('jsrlxfs').value+"</td></tr></table><table class='tbstyle' width='100%'  align='center'><tr><td width='20%' align='center' > �����</td>";   
    the_content +="<td colspan='3'><br>"+$('hdnr').value+"<br></td></tr><tr><td align='center'> ��ص�</td><td>"+$('hddd').value+"</td><td align='center' width='15%'> �μ�����</td><td width='15%'>"+$('cjrs').value+"</td></tr>";
	the_content +=" <tr><td align='center'>�ʱ��</td><td colspan='3'>"+ksn+"��"+ksy+"��"+ksr+"��"+kssj+"ʱ �� "+jsn+"��"+jsy+"��"+jsr+"��"+jssj+"ʱ</td> </tr><tr><td align='center'> ��ȫ��<br>����ʩ </td><td colspan='3'><br><br><br><br>";
	the_content +="<div align='right'>��ȫ������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content += "<div align=right>&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</div>";
	the_content +="</td></tr> <tr><td align='center'> "+jQuery("#xbmc").val()+"��<br>�����</td><td colspan='3'><br><br><br><br><div align='right'>������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content +="	<div align=right>��λ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br><div align=right>&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</div>";
	the_content +="	</td></tr><tr><td align='center'> ������<br>����<br>��� </td><td colspan='3'><br><br><br><br><div align='right'>������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content +="<div align=right>&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</div></td></tr><tr><td align=center> ������<br>���</td><td colspan=3>";
    the_content +="<br><br><br><br><div align='right'>������ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content +="	<div align=right>&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;</div>	</td></tr><tr><td align=center> ��ע</td> <td colspan='3'><br><br><br><br></td></tr></table>";
	
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='ҳ������' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='��ӡԤ��' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick=\"WebBrowser.ExecWB(6,6)\"></div>";

    var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
//
}

 function isEmail(sEmail){
	      sEmail = sEmail.trim();
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
 }
 
 
 function getGyJcXf(obj){
     var pkValue = obj.value;
     var jctag = "";
     var num = "";
     var jcType = "";
     var tb = "";
     var pk = "";
     var col = "";
     jcType = curr_row.cells[1].getElementsByTagName('select')[0].value;
     if(jcType=="jl"){
         tb = "xsjldmb";
         pk = "jldm";
         col = "jlxf";
     }else{
         tb = "xscfdmb";
         pk  = "cfdm";
         col = "cfxf";
     }
     gyglShareData.getGyDyXf(tb,col,pk,pkValue,function(data){      
         if(data!=""&&data != null){
              curr_row.cells[3].getElementsByTagName('input')[0].value=data;
         }else{
              curr_row.cells[3].getElementsByTagName('input')[0].value="";
         }
     });
}
 function getGyJcXf_Modi(jcType){    
     var pkValue = "";
     var jctag = "";
     var num = "";
     var jcType = "";
     var tb = "";
     var pk = "";
     var col = "";
     
     if(jcType=="jl"){
         tb = "xsjldmb";
         pk = "jldm";
         col = "jlxf";
         pkValue = $("jlnr").value;
         gyglShareData.getGyDyXf(tb,col,pk,pkValue,function(data){      
         if(data!=""&&data != null){
              $("ryjf").value=data;
         }else{
              $("ryjf").value="";
         }
     });
     }else{
         tb = "xscfdmb";
         pk  = "cfdm";
         col = "cfxf";
         pkValue = $("cfnr").value;
         gyglShareData.getGyDyXf(tb,col,pk,pkValue,function(data){      
         if(data!=""&&data != null){
              $("rykf").value=data;
         }else{
              $("rykf").value=""; 
         }
     });
     }
     
}