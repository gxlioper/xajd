//用于 系统维护－基础数据初始化－数据关联基础表 选择某个表后的操作
function selTable(obj){
	var tableNameStr = obj.value;
	var localTable ="";
	var remoteTable = "";
	//if(tableNameStr.split("!@") && tableNameStr.split("!@").length==2){
	//	localTable = tableNameStr.split("!@")[0];
	//	remoteTable = tableNameStr.split("!@")[1];
	//}
	//var localTableColumns = DWRUtil.byId('local');
	//var remoteTableColumns = DWRUtil.byId('remote');
	//if(tableNameStr != null && tableNameStr != ""){//在选中表的情况下才取值
		//获得本地表的列
	//	getBaseData.getLocalColumns(localTable,function(data){
	//		for(var i=0;i<data.length;i++){
	//			addOptionToSel(localTableColumns,data[i][1],data[i][0]);	
	//		}
	//	});
		//获得异地表的列
	//	getBaseData.getRemoteColumns(remoteTable,function(data){
	//		for(var i=0;i<data.length;i++){
	//			addOptionToSel(remoteTableColumns,data[i][1],data[i][0]);	
	//		}
	//	});
	//}
	if(tableNameStr !== null && tableNameStr.length>0){
		getBaseData.getTableProperties(tableNameStr,function(data){
			DWRUtil.byId('yearMonthDay').value= data[0].substr(0,8);//yyyymmdd
			DWRUtil.byId('hh').value= data[0].substr(8,2);
			DWRUtil.byId('mi').value= data[0].substr(10,2);
			DWRUtil.byId('ss').value= data[0].substr(12,2);
			var intervalTime = data[1].split("!@");
			DWRUtil.byId('day').value= intervalTime[0];
			DWRUtil.byId('hour').value= intervalTime[1];
			DWRUtil.byId('minute').value= intervalTime[2];
		});	
	} else {
		DWRUtil.byId('yearMonthDay').value= '';//yyyymmdd
			DWRUtil.byId('hh').value= '00';
			DWRUtil.byId('mi').value= '00';
			DWRUtil.byId('ss').value= '00';
			
			DWRUtil.byId('day').value= '000';
			DWRUtil.byId('hour').value= '000';
			DWRUtil.byId('minute').value= '000';
	}	
	DWRUtil.byId('displayCols').style.display="block";
}

//跳转到教务系统
function redirectToJw(){
	var jwUrl = "";
	var userName = DWRUtil.byId("userName").value;
	var jsName = DWRUtil.byId("jsName").value=="teacher"?"js":"xs";
	getBaseData.getJwSiteFromJwWebservice(userName,jsName,function(data){
		if(data != null){
			jwUrl = data[0]+"?userName="+userName+"&jsName="+jsName+"&strSysDatetime="+data[1]+"&verify="+data[2];	
			window.top.location = jwUrl;
		} else {
			alert("请联系学工系统管理员检查view_zf_key视图！！");
			return false;
		}
	});
}


