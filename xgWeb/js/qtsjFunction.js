function getInsureInfoExist(){
	var insureInfo=null;	
	var xh=document.getElementById("xh").value;
	var nd=document.getElementById("nd").value;
	var Msg="";
	getInsureInfo.InsureInfoisExist(xh,nd,function(data){
		if(!data==""){
			alert(data);
		}
	});
}


function CheckSameOfInsureInfo(colum){		
	var len=0;	
	var xh=document.getElementById("xh").value;
	var nd=document.getElementById("nd").value;
	var bxgsdm=document.getElementById("bxgsdm").value;	
	var val = "";
	if(colum=="bxnx"){
		val = document.getElementById("bxqx").value;
	}
	else{
		val = document.getElementById(colum).value;	
	}
	getInsureInfo.CheckExist(colum,val,xh,nd,function(data){	
		if(data!=null && data!=""){
			alert(data);
		}
	});
	
}

function checkTime(){
	var zykssj = document.getElementById("zykssj").value;
	var zyjzsj = document.getElementById("zyjzsj").value;
	if(zykssj > zyjzsj){
		alert("住院开始时间晚于住院结束时间，请重新填写！！");
		return false;
	} else
	return true;	
}

function CheckOrtherExist(tabName1,tabName2,str,tabValue1,tabValue2,flag1,flag2,val){
 var xh=document.getElementById("xh").value;
 var nd=document.getElementById("nd").value;
 var bxgsdm=document.getElementById("bxgsdm").value;
 var value="";
 if(str=='bxnx')
 	value=document.getElementById("bxqx").value;
 else
  	value=document.getElementById(str).value;
 var strs=null;
 var msg="";
 getInsureInfo.CheckOrtherExist(tabName1,tabName2,str,value,xh,nd,bxgsdm,flag1,flag2,function(data){
 	strs=data.split(",");
 	if(strs[0]!=""){
 		msg+=val+"与"+tabValue1+"中的不一致!"+"\n";
 	}
 	if(strs[1]!=""){
 		msg+=val+"与"+tabValue2+"中的不一致!"+"\n";
 	}
 	if(strs!=null || strs!=""){
 		alert(msg);
 	}
 });
}

/*//数据批量操作
  function dataBatch(url){
  	var RowsStr="!!";
  	var mes = "确定要操作所选记录？";
		for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	if(document.getElementsByName("pkV")[i].checked){
	    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	}
		}
				
		if (RowsStr=="!!"){
			alertInfo("请选择要操作的记录！");
			return false;
		}
				
	
		
		confirmInfo(mes,function(t){
			if(t!="ok"){
				return false;
			}else{

				url += "&pkValue=";
				url += RowsStr;
				refreshForm(url);
			}
		});
		
		
  }*/
  
  //数据保存
  function saveData(url,mustFill){
	  var eles = mustFill.split("-");
		for (i = 0; i < eles.length; i++) {
		    if($(eles[i])!=null||$(eles[i])){
			    if ($(eles[i]).value == "") {
			    	alertInfo("请将带\"<font color='red'>*</font>\"号的项目填写完整！");
			    	return false;
			   }
			}
		} 
		document.forms[0].action = url;
		document.forms[0].submit();
  }
  
  //数据保存
  function saveDataShowTips(url,mustFill,msg){
	  var eles = mustFill.split("-");
		for (var i = 0; i < eles.length; i++) {
		    if($(eles[i])!=null||$(eles[i])){
			    if ($(eles[i]).value == "") {
				  alert("请将带\"<font color='red'>*</font>\"号的项目填写完整！");
				  return false;
			   }
			}
		} 
		document.forms[0].action = url;
		document.forms[0].submit();
		BatAlert.showTips(msg);
  }
  
  //根据保险公司加载保险险种
  function loadBxxzList(){
	  var bxgsdm = document.getElementById("tbgsdm").value;
	  getInsureInfo.getBxxzByBxgs(bxgsdm,function(data){
		  DWRUtil.removeAllOptions("tbxzdm");		
		  DWRUtil.addOptions("tbxzdm",[{bxxzdm:'',bxxzmc:''}],"bxxzdm","bxxzmc");
		  DWRUtil.addOptions("tbxzdm",data,"bxxzdm","bxxzmc");	
	  });	  
  }
  
  //根据保险险种加载保险年限和保费
  function loadBxnxAndBf(){
	  var bxxzdm = document.getElementById("tbxzdm").value;
	  var bxnx = document.getElementById("bxnx").value;
	  var xh = document.getElementById("xh").value;
	  getInsureInfo.getBf(bxxzdm, bxnx, xh, function(data){
		  var bf = data.split("!!")[0];
		  var bxnx = data.split("!!")[1];
		  document.getElementById("bf").value = bf;
		  document.getElementById("bxnx").value = bxnx;
	  });
  }
  
/*  //数据批量操作
  function dataBatch(url){
	    var RowsStr="!!";	
	    var mes = "确定要操作所选记录？";
		for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	if(document.getElementsByName("pkV")[i].checked){
	    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	}
		}		
		if (RowsStr=="!!"){
			alertInfo("请选择要操作的记录！");
			return false;
		}
		
		confirmInfo(mes,function(t){
			if(t!="ok"){
				return false;
			}else{
				refreshForm(url);
			}
		});
		
		
  }
*/