var compartStatus = false;

function tjSeleInit(data,objId){
	if (data != null && typeof data == 'object') {
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
function dataIinit(){
      //$("xbxd").value=""; //�����Ա��޶�ֵΪ��		   
     var oraObj = document.forms[0].oracleList;
     var ldObj = document.forms[0].lddm;
     var csObj = document.forms[0].cs;    	
     while (oraObj.options.length > 0) {	//���δ�����б�  
        oraObj.options.removeChild(oraObj.options.firstChild);
     }
     while (ldObj.options.length > 0) {	//���¥���б�  
        ldObj.options.removeChild(ldObj.options.firstChild);
     }
     while (csObj.options.length > 0) {	//��ղ����б�  
        csObj.options.removeChild(csObj.options.firstChild);
     }

}
function ldLbIinit(){//����У��ֵ��ʼ��¥���б�
      var xiaoqu = "";
      var xbxd   = "";
      var userName   = "";
      if($("xiaoqu")){xiaoqu=$("xiaoqu").value;}
      if($("xbxd")){xbxd=$("xbxd").value;}
      if($("userName")){userName=$("userName").value;}
      gyglShareData.getSsHfLdList(xiaoqu,xbxd,userName,function tjSeleInit(data){ 
          if (data != null && typeof data == 'object') {
				var objId = "lddm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					//alert('ddd');
					DWRUtil.removeAllOptions("cs");	//��ղ����б�	
					DWRUtil.removeAllOptions("oracleList");	//���δ�����б�		
				}
		   }
      });
}
function getLdLb(){//����У��ֵ��ʼ��¥���б�
      var xqdm = ""
      var userName = "";
      var yqdm = "";
      if($("xqdm")){xqdm=$("xqdm").value;}
      if($("userName")){userName = $("userName").value;}
      if($("yqdm")){yqdm=$("yqdm").value;}
      gyglShareData.getSsldList(xqdm,yqdm,userName,function tjSeleInit(data){ 
          if (data != null && typeof data == 'object') {
				var objId = "lddm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");	
				}
		   }
      });
}

function getqshLb(){//����У��ֵ��ʼ��¥���б�
      var lddm = ""
      var userName = "";
      if($("lddm")){lddm=$("lddm").value;}
      if($("userName")){userName = $("userName").value};
      gyglShareData.GetQshList(lddm,userName,function tjSeleInit(data){ 
          if (data != null && typeof data == 'object') {
				var objId = "qsh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");	
				}
		   }
      });
}
function getSsbhLb(){//����У��ֵ��ʼ��¥�����ұ���б�
      var lddm = ""
      var userName = "";
      if($("lddm")){lddm=$("lddm").value;}
      if($("userName")){userName = $("userName").value};
      gyglShareData.GetSsbhList(lddm,function tjSeleInit(data){ 
          if (data != null && typeof data == 'object') {
				var objId = "ssbh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");	
				}
		   }
      });
}

function lcLbIinit(){//����¥������ֵ��ʼ��¥���б�
      var lddm = "";
      var userName = "";
      if($("lddm")){lddm=$("lddm").value;}
      if($("userName")){userName=$("userName").value;}
      gyglShareData.getSsHfLcList(lddm,userName,function tjSeleInit(data){ 
          if (data != null && typeof data == 'object') {
				var objId = "cs";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");					
					DWRUtil.removeAllOptions("oracleList");	//���δ�����б�			
				}
		   }
      });
}
function SsIinit(){//����¥������ֵ��ų�ʼ��δ���������б�
      var lddm = "";
      var cs   = "";
      var userName = "";
      if($("lddm")){lddm=$("lddm").value;}
      if($("cs")){cs=$("cs").value;}
      if($("userName")){userName=$("userName").value;}
      gyglShareData.getSsHfSsXxList(lddm,cs,userName,function tjSeleInit(data){ 
          if (data != null && typeof data == 'object') {
				var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
				}
		   }
      });
}

function YhfSsIinit(){//����¥������ֵ��ŵȳ�ʼ���ѻ�������б�
      var lddm = "";
      var cs   = "";
      var nj   = "";
      var xydm = "";
      var userName = "";
      if($("lddm")){lddm=$("lddm").value;}
      if($("cs")){cs=$("cs").value;}
      if($("nj")){nj=$("nj").value;}
      if($("xydm")){xydm=$("xydm").value;}
      if($("userName")){userName=$("userName").value;}
      gyglShareData.getSsYHfList(nj,xydm,lddm,cs,userName,function tjSeleInit(data){ 
          if (data != null && typeof data == 'object') {
				var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");					
					oldCondiSqlVConn();//�����ֵ��������	
					//document.write($("oldCondiSqlValue").value);		
				}
		   }
      });
}

