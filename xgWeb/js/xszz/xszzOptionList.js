var sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
			  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
			  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
			  "ywzm","sfbk","sfhdqtzz","sxzhpm"];
			  
var djList = ["pdbx","cjdj"];

//���ó�ʼֵ(����)
function setXmsqSelectList(){
	
	var xxdm = $("xxdm").value;
		
	dwr.engine.setAsync(false);
	
	setSfList(xxdm);

	//�Ƿ��б�
	if(sfList != null && sfList.length > 0){
	
		for(var i=0;i<sfList.length;i++){
			var id = sfList[i];	
			if($(id)){
			
				var options = [{dm:"��",mc:"��"},{dm:"��",mc:"��"}];
					DWRUtil.removeAllOptions(id);
					DWRUtil.addOptions(id,options,"dm","mc");
					
				var selectId = "select_"+id;
				$(id).value = $(selectId).value;
			}		
		}
	}
	
	//�ȼ��б�
	if(djList != null && djList.length > 0){
	
		for(var i=0;i<djList.length;i++){
			var id = djList[i];	
			if($(id)){
			
				var msg = "----��ѡ��----";
				var options = [{dm:"",mc:msg},{dm:"����",mc:"����"},{dm:"����",mc:"����"},{dm:"�е�",mc:"�е�"},{dm:"һ��",mc:"һ��"},{dm:"�ϲ�",mc:"�ϲ�"}];
					DWRUtil.removeAllOptions(id);
					DWRUtil.addOptions(id,options,"dm","mc");
					
				var selectId = "select_"+id;
				$(id).value = $(selectId).value;
			}		
		}
	}
	
	//���������ȼ�
	if($("dypddj")){

		var tableName = "hndx_xszz_dydjb"; 
		var dm = "dm"; 
		var mc = "mc";
		var msg = "----��ѡ��----";
		var pk = "";
		var pkValue = "";
		var id = "dypddj";
		
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
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
		
	//ѧ�����
	if($("xslb")) {	
		var id = "xslb";
		var options = [{dm:"",mc:msg},{dm:"��ʦ����",mc:"��ʦ����"},{dm:"ʦ������",mc:"ʦ������"},{dm:"��ְ����",mc:"��ְ����"},{dm:"��ְר��",mc:"��ְר��"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//��ͥ����
	if($('jthk')) {
		
		var id = "jthk";
		var options = [{dm:"",mc:msg},{dm:"����",mc:"����"},{dm:"ũ��",mc:"ũ��"}];
		if("10477" == xxdm){//����ʦ��ѧԺ
			options = [{dm:"",mc:msg},{dm:"ũҵ����",mc:"ũҵ����"},{dm:"��ũҵ����",mc:"��ũҵ����"},{dm:"δ֪",mc:"δ֪"}];
		}
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//���ҵ��ѧ-----�Ա�
	if($('kzzd1') && xxdm == '10058') {
		
		var id = "kzzd1";
		var options = [{dm:"",mc:msg},{dm:"��",mc:"��"},{dm:"Ů",mc:"Ů"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//��ͥ��������
	if($("jtnzsr")){
		var id = "jtnzsr";
		var options = [{dm:"",mc:msg},{dm:"2000Ԫ����",mc:"2000Ԫ����"},{dm:"2000-3000Ԫ",mc:"2000-3000Ԫ"},
		               {dm:"3000-5000Ԫ",mc:"3000-5000Ԫ"},{dm:"5000Ԫ����",mc:"5000Ԫ����"}];
		if ($("jtnzsr").tagName.toLowerCase() =="select"){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,options,"dm","mc");
			
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		}		
	}
	//��ͥ��������
	if($("jtyzsr")){
		var id = "jtyzsr";
		var options = [{dm:"",mc:msg},{dm:"300Ԫ����",mc:"300Ԫ����"},{dm:"300-600Ԫ",mc:"300-600Ԫ"},
		               {dm:"600-1000Ԫ",mc:"600-1000Ԫ"},{dm:"1000Ԫ����",mc:"1000Ԫ����"}];
		if ($("jtyzsr").tagName.toLowerCase() =="select"){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,options,"dm","mc");
			
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		}		
	}
	//���ж���ѧ����
	if($("jtddxrs")){
		var id = "jtddxrs";
		var options = [{dm:"",mc:msg},{dm:"4",mc:"4"},{dm:"3",mc:"3"},
		               {dm:"2",mc:"2"},{dm:"1",mc:"1"}];
		if ($("jtddxrs").tagName.toLowerCase() =="select"){
			DWRUtil.removeAllOptions(id);
			DWRUtil.addOptions(id,options,"dm","mc");
			
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		}		
	}
	//��ĸ����
	if($('fmjk')){
		var id = "fmjk";
		var options = [{dm:"��",mc:"��"},{dm:"������",mc:"������"},{dm:"ĸ����",mc:"ĸ����"},
		               {dm:"��ĸ����",mc:"��ĸ����"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//����״�� 
	if($('jkzk')) {
		var id = "jkzk";
		var options = [{dm:"",mc:msg},{dm:"����",mc:"����"},{dm:"һ��",mc:"һ��"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//��ĸ�������
	if($('fmjz')){
		var id = "fmjz";
		var options = [{dm:"��ĸ˫ȫ",mc:"��ĸ˫ȫ"},{dm:"��ĸ˫��",mc:"��ĸ˫��"},{dm:"����ĸ��",mc:"����ĸ��"},
		               {dm:"����ĸ��",mc:"����ĸ��"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//ƶ���ȼ�
	if($('pkxjb')){
		var id = "pkxjb";
		var options = [{dm:"���Ҽ�ƶ����",mc:"���Ҽ�ƶ����"},{dm:"ʡ��ƶ����",mc:"ʡ��ƶ����"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}

	// ʡ����
	if($('szsheng') && $('szshi') && $('szxian')){
		setHjxfHndx('szsheng','szshi','szxian');
	}

	//����ɷѷ�ʽ
	if($('sqjffs')) {
		var id = "sqjffs";
		var options = [{dm:"",mc:msg},{dm:"����",mc:"����"},{dm:"����",mc:"����"},{dm:"����",mc:"����"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//�W����r�u�r
	if($('xxqkpj')) {
		var id = "xxqkpj";
		var options = ["   ","��","��","һ��","��"];
		
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options);
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//ѧ�Ѽ����������
	if($('sqlb')) {
		var id = "sqlb";
		var options = ["   ","ȫ��","������"];
		
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options);
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//���޲������ü�¼
	if($('sfblxy')) {
		var id = "sfblxy";
		var options = ["   ","��","��"];
		
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options);
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	//У��
	if($("xiaoqudm")){		
		var tableName = "dm_zju_xq"; 
		var dm = "dm"; 
		var mc = "xqmc";
		var msg = "----��ѡ��----";
		var pk = "";
		var pkValue = "";
		var id = "xiaoqudm";
		
		if(pkValue == ""){
			pk = "";
		}
			
		getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,data,"dm","mc");
				$(id).options[0].value = "";
			}
			var selectId = "select_"+id;
			$(id).value = $(selectId).value;
		});
	}
	
	//����״�� 
	if($('qy')) {
		var msg = "----��ѡ��----";
		var id = "qy";
		var options = [{dm:"",mc:msg},{dm:"����",mc:"����"},{dm:"����",mc:"����"},{dm:"�в�",mc:"�в�"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	//�Ƿ��иߵ�ѧУѧ����ͥ����϶���
	if($('kzzd8') && xxdm == '10488') {
		
		var id = "kzzd8";
		var options = [{dm:"��",mc:"��"},{dm:"��",mc:"��"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	//�Ƿ��и߸ߵ�ѧУѧ����ͥ��������
	if($('kzzd9') && xxdm == '10488') {
		
		var id = "kzzd9";
		var options = [{dm:"��",mc:"��"},{dm:"��",mc:"��"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		
		var selectId = "select_"+id;
		$(id).value = $(selectId).value;
	}
	
	dwr.engine.setAsync(true);
}


function setSfList(xxdm){
	if(xxdm=="88888"){
		sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
			  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
			  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
			  "ywzm","sfbk","sfhdqtzz","sxzhpm","kzzd1","kzzd2","kzzd3","kzzd4",
			  "kzzd5","kzzd6","kzzd7"];
	}else if(xxdm=="10488"){
		sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
					  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
					  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
					  "ywzm","sfbk","sfhdqtzz","sxzhpm","kzzd8","kzzd9"];
	}else if(xxdm=="10504"){
		sfList = ["sfgc","lszn","sfdq","sfdb","sfydk","sxqsfqg","bxqsfqg","bxnsfdk",
			  "bxnsfjm","bxnsfqg","bxnsfjxj","bxnsfbz","bxnsfszz","sfjf","sfdk",
			  "sfge","sfcj","sfpkdqssmz","sfyfjt","sfdz","sfqz","sfpkx","sfbgb",
			  "ywzm","sfbk","sfhdqtzz","sxzhpm", "kzzd4","kzzd7","kzzd8"];
	}
}