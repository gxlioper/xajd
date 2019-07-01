//获取学院专业名称
function getXyzymc(){
	var xymc = $("xy").value;
	var zymc = $("zy").value;
	
	if (zymc == null || zymc == ""){
		zymc = "%";
	}
	if (xymc == null || xymc == ""){
		xymc = "%";
	}
	var xyzymc = xymc + "!!-!!" + zymc;
	GetList.getXyzybjList(xyzymc,initTjList);
}

//提交学生住宿分布
function TjStuDromFb(data){
	var cellfu =[
		function(data){return data.xh;},
		function(data){return data.xh;},
		function(data){return data.xm;},
		function(data){return data.xymc;},
		function(data){return data.zymc;},
		function(data){return data.bjmc;},
		function(data){return data.ssbh;},
		function(data){return data.fdyxm},
		function(data){return data.sz;},
		function(data){return data.cz;},
		function(data){return data.lz},
		function(data){return data.cwh}
	];
	if (data != null && typeof data == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");
			DWRUtil.addRows("rsTables",data,cellfu);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//获取学生住宿分布
function getStuDormFb(){
	var query = "";
	var txt1 = $("nj").value;
	var txt2 = $("xh").value;
	var txt3 = $("xy").value;
	var txt4 = $("zy").value;
	var txt5 = $("bj").value;
	
	if(txt1 == "" || txt1 == null){
		nj = "%"
	} else{
		nj = "%" + nj + "%";
	}
	if(txt2 == "" || txt2 == null){
		xh = "%"
	} else{
		xh = "%" + xh + "%";
	}
	if(txt3 == "" || txt3 == null){
		xy = "%";
	}else{
		xy = "%" + xy + "%";
	}
	if(txt4 == "" || txt4 == null){
		zy = "%";
	} else {
		zy = "%" + zy + "%";
	}
	if(txt5 == "" || txt5 == null){
		bj = "%";
	}else{
		bj = "%" + bj + "%";
	}
	query += nj;
	query += "!!-!!";
	query += xh;
	query += "!!-!!";
	query += xy;
	query += "!!-!!";
	query += zy;
	query += "!!-!!";
	query += bj;
	getList.getStuDormList(query,TjStuDromFb);
}

function initPage(){
	var isFdy = "false";
	var isBzr = "false";
	if($("nj")){GetListData.getNjList(initTjList)};
	
	if($("nf")){GetListData.getNfList(initTjList)};
	
	if($("xn")){GetListData.getXnList(initTjList)};	
//	if($("xq")){GetListData.getXqList(initTjList)};
	if($("nd")){GetListData.getNdList(initTjList)};
	
	if($("xy")){GetListData.getXyList(initTjList)};
	
	if($("zy") && ($("xyV").value != null ||$("xyV").value != "")){
		var xy = $("xyV").value;		
		var userName = "";
		var nj = "";
		if($("nj")&&$("nj")!=null){
	    	nj = $("nj").value;
		}	
		if($("isFdy")){
			isFdy = $("isFdy").value;
		}
		if($("isBzr")){
			isBzr = $("isBzr").value;
		}
		if($("userName")){
			userName = document.getElementById("userName").value;
		}
		GetListData.getZyList(xy,nj,userName,isFdy,isBzr,initTjList);
	}
	if($("zdm")){GetListData.getYhzList(initTjList)};
}

/**xy*/
function initXyList(){
	GetListData.getXyList(initTjList);
}

/**body start exec*/
function initOnLoadXyList(){
	var isFdy = "false";
	if($("xy")){GetListData.getXyList(initTjList)};
	if($("zy") && ($("xyV").value != null ||$("xyV").value != "")){
		var xy = $("xyV").value;
		var userName = "";
		
		if($("isFdy")){
			isFdy = $("isFdy").value;
		}
		if($("isBzr")){
			isBzr = $("isBzr").value;
		}
		if($("userName")){
			userName = document.getElementById("userName").value;
		}
		var nj = "";
		if($("nj")&&$("nj")!=null){
	    	nj = $("nj").value;
		}	
		GetListData.getZyList(xy,nj,userName,isFdy,isBzr,initTjList);
	}
	if($("bj") && $("zyV").value != null && $("zyV").value != ""){
		//initBjList();
		var xydm =""; var zydm =""; var nj =""; var query = ""; var userName = "";
		if($("isFdy")){
			isFdy = $("isFdy").value;
		}
		if($("njV")){nj = ($("njV").value == null || $("njV").value =="") ? "%" : ("%" + $("njV").value + "%")};
		if($("xyV")){xydm = ($("xyV").value == null || $("xyV").value =="") ? "%" : ("%" + $("xyV").value + "%")};
		if($("zyV")){zydm = ($("zyV").value == null || $("zyV").value =="") ? "%" : ("%" + $("zyV").value + "%")};
		if($("userName")){userName = $("userName").value};
		query = xydm+"!!-!!"+zydm+"!!-!!"+nj;
		//alert(query);
		GetListData.getBjList(query,userName,isFdy,isBzr,initTjList);
	}else{
		var objId = "bj";
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);
			document.forms[0].bj.options[0] = new Option("--请选择--",""); //initial
		}	
	}
}