function roomATCreat(obj){
  if($("xiaoqu").value==""){
      alert("У������Ϊ�գ�");
      $("xiaoqu").focus(); 
      return false;
  }
  if($("lddm").value==""){
      alert("¥������Ϊ�գ�");
       $("lddm").focus();  
      return false;
  }
  if($("cs").value==""){
      alert("¥�㲻��Ϊ�գ�"); 
       $("cs").focus(); 
      return false;
  }
  if($("cfjs").value==""){
      alert("����������Ϊ�գ�"); 
       $("cfjs").focus(); 
      return false;
  }
  if($("jcws").value==""){
      alert("��λ������Ϊ�գ�"); 
       $("jcws").focus(); 
      return false;
  }
  if($("sfbz").value==""){
      alert("��׼�շѲ���Ϊ�գ�"); 
       $("sfbz").focus(); 
      return false;
  }
  if($("fpbz").value==""){
      alert("��������ǲ���Ϊ�գ�"); 
      $("fpbz").focus(); 
      return false;
  }
  
  if(chkNumInput("cfjs")){
     alert("����������Ϊ���״̬�µ����֣�");
	 $("cfjs").focus();
	 return false;
  }
  if(chkNumInput("jcws")){
     alert("��λ������Ϊ���״̬�µ����֣�");
	 $("jcws").focus();
	 return false;
  }
  if(chkNumInput("sfbz")){
     alert("��׼�շѱ���Ϊ���״̬�µ����֣�");
	 $("sfbz").focus();
	 return false;
  }
  if($("qshbpgz").value==""){
     alert("���ɷ�Դ��ǰ�������Һű������ã�");
     return false;
  }
  var context= "������ "+$("qshbpgz").value+" ���Һű��Ź���\n";
  var xqmc = document.forms[0].xiaoqu.options[document.forms[0].xiaoqu.selectedIndex].text;
  var ldmc = document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
  var cs = document.forms[0].cs.options[document.forms[0].cs.selectedIndex].text;
  context +="Ϊ"; 
  context +=" "+xqmc;
  context +=" "+ldmc;
  context +=" "+cs;
  context += "���з����Զ����ɡ�\n\n";
  context +="ÿ�㷿������"; 
  context += $("cfjs").value;
  context +="\nÿ�����䴲λ��(��������)��"; 
  context += $("jcws").value;
  context +="\n�շѱ�׼��"; 
  context += $("sfbz").value;
   context +="\n������(��ס����)��"; 
  context += $("fpbz").value; 
  if(confirm(context+"\n\nȷ��Ҫ���з�Դ���Զ����ɣ�")){
      hiddenField();
      showTips();
      refreshForm('/xgxt/roomAutoCreate.do?autCT=DO');
      if(obj!=null){
		obj.disabled=true;
	  }
  }else{
     return false;  
  }
}

function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
}

