function initCpzList(){
	
	var xydm = $("xy").value;
	var xn   = $("xn").value;
		getCpzfp.getCpzList(xn,xydm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "cpz";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					if($('tableName')){
						$(objId).options[0] = new Option('','');   
					}
					DWRUtil.addOptions(objId,data,"cpzdm","xscpz");
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

function getCpzBjList(){
	
	if($("cpz")){
	var cpzdm = $("cpz").value;
	var xn   = $("xn").value;
	var xydm   = $("xy").value;
		getCpzfp.getCpzBjList(xn,xydm,cpzdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "yfpbj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					if($('tableName')){
						$(objId).options[0] = new Option('','');   
					}
					DWRUtil.addOptions(objId,data,"bjdm","bjmc");
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
	}
}

function initWfpBjList(){
	
	var xn   = $("xn").value;
	var xydm   = $("xy").value;
	var zydm   = $("zy").value;
	var nj   = $("nj").value;
	dwr.engine.setAsync(false);
		getCpzfp.getWfpBjList(xydm,zydm,nj,xn,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "wfpbj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"bjdm","bjmc");
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
	dwr.engine.setAsync(true);
	if(document.getElementById("yfpbj").options.length!=0){
		var yfpbjs = '';
		for(var i=0;i<document.getElementById("yfpbj").options.length;i++){	
		    	yfpbjs+=document.getElementById("yfpbj").options[i].value+"!";
		}
		for(var i=0;i<document.getElementById("wfpbj").options.length;i++){	
				if(yfpbjs.indexOf(document.getElementById("wfpbj").options[i].value)>=0){
		    		document.getElementById("wfpbj").options[i]=null;
		    	i--;
			}
		}
	}
}

function initjxjcpzBjList(){
	
	if($("cpz")){
	var cpzdm = $("cpz").value;
	var xn   = $("xn").value;
	var xydm   = $("xy").value;
		getCpzfp.getCpzBjList(xn,xydm,cpzdm,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);
					if($('tableName')){
						$(objId).options[0] = new Option('','');   
					}
					DWRUtil.addOptions(objId,data,"bjdm","bjmc");
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
	}
}