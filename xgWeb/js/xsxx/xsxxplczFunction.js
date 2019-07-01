function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[	
	function(data){
		return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
	},				 
	function(data){
		var htmltext = "<input  name='saveb_kssj' id='kssj" + count + "' style='width:100px' onclick='return showCalendar(\"kssj" +count+ "\",\"y-mm-dd\");'/>";	  		
		return htmltext;
    }, 
    function(data){
    	var htmltext = "<input  name='saveb_jssj' id='jssj" + count + "' style='width:100px' onclick='return showCalendar(\"jssj" +count+ "\",\"y-mm-dd\");'/>";		  		
		return htmltext;
    },      	
    function(data){
        var htmltext = "<input  name='saveb_szdq' id='szdq" + count + "' maxLength='100'/>";	  	      		
   	    return htmltext;
    },       
    function(data){
 	    var htmltext = "<input  name='saveb_szbm' id='szbm" + count + "'  maxLength='100'/>";	  		
   	    return htmltext;
    },
    function(data){
        var htmltext = "<input  name='saveb_drzw' id='drzw" + count + "'  maxLength='100'/>";	  		
   	    return htmltext;
    },
    function(data){
        var htmltext = "<input  name='saveb_zmr' id='zmr" + count + "'  maxLength='25'/>";	  		
   	    return htmltext;
    },
    function(data){
        var htmltext = "<input  name='saveb_zmrdw' id='zmrdw" + count + "'  maxLength='100'/>";	  		
   	    return htmltext;
    },
    function(data){
        var htmltext = "<input  name='saveb_zmrzw' id='zmrzw" + count + "'  maxLength='50'/>";	  		
   	    return htmltext;
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

function shgxtrAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("shgx_numAdd").value;
    count=len;     
	var cellfu =[	
	function(data){
		return count+ "<input type='hidden' style='width:50px'  name='_xuh' value='"+count+"'>";
	},				 
	function(data){
		var htmltext = "<input  name='saveb_cyxm' id='cyxm" + count + "' style='width:100px' maxLength='25'/>";	  		
		return htmltext;
    }, 
    function(data){
    	var htmltext = "<input  name='saveb_cynl' id='cynl" + count + "' style='width:100px' maxLength='5' onkeyup='value=value.replace(/[^\\d|-]/g,\"\") '/>";		  		
		return htmltext;
    },      	
    function(data){
        var htmltext = "<select  name='saveb_cyzzmmdm' id='cyzzmmdm" + count + "' maxLength='100'/></select>";	  	      		
   	    return htmltext;
    },       
    function(data){
 	    var htmltext = "<input  name='saveb_cygzdw' id='cygzdw" + count + "'  maxLength='100'/>";	  		
   	    return htmltext;
    },
    function(data){
        var htmltext = "<input  name='saveb_cydrzw' id='cydrzw" + count + "'  maxLength='50'/>";	  		
   	    return htmltext;
    },
    function(data){
        var htmltext = "<input  name='saveb_cylxdh' id='cylxdh" + count + "'  maxLength='20' onkeyup='value=value.replace(/[^\\d|-]/g,\"\") '/>";	  		
   	    return htmltext;
    }  
	];	
	if(type=='add'){
      DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});		
      //加载政治面貌列表数据
      initZzmmList("cyzzmmdm"+count);
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});	
          //加载政治面貌列表数据
          initZzmmList("cyzzmmdm"+count);
          count++;
       }
       $("shgx_numAdd").value = "";
    }        
}

function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("确定要删除第"+(length)+"行？")){       
         tabobj.deleteRow(tabobj.rows.length-1);                
    }
}

