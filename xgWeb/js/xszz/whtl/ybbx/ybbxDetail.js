 var count = 1;
 var max = 0;
 function trAdd(the_tab,type,numAdd,lb){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(numAdd).value;
    var doType = $("doType").value;
    var cellfu =null;
    if($("doType") && $("doType").value=="sh"){
		cellfu=getCellfuBySh(lb);
	}else{
	  cellfu=getCellfu(lb);
	}
   
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
			var htmltext = "<input type='text'   name='mzyy' id='mzyy" + max + "' style='width:100'  maxlength='30' />";
	 
	    	return htmltext;
	    },
	    function(data){	    
			var htmltext = "<input type='text'   name='bxje' id='bxje" + max + "'style='width:100' maxlength='10' onkeyup='checkInputNum(this)' onblur='checkInputNum(this)'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='wzsj' id='wzsj" + max + "' style='width:120'  onclick=\"return showCalendar('wzsj" + max + "','y-mm-dd');\"  onblur=\"dateFormatChg(this);\" readonly=\"true\"/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<textarea   name='ylyt' id='ylyt" + max + "'  style='word-break:break-all;' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\" />";
	    
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
			var htmltext = "<input type='text'   name='mzyy' id='mzyy" + max + "' style='width:100'  maxlength='30'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
			var htmltext = "<input type='text'   name='bxje' id='bxje" + max + "'style='width:100'  maxlength='10' onkeyup='checkInputNum(this)' onblur='checkInputNum(this)'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='wzsj' id='wzsj" + max + "' style='width:120'  onclick=\"return showCalendar('wzsj" + max + "','y-mm-dd');\"  onblur=\"dateFormatChg(this)\" readonly=\"true\"/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<textarea   name='ylyt' id='ylyt" + max + "'  style='word-break:break-all;' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\"  />";
	    
	    	return htmltext;
	    }
		];
	}else if(lb == 'view'){
		cellfu =[
		function(data){	    
			var htmltext = "<input type='text'   name='mzyy' id='mzyy" + max + "' style='width:100'  maxlength='30' readonly=\"true\"/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
			var htmltext = "<input type='text'   name='bxje' id='bxje" + max + "'style='width:100'  maxlength='10' onkeyup='checkInputNum(this)' onblur='checkInputNum(this)' readonly=\"true\"/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='wzsj' id='wzsj" + max + "' style='width:120'   readonly=\"true\"/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<textarea   name='ylyt' id='ylyt" + max + "'  style='word-break:break-all;' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\" readonly=\"true\" />";
	    
	    	return htmltext;
	    }
		];
	}
	return cellfu;
}

function getCellfuBySh(lb){
	var cellfu = new Array();
	if(lb == 'add'){
			cellfu =[
		function(data){
			
			var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
			var htmltext = "<input type='text'   name='mzyy' id='mzyy" + max + "' style='width:100'  />";
	    	return htmltext;
	    },
	    function(data){	    
			var htmltext = "<input type='text'   name='bxje' id='bxje" + max + "'style='width:100' />";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='wzsj' id='wzsj" + max + "' style='width:120'  readonly=\"true\"/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<textarea   name='ylyt' id='ylyt" + max + "'  readonly=\"true\"  style='word-break:break-all;' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\" />";
	    
	    	return htmltext;
	    },
	     function(data){
	    	var htmltext = "<input type='text'  name='shje' id='shje" + max + "' style='width:100' maxlength='10' onkeyup='checkInputNum(this)'/>";
	    
	    	return htmltext;
	    }
		];
	}else if(lb == 'sh'){
		cellfu =[
		function(data){	    
			
			var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
			var htmltext = "<input type='text'   name='mzyy' id='mzyy" + max + "' style='width:100'  readonly=\"true\"/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
			var htmltext = "<input type='text'   name='bxje' id='bxje" + max + "'style='width:100'  readonly=\"true\"/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='wzsj' id='wzsj" + max + "' readonly=\"true\" />";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<textarea   name='ylyt' id='ylyt" + max + "' readonly=\"true\"  style='word-break:break-all;' rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,250);\" />";
	    
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'  name='shje' id='shje" + max + "' style='width:100' maxlength='10' onkeyup='checkInputNum(this)' onblur='checkInputNum(this)'/>";
	    
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
		if($("mzyy"+i)){
			lens++;
			if($("mzyy"+i).value == ""){
				alertInfo("门诊医院信息不能为空，请确定！",function(){return false;});
				return false;
			}
		}
		if($("bxje"+i)){
			if($("bxje"+i).value == ""){
				alertInfo("报销金额信息不能为空，请确定！",function(){return false;});
				return false;
			}
		}
		if($("wzsj"+i)){
			if($("wzsj"+i).value == ""){
				alertInfo("问诊时间信息不能为空，请确定！",function(){return false;});
				return false;
			}
		}
		if($("ylyt"+i)){
			if($("ylyt"+i).value == ""){
				alertInfo("医疗用途信息不能为空，请确定！",function(){return false;});
				return false;
			}
		}
	}
	if(lens<1){
		alertInfo("请填写医保报销信息！",function(){return false;});
		return false;
	}
	
	return true;
}

function onShow(){
	var pkValue = $("pkValue").value;
		if(pkValue != ""){
			dwr.engine.setAsync(false);
			var tableName="xg_xszz_ybbxxxb";
			var colList =["mzyy","bxje","wzsj","ylyt"];
			
			if($("doType") && $("doType").value=="sh"){
				colList =["mzyy","bxje","wzsj","ylyt","shje"];
			}
			
			var pk = "xn||'!!@@!!'||xh";
			var query =" ";
			getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					trAdd('flag1','madd','numAdd1',$("doType").value);			
					for(var i=1;i<=data.length;i++){
						if($("mzyy"+i)){
							var mzyy = data[i-1].mzyy;
							if(mzyy == null){
								mzyy = "";
							}
							$("mzyy"+i).value = mzyy;
							
						}
						if($("bxje"+i)){
							var bxje = data[i-1].bxje;
							if(bxje == null){
								bxje = "";
							}
							$("bxje"+i).value = bxje;
							
						}
						if($("wzsj"+i)){
							var wzsj = data[i-1].wzsj;
							if(wzsj == null){
								wzsj = "";
							}
							$("wzsj"+i).value = wzsj;
						}
						
						if($("ylyt"+i)){
							var ylyt = data[i-1].ylyt;
							if(ylyt== null){
								ylyt = "";
							}
							$("ylyt"+i).value = ylyt;
						}
						
						if($("shje"+i)){
							var shje = data[i-1].shje;
							if(shje== null){
								shje = "";
							}
							$("shje"+i).value = shje;
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
