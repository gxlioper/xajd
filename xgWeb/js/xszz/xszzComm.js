var count = 1;
var max = 0;

//���Ӽ���
function trAdd(the_tab,type,numAdd){
	dwr.engine.setAsync(false);
	var len = document.getElementById(the_tab).rows.length+1;
	var num = $(numAdd).value;
	var mklx = $("mklx").value;

	var cellfu = new Array();

	if( mklx == "sq" || mklx == "xg" || mklx == "sh" || mklx == "jg"){
		cellfu = getJtcy();
	}else{
		//�Ƿ��漰���
		var sfje = getSfje();
		//�������
		var jelx = getJelx();
		
		cellfu = getCellfu(sfje,jelx);
	}
	
	count=len;  
	
	if(type=='add'){       
		max++;
		DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
	}else{
		if(num==""||num==null){	
			return false;
		}
		for(i=count;i<=num;i++){        
			max++;
			DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
			count++;
		}
		$(numAdd).value = "";
	}    
	dwr.engine.setAsync(true);
}

//ɾ����ѡTR
function trDel(the_tab,delRow){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    var checkbox = document.getElementsByName(delRow);
    var checkArray = new Array();
    var size = 0
    for(i=0;i<checkbox.length;i++){
    	if(checkbox[i].checked == true){
    		checkArray[size++] = eval(checkbox[i].value);
    	}	
    }
    
    if(checkArray.length == 0){
    	alert('��û��ѡ��Ҫ�����ļ�¼����ѡ��');
    	return false;
    }
    	
   
    if(confirm('ȷ��Ҫɾ��ѡ�еļ�¼��')){
    	for(i=0;i<checkArray.length;i++){
    		tabobj.deleteRow(checkArray[i]-1);
    		for(j=0;j<checkArray.length;j++){
    			checkArray[j] += -1;
    		}
   	 }
    
   	 for(i=0;i<checkbox.length;i++){
    		checkbox[i].value=i+1;
  	  }
    }
}

//ɾ������TR
function trDelAll(the_tab,numDel){
	var tabobj = document.getElementById(the_tab);
	var length = tabobj.rows.length;
	var num = $(numDel).value; 
	if(length==0){
		$(numDel).value = "";
		return false;     
	}
	if(num==""||num==null){	
		return false;
	}
	if(num>length){
		num = length;
	}
	if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){ 
		for(i=1;i<=num;i++){           
			length--;
		}
		for(i=1;i<=num;i++){
			length--;
			tabobj.deleteRow(tabobj.rows.length-1);
		}
	}
	$(numDel).value = "";
}	

//����TR
function getCellfu(sfje,jelx){
	var cellfu = new Array();
	if(sfje == '����Ҫ'){
		cellfu =[		
		function(data){
			var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
			htmlText += "<input type='checkbox' name='delRow1' value='"+count+"' />"
			return htmlText;
		},
		function(data){	    
			var htmltext = "<input type='text' style='width:100%' name='nojemc' id='jbmc1" + max + "' maxlength='25'/>";
	 
	    	return htmltext;
	    }
		];
	}else{
		if(jelx == "ȷ��ֵ"){
			cellfu =[		
			function(data){
				var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
				htmlText += "<input type='checkbox' name='delRow2' value='"+count+"' />"
				return htmlText;
			},
			function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='qdjemc' id='jbmc2" + max + "' maxlength='25'/>";
		 
		    	return htmltext;
		    },
		    function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='fjqdje' id='je" + max + "' ";
		 			htmltext+= " onkeydown='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'";
		 			htmltext+= " onmousedown='return onlyNum(this,5)'/>";
		    	return htmltext;
		    }
			];
		}else{
			cellfu =[		
			function(data){
				var htmlText = "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+max+"'>";
				htmlText += "<input type='checkbox' name='delRow3' value='"+count+"' />"
				return htmlText;
			},
			function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='qjjemc' id='jbmc3" + max + "' maxlength='25'/>";
		 
		    	return htmltext;
		    },
		    function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='fjxxje' id='jexx" + max + "' ";
					htmltext+= " onkeydown='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'";
		 			htmltext+= " onmousedown='return onlyNum(this,5)'/>";
		    	return htmltext;
		    },
		    function(data){	    
				var htmltext = "<input type='text' style='width:100%' name='fjsxje' id='jesx" + max + "' ";
		 			htmltext+= " onkeydown='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'";
		 			htmltext+= " onmousedown='return onlyNum(this,5)'/>";
		    	return htmltext;
		    }
			];
		}
	}

	return cellfu;
}

//�Ƿ��漰���
function getSfje(){
	var je = $("je");
	var bje = $("bje");
	var sfje = "";
	
	if(je.checked){
		sfje = je.value;
	}else{
		sfje = bje.value;
	}
	return sfje;
}

//�Ƿ�ּ�
function getSfFj(){
	var fj = $("fj");
	var bfj = $("bfj");
	var sffj = "";
	
	if(fj.checked){
		sffj = fj.value;
	}else{
		sffj = bfj.value;
	}
	return sffj;
}

//�������
function getJelx(){
	var qdz = $("qdz");
	var qj = $("qj");
	var jelx = "";
	
	if(qdz.checked){
		jelx = qdz.value;
	}else{
		jelx = qj.value;
	}
	return jelx;
}

//showidArr Ҫ��ʾԪ�ص�id���ϣ�hiddenidArr Ҫ����Ԫ�ص�id����
function changeDisplay(showidArr,hiddenidArr){
	for(var i=0; i<showidArr.length; i++){
		if($(showidArr[i])){
			$(showidArr[i]).style.display = "";
		}
	}
	for(var i=0; i<hiddenidArr.length; i++){
		if($(hiddenidArr[i])){
			$(hiddenidArr[i]).style.display = "none";
		}
	}
}

//����ѡ���ж�ѡ����ʾ��ЩTR
function showXmwh(){

	var showEle = [];
	var hiddenEle = [];
	
	//�Ƿ���Ҫ�ּ�
	if(getSfFj() == "�ּ�"){
		//�Ƿ���Ҫ���
		if(getSfje() == "��Ҫ"){		
			//����Ƿ�ȷ��ֵ
			if(getJelx() == "ȷ��ֵ"){
				showEle = ["jelxTr","fjTr","qdje"];	
				hiddenEle = ["qdjeTr","qjTr","noje","qjje"];		
			}else{
				showEle = ["jelxTr","fjTr","qjje"];	
				hiddenEle = ["qdjeTr","qjTr","noje","qdje"];	
			}
		}else{
			showEle = ["fjTr","noje"];	
			hiddenEle = ["jelxTr","qdjeTr","qjTr","qdje","qjje"];		
		}	
	}else{
		//�Ƿ���Ҫ���
		if(getSfje() == "��Ҫ"){
			
			//����Ƿ�ȷ��ֵ
			if(getJelx() == "ȷ��ֵ"){
				showEle = ["jelxTr","qdjeTr"];	
				hiddenEle = ["qjTr","fjTr","noje","qjje","qdje"];		
			}else{
				showEle = ["jelxTr","qjTr"];	
				hiddenEle = ["qdjeTr","fjTr","noje","qjje","qdje"];	
			}
			
		}else{
			hiddenEle = ["jelxTr","qdjeTr","qjTr","fjTr","noje","qjje","qdje"];		
		}
	}

	changeDisplay(showEle,hiddenEle);
}

