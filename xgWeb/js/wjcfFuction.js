////////////////////////sq 判断当前行是否选中////////////////
function chkRow(){
	if(curr_row == null){
		alert("请选择要操作的行！");
		return false;
	}
	return true;

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
	var ydrs = parseInt(document.getElementById("ydrs").value);
	var sdrs = parseInt(document.getElementById("sdrs").value);
	var qjrs = parseInt(document.getElementById("qjrs").value);
	if(sdrs+qjrs > ydrs){
		alert("实到人数与请假人数不符合!");
		return false;
	}
	var kssj=document.getElementById("kssj").value;
	var jssj=document.getElementById("jssj").value;
	var arry1=kssj.split(":");
	var arry2=jssj.split(":");
	
	for(i=0;i<2;i++)
	{
		var temp1=parseInt(arry1[i]);
		var temp2=parseInt(arry2[i]);
		if(temp1>temp2)
		{
			alert("开始时间不能大于结束时间！");
			return false;
		}
	}	
	document.forms[0].action = url;
	document.forms[0].submit();
}
//判断数据正确性
function CheckData(obj){
	var thisdata = obj.value;
	if(thisdata.match(/^\d+\.{0,1}\d{0,3}$/) == null){
		alert("数据格式不正确！");
		obj.value = "0";
		obj.focus();
		return false;
	}
	if($("ydrs")){
		rs = document.getElementById("ydrs").value;
		if(parseInt(thisdata) > parseInt(rs)){
			alert("考勤人数不得大于应到人数!");
			obj.value = "0";
			obj.focus();
			return false;
		}
	}
	return true;
}

