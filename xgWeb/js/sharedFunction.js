String.prototype.trim = function(){
	return this.replace(/^\s*/g,"").replace(/\s*$/g,"");
}
String.prototype.rtrim = function(){
	return this.replace(/\s*$/g,"");
}
String.prototype.ltrim = function(){
	return this.replace(/^\s*/g,"");
}

/*
 *��������ֻ���������� 
 */
function onlyNum(obj){
	//alert(this.name);
	obj.value = obj.value.replace(/[^\d]/g,"");
	return true;
}
/*
 *����ֻ���������ֻ���ĸ 
 */
function onlyNumWords(obj){
	obj.value = obj.value.replace(/[^\da-zA-Z]/g,"");
	return true;
}
//��������option�select��ǩ��
function addOptionToSel(selObj,opName,opVal){
	var option = document.createElement("option");
	option.appendChild(document.createTextNode(opName));
	option.value = opVal;
	selObj.appendChild(option);
}
//��һ��option����뵽select��
function moveOpToTargetSel(TargetSel,op){
	TargetSel.appendChild(op);
}
//����ɾ��select��ǩ�е�option
function delOptionToSel(selObj,opObj){
	selObj.removeChild(opObj);
}
//���һ����ѡ��ѡ��ҳ������еĸ�ѡ��
//�õ���dwr�ṩ�ķ���
function onclickCheckBox(){
	var allCheckBox = DWRUtil.byId('');
}