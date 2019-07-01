//验证数据格式是否是数字
function ckinpdata(obj){
         if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			alert('数据格式不正确或者输入值大于100分，请重新输入！');
			obj.value = '';
			return false;
		}
		return true;
	}
	
function chkinpdata(obj){
         if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			alert('数据格式不正确或者输入值大于100分，请重新输入！');
			obj.value = '';
			return false;
		}
		return true;
	}
	
	function saveinfo(url) {
		var checkBoxArr = document.getElementsByName("cbv");
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if (flag) {
			if (confirm('确认要保存当前页面的所有数据吗?')) {
				refreshForm(url);
			}
			return false;
		} else {
			alert("没有选择相应记录，请选择之后再进行操作！！");
			return false;
		}
	}
	
	function checkBox(obj) {
		if (obj.value != null && obj.value != null) {
			obj.parentNode.parentNode.getElementsByTagName("td")[0].getElementsByTagName('input')[0].checked = true;
		} 
	}
	
	function queryOne(xh) {
		var url = "/xgxt/stu_info_details.do?xh="+xh;
		showOpenWindow(url, 800, 500);
	}