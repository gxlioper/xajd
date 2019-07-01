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
			var htmltext = "<input type='text'   name='sbdm' id='sbdm" + max + "'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
	    		var kssj = "'rzkssj"+max+"'";
				var htmltext = "<input type='text'   name='sbdw' id='sbdw" + max + "'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='sbsl' id='sbsl" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='ghqk' id='ghqk" + max + "'/>";
	    
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
			var htmltext = "<input type='text'  readonly  name='sbdm' id='sbdm" + max + "'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
	    		var kssj = "'rzkssj"+max+"'";
				var htmltext = "<input type='text'  readonly  name='sbdw' id='sbdw" + max + "'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   readonly  name='sbsl' id='sbsl" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' readonly  name='ghqk' id='ghqk" + max + "'/>";
	    
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
	var lens=0;
	var tabLen = document.getElementById('flag1').rows.length;
	
	for(var i=1;i<=rowLen;i++){
		if($("sbdm"+i)){
			lens++;
			if($("sbdm"+i).value == ""){
				alert("借用物品情况有信息为空，请确定");
				return false;
			}
		}
		if($("sbdw"+i)){
			if($("sbdw"+i).value == ""){
				alert("借用物品情况有信息为空，请确定");
				return false;
			}
		}
		if($("sbsl"+i)){
			if($("sbsl"+i).value == ""){
				alert("借用物品情况有信息为空，请确定");
				return false;
			}
		}
		if($("ghqk"+i)){
			if($("ghqk"+i).value == ""){
				alert("借用物品情况有信息为空，请确定");
				return false;
			}
		}
	}
	if(lens<1){
		alert("请填写物品借用情况！");
		return false;
	}
	showTips('处理数据中，请等待......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function onShow(){
	var option = $('option').value;
	var pkValue = $("pkValue").value;
		if(pkValue != ""){
			dwr.engine.setAsync(false);
			var tableName="ntzy_wpb";
			var colList =["sbdm","sbdw","sbsl","ghbz"];
			var pk = "sqr || sqsj || jyrq";
			var query =" ";
			getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					if('shone' == option){
						trAdd('flag1','madd','numAdd1','rzqk_sh');
					}else{
						trAdd('flag1','madd','numAdd1','rzqk');			
					}
					for(var i=1;i<=data.length;i++){
						if($("sbdm"+i)){
							var sbdm = data[i-1].sbdm;
							if(sbdm == null){
								sbdm = "";
							}
							$("sbdm"+i).value = sbdm;
						}
						if($("sbdw"+i)){
							var sbdw = data[i-1].sbdw;
							if(sbdw == null){
								sbdw = "";
							}
							$("sbdw"+i).value = sbdw;
						}
						if($("sbsl"+i)){
							var sbsl = data[i-1].sbsl;
							if(sbsl == null){
								sbsl = "";
							}
							$("sbsl"+i).value = sbsl;
						}
						
						if($("ghqk"+i)){
							var ghbz = data[i-1].ghbz;
							if(ghbz== null){
								ghbz = "";
							}
							$("ghqk"+i).value = ghbz;
						}
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}
