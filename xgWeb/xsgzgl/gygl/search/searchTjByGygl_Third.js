//======================点击事件===========================

//点击校区-----------------------------未修改-----------------------------
function clickXq_Third(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//清空园区已选条件
	removeYxtjByLd("yqdm");
	//清空楼栋已选条件
	removeYxtjByLd("lddm");
	//清空层数已选条件
	removeYxtjByLd("cs");
	//清空寝室号已选条件
	removeYxtjByLd("qsh");
	
	//存在园区条件
	if($("yqdm_ul")){
		//联动园区
		setNewYq();
	}
	
	//存在楼栋条件
	if($("lddm_ul")){
		//联动楼栋
		setNewLd();
	}
	
	//存在层数条件
	if($("cs_ul")){
		//联动层数
		setNewCs();
	}
	
	//楼栋
	var ld_click = getClickLd();
	//是否点击楼栋
	var flag = false;
	if(ld_click.length > 0 ){
		flag = true;
	}

	//存在寝室条件
	if($("qsh_ul")){
		if(flag){
			$("qsh_dl").style.display = "";
			//联动寝室
			setNewQsh();
			//清空寝室其他DIV
			removeQshDiv_Third();
		}else{
			$("qsh_dl").style.display = "none";
		}
	}
	
}

//点击园区-----------------------------未修改-----------------------------
function clickYq_Third(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}

	//清空楼栋已选条件
	removeYxtjByLd("lddm");
	//清空层数已选条件
	removeYxtjByLd("cs");
	//清空寝室号已选条件
	removeYxtjByLd("qsh");
	
	//存在楼栋条件
	if($("lddm_ul")){
		//联动楼栋
		setNewLd();
	}
	
	//存在层数条件
	if($("cs_ul")){
		//联动楼栋
		setNewCs();
	}
	
	//楼栋
	var ld_click = getClickLd();
	//是否点击楼栋
	var flag = false;
	if(ld_click.length > 0 ){
		flag = true;
	}

	//存在寝室条件
	if($("qsh_ul")){
		if(flag){
			$("qsh_dl").style.display = "";
			//联动寝室
			setNewQsh();
			//清空寝室其他DIV
			removeQshDiv_Third();
		}else{
			$("qsh_dl").style.display = "none";
		}
	}

}

//点击楼栋-----------------------------已修改-----------------------------
function clickLd_Third(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}

	//清空层数已选条件
	removeYxtjByLd("ch");
	//清空寝室号已选条件
	removeYxtjByLd("qsh");
		
	//存在层号条件
	if($("ch_ul")){
		//联动层号
		setNewCh_Third();
	}
	
	//楼栋
	var ld_click = getClickLd_Third();
	//是否点击楼栋
	var flag = false;
	if(ld_click.length > 0 ){
		flag = true;
	}

	//存在寝室条件
	if($("qsh_ul")){
		if(flag){
			$("qsh_dl").style.display = "";
			//联动寝室
			setNewQsh_Third();
			//清空寝室其他DIV
			removeQshDiv_Third();
		}else{
			$("qsh_dl").style.display = "none";
		}
	}
	
}

//点击层号-----------------------------已修改-----------------------------
function clickCh_Third(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//清空寝室号已选条件
	removeYxtjByLd("qsh");
	
	//楼栋
	var ld_click = getClickLd_Third();
	//是否点击楼栋
	var flag = false;
	if(ld_click.length > 0 ){
		flag = true;
	}

	//存在寝室条件
	if($("qsh_ul")){
		if(flag){
			$("qsh_dl").style.display = "";
			//联动寝室
			setNewQsh_Third();
			//清空寝室其他DIV
			removeQshDiv_Third();
		}else{
			$("qsh_dl").style.display = "none";
		}
	}

}

//点击寝室号-----------------------------已修改-----------------------------
function clickQsh_Third(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}

}

