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
 *用于限制只能输入数字 
 */
function onlyNum(obj){
	//alert(this.name);
	obj.value = obj.value.replace(/[^\d]/g,"");
	return true;
}
/*
 *限制只能输入数字或字母 
 */
function onlyNumWords(obj){
	obj.value = obj.value.replace(/[^\da-zA-Z]/g,"");
	return true;
}
//单个增加option项到select标签中
function addOptionToSel(selObj,opName,opVal){
	var option = document.createElement("option");
	option.appendChild(document.createTextNode(opName));
	option.value = opVal;
	selObj.appendChild(option);
}
//把一个option项加入到select中
function moveOpToTargetSel(TargetSel,op){
	TargetSel.appendChild(op);
}
//单个删除select标签中的option
function delOptionToSel(selObj,opObj){
	selObj.removeChild(opObj);
}
//点击一个复选框，选中页面的所有的复选框
//用到了dwr提供的方法
function onclickCheckBox(){
	var allCheckBox = DWRUtil.byId('');
}