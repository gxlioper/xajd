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
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' style='width:50px'/>"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text'   name='zxcx' id='zxcx" + max + "' style='width:50px' maxlength='25'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
				var htmltext = "<input type='text'   name='zxsj' id='zxsj" + max + "' style='width:50px' maxlength='5'/>";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='zxls' id='zxls" + max + "' style='width:50px' maxlength='10'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'   name='zxjl' id='zxjl" + max + "' style='width:180px' maxlength='50'/>";
	    
	    	return htmltext;
	    },
	     function(data){
	    	var htmltext = "<input type='text'   name='sfcxzx' id='sfcxzx" + max + "' style='width:50px' maxlength='5'/>";
	    
	    	return htmltext;
	    }
		];
	}else if(lb == 'rzqk_sh'){
		cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"'style='width:50px' maxlength='25' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text'  name='zxcx' id='zxcx" + max + "' style='width:50px'  maxlength='25'/>";
	 
	    	return htmltext;
	    },
	    function(data){	    
				var htmltext = "<input type='text'  readonly  name='zxsj' id='zxsj" + max + "' style='width:50px'   maxlength='5' />";
	 
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text'     name='zxls' id='zxls" + max + "' style='width:50px' maxlength='10'/>";
	
	    	return htmltext;
	    },
	    function(data){
	    	var htmltext = "<input type='text' name='zxjl' id='zxjl" + max + "' style='width:180px' maxlength='50'/>";
	    
	    	return htmltext;
	    },
	     function(data){
	    	var htmltext = "<input type='text'  name='sfcxzx' id='sfcxzx" + max + "' style='width:50px' maxlength='5'/>";
	    
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
		if($("zxcx"+i)){
			lens++;
			if($("zxcx"+i).value == ""){
				alert("心理困惑咨询记录有信息为空，请确定");
				return false;
			}
		}
		if($("zxsj"+i)){
			if($("zxsj"+i).value == ""){
				alert("心理困惑咨询记录有信息为空，请确定");
				return false;
			}
		}
		if($("zxls"+i)){
			if($("zxls"+i).value == ""){
				alert("心理困惑咨询记录有信息为空，请确定");
				return false;
			}
		}
		if($("zxjl"+i)){
			if($("zxjl"+i).value == ""){
				alert("心理困惑咨询记录有信息为空，请确定");
				return false;
			}
		}
		if($("sfcxzx"+i)){
			if($("sfcxzx"+i).value == ""){
				alert("心理困惑咨询记录有信息为空，请确定");
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
	var pkValue = $("pkValue").value;
		if(pkValue != ""){
			dwr.engine.setAsync(false);
			var tableName="xljk_mjxy_xlkhzxjlb";
			var colList =["zxcx","zxsj","zxls","zxjl","sfcxzx"];
			var pk = "xh ";
			var query =" ";
			getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
				if( data != null && data.length > 0){	
					$("numAdd1").value=data.length;
					trAdd('flag1','madd','numAdd1','rzqk_sh');	
					for(var i=1;i<=data.length;i++){
						if($("zxcx"+i)){
							var zxcx = data[i-1].zxcx;
							if(zxcx == null){
								zxcx = "";
							}
							$("zxcx"+i).value = zxcx;
						}
						if($("zxsj"+i)){
							var zxsj = data[i-1].zxsj;
							if(zxsj == null){
								zxsj = "";
							}
							$("zxsj"+i).value = zxsj;
						}
						if($("zxls"+i)){
							var zxls = data[i-1].zxls;
							if(zxls == null){
								zxls = "";
							}
							$("zxls"+i).value = zxls;
						}
						
						if($("zxjl"+i)){
							var zxjl = data[i-1].zxjl;
							if(zxjl== null){
								zxjl = "";
							}
							$("zxjl"+i).value = zxjl;
						}
						if($("sfcxzx"+i)){
							var sfcxzx = data[i-1].sfcxzx;
							if(sfcxzx== null){
								sfcxzx = "";
							}
							$("sfcxzx"+i).value = sfcxzx;
						}
					}
				}		
			});
			
			dwr.engine.setAsync(true);
		}
}

function daoru(){
		var dd_html = "";
		dd_html += "<table width='240' class='tbstyle'>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "表名:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='daorus' id ='daorus'>";
		dd_html += "<option value=''></option>";
		dd_html += "<option value='xljk_mjxy_xlkhxsb'>心理困惑学生表</option>";
		dd_html += "<option value='xljk_mjxy_xlkhzxjlb'>学生困惑记录表</option>";
		dd_html += "</select>";
		dd_html += "</td>";
		dd_html += "</tr>";
	
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='right'>";
		dd_html += "<button  onclick='getDaoRub();closeStuDiv()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button  onclick='closeStuDiv()'>取消</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		showTempDivForJs("请选择表",dd_html,250,100);
	}
function daochu(){
		var dd_html = "";
		dd_html += "<table width='240' class='tbstyle'>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "表名:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='daochus' id ='daochus'>";
		dd_html += "<option value=''></option>";
		dd_html += "<option value='view_xljk_mjxy_xlkhxsb'>心理困惑学生</option>";
		dd_html += "<option value='xljk_mjxy_xlkhzxjlb'>学生困惑记录表</option>";
		dd_html += "</select>";
		dd_html += "</td>";
		dd_html += "</tr>";
	
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='right'>";
		dd_html += "<button  onclick='goDaochub();closeStuDiv()'>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button  onclick='closeStuDiv()'>取消</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		showTempDivForJs("请选择表",dd_html,250,100);
	}

function getDaoRub(){
	var daorus=$("daorus").value;
	$('realTable').value=daorus;
	if(daorus=="" || daorus=="null"){
		alert("请先选择要导入的表！");
		return false;
	}
	impAndChkData();
}

function goDaochub(){
	var daochus=$("daochus").value;
	if(daochus=="" || daochus=="null"){
		alert("请先选择要导出的表！");
		return false;
	}
	var url='/xgxt/xljkMjxyXlkhxs.do?method=expDate&daochu='+daochus;
	expData(url);
}
function closeStuDiv(){
		$('tempDiv').style.display='none';
		i = document.getElementsByTagName("select").length;
	
		for (j = 0; j < i; j++) {
			document.getElementsByTagName("select")[j].style.visibility = "";
			document.getElementsByTagName("select")[j].style.display = "";
		}
	}

