//�ı������Ŀ
function changeCzxm(xmdm){
	var url	="/xgxt/sjyJcsjcsh.do?method=jcsjcshManage";
		url+="&czxm="+xmdm;			
		refreshForm(url);
}

//ͬ������
function tbData(){
	if (confirm("��ȷ���Ƿ�Ҫ�ӽӿ�ͬ�����ݣ�\nע��ԭ�м�¼�������ã����������")) {
		saveUpdate('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=tb','');
	}
}

//��ʾ����
function showRule(){
	var czxm = $("czxm").value;
	var url = "/xgxt/sjyJcsjcsh.do?method=ruleManage";
		url+="&czxm="+czxm;	

	showTopWin(url,600,550);
}

//�ύ������Ϣ
function submitBmInfo(lx){

	var message = "";
	
	if(lx == "all"){
	
		message +="��ȷ���Ƿ�Ҫ����Ϣ�����ύ����ʽ�⣿\n\n";
		message +="ע��ԭ�������Ϣ�����ã������������\n";
		message +="    ����в����Ϲ���ļ�¼�����ᱻ��\n";
		message +="    �������ڡ������ƶ�������ȷ�ϡ�";
		
		if (confirm(message)) {
			if($("div_tsxx")){
				if($("div_toolbox")){
					$("div_toolbox").style.display="none";
				}
				if($("div_main_box")){
					$("div_main_box").style.display="none";
				}
				if($("div_tsxx")){
					$("div_tsxx").style.display="";
				}
				refreshForm("/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=allSubmit");
			}else{
				saveUpdate('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=allSubmit','');
			}
		}
	}else{
	
		var num =  document.getElementsByName("primarykey_checkVal").length;
		var flag = false;
		
		for(var i=0;i<num;i++){
			var obj = document.getElementsByName("primarykey_checkVal")[i];
			if(obj.checked){
				flag = true;
				break;
			}
		}
		
		if(flag){
			message +="��ȷ���Ƿ�Ҫ����ѡ��¼�ύ����ʽ�⣿\n\n";
			message +="ע��ԭ�������Ϣ�����ã������������\n";
			message +="    ����в����Ϲ���ļ�¼�����ᱻ��\n";
			message +="    �������ڡ������ƶ�������ȷ�ϡ�";
			
			if (confirm(message)) {
				saveUpdate('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage&doType=submit','');
			}
		}else{
			alert("�빴ѡ��Ҫ�ύ����ʽ�������");
			return false;
		}
	}
}