function chkNumInput(obj){
    var inValue = $(obj).value;
    if(inValue!=""&&inValue.match(/^\d+\.{0,1}\d{0,3}$/)==null){			
	   return true;
	}else{
	   return false;
	}
}
function showGzSet(){
	var items = document.getElementById("items");
	items.style.left = (screen.availWidth)/6;
	items.style.top = ((screen.availHeight)/6);
	items.style.display = "block";
}
 function chec(){
             for(i=0;i<document.getElementsByName("pkV").length;i++){
      	         document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
             }
 }
 function batch(){
           var url = "/xgxt/zgdzdx_Gygl.do?method=roomBatchDel"; 
		   var RowsStr="!!";		  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;		   
		   if (RowsStr=="!!"){
			   alert("��ѡ��Ҫɾ���ļ�¼��\n(����ÿ����¼ǰ��ѡ��)");
			   return false;
		   }
		
		   if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
			  return false;
		   }   
	       refreshForm(url);          
 }
 function clearData(){//ˢ��ʱ���      
       $("cfjs").value="";//������     
       $("jcws").value=""//��λ��
       $("sfbz").value="";//��׼�շ�
       $("fpbz").value="";//��������� 
 }
 function modifyFYKNote(){
    var url = "/xgxt/zgdzdx_Gygl.do?method=roomInfoMod&pkV=";	
	if(chkRow()){
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url,600,480,1);
	}
}
/**���幫�÷���:�жϵ�ǰ�Ƿ�ѡ����*/
function chkRow(){        
	       if(curr_row == null){
		     alert("�˲�����Ҫ��ѡ�еĵ�ǰ�У��뵥��Ҫ�������У�");
		     return false;
	       }
	      return true;
}
function afj_addColum(){
    
    if(document.forms[0].xydm.value==null||document.forms[0].xydm.value==""){
        alert("����ѡ��Ժϵ!");
        return false;
    }
    if(document.forms[0].nj.value==null||document.forms[0].nj.value==""){
        alert("����ѡ���꼶!");
        return false;
    }       
    var oraObject =  document.getElementById("oracleList");
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
		alert('����δ����������ѡ��һ���������ᣡ');
		return false;
	}
    for(i=0;i<oraObject.options.length;i++){
       if(oraObject.options[i].selected){
        oraV=oraObject.options[i].value;
        oraT=oraObject.options[i].text;        
        document.forms[0].sql.options[n++] = new Option(nj+'/'+xyT+'/'+oraT,nj+'/'+xyV+'/'+oraV);
        document.forms[0].oracleList.options[i]=null;
        i--;  
        compartStatus = true;                    
       }
    }   
}
function afj_delColum(){    
    var sqlObject =  document.getElementById("sql");
    var n = document.forms[0].oracleList.options.length;
    var count = 0;
    for(var i=0;i<sqlObject.options.length;i++){
		if(sqlObject.options[i].selected){
			count++;
		}
	}
	if(count==0){
		alert('�����ѻ��������ѡ��һ���������ᣡ');
		return false;
	}
    for(i=0;i<sqlObject.options.length;i++){
         if(sqlObject.options[i].selected){
             sqlV = sqlObject.options[i].value;
             sqlT = sqlObject.options[i].text;
             //alert();
             strV = sqlV.substr(sqlV.indexOf("/")+1,sqlV.length);
             strT = sqlT.substr(sqlT.indexOf("/")+1,sqlT.length);
             document.forms[0].oracleList.options[n++] = new Option(strT.substr(strT.indexOf("/")+1,strT.length),strV.substr(strV.indexOf("/")+1,strV.length));
             document.forms[0].sql.options[i]=null;
             i--;
             compartStatus = true;
         }
    }    
}
//��������¥������
function acs_ald_addColum(value){
    
    var oraObject =  document.getElementById("oracleList");
	var sqlObject =  document.getElementById("sql");
	var userName =  document.getElementById("userName").value;
	var nj =  document.getElementById("nj");
	var xydm =  document.getElementById("xydm");
	if(xydm.value==null||xydm.value==""){
        alert("����ѡ��Ժϵ!");
        return false;
    }
   if(nj.value==null||nj.value==""){
        alert("����ѡ���꼶!");
        return false;
    }
	var count = 0;
	var oraV = ""; 
	for(var i=0;i<oraObject.options.length;i++){
		if(oraObject.options[i].selected){
			count++;
			oraV=oraObject.options[i].value; 
		}
	}
	var cs = oraV.substr(oraV.lastIndexOf('/')+1);
	if(count==0){
		alert('����δ����������ѡ��һ�����ᣡ');
		return false;
	}else if(count>1){
		alert('ֻ��ѡ��һ�����ᣡ');
		return false;
	}else{
	    var clinStr = "";
	    var mcstr = document.forms[0].oracleList.options[document.forms[0].oracleList.selectedIndex].text;
	    var xymcstr = document.forms[0].xydm.options[document.forms[0].xydm.selectedIndex].text;	
	    var njstr = document.forms[0].nj.options[document.forms[0].nj.selectedIndex].text;		     
	    var strTem = mcstr.split("/");
	    var ld = strTem[0];
	    var lc = strTem[1];       	    
	    if(value=="acs"){
	       clinStr = "�˲��������� "+ld+" "+lc+" ��Դ�������Ữ�֣�";
	       clinStr +="\n\n��������¥�����з��仮�ָ� "+njstr+"�� "+xymcstr+"��";
	       clinStr +="\n\n����ǰ��ȷ����¥�����з�Դδ�����ֻ�ռ�á�";
	       clinStr += "\n\nȷ��Ҫ���д˲�����";
	    }else{
	       clinStr = "�˲��������� "+ld+" ��Դ�������Ữ�֣�";
	       clinStr +="\n\n��������¥�����з��仮�ָ� "+njstr+"�� "+xymcstr+"��";
	       clinStr +="\n\n����ǰ��ȷ����¥�����з�Դδ�����ֻ�ռ�á�";
	       clinStr += "\n\nȷ��Ҫ���д˲�����";	      
	    }
	    if (confirm(clinStr)){		
		var ssbh = oraV.substr(0,oraV.indexOf('/'));
		gyglShareData.getAcs_AldList(ssbh,value,nj.value,xydm.value,userName,function tjSeleInit(data){
           	if (data != null && typeof data == 'object') {
				var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					var tem = "";
					if(value=='acs'){
						for(var j=0;j<sqlObject.options.length;j++){
							tem = sqlObject.options[j].value.substr(sqlObject.options[j].value.lastIndexOf('/')+1);
							if(tem==cs){
								sqlObject.options[j]=null;
								j--;
							}
						}
					}else{
						DWRUtil.removeAllOptions(objId);
					}		
					DWRUtil.addOptions(objId,data,"dm","mc");
					var num = oraObject.options.length;	
					if(value=='acs'){	
						for(var j=0;j<oraObject.options.length;j++){
							tem = oraObject.options[j].value.substr(oraObject.options[j].value.lastIndexOf('/')+1);
							if(tem==cs){
								oraObject.options[j]=null;
								j--;
							}
						}
					}else{
						DWRUtil.removeAllOptions('oracleList');
					}
				  
				  saveSqlValue();
				  hiddenField(); 
				  showTips();
				  refreshForm("/xgxt/zgdzdx_Gygl.do?method=roomCompartition&doType=save&xsbj=no");   						
				}
			}
  		});	
  		
       }else{
         return false;
      }		 
  		 		 
   }             
   
	
}
//��������¥���ͷ�
function acs_ald_delColum(value){
    var userName =  document.getElementById("userName").value;
	var oraObject =  document.getElementById("oracleList");
	var sqlObject =  document.getElementById("sql");
	var nj =  document.getElementById("nj");
	var xydm =  document.getElementById("xydm");
	var count = 0;
	var sqlV = ""; 
	for(var i=0;i<sqlObject.options.length;i++){
		if(sqlObject.options[i].selected){
			count++;
			sqlV=sqlObject.options[i].value; 
		}
	}
	var cs = sqlV.substr(sqlV.lastIndexOf('/')+1);
	if(count==0){
		alert('�����ѻ��������ѡ��һ�����ᣡ');
		return false;
	}else if(count>1){
		alert('ֻ��ѡ��һ�����ᣡ');
		return false;
	}else{
	    var clinStr = "";
	    var mcstr = document.forms[0].sql.options[document.forms[0].sql.selectedIndex].text;		     
	    var strTem = mcstr.split("/");
	    var njstr = strTem[0];
	    var xystr = strTem[1];
	    var ldstr = strTem[2];
	    var lcstr = strTem[3];   	    	    
	    if(value=="acs"){
	       clinStr = "�˲��������� "+njstr+"�� "+xystr+" "+ldstr+" "+lcstr+" ��Դ���������ͷţ�";
	       clinStr +="\n\n�������ͷŸ��꼶��Ժϵ��¥�������ѻ��ַ��䣬�Ա��ٻ����������꼶��Ժϵ��";
	       clinStr += "\n\nȷ��Ҫ���д˲�����";
	    }else{
	       clinStr = "�˲��������� "+njstr+"�� "+xystr+" "+ldstr+" ��Դ���������ͷţ�";
	       clinStr +="\n\n�������ͷŸ��꼶��Ժϵ��¥�������ѻ��ַ��䣬�Ա��ٻ����������꼶��Ժϵ��";
	       clinStr += "\n\nȷ��Ҫ���д˲�����";
	    }	
	    if (confirm(clinStr)){		
		var ssbh = sqlV.substr(sqlV.indexOf('/',sqlV.indexOf('/')+1)+1,sqlV.indexOf('/',sqlV.indexOf('/',sqlV.indexOf('/')+1)+1)-(sqlV.indexOf('/',sqlV.indexOf('/')+1)+1));
		var lddm = ssbh.substr(0,ssbh.indexOf('-'));
		gyglShareData.getAcs_Ald_delList(ssbh,value,nj.value,xydm.value,userName,function tjSeleInit(data){
           	if (data != null && typeof data == 'object') {
				var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					var tem = "";		
					DWRUtil.addOptions(objId,data,"dm","mc");
					var sqlV1 = "";
					var ssbh1 = "";
					if(value=='acs'){	
						for(var j=0;j<sqlObject.options.length;j++){
							sqlV1 = sqlObject.options[j].value;
							ssbh1 = sqlV1.substr(sqlV1.indexOf('/',sqlV1.indexOf('/')+1)+1,sqlV1.indexOf('/',sqlV1.indexOf('/',sqlV1.indexOf('/')+1)+1)-(sqlV1.indexOf('/',sqlV1.indexOf('/')+1)+1));
							tem = ssbh1.substr(0,ssbh.indexOf('-'));
							var cs1 = sqlV1.substr(sqlV1.lastIndexOf('/')+1);;
							if(tem==lddm && cs==cs1){
								sqlObject.options[j]=null;
								j--;
							}
						}
					}else{
						for(var j=0;j<sqlObject.options.length;j++){
							sqlV1 = sqlObject.options[j].value;
							ssbh1 = sqlV1.substr(sqlV1.indexOf('/',sqlV1.indexOf('/')+1)+1,sqlV1.indexOf('/',sqlV1.indexOf('/',sqlV1.indexOf('/')+1)+1)-(sqlV1.indexOf('/',sqlV1.indexOf('/')+1)+1));
							tem = ssbh1.substr(0,ssbh.indexOf('-'));
							if(tem==lddm){
								sqlObject.options[j]=null;
								j--;
							}
						}
					}
				 
				  saveSqlValue();
				  hiddenField();
				  showTips(); 
				  refreshForm("/xgxt/zgdzdx_Gygl.do?method=roomCompartition&doType=save&xsbj=no");    						
				}
			}
  		  });
  		}else{
         return false;
      }		
 	}
}
function oldCondiSqlVConn(){
	var sqlValue = new Array();
	document.forms[0].conditionSqlValue.value = "";
	for (i = 0; i < document.forms[0].sql.options.length; i++) {				
		sqlValue[sqlValue.length] = document.forms[0].sql.options[i].value;	
	}
	document.forms[0].oldCondiSqlValue.value = sqlValue;
	//alert(sqlValue)
}

