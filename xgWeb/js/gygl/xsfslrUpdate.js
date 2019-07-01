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
			var htmltext = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmltext += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmltext;
		},
	    function(data){
	    	var htmltext = "<select id='xmxz" + max + "' onchange='getLrfInfo(" + max + ");'>";
	    	htmltext += "<option value='减分'>减分</option>";
	    	htmltext += "<option value='加分'>加分</option>";
	    	htmltext += "</select>"
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<select id='xmdm" + max + "' name='xmdm' style='width: 50%' onmousemove=''>";
	    	htmltext += $("reduce_select").innerHTML;
	    	htmltext += "</select>"
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' name='fs' style='width: 50px' onkeyup='checkInputNum(this);' maxlength='5' id='fs" + max + "'/>";
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
	
	var tabLen = document.getElementById('flag1').rows.length;
	
	for(var i=1;i<=rowLen;i++){
		if($("xmmc"+i)){
			if($("xmmc"+i).value == ""){
				alert("项目不能为空，请确定");
				return false;
			}
		}
		if($("fs"+i)){
			if($("fs"+i).value == ""){
				alert("分数不能为空，请确定");
				return false;
			}
		}
	}
	showTips('处理数据中，请等待......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function getLrfInfo(max){
	var xmxz = $('xmxz'+max).value;
	var tableName = "XG_GYGL_WSJCDMWHB";
	
	dwr.engine.setAsync(false);
	getGyglWsjcDAO.getWsjcInfo(xmxz,function(data){
		DWRUtil.removeAllOptions('xmdm'+max);			
		DWRUtil.addOptions('xmdm'+max,data,"xmdm","xmmc");
	});
	dwr.engine.setAsync(true);
}

function onShow(){
//	var option = $('option').value;
	var xh = $("xh").value;
	var jczq = $("jczq").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	var nd = $("nd").value;
	
	if(xh != ""){
		dwr.engine.setAsync(false);
		var tableName1="xg_view_gygl_xsfslr";
		var colList1 =["xmxz","xmdm","fs"];
		var pk = "";
		var pkValue = "";
		
		if("周" == jczq){
			var jczc = $("jczc").value;
			
			pk = "xh||xn||xq||nd||jczc"
			pkValue = xh + xn + xq + nd + jczc;
		} else{
			var jcsj = $("jcsj").value;
			
			pk = "xh||xn||xq||nd||jczc||jcsj";
			pkValue = xh + xn + xq + nd + "无" + jcsj;
		}
		
		var query ="";
		getOtherData.getTableListInfo(tableName1, colList1,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){	
				$("numAdd1").value=data.length;
				
				trAdd('flag1','madd','numAdd1','rzqk');			
			
				for(var i=1;i<=data.length;i++){
					if($("xmxz"+i)){
						var xmxz = data[i-1].xmxz;
						if(xmxz == null){
							xmxz = "";
						}
						$("xmxz"+i).value = xmxz;
						
					
						if("加分" == xmxz){
							
							if($("xmdm"+i)){
								for(var j = $("xmdm"+i).length-1;j>=0;j--){
									$("xmdm"+i).options[j] = null;
								}
								
								for(var j = 0;j<$("add_select").length;j++){
									$("xmdm"+i).options[j] = new Option($("add_select").options[j].text,$("add_select").options[j].value);
								}
							//$("xmdm"+i).outerHTML = $("reduce_select").outerHTML;
							}
						} 
					}
					if($("xmdm"+i)){
						var xmdm = data[i-1].xmdm;
						if(xmdm == null){
							xmdm = "";
						}
						$("xmdm"+i).value = xmdm;
					}
					if($("fs"+i)){
						var fs = data[i-1].fs;
						if(fs == null){
							fs = "";
						}
						$("fs"+i).value = fs;
					}		
				}
			}		
		});
		
		dwr.engine.setAsync(true);
	}
}
