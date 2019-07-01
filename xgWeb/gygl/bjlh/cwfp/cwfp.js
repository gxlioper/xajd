//更改校区时要刷新楼栋,获取未划分宿舍信息列表
function bjlhssFp_Xq(){  
   $("xb").value="";
   bjlhinitSsFpLdList();//刷新楼栋信息
   bjlhinitCwFpCwXxList(); // 
}

//获取楼栋列表
function bjlhinitSsFpLdList(){
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
}

//获取未划分宿舍信息列表
function bjlhinitCwFpCwXxList(){
    var objId = "oracleList";
    var show = objId+"show";   
    showDivWait(show,'10%','50%');	
	var lddm = "";
	var cs = "";
	var xqdm = "";
	var xb ="";
	var fpbj = "";
	if($("ld")){lddm = $("ld").value};
	if($("cs")){cs = $("cs").value};
	if($("xq")){xqdm = $("xq").value};
	if($("xb")){xb = $("xb").value};
	if($("fpbj")){fpbj = $("fpbj").value};
	
	   getBjlhGyglDAO.getCwFpCwXxList(xqdm,xb,lddm,cs,fpbj,function initTjList(data){
			if (data != null && typeof data == 'object') {
				//var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"dm","mc");
					if($(show)){
		               $(show).style.display= "none";
	                }
				}
			}
		});
}

function bjlhinitCwFpWfpXsXxList(){
    var objId = "oracleXsList";
    var show = objId+"show";
    showDivWait(show,'70%','50%');	
	var xydm = "";
	var zydm = "";
	var bjdm = "";
	var nj = "";
	var xb = "";
	var fpbj = "";
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
	if($("xb")){xb = $("xb").value};
	if($("fpbj")){fpbj = $("fpbj").value};
	
		getBjlhGyglDAO.getCwFpXsXxList(xydm,zydm,bjdm,nj,xb,fpbj,function initTjList(data){
			if (data != null && typeof data == 'object') {
				//var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");	
					oldCondiSqlVConn();
				   if($(show)){	
		               $(show).style.display= "none";
	               }																					
				}
			}
		});		
}

//获取宿舍划分已分配宿舍信息列表
function bjlhinitCwFpFpSxXxList(){
    var objId = "sql";
    var show = objId+"show";
    showDivWait(show,'70%','50%');	
	var lddm = "";
	var xq = "";
	var xydm = "";
	var zydm = "";
	var bjdm = "";
	var nj = "";
	var cs = "";
	var xb ="";
	var fpbj = "";
	if($("ld")){lddm = $("ld").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
	if($("cs")){cs = $("cs").value};
	if($("xb")){xb = $("xb").value};
	if($("fpbj")){fpbj = $("fpbj").value};
	
		getBjlhGyglDAO.getCwFpYfpXsXxList(xydm,zydm,bjdm,nj,xb,lddm,cs,fpbj,function initTjList(data){
			if (data != null && typeof data == 'object') {
				//var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");	
					oldCondiSqlVConn();
				   if($(show)){	
		               $(show).style.display= "none";
	               }																					
				}
			}
		});		
}

