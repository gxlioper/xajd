/*
 *  ���򷽷�(�������򷽷�����):��Ҫ������з��������ʹ��,��A,B,C�����������յڸ����������������,������ʾ���кſ��Ը��ݷ����ĸߵͽ�����������
 *  begin
 */

var v = 0;//�����ж��û��Ƿ��ǵ�һ�ε������,��һ��Ϊ����,�ڶ���Ϊ����
var col = -1;//��������,�����ж��û��Ƿ��ǵ�һ�ε������

//������Ϣ  id:��ID, flag:�Ƿ����ͷ����, cellposition: �к�λ��, obj:this
function keepRowNumber(id,flag,cellposition,obj){
		if(obj.cellIndex != col){//��һ������
			col = obj.cellIndex;
			v = 1;
		}else{//�ǵ�һ������
			col = -1;
			v = 0;
		}
		if(cellposition != obj.cellIndex){
			var trobj = $(id).getElementsByTagName('tr');//��ȡ���е��ж���
			var num;
			if(flag){//����ͷ����
				num = trobj.length-1;
				for(var i=1;i<trobj.length;i++){
					if(v == 1){	//��һ������Ϊ����,��ʾ�к�Ϊ����				
						trobj[i].cells[cellposition].innerText = num;
						num--;	
					}else{					
						trobj[i].cells[cellposition].innerText = i;
					}
									
				}
			}else{//������ͷ����
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
			alert('ֻ��������������С����');
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
		alert("����ѡ����"+jQuery("#xbmc").val()+"��ѧ��Ϊ��ѡ�");
		return;
	} else {
		//pjpyZjsyzy.chkxySzrs(xn,xydm,function (data){
			//if (data) {
				window.open(url);
			//} else {
			//	alert('��ѧԺѧ�����ʲ����ɼ���δ¼�룬����¼��ѧ�����ʲ����ɼ��ٽ���ͳ�ƣ�');
			//}	
		//});
	}
}