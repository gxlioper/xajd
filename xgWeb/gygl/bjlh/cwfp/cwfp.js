//����У��ʱҪˢ��¥��,��ȡδ����������Ϣ�б�
function bjlhssFp_Xq(){  
   $("xb").value="";
   bjlhinitSsFpLdList();//ˢ��¥����Ϣ
   bjlhinitCwFpCwXxList(); // 
}

//��ȡ¥���б�
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

//��ȡδ����������Ϣ�б�
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

//��ȡ���Ữ���ѷ���������Ϣ�б�
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

//��ȡ���Ữ��¥���б�
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

//��ȡδ����ѧ������,���ѻ��ִ�λ����
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
	getBjlhGyglDAO.getCwFpZsData(xydm,zydm,bjdm,nj,fpbj,function(data){//δ����
       if(data!=null){
        $('allbody').innerText=data[0];//δ����ѧ��������
       	$('allboy').innerText=data[1];//δ������������
       	$('allgirl').innerText=data[2];//δ����Ů������
       }
    });
    getBjlhGyglDAO.getCwFpYhfcws(xydm,zydm,bjdm,nj,lddm,cs,fpbj,function initArray2(data){//�ѻ���
		$('totalBed').innerText = data[0];//�ѻ��ִ�λ����
		$('boyBed').innerText = data[1];//�ѻ��ִ�λ��Ů����
		$('girlBed').innerText = data[2];//�ѻ��ִ�λŮ������
		$('xlBed').innerText = data[3];//�ѻ��ִ�λ�������
	});	
	 getBjlhGyglDAO.getCwFpWhfcws(xydm,lddm,cs,fpbj,function initArray2(data){//�ѻ���
		$('totalBedF').innerText = data[0];//�ѻ��ִ�λ����
		$('boyBedF').innerText = data[1];//�ѻ��ִ�λ��Ů����
		$('girlBedF').innerText = data[2];//�ѻ��ִ�λŮ������
		$('bgBedF').innerText = data[3];//�ѻ��ִ�λ�������
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

function addBjlhCwBatchColum(){//��λ����          
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
		   alert('�������\'δ���䴲λ\'�б��У�ѡ��һ���������¼��');
		   return false;
	    }
	    for(var i=0;i<oracleXsList.options.length;i++){
		    if(oracleXsList.options[i].selected){
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
	    	showMessage('showDiv',true,'#C7DEFC');    	    	    	        	
       }     
}

function addBjlhXlCw(){//���λ����          
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
		   alert('�������\'δ���䴲λ\'�б��У�ѡ��һ���������¼��');
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
           alert("��������סʱ�䣡");
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
		alert('�����ұ�\'�ѷ������\'�б�����ѡ��һ���������¼��');
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
//�������
function bjlhCwDistSave(){
  hiddenField();
  showTips();
  saveConditionSql();
  refreshForm('bjlh_gygl_saveCwfp.do');
}

function showDivWait(showlist,leftv,topv){  
    var d_html = "<table width='180' border='0' ><tr><td height='10' align='center'><font color='red'>����ˢ���У����Ժ�......</font>";
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


function bjlhbeforSSFPSubmit() {
   if (compartStatus) {
		if (confirm(" �ѻ�������б����˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
	         bjlhdormDistSave();
	    } else {
			refreshForm('bjlh_gygl_cwfp.do')
		}
	}
}