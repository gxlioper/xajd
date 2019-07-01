/**
 *   批量操作
 *   checkname为checkBox数组的name,lx为判断提示信息的类型，页面上的隐藏域pt放提示信息。
 *   lt
 *   2010-6-28
 */
 
function batchData(checkname,url,type) {
	var checkBoxArr = document.getElementsByName(checkname);
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		
		var confirmValue;
		
		if(type=='del'){
			//删除
			confirmValue='确定要删除所选择的数据吗？';
		}else if(type=='save'){
			//保存
			confirmValue='确定要修改所选择的数据吗？';	
		}else if(type=='sh'){
			//审核
			confirmValue='确定要审核所选择的数据吗？';
		}else if(type=='qk'){
			//清空
			confirmValue='确定要清空所选择的数据吗？';
		}else{
			//其他
			confirmValue='确定要操作所选择的数据吗？';	
		}
		
		//提示确认信息
		confirmInfo(confirmValue, function(tag){
			if(tag == 'ok'){
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		});

		//提示条信息
		if ($("pt")){
			BatAlert.showTips('正在操作，请等待...');
		}
		
	}else{
		alertError("没有选择相应记录，请选择之后再进行操作！");
	}
}