//����������
function checkKns(){
	var kns = document.getElementsByName("knsCk");
	var iskns = "";
	
	for(var i=0;i<kns.length;i++){
		if(kns[i].checked==true){
			iskns +=kns[i].value + "!!@@!!";
		}
	}
	
	if($("iskns")){
		$("iskns").value = iskns;
	}
}

//ҳ���ʼ��
function onshow(){
	//����ѡ���ж�ѡ����ʾ��ЩTR
	showXmwh();
	//�Ƿ���ʾ��˼���
	showShjb();
	//�Ƿ���ʾ�������Ƽ���
	showRsjb();
	//��ʾ���Ƽ���
	showKzjb();
	//����checkBox
	kjChecked();
	//���ü����б�
	showJbList();
	//��ʾ����������
	showKnstj();
	//��ʾ�ۺϷ�����
	setZhfTj();
}

//�Ƿ���Ҫ������
function setKnsJb(){

	var iskns = "";
	var knsSelect = $("knsSelect").value;
	
	if($("iskns")){
		iskns = $("iskns").value;
	}	

	var kns = document.getElementsByName("knsCk");
	
	for(var i=0;i<kns.length;i++){
		if(knsSelect != "������"){
			$(kns[i]).disabled = false;
		}else{
			$(kns[i]).disabled = true;
		}
	}
	
	if($("iskns") && knsSelect == "������"){
		$("iskns").value = "";
	}
}

//�����ۺϷ�����
function setZhfTj(){
	var zyf = "";
	
	if($("zyf")){
		zyf = $("zyf").value;
		if(zyf != "������"){
			$("zyftj").value = zyf.substr(0,1);
			$("zyfz").value = zyf.substr(1,zyf.length-1);
		}
	}
	
	var zhf = "";
	
	if($("zhf")){
		zhf = $("zhf").value;
		if(zhf != "������"){
			$("zhftj").value = zhf.substr(0,1);
			$("zhfz").value = zhf.substr(1,zhf.length-1);
		}
	}
	
	
	//------------ƽ���ɼ� 2010.12.7 qph----------------------
	var pjcj = "";
	
	if($("pjcj")){
		pjcj = $("pjcj").value;
		if(pjcj != "������"){
			$("pjcjtj").value = pjcj.substr(0,1);
			$("pjcjz").value = pjcj.substr(1,pjcj.length-1);
		}
	}
	//-------------ƽ���ɼ� end--------------------------------
	
	//-------------��Ȩƽ���֡���Ȩƽ������ qph 2012.3.8-----------
	var jqpjf = "";
	
	if($("jqpjf")){
		jqpjf = $("jqpjf").value;
		if(jqpjf != "������"){
			$("jqpjftj").value = jqpjf.substr(0,1);
			$("jqpjfz").value = jqpjf.substr(1,jqpjf.length-1);
		}
	}
	
	
	var jqpjjd = "";
	
	if($("jqpjjd")){
		jqpjjd = $("jqpjjd").value;
		if(jqpjjd != "������"){
			$("jqpjjdtj").value = jqpjjd.substr(0,1);
			$("jqpjjdz").value = jqpjjd.substr(1,jqpjjd.length-1);
		}
	}
	//---------------��Ȩƽ���֡���Ȩƽ������ end----------------------
}

//��ʾ����������
function showKnstj(){
	if($("iskns")){
	
		var iskns = $("iskns").value;
		
		if(iskns != ""){
			$("knsSelect").value = "��";
			var knsjb = iskns.split("!!@@!!");

			for(var i=0 ;i< knsjb.length;i++){
				var id = knsjb[i];	
				if($(id)){
					$(id).checked = true;
				}
			} 
			var kns = document.getElementsByName("knsCk");
			for(var i=0;i<kns.length;i++){
				$(kns[i]).disabled = false;
			}
		}
	}
}

//�жϰ༶����Ƿ�ѡ��
function clickBjsh(){

	var bjsh = $("bjsh");
	
	if(bjsh.checked){
		$("bzrsh").disabled = false;
		$("fdysh").disabled = false;
		$("fdysh").checked = true;
	}else{
		$("bzrsh").disabled = true;
		$("fdysh").disabled = true;
		$("bzrsh").checked = false;
		$("fdysh").checked = false;
	}
}

//�Ƿ���ʾ��˼���
function showShjb(){
	var shjb=$("shjb").value;
	if(shjb != "�������"){
		$("shjbTr").style.display = "";
	}else{
		$("shjbTr").style.display = "none";
	}
}

//�Ƿ���ʾ�������Ƽ���
function showRsjb(){
	var rsjb=$("rskz").value;
	if(rsjb == "��Ҫ"){
		$("rsjbTr").style.display = "";
	}else{
		$("rsjbTr").style.display = "none";
	}
}

//��ʾ���Ƽ���
function showKzjb(){
	var rsjb=$("rskz").value;
	if(rsjb == "��Ҫ"){
		$("kzjb").disabled = false;
	}else{
		$("kzjb").disabled = true;
	}
}

//������Ŀά��
function saveXmwh(){
	if(saveXzt() && checkShjb() && isFhtj() && isPkNull('xmmc')){
		if (confirm("ȷ��������������?")) {
			saveUpdate('/xgxt/commXszz.do?method=xmwhUpdate&doType=save','xmmc');
		}
	}
}

//�ж���Ŀ�����Ƿ���Ϲ淶
function isFhtj(){
	//�Ƿ�������
	if($("knsSelect") && $("knsSelect").value == "��"){
		var obj = document.getElementsByName("knsCk");
		var flag = false;
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				flag = true;
			}
		}
		if(!flag){
			alert("����Ŀ����Ϊ��Ҫ������������ƣ���ѡ�����ļ���");
			return false;
		}
	}
	//����������
	if($("zyftj")){
		if($("zyftj").value != "" && $("zyfz").value == ""){
			alert("�����������ֵ�����ֵ");
			return false;
		}
		if($("zyftj").value != "" && $("zyfz").value != ""){
			$("zyf").value = $("zyftj").value + $("zyfz").value;
		}else{
			$("zyf").value = "������";
		}
	}
	//�ۺϷ�����
	if($("zhftj")){
		if($("zhftj").value != "" && $("zhfz").value == ""){
			alert("�����������ֵ�����ֵ");
			return false;
		}
		if($("zhftj").value != "" && $("zhfz").value != ""){
			$("zhf").value = $("zhftj").value + $("zhfz").value;
		}else{
			$("zhf").value = "������";
		}
	}
	
	
	//ƽ���ɼ�����-------2010.12.7 qph ---------------
	if($("pjcjtj")){
		if($("pjcjtj").value != "" && $("pjcjz").value == ""){
			alert("������ƽ���ɼ�������ֵ");
			return false;
		}
		if($("pjcjtj").value != "" && $("pjcjz").value != ""){
			$("pjcj").value = $("pjcjtj").value + $("pjcjz").value;
		}else{
			$("pjcj").value = "������";
		}
	}
	//------------ƽ���ɼ� end--------------------------
	
	//--��Ȩƽ���֡���Ȩƽ������---qph---2012.3.8-----------------------
	if($("jqpjftj")){
		if($("jqpjftj").value != "" && $("jqpjfz").value == ""){
			alert("������ƽ���ɼ�������ֵ");
			return false;
		}
		if($("jqpjftj").value != "" && $("jqpjfz").value != ""){
			$("jqpjf").value = $("jqpjftj").value + $("jqpjfz").value;
		}else{
			$("jqpjf").value = "������";
		}
	}
	
	if($("jqpjjdtj")){
		if($("jqpjjdtj").value != "" && $("jqpjjdz").value == ""){
			alert("������ƽ���ɼ�������ֵ");
			return false;
		}
		if($("jqpjjdtj").value != "" && $("jqpjjdz").value != ""){
			$("jqpjjd").value = $("jqpjjdtj").value + $("jqpjjdz").value;
		}else{
			$("jqpjjd").value = "������";
		}
	}
	//--��Ȩƽ���֡���Ȩƽ������---qph---2012.3.8--end---------------------
	
	return true;
}