function beforSubmit() {
   if (compartStatus) {
		if (confirm("��ǰ�꼶��Ժϵ��¥���ѻ�����������˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
	         SsHfDataSave();
	    } else {
			refreshForm("/xgxt/zgdzdx_Gygl.do?method=roomCompartition");
		}
	}
}
//ͳ��δ���ֺ��ѻ��ֵ�����
function getData(){
	var nj = document.getElementById('nj').value;
	var xydm = document.getElementById('xydm').value;
	var userName = document.getElementById('userName').value;
	gyglShareData.getWhfrs(nj,xydm,userName,function initArray1(data){
		$('allbody').innerText = data[0];
		$('allboy').innerText = data[1];
		$('allgirl').innerText = data[2];
	});
	gyglShareData.getYhfcws(nj,xydm,userName,function initArray2(data){
		$('totalBed').innerText = data[0];
		$('boyBed').innerText = data[1];
		$('girlBed').innerText = data[2];
		$('bgBed').innerText = data[3];
	});
}
function saveSqlValue(){//���Ữ�ֱ���ʱ��ȡ��ֵ	
	var sqlValue = new Array();
	document.forms[0].conditionSqlValue.value = "";
	for (i = 0; i < document.forms[0].sql.options.length; i++) {					
		sqlValue[sqlValue.length] = document.forms[0].sql.options[i].value;	
	}
	document.forms[0].conditionSqlValue.value = sqlValue;
}

function SsHfDataSave(){
      saveSqlValue();
      hiddenField();
      showTips();
      refreshForm("/xgxt/zgdzdx_Gygl.do?method=roomCompartition&doType=save");
}
function sendvalue(obj){
	var ssbh = obj;
	showTopWin("/xgxt/zgdzdx_Gygl.do?method=getOneSsInfo&ssbh="+ssbh,600,600);
}
function roomCodeSave(){
   var chsfbl = document.forms[0].chsfbl;
   var fjhws  = document.forms[0].fjhws;
   if(chsfbl.value==""){
      alert("��ѡ�����Ƿ��㣡");
      chsfbl.focus();
      return false;
   }
   if(fjhws.value==""){
      alert("��ѡ�񷿼�˳���λ����");
      fjhws.focus();
      return false;
   }
   if(confirm("ȷ��Ҫ�����������")){
         refreshForm("/xgxt/zgdzdx_Gygl.do?method=roomCodeSave");
         document.getElementById('items').style.display='none'
   }else{
      return false;
   }    
  
}


function SssBjLb(){//��λ������
	var xydm ="";
	var zydm ="";
	var nj ="";
	var userName = "";
	if($("userName")){userName = $("userName").value};
	if($("xydm")){xydm = $("xydm").value};
	if($("zydm")){zydm = $("zydm").value};
	if($("nj")){nj = $("nj").value};	
		gyglShareData.getSssBjList(nj,xydm,zydm,userName,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");												
				}
		   }
		});
}
function SssXiaoqLb(){//��λ������
	var xydm ="";
	var nj ="";
	var userName = "";
	if($("userName")){userName = $("userName").value};
	if($("xydm")){xydm = $("xydm").value};	
	if($("nj")){nj = $("nj").value};	
		gyglShareData.getSssXqList(nj,xydm,userName,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "xq";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					document.getElementById("xbxd").options[0].selected=true;
//					DWRUtil.removeAllOptions("xbxd");
					DWRUtil.removeAllOptions("ld");
					DWRUtil.removeAllOptions("cs");
					//document.forms[0].xbxd.options[0]=new Option('--��ѡ��--','');
					DWRUtil.removeAllOptions("oracleList");												
				}
		   }
		});
}
//��ȡ��λ����¥���б�
function SssLdList(){//��λ������
	var xqdm = "";
	var xydm = "";
	var xb = "";
	var nj  = "";	
	var userName = "";
	if($("userName")){userName = $("userName").value};
	if($("xq")){xqdm = $("xq").value};
	if($("xy")){xydm = $("xy").value};
	if($("xbxd")){xb = $("xbxd").value};
	if($("nj")){nj = $("nj").value};
		gyglShareData.getSssLdList(xqdm,xydm,xb,nj,userName,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "ld";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");												
				}
			}
		});
}
//��ȡ��λ����¥���б�
function SssLcList(){//��λ������
	var lddm = "";
	var xydm = "";	
	var nj  = "";	
	var userName = "";
	if($("userName")){userName = $("userName").value};
	if($("ld")){lddm = $("ld").value};
	if($("xy")){xydm = $("xy").value};
	if($("nj")){nj = $("nj").value};
		gyglShareData.getSssCsList(lddm,nj,xydm,userName,function initTjList(data){
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
function SssCwXxList(){//��λ������
	var xqdm = "";
	var xydm = "";
	var xb = "";
	var lddm = "";
	var cs = "";
	var nj  = "";
	var userName = "";
	if($("userName")){userName = $("userName").value};
	if($("xq")){xqdm = $("xq").value};
	if($("xy")){xydm = $("xy").value};
	if($("xbxd")){xb = $("xbxd").value};
	if($("ld")){lddm = $("ld").value};
	if($("cs")){cs = $("cs").value};
	if($("nj")){nj = $("nj").value};
		gyglShareData.getSssCwList(xqdm,xydm,lddm,xb,cs,nj,userName,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "oracleList";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
				}
			}
		});
}

