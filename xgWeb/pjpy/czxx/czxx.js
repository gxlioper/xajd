//��֤���ݸ�ʽ�Ƿ�������
function ckinpdata(obj){
         if(obj.value==""){		
		   return false;
		}
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;		

		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
			alert('���ݸ�ʽ����ȷ��������ֵ����100�֣����������룡');
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
			alert('���ݸ�ʽ����ȷ��������ֵ����100�֣����������룡');
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
			if (confirm('ȷ��Ҫ���浱ǰҳ�������������?')) {
				refreshForm(url);
			}
			return false;
		} else {
			alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
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