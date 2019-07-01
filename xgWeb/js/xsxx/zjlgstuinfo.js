//－－－－－－－－－浙江理工－－－－－－－
function getZjlgStuInfo(type){

	var xh = document.getElementById("xh").value;
	if(type == "xxxx"){
		
		//学生成绩信息
		colList = ["nd", "xn", "xq", "xh", "xm", "nj", "bjmc","kssj", "jssj"];
		//getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getYbdyInfo);
		
		//学籍异动记录
		colList = ["ydlbmc", "ydqxymc", "ydqzymc", "ydqbjmc", "ydhxymc", "ydhzymc", "ydhbjmc","ydrq"];
		getStuDetails.getAllOfInfo(xh,'view_xjydjbxx',colList,getYdInfo);	
	}
}

//异动信息
function getYdInfo(data){

	var cellMuster=[
		function(data){return data.ydlbmc},
		function(data){return data.ydqxymc},
		function(data){return data.ydqzymc},
		function(data){return data.ydqbjmc},
		function(data){return data.ydhxymc},
		function(data){return data.ydhzymc},
		function(data){return data.ydhbjmc},
		function(data){return data.ydrq}		
	];
	if (data != null && typeof data == 'object') {
		if ($("ydxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("ydxx");
			DWRUtil.addRows("ydxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}