//�ж���˼����Ƿ����
function checkShjb(){
	var shjb = $("shjb").value;
	
	if(shjb == "�������"){
		return true;
	}
	var bjsh = $("bjsh");
	var xysh = $("xysh");
	var xxsh = $("xxsh");
	
	var n = 0;

	if(bjsh.checked){
		n++;
	}
	
	if(xysh.checked){
		n++;
	}
	
	if(xxsh.checked){
		n++;
	}
	
	if(shjb == "һ�����" && n>1){
		alert("��˼���Ϊһ��,���ֻ�ܹ�ѡһ����˼�����ȷ�ϣ�");
		return false;
	}
	
	if(shjb == "�������" && n>2){
		alert("��˼���Ϊ����,���ֻ�ܹ�ѡ������˼�����ȷ�ϣ�");
		return false;
	}

	return true;
}

//��ѡ�༶���
function clickBzrFdy(){
	var bzrsh = $("bzrsh");
	var fdysh = $("fdysh");
	if(bzrsh.checked || fdysh.checked ){
		$("bjsh").checked = true;
	}
	
}

//����ּ����
function saveXzt(){
	
	//��Ҫ���
	var sfje = "";
	if($("je").checked){
		sfje = $("je").value;
	}else{
		sfje = $("bje").value;
	}
	//�Ƿ���Ҫ�ּ�
	var sffj = "";
	if($("fj").checked){
		sffj = $("fj").value;
	}else{
		sffj = $("bfj").value;
	}
	//�������
	var jelx = "";
	if($("qdz").checked){
		jelx = $("qdz").value;
	}else{
		jelx = $("qj").value;
	}
	
	if(sfje == "��Ҫ"){		//��Ҫ���
		if(sffj == "���ּ�"){		//���ּ�
			if(jelx == "ȷ��ֵ"){ //���ȷ��ֵ
				var je = $("nofjje").value;
				if(je == ""){
					alert("���ܶ�Ϊ�գ���ȷ��!");
					$("nofjje").focus();
					return false;
				}
			}else if(jelx == "����"){//�������ֵ
				var xxje = $("nofjxx").value;
				var sxje = $("nofjsx").value;
				
				if(xxje != "" && sxje != "" && parseInt(sxje) < parseInt(xxje)){
					alert("���޽���С�����޽���ȷ�ϣ�");
					return false;
				}
				
				if(xxje == "" && sxje == ""){
					alert("��������޲��ܶ�Ϊ�գ���ȷ��!");
					return false;
				}
			}
		}else{//��Ҫ�ּ�
			var the_tab = "";
			
			if(jelx == "ȷ��ֵ"){
				the_tab = "qdnr";
			}else{
				the_tab = "qjnr";
			}
			var tabobj = document.getElementById(the_tab);
			var rowLen = max;
			var nrLen = tabobj.rows.length;
			
			if(nrLen == 0){
				alert("�Ѿ�ѡ��ּ����������ݲ���Ϊ�գ���ȷ�ϣ�");
				return false;
			}
			
			for(var i=0;i<=rowLen;i++){
			
				if(jelx == "ȷ��ֵ"){//���ȷ��ֵ
				
					var mcId = "jbmc2"+i;
					var jeId = "je"+i;
					
					if($(mcId)){
						if($(mcId).value == ""){
							alert("��������Ϊ�գ���ȷ��!");
							$(mcId).focus();
							return false;
						}
					}
						
					if($(jeId)){
						if($(jeId).value == ""){
							alert("���Ϊ�գ���ȷ��!");
							$(jeId).focus();
							return false;
						}
						num=$(jeId).value;
						if(num.length>1 && num.substring(0,1)=="0"){
							alert("������벻��ȷ����ȷ��!");
							$(jeId).focus();
							return false;
						}
					}
				}else{//�������ֵ
				
					var mcId = "jbmc3"+i;
					var xxId = "jexx"+i;
					var sxId = "jesx"+i;

					if($(mcId)){
						if($(mcId).value == ""){
							alert("��������Ϊ�գ���ȷ��!");
							$(mcId).focus();
							return false;
						}
					}
						
					if($(xxId) && $(sxId)){
						var xxje = $(xxId).value;//���޽��
						var sxje = $(sxId).value;//���޽��
						if(xxje == "" && sxje == ""){
							alert("��������޲��ܶ�Ϊ�գ���ȷ��!");
							$(xxId).focus();
							return false;
						}
						if(xxje != "" && sxje != "" && parseInt(sxje) < parseInt(xxje)){
							alert("���޽���С�����޽���ȷ�ϣ�");
							$(xxId).focus();
							return false;
						}
					}
				}
			}
		}
	}else{//����Ҫ���
		if(sffj == "�ּ�"){	//��Ҫ�ּ�
		
			var the_tab = "jbnr";
			var tabobj = document.getElementById(the_tab);
			var rowLen = max;
			var nrLen = tabobj.rows.length;
			
			if(nrLen == 0){
				alert("�Ѿ�ѡ��ּ����������ݲ���Ϊ�գ���ȷ�ϣ�");
				return false;
			}
			
			for(var i=0;i<=rowLen;i++){
				var mcId = "jbmc1"+i;
				
				if($(mcId)){
					if($(mcId).value == ""){
						alert("��������Ϊ�գ���ȷ��!");
						$(mcId).focus();
						return false;
					}
				}
			}
		}
	}
	
	return true;
}

//checked�ؼ�
function kjChecked(){

	//ѧԺ���checkbox
	var xyshCheck = $("xyshCheck").value;

	if(xyshCheck == "��"){
		$("xysh").checked = true;
	}
	
	//ѧУ���checkbox
	var xxshCheck = $("xxshCheck").value;

	if(xxshCheck == "��"){
		$("xxsh").checked = true;
	}
	
	//�༶���checkbox
	var bzrshCheck = $("bzrshCheck").value;
	var fdyshCheck = $("fdyshCheck").value;

	if(bzrshCheck == "��" || fdyshCheck == "��"){
		$("bjsh").checked = true;
	}
	
	if(bzrshCheck == "��" ){
		$("bzrsh").checked = true;
	}
	
	if(fdyshCheck == "��" ){
		$("fdysh").checked = true;
	}	
	
	//ѧԺ����checkbox
	var xykzCheck = $("xykzCheck").value;

	if(xykzCheck == "��"){
		$("xykz").checked = true;
	}
	
	//ѧУ����checkbox
	var xxkzCheck = $("xxkzCheck").value;

	if(xxkzCheck == "��"){
		$("xxkz").checked = true;
	}
	
	//�༶����checkbox
	var bzrkzCheck = $("bzrkzCheck").value;
	var fdykzCheck = $("fdykzCheck").value;

	if(bzrkzCheck == "��" || fdykzCheck == "��"){
		$("bjkz").checked = true;
	}
}

