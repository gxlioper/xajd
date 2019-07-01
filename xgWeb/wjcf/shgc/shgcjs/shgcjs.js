function dataSave_shgc(url)
{
	var xh = document.getElementById('xh').value;
	if (xh=='' || document.getElementById('cfyy').selectedIndex==0 || 
	document.getElementById('cflb').selectedIndex==0 || document.getElementById('wjjtsy').value==''
	|| document.getElementById('zacfqk').value=='' || document.getElementById('qtcfqk').value==''
	|| document.getElementById('bz').value=='' || document.getElementById('xq').value==''){
		alert('请将带*号的信息填写完整！');
	}
	else{
		document.forms[0].action = url;
		document.forms[0].submit();
	}//end if
}

function chkPriseOne_shgc(url,w,h){
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("请选择要操作的行！");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
	}
		showTopWin(url, w, h);
}

function savexxsh_shgc(url){	
	var val = document.getElementById("pkVal").value;
	url += val;
	refreshForm(url);
}

function chkPriseOne6(url){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要删除所选择的数据吗？')==true){
			document.forms[0].action = url;
		document.forms[0].submit();
		}
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}

function viewMoreinfoshgc(doType,w,h)
{
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
		   	 	w = "600";
		    	h = "400";
		    	if(act == "discuss"||act == "decide"){
		    		url = url.replace("wjcf_viewmoreinfoanddel.do","wjcf_shscheck.do");		    		
		    		w = "800";
		    		h = "630";
		    	}		    	
		    	showTopWin(url, w, h);
	       }
	    	if(doType == "modi"){
	    		if(confirm("确定要修改该条记录?")){	    	  	           
        	     	getWjcfInfo.getInfoEx("wjcf_xsssb","xh||cfwh||cfsj",pkValue," slqk='已受理' ",function(data){
		         		if(data){
		            		alert("该处分申诉已申请,并经"+jQuery("#xbmc").val()+"讨论已受理，不能再操作！");
			        		return false;
		         		}else{
		           			url = url.replace("wjcf_viewmoreinfoanddel.do","wjcf_xsshssq.do");
	    	       			url += doType;
	    	       			url += "&pkValue="
		           			url += pkValue;
		           			url += "&xh=";
		           			url += xh; 
	    	       			w = "800";
	    	       			h = "630";	    	       
		           			showTopWin(url, w, h);		        		            	
		         		}		        
		         	}); 	    	       	    	       	           
            	}else{
            		return false;
           		}	  
	    	}	
	  	} 	   
}