//点击寝室号其他-----------------------------已修改-----------------------------
function clickQshQt_Third(qshpy){
	var divid = "div_qsh_"+qshpy;
	var qtid = "div_qt_qsh_"+qshpy;
	var hiid = "hi_"+qshpy;
	
	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	var divHtml ="<div class='search_advanced'><div class='prop-item'>";
		divHtml+="<table width='80%' border='0' class='formlist open01'>";
		divHtml+="<tbody><tr><td colspan='4'>";
		divHtml+="<font color='orange' size='3'>"+qshpy+"字打头寝室</font>";
		divHtml+="</td></tr>";
		
		divHtml+="<tr><td colspan='4'>";
		divHtml+="<div style=\"width:100%;height:260;overflow-x:hidden;overflow-y:auto;\"><table style=\"width:100%\">";
		
		//构造的tr数量
		var tr_num = "6";
		
		dwr.engine.setAsync(false);
				
		//校区
		var xq_click = getClickTj("xqdm");
		//园区
		var yq_click = getClickTj("yqdm");
		//楼栋
		var ld_click = getClickLd_Third();
		//层号
		var ch_click = getClickTj("ch");
		
		searchUtil.getQsInfoByPy_Third(qshpy,xq_click,yq_click,ld_click,ch_click,userStatus,userName,userDep,function(data){

			if(data !=null && data.length >0){
				
				var size = data.length;
				
				//数据量过大，以实际数据为准
				if((size/4) > tr_num){
					tr_num = parseInt(size/4);
					
					//最后一行非满格的数量
					var sy_num = parseInt(size%4);
					if(sy_num != "0"){
						tr_num =parseInt(tr_num)+1;
					}
				}
				
				var n = size;
				for(var i=0;i<tr_num;i++){
					
					divHtml+="<tr>";
					
					for(var j=0;j<4;j++){
					
						if(n <= size && n >= 0){
						
							n--;
							
							if(n!=-1){
							
								var qshid = "a_qsh_"+data[n].qshdm;
								var xsid = "qsh_mc_xs_"+data[n].qshdm;
								var ycid = "qsh_mc_yc_"+data[n].qshdm;
							
								divHtml+="<td width='25%'>";
								if(!$(xsid) && !$(ycid)){
									
									var qshmc = data[n].qshmc;

									if(qshmc.length > 6){
										qshmc = qshmc.substring(0,6)+"...";
									}
									divHtml+="<li><a href='#' class='bg_none' name='a_qsh_mc'id=\""+ycid+"\" style=\"\"";
									divHtml+="title=\""+data[n].qshmc+"\"";
									divHtml+="onclick=\"clickQsh_Third(this);";
									divHtml+="creatClickedTj_Third('qsh','寝室','"+data[n].qshdm+"','"+data[n].qshmc+"',this);return false;\"";
									divHtml+=">"+qshmc+"</a></li>";
								}else{
									divHtml+="&nbsp;";
								}
								divHtml+="</td>";
							
							}else{
								divHtml+="<td width='25%'>&nbsp;</td>";
							}	
						}else{
							divHtml+="<td width='25%'>&nbsp;</td>";
						}
					}
					
					divHtml+="</tr>";
				}
			}
		});
				
		dwr.engine.setAsync(true);
			
		divHtml+="</table></div>";
		divHtml+="</td></tr>";
		divHtml+="</tbody>";
		divHtml+="<tfoot><tr><td colspan='4'><div class='btn'>";
		divHtml+="<button type=\"button\" onclick=\"hiddenZyDiv('"+divid+"');return false;\" id='buttonSave'>确定</button>";
		divHtml+="</div></td></tr></tfoot>";
		divHtml+="</table></div></div>";
		
	createOtherDiv("寝室选择",divHtml,divid,"600","380");

}
//======================点击事件 end===========================

//======================联动操作===========================

