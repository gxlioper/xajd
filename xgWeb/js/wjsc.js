function viewyh(obj,val){
	var value = '';
	var tab = document.getElementById('yhzinfo');
	var line = tab.getElementsByTagName('tr');
	if(obj == 'first'){			
		value = line[1].cells[0].getElementsByTagName('input')[0].value;
		line[1].cells[0].getElementsByTagName('span')[0].style.color='red';			
	}else{
		value = val;
		var yczdm = document.getElementById(document.getElementById('yczdm').value);
		if(yczdm != null){
			yczdm.style.display = 'none';
		}
		var sp = line[1].cells[0].getElementsByTagName('span');
		for(var n=0;n<sp.length;n++){
			sp[n].style.color='#003366';
		}
		obj.style.color='red';
	}	
	document.getElementById('yczdm').value = value;
	if(value == null){
		value = '';
	}
	var block = document.getElementById(value);
	if(obj == 'first' && block!= null){
		block.style.display = 'block';
	}
	if(block != null && obj != 'first'){
		block.style.display = 'block';
		var array = document.getElementById('wjjsr');
		var yhz = obj.parentNode.getElementsByTagName('input');
		for(var j=0;j<yhz.length;j++){
			if(yhz[j].value == value){
				num = j;
				break;
			}	
		}
		var yhzyh = array.options[num].value.split(',');
		var array2 = block.getElementsByTagName('input');
		for(var k = 0;k<array2.length;k++){
			for(var m=0;m<yhzyh.length;m++){
				if(array2[k].value == yhzyh[m]){
					array2[k].checked = true;
				}
			}
		}
	}	
}

function selectall(obj){
	
	viewyh(obj,obj.value);
	
	var tab = document.getElementById('yhinfo');
	var zdm = document.getElementById(obj.value);
	var array;
	var xmarray
	if(zdm != null){
		array = zdm.getElementsByTagName('input');
		xmarray = zdm.getElementsByTagName('span');
	} 
	var jsr = document.getElementById('wjjsr');
	var num = 0;
	if(zdm != null && zdm.style.display != 'none'){
		if(array != null && array.length>0){
			for(var i=0;i<array.length;i++){
				array[i].checked=obj.checked;		
			}
		}
	}
	var yhz = obj.parentNode.getElementsByTagName('input');
	for(var j=0;j<yhz.length;j++){
		if(yhz[j].value == obj.value){
			num = j;
			break;
		}	
	}	
	if(obj.checked == true){
			if(array != null && array.length>0){
				for(var k=0;k<array.length;k++){				
					jsr.options[num].value += array[k].value+',';
					jsr.options[num].innerText += xmarray[k].innerText+',';											
				}
			}
		
	}else{
		jsr.options[num].value = '';
		jsr.options[num].innerText = '';
	}
	if(jsr.options[num].value.length>0){
		jsr.options[num].value = jsr.options[num].value.substring(0,jsr.options[num].value.length-1);
		jsr.options[num].innerText = jsr.options[num].innerText.substring(0,jsr.options[num].innerText.length-1);
	}
	setTimeout("viewxm()",100); 
}

function selectjsr(obj){
	var array = obj.parentNode.getElementsByTagName('input');
	var id = obj.parentNode.id;
	var tab = document.getElementById('yhzinfo');
	var yhz = tab.getElementsByTagName('input');
	var jsr = document.getElementById('wjjsr');
	var num = 0;
	var flag = false;
	for(var i =0;i<array.length;i++){
		if(array[i].checked == true){
			flag = true;
			break;
		}
	}
	for(var j=0;j<yhz.length;j++){
		if(yhz[j].value == id){
			num = j;
			if(flag){
				yhz[j].checked = true;
			}else{
				yhz[j].checked = false;;
			}		
			break;
		}
	}
	var yhms = obj.parentNode.getElementsByTagName('input');
	var xms = obj.parentNode.getElementsByTagName('span');
	jsr.options[num].value = '';
	jsr.options[num].innerText = '';
	for(var k=0;k<yhms.length;k++){
		if(yhms[k].checked == true){
			jsr.options[num].value += yhms[k].value+',';
			jsr.options[num].innerText +=xms[k].innerText+',';
		}			
	}
	if(jsr.options[num].value.length>0){
		jsr.options[num].value = jsr.options[num].value.substring(0,jsr.options[num].value.length-1);
		jsr.options[num].innerText = jsr.options[num].innerText.substring(0,jsr.options[num].innerText.length-1);
	}
	
	setTimeout("viewxm()",100); 
}
function viewxm(){
	if ($('yhlist')){
		var yhms = $('yhlist').getElementsByTagName('input');
		var xms =  $('yhlist').getElementsByTagName('span');
		
		var temp ='';
		var temp_yhm='';
		
		$('jsrxm').innerText='';
		$('yhms').value = '';
		for(var k=0;k<yhms.length;k++){
			if(yhms[k].checked){
				temp +=xms[k].innerText;
				temp +=',';
				
				temp_yhm+=yhms[k].value;
				temp_yhm +=',';
			}			
		}
		
		$('jsrxm').innerText = temp.substring(0, temp.length-1);
		$('yhms').value = temp_yhm.substring(0,temp_yhm.length-1);
	}
}
function getyhs(){
	var jsr = document.getElementById('wjjsr');
	var tab = document.getElementById('yhzinfo');
	var line = tab.getElementsByTagName('tr');
	var yhs = '';
	var zdm = line[1].cells[0].getElementsByTagName('input');
	var zmc = line[1].cells[0].getElementsByTagName('span');
	
	var yhms = $('yhlist').getElementsByTagName('input');
	$('yhms').value = '';
	
	for(var k=0;k<yhms.length;k++){
		if(yhms[k].checked){
			yhs+=yhms[k].value;
			yhs+=',';
		}			
	}
	
	if(zdm != null){
		for(var j=0;j<zdm.length;j++){
			if(zdm[j].checked == true){
				$('zdms').value += zdm[j].value+',';
				$('zmcs').value += zmc[j].innerText+',';
			}
		}
		if($('zdms').value.length > 0){
			$('zdms').value = $('zdms').value.substring(0,$('zdms').value.length-1);
			$('zmcs').value = $('zmcs').value.substring(0,$('zmcs').value.length-1);
		}
	}
	if(yhs.length>0){
		$('yhms').value = yhs.substring(0,yhs.length-1);
	}
	return false;
}

