 var count = 1;
 var max = 0;
 function trAdd(the_tab,type,numAdd,lb){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $(numAdd).value;
    var doType = $("doType").value;
   
    count=len;  
	if(type=='add'){
		 max++;
	   var cellfu = getCellfu(lb);
	  
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
       initList();
      
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(j=count;j<=num;j++){          
          max++;
 		  var cellfu = getCellfu(lb);
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
          initList();
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
	var zxmzdArr=document.getElementsByName("zxmzd");
	var zxmzdlxArr=document.getElementsByName("zxmzdlx");
	var zxmlrxzArr=document.getElementsByName("zxmlrxz");
	var zxmlrcdArr=document.getElementsByName("zxmlrcd");
	
	if(lb == 'rzqk'){
		var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
		cellfu[0]=htmlText;
		
		//根据字段配置表设置显示分级字段
		for(i=1;i<=zxmzdArr.length;i++){
			var htmlV ="";
			
			//TEXT类型
			if("text"==zxmzdlxArr[i-1].value){
				htmlV ="<input type='text' name='"+zxmzdArr[i-1].value+"Arr'";
				htmlV+=" id='"+zxmzdArr[i-1].value+"_"+max+ "' style='width:120' maxlength='30' />";	
			}
			//SELECT类型
			if("select"==zxmzdlxArr[i-1].value){
				htmlV ="<select name='"+zxmzdArr[i-1].value+"Arr' id='"+zxmzdArr[i-1].value+"_"+max+ "'>"
				htmlV +="<option></option>"
				htmlV +="</select>";
			}
			
			
			cellfu[i]=htmlV;
		}
	
	}else if(lb == 'xxjlxg'){
		var htmlText = "<input type='hidden' style='width:99%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
		cellfu[0]=htmlText;
		//根据字段配置表设置显示分级字段
		
		for(i=1;i<=zxmzdArr.length;i++){
			var htmlV ="";
			
			//TEXT类型
			if("text"==zxmzdlxArr[i-1].value){
				htmlV ="<input type='text' name='"+zxmzdArr[i-1].value+"Arr'";
				htmlV+=" id='"+zxmzdArr[i-1].value+"_"+max+"' style='width:120' maxlength='30'/>";	
			}
			//SELECT类型
			if("select"==zxmzdlxArr[i-1].value){
				htmlV ="<select name='"+zxmzdArr[i-1].value+"Arr' id='"+zxmzdArr[i-1].value+"_"+max+"'>"
				htmlV +="<option></option>"
				htmlV +="</select>";
			}
			cellfu[i]=htmlV;
		}
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
	var pkValue = $("xmdm").value+"!!@@!!"+$("xh").value+"!!@@!!"+$("sqsj").value;
		if(pkValue != ""){
			dwr.engine.setAsync(false);
			var tableName="xg_rcsw_swsqzxb";
			var colList =new Array();
			var zxmzdArr=document.getElementsByName("zxmzd");
			for(i=0;i<zxmzdArr.length;i++){
				colList[i]=zxmzdArr[i].value;
			}
			var pk = "xmdm||'!!@@!!'||xh||'!!@@!!'||sqsj";
			var query =" ";
			
			getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					trAdd('flag1','madd','numAdd1','xxjlxg');	
					for(i=1;i<=data.length;i++){
						
						for(j=0;j<colList.length;j++){
							
							if($(colList[j]+"_"+i)){
								var str="";
								if("xn"==colList[j]){
									str= data[i-1].xn;
								}else if("xq"==colList[j]){
									str= data[i-1].xq;
								}else if("nd"==colList[j]){
									str= data[i-1].nd;
								}else if("zd1"==colList[j]){
									str= data[i-1].zd1;
								}else if("zd2"==colList[j]){
									str= data[i-1].zd2;
								}else if("zd3"==colList[j]){
									str= data[i-1].zd3;
								}else if("zd4"==colList[j]){
									str= data[i-1].zd4;
								}else if("zd5"==colList[j]){
									str= data[i-1].zd5;
								}else if("zd6"==colList[j]){
									str= data[i-1].zd6;
								}else if("zd7"==colList[j]){
									str= data[i-1].zd7;
								}else if("zd8"==colList[j]){
									str= data[i-1].zd8;
								}else if("zd9"==colList[j]){
									str= data[i-1].zd9;
								}else if("zd10"==colList[j]){
									str= data[i-1].zd10;
								}
								
								if(str == null){
									str = "";
								}
								var id=colList[j]+"_"+i;
								setTimeout("loadValue('"+id+"','"+str+"')",100);
							}
						}
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}


function loadValue(id,str){
	$(id).value = str;
}

function initList(){
	
	//字段
	var zxmzdArr=document.getElementsByName("zxmzd");
	var zxmzdlxArr=document.getElementsByName("zxmzdlx");
	var zxmlrxzArr=document.getElementsByName("zxmlrxz");
	var zxmlrcdArr=document.getElementsByName("zxmlrcd");
	for(i=1;i<=zxmzdArr.length;i++){
		//学年
		if("xn"==zxmzdArr[i-1].value 
			&& "select"==zxmzdlxArr[i-1].value){
			var html="<select name='xnArr'  id='xn_"+max+"'>";
			html+=$("select_xn").innerHTML;
			html+="</select>";
			if($("xn_"+max)){
				
				$("xn_"+max).outerHTML=html;
			}
		}
		//学期
		if("xq"==zxmzdArr[i-1].value 
			&& "select"==zxmzdlxArr[i-1].value){
			var html="<select name='xqArr'  id='xq_"+max+"'>";
			html+=$("select_xq").innerHTML;
			html+="</select>";
			if($("xq_"+max)){
				$("xq_"+max).outerHTML=html;
			}
		}
		
	}
}