//生成新的园区-----------------------------未修改-----------------------------
function setNewYq_Third(){
	
	$("yqdm_ul").innerHTML = "";
	
	//校区
	var xq_click = getClickTj("xqdm");
	
	dwr.engine.setAsync(false);
	
	var divHtml = "<ul id=\"yqdm_ul\">";

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;

	//根据校区取得园区
	searchUtil.getGyglInfo("yq",xq_click,null,null,null,gyglyQx,userName,userDep,function(data){
		if(data !=null && data.length >0){
		
			for(var i=0;i<data.length;i++){
				divHtml += "<li>";
				divHtml += "<a href=\"#\" class=\"\"";
				divHtml += " onclick=\"clickYq_Third(this);";
				divHtml += "creatClickedTj_Third('yqdm','园区','"+data[i].yqdm+"','"+data[i].yqmc+"',this);return false;\"";
				divHtml += " id=\"tj_yqdm_"+data[i].yqdm+"\" name=\"tj_yqdm\">";
				divHtml += data[i].yqmc;
				divHtml += "</a></li>";
			}
		}
	});

	divHtml += "</ul>";

	$("yqdm_ul").outerHTML = divHtml;

	dwr.engine.setAsync(true);

}

//生成新的楼栋-----------------------------已修改-----------------------------
function setNewLd_Third(){
	
	$("lddm_ul").innerHTML = "";
	
	//校区
	var xq_click = getClickTj("xqdm");
	//园区
	var yq_click = getClickTj("yqdm");
	
	dwr.engine.setAsync(false);
	
	var divHtml = "<ul id=\"lddm_ul\">";

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;

	//根据校区,园区,取得楼栋信息
	searchUtil.getGyglInfo_Third("ld",xq_click,yq_click,null,null,gyglyQx,userName,userDep,function(data){
		if(data !=null && data.length >0){

			for(var i=0;i<data.length;i++){
			
				if(i<8){
					divHtml += "<li>";
					divHtml += "<a href=\"#\" class=\"\"";
					divHtml += " onclick=\"clickLd_Third(this);";
					divHtml += "creatClickedTj_Third('lddm','楼栋','"+data[i].lddm+"','"+data[i].ldmc+"',this);return false;\"";
					divHtml += " id=\"lddm_mc_xs_"+data[i].lddm+"\" name=\"a_lddm_mc\">";
					divHtml += data[i].ldmc;
					divHtml += "</a></li>";
				}else{
					divHtml += "<li>";
					divHtml += "<a href=\"#\" class=\"\" style=\"display: none;\"";
					divHtml += " onclick=\"clickLd_Third(this);";
					divHtml += "creatClickedTj_Third('lddm','楼栋','"+data[i].lddm+"','"+data[i].ldmc+"',this);return false;\"";
					divHtml += " id=\"lddm_mc_yc_"+data[i].lddm+"\" name=\"a_lddm_mc\">";
					divHtml += data[i].ldmc;
					divHtml += "</a></li>";
				}
			}
			
			if(data.length < 8){
				if($("ld_more")){
					$("ld_more").style.display = "none";
				}
			}else{
				$("ld_more").style.display = "";
				$("ld_more").innerHTML = "更多";
				$("ld_more").className = "more_down";
			}
		}
	});

	divHtml += "</ul>";

	$("lddm_ul").outerHTML = divHtml;	

	dwr.engine.setAsync(true);

}

