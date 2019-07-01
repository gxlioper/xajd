//家庭成员
function getJtcy(){
	
	var xxdm = $("xxdm").value;
	
	var cellfu = "";

	if(xxdm == "10395"){//闽江学院
		cellfu = getMjxyJtcy();
	}else if(xxdm == "10589"){//海南大学
		cellfu = getHaindxJtcy();
	}else if(xxdm == "10491"){//中国地质大学
		cellfu = getZgdzdxJtcy();
	}else{//通用
		cellfu = getTyJtcy();
	}
	
	return cellfu;
}

//通用家庭成员属性
function getTyJtcy(){

	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:50px'  name='_xuh' id='xuh"+max+"'>";
				htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />"
				return htmlText;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:50px'  name='cyxm' id='cyxm" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		   	return htmltext;
		},
		function(data){
		   	var htmltext = "<input type='text' style='width:50px;ime-mode:disabled'  name='cynl' id='cynl" + max + "'";
				htmltext += " onkeydown='return onlyNum(this,3)'";
				htmltext += " onmousedown='return onlyNum(this,3)'";
		   		htmltext += " maxLength = '3'/>";	
		   	return htmltext;
		},
		function(data){
			var htmltext = "<select style='width:60px'  name='cygx' id='cygx" + max + "'>";
		  	htmltext+= $('gx').innerHTML;
		    return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100px'  name='cygzdw' id='cygzdw" + max + "'";
		    	htmltext += " maxLength = '50'/>";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:50px'  name='cyzy' id='cyzy" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:50px;ime-mode:disabled'  name='cydh' id='cydh" + max + "'";
		    	htmltext += " onkeydown='return onlyNum(this,20)'";
				htmltext += " onmousedown='return onlyNum(this,20)'";
		    	htmltext += " maxLength = '20'/>";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:50px;ime-mode:disabled'  name='cynsr' id='cynsr" + max + "'";
		    	htmltext += " onkeydown='return onlyNum(this,7)'";
				htmltext += " onmousedown='return onlyNum(this,7)'";
		    	htmltext += " maxLength = '7'/>";	
		    return htmltext;
		}, 
		function(data){
		    var htmltext = "<input type='text' style='width:50px'  name='cyjkzk' id='cyjkzk" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		    return htmltext;
		}
	];
	
	return cellfu;
}

//闽江学院家庭成员属性
function getMjxyJtcy(){
	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />"
			return htmlText;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:100%'  name='cyxm' id='cyxm" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:100%'  name='cynl' id='cynl" + max + "'";
		    	htmltext += " maxLength = '3'/>";	
		    return htmltext;
		},
		function(data){
			var htmltext = "<select style=''  name='cygx' id='cygx" + max + "'>";
			  		htmltext+= $('gx').innerHTML;
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cygzdw' id='cygzdw" + max + "'";
				htmltext += " maxLength = '50'/>";	
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cyzy' id='cyzy" + max + "'";
				htmltext += " maxLength = '10'/>";	
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cydh' id='cydh" + max + "'";
				htmltext += " onkeypress='return sztzNumInputValue(this,20)' style='width:100%;ime-mode:disabled'/>";
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cynsr' id='cynsr" + max + "'";
				htmltext += " onkeypress='return sztzNumInputValue(this,7,event)' style='width:100%;ime-mode:disabled'/>";
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cynzc' id='cynzc" + max + "'";
			htmltext += " onkeypress='return sztzNumInputValue(this,7,event)' style='width:100%;ime-mode:disabled'/>";
			return htmltext;
		},   
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cyjkzk' id='cyjkzk" + max + "'";
				htmltext += " maxLength = '10'/>";	
			return htmltext;
		}
		];
		
	return cellfu;
}

