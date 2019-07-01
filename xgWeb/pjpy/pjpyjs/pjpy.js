
//����ƴ���ַ���,�ж��ַ�������Ӧ�������Ƿ�Ϊ��
function checkNotNull(str) {
	if (str != null) {
		var spt = str.split("-");
		for (var i = 0; i < spt.length; i++) {
			if ($(spt[i])) {
				if (document.getElementById(spt[i]).value == null 
					|| document.getElementById(spt[i]).value == "") {
					return false;
				}
			}
		}
		return true;
	} else {
		return false;
	}
}

//ȥ���ո�
function replaceBlankSpace(str){
	return str.replace(/(^\s*)|(\s*$)/g, "")
}


function getStucjList() {
	var xh = document.getElementById('xh').value;
	var xn = document.getElementById('xn').value;
	getStuDtiaInfo.getStuCjList(xh,xn,getCjInfo);
}

function getCjInfo(data){
	var cellMuster=[
		function(data){return data[0]},
		function(data){return data[1]},
		function(data){return data[2]},
		function(data){return data[3]},
		function(data){return data[4]},
		function(data){return data[5]}
	];
	if (data != null && typeof data == 'object') {
		if ($("cjxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cjxx");
			DWRUtil.addRows("cjxx",data,cellMuster);
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}


function getStucfList() {
	var xh = document.getElementById('xh').value;
	var xn = document.getElementById('xn').value;
	getStuDtiaInfo.getStuWjcfList(xh,xn,getCfInfo);
}

function getCfInfo(data) {
	var cellMuster=[
		function(data){return data[0]},
		function(data){return data[1]},
		function(data){return data[2]},
		function(data){return data[3]},
		function(data){return data[4]}
	];
	if (data != null && typeof data == 'object') {
		if ($("cfxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cfxx");
			DWRUtil.addRows("cfxx",data,cellMuster);
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}


function getStucfxx() {
	var xh = document.getElementById('xh').value;
	var xn = '';
	if (xh == null || xh == '') {
		return false;
	}
	getStuDtiaInfo.getStuWjcfxx(xh,xn,getCfxx);
}

function getCfxx(data) {
	var cellMuster=[
		function(data){return data[0]},
		function(data){return data[1]},
		function(data){return data[2]},
		function(data){return data[3]},
		function(data){return data[4]},
		function(data){return data[5]},
		function(data){return data[6]}
	];
	if (data != null && typeof data == 'object') {
		if ($("cfxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cfxx");
			DWRUtil.addRows("cfxx",data,cellMuster);
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}