//生成新的层号-----------------------------已修改-----------------------------
function setNewCh_Third(){
	
	$("ch_ul").innerHTML = "";
	
	//校区
	var xq_click = getClickTj("xqdm");
	//园区
	var yq_click = getClickTj("yqdm");
	//楼栋
	var ld_click = getClickLd_Third();
	
	dwr.engine.setAsync(false);
	
	var divHtml = "<ul id=\"ch_ul\">";
	
	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;

	//根据校区,园区，楼栋，取得层数
	searchUtil.getGyglInfo_Third("ch",xq_click,yq_click,ld_click,null,gyglyQx,userName,userDep,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<data.length;i++){
				divHtml += "<li>";
				divHtml += "<a href=\"#\" class=\"\"";
				divHtml += " onclick=\"clickCh_Third(this);";
				divHtml += "creatClickedTj_Third('ch','层号','"+data[i].ch+"','"+data[i].chmc+"',this);return false;\"";
				divHtml += " id=\"tj_ch_"+data[i].ch+"\" name=\"tj_ch\">";
				divHtml += data[i].chmc;
				divHtml += "</a></li>";
			}
		}
	});

	divHtml += "</ul>";
	$("ch_ul").outerHTML = divHtml;	

	dwr.engine.setAsync(true);

}

//生成新的寝室-----------------------------已修改-----------------------------
function setNewQsh_Third(){

	//校区
	var xq_click = getClickTj("xqdm");
	//园区
	var yq_click = getClickTj("yqdm");
	//楼栋
	var ld_click = getClickLd_Third();
	//层号
	var ch_click = getClickTj("ch");
	
	$("qsh_ul").innerHTML = "";
	
	var noTj = false;

	//判断是否有相关联的条件被选中
	if(xq_click.length == 0 && yq_click.length == 0 && ld_click.length == 0 && ch_click.length == 0){
		noTj = true;
	}
	
	dwr.engine.setAsync(false);
	
	var divHtml = "<ul id=\"qsh_ul\">";

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	//根据校区,园区，楼栋，层数取得寝室
	searchUtil.getGyglInfo_Third("qsh",xq_click,yq_click,ld_click,ch_click,gyglyQx,userName,userDep,function(data){
	
		if(data !=null && data.length >0){
		
			var qsh_count = 0;
			var qsh_py_num = 0;
			//循环拼音
			for(var i=0;i<PY_BIG.length;i++){
			
				var	flag = true;
				over_flag = false;
				var qt_flag = true;
				var qt_num = 0;
				
				//寝室内容列表
				for(var j=0;j<data.length;j++){
					
					if(PY_BIG[i] == data[j].qshpy){
					
						var qi_id = "qsh_qt_qsh_"+PY_BIG[i];

						qsh_py_num++;
						
							if(qsh_count < 12){
								
								if(flag){
								
									divHtml += "<li>";
									
									divHtml += "<h5 id=\"qsh_py_xs_";
									divHtml += qsh_py_num
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;								
									flag = false;
								}
							
								if(qt_flag){
									divHtml += " <a href=\"#\" class=\"\" name=\"a_qsh_mc\" ";
									divHtml += " id=\"qsh_mc_xs_";
									divHtml += data[j].qshdm
									divHtml += "\"";
									divHtml += "onclick=\"clickQsh_Third(this);";
									divHtml += "creatClickedTj_Third('qsh','寝室','"+data[j].qshdm+"','"+data[j].qshmc+"',this);return false;\">";
									divHtml += data[j].qshmc;
									divHtml += "</a>";
																				
									qt_num++;
									
									//其他
									if(qt_num == 3){
										
										qsh_count++;
										qsh_count++;
										
										divHtml += "<a href=\"#\" class=\"moreValue_click\" onclick=\"clickQshQt_Third(\'"
										divHtml += data[j].qshpy
										divHtml += "\')\"";
										divHtml += "id=\"";
										divHtml += qi_id;
										divHtml += "\">更多</a>";

										qt_num = 0;
										
										qt_flag = false;
															
									}
								}														
							}else{
								if(flag){
								
									divHtml += "<li>";
									
									divHtml += "<h5 style=\"display:none\" id=\"qsh_py_yc_";
									divHtml += qsh_py_num
									divHtml += "\">";
									divHtml += "<img src=\""+stylePath+"images/num_"+PY_BIG[i]+".gif\" /></h5>";
									//divHtml += PY_BIG[i];
									divHtml += "</h5>";
									
									over_flag = true;										
									flag = false;
								}
							
								if(qt_flag){
								
									divHtml += " <a href=\"#\" class=\"\" name=\"a_qsh_mc\" style=\"display:none;\"";
									divHtml += " id=\"qsh_mc_yc_";
									divHtml += data[j].qshdm
									divHtml += "\"";
									divHtml += "onclick=\"clickQsh_Third(this);";
									divHtml += "creatClickedTj_Third('qsh','寝室','"+data[j].qshdm+"','"+data[j].qshmc+"',this);return false;\">";
									divHtml += data[j].qshmc;
									divHtml += "</a>";			
										
									divHtml += "<input type=\"hidden\" name=\"qsh_hidv_yc\" id=\"qsh_hidv_yc_";
									divHtml += data[j].qshdm;
									divHtml += "\" value=\"";
									divHtml += data[j].qshdm;
									divHtml += "\"/>";
								
									qt_num++;
									
									//其他
									if(qt_num == 3){
										
										qsh_count++;
										qsh_count++;
										
										divHtml += "<a href=\"#\" style=\"display:none;\" class=\"moreValue_click\" onclick=\"clickQshQt_Third(\'"
										divHtml += data[j].qshpy
										divHtml += "\')\"";
										divHtml += "id=\"";
										divHtml += qi_id;
										divHtml += "\">更多</a>";														
										
										divHtml += "<input type=\"hidden\" name=\"qsh_hidv_qt\" id=\"qsh_hidv_qt_";
										divHtml += data[j].qshdm;
										divHtml += "\" value=\"qsh_";
										divHtml += data[j].qshpy;
										divHtml += "\"/>";
										
										qt_num = 0;
										
										qt_flag = false;
															
									}
								}
							}
						
						if(qt_flag){
							qsh_count++;
						}
						
					}

				}	
				
				if(over_flag){
					divHtml+="</li>";
					over_flag = false;
				}		
			}

			if(qsh_count <= 12){
				if($("qsh_more")){
					$("qsh_more").style.display = "none";
				}
			}else{
				$("qsh_more").style.display = "";
				$("qsh_more").innerHTML = "更多";
				$("qsh_more").className = "more_down";
			}
			
			divHtml += "<input type=\"hidden\" id=\"qsh_py_num\" value=\""+qsh_py_num+"\"/>";
		}
	});
			
	divHtml += "</ul>";
	$("qsh_ul").outerHTML = divHtml;	
	
	dwr.engine.setAsync(true);
	
}