function trDelAll(the_tab,numId){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $(numId).value; 
    if(length==0){
       $(numId).value = "";
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
    $(numId).value = "";
}

//检测学习经历信息是否填写完整
function checkXsxxjlxx(){	
	if($('flag')){
		var tabobj = document.getElementById('flag');
		var rowLen = tabobj != null || tabobj != undefined ? tabobj.rows.length : 0;
		for(var i=1;i<=rowLen;i++){
			if($("kssj" + i) && $("kssj"+i).value != ""){
			    if($("jssj"+i) && $("jssj"+i).value == ""){
					alert("第"+i+"行结束时间不能为空！");
					return false;
				}
				if($("zmr"+i) && $("zmr"+i).value == ""){
					alert("第"+i+"行证明人不能为空！");
					return false;
				}	
			}
		}
	}
	return true;
}

function loadXsxxjl(){
	dwr.engine.setAsync(false);
	if(ele('flag')){
		getStuDetails.getXsxxjl(val('xh'),function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');  
				  
				for(var i=1;i<=data.length;i++){
					if($("kssj"+i)){
						var _kssj = data[i-1].kssj;
						_kssj = _kssj == null || _kssj=="null" ? "" : _kssj;
						$("kssj"+i).value = _kssj ;
					}
					if($("jssj"+i)){
						var _jssj = data[i-1].jssj;
						_jssj = _jssj == null || _jssj=="null" ? "" : _jssj;
						$("jssj"+i).value = _jssj ;
					}					
					if($("szdq"+i)){
						var _szdq = data[i-1].szdq;
						_szdq = _szdq == null ? "" : _szdq;
						$("szdq"+i).value = _szdq;
					}
					if($("szbm"+i)){
						var _szbm = data[i-1].szbm;
						_szbm = _szbm == null ? "" : _szbm;
						$("szbm"+i).value = _szbm;
					}
					if($("drzw"+i)){
						var _drzw = data[i-1].drzw;
						_drzw = _drzw == null ? "" : _drzw;
						$("drzw"+i).value = _drzw;
					}
					if($("zmr"+i)){
						var _zmr = data[i-1].zmr;
						_zmr = _zmr == null ? "" : _zmr;
						$("zmr"+i).value = _zmr;
					}
					if($("zmrdw"+i)){
						var _zmrdw = data[i-1].zmrdw;
						_zmrdw = _zmrdw == null ? "" : _zmrdw;
						$("zmrdw"+i).value = _zmrdw;
					}
					if($("zmrzw"+i)){
						var _zmrzw = data[i-1].zmrzw;
						_zmrzw = _zmrzw == null ? "" : _zmrzw;
						$("zmrzw"+i).value = _zmrzw;
					}
				}
			}
		});
	}
	dwr.engine.setAsync(true);
}

function loadXsshgx(){
	dwr.engine.setAsync(false);
	if(ele('shgx')){		
		getStuDetails.getXsshgx(val('xh'),function(data){
			if(data !=null && data.length>0){
				$("shgx_numAdd").value=data.length;
				shgxtrAdd('shgx','madd');  
				for(var i=1;i<=data.length;i++){
					if($("cyxm"+i)){
						var _cyxm = data[i-1].cyxm;
						_cyxm = _cyxm == null || _cyxm=="null" ? "" : _cyxm;
						$("cyxm"+i).value = _cyxm ;
					}
					if($("cynl"+i)){
						var _cynl = data[i-1].cynl;
						_cynl = _cynl == null || _cynl=="null" ? "" : _cynl;
						$("cynl"+i).value = _cynl;
					}					
					if($("cyzzmmdm"+i)){
						var _cyzzmmdm = data[i-1].cyzzmmdm;
						_cyzzmmdm = _cyzzmmdm == null ? "" : _cyzzmmdm;
						$("cyzzmmdm"+i).value = _cyzzmmdm;
					}
					if($("cygzdw"+i)){
						var _cygzdw = data[i-1].cygzdw;
						_cygzdw = _cygzdw == null ? "" : _cygzdw;
						$("cygzdw"+i).value = _cygzdw;
					}
					if($("cydrzw"+i)){
						var _cydrzw = data[i-1].cydrzw;
						_cydrzw = _cydrzw == null ? "" : _cydrzw;
						$("cydrzw"+i).value = _cydrzw;
					}
					if($("cylxdh"+i)){
						var _cylxdh = data[i-1].cylxdh;
						_cylxdh = _cylxdh == null ? "" : _cylxdh;
						$("cylxdh"+i).value = _cylxdh;
					}
				}
			}
		});
	}
	dwr.engine.setAsync(true);
}

//检测社会关系信息是否填写完整
function checkXsshgxxx(){
	if($('shgx')){
		var tabobj = document.getElementById('shgx');
		var rowLen = tabobj != null || tabobj != undefined ? tabobj.rows.length : 0;
	
		var cyxmArr = [];
		for(var i=1;i<=rowLen;i++){
			cyxmArr[i-1] = $("cyxm"+i).value;
		}
		if(checkArrayEleRepeat(cyxmArr,'')){
			alert("社会关系成员姓名有重复！");
			return false;
		}
	}
	return true;
}