//���ü����б�
function showJbList(){

	var xmdm = $("xmdm").value;
	//��Ҫ���
	var sfje = "";
	if($("je").checked){
		sfje = $("je").value;
	}else{
		sfje = $("bje").value;
	}
	//�Ƿ���Ҫ�ּ�
	var sffj = "";
	if($("fj").checked){
		sffj = $("fj").value;
	}else{
		sffj = $("bfj").value;
	}
	//�������
	var jelx = "";
	if($("qdz").checked){
		jelx = $("qdz").value;
	}else{
		jelx = $("qj").value;
	}
	
	if(xmdm != ""){
	
		dwr.engine.setAsync(false);
		
		var tableName="xszz_xmfjqkb";
		var colList =["fjmc","fjxxje","fjsxje","fjqdje"];
		var pk = "xmdm";
		var pkValue = xmdm;
		var query =" ";
		
		getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){
				if(sfje == "��Ҫ"){		//��Ҫ���
					if(sffj == "���ּ�"){ //���ּ�
						if(jelx == "ȷ��ֵ"){ //���ȷ��ֵ
							var quje = data[0].fjqdje;
							if(quje != null){
								$("nofjje").value = quje;
							}
						}else if(jelx == "����"){//�������ֵ
							var xxje = data[0].fjxxje;
							var sxje = data[0].fjsxje;
							if(xxje != null){
								$("nofjxx").value = xxje;
							}
							if(sxje != null){
								$("nofjsx").value = sxje;
							}
						}
					}else{//��Ҫ�ּ�
						var btnId = "";
						var tbId = "";
						if(jelx == "ȷ��ֵ"){
							btnId = "qdnrAdd";
							tbId = "qdnr";
						}else{
							btnId = "qjnrAdd";
							tbId = "qjnr";
						}
						
						var rowLen = data.length;
						$(btnId).value = rowLen;
						trAdd(tbId,'madd',btnId);		
						
						for(var i=1;i<=rowLen;i++){
							if(jelx == "ȷ��ֵ"){//���ȷ��ֵ
							
								var mcId = "jbmc2"+i;
								var jeId = "je"+i;
								
								var jbmc = data[i-1].fjmc;
								var qdje = data[i-1].fjqdje;
								
								//��������
								if($(mcId)){
									if(jbmc != null){
										$(mcId).value = jbmc;
									}
								}
								
								//ȷ�����
								if($(jeId)){
									if(qdje != null){
										$(jeId).value = qdje;
									}
								}
							}else{//�������ֵ
								
								var mcId = "jbmc3"+i;
								var xxId = "jexx"+i;
								var sxId = "jesx"+i;
				
								var jbmc = data[i-1].fjmc;
								var xxje = data[i-1].fjxxje;
								var sxje = data[i-1].fjsxje;
									
								//��������
								if($(mcId)){
									if(jbmc != null){
										$(mcId).value = jbmc;
									}
								}
								//���޽��
								if($(xxId)){
									if(xxje != null){
										$(xxId).value = xxje;
									}
								}
								//���޽��
								if($(sxId)){
									if(sxje != null){
										$(sxId).value = sxje;
									}
								}
							}
						}
					}
				}else{//����Ҫ���
					if(sffj == "�ּ�"){	//��Ҫ�ּ�
						var tbId = "jbnr";
						var btnId = "jbnrAdd";
						var rowLen = data.length;
						$(btnId).value = rowLen;
						trAdd(tbId,'madd',btnId);		
						
						for(var i=1;i<=rowLen;i++){
	
							var mcId = "jbmc1"+i;
							var jbmc = data[i-1].fjmc;
							
							if($(mcId)){
								if(jbmc != null){
									$(mcId).value = jbmc;
								}
							}
						}
					}	
				}
			}
		});
			
		dwr.engine.setAsync(true);
		
	}
}

//�����ʾ�ش�ش����DIV
function showRsszDiv(){
	
//	var d_width = document.body.clientWidth;
//	var d_height = document.body.clientHeight ;
//	var d_left = 0;
//	var d_top = 0;
//	var d_color = "#EEF4F9";
//	var d_width_top = 250;
//	var d_height_top = 120;
//	var d_left_top = (d_width - d_width_top) / 2.5;
//	var d_top_top = (d_height - d_height_top) / 3.5;
//	var d_color_top = "#EEF4F9";
//	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
//	dd_html += "</div>";
//	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
//	dd_html += "<table width='280' class='tbstyle'>";
//	dd_html += "<thead>";
//	dd_html += "<tr height='30'>";
//	dd_html += "<td colspan='2' align='center'>";
//	dd_html += "-----------------------����ֵ----------------------";
//	dd_html += "</td>";
//	dd_html += "</tr>";
//	dd_html += "</thead>";
//	dd_html += "<tbody>";
	
	var dd_html="";
	dd_html += "<div class=\"tab\">";
	dd_html += "<table class=\"formlist\">";
	dd_html += "<thead>";
	dd_html += "<tr>";
	dd_html += "<th colspan='2'>";
	dd_html += "<span> ����ֵ </span>";
	dd_html += "</th>";
	dd_html += "</tr>";
	dd_html += "</thead>";
	
	dd_html += "<tbody>";
	
	
	//���䷽ʽΪ����
	if(getFpfs()){
		dd_html += "<tr height='30'>";
		dd_html += "<td align='left' colspan='2'><div align='left' width='100%'>";
		dd_html += "ע�������䲿������ֵΪ�������䲿��������*����";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th style='width:20%'>";
		dd_html += "����";
		dd_html += "</th>";
		dd_html += "<td >";
		dd_html += "<input type='text' name='bl' id='bl' onblur='chMax(this,100)'";
		dd_html += "onkeypress='return sztzNumInputValue(this,5,event)' style='width:30%;ime-mode:disabled'/>%";
		dd_html += "</td>";
		dd_html += "</tr>";
	}else{
		dd_html += "<tr height='30'>";
		dd_html += "<td align='left' colspan='2'><div align='left' width='100%'>";
		dd_html += "ע������������ƽ���������ѡ����������ѡ������<br/>";
		dd_html += "ƽ����������в��š�����������С�ڷ���"+jQuery("#xbmc").val()+"����<br/>";
		dd_html += "����ƽ������ֵС��1ʱ��Ĭ�Ϸ���1��</div>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr height='30'>";
		dd_html += "<th style='width:20%'>";
		dd_html += "��������";
		dd_html += "</th>";
		dd_html += "<td>";
		dd_html += "<input type='text' name='fprs' id='fprs'";
		dd_html += "onkeypress='return onlyNum(this,5)' style='width:30%;ime-mode:disabled'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		
		
	}
	dd_html +=" </tbody>";
	dd_html +=" <tfoot>";
	dd_html +="<tr>";
	dd_html +="<td colspan=\"2\">";
	dd_html +="<div class=\"btn\">";
	
	dd_html +="<button type='button' onclick='saveRssz();'>";
	dd_html +="ȷ��";
	dd_html +="</button>";
	dd_html += "<button type='button'  class='button2' onclick='hiddenMessage(true,true);'>ȡ��</button>";
	dd_html +="</div>";
	dd_html +="</td>";
	dd_html +="</tr>";
	dd_html +="</tfoot>";
	dd_html += "</table>";
	
	
	if(!$("tsxxDiv")){
		var tmp1 = document.createElement("div");
		tmp1.innerHTML=dd_html;
		tmp1.id="tsxxDiv";
		document.forms[0].appendChild(tmp1);
		tmp1.style.display="none";	
	}else{
		$("tsxxDiv").innerHTML=dd_html;
	}
	viewTempDiv("��������","tsxxDiv",300,200);
}

