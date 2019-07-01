var count = 1;
 var max = 0;
 function trAdd(the_tab,type,numAdd,lb){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(numAdd).value;
    var doType = $("doType").value;
    var cellfu = getCellfu(lb);
	
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
	if(lb == 'rzqk'){
			cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text' style='width:100%'  name='rzkssj' id='rzkssj" + max + "'";
	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
	    		var kssj = "'rzkssj"+max+"'";
				var htmltext = "<input type='text' style='width:100%'  name='rzjssj' id='rzjssj" + max + "'";
	    		//htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onblur=\"dateFormatChg(this);checktime("+kssj+",this.id);\" style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='rzbm' id='rzbm" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='zw' id='zw" + max + "'/>";
	    
	    	return htmltext;
	    },
	    function(data){  
		var htmltext = "<input type='text' style='width:100%'  name='jdbm' id='jdbm" + max + "'/>";
	    	
	    	return htmltext;
	    }
		];
	}else if(lb == 'rzqk_sh'){
		cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text' style='width:100%'  name='rzkssj' id='rzkssj" + max + "'";
	    		htmltext+=" style='cursor:hand;' readonly='true'";
				htmltext+="/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
				var htmltext = "<input type='text' style='width:100%'  name='rzjssj' id='rzjssj" + max + "'";
	    		htmltext+=" style='cursor:hand;' readonly='true'";
				htmltext+="/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='rzbm' readonly='true' id='rzbm" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='zw' readonly='true' id='zw" + max + "'/>";
	    
	    	return htmltext;
	    },
	    function(data){  
		var htmltext = "<input type='text' style='width:100%'  name='jdbm' readonly='true' id='jdbm" + max + "'/>";
	    	
	    	return htmltext;
	    }
		];
	}else if(lb == 'hjqk_sh'){
		cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow2' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text' style='width:100%'  name='hjkssj' id='hjkssj" + max + "'";
	    		htmltext+=" style='cursor:hand;' readonly='true'";
				htmltext+=" />";
	 
	    	return htmltext;
	    },
	    function(data){	    
				var htmltext = "<input type='text' style='width:100%'  name='hjjssj' id='hjjssj" + max + "'";
	    		htmltext+=" style='cursor:hand;' readonly='true'";
				htmltext+="/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='jllb' readonly='true' id='jllb" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='sjbm' readonly='true' id='sjbm" + max + "'/>";
	    
	    	return htmltext;
	    }
		];
	}else{
		cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow2' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text' style='width:100%'  name='hjkssj' id='hjkssj" + max + "'";
	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
	    		var hjkssj = "'hjkssj"+max+"'";
				var htmltext = "<input type='text' style='width:100%'  name='hjjssj' id='hjjssj" + max + "'";
	    		htmltext+="onblur=\"dateFormatChg(this);checktime("+hjkssj+",this.id);\" style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='jllb' id='jllb" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='sjbm' id='sjbm" + max + "'/>";
	    
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

function save(url){
	var rowLen = max;
	var nullCout = 0;
	var xh = $("xh").value;
	
	var tabLen = document.getElementById('flag1').rows.length;
	
	if(xh == null || xh == ""){
		alert("学号不能为空");
		return false;
	}
	
	if(tabLen == null || tabLen == 0){
		alert("请填写社会履历");
		return false;
	}
	
	for(var i=1;i<=rowLen;i++){
		if($("rzkssj"+i)){
			if($("rzkssj"+i).value == ""){
				alert("您任职情况有信息为空，请确定");
				return false;
			}
		}
		if($("rzjssj"+i)){
			if($("rzjssj"+i).value == ""){
				alert("您任职情况有信息为空，请确定");
				return false;
			}
		}
		if($("rzbm"+i)){
			if($("rzbm"+i).value == ""){
				alert("您任职情况有信息为空，请确定");
				return false;
			}
		}
		if($("zw"+i)){
			if($("zw"+i).value == ""){
				alert("您任职情况有信息为空，请确定");
				return false;
			}
		}
		if($("jdbm"+i)){
			if($("jdbm"+i).value == ""){
				alert("您任职情况有信息为空，请确定");
				return false;
			}
		}
		if($("hjkssj"+i)){
			if($("hjkssj"+i).value == ""){
				alert("您授奖情况有信息为空，请确定");
				return false;
			}
		}
		if($("hjjssj"+i)){
			if($("hjjssj"+i).value == ""){
				alert("您授奖情况有信息为空，请确定");
				return false;
			}
		}
		if($("jllb"+i)){
			if($("jllb"+i).value == ""){
				alert("您授奖情况有信息为空，请确定");
				return false;
			}
		}
		if($("sjbm"+i)){
			if($("sjbm"+i).value == ""){
				alert("您授奖情况有信息为空，请确定");
				return false;
			}
		}
	}
	showTips('处理数据中，请等待......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function onShow(){
	var option = $('option').value;
	var xh = $("xh").value;
		if(xh != ""){
			dwr.engine.setAsync(false);
			var tableName1="sjxy_rzqkb";
			var colList1 =["rzkssj","rzjssj","rzbm","zw","jdbm"];
			var tableName2="sjxy_hjqkb";
			var colList2 = ["hjkssj","hjjssj","jllb","sjbm"];
			var pk = "xh";
			var pkValue = xh;
			var query =" ";
			getOtherData.getTableListInfo(tableName1, colList1,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					if('shone' == option){
						trAdd('flag1','madd','numAdd1','rzqk_sh');
					}else{
						trAdd('flag1','madd','numAdd1','rzqk');			
					}
					for(var i=1;i<=data.length;i++){
						if($("rzkssj"+i)){
							var rzkssj = data[i-1].rzkssj;
							if(rzkssj == null){
								rzkssj = "数据库无信息";
							}
							$("rzkssj"+i).value = rzkssj;
						}
						if($("rzjssj"+i)){
							var rzjssj = data[i-1].rzjssj;
							if(rzjssj == null){
								rzjssj = "数据库无信息";
							}
							$("rzjssj"+i).value = rzjssj;
						}
						if($("rzbm"+i)){
							var rzbm = data[i-1].rzbm;
							if(rzbm == null){
								rzbm = "数据库无信息";
							}
							$("rzbm"+i).value = rzbm;
						}
						if($("zw"+i)){
							var zw = data[i-1].zw;
							if(zw == null){
								zw = "数据库无信息";
							}
							$("zw"+i).value = zw;
						}
						
						if($("jdbm"+i)){
							var jdbm = data[i-1].jdbm;
							if(jdbm == null){
								jdbm = "数据库无信息";
							}
							$("jdbm"+i).value = jdbm;		
						}					
					}
				}		
			});
			
			getOtherData.getTableListInfo(tableName2, colList2,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd2").value=data.length;
					if('shone' == option){
						trAdd('flag2','madd','numAdd2','hjqk_sh');
					}else{
						trAdd('flag2','madd','numAdd2','hjqk');
					}
					rows = $('flag1').rows.length;			
					for(var i=1;i<=data.length;i++){
						row = rows+i;
						if($("hjkssj"+row)){
							var hjkssj = data[i-1].hjkssj;
							if(hjkssj == null){
								hjkssj = "数据库无信息";
							}
							$("hjkssj"+row).value = hjkssj;
						}
						if($("hjjssj"+row)){
							var hjjssj = data[i-1].hjjssj;
							if(hjjssj == null){
								hjjssj = "数据库无信息";
							}
							$("hjjssj"+row).value = hjjssj;
						}
						if($("jllb"+row)){
							var jllb = data[i-1].jllb;
							if(jllb == null){
								jllb = "数据库无信息";
							}
							$("jllb"+row).value = jllb;
						}
						if($("sjbm"+row)){
							var sjbm = data[i-1].sjbm;
							if(sjbm == null){
								sjbm = "数据库无信息";
							}
							$("sjbm"+row).value = sjbm;
						}					
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}
