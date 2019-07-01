function chkPjzbDoType(){
	var doType = $("doType").value;
	if(doType == "modi"){
		var pjh = $("pjh").value;
		var d_html = pjh + "<input type='hidden' name='pjh' value='" + pjh + "'>";
		$("doRow").innerHTML = d_html;
	}
}
function savePjzb(){
	var mxdx = $("mxdx").value;
	var fz = $("fz").value;
	var pjnr = $("pjnr").value;
	var pjh = $("pjh").value;
	var pjnr = $("pjnr").value;
	var jtxx = $("jtxx").value;
	if(pjnr.length>100){
		alert("评价内容不能超过100字符或汉字");
		return;
	}
	if(jtxx.length>300){
		alert("具体评价指标信息不能超过300字符或汉字");
		return;
	}
	if(pjh == ""){
		alert("评价号不得为空！");
		$("pjh").focus();
		return false;
	}
   if(mxdx == ""){
		alert("面向对象不得为空！");
		$("mxdx").focus();
		return false;
	}
	if(fz == ""){
		alert("对应分值不得为空！");
		$("fz").focus();
		return false;
	}
	if(pjnr == ""){
		alert("评价内容不得为空！");
		$("pjnr").focus();
		return false;
	}
	BatAlert.showTips('正在处理数据，请稍候！');
	refreshForm('setPjzb.do?act=save');
}
function chkPfbzDoType(){
	var doType = $("doType").value;
	if(doType == "modi"){
		$("bzbhT").readOnly = true;
		$("bzmc").readOnly = true;
		$("bzbhT").style.backgroundColor = "#98BCE1";
		$("bzmc").style.backgroundColor = "#98BCE1";
		$("pfxm").focus();
	}else{
		$("bzbhT").readOnly = false;
		$("bzmc").readOnly = false;
		$("bzbhT").style.backgroundColor = "#FFFFFF";
		$("bzmc").style.backgroundColor = "#FFFFFF";
		$("bzbhT").focus();
	}
	
	$("bzbhS").onchange = function(){
		if($("bzbhS").value != ""){
			$("bzbhT").value = $("bzbhS").value;
			$("bzmc").value = $("bzbhS").options[$("bzbhS").selectedIndex].text;
			$("bzbhT").readOnly = true;
			$("bzmc").readOnly = true;
			$("bzbhT").style.backgroundColor = "#98BCE1";
			$("bzmc").style.backgroundColor = "#98BCE1";
			$("pfxm").focus();
		}else{
			$("bzbhT").value = "";
			$("bzmc").value = "";
			$("bzbhT").readOnly = false;
			$("bzmc").readOnly = false;
			$("bzbhT").style.backgroundColor = "#FFFFFF";
			$("bzmc").style.backgroundColor = "#FFFFFF";
			$("bzbhT").focus();
		}		
	};
	
	$("bzbhT").onblur = function(){
		var tmp = $("bzbhT").value;
		for(var i = 1;i < $("bzbhS").options.length; i++){
			if($("bzbhS").options[i].value == tmp){
				$("bzbhS").options[i].selected = true;
				$("bzbhS").onchange();
				break;
			}
		}
	};	
}
function savePfbz(){
	var bzbhT = $("bzbhT").value;
	var bzmc = $("bzmc").value;
	var pfxm = $("pfxm").value;
	var qz = $("qz").value;
	var xssx = $("xssx").value;
	if(bzbhT == ""){
		alert("编号不得为空！");
		$("bzbhT").focus();
		return false;
	}
	if(bzmc == ""){
		alert("名称不得为空！");
		$("bzmc").focus();
		return false;
	}
	if(pfxm == ""){
		alert("评分项目不得为空！");
		$("pfxm").focus();
		return false;
	}
	if(qz == ""){
		alert("权重不得为空！");
		$("qz").focus();
		return false;
	}
	if(xssx == ""){
		alert("显示顺序不得为空！");
		$("xssx").focus();
		return false;
	}
	$("bzbhS").disabled = true;
	BatAlert.showTips('正在处理数据,请稍候！');
	refreshForm('setPfbz.do?act=save');
}
function getContInfo(){
	if($("fdyxmList").options.length < 1 || $("fdyxmList").selectedIndex < 0){
		return false;
	}
	$('zgh').value=$("fdyxmList").value;
	refreshForm('setFdyBj.do');
}