//��ȡ��λ����ѧ���б�
function SssXsList(){//��λ������
	var xydm = "";
	var bjdm = "";	
	var nj = "";
	var xb = "";
	var userName = "";
	if($("userName")){userName = $("userName").value};
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
	if($("xbxd")){xb = $("xbxd").value};
		gyglShareData.getSssXsList(xydm,nj,bjdm,xb,userName,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "xh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					//$(objId).options[0].value = "";					
				}
			}
		});
}

//��ȡ��λ�����ѷ������ᴲλ��Ϣ�б�
function SssFpCwList(){
	var xydm = "";
	var bjdm = "";	
	var nj = "";
	var xb = "";
	var userName = "";
	if($("userName")){userName = $("userName").value};
    if($("xy")){xydm = $("xy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
	if($("xbxd")){xb = $("xbxd").value};
	gyglShareData.getSssFpCwList(xydm,nj,bjdm,xb,userName,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "sql";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");							
					oldCondiSqlVConn();					
				}
			}
		});
}


function sssaddDisBedList(){
	  var comp = "no";
//      if(document.forms[0].xbxd.value==null&&document.forms[0].xbxd.value==''){
//        alert("����ѡ���Ա�!");
//        return false;
//      } 
      if(document.forms[0].oracleList.selectedIndex<0){
        alert("����ѡ��λ!");
        return false;
      }
      var oracleListIndex = document.forms[0].oracleList.selectedIndex;
      var xhIndex = document.forms[0].xh.selectedIndex;
      var addOracleV = document.forms[0].oracleList.options[oracleListIndex].value;
	  var addOracleT = document.forms[0].oracleList.options[oracleListIndex].text;
	 
	  var xhV = document.forms[0].xh.options[xhIndex].value;
	  var xhT = document.forms[0].xh.options[xhIndex].text;	    
	    for (i = 0; i < document.forms[0].sql.options.length; i++) {
	    	  var sqlValue = document.forms[0].sql.options[i].value;	    	  	
		      if(addOracleV == sqlValue.substr(sqlValue.indexOf("/")+1,sqlValue.length)){
			     comp = "yes";
		      }	
	      }	
	    if(comp=="yes"){
	    	alert("�ѷ��������Ѵ��ڸô�λ��");
	    	return false;
	    }else{ 
	    compartStatus = true;	  
        document.forms[0].sql.options[document.forms[0].sql.options.length] = new Option(xhT + "/" + addOracleT,xhV + "/" + addOracleV);
//        totalBed.innerText=parseInt(totalBed.innerText)+1;
//        
//      	if(document.forms[0].xb.value=='��'){
//        	boyBed.innerText=parseInt(boyBed.innerText)+1;
//      	}else if(document.forms[0].xb.value=='Ů'){
//        	girlBed.innerText=parseInt(girlBed.innerText)+1;
//      	}
//      	totalBoyGirl(0);
      	document.forms[0].oracleList.options[oracleListIndex]=null;
      	document.forms[0].xh.options[xhIndex]=null;
	    }
    }
    
