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
			var htmltext = "<input type='text' style='width:100%'  name='hjsj' id='hjsj" + max + "'";
	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='hjmc' id='hjmc" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:100%'  name='hjsy' id='hjsy" + max + "'/>";
	    
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' onblur='checkInputData(this);' style='width:100%'  name='grhjf' id='grhjf" + max + "'/>";
	    
	    	return htmltext;
	    }
		];
	}else{
		cellfu =[
		 		function(data){
		 			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
		 			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"'/>"
		 			return htmlText;
		 		},
		 		function(data){	    
		 			var htmltext = "<input type='text' style='width:100%'  name='hjsj' id='hjsj" + max + "'";
		 	    		htmltext+="onblur='dateFormatChg(this)' style='cursor:hand;' readonly='true'";
		 				htmltext+="onclick='time(this.id)'/>";
		 	 
		 	    	return htmltext;
		 	    },
		 	    function(data){
		 	    	var htmltext = "<input type='text' style='width:100%' readonly='true' name='hjmc' id='hjmc" + max + "'/>";
		 	
		 	    	return htmltext;
		 	    },
		 	    function(data){
		 	    	var htmltext = "<input type='text' style='width:100%' readonly='true' name='hjsy' id='hjsy" + max + "'/>";
		 	    
		 	    	return htmltext;
		 	    },
		 	    function(data){
		 	    	var htmltext = "<input type='text' style='width:100%'  name='grhjf' id='grhjf" + max + "'/>";
		 	    
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
		alert("请填写获奖信息");
		return false;
	}
	
	for(var i=1;i<=rowLen;i++){
		if($("hjsj"+i)){
			if($("hjsj"+i).value == ""){
				alert("获奖时间不能为空，请确定");
				return false;
			}
		}
		if($("hjmc"+i)){
			if($("hjmc"+i).value == ""){
				alert("获奖名称不能为空，请确定");
				return false;
			}
		}
		if($("hjsy"+i)){
			if($("hjsy"+i).value == ""){
				alert("获奖事由不能为空，请确定");
				return false;
			}
		}
		if($("grhjf"+i)){
			if($("grhjf"+i).value == ""){
				alert("申请荣誉加分不能为空，请确定");
				return false;
			}
		}
	}
	showTips('处理数据中，请等待......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function onShow(){
//	var option = $('option').value;
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
		if(xh != "" && xn != "" && xq != ""){
			dwr.engine.setAsync(false);
			var tableName1="ghxy_grjfsqb";
			var colList1 =["hjsj","hjmc","hjsy","grhjf"];
			var pk = "xh||xn||xq";
			var pkValue = xh+xn+xq;
			var query =" ";
			getOtherData.getTableListInfo(tableName1, colList1,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					if($("operation")){	
						trAdd('flag1','madd','numAdd1','rzqk_sh');
					}else{
						trAdd('flag1','madd','numAdd1','rzqk');			
					}
					for(var i=1;i<=data.length;i++){
						if($("hjsj"+i)){
							var hjsj = data[i-1].hjsj;
							if(hjsj == null){
								hjsj = "数据库无信息";
							}
							$("hjsj"+i).value = hjsj;
						}
						if($("hjmc"+i)){
							var hjmc = data[i-1].hjmc;
							if(hjmc == null){
								hjmc = "数据库无信息";
							}
							$("hjmc"+i).value = hjmc;
						}
						if($("hjsy"+i)){
							var hjsy = data[i-1].hjsy;
							if(hjsy == null){
								hjsy = "数据库无信息";
							}
							$("hjsy"+i).value = hjsy;
						}
						if($("grhjf"+i)){
							var grhjf = data[i-1].grhjf;
							if(grhjf == null){
								grhjf = "数据库无信息";
							}
							$("grhjf"+i).value = grhjf;
						}				
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}
