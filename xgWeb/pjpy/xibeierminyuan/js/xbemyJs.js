function checkMustNotNull(){
	var xn = DWRUtil.byId('xn');
	var jxj = DWRUtil.byId('xmdm');
	if((xn.value === null || xn.value.length===0) || (jxj.value === null || jxj.value.length===0)){
		alert("ѧ��,��ѧ���Ǳ�ѡ���ѡ����ѯ��");
		return false;
	}	
	return true;
}
/**
 * ���ݺ�̨�����ֵ����ѡ���ѡ�б��
 */
function shResult(){
	var table = DWRUtil.byId('rsTable');
	var tableTrs = null;
	var tableRows = null;
	if(table){
		tableTrs  = table.getElementsByTagName("tr");
		tableRows = table.rows;
	}
	if(tableTrs){
		for(var i=0;i<tableTrs.length;i++){
			if(tableTrs[i].style.backgroundColor == '#99ffcc'){//���ͨ���ļ�¼����ɫ��#99FFCC
				if(tableRows[i].cells[0].getElementsByTagName){
					tableRows[i].cells[0].getElementsByTagName('input')[0].checked=true;//�����ͨ������ѡ��	
				}
			}
		}
	}
}

/**
 * ��˰�ť�ύ����
 * @param res ��˽����tg or btg
 */
function shSubmit(res){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		document.forms[0].action="/xgxt/xyjxjSh.do?param1="+res;
		document.forms[0].submit();
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}

/**
 * �û�����ѡ��
 */
function selectAll(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function pjpySbExport(){
	var xn = DWRUtil.byId('xn');
	var jxj = DWRUtil.byId('xmdm');
	if((xn.value === null || xn.value.length===0) || (jxj.value === null || jxj.value.length===0)){
		alert("ѧ��,��ѧ���Ǳ�ѡ���ѡ����ѯ��");
		return false;
	}	
	document.forms[0].action = "/xgxt/xysbjxj.do?method=dataExport";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