//��÷��䷽ʽ
function getFpfs(){
	var blRad = $("blRad");
	var rsRad = $("rsRad");
	
	if(blRad.checked == true){
		return true;
	}else{
		return false;
	}	
}

//������������
function saveRssz(){
	var kzjb = $("kzjb").value;
	var rssx = jQuery("#rssx").value;
	var bl = "";
	var fprs = "";
	
	if($("bl")){
		bl=$("bl").value;
	}
	
	if($("fprs")){
		fprs=$("fprs").value;
	}
	
	if(getFpfs()){
		if(bl == ""){
			alert("�������ܲ���Ϊ�գ���ȷ�ϣ���");
			return false;
		}
	}else{
		if(fprs == ""){
			alert("������������Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if(rssx != "" && parseInt(fprs) > parseInt(rssx)){
			alert("�����������ܳ���������������ȷ�ϣ���");
			return false;
		}
	}
	
	//���Ƽ���ΪѧԺ
	if(kzjb == jQuery("#xbmc").val()){
		saveXyrs(bl,fprs);
	}
	//���Ƽ���Ϊרҵ
	else if(kzjb == "רҵ"){
		saveZyrs(bl,fprs);
	}
	//���Ƽ���Ϊ�༶
	else if(kzjb == "�༶"){
		saveBjrs(bl,fprs);
	}
}

//����ѧԺ����
function saveXyrs(bl,fprs){
	
	var szxy = $("xyR");	
	var msg = "";
	var url = "/xgxt/commXszz.do?method=rsszUpdate&doType=save";
	
	if(szxy.options.length == "0"){
		if(bl != ""){
			msg = "��ȫ��Ժϵ�ĸ�������Ŀ������������Ϊ" + bl + "%,��ȷ��";
		}else{
			msg = "��"+fprs+"��ƽ�������ȫ��Ժϵ,��ȷ��";
		}
	}else if(szxy.options.length != "0"){
		if(bl != ""){
			msg = "����ѡԺϵ�ĸ�������Ŀ������������Ϊ" + bl + "%,��ȷ��";
		}else{
			msg = "��"+fprs+"��ƽ���������ѡԺϵ,��ȷ��";
		}
	}
	
	if (confirm(msg)) {
		for(var i = 0 ; i <szxy.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "bmdm";
			tmp.value = szxy.options[i].value;
			document.forms[0].appendChild(tmp);
		}
		saveUpdate(url,'');
	}	
}

//����רҵ����
function saveZyrs(bl,fprs){
	
	var szzy = $("zyR");
	var msg = "";
	var url = "/xgxt/commXszz.do?method=rsszUpdate&doType=save";
	
	var nj =  $("nj").value;
	var xy =  $("xy");
	var xymc =  "";
	
	for(var i=0;i<xy.options.length;i++){
		if(xy.options[i].selected){
			xymc=xy.options[i].text;
		}
	}
	
	msg = "��";
	if(fprs != ""){
		msg += fprs+"��ƽ�������";
	}
	if(nj != ""){
		msg += nj +"��";
	}else{
		msg += "�����꼶";
	}
	if(xymc != ""){
		msg += xymc;
	}else{
		msg += "����"+jQuery("#xbmc").val();
	}
	if(szzy.options.length != "0"){
		msg += "��ѡרҵ";
	}else{
		msg += "����רҵ";
	}
	if(bl != ""){
		msg +="��������Ŀ������������Ϊ" + bl + "%";
	}
	msg += "����ȷ�ϣ�"
	
	if (confirm(msg)) {
		for(var i = 0 ; i <szzy.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "bmdm";
			tmp.value = szzy.options[i].value;
			document.forms[0].appendChild(tmp);
		}
		saveUpdate(url,'');
	}	
}

//����༶����
function saveBjrs(bl,fprs){
	
	var szbj = $("bjR");
	var msg = "";
	var url = "/xgxt/commXszz.do?method=rsszUpdate&doType=save";
	
	var nj =  $("nj").value;
	var xy =  $("xy");
	var zy =  $("zy");
	var xymc =  "";
	var zymc =  "";
	
	for(var i=0;i<xy.options.length;i++){
		if(xy.options[i].selected){
			xymc=xy.options[i].text;
		}
	}
	for(var i=0;i<zy.options.length;i++){
		if(zy.options[i].selected){
			if(zy.options[i].value != ""){
				zymc=zy.options[i].text;
			}
		}
	}
	
	msg = "��";
	
	if(fprs != ""){
		msg += fprs+"��ƽ�������";
	}
	if(nj != ""){
		msg += nj +"��";
	}else{
		msg += "�����꼶";
	}
	if(xymc != ""){
		msg += xymc;
	}else{
		msg += "����"+jQuery("#xbmc").val();
	}
	if(zymc != ""){
		msg += zymc;
	}else{
		msg += "����רҵ";
	}
	if(szbj.options.length != "0"){
		msg += "��ѡ�༶";
	}else{
		msg += "���а༶";
	}
	if(bl != ""){
		msg +="��������Ŀ������������Ϊ" + bl + "%";
	}
	msg += "����ȷ�ϣ�"
	
	if (confirm(msg)) {
		for(var i = 0 ; i <szbj.options.length; i++){
			var tmp = document.createElement("input");
			tmp.type = "hidden";
			tmp.name = "bmdm";
			tmp.value = szbj.options[i].value;
			document.forms[0].appendChild(tmp);
		}
		saveUpdate(url,'');
	}	
}

//���������б��ֵ
function selectListNull(){

	dwr.engine.setAsync(false);

	var kzjb = $("kzjb").value;

	if(kzjb == "רҵ"){
	
		initZyList();
		
		var zy = $("zy");

		for(var i = 0 ; i <zy.options.length; i++){
			var zydm = zy.options[i].value;
			if(zydm == null || zydm == ""){	
				zy.options[i] = null;
			}
		}
	}else if(kzjb == "�༶"){
		
		initBjList();
		
		var bj = $("bj");

		for(var i = 0 ; i <bj.options.length; i++){
			var bjdm = bj.options[i].value;
			if(bjdm == null || bjdm == ""){	
				bj.options[i] = null;
			}
		}
	}
	
	dwr.engine.setAsync(true);
}

//���ó�ʼֵ
function setXmshSelectList(){
	
	dwr.engine.setAsync(false);
	
	setXmsqSelectList();
	
	var tableName = $("xmb").value; 
	var pk = "xh||sqsj||xmdm";
	var pkValue = $("xh").value + $("sqsj").value + $("xmdm").value;

	var colList = ["xmzzjb","xmzzje","shzt1yj","shzt2yj","shzt3yj"]	
	
	getXszzInfo.getXszzInfo(tableName,pk,pkValue,colList,function(data){
		if(data!=null){
			if($("xmzzjb")){
				$("xmzzjb").value = data.xmzzjb==null ? "" : data.xmzzjb;
				if(data.xmzzjb!=null){
					setJeqk(data.xmzzjb);
				}
			}
			if($("xmzzje")){
				$("xmzzje").value = data.xmzzje==null ? "" : data.xmzzje;
			}
			if($("shzt1yj")){
				$("shzt1yj").value = data.shzt1yj==null ? "" : data.shzt1yj;
			}
			if($("shzt2yj")){
				$("shzt2yj").value = data.shzt2yj==null ? "" : data.shzt2yj;
			}
			if($("shzt3yj")){
				$("shzt3yj").value = data.shzt3yj==null ? "" : data.shzt3yj;
			}
		}
	});	
	
	dwr.engine.setAsync(true);
}

//������Ŀ����
function saveXmsq(){

	//��֤����¼���ֶ�
	var bxlr = document.getElementsByName("bxlr");
	
	var msg = "";
	
	for(var i=0;i<bxlr.length;i++){
		var nr = bxlr[i].value.split("!!@@!!");	
		var zdm = nr[0];
		var id =  nr[1];
		if($(id) && $(id).value == ""){
			msg+=zdm+"Ϊ�����������Ϊ��\n";
		}
	}
	
	if(msg != ""){
		msg+="��ȷ�ϣ�";
		alert(msg);
		return false;
	}
	
	var mklx = $("mklx").value;
	var url = "/xgxt/commXszz.do?method=xmsqUpdate&doType=save";
	url += "&mklx="+mklx;
	if (confirm("ȷ��������������?")) {	
		saveUpdate(url,'');	
	}
}


//��������
function saveZzxb(){

	//��֤����¼���ֶ�
	var bxlr = document.getElementsByName("bxlr");
	
	var msg = "";
	
	for(var i=0;i<bxlr.length;i++){
		var nr = bxlr[i].value.split("!!@@!!");	
		var zdm = nr[0];
		var id =  nr[1];
		if($(id) && $(id).value == ""){
			msg+=zdm+"Ϊ�����������Ϊ��\n";
		}
	}
	
	if(msg != ""){
		msg+="��ȷ�ϣ�";
		alert(msg);
		return false;
	}
	
	var url = "/xgxt/commXszz.do?method=zzxbUpdate&doType=save";
	
	if (confirm("ȷ��������������?")) {	
		saveUpdate(url,'');	
	}
}



//����ֻ����Ŀ
function setKzReadOnly(){

	dwr.engine.setAsync(false);
	
	var tableName = "xszz_xmnrzdb"; 
	
	var dm = "zd"; 
	var mc = "zd";
	var msg = "";
	var pk = "xmdm";
	var pkValue = "";
	var mrxm = $("mrxm").value;

	if(mrxm == "��"){
		pkValue = "��";
	}else{
		pkValue = $("xmdm").value;
	}
	
	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		
		if(data!=null && data.length >0 ){
			for(var i=0;i<data.length;i++){
				var id = data[i].dm;
				if($(id) && id != "xh" && id != "xmzzjb" && id != "xmzzje"){
					$(id).disabled = true;
				}
			}
		}
	});
	
	dwr.engine.setAsync(true);
}

