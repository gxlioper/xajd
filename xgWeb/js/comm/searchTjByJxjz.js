//======================点击事件===========================

//点击团
function clickTid(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//清空营已选条件
	removeYxtjByLd("yid");
	//清空连已选条件
	removeYxtjByLd("lid");
	//清空排已选条件
	removeYxtjByLd("pid");
	
	removeYxtjByLd("bid");
	
	removeYxtjByLd("ssid");
	
	//存在营条件
	if($("yid_ul")){
		//联动营
		createJxjzHtml("yid");
	}
	
	//存在连条件
	if($("lid_ul")){
		//联动连
		createJxjzHtml("lid");
	}
	
	//存在排条件
	if($("pid_ul")){
		//联动排
		createJxjzHtml("pid");
	}
	
	if($("bid_ul")){
		//联动班
		createJxjzHtml("bid");
	}
	
	if($("ssid_ul")){
		//联动宿舍
		createJxjzHtml("ssid");
	}

	
	/*	//楼栋
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
			removeQshDiv();
		}else{
			$("qsh_dl").style.display = "none";
		}
	}*/
	
}

//点击营
function clickYid(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//清空连已选条件
	removeYxtjByLd("lid");
	//清空排已选条件
	removeYxtjByLd("pid");
	
	removeYxtjByLd("bid");
	
	removeYxtjByLd("ssid");
	
	//存在连条件
	if($("lid_ul")){
		//联动连
		createJxjzHtml("lid");
	}
	
	//存在排条件
	if($("pid_ul")){
		//联动排
		createJxjzHtml("pid");
	}
	
	if($("bid_ul")){
		//联动班
		createJxjzHtml("bid");
	}
	
	if($("ssid_ul")){
		//联动宿舍
		createJxjzHtml("ssid");
	}

/*	//存在寝室条件
	if($("qsh_ul")){
		if(flag){
			$("qsh_dl").style.display = "";
			//联动寝室
			setNewQsh();
			//清空寝室其他DIV
			removeQshDiv();
		}else{
			$("qsh_dl").style.display = "none";
		}
	}*/

}

//点击连
function clickLid(obj){
	
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//清空排已选条件
	removeYxtjByLd("pid");
	
	removeYxtjByLd("bid");
	
	removeYxtjByLd("ssid");
	
	//存在排条件
	if($("pid_ul")){
		//联动排
		createJxjzHtml("pid");
	}
	
	if($("bid_ul")){
		//联动班
		createJxjzHtml("bid");
	}
	
	if($("ssid_ul")){
		//联动宿舍
		createJxjzHtml("ssid");
	}

//	//存在寝室条件
//	if($("qsh_ul")){
//		if($("qsh_dl")){
//			if(flag){
//				$("qsh_dl").style.display = "";
//				//联动寝室
//				setNewQsh();
//				//清空寝室其他DIV
//				removeQshDiv();
//			}else{
//				$("qsh_dl").style.display = "none";
//			}
//		}
//	}
}

//点击排
function clickPid(obj){

	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	removeYxtjByLd("bid");
	
	removeYxtjByLd("ssid");
	
	if($("bid_ul")){
		//联动班
		createJxjzHtml("bid");
	}
	
	if($("ssid_ul")){
		//联动宿舍
		createJxjzHtml("ssid");
	}
	
}


function clickBid(obj){

	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	removeYxtjByLd("ssid");
	
	if($("ssid_ul")){
		//联动宿舍
		createJxjzHtml("ssid");
	}
	
}

function clickSsid(obj){

	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
}

//======================点击事件 end===========================

//======================联动操作===========================

