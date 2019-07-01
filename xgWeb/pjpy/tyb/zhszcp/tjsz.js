var count = 1; //起始序号
var counts = 1;
	
	function checkDataUnion(obj){
		if (obj.value != "" && obj.value != null) {
			PjpyTybDAO.checkDmDataUnion(obj.value,function(data) {
				if (data) {
					alert("代码：" + obj.value + " 已经存在，请重新输入!");
					obj.value = "";
					return false;
				}
			});
		}
	}
	
	function loadchange(){
			var tab = document.getElementById("titName").value;
			document.getElementById(tab+"l").className = "xxk_on_l";
			document.getElementById(tab+"m").className = "xxk_on_m";
			document.getElementById(tab+"r").className = "xxk_on_r";
	    }
	    
	    function changePage(obj){
	    	var id = obj.parentNode.id;
	    	id = id.substring(0,id.length-2);
	    	var len = 0;
	    	len =toInt(document.getElementById('xxkDiv').children.length); 
	    	for(var i=0; i<len; i++){
	    		nodeId = document.getElementById('xxkDiv').children[i].id;
	    		nodeId = nodeId.substring(0,nodeId.length-2);
	    		
	    		document.getElementById(nodeId+"l").className = "xxk_off_l";
	    		document.getElementById(nodeId+"m").className = "xxk_off_m";
	    		document.getElementById(nodeId+"r").className = "xxk_off_r";
	    		if(document.getElementById(nodeId)){
	    			document.getElementById(nodeId).style.display = "none";	    			
	    		}
	    	}		
			document.getElementById(id+"l").className = "xxk_on_l";
			document.getElementById(id+"m").className = "xxk_on_m";
			document.getElementById(id+"r").className = "xxk_on_r";
			document.getElementById(id+"ul").style.display = "";
			id = id.replace("ul", "");
			refreshForm('pjpy_tyb_zctjsz.do?id=' + id);
	    }
	    
 //增加一行
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    var cellfu = new Array();

    count=len;  
		cellfu =[
		function(data){
			return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
		},
		function(data){
			return "<input type='text' style='width:50px'  name='ejdm' onkeyup='checkDataUnion(this)'  id='ejdm" + count + "'>";
		},
		function(data){
			return "<input type='text'  name='ejmc'  id='ejmc" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:50px'  name='ejbl' maxlength='4' onkeyup='ckinpdata(this)'  id='ejbl" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:50px'  name='ejxzf' maxlength='4' onkeyup='ckinpjedata(this)' id='ejxzf" + count + "'>";
		},
		function(data){	    
			var htmltext = "<select style=''  name='ejlb' id='ejlb" + count + "'>";
		  		htmltext+= "<option value='+'>加分</option>";
		  		htmltext+= "<option value='-'>减分</option></select>";
			return htmltext;
	    },
	    function(data){
			return  "<input type='hidden' style='' value='${rs.dm}' readonly='true'  name='ejfdm'  id='ejfdm" + count + "'><input type='text'  value='${rs.mc}' readonly='true'  name='ejfdmmc'  id='ejfdmmc" + count + "'>";
		}
		];
	
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $("numAdd").value = "";
    }    
}

