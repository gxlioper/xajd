/*
 *  排序方法(加在排序方法后面):主要是针对有分数排序的使用,如A,B,C三个分数按照第个分数进行排序操作,行首显示的行号可以根据分数的高低进行名次排序
 *  begin
 */

var v = 0;//用于判断用户是否是第一次点击排序,第一次为升序,第二次为降序
var col = -1;//辅助参数,用于判断用户是否是第一次点击排序

//参数信息  id:表ID, flag:是否带表头排序, cellposition: 行号位置, obj:this
function keepRowNumber(id,flag,cellposition,obj){
		if(obj.cellIndex != col){//第一次排序
			col = obj.cellIndex;
			v = 1;
		}else{//非第一次排序
			col = -1;
			v = 0;
		}
		if(cellposition != obj.cellIndex){
			var trobj = $(id).getElementsByTagName('tr');//获取所有的行对象
			var num;
			if(flag){//带表头排序
				num = trobj.length-1;
				for(var i=1;i<trobj.length;i++){
					if(v == 1){	//第一次排序为升序,显示行号为降序				
						trobj[i].cells[cellposition].innerText = num;
						num--;	
					}else{					
						trobj[i].cells[cellposition].innerText = i;
					}
									
				}
			}else{//不带表头排序
				num = trobj.length;
				for(var i=0;i<trobj.length;i++){
					trobj[i].cells[cellposition].innerText = i+1;
				}
			}
		}	
	}
/*
 *  end
 */

function ckinpdatanum(obj){
		obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
			alert('只能输入正整数或小数！');
			obj.value = '';
			return false;
		}
		return true;
	}
	
function szcptj() {
	var xydm = document.getElementById('xy').value;
	var xn = document.getElementById('xn').value;
	var url = 'pjpy_zgkd_zhszcphz.do?xydm=';
	url += xydm;
	url += '&xn=';
	url += xn;
	var ss='0';
	if (xydm==''||xn=='') {
		alert("条件选择中"+jQuery("#xbmc").val()+"、学年为必选项！");
		return;
	} else {
		//pjpyZjsyzy.chkxySzrs(xn,xydm,function (data){
			//if (data) {
				window.open(url);
			//} else {
			//	alert('该学院学生素质测评成绩尚未录入，请先录入学生素质测评成绩再进行统计！');
			//}	
		//});
	}
}