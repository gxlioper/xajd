function selectall(obj){
			var tab = document.getElementById('zyinfo');
			var array = tab.getElementsByTagName('input');
			for(var i=0;i<array.length;i++){
				array[i].checked = obj.checked;
			}
			var sxzy = document.getElementById('sxzy');
			if(obj.checked == true){
				sxzy.innerText = '专业不限';
				$('viewzydm').value = '';
			}else{
				sxzy.innerText = '';
			}
		}
function viewzy(){
			var sxzy = document.getElementById('sxzy');
			var viewzydm = document.getElementById('viewzydm');
			var tab = document.getElementById('zyinfo');
			var array = tab.getElementsByTagName('tr');
			viewzydm.value = '';
			sxzy.innerText = '';
			for(var i=0;i<array.length;i++){
				if(array[i].cells[0].getElementsByTagName('input')[0].checked == true){
					viewzydm.value += array[i].cells[0].getElementsByTagName('input')[0].value+',';
					sxzy.innerText += array[i].cells[1].innerText+',';
				}
			}
			if(viewzydm.value.length>0){
				viewzydm.value = viewzydm.value.substring(0,viewzydm.value.length-1);
				sxzy.innerText = sxzy.innerText.substring(0,sxzy.innerText.length-1);
			}
		}
 function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
     }