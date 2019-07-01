function addTab(url,x,y) {
	showTopWin(url, x, y);
}
function modiTab(url,x,y) {
	var pkValue = "";
	var xh = "" ;

	if (curr_row == null) {
		alert("请选择要修改的数据！\n（单击相应的行）");
		return false;
	} else {
		//if (confirm("确定要修改该行数据吗？")) {
			pkValue = curr_row.getElementsByTagName("input")[0].value;
			xh = curr_row.getElementsByTagName("input")[1].value;
			//alert(xh);
			url += pkValue+"&xh="+xh;
			showTopWin(url, x, y);
			return true;
		//} else {
		//	return false;
		//}
	}
}

function viewTab(url,x,y) {
	var pkValue = "";
	var xh = "" ;
			pkValue = curr_row.getElementsByTagName("input")[0].value;
			xh = curr_row.getElementsByTagName("input")[1].value;
			url += pkValue+"&xh="+xh;
			//alert(url);
			showTopWin(url, x, y);
}

function delTab(url) {
    var RowsStr="!!#!!";
    for (i=0; i<document.getElementsByName("pk").length; i++){
		if(document.getElementsByName("pk")[i].checked){
			RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
		}
    }
    document.forms[0].pkstring.value = RowsStr;
    alert(RowsStr);
    if (RowsStr=="!!#!!"){
         alert("请选择要批量删除的记录！\n(单击每条记录前复选框)");
	   return false;
	}

    if (!confirm("确定要删除所选记录？")){
	   return false;
    }
    document.forms[0].action=url;
    document.forms[0].submit();
}

function del5s(url) {
    var RowsStr="!!#!!";
    for (i=0; i<document.getElementsByName("checkVal").length; i++){
	   if(document.getElementsByName("checkVal")[i].checked){
		RowsStr+=document.getElementsByName("checkVal")[i].value+"!!#!!";
	   }
    }
    document.forms[0].pkstring.value = RowsStr;
       if (RowsStr=="!!#!!"){
         alert("请选择要批量删除的记录！\n(单击每条记录前复选框)");
	   return false;
	}

    if (!confirm("确定要删除所选记录？")){
	   return false;
    }
    document.forms[0].action=url;
    document.forms[0].submit();
}

function initPrintZyList(){
   // dataLoad(true);
	var xydm = "";
	var query = "";
	var userName = "";
	var isFdy = "false";
	var isBzr = "false";
	var nj    = "";
	if($("xy")){xydm = $("xy").value};	
	if($("nj")&&$("nj")!=null){
	    nj = $("nj").value;
	}
		GetListData.getZyList(xydm,nj,userName,isFdy,isBzr,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "zy";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");										
					$(objId).options[0].value = "";
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

function initPrintBjList(){
    
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var isFdy="false";
	var isBzr="false";
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	
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
		GetListData.getBjList(query,userName,isFdy,isBzr,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){				   
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"dm","mc");
					$(objId).options[0] = null;	
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

function setYfList(xq){
	getSzPjpyDAO.dao_getYf(xq,function initTjList(data){
		if (data != null && typeof data == 'object') {
			var objId = "yf";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"yfdm","yfmc");										
				$(objId).options[0].value = "";
			}
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
	});
}

function sh5s(url,shzt) {
    var RowsStr="!!@@!!";
    for (i=0; i<document.getElementsByName("checkVal").length; i++){
	   if(document.getElementsByName("checkVal")[i].checked){
		RowsStr+=document.getElementsByName("checkVal")[i].value+"!!@@!!";
	   }
    }
    document.forms[0].pkstring.value = RowsStr;
    if (RowsStr=="!!@@!!"){
       alert("请选择要批量审核的记录！\n(单击每条记录前复选框)");
	   return false;
	}
	
	if(confirm("确定要审核所勾选数据嘛？")){
		url+="&sh="+shzt;
		document.forms[0].action=url;
	    document.forms[0].submit();
    }else{
    	return false;
    }
}