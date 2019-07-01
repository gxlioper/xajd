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
			var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text'   name='qssj' id='qssj" + max + "' style='width:100' onclick=\"return showCalendar('qssj" + max + "','y-mm-dd');\"  onblur=\"dateFormatChg(this)\" readonly=\"true\" />";
	 
	    	return htmltext;
	    },
	    function(data){	    
			var htmltext = "<input type='text'   name='zzsj' id='zzsj" + max + "'style='width:100' onclick=\"return showCalendar('zzsj" + max + "','y-mm-dd');\"  onblur=\"dateFormatChg(this)\" readonly=\"true\" />";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='dwmc' id='dwmc" + max + "' style='width:120' maxlength='30'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='jzr' id='jzr" + max + "' style='width:100' maxlength='10'/>";
	    
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='bz' id='bz" + max + "' style='width:120' maxlength='50'/>";
	    
	    	return htmltext;
	    }
		];
	}else if(lb == 'xxjlxg'){
		cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text'  readonly  name='qssj' id='qssj" + max + "'  style='width:100'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
			var htmltext = "<input type='text' readonly  name='zzsj' id='zzsj" + max + "'   style='width:100' />";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='dwmc' id='dwmc" + max + "'  style='width:120' maxlength='30'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='jzr' id='jzr" + max + "'  style='width:100' maxlength='10'/>";
	    
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'    name='bz' id='bz" + max + "'  style='width:120' maxlength='50'/>";
	    
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

function saveXxjl(url){
	var rowLen = max;
	var nullCout = 0;
	var lens=0;
	var tabLen = document.getElementById('flag1').rows.length;
	
	for(var i=1;i<=rowLen;i++){
		if($("qssj"+i)){
			lens++;
			if($("qssj"+i).value == ""){
				alert("起始时间信息为空，请确定");
				return false;
			}
		}
		if($("zzsj"+i)){
			if($("zzsj"+i).value == ""){
				alert("终止时间有信息为空，请确定");
				return false;
			}
		}
		if($("qssj"+i) && $("zzsj"+i)){
			if(!checkSjTj("qssj"+i,"起始年月","zzsj"+i,"终止年月")){
				return false;
			}
		}
		if($("dwmc"+i)){
			if($("dwmc"+i).value == ""){
				alert("学校或单位名称有信息为空，请确定");
				return false;
			}
		}
		if($("zmr"+i)){
			if($("zmr"+i).value == ""){
				alert("证明人有信息为空，请确定");
				return false;
			}
		}
		if($("bz"+i)){
			if($("bz"+i).value == ""){
				alert("备注有信息为空，请确定");
				return false;
			}
		}
		
		for(var j=i+1;j<=rowLen;j++){
			if($("qssj"+i)&&$("qssj"+j)){
				if($("qssj"+i).value==$("qssj"+j).value 
					&& $("zzsj"+i).value==$("zzsj"+j).value){
					alert("你输入的学习经历存在重复记录！");
					return false;	
				}
			}
		}
	}
	if(lens<1){
		alert("请填写学生经历信息！");
		return false;
	}
	showTips('处理数据中，请等待......');
	document.forms[0].action = url;
	document.forms[0].submit();
}

function onShow(){
	var pkValue = $("pkValue").value;
		if(pkValue != ""){
			dwr.engine.setAsync(false);
			var tableName="xsxx_cslg_xxjlb";
			var colList =["qssj","zzsj","dwmc","jzr","bz"];
			var pk = "xh";
			var query =" ";
			getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					trAdd('flag1','madd','numAdd1','xxjlxg');			
					for(var i=1;i<=data.length;i++){
						if($("qssj"+i)){
							var qssj = data[i-1].qssj;
							if(qssj == null){
								qssj = "";
							}
							$("qssj"+i).value = qssj;
							$("qssj"+i).readOnly="true";
						}
						if($("zzsj"+i)){
							var zzsj = data[i-1].zzsj;
							if(zzsj == null){
								zzsj = "";
							}
							$("zzsj"+i).value = zzsj;
							$("zzsj"+i).readOnly="true";
						}
						if($("dwmc"+i)){
							var dwmc = data[i-1].dwmc;
							if(dwmc == null){
								dwmc = "";
							}
							$("dwmc"+i).value = dwmc;
						}
						
						if($("jzr"+i)){
							var jzr = data[i-1].jzr;
							if(jzr== null){
								jzr = "";
							}
							$("jzr"+i).value = jzr;
						}
						
						if($("bz"+i)){
							var bz = data[i-1].bz;
							if(bz== null){
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