function sssdelDisBedList(){
       var sqlIndex = document.forms[0].sql.selectedIndex;
       if(sqlIndex==-1){
          return false;
       }
       var sqlV = document.forms[0].sql.options[sqlIndex].value;
	   var sqlT = document.forms[0].sql.options[sqlIndex].text;
	   var sqlTstr = sqlT.substring(sqlT.indexOf("/")+1,sqlT.length);
	   var sqlTemXm = sqlTstr.substring(0,sqlTstr.indexOf("/"));//����
	   var sqlTtemLdmcCwsCwh = sqlTstr.substring(sqlTstr.indexOf("/")+1,sqlTstr.length);//¥�����ƴ�λ����λ��    
	  // return false;
       document.forms[0].oracleList.options[document.forms[0].oracleList.options.length] = 
       new Option(sqlTtemLdmcCwsCwh,sqlV.substring(sqlV.indexOf("/")+1,sqlV.length));
       document.forms[0].xh.options[document.forms[0].xh.options.length] = 
       new Option(sqlT.substring(0,sqlT.indexOf("/"))+"/"+sqlTemXm, sqlV.substring(0,sqlV.indexOf("/")));
      	
//       if(parseInt(totalBed.innerText)!=0){
//       		 totalBed.innerText=parseInt(totalBed.innerText)-1;
//       	}
//       	if(document.forms[0].xb.value=='��'){
//          	  if(parseInt(boyBed.innerText)!=0){
//          		   boyBed.innerText=parseInt(boyBed.innerText)-1;
//          		}
//       		}else{
//       		   if(parseInt(girlBed.innerText)!=0){
//          		   girlBed.innerText=parseInt(girlBed.innerText)-1;
//       		    }
//       		}    
//       	totalBoyGirl(0);
       	document.forms[0].sql.options[sqlIndex]=null;
       	compartStatus = true;
    }

