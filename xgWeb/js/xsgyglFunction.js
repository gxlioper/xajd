//学生宿舍分布
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
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		}else{
			if(confirm("确定要删除该行数据吗？")){
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
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}
			pkValue = curr_row.getElementsByTagName("input")[0].value;
			url += pkValue;			
		}
//		alert(url);
		showTopWin(url,h,w);
	}
}

//保存数据
function Savedata(mustFill,url){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if($(eles[i])){
			if (document.getElementById(eles[i]).value == "") {
				alert("请将带\"*\"号的项目输入完整！");
				return false;
			}
		}
	}
	if($("sj_hour")){
		var hour = $("sj_hour").value;
		if(hour.match(/^\d+\.{0,1}\d{0,3}$/)==null || hour>24 || hour.length!=2){
			alert("活动时间输入格式不正确！");
			document.getElementById('sj_hour').focus();
			return false;
		}
	}
	if($("sj_minute")){
		var minute = $("sj_minute").value;
		if(hour.match(/^\d+\.{0,1}\d{0,3}$/)==null || minute>60 || minute.length!=2){
			alert("活动时间输入格式不正确！");
			document.getElementById('sj_minute').focus();
			return false;
		}
	}
	if($("sj_second")){
		var second = $("sj_second").value;
		if(second.match(/^\d+\.{0,1}\d{0,3}$/)==null || second>60 || second.length!=2){
			alert("活动时间输入格式不正确！");
			document.getElementById('sj_second').focus();
			return false;
		}
	}
	if($("sjh")){
		var sjh = $("sjh").value;
		if(sjh!=""){
		    if(sjh.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			   alert("手机号码格式不正确!");
			   document.getElementById("sjh").focus();
			   return false;
		   }
		}
	}
	if($("qsdh")){
		var qsdh = $("qsdh").value;
		if(qsdh!=""){
		   if(qsdh.match(/^\d{0,4}\-{0,1}\d{7,8}$/)==null){
			   alert("电话号码格式不正确!");
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

//选择学院/专业/班级数据
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

///////////////////////Ajax 提交数据////////////////////////////////

//提交宿舍息
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
		showMsgWin("有错误出现：远程数据读取失败！");
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

//获取宿舍分配楼栋列表
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

//获取宿舍分配宿舍信息列表
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

//获取宿舍划分宿舍信息列表(杭州职业技术学院)
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
	
	if(document.forms[0].xxdm.value == "12872"){//杭州职业技术学院
	   if($("fpfs").value=="acw"&&cs==""){//按床位分配时，为避免刷新过多没必要数据带来运行缓慢，必先选择层号
	       alert("请先选择层号！");
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
					
					//--------2011.4.25 edit by 鲁大----begin--------------------
					//把原来的DWR加载下拉列表改为拼HTML的方式
					//注：经过多种方法尝试，拼HTML方式速度最快
					 var html = new Array(); 
					 for (var i = 0 ; i < data.length; i++){
						 html[i] = ['<option value="', data[i].dm, '">', data[i].mc, '</option>'].join("");
					 }
					 var selectHtml = '<select name="oracleItem" style="width:100%;" size="27" id="oracleList" multiple="multiple">';
					 $('wfpDiv').innerHTML = selectHtml+html.join("")+"</seclet>";
					//--------2011.4.25 edit by 鲁大----end--------------------
					 
					if($(show)){	
		              $(show).style.display= "none";
	                }		
				}
			}
		});
	}
	//dataLoad(false);
}
//获取宿舍划分已分配宿舍信息列表
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
					//----------edit by 鲁大 2011.4.26------begin-----
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
//获取宿舍划分楼层列表
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
//获取床位分配校区列表
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

//获取床位分配楼栋列表
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

//获取床位分配楼层列表
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


//获取床位分配宿舍床位信息列表
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
	
	//----------edit by 鲁大 2011.4.26--------begin-----
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
//获取床位分配总人数
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

//获取床位分配已分配宿舍床位信息列表
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

