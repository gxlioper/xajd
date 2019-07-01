//======================点击事件===========================

//点击违纪类别大类
function clickGyjllbdldm(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//清空违纪类别代码已选条件
	removeYxtjByLd("gyjllbdm");
	
	//存在违纪类别代码
	if($("gyjllbdm_ul")){
		//违纪类别联动
		creatWjHtml("gyjllbdm");
	}
	
}

//点击违纪类别代码
function clickGyjllbdm(obj){

	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
}
//======================点击事件 end===========================


//获得选中的gyjllbdldm
function getClickGyjllbdldm(){

	//团obj
	var dm_num = document.getElementsByName("tj_gyjllbdldm").length;
	var dm_arr = new Array();
	var dm_count = 0;

	for(var i=0;i<dm_num;i++){
		var obj = document.getElementsByName("tj_gyjllbdldm")[i];
		if(obj.className == "selectedValue"){
			var dm_v = obj.id.split("_")[3];
			dm_arr[dm_count] = dm_v;
			dm_count++;
		}
	}
	
	return dm_arr;
}
//======================联动操作 end===========================

