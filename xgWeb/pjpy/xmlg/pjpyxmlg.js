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
		function(data){return data[3]}
	];
	if (data != null && typeof data == 'object') {
		if ($("cjxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cjxx");
			DWRUtil.addRows("cjxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
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
		function(data){return data[3]}
	];
	if (data != null && typeof data == 'object') {
		if ($("cfxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cfxx");
			DWRUtil.addRows("cfxx",data,cellMuster);
		}
	}else{
		showMsgWin("有错误出现：远程数据读取失败！");
	}
}

/**
 *  检测文本框输入的字是否超限 未超返回TRUE ，反之 FALSE
 *  zdList 要检测文本模的ID列表，    格式 xh-xm-xb
 *  zdmcList 提示信息中的中文列表    格式 学号-姓名-性别
 *  zsnum  要检测文本模的字数的最大值 格式 正整数
 */
function checkTextAreaLength(zdList,zdmcList,zsnum) {
	if (zdList == null ||zdmcList == null|| zsnum == null) {
		return false;
	}
	var zdArray = zdList.split("-");
	var zdmcArray = zdmcList.split("-");
	for (var i=0;i<zdArray.length;i++) {
		if ($(zdArray[i])) {
			var textValue = document.getElementById(zdArray[i]).value;
			if (textValue != '' && textValue.length >= zsnum) {
				alert("键入" + zdmcArray[i] +"的字数过大,请控制在" + zsnum + "以内.");
				return false;
			}
		}
	}
	return true;
}

//批量审核
function shdata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定审核所选择的数据吗?')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('正在操作，请等待...');
			}
		}
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}

//奖学金批量审核前的检测
function checkShdata(url,key) {
	var userType = document.getElementById('userType').value;
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	var len = 0;
	var pkList = "";
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			len++;
			pkList+=checkBoxArr[i].value+"!@";
		}
	}
	if (flag){
		if (userType=='xy') {//学院要检测
			var szrs = document.getElementById('szrs').value;
			var fpfs = document.getElementById('fpfs').value;
			var dm="" ;
			var bmdm="";
			if(fpfs==""){
			   alert("请在参数设置进行调整方式设置及相关人数调整！");
			   return false;
			}
			if ('zy'==fpfs) {
				bmdm = document.getElementById('zydm').value;
			} else if ('bj'==fpfs) {
				bmdm = document.getElementById('bjdm').value;		
			}
			if ($('jxjdm')) {
				dm = document.getElementById('jxjdm').value;
			}else {
				dm = document.getElementById('rychdm').value;
			}
			if (bmdm =='' || bmdm==null || dm=='' ||dm==null) {
				alert("提示：\""+('rych'==key ? '荣誉称号' : '奖学金') +"\"和\"" + ('zy'==fpfs ? '专业' : '班级') + '\"条件必选!');
				return false;
			}
			
			if (confirm('确定审核所选择的数据吗?')){
				//这里先检测是否超限制人数
				getStuDtiaInfo.checkPlshxx(fpfs,szrs,key,len,dm,bmdm,pkList,function (data) {
					if (data =='' || data==null) {
						document.forms[0].action = url;
						document.forms[0].submit();
						if ($("pt")) {
							BatAlert.showTips('正在操作，请等待...');
						}
					} else {
						alert(data);
						return false;
					}
				});
			}
		} else {//学校不用检测
			if (confirm('确定审核所选择的数据吗?')){
				document.forms[0].action = url;
				document.forms[0].submit();
				if ($("pt")) {
					BatAlert.showTips('正在操作，请等待...');
				}
			}
		}
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！");
	}
}


//批量审核
function plshdata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var pk = "";
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pk += checkBoxArr[i].value+"!@";
		}
	}
	if (flag){
			showTopWin(url + "?keys=" + pk,450,350);
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}