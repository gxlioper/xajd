function baseDataInit(lb) {
	if (confirm("��ʼ������������յ�ǰѧ�ꡢѧ�ڵ��������ݣ����������ɡ�\n���ܺķѽϳ�ʱ�䣬ȷ��Ҫ��ʼ����?")) {
		var dd_html = "";
		dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>���ڳ�ʼ�����ݣ����Ժ�......<br><br>";
		dd_html += "<span class='roll_tip'></span>";
		dd_html += "</td></tr></table>";
		for (i = 1; i < document.getElementsByTagName("table").length; i++) {
			document.getElementsByTagName("table")[i].style.display = "none";
		}
		showDiv(dd_html, 300, 120);
		refreshForm("pjpyzjkj.do?method=baseDataInit&lb="+lb);
		return true;
	}
	return false;
}	

function rstz(url,lx){
	//�û�����
	var userType = val('userType');
	var dm = val("dm");
	//����ѡ��Ҫ���������Ľ�ѧ��������ƺ�
	if(dm == ""){
		var msg = "��ѡ��" + (lx == "jxj" ? "��ѧ��" : "�����ƺţ�");
		alert(msg);
		return false;		
	}
	//��ʾ��Χ
	var fs = val('fs');
	
	//ҳ�����ݼ��
	if("xy" == userType){//ѧԺ�û�		
		if("xy" == fs){
			alert(jQuery("#xbmc").val()+"�û�ֻ�ܵ���רҵ��༶������");
			return false;
		}else if("zy" == fs){
			if(val('xy') == null || val('xy') == ""){
				alert("��ѡ��"+jQuery("#xbmc").val()+"��");
				return false;
			}
			if(val('nj') == null || val('nj') == ""){
				alert("��ѡ���꼶��");
				return false;
			}
		}else if("bj" == fs){
			if(val('zy') == null || val('zy') == ""){
				alert("��ѡ��רҵ��");
				return false;
			}
			if(val('nj') == null || val('nj') == ""){
				alert("��ѡ���꼶��");
				return false;
			}
		}
	}else{//ѧУ�û�
		if("xy" != fs){
			alert("ѧУ�û�ֻ�ܵ���"+jQuery("#xbmc").val()+"������");
			return false;
		}
	}
	//�ύ
	refreshForm(url);
}