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

/**
*��߿��ֵ����ӵ��ұ߿���ӳɹ�ͬʱ��ɾ��ԭ��ֵ��
*luojw
*20100628
*/
function addAllRightFrame(left,right){

	dwr.engine.setAsync(false);

	var obj =  document.getElementById(left);
	
	var count = 0; 
	
	for(var i=0;i<obj.options.length;i++){
		if(obj.options[i].selected){
			count++;
		}
	}
    
	if(count==0){
		alert('��������ѡ��һ�����ݣ�');
		return false;
	}
	
	var leftV = "";
	var leftT = "";   
    var n = $(right).options.length;

	for(i=0;i<obj.options.length;i++){
	
		if(obj.options[i].selected){

			leftV=obj.options[i].value;
			leftT=obj.options[i].text;

			if(leftV == ""){
				continue;
			}
			
			var flag = true;
			
			for(var j=0;j<n;j++){
				if(leftV == $(right).options[j].value){
					flag = false;
				}
			}
			
			if(flag){
				$(right).options[$(right).options.length] = new Option(leftT,leftV);
				$(left).options[i]=null;
				i--; 
			}                
		}
	}   
	dwr.engine.setAsync(true);
}

/**
*�ұ߿��ֵ����ӵ���߿���ӳɹ�ͬʱ��ɾ��ԭ��ֵ��
*luojw
*20100628
*/
function addAllLeftFrame(left,right){

	dwr.engine.setAsync(false);

	var obj =  document.getElementById(right);
	
	var count = 0; 
	
	for(var i=0;i<obj.options.length;i++){
		if(obj.options[i].selected){
			count++;
		}
	}
    
	if(count==0){
		alert('��������ѡ��һ�����ݣ�');
		return false;
	}
	
	var rightV = "";
	var rightT = "";  
	 
    var n = $(left).options.length;

	for(i=0;i<obj.options.length;i++){
	
		if(obj.options[i].selected){

			rightV=obj.options[i].value;
			rightT=obj.options[i].text;

			if(rightV == ""){
				continue;
			}
			
			var flag = true;
			
			for(var j=0;j<n;j++){
				if(rightV == $(left).options[j].value){
					flag = false;
				}
			}
			
			if(flag){
				$(left).options[$(left).options.length] = new Option(rightT,rightV);
				$(right).options[i]=null;
				i--; 
			}                
		}
	}   
	dwr.engine.setAsync(true);
}

function printZhszcphzb(){
	var xxdm = val("xxdm");
	var url = "";
	if(xxdm == "10352"){//��ˮѧԺ
		url = "pjpyLsxy.do?method=printZhszcphzb";
		if(val('xn') == ""){
			alert("��ѡ��ѧ�꣡");
			return false;
		}
		if(val('xq') == ""){
			alert("��ѡ��ѧ�ڣ�");
			return false;
		}
		if(val('bj') == ""){
			alert("��ѡ��༶��");
			return false;
		}
		//��ӡ
		url += "&xn=" 
		url += val("xn");
		url += "&xq=" 
		url += val("xq");
		url += "&xqmc=" 
		url += selText("xq");
		url += "&bjdm=" 
		url += val("bj");
		url += "&bjmc=" 
		url += selText("bj");
		
		showOpenWindow(url,800,600);
	}
}

/**
 * �任ѡ�
 * @param obj
 * */
function changeCard(obj){
	this.parentNode.className = 'ha';
}


/**
 * ��ʾ��һ��ѡ�
 * */
function loadFirstCard(){
	var tab = document.getElementById("firstCard").value;
	ele(tab).parentNode.className = 'ha';
}