function SsscwfpDataSave(){    
      saveSqlValue();
      hiddenField();
      showTips();
      refreshForm("/xgxt/zgdzdx_Gygl.do?method=bedCompartition&doType=save");
}

function bedbeforSubmit() {
   if (compartStatus) {
		if (confirm("��ǰ�ѷ�����������˱仯��Ҫ������\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")) {
	        SsscwfpDataSave();
	    } else {
			refreshForm("/xgxt/zgdzdx_Gygl.do?method=bedCompartition");
		}
	}
}


function dataCL(){
      $("xbxd").value=""; //�����Ա�ֵΪ��	
     var ld = document.forms[0].ld;
     var cs = document.forms[0].cs;
   	 var oracleList = document.forms[0].oracleItem;
     for (i=1;i<ld.length;i++) {	//���¥�����б�  
        ld.options[i]=null;
     }
     for (i=1;i<cs.length;i++) {	//��ղ���б�  
        cs.options[i]=null;
     }
     //for (i=1;i<oracleList.length;i++){//��շ�Դ�б�  
        oracleList=null;
     //}
            
}

//��ȡ��λ�ѷ���������
function SssCwYfpZsData(){
	var xydm = "";
	var bjdm = "";	
	var nj = "";
	var userName = "";
	if($("userName")){userName = $("userName").value};
    if($("xy")){xydm = $("xy").value};
    //if($("zy")){zydm = $("zy").value};
	if($("bj")){bjdm = $("bj").value};
	if($("nj")){nj = $("nj").value};
    gyglShareData.getSssCwYfpZsData(xydm,nj,bjdm,userName,function(data){
       if(data!=null){
        totalBed.innerText=data[0];
       	boyBed.innerText=data[1];
       	girlBed.innerText=data[2];
       }
    });	
    gyglShareData.getSssCwWfpZsData(xydm,nj,bjdm,userName,function(data){
       if(data!=null){
        allbody.innerText=data[0];
       	allboy.innerText=data[1];
       	allgirl.innerText=data[2];
       }
    });	
    
}


//��ȡ��λ�ѷ���������
function shareLdList(){
	var xqdm = "";	
	var userName = "";
	if($("userName")){userName = $("userName").value};
    if($("xqdm")){xqdm = $("xqdm").value};alert(xqdm);
    gyglShareData.getSsHfLdList(xqdm,userName,function(data){
       if (data != null && typeof data == 'object') {
				var objId = "lddm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");							
									
				}
			}
    });	        
}

function getLcList2(){
	var lddm ="";
	if($("lddm")){lddm = $("lddm").value};
		gyglShareData.getLcList2(lddm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "cs";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");												
				}
		   }
		});
}


/**����������Ƿ��Ѿ�����*/
function isSsbhExists(){
	var mustFill = "ssbh-lddm-qsh-cws-fpbj-cs";
	if(!mustFillFun(mustFill)){
		return;
	}
	if($("bz").value.length>150){
	   alert("��ע�������ܴ���150�֣�");
	   return false;
	}
	var ssbh = document.getElementById("ssbh").value;	
	gyglShareData.isSsbhExists(ssbh,function(data){
		if(data){	
			if(confirm("���������Ѿ����ڣ��Ƿ�Ҫ�����޸ģ�\n���\"ȷ��\"���������ݣ����\"ȡ��\"�����������ģ�")){
			   dataDoSave(mustFill);
			}else{
			   return false;
			}
		}else{
			 dataDoSave(mustFill);
		}
	});
}

function mustFillFun(mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		}
	}
	return true;
}

function getYqList(){
    var xqdm = document.getElementById("xqdm").value;
    gyglShareData.getYqList(xqdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "yqdm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");												
				}
		   }
		});
}
function getYqLdList(){
    var yqdm = document.getElementById("yqdm").value;
    gyglShareData.getYqLdList(yqdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "lddm";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");												
				}
		   }
	});

}
function zgdz_addCwBatchColum(){//��λ����          
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
function zgdz_addCwColum(){
        var rzrq = "";
        if($("rzrq"))rzrq=$("rzrq").value;
        if(rzrq==""){
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
	    var countXh = 0;
	    var n = document.forms[0].sql.options.length;
	    var oraObject =  document.getElementById("oracleList");
	    var xh= document.getElementById("xh"); 
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
             document.forms[0].sql.options[n++] = new Option(xhTarr[i]+'/'+cwTarr[i]+'/'+rzrq,xhVarr[i]+'/'+cwVarr[i]+'/'+rzrq);                                                       
        }
        compartStatus = true; 
}
function zgdz_delCwBatchColum(){    
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
              sqlV = sqlObject.options[i].value;
              sqlV = sqlV.substring(0,sqlV.lastIndexOf('/')); 
              sqlT = sqlObject.options[i].text;
              sqlTArr = sqlT.split('/');    
              document.forms[0].xh.options[m++] = new Option(sqlTArr[0]+'/'+sqlTArr[1]+'/'+sqlTArr[2],sqlV.substr(0,sqlV.indexOf('/')));
              document.forms[0].oracleList.options[n++] = new Option(sqlTArr[3]+'/'+sqlTArr[4]+'/'+sqlTArr[5],sqlV.substr(sqlV.indexOf("/")+1,sqlV.length));    
              document.forms[0].sql.options[i]=null;
              i--;
         }
    }   
    compartStatus = true; 
}
function chckXh(){
	var xh = document.forms[0].cz.value;	
	if(xh!=""){
		getStuDetails.dctStuXh(xh,function(data){
		         if(!data){
		            alert("�������ѧ�Ų����ڣ����ʵ��������ȷ��ѧ��!");
		            //document.forms[0].cz.value="";
		            document.forms[0].cz.focus();	
		         }	           
		});
	}	
}