function initOnLoadZyList(){
	if($("xy") && ($("xy").value == "" || $("xy").value == null)){
		var objId = "zy";
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);
			document.forms[0].zy.options[0] = new Option("--请选择--",""); //initial
		}
		objId = "bj";
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);
			document.forms[0].bj.options[0] = new Option("--请选择--",""); //initial
		}		
	}else{
		initZyList();
		initBjList();
	}	
}

/**xn*/
function initXnList(){
	GetListData.getXnList(function initTjList(data){
		if (data != null && typeof data == 'object') {
			var objId = data[0].dm;	
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
				$(objId).options[0].value = "";
				//alert(0);
				if($(objId + "V")){
					//alert(1111111);
				if(objId + "V"){
					
				if($(objId +"V").value != "" && $(objId + "V").value != null){
					for(var i = 0;i < $(objId).options.length; i++){
						if($(objId).options[i].value == $(objId +"V").value){
							$(objId).options[i].selected = true;
							return true;
						}
					}
				}
			  }}
				//alert(55555);
			}
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
	});
}

/**nj*/
function initNjList(){
	GetListData.getNjList(initTjList);
}

/**nd*/
function initNdList(){
	GetListData.getNdList(initTjList);
}

function initTjList(data){
	if (data != null && typeof data == 'object') {
		var objId = data[0].dm;	
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);			
			DWRUtil.addOptions(objId,data,"dm","mc");			
			$(objId).options[0].value = "";
			if(objId + "V"){
			if($(objId +"V").value != "" && $(objId + "V").value != null){
				for(var i = 0;i < $(objId).options.length; i++){
					if($(objId).options[i].value == $(objId +"V").value){
						$(objId).options[i].selected = true;
						return true;
					}
				}
			}
		  }
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

function initBjList(){
    //dataLoad(true);
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var userType = "";
	var isFdy="false";
	var isBzr="false";
	if($("userName")){userName = $("userName").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("isFdy")){isFdy=$("isFdy").value};
	if($("userType")&&$("userType")!=null){userType = $("userType").value};
	if("bzr"==userType){
		isFdy = "true";
	}
	if($("isBzr")){isBzr = $("isBzr").value};
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}	
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;	
		GetListData.getBjList(query,userName,isFdy,isBzr,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName && $(objId).tagName.toLowerCase() == "select"){				   
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"dm","mc");
					if($("ysbjdm")){//原始班级
						DWRUtil.removeAllOptions("ysbjdm");
						DWRUtil.addOptions("ysbjdm",data,"dm","mc");
						$("ysbjdm").options[0].value = "";
					}
					if($("mkbjdm")){//模块班级
						DWRUtil.removeAllOptions("mkbjdm");
						DWRUtil.addOptions("mkbjdm",data,"dm","mc");						
						$("mkbjdm").options[0].value = "";
					}
					$(objId).options[0].value = "";
					
					if($(objId + "V")){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
					//dataLoad(false);
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

function initBjallList(){
    //dataLoad(true);
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var userType = "";
	var isFdy="false";
	var isBzr="false";
	if($("userName")){userName = $("userName").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("isFdy")){isFdy=$("isFdy").value};
	if($("userType")&&$("userType")!=null){userType = $("userType").value};
	if("bzr"==userType){
		isFdy = "true";
	}
	if($("isBzr")){isBzr = $("isBzr").value};
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}	
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;	
		GetListData.getBjallList(query,userName,isFdy,isBzr,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bj";
				if($(objId) && $(objId).tagName && $(objId).tagName.toLowerCase() == "select"){				   
					DWRUtil.removeAllOptions(objId);
					DWRUtil.addOptions(objId,data,"dm","mc");
					if($("ysbjdm")){//原始班级
						DWRUtil.removeAllOptions("ysbjdm");
						DWRUtil.addOptions("ysbjdm",data,"dm","mc");
						$("ysbjdm").options[0].value = "";
					}
					if($("mkbjdm")){//模块班级
						DWRUtil.removeAllOptions("mkbjdm");
						DWRUtil.addOptions("mkbjdm",data,"dm","mc");						
						$("mkbjdm").options[0].value = "";
					}
					$(objId).options[0].value = "";
					
					if($(objId + "V")){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
					//dataLoad(false);
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

//杭州职业技术学院班级分毕业的和在校的
function initBj(type){
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	if($("userName")){userName = $("userName").value};
		if($("xy")){xydm = $("xy").value};
		if($("zy")){zydm = $("zy").value};
		if($("nj")){nj = $("nj").value};
		if(xydm == null || xydm == ""){
			xydm = "%";
		} else{
			xydm = "%" + xydm + "%";
		}
		if(zydm == null || zydm == ""){
			zydm = "%";
		} else{
			zydm = "%" + zydm + "%";
		}
		if(nj == null || nj == ""){
			nj = "%";
		} else{
			nj = "%" + nj + "%";
		}
		query += xydm;
		query += "!!-!!";
		query += zydm;
		query += "!!-!!";
		query += nj;
		GetListData.getBj(query,type,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = data[0].dm;
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					$(objId).options[0].value = "";
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
                           }
						}
					}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}
function initZyList(){
   // dataLoad(true);
	var xydm = "";
	var query = "";
	var userName = "";
	var userType = "";	
	var nj    = "";
	var isFdy="";
	var isBzr="";
	if($("isFdy")){isFdy = $("isFdy").value};
	if($("isBzr")){
			isBzr = $("isBzr").value;
	}
	if($("xy")){xydm = $("xy").value};	
	if($("userName")){userName = $("userName").value};
	if($("userType")){userType = $("userType").value};
	if("bzr"==userType){
	   isFdy = "true";
	}
	if($("nj")&&$("nj")!=null){
	    nj = $("nj").value;
	}
		//if(xydm == null || xydm == ""){
		//	xydm = "%";
		//} else{
		//    query += " and xydm='"+xydm+"' ";
			//xydm = "%" + xydm +"%";
		//}	
		//if(nj!=""){
		//    query += " and nj='"+nj+"' ";
		//}
		
		//query = xydm;
		GetListData.getZyList(xydm,nj,userName,isFdy,isBzr,function initTjList(data){
			if (data != null && data != '' && typeof data == 'object') {
				var objId = "zy";
				if($(objId) && $(objId).tagName &&  $(objId).tagName.toLowerCase() == "select"){
					
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
					$(objId).options[0].value = "";
					if($(objId + "V")){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;								
								return true;
							}
							
						}
					}					
					}
				}
			}else{
				//showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

function initZyallList(){
	   // dataLoad(true);
		var xydm = "";
		var query = "";
		var userName = "";
		var userType = "";	
		var nj    = "";
		var isFdy="";
		var isBzr="";
		if($("isFdy")){isFdy = $("isFdy").value};
		if($("isBzr")){
				isBzr = $("isBzr").value;
		}
		if($("xy")){xydm = $("xy").value};	
		if($("userName")){userName = $("userName").value};
		if($("userType")){userType = $("userType").value};
		if("bzr"==userType){
		   isFdy = "true";
		}
		if($("nj")&&$("nj")!=null){
		    nj = $("nj").value;
		}
			//if(xydm == null || xydm == ""){
			//	xydm = "%";
			//} else{
			//    query += " and xydm='"+xydm+"' ";
				//xydm = "%" + xydm +"%";
			//}	
			//if(nj!=""){
			//    query += " and nj='"+nj+"' ";
			//}
			
			//query = xydm;
			GetListData.getZyallList(xydm,nj,userName,isFdy,isBzr,function initTjList(data){
				if (data != null && data != '' && typeof data == 'object') {
					var objId = "zy";
					if($(objId) && $(objId).tagName &&  $(objId).tagName.toLowerCase() == "select"){
						
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");
						$(objId).options[0].value = "";
						if(objId + "V"){
						if($(objId +"V").value != "" && $(objId + "V").value != null){
							for(var i = 0;i < $(objId).options.length; i++){
								if($(objId).options[i].value == $(objId +"V").value){
									$(objId).options[i].selected = true;								
									return true;
								}
								
							}
						}					
						}
					}
				}else{
					//showMsgWin("有错误出现：远程数据读取失败！");
				}
			});
	}

	function initBmList(){
		var bmlb = $("bmlb").value;
			GetListData.getBmListByLb(bmlb,function initTjList(data){
				if (data != null && data != '' && typeof data == 'object') {
					var objId = "bmdm";
					if($(objId) && $(objId).tagName &&  $(objId).tagName.toLowerCase() == "select"){
						
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"bmdm","bmjc");
						//$(objId).options[0].value = "";
						
						for(var i = 0;i < $(objId).options.length; i++){
							$(objId).options[i].title=data[i].bmmc;
						}
						
					}
				}else{
					
				}
			});
	}

function setDm(){
	if($("xyV")){
		document.forms[0].xyV.value = $("xy").value;
	}
}

function GetyhList(){
	var zdm = document.getElementById("zdm").value;
	if(zdm == "" || zdm == null ){
		zdm = "%";
	}else{
		zdm = "%" + zdm + "%";
	}
	GetListData.getYhList(zdm,initTjList);
}

//在卫生检查增加页面当选择等级后自动显示分数
function GetFs(){
	var dj = document.getElementById("dj").value;
	GetListData.getFs(dj,function showFs(str){
		$("fs").value = str;
	} );
}
//在卫生检查增加页面当选择等级后自动显示分数
function GetFs2(obj){
	var id = obj.id;
	var val = obj.value;
	var idIndex = id.split("-");
	GetListData.getFs(val,function showFs(str){	
		$("fs-"+idIndex[1]).value = str;
	} );
 }
function GetQshList(){
	dwr.engine.setAsync(false);

	var lddm = document.getElementById("lddm").value;
	GetListData.GetQshList(lddm,function initTjList(data){
	       if (data != null && typeof data == 'object') {
				var objId = "qsh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);	
					DWRUtil.addOptions(objId,[{dm:'',mc:'--请选择--'}],"dm","mc");
					DWRUtil.addOptions(objId,data,"dm","mc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}	
	});
	
	dwr.engine.setAsync(true);
	
}

function GetCwhList(){
	var lddm = document.getElementById("lddm").value;
	var qsh = document.getElementById("qsh").value;
	if(lddm != "" && lddm != null && qsh != "" && qsh != null){
		var ssbh = lddm + "-" + qsh;
		GetListData.GetCwhList(ssbh,function initTjList(data){
	       if (data != null && typeof data == 'object') {
				var objId = "cwh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}	
	    });
		if($("ydhsfbz")){
		   GetListData.getStrInfo("ssxxb","sfbz","ssbh",ssbh,function(data){
		       $("ydhsfbz").value =data;
		   });
		}
	}
}

function GetOneList(obj){
	var objValue = obj.value;
	if(objValue == "" || objValue == null){
		objValue = "%";
	}else{
		objValue = objValue;
	}
	GetListData.GetOneList(objValue,initTjList);
}
function GetOneList1(obj){
	var objValue = obj.value;
	if(objValue == "" || objValue == null){
		objValue = "%";
	}else{
		objValue = objValue;
	}
	GetListData.GetOneList1(objValue,initTjList);
}

function GetGwInfo(){
	var xh = document.getElementById("xh").value;
	var gwdm = document.getElementById("tzqgw").value;
	var gwsbsj = document.getElementById("tzqgwsbsj").value;
	var querry = "";
	if(xh == "" || xh == null){
		xh = "%";
	}else{
		xh = "%" + xh + "%";
	}
	if(gwdm == "" || gwdm == null){
		gwdm = "%";
	}else{
		gwdm = "%" + gwdm + "%";
	}
	if(gwsbsj == "" || gwsbsj == null){
		gwsbsj = "%";
	}else{
		gwsbsj = "%" + gwsbsj + "%";
	}
	querry += xh;
	querry += "!!-!!";
	querry += gwdm;
	querry += "!!-!!";
	querry += gwsbsj;
	GetListData.GetGwInfo(querry,setTjInfo);
}

function setTjInfo(data){
	if(data!=null){
		document.getElementById("tzqgzxn").value = data[0] == null ? "" : data[0];
		document.getElementById("tzqgznd").value = data[1] == null ? "" : data[1];
		document.getElementById("tzqgzxq").value = data[2] == null ? "" : data[2];
		document.getElementById("tzqgzxqmc").value = data[3] == null ? "" : data[3];
		document.getElementById("tzqkcjqgzxsj").value = data[4] == null ? "" : data[4];
	}else{
		document.getElementById("tzqgzxn").value = "";
		document.getElementById("tzqgznd").value = "";
		document.getElementById("tzqgzxq").value = "";
		document.getElementById("tzqgzxqmc").value = "";
		document.getElementById("tzqkcjqgzxsj").value = "";
	}
}

function getStuLczInfo(){
	var xh = document.getElementById("ghhxh").value;
	GetListData.GetStuLczInfo(xh,setStuLczInfo);
}

function setStuLczInfo(data){
	var col = ["ghhxh","ghhxm","ghhxb","ghhbjmc","ghhlddm","ghhqsh","ghhqsdh"];
	for(i=0; i<col.length; i++){
		document.getElementById(col[i]).value = data[i];
	}
}

function getDykpInfo(){
	var xn = document.getElementById("xn").value;
	var xq = document.getElementById("xq").value;
	var xh = document.getElementById("xh").value;
	var querry="";
	if(xh == "" || xh == null){
		xh = "%";
	}else{
		xh = "%" + xh + "%";
	}
	if(xn == "" || xn == null){
		xn = "%";
	}else{
		xn = "%" + xn + "%";
	}
	if(xq == "" || xq == null){
		xq = "%";
	}else{
		xq = "%" + xq + "%";
	}
	querry += xh;
	querry += "!!-!!";
	querry += xn;
	querry += "!!-!!";
	querry += xq;
	GetListData.getDykpInfo(querry,TjStuDykpInfo);
}

function TjStuDykpInfo(data){
	var cellfu =[
		function(data){return data.jlsj;},
		function(data){return data.cfsj;}
	];
	if (data != null && typeof data == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");
			DWRUtil.addRows("rsTables",data,cellfu);
			var tbody = document.getElementById("rsTables");
			for (var i=0; i<tbody.rows.length; i++) {
				tbody.rows[i].onclick = function () {
					if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
						curr_row.style.backgroundColor = obj_bgc;
					}
					curr_row = this;
					obj_bgc = curr_row.style.backgroundColor;
					curr_row.style.backgroundColor = cur_bgc;
					displayDe();
				}
			}
		}
		document.getElementById("DetailInfo").style.display="none";
		
	}else{
		showMsgin("有错误出现：远程数据读取失败！");
	}
}

//显示学生德育考评详细信息
function TjStuDykpDeInfo(data){
	var cellfu =[
		function(data){return data.ssbh;},
		function(data){return data.nr;},
		function(data){return data.qk;},
		function(data){return data.jjf}
	];
	if (data != null && typeof data == 'object') {
		if ($("rsTables1").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables1");
			DWRUtil.addRows("rsTables1",data,cellfu);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//显示查询用户信息
function TjQuUserInfo(data){
	if (data != null && typeof data == 'object') {
		var objId = window.dialogArguments.document.getElementById("topGroup");
		if(objId && objId.tagName.toLowerCase() == "select"){
			window.dialogArguments.DWRUtil.removeAllOptions("topGroup");
			window.dialogArguments.DWRUtil.addOptions("topGroup",data,"yhm","xm");
			window.close();			
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//显示用户权限
function TjUserPower(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("groupPower");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("groupPower");
			DWRUtil.addOptions("groupPower",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//显示未分配给用户的权限
function TjUnAllotPower(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("powerSub");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("powerSub");
			DWRUtil.addOptions("powerSub",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//显示未分配给用户的子功能
function TjUnAllotSubList(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("powerTop");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("powerTop");
			DWRUtil.addOptions("powerTop",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

//显示未分配给用户组的子功能
function TjUnAllotPowerOfG(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("powerTop");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("powerTop");
			DWRUtil.addOptions("powerTop",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}



//获取楼层列表
function getLcList(){
//	if(document.getElementById("xxdm").value == "12872"){
		var lddm = document.getElementById("lddm").value;
		var userName = "";
		if($("userName")){
		   userName = $("userName").value;
		}		
		GetListData.GetLcList(lddm,userName,function initTjList(data){
	       if (data != null && typeof data == 'object') {
				var objId = "lc";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}	
	    });
//	}else{
//		return false;
//	}
}

//加了楼层号判断
function getQshList2(){
	var lddm = document.getElementById("lddm").value;
	var lch = document.getElementById("lc").value;
	var userName = "";
		if($("userName")){
		   userName = $("userName").value;
		}
	GetListData.GetQshList2(lddm,lch,userName,function initTjList(data){
	       if (data != null && typeof data == 'object') {
				var objId = "qsh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}	
	});
}
//加了楼层号判断(返回保留寝室列表)
function GetBlQshList(){
	var lddm = document.getElementById("lddm").value;
	var lch = document.getElementById("lc").value;
	
	GetListData.GetBlQshList(lddm,lch,function initTjList(data){
	       if (data != null && typeof data == 'object') {
				var objId = "qsh";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}	
	});
}
//合作教育（单位）分数录入。获得分值和描述
function getHzdwscoreinfo(){
   var hzdwlevel = document.getElementById("hzdwlevel").value;

   getScoreinfo.getHzdwscore(hzdwlevel,function findother(data){
           if(data!=null){
          var col = ["hzdwscore","hzdwlevel","hzdwdescribe"];
	      for(i=0; i<col.length; i++){
		     document.getElementById(col[i]).value = data[i];
	      }
	      document.getElementById("hzdwscore2").innerText = data[0];
	      return true;
	      }else{
	        document.getElementById("hzdwscore").value = "";
	        document.getElementById("hzdwdescribe").value = "";
	        return false;
	      }
  });
}

//合作教育（协调员）分数录入。获得分值和描述
function getXtyscoreinfo(){
   var xtylevel = document.getElementById("xtylevel").value;
    
   getScoreinfo.getXtyscore(xtylevel,function findother(data){
          if(data!=null){
          var col = ["xtyscore","xtylevel","xtydescribe"];
	      for(i=0; i<col.length; i++){
		     document.getElementById(col[i]).value = data[i];
	      }
	      document.getElementById("xtyscore2").innerText = data[0];
	       return true;
	       }else{
	         document.getElementById("xtyscore").value = "";
	         document.getElementById("xtydescribe").value = "";
	        return false;
	       }
  });
}
//合作教育（业务报告）分数录入。获得分值和描述
function getYwbgscoreinfo(){
   var ywbglevel = document.getElementById("ywbglevel").value;

   getScoreinfo.getYwbgscore(ywbglevel,function findother(data){
         if(data!=null&&typeof data == 'object'){
          	var col = ["ywbgscore","ywbglevel","ywbgdescribe"];
	      	for(i=0; i<col.length; i++){
		     	document.getElementById(col[i]).value = data[i];
	        }
	        document.getElementById("ywbgscore2").innerText = data[0];
	        return true;
	      }else{
	         document.getElementById("ywbgscore").value = "";
	         document.getElementById("ywbgdescribe").value = "";
	        return false;
	      }
  });
}
//获得综合评价成绩分数和等级
function getAllscoreinfo(){
    var hzdwlevel = document.getElementById("hzdwlevel").value;
    var xtylevel = document.getElementById("xtylevel").value;
    var ywbglevel = document.getElementById("ywbglevel").value;
    
    getScoreinfo.getAllscore(hzdwlevel,xtylevel,ywbglevel,function finaall(data){
    
    
           document.getElementById("allscore").value = data[0];
           document.getElementById("allscore2").innerText =data[0];
           document.getElementById("alllevel").innerText = data[1];
           document.getElementById("alllevel2").innerText = data[1];
          
           return true;

    });

}


//获得两个日期的相差天数
function gethzjyTowdays(){

          var hzjykssj = document.getElementById("hzjykssj").value;
          var hzjyjssj = document.getElementById("hzjyjssj").value;
                  
          getTowdays.getDaysOfTowDate(hzjykssj,hzjyjssj,function finaresult(data){
             if(hzjykssj==""&&hzjyjssj==""){
             
              document.getElementById("towdays2").innerText = "参数未设置";
              return true;
             }
             if(data=="参数出错！"){
                      document.getElementById("towdays").value = data;
                      document.getElementById("towdays2").innerText = data;
                    }else{
                      document.getElementById("towdays2").innerText = "共"+data+"天";
                    }
          return true;
          });
}

//获得设置好的两个时间段
function getSzTowdays(){
          var xydm = document.getElementById("xydm").value;
          getScoreinfo.getSjd(xydm,function finaresult(data){
                    document.getElementById("hzjykssj").value = data[0];
                    document.getElementById("hzjyjssj").value = data[1];
          return true;
          });
}




//学生资助 广东女子职业技术学院 合同信息
function getGdnzHtXxxx(){
   var hth = document.getElementById("hth").value;
    
   getGdnzHdHtxx.getGdnzHtxx(hth,function findother(data){
          if(data!=null){
          var col = ["hth","htje","htzje","dkqx","zfxjtrq","nll","hkzhlx","hkzhhm","hksj","gzdwmc","gzdwdz","gzdwyb","dwdh"];
	      for(i=0; i<col.length; i++){
		     document.getElementById(col[i]).value = data[i];
	      }
	       return true;
	       }else{
	         document.getElementById("htje").value = "";
	         document.getElementById("dkqx").value = "";
	         document.getElementById("zfxjtrq").value = "";
	         document.getElementById("nll").value = "";
	         document.getElementById("hkzhlx").value = "";
	         document.getElementById("hkzhhm").value = "";
	         document.getElementById("hksj").value = "";
	        return false;
	       }
  });
}

function getFdyList(){
	var bmdm = "";
	var query = "";
	if($("bmdm")){bmdm = $("bmdm").value};
		if(bmdm == null || bmdm == ""){
			bmdm = "%";
		} else{
			bmdm = bmdm;
		}
		query = bmdm;
		GetFdyList.getFdyList(query,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = data[0].dm;
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					$(objId).options[0].value = "";
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
							}
						}
					}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

//获取部门名称列表
function getBmmcList(){
	var bmmc = "";
	var query = "";
	if($("bmmc")){bmmc = $("bmmc").value};
		if(bmmc == null || bmmc == ""){
			bmmc = "%";
		} else{
			bmmc = bmmc;
		}
		query = bmmc;
		GetBmList.getBmList(query,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = "bmmcChoose";
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"mc","mc");			
					$(objId).options[0].value = "";
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
						}
						}
					}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
}

//就业网 隐藏操作菜单
function setdisplay(){
   
       var admingl = $("admingl").style.display;
       var admingl2 = $("admingl2").style.display;
       
       if(admingl=="none"){
       $("admingl").style.display="";
       }
       
       if(admingl==""){
       $("admingl").style.display="none";
       }
       
       if(admingl2=="none"){
       $("admingl2").style.display="";
       }
       
       if(admingl2==""){
       $("admingl2").style.display="none";
       }
}
//就业网 隐藏操作菜单2
function setdisplay2(){
   
       var admingl2 = $("admingl2").style.display;
       
       if(admingl2=="none"){
       $("admingl2").style.display="";
       }
       
       if(admingl2==""){
       $("admingl2").style.display="none";
       }
       
}

function TjRzXsXx(data){
    if(data!=null){
    dataTem = new Array(1) ;
    dataTem[0]=data[1];
	var cellfu =[
	    function(dataTem) {return "<input type='hidden' id='xhV' name='xhV' value='"+data[0]+"'/>该床位已入住";},
		function(dataTem){return "入住人"+dataTem;},
		function(dataTem) {
        return "<input type='checkbox' id='sfjh' name='sfjh' value='yes' /> 是否交换床位";
        }];
	if (dataTem != null && typeof dataTem == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");			
			DWRUtil.addRows("rsTables",dataTem,cellfu,{escapeHtml:false});			
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
	}else{
	   if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");	
	   }		
	}
}

function TjRzSsXsXx(data){
	var cellfu =[
	    function(data) {return  data;}					
	 ];
	if (data != null && typeof data == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");		
			DWRUtil.addRows("rsTables",data,cellfu,{escapeHtml:false});			
			if($("rsNum")){
			   $("rsNum").innerText = data.length;
			}
			if($("ldqs")){
			  var ldmc=($("ldmc"))?$("ldmc").value:document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
			  $("ldqs").innerText = ldmc+$("qsh").value;
			}			
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

function getRzQsXsXx(){
	var lddm = $("lddm").value;
	var qsh  = $("qsh").value;
	var cwh  = $("cwh").value;
//	if($("doType").value=='add'){//只有增加时才能进行交换
	GetListData.GetRzXsXx(lddm,qsh,cwh,TjRzXsXx);
//	}
}

function getRzXsXx(){
	var ssbh = "";
	if($("ssbh")){
	  ssbh = $("ssbh").value;
	}else{
	  ssbh = $("lddm").value+"-"+$("qsh").value;
	}
	GetListData.GetQsRzXsXx(ssbh,TjRzSsXsXx);
	
}

//云南艺术 跟据所选年度性别得到连队列表
function getLdjzList(){
	var nd = document.getElementById("nd").value;
	var xb = document.getElementById("xb").value;
	getJxLdjzList.getLdList(nd,xb,function initTjList(data){
			if (data != null && typeof data == 'object') {
				var objId = data[0].dm;
				if($(objId) && $(objId).tagName.toLowerCase() == "select"){
					DWRUtil.removeAllOptions(objId);			
					DWRUtil.addOptions(objId,data,"dm","mc");			
					$(objId).options[0].value = "";
					if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value != null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
								$(objId).options[i].selected = true;
								return true;
}
						}
					}
					}
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		})
}

//浙江传媒军训成绩比例
function getJxcjbl(){
	if($("xlcjbl")&&$("kscjbl")){
	getJxLdjzList.getCjbl(function(data){
			if(data!=null){
				document.getElementById("xlcjbl").value=data[0];
				document.getElementById("kscjbl").value=data[1];			
			}
		});
	}
}

/**根据id=xy来查询辅导员的信息列表*/
function initFdyList(){
	var bmdm = "";
	if($("xy")){bmdm = $("xy").value};
	if(bmdm == null || bmdm == ""){
		bmdm = "%";
	}
	GetFdyList.getFdyList(bmdm,initTjList);
}


function kshTaoPrint() {
	var boxes = document.getElementsByName("checkVal");
	var modiZxdm = new Array();
	for(var i = 0; i < boxes.length; i++){
		if(boxes[i].checked){
			modiZxdm[modiZxdm.length] = boxes[i].value;
		}
	}
	return modiZxdm.join(",");
}

function expData(url){
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
}

/*
	查询条件重置
	author 屈朋辉
	date   2010-7-30
*/

function searchReset() {

	var input = document.getElementsByTagName('input');
	var select = document.getElementsByTagName('select');
				
	for (var i = 0;i<input.length;i++) {
		if (input[i].type != 'hidden' && input[i].disabled != true 
			&& input[i].type != 'checkbox' 
			&& (!input[i].readOnly||input[i].className=="jssj")
			&& input[i].id !="pageno"
			&& input[i].id !="pagesize")
			input[i].value='';
	}
	for (var i = 0;i<select.length;i++) {
		if (select[i].disabled != true && select[i].options.length > 0 && select[i].options[0].value == "")
		select[i].value='';
	}
}

/**
 * 加载政治面貌
 * @param zzmmId
 * */
function initZzmmList(zzmmId){
	GetListData.getZZmmList(function(data){
		if (data != null && typeof data == 'object') {
			var objId = zzmmId;	
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
				$(objId).options[0].value = "";
				if($(objId + "V")){
				if(objId + "V"){
					
				if($(objId +"V").value != "" && $(objId + "V").value != null){
					for(var i = 0;i < $(objId).options.length; i++){
						if($(objId).options[i].value == $(objId +"V").value){
							$(objId).options[i].selected = true;
							return true;
						}
					}
				}
			  }}
			}
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
	});
}
