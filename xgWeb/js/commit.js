/**
 *   ��������
 *   checknameΪcheckBox�����name,lxΪ�ж���ʾ��Ϣ�����ͣ�ҳ���ϵ�������pt����ʾ��Ϣ��
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
			//ɾ��
			confirmValue='ȷ��Ҫɾ����ѡ���������';
		}else if(type=='save'){
			//����
			confirmValue='ȷ��Ҫ�޸���ѡ���������';	
		}else if(type=='sh'){
			//���
			confirmValue='ȷ��Ҫ�����ѡ���������';
		}else if(type=='qk'){
			//���
			confirmValue='ȷ��Ҫ�����ѡ���������';
		}else{
			//����
			confirmValue='ȷ��Ҫ������ѡ���������';	
		}
		
		//��ʾȷ����Ϣ
		confirmInfo(confirmValue, function(tag){
			if(tag == 'ok'){
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		});

		//��ʾ����Ϣ
		if ($("pt")){
			BatAlert.showTips('���ڲ�������ȴ�...');
		}
		
	}else{
		alertError("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�����");
	}
}