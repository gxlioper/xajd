var DCCLBH="xsxx_gygl_xsxxcj.do";
function addXsxxcj(){
	showDialog('����ѧ����Ϣ�ɼ�',800,520,'xsxx_gygl_xsxxcj.do?method=addXsxxcj');
}

function save(){
	var flag=true;
	flag=checkNotNull();
	if(!flag){
		return false;
	}
	var url = "xsxx_gygl_xsxxcj.do?method=addXsxxcj&type=save";
    ajaxSubFormWithFun("xsxxcjForm",url,function(data){
  	 if(data["message"]=="����ɹ���"){
  		 showAlert(data["message"],{},{"clkFun":function(){
  				if (parent.window){
  					refershParent();
  				}
  			}});
  	 }else{
  		 showAlert(data["message"]);
  		 
  	 }
	});
}
function update(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'xsxx_gygl_xsxxcj.do?method=updateXsxxcj&xh='+rows[0]["xh"];
		var title = "�޸�ѧ���ɼ���Ϣ";
		showDialog(title,800,520,url);
	}
}

function viewXsxxcj(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
		var url = 'xsxx_gygl_xsxxcj.do?method=viewXsxxcj&xh='+rows[0]["xh"];
		var title = "�鿴ѧ���ɼ���Ϣ";
		showDialog(title,800,520,url);
	}
}
function xsxxcjView(xh){
	var url = 'xsxx_gygl_xsxxcj.do?method=viewXsxxcj&xh='+xh;
	var title = "�鿴ѧ���ɼ���Ϣ";
	showDialog(title,800,520,url);
}


function saveUpdate(){
	var flag=true;
	flag=checkNotNull();
	if(!flag){
		return false;
	}
	var url = "xsxx_gygl_xsxxcj.do?method=updateXsxxcj&type=update";
	ajaxSubFormWithFun("xsxxcjForm",url,function(data){
	  	 if(data["message"]=="����ɹ���"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  				if (parent.window){
	  					refershParent();
	  				}
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
	});
	
}


function delXsxxcj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
		jQuery.post("xsxx_gygl_xsxxcj.do?method=delXsxxcj",{values:ids.toString()},function(data){
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		},'json');
		}});
	}
}

function saveXscj(){
	var flag=true;
	flag=checkNotNull();
	if(!flag){
		return false;
	}
	var url = "xsxx_gygl_xsxxcj.do?method=saveXsxxcj";
	ajaxSubFormWithFun("xsxxcjForm",url,function(data){
	  	 if(data["message"]=="����ɹ���"){
	  		 showAlert(data["message"],{},{"clkFun":function(){
	  			}});
	  	 }else{
	  		 showAlert(data["message"]);
	  		 
	  	 }
	});
	
}

function exportConfig(){
	customExport(DCCLBH, exportData);
}

