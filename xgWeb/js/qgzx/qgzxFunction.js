function dtjsCommonSave(url,yzdz,yzcd,mustFill){
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return;
		}
	}
	var splitDz = yzdz.split("-");
	var splitCd = yzcd.split("-");
	if(splitDz[0]!=""){
	for (i = 0; i < splitDz.length; i++) {
		var dzsjcd = $(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
		var dzsjmc = $(splitDz[i]).cells[0].innerHTML;
		if (dzsjcd.length>splitCd[i]) {
			alert(dzsjmc+"���ܳ���"+splitCd[i]+"���֣�");
			return;
		}
	}
	}
	document.forms[0].action = url;
	document.forms[0].submit();
}

//���´���
function showInfo(url,doType,w,h){
	
	if(doType == "update"){
		if(curr_row == null){
			alert('��ѡ��Ҫ�޸ĵ����ݣ�');
			return false;
		}
	}else if(doType == "sh"){
		if(curr_row == null){
			alert('��ѡ����˵����ݣ�');
			return false;
		}
	}else if(doType == "sb"){
		if(curr_row == null){
			alert('��ѡ��Ҫ�걨�����ݣ�');
			return false;
		}
	}else if(doType == "view"){
		if(curr_row == null){
			alert('��ѡ��Ҫ�鿴�����ݣ�');
			return false;
		}
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&doType="+doType;
	url+="&pk="+pk;
	showTopWin(url,w,h);
}

//����ҳ��
function openInfo(url){
	if(curr_row == null){
		alert('��ѡ��Ҫ���������ݣ�');
		return false;
	}
	var pk = curr_row.getElementsByTagName('input')[0].value;
	url+="&pk="+pk;
	window.open(url);
}

//�����ύ
function sumitInfo(url,doType){
	var len=jQuery("input:checkbox[name=primarykey_checkVal]:checked").length;
	if(len > 0){
		//if(doType=="del"){
			url+="&doType="+doType;
			if (confirm("ȷ��Ҫɾ������ѡ������?")) {
				showTips('���������У���ȴ�......');
				refreshForm(url);
			}
		//}else{
			
		//}
	}else{
		alert("�빴ѡҪ���������");
		return false;
	}
}

 /**
 *   ָ������ѧ���ֶ����ݳ���
 *   luojw
 *   2010-6-28
 */
function sendXx(){
	//ҳ���������ָ������ѯ���ݳ��������磨<input type="hidden" id="tableName" name="tableName" value="view_ybdyxx"/>��
	var tableName=$("tableName").value;
	//����Դ��������չʾjsp�ı�ͷ�����磨<input type="hidden" id="lx" name="lx" value="Ԥ����Ա"/>��
	var lx="";
	//����Դ����������������:
	//<input type="hidden" id="zd" name="zd" value="sfty-xxsh"/>
	//<input type="hidden" id="va" name="va" value="��-ͨ��"/>
	var zd="";
	var va="";
	var url = "/xgxt/czxxDtjsDyxx.do?method=xsxxManage";
	
	if($("lx")){
		lx=$("lx").value
	};
	if($("zd")){
		zd=$("zd").value
	};
	if($("va")){
		va=$("va").value
	};
	
	url+="&tableName="+tableName;
	if(lx !=""){
		url+="&lx="+lx;
	}
	if(zd !=""){
		url+="&zd="+zd;
	}
	if(va !=""){
		url+="&va="+va;
	}
	showTopWin(url,800,600);
}

function checkNull(obj){
	if(obj.value == ""){
		obj.value = "0";
	}
}
/**
*��߿��ֵ����ӵ��ұ߿���ӳɹ�ͬʱ��ɾ��ԭ��ֵ��
*luojw
*20100628
*/
function addBjP(){

	dwr.engine.setAsync(false);
	//��߿�ռ�����Ϊbj(���Կ��Ǵ������)
	var fromIndx = $("bj").selectedIndex;
	//�ұ߿�ռ�����ΪbjP(���Կ��Ǵ������)
	var toIndx = $("bjP").options.length;
	if(fromIndx < 0){
		return false;
	}

	if($("bj").options[fromIndx].value == "" || $("bj").options[fromIndx].value == null || $("bj").options[fromIndx].value == "null"){
		return false;
	}
	
	for(var i=0;i<toIndx;i++){
		if($("bj").options[fromIndx].value == $("bjP").options[i].value){
			return false;
		}
	}
	$("bjP").options[$("bjP").options.length] = new Option($("bj").options[fromIndx].text,$("bj").options[fromIndx].value);
	$("bj").options[fromIndx] = null;
	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($("bjP").options.length > 0){
		$("bjP").options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

/**
*�ұ߿��ֵ����ӵ��ұ߿���ӳɹ�ͬʱ��ɾ��ԭ��ֵ��
*luojw
*20100628
*/
function delBjP(){

	dwr.engine.setAsync(false);
	//�ұ߿�ռ�����ΪbjP(���Կ��Ǵ������)
	var toIndx = $("bjP").selectedIndex;
	//��߿�ռ�����Ϊbj(���Կ��Ǵ������)
	var fromIndx = $("bj").options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($("bjP").options[toIndx].value == $("bj").options[i].value){
			$("bjP").options[toIndx] = null;
			return false;
		}
	}
	$("bj").options[$("bj").options.length] = new Option($("bjP").options[toIndx].text,$("bjP").options[toIndx].value);
	$("bjP").options[toIndx] = null;

	if($("bj").options.length > 0){
		$("bj").options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

function setXylist(){

	var nj = $("nj").value;
	var objId = "xydm";
	
	getPjpyInfo.getXyList(nj,function(data){
		if(data !=null && data.length >0){
			if(data !=null && data.length >0){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");
			}
		}
	});
}

/**
*��߿��ֵ����ӵ��ұ߿���ӳɹ�ͬʱ��ɾ��ԭ��ֵ��
*luojw
*20100628
*/
function addRightFrame(left,right){

	dwr.engine.setAsync(false);
	
	var fromIndx = $(left).selectedIndex;
	var toIndx = $(right).options.length;

	if(fromIndx < 0){
		return false;
	}

	if($(left).options[fromIndx].value == "" || $(left).options[fromIndx].value == null || $(left).options[fromIndx].value == "null"){
		return false;
	}
	
	for(var i=0;i<toIndx;i++){
		if($(left).options[fromIndx].value == $(right).options[i].value){
			return false;
		}
	}
	
	$(right).options[$(right).options.length] = new Option($(left).options[fromIndx].text,$(left).options[fromIndx].value);
	$(left).options[fromIndx] = null;
	if($(left).options.length > 0){
		$(left).options[0].selected = true;
		//$("delBj").disabled = false;
	}else{		
		//$("addBj").disabled = true;
	}
	if($(right).options.length > 0){
		$(right).options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}

/**
*�ұ߿��ֵ����ӵ���߿���ӳɹ�ͬʱ��ɾ��ԭ��ֵ��
*luojw
*20100628
*/
function addLeftFrame(left,right){

	dwr.engine.setAsync(false);
	
	var toIndx = $(right).selectedIndex;
	var fromIndx = $(left).options.length;
	if(toIndx < 0){
		return false;
	}
	for(var i=0;i<fromIndx;i++){
		if($(right).options[toIndx].value == $(left).options[i].value){
			$(right).options[toIndx] = null;
			return false;
		}
	}
	$(left).options[$(left).options.length] = new Option($(right).options[toIndx].text,$(right).options[toIndx].value);
	$(right).options[toIndx] = null;

	if($(left).options.length > 0){
		$(left).options[0].selected = true;
	}
	
	dwr.engine.setAsync(true);
}