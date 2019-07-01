//更改校区时要刷新楼栋,获取未划分宿舍信息列表
function bjlhssFp_Xq(){  
   $("xb").value="";
   setLdList();//刷新楼栋信息
   bjlhinitSsFpSsXxList(); // 
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
function bjlhinitSsFpSsXxList(){
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
	
	   getBjlhGyglDAO.getSsFpSsXxList(xqdm,xb,lddm,cs,function initTjList(data){
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

//获取宿舍划分已分配宿舍信息列表
function bjlhinitSsFpFpSxXxList(){
    var objId = "sql";
    var show = objId+"show";
    showDivWait(show,'70%','50%');	
	var lddm = "";
	var xq = "";
	var xydm = "";
	var cs = "";
	var fpbj = "";
	if($("ld")){lddm = $("ld").value};
	if($("xy")){xydm = $("xy").value};
	if($("cs")){cs = $("cs").value};
	if($("fpbj")){fpbj = $("fpbj").value};
		getBjlhGyglDAO.getSsFpFpSsXxList(lddm,cs,xydm,fpbj,function initTjList(data){
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

//获取未分配学生总数,及已划分床位总数
function bblhssfpTj(){
    var xydm = "";
    var fpbj = "";
    if($("xy")){xydm = $("xy").value};
	if($("fpbj")){fpbj = $("fpbj").value};
	getBjlhGyglDAO.getCwFpZsDatas(xydm,fpbj,function(data){//未分配
       if(data!=null){
        $('allbody').innerText=data[0];//未分配学生总人数
       	$('allboy').innerText=data[1];//未分配男生人数
       	$('allgirl').innerText=data[2];//未分配女生人数
       }
    });
    getBjlhGyglDAO.getSsFpYhfcws(xydm,fpbj,function (data){//已划分
	    if (data != null) {
	    	$('totalBed').innerText = data[0];//已划分床位总数
			$('boyBed').innerText = data[1];//已划分床位男女总数
			$('girlBed').innerText = data[2];//已划分床位女生总数
			$('bgBed').innerText = data[3];//已划分床位混合总数	
	    }
	});	
}

//划分操作
function bjlhaddBatchColum(){
	var xyV = "";
	var fpbj = document.getElementById('fpbj').value;
    if($("xy")){xyV = $("xy").value};
     if((xyV==null||xyV=="") && fpbj == 'qrz'){
        alert("请先选择"+jQuery("#xbmc").val()+"！");
        return false;
      }
     var oraObject =  document.getElementById("oracleList");
     if(oraObject.options.length==0){
         alert("\'未划分宿舍\'列表为空！");
         return false;
     }         

                var xyIndex = document.forms[0].xy.selectedIndex;
                var nj  = '';
                var xyV = document.forms[0].xy.options[xyIndex].value;
                var xyT = document.forms[0].xy.options[xyIndex].text; 
                
                if ('0202'==fpbj) {
                	xyV = "0202";
                	xyT = "团委";
                } else if ('0405'==fpbj) {
                	xyV = "0405";
                	xyT = "体育教学部";
                } else if ('0110'==fpbj) {
                	xyV = "0110";
                	xyT = "科研处";
                } else if ('0117'==fpbj) {
                	xyV = "0117";
                	xyT = "成人教育处";
                }
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
                    document.forms[0].sql.options[n++] = new Option(xyT+'/'+oraT,xyV+'/'+oraV+'/'+nj);
                    document.forms[0].oracleList.options[i]=null;
                     i--;                    
                    }
                 }  
                 compartStatus = true;  
}

//释放操作
function bjlhdelBatchColum(){    
    var oraObject =  document.getElementById("oracleList");
    var sqlObject =  document.getElementById("sql");
    var n = document.forms[0].oracleList.options.length;
    var m = document.forms[0].sql.options.length;
	if(m==0){
		alert('\'已划分情况\'列表为空！');
		return false;
	}	
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
                  
                 var sqlV = sqlObject.options[i].value;
                 var sqlT = sqlObject.options[i].text;
                              
                 var sqlVArr = sqlV.split('/');
                 var sqlTArr = sqlT.split('/');
                 
                 for(j=0;j<oraObject.options.length;j++){
                     var oraV = oraObject.options[j].value;
                     if(oraV.substr(0,oraV.indexOf('/'))==sqlVArr[1]){
                        sqlVArr[2] =  parseInt(sqlVArr[2])+parseInt(oraV.substr(oraV.indexOf('/')+1,oraV.length));
                     }
                 }   
                 
                 var optionText = ""
                 var optionTextArr = sqlT.split("/");
                 for (var k=1;k<optionTextArr.length;k++) {
                 	optionText += optionTextArr[k] + "/";
                 }
                 var optionValue =  sqlVArr[1]+'/'+sqlVArr[2];                  
                 document.forms[0].oracleList.options[n++] = new Option(optionText.substr(0,optionText.length-1),optionValue);
                 document.forms[0].sql.options[i]=null;
                 i--;                
             }
          }
          compartStatus = true;    
}
//保存操作
function bjlhdormDistSave(){
  hiddenField();
  showTips();
  saveConditionSql();
  refreshForm('bjlh_gygl_saveSsfp.do')
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
   var clinText = "";
   var xyText  = document.forms[0].xydm.options[document.forms[0].xydm.selectedIndex].text;
   clinText = xyText ;
   if (compartStatus) {
		if (confirm(clinText+" 已划分情况列表发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
	         bjlhdormDistSave();
	    } else {
			refreshForm('bjlh_gygl_ssfp.do')
		}
	}
}

function bjlhsetLdList(){
	var xqdm = $("xq").value;
	var objId = "ld";
	var xbxd = $("xb").value;
	getBjlhGyglDAO.getxbXqLdList("ld",xqdm,"",xbxd,function(data){
		if(data !=null && data.length >0){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");
			$(objId).options[0].value="";
		}
	});
}