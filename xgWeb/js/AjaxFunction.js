//��ȡѧԺרҵ����
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

//�ύѧ��ס�޷ֲ�
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ȡѧ��ס�޷ֲ�
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
			document.forms[0].bj.options[0] = new Option("--��ѡ��--",""); //initial
		}	
	}
}

function initOnLoadZyList(){
	if($("xy") && ($("xy").value == "" || $("xy").value == null)){
		var objId = "zy";
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);
			document.forms[0].zy.options[0] = new Option("--��ѡ��--",""); //initial
		}
		objId = "bj";
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions(objId);
			document.forms[0].bj.options[0] = new Option("--��ѡ��--",""); //initial
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
			showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
					if($("ysbjdm")){//ԭʼ�༶
						DWRUtil.removeAllOptions("ysbjdm");
						DWRUtil.addOptions("ysbjdm",data,"dm","mc");
						$("ysbjdm").options[0].value = "";
					}
					if($("mkbjdm")){//ģ��༶
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
					if($("ysbjdm")){//ԭʼ�༶
						DWRUtil.removeAllOptions("ysbjdm");
						DWRUtil.addOptions("ysbjdm",data,"dm","mc");
						$("ysbjdm").options[0].value = "";
					}
					if($("mkbjdm")){//ģ��༶
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
}

//����ְҵ����ѧԺ�༶�ֱ�ҵ�ĺ���У��
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
				//showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
					//showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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

//�������������ҳ�浱ѡ��ȼ����Զ���ʾ����
function GetFs(){
	var dj = document.getElementById("dj").value;
	GetListData.getFs(dj,function showFs(str){
		$("fs").value = str;
	} );
}
//�������������ҳ�浱ѡ��ȼ����Զ���ʾ����
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
					DWRUtil.addOptions(objId,[{dm:'',mc:'--��ѡ��--'}],"dm","mc");
					DWRUtil.addOptions(objId,data,"dm","mc");
				}
			}else{
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
		showMsgin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʾѧ������������ϸ��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʾ��ѯ�û���Ϣ
function TjQuUserInfo(data){
	if (data != null && typeof data == 'object') {
		var objId = window.dialogArguments.document.getElementById("topGroup");
		if(objId && objId.tagName.toLowerCase() == "select"){
			window.dialogArguments.DWRUtil.removeAllOptions("topGroup");
			window.dialogArguments.DWRUtil.addOptions("topGroup",data,"yhm","xm");
			window.close();			
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʾ�û�Ȩ��
function TjUserPower(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("groupPower");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("groupPower");
			DWRUtil.addOptions("groupPower",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʾδ������û���Ȩ��
function TjUnAllotPower(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("powerSub");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("powerSub");
			DWRUtil.addOptions("powerSub",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʾδ������û����ӹ���
function TjUnAllotSubList(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("powerTop");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("powerTop");
			DWRUtil.addOptions("powerTop",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

//��ʾδ������û�����ӹ���
function TjUnAllotPowerOfG(data){
	if (data != null && typeof data == 'object') {
		var objId = document.getElementById("powerTop");
		if(objId && objId.tagName.toLowerCase() == "select"){
			DWRUtil.removeAllOptions("powerTop");
			DWRUtil.addOptions("powerTop",data,"gnmkdm","gnmkmc");		
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}



//��ȡ¥���б�
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}	
	    });
//	}else{
//		return false;
//	}
}

//����¥����ж�
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}	
	});
}
//����¥����ж�(���ر��������б�)
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}	
	});
}
//������������λ������¼�롣��÷�ֵ������
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

//����������Э��Ա������¼�롣��÷�ֵ������
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
//����������ҵ�񱨸棩����¼�롣��÷�ֵ������
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
//����ۺ����۳ɼ������͵ȼ�
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


//����������ڵ��������
function gethzjyTowdays(){

          var hzjykssj = document.getElementById("hzjykssj").value;
          var hzjyjssj = document.getElementById("hzjyjssj").value;
                  
          getTowdays.getDaysOfTowDate(hzjykssj,hzjyjssj,function finaresult(data){
             if(hzjykssj==""&&hzjyjssj==""){
             
              document.getElementById("towdays2").innerText = "����δ����";
              return true;
             }
             if(data=="��������"){
                      document.getElementById("towdays").value = data;
                      document.getElementById("towdays2").innerText = data;
                    }else{
                      document.getElementById("towdays2").innerText = "��"+data+"��";
                    }
          return true;
          });
}

//������úõ�����ʱ���
function getSzTowdays(){
          var xydm = document.getElementById("xydm").value;
          getScoreinfo.getSjd(xydm,function finaresult(data){
                    document.getElementById("hzjykssj").value = data[0];
                    document.getElementById("hzjyjssj").value = data[1];
          return true;
          });
}




//ѧ������ �㶫Ů��ְҵ����ѧԺ ��ͬ��Ϣ
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
}

//��ȡ���������б�
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		});
}

//��ҵ�� ���ز����˵�
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
//��ҵ�� ���ز����˵�2
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
	    function(dataTem) {return "<input type='hidden' id='xhV' name='xhV' value='"+data[0]+"'/>�ô�λ����ס";},
		function(dataTem){return "��ס��"+dataTem;},
		function(dataTem) {
        return "<input type='checkbox' id='sfjh' name='sfjh' value='yes' /> �Ƿ񽻻���λ";
        }];
	if (dataTem != null && typeof dataTem == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");			
			DWRUtil.addRows("rsTables",dataTem,cellfu,{escapeHtml:false});			
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

function getRzQsXsXx(){
	var lddm = $("lddm").value;
	var qsh  = $("qsh").value;
	var cwh  = $("cwh").value;
//	if($("doType").value=='add'){//ֻ������ʱ���ܽ��н���
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

//�������� ������ѡ����Ա�õ������б�
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
				showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
			}
		})
}

//�㽭��ý��ѵ�ɼ�����
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

/**����id=xy����ѯ����Ա����Ϣ�б�*/
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
	��ѯ��������
	author �����
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
 * ����������ò
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
			showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
		}
	});
}
