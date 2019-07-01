var count = 1;
 var max = 0;
 function trAdd(the_tab,type,numAdd,lb){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(numAdd).value;
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
    	alert('您没有选定删除行，请选择要删除的数据行！');
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
	    	var htmltext = "<input type='text' style='width:80%' maxlength='100' name='pxmc' id='pxmc" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' name='pxdd' maxlength='50' id='pxdd" + max + "'/>";
	    
	    	return htmltext;
	    },
		function(data){	    
			var htmltext = "<input type='text' name='pxsj' style='width:80%' id='pxsj" + max + "'";
	    		htmltext+="onblur='dateFormatChg(this)'  readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' name='pxnr' maxlength='100' id='pxnr" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<textarea rows='3' cols='3' style='word-break:break-all;width:100%' onblur='checkLen(this,200)' name='bz' id='bz" + max + "'/>";
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

	var zgh = $('zgh').value;
	var tabLen = document.getElementById('flag1').rows.length;
	
	
	if(zgh == ""){
		alert("请选择相关辅导员！");
		return false;
	}
	
	if(tabLen == null || tabLen == 0){
		alert("请填写相关辅导员培训信息！");
		return false;
	}	
	
	for(var i=1;i<=rowLen;i++){
		if($("pxsj"+i)){
			if($("pxsj"+i).value == ""){
				alert("培训时间不能为空，请确定");
				return false;
			}
		}
		if($("pxdd"+i)){
			if($("pxdd"+i).value == ""){
				alert("培训地点不能为空，请确定");
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
	var zgh = $("zgh").value;
		if(zgh != ""){
			dwr.engine.setAsync(false);
			var tableName1="szdw_xysf_fdypxb";
			var colList1 =["pxsj","pxdd","pxmc","pxnr","bz"];
			var pk = "zgh";
			var pkValue = zgh;
			var query =" ";
			getOtherData.getTableListInfo(tableName1, colList1,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					
					trAdd('flag1','madd','numAdd1','rzqk');			
				
					for(var i=1;i<=data.length;i++){
						if($("pxsj"+i)){
							var pxsj = data[i-1].pxsj;
							if(pxsj == null){
								pxsj = "";
							}
							$("pxsj"+i).value = pxsj;
						}
						if($("pxdd"+i)){
							var pxdd = data[i-1].pxdd;
							if(pxdd == null){
								pxdd = "";
							}
							$("pxdd"+i).value = pxdd;
						}
						if($("pxmc"+i)){
							var pxmc = data[i-1].pxmc;
							if(pxmc == null){
								pxmc = "";
							}
							$("pxmc"+i).value = pxmc;
						}
						if($("pxnr"+i)){
							var pxnr = data[i-1].pxnr;
							if(pxnr == null){
								pxnr = "";
							}
							$("pxnr"+i).value = pxnr;
						}
						if($("bz"+i)){
							var bz = data[i-1].bz;
							if(bz == null){
							bz = "";
							}
							$("bz"+i).value = bz;
						}					
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}