function zhcpjxjsb(){
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			pkValues[num] = checkBoxArr[i].value;
			num +=1;
			flag = true;
			
		}
	}
	if (flag){
		//���ѧ���Ƿ��Ѿ��ϱ�����ѧ��
		zhcpjxjDWR.checkJxjsb(pkValues,function(data){
			if(data){
				viewTempDiv('��ѧ���ϱ�','jxjDiv',300, 150);
			}else{
				alert("��ѡ���ѧ���в����Ѿ��ϱ��˽�ѧ�������ϱ�����ȡ���ϱ���");
				return false;
			}			
		});
				
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

function zhcpjxjsbCommit(url){
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pkValues[num++] = checkBoxArr[i].value;
		}
	}
	
	if (flag){
		//���ѧ���Ƿ��Ѿ��ϱ�����ѧ��
		zhcpjxjDWR.checkJxjsb(pkValues,function(data){
			if(data){
				if (confirm('ȷ��Ҫ�ϱ���ѡ���ѧ����')){
					document.forms[0].action = url;
					document.forms[0].submit();
					hiddenMessage(true,true);
					if ($("pt")) {
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
				}
			}else{
				alert("��ѡ���ѧ���в����Ѿ��ϱ��˽�ѧ�������ϱ�����ȡ���ϱ���");
				return false;
			}			
		});		
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

function zhcpqxjxjsb(url){	
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pkValues[num++] = checkBoxArr[i].value;
		}
	}
	
	if (flag){
		//��⽱ѧ���ϱ��Ƿ�ͨ��ѧУ���		
		zhcpjxjDWR.checkQxJxjsb(pkValues,function(data){
			if(data){
				if (confirm('ȷ��Ҫȡ���ϱ���ѡ���ѧ����')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
				}
			}else{
				alert("��ѡ��Ĳ���ѧ���ϱ���ѧ���Ѿ�ͨ��ѧУ��ˣ���ʱ����ȡ���ϱ���");
				return false;
			}			
		});		
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

function jxjsb(){
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			pkValues[num] = checkBoxArr[i].value;
			num +=1;
			flag = true;
			
		}
	}
	setVal('sbNum',num);
	//�ж��Ƿ�ѡ����ѧԺ
	if(val('xy') == '' || val('xn')=='' || val('nj') == ''){
		alert("��ѡ��ѧ�ꡢ�꼶��"+jQuery("#xbmc").val()+"��");
		return false;
	}
	if (flag){
		//���ѧ���Ƿ��Ѿ��ϱ�����ѧ��
		var paramArr = {xn:val('xn'), nd:val('nd'), xq:val('xq')};
		jxjDWR.checkJxjsb(pkValues,paramArr,function(data){
			if(data){
				setVal('select_jxjdm','');
				viewTempDiv('��ѧ���ϱ�','jxjDiv',300, 150);
			}else{
				alert("��ѡ���ѧ���в����Ѿ��ϱ��˽�ѧ�������ϱ�����ȡ���ϱ���");
				return false;
			}			
		});
		//TODO ��⽱ѧ�������Ƿ񳬹�����		
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

function jxjsbCommit(url){
	if(ele('jxjjeTr').style.display != 'none' && val('jxjje')==''){
		alert('����д��ѧ���');
		return false;
	}
	if(isNaN(val('jxjje'))){
		alert('��ѧ��������������֣�');
		return false;
	}
	//�ж������Ƿ�����
	var input = {nj:val('nj'),xydm:val('xy'),jxjdm:val('select_jxjdm'),xn:val('xn'),jxjje:val('jxjje')};
	var num = 0;
	num = val('sbNum');
	jxjDWR.checkXyjxjsbrs(input,num,function(data){
		if(data>=0){
			//�жϽ�ѧ�����Ƿ�����
			if(val('jxjje')!= ''){
				jxjDWR.checkJxjje(input,num,function(data){
					if(data<0){
						alert("��ѧ�������"+jQuery("#xbmc").val()+"���ܽ�������" + Math.abs(data) +"Ԫ");
						return false;
					}else{
							if (confirm('ȷ��Ҫ�ϱ���ѡ���ѧ����')){		
								url += "&sb_jxjdm=" + val('select_jxjdm');
								url += "&sb_jxjje=" + val('jxjje');
								
								document.forms[0].action = url;
								document.forms[0].submit();
								hiddenMessage(true,true);
								if ($("pt")) {
									BatAlert.showTips('���ڲ�������ȴ�...');
								}
							}
					}
				});
			}else{
				if (confirm('ȷ��Ҫ�ϱ���ѡ���ѧ����')){		
					url += "&sb_jxjdm=" + val('select_jxjdm');
					url += "&sb_jxjje=" + val('jxjje');
					
					document.forms[0].action = url;
					document.forms[0].submit();
					hiddenMessage(true,true);
					if ($("pt")) {
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
				}
			}
			
			
		}else{
			alert(jQuery("#xbmc").val()+"����������������������������" + Math.abs(data) + "�ˣ�");
			return false;
		}
	});	
}

function qxjxjsb(url){	
	var checkBoxArr = document.getElementsByName("primarykey_cbv");
	var flag = false;
	var pkValues = [];
	var num = 0;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pkValues[num++] = checkBoxArr[i].value;
		}
	}
	if (flag){
		//��⽱ѧ���ϱ��Ƿ�ͨ��ѧУ���
		if(val('xn') == ''){
			alert('��ѡ��ѧ�꣡');
			return false;
		}
		var paramArr = {xn:val('xn'), nd:val('nd'), xq:val('xq')};
		jxjDWR.checkQxJxjsb(pkValues,paramArr,function(data){
			if(data){
				if (confirm('ȷ��Ҫȡ���ϱ���ѡ���ѧ����')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
				}
			}else{
				alert("��ѡ��Ĳ���ѧ���ϱ���ѧ���Ѿ�ͨ��������ˣ���ʱ����ȡ���ϱ���");
				return false;
			}			
		});		
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}

function jxjjesz(){
	if(val('xn') == ''){
		alert('��ѡ��ѧ�꣡');
		return false;
	}
	//�ж��Ƿ�������
	if(ele('rsTable') == null || ele('rsTable') == undefined){
		alert('û�пɱ�������ݣ�');
		return false;
	}
	refreshForm('pjpyTybXysz.do?method=jxjjesz&act=save');
}