function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("确定要删除第"+(length)+"行吗？")){
         tabobj.deleteRow(tabobj.rows.length-1);    
    }
}
function trDelAll(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDel").value; 
    if(length==0){
       $("numDel").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("确定要删除最后"+num+"行吗？")){ 
         for(i=1;i<=num;i++){           
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
}	

function save2jtj(the_tab,type){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	
	if(rowLen == 0){
		alert("三级代码条件设置不能为空!");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
	
		if($("ejdm"+i)){
			if($("ejdm"+i).value == "" ){
				alert("三级代码条件设置第"+i+"行代码为空，请输入！");
				return false;
			}
		}
		if($("ejmc"+i)){
			if($("ejmc"+i).value == ""){
				alert("三级代码条件设置第"+i+"行名称为空，请输入！");
				return false;
			}
		}
		if($("ejbl"+i)){
			if($("ejbl"+i).value == ""){
				alert("三级代码条件设置第"+i+"行比例为空，请输入！");
				return false;
			}
		}
		if($("ejfdm"+i)){
			if($("ejfdm"+i).value == ""){
				alert("三级代码条件设置第"+i+"行父代码为空，请输入！");
				return false;
			}
		}
	}
	refreshForm('pjpy_tyb_zctjsz.do?act=save&type=3&id=' + $('titName').value);
}

function onShow(){
		dwr.engine.setAsync(false);
		var tableName="pjpy_zctjszb";
		var colList =["dm","mc","bl","xzf","lb", "fdm"];
		var pk = "fdm";
		var pkValue = $('dm').value;
		var query =" order by dm";
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){	
				$("numAdd").value=data.length;
				trAdd('flag','madd');			
				for(var i=1;i<=data.length;i++){
					if($("ejdm"+i)){
						$("ejdm"+i).value = data[i-1].dm;
					}
					if($("ejmc"+i)){
						$("ejmc"+i).value = data[i-1].mc;
					}
					if($("ejbl"+i)){
						$("ejbl"+i).value = data[i-1].bl;
					}
					if($("ejxzf"+i)){
						$("ejxzf"+i).value = data[i-1].xzf;
					}
					if($("ejlb"+i)){
						$("ejlb"+i).value = data[i-1].lb;
					}
					if($("ejfdm"+i)){
						$("ejfdm"+i).value = data[i-1].fdm;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
}  

 //增加一行
 function trAdds(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdds").value;
    var cellfu = new Array();
	
    count=len;  
		cellfu =[
		function(data){
			return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
		},
		function(data){
			return "<input type='text' style='width:50px'  name='sjdm' onkeyup='checkDataUnion(this)' id='sjdm" + count + "'>";
		},
		function(data){
			return "<input type='text'  name='sjmc'  id='sjmc" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:50px'  name='sjbl' maxlength='4' onkeyup='ckinpdata(this)'  id='sjbl" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:50px'  name='sjxzf' maxlength='4' onkeyup='ckinpjedata(this)' id='sjxzf" + count + "'>";
		},
		function(data){	    
			var htmltext = "<select style=''  name='sjlb' id='sjlb" + count + "'>";
		  		htmltext+= "<option value='+'>加分</option>";
		  		htmltext+= "<option value='-'>减分</option></select>";
			return htmltext;
	    },
	    function(data){
			return  "<input type='text' style='width:70px'  name='sjbm' maxlength='20' id='sjbm" + count + "'>";
		},
		function(data){
			return  "<input type='text' style='width:100px'  name='sjzd' maxlength='20' id='sjzd" + count + "'>";
		},
		function(data){	    
			var htmltext = "<select style=''  name='sjshjb' id='sjshjb" + count + "'>";
		  		htmltext+= "<option value='0'></option><option value='1'>一级</option>";
		  		htmltext+= "<option value='2'>二级</option><option value='3'>三级</option></select>";
			return htmltext;
	    },
	    function(data){
			return  "<input type='text' style='width:100px'  name='sjtj' maxlength='30' id='sjtj" + count + "'>";
		},
		function(data){	    
			var htmltext = "<select style='' name='sjsfplzj' id='sjsfplzj" + count + "'>";
		  		htmltext+= "<option value='1'>是</option>";
		  		htmltext+= "<option value='0'>否</option></select>";
			return htmltext;
	    },
	    function(data){	    
			var htmltext = "<select style=''  name='sjfdm' id='sjfdm" + count + "'>";
		  		htmltext+= "<option value=''></option>";
		  		htmltext+= "</select>";
			return htmltext;
	    }
		];
	
	var dm = $('titName').value;
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
       objId = "sjfdm"+count;
		      dwr.engine.setAsync(false);
		      PjpyTybDAO.query3jZcdm(dm,function(data4){
		         DWRUtil.removeAllOptions(objId);
			     DWRUtil.addOptions(objId,data4,"dm","mc");	
			  });
		      dwr.engine.setAsync(true); 
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          objId = "sjfdm"+count;
		      dwr.engine.setAsync(false);
		      PjpyTybDAO.query3jZcdm(dm,function(data4){
		         DWRUtil.removeAllOptions(objId);
			     DWRUtil.addOptions(objId,data4,"dm","mc");	
			  });
		      dwr.engine.setAsync(true); 
          count++;
       }
       $("numAdds").value = "";
    }    
}
function trDels(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("确定要删除第"+(length)+"行吗？")){
         tabobj.deleteRow(tabobj.rows.length-1);    
    }
}
function trDelAlls(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDels").value; 
    if(length==0){
       $("numDels").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("确定要删除最后"+num+"行吗？")){ 
         for(i=1;i<=num;i++){           
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDels").value = "";
}	

function save3jtj(the_tab,type){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	
	if(rowLen == 0){
		alert("四级代码条件设置不能为空!");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
	
		if($("sjdm"+i)){
			if($("sjdm"+i).value == "" ){
				alert("四级代码条件设置第"+i+"行代码为空，请输入！");
				return false;
			}
		}
		if($("sjmc"+i)){
			if($("sjmc"+i).value == ""){
				alert("四级代码条件设置第"+i+"行名称为空，请输入！");
				return false;
			}
		}
		if($("sjbl"+i)){
			if($("sjbl"+i).value == ""){
				alert("四级代码条件设置第"+i+"行比例为空，请输入！");
				return false;
			}
		}
		if($("sjbm"+i)){
			if($("sjbm"+i).value == ""){
				alert("四级代码条件设置第"+i+"行表名为空，请输入！");
				return false;
			}
		}
		if($("sjzd"+i)){
			if($("sjzd"+i).value == ""){
				alert("四级代码条件设置第"+i+"行字段为空，请输入！");
				return false;
			}
		}
		if($("sjfdm"+i)){
			if($("sjfdm"+i).value == ""){
				alert("四级代码条件设置第"+i+"行父代码为空，请输入！");
				return false;
			}
		}
		
	}
	showTips('数据保存中，请等待......');
	refreshForm('pjpy_tyb_zctjsz.do?act=save&type=4&id=' + $('titName').value);
}

function onShows(){
		dwr.engine.setAsync(false);
		var tableName="pjpy_zctjszb";
		var colList =["dm","mc","bl","xzf","lb","bm", "zd", "tj", "sfplzj", "shjb", "fdm"];
		var pk = "1";
		var pkValue = "1";
		var dm = $('sjdmstr').value.split("!@");
		var query ="";
		if (dm != null && dm.length>0) {
			query =" and fdm in ('";
			for (var i=0;i<dm.length;i++) {
				query += dm[i];
				if (i<dm.length-1) {					
					query+= "','";
				} else {
					query+="'";
				}
			}
			query += ") order by dm";
		}
		
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data1){
			if( data1 != null && data1.length > 0){	
				$("numAdds").value=data1.length;
				trAdds('flags','madd');			
				for(var i=1;i<=data1.length;i++){
					if($("sjdm"+i)){
						$("sjdm"+i).value = data1[i-1].dm;
					}
					if($("sjmc"+i)){
						$("sjmc"+i).value = data1[i-1].mc;
					}
					if($("sjbl"+i)){
						$("sjbl"+i).value = data1[i-1].bl;
					}
					if($("sjxzf"+i)){
						$("sjxzf"+i).value = data1[i-1].xzf;
					}
					if($("sjlb"+i)){
						$("sjlb"+i).value = data1[i-1].lb;
					}
					if($("sjfdm"+i)){
						$("sjfdm"+i).value = data1[i-1].fdm;
					}
					if($("sjbm"+i)){
						$("sjbm"+i).value = data1[i-1].bm;
					}
					if($("sjzd"+i)){
						$("sjzd"+i).value = data1[i-1].zd;
					}
					if($("sjtj"+i)){
						var tj = data1[i-1].tj;
						if (tj==null) {
							tj = "";
						}
						$("sjtj"+i).value = tj;
					}
					if($("sjsfplzj"+i)){
						$("sjsfplzj"+i).value = data1[i-1].sfplzj;
					}
					if($("sjshjb"+i)){
						$("sjshjb"+i).value = data1[i-1].shjb;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
}  