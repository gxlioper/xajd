//���潱ѧ������
function savejxjdata1(url){
		var xh = document.getElementById('xh').value;
		if (xh==''||xh==null){
			alert('ѧ�ŷǿգ�');
			}
		else{
		document.forms[0].action = url;
		document.forms[0].submit();
		}
}

//�޸���άѧ��
function modishjxj(url){
	if (curr_row == null) {
		alert('��ѡ��Ҫ�������У�');
	}else{
		var pkVal = curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += pkVal;
		showTopWin(url,720,620);
	}
}

//����ɾ��
function delshjxj(url){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('ȷ��Ҫɾ����ѡ���������')==true){
			document.forms[0].action = url;
			document.forms[0].submit();
		}
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}

/**
 * ��˰�ť�ύ����
 * @param res ��˽����tg or btg
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
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}
