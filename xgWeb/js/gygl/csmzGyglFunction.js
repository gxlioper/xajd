var distriStatus = false;
function initTjList(data){
	if (data != null && typeof data == 'object') {
		var objId = data[0].dm;		
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");			
			$(objId).options[0].value = "";
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ȡ���Ữ��������Ϣ�б�
function xsInitSsFpSsXxList(){ 
    //dataLoad(true); 
	var lddm = "";
	var xqdm = "";
	var xb ="";
	if($("ld")){lddm = $("ld").value};
	if($("xq")){xqdm = $("xq").value};
	if($("xb")){xb = $("xb").value};
	GetGyglDataInfo.getSsFpSsXxList(xqdm,xb,lddm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
	//dataLoad(false);	
}
function xsInitLdXbXdList(){
	var xq = "";	
	if($("xq")){xqdm = $("xq").value};
       GetGyglDataInfo.getLdXbXdList(xqdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "xb";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					DWRUtil.removeAllOptions("lddm");
					DWRUtil.removeAllOptions("oracleList");					
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
}

//��ȡ���Ữ��¥���б�
function xsinitSsFpLdList(){
    //dataLoad(true);
	var xqdm = "";
	var xbxd = "";	
	if($("xq")){xqdm = $("xq").value};
	if($("xb")){xbxd = $("xb").value};			
		GetGyglDataInfo.getSsFpLdList(xqdm,xbxd,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "lddm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					DWRUtil.removeAllOptions("oracleList");									
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
	//dataLoad(false);	
}

function addDisDorm(){      
	  var comp = "no";
	  var fpfs =  document.getElementById('fpfs').value;
	  var nj = document.getElementById("nj").value;
      if(document.forms[0].xb.value==null&&document.forms[0].xb.value==""){
        alert("����ѡ���Ա�!");
        return false;
      } 
      if(document.forms[0].xydm.value==null||document.forms[0].xydm.value==""){
        alert("����ѡ��"+jQuery("#xbmc").val()+"!");
        return false;
      } 
      var oraObject =  document.getElementById("oracleList");
      if(oraObject.options.length==0){
         alert("\'δ��������\'�б�Ϊ�գ�");
         return false;
      }     
      if(fpfs=="cw"){//����λ
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
        showTopWin('/xgxt/csmz_gygl.do?method=cwsFp&qssycws='+qssycws+"&nj="+nj+"&doType=add",380,200);
        distriStatus = true;
        return false;
      }else{
      //����������
        //var nj = document.getElementById("nj").value;
      	var oracleListIndex = document.forms[0].oracleList.selectedIndex;
      	if(oracleListIndex==-1){
             return false;      	
      	}
      	var xyIndex = document.forms[0].xy.selectedIndex;
      	var xyV = document.forms[0].xy.options[xyIndex].value;
	  	var xyT = document.forms[0].xy.options[xyIndex].text;
	  	oracleListIndex = (oracleListIndex==-1)?"0":oracleListIndex;
	  			 
        var addOracleV = "";
	    var addOracleT = "";
	  	
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
//                oraV=oraObject.options[i].value;
//                oraT=oraObject.options[i].text;        
//                document.forms[0].sql.options[n++] = new Option(nj+'/'+xyT+'/'+oraT,xyV+'/'+oraV+'/'+nj);
//                document.forms[0].oracleList.options[i]=null;                
                addOracleV = oraObject.options[i].value;
	            addOracleT = oraObject.options[i].text;	  
                
                document.forms[0].sql.options[n++] = new Option(xyT + "/" + addOracleT,xyV + "/" + addOracleV+"/"+nj);
                document.forms[0].oracleList.options[i]=null;
                i--;                    
            }
        }	  	
	  	distriStatus = true;	
//	  	if(addOracleV!=""){	
//	  	  for (i = 0; i < document.forms[0].sql.options.length; i++) {			
//		      if(xyT + "/" + addOracleV == document.forms[0].sql.options[i].text){
//			     comp = "yes";
//		      }	
//	        }	       
//	      if(comp == "yes"){
//	      	alert("�ѻ��������Ѵ��ڸ������ţ�");
//	      	return false;
//	      }else{
//	          distriStatus = true;		  	
//              document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(xyT + "/" + addOracleT,xyV + "/" + addOracleV+"/"+nj);
//              document.forms[0].oracleList.options[oracleListIndex]=null;
//	      }
//	    }	
      }    	         
 }

function delDisDorm(){
        var m = document.forms[0].sql.options.length;
        var fpfs =  document.getElementById('fpfs').value; 
        var oraObject =  document.getElementById("oracleList");
        var sqlObject =  document.getElementById("sql");
        var n = document.forms[0].oracleList.options.length;  
         var flag = false;      
        if(m==0){
		   alert('\'�ѻ������\'�б�Ϊ�գ�');
		   return false;
	    }
	    if(fpfs=="cw"){//����λ
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
            var oracleListIndexCw = document.forms[0].sql.selectedIndex;
    	    var acwValue = document.forms[0].sql.options[oracleListIndexCw].text;
    	    var acwV = document.forms[0].sql.options[oracleListIndexCw].value;
    	    var qssycws = acwValue.substring(acwValue.lastIndexOf('/')+1);
    	    var nj = acwV.substring(acwV.length-4,acwV.length);    
            showTopWin('/xgxt/csmz_gygl.do?method=cwsFp&qssycws='+qssycws+"&nj="+nj+"&doType=del",380,200);
            distriStatus = true;
            return false;	        
	    }else{//������
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
          for(var i=0;i<sqlObject.options.length;i++){ 
             if(sqlObject.options[i].selected){
                 var sqlV = document.forms[0].sql.options[i].value;
	             var sqlT = document.forms[0].sql.options[i].text;	  
	             var strTem = sqlV.split("/");	   
	             var sqlvalue = sqlV.substring(sqlV.indexOf('/')+1,sqlV.indexOf('/',sqlV.indexOf('/')+1));	   	  
	             var yfcws = sqlT.substring(sqlT.lastIndexOf('/')+1);
	             var oracleList = document.getElementById('oracleList').options;
	            
	             var num = -1;
	             for(var j=0;j<oracleList.length;j++){
	   		        var oraclevalue = oracleList[j].value.substring(0,(oracleList[j].value).indexOf('/'));
	   		        if(oraclevalue==sqlvalue){
	   			       flag = true;
	   			       num = j;
	   			       break;
	   		        }
	             }
	            if(flag){
	               var value = oracleList[num].value;
	   		       var textvalue = oracleList[num].innerText;
	   		       var textvalue1 = oracleList[num].innerText = textvalue.substring(0,textvalue.lastIndexOf('/')+1)+(parseInt(textvalue.substring(textvalue.lastIndexOf('/')+1))+parseInt(yfcws));
	   		    //oracleList[num] = null;  alert(value+","+textvalue+","+textvalue1); 		
	   		       document.forms[0].oracleList.options[num] = new Option(textvalue1,value);
	            }else{
	               document.forms[0].oracleList.options[n++] = new Option( sqlT.substring(sqlT.indexOf("/")+1,sqlT.length),sqlV.substring(sqlV.indexOf("/")+1,sqlV.lastIndexOf("/")));
                }
                sqlObject.options[i]=null;
                i--;	                  	         	            	             	             
	         }
         }	
           distriStatus = true;              	          
	    }      
//       var sqlIndex = document.forms[0].sql.selectedIndex;
//       if(sqlIndex==-1){
//          return false;
//       }
//       var sqlV = document.forms[0].sql.options[sqlIndex].value;
//	   var sqlT = document.forms[0].sql.options[sqlIndex].text;	   
//	   var strTem = sqlV.split("/");	   
//	   var sqlvalue = sqlV.substring(sqlV.indexOf('/')+1,sqlV.indexOf('/',sqlV.indexOf('/')+1));	   	  
//	   var yfcws = sqlT.substring(sqlT.lastIndexOf('/')+1);
//	   var oracleList = document.getElementById('oracleList').options;
//	   var flag = false;
//	   var num = -1;
//	   for(var i=0;i<oracleList.length;i++){
//	   		var oraclevalue = oracleList[i].value.substring(0,(oracleList[i].value).indexOf('/'));
//	   		if(oraclevalue==sqlvalue){
//	   			flag = true;
//	   			num = i;
//	   			break;
//	   		}
//	     }
//	    if(flag){
//	        var value = oracleList[num].value;
//	   		var textvalue = oracleList[num].innerText;
//	   		var textvalue1 = oracleList[num].innerText = textvalue.substring(0,textvalue.lastIndexOf('/')+1)+(parseInt(textvalue.substring(textvalue.lastIndexOf('/')+1))+parseInt(yfcws));
//	   		//oracleList[num] = null;  alert(value+","+textvalue+","+textvalue1); 		
//	   		document.forms[0].oracleList.options[num] = new Option(textvalue1,value);
//	     }else{
//	      document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = new Option( sqlT.substring(sqlT.indexOf("/")+1,sqlT.length),sqlV.substring(sqlV.indexOf("/")+1,sqlV.lastIndexOf("/")));
//         }
//       document.forms[0].sql.options[sqlIndex]=null;
 }
 
  function addDisDormList2(){ 
        distriStatus = true;
        var doType =  document.getElementById('doType').value;
        var cws = document.getElementById('cwfps').value;           
        if(doType=="add"){//����λ����
           var nj = document.getElementById('nj').value;
		   var oracleListIndex = document.forms[0].oracleList.selectedIndex;
    	   var acwValue = document.forms[0].oracleList.options[oracleListIndex].text;
    	   var qssycws = acwValue.substring(acwValue.lastIndexOf('/')+1);   	   
    	   var qssycws1 = parseInt(qssycws)-parseInt(cws);
      	   var xyIndex = document.forms[0].xy.selectedIndex;
      	   var xyV = document.forms[0].xy.options[xyIndex].value;
	 	   var xyT = document.forms[0].xy.options[xyIndex].text;
      	   var addOracleV = document.forms[0].oracleList.options[oracleListIndex].value;
	  	   var addOracleT = document.forms[0].oracleList.options[oracleListIndex].text;
	  	   var addOracleT1 = addOracleT.substring(0,addOracleT.lastIndexOf('/'))+'/'+cws;
	 	   var addOracleV1 = addOracleV.substring(0,addOracleV.indexOf('/')+1)+cws;	
	  	   var addOracleT2 = addOracleT.substring(0,addOracleT.lastIndexOf('/'))+'/'+qssycws1;
	 	   var addOracleV2 = addOracleV.substring(0,addOracleV.indexOf('/')+1)+qssycws1;
	 		
	       document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(xyT + "/" + addOracleT1, xyV + "/" + addOracleV1+"/"+nj);
     	   if(qssycws1=='0'){
     	 	document.forms[0].oracleList.options[oracleListIndex]=null;
     	   }else{
     	 	document.forms[0].oracleList.options[oracleListIndex]=new Option(addOracleT2,addOracleV2);
     	   }
     	}else if(doType=="del"){//����λ�ͷ�
//     	   var sqlListIndex = document.forms[0].sql.selectedIndex;
//     	   var acwText = document.forms[0].sql.options[sqlListIndex].text;
//     	   var acwValue = document.forms[0].sql.options[sqlListIndex].value;
//     	   alert(acwText+"   "+acwValue);
       var sqlIndex = document.forms[0].sql.selectedIndex;
       if(sqlIndex==-1){
          return false;
       }
       var sqlV = document.forms[0].sql.options[sqlIndex].value;
	   var sqlT = document.forms[0].sql.options[sqlIndex].text;	   
	   var strTem = sqlV.split("/");	   
	   var sqlvalue = sqlV.substring(sqlV.indexOf('/')+1,sqlV.indexOf('/',sqlV.indexOf('/')+1));	   	  
	   var yfcws = sqlT.substring(sqlT.lastIndexOf('/')+1);
	   var oracleList = document.getElementById('oracleList').options;
	   var flag = false;
	   var num = -1;
	   for(var i=0;i<oracleList.length;i++){
	   		var oraclevalue = oracleList[i].value.substring(0,(oracleList[i].value).indexOf('/'));
	   		if(oraclevalue==sqlvalue){
	   			flag = true;
	   			num = i;
	   			break;
	   		}
	     }
	    if(flag){
	        var value = oracleList[num].value;
	   		var textvalue = oracleList[num].innerText;
	   		var textvalue1 = oracleList[num].innerText = textvalue.substring(0,textvalue.lastIndexOf('/')+1)+(parseInt(textvalue.substring(textvalue.lastIndexOf('/')+1))+parseInt(yfcws));
	   		document.forms[0].oracleList.options[num] = new Option(textvalue1,value);
	     }else{
	      document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = new Option( sqlT.substring(sqlT.indexOf("/")+1,sqlT.length),sqlV.substring(sqlV.indexOf("/")+1,sqlV.lastIndexOf("/")));
         }
       document.forms[0].sql.options[sqlIndex]=null;    	       	   
     	}     	 
     	close();
    }
 
 //��ȡ���Ữ���ѻ���������Ϣ�б�
function SsFpYFpSxXxListInit(){
    //dataLoad(true);
	var lddm = "";
	var xq = "";
	var xydm = "";
	var bjdm = "";	
	var nj = "";
	if($("ld")){lddm = $("ld").value};
	if($("xq")){xqdm = $("xq").value};
	if($("xy")){xydm = $("xy").value};
	if($("bjdm")){bjdm = $("bjdm").value};
	if($("nj")){nj = $("nj").value};
		GetGyglDataInfo.getSsFpFpSsXxList(lddm,xqdm,xydm,nj,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					oldCondiSqlVConn();					
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}			
		});	
	//dataLoad(false);	
}  

function ssFpZyListInit(){
  //dataLoad(true);
  var nj = "";
  var xydm = "";
  if($("nj")){nj = $("nj").value};
  if($("xy")){xydm = $("xy").value};
  GetGyglDataInfo.getSsFpZyList(xydm,nj,function initTjList(data){
       if (data != null && typeof data == 'object') {
				var objId = "zy";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					DWRUtil.removeAllOptions("xh");//��δ����ѧ�������				
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
	 //dataLoad(false);		
  });
}

function ssFpBjListInit(){
  //dataLoad(true);
  var nj = "";
  var zydm = "";
  if($("nj")){nj = $("nj").value};
  if($("zy")){zydm = $("zy").value};
  GetGyglDataInfo.getSsFpBjList(zydm,nj,function initTjList(data){
       if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
//					DWRUtil.removeAllOptions("xh");//��δ����ѧ�������				
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
	  //dataLoad(false);		
  });
}
  

  
function wfpXsListInit(){
  //dataLoad(true);
  var nj = "";
  var xydm = "";
  var zydm = "";
  var xb = "";
  var bjdm = "";
  var ksh = "";
  if($("nj")){nj = $("nj").value};
  if($("xy")){xydm = $("xy").value};
  if($("zy")){zydm = $("zy").value};
  if($("xb")){xb = $("xb").value;} 
  if($("bjdm")){bjdm=$("bjdm").value};
  if($("ksh")){ksh=$("ksh").value};
  GetGyglDataInfo.getWfpXsList(xydm,zydm,bjdm,nj,xb,ksh,function initTjList(data){
       if (data != null && typeof data == 'object') {
				var objId = "xh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
	//dataLoad(false);		
  });
}  

function cwfpXaioQuInit(){
  var nj = "";
  var xydm = "";
  if($("nj")){nj = $("nj").value};
  if($("xy")){xydm = $("xy").value};
  GetGyglDataInfo.getXiaoQuList(xydm,nj,function initTjList(data){
       if (data != null && typeof data == 'object') {
				var objId = "xq";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					DWRUtil.removeAllOptions("xb");
					DWRUtil.removeAllOptions("lddm");
					DWRUtil.removeAllOptions("oracleList");	
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
  });
} 

function xswFpLdXxList(){
    //dataLoad(true);
    var nj = "";
    var xydm = "";
    var xbxd = "";
    var xqdm = "";
    if($("nj")){nj = $("nj").value};
    if($("xy")){xydm = $("xy").value};
    if($("zy")){zydm = $("zy").value};
    if($("xb")){xbxd = $("xb").value};
    GetGyglDataInfo.getXsCwFpLdList(xydm,nj,xqdm,xbxd,function initTjList(data){
       if (data != null && typeof data == 'object') {
				var objId = "ld";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					DWRUtil.removeAllOptions("oracleList");	
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
	//dataLoad(false);		
  });   
}

function xsCwFpSxListInit(){
    //dataLoad(true);
    var nj = "";
    var lddm = "";
    var xydm = "";
    if($("ld")){lddm = $("ld").value};
    if($("nj")){nj = $("nj").value};
    if($("xy")){xydm = $("xydm").value};
    GetGyglDataInfo.getXsCwFpSsList(lddm,nj,xydm,function initTjList(data){
       if (data != null && typeof data == 'object') {
				var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");							
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
	//dataLoad(false);		
  });   
}

function xsAddDisBedList(){
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
	       showMessage('showDiv',true,'#C7DEFC'); 
        }
    }
function xsAddCwColum(){
        var zsrq = "";
        if($("zsrq"))zsrq=$("zsrq").value;
        if(zsrq==""){
           alert("��������סʱ�䣡");
           return false;
        }
        hiddenMessage(true,true);
 	    var cwVarr =  Array();
	    var cwTarr =  Array();	
	    var xhVarr =  Array();
	    var xhTarr =  Array();
	    var j = 0; 
	    var k = 0; 
	    var n = document.forms[0].sql.options.length;
	    var xh= document.getElementById("xh"); 
	    var countXh = 0; 
	    var oraObject =  document.getElementById("oracleList");	    
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
             document.forms[0].sql.options[n++] = 
             new Option(xhTarr[i] + "/" + cwTarr[i]+'/'+zsrq,xhVarr[i] + "/" + cwVarr[i]+'/'+zsrq);                                                                    
        }
        distriStatus = true;  
}   
function xsDelDisBedList(){
    var sqlObject =  document.getElementById("sql");
    var n = document.forms[0].oracleList.options.length;
    var m = document.forms[0].xh.options.length;
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
              var sqlV = sqlObject.options[i].value;
                  sqlV = sqlV.substring(0,sqlV.lastIndexOf("/"));
              var sqlT = sqlObject.options[i].text;
                  sqlT = sqlT.substring(0,sqlT.lastIndexOf("/")); 
	          var subStrSqlT = sqlT.substring(sqlT.indexOf("/")+1,sqlT.length);
	              subStrSqlT = subStrSqlT.substring(subStrSqlT.indexOf("/")+1,subStrSqlT.length);                                 
              document.forms[0].oracleList.options[n++] =
              new Option(subStrSqlT.substring(subStrSqlT.indexOf("/")+1,subStrSqlT.length),sqlV.substring(sqlV.indexOf("/")+1,sqlV.length));        
              document.forms[0].xh.options[m++] = 
              new Option(sqlT.substring(0,sqlT.indexOf(subStrSqlT.substring(subStrSqlT.indexOf("/"),subStrSqlT.length))), sqlV.substring(0,sqlV.indexOf("/")));              
              document.forms[0].sql.options[i]=null;
              i--;             
         }
    }   
    distriStatus=true;	
    }
function oldCondiSqlVConn(){
	var sqlValue = new Array();
	document.forms[0].conditionSqlValue.value = "";
	for (i = 0; i < document.forms[0].sql.options.length; i++) {				
		sqlValue[sqlValue.length] = document.forms[0].sql.options[i].value;	
	}
	document.forms[0].oldCondiSqlValue.value = sqlValue;
}

 function sendCws(){
    this.distriStatus = true;
    var cws = document.getElementById('cwfps').value;
    window.dialogArguments.document.getElementById('doType').value=document.getElementById('doType').value;
	window.dialogArguments.document.getElementById('cwfps').value=cws;
	close();
	window.dialogArguments.document.getElementById('acwfp').click();
   }

function beforSsFpSubmit() {
	if (distriStatus) {
		if (confirm("��ǰ"+jQuery("#xbmc").val()+"��¥���ѻ�����������˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
	         xsDormDistSave();
	    } else {
	        refreshForm("/xgxt/csmz_gygl.do?method=xsDorm_Distribute");
		}
	}else{
	  return false;
	}
}

function beforcwFpSubmit() {
	if (distriStatus) {
		if (confirm("��ǰ�ѷ�����������˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
	        xsBedDistSave();
	    } else {
	        refreshForm("/xgxt/csmz_gygl.do?method=xsBed_Distribute");
		}
	}else{
	  return false;
	}
}	



//��ȡ��λ�����ѷ������ᴲλ��Ϣ�б�
function initXsYFpCwList(){
    //dataLoad(true);
	var zydm = "";
	var nj = "";
	var xb = "";
	var bjdm = "";
	var xydm = "";
	var ksh = "";
    if($("xy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("xb")){xb = $("xb").value};
	if($("bj")){bjdm = $("bj").value};
	if($("xydm")){xydm = $("xydm").value};
	if($("ksh")){ksh = $("ksh").value};
	GetGyglDataInfo.getXsYFpCwList(zydm,nj,xb,bjdm,xydm,ksh,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					oldCondiSqlVConn();												
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
			//dataLoad(false);
		});
}



function loadStrInit(){
   document.getElementById("xq").options[0].value ="";
   document.getElementById("xq").options[0].text ="--����ѡ��Ժϵ--";
}
function xsXbSele(){
     if($("xb").value=="���"){
         document.getElementById('xsXb').style.display="";
     }else{
         document.getElementById('xsXb').style.display="none";
     }
}

function autoChkStuInfo(cod , obj) {
	if (cod == 13) {
	    var val = obj.value;
	    GetGyglDataInfo.getStuKshExist("view_newstuinfo","ksh",val,function(data){
		         if(data){
		            refreshForm('/xgxt/csmz_gygl.do?method=xsZsxxAdd&xh='+val);
		            return true;
		         }else{
		            alert("�ÿ������д���򲻴��ڣ�������������룡");
		         return false;
		         }
	    });
	 }
}	

function xsZsChkAndSubmit(str2){
     if(allFilled(str2)){
      var val = document.forms[0].xh.value;
      GetGyglDataInfo.getStuKshExist("view_newstuinfo","ksh",val,function(data){
		         if(data){
		            xsZsXxSave();
		         }else{
		            alert("�ÿ������д���򲻴��ڣ�������������룡");
		            return false;
		         }
	    });
	  }  
}

function xsZsXxSave(){
     var value = document.forms[0].xh.value;
     GetGyglDataInfo.getStuKshExist("xszsxxb","xh",value,function(data){
            if(data){
		        if(confirm("��Ϊ���������˷���(��λ),�Ƿ�Ҫ���·��䣿")){
		            refreshForm('/xgxt/csmz_gygl.do?method=xsZsxxAddSave');
		            return true;
		        }else{
		            return false;
		        }
		    }else{		        
		        refreshForm('/xgxt/csmz_gygl.do?method=xsZsxxAddSave');
		        return true;
		    }       
     });
}

function ldQshInit(){   
    var lddm = ""; 
    var xydm  ="";
    var nj   ="";
    if($("lddm")){lddm = $("lddm").value};
     if($("xydm")){xydm = $("xydm").value};
      if($("nj")){nj = $("nj").value};
    GetGyglDataInfo.getQshList(lddm ,xydm,nj,function initTjList(data){
       if (data != null && typeof data == 'object') {
				var objId = "qsh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					DWRUtil.removeAllOptions("cwh");												
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
  });   
}
function ldCwhInit(){
    var ssbh = ""; 
    if($("qsh")){ssbh = $("qsh").value};
    GetGyglDataInfo.getCwhList(ssbh ,function initTjList(data){
           if (data != null && typeof data == 'object') {
				var objId = "cwh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");							
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
  });   
}
function xsDormDistSave(){
   hiddenField();
   showTips();
   saveConditionSql();
   refreshForm('/xgxt/csmz_gygl.do?method=xsDorm_Distribute&doType=save');
}

function xsBedDistSave(){
   hiddenField();
   showTips();
   saveConditionSql();
   refreshForm('/xgxt/csmz_gygl.do?method=xsBed_Distribute&doType=save');
}

//ͳ��δ���ֺ��ѻ������Ữ�ֵ�����
function getXsSSFPData(){
	var nj = document.getElementById('nj').value;
	var xydm = document.getElementById('xydm').value;
	var zydm = "";
	var bjdm = "";
	if($("zy") != null && typeof $("zy") == 'object'){zydm=document.getElementById("zy").value;}
	if($("bjdm") != null && typeof $("bjdm") == 'object'){bjdm=document.getElementById("bjdm").value;}
	GetGyglDataInfo.getXsWFPrs(nj,xydm,zydm,bjdm,function initArray1(data){
		$('allbody').innerText = data[0];
		$('allboy').innerText = data[1];
		$('allgirl').innerText = data[2];
	});
	GetGyglDataInfo.getXSYFPcws(nj,xydm,function initArray2(data){
		$('totalBed').innerText = data[0];
		$('boyBed').innerText = data[1];
		$('girlBed').innerText = data[2];
		$('bgBed').innerText = data[3];
	});
}
//ͳ��δ������ѷ���ѧ�����������
function getXsCWFPData(){
    var nj = document.getElementById('nj').value;
	var xydm = document.getElementById('xydm').value;
	var zydm = "";
	var bjdm = "";
	if($("zy") != null && typeof $("zy") == 'object'){zydm=document.getElementById("zy").value;}
	if($("bjdm") != null && typeof $("bjdm") == 'object'){bjdm=document.getElementById("bjdm").value;}
	GetGyglDataInfo.getXsWFPrs(nj,xydm,zydm,bjdm,function initArray1(data){
		$('allbody').innerText = data[0];
		$('allboy').innerText = data[1];
		$('allgirl').innerText = data[2];
	});
	GetGyglDataInfo.getXsYFPrs(nj,xydm,zydm,bjdm,function initArray2(data){
		$('totalBed').innerText = data[0];
		$('boyBed').innerText = data[1];
		$('girlBed').innerText = data[2];		
	});
}
function defaultDisBed() {
	var len = document.forms[0].oracleList.options.length > document.forms[0].xh.options.length ? 
	document.forms[0].xh.options.length : document.forms[0].oracleList.options.length;
	var sqlLen = document.forms[0].sql.options.length;
	var i;
	var j;
	var xhV;
	var xhT;
	var addOracleV;
	var addOracleT;
	for (i = 0; i < len; i++) {
		xhV = document.forms[0].xh.options[i].value;
		xhT = document.forms[0].xh.options[i].text;
		
		addOracleV = document.forms[0].oracleList.options[i].value;
		addOracleT = document.forms[0].oracleList.options[i].text;
		
		document.forms[0].sql.options[sqlLen+i] = 
		new Option(xhT + "/" + addOracleT, xhV + "/" + addOracleV);
		distriStatus = true;			    	
	}
	for (j = 0; j < len; j++) {
	    document.forms[0].oracleList.options[0]=null;
        document.forms[0].xh.options[0]=null;
	}
	
}

function xhKshSynchro(){//��������תΪѧ��
    var nj   = document.getElementById("nj").value;
    var xydm = document.getElementById("xy").value;
    var xymc = document.getElementById("xy").options[document.getElementById("xy").options.selectedIndex].text;
    var zydm = document.getElementById("zy").value;
    var zymc = document.getElementById("zy").options[document.getElementById("zy").options.selectedIndex].text;
    var bjdm = "";
    var bjmc = "";
    if($("bj")){
       bjdm = document.getElementById("bj").value;
       document.getElementById("bj").options[document.getElementById("bj").options.selectedIndex].text;
    }
    var exisTClin = "" ;
    var Clin = "\""+nj+"��\" ";
    //var querry = " nj='"+nj+"'";
    var querry = " 1=1 ";
    if(xydm!=""&&xydm!="null"){
        querry += " and xydm='"+xydm+"'";
        Clin +="\""+xymc+"\"\n";
    }
    if(zydm!=""&&zydm!="null"){
        querry += " and zydm='"+zydm+"'";
        Clin +="\""+zymc+"רҵ\" ";
    }
    if(bjdm!=""&&bjdm!="null"){
        querry += " and bjdm='"+bjdm+"'";
        Clin +="\""+bjmc+" ";
    }
    var RowsStr="";	
    var pkVArray = "'";
    var kshNum = 0;		  
	   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	if(document.getElementsByName("pkV")[i].checked){
	    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    		pkVArray+=document.getElementsByName("pkV")[i].value+"','";
	    		kshNum++;
	    	}
	   }
    document.forms[0].delPk.value = RowsStr;
    pkVArray=pkVArray.substring(0,pkVArray.length-2);
        if (RowsStr==""){
			   alert("��ѡ��Ҫ�����ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
        }	
//		if (!confirm("ȷ��ҪΪѡ�еĿ����ţ�����ѧ�ŵ�ͬ��ת����")){
//			  return false;
//		}else{		
		GetGyglDataInfo.xhKshSynchro(pkVArray,querry,function(data){		     
		     if(data>0){		        
		        if(data==kshNum){
		           alert("��ѡ��ѧ����Ŀǰ��δһ�˱���ѧ�ţ�");
		        }else{		          
		          if (confirm("��ѡ��"+kshNum+"��ѧ����¼��������"+data+"��ѧ����δ����ѧ�ţ�\n\nȷ��Ҫ���ѱ��ѧ������ѧ��ͬ��ת����\n\nת��������ݽ�ת�Ƶ�����ס����Ϣ���У�")){
	                   underDealWith();
                       refreshForm("/xgxt/csmz_gygl.do?method=xhKshSynchro")
		          }
		        }
		     }else{
		          if (confirm("ȷ��ҪΪѡ�еĿ����ţ�����ѧ�ŵ�ͬ��ת����")){
	                   underDealWith();
                       refreshForm("/xgxt/csmz_gygl.do?method=xhKshSynchro")
		          }	     
		     }
		});
//           GetGyglDataInfo.getInfoExist("view_xsxxb",querry,function(data){
//                if(data){
//                   underDealWith();
//                   refreshForm("/xgxt/csmz_gygl.do?method=xhKshSynchro")
//                }else{
//                  alert("Ŀǰ��δ��"+Clin+"ѧ������ѧ�ţ�");
//          }
//       });
//   }
}




