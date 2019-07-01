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
			var htmltext = "<input type='text' name='sksj' style='width:80%' id='sksj" + max + "'";
	    		htmltext+="onblur='dateFormatChg(this)'  readonly='true'";
				htmltext+="onclick='time(this.id)'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' style='width:80%'  name='skdd' id='skdd" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' name='zt' id='zt" + max + "'/>";
	    
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' name='hdgm' id='hdgm" + max + "'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<textarea rows='3' cols='3' style='width:100%' onblur='checkLen(this,200)' name='bz' id='bz" + max + "'/>";
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
	
	if(tabLen == null || tabLen == 0){
		alert("课程安排信息");
		return false;
	}
	
	for(var i=1;i<=rowLen;i++){
		if($("sksj"+i)){
			if($("sksj"+i).value == ""){
				alert("授课时间不能为空，请确定");
				return false;
			}
		}
		if($("skdd"+i)){
			if($("skdd"+i).value == ""){
				alert("授课地点不能为空，请确定");
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
			var tableName1="xysf_dyjskcb";
			var colList1 =["sksj","skdd","zt","hdgm","bz"];
			var pk = "zgh";
			var pkValue = zgh;
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
						if($("sksj"+i)){
							var sksj = data[i-1].sksj;
							if(sksj == null){
								sksj = "数据库无信息";
							}
							$("sksj"+i).value = sksj;
						}
						if($("skdd"+i)){
							var skdd = data[i-1].skdd;
							if(skdd == null){
								skdd = "数据库无信息";
							}
							$("skdd"+i).value = skdd;
						}
						if($("zt"+i)){
							var zt = data[i-1].zt;
							if(zt == null){
								zt = "";
							}
							$("zt"+i).value = zt;
						}
						if($("hdgm"+i)){
							var hdgm = data[i-1].hdgm;
							if(hdgm == null){
								hdgm = "";
							}
							$("hdgm"+i).value = hdgm;
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