function getBzrInfo(){
	if($("fdyxmList").options.length < 1 || $("fdyxmList").selectedIndex < 0){
		return false;
	}
	$('zgh').value=$("fdyxmList").value;
	refreshForm('counsellorXg.do?method=fdybb');
}
function addFdyBj(){
		var fpfs = $("fpfs").value;
	    var fdyIndx = $("fdyxmList").selectedIndex;
	    
	    var bjTlist =  document.getElementById("bjTlist");
        var fpfs = $("fpfs").value;
        var bjFlist= document.getElementById("bjFlist"); 
        var bjTLength = bjTlist.length;      
        var bjTNum = 0;
        var j = 0;
        var bjdms =  Array();
	    var bjmcs =  Array();
	    
	    if(fdyIndx < 0){
				alert("请选择（双击）辅导员(班主任)！");
				return false;
		}
	    if((fpfs=='1'||fpfs=='3')){
			var opSelected = 0;
			for(var i=0;i<bjFlist.options.length;i++){
		    	if(bjFlist.options[i].selected){
			  		opSelected++;
		    	}
			}
	    	if(opSelected>1){
	    		alert("辅导员只能分管一个班级！");
				return false;
	    	}
	    }    	
		if((fpfs=='1'||fpfs=='3')&$('bjTlist').options.length!=0){
			var qx = $('qx').value;
			if(qx=="fdy"){
				alert("辅导员只能分管一个班级！");
			}else{
				alert("班主任只能分管一个班级！");
			}
			return false;
	    }        
     	
	    
	    //alert(wfpBj.options.length);
        for(var i=0;i<bjFlist.options.length;i++){
		    if(bjFlist.options[i].selected){
		    	if($("bjFlist").options[i].style.color=="red"&&(fpfs=='1'||fpfs=='2')){
						alert("辅导员不能分管已分配的班级！");
						break;
				}
		    	bjdms[j]=bjFlist.options[i].value;
                bjmcs[j]=bjFlist.options[i].text;
                bjFlist.options[i]=null;
			  	bjTNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(bjFlist==0){
		   alert('请在左边\'班级\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    for (i = 0; i < j; i++) {     	   
             bjTlist.options[bjTLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }
        
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
		$("delFdyBjB").disabled = false;
	}else{		
		$("addFdyBjB").disabled = true;
	}
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
	}
}	
function delFdyBj(){
	var fdyIndx = $("fdyxmList").selectedIndex;
	if(fdyIndx < 0){
		alert("请选择（双击）辅导员！");
		return false;
	}
	var bjTlist =  document.getElementById("bjTlist");
    var bjFlist= document.getElementById("bjFlist"); 
    var bjFLength = bjFlist.length;      
    var bjTNum = 0;
    var j = 0;
    var bjdms =  Array();
	var bjmcs =  Array();	
	    //alert(wfpBj.options.length);
        for(var i=0;i<bjTlist.options.length;i++){	
		    if(bjTlist.options[i].selected){
		    	bjdms[j]=bjTlist.options[i].value;
                bjmcs[j]=bjTlist.options[i].text;
                bjTlist.options[i]=null;
			  	bjTNum++;
			  	i--;
			  	j++;
		    }
	    }
	    if(bjTNum==0){
		   alert('请在左边\'班级\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    //alert(j);
	    for (i = 0; i < j; i++) {     	   
             bjFlist.options[bjFLength++] = new Option(bjmcs[i],bjdms[i]);                                                       
        }
    if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
		$("addFdyBjB").disabled = false;
	}else{		
		$("delFdyBjB").disabled = true;
	}	
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
	}	
}
function initSetFdyBj(){		
	if($("bjFlist").options.length > 0){
		$("bjFlist").options[0].selected = true;
	}
	if($("bjTlist").options.length > 0){
		$("bjTlist").options[0].selected = true;
	}
	if($("zgh").value == ""){
		$("addFdyBjB").disabled = true;
		$("delFdyBjB").disabled = true;
		$("bjFlist").disabled = true;
		$("bjTlist").disabled = true;
	}
	if($("bjFlist").options.length < 1){
		$("addFdyBjB").disabled = true;
	}
	if($("bjTlist").options.length < 1){
		$("delFdyBjB").disabled = true;
	}
}	

function saveFdyBj(){
	setEleDisable("button");
	showTips("处理数据中，请等待......");
	for(var i = 0 ; i < $("bjTlist").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "bjdm";
		tmp.value = $("bjTlist").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("setFdyBj.do?act=save");
}

function saveBzrBj(){
	setEleDisable("button");
	showTips("处理数据中，请等待......");
	for(var i = 0 ; i < $("bjTlist").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "bjdm";
		tmp.value = $("bjTlist").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("counsellorXg.do?act=save");
}

function chkPjPage(){
	if($("xxdm").value=='10290'){
	  
	}else{
		xyDisabled("xy");
	}
	
	if($("sfcp").value == "yes"){
		if($("fdy") && $("fdy").value == ""){
			if($("rsTable")){
				$("rsTable").style.display = "none";
			}
			if($("btn")){
				$("btn").style.display = "none";
			}
		}
		if($("btn").getElementsByTagName("a").length == 1){
			var pjs = document.getElementsByName("pjfs");
			alert(pjs.length);
			for(var i = 0; i < pjs.length; i++){
				pjs[i].disabled = true;
			}
		}
	}
}
function submitPj(){
	var pjs = document.getElementsByName("pjfs");
	for(var i = 0; i < pjs.length; i++){
		if(pjs[i].value == ""){
			alert("尚未评价完毕，不能提交！");
			return false;
		}
	}
	if(confirm('提交之后将不能再修改，确定要提交吗？')){
		setEleDisable("button");
		showTips("提交评价中，请等待......");
		refreshForm('pj.do?act=save&do=submit');
	}
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
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
	the_content += "<\/div>";
	//confirm(the_content);
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}
function MoreDo(doType){
	var url = "/xgxt/wjstwh_make.do?doType=";
	url += doType;
	w = "600";
	h = "500";		
	if(doType=="xxwh"){
		url = "/xgxt/wjstxxwh.do?id=";		
		if(curr_row==null){
	    	alert("请选择要操作的行！");
		    return false;
		}else{
			url += curr_row.cells[0].innerText;
		}    
	}
	if(doType=="wjyl"){
		url = "/xgxt/wj_view.do";
		w = "700";
		h = "800";
	}
	if(doType=="modi"||doType=="del"||doType=="view"){
	    if(curr_row==null){
	    	alert("请选择要操作的行！");
		    return false;
	    }else{
	    	url += "&id=";
	    	url +=  curr_row.cells[0].innerText;		    	
	        if(doType=="modi"||doType=="view"){
	           showTopWin(url,w,h);	
	        }else{
	        	if(confirm("确定要删除该条记录？"))
	           		refreshForm(url);
	        }	
	    }
	}else{
		showTopWin(url,w,h);
	}
}
function MoreDo2(doType){
	var url = "/xgxt/wjstxxwh_make.do?doType=";
	var id = document.getElementById("id").value;	
	url += doType;
	url += "&id=";
	url += id;
	w = "500";
	h = "300";	
	if(doType=="modi"||doType=="del"){
		if(curr_row==null){
			alert("请选择要操作的行！");
			return false;
		}else{			
			url += "&xxid=";
			url += curr_row.cells[0].innerText;	
			if(doType=="modi"){
	            showTopWin(url,w,h);	
	        }else{
	        	if(confirm("确定要删除该条记录？"))
	           		refreshForm(url);
	        }	
		}
	}else{
		showTopWin(url,w,h);	
	}
}
function loadTestSelectItem(){//问卷浏览
  var xxArray =  new Array();
  var txt = document.forms[0].xxStr.value;
  var splitTxt = txt.split("!!SplitSignOne!!");
  var stLen = document.forms[0].stLen.value;
  var stlxtype;  
  if(splitTxt != ""){
	  for(i = 0;i<splitTxt.length;i++)
	    xxArray[i] = splitTxt[i].split("!!SplitSignTwo!!");
	  for(j = 0;j<xxArray.length;j++){
	  	if(j == 0){
	  		document.getElementById(xxArray[j][0]).innerHTML = document.getElementById(xxArray[j][0]).innerHTML+"</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	  	}else if(j < xxArray.length){
	  		if(xxArray[j][0] != xxArray[j-1][0]){
	  		    document.getElementById(xxArray[j][0]).innerHTML = document.getElementById(xxArray[j][0]).innerHTML+"</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";	
	  		}
	  	}
	    document.getElementById(xxArray[j][0]).innerHTML = document.getElementById(xxArray[j][0]).innerHTML+
	    ""+xxArray[j][1]+".&nbsp;"+xxArray[j][2]+"&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	  }
  }
}

function loadTestSelectItem2(){//问卷填写
  var xxArray =  new Array();
  var txt = document.forms[0].xxStr.value;
  var splitTxt = txt.split("!!SplitSignOne!!");
  var stLen = document.forms[0].stLen.value;
  var stlxtype;  
  if(splitTxt != ""){
	  for(i = 0;i<splitTxt.length;i++)
	    xxArray[i] = splitTxt[i].split("!!SplitSignTwo!!");
	  for(j = 0;j<xxArray.length;j++){
	  	if(j == 0){
	  		document.getElementById(xxArray[j][0]).innerHTML = document.getElementById(xxArray[j][0]).innerHTML+"</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	  	}else if(j < xxArray.length){
	  		if(xxArray[j][0] != xxArray[j-1][0]){
	  		    document.getElementById(xxArray[j][0]).innerHTML = document.getElementById(xxArray[j][0]).innerHTML+"</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";	
	  		}
	  	}
//   if(xxArray[j][1] =  = "001")
	           stlxtype = "radio";   
//	    else if(xxArray[j][1] == "002"||xxArray[j][1] == "003")
//	           stlxtype = "checkbox";	          
  		document.getElementById(xxArray[j][0]).innerHTML=document.getElementById(xxArray[j][0]).innerHTML+"<input type='"+stlxtype+"' name='"+xxArray[j][0]+"' value='"+xxArray[j][1]+"'>&nbsp;"+xxArray[j][1]+".&nbsp;"+xxArray[j][2]+"&nbsp;&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	  }
  }
}

function saveTestSelectItem(){
	var url = "";
    var selectedStr="";
    var tmp;
    var n = 0;
    for(i=0;i<tp.rows.length;i++){
       if(i!=0){
          selectedStr += "!!SplitSignOne!!";
       }
       selectedStr += tp.rows[i].cells[0].id;
       selectedStr += "!!SplitSignTwo!!";
       tmp=document.getElementsByName(tp.rows[i].cells[0].id);
       
       for(j=1;j<tmp.length;j++){       	   
           if(tmp[j].checked){
           	   selectedStr += tmp[j].value; 
           	   n = n+1;          	   
           }else{
           	   selectedStr += "";
           }               
       }
    }
    if(n!=tp.rows.length){
       alert("请将问卷填写完整！");
       return false;
    }else {
       var tem = document.getElementById("xh||xn||xq").value;
       tem += document.getElementById("zgh").value;
       var url = "/xgxt/wj_view.do?act=save&strV=";
       url += selectedStr;
       url += "&zgh=";
       url += document.getElementById("zgh").value;
       getSztzData.getDataEx("fdygz_dcjgb","xh||xn||xq||zgh",tem,function(str){
          if(str!=""){
          	alert("您已经对该辅导员进行了评价！");		          
			return false;
          }else{
          	document.forms[0].action = url;
	        document.forms[0].submit();
          }        
        });
    }    
}

function onlyNumInputValue(obj, maxLen) {
	if (event.keyCode >= 48 && event.keyCode <= 57 && obj.value.length < maxLen) {
		return true;
	} else {
		return false;
	}
}

function onlyNumWordInputValue(obj, maxLen) {
	if (obj.value.length < maxLen &&(event.keyCode >= 48 && event.keyCode <= 57)||(event.keyCode >= 65 && event.keyCode <= 90)||(event.keyCode >= 97 && event.keyCode <= 122)) {
		return true;
	} else {
		return false;
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

function checkStL(obj){
	 var tem = obj.value;
	 tem += document.getElementById("xn").value;
	 tem += document.getElementById("xq").value;
	 getSztzData.getDataEx("fdygz_dcwjstb","id||xn||xq",tem,function(str){
          if(str!=""){
          	alert("该问题列已经存在！");		          
			obj.focus();
			return false;
          }else{
          	return true;
          }
      });
}
function checkXxL(obj){
	 var tem = obj.value;
	  tem += document.getElementById("id").value;
	  tem += document.getElementById("xn").value;
	  tem += document.getElementById("xq").value;
	 getSztzData.getDataEx("fdygz_dcwjstxxb","xxl||stid||xn||xq",tem,function(str){
          if(str!=""){
          	alert("该选项列已经存在！");		          
			obj.focus();
			return false;
          }else{
          	return true;
          }
      });
}

function getValueSave2(url2){
	alert("操作成功！");
	    // window.dialogArguments.Close();
	  	window.dialogArguments.refreshForm(url2);
	    Close();
}

function initSetFdyZy(){		
	if($("zyFlist").options.length > 0){
		$("zyFlist").options[0].selected = true;
	}
	if($("zyTlist").options.length > 0){
		$("zyTlist").options[0].selected = true;
	}
	if($("zgh").value == ""){
		$("addFdyZyB").disabled = true;
		$("delFdyZyB").disabled = true;
		$("zyFlist").disabled = true;
		$("zyTlist").disabled = true;
	}
	if($("zyFlist").options.length < 1){
		$("addFdyZyB").disabled = true;
	}
	if($("zyTlist").options.length < 1){
		$("delFdyZyB").disabled = true;
	}
}	

function getDzbsjInfo(){
	if($("fdyxmList").options.length < 1 || $("fdyxmList").selectedIndex < 0){
		return false;
	}
	$('zgh').value=$("fdyxmList").value;
	refreshForm('jhzy_szdw_dzzsjhf.do');
}

function addFdyZy(){
	var fdyIndx = $("fdyxmList").selectedIndex;
	var fromIndx = $("zyFlist").selectedIndex;
	if(fdyIndx < 0){
		alert("请选择（双击）辅导员！");
		return false;
	}
	if(fromIndx < 0){
		//alert("请选择要添加给该辅导员的班级！");
		return false;
	}
	$("zyTlist").options[$("zyTlist").options.length] = new Option($("zyFlist").options[fromIndx].text,$("zyFlist").options[fromIndx].value);
	$("zyFlist").options[fromIndx] = null;
	if($("zyFlist").options.length > 0){
		$("zyFlist").options[0].selected = true;
		$("delFdyZyB").disabled = false;
	}else{		
		$("addFdyZyB").disabled = true;
	}
	if($("zyTlist").options.length > 0){
		$("zyTlist").options[0].selected = true;
	}
}	

function delFdyZy(){
	var fdyIndx = $("fdyxmList").selectedIndex;
	var toIndx = $("zyTlist").selectedIndex;
	if(fdyIndx < 0){
		alert("请选择（双击）辅导员！");
		return false;
	}
	if(toIndx < 0){
		//alert("请选择要从该辅导员名下移去的班级！");
		return false;
	}
	$("zyFlist").options[$("zyFlist").options.length] = new Option($("zyTlist").options[toIndx].text,$("zyTlist").options[toIndx].value);
	$("zyTlist").options[toIndx] = null;
	if($("zyTlist").options.length > 0){
		$("zyTlist").options[0].selected = true;
		$("addFdyZyB").disabled = false;
	}else{		
		$("delFdyZyB").disabled = true;
	}	
	if($("zyFlist").options.length > 0){
		$("zyFlist").options[0].selected = true;
	}
}

function saveBzrZy(){
	setEleDisable("button");
	showTips("处理数据中，请等待......");
	for(var i = 0 ; i < $("zyTlist").length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "zydms";
		tmp.value = $("zyTlist").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	refreshForm("jhzy_pjpysz.do?method=dzzsjhfSave");
}

/**
 * 把班主任辅导员编班信息转移至历史库中
 */
function putLsjl(){
	if(confirm("要保存本学年的辅导员和班主任编班记录到历史库吗？\n (会把历史库里本学年的相关编班信息删除)!") ){
		showTips("处理数据中，请等待......");
	    refreshForm("szdwfzjy.do?method=putLsjl");
	}
}








	
	