//��Ŀ���ҳ���ʼ��
function onShow_xmsh(){
	var xmb = $("xmb").value;
	//��ʼ�������б�ֵ
	setXmshSelectList();
	//ֻ��
	setKzReadOnly();
	//���ü�ͥ��Ա��Ϣ
	setJtcyInfo();	
	
	if($("jtcyCtrl")){
		$("jtcyCtrl").style.display = "none";
	}
	if($('tTb')){
		$('tTb').disabled=true;
	}
	
	//���������ֵ
	setShyj();
}

//��Ŀ���ҳ���ʼ��
function onShow_xmjg(){

	var xmb = $("xmb").value;
	
	//��ʼ�������б�ֵ
	setXmshSelectList();
	
	if(xmb != "jtqkdcb"){
		//ֻ��
		//setKzReadOnly();
		setJtcyReadOnly();
	}
	if(xmb != "xsqtxxb"){
		setQtxxReadOnly();
	}
	//���ü�ͥ��Ա��Ϣ
	setJtcyInfo();	
	
	if($("jtcyCtrl") && xmb != "jtqkdcb" && (xmb != "xszz_knsb" 
		&& ("yes" != $('iskns').value || "true" != $('knsdl').value || "true" != $('jtqkdc').value))){
		$("jtcyCtrl").style.display = "none";
	}
	
	//���������ֵ
	setShyj();
}

//�жϽ���Ƿ񳬹�������
function checkJe(obj){
	
	var xmzzje = obj.value;

	var xxje = "";
	if($("xxje")){
		xxje = $("xxje").value;
	}

	var sxje = "";
	if($("sxje")){
		sxje = $("sxje").value;
	}

	var qdje = "";
	if($("qdje")){
		qdje = $("qdje").value;
	}

	if(xxje != "" && parseInt(xxje) > parseInt(xmzzje)){
		alert("���ܳ������޽���ȷ����");
		obj.focus();
	}

	if(sxje != "" && parseInt(sxje) < parseInt(xmzzje)){
		alert("���ܳ������޽���ȷ����");
		obj.focus();
	}

	if(qdje != "" && parseInt(qdje) != parseInt(xmzzje)){
		alert("����Ŀ���̶�Ϊ"+qdje+"�������޸ģ�");
		obj.value=qdje;
		obj.focus();
	}
}

//���ý�����
function setJeqk(fjmc){
	
	var tableName = "xszz_xmfjqkb"; 
	var pk = "xmdm||fjmc";
	var pkValue = $("xmdm").value + fjmc;
	var colList = ["fjxxje","fjsxje","fjqdje"]	
	
	dwr.engine.setAsync(false);
			
	getXszzInfo.getXszzInfo(tableName,pk,pkValue,colList,function(data){
		if(data!=null){
			if($("xxje") && data.fjxxje != "��"){
				$("xxje").value = data.fjxxje;
			}
			if($("sxje") && data.fjsxje != "��"){
				$("sxje").value = data.fjsxje;
			}
			if($("qdje") && data.fjqdje != "��"){
				$("qdje").value = data.fjqdje;
				$("xmzzje").value = data.fjqdje;
			}
			if(data.fjxxje == "��" && data.fjsxje == "��" && data.fjqdje == "��"){
				if($("xmzzje")){
				$("xmzzje").value = "���漰���";
				$("xmzzje").readOnly = true;}
			}
		}
	});	
	
	dwr.engine.setAsync(true);
}