function check_time(id)
{
	var time=document.getElementById(id).value;
	var timeMatch=/([0-1][0-9]|2[0-3]):([0-5][0-9])/g;
	var timeMatch1=/([0-1]?[0-9]|2[0-3]):([0-5][0-9])/g;
	var result=timeMatch.test(time);
	if(true==result)
	{
		return true;
	}
	else if(false==result)
	{
		alert("输入时间格式有误！正确格式如：08:59");
		document.getElementById(id).value="";
	}
}
//个人违纪处分信息显示
function sendWjcfInfo() {
	var iscsmz = document.getElementById('isCSMZ').value;
	var isJGSDX = document.getElementById('isJGSDX').value;	
	var xxdm = document.getElementById('xxdm').value;
	var tabType = document.getElementById('tabType').value;
	if(confirm("确定选择该条记录吗?")){
		var cfid = replaceChar(curr_row.getElementsByTagName('input')[0].value," ","");		
   	 	getWjcfInfo.getInfoEx("wjcf_xsssb","xh||cfwh||cfsj",cfid," 1=1 ",function(data){
		         if(data && tabType=='wjcf_xsssb'){
		            alert("该处分申诉已申请,不能再操作！");
			        return false;
		         }else{
		         	var vel = window.dialogArguments.document.forms[0].cfwh;
					vel.focus();
					vel.value = replaceChar(curr_row.cells[0].innerText," ","");	
					var xh = window.dialogArguments.document.forms[0].xh.value;
					if (isJGSDX=='lxck' || tabType=='wjcf_cxcfb') {
					
					var str = "/xgxt/cxcfinfo.do?from=" + document.forms[0].url.value +"&xh="+xh+"&cfid="+cfid;
					}else{
    				var str = "/xgxt/wjcf_xsshssq.do?from=" + document.forms[0].url.value +"&xh="+xh+"&cfid="+cfid;
		            }
		            window.dialogArguments.document.forms[0].action = str;
	                window.dialogArguments.document.forms[0].submit(); 	
	                window.close();	        		            	
		         }		        
		         }); 		
	}else{
		return false;
	}
}
//显示详细信息
function viewMoreinfo(doType,w,h){
var sty = "toolbar=no,location=no,directories=no,status=yes";
				sty += ",menubar=no,scrollbars=yes,resizable=yes,width=800,height=700,top=100";
				sty += ",left=200";
	    if(chkRow()){
			var url ="/xgxt/wjcf_viewmoreinfoanddel.do?doType=";
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			var realtab = document.getElementById("realTable").value;
			var xh = curr_row.cells[0].innerText.replace(" ","");
			var act = document.getElementById("act").value;
			var w = "0";
			var h = "0";
			if (doType == "view"){		    
		    	url += doType;
		    	url += "&pkValue="
		    	url += pkValue;
		    	url += "&realTable=";
		    	url += realtab;
		    	url += "&xh=";
		    	url += xh;
		    	url += "&act=";
		    	url += act;
		   	 	w = "700";
		    	h = "600";
		    	if(act == "discuss"||act == "decide"){
		    		url = url.replace("wjcf_viewmoreinfoanddel.do","wjcf_shscheck.do");		    		
		    		w = "800";
		    		h = "650";
		    	}
		    			    	
		    	//window.open(url,'', sty);
		    	showTopWin(url,w,h);
	       }
	    	if(doType == "modi"){
	    		   	  	           
        	     	getWjcfInfo.getInfoEx("wjcf_xsssb","xh||cfwh||cfsj",pkValue," sh='成立' ",function(data){
		         		if(data){
		            		alert("该处分申诉已申请,并经相关部门讨论已成立，不能再操作！");
			        		return false;
		         		}else{
		           			url = url.replace("wjcf_viewmoreinfoanddel.do","wjcf_xsshssq.do");
	    	       			url += doType;
	    	       			url += "&pkValue="
		           			url += pkValue;
		           			url += "&xh=";
		           			url += xh; 
	    	       			w = "880";
	    	       			h = "680";	    	       
		           			window.open(url,'',sty);		        		            	
		         		}		        
		         	}); 	    	       	    	       	           
            	
	    	}	
	  	} 	         
}
//提交申诉申请
function addShsApply(mustFill,url){
        var eles = mustFill.split("-");
	    for (i = 0; i < eles.length; i++) {
		    if($(eles[i])){
			   if (document.getElementById(eles[i]).value == "") {
				  alert("请将带\"*\"号的项目输入完整！");
				  return false;
			   }
		    }
	    }	   	    	    	    
	     if(document.getElementById("yq").value.length >400){
	        alert("改变处分要求内容字数("+ document.getElementById("yq").value.length +")大于四百字！");
	        return false;
	    }
	    
	    var cfwj = document.getElementById('cfwj').value;
	   	if (cfwj != null && cfwj != '') {
	   		if (cfwj.length <= 4) {
	   			alert("上传文件路径不正确,请重新选择!");
	   			return false;
	   		}
	   		var kzm = cfwj.substr(cfwj.length-3,3);
	   		if (kzm == 'txt' || kzm == 'xls' ||kzm == 'doc' ||kzm == 'pdf' ||kzm == 'chm' || kzm=='rar') {
	   			
	   		} else{
	   			alert("上传文件格式不正确,只能上传txt,doc,xls,pdf,chm,rar类型的文件!");
	   			return false;
	   		}
	   	}
	    
	    var dat = false ;
	    var doType = document.getElementById("doType").value;
	    var pkValue = "nullValue"+document.getElementById("pkValue").value;
	    var xh = document.getElementById("xh").value;
	    var cfwh = document.getElementById("cfwh").value;
	    var cfsj = document.getElementById('cfsj').value;
		   if(pkValue=="nullValue"){
		   	    getWjcfInfo.getInfoEx("wjcf_xsssb","xh||cfwh||cfsj",xh+cfwh+cfsj," 1=1",function(data){
		         if(data){
		            alert("该处分申诉已申请,不能再提交！");
			        return false;
		         }else{
		            document.forms[0].action = "/xgxt/wjcf_xsshssq.do?doType=save";
		            document.forms[0].submit();
		            showTips('数据保存中，请稍候...');			        		            	
		         }		        
		         }); 		   	   	
		   }else{
		   	    pkValue = pkValue.replace("nullValue","");
		   	    getWjcfInfo.getInfoEx("wjcf_xsssb","xh||cfwh||cfsj",pkValue,"sh='成立'",function(data){
		         if(data){
		            alert("该处分申诉已申请,并经相关部门讨论已成立，不能再操作！");
			        return false;
		         }else{
		            document.forms[0].action = "/xgxt/wjcf_xsshssq.do?doType=save&pkValue="+pkValue;
		            document.forms[0].submit();	
		            showTips('数据保存中，请稍候...');		        		            	
		         }		        
		         }); 	        
		   }
		       
 		
     }