function selectPoint(obj,oneObj,towObj,thObj){
	if(obj.value == "" || obj.value == null){
		document.getElementById(oneObj).value = "";
		document.getElementById(towObj).value = "";
		return false;
	}
	
	var oneValue = document.getElementById(oneObj).value;
	if(oneValue.search(obj.value) != -1){
		return false;
	}else{
		oneValue += obj.value;
		oneValue += ",";
	}
	document.getElementById(oneObj).value = oneValue;
	var strText =document.getElementById(thObj).options[document.getElementById(thObj).selectedIndex].text;
	var objText = obj.options[obj.selectedIndex].text;
	var ptionText  = strText+objText;
	var towValue = document.getElementById(towObj).value;
	if(towValue.search(ptionText) != -1){
		return false;
	}else{
		towValue += ptionText;
		towValue += ",";
	}
	document.getElementById(towObj).value = towValue;	
}

function SjParaChk(sqB, sqE){
	var a1 = document.all(sqB + "1").value;
	var a2 = (document.all(sqB + "2").value != "" && document.all(sqB + "2").value.length != 2) ? ("0" + document.all(sqB + "2").value) : document.all(sqB + "2").value;
	var a3 = (document.all(sqB + "3").value != "" && document.all(sqB + "3").value.length != 2) ? ("0" + document.all(sqB + "3").value) : document.all(sqB + "3").value;
	var a4 = (document.all(sqB + "4").value != "" && document.all(sqB + "4").value.length != 2) ? ("0" + document.all(sqB + "4").value) : document.all(sqB + "4").value;
	var b1 = document.all(sqE + "1").value;
	var b2 = (document.all(sqE + "2").value != "" && document.all(sqE + "2").value.length != 2) ? ("0" + document.all(sqE + "2").value) : document.all(sqE + "2").value;
	var b3 = (document.all(sqE + "3").value != "" && document.all(sqE + "3").value.length != 2) ? ("0" + document.all(sqE + "3").value) : document.all(sqE + "3").value;
	var b4 = (document.all(sqE + "4").value != "" && document.all(sqE + "4").value.length != 2) ? ("0" + document.all(sqE + "4").value) : document.all(sqE + "4").value;
	a1 = (a1 == "") ? "1900-01-01" : a1;
	a2 = (a2 == "" || isNaN(parseInt(a2)) || parseInt(a2) > 24 || parseInt(a2) < 0) ? "00" : a2;
	a3 = (a3 == "" || isNaN(parseInt(a3)) || parseInt(a3) > 60 || parseInt(a3) < 0) ? "00" : a3;
	a4 = (a4 == "" || isNaN(parseInt(a4)) || parseInt(a4) > 60 || parseInt(a4) < 0) ? "00" : a4;
	b1 = (b1 == "") ? "1900-01-01" : b1;
	b2 = (b2 == "" || isNaN(parseInt(b2)) || parseInt(b2) > 24 || parseInt(b2) < 0) ? "00" : b2;
	b3 = (b3 == "" || isNaN(parseInt(b3)) || parseInt(b3) > 60 || parseInt(b3) < 0) ? "00" : b3;
	b4 = (b4 == "" || isNaN(parseInt(b4)) || parseInt(b4) > 60 || parseInt(b4) < 0) ? "00" : b4;
	var a = a1 + a2 + a3 + a4;
	var b = b1 + b2 + b3 + b4;
	if (a > b) {
		alert("��ʼʱ�����ڽ�ֹʱ�䣬�޷����棡");
		return false;
	}
	document.getElementById(sqB).value = replaceChar(a, "-", "");
	document.getElementById(sqE).value = replaceChar(b, "-", "");
	return true;	
}
function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
}

function viewXsCxTj(){
   var cxtj = $("xsCxTj");
   if($("tbview").checked){
      cxtj.style.display='';
     // $("search_go").style.height=50;
   }else{
      cxtj.style.display='none';
     // $("search_go").style.height=25;
   }
}

function getZs(obj){
	GetListData.getZs(obj.value,function zs(data){ 
         document.getElementById('zs').value = '��'+data+'��';            
     }) 
}