//生成新的园区
function setNewYq(){
	
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
				divHtml += " onclick=\"clickYq(this);";
				divHtml += "creatClickedTj('yqdm','园区','"+data[i].yqdm+"','"+data[i].yqmc+"',this);return false;\"";
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

//生成新的楼栋
function setNewLd(){
	
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
	searchUtil.getGyglInfo("ld",xq_click,yq_click,null,null,gyglyQx,userName,userDep,function(data){
		if(data !=null && data.length >0){

			for(var i=0;i<data.length;i++){
			
				if(i<8){
					divHtml += "<li>";
					divHtml += "<a href=\"#\" class=\"\"";
					divHtml += " onclick=\"clickLd(this);";
					divHtml += "creatClickedTj('lddm','楼栋','"+data[i].lddm+"','"+data[i].ldmc+"',this);return false;\"";
					divHtml += " id=\"lddm_mc_xs_"+data[i].lddm+"\" name=\"a_lddm_mc\">";
					divHtml += data[i].ldmc;
					divHtml += "</a></li>";
				}else{
					divHtml += "<li>";
					divHtml += "<a href=\"#\" class=\"\" style=\"display: none;\"";
					divHtml += " onclick=\"clickLd(this);";
					divHtml += "creatClickedTj('lddm','楼栋','"+data[i].lddm+"','"+data[i].ldmc+"',this);return false;\"";
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

//生成新的层数
function setNewCs(){
	
	$("cs_ul").innerHTML = "";
	
	//校区
	var xq_click = getClickTj("xqdm");
	//园区
	var yq_click = getClickTj("yqdm");
	//楼栋
	var ld_click = getClickLd();
	
	dwr.engine.setAsync(false);
	
	var divHtml = "<ul id=\"cs_ul\">";

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;

	//根据校区,园区，楼栋，取得层数
	searchUtil.getGyglInfo("cs",xq_click,yq_click,ld_click,null,gyglyQx,userName,userDep,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<data.length;i++){
				divHtml += "<li>";
				divHtml += "<a href=\"#\" class=\"\"";
				divHtml += " onclick=\"clickCs(this);";
				divHtml += "creatClickedTj('cs','层数','"+data[i].cs+"','"+data[i].cs+"',this);return false;\"";
				divHtml += " id=\"tj_cs_"+data[i].cs+"\" name=\"tj_cs\">";
				divHtml += data[i].cs;
				divHtml += "</a></li>";
			}
		}
	});

	divHtml += "</ul>";

	$("cs_ul").outerHTML = divHtml;	

	dwr.engine.setAsync(true);

}

//生成新的寝室
function setNewQsh(){

	//校区
	var xq_click = getClickTj("xqdm");
	//园区
	var yq_click = getClickTj("yqdm");
	//楼栋
	var ld_click = getClickLd();
	//层数
	var cs_click = getClickTj("cs");
	
	$("qsh_ul").innerHTML = "";
	
	var noTj = false;

	//判断是否有相关联的条件被选中
	if(xq_click.length == 0 && yq_click.length == 0 && ld_click.length == 0 && cs_click.length == 0){
		noTj = true;
	}
	
	dwr.engine.setAsync(false);
	
	var divHtml = "<ul id=\"qsh_ul\">";

	var gyglyQx = $("gyglyQx").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	
	//根据校区,园区，楼栋，层数取得寝室
	searchUtil.getGyglInfo("qsh",xq_click,yq_click,ld_click,cs_click,gyglyQx,userName,userDep,function(data){
	
		if(data !=null && data.length >0){
		
			var qsh_count = 0;
			var qsh_py_num = 0;

			//循环拼音
			for(var i=0;i<PY_BIG.length;i++){
			
				var	flag = true;
				over_flag = false;
				var qt_flag = true;
				var qt_num = 0;
				
				//班级内容列表
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
									divHtml += "onclick=\"clickQsh(this);";
									divHtml += "creatClickedTj('qsh','寝室','"+data[j].qshdm+"','"+data[j].qshmc+"',this);return false;\">";
									divHtml += data[j].qshmc;
									divHtml += "</a>";
																				
									qt_num++;
									
									//其他
									if(qt_num == 3){
										
										qsh_count++;
										qsh_count++;
										
										divHtml += "<a href=\"#\" class=\"moreValue_click\" onclick=\"clickQshQt(\'"
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
									divHtml += "onclick=\"clickQsh(this);";
									divHtml += "creatClickedTj('qsh','寝室','"+data[j].qshdm+"','"+data[j].qshmc+"',this);return false;\">";
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
										
										divHtml += "<a href=\"#\" style=\"display:none;\" class=\"moreValue_click\" onclick=\"clickQshQt(\'"
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
//获得选中的团
function getClickTid(){

	//团obj
	var tid_num = document.getElementsByName("tj_tid").length;
	var tid_arr = new Array();
	var tid_count = 0;

	for(var i=0;i<tid_num;i++){
		var obj = document.getElementsByName("tj_tid")[i];
		if(obj.className == "selectedValue"){
			var tid_v = obj.id.split("_")[3];
			tid_arr[tid_count] = tid_v;
			tid_count++;
		}
	}
	
	return tid_arr;
}

//获得选中的营
function getClickYid(){

	//营obj
	var yid_num = document.getElementsByName("tj_yid").length;
	var yid_arr = new Array();
	var yid_count = 0;

	for(var i=0;i<yid_num;i++){
		var obj = document.getElementsByName("tj_yid")[i];
		if(obj.className == "selectedValue"){
			var yid_v = obj.id.split("_")[3];
			yid_arr[yid_count] = yid_v;
			yid_count++;
		}
	}
	
	return yid_arr;
}

//获得选中的连
function getClickLid(){

	//连obj
	var lid_num = document.getElementsByName("tj_lid").length;
	var lid_arr = new Array();
	var lid_count = 0;

	for(var i=0;i<lid_num;i++){
		var obj = document.getElementsByName("tj_lid")[i];
		if(obj.className == "selectedValue"){
			var lid_v = obj.id.split("_")[3];
			lid_arr[lid_count] = lid_v;
			lid_count++;
		}
	}
	
	return lid_arr;
}

//获得选中的排
function getClickPid(){

	//排obj
	var pid_num = document.getElementsByName("tj_pid").length;
	var pid_arr = new Array();
	var pid_count = 0;

	for(var i=0;i<pid_num;i++){
		var obj = document.getElementsByName("tj_pid")[i];
		if(obj.className == "selectedValue"){
			var pid_v = obj.id.split("_")[3];
			pid_arr[pid_count] = pid_v;
			pid_count++;
		}
	}
	
	return pid_arr;
}

//获得选中的班
function getClickBid(){

	//排obj
	var bid_num = document.getElementsByName("tj_bid").length;
	var bid_arr = new Array();
	var bid_count = 0;

	for(var i=0;i<bid_num;i++){
		var obj = document.getElementsByName("tj_bid")[i];
		if(obj.className == "selectedValue"){
			var bid_v = obj.id.split("_")[3];
			bid_arr[bid_count] = bid_v;
			bid_count++;
		}
	}
	
	return bid_arr;
}

//获得选中的宿舍
function getClickSsid(){

	//排obj
	var ssid_num = document.getElementsByName("tj_ssid").length;
	var ssid_arr = new Array();
	var ssid_count = 0;

	for(var i=0;i<ssid_num;i++){
		var obj = document.getElementsByName("tj_ssid")[i];
		if(obj.className == "selectedValue"){
			var ssid_v = obj.id.split("_")[3];
			ssid_arr[ssid_count] = ssid_v;
			ssid_count++;
		}
	}
	
	return ssid_arr;
}


//======================联动操作 end===========================

//======================联动加载html=begin==========================
//联动军训建制
function createJxjzHtml(ldlx){
	
	var ul_id = ldlx+"_ul";
	//团id
	var tid_click = getClickTid();
	//营id
	var yid_click = getClickYid();
	//连id
	var lid_click = getClickLid();
	//排id
	var pid_click = getClickPid();
	//班id
	var bid_click = getClickBid();
	//宿舍id
	var ssid_click = getClickSsid();
	//样式path
	var v4Path = stylePath;
	//军训id
	var jxid = $("jxid").value;
	

	//参数
 	var parameter = {
	 	"tid":tid_click.join("!!@@!!"),
	 	"yid":yid_click.join("!!@@!!"),
 		"lid":lid_click.join("!!@@!!"),
 		"pid":pid_click.join("!!@@!!"),
 		"bid":bid_click.join("!!@@!!"),
 		"ssid":ssid_click.join("!!@@!!"),
 		"ldlx":ldlx,
 		"stylePath":v4Path,
 		"jxid":jxid
	};

	var url="seachTjManage.do?method=creatJxjzHtml";

	jQuery.ajaxSetup({async:false});
	
	if($(ul_id)){
		jQuery("#"+ul_id).load(url,parameter,function(){
		
			var bm_num_id = ldlx+"_num";
			var bm_more_id = ldlx+"_more";
			var num = $(bm_num_id).value;

			if(num < 8){
				if($(bm_more_id)){
					$(bm_more_id).style.display = "none";
				}
			}else{
				$(bm_more_id).style.display = "";
				$(bm_more_id).innerHTML = "更多";
				$(bm_more_id).className = "more_down";
			}
		});
	}
	
	jQuery.ajaxSetup({async:true});	
}
//======================联动加载html=end==========================