//======================联动操作 end===========================

//======================其他操作 end===========================
//获得选中的楼栋-----------------------------已修改-----------------------------
function getClickLd_Third(){

	//楼栋
	var lddm_num = document.getElementsByName("a_lddm_mc").length;
	var lddm_arr = new Array();
	var lddm_count = 0;

	for(var i=0;i<lddm_num;i++){
		var obj = document.getElementsByName("a_lddm_mc")[i];
		if(obj.className == "selectedValue"){
			var lddm_v = obj.id.split("_")[3];
			lddm_arr[lddm_count] = lddm_v;
			lddm_count++;
		}
	}
	
	return lddm_arr;
}

//清空寝室其他层-----------------------------已修改-----------------------------
function removeQshDiv_Third(){
	//循环拼音
	for(var i=0;i<PY_BIG.length;i++){
		var id = "div_qsh_"+PY_BIG[i];
		if($(id)){
			//清空已选条件
			var a_num = $(id).getElementsByTagName('a').length;
			for(var j=0;j<a_num;j++){
				var obj = $(id).getElementsByTagName('a')[j];
				if(obj.name == "a_qsh_mc"){
					var a_id = obj.id;
					var qshdm = a_id.split("_")[3];
					var dd_id = qshdm + "_qsh_yxtj_dd";
					
					if($(dd_id)){
						$(dd_id).outerHTML = "";
					}
				}
			}
			//清空层
			$(id).outerHTML = "";
		}
	}
}
//======================联动操作 end===========================