//获取床位已分配总人数
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
	   alert("操作非法，不能和自己交换床位!");
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
		              if(confirm("点击确定后，异动前后宿舍床位将自动进行交换，是否要交换？")){
		                   dataDoSave('xh-xn-xq-lddm-qsh-ydsj-cwh');
		              }else{
		                   return false;
		              }
		           }else{
                      alert("所选异动后宿舍床位已有人入住,暂不能进行异动！");
                      return false;		           
		           }	          
		         }else{	
		           	if(confirm("确定要进行宿舍床位异动？")){         
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


//获取床位分配学生列表
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

//获公寓维修负责部门信息列表
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
				showMsgWin("有错误出现：远程数据读取失败！");
			}
     });
 }
 
 /**
 	取得之前的已经分配的床位备份到sqlValue数组中
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
		if (confirm("当前已分配情况列表发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
	         bedDistSave();
	    } else {
			refreshForm("/xgxt/bed_distribute.do");
		}
	}
}

function dataIinit(){
     $("xb").value=""; //重置性别值为空	
     var ld = document.forms[0].ld;
     var cs = document.forms[0].cs;
    	
     for (i=1;i<ld.length;i++) {	//清空楼栋分列表  
        ld.options[i]=null;
     }
     for (i=1;i<cs.length;i++) {	//清空层号列表  
        cs.options[i]=null;
     }
            
}
function addBatchColum(){
      if(document.forms[0].nj.value==null||document.forms[0].nj.value==""){
        alert("请先选择年级！");
        return false;
      } 
       if(document.forms[0].xydm.value==null||document.forms[0].xydm.value==""){
        alert("请先选择"+jQuery("#xbmc").val()+"！");
        return false;
      }
     var nj  = document.forms[0].nj.value;
     var xyV  = document.forms[0].xydm.value;
     var oraObject =  document.getElementById("oracleList");
     if(oraObject.options.length==0){
         alert("\'未划分宿舍\'列表为空！");
         return false;
     }         
     if(document.forms[0].xxdm.value == "12872"){//杭州职业技术学院
           if(document.forms[0].bjdm.value==null||document.forms[0].bjdm.value==""){
        	   alert("请先选择班级！");
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
		     alert('请在左边\'未划分宿舍\'列表中选择一条或多条记录！');
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
           if(czfs=='ass'){//按宿舍
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
		           alert('请在左边\'未划分宿舍\'列表中选择一条或多条记录！');
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
           }else{//按床位
                var count = 0; 
                var n = document.forms[0].sql.options.length;
                for(var i=0;i<oraObject.options.length;i++){
		            if(oraObject.options[i].selected){
			           count++;
                       if(count>=2)break;
		            }
	            }
                if(count==0){
		           alert('请在左边\'未划分宿舍\'列表中选中一条记录！');
		           return false;
	            }
	            if(count>=2){
	               alert('\'按床位数\'划分方式下，只能选择一条记录！');
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
		alert('\'已划分情况\'列表为空！');
		return false;
	}	
	if(document.forms[0].xxdm.value == "12872"){//杭州职业技术学院
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
       if(czfs=='ass'){//按宿舍
          var count = 0;
          for(var i=0;i<sqlObject.options.length;i++){
		     if(sqlObject.options[i].selected){
			 count++;
		     }
	      }
	      if(count==0){
		    alert('请在右边\'已划分情况\'列表中选择一条或多条记录！');
		    return false;
	      }
	      for(i=0;i<sqlObject.options.length;i++){
              if(sqlObject.options[i].selected){
                  
                 var sqlV = sqlObject.options[i].value;//sqlV排列方式：学院代码/宿舍编号/分配床位数/年级
                 var sqlT = sqlObject.options[i].text;//sqlT排列方式：年级/学院名称/楼栋名称/楼层/寝室号//总床位数/分配床位数
                              
                 var sqlVArr = sqlV.split('/');
                 var sqlTArr = sqlT.split('/');
                 
                 for(j=0;j<oraObject.options.length;j++){
                     var oraV = oraObject.options[j].value;//oraV排列方式:宿舍编号/剩余床位数
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
       }else{//按床位
                var count = 0;                               
                for(var i=0;i<sqlObject.options.length;i++){
		            if(sqlObject.options[i].selected){
			           count++;
                       if(count>=2)break;
		            }
	            }
                if(count==0){
		           alert('请在右边\'已划分情况\'列表中选中一条记录！');
		           return false;
	            }
	            if(count>=2){
	               alert('\'按床位数\'划分方式下，只能选择一条记录！');
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
	 	if(addordel=="add"){//按床位数分配
	 	     var nj       = document.getElementById('nj').value;
		     var oracleListIndex = document.forms[0].oracleList.selectedIndex;
    	     var acwValue = document.forms[0].oracleList.options[oracleListIndex].text;
    	     var qssycws  = acwValue.substring(acwValue.lastIndexOf('/')+1);
    	     var xyIndex  = document.forms[0].xy.selectedIndex;      	    	
      	     var xyV      = document.forms[0].xy.options[xyIndex].value;     	
	 	     var xyT      = document.forms[0].xy.options[xyIndex].text;
	 	     var qssycws1    = parseInt(qssycws)-parseInt(cws);
      	     var addOracleV  = document.forms[0].oracleList.options[oracleListIndex].value;//addOracleV排列方式： 楼栋名称寝室号/剩余床位数    	
	  	     var addOracleT  = document.forms[0].oracleList.options[oracleListIndex].text;//addOracleV排列方式：	宿舍编号/剩余床位数  	
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
     	}else  if(addordel=="del"){//按床位数释放
     	     var sqlIndex = document.forms[0].sql.selectedIndex;
     	     var acwValue = document.forms[0].sql.options[sqlIndex].text;
     	     var yfpcws   = acwValue.substring(acwValue.lastIndexOf('/')+1);
     	     var yfpcws1  = parseInt(yfpcws)-parseInt(cws); 
     	     var sqlV     = document.forms[0].sql.options[sqlIndex].value;//sqlV排列方式：学院代码/宿舍编号/分配床位数/年级     	
	  	     var sqlT     = document.forms[0].sql.options[sqlIndex].text;//sqlT排列方式：年级/学院名称/楼栋名称/楼层/寝室号/总床位数/分配床位数
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
   if(document.forms[0].xxdm.value == "12872"){//杭州职业技术学院
       var bjText = document.forms[0].bjdm.options[document.forms[0].bjdm.selectedIndex].text;
       clinText = bjText + "班";
   }else{
       clinText = xyText ;
   }
   if (compartStatus) {
		if (confirm(clinText+" 已划分情况列表发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
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

function addCwBatchColum(){//床位分配          
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
		   alert('请在左边\'未分配床位\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    for(var i=0;i<xh.options.length;i++){
		    if(xh.options[i].selected){
			  countXh++;
		    }
	    }
	    if(countXh==0){
		   alert('请在左边\'未分配学生\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    if(countCw!=countXh){
	       alert('未分配床位、未分配学生列表中，所选择记录数不一致！');
		   return false;
	    }else{
	    	//showMessage('showDiv',true,'#C7DEFC');    
	    	viewTempDiv('入住时间设置','showDiv',280,160);	    	    	        	
       }     
}
function addCwColum(){
        var zsrq = "";
        if($("zsrq"))zsrq=$("zsrq").value;
        if(zsrq==""){
           alert("请输入入住时间！");
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
        	 if(xhTarr[i].substring(xhTarr[i].lastIndexOf("/")+1)=="混合"||(xhTarr[i].substring(xhTarr[i].lastIndexOf("/")+1)==cwTarr[i].substring(cwTarr[i].lastIndexOf("/")+1))){
        		 document.forms[0].sql.options[n++] = new Option(xhTarr[i]+'/'+cwTarr[i]+'/'+zsrq,xhVarr[i]+'/'+cwVarr[i]+'/'+zsrq);                                                       
        	 }else{
        		 alert("性别匹配错误！");
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
		alert('请在右边\'已分配情况\'列表中中选择一条或多条记录！');
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
    
    //把释放的和未分配的床位加载到一起并排序
    //放弃了，数据量再大点又扛不住了--edit by 鲁大----2011.4.26-
   // initCwFpSsCwXxList(releaseOptionValues,releaseOptionTexts);
}

function ssfpTj(){
    var xydm = "";
	var bjdm = "";	
	var nj = "";
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};	
	getOtherData.getCwFpZsData(xydm,nj,bjdm,function(data){//未分配
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
    getOtherData.getCwFpZsData(xydm,nj,bjdm,function(data){//未分配
       if(data!=null){
        allbody.innerText=data[0];
       	allboy.innerText=data[1];
       	allgirl.innerText=data[2];
       }
    });	
    getOtherData.getCwYfpZsData(xydm,nj,bjdm,function(data){//已分配
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
    initCwFpXqListt();//床位分配校区信息筛选
    initBjList();//班级信息筛选
    initCwFpXsList();//床位分配学生信息筛选
    initCwFpFpCwList();//床位分配已分配床位（学生）信息筛选
    initCwFpSsCwXxList();//床位分配宿舍床位信息筛选
    //dataIinit();
    // dataLoad(false);
}
function CwFp_Xy(){ 
    //dataLoad(true);
    initBjList();//班级信息筛选
    initCwFpXqListt();//床位分配校区信息筛选
    cwfpTj();
    initCwFpXsList();//床位分配未分配学生信息筛选
    initCwFpSsCwXxList();//床位分配宿舍床位信息筛选
    initCwFpFpCwList();//床位分配已分配床位（学生）信息筛选
    //dataLoad(false);  
}
function CwFp_Bj(){
    //dataLoad(true);
    cwfpTj();  
    initCwFpXsList();//床位分配未分配学生信息筛选
    if($("xxdm").value=='12872'){//杭州职业技术学院 
      initCwFpXqListt();//床位分配校区信息筛选  
      initCwFpSsCwXxList();//床位分配宿舍床位信息筛选
    }
    initCwFpFpCwList();//床位分配已分配床位（学生）信息筛选
    //dataLoad(false);    
}
function CwFp_Xq(){
    //dataLoad(true);    
    $("xb").value=""; //重置性别值为空	
     var ld = document.forms[0].ld;
     var cs = document.forms[0].cs;
    	
     for (i=1;i<ld.length;i++) {	//清空楼栋分列表  
        ld.options[i]=null;
     }
     for (i=1;i<cs.length;i++) {	//清空层号列表  
        cs.options[i]=null;
     }
    //选择校区不直接加载未分配床位了-----edit by 鲁大-------  
    //initCwFpSsCwXxList();//床位分配宿舍床位信息筛选
    dataIinit();
    //dataLoad(false);        
} 
function CwFp_Xb(){
    //dataLoad(true);    
    initCwFpLdList();//床位分配楼栋信息筛选
    //initCwFpSsCwXxList();//床位分配宿舍床位信息筛选
    initCwFpXsList();//床位分配未分配学生信息筛选
    initCwFpFpCwList();//床位分配已分配床位（学生）信息筛选
    //dataLoad(false);        
}
function CwFp_Ld(){
    //dataLoad(true);   
	//---------edit by 鲁大-2011.4.26---begin------
	//按楼栋不加载未分配床位（按楼栋2万条数据楼层加载上来了床位还没加载好，点了楼层又去加载床位直接导致JVM吃不消）
    //initCwFpSsCwXxList();//床位分配宿舍床位信息筛选
	initCwFpFpCwList();//床位分配已分配床位（学生）信息筛选
	//-------------------end--------------
    initCwFpFpCsList(); //床位分配层数信息筛选
    //dataLoad(false);       
}
function CwFp_Cs(){   
    initCwFpSsCwXxList();//床位分配宿舍床位信息筛选
}
function showDivWait(showlist,leftv,topv){  
	//IE7有点问题，暂时删了提示，貌似影响不大 by luojw
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
	//访问页面上必须存在ID为"tmpdivone","tmpdivtow","tmpdivthree"等div层。
	if(showlist=='oracleListshow'){
	  tmpdivone.innerHTML = dd_html;
	}else if(showlist=='xhshow'){
	  tmpdivtow.innerHTML = dd_html;
	}else if(showlist=='sqlshow'){
	  tmpdivthree.innerHTML = dd_html;
	}   
}

function showValues(){       
    var table_title = $('nf').value+"年"+$('yf').value+"月份入住公寓辅导员工作月报表";
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

	the_content +="<br><div align='right'>汇报工作时间段："+ $('gzksrq').value+"－"+ $('gzjsrq').value+"</div>";
    the_content +="<div align='center'> <table width='100%' class='tbstyle'> <tr><td width='15%'>";
	the_content +="辅导员姓名</td><td>"+ $('xm').value+"</td><td width='15%'>入住楼栋</td><td>"+$('rzqsh').value+"</td></tr>";   
    the_content +="<tr><td>负责楼栋</td><td colspan='3'>"+ $('fzld').value+"</td></tr></table>";
	the_content +="<table width=100%  class=tbstyle><tr><td rowspan=5 width=8%><br><br><p align=center>主</p><p align=center>要</p>";
	the_content +="<p align=center>工</p><p align=center>作</p><p align=center>内</p><p align=center>容</p><br><br></td><td height='80px'>";
	the_content += $('gznr_jyfk').value+"</td></tr><tr><td height='80px'>"+ $('jgznr_jyjl').value+"</td></tr><tr><td height='80px'>"+ $('gznr_jhynr').value;
	the_content +="</td></tr><tr><td height='80px'>"+ $('gznr_jxsdt').value+"</td></tr><tr><td height='80px'>"+ $('gznr_jqtnr').value+"</td></tr><tr><td rowspan=3>";
	the_content +="<br><br><p align=center>需要</p><p align=center>反馈</p><p align=center>的情</p><p align=center>况和</p><p align=center>";
	the_content +="工作<p align=center>建议</p><br><br></td><td height='100px'>"+ $('fkyj_xyfk').value+"</td></tr><tr><td height='80px'>"+ $('fkyj_xgbfk').value;
	the_content +="</td></tr><tr><td height='80px'>"+ $('fkyj_fwzxfk').value+"</td></tr><tr><td align=center>备注</td><td height='80px'>"+ $('bz').value+"</td></tr></table>";
    
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\"></div>";
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
    
    var table_title = "中国地质大学（武汉）集会、文体娱乐活动登记表";
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

	the_content +="<br><table class='tbstyle' width='100%'  align='center'> <tr><td   width='10%' >活动名称</td><td  >"+$('hdmc').value+"</td><td width='12%' align='center'> ";
    the_content +="负责人姓名</td><td width='10%'>"+$('fzrxm').value+"</td><td width='12%' align='center'> 经手人姓名</td><td width='10%'>"+$('jsrxm').value+"</td></tr><tr><td > 主办单位</td><td  >"+$('zbdw').value+"</td><td  align='center'> 负责人联系方式</td>";
	the_content +="<td>"+$('fzrlxfs').value+"</td><td align='center'> 经手人联系方式</td><td>"+$('jsrlxfs').value+"</td></tr></table><table class='tbstyle' width='100%'  align='center'><tr><td width='20%' align='center' > 活动内容</td>";   
    the_content +="<td colspan='3'><br>"+$('hdnr').value+"<br></td></tr><tr><td align='center'> 活动地点</td><td>"+$('hddd').value+"</td><td align='center' width='15%'> 参加人数</td><td width='15%'>"+$('cjrs').value+"</td></tr>";
	the_content +=" <tr><td align='center'>活动时间</td><td colspan='3'>"+ksn+"年"+ksy+"月"+ksr+"日"+kssj+"时 至 "+jsn+"年"+jsy+"月"+jsr+"日"+jssj+"时</td> </tr><tr><td align='center'> 安全保<br>卫措施 </td><td colspan='3'><br><br><br><br>";
	the_content +="<div align='right'>安全负责人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content += "<div align=right>&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</div>";
	the_content +="</td></tr> <tr><td align='center'> "+jQuery("#xbmc").val()+"社<br>团意见</td><td colspan='3'><br><br><br><br><div align='right'>负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content +="	<div align=right>单位盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br><div align=right>&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</div>";
	the_content +="	</td></tr><tr><td align='center'> 场所管<br>理部门<br>意见 </td><td colspan='3'><br><br><br><br><div align='right'>负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content +="<div align=right>&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</div></td></tr><tr><td align=center> 保卫处<br>意见</td><td colspan=3>";
    the_content +="<br><br><br><br><div align='right'>负责人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><br>";
	the_content +="	<div align=right>&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</div>	</td></tr><tr><td align=center> 备注</td> <td colspan='3'><br><br><br><br></td></tr></table>";
	
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\"></div>";

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