//������ѯ���
function exportData(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_gygl_xsxxcj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����ѧ��������Ϣͳ�ơ�̨�ˡ�

function exportXsjbxxtz(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_gygl_xsxxcj.do?method=exportXsjbxxtz";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�����������ͳ�ơ�̨�ˡ�

function exportXsknxxtz(){
	setSearchTj();//���ø߼���ѯ����
	var url = "xsxx_gygl_xsxxcj.do?method=exportXsknxxtz";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}


function checkNotNull(){
	var xh=jQuery("#xh").val();
	if(xh==null||xh==""){
		showAlert("��ѡ��ѧ����");
		flag=false;
		return false;
	}
	
	
	//�������ڵ��ж�
	var hkszdshen=jQuery("#hkszdshen").val();
	var hkszdshi=jQuery("#hkszdshi").val();
	var hkszdxian=jQuery("#hkszdxian").val();
	var hkszdz=jQuery("#hkszdz").val();
	if(hkszdshen==""||hkszdshen==null||hkszdshi==""||hkszdshi==null||hkszdxian==""||hkszdxian==null||hkszdz==""||hkszdz==null){
		showAlert("����д�����������ڵأ�");
		flag=false;
		return false;
	}
	//��ͥ��ַ�ж�
	var jtdzshen=jQuery("#jtdzshen").val();
	var jtdzshi=jQuery("#jtdzshi").val();
	var jtdzxian=jQuery("#jtdzxian").val();
	var jtdzz=jQuery("#jtdzz").val();
	if(jtdzshen==""||jtdzshen==null||jtdzshi==""||jtdzshi==null||jtdzxian==""||jtdzxian==null||jtdzz==""||jtdzz==null){
		showAlert("����д������ͥ��ַ��");
		flag=false;
		return false;
	}
	//��Դ���ж�
	var sydshen=jQuery("#sydshen").val();
	var sydshi=jQuery("#sydshi").val();
	var sydxian=jQuery("#sydxian").val();
	if(sydshen==""||sydshen==null||sydshi==""||sydshi==null||sydxian==""||sydxian==null){
		showAlert("����д������Դ�أ�");
		flag=false;
		return false;
	}
	//�����ж�
	var jgshen=jQuery("#jgshen").val();
	var jgshi=jQuery("#jgshi").val();
	var jgxian=jQuery("#jgxian").val();
	if(jgshen==""||jgshen==null||jgshi==""||jgshi==null||jgxian==""||jgxian==null){
		showAlert("����д�������ᣡ");
		flag=false;
		return false;
	}
	
	var jtlxfs=jQuery("#jtlxfs").val();
	if(jtlxfs==""){
		showAlert("����д��ͥ��ϵ��ʽ��");
		flag=false;
		return false;
	}
	
	//�뵳����Ϊ���ǡ��ж�
	var sfsqrd=jQuery("#sfsqrd").val();
	if(sfsqrd=="��"){
		var djsqssj=jQuery("#djsqssj").val();
		var rdsj=jQuery("#rdsj").val();
		var zzsj=jQuery("#zzsj").val();
		if(djsqssj==null||djsqssj==""){
			showAlert("����д�뵳��Ϣ��");
			flag=false;
			return false;
		}
	}
	
	//��������Ϊ���ǡ��ж�
	var sfssmz=jQuery("#sfssmz").val();
	if(sfssmz=="��"){
		var ssmz=jQuery("#ssmz").val();
		if(ssmz==null||ssmz==""){
			showAlert("����ѡ���������壡");
			flag=false;
			return false;
		}
	}
	
	//��ʱ��ѵΪ���ǡ��ж�
	var sflspx=jQuery("#sflspx").val();
	if(sflspx=="��"){
		var pxmc=jQuery("#pxmc").val();
		if(pxmc==null||pxmc==""){
			showAlert("����д��ѵ���ƣ�");
			flag=false;
			return false;
		}
	}
	
	//�ڽ�����Ϊ���ǡ��ж�
	var sfzjxy=jQuery("#sfzjxy").val();
	if(sfzjxy=="��"){
		var xyzjmc=jQuery("#xyzjmc").val();
		var cjzjsj=jQuery("#cjzjsj").val();
		if(cjzjsj==null||cjzjsj==""||xyzjmc==null||xyzjmc==""){
			showAlert("����д�ڽ���Ϣ��");
			flag=false;
			return false;
		}
	}
	
	//��������Ϊ���ǡ��ж�
	var sfjjkn=jQuery("#sfjjkn").val();
	if(sfjjkn=="��"){
		var jjknyy=jQuery("#jjknyy").val();
		if(jjknyy==null||jjknyy==""){
			showAlert("����д��������ԭ��");
			flag=false;
			return false;
		}
	}
	
	//�����Ƿ񼲲�Ϊ���ǡ��ж�
	var stsfcj=jQuery("#stsfcj").val();
	if(stsfcj=="��"){
		var stcjyy=jQuery("#stcjyy").val();
		if(stcjyy==null||stcjyy==""){
			showAlert("����д���弲��ԭ��");
			flag=false;
			return false;
		}
	}
	
	//ѧϰ����Ϊ���ǡ��ж�
	var sfxxkn=jQuery("#sfxxkn").val();
	if(sfxxkn=="��"){
		var xxknyy=jQuery("#xxknyy").val();
		if(xxknyy==null||xxknyy==""){
			showAlert("����дѧϰ����ԭ��");
			flag=false;
			return false;
		}
	}
	
	//��������Ϊ���ǡ��ж�
	var sfxlkr=jQuery("#sfxlkr").val();
	if(sfxlkr=="��"){
		var xlkryy=jQuery("#xlkryy").val();
		if(xlkryy==null||xlkryy==""){
			showAlert("����д��������ԭ��");
			flag=false;
			return false;
		}
	}
	
	//��ͥ����Ϊ���ǡ��ж�
	var sfjtkr=jQuery("#sfjtkr").val();
	if(sfjtkr=="��"){
		var jtkryy=jQuery("#jtkryy").val();
		if(jtkryy==null||jtkryy==""){
			showAlert("����д��ͥ����ԭ��");
			flag=false;
			return false;
		}
	}
	
	//��������Ϊ���ǡ��ж�
	var sfyqtkr=jQuery("#sfyqtkr").val();
	if(sfyqtkr=="��"){
		var qtkryy=jQuery("#qtkryy").val();
		if(qtkryy==null||qtkryy==""){
			showAlert("����д��������ԭ��");
			flag=false;
			return false;
		}
	}
	return true;
	
}