//======================基础操作 生成/删除选择条件 start===========================
//创建已选中的条件
function creatClickedTjBySearch_Third(){

	var input_num = $("searchTjDiv").getElementsByTagName('input').length;

	if(input_num != 0){
		
		for(var i=0;i<input_num;i++){
			var obj = $("searchTjDiv").getElementsByTagName('input')[i];
			var tjdm = obj.title;
			var dm = obj.value;
			
			if(tjdm != ""){
				//控件ID
				var a_id = "tj_" + tjdm+"_" + dm;
				var xs_id = tjdm+"_mc_xs_" + dm;
				var yc_id = tjdm+"_mc_yc_" + dm;
				
				if($(a_id)){
					$(a_id).onclick();
				}else if($(xs_id)){
					$(xs_id).onclick();
				}else if($(yc_id)){
					$(yc_id).onclick();
				}else{
					dwr.engine.setAsync(false);
					
					if(tjdm == "qsh"){//寝室号
					
						var bmpy = dm.substring(0,1);
						var divid = "div_"+tjdm+"_"+bmpy;
						
						clickQshQt_Third(bmpy);
						
						hiddenZyDiv(divid);
						if($(yc_id)){
							$(yc_id).onclick();
						}
					}
					
					dwr.engine.setAsync(true);
				}
			}
		}
	}
}

//创建已选中的条件
function creatClickedTj_Third(tjdm,tjmc,dm,mc,obj){

	$("yxtj_div").style.display = "";
	
	var className = obj.className;
	var id = dm + "_" + tjdm + "_yxtj_dd";

	var dlHtml = $("yxtj_dl").innerHTML;

	if(className == "selectedValue"){//点击查询
		dlHtml+= "<dd id=\""+id+"\">";
		dlHtml+= "<a href=\"#\" onclick=\"removeYxtj_Third('"+tjdm+"','"+dm+"');return false;\">";
		dlHtml+= "<h5>"+tjmc+"</h5>";
		dlHtml+= mc;
		dlHtml+= "<span class=\"close-icon\" title=\"取消\"></span></a></dd>";
		$("yxtj_dl").innerHTML = dlHtml;
	}else{
		$("yxtj_dl").innerHTML = dlHtml;
		$(id).outerHTML = "";
	}
	
	//判断是否要隐藏已选条件DIV
	hiddenYxtjDiv();
}

//移除已选条件
function removeYxtj_Third(tjdm,dm){

	var dd_id = dm + "_" + tjdm + "_yxtj_dd";
	var a_id = "tj_" + tjdm+"_" + dm;
	var xs_id = tjdm+"_mc_xs_" + dm;
	var yc_id = tjdm+"_mc_yc_" + dm;

	if($(dd_id)){
		$(dd_id).outerHTML = "";
	}
	
	if($(a_id)){
		$(a_id).className="";
	}

	if($(xs_id)){
		$(xs_id).className="";
	}
	
	if($(yc_id)){
		$(yc_id).className="";
	}
	
	//判断是否要隐藏已选条件DIV
	hiddenYxtjDiv();
	
	if(tjdm == "lddm" || tjdm == "ch" ){
		//楼栋
		var ld_click = getClickLd_Third();
		//是否点击楼栋
		var flag = false;
		if(ld_click.length > 0 ){
			flag = true;
		}
	
		//存在寝室条件
		if($("qsh_ul")){
			if(flag){
				$("qsh_dl").style.display = "";
				//联动寝室
				setNewQsh_Third();
				//清空寝室其他DIV
				removeQshDiv_Third();
			}else{
				$("qsh_dl").style.display = "none";
			}
		}
	}
	
	if(tjdm == "lddm"){
		//存在层号条件
		if($("ch_ul")){
			//联动层号
			setNewCh_Third();
			//清空层号其他DIV
//			removeZyDiv();
		}
	}
	
}
//======================基础操作 生成/删除选择条件 end===========================