//��Ŀ���
function xmsh(shzt){

	var jdkz = true;
	
	jQuery.ajaxSetup({async:false});
	if(shzt == "tg"){
		//��ÿ���---2012.3.9 qph--------
		var xh = jQuery('#xh').val();
		var xmdm = jQuery('#xmdm').val();
		jQuery.post('xszzAjax.do?method=checkBkjdxm',{xh:xh,xmdm:xmdm},function(data){
			var bkjdxm = data.xmdm;
			
			if (bkjdxm != '' && bkjdxm != null){
				alert('�Ѵ��ڲ��ɼ����Ŀ:{��Ŀ����:'+data.xmdm+',��Ŀ����:'+data.xmmc+'}');
				jdkz = false;
			}
		},'json');
	}


	if (jdkz && confirm("ȷ�����״̬?")) {
		var url = "/xgxt/commXszz.do?method=xmshUpdate&doType=save";
			url+= "&shzt="+shzt;
			
		if(shzt == "tg"){
			shzt = "ͨ��";
		}else{
			shzt = "��ͨ��";
		}
		
		var lx = $("lx").value;
		var shjb = $("shjb").value;

		if(shjb == "һ�����"){
			$("shzt1").value = shzt;
		}else if(shjb == "�������"){
		
			var jbdm1 = $("jbdm1").value;
			var jbdm2 = $("jbdm2").value;
			var jb = $("jb").value;

			if(jb == "Lv1"){
				if(jbdm1 != ""){
					var arrJb = jbdm1.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt1").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt1").value = shzt;
						}			
					}
				}
			}else{
				if(jbdm2 != ""){
					var arrJb = jbdm2.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt2").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt2").value = shzt;
						}			
					}
				}
			}
		}else if(shjb == "�������"){
		
			var jbdm1 = $("jbdm1").value;
			var jbdm2 = $("jbdm2").value;
			var jbdm3 = $("jbdm3").value;
			var jb = $("jb").value;

			if(jb == "Lv1"){
				if(jbdm1 != ""){
					var arrJb = jbdm1.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt1").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt1").value = shzt;
						}			
					}
				}
			}else if(jb == "Lv2"){
				if(jbdm2 != ""){
					var arrJb = jbdm2.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt2").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt2").value = shzt;
						}			
					}
				}
			}else{
				if(jbdm3 != ""){
					var arrJb = jbdm3.split("-");
					for(var i=0;i<arrJb.length;i++){
						var yhsh = lx+"sh";
						if(arrJb[i] == yhsh){
							$("shzt3").value = shzt;
						}else if(lx == "jd" && (arrJb[i] == "fdysh" || arrJb[i] == "bzrsh")){
							$("shzt3").value = shzt;
						}			
					}
				}
			}
		}
		
		if(lx == "bzr"){
			if($("bzrsh")){$("bzrsh").disabled = false;}
			if($("bzrsh")){$("bzrsh").value = shzt;}
		}else if(lx == "fdy"){
			if($("fdysh")){$("fdysh").disabled = false;}
			if($("fdysh")){$("fdysh").value = shzt;}
		}else if(lx == "jd"){
			if($("bzrsh")){$("bzrsh").disabled = false;}
			if($("fdysh")){$("fdysh").disabled = false;}
			if($("bzrsh")){$("bzrsh").value = shzt;}
			if($("fdysh")){$("fdysh").value = shzt;}
		}else if(lx == "xy"){
			if($("xysh")){$("xysh").disabled = false;}
			if($("xysh")){$("xysh").value = shzt;}
		}else if(lx == "xx"){
			if($("xxsh")){$("xxsh").disabled = false;}
			if($("xxsh")){$("xxsh").value = shzt;}
		}
		saveUpdate(url,"xmzzjb-xmzzje-fj");
	}
	jQuery.ajaxSetup({async:true});
}

//��������Ŀ�ֵ
function setShyj(){

	var qdje = "";
	if($("qdje")){
		qdje = $("qdje").value;
	}
	if($("xmzzje") && $("xmzzje").value == "null" ){
		$("xmzzje").value = qdje;
	}
	if($("shzt1yj") && $("shzt1yj").value == "null" ){
		$("shzt1yj").value = "";
	}
	if($("shzt2yj") && $("shzt2yj").value == "null" ){
		$("shzt2yj").value = "";
	}
	if($("shzt3yj") && $("shzt3yj").value == "null" ){
		$("shzt3yj").value = "";
	}
}

//���ü�ͥ��Աֻ��
function setJtcyReadOnly(){
	var tableName = "xszz_xmnrzdb"; 
	
	var dm = "zd"; 
	var mc = "zd";
	var msg = "";
	var pk = "xmdm||lyb";
	var pkValue = "";
	var mrxm = $("mrxm").value;
	
	if(mrxm == "��"){
		pkValue = "��jtqkdcb";
	}else{
		pkValue = $("xmdm").value+"jtqkdcb";
	}

	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null && data.length >0 ){
			for(var i=0;i<data.length;i++){
				var id = data[i].dm;
				if($(id)){
					$(id).disabled = true;
				}
			}
		}
	});
}

//����������Ϣֻ��
function setQtxxReadOnly(){
	var tableName = "xszz_xmnrzdb"; 
	
	var dm = "zd"; 
	var mc = "zd";
	var msg = "";
	var pk = "xmdm||lyb";
	var pkValue = "";
	var mrxm = $("mrxm").value;
	
	if(mrxm == "��"){
		pkValue = "��xsqtxxb";
	}else{
		pkValue = $("xmdm").value+"xsqtxxb";
	}

	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null && data.length >0 ){
			for(var i=0;i<data.length;i++){
				var id = data[i].dm;
				if($(id)){
					$(id).disabled = true;
				}
			}
		}
	});
}

//��Ŀ����ҳ���ʼ��
function onShow_xmsq(){
	var xmb = $("xmb").value;
	//������Ŀ���Ǽ�ͥ�������
	if(xmb != "jtqkdcb"){
		if (!(xmb == "xszz_knsb" && "yes" == $('iskns').value && "true" != $('knsdl').value && "true" != $('jtqkdc').value)){
			if($("jtcyCtrl")){
				$("jtcyCtrl").style.display = "none";
			}
			setJtcyReadOnly();
		}
		//setKzReadOnly();
	}
	if(xmb != "xsqtxxb"){
		setQtxxReadOnly();
	}
	//���������б���Ϣ
	setXmsqSelectList();
	//���ü�ͥ��Ա��Ϣ
	setJtcyInfo();	
}

function time(id){
	return showCalendar(id,'y-mm-dd');
}


//��������
function impInfo(){

	var xmdm = $("xmdm").value;
	
	if(xmdm == ""){
		alert("��ѡ����Ҫ�������Ŀ");
		return false;
	}else{
		
		dwr.engine.setAsync(false);
		
		var tableName="xszz_zzxmb";
		var colList =["xmb"];
		var pk = "xmdm";
		var pkValue = xmdm;

		getXszzInfo.getXszzInfo(tableName,pk,pkValue,colList,function(data){
			if(data != null){
				$("realTable").value=data.xmb;
			}	
		});
		
		dwr.engine.setAsync(true);
		
		impAndChkData()
	}
}

//��������
function expInfo(){

	var xmdm = $("xmdm").value;
	
	if(xmdm == ""){
		alert("��ѡ����Ҫ��������Ŀ");
		return false;
	}else{
		wjcfDataExport('commXszz.do?method=jgcxManage&doType=exp&xmdm='+xmdm);
	}
}

