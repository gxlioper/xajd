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
			var htmltext = "<input type='text' style='width:100%'  name='cdmc' id='cdmc" + max + "'";
	    		htmltext+=" style='cursor:hand;'";
				htmltext+="/>";
	 
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
			var htmltext = "<input type='text' style='width:100%'  name='wzmc' id='wzmc" + max + "'";
	    		htmltext+=" style='cursor:hand;' ";
				htmltext+="/>";
	 
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

	tab1Len = document.getElementById('flag1').rows.length;
	tab2Len = document.getElementById('flag2').rows.length;
	
	if(tab1Len == 0){
		alert("请添加场地");
		return false;
	}
	
	if(tab2Len == 0){
		alert("请添加物资");
		return false;
	}
	
	for(var i=1;i<=rowLen;i++){
		if($("cdmc"+i)){
			if($("cdmc"+i).value == ""){
				alert("您的活动场地有信息为空，请确定");
				return false;
			}
		}
		if($("wzmc"+i)){
			if($("wzmc"+i).value == ""){
				alert("您的申请物资有信息为空，请确定");
				return false;
			}
		}
	}
	showTips('处理数据中，请等待......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function onShow(){
	
	var hddm = $("hddm").value;
		if(hddm != ""){
			dwr.engine.setAsync(false);
			var tableName1="gdby_xywhcdb";
			var colList1 =["cdmc"];
			var tableName2="gdby_xywhwzb";
			var colList2 = ["wzmc"];
			var pk = "hddm";
			var pkValue = hddm;
			var query =" ";
			getOtherData.getTableListInfo(tableName1, colList1,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					trAdd('flag1','madd','numAdd1','rzqk');			
					for(var i=1;i<=data.length;i++){
						if($("cdmc"+i)){
							var cdmc = data[i-1].cdmc;
							if(cdmc == null){
								cdmc = "";
							}
							$("cdmc"+i).value = cdmc;
						}					
					}
				}		
			});
			
			getOtherData.getTableListInfo(tableName2, colList2,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd2").value=data.length;
					trAdd('flag2','madd','numAdd2','hjqk');
					rows = $('flag1').rows.length;			
					for(var i=1;i<=data.length;i++){
						row = rows+i;
						if($("wzmc"+row)){
							var wzmc = data[i-1].wzmc;
							if(wzmc == null){
								wzmc = "";
							}
							$("wzmc"+row).value = wzmc;
						}				
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}
