//�������������������㽭����������������
function getZjlgStuInfo(type){

	var xh = document.getElementById("xh").value;
	if(type == "xxxx"){
		
		//ѧ���ɼ���Ϣ
		colList = ["nd", "xn", "xq", "xh", "xm", "nj", "bjmc","kssj", "jssj"];
		//getStuDetails.getAllOfInfo(xh,'view_ybdyxx',colList,getYbdyInfo);
		
		//ѧ���춯��¼
		colList = ["ydlbmc", "ydqxymc", "ydqzymc", "ydqbjmc", "ydhxymc", "ydhzymc", "ydhbjmc","ydrq"];
		getStuDetails.getAllOfInfo(xh,'view_xjydjbxx',colList,getYdInfo);	
	}
}

//�춯��Ϣ
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
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}