//���Ի���ѯ����
function getTjsz(){

	var xmdm = $("xmdm").value;
	
	if(xmdm != ""){
	
		dwr.engine.setAsync(false);
		
		var tableName = "xszz_zzxmb";
		var colList = ["sffj","shjb","sqzq"];
		var pk = "xmdm";
		var pkValue = xmdm;
		var query = "";
	
		getOtherData.getTableInfo(tableName, colList,pk, pkValue,query,function(data){
			if( data != null && data.length > 0){
				//�Ƿ�ּ�
				var sffj = data[0];
				//��˼���
				var shjb = data[1];
				//��������
				var sqzq = data[2];
				
				if (sffj == "�ּ�"){
					$("xmzzjb").disabled = false;
					$("jbId").style.display = "";
					$("jbVId").style.display = "";
					setXmjbList();
				}else{
					$("xmzzjb").disabled = true;
					$("jbId").style.display = "none";	
					$("jbVId").style.display = "none";
				}
				
				if (shjb == "һ�����"){
					$("shzt1").disabled = false;
					$("shzt2").disabled = true;
					$("shzt3").disabled = true;
					
					$("zt1Id").style.display = "";
					$("zt2Id").style.display = "none";
					$("zt3Id").style.display = "none";
					
					$("zt1VId").style.display = "";
					$("zt2VId").style.display = "none";
					$("zt3VId").style.display = "none";
					
				}else if (shjb == "�������"){
					$("shzt1").disabled = false;
					$("shzt2").disabled = false;
					$("shzt3").disabled = true;
					
					$("zt1Id").style.display = "";
					$("zt2Id").style.display = "";
					$("zt3Id").style.display = "none";
					
					$("zt1VId").style.display = "";
					$("zt2VId").style.display = "";
					$("zt3VId").style.display = "none";
					
				}else if (shjb == "�������"){
					$("shzt1").disabled = false;
					$("shzt2").disabled = false;
					$("shzt3").disabled = false;
					
					$("zt1Id").style.display = "";
					$("zt2Id").style.display = "";
					$("zt3Id").style.display = "";
					
					$("zt1VId").style.display = "";
					$("zt2VId").style.display = "";
					$("zt3VId").style.display = "";
					
				}else{
					
					$("shzt1").value = "";
					$("shzt2").value = "";
					$("shzt3").value = "";
					
					$("shzt1").disabled = true;
					$("shzt2").disabled = true;
					$("shzt3").disabled = true;
					
					$("zt1Id").style.display = "none";
					$("zt2Id").style.display = "none";
					$("zt3Id").style.display = "none";
					
					$("zt1VId").style.display = "none";
					$("zt2VId").style.display = "none";
					$("zt3VId").style.display = "none";
				}
				
				if (sqzq == "ѧ��"){
					$("xn").disabled = false;
					$("xq").disabled = true;
					$("nd").disabled = true;
				}else if (sqzq == "ѧ��"){
					$("xn").disabled = false;
					$("xq").disabled = false;
					$("nd").disabled = true;
				}else if (sqzq == "���"){
					$("xn").disabled = true;
					$("xq").disabled = true;
					$("nd").disabled = false;
				}else{
					$("xn").disabled = true;
					$("xq").disabled = true;
					$("nd").disabled = true;
				}
			}
		});
		
		dwr.engine.setAsync(true);
	}else{
	
		if($("jbId")){$("jbId").style.display = "none";}
	
		if($("zt1Id")){$("zt1Id").style.display = "none";}
		if($("zt2Id")){$("zt2Id").style.display = "none";}
		if($("zt3Id")){$("zt3Id").style.display = "none";}
					
		$("xn").disabled = false;
		$("xq").disabled = false;
		$("nd").disabled = false;
	}
}

//���÷ּ��б�ֵ
function setXmjbList(){

	dwr.engine.setAsync(false);
	
	var tableName = "xszz_xmfjqkb";
	var colList = ["fjmc"];
	var pk = "xmdm";
	var pkValue = $("xmdm").value;
	var xmjb = $("xmjb").value;
	var query = "";
	
	var options = new Array();
	var n = 0;
	options[n]={dm:"",mc:"----��ѡ��----"};
	n++;
				
	getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data) {
		if(data!=null&&data.length>0){
			for(var i=0;i<data.length;i++){
				options[n]={dm:data[i].fjmc,mc:data[i].fjmc};
				n++;
			}
		}
		options[n]={dm:"δָ��",mc:"δָ��"};
		n++;
	});
					
	var id = "xmzzjb";
	DWRUtil.removeAllOptions(id);
	DWRUtil.addOptions(id,options,"dm","mc");
	
	if(xmjb != ""){
		$(id).value = xmjb;
	}
	dwr.engine.setAsync(true);
		
}

//�޸���Ŀ���
function chXmlb(){
	var tableName = "xszz_zzxmb"; 
	var dm = "xmdm"; 
	var mc = "xmmc";
	var msg = "----��ѡ��----";
	var pk = "xmlb";
	var pkValue = $("xmlb").value;
	var id = "xmdm";
		
	if(pkValue == ""){
		pk = "";
	}
			
	getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
		if(data!=null){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,data,"dm","mc");
			$(id).options[0].value = "";
		}
	});
}

//չ�����ҳ��
function showAddJgJsp(){

	var xmdm = $("xmdm").value;

	if(xmdm == ""){
		alert("��ѡ��ϣ����ӵ�������Ŀ��");
		return false;
	}
	var iskns = $('iskns').value;
	var url = "/xgxt/commXszz.do?method=jgcxUpdate&iskns="+iskns;
		url+= "&xmdm="+xmdm;
		
	showOpenInfo(url,'add','','800','600');
}

//�ļ��ϴ�
function fileUpload(url) {
	var d_width = document.body.clientWidth;
	var d_height = document.body.clientHeight ;
	var d_left = 0;
	var d_top = 0;
	var d_color = "#EEF4F9";
	var d_width_top = 400;
	var d_height_top = 250;
	var d_left_top = (d_width - d_width_top) / 2;
	var d_top_top = (d_height - d_height_top) / 0.5;
	var d_color_top = "#EEF4F9";
	dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px;'>";
	dd_html += "</div>";
	dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
	dd_html += "<iframe name='mainFrame' style='height:100%; width:100%; ' marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' src='";
	dd_html += url;
	dd_html += "'></iframe>";
	dd_html += "</div>";
	tmpdiv1.innerHTML = dd_html;
}	



var array = new Array();

//����Ƿ��й��м�¼,����ǹ��еķ������鹩����������
function isChecked(tagName) {
	var checkBoxArr = document.getElementsByName(tagName);
	var flag = false;
	var n = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag = true;
			array[n] = checkBoxArr[i];
			n++;
		}
	}
	if (flag) {
		if (confirm("\u786e\u5b9a\u8981\u6279\u91cf\u8bbe\u7f6e\u6240\u9009\u62e9\u7684\u6570\u636e\u5417\uff1f")) {
			return true;
		}
	} else {
		alert("\u6ca1\u6709\u9009\u62e9\u76f8\u5e94\u8bb0\u5f55\uff0c\u8bf7\u9009\u62e9\u4e4b\u540e\u518d\u8fdb\u884c\u64cd\u4f5c!");
		return false;
	}
}

//�ж��ַ��Ƿ��Ѿ�
function checkZs(){
	num=$("rssx").value;
	if(num.length>1 && num.substring(0,1)=="0"){
		alert("�������ޱ���Ϊ����!");
		$("rssx").focus();
		return false;
	}
	
	//ƽ���ɼ�
	num=$("pjcjz").value;
	//����С��
	if($("pjcjtj").value!="" && num.indexOf(".")=="-1" 
		&& num.length>1 && num.substring(0,1)=="0"){
		
		alert("ƽ���ɼ������ʽ����ȷ!");
		$("pjcjz").focus();
		return false;
	}else if($("pjcjtj").value!="" && !isDouble(num)){
		alert("ƽ���ɼ������ʽ����ȷ!");
		$("pjcjz").focus();
		return false;
	}
	
	//�������Ŵ�
	num=$("bjgkms").value;
	if(num.length>1 && num.substring(0,1)=="0"
		&& !$('bjgkm').checked){
		alert("�������Ŵα���Ϊ����!");
		$("bjgkms").focus();
		return false;
	}
	saveXmwh();
}







