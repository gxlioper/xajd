function getStucjList() {
	var xh = document.getElementById('xh').value;
	var xn = document.getElementById('xn').value;
	getStuDtiaInfo.getStuCjList(xh,xn,getCjInfo);
}

function getCjInfo(data){
	var cellMuster=[
		function(data){return data[0]},
		function(data){return data[1]},
		function(data){return data[2]},
		function(data){return data[3]}
	];
	if (data != null && typeof data == 'object') {
		if ($("cjxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cjxx");
			DWRUtil.addRows("cjxx",data,cellMuster);
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

function getStucfList() {
	var xh = document.getElementById('xh').value;
	var xn = document.getElementById('xn').value;
	getStuDtiaInfo.getStuWjcfList(xh,xn,getCfInfo);
}

function getCfInfo(data) {
	var cellMuster=[
		function(data){return data[0]},
		function(data){return data[1]},
		function(data){return data[2]},
		function(data){return data[3]}
	];
	if (data != null && typeof data == 'object') {
		if ($("cfxx").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("cfxx");
			DWRUtil.addRows("cfxx",data,cellMuster);
		}
	}else{
		showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
	}
}

/**
 *  ����ı�����������Ƿ��� δ������TRUE ����֮ FALSE
 *  zdList Ҫ����ı�ģ��ID�б�    ��ʽ xh-xm-xb
 *  zdmcList ��ʾ��Ϣ�е������б�    ��ʽ ѧ��-����-�Ա�
 *  zsnum  Ҫ����ı�ģ�����������ֵ ��ʽ ������
 */
function checkTextAreaLength(zdList,zdmcList,zsnum) {
	if (zdList == null ||zdmcList == null|| zsnum == null) {
		return false;
	}
	var zdArray = zdList.split("-");
	var zdmcArray = zdmcList.split("-");
	for (var i=0;i<zdArray.length;i++) {
		if ($(zdArray[i])) {
			var textValue = document.getElementById(zdArray[i]).value;
			if (textValue != '' && textValue.length >= zsnum) {
				alert("����" + zdmcArray[i] +"����������,�������" + zsnum + "����.");
				return false;
			}
		}
	}
	return true;
}

//�������
function shdata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('ȷ�������ѡ���������?')){
			document.forms[0].action = url;
			document.forms[0].submit();
			if ($("pt")) {
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

//��ѧ���������ǰ�ļ��
function checkShdata(url,key) {
	var userType = document.getElementById('userType').value;
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	var len = 0;
	var pkList = "";
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			len++;
			pkList+=checkBoxArr[i].value+"!@";
		}
	}
	if (flag){
		if (userType=='xy') {//ѧԺҪ���
			var szrs = document.getElementById('szrs').value;
			var fpfs = document.getElementById('fpfs').value;
			var dm="" ;
			var bmdm="";
			if(fpfs==""){
			   alert("���ڲ������ý��е�����ʽ���ü��������������");
			   return false;
			}
			if ('zy'==fpfs) {
				bmdm = document.getElementById('zydm').value;
			} else if ('bj'==fpfs) {
				bmdm = document.getElementById('bjdm').value;		
			}
			if ($('jxjdm')) {
				dm = document.getElementById('jxjdm').value;
			}else {
				dm = document.getElementById('rychdm').value;
			}
			if (bmdm =='' || bmdm==null || dm=='' ||dm==null) {
				alert("��ʾ��\""+('rych'==key ? '�����ƺ�' : '��ѧ��') +"\"��\"" + ('zy'==fpfs ? 'רҵ' : '�༶') + '\"������ѡ!');
				return false;
			}
			
			if (confirm('ȷ�������ѡ���������?')){
				//�����ȼ���Ƿ���������
				getStuDtiaInfo.checkPlshxx(fpfs,szrs,key,len,dm,bmdm,pkList,function (data) {
					if (data =='' || data==null) {
						document.forms[0].action = url;
						document.forms[0].submit();
						if ($("pt")) {
							BatAlert.showTips('���ڲ�������ȴ�...');
						}
					} else {
						alert(data);
						return false;
					}
				});
			}
		} else {//ѧУ���ü��
			if (confirm('ȷ�������ѡ���������?')){
				document.forms[0].action = url;
				document.forms[0].submit();
				if ($("pt")) {
					BatAlert.showTips('���ڲ�������ȴ�...');
				}
			}
		}
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�����");
	}
}


//�������
function plshdata(url) {
	var checkBoxArr = document.getElementsByName("cbv");
	var pk = "";
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pk += checkBoxArr[i].value+"!@";
		}
	}
	if (flag){
			showTopWin(url + "?keys=" + pk,450,350);
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}