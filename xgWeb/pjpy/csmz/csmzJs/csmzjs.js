//保存奖学金数据
function savejxjdata1(url){
		var xh = document.getElementById('xh').value;
		if (xh==''||xh==null){
			alert('学号非空！');
			}
		else{
		document.forms[0].action = url;
		document.forms[0].submit();
		}
}

//修改社会奖学金
function modishjxj(url){
	if (curr_row == null) {
		alert('请选择要操作的行！');
	}else{
		var pkVal = curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += pkVal;
		showTopWin(url,720,620);
	}
}

//批量删除
function delshjxj(url){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要删除所选择的数据吗？')==true){
			document.forms[0].action = url;
			document.forms[0].submit();
		}
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}

/**
 * 审核按钮提交操作
 * @param res 审核结果：tg or btg
 */
function jxjshSubmit(url,res){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		document.forms[0].action=url+'?param1='+res;
		document.forms[0].submit();
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
}
