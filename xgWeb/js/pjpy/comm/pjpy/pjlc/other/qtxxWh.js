 var count = 1;
 var max = 0;
 function trAdd(the_tab,type,numAdd,lb){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(numAdd).value;
    var doType = $("doType").value;
    var cellfu =null;
    
	cellfu=getCellfu(lb);

    count=len;  
	if(type=='add'){
		max++;
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          max++;
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $(numAdd).value = "";
    }    
}
function time(id){
	return showCalendar(id,'y-mm-dd');
}

function scollChange(obj){
	obj.style.posHeight=obj.scrollHeight;
}

function trDel(the_tab,delRow){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    var checkbox = document.getElementsByName(delRow);
    var checkArray = new Array();
    var size = 0
    for(i=0;i<checkbox.length;i++){
    	if(checkbox[i].checked == true){
    		checkArray[size++] = eval(checkbox[i].value);
    	}	
    }
    
    if(checkArray.length == 0){
    	alert('您没有选中要操作的记录，请选择！');
    	return false;
    }
    	
    if(confirm('确定要删除选中的记录？')){
    	for(i=0;i<checkArray.length;i++){
    		tabobj.deleteRow(checkArray[i]-1);
    		for(j=0;j<checkArray.length;j++){
    			checkArray[j] += -1;
    		}
   	 }
    
   	 for(i=0;i<checkbox.length;i++){
    		checkbox[i].value=i+1;
  	  }
    }
}

function checktime(kssj,jssj){
//	var kssj = "rzkssj"+jssj.replace("rzjssj");
//	alert("kssj:"+kssj);
//	alert("jssj:"+kssj);
	var begin = document.getElementById(kssj).value;
	var end = document.getElementById(jssj).value;
	
	
	if(begin == "" || end == ""){
		document.getElementById(kssj).value="";
		document.getElementById(jssj).value="";
	}else if(eval(begin) > eval(end)){
			document.getElementById(kssj).value="";
			document.getElementById(jssj).value="";
			alert('时间先后顺序不正确');
	}
}

function getCellfu(lb){
	var cellfu = new Array();
	
	if(lb == 'add' || lb=="save"){
		cellfu =[
		function(data){
		var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
		htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
		return htmlText;
		},
	    function(data){
	    	var htmltext = "<textarea name='jlnr' id='jlnr" + max + "'  style='word-break:break-all;width:95%' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\" />";
	    
	    	return htmltext;
	    }
		];
	}else if(lb == 'modi'){
		cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
	    function(data){
	    	var htmltext = "<textarea   name='jlnr' id='jlnr" + max + "'  style='word-break:break-all;width:95%' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\"  />";
	    
	    	return htmltext;
	    }
		];
	}else if(lb == 'view'){
		cellfu =[
	    function(data){
	    	var htmltext = "<textarea   name='jlnr' id='jlnr" + max + "'  style='word-break:break-all;width:95%' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\" readonly=\"true\" />";
	    
	    	return htmltext;
	    }
		];
	}
	return cellfu;
}

function trDelAll(the_tab,numDel){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $(numDel).value; 
    if(length==0){
       $(numDel).value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("确定要删除最后"+num+"行？")){ 
         for(i=1;i<=num;i++){           
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $(numDel).value = "";
}	

function checkSaveInfo(){
	var rowLen = max;
	var nullCout = 0;
	var lens=0;
	var tabLen = document.getElementById('flag1').rows.length;
	
	for(var i=1;i<=rowLen;i++){
		if($("jlnr"+i)){
			lens++;
			if($("jlnr"+i).value == ""){
				alertInfo("其他奖励信息内容不能为空，请确定！",function(){return false;});
				return false;
			}
		}
	}
	if(lens<1){
		alertInfo("请填写其他奖励信息！",function(){return false;});
		return false;
	}

	return true;
}

function onShow(){
	var pkValue = $("pkValue").value;
		if(pkValue != ""){
			dwr.engine.setAsync(false);
			var tableName="xg_pjpy_qtjlb";
			
			var colList =["jlnr"];
			
			var pk = "xn||'!!@@!!'||xh";
			var query =" ";
			getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					trAdd('flag1','madd','numAdd1',$("doType").value);			
					for(var i=1;i<=data.length;i++){
						if($("jlnr"+i)){
							var jlnr = data[i-1].jlnr;
							if(jlnr == null){
								jlnr = "";
							}
							$("jlnr"+i).value = jlnr;
							
						}
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
		
		if($("userType").value=="xx" || $("userType").value=="admin"){

			jQuery("#xyshyj").attr("readOnly",true);
		}
}

function changeTextArea(id,lx){
	if($(id)){
		if(lx == "focus"){
			$(id).rows = "5";
		}else{
			$(id).rows = "1";
		}
	}
}