function wjcfInfoTo(lx){
        var xh = document.getElementById("xh").value;
        var xm = document.getElementById("xm").value;
        var tabType = document.getElementById('tabType').value;
        var pkValue = document.getElementById("pkValue").value;
        var url = "/xgxt/wjcf_info.do?xh=";
            url += xh;
            url += "&xm=";
            url += xm;
            url += "&pkValue=";
            url += pkValue;
            url += "&tabType=";
            url += tabType;
            url += "&lx="+lx;
        if(xh == ""){
            alert("学号为空,请先输入学号！");
            return false;
        }else{      
            showTopWin(url,750,550);
            return true;
      }
}
function dataDel(url){
	if(chkRow()){
	if(confirm("确定要删除该条记录？")){
		 var pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += "&pkValue=";
		 url += pkValue;
		  getWjcfInfo.getInfoEx("wjcf_xsssb","xh||cfwh||cfsj",pkValue,"jd!='未决定'",function(data){
		         if(data){
		            alert("该处分申诉已审核，不能再操作！");
			        return false;
		         }else{
		            document.forms[0].action = url;
		 			document.forms[0].submit();
		 			document.getElementById("search_go").click();			        		            	
		         }		        
		         }); 			
	}else{
		 return false;
	}
	}
}
function wjcfChkSave(url){          
			var pkValue = document.getElementById("pkValue").value;
			var act = document.getElementById("act").value;	
			var realtab = document.getElementById("realTable").value;
			var xh = document.getElementById("xh").value;
			var act = document.getElementById("act").value;    
		    url += "?doType=save";
            url += "&pkValue=";
		   	url += pkValue;
		    url += "&act=";
		    url += act;
		    url += "&realTable=";
		    url += realtab;
		    url += "&xh=";
		    url += xh	
		   showTopWin(url, "800","600");  	     
}
function toStuViewMore(url){
	      var xh = document.getElementById("xh").value;
	      var cfwh = curr_row.cells[0].innerText.replace(" ","").replace(" ","");
	      var cfsj = curr_row.cells[3].innerText.replace(" ","");
	      var pkValue = xh+cfwh+cfsj;	     
	      url += pkValue;
	      showTopWin(url, 750,600); 
}
	
function sendWjcfInfo1(){
	var iscsmz = document.getElementById('isCSMZ').value;	
	if(confirm("确定选择该条记录吗?")){
		var cfid = replaceChar(curr_row.getElementsByTagName('input')[0].value," ","");		
		         	var vel = window.dialogArguments.document.forms[0].cfwh;
					vel.focus();
					vel.value = replaceChar(curr_row.cells[0].innerText," ","");	
					var xh = window.dialogArguments.document.forms[0].xh.value;
    				var str = "/xgxt/csmzcxcfsqinfo.do?from=" + document.forms[0].url.value +"&xh="+xh+"&cfid="+cfid;
		            window.dialogArguments.document.forms[0].action = str;
	                window.dialogArguments.document.forms[0].submit(); 	
	                window.close();	        		            	
		         		        		
	}else{
		return false;
	}
}	
	
function wjcfInfoBylxck(param1){
	var xh = document.getElementById("xh").value;
        var xm = document.getElementById("xm").value;
        var tabType = document.getElementById('tabType').value;
        var pkValue = document.getElementById("pkValue").value;
        var url = "/xgxt/wjcf_info_lxck.do?xh=";
            url += xh;
            url += "&xm=";
            url += xm;
            url += "&pkValue=";
            url += pkValue;
            url += "&tabType=";
            url += tabType;
            url += '&param=';
            url += param1;
        if(xh == ""){
            alert("学号为空！");
            return false;
        }else{      
            showTopWin(url,750,550);
            return true;
      }
}


function print() {
				var xxdm = document.getElementById('xxdm').value;
				var url = 'wjcf_hhgxy_ssprint.do?pkValue=';
				if (xxdm=='11641') {
					if (curr_row==null||curr_row=='') {
						if (confirm('没有选择任何数据,单击一行即可,要继续打印吗?')) {
							window.open(url);
						} 
						return;
					} else {
						var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
						url += pk;
						window.open(url);
						return;
					}
				} else {
					expTab('rsTable','学生违纪处分申诉申请结果','')
				}
			}
			function jgprint(){
				var xxdm = document.getElementById('xxdm').value;
				var url = 'wjcf_hhgxy_ssjdprint.do?pkValue=';
				if (xxdm=='11641') {
					if (curr_row==null||curr_row=='') {
						if (confirm('没有选择任何数据,单击一行即可,要继续打印吗?')) {
							window.open(url);
						} 
						return;
					} else {
						var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
						url += pk;
						window.open(url);
						return;
					}
				} else {
					expTab('rsTable','学生违纪处分申诉申请结果','')
				}
			}
	