//海南大学家庭成员属性
function getHaindxJtcy(){
	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />"
			return htmlText;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:100%'  name='cyxm' id='cyxm" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:100%'  name='cynl' id='cynl" + max + "'";
		    	htmltext += " maxLength = '3'/>";	
		    return htmltext;
		},
		function(data){
			var htmltext = "<select style=''  name='cygx' id='cygx" + max + "'>";
			  		htmltext+= $('gx').innerHTML;
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cygzdw' id='cygzdw" + max + "'";
				htmltext += " maxLength = '50'/>";	
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cyzy' id='cyzy" + max + "'";
				htmltext += " maxLength = '10'/>";	
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cydh' id='cydh" + max + "'";
				htmltext += " onkeypress='return sztzNumInputValue(this,20)' style='width:100%;ime-mode:disabled'/>";
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cyyb' id='cyyb" + max + "'";
				htmltext += " onkeypress='return sztzNumInputValue(this,10,event)' style='width:100%;ime-mode:disabled'/>";
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cynsr' id='cynsr" + max + "'";
				htmltext += " onkeypress='return sztzNumInputValue(this,7,event)' style='width:100%;ime-mode:disabled'/>";
			return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cyysr' id='cyysr" + max + "'";
			htmltext += " onkeypress='return sztzNumInputValue(this,7,event)' style='width:100%;ime-mode:disabled'/>";
			return htmltext;
		},   
		function(data){
			var htmltext = "<input type='text' style='width:100%'  name='cyjkzk' id='cyjkzk" + max + "'";
				htmltext += " maxLength = '10'/>";	
			return htmltext;
		}
		];
		
	return cellfu;
}

// 中国地质大学
function getZgdzdxJtcy(){
	var cellfu =[
		function(data){
			var htmlText = "<input type='hidden' style='width:50px'  name='_xuh' id='xuh"+max+"'>";
				htmlText += "<input type='checkbox' name='delRow' value='"+count+"' />"
				return htmlText;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:50px'  name='cyxm' id='cyxm" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		   	return htmltext;
		},
		function(data){
		   	var htmltext = "<input type='text' style='width:50px;ime-mode:disabled'  name='cynl' id='cynl" + max + "'";
				htmltext += " onkeydown='return onlyNum(this,3)'";
				htmltext += " onmousedown='return onlyNum(this,3)'";
		   		htmltext += " maxLength = '3'/>";	
		   	return htmltext;
		},
		function(data){
			var htmltext = "<select style='width:60px'  name='cygx' id='cygx" + max + "'>";
		  	htmltext+= $('gx').innerHTML;
		    return htmltext;
		},
		function(data){
			var htmltext = "<input type='text' style='width:100px'  name='cygzdw' id='cygzdw" + max + "'";
		    	htmltext += " maxLength = '50'/>";	
		    return htmltext;
		},
		function(data){
		    var htmltext = "<input type='text' style='width:50px'  name='cyzy' id='cyzy" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		    return htmltext;
		},
		
		function(data){
		    var htmltext = "<input type='text' style='width:50px;ime-mode:disabled'  name='cynsr' id='cynsr" + max + "'";
		    	htmltext += " onkeydown='return onlyNum(this,7)'";
				htmltext += " onmousedown='return onlyNum(this,7)'";
		    	htmltext += " maxLength = '7'/>";	
		    return htmltext;
		}, 
		function(data){
		    var htmltext = "<input type='text' style='width:50px'  name='cyjkzk' id='cyjkzk" + max + "'";
		    	htmltext += " maxLength = '10'/>";	
		    return htmltext;
		}
	];
	
	return cellfu;
}


//设置家庭成员信息
function setJtcyInfo(){

	var xh = $("xh").value;	
	var btnId = "cyAdd";
	if($(btnId) && xh!= ""){
	dwr.engine.setAsync(false);

	var colList = new Array();
	getXszzInfo.getTableZd("xszz_jtcyb",function(data){
		if( data != null){
			var n=0;
			for(var i=0;i<data.length;i++){
				if(data[i] != "xh" && data[i] != "id"){
					colList[n] = data[i];
					n++;
				}
			}
		}
	});
	var xmb = $("xmb").value;
	var tableName="xszz_jtcyb";
	var pk = "xh";
	var pkValue = xh;
	var query =" ";
	var flg = xmb != "jtqkdcb" && (xmb != "xszz_knsb" && ("yes" != $('iskns').value || "true" != $('knsdl').value || "true" != $('jtqkdc').value));
	
	getOtherData.getTableListArrInfo(tableName, colList,pk, pkValue,query,function(data){
		if( data != null && data.length > 0){
			var rowLen = data.length;
			var tbId = "jtcyqk";
			
			$(btnId).value = rowLen;
			trAdd(tbId,'madd',btnId);	
			for(var i=0;i<=rowLen;i++){
				for(var j=0;j<=colList.length;j++){
					if($(colList[j]+ i)){
						var nr = data[i-1][j];
						if(nr != null){
							$(colList[j]+ i).value = nr;
						}else{
							$(colList[j]+ i).value = "";
						}
						if(flg){
							$(colList[j]+ i).disabled = true;
						}
					}
				}
			}	
		}else{
			if(flg){
				$("jtcyTrId").style.display = "none";
			}
		}
	});
	
	dwr.engine.setAsync(true);
	}
}