//获取宿舍划分楼层列表
function bjlhinitSsFpFpCsList(){
	var lddm = "";	
	if($("ld")){lddm = $("ld").value};;
		getBjlhGyglDAO.getSsFpCsList(lddm,function initTjList(data){
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

//获取未分配学生总数,及已划分床位总数
function bblhssfpTj(){
    var xydm = "";
    var zydm = "";
    var bjdm = "";
    var nj = "";
    var lddm = "";
    var cs = "";
    var fpbj = "";
    if($("xy")){xydm = $("xy").value};
    if($("zy")){zydm = $("zy").value};
    if($("bj")){bjdm = $("bj").value};
    if($("nj")){nj = $("nj").value};
    if($("ld")){lddm = $("ld").value};
    if($("cs")){cs = $("cs").value};
	if($("fpbj")){fpbj = $("fpbj").value};
	getBjlhGyglDAO.getCwFpZsData(xydm,zydm,bjdm,nj,fpbj,function(data){//未分配
       if(data!=null){
        $('allbody').innerText=data[0];//未分配学生总人数
       	$('allboy').innerText=data[1];//未分配男生人数
       	$('allgirl').innerText=data[2];//未分配女生人数
       }
    });
    getBjlhGyglDAO.getCwFpYhfcws(xydm,zydm,bjdm,nj,lddm,cs,fpbj,function initArray2(data){//已划分
		$('totalBed').innerText = data[0];//已划分床位总数
		$('boyBed').innerText = data[1];//已划分床位男女总数
		$('girlBed').innerText = data[2];//已划分床位女生总数
		$('xlBed').innerText = data[3];//已划分床位混合总数
	});	
	 getBjlhGyglDAO.getCwFpWhfcws(xydm,lddm,cs,fpbj,function initArray2(data){//已划分
		$('totalBedF').innerText = data[0];//已划分床位总数
		$('boyBedF').innerText = data[1];//已划分床位男女总数
		$('girlBedF').innerText = data[2];//已划分床位女生总数
		$('bgBedF').innerText = data[3];//已划分床位混合总数
	});	
}

function setNjList(lx){
	var objId = "nj";
	getBjlhGyglDAO.getNjList(lx,function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setXyList(lx){
	var objId = "xy";
	getBjlhGyglDAO.getXyZyBjList("xy",lx,"","","",function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setZyList(lx){
	var nj = $("nj").value;
	var xydm = $("xy").value;
	var objId = "zy";
	getBjlhGyglDAO.getXyZyBjList("zy",lx,nj,xydm,"",function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function setBjList(lx){
	var nj = $("nj").value;
	var xydm = $("xy").value;
	var zydm = $("zy").value;
	var objId = "bj";
	getBjlhGyglDAO.getXyZyBjList("bj",lx,nj,xydm,zydm,function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}

function addBjlhCwBatchColum(){//床位分配          
        var oraObject =  document.getElementById("oracleList");
        var oracleXsList= document.getElementById("oracleXsList");            
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
	    for(var i=0;i<oracleXsList.options.length;i++){
		    if(oracleXsList.options[i].selected){
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
	    	showMessage('showDiv',true,'#C7DEFC');    	    	    	        	
       }     
}

function addBjlhXlCw(){//行李床位分配          
        var oraObject =  document.getElementById("oracleList");
        var countCw = 0;
		var xlValue = new Array();
        
		document.forms[0].conditionXlValue.value = "";
        for(var i=0;i<oraObject.options.length;i++){
		    if(oraObject.options[i].selected){
			  xlValue[countCw] = oraObject.options[i].value;
			  countCw++;
		    }
	    }
	    document.forms[0].conditionXlValue.value = xlValue;
	    
	    if(countCw==0){
		   alert('请在左边\'未分配床位\'列表中，选择一条或多条记录！');
		   return false;
	    }
	    hiddenField();
        showTips();
        refreshForm('bjlh_gygl_saveXlCwfp.do')
}

function addBjlhCwColum(){
        var rzrq = "";
        if($("rzrq"))rzrq=$("rzrq").value;
        if(rzrq==""){
           alert("请输入入住时间！");
           return false;
        }
        hiddenMessage(true,true);
        var oraObject =  document.getElementById("oracleList");
	    var oracleXsList= document.getElementById("oracleXsList"); 
	    var cwVarr =  Array();
	    var cwTarr =  Array();	
	    var xhVarr =  Array();
	    var xhTarr =  Array();
	    var j = 0; 
	    var k = 0; 
	    var n = document.forms[0].sql.options.length;
	    var countXh = 0; 
	    for(var i=0;i<oracleXsList.options.length;i++){
		    if(oracleXsList.options[i].selected){
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
        for(i=0;i<oracleXsList.options.length;i++){
            if(oracleXsList.options[i].selected){
                 xhVarr[k]=document.forms[0].oracleXsList.options[i].value;
                 xhTarr[k]=document.forms[0].oracleXsList.options[i].text;
                 document.forms[0].oracleXsList.options[i]=null;
                 i--;
                 k++;
            }
        }
        for (i = 0; i < countXh; i++) {     	   
             document.forms[0].sql.options[n++] = new Option(xhTarr[i]+'/'+cwTarr[i]+'/'+rzrq,xhVarr[i]+'/'+cwVarr[i]+'/'+rzrq);                                                       
        }
         compartStatus = true;      
} 

function delBjlhCwBatchColum(){    
    var sqlObject =  document.getElementById("sql");
    var n = document.forms[0].oracleList.options.length;
    var m = document.forms[0].oracleXsList.options.length;
    var count = 0;
    for(var i=0;i<sqlObject.options.length;i++){
		if(sqlObject.options[i].selected){
			count++;
		}
	}
	if(count==0){
		alert('请在右边\'已分配情况\'列表中中选择一条或多条记录！');
		return false;
	}
    for(i=0;i<sqlObject.options.length;i++){
         if(sqlObject.options[i].selected){
              sqlV = sqlObject.options[i].value;
              sqlT = sqlObject.options[i].text;
              
              sqlTArr = sqlT.split('/');
              sqlVArr = sqlV.split('/');
              document.forms[0].oracleXsList.options[m++] = new Option(sqlTArr[0]+'/'+sqlTArr[1]+'/'+sqlTArr[2],sqlVArr[0]);
              document.forms[0].oracleList.options[n++] = new Option(sqlTArr[3]+'/'+sqlTArr[4]+'/'+sqlTArr[5],sqlVArr[1]+'/'+sqlVArr[2]+'/'+sqlVArr[3]+'/'+sqlVArr[4]);    
              document.forms[0].sql.options[i]=null;
              i--;              
         }
    }   
    compartStatus = true; 
}
//保存操作
function bjlhCwDistSave(){
  hiddenField();
  showTips();
  saveConditionSql();
  refreshForm('bjlh_gygl_saveCwfp.do');
}

function showDivWait(showlist,leftv,topv){  
    var d_html = "<table width='180' border='0' ><tr><td height='10' align='center'><font color='red'>数据刷新中，请稍候......</font>";
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


function bjlhbeforSSFPSubmit() {
   if (compartStatus) {
		if (confirm(" 已划分情况列表发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
	         bjlhdormDistSave();
	    } else {
			refreshForm('bjlh_gygl_cwfp.do')
		}
	}
}